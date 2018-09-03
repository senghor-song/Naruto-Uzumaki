package com.xiaoyi.ssm.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.CustMapper;
import com.xiaoyi.ssm.model.Cust;
import com.xiaoyi.ssm.service.CustService;
import com.xiaoyi.ssm.util.Utils;

/**  
 * @Description: 注册用户业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月5日 下午7:44:44 
 */ 
@Service
public class CustServiceImpl extends AbstractService<Cust,String> implements CustService{

	@Autowired
	private CustMapper custMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(custMapper);
	}

	@Override
	public Cust login(Cust cust) {
		return custMapper.login(cust);
	}

	@Override
	public int register(Cust cust) {
		cust.setId(Utils.getUUID());
		cust.setRegdate(new Date());
		return custMapper.insertSelective(cust);
	}

	@Override
	public Cust getPhoneRegister(String mobile) {
		return custMapper.getPhoneRegister(mobile);
	}


}
