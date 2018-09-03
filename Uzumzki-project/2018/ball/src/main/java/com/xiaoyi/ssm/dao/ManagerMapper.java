package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.Manager;
import com.xiaoyi.ssm.model.Venue;

public interface ManagerMapper extends BaseMapper<Manager, String>{
	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	Integer countByVenue(String id);

//	/**  
//	 * @Description: 管理员登录接口
//	 * @author 宋高俊  
//	 * @date 2018年8月18日 下午4:46:48 
//	 */ 
//	Manager login(Manager manager);
	
	/**  
	 * @Description: 根据场馆ID查询管理员
	 * @author 宋高俊  
	 * @date 2018年8月20日 下午2:22:29 
	 */ 
	List<Manager> selectByVenue(String venueid);

	/**  
	 * @Description: 管理登录方法
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午4:45:02 
	 */ 
	Manager login(@Param("phone")String phone, @Param("password")String password);
	
	/**  
	 * @Description: 根据管理员和场馆查询该管理员是否管理该场馆
	 * @author 宋高俊  
	 * @date 2018年8月21日 上午9:22:50 
	 */ 
	Integer selectByManagerAndVenue(@Param("managerid")String managerid, @Param("venueid")String venueid);
}