package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.Field;

public interface FieldMapper extends BaseMapper<Field, String>{
	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	Integer countByVenue(String id);
}