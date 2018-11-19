package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TrainTeamCoachMapper;
import com.xiaoyi.ssm.model.TrainTeamCoach;
import com.xiaoyi.ssm.service.TrainTeamCoachService;

/**  
 * @Description: 培训机构拥有教练业务逻辑实现
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:06:32 
 */ 
@Service
public class TrainTeamCoachServiceImpl extends AbstractService<TrainTeamCoach,String> implements TrainTeamCoachService{

	@Autowired
	private TrainTeamCoachMapper trainTeamCoachMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(trainTeamCoachMapper);
	}
	
	/**  
	 * @Description: 根据培训机构查询拥有教练
	 * @author 宋高俊  
	 * @param trainTeamID
	 * @return 
	 * @date 2018年10月30日 下午6:00:46 
	 */ 
	@Override
	public List<TrainTeamCoach> selectByTrainTeamID(String trainTeamID) {
		return trainTeamCoachMapper.selectByTrainTeamID(trainTeamID);
	}

	/**  
	 * @Description: 根据培训机构和教练查询
	 * @author 宋高俊  
	 * @param trainCoachId
	 * @param trainTeamId
	 * @return 
	 * @date 2018年10月30日 下午8:54:44 
	 */ 
	@Override
	public TrainTeamCoach selectByCoachTeam(String trainCoachId, String trainTeamId) {
		return trainTeamCoachMapper.selectByCoachTeam(trainCoachId, trainTeamId);
	}

	/**  
	 * @Description: 根据用户和培训机构查询
	 * @author 宋高俊  
	 * @param trainTeamId
	 * @return 
	 * @date 2018年10月30日 下午8:54:44 
	 */ 
	@Override
	public TrainTeamCoach selectByMemberTeam(String memberId, String trainTeamId) {
		return trainTeamCoachMapper.selectByMemberTeam(memberId, trainTeamId);
	}

}
