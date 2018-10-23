package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TrainTeamLogMapper;
import com.xiaoyi.ssm.model.TrainTeamLog;
import com.xiaoyi.ssm.service.TrainTeamLogService;

/**  
 * @Description: 培训机构日志业务逻辑实现
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:06:32 
 */ 
@Service
public class TrainTeamLogServiceImpl extends AbstractService<TrainTeamLog,String> implements TrainTeamLogService{

	@Autowired
	private TrainTeamLogMapper trainTeamLogMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(trainTeamLogMapper);
	}
	
	/**  
	 * @Description: 根据培训机构统计日志数量
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月11日 下午8:52:37 
	 */ 
	@Override
	public int countByTeam(String id) {
		return trainTeamLogMapper.countByTeam(id);
	}

	/**  
	 * @Description: 根据培训机构获取日志
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月12日 下午8:27:28 
	 */ 
	@Override
	public List<TrainTeamLog> selectByTeam(String id) {
		// TODO Auto-generated method stub
		return trainTeamLogMapper.selectByTeam(id);
	}

}
