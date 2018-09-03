package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.Manager;

/**  
 * @Description: 管理员业务逻辑接口
 * @author 宋高俊  
 * @date 2018年8月18日 下午12:00:58 
 */ 
public interface ManagerService extends BaseService<Manager, String> {

	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	Integer countByVenue(String id);

//	/**  
//	 * @Description: 管理登录方法
//	 * @author 宋高俊  
//	 * @date 2018年8月18日 下午4:45:02 
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
	Manager login(String phone, String password);
	
	/**  
	 * @Description: 根据管理员和场馆查询该管理员是否管理该场馆
	 * @author 宋高俊  
	 * @date 2018年8月21日 上午9:22:50 
	 */ 
	Integer selectByManagerAndVenue(String managerid, String venueid);

}
