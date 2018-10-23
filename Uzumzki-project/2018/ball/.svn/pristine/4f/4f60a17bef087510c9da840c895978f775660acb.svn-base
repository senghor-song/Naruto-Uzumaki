package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TrainTeamFeedbackMapper;
import com.xiaoyi.ssm.model.TrainTeamFeedback;
import com.xiaoyi.ssm.service.TrainTeamFeedbackService;

/**  
 * @Description: 培训机构反馈业务逻辑实现
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:06:32 
 */ 
@Service
public class TrainTeamFeedbackServiceImpl extends AbstractService<TrainTeamFeedback,String> implements TrainTeamFeedbackService{

	@Autowired
	private TrainTeamFeedbackMapper trainTeamFeedbackMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(trainTeamFeedbackMapper);
	}

	/**  
	 * @Description: 根据培训机构查询反馈数量
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月12日 上午10:04:05 
	 */ 
	@Override
	public int countByTeam(String id) {
		return trainTeamFeedbackMapper.countByTeam(id);
	}

	/**  
	 * @Description: 根据培训机构查询反馈
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月12日 下午8:41:37 
	 */ 
	@Override
	public List<TrainTeamFeedback> selectByTeam(String id) {
		return trainTeamFeedbackMapper.selectByTeam(id);
	}

}
