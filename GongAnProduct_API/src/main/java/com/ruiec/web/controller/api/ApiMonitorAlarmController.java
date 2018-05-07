package com.ruiec.web.controller.api;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.web.common.ApiReturn;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.dto.MonitorAlarmDTO;
import com.ruiec.web.entity.MonitorAlarm;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.MonitorAlarmService;
import com.ruiec.web.service.MonitorService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.util.RuiecDateUtils;

/**
 * 布控预警api控制器
 * 
 * @author qinzhitian<br>
 * @date 2018年1月12日 上午9:30:27
 */
@Controller("apiMonitorAlarmController")
@RequestMapping("/api/admin/monitorAlarm")
public class ApiMonitorAlarmController {

	private static final Logger LOGGER = Logger.getLogger(ApiMonitorAlarmController.class);

	@Resource
	private MonitorAlarmService monitorAlarmService;
	@Resource
	private MonitorService monitorService;
	@Resource
	private OperationLogService operationLogService;
	@Resource
	private UserService userService;

	/**
	 * 人员布控列表分页查询
	 * @author Senghor<br>
	 * @date 2018年4月10日 下午4:40:59
	 */
	@RequestMapping("/personList")
	@ResponseBody
	public ApiReturn personList(Page page, HttpServletRequest request,String type) {
		try {
			// 获取登录用户
			User loginUser = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			// 分页数据
			Map<String, Object> map = monitorService.findByPage(page, loginUser, loginUserUnit,type);
			// 插入操作日志
			operationLogService.insertOperationLogs(loginUser, 4, request.getRequestURI().toString(), loginUser.getPoliceName() + "查看了人员布控列表信息",
					LogUtil.getData(request.getParameterMap()));
			return new ApiReturn(200, "人员布控列表分页查询成功", map);
		} catch (Exception e) {
			LOGGER.error("人员布控分页查询失败" + e);
			return new ApiReturn(400, "人员布控列表分页查询失败");
		}
	}
	
