package com.ruiec.web.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.ApiReturn;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonData;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.dto.ControlPersonDTO;
import com.ruiec.web.entity.APIConfig;
import com.ruiec.web.entity.ControlPerson;
import com.ruiec.web.entity.ControlPersonAlarm;
import com.ruiec.web.entity.ControlPersonRelation;
import com.ruiec.web.entity.ControlPersonType;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.DynamicInfo;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.enumeration.WarningColor;
import com.ruiec.web.service.ControlPersonAlarmService;
import com.ruiec.web.service.ControlPersonExtendService;
import com.ruiec.web.service.ControlPersonRelationService;
import com.ruiec.web.service.ControlPersonService;
import com.ruiec.web.service.ControlPersonTypeService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.DictionaryTypeService;
import com.ruiec.web.service.DynamicInfoService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.service.UserUnitService;
import com.ruiec.web.util.RuiecDateUtils;

/**
 * 重点人员api控制器
 * 
 * @author zhongguocheng<br>
 * @date 2018年1月8日 下午5:16:31
 */
@Controller("apiControlPersonController")
@RequestMapping("/api/admin/controlPerson")
public class ApiControlPersonController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(ApiControlPersonController.class);

	@Resource
	private ControlPersonService controlPersonService;
	@Resource
	private ControlPersonExtendService controlPersonExtendService;
	@Resource
	private ControlPersonRelationService controlPersonRelationService;
	@Resource
	private ControlPersonAlarmService controlPersonAlarmService;
	@Resource
	private ControlPersonTypeService controlPersonTypeService;
	@Resource
	private UnitService unitService;
	@Resource
	private UserService userService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private DictionaryTypeService dictionaryTypeService;
	@Resource
	private UserUnitService userUnitService;
	@Resource
	private OperationLogService operationLogService;
	@Resource
	private DynamicInfoService dynamicInfoService;

	/**
	 * 获取重点人员列表
	 * 
	 * @author Senghor<br>
	 * @modify zhongguocheng<br>
	 * @date 2018年1月8日 下午5:17:07
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/list")
	@ResponseBody
	public ApiReturn list(Page page, ControlPerson controlPerson, ControlPersonDTO controlPersonDTO, HttpServletRequest request) {
		//最终返回数据
		Map<String, Object> returnMap = new HashMap<String, Object>();
		//人员类型
		String personnlType = getPersonnlType(controlPerson.getPersonnelType());
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		controlPersonDTO.setUser(user);
		// 用来保存要查询单位的子单位id
		List<Integer> adminUnits = new ArrayList<Integer>();
		if (loginUserUnit != null && loginUserUnit.getUnitIds() != null && loginUserUnit.getUnitIds().size() > 0) {
		 // 管理员
		    // 管理员
			if (!loginUserUnit.getUnitRank().getName().equals("city")) {
				// 普通管理员
                // 得到所管理单位的下级单位id
                adminUnits = loginUserUnit.getUnitSonIds();
			}
		} else {
			// 警员则只查询所管理的重点人
			page.add(Filter.eq("user", user));
		}
		controlPersonDTO.setUnitIds(adminUnits);
		controlPersonService.findByPage(page, controlPerson, controlPersonDTO);
		// 列表总记录数
		returnMap.put("totalCount", page.getTotalCount());
		returnMap.put("pageSize", page.getPageSize());
		returnMap.put("pageNumber", page.getPageNumber());
		List<ControlPerson> controlPersonList = (List<ControlPerson>) page.getList();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String,Object>>();
		for (ControlPerson list : controlPersonList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", list.getId());//重点人id
			map.put("name", ObjectUtils.firstNonNull(list.getName(),GlobalUnit.NULLMSG));//姓名
			map.put("photo", ObjectUtils.firstNonNull(list.getControlPersonExtend().getPhoto(),""));//图片
			map.put("createDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(list.getCreateDate()).toString());//创建时间
			map.put("columnTub", "1".equals(list.getIsAuditKeyPersonColumnTub()) ? "未审核" : "2".equals(list.getIsAuditKeyPersonColumnTub()) ? "已审核" : "未通过审核");
			listMaps.add(map);
		}
		returnMap.put("mapList", listMaps);
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看了" + personnlType + "列表",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
			return new ApiReturn(400, "查询重点人信息列表失败");
		}
		return new ApiReturn(200, "查询重点人信息列表成功", returnMap);
	}

	/**
	 * 跳转重点人员查看页面
	 * 
	 * @author Senghor<br>
	 * @modify zhongguocheng<br>
	 * @date 2018年1月9日 上午10:33:03
	 */
	@RequestMapping("/look")
	@ResponseBody
	public ApiReturn look(HttpServletRequest request, Integer id, Integer personnelType) {
		String personnlType = getPersonnlType(personnelType);
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		// 根据id获取重点人员数据
		ControlPerson controlPerson = controlPersonService.get(id, null,
				Fetch.alias("controlPersonExtend", "controlPersonExtendAlias", JoinType.LEFT_OUTER_JOIN));
		if (controlPerson != null) {
			List<Map<String, Object>> mapList = new ArrayList<>();
			Map<String, Object> map = new HashMap<String, Object>();
			// 能否审核重点人
			if (loginUserUnit != null && loginUserUnit.getUnitIds() != null && loginUserUnit.getUnitIds().size() > 0) {
				// 管理员
				if (loginUserUnit.getUnitRank().getName().equals("town")) {
					// 区级管理员
					// 不能审核重点人
					map.put("isColumn", 1);
				}
				Unit thisUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, controlPerson.getUnitId());
				// 如果是市局警员添加的重点人，只有市局管理员才能审核
				User thisUser = new User();
				if (controlPerson.getUserId() != null) {
					thisUser = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, controlPerson.getUserId());
					if (thisUser != null && "city".equals(thisUnit.getUnitRank())) {
						if (!loginUserUnit.getUnitRank().getName().equals("city")) {
							// 不能审核重点人
							map.put("isColumn", 1);
						}
					}
				}
			} else {
				// 警员
				// 不能审核重点人
				map.put("isColumn", 1);
			}
			map.put("photo", ObjectUtils.firstNonNull(controlPerson.getControlPersonExtend().getPhoto(),""));
			map.put("name", ObjectUtils.firstNonNull(controlPerson.getName(),GlobalUnit.NULLMSG));
			map.put("phone", ObjectUtils.firstNonNull(controlPerson.getPhone(),GlobalUnit.NULLMSG));
			map.put("idCard", ObjectUtils.firstNonNull(controlPerson.getIdCard(),GlobalUnit.NULLMSG));
			map.put("sex", ObjectUtils.firstNonNull(controlPerson.getSex(),GlobalUnit.NULLMSG));
			// 籍贯
			map.put("nativePlace", ObjectUtils.firstNonNull(controlPerson.getControlPersonExtend().getNativePlace(),GlobalUnit.NULLMSG));
			// 户籍地区
			map.put("householdRegisterPlace", ObjectUtils.firstNonNull(controlPerson.getControlPersonExtend().getHouseholdRegisterPlace(),GlobalUnit.NULLMSG));
			// 现住地区
			map.put("nowLiveArea", ObjectUtils.firstNonNull(controlPerson.getControlPersonExtend().getNowLiveArea(),GlobalUnit.NULLMSG));
			// 是否在控
			if (controlPerson.getIsControl() == null) {
				map.put("isControlTranslation", GlobalUnit.NULLMSG);
			}else {
				Dictionary dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPerson.getIsControl());
				if (dictionary != null) {
					map.put("isControlTranslation", dictionary.getItemName());
				} else {
					map.put("isControlTranslation", GlobalUnit.NULLMSG);
				}
			}
			// 危险级别
			if (controlPerson.getDangerousLevel() != null) {
				// 危险级别中文
				Dictionary dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPerson.getDangerousLevel());
				if (dictionary != null) {
					map.put("dangerousLevel", dictionary.getItemName());
				} else {
					map.put("dangerousLevel", GlobalUnit.NULLMSG);
				}
			} else {
				map.put("dangerousLevel", GlobalUnit.NULLMSG);
			}
			// 人员类别
			List<ControlPersonType> list = controlPerson.getControlPersonTypes();
			String personTypes = new String();
			if (list != null && list.size() > 0) {
				for (ControlPersonType controlPersonType : list) {
					Dictionary dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPersonType.getDictionaryId());
					personTypes = personTypes + dictionary.getItemName() + ",";
				}
			} else {
				personTypes = GlobalUnit.NULLMSG;
			}
			map.put("personTypes", personTypes);
			// 单位名称
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, controlPerson.getUnitId());
			map.put("unitName", unit == null ?  GlobalUnit.NULLMSG : unit.getUnitName());
			//责任民警
			User personUser = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, controlPerson.getUserId());
			map.put("policeName", personUser == null ? GlobalUnit.NULLMSG : personUser.getPoliceName());
            map.put("policePhone", ObjectUtils.firstNonNull(controlPerson.getControlPersonExtend().getPoliceComprehensiveContactI(),GlobalUnit.NULLMSG));
			//列管时间
			if (controlPerson.getColumnDate() != null) {
				map.put("columnDate", controlPerson.getColumnDate().toString());
			} else {
				map.put("columnDate", GlobalUnit.NULLMSG);
			}
			//事由
			map.put("reason", ObjectUtils.firstNonNull(controlPerson.getControlPersonExtend().getReason(),GlobalUnit.NULLMSG));
			// 根据重点人员ID获取对应的关系列表
			List<ControlPersonRelation> controlPersonRelationList = controlPersonRelationService.findList(null, Filter.eq("controlPersonId", id), null);
			for (ControlPersonRelation controlPersonRelation : controlPersonRelationList) {
				Map<String, Object> relationMap = new HashMap<String, Object>();
				relationMap.put("relationName", controlPersonRelation.getName());
				relationMap.put("relationIdCard", controlPersonRelation.getIdCard());
				Dictionary relationType = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPersonRelation.getTypeId());
				relationMap.put("relationType", relationType.getItemName());
				mapList.add(relationMap);
			}
			map.put("relations", mapList);
			map.put("columnTubeState", controlPerson.getColumnTubeState());
			operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看了" + controlPerson.getName()
					+ personnlType + "信息", LogUtil.getData(request.getParameterMap()));
			return new ApiReturn(200, "查询重点人详情成功", map);
		} else {
			return new ApiReturn(400, "查询重点人详情失败");
		}
	}

	/**
	 * 查询重点人员预警列表
	 * 
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午4:35:18
	 */
	@RequestMapping("/lookAlarm")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ApiReturn lookAlarm(Page page, HttpServletRequest request, Integer id, Integer personnelType) {
		String personnlType = getPersonnlType(personnelType);
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (id != null) {
			// 根据重点人Id查询该重点人所有的预警信息
			DetachedCriteria cpa = DetachedCriteria.forClass(ControlPersonAlarm.class);
			DetachedCriteria cp = cpa.createCriteria("controlPerson");
			cp.setFetchMode("controlPersonTypes", FetchMode.SELECT);
//			cpa.createCriteria("alarmUnit");
//			cpa.createCriteria("controlUnit");
			cp.add(Restrictions.eq("id", id));
			controlPersonAlarmService.findByPage(page, cpa);
			List<ControlPersonAlarm> alarmList = (List<ControlPersonAlarm>) page.getList();
			returnMap.put("totalCount", page.getTotalCount());
			returnMap.put("pageSize", page.getPageSize());
			returnMap.put("pageNumber", page.getPageNumber());
			List<Map<String, Object>> listMaps = new ArrayList<Map<String,Object>>();
			//循环解析数据
			for (ControlPersonAlarm controlPersonAlarm : alarmList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", ObjectUtils.firstNonNull(controlPersonAlarm.getControlPerson().getName(),GlobalUnit.NULLMSG));//姓名
				map.put("idCard", ObjectUtils.firstNonNull(controlPersonAlarm.getControlPerson().getIdCard(),GlobalUnit.NULLMSG));//身份证号
				// 预警级别
				Dictionary dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPersonAlarm.getWarningLevel());
				if (dictionary == null) {
					dictionary = dictionaryService.get(Integer.parseInt(controlPersonAlarm.getWarningLevel()));
				}
				if (dictionary == null) {
					map.put("warningLevelColor", GlobalUnit.NULLMSG);
					map.put("warningLevelValue", GlobalUnit.NULLMSG);
				} else {
					map.put("warningLevelColor", dictionary.getItemName());
					map.put("warningLevelValue", WarningColor.getValue(dictionary.getItemName()));
				}
				
				if (controlPersonAlarm.getCreateDate() != null) {
					map.put("columnDate", controlPersonAlarm.getCreateDate().toString());
				} else {
					map.put("columnDate", GlobalUnit.NULLMSG);
				}
				// 人员类别
				List<ControlPersonType> list = controlPersonAlarm.getControlPerson().getControlPersonTypes();
				String personTypes = new String();
				if (list != null && list.size() > 0) {
					for (ControlPersonType controlPersonType : list) {
						dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPersonType.getDictionaryId());
						if (dictionary == null) {
							continue;
						}
						personTypes = personTypes + dictionary.getItemName() + ",";
					}
				} else {
					personTypes = GlobalUnit.NULLMSG;
				}
				map.put("personnelType", ObjectUtils.firstNonNull(personTypes,GlobalUnit.NULLMSG));
				// 动态信息类别
				DynamicInfo dynamicInfo = dynamicInfoService.get(controlPersonAlarm.getDynamicInfoId());
				if (dynamicInfo != null) {
					// 轨迹类型
					APIConfig apiConfig = (APIConfig) RedisUtil.getRedisOne(GlobalUnit.API_CONFIG_MAP, dynamicInfo.getType());
					if (apiConfig == null) {
						continue;
					}
					// 标题集合
					List<String> titles = new ArrayList<String>();
					// 将json字符串转换为json格式
					JSONObject json = JSONObject.fromObject(dynamicInfo.getInformation().toString());
					// 获取title标题数据
					JSONObject titleJsonObject = (JSONObject) json.get("title");
					// 获取value内容数据集合
					JSONArray valueJsonObject = json.getJSONArray("value");
					List<JsonData> jsonDatas = new ArrayList<JsonData>();
					// 迭代标题数据
					for (Iterator<?> iter = titleJsonObject.keys(); iter.hasNext();) {
						// 获取标题的key
						String key = (String) iter.next();
						// 根据标题的key获取value
						String value = titleJsonObject.get(key).toString();
						// 给标题集合赋值
						titles.add(value);

					}
					// 将标题对应的值获取到
					for (int j = 0; j < valueJsonObject.size(); j++) {
						JsonData jsonData = (JsonData) JSONObject.toBean(valueJsonObject.getJSONObject(j), JsonData.class);
						jsonDatas.add(jsonData);
					}
					map.put("dynamicInfo", jsonDatas);
					map.put("dynamicInfoName", ObjectUtils.firstNonNull(apiConfig.getName(), GlobalUnit.NULLMSG));
				} else {
					map.put("dynamicInfo", GlobalUnit.NULLMSG);
					map.put("dynamicInfoName", GlobalUnit.NULLMSG);
				}
				listMaps.add(map);
			}
			returnMap.put("mapList", listMaps);
			operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看了" + personnlType + "信息",
					LogUtil.getData(request.getParameterMap()));
			return new ApiReturn(200, "查询重点人预警列表成功", returnMap);
		} else {
			return new ApiReturn(400, "查询重点人预警列表失败", null);
		}
	}

	/**
	 * 获取当前操作的人员类型
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月27日 下午2:23:21
	 */
	private String getPersonnlType(Integer personnelTypeId) {
		String personnelType = "";
		if (personnelTypeId != null) {
			if (personnelTypeId == 1) {
				personnelType = "重点人员";
			} else if (personnelTypeId == 2) {
				personnelType = "关注人员";
			} else if (personnelTypeId == 3) {
				personnelType = "外地人员";
			}
		}
		return personnelType;
	}

}
