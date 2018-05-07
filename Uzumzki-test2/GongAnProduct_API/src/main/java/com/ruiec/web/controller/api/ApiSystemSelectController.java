package com.ruiec.web.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseController;
import com.ruiec.web.common.ApiReturn;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.APIConfig;
import com.ruiec.web.entity.ControlPersonAlarm;
import com.ruiec.web.entity.ControlPersonExtend;
import com.ruiec.web.entity.ControlPersonType;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.LeavePerson;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.ControlPersonAlarmService;
import com.ruiec.web.service.ControlPersonExtendService;
import com.ruiec.web.service.ControlPersonService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.DynamicInfoService;
import com.ruiec.web.service.LeavePersonService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.service.UserUnitService;
import com.ruiec.web.util.RuiecDateUtils;

/**
 * 系统查询api控制器
 * 
 * @author yuankai<br>
 * @date 2018年1月6日 下午7:39:58
 */
@Controller
@RequestMapping("/api/admin/systemSelect")
public class ApiSystemSelectController extends BaseController {

	private static final Logger LOGGER = Logger.getLogger(ApiSystemSelectController.class);

	@Resource
	private LeavePersonService leavePersonService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private OperationLogService operationLogService;
	@Resource
	private ControlPersonService controlPersonService;
	@Resource
	private ControlPersonExtendService controlPersonExtendService;
	@Resource
	private ControlPersonAlarmService controlPersonAlarmService;
	@Resource
	private DynamicInfoService dynamicInfoService;
	@Resource
	private UserUnitService userUnitService;
	@Resource
	private UserService userService;

	/**
	 * 系统查询获取重点人员列表
	 * 
	 * @author yuankai<br>
	 * @date 2018-01-09
	 */
	/*@ResponseBody
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/controlPersonList", method = RequestMethod.POST)
	public ApiReturn controlPersonList(Page page, String search, String searchType, ControlPerson controlPerson, ControlPersonDTO controlPersonDTO) {
		if (searchType == null || searchType.equals(" ") || searchType.length() == 0) {
			return new ApiReturn(400, "请输入查询内容");
		}
		if ("name".equals(search)) {
			page.add(Filter.like("name", searchType));
		}
		if ("idCard".equals(search)) {
			page.add(Filter.eq("idCard", searchType));
		}
		controlPersonService.findByPage(page, controlPerson, controlPersonDTO);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();

		List<ControlPerson> list2 = (List<ControlPerson>) page.getList();
		for (ControlPerson l : list2) {
			map = new HashMap<String, Object>();
			map.put("id", l.getId());
			map.put("name", l.getName());
			map.put("createDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(l.getCreateDate()));
			map.put("photo", l.getControlPersonExtend().getPhoto());
			String columnTub = null;
			switch (l.getIsAuditKeyPersonColumnTub()) {
			case "1":
				columnTub = "已审核";
				break;
			case "2":
				columnTub = "未审核";
				break;
			case "3":
				columnTub = "拒绝";
				break;
			default:
				break;
			}
			map.put("tub", columnTub);
			map.put("columnTub", l.getIsAuditKeyPersonColumnTub());
			map.put("id", l.getId());
			list.add(map);
		}
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("list", list);
		listMap.put("totalCount", page.getTotalCount());
		listMap.put("pageSize", page.getPageSize());
		return new ApiReturn(200, "查询成功!", listMap);
	}*/

	/**
	 * 系统查询预警信息分页信息
	 * 
	 * @author yuankai Date 2018-01-09
	 * */
	@ResponseBody
	@RequestMapping(value = "/controlPersonAlarmList", method = RequestMethod.POST)
	public ApiReturn controlPersonAlarmList(Page page, String searchType, String search, ControlPersonAlarm controlPersonAlarm, HttpServletRequest request) {
		if (StringUtils.isBlank(searchType) || StringUtils.isBlank(search)) {
			return new ApiReturn(400, "请输入查询内容");
		}
		try {
			Map<String, Object> map = controlPersonAlarmService.controlPersonAlarmList(page, null, searchType, search);
			return new ApiReturn(200, "查询预警信息列表成功", map);
		} catch (Exception e) {
			LOGGER.error("查询预警信息列表失败", e);
			return new ApiReturn(400, "查询预警信息列表失败");
		}
	}

