package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.PropertyFollow;

/**  
 * @Description: 房源跟进业务逻辑层
 * @author 宋高俊  
 * @date 2018年8月2日 下午4:12:55 
 */ 
public interface PropertyFollowService extends BaseService<PropertyFollow, String>{
	
	/**  
	 * @Description: 根据房源查询房源跟进数量
	 * @author 宋高俊  
	 * @date 2018年8月2日 下午4:14:54 
	 */ 
	Integer countByProperty(String id);
}
