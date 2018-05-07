package com.ruiec.web.controller.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.web.common.ApiReturn;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.Monitor;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.DictionaryTypeService;
import com.ruiec.web.service.DynamicInfoService;
import com.ruiec.web.service.MonitorAlarmService;
import com.ruiec.web.service.MonitorService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.service.UserUnitService;
import com.ruiec.web.util.RuiecDateUtils;

/**
 * 布控api控制器
 * 
 * @author 陈靖原<br>
 * @date 2018年1月12日 上午9:30:27
 */
@Controller("apiMonitorController")
@RequestMapping("/api/admin/monitor")
public class ApiMonitorController {

	private static final Logger LOGGER = Logger.getLogger(ApiMonitorController.class);

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

	/**
	 * 获取布控人员列表
	 * 
	 * @author 陈靖原<br>
	 * @throws ParseException
	 * @date 2018年1月8日 上午9:05:12
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ApiReturn list(Page page, Monitor monitor, HttpServletRequest request) throws ParseException {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		// 用户未登录
		if (user == null) {
			return new ApiReturn(400, "尚未登录");
		}
		// 查询条件
		DetachedCriteria mt = DetachedCriteria.forClass(Monitor.class);
		mt.add(Restrictions.eq("isDelete", 0));//查询未删除的数据
		// 用来保存要查询单位的子单位id
		// 单位
		if (!"警员".equals(loginUserUnit.getUserName()) && loginUserUnit.getUnitSonIds().size() > 0) {
			if (monitor.getUnitId() != null) {
				Disjunction disjunction = Restrictions.disjunction();
				for (int i = 0; i < unitService.findSonIds(monitor.getUnitId()).size(); i++) {
					disjunction.add(Restrictions.eq("unitId", unitService.findSonIds(monitor.getUnitId()).get(i)));
				}
				Disjunction dis = Restrictions.disjunction();
				for (int i = 0; i < loginUserUnit.getUnitSonIds().size(); i++) {
					dis.add(Restrictions.eq("unitId", loginUserUnit.getUnitSonIds().get(i)));
				}
				mt.add(Restrictions.or(Restrictions.and(disjunction, dis), Restrictions.eq("userId", user.getId())));
			} else {
				Disjunction dis = Restrictions.disjunction();
				for (int i = 0; i < loginUserUnit.getUnitSonIds().size(); i++) {
					dis.add(Restrictions.eq("unitId", loginUserUnit.getUnitSonIds().get(i)));
				}
				mt.add(Restrictions.or(dis, Restrictions.eq("userId", user.getId())));
			}
		} else {
			if (monitor.getUnitId() != null) {
				mt.add(Restrictions.and(Restrictions.eq("userId", user.getId()), Restrictions.eq("unitId", user.getUnitId())));
			} else {
				mt.add(Restrictions.eq("userId", user.getId()));
			}
		}
		// 尚未逻辑删除的布控人
		page.add(Filter.eq("isDelete", 0));
		mt.setFetchMode("monitorTypes", FetchMode.SELECT);
		monitorService.findByPage(page, mt);
		// 分页数据
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 列表总记录数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", page.getTotalCount());
		map.put("pageSize", page.getPageSize());
		map.put("pageNumber", page.getPageNumber());
		for (int i = 0; i < page.getList().size(); i++) {
			Monitor mot = (Monitor) page.getList().get(i);
			Map<String, Object> mp = new HashMap<String, Object>();
			mp.put("id", mot.getId());
			mp.put("photo", ObjectUtils.firstNonNull(mot.getPhoto(),""));
			mp.put("name", ObjectUtils.firstNonNull(mot.getName(),GlobalUnit.NULLMSG));
			mp.put("idCard", ObjectUtils.firstNonNull(mot.getIdCard(),GlobalUnit.NULLMSG));
			mp.put("duration", ObjectUtils.firstNonNull(mot.getDuration(),GlobalUnit.NULLMSG));
			/** 布控状态(0：未审核，1，已审核：2：审核拒绝，3：初审) */
			Integer statusType = mot.getStatus() ;
			String status = statusType == 0 ? "未审核" : statusType == 1 ? "已审核" : statusType == 2 ? "审核拒绝" : statusType == 3 ? "初审" : "暂无";
			mp.put("status", status);
			mp.put("createDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(mot.getCreateDate()));
			list.add(mp);
		}
		map.put("monitorList", list);
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看了布控列表",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		return new ApiReturn(200, "查询布控列表成功！", map);
	}

	/**
	 * 保存布控人员添加信息
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午9:05:12
	 */
	@RequestMapping("/save")
	@ResponseBody
	public ApiReturn save(Monitor monitor, HttpServletRequest request, String Categories, Integer unitId, Integer unitId2) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		boolean flag = false;
		try {
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(Filter.eq("userId", monitor.getUserId()));
			filters.add(Filter.eq("idCard", monitor.getIdCard()));
			if (monitorService.getCount(filters) != null && monitorService.getCount(filters) > 0) {
				return new ApiReturn(400, "同一警员不可重复布控同一布控人");
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
			operationLogService.insertOperationLogs(user, 1, request.getRequestURL().toString(), user.getPoliceName() + "新增了" + monitor.getName() + "布控人",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		if (flag) {
			return new ApiReturn(200, "新增成功");
		} else {
			return new ApiReturn(400, "新增失败");
		}
	}

	/**
	 * 跳转布控人员查看页面
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月9日 下午7:02:24
	 */
	@RequestMapping("/look")
	@ResponseBody
	public ApiReturn look(HttpServletRequest request, Integer id) {
		try {
			// 获取当前登录用户
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			if (!monitorService.isPower(id, user, loginUserUnit)) {
				return new ApiReturn(400, "尚无权限查看！");
			}
			// 根据id获取布控人员数据
			Monitor monitor = monitorService.get(id);
			if (null == monitor) {
				return new ApiReturn(400, "布控人不存在！");
			}
			// 存放需要数据
			Map<String, Object> map = new HashMap<String, Object>();
			// 存放布控人信息
			map.put("id", monitor.getId());
			map.put("photo", ObjectUtils.firstNonNull(monitor.getPhoto(),""));
			map.put("name", ObjectUtils.firstNonNull(monitor.getName(),GlobalUnit.NULLMSG));
			map.put("contactInfo", StringUtils.isBlank(monitor.getContactInfo()) ? GlobalUnit.NULLMSG : monitor.getContactInfo());
			map.put("idCard", ObjectUtils.firstNonNull(monitor.getIdCard(),GlobalUnit.NULLMSG));
			map.put("sex", ObjectUtils.firstNonNull(monitor.getSex(),GlobalUnit.NULLMSG));
			Dictionary dictionary = null;
			if (monitor.getNationNumber() != null) {
				dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, monitor.getNationNumber());
			}
			map.put("nation", dictionary == null ? GlobalUnit.NULLMSG : dictionary.getItemName());
			map.put("nativeAddress", ObjectUtils.firstNonNull(monitor.getNativeAddress(), GlobalUnit.NULLMSG));
			map.put("currentAddress", ObjectUtils.firstNonNull(monitor.getCurrentAddress(), GlobalUnit.NULLMSG));
			// 存放布控信息
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, monitor.getUnitId() == null ? "" : monitor.getUnitId());
			User user1 = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, monitor.getUserId() == null ? "" : monitor.getUserId());
			map.put("unitName", unit == null ? GlobalUnit.NULLMSG : unit.getUnitName());
			map.put("userName", user1 == null ? GlobalUnit.NULLMSG : user1.getPoliceName());
			map.put("contactsPolice", ObjectUtils.firstNonNull(monitor.getContactsPolice(), GlobalUnit.NULLMSG));
			map.put("duration", ObjectUtils.firstNonNull(monitor.getDuration(), "0") + "天");
			dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, monitor.getPersonTypeId());
			map.put("personType", dictionary == null ? GlobalUnit.NULLMSG : dictionary.getItemName());
			/** 布控状态(0：未审核，1，已审核：2：审核拒绝，3：初审) */
			Integer statusType = monitor.getStatus() ;
			String status = statusType == 0 ? "未审核" : statusType == 1 ? "已审核" : statusType == 2 ? "审核拒绝" : statusType == 3 ? "初审" : "暂无";
			map.put("status", status);
			map.put("createDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(monitor.getCreateDate()));
			// 布控理由
			map.put("reason",ObjectUtils.firstNonNull(monitor.getReason(), GlobalUnit.NULLMSG));
			// 是否可审核
			String isAudit = monitorService.isAudit(user, loginUserUnit, monitor);
			map.put("isAudit", isAudit);
			// 操作日志
			operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看了" + monitor.getName() + "布控人信息",
					LogUtil.getData(request.getParameterMap()));
			return new ApiReturn(200, "查询成功", map);
		} catch (Exception e) {
			LOGGER.error(e);
			return new ApiReturn(400, "未知错误");
		}
	}

	/**
	 * 查询单位
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月12日 上午9:30:27
	 */
	@RequestMapping("/getUnit")
	@ResponseBody
	public ApiReturn getUnit(Integer unitId, HttpServletRequest request) {
		try {
			User usr = (User) request.getSession().getAttribute("user");
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, usr.getUnitId());
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			boolean flag = isPoliceManager(loginUserUnit);
			if (!flag) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", unit.getId());
				map.put("name", unit.getUnitName());
				return new ApiReturn(200, "查询成功", map);
			} else {
				if (null == unitId) {
					List<Map<String, Object>> units = new ArrayList<Map<String, Object>>();
					if (usr.getId() == 1 || "city".equals(loginUserUnit.getUnitRank().getName())) {
						for (Unit newUnit : unitService.getUnitArea(1)) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("id", newUnit.getId());
							map.put("name", newUnit.getUnitName());
							units.add(map);
						}
						return new ApiReturn(200, "查询成功", units);
					} else {
						for (Integer integer : loginUserUnit.getUnitSonIds()) {
							Unit newUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, integer);
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("id", newUnit.getId());
							map.put("name", newUnit.getUnitName());
							units.add(map);
						}
						return new ApiReturn(200, "查询成功", units);
					}
				} else {
					List<Map<String, Object>> units = unitService.getNextUnit(unitId);
					return new ApiReturn(200, "查询成功", units);
				}
			}
		} catch (Exception e) {
			LOGGER.error("查询失败" + e);
			return new ApiReturn(400, "查询失败");
		}
	}

	/**
	 * 查询单位下所有民警
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月12日 上午9:30:27
	 */
	@RequestMapping("/getUserByUnit")
	@ResponseBody
	public ApiReturn getUserByUnit(Integer unitId, HttpServletRequest request) {
		try {
			User usr = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			if (unitId == null) {
				return new ApiReturn(400, "请选择单位");
			}
			boolean flag = isPoliceManager(loginUserUnit);
			if (!flag) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", usr.getId());
				map.put("userName", usr.getPoliceName());
				return new ApiReturn(200, "查询成功", map);
			} else {
				List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
				List<User> users = userService.findList(null, Filter.eq("unit.id", unitId), null, null, Fetch.join("unit"));
				for (User user : users) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("userId", user.getId());
					map.put("userName", user.getPoliceName());
					userList.add(map);
				}
				return new ApiReturn(200, "查询成功", userList);
			}
		} catch (Exception e) {
			LOGGER.error("查询失败" + e);
			return new ApiReturn(400, "查询失败");
		}
	}

	/**
	 * 字典(民族,处置措施,人员类型,人员类别)
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月12日 上午9:30:27
	 */
	@RequestMapping("/getDictionary")
	@ResponseBody
	public ApiReturn getDictionary(String dictionary) {
		try {
			List<Map<String, Object>> list = dictionaryService.findByItemCode(dictionary, 0);
			return new ApiReturn(200, "查询成功", list);
		} catch (Exception e) {
			LOGGER.error(e);
			return new ApiReturn(400, "查询失败");
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
	public ApiReturn unControl(Integer id, HttpServletRequest request) throws ParseException {
		User user = (User) request.getSession().getAttribute("user");
		Monitor monitor = monitorService.get(id);
		// 审核状态
		Integer status = monitor.getStatus();
		if (status != 1) {
			return new ApiReturn(400, "未通过审核，无法撤控");
		}
		if (!user.getId().equals(monitor.getProposer())) {
			return new ApiReturn(400, "尚无权限");
		}
		// 审批时间
		String Atime = "";
		if (StringUtils.isBlank(monitor.getApprovalTime())) {
			return new ApiReturn(400, "审核时间为空，无法撤控");
		}
		String[] time = monitor.getApprovalTime().split(",");
		Atime = time[time.length - 1];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
		Date beginDate = sdf.parse(Atime);
		Date nowDate = new Date();
		long day = (nowDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		if (StringUtils.isBlank(monitor.getDuration()) || day > Long.valueOf(monitor.getDuration()).longValue() || day < 0) {
			return new ApiReturn(400, "布控时间过期，无法撤控");
		}
		monitor.setIsRevoke(1);
		monitor.setDuration("");
		monitorService.update(monitor);
		return new ApiReturn(200, "撤控成功");
	}

	/**
	 * 续控
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午9:05:12
	 */
	@RequestMapping("/conControl")
	@ResponseBody
	public ApiReturn conControl(Integer id, Integer days, HttpServletRequest request) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			Monitor monitor = monitorService.get(id);
			if (monitor == null) {
				return new ApiReturn(400, "布控信息不存在");
			}
			if (!user.getId().equals(monitor.getProposer())) {
				return new ApiReturn(400, "尚无权限");
			}
			// 审核状态
			Integer status = monitor.getStatus();
			if (status != 1) {
				return new ApiReturn(400, "未通过审核，续控失败");
			}
			if (days == null) {
				return new ApiReturn(400, "续控天数不可为空");
			}
			if (days <= 0) {
				return new ApiReturn(400, "续控天数不可为负数或0");
			}
			String[] times = monitor.getApprovalTime().split(",");
            String a = times[times.length - 1];
            // 审批时间
            Date pDate = null;
            try {
                pDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(a);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // 过期时间
            Date overDate = RuiecDateUtils.getNDaysTime(pDate, Integer.valueOf(monitor.getDuration()));
            // 过期天数
            long day = RuiecDateUtils.getTimeDifference(new Date(), overDate, 3);
            day = day + 1;
            if (day < 0) {
                day = 0;
            }
			int newDay = (int)day + days;
			if (newDay > 30) {
				return new ApiReturn(400, "续控总天数不可大于30天");
			}
			monitor.setDuration(String.valueOf(newDay));
			monitor.setApprovalTime(RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(new Date()));
			monitorService.update(monitor);
			return new ApiReturn(200, "续控成功");
		} catch (Exception e) {
			return new ApiReturn(400, "续控失败");
		}
	}

	/**
	 * 重新布控(修改)
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午9:05:12
	 */
	@RequestMapping("/reControl")
	@ResponseBody
	public ApiReturn reControl(Integer id, Integer days, HttpServletRequest request) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		Monitor mo = monitorService.get(id);
		if (mo == null) {
			return new ApiReturn(400, "布控信息不存在");
		}
		if (!monitorService.isPower(mo.getId(), user, loginUserUnit)) {
			return new ApiReturn(400, "无权限操作");
		}
		if (!mo.getProposer().equals(user.getId())) {
			return new ApiReturn(400, "不是布控申请人，无权限操作");
		}
		/*if (days == null || days == 0) {
		    return new ApiReturn(400, "布控天数不能为空");
        }
		if (days >30) {
            return new ApiReturn(400, "布控天数不能超过30天");
        }*/
		days = 30;
		if (mo.getIsRevoke() != 1) {
		    return new ApiReturn(400, "布控未撤销不能重新布控");
        }
		boolean flag = false;
		try {
			if (monitorService.updMonitor(user, loginUserUnit, mo, days)) {
				flag = true;
			}
		} catch (Exception e) {
			LOGGER.error("修改失败!" + e);
		}
		// 获取当前登录用户
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 1, request.getRequestURL().toString(), user.getPoliceName() + "修改了" + mo.getName() + "布控人",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		if (flag) {
			return new ApiReturn(200, "修改成功");
		} else {
			return new ApiReturn(400, "修改失败");
		}
	}

	/**
	 * 审核布控人
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午9:05:12
	 */
	@RequestMapping("/isColumn")
	@ResponseBody
	public ApiReturn isColumn(Integer id, Integer status, HttpServletRequest request) {
		// 获取当前登录用户
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		if (!monitorService.isPower(id, user, loginUserUnit)) {
			return new ApiReturn(400, "没有权限");
		}
		Monitor monitor = monitorService.get(id);
		if (monitor == null) {
			return new ApiReturn(400, "布控信息不存在");
		}else {
	        if (monitor.getStatus() == 1) {
	            return new ApiReturn(400, "该审核已通过");
	        }else if(monitor.getStatus() == 2) {
	            return new ApiReturn(400, "该审核已被拒绝");
	        }
        }
		Unit unit = unitService.get(monitor.getUnitId());
		String isAudit = monitorService.isAudit(user, loginUserUnit, monitor);
		try {
			if (isAudit.equals("noPower")) {
				return new ApiReturn(400, "没有权限");
			} else if (isAudit.equals("town") || isAudit.equals("town")) {
				if (status != null) {
					// 审核状态(初审)
					monitor.setStatus(status == 1 ? 3 : 2);
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
					monitor.setStatus(status == 1 ? 1 : 2);
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
					monitor.setStatus(status == 1 ? 1 : 2);
					// 审批单位
					if (unit.getPoliceTypesParentId() != null && unit.getParentId() == null) {
						monitor.setApprovalUnitId(monitor.getApprovalUnitId() + "," + unit.getPoliceTypesParentId());
					} else {
						monitor.setApprovalUnitId(monitor.getApprovalUnitId() + "," + unit.getParentId());
					}
					// 审批人
					monitor.setApprovalUserId(monitor.getApprovalUserId() + "," + user.getId());
					// 审批时间
					monitor.setApprovalTime(monitor.getApprovalTime() + "," + RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(new Date()));
				} else {
					// 审核状态(分县局通过或市局通过派出所民警)
					monitor.setStatus(status == 1 ? 1 : 2);
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
			e.printStackTrace();
			LOGGER.error("审核失败!" + e);
		}
		try {
			// 操作日志
			operationLogService.insertOperationLogs(user, 3, request.getRequestURL().toString(), user.getPoliceName() + "审核了" + monitor.getName(),
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		if (status == 1 || status == 3) {
			return new ApiReturn(200, "审核通过");
		} else {
			return new ApiReturn(200, "审核拒绝");
		}
	}

	/**
	 * 是否为管理员
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月8日 上午9:05:12
	 */
	public boolean isPoliceManager(LoginUserUnit loginUserUnit) {
		if ("警员".equals(loginUserUnit.getUserName())) {
			return false;
		}
		return true;
	}
	
	/**
     * 重点人员基本信息
     * 
     * @author qinzhitian<br>
     * @date 2018年1月12日 下午3:31:04
     */
    @RequestMapping("/monitorDetail")
    @ResponseBody
    public ApiReturn monitorDetail(Integer id, HttpServletRequest request) {
        try {
            if (id == null) {
                return new ApiReturn(400, "布控人员id不能为空");
            }
            User user = (User) request.getSession().getAttribute("user");
            LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
//            boolean flag = monitorAlarmService.isPower(user, loginUserUnit, id, "look");
//            if (!flag) {
//                return new ApiReturn(400, "无查看权限");
//            }
            // 布控详情
            Map<String, Object> map = monitorService.findMapById(id);
            // 插入日志
            operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "查看了布控人员基本信息",
                    LogUtil.getData(request.getParameterMap()));
            return new ApiReturn(200, "布控人员基本信息查询成功", map);
        } catch (Exception e) {
            LOGGER.error("布控人员基本信息查询失败");
            return new ApiReturn(400, "布控人员基本信息查询失败");
        }
    }
}
