package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.EmpCustMapper;
import com.xiaoyi.ssm.model.EmpCust;
import com.xiaoyi.ssm.service.EmpCustService;

/**  
 * @Description: 私客业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月30日 下午3:37:44 
 */ 
@Service
public class EmpCustServiceImpl extends AbstractService<EmpCust,String> implements EmpCustService{

	@Autowired
	private EmpCustMapper empCustMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(empCustMapper);
	}

	@Override
	public Integer countByEmp(String empId) {
		return empCustMapper.countByEmp(empId);
	}
	
}
