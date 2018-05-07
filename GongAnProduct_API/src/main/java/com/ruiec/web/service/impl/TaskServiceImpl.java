/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.common.ApiReturn;
import com.ruiec.web.common.GlobalUnit;
import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.common.RedisUtil;
import com.ruiec.web.dto.TaskDTO;
import com.ruiec.web.entity.Task;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.service.TaskDetailsService;
import com.ruiec.web.service.TaskService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.util.RuiecStringUtil;

/**
 * 合成研判任务服务接口
 * 
 * @author Senghor<br>
 * @date 2018年1月6日 下午7:14:31
 */
@Service("taskServiceImpl")
public class TaskServiceImpl extends BaseServiceImpl<Task, Integer> implements TaskService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);
	@Resource
	private TaskDetailsService taskDetailsService;
	@Resource
	private UnitService unitService;

	/**
	 * 新增任务数据
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月8日 下午3:22:38
	 */
	@Override
	@Transactional
	public ApiReturn save(Task task, TaskDTO taskDTO) {
		// 附件拼接字符串地址
		String approvalAttachment = RuiecStringUtil.pingString(taskDTO.getFileUrls(), ";");// 得到附件的上传地址字符串
		task.setApprovalAttachment(approvalAttachment);// 附件地址的拼接字符串
		task.setOriginatorUnitId(taskDTO.getLoginUserUnit().getUnitId());// 任务发起人单位ID
		task.setOriginatorUserId(taskDTO.getUser().getId());// 任务发起人id
		task.setLog(taskDTO.getUser().getPoliceName() + "创建了" + task.getName() + "任务");// 记录这条数据自己的日志，（还需要保存下发接受人的id）
		if (taskDTO.getUrgency().equals(1)) {// 任务级别
			task.setTaskLevel("紧急");
		} else {
			task.setTaskLevel("不紧急");
		}

		// 当前管理员的管理级别
		String unitRank = taskDTO.getLoginUserUnit().getUnitRank().getName();

		// 保存任务的详细信息
		for (int i = 0; i < taskDTO.getAreaUnitIds().length; i++) {
			// 需要发送任务的单位
			Unit sendUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, taskDTO.getAreaUnitIds()[i]);

			if (unitRank.equals("city")) {
				task.setStatus("1");// 任务状态
				task.setIsCheck(1);// 是否审核
				// 市局不需要审核
				break;
			} else if (unitRank.equals("area")) {
				// 判断是否管理该单位
				if (this.isUnitId(taskDTO.getLoginUserUnit(), sendUnit.getId())) {
					task.setStatus("1");// 任务状态
					task.setIsCheck(1);// 是否审核
				} else {
					task.setStatus("0");// 任务状态
					task.setIsCheck(2);// 是否审核
					break;
				}
			} else if (unitRank.equals("town")) {
				// 判断是否管理该单位
				if (this.isUnitId(taskDTO.getLoginUserUnit(), sendUnit.getId())) {
					task.setStatus("1");// 任务状态
					task.setIsCheck(1);// 是否审核
				} else {
					// 判断是否跨区
					Unit userUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, taskDTO.getLoginUserUnit().getUnitId());// 最高权限单位

					// 判断接收单位是否为同一个父级
					if (sendUnit.getParentId() != null && sendUnit.getParentId().equals(userUnit.getParentId())) {
						task.setStatus("0");// 任务状态
						task.setIsCheck(2);// 是否审核
					} else {
						task.setStatus("0");// 任务状态
						task.setIsCheck(3);// 是否审核
						break;
					}
				}
			}
		}
		try {
			// 保存合成研判的任务
			task = super.save(task);
			// 保存任务的详细信息
			for (int i = 0; i < taskDTO.getAreaUnitIds().length; i++) {
				taskDetailsService.save(task, taskDTO.getAreaUnitIds()[i]);
			}
		} catch (Exception e) {
			LOGGER.error("创建任务失败：" + e);
		}
		return new ApiReturn(200, "创建成功");
	}

	/**
	 * 保存任务结束理由
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月8日 下午5:07:44
	 */
	@Override
	@Transactional
	public ApiReturn saveTaskOver(Task task, TaskDTO taskDTO) {
		Task outTask = super.get(taskDTO.getTaskId());
		if (outTask.getStatus().equals("3")) {
			return new ApiReturn(400, "请不要重复操作");
		}
		// 判断当前用户是否有权限结束该任务
		if (outTask.getOriginatorUserId().equals(taskDTO.getUser().getId())) {
			task.setId(taskDTO.getTaskId());// 任务id
			task.setStatus("3");// 改为结束状态
			task.setLog(outTask.getLog() + ";" + taskDTO.getUser().getPoliceName() + "结束了" + outTask.getName() + "任务");
			super.updateInclude(task, new String[] { "status", "log" }, null);// 只修改结束理由字段
			return new ApiReturn(200, "已结束该任务");
		} else {
			return new ApiReturn(400, "没有权限执行该操作");
		}
	}

	/**
	 * 是否能操作该任务
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月8日 下午9:26:05
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isOperationTask(TaskDTO taskDTO) {
		Task task = super.get(taskDTO.getTaskId());
		if (task.getOriginatorUserId().equals(taskDTO.getUser().getId())) {
			return true;
		}
		return false;
	}

	/**
	 * 该任务是否已结束
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月9日 下午8:47:48
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isTaskOver(TaskDTO taskDTO) {
		Task task = super.get(taskDTO.getTaskId());
		if (task.getStatus().equals("3")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断所管理的单位中是否包含该单位
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月10日 上午10:49:49
	 */
	private boolean isUnitId(LoginUserUnit loginUserUnit, Integer unitId) {
		for (int i = 0; i < loginUserUnit.getUnitSonIds().size(); i++) {
			if (loginUserUnit.getUnitSonIds().get(i).equals(unitId)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 保存编辑的任务数据
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月11日 上午11:04:05
	 */
	@Override
	@Transactional
	public ApiReturn update(Task task, TaskDTO taskDTO) {
		Task outTask = super.get(taskDTO.getTaskId());// 修改之前的数据
		task.setId(taskDTO.getTaskId());// 任务id
		// 附件拼接字符串地址
		String approvalAttachment = RuiecStringUtil.pingString(taskDTO.getFileUrls(), ";");// 得到附件的上传地址字符串
		task.setApprovalAttachment(approvalAttachment);// 附件地址的拼接字符串
		task.setLog(outTask.getLog() + ";" + taskDTO.getUser().getPoliceName() + "修改了" + task.getName() + "任务");// 记录这条数据自己的日志，（还需要保存下发接受人的id）

		// 当前管理员的管理级别
		String unitRank = taskDTO.getLoginUserUnit().getUnitRank().getName();

		if (taskDTO.getAreaUnitIds() == null) {
			return new ApiReturn(400, "请选择单位");
		}

		// 保存任务的详细信息
		for (int i = 0; i < taskDTO.getAreaUnitIds().length; i++) {
			// 需要发送任务的单位
			Unit sendUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, taskDTO.getAreaUnitIds()[i]);

			if (unitRank.equals("city")) {
				task.setStatus("1");// 任务状态
				task.setIsCheck(1);// 是否审核
				// 市局不需要审核
				break;
			} else if (unitRank.equals("area")) {
				// 判断是否管理该单位
				if (this.isUnitId(taskDTO.getLoginUserUnit(), sendUnit.getId())) {
					task.setStatus("1");// 任务状态
					task.setIsCheck(1);// 是否审核
				} else {
					task.setStatus("0");// 任务状态
					task.setIsCheck(2);// 是否审核
					break;
				}
			} else if (unitRank.equals("town")) {
				// 判断是否管理该单位
				if (this.isUnitId(taskDTO.getLoginUserUnit(), sendUnit.getId())) {
					task.setStatus("1");// 任务状态
					task.setIsCheck(1);// 是否审核
				} else {
					// 判断是否跨区
					Unit userUnit = (Unit) RedisUtil.getRedisOne(GlobalUnit.UNIT_MAP, taskDTO.getLoginUserUnit().getUnitId());// 最高权限单位

					// 判断单位是否是警种
					if (sendUnit.getParentId() != null && sendUnit.getParentId().equals(userUnit.getParentId())) {
						task.setStatus("0");// 任务状态
						task.setIsCheck(2);// 是否审核
					}
					task.setStatus("0");// 任务状态
					task.setIsCheck(3);// 是否审核
					break;
				}
			}
		}
		task.setCheckUserId(null);
		task.setCheckTime(null);
		task.setCheckRemark(null);
		try {
			// 修改合成研判的任务
			super.updateInclude(task, new String[] { "name", "typeNumber", "approvalAttachment", "handleMode", "handleTime", "details", "status", "isCheck",
					"log", "checkUserId", "checkTime", "checkRemark" }, null);
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(Filter.eq("task", task));
			taskDetailsService.delete(filters);
			// 修改任务的详细信息
			for (int i = 0; i < taskDTO.getAreaUnitIds().length; i++) {
				taskDetailsService.save(task, taskDTO.getAreaUnitIds()[i]);
			}
		} catch (Exception e) {
			LOGGER.error("修改任务失败：" + e);
		}
		return new ApiReturn(200, "编辑成功");
	}

	/**
	 * 根据任性id判断发起人是否能操作
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月11日 下午4:48:36
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isOriginateUser(TaskDTO taskDTO) {
		// 任务详情
		Task task = super.get(taskDTO.getTaskId());
		// 判断任务发起人是否是当前登录用户
		if (task.getOriginatorUserId().equals(taskDTO.getUser().getId())) {
			return true;
		}
		return false;
	}
}
