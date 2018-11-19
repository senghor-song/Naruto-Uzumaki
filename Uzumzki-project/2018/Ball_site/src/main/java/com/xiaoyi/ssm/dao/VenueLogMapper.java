package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	
	/**  
	 * @Description: 条件查询场馆日志数据
	 * @author 宋高俊  
	 * @param selectType
	 * @param keyword
	 * @return 
	 * @date 2018年10月15日 下午3:12:12 
	 */ 
	List<VenueLog> selectBySearch(@Param("selectType")Integer selectType, @Param("keyword")String keyword);
}