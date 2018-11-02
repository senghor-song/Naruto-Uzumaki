package com.xiaoyi.ssm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.VenueStatis;

public interface VenueStatisMapper extends BaseMapper<VenueStatis, String> {

	/**
	 * @Description: 根据场馆ID获取日统计数据
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年9月7日 上午9:44:00
	 */
	List<VenueStatis> selectByVenue(@Param("venueid") String venueid, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**  
	 * @Description: 查询当天是否已经设置过统计
	 * @author 宋高俊  
	 * @param venueid
	 * @param date
	 * @return 
	 * @date 2018年10月31日 下午5:22:04 
	 */ 
	VenueStatis selectByOldVenueStatis(@Param("venueid")String venueid, @Param("date")Date date);

}