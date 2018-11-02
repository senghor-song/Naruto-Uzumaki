package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.TrainTeamCoach;

/**  
 * @Description: 培训机构拥有教练业务逻辑层
 * @author 宋高俊  
 * @date 2018年10月23日 下午8:23:41 
 */ 
public interface TrainTeamCoachService extends BaseService<TrainTeamCoach, String> {

	/**  
	 * @Description: 根据培训机构查询拥有教练
	 * @author 宋高俊  
	 * @param trainTeamID
	 * @return 
	 * @date 2018年10月30日 下午6:00:46 
	 */ 
	List<TrainTeamCoach> selectByTrainTeamID(String trainTeamID);

	/**  
	 * @Description: 根据培训机构和教练查询
	 * @author 宋高俊  
	 * @param trainCoachId
	 * @param trainTeamId
	 * @return 
	 * @date 2018年10月30日 下午8:54:44 
	 */ 
	TrainTeamCoach selectByCoachTeam(String trainCoachId, String trainTeamId);

	/**  
	 * @Description: 根据用户和培训机构查询
	 * @author 宋高俊  
	 * @param trainCoachId
	 * @param trainTeamId
	 * @return 
	 * @date 2018年10月30日 下午8:54:44 
	 */ 
	TrainTeamCoach selectByMemberTeam(String memberId, String trainTeamId);

}
