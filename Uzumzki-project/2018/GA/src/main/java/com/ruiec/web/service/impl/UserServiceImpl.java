package com.ruiec.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
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
		if (townId != null && townId != -1) {
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
	 * 根据姓名或者身份证查询用户姓名
	 * 
	 * @author 贺云<br>
	 * @date 2017年12月11日 下午4:42:17
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> findUserList(Page page, String searchContent) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		String hql = "from User where policeName like :searchContent or idCard like :searchContent";
		Query query = getSession().createQuery(hql);
		query.setString("searchContent", "%" + searchContent + "%");
		query.setFirstResult(0);
		query.setMaxResults(page.getPageSize());
		List<User> users = query.list();
		for (User user : users) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", user.getId());
			map.put("policeName", user.getPoliceName());
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
			map.put("unitName", unit.getUnitName());
			map.put("unitId", unit.getId());
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

	/**
	 * 根据身份证，单位，姓名查询用户列表
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月22日 下午2:20:58
	 */
	@Override
	@Transactional(readOnly = true)
	public Page findUsers(Page page, String search, String forSearch, Integer unitId) {
		DetachedCriteria u = DetachedCriteria.forClass(User.class);

		DetachedCriteria unit = DetachedCriteria.forClass(Unit.class);
		DetachedCriteria unit2 = DetachedCriteria.forClass(Unit.class);
		DetachedCriteria unit3 = DetachedCriteria.forClass(Unit.class);
		DetachedCriteria unit4 = DetachedCriteria.forClass(Unit.class);
		if (StringUtils.isNotBlank(forSearch)) {
			// 根据警员姓名模糊查询
			if ("policeName".equals(search)) {
				u.add(Restrictions.like("policeName", forSearch, MatchMode.ANYWHERE));
			}
			// 根据警员身份证模糊查询
			if ("idCard".equals(search)) {
				u.add(Restrictions.like("idCard", forSearch, MatchMode.ANYWHERE));
			}
		}
		// 根据单位查询
		if (unitId != null) {
			unit.add(Restrictions.eq("id", unitId));
			unit.setProjection(Property.forName("id"));

			unit2.add(Restrictions.or(Restrictions.eq("parentId", unitId), Restrictions.eq("policeTypesParentId", unitId)));
			unit2.setProjection(Property.forName("id"));

			unit4.add(Restrictions.or(Restrictions.eq("parentId", unitId), Restrictions.eq("policeTypesParentId", unitId)));
			unit4.setProjection(Property.forName("id"));
			unit3.add(Restrictions.or(Property.forName("parentId").in(unit4), Property.forName("policeTypesParentId").in(unit4)));
			unit3.setProjection(Property.forName("id"));

			u.add(Restrictions.or(Property.forName("unitId").in(unit), Property.forName("unitId").in(unit2), Property.forName("unitId").in(unit3)));
		}
		findByPage(page, u);
		// 分页
		if (page.getTotalCount() != 0) {
			page.setTotalCount(page.getTotalCount() - 1);
		}
		return page;
	}
}
