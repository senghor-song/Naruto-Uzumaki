package com.ruiec.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.query.Sort;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.dto.UserUnitDTO;
import com.ruiec.web.entity.TaskDetails;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.entity.UserUnit;
import com.ruiec.web.service.TaskDetailsService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.service.UserUnitService;

/**
 * 管理员管理接口实现类
 * 
 * @author 贺云<br>
 * @date 2017年12月5日 下午4:02:09
 */
@Service
public class UserUnitServiceImpl extends BaseServiceImpl<UserUnit, Integer> implements UserUnitService {

	private static final Logger LOGGER = Logger.getLogger(UserUnitServiceImpl.class);
	@Resource
	private UserService userService;
	@Resource
	private UnitService unitService;
	@Resource
	private TaskDetailsService taskDetailsService;

	/**
	 * 查询单位管理员是否存在
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月8日 上午10:12:29
	 */
	@Override
	@Transactional(readOnly = true)
	public JsonReturn checkByIds(Integer[] ids, Integer userId) {
		// 返回结果
		List<UserUnit> userUnitList = new ArrayList<UserUnit>();
		for (int id : ids) {
			// 查询单位管理员
			// 构造复杂查询entity
			DetachedCriteria criteria = DetachedCriteria.forClass(UserUnit.class);
			// 给entity中的复杂属性取别名
			criteria.createAlias("unit", "ut");
			// 通过别名完成关联查询
			criteria.add(Restrictions.eq("ut.id", id));
			List<UserUnit> uus = findList(criteria, null, null, null);
			List<UserUnit> userUnits = new ArrayList<UserUnit>();
			// 移除与操作用户相同的管理员
			for (UserUnit uu : uus) {
				if (uu.getUser().getId() != userId) {
					userUnits.add(uu);
				}
			}
			if (userUnits.size() == 0) {
				return new JsonReturn(400, "没有可签收的管理员");
			} else {
				userUnitList.addAll(userUnits);
			}
		}
		return new JsonReturn(200, "ok", userUnitList);
	}

