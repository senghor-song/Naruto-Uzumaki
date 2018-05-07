package com.ruiec.web.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.query.Sort;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonData;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.ControlPerson;
import com.ruiec.web.entity.DynamicInfo;
import com.ruiec.web.entity.Monitor;
import com.ruiec.web.entity.MonitorAlarm;
import com.ruiec.web.entity.MonitorType;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.APIConfigService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.DictionaryTypeService;
import com.ruiec.web.service.DynamicInfoService;
import com.ruiec.web.service.MonitorAlarmService;
import com.ruiec.web.service.MonitorService;
import com.ruiec.web.service.MonitorTypeService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.service.UserUnitService;
import com.ruiec.web.util.RuiecDateUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 布控人员控制器
 * 
 * @author 陈靖原<br>
 * @date 2018年1月6日 下午5:57:18
 */
@Controller("monitorController")
@RequestMapping("/admin/monitor")
public class MonitorController extends BaseAdminController {
	private static final Logger LOGGER = Logger.getLogger(MonitorController.class);

	@Resource
	private MonitorService monitorService;
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
	@Resource
	private MonitorAlarmService monitorAlarmService;
	@Resource
	private APIConfigService apiConfigService;
	@Resource
	private MonitorTypeService monitorTypeService;

	/**
	 * 获取布控人员列表
	 * 
	 * @author 陈靖原<br>
	 * @throws ParseException
	 * @date 2018年1月8日 上午9:05:12
	 */
	@RequestMapping("/list")
	public String list(Page page, Model model, Monitor monitor, Date startDate, Date endDate,
			HttpServletRequest request) throws ParseException {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		// 用户未登录
		if (user == null) {
			return "redirect:/admin/view.shtml";
		}
		// 用来保存要查询单位的子单位id
		if (user.getId() == 1) {
			// 最高级管理员
			// 最高级权限管理
			model.addAttribute("units", unitService.getUnitArea(0));
		} else if (loginUserUnit != null && loginUserUnit.getUnitIds() != null
				&& loginUserUnit.getUnitIds().size() > 0) {
			// 管理员
			if (loginUserUnit.getUnitRank().getName().equals("city")) {
				// 市局管理员
				// 区级公安局信息
				model.addAttribute("units", unitService.getUnitArea(0));
			} else {
				// 普通管理员
				// 获取当前用户所管理的单位
				List<Integer> unitIds = loginUserUnit.getUnitIds();
				List<Unit> userUnits = new ArrayList<Unit>();
				for (int i = 0; i < unitIds.size(); i++) {
					userUnits.add(unitService.get(unitIds.get(i)));
				}
				// 当前用管理单位则是管理员，没有则是警员
				if (userUnits != null && userUnits.size() > 0) {
					// 所管辖的公安局信息
					model.addAttribute("units", userUnits);
				}
			}
		} else {
			// 警员则只查询所管理的布控人
			page.add(Filter.eq("userId", user.getId()));
		}
		// 查询条件
		DetachedCriteria mt = DetachedCriteria.forClass(Monitor.class);
		// 尚未逻辑删除的布控人
		page.add(Filter.eq("isDelete", 0));
		// 性别
		if (StringUtils.isNotBlank(monitor.getSex())) {
			mt.add(Restrictions.eq("sex", monitor.getSex()));
		}
		model.addAttribute("sex", monitor.getSex());
		// 审核状态
		if (null != monitor.getStatus()) {
			mt.add(Restrictions.eq("status", monitor.getStatus()));
		}
		model.addAttribute("status", monitor.getStatus());
		// 单位
		if (!"警员".equals(loginUserUnit.getUserName()) && loginUserUnit.getUnitSonIds().size() > 0) {
			if (monitor.getUnitId() != null) {
				if (user.getId() == 1) {
					Disjunction disjunction = Restrictions.disjunction();
					for (int i = 0; i < unitService.findSonIds(monitor.getUnitId()).size(); i++) {
						disjunction.add(Restrictions.eq("unitId", unitService.findSonIds(monitor.getUnitId()).get(i)));
					}
					mt.add(disjunction);
				}else {
					Disjunction disjunction = Restrictions.disjunction();
					for (int i = 0; i < unitService.findSonIds(monitor.getUnitId()).size(); i++) {
						disjunction.add(Restrictions.eq("unitId", unitService.findSonIds(monitor.getUnitId()).get(i)));
					}
					Disjunction dis = Restrictions.disjunction();
					for (int i = 0; i < loginUserUnit.getUnitSonIds().size(); i++) {
						dis.add(Restrictions.eq("unitId", loginUserUnit.getUnitSonIds().get(i)));
					}
					mt.add(Restrictions.or(Restrictions.and(disjunction, dis),
							Restrictions.eq("userId", user.getId())));
				}
			} else {
				Disjunction dis = Restrictions.disjunction();
				for (int i = 0; i < loginUserUnit.getUnitSonIds().size(); i++) {
					dis.add(Restrictions.eq("unitId", loginUserUnit.getUnitSonIds().get(i)));
				}
				mt.add(Restrictions.or(dis, Restrictions.eq("userId", user.getId())));
			}
		} else {
			if (monitor.getUnitId() != null) {
				mt.add(Restrictions.and(Restrictions.eq("userId", user.getId()),
						Restrictions.eq("unitId", user.getUnitId())));
			} else {
				mt.add(Restrictions.eq("userId", user.getId()));
			}
		}
		model.addAttribute("unitId", monitor.getUnitId());
		// 姓名
		if (StringUtils.isNotBlank(monitor.getName())) {
			mt.add(Restrictions.like("name", monitor.getName(), MatchMode.ANYWHERE));
		}
		model.addAttribute("name", monitor.getName());
		// 身份证
		if (StringUtils.isNotBlank(monitor.getIdCard())) {
			mt.add(Restrictions.like("idCard", monitor.getIdCard(), MatchMode.ANYWHERE));
		}
		model.addAttribute("idCard", monitor.getIdCard());
		// 入库时间
		if (startDate != null) {
			mt.add(Restrictions.ge("createDate", startDate));
		}
		if (endDate != null) {
			mt.add(Restrictions.le("createDate", endDate));
		}
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		mt.setFetchMode("monitorTypes", FetchMode.SELECT);
		page = monitorService.findByPage(page, mt);
		List<Monitor> list = new ArrayList<Monitor>();
		for (int i = 0; i < page.getList().size(); i++) {
			Monitor monitor2 = (Monitor) page.getList().get(i);
			// 审批时间
			String Atime = "";
			if (StringUtils.isNotBlank(monitor2.getApprovalTime()) && monitor2.getStatus() != null && monitor2.getStatus() == 1) {
				String[] time = monitor2.getApprovalTime().split(",");
				Atime = time[time.length - 1];
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
				Date beginDate = sdf.parse(Atime);
				Date nowDate = new Date();
				long day = (nowDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
				if (StringUtils.isBlank(monitor2.getDuration())
						|| day > Long.valueOf(monitor2.getDuration()).longValue() || day < 0) {
					// 过期
					monitor2.setIsRevoke(1);
				} else {
					// 未过期
					monitor2.setIsRevoke(0);
				}
			}else {
				// 未过期
				monitor2.setIsRevoke(0);
			}
			list.add(monitor2);
		}
		page.setList(list);
		// 分页数据
		model.addAttribute("page", page);
		try {
			// 插入操作日志
			// operationLogService.insertOperationLogs(user, 4,
			// request.getRequestURL().toString(), user.getPoliceName() + "查看了布控列表",
			// LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		return "/admin/monitor/list";
	}

	/**
	 * 跳转布控人员添加页面
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午9:05:12
	 */
	@RequestMapping("/add")
	public String add(Model model, Integer personnelType, HttpServletRequest request) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		if (user.getId() == 1) {
			// 最高级管理员
			// 最高级权限管理
			model.addAttribute("units", unitService.getUnitArea(1));
		} else if (loginUserUnit != null && loginUserUnit.getUnitIds() != null
				&& loginUserUnit.getUnitIds().size() > 0) {
			// 管理员
			if (loginUserUnit.getUnitRank().getName().equals("city")) {
				// 市局管理员
				// 区级公安局信息
				model.addAttribute("units", unitService.getUnitArea(1));
			} else {
				// 普通管理员
				// 获取当前用户所管理的单位
				List<Integer> unitIds = loginUserUnit.getUnitIds();
				List<Unit> userUnits = new ArrayList<Unit>();
				for (int i = 0; i < unitIds.size(); i++) {
					userUnits.add(unitService.get(unitIds.get(i)));
				}
				// 当前用管理单位则是管理员，没有则是警员
				if (userUnits != null && userUnits.size() > 0) {
					// 所管辖的公安局信息
					model.addAttribute("units", userUnits);
				}
			}
		} else {
			// 警员
			// 警员则只查询所管理的布控人
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
			List<Unit> units = new ArrayList<Unit>();
			units.add(unit);
			model.addAttribute("isUser", 1);
			model.addAttribute("units", units);
			// 判断该警员属于那种单位的，显示不同的单位数据
			if (unit.getUnitRank().equals("city") || unit.getUnitRank().equals("area")) {
				// 市级警员和区级警员则只显示第一单位数据
				model.addAttribute("unit1", unit);
			} else if (unit.getUnitRank().equals("town")) {
				// 镇级警员则要显示父级单位数据，和镇级单位
				List<Filter> filters2 = new ArrayList<Filter>();
				if (unit.getIsPoliceSection() == 1) {
					// 警种单位则查询警种父级单位
					filters2.add(Filter.eq("id", unit.getPoliceTypesParentId()));
					model.addAttribute("unit1", unitService.get(filters2));
				} else {
					// 非警种单位则查询父级单位
					filters2.add(Filter.eq("id", unit.getParentId()));
					model.addAttribute("unit1", unitService.get(filters2));
				}
				model.addAttribute("unit2", unit);
			}
			// 警员信息
			model.addAttribute("user", user);
		}
		// 民族
		model.addAttribute("nations", getDictionary("nation"));
		// 人员类型
		model.addAttribute("monitorType", getDictionary("monitorType"));
		// 布控人员类别大类
		model.addAttribute("dataitemIds", getDictionary("monitorClass"));
		// 布控措施
		model.addAttribute("disposalMeasure", getDictionary("disposalMeasure"));
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(),
					user.getPoliceName() + "访问了布控人新增页面", LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		return "/admin/monitor/add";
	}

	/**
	 * 保存布控人员添加信息
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午9:05:12
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonReturn save(Monitor monitor, HttpServletRequest request, String Categories, Integer unitId,
			Integer unitId2) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		boolean flag = false;
		if (user == null) {
			return new JsonReturn(400, "尚未登录");
		}
		if (monitor.getIdCard() == null) {
			return new JsonReturn(400, "身份证不可为空");
		}
		if (monitor.getUserId() == -1) {
			return new JsonReturn(400, "请选择联系民警");
		}
		if (monitor.getNationNumber() == -1) {
			return new JsonReturn(400, "请选择民族");
		}
		if (StringUtils.isNotBlank(monitor.getReason()) && monitor.getReason().length() > 200) {
			return new JsonReturn(400, "布控理由应在200字以内");
		}
		try {
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(Filter.eq("userId", monitor.getUserId()));
			filters.add(Filter.eq("idCard", monitor.getIdCard()));
			if (monitorService.getCount(filters) != null && monitorService.getCount(filters) > 0) {
				return new JsonReturn(400, "同一警员不可重复布控同一布控人");
			}
			if (monitorService.saveMonitor(user, loginUserUnit, monitor, Categories, unitId, unitId2)) {
				flag = true;
			}
		} catch (Exception e) {
			LOGGER.error("新增失败!" + e);
		}
		// 获取当前登录用户
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 1, request.getRequestURL().toString(),
					user.getPoliceName() + "新增了" + monitor.getName() + "布控人",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		if (flag) {
			return new JsonReturn(200, "新增成功", "/admin/monitor/list.shtml");
		} else {
			return new JsonReturn(400, "新增失败");
		}
	}
	
	/**
	 * 跳转布控人员修改页面
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午9:05:12
	 */
	@RequestMapping("/edit")
	public String edit(Model model, Integer id, Integer personnelType, HttpServletRequest request) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		// 布控人信息
		if (id == null) {
			return "redirect:/admin/monitor/list.shtml";
		}
		Monitor mo = monitorService.get(id);
		if (mo == null) {
			return "redirect:/admin/monitor/list.shtml";
		}
		if (!monitorService.isPower(mo.getId(), user, loginUserUnit)) {
			return "redirect:/admin/monitor/list.shtml";
		}
		if (!mo.getProposer().equals(user.getId())) {
			return "redirect:/admin/monitor/list.shtml";
		}
		if ((mo.getStatus() != 1 && mo.getIsRevoke() != 1) && (mo.getStatus() != 2 && mo.getIsRevoke() != 0)) {
			return "redirect:/admin/monitor/list.shtml";
		}
		model.addAttribute("monitor", mo);
		if (user.getId() == 1) {
			// 最高级管理员
			// 最高级权限管理
			model.addAttribute("units", unitService.getUnitArea(1));
		} else if (loginUserUnit != null && loginUserUnit.getUnitIds() != null
				&& loginUserUnit.getUnitIds().size() > 0) {
			if (loginUserUnit.getUnitRank().getName().equals("city")) {
				// 市局管理员
				// 区级公安局信息
				model.addAttribute("units", unitService.getUnitArea(1));
			} else {
				// 普通管理员
				// 获取当前用户所管理的单位
				List<Integer> unitIds = loginUserUnit.getUnitIds();
				List<Unit> userUnits = new ArrayList<Unit>();
				for (int i = 0; i < unitIds.size(); i++) {
					userUnits.add((Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unitIds.get(i)));
				}
				// 当前用管理单位则是管理员,没有则是警员
				if (userUnits != null && userUnits.size() > 0) {
					// 所管辖的公安局信息
					model.addAttribute("units", userUnits);
				}
			}
			// 判断所属单位是否为区级单位
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, mo.getUnitId());
			if (unit != null) {
				if ("area".equals(unit.getUnitRank())) {
					model.addAttribute("unit1", unit.getId());
				} else {
					// 判断是否为警种单位,取父级id
					if (unit.getIsPoliceSection() == 1) {
						model.addAttribute("unit1", unit.getPoliceTypesParentId());
					} else {
						model.addAttribute("unit1", unit.getParentId());
					}
					model.addAttribute("unit2", unit.getId());
				}
			}
		} else {
			// 警员
			// 警员则只查询所管理的布控人
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
			List<Unit> units = new ArrayList<Unit>();
			units.add(unit);
			model.addAttribute("isUser", 1);
			model.addAttribute("units", units);
			// 判断该警员属于那种单位的，显示不同的单位数据
			if (unit.getUnitRank().equals("city") || unit.getUnitRank().equals("area")) {
				// 市级警员和区级警员则只显示第一单位数据
				model.addAttribute("unit1", unit);
			} else if (unit.getUnitRank().equals("town")) {
				// 镇级警员则要显示父级单位数据，和镇级单位
				List<Filter> filters2 = new ArrayList<Filter>();
				if (unit.getIsPoliceSection() == 1) {
					// 警种单位则查询警种父级单位
					filters2.add(Filter.eq("id", unit.getPoliceTypesParentId()));
					model.addAttribute("unit1", unitService.get(filters2));
				} else {
					// 非警种单位则查询父级单位
					filters2.add(Filter.eq("id", unit.getParentId()));
					model.addAttribute("unit1", unitService.get(filters2));
				}
				model.addAttribute("unit2", unit);
			}
			// 警员信息
			model.addAttribute("user", user);
		}
		// 人员类别数据
		String Categories = "";
		for (int i = 0; i < mo.getMonitorTypes().size(); i++) {
			Categories += mo.getMonitorTypes().get(i).getDictionaryId() + ",";
		}
		// 人员类别数据
		model.addAttribute("Categories", Categories);
		// 民族
		model.addAttribute("nations", getDictionary("nation"));
		// 人员类型
		model.addAttribute("monitorType", getDictionary("monitorType"));
		// 布控人员类别大类
		model.addAttribute("dataitemIds", getDictionary("monitorClass"));
		// 布控措施
		model.addAttribute("disposalMeasure", getDictionary("disposalMeasure"));
		// 人员ID
		model.addAttribute("id", id);
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(),
					user.getPoliceName() + "访问了布控人修改页面", LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		return "/admin/monitor/edit";
	}
	
	/**
	 * 修改布控人员信息
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午9:05:12
	 */
	@RequestMapping("/update")
	@ResponseBody
	public JsonReturn update(Monitor monitor, HttpServletRequest request, String Categories, Integer unitId,
			Integer unitId2) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		Monitor mo = monitorService.get(monitor.getId());
		if (!monitorService.isPower(mo.getId(), user, loginUserUnit)) {
			return new JsonReturn(400, "无权限操作");
		}
		if (!mo.getProposer().equals(user.getId())) {
			return new JsonReturn(400, "无权限操作");
		}
		if ((mo.getStatus() != 1 && mo.getIsRevoke() != 1) && (mo.getStatus() != 2 && mo.getIsRevoke() != 0)) {
			return new JsonReturn(400, "无权限操作");
		}
		if (monitor.getUserId() == -1) {
			return new JsonReturn(400, "请选择联系民警");
		}
		if (monitor.getNationNumber() == -1) {
			return new JsonReturn(400, "请选择民族");
		}
		boolean flag = false;
		if (monitor.getIdCard() == null) {
			return new JsonReturn(400, "身份证不可为空");
		}
		if (StringUtils.isNotBlank(monitor.getReason()) && monitor.getReason().length() > 200) {
			return new JsonReturn(400, "布控理由应在200字以内");
		}
		try {
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(Filter.eq("userId", monitor.getUserId()));
			filters.add(Filter.eq("idCard", monitor.getIdCard()));
			if (monitorService.getCount(filters) != null && monitorService.getCount(filters) > 1) {
				return new JsonReturn(400, "同一警员不可重复布控同一布控人");
			}
			if (monitorService.updMonitor(user, loginUserUnit, monitor, Categories, unitId, unitId2)) {
				flag = true;
			}
		} catch (Exception e) {
			LOGGER.error("修改失败!" + e);
		}
		// 获取当前登录用户
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 1, request.getRequestURL().toString(),
					user.getPoliceName() + "修改了" + monitor.getName() + "布控人",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		if (flag) {
			return new JsonReturn(200, "修改成功", "/admin/monitor/list.shtml");
		} else {
			return new JsonReturn(400, "修改失败");
		}
	}

	/**
	 * 根据id删除布控人员信息
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午9:05:12
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public JsonReturn delete(Integer[] ids, HttpServletRequest request) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		List<String> nameStrings = new ArrayList<String>();
		try {
			// 遍历需要删除的布控人员，做逻辑删除，即修改状态为已删除
			for (int i = 0; i < ids.length; i++) {
				Monitor delMonitor = monitorService.get(ids[i]);
				if (!monitorService.isPower(delMonitor.getId(), user, loginUserUnit)) {
					return new JsonReturn(400, "没有权限!");
				}
				// 对已经逻辑删除的数据做处理，将身份证号
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				Date date = new java.util.Date();
				String delDate = sdf.format(date);
				delMonitor.setIdCard(delMonitor.getIdCard() + "_" + delDate);
				delMonitor.setIsDelete(1);
				monitorService.updateInclude(delMonitor, new String[] { "isDelete", "idCard" }, null);
				nameStrings.add(delMonitor.getName());
			}
		} catch (Exception e) {
			LOGGER.error("布控人删除失败", e);
			return new JsonReturn(400, "布控人删除失败");
		}
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 2, request.getRequestURL().toString(),
					user.getPoliceName() + "删除了" + nameStrings.toString() + "布控信息",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		return new JsonReturn(200, "布控人删除成功", "/admin/monitor/list.shtml");
	}

	/**
	 * 根据身份证查询布控人信息是否存在
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午9:05:12
	 */
	@RequestMapping("/selByIdCard")
	@ResponseBody
	public JsonReturn selByIdCard(String idCard) {
		boolean flag = monitorService.selByIdCard(idCard);
		if (flag) {
			return new JsonReturn(200, "查询成功");
		}
		return new JsonReturn(400, "查询失败");
	}

	/**
	 * 根据身份证查询布控人信息(列表)
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午9:05:12
	 */
	@RequestMapping("/selByIdCards")
	public String selByIdCards(Page page, String idCard, Model model, Integer personType) {
		if (personType == null || personType == 0) {
			DetachedCriteria mo = DetachedCriteria.forClass(Monitor.class);
			mo.add(Restrictions.eq("idCard", idCard));
			mo.setFetchMode("monitorTypes", FetchMode.SELECT);
			page.setPageSize(5);
			monitorService.findByPage(page, mo);
		} else {
			DetachedCriteria cp = DetachedCriteria.forClass(ControlPerson.class);
			cp.createCriteria("controlPersonExtend");
			cp.add(Restrictions.eq("idCard", idCard));
			cp.add(Restrictions.eq("personnelType", personType));
			cp.setFetchMode("controlPersonTypes", FetchMode.SELECT);
			page.setPageSize(5);
			monitorService.findByPage(page, cp);
		}
		model.addAttribute("page", page);
		model.addAttribute("idCard", idCard);
		model.addAttribute("personType", personType);
		return "/admin/monitor/selByIdCards";
	}

	/**
	 * 根据身份证查询布控人信息(个人)
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午9:05:12
	 */
	@RequestMapping("/selOneByIdCard")
	@ResponseBody
	public JsonReturn selOneByIdCard(Integer id, Integer ptype) {
		Map<String, Object> map = monitorService.selOneByIdCard(id, ptype);
		if (null != map) {
			return new JsonReturn(200, "选择成功", map);
		}
		return new JsonReturn(400, "选择失败");
	}

	/**
	 * 跳转布控人员查看页面
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月9日 下午7:02:24
	 */
	@RequestMapping("/look")
	public String look(Page page, Model model, HttpServletRequest request, Integer id, Integer personnelType,
			Integer typeId, Integer viewType) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		if (!monitorService.isPower(id, user, loginUserUnit)) {
			return "redirect:/admin/view.shtml";
		}
		// 根据id获取布控人员数据
		Monitor monitor = monitorService.get(id);
		// 是否可审核
		String isAudit = monitorService.isAudit(user, loginUserUnit, monitor);
		model.addAttribute("isAudit", isAudit);
		// 布控人信息
		model.addAttribute("monitor", monitor);
		// 审批人
		if (StringUtils.isNotBlank(monitor.getApprovalUserId())) {
			String[] aUserId = monitor.getApprovalUserId().split(",");
			model.addAttribute("aUserId", aUserId);
			model.addAttribute("size", aUserId.length);
		}
		if (StringUtils.isNotBlank(monitor.getApprovalUnitId())) {
			// 审批单位
			String[] aUnitId = monitor.getApprovalUnitId().split(",");
			List<Unit> units = new ArrayList<Unit>();
			for (int i = 0; i < aUnitId.length; i++) {
				Unit u = unitService.get(Integer.valueOf(aUnitId[i]));
				units.add(u);
			}
			model.addAttribute("units", units);
		}
		if (StringUtils.isNotBlank(monitor.getApprovalTime())) {
			// 审批时间
			String[] aTime = monitor.getApprovalTime().split(",");
			model.addAttribute("aTime", aTime);
		}
		List<MonitorType> personnelTypes = monitorTypeService.findList(null,
				Filter.build().add(Filter.eq("monitor", monitor)).build(), null);
		// 分页数据
		model.addAttribute("page", page);
		// 人员类型
		model.addAttribute("personnelType", personnelType);
		// 人员类别
		model.addAttribute("personnelTypes", personnelTypes);
		// 布控人id
		model.addAttribute("monitorId", id);
		// 操作日志
		operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(),
				user.getPoliceName() + "查看了" + monitor.getName() + "布控人信息", LogUtil.getData(request.getParameterMap()));
		return "/admin/monitor/look";
	}

	/**
	 * 统计轨迹数据
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月17日 下午4:50:02
	 */
	@RequestMapping("/countTypeList")
	public String countTypeList(Model model, Integer id) {
		// 预警总数
		model.addAttribute("alarmCount", monitorAlarmService.selectByMonitorAlarmCount(id));
		// 轨迹统计数据
		List<Map<String, Object>> dynamicInfoCountMaps = dynamicInfoService.dynamicInfoCountForMonitor(id);
		// 用于存放所有的轨迹统计数据的
		List<Map<String, Object>> dynamicInfos = apiConfigService.getApiConfig();
		for (int i = 0; i < dynamicInfos.size(); i++) {
			Map<String, Object> map = dynamicInfos.get(i);
			int count = 0;
			for (int j = 0; j < dynamicInfoCountMaps.size(); j++) {
				if (dynamicInfos.get(i).get("id").equals(dynamicInfoCountMaps.get(j).get("type"))) {
					count = Integer.valueOf(dynamicInfoCountMaps.get(j).get("count").toString()).intValue();
				}
			}
			map.put("count", count);
			dynamicInfos.set(i, map);
		}
		model.addAttribute("dynamicInfos", dynamicInfos);
		return "/admin/monitor/typeList";
	}

	/**
	 * 根据轨迹类型id和重点人id获取轨迹分页数据
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月17日 下午2:36:28
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getDynamicInfo")
	public String getDynamicInfo(Page page, Model model, Integer id, Integer typeId) {
		if (typeId == null || typeId == 0) {
			// 根据重点人Id查询该重点人所有的预警信息
			DetachedCriteria ma = DetachedCriteria.forClass(MonitorAlarm.class);
			DetachedCriteria mt = ma.createCriteria("monitor");
			mt.setFetchMode("monitorTypes", FetchMode.SELECT);
			mt.add(Restrictions.eq("id", id));
			monitorAlarmService.findByPage(page, ma);
		} else {
			page.add(Sort.desc("createDate"));
			page.add(Filter.eq("controlPersonId", id));
			page.add(Filter.eq("type", typeId));
			dynamicInfoService.findByPage(page);
			List<DynamicInfo> dynamicInfos = (List<DynamicInfo>) page.getList();
			// 标题集合
			List<String> titles = new ArrayList<String>();
			// 轨迹数据集合
			List<List<JsonData>> datas = new ArrayList<List<JsonData>>();
			// 定义获取数据库json数据json对象
			JSONObject json = new JSONObject();
			// 定义获取标题json数据json对象
			JSONObject titleJsonObject = new JSONObject();
			// 定义获取轨迹json数据json对象
			JSONArray valueJsonObject = new JSONArray();
			if (dynamicInfos != null && dynamicInfos.size() > 0) {
				for (int i = 0; i < dynamicInfos.size(); i++) {
					// 将json字符串转换为json格式
					json = JSONObject.fromObject(dynamicInfos.get(i).getInformation().toString());
					// 获取title标题数据
					titleJsonObject = (JSONObject) json.get("title");
					// 获取value内容数据集合
					valueJsonObject = json.getJSONArray("value");
					List<JsonData> jsonDatas = new ArrayList<JsonData>();
					// 迭代标题数据
					for (Iterator<?> iter = titleJsonObject.keys(); iter.hasNext();) {
						// 只获取一次标题的值，获取一次就不在获取
						if (i > 0) {
							iter.next();
							continue;
						} else {
							// 获取标题的key
							String key = (String) iter.next();
							// 根据标题的key获取value
							String value = titleJsonObject.get(key).toString();
							// 给标题集合赋值
							titles.add(value);
						}
					}
					// 将标题对应的值获取到
					for (int j = 0; j < valueJsonObject.size(); j++) {
						JsonData jsonData = (JsonData) JSONObject.toBean(valueJsonObject.getJSONObject(j),
								JsonData.class);
						jsonDatas.add(jsonData);
					}
					// 得到一条完整的轨迹数据就存入轨迹数据集合
					datas.add(jsonDatas);
				}
			}
			page.setList(datas);
			model.addAttribute("titles", titles);// 轨迹标题数据
			model.addAttribute("datas", datas);// 轨迹数据
		}
		// 轨迹类型id（预警信息固定为0）
		model.addAttribute("typeId", typeId);
		// 重点人id
		model.addAttribute("id", id);
		model.addAttribute("page", page);
		return "/admin/monitor/dynamicInfo";
	}

	/**
	 * 审核布控人
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午9:05:12
	 */
	@RequestMapping("/isColumn")
	@ResponseBody
	public JsonReturn isColumn(Integer id, Integer status, HttpServletRequest request) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		if (!monitorService.isPower(id, user, loginUserUnit)) {
			return new JsonReturn(400, "没有权限");
		}
		Monitor monitor = monitorService.get(id);
		if (monitor.getStatus() == 1) {
			return new JsonReturn(400, "该审核已通过");
		}
		if (monitor.getStatus() == 2) {
			return new JsonReturn(400, "该审核已被拒绝");
		}
		Unit unit = unitService.get(monitor.getUnitId());
		String isAudit = monitorService.isAudit(user, loginUserUnit, monitor);
		try {
			if (isAudit.equals("noPower")) {
				return new JsonReturn(400, "没有权限");
			} else if (isAudit.equals("town") || isAudit.equals("town")) {
				if (status != null) {
					// 审核状态(初审)
					monitor.setStatus(status);
					// 审批单位
					monitor.setApprovalUnitId(monitor.getUnitId().toString());
					// 审批人
					monitor.setApprovalUserId(user.getId().toString());
					// 审批时间
					monitor.setApprovalTime(RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(new Date()));
				}
			} else if (isAudit.equals("area") || isAudit.equals("city")) {
				if (status != null) {
					// 审核状态(分县局通过分县局民警或市局通过市局民警)
					monitor.setStatus(status);
					// 审批单位
					monitor.setApprovalUnitId(monitor.getUnitId().toString());
					// 审批人
					monitor.setApprovalUserId(user.getId().toString());
					// 审批时间
					monitor.setApprovalTime(RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(new Date()));
				}
			} else if (isAudit.equals("areaTown")) {
				// 派出所民警
				if (StringUtils.isNotBlank(monitor.getApprovalUnitId())) {
					// 审核状态(分县局通过或市局通过派出所民警)
					monitor.setStatus(status);
					// 审批单位
					if (unit.getPoliceTypesParentId() != null && unit.getParentId() == null) {
						monitor.setApprovalUnitId(monitor.getApprovalUnitId() + "," + unit.getPoliceTypesParentId());
					} else {
						monitor.setApprovalUnitId(monitor.getApprovalUnitId() + "," + unit.getParentId());
					}
					// 审批人
					monitor.setApprovalUserId(monitor.getApprovalUserId() + "," + user.getId());
					// 审批时间
					monitor.setApprovalTime(
							monitor.getApprovalTime() + "," + RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(new Date()));
				} else {
					// 审核状态(分县局通过派出所民警或管理员)
					monitor.setStatus(status);
					// 审批单位
					if (unit.getPoliceTypesParentId() != null && unit.getParentId() == null) {
						monitor.setApprovalUnitId(unit.getPoliceTypesParentId().toString());
					} else {
						monitor.setApprovalUnitId(unit.getParentId().toString());
					}
					// 审批人
					monitor.setApprovalUserId(user.getId().toString());
					// 审批时间
					monitor.setApprovalTime(RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(new Date()));
				}
			}
			monitorService.update(monitor);
		} catch (Exception e) {
			LOGGER.error("审核失败!" + e);
		}
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 3, request.getRequestURL().toString(),
					user.getPoliceName() + "审核了" + monitor.getName(), LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		if (status == 1 || status == 3) {
			return new JsonReturn(200, "审核通过");
		} else {
			return new JsonReturn(200, "审核拒绝");
		}
	}

	/**
	 * 撤控
	 * 
	 * @author 陈靖原<br>
	 * @throws ParseException
	 * @date 2018年1月8日 上午9:05:12
	 */
	@RequestMapping("/unControl")
	@ResponseBody
	public JsonReturn unControl(Integer id,HttpServletRequest request) throws ParseException {
		try {
			Monitor monitor = monitorService.get(id);
			User user = (User) request.getSession().getAttribute("user");
			if (user == null) {
				return new JsonReturn(400, "尚未登录");
			}
			if (monitor == null) {
				return new JsonReturn(400, "布控信息不存在");
			}
			if (!monitor.getProposer().equals(user.getId())) {
				return new JsonReturn(400, "无权限操作");
			}
			// 审核状态
			Integer status = monitor.getStatus();
			if (status != 1) {
				return new JsonReturn(400, "未通过审核，无法撤控");
			}
			// 审批时间
			String Atime = "";
			String[] time = monitor.getApprovalTime().split(",");
			Atime = time[time.length - 1];
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
			Date beginDate = sdf.parse(Atime);
			Date nowDate = new Date();
			long day = (nowDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
			if (StringUtils.isBlank(monitor.getDuration()) || day > Long.valueOf(monitor.getDuration()).longValue()
					|| day < 0) {
				return new JsonReturn(400, "布控时间过期，无法撤控");
			}
			monitor.setIsRevoke(1);
			monitor.setDuration("");
			monitorService.update(monitor);
			return new JsonReturn(200, "撤控成功");
		} catch (Exception e) {
			LOGGER.error("撤控失败" + e);
			return new JsonReturn(400, "撤控失败");
		}
	}

	/**
	 * 续控
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午9:05:12
	 */
	@RequestMapping("/conControl")
	@ResponseBody
	public JsonReturn conControl(Integer id, Integer day, HttpServletRequest request) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			Monitor monitor = monitorService.get(id);
			if (user == null) {
				return new JsonReturn(400, "未通过审核，续控失败");
			}
			if (!user.getId().equals(monitor.getProposer())) {
				return new JsonReturn(400, "尚无权限");
			}
			// 审核状态
			Integer status = monitor.getStatus();
			if (status != 1) {
				return new JsonReturn(400, "未通过审核，续控失败");
			}
			if (day == null) {
				return new JsonReturn(400, "续控天数不可为空");
			}
			if (day <= 0) {
				return new JsonReturn(400, "续控天数不可为负数或0");
			}
			int newDay = Integer.valueOf(monitor.getDuration()) + day;
			if (newDay > 30) {
				return new JsonReturn(400, "续控总天数不可大于30天");
			}
			monitor.setDuration(String.valueOf(newDay));
			monitorService.update(monitor);
			return new JsonReturn(200, "续控成功");
		} catch (Exception e) {
			return new JsonReturn(400, "续控失败");
		}
	}

	/**
	 * 根据字典类型id查询简单字典类型数据
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午9:05:12
	 */
	private List<Map<String, Object>> getDictionary(String name) {
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		if (name != null) {
			// 根据类型id查询简单类型数据
			maps = dictionaryService.findByItemCode(name, 0);
		}
		return maps;
	}
}
