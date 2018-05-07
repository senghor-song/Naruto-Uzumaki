package com.ruiec.web.controller.admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.dto.AlarmDTO;
import com.ruiec.web.entity.ControlPerson;
import com.ruiec.web.entity.ControlPersonAlarm;
import com.ruiec.web.entity.DynamicInfo;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.ControlPersonAlarmService;
import com.ruiec.web.service.ControlPersonInstructiService;
import com.ruiec.web.service.ControlPersonService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.DynamicInfoService;
import com.ruiec.web.service.OperationLogService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 重点人员预警控制器
 * 
 * @author Senghor<br>
 * @date 2017年12月1日 上午9:05:41
 */
@Controller("controlPersonAlarmController")
@RequestMapping("/admin/controlPersonAlarm")
public class ControlPersonAlarmController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(ControlPersonAlarmController.class);

	@Resource
	private ControlPersonAlarmService controlPersonAlarmService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private ControlPersonService controlPersonService;
	@Resource
	private ControlPersonInstructiService controlPersonInstructiService;
	@Resource
	private OperationLogService operationLogService;
	@Resource
	private DynamicInfoService dynamicInfoService;

	/**
	 * 查询稳控列表
	 * 
	 * @author yuankai 
     * @data 2017年12月8日 下午14:30:43
	 * */
	@RequestMapping("/stableControlList")
	public String stableControlList(Page page, Model model, String remark, String signStatus, String destination, String search, String searchType,
			HttpSession session, HttpServletRequest request, AlarmDTO alarmDTO, ControlPerson cp, ControlPersonAlarm cpam) {
		// 获取登录用户
		// User loginUser = (User) session.getAttribute("user");
		Map<String, Object> map = controlPersonAlarmService.stableControlList(page, signStatus, search, searchType, cp, cpam, alarmDTO);
		if (null != map) {
			// 是否反馈
			if (StringUtils.isNotBlank(cpam.getFeedbackTime())) {
				if ("yes".equals(cpam.getFeedbackTime())) {
					model.addAttribute("feedbackTime", "yes");
				} else {
					model.addAttribute("feedbackTime", "no");
				}
			}
			// 目的地
			List<String> destinationList = controlPersonAlarmService.selectLocation();
			// 签收状态
			model.addAttribute("signStatus", cpam.getSignStatus());
			// 人员类别
			model.addAttribute("personType", alarmDTO.getPersonType());
			// 开始时间
			model.addAttribute("startDate", alarmDTO.getStartDate());
			// 结束时间
			model.addAttribute("endDate", alarmDTO.getEndDate());
			// 动态信息
			model.addAttribute("dynamicTypeIdz", alarmDTO.getDynamicTypeIdz());
			// 预警地区
			model.addAttribute("unitId", alarmDTO.getUnitId());
			// 稳控状态
			model.addAttribute("stableControl", alarmDTO.getStableControl());
			page = (Page) map.get("page");
			// 解析json格式数据得到最新的一条稳控状态
			for (Object object : page.getList()) {
				ControlPersonAlarm controlPersonAlarm = (ControlPersonAlarm) object;
				if (!StringUtils.isBlank((controlPersonAlarm.getRemark()))) {
					JSONArray jsons = JSONArray.fromObject((controlPersonAlarm.getRemark()));
					JSONArray arry = JSONArray.fromObject(jsons.get(jsons.size() - 1));
					List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
					for (int i = 0; i < arry.size(); i++) {
						JSONObject jsonObject = arry.getJSONObject(i);
						Map<String, String> map1 = new HashMap<String, String>();
						for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
							String key = (String) iter.next();
							String value = jsonObject.get(key).toString();
							map1.put(key, value);
						}
						rsList.add(map1);
					}
					String contros = rsList.get(0).get("contro");
					controlPersonAlarm.setStatus(contros);
				} else {
					model.addAttribute("", false);
					model.addAttribute("", null);
				}
			}
			// 动态信息
			model.addAttribute("dynamicType", map.get("dynamicType"));
			// 人员类别
			model.addAttribute("dataitem", map.get("dataitem"));
			// 预警地区
			model.addAttribute("units", map.get("units"));
			// 下拉框的类型 （name和idCard）
			model.addAttribute("search", search);
			// 下拉框的值
			model.addAttribute("searchType", searchType);
			// 目的地
			model.addAttribute("destinationList", destinationList);
			model.addAttribute("destination", destination);
			// 分页数据
			model.addAttribute("page", map.get("page"));

		}
		/*operationLogService.insertOperationLogs(loginUser, 4, request.getRequestURI().toString(), loginUser.getPoliceName() + "查看了系统查询稳控列表",
				LogUtil.getData(request.getParameterMap()));*/
		return "/admin/controlPersonAlarm/stableControlList";
	}

	/**
	 * 查询预警信息分页
	 * 
	 * @author 陈靖原<br>
	 *         Date 2017年12月7日 下午5:00:43
	 * @throws ParseException
	 * */
	@RequestMapping("/list")
	public String list(Model model, Page page, ControlPerson cp, ControlPersonAlarm cpam, AlarmDTO alarmDTO, HttpSession session, HttpServletRequest request)
			throws ParseException {
		// 获取登录用户
		User loginUser = (User) session.getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) session.getAttribute("loginUserUnit");
		Map<String, Object> map = controlPersonAlarmService.searchForAlarm(page, cp, cpam, alarmDTO, loginUserUnit, loginUser);
		// 是否反馈
		if (StringUtils.isNotBlank(cpam.getFeedbackTime())) {
			if ("yes".equals(cpam.getFeedbackTime())) {
				model.addAttribute("feedbackTime", "yes");
			} else {
				model.addAttribute("feedbackTime", "no");
			}
		}
		// 身份证
		model.addAttribute("idCard", cp.getIdCard());
		// 姓名
		model.addAttribute("name", cp.getName());
		// 签收状态
		model.addAttribute("signStatus", cpam.getSignStatus());
		// 人员类别
		model.addAttribute("personType", alarmDTO.getPersonType());
		// 开始时间
		model.addAttribute("startDate", alarmDTO.getStartDate());
		// 结束时间
		model.addAttribute("endDate", alarmDTO.getEndDate());
		// 动态信息
		model.addAttribute("dynamicTypeIdz", alarmDTO.getDynamicTypeIdz());
		// 预警地区
		model.addAttribute("unitId", alarmDTO.getUnitId());
		// 预警级别（当天）
		if (!StringUtils.isBlank(alarmDTO.getTodayAlarm())) {
			// 开始时间
			model.addAttribute("startDate", controlPersonAlarmService.getTimeOfDay(new Date(), 0, 0, 0, 0));
			// 结束时间
			model.addAttribute("endDate", controlPersonAlarmService.getTimeOfDay(new Date(), 23, 59, 59, 999));
			model.addAttribute("warningLevelz", alarmDTO.getTodayAlarm());
		}
		// 字典数据
		// 动态信息
		model.addAttribute("dynamicType", map.get("dynamicType"));
		// 人员类别
		model.addAttribute("dataitem", map.get("dataitem"));
		// 预警地区
		model.addAttribute("units", map.get("units"));
		// 预警级别
		model.addAttribute("warningLevels", map.get("warningLevels"));
		// 分页数据
		model.addAttribute("page", map.get("page"));
		// 预警总数
		model.addAttribute("count", map.get("count"));
		// 当日预警级别
		model.addAttribute("todayAlarm", alarmDTO.getTodayAlarm());
		// 插入操作日志
		try {
			/*operationLogService.insertOperationLogs(loginUser, 4, request.getRequestURI().toString(), loginUser.getPoliceName() + "查看了预警信息",
					LogUtil.getData(request.getParameterMap()));*/
		} catch (Exception e) {
			LOGGER.error("插入预警列表日志失败:" + e);
		}

		return "/admin/controlPersonAlarm/list";
	}

	/**
	 * 签收预警
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月7日 上午7:53:29
	 */
	@RequestMapping("/receiveAlarm")
	@ResponseBody
	public JsonReturn receiveAlarm(Integer id, HttpServletRequest request, HttpSession session, ControlPersonAlarm controlPersonAlarm) {
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		boolean flag = controlPersonAlarmService.isPower(user, loginUserUnit, id, "sign");
		if (!flag) {
			return new JsonReturn(400, "无权限进行此操作");
		}
		if (null == user) {
			return new JsonReturn(400, "尚未登录");
		}
		controlPersonAlarm = controlPersonAlarmService.get(id);
		if (StringUtils.isNotBlank(controlPersonAlarm.getSignName()) || "2".equals(controlPersonAlarm.getSignName())) {
			return new JsonReturn(400, "该预警已签收");
		}
		controlPersonAlarm.setSignName(user.getPoliceName());
		controlPersonAlarm.setSignPrikey(user.getId());
		controlPersonAlarm.setSignStatus(2);
		controlPersonAlarm.setSignTime(DateFormat.getDateTimeInstance().format(new Date()));
		controlPersonAlarmService.updateInclude(controlPersonAlarm, new String[] { "signName", "signPrikey", "signStatus", "signTime" }, null);
		// 插入日志
		operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "签收了预警",
				LogUtil.getData(request.getParameterMap()));
		return new JsonReturn(200, "签收成功", "/admin/controlPersonAlarm/list.shtml");
	}

	/**
	 * 预警列表详情
	 * 
	 * @author 陈靖原<br>
	 *  id  预警id
	 *  pid 重点人id
	 *  iid 指令id
	 * @date 2017年12月7日 上午7:53:29
	 */
	@RequestMapping("/alarmDetail")
	public String AlarmDetail(Integer id, Integer pid, Integer iid, Model model, HttpServletRequest request,String name) {
		if (id == null) {
			LOGGER.info("该预警信息不存在");
			throw new RuntimeException("该预警信息不存在");
		}
		User user = (User) request.getSession().getAttribute("user");
		if (StringUtils.isBlank(name)) {
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			boolean flag = false;
			boolean flagz = false;
			if (id == null || pid == null) {
				LOGGER.info("无权限");
				throw new RuntimeException("无权限");
			}
			if (iid == null) {
				flag = controlPersonAlarmService.isPower(user, loginUserUnit, id, "look");
				if (!flag) {
					LOGGER.info("无权限");
			    	throw new RuntimeException("无权限");
					
				}
			} else {
				flagz = controlPersonInstructiService.isPower(user, loginUserUnit, iid, 4);
				if (!flagz) {
					LOGGER.info("无权限");
					throw new RuntimeException("无权限");
				}
			}
		}
		// 重点人详情
        DetachedCriteria cpn = DetachedCriteria.forClass(ControlPerson.class);
        cpn.createCriteria("controlPersonExtend", JoinType.LEFT_OUTER_JOIN);
        cpn.add(Restrictions.eq("id", pid));
        List<ControlPerson> cpnlist = controlPersonService.findList(cpn, 1, null, null);
        if (null != cpnlist && cpnlist.size() > 0) {
            model.addAttribute("controlPerson", cpnlist.get(0));
        }
        // 重点人预警信息
        ControlPersonAlarm controlPersonAlarm = controlPersonAlarmService.get(id);
        model.addAttribute("controlPersonAlarm", controlPersonAlarm);
        // 重点人人员类别
        DetachedCriteria cp = DetachedCriteria.forClass(ControlPerson.class);
        cp.add(Restrictions.eq("id", pid));
        cp.setFetchMode("controlPersonTypes", FetchMode.SELECT);
        List<ControlPerson> controlPersonz = controlPersonService.findList(cp, 1, null, null);
        if (null != controlPersonz && controlPersonz.size() > 0) {
            model.addAttribute("controlPersonz", controlPersonz.get(0));
        }
        // 重点人预警签收指令信息
        List<Map<String, Object>> InstructiPerson = controlPersonInstructiService.issuedListByPerson(id);
        List<Map<String, Object>> InstructiUnit = controlPersonInstructiService.issuedListByUnit(id);
        // 下发的处置要求
        if (null != iid) {
            JSONObject json = controlPersonInstructiService.findInstructionDetail(iid);
            model.addAttribute("json", json.opt("content"));
        }
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        // 解析轨迹
        if (null != controlPersonAlarm.getDynamicInfoId()) {
            DynamicInfo d = dynamicInfoService.get(controlPersonAlarm.getDynamicInfoId());
            if (d != null) {
                ControlPerson c = controlPersonService.get(d.getControlPersonId());
                if (d != null && c.getIdCard().equals(controlPersonAlarm.getIdCard())) {
                    String json = (d).getInformation();
                    l = controlPersonAlarmService.getDynamic(json);
                }
            }
        }
        model.addAttribute("InstructiPerson", InstructiPerson);
        model.addAttribute("InstructiUnit", InstructiUnit);
        model.addAttribute("id", id);
        model.addAttribute("pid", pid);
        model.addAttribute("iid", iid);
        if (l != null && l.size() > 0) {
            model.addAttribute("l", l);
        }
		// 插入日志
		/*operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "查看了预警列表详情",
				LogUtil.getData(request.getParameterMap()));*/
		return "/admin/controlPersonAlarm/alarmDetail";
	}
     
	
	/**
	 * 判断是否有权限查看预警详情
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月25日 下午3:37:01
	 */
	@RequestMapping("/isPower")
	@ResponseBody
	public JsonReturn AlarmDetail(Integer id, Integer pid, Integer iid, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		boolean flag = false;
		boolean flagz = false;
		if (id == null || pid == null) {
			return new JsonReturn(400, "无权限操作");
		}
		if (iid == null) {
			flag = controlPersonAlarmService.isPower(user, loginUserUnit, id, "look");
			if (flag) {
				return new JsonReturn(200, "成功");
			}
		} else {
			flagz = controlPersonInstructiService.isPower(user, loginUserUnit, iid, 4);
			if (flagz) {
				return new JsonReturn(200, "成功");
			}
		}
		return new JsonReturn(400, "无权限操作");
	}

	/**
	 * 历史预警记录
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月7日 上午7:53:29
	 */
	@RequestMapping("/alarmHistorical")
	public String instructionHistorical(Integer id, Integer pid, Integer iid, Model model, Page page, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		boolean flag = controlPersonAlarmService.isPower(user, loginUserUnit, id, "look");
		if (!flag) {
			return "redirect:/admin/controlPersonAlarm/list.shtml";
		}
		// 根据重点人Id查询所有预警
		DetachedCriteria cpa = DetachedCriteria.forClass(ControlPersonAlarm.class);
		DetachedCriteria cp = cpa.createCriteria("controlPerson", JoinType.LEFT_OUTER_JOIN);
		// 尚未被逻辑删除的重点人
		cp.add(Restrictions.eq("isDelete", 0));
		cp.setFetchMode("controlPersonTypes", FetchMode.SELECT);
		cp.add(Restrictions.eq("id", pid));
		controlPersonAlarmService.findByPage(page, cpa);
		model.addAttribute("page", page);
		model.addAttribute("id", id);
		model.addAttribute("pid", pid);
		model.addAttribute("iid", iid);
		// 插入日志
		/*operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "查看了 历史预警记录",
				LogUtil.getData(request.getParameterMap()));*/
		return "/admin/controlPersonAlarm/alarmHistorical";
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
		boolean flag = true;
		flag = controlPersonAlarmService.isPower(user, loginUserUnit, id, "feedback");
		if (!flag) {
			return "redirect:/admin/controlPersonAlarm/list.shtml";
		}
		// 查询字典稳控大类
		List<Map<String, Object>> list = controlPersonAlarmService.getDictionary("steady");
		// 查询该字典是否有小类，如果有则添加
		if (list.size() > 0) {
			// 查询该字典是否有小类，如果有则添加
			List<Map<String, Object>> steady2 = dictionaryService.findSubSet(new Integer(list.get(0).get("id").toString()), null);
			if (steady2.size() > 0) {
				model.addAttribute("steady2", steady2);
			}
			model.addAttribute("steady1", list);
		}
		
		ControlPersonAlarm cpa = controlPersonAlarmService.get(id);
		String jsonStr = cpa.getRemark();
		model.addAttribute("id", id);
		model.addAttribute("jsonStr", jsonStr);
		// 插入日志
		/*operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "跳转预警反馈页面",
				LogUtil.getData(request.getParameterMap()));*/
		return "/admin/controlPersonAlarm/foundBack";
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
		boolean flag = controlPersonAlarmService.isPower(user, loginUserUnit, id, "look");
		if (!flag) {
			return "redirect:/admin/controlPersonAlarm/list.shtml";
		}
		model.addAttribute("id", id);
		return "/admin/controlPersonAlarm/foundBackDetail";
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
		boolean flag = controlPersonAlarmService.isPower(user, loginUserUnit, id, "look");
		if (!flag) {
			return new JsonReturn(400, "无权限查看该预警详情");
		}
		JsonReturn jsonReturn = controlPersonAlarmService.alarmFeedbackDetails(id);
		return jsonReturn;
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
		if (steady.size() == 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", "暂无");
			map.put("id", "");
			steady.add(map);
		}
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
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			JsonReturn jsonReturn = controlPersonAlarmService.feedback(jsonStr, id, user, loginUserUnit);
			// 插入日志
			operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "进行预警反馈",
					LogUtil.getData(request.getParameterMap()));
			return jsonReturn;
		} catch (Exception e) {
			LOGGER.error("反馈失败:" + e);
			return new JsonReturn(400, "反馈失败，字数过多");
		}
	}

	/**
	 * 当日预警数量
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月22日 上午9:07:28
	 */
	@RequestMapping("/alarmCounts")
	@ResponseBody
	public JsonReturn alarmCounts(HttpServletRequest request) {
		User loginUser = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		try {
			List<Map<String, Object>> list = controlPersonAlarmService.alarmCounts(loginUser, loginUserUnit);
			return new JsonReturn(list);
		} catch (Exception e) {
			LOGGER.error("统计当日预警失败:" + e);
			return new JsonReturn(400);
		}
	}
	
	/**
	 * 根据身份查预警总量
	 * 
	 * @author 陈靖原<br>
	 * @date 2018年1月22日 上午9:07:28
	 */
	@RequestMapping("/alarmCount")
	@ResponseBody
	public JsonReturn alarmCount(HttpServletRequest request) {
		User loginUser = (User) request.getSession().getAttribute("user");
		LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
		try {
			Long count = controlPersonAlarmService.alarmCount(loginUserUnit, loginUser);
			Map<String, Object> mp = new HashMap<>();
			mp.put("count", count);
			return new JsonReturn(mp);
		} catch (Exception e) {
			LOGGER.error("统计预警总数失败:" + e);
			return new JsonReturn(400);
		}
	}
}
