package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.Coach;

public interface CoachMapper extends BaseMapper<Coach, String>{
	/**  
	 * @Description: 根据场馆ID查询
	 * @author 宋高俊  
	 * @date 2018年8月18日 下午2:03:12 
	 */ 
	Integer countByVenue(String id);
}