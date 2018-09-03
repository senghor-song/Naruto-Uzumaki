package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.VenueLog;

public interface VenueLogMapper extends BaseMapper<VenueLog, String>{
	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	Integer countByVenue(String id);
	
	/**  
	 * @Description: 根据场馆ID获取日志数据
	 * @author 宋高俊  
	 * @date 2018年8月20日 下午3:02:24 
	 */ 
	List<VenueLog> selectByVenue(String venueid);
}