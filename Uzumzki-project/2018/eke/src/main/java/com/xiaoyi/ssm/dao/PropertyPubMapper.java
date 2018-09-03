package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.PropertyPub;

/**  
 * @Description: 分发公盘记录数据访问层
 * @author 宋高俊  
 * @date 2018年7月28日 下午4:51:10 
 */ 
public interface PropertyPubMapper extends BaseMapper<PropertyPub, String> {
	/**  
	 * @Description: 根据公盘统计认领次数
	 * @author 宋高俊  
	 * @date 2018年7月28日 下午4:51:30 
	 */ 
	Integer countClaimByPropertypre(String propertyPreId);
	
	/**  
	 * @Description: 根据公盘统计分发次数
	 * @author 宋高俊  
	 * @date 2018年7月28日 下午4:52:02 
	 */ 
	Integer countPubByPropertypre(String propertyPreId);
	
	/**  
	 * @Description: 根据公盘查询分发记录
	 * @author 宋高俊  
	 * @date 2018年8月6日 下午4:59:46 
	 */ 
	List<PropertyPub> selectByPropertyPre(String id);
}