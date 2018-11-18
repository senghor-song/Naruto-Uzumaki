package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.VenueRefund;

/**  
 * @Description: 场馆退费申请业务逻辑层
 * @author 宋高俊  
 * @date 2018年11月16日17:18:06
 */ 
public interface VenueRefundService extends BaseService<VenueRefund, String> {

	/**  
	 * @Description: 查询是否有申请过
	 * @author 宋高俊
	 * @param orderid
	 * @return
	 * @date 2018年11月16日 下午5:24:59
	 */
	VenueRefund selectByOrder(String orderid);

	/**
	 * @Description: 查询场馆的所有申请
	 * @author 宋高俊
	 * @param venueid
	 * @return
	 * @date 2018年11月16日 下午7:37:42
	 */
	List<VenueRefund> selectByVenue(String venueid);

	/**
	 * @Description: 统计申请数量
	 * @author 宋高俊
	 * @param venueid
	 * @return
	 * @date 2018年11月16日 下午8:18:15
	 */
	Integer countByVenue(String venueid);
	
}
