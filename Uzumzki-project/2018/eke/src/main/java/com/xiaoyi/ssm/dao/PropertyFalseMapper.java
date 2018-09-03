package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.PropertyFalse;

public interface PropertyFalseMapper extends BaseMapper<PropertyFalse, String>{
	
	/**  
	 * @Description: 根据房源ID统计房源投诉次数
	 * @author 宋高俊  
	 * @date 2018年8月22日 下午5:16:32 
	 */ 
	Integer countByProperty(String id);
}