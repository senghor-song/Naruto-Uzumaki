package com.xiaoyi.ssm.service;

import com.xiaoyi.ssm.model.AmountRefund;

/**  
 * @Description: 退款业务逻辑层
 * @author 宋高俊  
 * @date 2018年9月11日 下午5:01:19 
 */ 
public interface AmountRefundService extends BaseService<AmountRefund, String> {

	/**
	 * @Description: 根据订单ID查询最新的退款数据
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年12月13日下午3:37:00
	 */
	AmountRefund selectByNowSourceId(String sourceId);

}
