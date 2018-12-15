package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.VenueCoach;

public interface VenueCoachMapper extends BaseMapper<VenueCoach, String>{
	
	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	Integer countByVenue(String id);

	/**  
	 * @Description: 根据场馆查询培训的教练
	 * @author 宋高俊  
	 * @param venueid
	 * @return 
	 * @date 2018年10月30日 下午3:28:29 
	 */ 
	List<VenueCoach> selectByVenue(String venueid);

	/**
	 * @Description: 逻辑删除
	 * @author 宋高俊
	 * @param dateStr
	 * @param trainTeamId
	 * @param trainCoachId
	 * @return
	 * @date 2018年11月21日 下午5:07:52
	 */
	int updateByDeleteCoach(@Param("dateStr")String dateStr, @Param("trainTeamId")String trainTeamId, @Param("trainCoachId")String trainCoachId);

	/**
	 * @Description: 陪练数据逻辑删除
	 * @author 宋高俊
	 * @param venueid
	 * @param dateStr
	 * @return
	 * @date 2018年11月21日 下午8:55:36
	 */
	int updateByVenue(@Param("venueid")String venueid, @Param("dateStr")String dateStr);

	/**
	 * @Description: 
	 * @author 宋高俊
	 * @param coachid
	 * @return
	 * @date 2018年12月6日下午8:37:23
	 */
	VenueCoach selectByTrainCoachId(String trainCoachId);
}