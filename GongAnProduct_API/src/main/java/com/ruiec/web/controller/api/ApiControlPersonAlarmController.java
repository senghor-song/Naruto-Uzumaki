package com.ruiec.web.controller.api;

import java.text.DateFormat;
import java.util.ArrayList;
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
import com.ruiec.framework.web.support.controller.BaseController;
import com.ruiec.web.common.ApiReturn;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.dto.PersonAlarmDTO;
import com.ruiec.web.entity.ControlPersonAlarm;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.ControlPersonAlarmService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.util.RuiecDateUtils;

/**
 * 重点人员预警api控制器
 * 
 * @author qinzhitian<br>
 * @date 2018年1月6日 下午7:39:58
 */
@Controller("apiControlPersonAlarmController")
@RequestMapping("/api/admin/controlPersonAlarm")
public class ApiControlPersonAlarmController extends BaseController {

	private static final Logger LOGGER = Logger.getLogger(ApiControlPersonAlarmController.class);
	@Resource
	private ControlPersonAlarmService controlPersonAlarmService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private UserService userService;
	@Resource
	private OperationLogService operationLogService;

	/**
	 * 查询预警信息列表
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月6日 下午7:46:28
	 */
	@RequestMapping("/alarmList")
	@ResponseBody
	public ApiReturn alarmList(Page page, HttpServletRequest request) {
		try {
			// 获取登录用户
			User loginUser = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			Map<String, Object> map = controlPersonAlarmService.findApiAlarmList(page, loginUserUnit, loginUser);
			//插入操作日志
			//operationLogService.insertOperationLogs(loginUser, 4, request.getRequestURI().toString(), loginUser.getPoliceName()+"查看了预警信息列表",LogUtil.getData(request.getParameterMap()));
			return new ApiReturn(200, "查询预警信息列表成功", map);
		} catch (Exception e) {
			LOGGER.error("查询预警信息列表失败", e);
			return new ApiReturn(400, "查询预警信息列表失败");
		}
	}

	/**
	 * 查询预警信息详情
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月6日 下午7:46:28
	 * @param id
	 *            预警信息id
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
			if (id==null || id == 0) {
                return new ApiReturn(400, "缺少预警id");
            }
			boolean flag = controlPersonAlarmService.isPower(user, loginUserUnit, id, "look");
			if (!flag) {
				return new ApiReturn(400, "无权限操作");
			}
			//插入日志
			//operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName()+"查看了 预警列表详情", LogUtil.getData(request.getParameterMap()));
			return new ApiReturn(200, "查询预警信息详情成功", controlPersonAlarmService.getMapById(id));
		} catch (Exception e) {
			LOGGER.error("查询预警信息详情失败", e);
			return new ApiReturn(400, "查询预警信息详情失败");
		}
	}

	/**
	 * 历史预警记录api接口
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月8日 下午8:11:36
	 */
	@RequestMapping("/alarmHistoricalList")
	@ResponseBody
	public ApiReturn alarmHistoricalList(Integer id, Page page, HttpServletRequest request) {
		try {
			if (id == null) {
				return new ApiReturn(400, "预警id不能为空");
			}
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			// 查询权限
			boolean flag = controlPersonAlarmService.isPower(user, loginUserUnit, id, "look");
			// 预警
			ControlPersonAlarm c = controlPersonAlarmService.get(id);
			if (!flag) {
				return new ApiReturn(400, "无权限操作");
			}
			// 根据重点人Id查询所有预警
			Map<String, Object> map = controlPersonAlarmService.findAlarmHistorical(page,c.getControlPerson().getId(),user);
			return new ApiReturn(200, "查询历史预警记录列表成功", map);
		} catch (Exception e) {
			LOGGER.error("查询预警信息详情失败", e);
			return new ApiReturn(400, "查询历史预警记录列表失败");
		}
	}

