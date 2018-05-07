package com.ruiec.web.controller.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.constructs.web.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.ApiReturn;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.JsonReturn;
import com.ruiec.web.common.LogUtil;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.dto.FeedbackDTO;
import com.ruiec.web.dto.TaskDTO;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.Task;
import com.ruiec.web.entity.TaskDetails;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.service.DictionaryService;
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
@Controller
@RequestMapping("api/admin/syntheticAnalysis")
public class ApiSyntheticAnalysisController extends BaseAdminController {

	private static final Logger LOGGER = Logger.getLogger(ApiSyntheticAnalysisController.class);

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

	/**
	 * 合成研判我发起的任务列表
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月6日 上午11:02:44
	 */
	@RequestMapping("/taskList")
	@ResponseBody
	public ApiReturn taskList(Page page, TaskDTO taskDTO, HttpServletRequest request) {
		try {
			// 初始化session中的数据
			taskDTO.init(request);
			DetachedCriteria t = DetachedCriteria.forClass(Task.class);
			t.setFetchMode("taskDetails", FetchMode.SELECT);
			// 任务发起人
			page.add(Filter.eq("originatorUserId", taskDTO.getUser().getId()));
			taskService.findByPage(page, t);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < page.getList().size(); i++) {
				Task task = (Task) page.getList().get(i);
				Map<String , Object> map = new HashMap<String, Object>();
				map.put("taskId",task.getId());// 任务id
				map.put("taskName",ObjectUtils.firstNonNull(task.getName(),GlobalUnit.NULLMSG));// 任务名称
				map.put("createDate",RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(task.getCreateDate()));// 任务开始时间
				map.put("overDate",RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(task.getHandleTime()));// 任务结束时间
				map.put("status",ObjectUtils.firstNonNull(task.getStatus(),GlobalUnit.NULLMSG));// 任务状态
//				List<Map<String, Object>> unitList = new ArrayList<Map<String, Object>>();
//				for (int j = 0; j < task.getTaskDetails().size(); j++) {
//					Map<String, Object> map = new HashMap<String, Object>();
//					Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, task.getTaskDetails().get(j).getReceiveUnitId());
//					if (unit == null) {
//						map.put("unitName", GlobalUnit.NULLMSG);// 单位名称
//					} else {
//						map.put("unitName", unit.getUnitName());// 单位名称
//					}
//					map.put("isSign", task.getTaskDetails().get(j).getFeedbackStatus() == 0 ? "待签收" : "已签收");
//					// 任务反馈状态，0为未签收，1为已签收/待反馈，2为待审核，3审核通过，4审核拒绝
//					unitList.add(map);
//				}
//				tListDTO.setTaskUnit(unitList);// 签收单位集合
				User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, taskDTO.getUser().getId());
				map.put("userName", user == null ? GlobalUnit.NULLMSG : user.getPoliceName());
                Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, taskDTO.getUser().getUnitId());
                map.put("unitName", unit == null ? GlobalUnit.NULLMSG : unit.getUnitName());
				jsonArray.add(map);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageSize", page.getPageSize());// 每页显示条数
			map.put("pageNumber", page.getPageNumber());
			map.put("totalCount", page.getTotalCount());// 总记录数据
			map.put("mapList", jsonArray);//
			return new ApiReturn(200, "查询成功", map);
		} catch (Exception e) {
			return new ApiReturn(400, "查询失败");
		}
	}

	/**
	 * 合成研判我接收的任务列表
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月6日 上午11:02:44
	 */
	@RequestMapping("/myTaskList")
	@ResponseBody
	public ApiReturn myTaskList(Page page, TaskDTO taskDTO, HttpServletRequest request) {
		try {
			// 初始化session中的数据
			taskDTO.init(request);
			DetachedCriteria td = DetachedCriteria.forClass(TaskDetails.class);
			DetachedCriteria t = td.createCriteria("task");
			t.setFetchMode("taskDetails", FetchMode.SELECT);
			td.add(Restrictions.eq("receiveUserId", taskDTO.getUser().getId()));// 所管理的单位直属任务
			t.add(Restrictions.eq("isCheck", 1));
			if (!StringUtils.isBlank(taskDTO.getStatus())) {
                if (!taskDTO.getStatus().equals("-1")) {
                    t.add(Restrictions.eq("status", taskDTO.getStatus()));
                }
            }else {
                return new ApiReturn(400, "查询失败,缺少筛选条件");
            }
			taskDetailsService.findByPage(page, td);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < page.getList().size(); i++) {
				TaskDetails taskDetails = (TaskDetails) page.getList().get(i);
                Map<String , Object> map = new HashMap<String, Object>();
				map.put("taskDeatailsId",taskDetails.getId());// 任务详情
				map.put("taskName",ObjectUtils.firstNonNull(taskDetails.getTask().getName(),GlobalUnit.NULLMSG));// 任务名称
				map.put("createDate",RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(taskDetails.getTask().getCreateDate()));// 任务开始时间
				map.put("overDate",RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(taskDetails.getTask().getHandleTime()));// 任务结束时间
				Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, taskDetails.getTask().getOriginatorUnitId());
				if (unit == null) {
					map.put("unitName",GlobalUnit.NULLMSG);// 发起单位名称
				} else {
					map.put("unitName",unit.getUnitName());// 发起单位名称
				}
				User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, taskDTO.getUser().getId());
                map.put("userName", user == null ? GlobalUnit.NULLMSG : user.getPoliceName());
				map.put("feedbackStatus",taskDetails.getFeedbackStatus());// 任务反馈状态
				map.put("status",ObjectUtils.firstNonNull(taskDetails.getTask().getStatus(),GlobalUnit.NULLMSG));// 任务状态
				map.put("taskId",taskDetails.getTask().getId());// 任务id
				jsonArray.add(map);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageSize", page.getPageSize());// 每页显示条数
			map.put("pageNumber", page.getPageNumber());
			map.put("totalCount", page.getTotalCount());// 总记录数据
			map.put("mapList", jsonArray);//
			return new ApiReturn(200, "查询成功", map);
		} catch (Exception e) {
			return new ApiReturn(400, "查询失败");
		}
	}

	/**
	 * 签收任务
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月8日 下午9:11:22
	 */
	@RequestMapping("/taskReceive")
	@ResponseBody
	public ApiReturn taskReceive(TaskDTO taskDTO, HttpServletRequest request) {
		// 主任务
		Task task = new Task();
		try {
			// 需要判断当前任务是否结束，结束则不执行操作
			if (taskDetailsService.isTaskOver(taskDTO)) {
				return new ApiReturn(400, "该任务已结束");
			}
			// 初始化session中的数据
			taskDTO.init(request);
			TaskDetails taskDetails = taskDetailsService.get(taskDTO.getTaskDetailsId(), null, Fetch.alias("task", "taskAlias"));
			if (taskDetails.getReceiveUserId() != null) {
				return new ApiReturn(400, "该任务已被签收");
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
				return new ApiReturn(400, "没有权限操作该任务");
			}
		} catch (Exception e) {
			LOGGER.error("操作修改异常:" + e);
			return new ApiReturn(400, "操作异常");
		}
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(taskDTO.getUser(), 3, request.getRequestURI().toString(),
					taskDTO.getUser().getPoliceName() + "签收了" + task.getName() + "任务", LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入操作日志异常:" + e);
		}
		// 任务id
		return new ApiReturn(200, "签收成功");
	}

	/**
	 * 任务详情接口
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月9日 下午2:27:18
	 */
	@RequestMapping("/taskDetails")
	@ResponseBody
	public ApiReturn taskDetails(TaskDTO taskDTO, HttpServletRequest request) {
		// 初始化session中的数据
		taskDTO.init(request);
		Task task = taskService.get(taskDTO.getTaskId());
		if (task == null) {
			return new ApiReturn(400, "该任务不存在");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskId",taskDTO.getTaskId());// 任务id
        map.put("status",task.getStatus());// 任务状态
		map.put("taskName",ObjectUtils.firstNonNull(task.getName(),GlobalUnit.NULLMSG));// 任务名称
		map.put("handleMode",ObjectUtils.firstNonNull(task.getHandleMode(),GlobalUnit.NULLMSG));// 处置要求
		map.put("handleTime",RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(task.getCreateDate())+" 至 "+RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(task.getHandleTime()));// 处置时间
		map.put("details",ObjectUtils.firstNonNull(task.getDetails(),GlobalUnit.NULLMSG));// 任务详情
		User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, task.getOriginatorUserId());
        map.put("userName", user == null ? GlobalUnit.NULLMSG : user.getPoliceName());
        Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, task.getOriginatorUnitId());
        map.put("unitName", unit == null ? GlobalUnit.NULLMSG : unit.getUnitName());
		
		if (!RuiecStringUtil.checkNull(task.getApprovalAttachment())) {
			map.put("fileUrls",task.getApprovalAttachment().split(";"));// 附件数组
		}
		Dictionary dictionary = (Dictionary) RedisUtil.getRedisOne(GlobalUnit.DICTIONARY_MAP, task.getTypeNumber());
		map.put("taskType", dictionary == null ? GlobalUnit.NULLMSG : dictionary.getItemName());
		
		List<Map<String, Object>> feedbackList = new ArrayList<Map<String, Object>>();
        List<String> signList = new ArrayList<String>();
		boolean isCheck = false;
        boolean isFeed = false;
		if (task.getIsCheck() == 2) {
			// 2=需要分县局/市局审核
			// 判断是否是发起单位的上级单位才能审核
			Unit parentUnit = unitService.get(task.getOriginatorUnitId());
			if (isUnitId(taskDTO.getLoginUserUnit(), parentUnit)) {
				if (taskDTO.getLoginUserUnit().getUnitRank().getName().equals("area") && task.getCheckUserId() == null && parentUnit.getUnitRank().equals("town")) {
					// 需要分县局,只有分县局能查看
					// 允许审核
					isCheck = true;
				}
				if (taskDTO.getLoginUserUnit().getUnitRank().getName().equals("city") && task.getCheckUserId() == null && parentUnit.getUnitRank().equals("area")) {
					// 需要市局审核,只有市局能查看
					// 允许审核
					isCheck = true;
				}
				if (taskDTO.getLoginUserUnit().getUnitRank().getName().equals("city") && task.getCheckUserId() != null && parentUnit.getUnitRank().equals("town")) {
					// 需要市局再次审核,只有市局能查看
					// 允许审核
					isCheck = true;
				}
			}
		} else if (task.getIsCheck() == 3) {
			// 3=需要分县局审核
			Unit parentUnit = unitService.get(task.getOriginatorUnitId());
			if (isUnitId(taskDTO.getLoginUserUnit(), parentUnit) && taskDTO.getLoginUserUnit().getUnitRank().getName().equals("area")) {
				// 允许审核
				isCheck = true;
			}
		}
		Date newDate = null;
        if (task.getTaskDetails().size()>0) {
            newDate = task.getTaskDetails().get(0).getModifyDate();
        }
        // 判断是否有人反馈
        for (int i = 0; i < task.getTaskDetails().size(); i++) {
            // 任务详情数据
            TaskDetails taskDetails = task.getTaskDetails().get(i);
            Unit feedbackUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, taskDetails.getReceiveUnitId());
            if (feedbackUnit == null) {
                continue;
            }
            User feedbackUser = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, taskDetails.getReceiveUserId());
            //显示签收单位的数据
            signList.add(feedbackUnit.getUnitName()+"("+(feedbackUser == null ? "未签收":feedbackUser.getPoliceName())+")");
            
            //显示最新时间的反馈数据
            if (i!=0) {
                int sum = taskDetails.getModifyDate().compareTo(newDate); 
                if (sum<1) {
                    continue;
                }
            }
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
                    if (task.getStatus().equals("3")) {
                        // 已审核数据
                        if (feedbackDTO.getType() != 0) {
                            feedbackDTOs.add(feedbackDTO);
                        }
                    } else {
                        feedbackDTOs.add(feedbackDTO);
                    }
                } else if (taskDetails.getReceiveUserId().equals(taskDTO.getUser().getId())
                        || taskDetails.getReceiveUnitId().equals(taskDTO.getLoginUserUnit().getUnitId())) {
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
            // 任务反馈信息
            Map<String, Object> feedbackMap = new HashMap<String, Object>();
            if (feedbackDTOs.size() > 0) {
                // 取最新一条反馈返回app
                FeedbackDTO newFeedbackDTO = feedbackDTOs.get(feedbackDTOs.size() - 1);
                feedbackMap.put("feedbackUser", feedbackUser == null ? GlobalUnit.NULLMSG:feedbackUser.getPoliceName());// 反馈人
//              map.put("taskDetailsId", taskDetails.getId());// 任务详情id
                feedbackMap.put("feedbackTime", ObjectUtils.firstNonNull(newFeedbackDTO.getCreateTime(),GlobalUnit.NULLMSG));// 反馈时间
//              map.put("points", newFeedbackDTO.getPoints());// 反馈评分
                feedbackMap.put("content", ObjectUtils.firstNonNull(newFeedbackDTO.getContent(),GlobalUnit.NULLMSG));// 反馈内容
                feedbackMap.put("feedbackUnit", feedbackUnit.getUnitName());// 反馈单位
//              map.put("auditType", newFeedbackDTO.getType());// 审核状态
//              map.put("comment", ObjectUtils.firstNonNull(newFeedbackDTO.getComment(),GlobalUnit.NULLMSG));// 审核评价
                //只放最新的一条数据
                feedbackList.removeAll(feedbackList);
                feedbackList.add(feedbackMap);
            }
            if (taskDetails.getFeedbackStatus() != 2 && taskDetails.getFeedbackStatus() != 0) {
                isFeed = true;
            }
        }
        map.put("feedbackList",feedbackList);
		map.put("checkAudit",isCheck);// 是否能审核
        map.put("signList",signList);// 接收单位
        map.put("feedType",isFeed);// 是否能反馈
		JSONObject jsonObject = JSONObject.fromObject(map);
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(taskDTO.getUser(), 4, request.getRequestURI().toString(),
					taskDTO.getUser().getPoliceName() + "查看了" + task.getName() + "任务详情", LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入操作日志异常:" + e);
		}
		return new ApiReturn(200, "查询成功", jsonObject);
	}

	/**
	 * 更多反馈接口
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月9日 下午2:27:18
	 */
	@RequestMapping("/moreFeedback")
	@ResponseBody
	public ApiReturn moreFeedback(TaskDTO taskDTO, HttpServletRequest request, Integer userId) {
		// 初始化session中的数据
		taskDTO.init(request);
        Task task = taskService.get(taskDTO.getTaskId());
//		TaskDetails taskDetails = taskDetailsService.get(taskDTO.getTaskDetailsId(), null, Fetch.alias("task", "taskAlias"));
		if (task == null) {
			return new ApiReturn(400, "查询失败,任务不存在");
		}
        List<Object> feedbacks = new ArrayList<Object>();
        Map<String , Object> returnMap = new HashMap<String, Object>();
		for (int j = 0; j < task.getTaskDetails().size(); j++) {
            // 任务详情数据
            TaskDetails taskDetails = task.getTaskDetails().get(j);
    		// 任务详情数据
    		JSONArray jsonArray = JSONArray.fromObject(taskDetails.getFeedbackDetails());
    		for (int i = 0; i < jsonArray.size(); i++) {
    			FeedbackDTO feedbackDTO = (FeedbackDTO) JSONObject.toBean(jsonArray.getJSONObject(i), FeedbackDTO.class);
    			// 空则循环下一条数据
    			if (feedbackDTO == null) {
    				continue;
    			}
    			// 任务反馈信息
    			Map<String, Object> map = new HashMap<String, Object>();
    			if (taskDetails.getReceiveUserId() != null) {
    				User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, taskDetails.getReceiveUserId());
    				map.put("feedbackUser", user.getPoliceName());// 反馈人
    			} else {
    				map.put("feedbackUser", null);// 反馈人
    			}
    			Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, taskDetails.getReceiveUnitId());
                if (unit == null) {
                    map.put("feedbackUnit", GlobalUnit.NULLMSG);// 反馈单位
                }else {
                    map.put("feedbackUnit", unit.getUnitName());// 反馈单位
                }
//    			map.put("taskDetailsId", taskDetails.getId());// 任务详情id
    			map.put("feedbackTime", ObjectUtils.firstNonNull(feedbackDTO.getCreateTime(),GlobalUnit.NULLMSG));// 反馈时间
//    			map.put("points", feedbackDTO.getPoints());// 反馈评分
    			map.put("content", ObjectUtils.firstNonNull(feedbackDTO.getContent(),GlobalUnit.NULLMSG));// 反馈内容
//    			map.put("auditType", feedbackDTO.getType());// 审核状态
//    			map.put("comment", ObjectUtils.firstNonNull(feedbackDTO.getComment(),GlobalUnit.NULLMSG));// 审核评价
                String[] fileuUrl = feedbackDTO.getFileUrl().indexOf(";") == -1 ? new String[]{} : feedbackDTO.getFileUrl().split(";");
                map.put("fileUrl", fileuUrl);
                feedbacks.add(map);
    		}
		}
        Collections.reverse(feedbacks);//倒序
		returnMap.put("mapList", feedbacks);
		return new ApiReturn(200, "查询成功", returnMap);
	}

	/**
	 * 保存任务反馈信息
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月11日 下午9:11:22
	 */
	@RequestMapping(value = "/saveFeedback", method = RequestMethod.POST)
	@ResponseBody
	public ApiReturn saveFeedback(TaskDTO taskDTO, FeedbackDTO feedbackDTO, HttpServletRequest request) {
		Task task = new Task();
		try {
			// 需要判断当前任务是否结束，结束则不执行操作
		    task = taskService.get(taskDTO.getTaskId());
		    if (task==null) {
                return new ApiReturn(400, "任务不存在");
            }
	        if (task.getStatus().equals("3")) {
	            return new ApiReturn(400, "任务已结束不能反馈");
	        }
			// 初始化session中的数据
			taskDTO.init(request);
			TaskDetails taskDetails = new TaskDetails();
            // 需要判断当前任务当前用户是否能反馈
			for (int i = 0; i < task.getTaskDetails().size(); i++) {
			    taskDetails = task.getTaskDetails().get(i);
			    if (taskDTO.getUser().getId().equals(taskDetails.getReceiveUserId())) {
                    break;
                }
			    if (task.getTaskDetails().size() == i+1) {
			        return new ApiReturn(400, "没有权限操作该任务");
                }
            }
			feedbackDTO.setCreateTime(RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(new Date()));// 反馈时间
			feedbackDTO.setType(0);// 审核状态，默认待审核
			JSONArray jsonArray = new JSONArray();
			// 判断是否是第一次反馈
			if (!RuiecStringUtil.checkNull(taskDetails.getFeedbackDetails())) {
				// 再次反馈则追加反馈
				jsonArray = JSONArray.fromObject(taskDetails.getFeedbackDetails());
				FeedbackDTO fDto = (FeedbackDTO) JSONObject.toBean((JSONObject) jsonArray.get(jsonArray.size() - 1), FeedbackDTO.class);
                // 判断上一条反馈是否审核
                if (fDto.getType() == 0) {
                    return new ApiReturn(400, "请等待您的反馈审核");
                }
			}
			// 转换成json数据保存
			jsonArray.add(feedbackDTO);
			taskDetails.setFeedbackDetails(jsonArray.toString());// 任务反馈信息
            taskDetails.setFeedbackStatus(2);// 状态变为待审核
		    // 修改为待审核状态
            taskDetailsService.updateInclude(taskDetails, new String[] { "feedbackStatus", "feedbackDetails" }, null);
		} catch (Exception e) {
			LOGGER.error("操作修改异常:" + e);
			return new ApiReturn(400, "操作异常");
		}
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(taskDTO.getUser(), 3, request.getRequestURI().toString(),
					taskDTO.getUser().getPoliceName() + "反馈了" + task.getName() + "任务", LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入操作日志异常:" + e);
		}
		return new ApiReturn(200, "反馈成功");
	}

	/**
	 * 保存审核反馈信息
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月8日 下午9:11:22
	 */
	@RequestMapping("/saveAuditFeedback")
	@ResponseBody
	public ApiReturn saveAuditFeedback(TaskDTO taskDTO, FeedbackDTO feedbackDTO, HttpServletRequest request) {
		// 判断该任务是否结束了
		if (taskDetailsService.isTaskOver(taskDTO)) {
			return new ApiReturn(200, "该任务已结束");
		}
		// 初始化session中的数据
		taskDTO.init(request);
		//判断是否是发起人在操作 
		if (!taskDetailsService.isOriginateUser(taskDTO)) {
			return new ApiReturn(400, "没有权限");
		}
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
			return new ApiReturn(400, "操作异常");
		}
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(taskDTO.getUser(), 4, request.getRequestURI().toString(), taskDTO.getUser().getPoliceName() + "对"
					+ taskDetails.getTask().getName() + "任务的反馈进行审核," + (feedbackDTO.getType() == 1 ? "通过审核" : "不通过审核"),
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入操作日志异常:" + e);
		}
		return new ApiReturn(200, feedbackDTO.getType() == 1 ? "通过审核" : "不予通过");
	}

	/**
	 * 结束任务操作
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月8日 上午10:24:31
	 */
	@RequestMapping("/saveTaskOver")
	@ResponseBody
	public ApiReturn saveTaskOver(Task task, TaskDTO taskDTO, HttpServletRequest request) {
		// 初始化session中的数据
		taskDTO.init(request);
		// 判断是否是发起人操作
		if (!taskService.isOriginateUser(taskDTO)) {
			return new ApiReturn(400, "没有权限");
		}
		if (taskDTO.getTaskId() != null) {
			return taskService.saveTaskOver(task, taskDTO);
		}
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(taskDTO.getUser(), 3, request.getRequestURI().toString(),
					taskDTO.getUser().getPoliceName() + "结束了" + task.getName() + "任务", LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入操作日志异常:" + e);
		}
		return new ApiReturn(400, "结束任务失败！");
	}

	/**
	 * 审核任务接口
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月10日 下午9:15:11
	 */
	@RequestMapping("/saveIsCheck")
	@ResponseBody
	public ApiReturn saveIsCheck(TaskDTO taskDTO, HttpServletRequest request) {
		// 初始化session中的数据
		taskDTO.init(request);
		if (taskDTO.getIsCheck() == null) {
			return new ApiReturn(400, "请选择要通过还是不通过审核");
		}
		Task task = taskService.get(taskDTO.getTaskId());
		if (task == null) {
			return new ApiReturn(400, "任务不存在");
		}
		try {
			if (task.getIsCheck() == 1 || task.getIsCheck() == 0) {
				return new ApiReturn(400, "该任务已不可审核");
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
		} catch (Exception e) {
			return new ApiReturn(400, "审核失败");
		}
		try {
			// 插入操作日志
			operationLogService.insertOperationLogs(taskDTO.getUser(), 4, request.getRequestURI().toString(),
					taskDTO.getUser().getPoliceName() + "审核了" + task.getName() + "任务," + (taskDTO.getIsCheck() == 1 ? "通过审核" : "不通过审核"),
					LogUtil.getData(request.getParameterMap()));
		} catch (Exception e) {
			LOGGER.error("插入操作日志异常:" + e);
		}
		return new ApiReturn(200, taskDTO.getIsCheck() == 1 ? "已通过审核" : "不通过审核");
	}

	/**
	 * 判断所管理的单位中是否包含该单位
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月10日 上午10:49:49
	 */
	private boolean isUnitId(LoginUserUnit loginUserUnit, Unit unit) {
	    if (unit == null) {
	        return false;
        }
	    Integer unitId = unit.getParentId();
		for (int i = 0; i < loginUserUnit.getUnitSonIds().size(); i++) {
			if (loginUserUnit.getUnitSonIds().get(i).equals(unitId)) {
				return true;
			}
		}
		return false;
	}
	
	/**
     * 合成研判待审核/待签收待反馈任务列表
     * 
     * @author Senghor<br>
     * @date 2018年1月6日 上午11:02:44
     */
    @RequestMapping("/taskTypeList")
    @ResponseBody
    public ApiReturn taskTypeList(Page page, TaskDTO taskDTO, HttpServletRequest request) {
        try {
            // 初始化session中的数据
            Map<String, Object> returnMap = new HashMap<String, Object>();
            taskDTO.init(request);
            if (taskDTO.getType()==1) {
                DetachedCriteria t = DetachedCriteria.forClass(Task.class);
                t.setFetchMode("taskDetails", FetchMode.SELECT);
                t.add(Restrictions.eq("status", "0"));
                //待审核的任务
                if ("警员".equals(taskDTO.getLoginUserUnit().getUserName()) || taskDTO.getLoginUserUnit().getUnitRank().getName().equals("town")) {
                    return new ApiReturn(200, "无审核任务");
                }else {
                    //市局权限
                    if (taskDTO.getLoginUserUnit().getUnitRank().getName().equals("city")) {
                        t.add(Restrictions.eq("isCheck", 2));//查询正在通过初审和只需要审核一次的任务
                    }else if (taskDTO.getLoginUserUnit().getUnitRank().getName().equals("area")) {
                        t.add(Restrictions.in("isCheck", new Integer[]{2,3}));//查询正在审核通过的任务
                        t.add(Restrictions.in("originatorUnitId", taskDTO.getLoginUserUnit().getUnitSonIds()));//查询下级单位创建的任务
                    }
                }
                taskService.findByPage(page, t);
                List<Map<String, Object>> listMaps = new  ArrayList<Map<String,Object>>(); 
                for (int i = 0; i < page.getList().size(); i++) {
                    Task task = (Task) page.getList().get(i);
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("taskId", task.getId());
                    map.put("taskName", ObjectUtils.firstNonNull(task.getName(),GlobalUnit.NULLMSG));
                    map.put("createDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(task.getCreateDate()));
                    map.put("overDate", RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(task.getHandleTime()));
                    map.put("status", ObjectUtils.firstNonNull(task.getStatus(),GlobalUnit.NULLMSG));
                    User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, task.getOriginatorUserId());
                    //发起人
                    if (user==null) {
                        map.put("userName", GlobalUnit.NULLMSG);
                    }else {
                        map.put("userName", ObjectUtils.firstNonNull(user.getPoliceName(),GlobalUnit.NULLMSG));
                    }
                    //发起单位
                    Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, task.getOriginatorUnitId());
                    if (unit==null) {
                        map.put("unitName", GlobalUnit.NULLMSG);
                    }else {
                        map.put("unitName", ObjectUtils.firstNonNull(unit.getUnitName(),GlobalUnit.NULLMSG));
                    }
                    listMaps.add(map);
                }
                returnMap.put("pageNumber", page.getPageNumber());//当前页数
                returnMap.put("totalCount", page.getTotalCount());// 总记录数据
                returnMap.put("mapList", listMaps);//
            }else {
                DetachedCriteria td = DetachedCriteria.forClass(TaskDetails.class);
                DetachedCriteria t = td.createCriteria("task");
                t.setFetchMode("taskDetails", FetchMode.SELECT);
                //查询未结束的任务
                t.add(Restrictions.in("status", new String[]{"1","2"}));
                //待签收和反馈的任务
                t.add(Restrictions.eq("isCheck", 1));//查询审核通过的任务
                //待审核的任务
                if (taskDTO.getLoginUserUnit().getUnitRank() == null) {
                    return new ApiReturn(200, "无签收任务");
                }else {
                    td.add(Restrictions.in("receiveUnitId", taskDTO.getLoginUserUnit().getUnitIds()));//签收单位
                }
                td.add(Restrictions.in("feedbackStatus", new Integer[]{0,1}));//查询未签收的任务和未反馈的任务
                td.add(Restrictions.isNull("feedbackTime"));//反馈时间为空的任务
                taskDetailsService.findByPage(page, td);
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < page.getList().size(); i++) {
                    TaskDetails taskDetails = (TaskDetails) page.getList().get(i);
                    Map<String, Object> dateMap = new HashMap<String, Object>();
                    dateMap.put("taskDeatailsId",taskDetails.getId());// 任务详情
                    dateMap.put("taskName",ObjectUtils.firstNonNull(taskDetails.getTask().getName(),GlobalUnit.NULLMSG));// 任务名称
                    dateMap.put("createDate",RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(taskDetails.getTask().getCreateDate()));// 任务开始时间
                    dateMap.put("overDate",RuiecDateUtils.format_yyyy_MM_dd_HH_mm_ss(taskDetails.getTask().getHandleTime()));// 任务结束时间
                    Unit unit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, taskDetails.getTask().getOriginatorUnitId());
                    if (unit == null) {
                        dateMap.put("unitName",GlobalUnit.NULLMSG);// 发起单位名称
                    } else {
                        dateMap.put("unitName",unit.getUnitName());// 发起单位名称
                    }
                    User user = (User) RedisUtil.getRedisOne(GlobalUnit.USER_MAP, taskDetails.getTask().getOriginatorUserId());
                    //发起人
                    if (user==null) {
                        dateMap.put("userName",GlobalUnit.NULLMSG);
                    }else {
                        dateMap.put("userName",ObjectUtils.firstNonNull(user.getPoliceName(),GlobalUnit.NULLMSG));
                    }
                    dateMap.put("feedbackStatus",taskDetails.getFeedbackStatus());// 任务反馈状态
                    dateMap.put("status",taskDetails.getFeedbackStatus());// 任务状态
                    dateMap.put("taskId",taskDetails.getTask().getId());// 任务id
                    jsonArray.add(dateMap);
                }
                returnMap.put("pageNumber", page.getPageNumber());
                returnMap.put("totalCount", page.getTotalCount());// 总记录数据
                returnMap.put("mapList", jsonArray);//
            }
            return new ApiReturn(200, "查询成功",returnMap);
        } catch (Exception e) {
            LOGGER.error("查询失败："+e);
            return new ApiReturn(400, "查询失败");
        }
    }
}
