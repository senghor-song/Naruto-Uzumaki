package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.TrainTrialMapper;
import com.xiaoyi.ssm.model.TrainTrial;
import com.xiaoyi.ssm.service.TrainTrialService;

/**  
 * @Description: 培训机构业务逻辑实现
 * @author 宋高俊  
 * @date 2018年9月29日 下午8:06:32 
 */ 
@Service
public class TrainTrialServiceImpl extends AbstractService<TrainTrial,String> implements TrainTrialService{

	@Autowired
	private TrainTrialMapper trainTrialMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(trainTrialMapper);
	}

}
