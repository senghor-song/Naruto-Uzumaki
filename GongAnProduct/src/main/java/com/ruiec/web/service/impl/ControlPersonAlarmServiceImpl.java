/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.dto.AlarmDTO;
import com.ruiec.web.dto.ControlPersonAlarmDTO;
import com.ruiec.web.entity.ControlPerson;
import com.ruiec.web.entity.ControlPersonAlarm;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.APIConfigService;
import com.ruiec.web.service.ControlPersonAlarmService;
import com.ruiec.web.service.ControlPersonInstructiService;
import com.ruiec.web.service.ControlPersonService;
import com.ruiec.web.service.ControlPersonTypeService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.DictionaryTypeService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.util.RuiecDateUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 重点人员预警服务接口实现类
 * 
 * @author Senghor<br>
 * @date 2017年11月30日 上午9:01:37
 */
@Service("controlPersonAlarmServiceImpl")
public class ControlPersonAlarmServiceImpl extends BaseServiceImpl<ControlPersonAlarm, Integer>
		implements ControlPersonAlarmService {

	@Resource
	private DictionaryTypeService dictionaryTypeService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private ControlPersonService controlPersonService;
	@Resource
	private ControlPersonInstructiService controlPersonInstructiService;
	@Resource
	private UnitService unitService;
	@Resource
	private UserService userService;
	@Resource
	private ControlPersonTypeService controlPersonTypeService;
	@Resource
	private APIConfigService apiConfigService;

	/**
	 * 根据预警级别分类获取数量
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年11月29日 下午10:50:06
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<ControlPersonAlarmDTO> getWarnCount(LoginUserUnit loginUserUnit, User loginUser) {
		String hql = "";
		boolean uids = false;
		boolean cpid = false;
		// 根据登录用户身份查看对应数据
		if (null != loginUserUnit) {
			if (!"警员".equals(loginUserUnit.getUserName())) {
				if ("city".equals(loginUserUnit.getUnitRank().getName())) {
					hql = "select new com.ruiec.web.dto.ControlPersonAlarmDTO(count(*),cpa.warningLevel) from ControlPersonAlarm cpa "
							+ ",ControlPerson cp where cpa.createDate >=:startDate and cpa.createDate <=:endDate and cp.id = cpa.controlPerson.id and cp.isDelete = 0 group by cpa.warningLevel";
					// 市局管理员
				} else {
					// 管理员(管控单位)
					hql = "select new com.ruiec.web.dto.ControlPersonAlarmDTO(count(*),cpa.warningLevel) from ControlPersonAlarm cpa "
							+ ",ControlPerson cp where cpa.createDate >=:startDate and cpa.createDate <=:endDate and cp.id = cpa.controlPerson.id and cp.isDelete = 0 and cpa.controlUnit in (:uids) group by cpa.warningLevel";
					uids = true;
				}
			} else {
				// 警员(责任人)
				hql = "select new com.ruiec.web.dto.ControlPersonAlarmDTO(count(*),cpa.warningLevel) from ControlPersonAlarm cpa "
						+ ",ControlPerson cp where cpa.createDate >=:startDate and cpa.createDate <=:endDate and cp.id = cpa.controlPerson.id and cp.isDelete = 0 and cp.userId=:cpid group by cpa.warningLevel";
				cpid = true;
			}
		} else {
			// 警员(责任人)
			hql = "select new com.ruiec.web.dto.ControlPersonAlarmDTO(count(*),cpa.warningLevel) from ControlPersonAlarm cpa "
					+ ",ControlPerson cp where cpa.createDate >=:startDate and cpa.createDate <=:endDate and cp.id = cpa.controlPerson.id and cp.isDelete = 0 and cp.userId=:cpid group by cpa.warningLevel";
			cpid = true;
		}
		Query query = getSession().createQuery(hql);
		query.setParameter("startDate", getTimeOfDay(new Date(), 0, 0, 0, 0));
		query.setParameter("endDate", getTimeOfDay(new Date(), 23, 59, 59, 999));
		if (uids) {
			query.setParameterList("uids", loginUserUnit.getUnitSonIds().toArray());
		}
		if (cpid) {
			query.setParameter("cpid", loginUser.getId());
		}
		List<ControlPersonAlarmDTO> list = query.list();
		return list;
	}

	// 获取时间
	public Date getTimeOfDay(Date date, int hour, int min, int sec, int misec) {
		Calendar day = Calendar.getInstance();
		day.setTime(date);
		day.set(Calendar.HOUR_OF_DAY, hour);
		day.set(Calendar.MINUTE, min);
		day.set(Calendar.SECOND, sec);
		day.set(Calendar.MILLISECOND, misec);
		return day.getTime();
	}

	/**
	 * 获取所有目的地
	 * 
	 * @author yuankai 
	 * @date 2017年11月29日 下午10:50:06
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<String> selectLocation() {
		String sql = "select distinct DESTINATION FROM T_COR_CONTROL_PERSON_ALARM where DESTINATION is not null";
		Query query = getSession().createSQLQuery(sql);
		List<String> list = query.list();
		return list;
	}

	/**
	 * 根据idCard去重，在用排序获取最大的id
	 * 
	 * @author yuankai 
     * @date 2017年11月29日 下午10:50:06
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Object> steadyControl() {
		String sql = "select max(T.PRIKEY) as from T_COR_CONTROL_PERSON_ALARM T group by CONTROL_PERSON_ID";
		Query query = getSession().createSQLQuery(sql);
		List<Object> list = query.list();
		return list;
	}

	/**
	 * 根据条件查询稳控
	 * 
	 * @author yuankai<br>
	 * @date 2017年11月29日
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> stableControlList(Page page, String signStatus, String search, String searchType,
			ControlPerson cp, ControlPersonAlarm cpam, AlarmDTO alarmDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> list = new ArrayList<Object>();
		DetachedCriteria cpa = DetachedCriteria.forClass(ControlPersonAlarm.class);
		cpa.setProjection(Projections.projectionList().add(Projections.max("id")).add(Projections.groupProperty("id")));
		DetachedCriteria cpn = cpa.createCriteria("controlPerson", JoinType.LEFT_OUTER_JOIN);
		cpn.setFetchMode("controlPersonTypes", FetchMode.SELECT);
		cpa.setFetchMode("id", FetchMode.SELECT);

		list = steadyControl();
		List<Integer> alarms = new ArrayList<Integer>();
		cpa.setProjection(Projections.max("id"));
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				alarms.add(Integer.valueOf(list.get(i).toString()));

			}				
		}
//		if (alarms.size() == 0) {
//			return null;
//		}
        // alarms为存储数据的数组，alarms.get(i)中的数据不超过1000
		Disjunction disjunction = Restrictions.disjunction();
		for (int i = 0; i < alarms.size(); i++) {
			disjunction.add(Restrictions.eq("id", alarms.get(i)));
		}
		cpa.add(disjunction);

		// 重点人姓名
		if (searchType != null && "name".equals(search) && StringUtils.isNotBlank(search)) {
			cpn.add(Restrictions.eq("name", searchType));
		}
		// 重点人身份证
		if (searchType != null && "idCard".equals(search) && StringUtils.isNotBlank(search)) {
			cpn.add(Restrictions.eq("idCard", searchType));
		}
		// 目的地
		if (null != cpam.getDestination()) {
			cpa.add(Restrictions.eq("destination", cpam.getDestination()));
		}
		// 根据人员类别id查询下级所有数据
		if (null != alarmDTO.getPersonType()) {
			Integer[] personTypeIds = dictionaryService.findSonId(alarmDTO.getPersonType());
			if (personTypeIds != null && personTypeIds.length > 0) {
				cpn.createAlias("controlPersonTypes", "c");
				cpn.add(Restrictions.in("c.dictionaryId", personTypeIds));
			}
		}
		// 是否签收 1.未签收 2.已签收
		if (null != cpam.getSignStatus()) {
			cpa.add(Restrictions.eq("signStatus", cpam.getSignStatus()));
		}
		// 是否反馈(反馈时间不为空时为已反馈，否则未反馈)
		if (!StringUtils.isBlank(cpam.getFeedbackTime())) {
			if (!"no".equals(cpam.getFeedbackTime())) {
				cpa.add(Restrictions.isNotNull("feedbackTime"));
			} else {
				cpa.add(Restrictions.isNull("feedbackTime"));
			}
		}
		// 尚未被逻辑删除的重点人
		cpn.add(Restrictions.eq("isDelete", 0));
		// 登记时间
		if (null != alarmDTO.getStartDate()) {
			cpa.add(Restrictions.ge("createDate", alarmDTO.getStartDate()));
		}
		if (null != alarmDTO.getEndDate()) {
			cpa.add(Restrictions.le("createDate", alarmDTO.getEndDate()));
		}
		// 动态信息类别
		if (alarmDTO.getDynamicTypeIdz() != null && alarmDTO.getDynamicTypeIdz().length > 0) {
			cpa.add(Restrictions.in("dynamicInfoTypeId", alarmDTO.getDynamicTypeIdz()));
		}
		// 预警级别（当天）
		if (!StringUtils.isBlank(alarmDTO.getTodayAlarm())) {
			cpa.add(Restrictions.eq("warningLevel", alarmDTO.getTodayAlarm()));
			// 开始时间
			cpa.add(Restrictions.ge("createDate", getTimeOfDay(new Date(), 0, 0, 0, 0)));
			// 结束时间
			cpa.add(Restrictions.le("createDate", getTimeOfDay(new Date(), 23, 59, 59, 999)));
		}
		// 预警地区
		if (null != alarmDTO.getUnitId()) {
			cpa.add(Restrictions.eq("alarmUnit", alarmDTO.getUnitId()));
		}

		// 稳控状态
		List<Map<String, Object>> stableControl = getDictionary("steady");
    	map.put("stableControl", stableControl);
		// 动态信息类别
		List<Map<String, Object>> dynamicType = apiConfigService.getApiConfig();
		map.put("dynamicType", dynamicType); 
		// 人员类别
		List<Map<String, Object>> dataitem = getDictionary("personClass");
		map.put("dataitem", dataitem);
		// 预警地区
		List<Unit> units = unitService.findList(null, Filter.eq("unitRank", "area"), null);
		map.put("units", units);

		// 分页
		findByPage(page, cpa);
		map.put("page", page);

		return map;
	}

	/**
	 * 根据条件查询预警
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年11月29日 下午10:50:06
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> searchForAlarm(Page page, ControlPerson cp, ControlPersonAlarm cpam, AlarmDTO alarmDTO,
			LoginUserUnit loginUserUnit, User loginUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		//
		DetachedCriteria cpa = DetachedCriteria.forClass(ControlPersonAlarm.class);
		DetachedCriteria cpn = cpa.createCriteria("controlPerson", JoinType.LEFT_OUTER_JOIN);
		cpn.setFetchMode("controlPersonTypes", FetchMode.SELECT);
		// 根据登录用户身份查看对应数据
		if (null != loginUserUnit) {
			if (!"警员".equals(loginUserUnit.getUserName())) {
				if ("city".equals(loginUserUnit.getUnitRank().getName())) {
					// 市局管理员
				} else {
					// 管理员
					cpa.add(Restrictions.in("controlUnit", loginUserUnit.getUnitSonIds().toArray()));
				}
			} else {
				cpn.add(Restrictions.eq("userId", loginUser.getId()));
			}
		} else {
			// 警员
			cpn.add(Restrictions.eq("userId", loginUser.getId()));
		}
		// 重点人身份证号
		if (!StringUtils.isBlank(cp.getIdCard())) {
			cpn.add(Restrictions.like("idCard", cp.getIdCard(), MatchMode.ANYWHERE));
		}
		// 重点人姓名
		if (!StringUtils.isBlank(cp.getName())) {
			cpn.add(Restrictions.like("name", cp.getName(), MatchMode.ANYWHERE));
		}
		// 是否签收 1.未签收 2.已签收
		if (null != cpam.getSignStatus()) {
			cpa.add(Restrictions.eq("signStatus", cpam.getSignStatus()));
		}
		// 是否反馈(反馈时间不为空时为已反馈，否则未反馈)
		if (!StringUtils.isBlank(cpam.getFeedbackTime())) {
			if (!"no".equals(cpam.getFeedbackTime())) {
				cpa.add(Restrictions.isNotNull("feedbackTime"));
			} else {
				cpa.add(Restrictions.isNull("feedbackTime"));
			}
		}
		// 尚未被逻辑删除的重点人
		cpn.add(Restrictions.eq("isDelete", 0));
		// 人员类别
		// 根据人员类别id查询下级所有数据
		if (null != alarmDTO.getPersonType()) {
			Integer[] personTypeIds = dictionaryService.findSonId(alarmDTO.getPersonType());
			if (personTypeIds != null && personTypeIds.length > 0) {
				cpn.createAlias("controlPersonTypes", "c");
				cpn.add(Restrictions.in("c.dictionaryId", personTypeIds));
			}
		}
		// 登记时间
		if (null != alarmDTO.getStartDate()) {
			cpa.add(Restrictions.ge("createDate", alarmDTO.getStartDate()));
		}
		if (null != alarmDTO.getEndDate()) {
			cpa.add(Restrictions.le("createDate", alarmDTO.getEndDate()));
		}
		// 动态信息类别
		if (null != alarmDTO.getDynamicTypeIdz() && alarmDTO.getDynamicTypeIdz().length > 0) {
			cpa.add(Restrictions.in("dynamicInfoTypeId", alarmDTO.getDynamicTypeIdz()));
		}
		// 预警级别（当天）
		if (!StringUtils.isBlank(alarmDTO.getTodayAlarm())) {
			cpa.add(Restrictions.eq("warningLevel", alarmDTO.getTodayAlarm()));
			// 开始时间
			cpa.add(Restrictions.ge("createDate", getTimeOfDay(new Date(), 0, 0, 0, 0)));
			// 结束时间
			cpa.add(Restrictions.le("createDate", getTimeOfDay(new Date(), 23, 59, 59, 999)));
		}
		// 预警地区
		if (null != alarmDTO.getUnitId()) {
			cpa.add(Restrictions.eq("alarmUnit", alarmDTO.getUnitId()));
		}
		// 预警级别
		if (StringUtils.isNotBlank(alarmDTO.getWarningLevel())) {
			cpa.add(Restrictions.eq("warningLevel", alarmDTO.getWarningLevel()));
		}

		// 字典查询
		// 轨迹类型
		List<Map<String, Object>> dynamicType = apiConfigService.getApiConfig();
		map.put("dynamicType", dynamicType);
		// 人员类别
		List<Map<String, Object>> dataitem = getDictionary("personClass");
		map.put("dataitem", dataitem);
		// 预警地区
		List<Unit> units = unitService.findList(null, Filter.eq("unitRank", "area"), null);
		map.put("units", units);
		// 预警级别
		List<Map<String, Object>> warningLevels = getDictionary("warningLevel");
		map.put("warningLevels", warningLevels);
		// 分页
		findByPage(page, cpa);
		map.put("page", page);
		return map;
	}

	// 获取字典数据
	public List<Map<String, Object>> getDictionary(String name) {
		List<Map<String, Object>> maps = dictionaryService.findByItemCode(name, 0);
		if (name != null) {
			// 根据类型id查询简单类型数据
			maps = dictionaryService.findByItemCode(name, 0);
		}
		return maps;
	}

	/**
	 * 根据身份查询预警总数
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月22日 下午10:50:06
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Long alarmCount(LoginUserUnit loginUserUnit, User loginUser) {
		String hql = "";
		boolean uids = false;
		boolean cpid = false;
		// 根据登录用户身份查看对应数据
		if (null != loginUserUnit) {
			if (!"警员".equals(loginUserUnit.getUserName())) {
				if ("city".equals(loginUserUnit.getUnitRank().getName())) {
					hql = "select count(cpa.id) from ControlPersonAlarm cpa "
							+ ",ControlPerson cp where cp.id = cpa.controlPerson.id and cp.isDelete = 0";
					// 市局管理员
				} else {
					// 管理员(管控单位)
					hql = "select count(cpa.id) from ControlPersonAlarm cpa "
							+ ",ControlPerson cp where cp.id = cpa.controlPerson.id and cp.isDelete = 0 and cpa.controlUnit in (:uids) ";
					uids = true;
				}
			} else {
				// 警员(责任人)
				hql = "select count(cpa.id) from ControlPersonAlarm cpa "
						+ ",ControlPerson cp where cp.id = cpa.controlPerson.id and cp.isDelete = 0 and cp.userId=:cpid ";
				cpid = true;
			}
		} else {
			// 警员(责任人)
			hql = "select count(cpa.id) from ControlPersonAlarm cpa "
					+ ",ControlPerson cp where cp.id = cpa.controlPerson.id and cp.isDelete = 0 and cp.userId=:cpid ";
			cpid = true;
		}
		Query query = getSession().createQuery(hql);
		if (uids) {
			query.setParameterList("uids", loginUserUnit.getUnitSonIds().toArray());
		}
		if (cpid) {
			query.setParameter("cpid", loginUser.getId());
		}
		List<Long> list = query.list();
		Long count = list.get(0);
		return count;
	}

	/**
	 * 根据重点人统计预警数量
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月30日 下午1:49:00
	 */
	@Override
	@Transactional(readOnly = true)
	public Long selectByPersonAlarmCount(Integer personId) {
		String hql = "select count(cpa.id) from ControlPersonAlarm cpa "
				+ ",ControlPerson cp where cp.id = cpa.controlPerson.id and cp.isDelete = 0 and cp.id = :personId";
		Query query = getSession().createQuery(hql);
		query.setParameter("personId", personId);
		Long count = (long) ((Number) query.uniqueResult()).intValue();
		return count;
	}

	/**
	 * 解析动态信息
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月22日 下午10:50:06
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getDynamic(String j) {
		JSONObject json = JSONObject.fromObject(j);
		List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
		if (!json.isEmpty()) {
			// 标题(键)
			JSONObject jsons = JSONObject.fromObject(json.opt("title"));
			Iterator<String> titles = jsons.keys();
			Map<String, Object> map = new HashMap<String, Object>();
			while (titles.hasNext()) {
				// 获得key
				String key = titles.next();
				map.put(key, jsons.getString(key));
			}
			// 值(json数组)
			JSONArray js = JSONArray.fromObject(json.opt("value"));
			for (Object object : js) {
				JSONObject obj = JSONObject.fromObject(object);
				Iterator<String> values = obj.keys();
				Map<String, Object> mapz = new HashMap<String, Object>();
				while (values.hasNext()) {
					// 获得keyz
					String keyz = values.next();
					String value = obj.getString(keyz);
					mapz.put(keyz, value);
					for (String key : map.keySet()) {
						if (value.equals(key)) {
							mapz.put(keyz, map.get(key));
						}
					}
				}
				ls.add(mapz);
			}
		}
		return ls;
	}

	/**
	 * 是否有权限进行预警或指令操作
	 * 
	 * @author 陈靖原<br>
	 * @param aid预警编号
	 *            iid指令编号
	 * @date 2017年12月22日 下午10:50:06
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isPower(User user, LoginUserUnit loginUserUnit, Integer aid, String type) {
		if (aid == null) {
			return false;
		}
		// 预警
		ControlPersonAlarm c = get(aid);
		if (c == null) {
			return false;
		}
		if (StringUtils.isBlank(type)) {
			return false;
		}
		// 是否管理单位(身份)
		boolean flag = isManager(loginUserUnit);
		// 是否管理该单位
		boolean isManagerUnit = isManagerUnit(loginUserUnit, c.getControlUnit());
		// 超管
		switch (type) {
		// 反馈
		case "feedback":
			if (c.getSignPrikey() != null && c.getSignPrikey().equals(user.getId())) {
				return true;
			}
			break;
		// 签收
		case "sign":
			if (flag || user.getId() == 1) {
				if (isManagerUnit || user.getId() == 1) {
					return true;
				}
			} else {
				if (c.getControlPerson().getUserId() != null && c.getControlPerson().getUserId().equals(user.getId())) {
					return true;
				}
			}
			break;
		// 查看
		case "look":
			if (flag || user.getId() == 1) {
				if (isManagerUnit || user.getId() == 1) {
					return true;
				}
			} else {
				if (c.getControlPerson().getUserId() != null && c.getControlPerson().getUserId().equals(user.getId())) {
					return true;
				}
			}
			break;
		// 下发
		case "issued":
			if (flag || user.getId() == 1) {
				if (isManagerUnit || user.getId() == 1) {
					return true;
				}
			}
			break;
		default:
			break;
		}
		return false;
	}
	
	/**
	 * 当日预警数量
	 * @author 陈靖原<br>
	 * @date 2018年1月22日 上午9:07:28
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> alarmCounts(User loginUser, LoginUserUnit loginUserUnit){
		// 查询所有预警类型
		List<Map<String, Object>> lists = getDictionary("warningLevel");
		// 查询当日的预警次数
		List<ControlPersonAlarmDTO> list = getWarnCount(loginUserUnit, loginUser);
		// 不同预警的预警次数
		List<Map<String, Object>> listz = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < lists.size(); i++) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("id", lists.get(i).get("id"));
			maps.put("itemName", lists.get(i).get("itemName"));
			maps.put("itemValue", lists.get(i).get("itemValue"));
			if (list != null && list.size() > 0) {
				for (int j = 0; j < list.size(); j++) {
					if ((lists.get(i).get("id").toString()).equals(list.get(j).getWarningLevel())) {
						maps.put("count", list.get(j).getCount());
					}
				}
			}else {
				maps.put("count", 0);
			}
			listz.add(maps);
		}
		return listz;
	}

	/**
	 * 是否为管理员
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
	 * @author 陈靖原<br>
	 * @date 2018年1月20日 上午11:30:03
	 */
	private boolean isManagerUnit(LoginUserUnit loginUserUnit,Integer unitId) {
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
	 * 预警反馈
	 * @author 陈靖原<br>
	 * @param 1.jsonStr 反馈内容 2.id 预警编号 3.user 登录用户 4.loginUserUnit 用户权限
	 * @date 2018年1月22日 上午9:07:28
	 */
	@Override
	@Transactional
	public JsonReturn feedback(String feedbackContent, Integer id, User user, LoginUserUnit loginUserUnit) {
        if (null == user) {
            return new JsonReturn(400,"反馈失败","/admin/controlPersonAlarm/list.shtml");
        }
//        ControlPersonAlarm controlPersonAlarm = super.get(id);
		ControlPersonAlarm controlPersonAlarmOut = super.get(id);
        ControlPersonAlarm controlPersonAlarm = new ControlPersonAlarm();
        controlPersonAlarm.setId(id);
        controlPersonAlarm.setRemark(controlPersonAlarmOut.getRemark());
		JSONObject json = JSONObject.fromObject(feedbackContent);
		if(StringUtils.isBlank(feedbackContent)) {
			return new JsonReturn(400,"反馈失败，请输入反馈内容");
		}
		boolean flag = isPower(user, loginUserUnit, id, "feedback");
		if (!flag) {
			return new JsonReturn(400,"无权限反馈");
		}
		Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
		//完善反馈信息
		json.put("userName", user.getPoliceName());
		json.put("unitName", unit!=null ? unit.getUnitName() : "暂无");
		json.put("feedbackTime", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(new Date()));
		//判断是否有进行过反馈
		if(StringUtils.isBlank(controlPersonAlarm.getRemark())){
			JSONArray jsons=new JSONArray();
			jsons.add(json);
			//反馈内容
			controlPersonAlarm.setRemark(jsons.toString());
		}else{
			JSONArray jsons=JSONArray.fromObject(controlPersonAlarm.getRemark());
			jsons.add(json);
			//追加反馈内容
			controlPersonAlarm.setRemark(jsons.toString());
		}
		//反馈时间
		controlPersonAlarm.setFeedbackTime(RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(new Date()));
		
		//反馈信息
		super.updateInclude(controlPersonAlarm, new String[] {"remark","feedbackTime"}, null);
		return new JsonReturn(200,"反馈成功","/admin/controlPersonAlarm/list.shtml");
	}

	/**
	 * 预警反馈详情
	 * @author 陈靖原<br>
	 * @date 2018年1月22日 上午9:07:28
	 */
	@Override
	@Transactional(readOnly = true)
	public JsonReturn alarmFeedbackDetails(Integer id) {
		ControlPersonAlarm cpa = get(id);
		JSONArray jsonArray = JSONArray.fromObject(cpa.getRemark());
		JSONArray jsons = new JSONArray();
		for (Object obj : jsonArray) {
			JSONObject json = JSONObject.fromObject(obj);
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
			jsons.add(json);
		}
		return new JsonReturn(jsons);
	}
}
