package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.TrainCoach;

/**  
 * @Description: 培训教练业务逻辑层
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:04:23 
 */ 
public interface TrainCoachService extends BaseService<TrainCoach, String> {

	/** 
	 * @Description: 根据培训机构ID获取培训教练
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月29日 下午8:48:31 
	 */
	List<TrainCoach> selectByTrainTeamID(String id);

	/**  
	 * @Description: 根据用户ID查询教练
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月8日 下午3:03:31 
	 */ 
	TrainCoach selectByMemberId(String id);

	/**  
	 * @Description: 根据用户ID和培训机构ID查询教练
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年10月8日 下午7:07:42 
	 */ 
	TrainCoach selectByMemberTeam(String memberId, String teamId);

	/**  
	 * @Description: 统计培训机构下的教练团队
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月10日 下午7:28:10 
	 */ 
	int countByTeam(String id);
}
