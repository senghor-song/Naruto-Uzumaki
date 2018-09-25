package com.xiaoyi.ssm.dao;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.Member;

public interface MemberMapper extends BaseMapper<Member, String>{

	/**  
	 * @Description: 微信端用户登录接口
	 * @author 宋高俊  
	 * @date 2018年8月16日 下午4:34:06 
	 */ 
	Member login(Member member);
	
	/**  
	 * @Description: 根据手机号查询用户
	 * @author 宋高俊  
	 * @date 2018年8月16日 下午5:06:01 
	 */ 
	Member selectByPhone(String phone);
	
	/**  
	 * @Description: 根据手机号修改密码
	 * @author 宋高俊  
	 * @date 2018年8月16日 下午5:27:29 
	 */ 
	int updateByPhone(@Param("phone")String phone, @Param("password")String password);

	/**  
	 * @Description: 根据openid查询用户
	 * @author 宋高俊  
	 * @param openid
	 * @return 
	 * @date 2018年9月10日 下午8:40:45 
	 */ 
	Member selectByOpenid(String openid);
	
	/**  
	 * @Description: 根据Unionid查询用户
	 * @author 宋高俊  
	 * @param unionid
	 * @return 
	 * @date 2018年9月13日 上午11:35:24 
	 */ 
	Member selectByUnionid(String unionid);
	
}