package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.Cust;

/**  
 * @Description: 注册用户数据访问接口
 * @author 宋高俊  
 * @date 2018年7月18日 下午4:26:25 
 */ 
public interface CustMapper extends BaseMapper<Cust, String> {
	
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

	/**  
	 * @Description: 根据手机号判断该客户是否存在
	 * @author 宋高俊  
	 * @date 2018年7月20日 上午11:50:13 
	 */ 
	Cust getPhoneRegister(String mobile);
}