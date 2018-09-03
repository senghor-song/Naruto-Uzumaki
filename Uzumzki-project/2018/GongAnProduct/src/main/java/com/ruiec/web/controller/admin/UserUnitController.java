package com.ruiec.web.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.dto.UserUnitDTO;
import com.ruiec.web.entity.TaskDetails;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.entity.UserUnit;
import com.ruiec.web.interceptor.MySessionContext;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.TaskDetailsService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.service.UserUnitService;

/**
 * 管理员管理控制器
 * 
 * @author 贺云<br>
 * @date 2017年12月5日 下午3:47:21
 */
@Controller("userUnitController")
@RequestMapping(value = "/admin/manager")
public class UserUnitController extends BaseAdminController {
	private static final Logger LOGGER = Logger.getLogger(UserUnitController.class);
	@Resource
	private UserUnitService userUnitService;
	@Resource
	private UserService userService;
	@Resource
	private OperationLogService operationLogService;
	@Resource
	private UnitService unitService;
	@Resource
	private TaskDetailsService taskDetailsService;

	/**
	 * 查询管理员列表
	 * 
	 * @author 贺云<br>
	 * @date 2017年12月7日 上午9:34:48
	 */
	@RequestMapping(value = "/list")
	public String adminList(Page page, Model model, UserUnitDTO dto) {
		userUnitService.findUserUnitList(page, dto);
		model.addAttribute("page", page);
		model.addAttribute("dto", dto);
		return "/admin/userUnit/list";
	}

