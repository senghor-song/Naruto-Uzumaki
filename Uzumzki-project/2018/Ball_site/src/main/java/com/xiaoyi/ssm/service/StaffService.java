package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.Staff;

/**  
 * @Description: 管理员账号业务逻辑层
 * @author 宋高俊  
 * @date 2018年7月25日 下午2:33:43 
 */ 
public interface StaffService extends BaseService<Staff, String> {

	/**  
	 * @Description: 登录方法
	 * @author 宋高俊  
	 * @date 2018年7月25日 下午2:34:26 
	 */ 
	Staff login(Staff staff);

	/**  
	 * @Description: 判断该手机号是否被注册过
	 * @author 宋高俊  
	 * @date 2018年7月26日 下午3:20:34 
	 */ 
	Staff getPhoneRegister(String phone);

}
