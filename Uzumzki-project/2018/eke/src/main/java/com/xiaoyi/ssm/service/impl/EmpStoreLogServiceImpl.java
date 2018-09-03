package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.EmpStoreLogMapper;
import com.xiaoyi.ssm.model.EmpStoreLog;
import com.xiaoyi.ssm.service.EmpStoreLogService;

/**  
 * @Description: 商户变更日志
 * @author 宋高俊  
 * @date 2018年8月1日 上午10:27:35 
 */ 
@Service
public class EmpStoreLogServiceImpl extends AbstractService<EmpStoreLog,String> implements EmpStoreLogService{

	@Autowired
	private EmpStoreLogMapper empStoreLogMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(empStoreLogMapper);
	}

	@Override
	public Integer countByEmpStore(String id) {
		return empStoreLogMapper.countByEmpStore(id);
	}
	
}
