package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.EmpStoreEstateMapper;
import com.xiaoyi.ssm.model.EmpStoreEstate;
import com.xiaoyi.ssm.service.EmpStoreEstateService;

/**  
 * @Description: 小区日志业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月5日 下午7:44:44 
 */ 
@Service
public class EmpStoreEstateServiceImpl extends AbstractService<EmpStoreEstate,String> implements EmpStoreEstateService{

	@Autowired
	private EmpStoreEstateMapper empStoreEstateMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(empStoreEstateMapper);
	}

	@Override
	public Integer countByEstateType(String id) {
		return empStoreEstateMapper.countByEstateType(id);
	}


}
