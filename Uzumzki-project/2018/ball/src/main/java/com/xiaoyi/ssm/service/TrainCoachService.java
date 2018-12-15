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
	 * @Description: 根据用户查询是否已成为某个培训机构的教练
	 * @author 宋高俊  
	 * @param memberId
	 * @return 
	 * @date 2018年10月30日 下午7:47:15 
	 */ 
	TrainCoach selectByMember(String memberId);

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

	/**  
	 * @Description: 根据场馆查询陪练
	 * @author 宋高俊  
	 * @param venueid
	 * @param trainTeamId 
	 * @return 
	 * @date 2018年11月5日 下午4:52:39 
	 */ 
	List<TrainCoach> selectByVenue(String venueid, String trainTeamId);

	/**  
	 * @Description: 查询默认陪练
	 * @author 宋高俊  
	 * @param cityId
	 * @return 
	 * @date 2018年11月6日 下午5:40:26 
	 */ 
	TrainCoach selectByDefault(String cityId);

    /**
     * @Description: 查询机构的馆长
     * @author 宋高俊
     * @param trainTeamId
     * @return
     * @date 2018年11月6日 下午5:40:26
     */
    TrainCoach selectByTeamManager(String trainTeamId);

	/**  
	 * @Description: 根据用户ID和培训机构ID查询指定身份的教练
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年10月8日 下午7:07:42 
	 */ 
	TrainCoach selectByMemberTeamManager(String memberId, String teamId);

	/**
	 * @Description: 根据ID查询教练
	 * @author 宋高俊
	 * @param coachid
	 * @return
	 * @date 2018年12月6日下午8:54:20
	 */
	TrainCoach selectByTrainCoachId(String coachid);
}
