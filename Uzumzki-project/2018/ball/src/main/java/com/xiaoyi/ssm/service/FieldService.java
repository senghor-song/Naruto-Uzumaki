package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.Field;

/**  
 * @Description: 场馆业务逻辑层
 * @author 宋高俊  
 * @date 2018年8月16日 下午5:59:40 
 */ 
public interface FieldService extends BaseService<Field, String> {

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
	int updateByVenue(String venueid, String dateStr);

	/**
	 * @Description: 根据模板ID查询默认使用的场地
	 * @author 宋高俊
	 * @param id
	 * @param string 
	 * @return
	 * @date 2018年12月14日上午10:33:03
	 */
	List<Field> selectByDefaultVenue(String venueId, String venueTemplateId);

	/**
	 * @Description: 根据场馆ID和场地名称查询
	 * @author 宋高俊
	 * @param id
	 * @param fieldName
	 * @return
	 * @date 2018年12月15日下午4:06:39
	 */
	Field selectByVenueName(String venueid, String fieldName);

}
