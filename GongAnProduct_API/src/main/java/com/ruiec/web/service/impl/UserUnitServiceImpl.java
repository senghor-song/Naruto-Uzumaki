package com.ruiec.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.entity.UserUnit;
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
}
