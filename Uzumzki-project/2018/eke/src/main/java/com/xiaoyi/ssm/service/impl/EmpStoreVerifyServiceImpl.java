package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.EmpStoreVerifyMapper;
import com.xiaoyi.ssm.model.EmpStoreVerify;
import com.xiaoyi.ssm.service.EmpStoreVerifyService;

/**  
 * @Description: 后台交流业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月31日 上午9:18:15 
 */ 
@Service
public class EmpStoreVerifyServiceImpl extends AbstractService<EmpStoreVerify,String> implements EmpStoreVerifyService{

	@Autowired
	private EmpStoreVerifyMapper empStoreVerifyMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(empStoreVerifyMapper);
	}

	@Override
	public EmpStoreVerify selectVerifyByStore(String id) {
		return empStoreVerifyMapper.selectVerifyByStore(id);
	}

	@Override
	public Integer countByNoDispose() {
		return empStoreVerifyMapper.countByNoDispose();
	}
	
}
