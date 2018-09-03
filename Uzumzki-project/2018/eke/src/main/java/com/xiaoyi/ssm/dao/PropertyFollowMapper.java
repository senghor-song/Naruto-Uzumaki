package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.PropertyFollow;

/**  
 * @Description: 房源跟进数据访问层
 * @author 宋高俊  
 * @date 2018年8月2日 下午4:19:38 
 */ 
public interface PropertyFollowMapper extends BaseMapper<PropertyFollow, String>{

	/**  
	 * @Description: 根据房源查询房源跟进数量
	 * @author 宋高俊  
	 * @date 2018年8月2日 下午4:14:54 
	 */ 
	Integer countByProperty(String id);

}