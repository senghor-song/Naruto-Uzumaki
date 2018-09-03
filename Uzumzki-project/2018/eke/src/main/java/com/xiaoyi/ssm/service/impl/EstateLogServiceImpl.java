package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.EstateLogMapper;
import com.xiaoyi.ssm.model.EstateLog;
import com.xiaoyi.ssm.service.EstateLogService;

/**  
 * @Description: 小区日志业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月5日 下午7:44:44 
 */ 
@Service
public class EstateLogServiceImpl extends AbstractService<EstateLog,String> implements EstateLogService{

	@Autowired
	private EstateLogMapper estateLogMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(estateLogMapper);
	}

	@Override
	public Integer countLogByEstate(String id) {
		return estateLogMapper.countLogByEstate(id);
	}


}
