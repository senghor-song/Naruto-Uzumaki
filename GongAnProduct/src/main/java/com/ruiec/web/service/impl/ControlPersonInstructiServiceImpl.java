/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.dto.IssuedJSONDTO;
import com.ruiec.web.entity.ControlPersonAlarm;
import com.ruiec.web.entity.ControlPersonInstructi;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.entity.UserUnit;
import com.ruiec.web.service.ControlPersonAlarmService;
import com.ruiec.web.service.ControlPersonInstructiService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.service.UserUnitService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 重点人员预警指令服务接口实现类
 * 
 * @author Senghor<br>
 * @date 2017年11月30日 上午9:01:37
 */
@Repository("controlPersonInstructiServiceImpl")
public class ControlPersonInstructiServiceImpl extends BaseServiceImpl<ControlPersonInstructi, Integer> implements ControlPersonInstructiService {

	@Resource
	private UserUnitService userUnitService;
	@Resource
	private UserService userService;
	@Resource
	private ControlPersonAlarmService controlPersonAlarmService;
	@Resource
	private UnitService unitService;
	@Resource
	private DictionaryService dictionaryService;

	/**
	 * 判断权限 1：签收 2：反馈 3：审核 4：查看
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月25日 上午10:07:29
	 */
	@Override
	@Transactional
	public boolean isPower(User user, LoginUserUnit loginUserUnit, Integer id, int type) {
		// 指令
		ControlPersonInstructi cpi = super.get(id);
		if (cpi == null) {
			return false;
		}
		switch (type) {
		// 签收操作
		case 1:
			// 判断指令接收人
			if (cpi.getInstructionRecipientPrikey().equals(user.getId())) {
				return true;
			}
			break;
		// 反馈操作
		case 2:
			// 判断指令接收人
			if (cpi.getInstructionRecipientPrikey().equals(user.getId())) {
				return true;
			}
			break;
		// 审核
		case 3:
			// 判断指令下发人,超管放行s
			if (cpi.getIssuedPolicePrikey().equals(user.getId()) || user.getId() == 1) {
				return true;
			}
			break;
		// 查看
		case 4:
			// 管理员是否管理下发单位
			if (isManager(loginUserUnit)) {
				if (isManagerUnit(loginUserUnit, cpi.getIssuedPoliceUnitId())) {
					return true;
				}
			}
			// 判断指令下发人和指令接收人,超管放行
			if (cpi.getIssuedPolicePrikey().equals(user.getId()) || cpi.getInstructionRecipientPrikey().equals(user.getId()) || user.getId() == 1) {
				return true;
			}
			break;
		/*
		 * // 下发 case 5: // 是否管理员 if (isManager(loginUserUnit)) { // 判断指令接收人 if
		 * (cpi.getInstructionRecipientPrikey().equals(user.getId())) { return
		 * true; } } break;
		 */
		default:
			break;
		}
		return false;
	}

