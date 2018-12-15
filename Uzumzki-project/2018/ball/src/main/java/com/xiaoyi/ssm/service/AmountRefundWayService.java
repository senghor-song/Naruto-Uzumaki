package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.AmountRefundWay;

/**
 * @Description: 退款费率逻辑层
 * @author 宋高俊
 * @date 2018年11月28日下午5:25:10
 */
public interface AmountRefundWayService extends BaseService<AmountRefundWay, String> {

	/**  
	 * @Description: 根据场馆ID查询退费费率
	 * @author 宋高俊  
	 * @param id
	 * @return 
	 * @date 2018年9月7日 下午5:58:06 
	 */ 
	AmountRefundWay selectByVenue(String id);

	/**
	 * @Description: 退费费率逻辑删除
	 * @author 宋高俊
	 * @param venueid
	 * @param dateStr
	 * @return
	 * @date 2018年11月21日 下午8:51:49
	 */
	int updateByVenue(String venueid, String dateStr);

}
