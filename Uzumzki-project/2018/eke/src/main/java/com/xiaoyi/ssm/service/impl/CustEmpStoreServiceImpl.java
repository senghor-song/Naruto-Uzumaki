package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.CustEmpStoreMapper;
import com.xiaoyi.ssm.model.CustEmpStore;
import com.xiaoyi.ssm.service.CustEmpStoreService;

/**  
 * @Description: 客户顾问业务逻辑实现层
 * @author 宋高俊  
 * @date 2018年8月6日 上午9:24:51 
 */ 
@Service
public class CustEmpStoreServiceImpl extends AbstractService<CustEmpStore,String> implements CustEmpStoreService{

	@Autowired
	private CustEmpStoreMapper custEmpStoreMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(custEmpStoreMapper);
	}

}
