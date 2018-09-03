package com.xiaoyi.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MassEmpPayMapper;
import com.xiaoyi.ssm.dao.SysSetMapper;
import com.xiaoyi.ssm.model.MassEmpPay;
import com.xiaoyi.ssm.service.MassEmpPayService;

/**  
 * @Description: 支付业务逻辑接口
 * @author 宋高俊  
 * @date 2018年7月6日 下午7:54:15 
 */
@Service
public class MassEmpPayServiceImpl extends AbstractService<MassEmpPay,String> implements MassEmpPayService{

	@Autowired
	private MassEmpPayMapper massEmpPayMapper;
	@Autowired
	private SysSetMapper sysSetMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(massEmpPayMapper);
	}

	@Override
	public List<MassEmpPay> selectAll(String empId) {
		return massEmpPayMapper.selectAll(empId);
	}

	@Override
	public String selectByPay() {
		return sysSetMapper.selectByPay();
	}


}
