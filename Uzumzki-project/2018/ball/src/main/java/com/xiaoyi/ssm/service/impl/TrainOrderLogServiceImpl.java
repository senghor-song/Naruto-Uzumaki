package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TrainOrderLogMapper;
import com.xiaoyi.ssm.model.TrainOrderLog;
import com.xiaoyi.ssm.service.TrainOrderLogService;

/**  
 * @Description: 培训课程日志业务逻辑实现
 * @author 宋高俊  
 * @date 2018年10月30日 上午9:37:15 
 */ 
@Service
public class TrainOrderLogServiceImpl extends AbstractService<TrainOrderLog,String> implements TrainOrderLogService{

	@Autowired
	private TrainOrderLogMapper trainOrderLogMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(trainOrderLogMapper);
	}

	/**  
	 * @Description: 根据订单ID查询订单日志
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月30日 上午9:42:44 
	 */ 
	@Override
	public List<TrainOrderLog> selectByOrder(String id) {
		return trainOrderLogMapper.selectByOrder(id);
	}
	
}
