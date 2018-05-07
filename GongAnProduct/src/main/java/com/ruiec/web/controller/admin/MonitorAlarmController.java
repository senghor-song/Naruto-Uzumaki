package com.ruiec.web.controller.admin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.dto.AlarmDTO;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.DynamicInfo;
import com.ruiec.web.entity.Monitor;
import com.ruiec.web.entity.MonitorAlarm;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.APIConfigService;
import com.ruiec.web.service.ControlPersonAlarmService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.DynamicInfoService;
import com.ruiec.web.service.MonitorAlarmService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserUnitService;
import com.ruiec.web.util.RuiecDateUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 布控预警控制器
 * 
 * @author 陈靖原<br>
 * @date 2018年1月6日 下午5:57:25
 */
@Controller("monitorAlarmController")
@RequestMapping("/admin/monitorAlarm")
public class MonitorAlarmController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(MonitorAlarmController.class);

	@Resource
	private MonitorAlarmService monitorAlarmService;
	@Resource
	private OperationLogService operationLogService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private UserUnitService userUnitService;
	@Resource
	private DynamicInfoService dynamicInfoService;
	@Resource
	private ControlPersonAlarmService controlPersonAlarmService;
	@Resource
	private UnitService unitService;
	@Resource
	private APIConfigService apiConfigService;

	/**
	 * 布控预警分页查询
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月11日 上午9:34:36
	 */
	@RequestMapping("/list")
	public String list(Model model, Page page, AlarmDTO alarmDTO, HttpServletRequest request, String count, String todayCount) throws ParseException {
		LOGGER.info("-------------------------------布控预警分页查询开始-----------------------------");
		// 获取登录用户
		User loginUser = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		DetachedCriteria cpa = DetachedCriteria.forClass(MonitorAlarm.class);
		DetachedCriteria cp = cpa.createCriteria("monitor", "m");
		//cpa.createAlias("monitor", "m");
		// 回显
		// 身份证
		if (StringUtils.isNotBlank(alarmDTO.getIdCard())) {
			model.addAttribute("idCard", alarmDTO.getIdCard());
			cpa.add(Restrictions.like("idCard", "%"+alarmDTO.getIdCard()+"%"));
		}
		// 姓名
		if (StringUtils.isNotBlank(alarmDTO.getName())) {
			model.addAttribute("name", alarmDTO.getName());
			cp.add(Restrictions.like("name", "%"+alarmDTO.getName()+"%"));
		}
		// 预警地区
		if (alarmDTO.getUnitId() != null) {
			List<Integer> ids = unitService.findSonIds(alarmDTO.getUnitId());
			cpa.add(Restrictions.in("alarmUnitId", ids.toArray(new Integer[ids.size()])));
			model.addAttribute("unitId", alarmDTO.getUnitId());
		}
		// 是否反馈
		if (alarmDTO.getIsFeedback() != null) {
			model.addAttribute("isFeedback", alarmDTO.getIsFeedback());
			if (alarmDTO.getIsFeedback() == 1) {
				cpa.add(Restrictions.isNotNull("feedbackTime"));
			} else {
				cpa.add(Restrictions.isNull("feedbackTime"));
			}
		}
		// 签收状态
		if (alarmDTO.getSignStatus() != null) {
			model.addAttribute("signStatus", alarmDTO.getSignStatus());
			cpa.add(Restrictions.eq("signStatus", alarmDTO.getSignStatus()));
		}
		// 动态信息类别
		if (alarmDTO.getDynamicTypeIdz() != null && alarmDTO.getDynamicTypeIdz().length > 0) {
			model.addAttribute("dynamicTypeIdz", alarmDTO.getDynamicTypeIdz());
			cpa.add(Restrictions.in("dynamicInfoTypeId", alarmDTO.getDynamicTypeIdz()));
		}
		// 开始时间
		if (alarmDTO.getStartDate() != null) {
			model.addAttribute("startDate", alarmDTO.getStartDate());
			cpa.add(Restrictions.ge("createDate", alarmDTO.getStartDate()));
		}
		// 结束时间
		if (alarmDTO.getEndDate() != null) {
			model.addAttribute("endDate", alarmDTO.getEndDate());
			cpa.add(Restrictions.le("createDate", alarmDTO.getEndDate()));
		}
		// 分页数据
		monitorAlarmService.findByPage(page, cpa, cp, loginUser, loginUserUnit);
		model.addAttribute("page", page);
		// 预警总数
		if (StringUtils.isBlank(count)) {
			count = page.getTotalCount().toString();
		}
		model.addAttribute("count", count);
		// 当日预警数量
		if (StringUtils.isBlank(todayCount)) {
			todayCount = monitorAlarmService.findTodayCount(loginUserUnit).toString();
		}
		model.addAttribute("todayCount", todayCount);
		// 查询单位列表
		List<Map<String, Object>> unitList = userUnitService.findListByUser(loginUser);
		model.addAttribute("units", unitList);

		// 查询所有轨迹类型
		List<Map<String, Object>> dynamicInfoTypeList = apiConfigService.getApiConfig();
		model.addAttribute("dynamicInfoTypeList", dynamicInfoTypeList);
		// 插入操作日志
//		operationLogService.insertOperationLogs(loginUser, 4, request.getRequestURI().toString(), loginUser.getPoliceName() + "查看了布控预警列表信息",
//				LogUtil.getData(request.getParameterMap()));
		LOGGER.info("-------------------------------布控预警分页查询结束-----------------------------");
		return "/admin/monitorAlarm/list";
	}

	/**
	 * 跳转预警反馈页面
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月7日 上午7:53:29
	 */
	@RequestMapping("/alarmFeedback")
	public String alarmFeedback(Model model, Integer id, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		boolean flag = monitorAlarmService.isPower(user, loginUserUnit, id, "feedback");
		if (!flag) {
			return "redirect:/admin/monitorAlarm/list.shtml";
		}
		// 查询字典稳控大类
		List<Map<String, Object>> list = monitorAlarmService.getDictionary("steady");
		// 查询该字典是否有小类，如果有则添加
		List<Map<String, Object>> steady2 = dictionaryService.findSubSet(new Integer(list.get(0).get("id").toString()), null);
		if (steady2.size() > 0) {
			model.addAttribute("steady2", steady2);
		}
		MonitorAlarm cpa = monitorAlarmService.get(id);
		String jsonStr = cpa.getRemark();
		model.addAttribute("steady1", list);
		model.addAttribute("id", id);
		model.addAttribute("jsonStr", jsonStr);
		// 插入日志
		operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "跳转布控预警反馈页面",
				LogUtil.getData(request.getParameterMap()));
		return "/admin/monitorAlarm/foundBack";
	}

	/**
	 * 跳转预警反馈详情页面
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月7日 上午7:53:29
	 */
	@RequestMapping("/alarmFeedbackDetail")
	public String alarmFeedbackDetail(Model model, Integer id, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		boolean flag = monitorAlarmService.isPower(user, loginUserUnit, id, "look");
		if (!flag) {
			return "redirect:/admin/monitorAlarm/list.shtml";
		}
		model.addAttribute("id", id);
		return "/admin/monitorAlarm/foundBackDetail";
	}

	/**
	 * 跳转预警反馈详情页面(返回json)
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月7日 上午7:53:29
	 */
	@RequestMapping("/alarmFeedbackDetails")
	@ResponseBody
	public JsonReturn alarmFeedbackDetails(Integer id, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		boolean flag = monitorAlarmService.isPower(user, loginUserUnit, id, "look");
		if (!flag) {
			return new JsonReturn(400, "无权限查看该预警详情");
		}
		MonitorAlarm cpa = monitorAlarmService.get(id);
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

	/**
	 * 稳控状态
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月7日 上午7:53:29
	 */
	@RequestMapping("/steady")
	@ResponseBody
	public JsonReturn steady(Integer id) {
		List<Map<String, Object>> steady = dictionaryService.findSubSet(id, null);
		return new JsonReturn(steady);
	}

	/**
	 * 预警反馈
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月7日 上午7:53:29
	 */
	@RequestMapping("/feedback")
	@ResponseBody
	public JsonReturn feedback(String jsonStr, Integer id, HttpServletRequest request) {
		try {
			MonitorAlarm ma = monitorAlarmService.get(id);
			JSONObject json = JSONObject.fromObject(jsonStr);
			if (StringUtils.isBlank(jsonStr)) {
				return new JsonReturn(400, "反馈失败，字数过多");
			}
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			if (null == user) {
				return new JsonReturn(400, "反馈失败", "/admin/monitorAlarm/list.shtml");
			}
			boolean flag = monitorAlarmService.isPower(user, loginUserUnit, id, "feedback");
			if (!flag) {
				return new JsonReturn(400, "无权限反馈");
			}
			json.put("userName", user.getPoliceName());
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
			json.put("unitName", unit.getUnitName());
			json.put("feedbackTime", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(new Date()));
			if (StringUtils.isBlank(ma.getRemark())) {
				JSONArray jsons = new JSONArray();
				jsons.add(json);
				// 反馈内容
				ma.setRemark(jsons.toString());
			} else {
				JSONArray jsons = JSONArray.fromObject(ma.getRemark());
				jsons.add(json);
				// 追加反馈内容
				ma.setRemark(jsons.toString());
			}
			// 反馈时间
			ma.setFeedbackTime(new Date());

			// 反馈信息
			monitorAlarmService.updateInclude(ma, new String[] { "remark", "feedbackTime" }, new String[] {});
			// 插入日志
			operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "进行布控预警反馈",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			return new JsonReturn(400, "反馈失败，字数过多");
		}
		return new JsonReturn(200, "反馈成功", "/admin/monitorAlarm/list.shtml");
	}

	/**
	 * 布控预警详情
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月11日 上午9:34:36
	 */
	@RequestMapping("/alarmDetail")
	public String AlarmDetail(Integer id, Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		boolean flag = monitorAlarmService.isPower(user, loginUserUnit, id, "look");
		if (!flag) {
			model.addAttribute("msg", "无权限操作");
			return "forward:/admin/monitorAlarm/list.shtml";
		}
		// 布控预警详情
		DetachedCriteria cpn = DetachedCriteria.forClass(MonitorAlarm.class);
		DetachedCriteria cp = cpn.createCriteria("monitor", JoinType.LEFT_OUTER_JOIN);
		cp.setFetchMode("monitorTypes", FetchMode.SELECT);
		cpn.add(Restrictions.eq("id", id));
		List<MonitorAlarm> cpnlist = monitorAlarmService.findList(cpn, 1, null, null);
		MonitorAlarm monitorAlarm = new MonitorAlarm();
		if (null != cpnlist && cpnlist.size() > 0) {
			monitorAlarm = cpnlist.get(0);
			model.addAttribute("monitorAlarm", monitorAlarm);
		}
		// 已签收预警
		List<Filter> filters = new ArrayList<>();
		Monitor monitor = monitorAlarm.getMonitor();
		filters.add(Filter.eq("monitor", monitor));
		filters.add(Filter.eq("signStatus", 2));
		List<MonitorAlarm> InstructiAlarm = monitorAlarmService.findList(null, filters, null);
		model.addAttribute("InstructiAlarm", InstructiAlarm);
		// 已签收单位
		List<Integer> InstructiUnit = new ArrayList<>();
		for (MonitorAlarm ma : InstructiAlarm) {
			InstructiUnit.add(ma.getAlarmUnitId());
		}
		// 去重
		InstructiUnit = new ArrayList<Integer>(new LinkedHashSet<>(InstructiUnit));
		model.addAttribute("InstructiUnit", InstructiUnit);
		
		// 已签收单位
		List<Integer> signUsers = new ArrayList<>();
		for (MonitorAlarm ma : InstructiAlarm) {
			if (ma.getSignUserId() != null) {
				signUsers.add(ma.getSignUserId());
			}
		}
		// 去重
		signUsers = new ArrayList<Integer>(new LinkedHashSet<>(signUsers));
		model.addAttribute("signUsers", signUsers);

		List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
		// 解析轨迹
		if (null != monitorAlarm.getDynamicInfoId()) {
			DynamicInfo d = dynamicInfoService.get(monitorAlarm.getDynamicInfoId());
			if (d != null) {
				String json = (d).getInformation();
				l = controlPersonAlarmService.getDynamic(json);
			}
		}
		model.addAttribute("l", l);
		// 插入日志
		operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "查看了布控预警详情",
				LogUtil.getData(request.getParameterMap()));
		return "/admin/monitorAlarm/alarmDetail";
	}

	/**
	 * 预警签收
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月12日 下午4:18:21
	 */
	@RequestMapping("/receiveAlarm")
	@ResponseBody
	public JsonReturn receiveAlarm(Integer id, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		boolean flag = monitorAlarmService.isPower(user, loginUserUnit, id, "sign");
		if (!flag) {
			return new JsonReturn(400, "无权限进行此操作");
		}
		if (null == user) {
			return new JsonReturn(400, "尚未登录");
		}
		MonitorAlarm monitorAlarm = monitorAlarmService.get(id);
		if (monitorAlarm.getSignUserId() != null) {
			return new JsonReturn(401, "该预警已签收");
		}
		monitorAlarm.setSignUserId(user.getId());
		monitorAlarm.setSignStatus(2);
		monitorAlarm.setSignTime(new Date());
		monitorAlarmService.updateInclude(monitorAlarm, new String[] { "signUserId", "signStatus", "signTime" }, null);
		// 插入日志
		operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "签收了布控预警",
				LogUtil.getData(request.getParameterMap()));
		return new JsonReturn(200, "签收成功", "/admin/monitorAlarm/list.shtml");
	}
}
