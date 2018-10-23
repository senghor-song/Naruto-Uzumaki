package com.xiaoyi.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.StaffMapper;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.service.StaffService;

/**  
 * @Description: 管理员业务逻辑实现
 * @author 宋高俊  
 * @date 2018年7月25日 下午5:11:08 
 */ 
@Service
public class StaffServiceImpl extends AbstractService<Staff,String> implements StaffService{

	@Autowired
	private StaffMapper staffMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(staffMapper);
	}

	/**  
	 * @Description: 登录方法
	 * @author 宋高俊  
	 * @date 2018年7月25日 下午5:03:29 
	 */ 
	@Override
	public Staff login(Staff staff) {
		return staffMapper.login(staff);
	}

	/**  
	 * @Description: 判断该手机号是否被注册过
	 * @author 宋高俊  
	 * @date 2018年7月26日 下午3:20:54 
	 */ 
	@Override
	public Staff getPhoneRegister(String phone) {
		return staffMapper.getPhoneRegister(phone);
	}

}
