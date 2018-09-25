package com.ruiec.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

	@Resource
	private UnitService unitService;

	/**
	 * 登录
	 * 
	 * @param User
	 * @return User
	 * @author 陈靖原<br>
	 * @date 2017年11月29日 下午10:50:06
	 */
	@Override
	@Transactional(readOnly = true)
	public User login(User user) {
		String hql = "from User where idCard=:idCard and password=:password";
		Query query = getSession().createQuery(hql);
		query.setParameter("idCard", user.getIdCard());
		query.setParameter("password", user.getPassword());
		query.setMaxResults(1);
		return (User) query.uniqueResult();
	}

	/**
	 * 根据单位查询下面所面所有的警员
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月11日 上午9:59:01
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getUnitPerson(Integer areaId, Integer townId) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		Integer unitId = null;
		if (townId != null) {
			unitId = townId;
		} else {
			unitId = areaId;
		}
		List<User> users = new ArrayList<User>();
		DetachedCriteria cu = DetachedCriteria.forClass(User.class);
		// 根据单位查询
		if (unitId != null) {
			cu.add(Restrictions.eq("unitId", unitId));
		}
		users = super.findList(cu, null, null, null);
		for (int i = 0; i < users.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", users.get(i).getId());
			map.put("name", users.get(i).getPoliceName());
			listMap.add(map);
		}
		return listMap;
	}

	/**
	 * 根据单位查询下面所面所有的警员
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月28日 下午8:22:52
	 */
	@Override
	@Transactional
	public List<Map<String, Object>> getUnitPerson(Integer unitId) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		List<User> users = new ArrayList<User>();
		// 根据单位查询
		DetachedCriteria cu = DetachedCriteria.forClass(User.class);
		cu.add(Restrictions.eq("unitId", unitId));
		users = super.findList(cu, null, null, null);
		for (int i = 0; i < users.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", users.get(i).getId());
			map.put("name", users.get(i).getPoliceName());
			listMap.add(map);
		}
		return listMap;
	}

	/**
	 * 根据登录用户获取单位数据
	 * 
	 * @author 贺云<br>
	 * @date 2017年12月11日 下午4:42:17
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Unit> findUserUnit(User user, LoginUserUnit loginUserUnit, Integer isCity) {
		List<Unit> userUnits = unitService.getUnitArea(isCity);
		if (loginUserUnit != null && loginUserUnit.getUnitIds() != null && loginUserUnit.getUnitIds().size() > 0) {
			// 管理员
			if (!loginUserUnit.getUnitRank().getName().equals("city")) {
				// 普通管理员
				// 获取当前用户所管理的单位
				List<Integer> unitIds = loginUserUnit.getUnitIds();
				List<Unit> userAdminUnits = new ArrayList<Unit>();
				;
				for (int i = 0; i < unitIds.size(); i++) {
					userAdminUnits.add(unitService.get(unitIds.get(i)));
				}
				// 当前用管理单位则是管理员，没有则是警员
				if (userAdminUnits != null && userAdminUnits.size() > 0) {
					// 所管辖的公安局信息
					userUnits = userAdminUnits;
				}
			}
		}
		return userUnits;
	}
}