	/**
	 * 系统查询离堰人员分页信息
	 * 
	 * @author yuankai Date 2018-01-09
	 * */
	@ResponseBody
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/leavePersonList", method = RequestMethod.POST)
	public ApiReturn leavePersonList(Page page, String search, String searchType) {
	    if (StringUtils.isBlank(searchType) || StringUtils.isBlank(search)) {
			return new ApiReturn(400, "请输入查询内容");
		}
		if ("name".equals(searchType)) {
			page.add(Filter.like("name", search));
		}else if ("idCard".equals(searchType)) {
			page.add(Filter.eq("idCard", search));
		}else {
	        return new ApiReturn(400, "查询条件错误！");
        }
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		leavePersonService.findByPage(page);
		List<LeavePerson> lists = (List<LeavePerson>) page.getList();
		for (LeavePerson leavePerson : lists) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", leavePerson.getId());//离市人员id
			map.put("idCard", ObjectUtils.firstNonNull(leavePerson.getIdCard(),GlobalUnit.NULLMSG));//身份证号
			map.put("name", ObjectUtils.firstNonNull(leavePerson.getName(),GlobalUnit.NULLMSG));//姓名
			map.put("sex", ObjectUtils.firstNonNull(leavePerson.getSex(),GlobalUnit.NULLMSG));//性别
			//出发时间
			map.put("departureTime", ObjectUtils.firstNonNull(leavePerson.getDepartureTime(),GlobalUnit.NULLMSG));
			//出发地-目的地
			map.put("place", leavePerson.getStartPlace()+"-"+leavePerson.getEndPlace());
//			map.put("nativePlace", leavePersons.getNativePlace());//户籍地址
//			map.put("createDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(leavePersons.getCreateDate()));//创建时间
			list.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mapList", list);
		map.put("totalCount", page.getTotalCount());
		map.put("pageNumber", page.getPageNumber());
		map.put("pageSize", page.getPageSize());
		return new ApiReturn(200, "查询成功！", map);
	}