	/**
	 * 是否为管理员
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月20日 上午11:30:03
	 */
	private boolean isManager(LoginUserUnit loginUserUnit) {
		if (loginUserUnit != null) {
			if (!"警员".equals(loginUserUnit.getUserName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 管理员是否管理该单位
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月20日 上午11:30:03
	 */
	private boolean isManagerUnit(LoginUserUnit loginUserUnit, Integer unitId) {
		if (null != loginUserUnit.getUnitSonIds() && loginUserUnit.getUnitSonIds().size() > 0) {
			for (int i = 0; i < loginUserUnit.getUnitSonIds().size(); i++) {
				if (unitId.equals(loginUserUnit.getUnitSonIds().get(i))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 预警下发
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月8日 下午4:23:33
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public JsonReturn issued(Integer[] unitIds, Integer id, User user, LoginUserUnit loginUserUnit, Integer[] userIds, String content) {

		// 1、查询预警数据
		ControlPersonAlarm controlPersonAlarm = controlPersonAlarmService.get(id);
		// 2、判断下发权限
		boolean isOK = false;
		if (user.getId() == 1) {
			isOK = true;
		} else {
			if (isManager(loginUserUnit)) {
				if (isManagerUnit(loginUserUnit, controlPersonAlarm.getControlUnit())) {
					isOK = true;
				}
			}
		}
		if (!isOK) {
			new JsonReturn(400, "无下发权限");
		}

		Date date = new Date();
		// 3、指令和反馈以JSON进行追加
		IssuedJSONDTO issuedJSONDTO = new IssuedJSONDTO();
		issuedJSONDTO.setContent(content);
		issuedJSONDTO.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(date));

		Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
		issuedJSONDTO.setDepartment(unit.getUnitName());
		issuedJSONDTO.setPoliceName(user.getPoliceName());
		issuedJSONDTO.setType("1");
		JSONArray jsonObject = JSONArray.fromObject(issuedJSONDTO);

		if (controlPersonAlarm.getDistributeStatus() == 0) {
			// 4、修改预警下发状态
			controlPersonAlarm.setDistributeStatus(1);
			controlPersonAlarmService.update(controlPersonAlarm);
		}
		// 5、查询预警指令已下发单位
		/*
		 * List<ControlPersonInstructi> cpiList = findList(null,
		 * Filter.eq("controlPersonAlarm", controlPersonAlarm), null); for
		 * (UserUnit userUnit:userUnitList) { for (ControlPersonInstructi cpi :
		 * cpiList) { if
		 * (cpi.getInstructionRecipientUnitId()==userUnit.getUnit().getId()) {
		 * return new JsonReturn(400,userUnit.getUnit().getUnitName()+"已下发"); }
		 * } }
		 */
		// 下发到单位
		if (unitIds != null && unitIds.length > 0) {
			// 1、查询管理员id
			JsonReturn jsonReturn = this.userUnitService.checkByIds(unitIds, user.getId());
			if (jsonReturn.getCode() == 400) {
				return jsonReturn;
			}
			List<UserUnit> userUnitList = (List<UserUnit>) jsonReturn.getData();
			// 6、新增下发预警指令
			for (UserUnit userUnit : userUnitList) {
				// 添加下发实体信息
				ControlPersonInstructi controlPersonInstructi = new ControlPersonInstructi();
				// 身份证号
				controlPersonInstructi.setControlPerson(controlPersonAlarm.getControlPerson());
				// 动态信息
				controlPersonInstructi.setDynamicsInformation(controlPersonAlarm.getDynamicInfoName());
				// 四色预警，1是红色2是橙色3是黄色4是橙色
				controlPersonInstructi.setFourColourWarning(Integer.parseInt(controlPersonAlarm.getWarningLevel()));
				// 指令状态 0为未签收 1为已签收 2为待审核 3未审核通过 4未审核拒绝
				controlPersonInstructi.setInstructionsState(0);
				// 下发人的民警ID
				controlPersonInstructi.setIssuedPolicePrikey(user.getId());
				// 下发人的单位ID
				controlPersonInstructi.setIssuedPoliceUnitId(user.getUnitId());
				// 关联预警
				controlPersonInstructi.setcontrolPersonAlarm(controlPersonAlarm);
				// 指令和反馈以JSON进行追加
				controlPersonInstructi.setInstructionFeedbackInformati(jsonObject.toString());
				// 指令接受人的民警ID
				controlPersonInstructi.setInstructionRecipientPrikey(userUnit.getUser().getId());
				// 指令接受人的单位ID
				controlPersonInstructi.setInstructionRecipientUnitId(userUnit.getUnit().getId());
				save(controlPersonInstructi);
			}
		}
		if (userIds != null && userIds.length > 0) {
			// 下发到警员
			// 查询警员信息
			List<User> users = userService.getByIds(userIds);
			// 6、新增下发预警指令
			for (User user2 : users) {
				// 添加下发实体信息
				ControlPersonInstructi controlPersonInstructi = new ControlPersonInstructi();
				// 身份证号
				controlPersonInstructi.setControlPerson(controlPersonAlarm.getControlPerson());
				// 动态信息
				controlPersonInstructi.setDynamicsInformation(controlPersonAlarm.getDynamicInfoName());
				// 四色预警，1是红色2是橙色3是黄色4是橙色
				controlPersonInstructi.setFourColourWarning(Integer.parseInt(controlPersonAlarm.getWarningLevel()));
				// 指令状态 0为未签收 1为已签收 2为待审核 3未审核通过 4未审核拒绝
				controlPersonInstructi.setInstructionsState(0);
				// 下发人的民警ID
				controlPersonInstructi.setIssuedPolicePrikey(user.getId());
				// 下发人的单位ID
				controlPersonInstructi.setIssuedPoliceUnitId(user.getUnitId());
				// 关联预警
				controlPersonInstructi.setcontrolPersonAlarm(controlPersonAlarm);
				// 指令和反馈以JSON进行追加
				controlPersonInstructi.setInstructionFeedbackInformati(jsonObject.toString());
				// 指令接受人的民警ID
				controlPersonInstructi.setInstructionRecipientPrikey(user2.getId());
				// 指令接受人的单位ID
				controlPersonInstructi.setInstructionRecipientUnitId(user2.getUnitId());
				save(controlPersonInstructi);
			}
		}

		return new JsonReturn(200, "下发预警成功");
	}

	/**
	 * 预警指令反馈
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月9日 下午4:54:20
	 */
	@Override
	@Transactional
	public JsonReturn feedBack(Integer id, String json, User user) {
		// 查询指令信息
		ControlPersonInstructi controlPersonInstructi = get(id);
		if (controlPersonInstructi != null) {
			if (controlPersonInstructi.getInstructionsState() == 0) {
				return new JsonReturn(400, "预警指令未签收");
			} else if (controlPersonInstructi.getInstructionsState() == 2) {
				return new JsonReturn(400, "预警指令已反馈");
			} else if (controlPersonInstructi.getInstructionsState() == 3) {
				return new JsonReturn(400, "预警指令已审核");
			}
			// 追加指令信息
			IssuedJSONDTO issuedJSONDTO = new IssuedJSONDTO();
			issuedJSONDTO.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(controlPersonInstructi.getModifyDate()));
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
			issuedJSONDTO.setDepartment(unit.getUnitName());
			issuedJSONDTO.setJson(JSONObject.fromObject(json));
			issuedJSONDTO.setPoliceName(user.getPoliceName());
			issuedJSONDTO.setType("2");
			JSONObject jsonObject = JSONObject.fromObject(issuedJSONDTO);
			JSONArray jsonArray = JSONArray.fromObject(controlPersonInstructi.getInstructionFeedbackInformati());
			jsonArray.add(jsonObject);
			controlPersonInstructi.setInstructionFeedbackInformati(jsonArray.toString());
			// 修改指令状态()
			controlPersonInstructi.setInstructionsState(2);
			update(controlPersonInstructi);
			return new JsonReturn(200, "反馈成功");
		}
		return new JsonReturn(400, "反馈失败");
	}

	/**
	 * 预警反馈详情
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月9日 下午4:54:20
	 */
	@Override
	@Transactional
	public List<JSONObject> findFeedBackDetail(Integer id) {
		// 根据id查询预警信息详情
		ControlPersonInstructi cpi = get(id);
		// 取出指令信息
		String jsonStr = cpi.getInstructionFeedbackInformati();
		// String[] strings = jsonStr.split(";");
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		// 取出反馈指令
		for (Object object : jsonArray) {
			JSONObject jsonObject = JSONObject.fromObject(object);
			if (jsonObject.getInt("type") == 2) {
				// 稳控状态转换
				JSONObject obj = jsonObject.getJSONObject("json");
				Dictionary contro = dictionaryService.get(obj.optInt("contro"));
				if (contro != null) {
					obj.put("contro", contro.getItemName());
				} else {
					obj.put("contro", "无");
				}
				Dictionary contro2 = dictionaryService.get(obj.optInt("contro2"));
				if (contro2 != null) {
					obj.put("contro2", contro2.getItemName());
				} else {
					obj.put("contro2", "无");
				}
				jsonObjects.add(obj);
			}
		}
		return jsonObjects;
	}

	/**
	 * 获取最后一次反馈信息
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月13日 上午11:19:38
	 */
	@Override
	@Transactional
	public JSONObject findLastFeedBack(Integer id) {
		// 根据id查询预警信息详情
		ControlPersonInstructi cpi = get(id);
		// 取出指令信息
		String jsonStr = cpi.getInstructionFeedbackInformati();
		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		return JSONObject.fromObject(jsonArray.get(jsonArray.size() - 1));
	}

	/**
	 * 查询可下发目标
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月17日 下午4:14:28
	 */
	@Override
	@Transactional
	public Map<String, List<Map<String, Object>>> findIssued(User user) {
		Map<String, List<Map<String, Object>>> map = new HashMap<>();
		// 查询用户担任管理员的单位
		List<UserUnit> userUnitList = userUnitService.findList(null, Filter.eq("user", user), null);
		// 返回结果
		List<Map<String, Object>> unitList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> units = null;
		List<Map<String, Object>> users = null;
		if (userUnitList.size() > 0) {
			for (UserUnit userUnit : userUnitList) {
				Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, userUnit.getUnit().getId());
				if ("city".equals(unit.getUnitRank())) {
					// 用户为市级管理员，查询所有区级单位
					unitList = unitService.getNextUnit(unit.getId());
					userList = userService.getUnitPerson(unit.getId());
					//break;
				}
				if ("area".equals(unit.getUnitRank())) {
					// 用户为区级管理员，查询所有镇级单位
					units = new ArrayList<Map<String, Object>>();
					units = unitService.getNextUnit(unit.getId());
					unitList.addAll(units);
					// 用户为区级管理员，查询单位下所有警员
					users = new ArrayList<Map<String, Object>>();
					users = userService.getUnitPerson(unit.getId());
					userList.addAll(users);
				}
				if ("town".equals(unit.getUnitRank())) {
					// 用户为镇级管理员，查询所有警员
					users = new ArrayList<Map<String, Object>>();
					users = userService.getUnitPerson(unit.getId());
					userList.addAll(users);
				}
				// 获取预警指令签收单位下所有管理员
				DetachedCriteria cpa = DetachedCriteria.forClass(UserUnit.class);
				cpa.createAlias("user", "u");
				cpa.add(Restrictions.eq("u.unitId", unit.getId()));
				List<UserUnit> userUnits = userUnitService.findList(cpa, null, null, null);
				// 去除管理员
				for (UserUnit userUnit1 : userUnits) {
					Map<String, Object> userMap = new HashMap<String, Object>();
					userMap.put("id", userUnit1.getUser().getId());
					userMap.put("name", userUnit1.getUser().getPoliceName());
					userList.remove(userMap);
				}
			}
			// 去重
			unitList = new ArrayList<Map<String, Object>>(new LinkedHashSet<>(unitList));
			userList = new ArrayList<Map<String, Object>>(new LinkedHashSet<>(userList));
			/*// 去除下发人
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("id", user.getId());
			userMap.put("name", user.getPoliceName());
			userList.remove(userMap);*/
		}
		map.put("userList", userList);
		map.put("unitList", unitList);
		return map;
	}

	/**
	 * 预警指令详情
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月18日 下午4:54:20
	 */
	@Override
	@Transactional
	public JSONObject findInstructionDetail(Integer id) {
		// 根据id查询预警信息详情
		ControlPersonInstructi cpi = get(id);
		// 取出指令信息
		String jsonStr = cpi.getInstructionFeedbackInformati();
		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		return JSONObject.fromObject(jsonArray.get(0));
	}

	/**
	 * 签收
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月18日 下午9:00:05
	 */
	@Override
	@Transactional
	public JsonReturn updateInstructionsState(Integer id, Integer state) {
		JsonReturn jsonReturn = null;
		ControlPersonInstructi cpi = super.get(id);
		// 获取指令状态
		int instructionsState = cpi.getInstructionsState();
		if (state == 1) {
			// 签收时
			if (instructionsState == 0) {
				cpi.setInstructionsState(state);
				super.update(cpi);
				jsonReturn = new JsonReturn(200, "签收成功");
			} else {
				jsonReturn = new JsonReturn(400, "预警已签收");
			}
		} else {
			jsonReturn = new JsonReturn(400, "签收失败");
		}
		return jsonReturn;
	}

	/**
	 * 审核
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月18日 下午9:00:05
	 */
	@Override
	@Transactional
	public JsonReturn audit(Integer id, Integer state, String content) {
		JsonReturn jsonReturn = null;
		ControlPersonInstructi cpi = super.get(id);
		int instructionsState = cpi.getInstructionsState();
		// 审核
		if (instructionsState == 2) {
			cpi.setInstructionsState(state);
			if (state == 4) {
				// 取出指令信息
				String jsonStr = cpi.getInstructionFeedbackInformati();
				JSONArray jsonArray = JSONArray.fromObject(jsonStr);
				// 拼接反馈信息
				int last = jsonArray.size() - 1;
				Object object = jsonArray.get(last);
				JSONObject json = JSONObject.fromObject(object);
				json.put("content", content);
				jsonArray.remove(last);
				jsonArray.add(json);
				cpi.setInstructionFeedbackInformati(jsonArray.toString());
			}
			// 修改预警下发指令
			super.update(cpi);
			jsonReturn = new JsonReturn(200, "审核成功");
		} else if (instructionsState == 0) {
			jsonReturn = new JsonReturn(400, "预警未签收");
		} else if (instructionsState == 1) {
			jsonReturn = new JsonReturn(400, "预警未反馈");
		} else {
			jsonReturn = new JsonReturn(400, "预警已审核");
		}
		return jsonReturn;
	}

	/**
	 * 根据预警id查询签收指令信息(根据签收人分组)
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月26日 下午8:44:17
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Map<String, Object>> issuedListByPerson(Integer id) {
		String hql = "select cpi.instructionRecipientPrikey from ControlPersonInstructi cpi where cpi.controlPersonAlarm.id=:id group by cpi.instructionRecipientPrikey";
		//
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		List<Object> list = query.list();
		//
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("instructionRecipientPrikey", list.get(i));
			lists.add(map);
		}
		return lists;
	}

	/**
	 * 根据预警id查询签收指令信息(根据签收单位分组)
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月26日 下午8:44:17
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Map<String, Object>> issuedListByUnit(Integer id) {
		String hql = "select cpi.instructionRecipientUnitId from ControlPersonInstructi cpi where cpi.controlPersonAlarm.id=:id group by cpi.instructionRecipientUnitId";
		//
		Query query = getSession().createQuery(hql);
		//
		query = getSession().createQuery(hql);
		query.setParameter("id", id);
		List<Object> list = query.list();
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("instructionRecipientUnitId", list.get(i));
			lists.add(map);
		}
		return lists;
	}

	/**
	 * 根据签收单位查询可下发单位或警员
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月28日 上午10:16:27
	 */
	@Override
	@Transactional
	public Map<String, List<Map<String, Object>>> findCommandIssued(User user, Integer id) {
		Map<String, List<Map<String, Object>>> map = new HashMap<>();
		// 查询预警指令签收单位
		ControlPersonInstructi cpi = super.get(id);
		Unit unit = unitService.get(cpi.getInstructionRecipientUnitId());
		// 下发单位
		List<Map<String, Object>> unitList = new ArrayList<Map<String, Object>>();
		// 下发警员
		List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
		if ("city".equals(unit.getUnitRank())) {
			// 用户为市级管理员，查询所有区级单位
			unitList = unitService.getNextUnit(unit.getId());
			userList = userService.getUnitPerson(unit.getId());
		} else if ("area".equals(unit.getUnitRank())) {
			unitList = unitService.getNextUnit(unit.getId());
			userList = userService.getUnitPerson(unit.getId());
		} else {
			// 用户为区级管理员，查询所有警员
			userList = userService.getUnitPerson(unit.getId());
		}
		// 获取预警指令签收单位下所有管理员
		DetachedCriteria cpa = DetachedCriteria.forClass(UserUnit.class);
		cpa.createAlias("user", "u");
		cpa.add(Restrictions.eq("u.unitId", unit.getId()));
		List<UserUnit> userUnits = userUnitService.findList(cpa, null, null, null);
		// 去除管理员
		for (UserUnit userUnit : userUnits) {
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("id", userUnit.getUser().getId());
			userMap.put("name", userUnit.getUser().getPoliceName());
			userList.remove(userMap);
		}
		/*// 去除下发人
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("id", user.getId());
		userMap.put("name", user.getPoliceName());
		userList.remove(userMap);
		// 去除超管
		userMap = new HashMap<String, Object>();
		userMap.put("id", 1);
		userMap.put("name", "超级管理员");
		userList.remove(userMap);*/
		map.put("unitList", unitList);
		map.put("userList", userList);
		return map;
	}
}