	/**
	 * 查询单位管理员是否存在
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月8日 上午10:12:29
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean checkById(Integer id) {
		// 返回结果
		// List<UserUnit> userUnitList = new ArrayList<UserUnit>();
		// 查询单位管理员
		// 构造复杂查询entity
		DetachedCriteria criteria = DetachedCriteria.forClass(UserUnit.class);
		// 给entity中的复杂属性取别名
		criteria.createAlias("unit", "ut");
		// 通过别名完成关联查询
		criteria.add(Restrictions.eq("ut.id", id));
		List<UserUnit> userUnits = super.findList(criteria, null, null, null);
		if (userUnits.size() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 新增管理员
	 * 
	 * @author 贺云<br>
	 * @date 2017年12月12日 上午10:25:16
	 */
	@Override
	@Transactional
	public JsonReturn saveUserUnit(Integer userId, Integer unitId, User loginUser) {
		try {
			User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, userId);
			if (user == null) {
				return new JsonReturn(400, "用户不存在");
			}
			// 要管理的单位
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unitId);
			if (unit == null) {
				return new JsonReturn(400, "单位不存在");
			}
			List<UserUnit> isUserUnit = super.findList(null, Filter.eq("user", user), null);
			if (isUserUnit != null && isUserUnit.size() > 0) {
				return new JsonReturn(400, "管理员只可管理一个单位");
			}
			Unit userUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
			if (!unit.getUnitRank().equals("city")) {
				// 分县局民警添加管理员身份需要判断管理的单位是否是所管理的
				if (userUnit.getUnitRank().equals("area")) {
					if (unit.getUnitRank().equals("area")) {
						if (!unitId.equals(userUnit.getId())) {
							return new JsonReturn(400, "该警员不能管理该单位");
						}
					} else {
						List<Integer> idsList = unitService.findSonIds(unit.getIsPoliceSection() == 1 ? unit.getPoliceTypesParentId() : unit.getParentId());
						if (!isUnitId(idsList, unitId)) {
							return new JsonReturn(400, "该警员不能管理该单位");
						}
					}
				} else if (userUnit.getUnitRank().equals("town")) {
					// 派出所民警添加管理员
					// 要管理的单位区级时
					if (unit.getUnitRank().equals("area")) {
						if (userUnit.getIsPoliceSection() == 1) {
							// 判断是否是警员所属单位的上级
							if (!(unitId.equals(userUnit.getPoliceTypesParentId()) || unitId.equals(userUnit.getParentId()))) {
								return new JsonReturn(400, "该警员不能管理该单位");
							}
						} else {

							// 判断是否是警员所属单位的上级
							if (!unitId.equals(userUnit.getParentId())) {
								return new JsonReturn(400, "该警员不能管理该单位");
							}
						}
					} else {
						if (!unitId.equals(userUnit.getId())) {
							return new JsonReturn(400, "该警员不能管理该单位");
						}
					}
				}
			}
			List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
			// 构建新增管理员的对象
			UserUnit newUserUnit = new UserUnit();
			newUserUnit.setUser(user);
			newUserUnit.setUnit(unit);
			save(newUserUnit);
			// 存储当前的管理员对象
			Map<String, Object> userUnitMap = new HashMap<String, Object>();
			userUnitMap.put("id", unit.getId());// 单位id
			userUnitMap.put("unitRank", unit.getUnitRank());// 单位级别
			userUnitMap.put("unitName", unit.getUnitName());// 单位名称
			listMaps.add(userUnitMap);
			// 更新缓存里的数据
			RedisUtil.addRedis(GlobalUnit.USER_UNIT_MAP, user.getId().toString(), listMaps);
			return new JsonReturn(200, "创建成功");
		} catch (Exception e) {
			LOGGER.error("添加管理异常：" + e);
			return new JsonReturn(400, "创建失败");
		}
	}

	/**
	 * 管理员列表
	 * 
	 * @author 贺云<br>
	 * @date 2017年12月13日 上午9:35:59
	 */
	@Override
	@Transactional
	public void findUserUnitList(Page page, UserUnitDTO dto) {
		// 构造复杂查询entity
		DetachedCriteria criteria = DetachedCriteria.forClass(UserUnit.class);
		// 给entity中的复杂属性取别名
		criteria.createAlias("user", "u");
		criteria.createAlias("unit", "ut");
		criteria.add(Restrictions.ne("u.id", 1));
		// 通过别名完成关联查询
		if (dto.getType() != null && dto.getSearchContent() != null) {
			if (dto.getType() == 1) {
				criteria.add(Restrictions.like("u.idCard", dto.getSearchContent(), MatchMode.ANYWHERE));
			}
			if (dto.getType() == 2) {
				criteria.add(Restrictions.like("u.policeName", dto.getSearchContent(), MatchMode.ANYWHERE));
			}
		}
		if (dto.getUnitId() != null) {
			criteria.add(Restrictions.eq("ut.id", dto.getUnitId()));
		}
		page.add(Sort.desc("id"));
		findByPage(page, criteria);
	}

	/**
	 * 查询用户关联管理单位
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月17日 下午2:50:55
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> findListByUser(User user) {
		// 查询用户担任管理员的单位
		List<UserUnit> userUnitList = super.findList(null, Filter.eq("user", user), null);
		// 返回结果
		List<Map<String, Object>> unitList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> units = null;
		for (UserUnit userUnit : userUnitList) {
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, userUnit.getUnit().getId());
			if ("city".equals(unit.getUnitRank())) {
				// 用户为市级管理员，查询所有区级单位
				unitList = unitService.getNextUnit(unit.getId());
				break;
			}
			if ("area".equals(unit.getUnitRank())) {
				// 用户为区级管理员，查询所有镇级单位
				units = new ArrayList<Map<String, Object>>();
				units = unitService.getNextUnit(unit.getId());
				unitList.addAll(units);
				// 去重
				unitList = new ArrayList<Map<String, Object>>(new LinkedHashSet<>(unitList));
			}
		}
		return unitList;
	}

	/**
	 * 获取用户对应单位的数据
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月20日 下午4:38:52
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Map<String, List<Map<String, Object>>> getUserUnits() {
		Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();
		String hql = "SELECT USER_ID userId, listagg(unit_id,',')within group (order by USER_ID) unitId FROM T_SYS_USER_UNIT GROUP BY USER_ID";
		Query query = getSession().createSQLQuery(hql);
		List<Object[]> list = (List<Object[]>) query.list();
		for (int i = 0; i < list.size(); i++) {
			// 存储一个管理员所管理的单位
			List<Map<String, Object>> userMaps = new ArrayList<Map<String, Object>>();
			String userId = list.get(i)[0].toString();
			// 获取单位数组id
			String[] unitIds = list.get(i)[1].toString().split(",");
			for (int j = 0; j < unitIds.length; j++) {
				Map<String, Object> userUnitMap = new HashMap<String, Object>();
				Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unitIds[j]);
				userUnitMap.put("id", unit.getId());// 单位id
				userUnitMap.put("unitRank", unit.getUnitRank());// 单位级别
				userUnitMap.put("unitName", unit.getUnitName());// 单位名称
				userMaps.add(userUnitMap);
			}
			map.put(userId, userMaps);
		}
		return map;
	}

	/**
	 * 修改管理员
	 * 
	 * @author 贺云<br>
	 * @date 2017年12月22日 上午9:32:35
	 */
	@Override
	@Transactional
	public JsonReturn updateUserUnit(Integer id, Integer unitId) {
		try {
			if (id == null) {
				return new JsonReturn(400, "操作id不能为NULL");
			}
			if (unitId == null) {
				return new JsonReturn(400, "单位unitId不能为NULL");
			}
			UserUnit updaUserUnit = get(id);
			if (updaUserUnit == null) {
				return new JsonReturn(400, "操作项不存在");
			}
			if (!updaUserUnit.getUnit().getId().equals(id)) {
				User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, updaUserUnit.getUser().getId());
				Unit userUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
				// 要管理的单位
				Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unitId);
				if (!unit.getUnitRank().equals("city")) {
					// 分县局民警添加管理员身份需要判断管理的单位是否是所管理的
					if (userUnit.getUnitRank().equals("area")) {
						if (unit.getUnitRank().equals("area")) {
							if (!unitId.equals(userUnit.getId())) {
								return new JsonReturn(400, "该警员不能管理该单位");
							}
						} else {
							List<Integer> idsList = unitService.findSonIds(unit.getIsPoliceSection() == 1 ? unit.getPoliceTypesParentId() : unit.getParentId());
							if (!isUnitId(idsList, unitId)) {
								return new JsonReturn(400, "该警员不能管理该单位");
							}
						}
					} else if (userUnit.getUnitRank().equals("town")) {
						// 派出所民警添加管理员
						// 要管理的单位区级时
						if (unit.getUnitRank().equals("area")) {
							if (userUnit.getIsPoliceSection() == 1) {
								// 判断是否是警员所属单位的上级
								if (!(unitId.equals(userUnit.getPoliceTypesParentId()) || unitId.equals(userUnit.getParentId()))) {
									return new JsonReturn(400, "该警员不能管理该单位");
								}
							} else {
	
								// 判断是否是警员所属单位的上级
								if (!unitId.equals(userUnit.getParentId())) {
									return new JsonReturn(400, "该警员不能管理该单位");
								}
							}
						} else {
							if (!unitId.equals(userUnit.getId())) {
								return new JsonReturn(400, "该警员不能管理该单位");
							}
						}
					}
				}
				// 要管理的单位
				updaUserUnit.setUnit(unit);
				// 修改管理员数据
				update(updaUserUnit);
				List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
				// 存储当前的管理员对象
				Map<String, Object> userUnitMap = new HashMap<String, Object>();
				userUnitMap.put("id", unit.getId());// 单位id
				userUnitMap.put("unitRank", unit.getUnitRank());// 单位级别
				userUnitMap.put("unitName", unit.getUnitName());// 单位名称
				listMaps.add(userUnitMap);
				RedisUtil.addRedis(GlobalUnit.USER_UNIT_MAP, updaUserUnit.getUser().getId().toString(), listMaps);
				//需要将该管理员签收过的任务全部改为未签收状态
	
				DetachedCriteria td = DetachedCriteria.forClass(TaskDetails.class);
				DetachedCriteria t = td.createCriteria("task");
				t.add(Restrictions.ne("status", "3"));
				td.add(Restrictions.eq("receiveUserId", updaUserUnit.getUser().getId()));// 所管理的任务
				List<TaskDetails> taskDetailsList = taskDetailsService.findList(td, null, null, null);
				for (int i = 0; i < taskDetailsList.size(); i++) {
					TaskDetails taskDetails = taskDetailsList.get(i);
					taskDetails.setFeedbackStatus(0);
					taskDetailsService.updateInclude(taskDetails, new String[]{"feedbackStatus"}, null);
				}
				return new JsonReturn(200, "修改成功");
			}else {
				return new JsonReturn(200, "修改成功");
			}
		} catch (Exception e) {
			LOGGER.error("添加管理异常：" , e);
			return new JsonReturn(400, "添加管理失败");
		}
	}

	/**
	 * 迭代判断2个数组中是否有重复的
	 * 
	 * @param t1
	 * @param t2
	 */
	public <T> Integer[] authority(T[] t1, T[] t2) {
		// 将数组转换为集合
		List<T> list1 = Arrays.asList(t1);
		List<T> reList = new ArrayList<T>();// 重复数据的集合
		// 迭代判断第二个数组中的元素第一个数组中是否有
		for (T t : t2) {
			// java.lang.String.contains() 方法返回true，当且仅当此字符串包含指定的char值序列
			if (list1.contains(t)) {
				reList.add(t);
			}
		}
		Integer[] ids = new Integer[reList.size()];
		for (int i = 0; i < reList.size(); i++) {
			ids[i] = new Integer(reList.get(i).toString());
		}
		return ids;
	}

	/**
	 * 判断所管理的单位中是否包含该单位
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月10日 上午10:49:49
	 */
	private boolean isUnitId(List<Integer> list, Integer unitId) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(unitId)) {
				return true;
			}
		}
		return false;
	}
}