	/**
	 * 稳控状态接口
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月9日 上午8:53:24
	 */
	@RequestMapping("/steady")
	@ResponseBody
	public ApiReturn steady() {
		try {
		    // 查询字典稳控大类
	        List<Map<String, Object>> listMap = controlPersonAlarmService.getDictionary("steady");
	        // 查询该字典是否有小类，如果有则添加
	        for (int i = 0; i < listMap.size(); i++) {
	            Map<String, Object> map = listMap.get(i);
                // 查询该字典是否有小类，如果有则添加
                List<Map<String, Object>> steadys = dictionaryService.findByItemCode(GlobalUnit.STEADY_ITEMCODE, Integer.valueOf(map.get("id").toString()));
                map.put("mapList", steadys);
            }
			return new ApiReturn(200, "查询稳控状态成功", listMap);
		} catch (Exception e) {
			LOGGER.error("查询稳控状态失败", e);
			return new ApiReturn(400, "查询稳控状态失败");
		}
	}

	/**
	 * 预警反馈接口
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月9日 上午9:13:31
	 */
	@RequestMapping("/feedback")
	@ResponseBody
	public ApiReturn feedback(PersonAlarmDTO personAlarmDTO, HttpServletRequest request) {
		try {
			Date newDate = new Date();//当前时间
			if (personAlarmDTO.getAlarmId() == null) {
				return new ApiReturn(400, "预警id不能为空");
			}
			ControlPersonAlarm cpa = controlPersonAlarmService.get(personAlarmDTO.getAlarmId());
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			if (null == user) {
				return new ApiReturn(400, "反馈失败");
			}
			boolean flag = controlPersonAlarmService.isPower(user, loginUserUnit, personAlarmDTO.getAlarmId(), "feedback");
			if (!flag) {
				return new ApiReturn(400, "无权限反馈");
			}
			JSONObject json = new JSONObject();
			if (personAlarmDTO.getColumnTubeMode() == null || personAlarmDTO.getContro() == null || personAlarmDTO.getContro2() == null || personAlarmDTO.getFeedback() == null) {
				return new ApiReturn(400, "请完善内容后提交");
			}

            json.put("feedback", personAlarmDTO.getFeedback());
			json.put("columnTubeMode", personAlarmDTO.getColumnTubeMode());
			json.put("contro", personAlarmDTO.getContro());
			json.put("contro2", personAlarmDTO.getContro2());
			if (personAlarmDTO.getColumnTubeMode().equals("已发现")) {
				//判断是否有手机号
				if (StringUtils.isBlank(personAlarmDTO.getPhone())) {
					return new ApiReturn(400, "请完善内容后提交");
				}
				json.put("phone", personAlarmDTO.getPhone());
			}
			//判断反馈内容字符是否超过长度
			if (personAlarmDTO.getFeedback().length()>200) {
				return new ApiReturn(400, "反馈内容过多，请调整内容");
			}
			json.put("userName", user.getPoliceName());
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
			json.put("unitName", unit.getUnitName());
			// 反馈时间
			cpa.setFeedbackTime(RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(newDate));
			json.put("feedbackTime", cpa.getFeedbackTime());
			//判断之前是否有反馈
			if (StringUtils.isBlank(cpa.getRemark())) {
				//没有则新建一个反馈json字符串
				JSONArray jsons = new JSONArray();
				jsons.add(json);
				// 反馈内容
				cpa.setRemark(jsons.toString());
			} else {
				//有则追加一个反馈json字符串
				JSONArray jsons = JSONArray.fromObject(cpa.getRemark());
				jsons.add(json);
				// 追加反馈内容
				cpa.setRemark(jsons.toString());
			}
			// 反馈信息
			controlPersonAlarmService.updateInclude(cpa, new String[] { "remark", "feedbackTime" }, new String[] {});
			// 插入日志
			operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "进行预警反馈",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("预警反馈失败", e);
			return new ApiReturn(400, "预警反馈失败");
		}
		return new ApiReturn(200, "反馈成功");
	}