	/**
	 * 系统查询预警信息详情
	 * 
	 * @author yuankai Date 2018-01-10
	 * */
	@ResponseBody
	@RequestMapping(value = "/controlPersonAlarmListDetail", method = RequestMethod.POST)
	public ApiReturn controlPersonAlarmListDetail(HttpServletRequest request, Integer id) {
		User user = (User) request.getSession().getAttribute("user");
		DetachedCriteria cpaCriteria = DetachedCriteria.forClass(ControlPersonAlarm.class);
		DetachedCriteria cpn = cpaCriteria.createCriteria("controlPerson", JoinType.LEFT_OUTER_JOIN);
		cpn.setFetchMode("controlPersonTypes", FetchMode.SELECT);
		cpaCriteria.createCriteria("controlUnit", JoinType.LEFT_OUTER_JOIN);
		ControlPersonAlarm cpa = controlPersonAlarmService.get(id, null, Fetch.join("alarmUnit"), Fetch.join("controlUnit"),
				Fetch.join("controlPerson"));
		if (cpa == null) {
			return new ApiReturn(400, "查无此预警信息！");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		
		ControlPersonExtend cpe = controlPersonExtendService.get(cpa.getControlPerson().getId());
		map.put("photo", ObjectUtils.firstNonNull(cpe.getPhoto(),GlobalUnit.NULLMSG));//人员头像
		map.put("name", ObjectUtils.firstNonNull(cpa.getControlPerson().getName(),GlobalUnit.NULLMSG));//预警人姓名
		map.put("phone", ObjectUtils.firstNonNull(cpa.getControlPerson().getPhone(),GlobalUnit.NULLMSG));//手机号
		map.put("idCard", ObjectUtils.firstNonNull(cpa.getIdCard(),GlobalUnit.NULLMSG));//身份证号
		map.put("sex", ObjectUtils.firstNonNull(cpa.getControlPerson().getSex(),GlobalUnit.NULLMSG));//性别
		map.put("nativePlace", ObjectUtils.firstNonNull(cpe.getNativePlace(),GlobalUnit.NULLMSG));//籍贯
		map.put("householdRegisterPlace", ObjectUtils.firstNonNull(cpe.getHouseholdRegisterPlace(),GlobalUnit.NULLMSG));//户籍详址
		map.put("habitualResidence", ObjectUtils.firstNonNull(cpe.getHabitualResidence(),GlobalUnit.NULLMSG));//现住详址
		map.put("isControl", (cpa.getControlPerson().getIsControl() == null || cpa.getControlPerson().getIsControl() == 0) ? "失控" : "在控");//在控状态
		// 危险级别
		Dictionary dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, cpa.getControlPerson().getDangerousLevel());
		if (dictionary == null) {
			map.put("dangerousLevel", GlobalUnit.NULLMSG);
		} else {
			map.put("dangerousLevel", dictionary.getItemName());
		}
		// 人员类别
		String cpt_str = "";
		dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, cpa.getWarningLevel());
		List<ControlPersonType> cptList = cpa.getControlPerson().getControlPersonTypes();
		if (cptList != null && cptList.size() > 0) {
			for (ControlPersonType controlPersonType : cptList) {
				dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, controlPersonType.getDictionaryId());
				if (dictionary == null) {
					continue;
				}
				cpt_str = cpt_str + dictionary.getItemName() + ",";
			}
		}
		map.put("personnelType", StringUtils.isBlank(cpt_str) ? GlobalUnit.NULLMSG : cpt_str);
		// 管控责任单位
		Unit controlUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, cpa.getControlUnit());
		map.put("controlUnit", controlUnit.getUnitName());
		if (cpa.getControlPerson().getUserId() != null) {
			//责任民警
			User dutyUser = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, cpa.getControlPerson().getUserId());
			map.put("dutyName", dutyUser.getPoliceName());
			//民警电话
			map.put("dutyPhone", dutyUser.getPhone());
		}else {
			//责任民警
			map.put("dutyName", GlobalUnit.NULLMSG);
			//民警电话
			map.put("dutyPhone", GlobalUnit.NULLMSG);
		}
		//列管时间
		map.put("columnDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(cpa.getControlPerson().getColumnDate()));
		//列管理由
		map.put("reason", ObjectUtils.firstNonNull(cpe.getReason(),GlobalUnit.NULLMSG));
		
//		map.put("createDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(controlPersonAlarm.getCreateDate()));//预警时间
//		map.put("signName", controlPersonAlarm.getSignName());//签收人
//		// 签收时间
//		if (StringUtils.isNotBlank(controlPersonAlarm.getSignTime())) {
//			map.put("signTime", controlPersonAlarm.getSignTime());
//		} else {
//			map.put("signTime", GlobalUnit.NULLMSG);
//		}
//		// 反馈时间
//		if (StringUtils.isNotBlank(controlPersonAlarm.getFeedbackTime())) {
//			map.put("feedbackTime", controlPersonAlarm.getFeedbackTime());
//		} else {
//			map.put("feedbackTime", GlobalUnit.NULLMSG);
//		}
//		Unit alarmUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, controlPersonAlarm.getAlarmUnit());
//		// 预警地派出所
//		map.put("alarmUnit", alarmUnit.getUnitName());
//		// 预警级别
//		if (dictionary == null) {
//			map.put("warningLevelColor", GlobalUnit.NULLMSG);
//		} else {
//			map.put("warningLevelColor", dictionary.getItemName());
//		}
		
		// 插入日志
		operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查询预警信息详情",
				LogUtil.getData(request.getParameterMap()));
		return new ApiReturn(200, "查询成功！", map);
	}

	/**
	 * 系统查询离堰人员详情信息
	 * 
	 * @author yuankai Date 2018-01-11
	 * */
	@ResponseBody
	@RequestMapping(value = "/LeavePersonDetail", method = RequestMethod.POST)
	public ApiReturn LeavePersonDetail(Page page, Integer id, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
//		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		List<Unit> unitList = new ArrayList<>();
		Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
		unitList.add(unit);
		LeavePerson leavePerson = leavePersonService.get(id);
		if (leavePerson == null) {
			return new ApiReturn(400, "查无此人！");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", ObjectUtils.firstNonNull(leavePerson.getName(),GlobalUnit.NULLMSG));//姓名
		map.put("idCard", ObjectUtils.firstNonNull(leavePerson.getIdCard(),GlobalUnit.NULLMSG));//身份证号
		map.put("sex", ObjectUtils.firstNonNull(leavePerson.getSex(),GlobalUnit.NULLMSG));//性别
		APIConfig apiConfig = (APIConfig) RedisUtil.getRedisOne(GlobalUnit.API_CONFIG_MAP, leavePerson.getDynamicInfoId());
		map.put("dynamicInfoName", ObjectUtils.firstNonNull(apiConfig.getName(), GlobalUnit.NULLMSG));// 轨迹类型
		map.put("departureTime", ObjectUtils.firstNonNull(leavePerson.getDepartureTime(),GlobalUnit.NULLMSG));//出行时间
		map.put("startPlace", ObjectUtils.firstNonNull(leavePerson.getStartPlace(),GlobalUnit.NULLMSG));//出发地
		map.put("endPlace", ObjectUtils.firstNonNull(leavePerson.getEndPlace(),GlobalUnit.NULLMSG));//目的地
		map.put("nativePlace", ObjectUtils.firstNonNull(leavePerson.getNativePlace(),GlobalUnit.NULLMSG));//户籍地址
		// 户籍地派出所
		map.put("nativePlacePoliceStationCo", ObjectUtils.firstNonNull(leavePerson.getNativePlacePoliceStationCo(),GlobalUnit.NULLMSG));
//		// 签收人
//		if (leavePerson.getSignPersonId() == null) {
//			map.put("signPersonId", GlobalUnit.NULLMSG);
//		} else {
//			User userUnit = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, leavePerson.getSignPersonId());
//			map.put("signPersonId", userUnit.getPoliceName());
//		}
//		// 签收单位
//		if (leavePerson.getSignUnitId() == null) {
//			map.put("signUnitId", GlobalUnit.NULLMSG);
//		} else {
//			Unit userUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, leavePerson.getSignUnitId());
//			map.put("signUnitId", userUnit.getUnitName());
//		}
//		// 查询重点人关系
//		String idCards = null;
//		DetachedCriteria detached = DetachedCriteria.forClass(ControlPersonRelation.class);
//		detached.add(Restrictions.eq("idCard", idCards));
//		// 分页查询离堰人员列表的关系人
//	    Map<String, Object> maps= leavePersonService.findByPageOfLeavePerson(page, user, loginUserUnit, id);
//		map.put("mapList", maps);
		// 插入日志
		operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查询离市人员详情",
				LogUtil.getData(request.getParameterMap()));
		return new ApiReturn(200, "查询成功！", map);
	}
}
