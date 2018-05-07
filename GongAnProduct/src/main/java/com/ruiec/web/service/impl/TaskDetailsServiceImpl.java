/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.query.Fetch;
import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.dto.TaskDTO;
import com.ruiec.web.entity.Task;
import com.ruiec.web.entity.TaskDetails;
import com.ruiec.web.service.TaskDetailsService;
import com.ruiec.web.service.UserUnitService;

/**
 * 合成研判任务服务接口
 * 
 * @author Senghor<br>
 * @date 2018年1月6日 下午7:14:31
 */
@Service("taskDetailsServiceImpl")
public class TaskDetailsServiceImpl extends BaseServiceImpl<TaskDetails, Integer> implements TaskDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskDetailsServiceImpl.class);
	@Resource
	private UserUnitService userUnitService;

	/**
	 * 保存任务详情数据
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月8日 下午2:56:34
	 */
	@Override
	@Transactional
	public void save(Task task, Integer unitId) {
		TaskDetails taskDetails = new TaskDetails();
		taskDetails.setPoints(0);// 积分(评分)默认0分
		taskDetails.setFeedbackStatus(0);// 默认未签收
		taskDetails.setReceiveUnitId(unitId);// 接受单位id
		taskDetails.setTask(task);// 关联任务
		try {
			super.save(taskDetails);
		} catch (Exception e) {
			e.printStackTrace();
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
	public boolean isOperationTaskDetails(TaskDTO taskDTO) {
		// 任务详情数据
		TaskDetails taskDetails = super.get(taskDTO.getTaskDetailsId(), null, Fetch.alias("task", "taskAlias"));
		// 判断当前登录的管理员是否管理了该任务的签收单位
		for (int i = 0; i < taskDTO.getLoginUserUnit().getUnitIds().size(); i++) {
			if (taskDetails.getReceiveUnitId().equals(taskDTO.getLoginUserUnit().getUnitIds().get(i))) {
				return true;
			}
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
		// 获取任务详情数据
		TaskDetails taskDetails = super.get(taskDTO.getTaskDetailsId(), null, Fetch.alias("task", "taskAlias"));
		// 判断当前任务是否已经结束
		if (taskDetails.getTask().getStatus().equals("3")) {
			return true;
		}
		return false;
	}

	/**
	 * 根据任务详情id判断发起人是否能操作
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月11日 下午4:44:39
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isOriginateUser(TaskDTO taskDTO) {
		// 任务详情
		TaskDetails taskDetails = super.get(taskDTO.getTaskDetailsId(), null, Fetch.alias("task", "taskAlias"));
		// 判断任务发起人是否是当前登录用户
		if (taskDetails.getTask().getOriginatorUserId().equals(taskDTO.getUser().getId())) {
			return true;
		}
		return false;
	}

	/**
	 * 根据任务详情id判断接收人是否能操作
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月11日 下午4:44:45
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean isReceiveUser(TaskDTO taskDTO) {
		// 任务详情
		TaskDetails taskDetails = super.get(taskDTO.getTaskDetailsId(), null, Fetch.alias("task", "taskAlias"));
		// 判断任务接收人是否是当前登录用户
		if (taskDetails.getReceiveUserId().equals(taskDTO.getUser().getId())) {
			return true;
		}
		return false;
	}
}
