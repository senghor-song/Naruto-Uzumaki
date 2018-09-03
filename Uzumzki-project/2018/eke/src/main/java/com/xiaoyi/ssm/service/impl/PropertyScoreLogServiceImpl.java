package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.PropertyScoreLogMapper;
import com.xiaoyi.ssm.model.PropertyScoreLog;
import com.xiaoyi.ssm.service.PropertyScoreLogService;

/**  
 * @Description: 房源积分记录业务逻辑实现
 * @author 宋高俊  
 * @date 2018年8月6日 下午5:38:57 
 */ 
@Service
public class PropertyScoreLogServiceImpl extends AbstractService<PropertyScoreLog,String> implements PropertyScoreLogService{

	@Autowired
	private PropertyScoreLogMapper propertyScoreLogMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(propertyScoreLogMapper);
	}

}