	/**
	 * 新增管理员功能
	 * 
	 * @author 贺云<br>
	 * @date 2017年12月12日 上午9:09:38
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public JsonReturn save(Integer userId, Integer unitId, HttpServletRequest request) {
		JsonReturn jsonReturn = null;
		try {
			// 获取当前登录用户
			User loginUser = (User) request.getSession().getAttribute("user");
			jsonReturn = userUnitService.saveUserUnit(userId, unitId, loginUser);
			if (jsonReturn.getCode() == 200) {
				try {
					User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, userId);
					// 插入操作日志
					operationLogService.insertOperationLogs(loginUser, 1, request.getRequestURL().toString(),
							loginUser.getPoliceName() + "新增了" + user.getPoliceName() + "管理员", LogUtil.getData(request.getParameterMap()));
					MySessionContext.DelSession(MySessionContext.getSession(user.getIsOnline()));
				} catch (Exception e) {
					LOGGER.error("插入日志失败!" + e);
				}
			}
		} catch (Exception e) {
			return new JsonReturn(400, "创建失败");
		}
		return jsonReturn;
	}

	/**
	 * 管理员删除
	 * 
	 * @author 贺云<br>
	 * @date 2017年12月9日 下午3:40:44
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public JsonReturn delete(Model model, Integer[] ids, HttpServletRequest request) {
		User loginUser = new User();
		try {
			// 获取当前登录用户
			loginUser = (User) request.getSession().getAttribute("user");
			if (ids != null && ids.length > 0) {
				UserUnit userUnitOne = userUnitService.get(ids[0]);
				for (int i = 0; i < ids.length; i++) {
					UserUnit userUnit = userUnitService.get(ids[i]);
					RedisUtil.delRedis(GlobalUnit.USER_UNIT_MAP, userUnit.getUser().getId().toString());
				}
				userUnitService.delete(ids);
				MySessionContext.DelSession(MySessionContext.getSession(userUnitOne.getUser().getIsOnline()));
			}
		} catch (Exception e) {
			return new JsonReturn(400, "删除失败");
		}
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(loginUser, 2, request.getRequestURL().toString(), loginUser.getPoliceName() + "新增了管理员",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入日志失败!" + e);
		}
		return new JsonReturn(200, "删除成功");
	}

	/**
	 * 编辑管理员初始化页面
	 * 
	 * @author 贺云<br>
	 * @date 2017年12月12日 上午9:09:38
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public JsonReturn update(Integer id) {
		try {
			UserUnit userUnit = userUnitService.get(id);
			if (userUnit == null) {
				return new JsonReturn(400, "未查询到此信息");
			}
			JSONObject json = new JSONObject();
			json.put("id", id);
			User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, userUnit.getUser().getId());
			json.put("userId", user.getId());
			json.put("userName", user.getPoliceName());
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, userUnit.getUnit().getId());
			json.put("unitId", unit.getId());
			json.put("unitName", unit.getUnitName());
			return new JsonReturn(200, "OK", json);
		} catch (Exception e) {
			return new JsonReturn(400, "查询失败");
		}
	}

	/**
	 * 修改管理员
	 * 
	 * @author 贺云<br>
	 * @date 2017年12月12日 上午9:09:38
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public JsonReturn edit(Integer id, Integer unitId, HttpServletRequest request) {
		User loginUser = new User();
		try {
			// 获取当前登录用户
			loginUser = (User) request.getSession().getAttribute("user");
//			JsonReturn jsonReturn = userUnitService.updateUserUnit(id, unitId);
			if (id == null) {
				return new JsonReturn(400, "操作id不能为NULL");
			}
			if (unitId == null) {
				return new JsonReturn(400, "单位unitId不能为NULL");
			}
			UserUnit updaUserUnit = userUnitService.get(id);
			if (updaUserUnit == null) {
				return new JsonReturn(400, "操作项不存在");
			}
			if (!updaUserUnit.getUnit().getId().equals(id)) {
				User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, updaUserUnit.getUser().getId());
				MySessionContext.DelSession(MySessionContext.getSession(user.getIsOnline()));
				Unit userUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, user.getUnitId());
				// 要管理的单位
				Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, unitId);
				if (!"city".equals(unit.getUnitRank())) {
					// 分县局民警添加管理员身份需要判断管理的单位是否是所管理的
					if ("area".equals(userUnit.getUnitRank())) {
						if ("area".equals(unit.getUnitRank())) {
							if (!unitId.equals(userUnit.getId())) {
								return new JsonReturn(400, "该警员不能管理该单位");
							}
						} else {
							List<Integer> idsList = unitService.findSonIds(unit.getIsPoliceSection() == 1 ? unit.getPoliceTypesParentId() : unit.getParentId());
							if (!isUnitId(idsList, unitId)) {
								return new JsonReturn(400, "该警员不能管理该单位");
							}
						}
					} else if ("town".equals(userUnit.getUnitRank())) {
						// 派出所民警添加管理员
						// 要管理的单位区级时
						if ("area".equals(unit.getUnitRank())) {
							if (userUnit.getIsPoliceSection() == 1) {
								// 判断是否是警员所属单位的上级
								if (!(unitId.equals(userUnit.getPoliceTypesParentId()) || unitId.equals(userUnit.getParentId()))) {
									return new JsonReturn(400, "该警员不能管理该单位");
								}
							} else {
	
								// 判断是否是警员所属单位的上级
								if (!unitId.equals(userUnit.getParentId())) {
									return new JsonReturn(400, "该警员不能管理该单位");
								}
							}
						} else {
							if (!unitId.equals(userUnit.getId())) {
								return new JsonReturn(400, "该警员不能管理该单位");
							}
						}
					}
				}
				// 要管理的单位
				updaUserUnit.setUnit(unit);
				// 修改管理员数据
				userUnitService.update(updaUserUnit);
				List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
				// 存储当前的管理员对象
				Map<String, Object> userUnitMap = new HashMap<String, Object>();
				userUnitMap.put("id", unit.getId());// 单位id
				userUnitMap.put("unitRank", unit.getUnitRank());// 单位级别
				userUnitMap.put("unitName", unit.getUnitName());// 单位名称
				listMaps.add(userUnitMap);
				RedisUtil.addRedis(GlobalUnit.USER_UNIT_MAP, updaUserUnit.getUser().getId().toString(), listMaps);
				//需要将该管理员签收过的任务全部改为未签收状态
	
				DetachedCriteria td = DetachedCriteria.forClass(TaskDetails.class);
				DetachedCriteria t = td.createCriteria("task");
				t.add(Restrictions.ne("status", "3"));
				td.add(Restrictions.eq("receiveUserId", updaUserUnit.getUser().getId()));// 所管理的任务
				List<TaskDetails> taskDetailsList = taskDetailsService.findList(td, null, null, null);
				for (int i = 0; i < taskDetailsList.size(); i++) {
					TaskDetails taskDetails = taskDetailsList.get(i);
					taskDetails.setReceiveUserId(null);
					taskDetails.setFeedbackStatus(0);
					taskDetails.setFeedbackDetails(null);
					taskDetailsService.updateInclude(taskDetails, new String[]{"feedbackStatus","receiveUserId","feedbackDetails"}, null);
				}
			}
			try {
				// 插入操作日志
				operationLogService.insertOperationLogs(loginUser, 2, request.getRequestURL().toString(), loginUser.getPoliceName() + "修改了管理员",
						LogUtil.getData(request.getParameterMap()));
			} catch (Exception e) {
				LOGGER.error("插入日志失败!" + e);
			}
			return new JsonReturn(200, "修改成功");
		} catch (Exception e) {
			LOGGER.error("添加管理异常：" , e);
			return new JsonReturn(400, "添加管理失败");
		}
	}

	/**
	 * 根据单位id查询管理员是否存在
	 * 
	 * @author qinzhitian<br>
	 * @date 2017年12月9日 下午1:35:44
	 */
	@RequestMapping(value = "/checkById")
	@ResponseBody
	public JsonReturn checkById(Integer id) {
		try {
			if (id != null && id != 0) {
				// 1、查询管理员id
				boolean result = this.userUnitService.checkById(id);
				if (!result) {
					return new JsonReturn(400, "该单位还未设置管理员");
				}
			}
		} catch (Exception e) {
			LOGGER.error("下发预警失败!" + e);
			return new JsonReturn(400, "管理员查询失败");
		}
		return new JsonReturn(200, "ok");
	}
	
	/**
	 * 判断所管理的单位中是否包含该单位
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月10日 上午10:49:49
	 */
	private boolean isUnitId(List<Integer> list, Integer unitId) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(unitId)) {
				return true;
			}
		}
		return false;
	}
}
