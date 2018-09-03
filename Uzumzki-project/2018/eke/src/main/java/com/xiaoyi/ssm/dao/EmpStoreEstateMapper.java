package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.EmpStoreEstate;

/**  
 * @Description: 商户责任小区数据访问层
 * @author 宋高俊  
 * @date 2018年8月1日 下午5:00:31 
 */ 
public interface EmpStoreEstateMapper extends BaseMapper<EmpStoreEstate, String>{
	
	/**  
	 * @Description: 根据商户统计数据
	 * @author 宋高俊  
	 * @date 2018年8月1日 下午5:00:53 
	 */ 
	Integer countByEstateType(String id);
}