package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.CustGrabMapper;
import com.xiaoyi.ssm.model.CustGrab;
import com.xiaoyi.ssm.service.CustGrabService;

/**  
 * @Description: 主动抢客业务逻辑接口
 * @author 宋高俊  
 * @date 2018年7月31日 上午9:30:39 
 */ 
@Service
public class CustGrabServiceImpl extends AbstractService<CustGrab,String> implements CustGrabService{

	@Autowired
	private CustGrabMapper custGrabMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(custGrabMapper);
	}

	@Override
	public Integer countByEmpId(String empId) {
		return custGrabMapper.countByEmpId(empId);
	}

}
