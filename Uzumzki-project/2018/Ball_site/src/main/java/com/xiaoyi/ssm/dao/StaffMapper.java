package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.Staff;

/**  
 * @Description: 管理员数据访问层
 * @author 宋高俊  
 * @date 2018年7月25日 下午5:03:48 
 */ 
public interface StaffMapper extends BaseMapper<Staff, String>{

	/**  
	 * @Description: 登录方法
	 * @author 宋高俊  
	 * @date 2018年7月25日 下午5:03:38 
	 */ 
	Staff login(Staff staff);

	/**  
	 * @Description: 根据手机号查询
	 * @author 宋高俊  
	 * @date 2018年7月26日 下午3:21:21 
	 */ 
	Staff getPhoneRegister(String phone);
	
}