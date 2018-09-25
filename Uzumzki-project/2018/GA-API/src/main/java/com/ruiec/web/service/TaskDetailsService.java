package com.ruiec.web.service;

import com.ruiec.framework.server.support.service.BaseService;
import com.ruiec.web.dto.TaskDTO;
import com.ruiec.web.entity.Task;
import com.ruiec.web.entity.TaskDetails;

/**
 * 合成研判任务详情服务接口
 * @author Senghor<br>
 * @date 2018年1月6日 下午7:10:32
 */
public interface TaskDetailsService extends BaseService<TaskDetails, Integer> {
	
	/**
	 * 保存合成研判的任务详情
	 * @author Senghor<br>
	 * @date 2018年1月8日 下午2:24:05
	 */
	public void save(Task task, Integer unitId);
	
	/**
	 * 是否能操作该任务
	 * @author Senghor<br>
	 * @date 2018年1月8日 下午9:43:07
	 */
	public boolean isOperationTaskDetails(TaskDTO taskDTO);
	
	/**
	 * 该任务是否已结束
	 * @author Senghor<br>
	 * @date 2018年1月9日 下午8:43:06
	 */
	public boolean isTaskOver(TaskDTO taskDTO);
	
	/**
	 * 根据任务详情id判断发起人是否能操作
	 * @author Senghor<br>
	 * @date 2018年1月11日 下午4:40:18
	 */
	public boolean isOriginateUser(TaskDTO taskDTO);
	
	/**
	 * 根据任务详情id判断接收人是否能操作
	 * @author Senghor<br>
	 * @date 2018年1月11日 下午4:40:18
	 */
	public boolean isReceiveUser(TaskDTO taskDTO);
}
