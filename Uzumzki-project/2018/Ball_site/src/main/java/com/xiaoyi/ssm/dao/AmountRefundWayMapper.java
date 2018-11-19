package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.AmountRefundWay;

public interface AmountRefundWayMapper extends BaseMapper< AmountRefundWay, String>{
	
	/**  
	 * @Description: 根据场馆ID查询退费费率
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月7日 下午5:58:06 
	 */ 
	AmountRefundWay selectByVenue(String id);
}