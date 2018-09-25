package com.ruiec.web.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.server.support.query.Sort;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.dto.FeedbackDTO;
import com.ruiec.web.dto.TaskDTO;
import com.ruiec.web.entity.Message;
import com.ruiec.web.entity.Task;
import com.ruiec.web.entity.TaskDetails;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.MessageService;
import com.ruiec.web.service.OperationLogService;
import com.ruiec.web.service.TaskDetailsService;
import com.ruiec.web.service.TaskService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.util.RuiecDateUtils;
import com.ruiec.web.util.RuiecStringUtil;

/**
 * 合成研判控制器
 * 
 * @author Senghor<br>
 * @date 2018年1月6日 上午10:54:13
 */
@Controller("syntheticAnalysisController")
@RequestMapping("/admin/syntheticAnalysis")
public class SyntheticAnalysisController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(SyntheticAnalysisController.class);

	@Resource
	private OperationLogService operationLogService;
	@Resource
	private TaskService taskService;
	@Resource
	private TaskDetailsService taskDetailsService;
	@Resource
	private UnitService unitService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private MessageService messageService;
	@Resource(name="taskExecutor")
	private TaskExecutor taskExecutor;

	/**
	 * 合成研判我发起的任务列表
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月6日 上午11:02:44
	 */
	@RequestMapping("/taskList")
	public String taskList(Model model, Page page, TaskDTO taskDTO, HttpServletRequest request) {
		// 初始化session中的数据
		taskDTO.init(request);
		DetachedCriteria t = DetachedCriteria.forClass(Task.class);
		t.setFetchMode("taskDetails", FetchMode.SELECT);
		// 任务发起人
		page.add(Filter.eq("originatorUserId", taskDTO.getUser().getId()));
		// 根据任务名称查询
		if (taskDTO.getTaskName() != null) {
			t.add(Restrictions.like("name", taskDTO.getTaskName(), MatchMode.ANYWHERE));
		}
		// 根据任务状态查询
		if (taskDTO.getTaskType() != null) {
			if (taskDTO.getTaskType()>3) {
				switch (taskDTO.getTaskType()) {
				case 4:
					page.add(Filter.eq("isCheck", 3));//待初审
					break;
				case 5:
					page.add(Filter.eq("isCheck", 2));//待复审
					t.add(Restrictions.isNotNull("checkUserId"));
					break;
				case 6:
					page.add(Filter.eq("isCheck", 0));//未通过的审核,即审核拒绝
					break;
				case 7:
					page.add(Filter.eq("isCheck", 2));//待审核
					t.add(Restrictions.isNull("checkUserId"));
					break;
				default:
					break;
				}
				page.add(Filter.ne("status", "3"));
			}else {
				page.add(Filter.eq("status", taskDTO.getTaskType().toString()));
			}
		}
		taskService.findByPage(page, t);
		model.addAttribute("page", page);
		model.addAttribute("taskDTO", taskDTO);
		return "/admin/syntheticAnalysis/taskList";
	}

	/**
	 * 合成研判我接收的任务列表
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月6日 上午11:02:44
	 */
	@RequestMapping("/myTaskList")
	public String myTaskList(Model model, Page page, TaskDTO taskDTO, HttpServletRequest request) {
		// 初始化session中的数据
		taskDTO.init(request);

		DetachedCriteria td = DetachedCriteria.forClass(TaskDetails.class);
		DetachedCriteria t = td.createCriteria("task");
		t.setFetchMode("taskDetails", FetchMode.SELECT);
		td.add(Restrictions.in("receiveUnitId", taskDTO.getLoginUserUnit().getUnitIds()));// 所管理的单位直属任务
		// 根据任务名称查询
		if (taskDTO.getTaskName() != null) {
			t.add(Restrictions.like("name", taskDTO.getTaskName(), MatchMode.ANYWHERE));
		}
		// 根据任务状态查询
		if (taskDTO.getTaskType() != null) {
			if (taskDTO.getTaskType() != 5) {
				page.add(Filter.eq("feedbackStatus", taskDTO.getTaskType()));
				t.add(Restrictions.ne("status", "3"));
			}else {
				t.add(Restrictions.eq("status", "3"));
			}
		}
		t.add(Restrictions.eq("isCheck", 1));
		taskDetailsService.findByPage(page, td);
		model.addAttribute("page", page);
		model.addAttribute("taskDTO", taskDTO);
		return "/admin/syntheticAnalysis/myTaskList";
	}

	/**
	 * 跳转到创建任务页面
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月8日 上午10:24:31
	 */
	@RequestMapping("/add")
	public String add(Model model, TaskDTO taskDTO, HttpServletRequest request) {
		// 初始化session中的数据
		taskDTO.init(request);
		if (taskDTO.getUrgency().equals(1) && (!taskDTO.getUser().getId().equals(1) || !taskDTO.getLoginUserUnit().getUnitId().equals(1))) {
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, taskDTO.getLoginUserUnit().getUnitId());
			List<Unit> unitList = new ArrayList<Unit>();
			unitList.add(unit);
			//紧急任务
			if (unit.getIsPoliceSection().equals(0)) {
				if (unit.getUnitRank().equals("area")) {
					// 分县局单位
					model.addAttribute("areaUnit", unitList);
				}else {
					// 派出所单位
					model.addAttribute("townUnit", unitList);
				}
			}else {
				if (unit.getUnitRank().equals("area")) {
					// 支队单位
					model.addAttribute("areaUnitPolice", unitList);
				}else {
					// 大队单位
					model.addAttribute("townUnitPolice", unitList);
				}
			}
		}else {
			//不紧急任务
			List<Filter> filters = new ArrayList<Filter>();
			List<Sort> sorts = new ArrayList<Sort>();
			filters.add(Filter.eq("parentId", 1));
			filters.add(Filter.eq("unitRank", "area"));
			filters.add(Filter.eq("isPoliceSection", 0));
			sorts.add(Sort.desc("createDate"));
			// 分县局单位
			model.addAttribute("areaUnit", unitService.findList(null, filters, sorts));
			filters.removeAll(filters);
			filters.add(Filter.eq("parentId", 1));
			filters.add(Filter.eq("unitRank", "area"));
			filters.add(Filter.eq("isPoliceSection", 1));
			// 支队单位
			model.addAttribute("areaUnitPolice", unitService.findList(null, filters, sorts));
		}
		// 任务类型
		model.addAttribute("taskTypes", dictionaryService.findByItemCode("taskType", 0));
		Task task = taskService.get(taskDTO.getTaskId());
		model.addAttribute("task", task);
		model.addAttribute("taskDTO", taskDTO);
		model.addAttribute("newDate", new Date());
		return "/admin/syntheticAnalysis/taskAdd";
	}

	/**
	 * 保存创建任务
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月8日 上午10:24:31
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonReturn save(Task task, TaskDTO taskDTO, HttpServletRequest request) {
		// 初始化session中的数据
		taskDTO.init(request);
		if(taskDTO.getUrgency().equals(1) && !taskDTO.getLoginUserUnit().getUrgency()){
			return new JsonReturn(400,"非法请求");
		}
		task.setHandleTime(RuiecDateUtils.getWeeHours(task.getHandleTime(), 1));
		// 处置时间没有或者小于当前时间则不创建
		if (task.getHandleTime() == null || task.getHandleTime().getTime() < new Date().getTime()) {
			return new JsonReturn(400, "请填写正确的处置时间");
		}
		if (taskDTO.getAreaUnitIds() == null) {
			return new JsonReturn(400, "请选择需要接收任务的单位");
		}
		JsonReturn jsonReturn = taskService.save(task, taskDTO);
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(taskDTO.getUser(), 1, request.getRequestURI().toString(), taskDTO.getUser().getPoliceName() + "创建了任务",
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入操作日志异常:" + e);
		}
		return jsonReturn;
	}

	/**
	 * 跳转到编辑任务页面
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月8日 上午10:24:31
	 */
	@RequestMapping("/edit")
	public String edit(Model model, TaskDTO taskDTO, HttpServletRequest request) {
		Task task = taskService.get(taskDTO.getTaskId());
		// 当前任务已审核通过则不能编辑
		if (task.getIsCheck() == 1) {
			return "/admin/syntheticAnalysis/taskList";
		}
		// 初始化session中的数据
		taskDTO.init(request);
		// 判断是否是发起人才能操作
		if (!task.getOriginatorUserId().equals(taskDTO.getUser().getId())) {
			return "/admin/syntheticAnalysis/taskList";
		}
		List<Filter> filters = new ArrayList<Filter>();
		List<Sort> sorts = new ArrayList<Sort>();
		filters.add(Filter.eq("parentId", 1));
		filters.add(Filter.eq("unitRank", "area"));
		filters.add(Filter.eq("isPoliceSection", 0));
		sorts.add(Sort.desc("createDate"));
		// 分县局单位
		model.addAttribute("areaUnit", unitService.findList(null, filters, sorts));
		filters.removeAll(filters);
		filters.add(Filter.eq("parentId", 1));
		filters.add(Filter.eq("unitRank", "area"));
		filters.add(Filter.eq("isPoliceSection", 1));
		// 支队单位
		model.addAttribute("areaUnitPolice", unitService.findList(null, filters, sorts));
		// 任务类型
		model.addAttribute("taskTypes", dictionaryService.findByItemCode("taskType", 0));
		model.addAttribute("task", task);
		if (task.getApprovalAttachment() != null) {
			model.addAttribute("fileUrls", task.getApprovalAttachment().split(";"));// 附件地址数组
		} else {
			model.addAttribute("fileUrls", null);// 附件地址数组
		}
		model.addAttribute("taskDTO", taskDTO);
		// 获取需要回显的单位数据
		List<TaskDetails> taskDetails = taskDetailsService.findList(null, Filter.eq("task", task), null, null, Fetch.alias("task", "taskAlias"));
		List<Integer> unitOne = new ArrayList<Integer>();// 一级选中单位数据
		List<Unit> unitTwo = new ArrayList<Unit>();// 二级选中单位数据
		for (int i = 0; i < taskDetails.size(); i++) {
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, taskDetails.get(i).getReceiveUnitId());
			if (GlobalUnit.CITY_UNIT_ID.equals(unit.getParentId()) || GlobalUnit.CITY_UNIT_ID.equals(unit.getPoliceTypesParentId()) || unit.getUnitRank().equals("area")) {
				unitOne.add(taskDetails.get(i).getReceiveUnitId());
			} else {
				unitTwo.add(unit);
			}
		}
		model.addAttribute("unitOne", unitOne);
		model.addAttribute("unitTwo", unitTwo);
		model.addAttribute("newDate", new Date());
		/*
		 * if (unitTwo!=null && unitTwo.size()>0) { Map<Integer, List<Integer>>
		 * map=new HashMap<Integer, List<Integer>>(); Unit unit =
		 * unitTwo.get(0); List<Integer> list = new ArrayList<Integer>(); for
		 * (int i = 0; i < unitTwo.size(); i++) { if
		 * (unitTwo.get(i).getParentId().equals(unit.getParentId())) { list =
		 * map.get(unitTwo.get(i).getParentId());
		 * list.add(unitTwo.get(i).getId());
		 * map.put(unitTwo.get(i).getParentId(), list); }else { list = new
		 * ArrayList<Integer>(); list.add(unitTwo.get(i).getId());
		 * map.put(unitTwo.get(i).getParentId(), list); } } }
		 */
		return "/admin/syntheticAnalysis/taskEdit";
	}

	/**
	 * 保存编辑的任务数据
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月8日 上午10:24:31
	 */
	@RequestMapping("/update")
	@ResponseBody
	public JsonReturn update(Task task, TaskDTO taskDTO, HttpServletRequest request) {
		// 初始化session中的数据
		taskDTO.init(request);
		if(taskDTO.getUrgency().equals(1) && !taskDTO.getLoginUserUnit().getUrgency()){
			return new JsonReturn(400,"非法请求");
		}
		task.setHandleTime(RuiecDateUtils.getWeeHours(task.getHandleTime(), 1));
		// 处置时间没有或者小于当前时间则不创建
		if (task.getHandleTime() == null || task.getHandleTime().getTime() < new Date().getTime()) {
			return new JsonReturn(400, "请填写正确的处置时间");
		}
		if (taskDTO.getAreaUnitIds() == null) {
			return new JsonReturn(400, "请选择需要接收任务的单位");
		}
		// 判断是否是发起人操作
		if (!taskService.isOriginateUser(taskDTO)) {
			return new JsonReturn(400, "没有权限");
		}
		JsonReturn jsonReturn = taskService.update(task, taskDTO);
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(taskDTO.getUser(), 3, request.getRequestURI().toString(),
					taskDTO.getUser().getPoliceName() + "编辑了" + task.getName() + "任务", LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入操作日志异常:" + e);
		}
		return jsonReturn;
	}

	/**
	 * 结束任务页面
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月8日 上午10:24:31
	 */
	@RequestMapping("/taskOver")
	public String taskOver(Model model, TaskDTO taskDTO, HttpServletRequest request) {
		// 初始化session中的数据
		taskDTO.init(request);
		// 判断是否是发起人操作
		if (!taskService.isOriginateUser(taskDTO)) {
			return "/admin/syntheticAnalysis/taskList";
		}
		model.addAttribute("taskDTO", taskDTO);
		return "/admin/syntheticAnalysis/taskOver";
	}

	/**
	 * 结束任务操作
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月8日 上午10:24:31
	 */
	@RequestMapping("/saveTaskOver")
	@ResponseBody
	public JsonReturn saveTaskOver(Task task, TaskDTO taskDTO, HttpServletRequest request) {
		// 初始化session中的数据
		taskDTO.init(request);
		// 判断是否是发起人操作
		if (!taskService.isOriginateUser(taskDTO)) {
			return new JsonReturn(400, "没有权限");
		}
		JsonReturn jsonReturn = taskService.saveTaskOver(task, taskDTO);
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(taskDTO.getUser(), 3, request.getRequestURI().toString(),
					taskDTO.getUser().getPoliceName() + "结束了" + task.getName() + "任务", LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入操作日志异常:" + e);
		}
		return jsonReturn;
	}

	/**
	 * 签收任务
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月8日 下午9:11:22
	 */
	@RequestMapping("/taskReceive")
	@ResponseBody
	public JsonReturn taskReceive(TaskDTO taskDTO, HttpServletRequest request) {
		// 主任务
		Task task = new Task();
		try {
			// 需要判断当前任务是否结束，结束则不执行操作
			if (taskDetailsService.isTaskOver(taskDTO)) {
				return new JsonReturn(400, "该任务已结束");
			}
			// 初始化session中的数据
			taskDTO.init(request);
			TaskDetails taskDetails = taskDetailsService.get(taskDTO.getTaskDetailsId(), null, Fetch.alias("task", "taskAlias"));
			if (taskDetails.getReceiveUserId() != null) {
				return new JsonReturn(400, "该任务已被签收");
			}
			// 需要判断当前任务当前用户是否能签收
			if (taskDetailsService.isOperationTaskDetails(taskDTO)) {
				// 判断主任务是否在进行中
				if (taskDetails.getTask().getStatus().equals("1")) {
					task.setId(taskDetails.getTask().getId());
					task.setStatus("2");
					// 修改状态为进行中
					taskService.updateInclude(task, new String[] { "status" }, null);
				}
				taskDetails.setFeedbackStatus(1);// 修改为已签收
				taskDetails.setReceiveUserId(taskDTO.getUser().getId());// 接受人ID
                taskDetails.setSignTime(new Date());
				// 修改为已签收状态
				taskDetailsService.updateInclude(taskDetails, new String[] { "feedbackStatus", "receiveUserId" }, null);
			} else {
				return new JsonReturn(400, "没有权限操作该任务");
			}
		} catch (Exception e) {
			LOGGER.error("操作修改异常:" + e);
			return new JsonReturn(400, "操作异常");
		}
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(taskDTO.getUser(), 3, request.getRequestURI().toString(),
					taskDTO.getUser().getPoliceName() + "签收了" + task.getName() + "任务", LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入操作日志异常:" + e);
		}
		// 任务id
		return new JsonReturn(200, "签收成功", "/admin/syntheticAnalysis/myTaskList.shtml");
	}

	/**
	 * 跳转到反馈页面
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月8日 下午9:11:22
	 */
	@RequestMapping("/openFeedback")
	public String openFeedback(Model model, TaskDTO taskDTO, HttpServletRequest request) {
		// 初始化session中的数据
		taskDTO.init(request);

		// 需要判断当前任务当前用户是否能反馈
		if (taskDetailsService.isOperationTaskDetails(taskDTO)) {
			// 任务id
			model.addAttribute("taskDetailsId", taskDTO.getTaskDetailsId());
			return "/admin/syntheticAnalysis/feedback";
		} else {
			return "redirect:/admin/syntheticAnalysis/myTaskList.shtml";
		}
	}

	/**
	 * 保存任务反馈信息
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月8日 下午9:11:22
	 */
	@RequestMapping("/saveFeedback")
	@ResponseBody
	public JsonReturn saveFeedback(TaskDTO taskDTO, FeedbackDTO feedbackDTO, HttpServletRequest request) {
		Task task = new Task();
		try {
			// 需要判断当前任务是否结束，结束则不执行操作
			if (taskDetailsService.isTaskOver(taskDTO)) {
				return new JsonReturn(400, "该任务已结束");
			}
			/*if (!validate(feedbackDTO)) {
				return new JsonReturn(400, "参数超出限制");
			}*/
			// 初始化session中的数据
			taskDTO.init(request);
			TaskDetails taskDetails = taskDetailsService.get(taskDTO.getTaskDetailsId(), null, Fetch.alias("task", "taskAlias"));
			// 需要判断当前任务当前用户是否能反馈
			if (taskDetails.getReceiveUserId().equals(taskDTO.getUser().getId())) {
				feedbackDTO.setCreateTime(RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(new Date()));// 反馈时间
				if (taskDTO.getFileUrls() != null && taskDTO.getFileUrls().length>10) {
					return new JsonReturn(400, "最多上传10个文件");
				}
				String fileUrls = RuiecStringUtil.pingString(taskDTO.getFileUrls(), ";");// 得到附件的上传地址字符串
				feedbackDTO.setFileUrl(fileUrls);// 得到附件的上传地址字符串
				feedbackDTO.setType(0);// 审核状态，默认未审核

				task = taskDetails.getTask();
				JSONArray jsonArray = new JSONArray();
				// 判断是否是第一次反馈
				if (!RuiecStringUtil.checkNull(taskDetails.getFeedbackDetails())) {
					// 再次反馈则追加反馈
					jsonArray = JSONArray.fromObject(taskDetails.getFeedbackDetails());
					FeedbackDTO fDto = (FeedbackDTO) JSONObject.toBean((JSONObject) jsonArray.get(jsonArray.size() - 1), FeedbackDTO.class);
					// 判断上一条反馈是否审核
					if (fDto.getType() == 0) {
						return new JsonReturn(400, "请等待您的反馈审核");
					}
				}
				// 转换成json数据保存
				jsonArray.add(feedbackDTO);
				taskDetails.setFeedbackDetails(jsonArray.toString());// 任务反馈信息
				taskDetails.setFeedbackStatus(2);// 状态变为待审核
				// 修改为待审核状态
				taskDetailsService.updateInclude(taskDetails, new String[] { "feedbackStatus", "feedbackDetails" }, null);
			} else {
				return new JsonReturn(400, "没有权限操作该任务");
			}
		} catch (Exception e) {
			LOGGER.error("操作修改异常:" + e);
			return new JsonReturn(400, "操作异常");
		}
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(taskDTO.getUser(), 3, request.getRequestURI().toString(),
					taskDTO.getUser().getPoliceName() + "反馈了" + task.getName() + "任务", LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入操作日志异常:" + e);
		}
		return new JsonReturn(200, "反馈成功");
	}

	/**
	 * 跳转到详情界面
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月9日 下午2:27:18
	 */
	@RequestMapping("/taskDetails")
	public String taskDetails(Model model, TaskDTO taskDTO, HttpServletRequest request) {
		// 初始化session中的数据
		taskDTO.init(request);
		model.addAttribute("taskDTO", taskDTO);
		Task task = taskService.get(taskDTO.getTaskId());
		boolean flag = isLook(taskDTO,task);
		if (flag) {
			return "redirect:/admin/syntheticAnalysis/taskList.shtml";
		}
		// 已审核才显示
		if (task.getIsCheck() == 1) {
			// 判断是否有人反馈
			for (int i = 0; i < task.getTaskDetails().size(); i++) {
				// 任务详情数据
				TaskDetails taskDetails = task.getTaskDetails().get(i);
				JSONArray jsonArray = JSONArray.fromObject(task.getTaskDetails().get(i).getFeedbackDetails());
				List<FeedbackDTO> feedbackDTOs = new ArrayList<FeedbackDTO>();
				for (int j = 0; j < jsonArray.size(); j++) {
					// 有反馈信息则显示出来
					FeedbackDTO feedbackDTO = (FeedbackDTO) JSONObject.toBean(jsonArray.getJSONObject(j), FeedbackDTO.class);
					// 空则循环下一条数据
					if (feedbackDTO == null) {
						continue;
					}
					// 任务发起人能看到所有的反馈
					if (taskDetails.getTask().getOriginatorUserId().equals(taskDTO.getUser().getId())) {
						// 判断该任务是否结束了
						if ("3".equals(task.getStatus())) {
							// 已审核数据
							if (feedbackDTO.getType() != 0) {
								feedbackDTOs.add(feedbackDTO);
							}
						} else {
							feedbackDTOs.add(feedbackDTO);
						}
					} else if (taskDetails.getReceiveUserId() != null && 
							(taskDetails.getReceiveUserId().equals(taskDTO.getUser().getId()) || taskDetails.getReceiveUnitId().equals(taskDTO.getLoginUserUnit().getUnitId()))) {
						// 发起人能看自己所有的反馈和所有已通过审核的反馈
						if (!taskDetails.getReceiveUserId().equals(taskDTO.getUser().getId())) {
							// 不是自己的反馈只显示已通过审核的数据
							if (feedbackDTO.getType() == 1) {
								feedbackDTOs.add(feedbackDTO);
							}
						} else {
							feedbackDTOs.add(feedbackDTO);
						}
					} else {
						// 任务结束则只显示已通过审核的数据
						if (feedbackDTO.getType() == 1) {
							feedbackDTOs.add(feedbackDTO);
						}
					}
				}
				// 保存反馈的数据
				taskDetails.setFeedbackDTO(feedbackDTOs);
				// 将解析的数据重新放入到任务中
				task.getTaskDetails().set(i, taskDetails);
			}
		} else if (task.getIsCheck() == 2) {
			// 2=需要分县局/市局审核
			// 判断是否是发起单位的上级单位才能审核
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, task.getOriginatorUnitId());
			if (isUnitId(taskDTO.getLoginUserUnit(), unit.getParentId(), unit.getPoliceTypesParentId())) {
				if (taskDTO.getLoginUserUnit().getUnitRank().getName().equals("area") && task.getCheckUserId() == null && unit.getUnitRank().equals("town")) {
					// 需要分县局,只有分县局能查看
					// 允许审核
					model.addAttribute("succeed", "succeed");
				}
				if (taskDTO.getLoginUserUnit().getUnitRank().getName().equals("city") && task.getCheckUserId() == null && unit.getUnitRank().equals("area")) {
					// 需要市局审核,只有市局能查看
					// 允许审核
					model.addAttribute("succeed", "succeed");
				}
				if (taskDTO.getLoginUserUnit().getUnitRank().getName().equals("city") && task.getCheckUserId() != null && unit.getUnitRank().equals("town")) {
					// 需要市局再次审核,只有市局能查看
					// 允许审核
					model.addAttribute("succeed", "succeed");
				}
			}
		} else if (task.getIsCheck() == 3) {
			// 3=需要分县局审核
			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, task.getOriginatorUnitId());
			if (isUnitId(taskDTO.getLoginUserUnit(), unit.getParentId(), unit.getPoliceTypesParentId()) && taskDTO.getLoginUserUnit().getUnitRank().getName().equals("area")) {
				// 允许审核
				model.addAttribute("succeed", "succeed");
			}
		}
		if (task.getApprovalAttachment() != null) {
			model.addAttribute("fileUrls", task.getApprovalAttachment().split(";"));// 附件地址数组
		} else {
			model.addAttribute("fileUrls", null);// 附件地址数组
		}
		model.addAttribute("task", task);
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(taskDTO.getUser(), 4, request.getRequestURI().toString(),
					taskDTO.getUser().getPoliceName() + "查看了" + task.getName() + "任务详情", LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入操作日志异常:" + e);
		}
		return "/admin/syntheticAnalysis/taskDetails";
	}

	/**
	 * 任务审核界面
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月11日 上午9:49:40
	 */
	@RequestMapping("/isCheck")
	public String isCheck(Model model, TaskDTO taskDTO) {
		model.addAttribute("taskDTO", taskDTO);
		return "/admin/syntheticAnalysis/isCheck";
	}

	/**
	 * 审核任务接口
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月10日 下午9:15:11
	 */
	@RequestMapping("/saveIsCheck")
	@ResponseBody
	public JsonReturn saveIsCheck(TaskDTO taskDTO, HttpServletRequest request) {
		// 初始化session中的数据
		taskDTO.init(request);
		if (taskDTO.getIsCheck() == null) {
			return new JsonReturn(400, "非法操作");
		}
		Task task = taskService.get(taskDTO.getTaskId());
		try {
			if (task.getIsCheck() == 1 || task.getIsCheck() == 0) {
				return new JsonReturn(400, "非法操作");
			}
			if (taskDTO.getIsCheck() == 1) {
				task.setIsCheck(task.getIsCheck() - 1);// 通过审核则减1
				if (task.getIsCheck() == 1) {
					task.setStatus("1");
				}
			} else {
				task.setIsCheck(0);// 不通过审核则改成0
				task.setStatus("0");
			}
			task.setCheckUserId((RuiecStringUtil.checkNull(task.getCheckUserId()) == true ? "" : (task.getCheckUserId() + ",")) + taskDTO.getUser().getId());// 拼接审核人的id
			task.setCheckTime((RuiecStringUtil.checkNull(task.getCheckTime()) == true ? "" : (task.getCheckTime() + ","))
					+ RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(new Date()));// 审核时间
			task.setCheckRemark((RuiecStringUtil.checkNull(taskDTO.getContent()) == true ? "" : taskDTO.getContent()));// 审核备注
			taskService.updateInclude(task, new String[] { "isCheck", "checkUserId", "checkTime", "checkRemark", "status" }, null);// 修改审核状态
			//合成研判任务已可以进行则可以进行消息推送
			if (task.getIsCheck() == 1) {
				//获取等待推送的消息
				List<Message> messages = new ArrayList<Message>();
				List<Filter> filters = new ArrayList<Filter>();
				filters.add(Filter.eq("sourceId", task.getId()));
				filters.add(Filter.eq("isPush", 2));
				messages = messageService.findList(null, filters, null);
				for (int i = 0; i < messages.size(); i++) {
					//修改推送状态为未推送
					Message newMessage = new Message();
					newMessage.setId(messages.get(i).getId());
					newMessage.setIsPush(0);
					messageService.updateInclude(newMessage, new String[]{"isPush"}, null);
				}
				//发送推送消息
				taskExecutor.execute(messageService.sendMessage());
			}
		} catch (Exception e) {
			return new JsonReturn(400, "审核失败");
		}
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(taskDTO.getUser(), 4, request.getRequestURI().toString(),
					taskDTO.getUser().getPoliceName() + "审核了" + task.getName() + "任务," + (taskDTO.getIsCheck() == 1 ? "通过审核" : "不通过审核"),
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入操作日志异常:" + e);
		}
		return new JsonReturn(200, taskDTO.getIsCheck() == 1 ? "通过审核" : "不通过审核");
	}

	/**
	 * 跳转到审核反馈信息页面
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月9日 下午5:37:21
	 */
	@RequestMapping("/auditFeedback")
	public String auditFeedback(Model model, TaskDTO taskDTO, Integer type) {
		model.addAttribute("taskDTO", taskDTO);
		model.addAttribute("type", type);
		return "/admin/syntheticAnalysis/auditFeedback";
	}

	/**
	 * 保存审核反馈信息
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月8日 下午9:11:22
	 */
	@RequestMapping("/saveAuditFeedback")
	@ResponseBody
	public JsonReturn saveAuditFeedback(TaskDTO taskDTO, FeedbackDTO feedbackDTO, HttpServletRequest request) {
		// 判断该任务是否结束了
		if (taskDetailsService.isTaskOver(taskDTO)) {
			return new JsonReturn(200, "该任务已结束");
		}
		// 初始化session中的数据
		taskDTO.init(request);

		TaskDetails taskDetails = taskDetailsService.get(taskDTO.getTaskDetailsId(), null, Fetch.alias("task", "taskAlias"));
		try {
			// 任务详情数据
			JSONArray jsonArray = JSONArray.fromObject(taskDetails.getFeedbackDetails());
			List<FeedbackDTO> feedbackDTOs = new ArrayList<FeedbackDTO>();
			for (int j = 0; j < jsonArray.size(); j++) {
				// 有反馈信息则显示出来
				FeedbackDTO outFeedbackDTO = (FeedbackDTO) JSONObject.toBean(jsonArray.getJSONObject(j), FeedbackDTO.class);
				// 修改最后一条反馈的信息
				if (j == jsonArray.size() - 1) {
					outFeedbackDTO.setComment(feedbackDTO.getComment());
					outFeedbackDTO.setPoints(feedbackDTO.getPoints());
					outFeedbackDTO.setType(feedbackDTO.getType());
				}
				feedbackDTOs.add(outFeedbackDTO);
			}
			JSONArray newJsonArray = JSONArray.fromObject(feedbackDTOs);
			taskDetails.setFeedbackDetails(newJsonArray.toString());
			if (feedbackDTO.getType() == 1) {
				taskDetails.setFeedbackStatus(3);// 通过审核
			} else {
				taskDetails.setFeedbackStatus(4);// 不通过审核
			}
			taskDetailsService.updateInclude(taskDetails, new String[] { "feedbackDetails", "feedbackStatus" }, null);
		} catch (Exception e) {
			LOGGER.error("操作修改异常:" + e);
			return new JsonReturn(400, "操作异常");
		}
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(taskDTO.getUser(), 4, request.getRequestURI().toString(), taskDTO.getUser().getPoliceName() + "对"
					+ taskDetails.getTask().getName() + "任务的反馈进行审核," + (feedbackDTO.getType() == 1 ? "通过审核" : "不通过审核"),
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入操作日志异常:" + e);
		}
		return new JsonReturn(200, feedbackDTO.getType() == 1 ? "通过审核" : "不予通过");
	}

	/**
	 * 合成研判单位任务列表
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月6日 上午11:02:44
	 */
	@RequestMapping("/unitTaskList")
	public String unitTaskList(Model model, Page page, TaskDTO taskDTO, HttpServletRequest request) {
		// 初始化session中的数据
		taskDTO.init(request);
		// 根据任务名称查询
		if (taskDTO.getTaskName() != null) {
			page.add(Filter.like("name", taskDTO.getTaskName()));
		}
		DetachedCriteria t = DetachedCriteria.forClass(Task.class);
		t.setFetchMode("taskDetails", FetchMode.SELECT);

		// 筛选的单位id和当前管理的单位是否相同，相同则跳过
		if (taskDTO.getUnitId() != null && taskDTO.getUnitId() != taskDTO.getLoginUserUnit().getUnitId()) {
			List<Integer> idsIntegers = unitService.findSonIds(taskDTO.getUnitId());
			Disjunction disjunction = Restrictions.disjunction();
			for (int i = 0; i < idsIntegers.size(); i++) {
				disjunction.add(Restrictions.eq("originatorUnitId", idsIntegers.get(i)));
			}
			t.add(disjunction);
		} else {
			// 判断是市局还是其他单位
			if (!taskDTO.getUser().getId().equals(1) && !taskDTO.getLoginUserUnit().getUnitRank().getName().equals("city")) {
				Disjunction disjunction = Restrictions.disjunction();
				for (int i = 0; i < taskDTO.getLoginUserUnit().getUnitSonIds().size(); i++) {
					disjunction.add(Restrictions.eq("originatorUnitId", taskDTO.getLoginUserUnit().getUnitSonIds().get(i)));
				}
				t.add(disjunction);
			}
		}
		// 根据任务状态查询
		if (taskDTO.getTaskType() != null) {
			if (taskDTO.getTaskType()>3) {
				switch (taskDTO.getTaskType()) {
				case 4:
					page.add(Filter.eq("isCheck", 3));//待初审
					break;
				case 5:
					page.add(Filter.eq("isCheck", 2));//待复审
					t.add(Restrictions.isNotNull("checkUserId"));
					break;
				case 6:
					page.add(Filter.eq("isCheck", 0));//未通过的审核,即审核拒绝
					break;
				case 7:
					page.add(Filter.eq("isCheck", 2));//待审核
					t.add(Restrictions.isNull("checkUserId"));
					break;
				default:
					break;
				}
				page.add(Filter.ne("status", "3"));
			}else {
				page.add(Filter.eq("status", taskDTO.getTaskType().toString()));
			}
		}
		// 当前管理的最高权限以及下属单位
		taskService.findByPage(page, t);
		model.addAttribute("taskDTO", taskDTO);
		model.addAttribute("page", page);
		// 插入操作日志
		operationLogService.insertOperationLogs(taskDTO.getUser(), 4, request.getRequestURI().toString(), taskDTO.getUser().getPoliceName() + "查看了合成研判任务列表",
				LogUtil.getData(request.getParameterMap()));
		return "/admin/syntheticAnalysis/unitTaskList";
	}

	/**
	 * 合成研判单位接受的任务列表
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月6日 上午11:02:44
	 */
	@RequestMapping("/myUnitTaskList")
	public String myUnitTaskList(Model model, Page page, TaskDTO taskDTO, HttpServletRequest request) {
		// 初始化session中的数据
		taskDTO.init(request);
		DetachedCriteria td = DetachedCriteria.forClass(TaskDetails.class);
		DetachedCriteria t = td.createCriteria("task");
		t.setFetchMode("taskDetails", FetchMode.SELECT);
		// 根据任务名称查询
		if (taskDTO.getTaskName() != null) {
			t.add(Restrictions.like("name", taskDTO.getTaskName(), MatchMode.ANYWHERE));
		}
		// 判断是市局还是其他单位
		if (!taskDTO.getUser().getId().equals(1) && !taskDTO.getLoginUserUnit().getUnitRank().getName().equals("city")) {
			// 得到当前管理员所管理的所有单位id
			Disjunction disjunction = Restrictions.disjunction();
			for (int i = 0; i < taskDTO.getLoginUserUnit().getUnitIds().size(); i++) {
				disjunction.add(Restrictions.eq("receiveUnitId", taskDTO.getLoginUserUnit().getUnitIds().get(i)));
			}
			td.add(disjunction);
		}
		// 根据任务状态查询
		if (taskDTO.getTaskType() != null) {
			if (taskDTO.getTaskType() != 5) {
				page.add(Filter.eq("feedbackStatus", taskDTO.getTaskType()));
				t.add(Restrictions.ne("status", "3"));
			}else {
				t.add(Restrictions.eq("status", "3"));
			}
		}
		t.add(Restrictions.eq("isCheck", 1));
		taskDetailsService.findByPage(page, td);
		model.addAttribute("page", page);
		model.addAttribute("taskDTO", taskDTO);
		// 插入操作日志
		operationLogService.insertOperationLogs(taskDTO.getUser(), 4, request.getRequestURI().toString(), taskDTO.getUser().getPoliceName() + "查看了合成研判任务列表",
				LogUtil.getData(request.getParameterMap()));
		return "/admin/syntheticAnalysis/myUnitTaskList";
	}

	/**
	 * 判断所管理的单位中是否包含该单位
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月10日 上午10:49:49
	 */
	private boolean isUnitId(LoginUserUnit loginUserUnit, Integer unitId, Integer parentUnitId) {
		for (int i = 0; i < loginUserUnit.getUnitSonIds().size(); i++) {
			if (loginUserUnit.getUnitSonIds().get(i).equals(unitId) || loginUserUnit.getUnitSonIds().get(i).equals(parentUnitId)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否能查看该任务
	 * @author Senghor<br>
	 * @date 2018年2月7日 下午4:49:44
	 */
	private boolean isLook(TaskDTO taskDTO,Task task) {
		boolean flag = true;
		if (!(taskDTO.getLoginUserUnit().getUnitId().equals(1) || taskDTO.getUser().getId().equals(1))) {
			List<Integer> souList = unitService.findSonIds(taskDTO.getLoginUserUnit().getUnitId());
			for (int i = 0; i < souList.size(); i++) {
				if (souList.get(i).equals(task.getOriginatorUnitId())) {
					flag = false;
					break;
				}
			}
			for (int i = 0; i < task.getTaskDetails().size(); i++) {
				for (int j = 0; j < souList.size(); j++) {
					if (souList.get(j).equals(task.getTaskDetails().get(i).getReceiveUnitId())) {
						flag = false;
						break;
					}
				}
			}
		}else {
			flag = false;
		}
		return flag;
	}
}
