package com.ruiec.web.controller.admin;

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
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.entity.ControlPersonInstructi;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.ControlPersonAlarmService;
import com.ruiec.web.service.ControlPersonInstructiService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.OperationLogService;

import net.sf.json.JSONObject;

/**
 * 重点人员预警指令控制器
 * 
 * @author Senghor<br>
 * @date 2017年12月1日 上午9:05:41
 */
@Controller("controlPersonInstructiController")
@RequestMapping("/admin/controlPersonInstructi")
public class ControlPersonInstructiController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(ControlPersonInstructiController.class);

	@Resource
	private ControlPersonInstructiService controlPersonInstructiService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private ControlPersonAlarmService controlPersonAlarmService;
	@Resource
	private OperationLogService operationLogService;

	/**
	 * 下发初始化
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月6日 下午8:23:45
	 */
	@RequestMapping(value = "issuedInit")
	public String issuedInit(HttpServletRequest request, Model model, Integer id) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			// 下发单位或警员
			Map<String, List<Map<String, Object>>> map = controlPersonInstructiService.findIssued(user);
			List<Map<String, Object>> unitList = map.get("unitList");
			List<Map<String, Object>> userList = map.get("userList");
			model.addAttribute("userList", userList);
			model.addAttribute("unitList", unitList);
			model.addAttribute("id", id);
		} catch (Exception e) {
			LOGGER.error("下发初始化失败" + e);
		}
		return "/admin/controlPersonInstructi/issued";
	}

	/**
	 * 下发指令初始化
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月6日 下午8:23:45
	 * @param aid
	 *            重点人预警id
	 * @param id
	 *            预警指令id
	 */
	@RequestMapping(value = "commandIssuedInit")
	public String commandIssuedInit(HttpServletRequest request, Model model, Integer aid, Integer id) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			// 下发单位或警员
			Map<String, List<Map<String, Object>>> map = controlPersonInstructiService.findCommandIssued(user, id);
			List<Map<String, Object>> unitList = map.get("unitList");
			List<Map<String, Object>> userList = map.get("userList");
			model.addAttribute("userList", userList);
			model.addAttribute("unitList", unitList);
			model.addAttribute("id", aid);
		} catch (Exception e) {
			LOGGER.error("下发指令初始化失败" + e);
		}
		return "/admin/controlPersonInstructi/issued";
	}

	/**
	 * 下发指令初始化
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月6日 下午8:23:45
	 */
	@RequestMapping(value = "commandIssued")
	@ResponseBody
	public JsonReturn commandIssuedInit(HttpServletRequest request, Integer id) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			// 下发单位或警员
			Map<String, List<Map<String, Object>>> map = controlPersonInstructiService.findCommandIssued(user, id);
			List<Map<String, Object>> unitList = map.get("unitList");
			List<Map<String, Object>> userList = map.get("userList");
			if (unitList.size() > 0 || userList.size() > 0) {
				return new JsonReturn(200, "");
			}
		} catch (Exception e) {
			LOGGER.error("下发初始化失败" + e);
		}
		return new JsonReturn(400, "当前无下级单位或警员可下发");
	}

	/**
	 * 下发初始化
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月27日 下午8:23:45
	 */
	@RequestMapping(value = "isIssued")
	@ResponseBody
	public JsonReturn issuedInit(HttpServletRequest request) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			// 下发单位或警员
			Map<String, List<Map<String, Object>>> map = controlPersonInstructiService.findIssued(user);
			List<Map<String, Object>> unitList = map.get("unitList");
			List<Map<String, Object>> userList = map.get("userList");
			if (unitList.size() > 0 || userList.size() > 0) {
				return new JsonReturn(200, "");
			}
		} catch (Exception e) {
			LOGGER.error("下发初始化失败" + e);
		}
		return new JsonReturn(400, "当前无下级单位或警员可下发");
	}

	/**
	 * 下发预警指令
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月7日 下午9:44:24
	 * @param ids
	 *            单位id数组
	 * @param userIds
	 *            警员id数组
	 * @param id
	 *            预警指令id
	 * @param content
	 *            下发内容
	 */
	@RequestMapping(value = "/issued")
	@ResponseBody
	public JsonReturn issued(HttpServletRequest request, Integer[] ids, Integer[] userIds, Integer id, String type, String content) {
		try {
			// 取出用户
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			JsonReturn jsonReturn = null;
			if (StringUtils.isBlank(content)) {
				return new JsonReturn(400, "下发内容不能为空");
			}
			if (content.length() > 100) {
				return new JsonReturn(400, "执行内容超出最大长度");
			}
			if ((ids != null && ids.length > 0) || (userIds != null && userIds.length > 0)) {
				// 新增下发预警指令
				jsonReturn = controlPersonInstructiService.issued(ids, id, user, loginUserUnit, userIds, content);
			} else {
				return new JsonReturn(400, "下发单位不能为空");
			}
			jsonReturn.setUrl("/admin/controlPersonInstructi/issuedInstruction.shtml");
			// 插入日志
			operationLogService.insertOperationLogs(user, 2, request.getRequestURL().toString(), user.getPoliceName() + "下发预警",
					LogUtil.getData(request.getParameterMap()));
			return jsonReturn;
		} catch (Exception e) {
			LOGGER.error("下发预警失败!" + e);
			return new JsonReturn(400, "下发预警失败");
		}
	}

	/**
	 * 签收指令
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月8日 下午8:47:16
	 */
	@RequestMapping(value = "/sign")
	@ResponseBody
	public JsonReturn sign(HttpServletRequest request, Integer id, Integer state) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			if (id == null || state == null || user == null || loginUserUnit == null) {
				return new JsonReturn(400, "签收失败");
			}
			boolean flag = controlPersonInstructiService.isPower(user, loginUserUnit, id, 1);
			if (!flag) {
				return new JsonReturn(400, "权限不足");
			}
			JsonReturn jsonReturn = controlPersonInstructiService.updateInstructionsState(id, state);
			if (jsonReturn.getCode() == 200) {
				// 插入操作记录
				operationLogService.insertOperationLogs(user, 3, request.getRequestURL().toString(), user.getPoliceName() + "签收预警",
						LogUtil.getData(request.getParameterMap()));
			}
			return jsonReturn;
		} catch (Exception e) {
			LOGGER.error("签收失败!" + e);
			return new JsonReturn(400, "签收失败");
		}
	}

	/**
	 * 审核指令
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月8日 下午8:47:16
	 */
	@RequestMapping(value = "/audit")
	@ResponseBody
	public JsonReturn audit(HttpServletRequest request, Integer id, Integer state, String content) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			if (id == null || user == null || loginUserUnit == null) {
				return new JsonReturn(400, "审核失败");
			}
			if (state == 4 && StringUtils.isBlank(content)) {
				return new JsonReturn(400, "审核失败, 请填写拒绝理由！");
			} else if (state == 4 && StringUtils.isBlank(content) && content.length() > 100) {
				return new JsonReturn(400, "拒绝理由不得超过100个字！");
			} else {
				// 判断权限
				boolean flag = controlPersonInstructiService.isPower(user, loginUserUnit, id, 3);
				if (!flag) {
					return new JsonReturn(400, "权限不足");
				}
				JsonReturn jsonReturn = controlPersonInstructiService.audit(id, state, content);
				if (jsonReturn.getCode() == 200) {
					// 插入日志
					operationLogService.insertOperationLogs(user, 3, request.getRequestURL().toString(), user.getPoliceName() + "审核预警",
							LogUtil.getData(request.getParameterMap()));
				}
				return jsonReturn;
			}
		} catch (Exception e) {
			LOGGER.error("审核失败!" + e);
			return new JsonReturn(400, "审核失败");
		}
	}

	/**
	 * 已下发预警指令
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月7日 上午7:53:29
	 */
	@RequestMapping("/issuedInstruction")
	public String issuedInstruction(Model model, HttpSession session, HttpServletRequest request, Page page) {
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (null != user) {
			// 根据登录人的ID查询下发指令
			DetachedCriteria cpi = DetachedCriteria.forClass(ControlPersonInstructi.class);
			cpi.createCriteria("controlPersonAlarm", JoinType.LEFT_OUTER_JOIN);
			DetachedCriteria cp = cpi.createCriteria("controlPerson", JoinType.LEFT_OUTER_JOIN);
			// 尚未被逻辑删除的重点人
			cp.add(Restrictions.eq("isDelete", 0));
			cp.setFetchMode("controlPersonTypes", FetchMode.SELECT);
			cpi.add(Restrictions.eq("issuedPolicePrikey", user.getId()));
			controlPersonInstructiService.findByPage(page, cpi);
		}
		model.addAttribute("page", page);
	/*	operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看已下发预警列表",
				LogUtil.getData(request.getParameterMap()));*/
		return "/admin/controlPersonInstructi/issuedlist";
	}

	/**
	 * 待签收预警(指令)（收到的预警指令）
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月7日 上午7:53:29
	 */
	@RequestMapping("/receivedInstruction")
	public String receivedInstruction(Model model, HttpServletRequest request, HttpSession session, Page page) {
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (null != user) {
			// 根据登录人的ID查询接受指令
			DetachedCriteria cpi = DetachedCriteria.forClass(ControlPersonInstructi.class);
			cpi.createCriteria("controlPersonAlarm");
			DetachedCriteria cp = cpi.createCriteria("controlPerson", JoinType.LEFT_OUTER_JOIN);
			// 尚未被逻辑删除的重点人
			cp.add(Restrictions.eq("isDelete", 0));
			cp.setFetchMode("controlPersonTypes", FetchMode.SELECT);
			cpi.add(Restrictions.eq("instructionRecipientPrikey", user.getId()));
			controlPersonInstructiService.findByPage(page, cpi);
		}
		model.addAttribute("page", page);
		/*operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看待签收预警列表",
				LogUtil.getData(request.getParameterMap()));*/
		return "/admin/controlPersonInstructi/receivedlist";
	}

	/**
	 * 下发签收记录(指令)
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月7日 上午7:53:29
	 */
	@RequestMapping("/instructionRecord")
	public String instructionRecord(Integer id, Integer pid, Integer iid, Model model, Page page) {
		// User user = (User) request.getSession().getAttribute("user");
		// 根据关联预警的ID查询指令
		DetachedCriteria cpi = DetachedCriteria.forClass(ControlPersonInstructi.class);
		DetachedCriteria cpa = cpi.createCriteria("controlPersonAlarm");
		DetachedCriteria cp = cpi.createCriteria("controlPerson", JoinType.LEFT_OUTER_JOIN);
		// 尚未被逻辑删除的重点人
		cp.add(Restrictions.eq("isDelete", 0));
		cp.setFetchMode("controlPersonTypes", FetchMode.SELECT);
		cpa.add(Restrictions.eq("id", id));
		controlPersonInstructiService.findByPage(page, cpi);
		model.addAttribute("page", page);
		model.addAttribute("id", id);
		model.addAttribute("pid", pid);
		model.addAttribute("iid", iid);
		/*operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看下发签收记录列表",
				LogUtil.getData(request.getParameterMap()));*/
		return "/admin/controlPersonInstructi/instructionRecord";
	}

	/**
	 * 跳转反馈页面
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月9日 下午4:05:43
	 */
	@RequestMapping("/feedBackInit")
	public String feedBackInit(Model model, Integer id) {
		if (id == null) {
			throw new RuntimeException("预警指令ID不能为空");
		}
		// 查询字典稳控大类
		String name = GlobalUnit.STEADY_ITEMCODE;
		List<Map<String, Object>> list = dictionaryService.findByItemCode(name, 0);
		if (list.size() > 0) {
			// 查询该字典是否有小类，如果有则添加
			List<Map<String, Object>> steady2 = dictionaryService.findSubSet(new Integer(list.get(0).get("id").toString()), null);
			if (steady2.size() > 0) {
				model.addAttribute("steady2", steady2);
			}
			model.addAttribute("steady1", list);
		}
		ControlPersonInstructi cpa = controlPersonInstructiService.get(id);
		model.addAttribute("id", id);
		model.addAttribute("cpa", cpa);
		return "/admin/controlPersonInstructi/feedBack";
	}

	/**
	 * 跳转反馈详情页
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月13日 上午11:19:38
	 */
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request, Model model, Integer id, Integer type) {
		try {
			if (id == null) {
				throw new RuntimeException("预警指令ID不能为空");
			}
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			boolean flag = controlPersonInstructiService.isPower(user, loginUserUnit, id, 4);
			if (!flag) {
				model.addAttribute("msg", "无权限操作");
				if (type == 1) {
					return "/admin/controlPersonInstructi/instructionRecord";
				} else {
					return "/admin/controlPersonInstructi/receivedlist";
				}
			}
			operationLogService.insertOperationLogs(user, 4, request.getRequestURL().toString(), user.getPoliceName() + "查看反馈详情",
					LogUtil.getData(request.getParameterMap()));
			model.addAttribute("id", id);
		} catch (Exception e) {
			LOGGER.error("获取反馈信息失败" + e);
		}
		return "/admin/controlPersonInstructi/feedBackDetail";
	}

	/**
	 * 获取反馈信息
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月13日 上午11:19:38
	 */
	@RequestMapping("/feedBackDetail")
	@ResponseBody
	public JsonReturn feedBackDetail(HttpServletRequest request, Integer id) {
		try {
			if (id == null) {
				throw new RuntimeException("预警指令ID不能为空");
			}
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			boolean flag = controlPersonInstructiService.isPower(user, loginUserUnit, id, 4);
			if (!flag) {
				return new JsonReturn(400, "权限不足");
			}
			List<JSONObject> jsons = controlPersonInstructiService.findFeedBackDetail(id);
			return new JsonReturn(200, "ok", jsons);
		} catch (Exception e) {
			LOGGER.error("反馈信息查询失败" + e);
			return new JsonReturn(400, "error");
		}
	}

	/**
	 * 跳转再次反馈页面
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月13日 上午11:19:38
	 * @param id
	 *            预警指令id
	 * @param state
	 *            预警指令状态
	 */
	@RequestMapping("/feedBackAgain")
	public String feedBackAgain(Model model, Integer id, Integer state) {
		try {
			if (id == null) {
				throw new RuntimeException("预警指令ID不能为空");
			}
			// 查询字典稳控大类
			String name = GlobalUnit.STEADY_ITEMCODE;
			List<Map<String, Object>> list = dictionaryService.findByItemCode(name, 0);
			if (list.size() > 0) {
				// 查询该字典是否有小类，如果有则添加
				List<Map<String, Object>> steady2 = dictionaryService.findSubSet(new Integer(list.get(0).get("id").toString()), null);
				if (steady2.size() > 0) {
					model.addAttribute("steady2", steady2);
				}
				model.addAttribute("steady1", list);
			}			
			model.addAttribute("id", id);
			model.addAttribute("state", state);
		} catch (Exception e) {
			LOGGER.error("反馈信息查询失败" + e);
		}
		return "/admin/controlPersonInstructi/feedBackAgain";
	}

	/**
	 * 获取最后一次反馈信息
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月13日 上午11:19:38
	 */
	@RequestMapping("/lastFeedBack")
	@ResponseBody
	public JsonReturn findLastFeedBack(HttpServletRequest request, Integer id) {
		try {
			if (id == null) {
				return new JsonReturn(400, "预警指令ID不能为空");
			}
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			boolean flag = controlPersonInstructiService.isPower(user, loginUserUnit, id, 4);
			if (!flag) {
				return new JsonReturn(400, "权限不足");
			}
			JSONObject jsonObject = controlPersonInstructiService.findLastFeedBack(id);
			JsonReturn jsonReturn = new JsonReturn(200, "ok", jsonObject);
			jsonReturn.setUrl("/admin/controlPersonInstructi/receivedInstruction.shtml");
			return jsonReturn;
		} catch (Exception e) {
			LOGGER.error("反馈信息查询失败" + e);
			return new JsonReturn(400, "error");
		}
	}

	/**
	 * 提交反馈信息
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月9日 下午4:05:43
	 */
	@RequestMapping("/feedBack")
	@ResponseBody
	public JsonReturn feedBack(HttpServletRequest request, String jsonStr, Integer id) {
		// 获取用户信息
		try {
			if (id == null) {
				return new JsonReturn(400, "预警指令ID不能为空");
			}
			if (StringUtils.isBlank(jsonStr)) {
				return new JsonReturn(400, "反馈信息不能为空");
			}
			User user = (User) request.getSession().getAttribute("user");
			LoginUserUnit loginUserUnit = (LoginUserUnit) request.getSession().getAttribute("loginUserUnit");
			boolean flag = controlPersonInstructiService.isPower(user, loginUserUnit, id, 2);
			if (!flag) {
				return new JsonReturn(400, "权限不足");
			}
			JsonReturn jsonReturn = controlPersonInstructiService.feedBack(id, jsonStr, user);
			if (jsonReturn.getCode() == 200) {
				// 插入操作记录
				operationLogService.insertOperationLogs(user, 1, request.getRequestURL().toString(), user.getPoliceName() + "提交反馈信息",
						LogUtil.getData(request.getParameterMap()));
			}
			return jsonReturn;
		} catch (Exception e) {
			LOGGER.error("反馈失败" + e);
			return new JsonReturn(400, "反馈失败");
		}
	}
}
