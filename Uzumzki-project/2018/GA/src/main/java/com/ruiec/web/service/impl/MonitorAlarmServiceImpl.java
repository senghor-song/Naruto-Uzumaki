package com.ruiec.web.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.entity.MonitorAlarm;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.MonitorAlarmService;
import com.ruiec.web.util.RuiecDateUtils;

/**
 * 布控预警服务实现类
 * 
 * @author 陈靖原<br>
 * @date 2018年1月8日 上午10:59:09
 */
@Service("monitorAlarmServiceImpl")
public class MonitorAlarmServiceImpl extends BaseServiceImpl<MonitorAlarm, Integer> implements MonitorAlarmService {
	@Resource
	private DictionaryService dictionaryService;

	/**
	 * 布控预警分页查询
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月11日 上午9:32:39
	 */
	@Override
	@Transactional
	public void findByPage(Page page, DetachedCriteria cpa, DetachedCriteria cp, User user, LoginUserUnit loginUserUnit) {
		// 未被逻辑删除的布控人
		cp.add(Restrictions.eq("isDelete", 0));
		cp.setFetchMode("monitorTypes", FetchMode.SELECT);
		if ("警员".equals(loginUserUnit.getUserName())) {
			// 民警
			cpa.add(Restrictions.or(Restrictions.eq("alarmUnitId", loginUserUnit.getUnitId()), Restrictions.eq("m.userId", user.getId())));
		} else {
			if (!"city".equals(loginUserUnit.getUnitRank().getName())) {
				// 非市级管理员
				cpa.add(Restrictions.in("alarmUnitId", loginUserUnit.getUnitSonIds()));
			}
		}
		super.findByPage(page, cpa);
		//return page;
	}

	/**
	 * 今日布控数量
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月11日 下午1:44:51
	 */
	@Override
	@Transactional(readOnly = true)
	public Long findTodayCount(LoginUserUnit loginUserUnit) {
		String hql = "select count(*) from MonitorAlarm ma where ma.createDate >=:startDate and ma.createDate <=:endDate";
		if ("警员".equals(loginUserUnit.getUserName())) {
			// 民警
			hql = hql + " and ma.alarmUnitId = :alarmUnitId";
		} else {
			if (!"city".equals(loginUserUnit.getUnitRank().getName())) {
				// 非市级管理员
				hql = hql + " and ma.alarmUnitId in :alarmUnitIds";
			}
		}
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", RuiecDateUtils.getWeeHours(new Date(), 0));
		query.setParameter("endDate", RuiecDateUtils.getWeeHours(new Date(), 1));
		if ("警员".equals(loginUserUnit.getUserName())) {
			// 民警
			query.setParameter("alarmUnitId", loginUserUnit.getUnitId());
		} else {
			if (!"city".equals(loginUserUnit.getUnitRank().getName())) {
				// 非市级管理员
				query.setParameterList("alarmUnitIds", loginUserUnit.getUnitSonIds());
			}
		}
		return (Long) query.uniqueResult();
	}

	/**
	 * 获取字典数据
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月11日 下午2:38:48
	 */
	public List<Map<String, Object>> getDictionary(String name) {
		List<Map<String, Object>> maps = dictionaryService.findByItemCode(name, 0);
		if (name != null) {
			// 根据类型id查询简单类型数据
			maps = dictionaryService.findByItemCode(name, 0);
		}
		return maps;
	}

	/**
	 * 是否有权限查看预警或反馈
	 * 
	 * @author 陈靖原<br>
	 * @param aid预警编号
	 * @date 2018年1月11日 下午2:38:48
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isPower(User user, LoginUserUnit loginUserUnit, Integer id, String type) {
		
		// 预警
		MonitorAlarm ma = get(id);
		if (ma == null) {
			return false;
		}
		if (StringUtils.isBlank(type)) {
			return false;
		}
		// 是否管理预警派出所
		boolean isManagerUnit = isManagerUnit(loginUserUnit, ma.getAlarmUnitId());
		switch (type) {
		// 反馈
		case "feedback":
			// 判断是否为签收人
			if (ma.getSignUserId() != null && ma.getSignUserId().equals(user.getId())) {
				return true;
			}
			break;
		// 签收
		case "sign":
			// 管理该单位
			if (isManagerUnit) {
				return true;
			}
			// 用户为布控民警
			if (ma.getMonitor().getUnitId() != null && ma.getMonitor().getUnitId().equals(user.getId())) {
				return true;
			}
			break;
		// 查看
		case "look":
			if (user.getId() == 1) {// 超管放行
				return true;
			}
			// 管理该单位
			if (isManagerUnit) {
				return true;
			}
			// 用户为布控民警
			if (ma.getMonitor().getUnitId() != null && ma.getMonitor().getUnitId().equals(user.getId())) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * 根据布控人统计预警数量
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月11日 下午2:38:48
	 */
	@Override
	@Transactional(readOnly = true)
	public Long selectByMonitorAlarmCount(Integer personId) {
		String hql = "select count(ma.id) from MonitorAlarm ma " + ",Monitor mt where mt.id = ma.monitor.id and mt.isDelete = 0 and ma.monitor.id = :personId";
		Query query = getSession().createQuery(hql);
		query.setParameter("personId", personId);
		Long count = (long) ((Number) query.uniqueResult()).intValue();
		return count;
	}

	/**
	 * 管理员是否管理该单位
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月20日 上午11:30:03
	 */
	private boolean isManagerUnit(LoginUserUnit loginUserUnit, Integer unitId) {
		if (loginUserUnit.getUnitRank().getName().equals("city")) {
			return true;
		}
		if (null != loginUserUnit.getUnitSonIds() && loginUserUnit.getUnitSonIds().size() > 0) {
			for (int i = 0; i < loginUserUnit.getUnitSonIds().size(); i++) {
				if (unitId.equals(loginUserUnit.getUnitSonIds().get(i))) {
					return true;
				}
			}
		}
		return false;
	}
}