	/**
	 * 布控预警列表分页查询
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月12日 上午10:08:59
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ApiReturn list(Page page, HttpServletRequest request) {
		try {
			// 获取登录用户
			User loginUser = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			// 分页数据
			Map<String, Object> map = monitorAlarmService.findByPage(page, loginUser, loginUserUnit);

			// 插入操作日志
			operationLogService.insertOperationLogs(loginUser, 4, request.getRequestURI().toString(), loginUser.getPoliceName() + "查看了布控预警列表信息",
					LogUtil.getData(request.getParameterMap()));
			return new ApiReturn(200, "布控预警列表分页查询成功", map);
		} catch (Exception e) {
			LOGGER.error("布控预警分页查询失败" + e);
			return new ApiReturn(400, "布控预警列表分页查询失败");
		}
	}

	/**
	 * 布控人员基本信息
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月12日 下午3:31:04
	 */
	@RequestMapping("/monitorDetail")
	@ResponseBody
	public ApiReturn MonitorDetail(Integer id, HttpServletRequest request) {
		try {
			if (id == null) {
				return new ApiReturn(400, "预警id不能为空");
			}
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			boolean flag = monitorAlarmService.isPower(user, loginUserUnit, id, "look");
			if (!flag) {
				return new ApiReturn(400, "无查看权限");
			}
			// 布控预警详情
			Map<String, Object> map = monitorAlarmService.findMapById(id);
			// 插入日志
			operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "查看了布控预警布控人员基本信息",
					LogUtil.getData(request.getParameterMap()));
			return new ApiReturn(200, "布控人员基本信息查询成功", map);
		} catch (Exception e) {
			LOGGER.error("布控人员基本信息查询失败");
			return new ApiReturn(400, "布控人员基本信息查询失败");
		}
	}

	/**
	 * 布控人员预警信息
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月12日 下午3:31:04
	 */
	@RequestMapping("/alarmDetail")
	@ResponseBody
	public ApiReturn AlarmDetail(Integer id, HttpServletRequest request) {
		try {
			if (id == null) {
				return new ApiReturn(400, "预警id不能为空");
			}
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			boolean flag = monitorAlarmService.isPower(user, loginUserUnit, id, "look");
			if (!flag) {
				return new ApiReturn(400, "无查看权限");
			}
			// 布控预警详情
			Map<String, Object> map = monitorAlarmService.findAlarmDetail(id);
			// 插入日志
			operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "查看了布控预警布控人员预警信息",
					LogUtil.getData(request.getParameterMap()));
			return new ApiReturn(200, "布控人员预警信息查询成功", map);
		} catch (Exception e) {
			LOGGER.error("布控人员预警信息查询失败");
			return new ApiReturn(400, "布控人员预警信息查询失败");
		}
	}

	/**
	 * 布控预警反馈
	 * 
	 * @author 陈靖原<br>
	 * @modify 覃志添<br>
	 * @date 2017年12月7日 上午7:53:29
	 */
	@RequestMapping("/feedback")
	@ResponseBody
	public ApiReturn feedback(MonitorAlarmDTO monitorAlarmDTO,HttpServletRequest request) {
		try {
			Date newDate = new Date();//当前时间
			if (monitorAlarmDTO.getMonitorAlarmId() == null) {
				return new ApiReturn(400, "预警id不能为空");
			}
			MonitorAlarm ma = monitorAlarmService.get(monitorAlarmDTO.getMonitorAlarmId());
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			boolean flag = monitorAlarmService.isPower(user, loginUserUnit, ma.getId(), "feedback");
			if (!flag) {
				return new ApiReturn(400, "无权限反馈");
			}
			JSONObject json = new JSONObject();
			if (monitorAlarmDTO.getColumnTubeMode() == null || monitorAlarmDTO.getContro() == null || monitorAlarmDTO.getContro2() == null || monitorAlarmDTO.getFeedback() == null) {
				return new ApiReturn(400, "请完善内容后提交");
			}
			json.put("columnTubeMode", monitorAlarmDTO.getColumnTubeMode());
			json.put("contro", monitorAlarmDTO.getContro());
			json.put("contro2", monitorAlarmDTO.getContro2());
			if (monitorAlarmDTO.getColumnTubeMode().equals("已发现")) {
				//判断是否有手机号
				if (StringUtils.isBlank(monitorAlarmDTO.getPhone())) {
					return new ApiReturn(400, "请完善内容后提交");
				}
				json.put("phone", monitorAlarmDTO.getPhone());
			}
			//判断反馈内容字符是否超过长度
			if (monitorAlarmDTO.getFeedback().length()>200) {
				return new ApiReturn(400, "反馈内容过多，请调整内容");
			}
			json.put("feedback", monitorAlarmDTO.getFeedback());
			json.put("userName", user.getPoliceName());
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
			json.put("unitName", unit.getUnitName());
			json.put("feedbackTime", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(newDate));
			//判断之前是否有反馈
			if (StringUtils.isBlank(ma.getRemark())) {
				//没有则新建一个反馈json字符串
				JSONArray jsons = new JSONArray();
				jsons.add(json);
				// 反馈内容
				ma.setRemark(jsons.toString());
			} else {
				//有则追加一个反馈json字符串
				JSONArray jsons = JSONArray.fromObject(ma.getRemark());
				jsons.add(json);
				// 追加反馈内容
				ma.setRemark(jsons.toString());
			}
			// 反馈时间
			ma.setFeedbackTime(newDate);
			// 反馈信息
			monitorAlarmService.updateInclude(ma, new String[] { "remark", "feedbackTime" }, new String[] {});
			// 插入日志
			operationLogService.insertOperationLogs(user, 3, request.getRequestURI().toString(), user.getPoliceName() + "进行预警反馈",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			return new ApiReturn(400, "反馈失败，字数过多");
		}
		return new ApiReturn(200, "反馈成功");
	}

	/**
	 * 预警签收
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月12日 下午4:18:21
	 */
	@RequestMapping("/receiveAlarm")
	@ResponseBody
	public ApiReturn receiveAlarm(Integer id, HttpServletRequest request) {
		try {
			if (id == null) {
				return new ApiReturn(400, "预警id不能为空");
			}
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			boolean flag = monitorAlarmService.isPower(user, loginUserUnit, id, "sign");
			if (!flag) {
				return new ApiReturn(400, "无权限进行此操作");
			}
			MonitorAlarm monitorAlarm = monitorAlarmService.get(id);
			if (monitorAlarm.getSignUserId() != null) {
				return new ApiReturn(400, "该预警已签收");
			}
			monitorAlarm.setSignUserId(user.getId());
			monitorAlarm.setSignStatus(2);
			monitorAlarm.setSignTime(new Date());
			monitorAlarmService.updateInclude(monitorAlarm, new String[] { "signUserId", "signStatus", "signTime" }, null);
			// 插入日志
			operationLogService.insertOperationLogs(user, 3, request.getRequestURI().toString(), user.getPoliceName() + "签收了预警",
					LogUtil.getData(request.getParameterMap()));
			return new ApiReturn(200, "签收成功");
		} catch (Exception e) {
			return new ApiReturn(400, "签收失败");
		}
	}

	/**
	 * 查看布控预警反馈记录
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月12日 下午5:06:35
	 */
	@RequestMapping("/feedbackList")
	@ResponseBody
	public ApiReturn feedbackList(Integer id, HttpServletRequest request) {
		try {
			if (id == null) {
				return new ApiReturn(400, "预警id不能为空");
			}
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			boolean flag = monitorAlarmService.isPower(user, loginUserUnit, id, "look");
			if (!flag) {
				return new ApiReturn(400, "无权限进行此操作");
			}
			List<Map<String, Object>> list = monitorAlarmService.findFeedBackList(id);
			// 插入日志
			operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "查看了布控预警反馈记录",
					LogUtil.getData(request.getParameterMap()));
			Map<String,Object> map = new HashMap<String, Object>();
	        Collections.reverse(list);//倒序
			map.put("mapList", list);
			return new ApiReturn(200, "布控预警反馈记录查询成功", map);
		} catch (Exception e) {
			LOGGER.error("布控预警反馈记录查询失败", e);
			return new ApiReturn(400, "布控预警反馈记录查询失败");
		}
	}
	
	/**
	 * 布控提醒接口查询
	 * @author Senghor<br>
	 * @date 2018年4月13日 下午1:43:44
	 */
	@RequestMapping("/alarmAlertList")
	@ResponseBody
	public ApiReturn alarmAlertList(Page page, HttpServletRequest request,Integer type) {
		try {
			if (type == null) {
				return new ApiReturn(400,"缺少参数");
			}
			// 获取登录用户
			User loginUser = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			// 分页数据
			Map<String, Object> map = monitorAlarmService.findByTypePage(page, loginUser, loginUserUnit,type);
			return new ApiReturn(200, "布控提醒查询成功", map);
		} catch (Exception e) {
			LOGGER.error("布控提醒查询失败" + e);
			return new ApiReturn(400, "布控提醒查询失败");
		}
	}
}
