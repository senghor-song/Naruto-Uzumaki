package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.Venue;

public interface VenueMapper extends BaseMapper<Venue, String>{


	/**  
	 * @Description: 根据管理员ID查询唯一管理的场馆
	 * @author 宋高俊  
	 * @param managerid
	 * @return 
	 * @date 2018年9月10日 下午4:20:09 
	 */ 
	Venue selectByManager(String managerid);

	/**  
	 * @Description: 查询常用场馆
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月10日 下午4:19:24 
	 */ 
	List<Venue> selectByOftenMember(String id);

	/**  
	 * @Description: 根据场馆名查询场馆
	 * @author 宋高俊  
	 * @param venuename
	 * @return 
	 * @date 2018年9月21日 下午8:58:30 
	 */ 
	Venue selectByVenueName(String venuename);
}