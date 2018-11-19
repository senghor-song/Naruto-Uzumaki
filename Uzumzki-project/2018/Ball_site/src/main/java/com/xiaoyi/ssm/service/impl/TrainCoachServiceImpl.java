package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TrainCoachMapper;
import com.xiaoyi.ssm.model.TrainCoach;
import com.xiaoyi.ssm.service.TrainCoachService;

/**  
 * @Description: 培训机构业务逻辑实现
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:06:32 
 */ 
@Service
public class TrainCoachServiceImpl extends AbstractService<TrainCoach,String> implements TrainCoachService{

	@Autowired
	private TrainCoachMapper trainCoachMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(trainCoachMapper);
	}
	
	/** 
	 * @Description: 根据培训机构ID获取培训教练
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月29日 下午8:48:31 
	 */
	@Override
	public List<TrainCoach> selectByTrainTeamID(String id) {
		return trainCoachMapper.selectByTrainTeamID(id);
	}

	/**  
	 * @Description: 根据用户查询是否已成为某个培训机构的教练
	 * @author 宋高俊  
	 * @return
	 * @date 2018年10月30日 下午7:47:15 
	 */  
	@Override
	public TrainCoach selectByMember(String id) {
		return trainCoachMapper.selectByMember(id);
	}
	
	/**  
	 * @Description: 根据用户ID和培训机构ID查询教练
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年10月8日 下午7:07:42 
	 */ 
	@Override
	public TrainCoach selectByMemberTeam(String memberId, String teamId) {
		return trainCoachMapper.selectByMemberTeam(memberId, teamId);
	}

	/**  
	 * @Description: 统计培训机构下的教练团队
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月10日 下午7:28:10 
	 */ 
	@Override
	public int countByTeam(String id) {
		return trainCoachMapper.countByTeam(id);
	}

	/**  
	 * @Description: 根据场馆查询陪练
	 * @author 宋高俊  
	 * @param venueid
	 * @return 
	 * @date 2018年11月5日 下午4:52:39 
	 */ 
	@Override
	public List<TrainCoach> selectByVenue(String venueid) {
		return trainCoachMapper.selectByVenue(venueid);
	}

	/**  
	 * @Description: 查询默认陪练
	 * @author 宋高俊  
	 * @param cityId
	 * @return 
	 * @date 2018年11月6日 下午5:40:26 
	 */ 
	@Override
	public TrainCoach selectByDefault(String cityId) {
		return trainCoachMapper.selectByDefault(cityId);
	}

	/**
	 * @Description: 查询机构的店长
	 * @author 宋高俊
	 * @param trainTeamId
	 * @return
	 * @date 2018年11月6日 下午5:40:26
	 */
	@Override
	public TrainCoach selectByTeamManager(String trainTeamId) {
		return trainCoachMapper.selectByTeamManager(trainTeamId);
	}

	/**  
	 * @Description: 根据用户ID和培训机构ID查询指定身份的教练
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年10月8日 下午7:07:42 
	 */ 
	@Override
	public TrainCoach selectByMemberTeamManager(String memberId, String teamId) {
		return trainCoachMapper.selectByMemberTeamManager(memberId, teamId);
	}

}
