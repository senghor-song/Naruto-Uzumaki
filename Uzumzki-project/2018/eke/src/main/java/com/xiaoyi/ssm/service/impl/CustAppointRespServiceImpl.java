package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.CustAppointRespMapper;
import com.xiaoyi.ssm.model.CustAppointResp;
import com.xiaoyi.ssm.service.CustAppointRespService;

/**  
 * @Description: 约看业务逻辑实现层
 * @author 宋高俊  
 * @date 2018年7月26日 下午4:14:19 
 */ 
@Service
public class CustAppointRespServiceImpl extends AbstractService<CustAppointResp,String> implements CustAppointRespService{

	@Autowired
	private CustAppointRespMapper custAppointRespMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(custAppointRespMapper);
	}
	
}
