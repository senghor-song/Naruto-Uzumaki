package com.xiaoyi.ssm.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.dto.ApiMessage;
import com.xiaoyi.ssm.model.VenueStatis;
import com.xiaoyi.ssm.model.VenueTemplate;

/**  
 * @Description: 保存场馆统计数据
 * @author 宋高俊  
 * @date 2018年9月12日 下午4:49:25 
 */ 
public interface VenueStatisService extends BaseService<VenueStatis, String> {
	/**
	 * @Description: 根据场馆ID获取日统计数据
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年9月7日 上午9:44:00
	 */
	List<VenueStatis> selectByVenue(@Param("venueid") String venueid, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	
	/**  
	 * @Description: 保存场馆统计数据
	 * @author 宋高俊  
	 * @return 
	 * @date 2018年9月12日 下午4:49:02 
	 */ 
	ApiMessage saveVenueStatis(String venueid,VenueTemplate venueTemplate,String date);
	
}
