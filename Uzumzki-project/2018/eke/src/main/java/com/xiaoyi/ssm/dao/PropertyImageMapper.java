package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.PropertyImage;

/**  
 * @Description: 室内图数据访问层
 * @author 宋高俊  
 * @date 2018年8月2日 下午4:15:52 
 */ 
public interface PropertyImageMapper extends BaseMapper<PropertyImage, String>{
	/**  
	 * @Description: 根据房源查询室内图数量
	 * @author 宋高俊  
	 * @date 2018年8月2日 下午4:14:54 
	 */ 
	Integer countByProperty(String id);
}