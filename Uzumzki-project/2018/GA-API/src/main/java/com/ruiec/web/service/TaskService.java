package com.ruiec.web.service;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.common.ApiReturn;
import com.ruiec.web.dto.TaskDTO;
import com.ruiec.web.entity.Task;

/**
 * 合成研判任务服务接口
 * @author Senghor<br>
 * @date 2018年1月6日 下午7:10:32
 */
public interface TaskService extends BaseService<Task, Integer> {

	/**
	 * 保存合成研判的任务
	 * @author Senghor<br>
	 * @date 2018年1月8日 下午2:24:05
	 */
	public ApiReturn save(Task task, TaskDTO taskDTO);

	/**
	 * 保存编辑的任务数据
	 * @author Senghor<br>
	 * @date 2018年1月11日 上午11:03:36
	 */
	public ApiReturn update(Task task, TaskDTO taskDTO);
	
	/**
	 * 保存任务结束理由
	 * @author Senghor<br>
	 * @date 2018年1月8日 下午5:07:18
	 */
	public ApiReturn saveTaskOver(Task task,TaskDTO taskDTO);
	
	/**
	 * 是否能操作该任务
	 * @author Senghor<br>
	 * @date 2018年1月8日 下午9:21:44
	 */
	public boolean isOperationTask(TaskDTO taskDTO);
	
	/**
	 * 该任务是否已结束
	 * @author Senghor<br>
	 * @date 2018年1月9日 下午8:43:06
	 */
	public boolean isTaskOver(TaskDTO taskDTO);
	
	/**
	 * 判断发起人能否操作
	 * @author Senghor<br>
	 * @date 2018年1月11日 下午4:40:18
	 */
	public boolean isOriginateUser(TaskDTO taskDTO);
}