	/**
	 * 预警反馈记录
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月9日 上午9:40:39
	 */
	@RequestMapping("/alarmFeedbackDetails")
	@ResponseBody
	public ApiReturn alarmFeedbackDetails(Integer id, HttpServletRequest request) {
		try {
			if (id == null) {
				return new ApiReturn(400, "预警id不能为空");
			}
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			boolean flag = controlPersonAlarmService.isPower(user, loginUserUnit, id, "look");
			if (!flag) {
				return new ApiReturn(400, "无权限查看该预警反馈记录");
			}
			ControlPersonAlarm cpa = controlPersonAlarmService.get(id);
			JSONArray jsonArray = JSONArray.fromObject(cpa.getRemark());
			List<Object> mapList = new ArrayList<Object>();
			for (Object obj : jsonArray) {
				JSONObject json = JSONObject.fromObject(obj);
				if (json.isNullObject()) {
					continue;
				}
				Dictionary contro = dictionaryService.get(json.optInt("contro"));
				if (contro != null) {
					json.put("contro", contro.getItemName());
				} else {
					json.put("contro", GlobalUnit.NULLMSG);
				}
				Dictionary contro2 = dictionaryService.get(json.optInt("contro2"));
				if (contro2 != null) {
					json.put("contro2", contro2.getItemName());
				} else {
					json.put("contro2", GlobalUnit.NULLMSG);
				}
				mapList.add(json);
			}
			Collections.reverse(mapList);//倒序
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mapList", mapList);
			return new ApiReturn(200, "查询预警反馈记录成功", map);
		} catch (Exception e) {
			LOGGER.error("查询预警反馈记录失败", e);
			return new ApiReturn(400, "查询预警反馈记录失败");
		}
	}

	/**
	 * 预警签收
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月9日 下午3:28:30
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
			boolean flag = controlPersonAlarmService.isPower(user, loginUserUnit, id, "sign");
			if (!flag) {
				return new ApiReturn(400, "无权限进行此操作");
			}
			ControlPersonAlarm controlPersonAlarm = controlPersonAlarmService.get(id);
			if (StringUtils.isNotBlank(controlPersonAlarm.getSignName()) || "2".equals(controlPersonAlarm.getSignName())) {
				return new ApiReturn(400, "该预警已签收");
			}
			controlPersonAlarm.setSignName(user.getPoliceName());
			controlPersonAlarm.setSignPrikey(user.getId());
			controlPersonAlarm.setSignStatus(2);
			controlPersonAlarm.setSignTime(DateFormat.getDateTimeInstance().format(new Date()));
			controlPersonAlarmService.updateInclude(controlPersonAlarm, new String[] { "signName", "signPrikey", "signStatus", "signTime" }, null);
			
			// 插入日志
			operationLogService.insertOperationLogs(user, 4, request.getRequestURI().toString(), user.getPoliceName() + "签收了预警",
					LogUtil.getData(request.getParameterMap()));
			return new ApiReturn(200, "签收成功");
		} catch (Exception e) {
			LOGGER.error("预警签收失败", e);
			return new ApiReturn(400, "预警签收失败");
		}
	}

	/**
	 * 查询重点人基本信息
	 * 
	 * @author qinzhitian<br>
	 * @date 2018年1月10日 上午10:05:21
	 */
	@RequestMapping("/baseInformation")
	@ResponseBody
	public ApiReturn baseInformation(HttpServletRequest request, Integer id) {
		try {
			if (id == null) {
				return new ApiReturn(400, "预警id不能为空");
			}
			// 获取当前登录用户
			//User user = (User) request.getSession().getAttribute("user");
			// 根据id获取重点人员数据
			Map<String, Object> map = controlPersonAlarmService.findPersonById(id);
			map.put("id", id);
			// 插入日志
			return new ApiReturn(200, "查询重点人基本信息成功", map);
		} catch (Exception e) {
			LOGGER.error("查询重点人基本信息失败", e);
			return new ApiReturn(400, "查询重点人基本信息失败");
		}
	}
	
	/**
	 * 查询预警提醒列表
	 * @author Senghor<br>
	 * @param type0=待签收预警1=待反馈预警
	 * @date 2018年4月10日 下午3:44:07
	 */
	@RequestMapping("/alarmAlertList")
	@ResponseBody
	public ApiReturn alarmAlertList(Page page, HttpServletRequest request,String type) {
		try {
			// 获取登录用户
			User loginUser = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			Map<String, Object> map = controlPersonAlarmService.findByTypeApiAlarmList(page, loginUserUnit, loginUser, type);
			//插入操作日志
			//operationLogService.insertOperationLogs(loginUser, 4, request.getRequestURI().toString(), loginUser.getPoliceName()+"查看了预警信息列表",LogUtil.getData(request.getParameterMap()));
			return new ApiReturn(200, "查询预警信息列表成功", map);
		} catch (Exception e) {
			LOGGER.error("查询预警信息列表失败", e);
			return new ApiReturn(400, "查询预警信息列表失败");
		}
	}
}
