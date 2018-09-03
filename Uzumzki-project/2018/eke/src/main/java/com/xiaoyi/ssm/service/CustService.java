package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.Cust;

/**  
 * @Description: 注册用户业务逻辑接口
 * @author 宋高俊  
 * @date 2018年7月18日 下午4:19:03 
 */ 
public interface CustService extends BaseService<Cust, String> {

	/**  
	 * @Description: 根据手机号判断该客户是否存在
	 * @author 宋高俊  
	 * @date 2018年7月20日 上午11:48:13 
	 */ 
	Cust getPhoneRegister(String mobile);
	
	/**  
	 * @Description: 用户登录
	 * @author 宋高俊  
	 * @date 2018年7月18日 下午4:19:42 
	 */ 
	Cust login(Cust cust);

	/**  
	 * @Description: 用户注册
	 * @author 宋高俊  
	 * @date 2018年7月18日 下午4:19:42 
	 */ 
	int register(Cust cust);
	
}
