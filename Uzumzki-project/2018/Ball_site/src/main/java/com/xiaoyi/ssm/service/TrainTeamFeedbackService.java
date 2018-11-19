package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.TrainTeamFeedback;

/**  
 * @Description: 培训机构反馈业务逻辑层
 * @author 宋高俊  
 * @date 2018年10月12日 上午9:55:06 
 */ 
public interface TrainTeamFeedbackService extends BaseService<TrainTeamFeedback, String> {

	/**  
	 * @Description: 根据培训机构查询反馈数量
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月12日 上午10:04:05 
	 */ 
	int countByTeam(String id);

	/**  
	 * @Description: 根据培训机构查询反馈
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月12日 下午8:41:37 
	 */ 
	List<TrainTeamFeedback> selectByTeam(String id);

}
