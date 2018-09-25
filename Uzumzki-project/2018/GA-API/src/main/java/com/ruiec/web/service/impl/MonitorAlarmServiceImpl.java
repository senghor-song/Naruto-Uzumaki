package com.ruiec.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.APIConfig;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.DynamicInfo;
import com.ruiec.web.entity.MonitorAlarm;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.APIConfigService;
import com.ruiec.web.service.ControlPersonAlarmService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.DynamicInfoService;
import com.ruiec.web.service.MonitorAlarmService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.util.MapUtils;
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
	@Resource
	private UnitService unitService;
	@Resource
	private UserService userService;
	@Resource
	private ControlPersonAlarmService controlPersonAlarmService;
	@Resource
	private DynamicInfoService dynamicInfoService;
	@Resource
	private APIConfigService apiConfigService;

	/**
	 * 布控预警分页查询
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月11日 上午9:32:39
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Map<String, Object> findByPage(Page page, User loginUser, LoginUserUnit loginUserUnit) {
		Map<String, Object> map = new HashMap<>();
		DetachedCriteria ma = DetachedCriteria.forClass(MonitorAlarm.class);
		DetachedCriteria m = ma.createCriteria("monitor", "m");
		if ("警员".equals(loginUserUnit.getUserName())) {
			// 民警
			ma.add(Restrictions.or(Restrictions.eq("alarmUnitId", loginUserUnit.getUnitId()), Restrictions.eq("m.userId", loginUser.getId())));
		} else {
			if (!"city".equals(loginUserUnit.getUnitRank().getName())) {
				// 非市级管理员
				ma.add(Restrictions.in("alarmUnitId", loginUserUnit.getUnitSonIds()));
			}
		}
        m.add(Restrictions.eq("isDelete", 0));//查询未删除的数据
		ma.add(Restrictions.eq("signUserId", loginUser.getId()));
		//查询需要再次反馈的数据（则反馈时间不为空的数据）
        ma.add(Restrictions.isNotNull("feedbackTime"));
		super.findByPage(page, ma);
		// 重新封装结果集
		List<MonitorAlarm> monitorAlarms = (List<MonitorAlarm>) page.getList();
		map.put("pageSize", page.getPageSize());
		map.put("totalCount", page.getTotalCount());
		map.put("pageNumber", page.getPageNumber());
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> obj = null;
		for (MonitorAlarm monitorAlarm : monitorAlarms) {
			obj = new HashMap<>();
			obj.put("id", monitorAlarm.getId());
			obj.put("name", ObjectUtils.firstNonNull(monitorAlarm.getMonitor().getName(),GlobalUnit.NULLMSG));
			obj.put("idCard", ObjectUtils.firstNonNull(monitorAlarm.getIdCard(), monitorAlarm.getMonitor().getIdCard(), GlobalUnit.NULLMSG));
			// 布控单位
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, monitorAlarm.getMonitor().getUnitId());
			if (unit != null) {
				obj.put("unitName", unit.getUnitName());
			} else {
				obj.put("unitName", GlobalUnit.NULLMSG);
			}
			// 签收人
			User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, monitorAlarm.getSignUserId());
			if (user != null) {
				obj.put("signName", user.getPoliceName());
			} else {
				obj.put("signName", GlobalUnit.NULLMSG);
			}
			// 预警时间
			obj.put("createDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(monitorAlarm.getCreateDate()));
			// 是否反馈
			if (monitorAlarm.getSignStatus() != null && monitorAlarm.getSignStatus() == 2) {// 已签收
				// 判断签收人是否为当前用户
				if (user.getId().equals(monitorAlarm.getSignUserId())) {
					if (monitorAlarm.getFeedbackTime() != null) {
						obj.put("state", "已反馈");
					} else {
						obj.put("state", "未反馈");
					}
				}
			} else {
				obj.put("state", "未签收");
			}
			// 轨迹类型
            APIConfig apiConfig = (APIConfig) RedisUtil.getRedisOne(GlobalUnit.API_CONFIG_MAP, monitorAlarm.getDynamicInfoTypeId());
            obj.put("dynamicType", apiConfig!=null ? apiConfig.getName() : GlobalUnit.USER_MAP);
            Unit unitAlarm = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, monitorAlarm.getAlarmUnitId());
            if (unitAlarm != null) {
                obj.put("unitAlarmName", unitAlarm.getUnitName());
            } else {
                obj.put("unitAlarmName", GlobalUnit.NULLMSG);
            }
			list.add(obj);
		}
		map.put("mapList", list);
		return map;
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
	 * 根据id查询重点人基本信息
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月12日 上午11:11:51
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> findMapById(Integer id) {
		Map<String, Object> map = new HashMap<>();
		// 查询布控预警详情
		DetachedCriteria cpn = DetachedCriteria.forClass(MonitorAlarm.class);
		cpn.createCriteria("monitor", JoinType.LEFT_OUTER_JOIN);
		cpn.add(Restrictions.eq("id", id));
		MonitorAlarm monitorAlarm = super.get(id, null, Fetch.join("monitor"));
		// 重新封装结果集
		/** ----------- 个人信息--------------- **/
		map.put("id", monitorAlarm.getId());
		map.put("name", ObjectUtils.firstNonNull(monitorAlarm.getMonitor().getName(),GlobalUnit.NULLMSG));
		map.put("photo", ObjectUtils.firstNonNull(monitorAlarm.getMonitor().getPhoto(), ""));
		map.put("contactInfo", ObjectUtils.firstNonNull(monitorAlarm.getMonitor().getContactInfo(), GlobalUnit.NULLMSG));// 联系方式
		map.put("idCard", ObjectUtils.firstNonNull(monitorAlarm.getIdCard(), monitorAlarm.getMonitor().getIdCard(), GlobalUnit.NULLMSG));
		map.put("sex", ObjectUtils.firstNonNull(monitorAlarm.getMonitor().getSex(), GlobalUnit.NULLMSG));// 性别
		// 民族
		Dictionary dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, monitorAlarm.getMonitor().getNationNumber());
		if (dictionary != null) {
			map.put("nation", dictionary.getItemName());
		} else {
			map.put("nation", GlobalUnit.NULLMSG);
		}
		map.put("nativeAddress", ObjectUtils.firstNonNull(monitorAlarm.getMonitor().getNativeAddress(), GlobalUnit.NULLMSG));// 户籍地址
		map.put("currentAddress", ObjectUtils.firstNonNull(monitorAlarm.getMonitor().getCurrentAddress(), GlobalUnit.NULLMSG));// 现住地址
		/** ----------- 布控信息--------------- **/
		// 布控单位
		Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, monitorAlarm.getMonitor().getUnitId());
		if (unit != null) {
			map.put("unitName", unit.getUnitName());
		} else {
			map.put("unitName", GlobalUnit.NULLMSG);
		}
		// 联系民警
		User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, monitorAlarm.getMonitor().getUserId());
		if (user != null) {
			map.put("policeName", user.getPoliceName());
		} else {
			map.put("policeName", GlobalUnit.NULLMSG);
		}
		map.put("contactsPolice", ObjectUtils.firstNonNull(monitorAlarm.getMonitor().getContactsPolice(), GlobalUnit.NULLMSG));// 民警电话
		map.put("duration", ObjectUtils.firstNonNull(monitorAlarm.getMonitor().getDuration(), GlobalUnit.NULLMSG));// 布控时间
		// 人员类别
		dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, monitorAlarm.getMonitor().getPersonTypeId());
		if (dictionary != null) {
			map.put("personType", dictionary.getItemName());
		} else {
			map.put("personType", GlobalUnit.NULLMSG);
		}
		// 是否审核
		if (monitorAlarm.getMonitor().getStatus() == null || monitorAlarm.getMonitor().getStatus() == 0) {
			map.put("status", "未审核");
		} else {
			map.put("status", "已审核");
		}
		map.put("createDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(monitorAlarm.getMonitor().getCreateDate()));// 入库时间
        // 危险级别
		map.put("dangerousLevel", GlobalUnit.NULLMSG);
		// 布控理由
		map.put("reason", ObjectUtils.firstNonNull(monitorAlarm.getMonitor().getReason(), GlobalUnit.NULLMSG));
		// 布控人联系方式
		map.put("phone", ObjectUtils.firstNonNull(monitorAlarm.getMonitor().getContactInfo(), GlobalUnit.NULLMSG));
		return map;
	}

	/**
	 * 根据id查询重点人员预警信息
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月12日 上午11:11:51
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> findAlarmDetail(Integer id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		// 查询布控预警详情
		MonitorAlarm monitorAlarm = super.get(id);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 解析轨迹
		if (null != monitorAlarm.getDynamicInfoId()) {
			DynamicInfo dynamicInfo = dynamicInfoService.get(monitorAlarm.getDynamicInfoId());
			if (dynamicInfo != null) {
				list = controlPersonAlarmService.getDynamic(dynamicInfo.getInformation());
			}
		}
		map.put("dynamicInfo", list);
		// 轨迹类型
		APIConfig dynamicType = (APIConfig) RedisUtil.getRedisOne(GlobalUnit.API_CONFIG_MAP, monitorAlarm.getDynamicInfoTypeId());
		if (dynamicType == null) {
			dynamicType = apiConfigService.get(monitorAlarm.getDynamicInfoTypeId());
		}
		if (dynamicType != null) {
			map.put("dynamicType", dynamicType.getName());
		} else {
			map.put("dynamicType", GlobalUnit.NULLMSG);
		}
		// 签收时间
		if (monitorAlarm.getSignTime() != null) {
			map.put("signTime", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(monitorAlarm.getSignTime()));
		} else {
			map.put("signTime", GlobalUnit.NULLMSG);
		}
		// 预警时间
		map.put("createDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(monitorAlarm.getCreateDate()));
		// 反馈时间
		if (monitorAlarm.getFeedbackTime() != null) {
			map.put("feedbackTime", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(monitorAlarm.getFeedbackTime()));
		} else {
			map.put("feedbackTime", GlobalUnit.NULLMSG);
		}
		return map;
	}

	/**
	 * 查询反馈记录
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月12日 上午11:11:51
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> findFeedBackList(Integer id) {
		Map<String, Object> map = null;
		// 查询布控预警详情
		MonitorAlarm monitorAlarm = super.get(id);
		if (monitorAlarm == null || monitorAlarm.getRemark() == null) {
			return new ArrayList<Map<String, Object>>();
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		JSONArray jsonArray = JSONArray.fromObject(monitorAlarm.getRemark());
		for (Object obj : jsonArray) {
			map = new HashMap<>();
			JSONObject json = JSONObject.fromObject(obj);
			if (json.isNullObject()) {
				continue;
			}
			Dictionary contro = dictionaryService.get(json.optInt("contro"));
			if (contro != null) {
				json.put("contro", contro.getItemName());
			} else {
				json.put("contro", "无");
			}
			Dictionary contro2 = dictionaryService.get(json.optInt("contro2"));
			if (contro2 != null) {
				json.put("contro2", contro2.getItemName());
			} else {
				json.put("contro2", "无");
			}
			map = MapUtils.getJsonToMap(json.toString());
			list.add(map);
		}
		return list;
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
				if (loginUserUnit.getUnitSonIds().get(i).equals(unitId)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 根据待签收和待反馈查询
	 * @param
	 * @return
	 * @exception
	 * @author Senghor<br>
	 * @date 2018年4月13日 下午1:48:24
	 */
	@Override
	@Transactional
	public Map<String, Object> findByTypePage(Page page, User loginUser, LoginUserUnit loginUserUnit, Integer type) {
		Map<String, Object> map = new HashMap<>();
		DetachedCriteria ma = DetachedCriteria.forClass(MonitorAlarm.class);
		DetachedCriteria m = ma.createCriteria("monitor", "m");
		if ("警员".equals(loginUserUnit.getUserName())) {
			// 民警
			ma.add(Restrictions.or(Restrictions.eq("alarmUnitId", loginUserUnit.getUnitId()), Restrictions.eq("m.userId", loginUser.getId())));
		} else {
			if (!"city".equals(loginUserUnit.getUnitRank().getName())) {
				// 非市级管理员
				ma.add(Restrictions.in("alarmUnitId", loginUserUnit.getUnitSonIds()));
			}
		}
		//代签收状态(签收人为空)
		if (type == 1) {
			ma.add(Restrictions.isNull("signUserId"));
            ma.add(Restrictions.isNotNull("alarmUnitId"));
		}else {
			//待反馈状态(签收人不为空和反馈时间为空)
			ma.add(Restrictions.isNull("feedbackTime"));
            ma.add(Restrictions.eq("signUserId",loginUser.getId()));
		}
        m.add(Restrictions.eq("isDelete", 0));// 查询未删除的数据
		super.findByPage(page, ma);
		// 重新封装结果集
		List<MonitorAlarm> monitorAlarms = (List<MonitorAlarm>) page.getList();
		map.put("pageSize", page.getPageSize());
		map.put("totalCount", page.getTotalCount());
		map.put("pageNumber", page.getPageNumber());
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> obj = null;
		for (MonitorAlarm monitorAlarm : monitorAlarms) {
			obj = new HashMap<>();
			obj.put("id", monitorAlarm.getId());
			obj.put("name", ObjectUtils.firstNonNull(monitorAlarm.getMonitor().getName(),GlobalUnit.NULLMSG));
			obj.put("idCard", ObjectUtils.firstNonNull(monitorAlarm.getIdCard(), monitorAlarm.getMonitor().getIdCard(), GlobalUnit.NULLMSG));
			// 布控单位
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, monitorAlarm.getMonitor().getUnitId());
			if (unit == null) {
				unit = unitService.get(monitorAlarm.getMonitor().getUnitId());
			}
			if (unit != null) {
				obj.put("unitName", unit.getUnitName());
			} else {
				obj.put("unitName", GlobalUnit.NULLMSG);
			}
			// 签收人
			User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, monitorAlarm.getSignUserId());
			if (user != null) {
				obj.put("signName", user.getPoliceName());
			} else {
				obj.put("signName", GlobalUnit.NULLMSG);
			}
			// 预警时间
			obj.put("createDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(monitorAlarm.getCreateDate()));
			// 是否反馈
			if (monitorAlarm.getSignStatus() != null && monitorAlarm.getSignStatus() == 2) {// 已签收
				if (monitorAlarm.getFeedbackTime() != null) {
					obj.put("state", "已反馈");
				} else {
					obj.put("state", "未反馈");
				}
			} else {
				obj.put("state", "未签收");
			}
			// 轨迹类型
			APIConfig dynamicType = (APIConfig) RedisUtil.getRedisOne(GlobalUnit.API_CONFIG_MAP, monitorAlarm.getDynamicInfoTypeId());
			if (dynamicType == null) {
				dynamicType = apiConfigService.get(monitorAlarm.getDynamicInfoTypeId());
			}
			if (dynamicType != null) {
				obj.put("dynamicType", dynamicType.getName());
			} else {
				obj.put("dynamicType", GlobalUnit.NULLMSG);
			}
			Unit unitAlarm = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, monitorAlarm.getAlarmUnitId());
            if (unitAlarm != null) {
                obj.put("unitAlarmName", unitAlarm.getUnitName());
            } else {
                obj.put("unitAlarmName", GlobalUnit.NULLMSG);
            }
			list.add(obj);
		}
		map.put("mapList", list);
		return map;
	}
}
