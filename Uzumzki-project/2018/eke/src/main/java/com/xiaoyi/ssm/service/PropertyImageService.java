package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.PropertyImage;

/**  
 * @Description: 室内图业务逻辑层
 * @author 宋高俊  
 * @date 2018年8月2日 下午4:12:55 
 */ 
public interface PropertyImageService extends BaseService<PropertyImage, String>{
	
	/**  
	 * @Description: 根据房源查询室内图数量
	 * @author 宋高俊  
	 * @date 2018年8月2日 下午4:14:54 
	 */ 
	Integer countByProperty(String id);
}
