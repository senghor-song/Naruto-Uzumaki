package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.VenueError;

public interface VenueErrorMapper extends BaseMapper<VenueError, String>{

	/**  
	 * @Description: 根据场馆统计纠错数据
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年10月17日 上午10:02:59 
	 */ 
	Integer countByVenue(String id);

	/**  
	 * @Description: 根据场馆查询纠错数据
	 * @author 宋高俊  
	 * @param venueid
	 * @return 
	 * @date 2018年11月1日 上午10:06:42 
	 */ 
	List<VenueError> selectByVenue(String venueid);
}