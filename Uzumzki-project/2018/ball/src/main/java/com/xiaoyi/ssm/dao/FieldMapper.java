package com.xiaoyi.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyi.ssm.model.Field;

public interface FieldMapper extends BaseMapper<Field, String>{
	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	Integer countByVenue(String id);

	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	List<Field> selectByVenue(String id);

	/**
	 * @Description: 场地逻辑删除
	 * @author 宋高俊
	 * @param venueid
	 * @param dateStr
	 * @return
	 * @date 2018年11月21日 下午8:29:25
	 */
	int updateByVenue(@Param("venueid")String venueid, @Param("dateStr")String dateStr);
	
	/**
	 * @Description: 根据模板ID查询默认使用的场地
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年12月14日上午10:33:03
	 */
	List<Field> selectByDefaultVenue(@Param("venueId")String venueId, @Param("venueTemplateId")String venueTemplateId);

	/**
	 * @Description: 根据场馆ID和场地名称查询
	 * @author 宋高俊
	 * @param id
	 * @param fieldName
	 * @return
	 * @date 2018年12月15日下午4:06:39
	 */
	Field selectByVenueName(@Param("venueid")String venueid, @Param("fieldName")String fieldName);
}