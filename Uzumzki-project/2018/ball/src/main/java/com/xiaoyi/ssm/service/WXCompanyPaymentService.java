package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.WXCompanyPayment;

/**  
 * @Description: 企业支付业务逻辑层
 * @author 宋高俊  
 * @date 2018年10月11日 下午5:05:38 
 */ 
public interface WXCompanyPaymentService extends BaseService<WXCompanyPayment, String> {

	/**
	 * @Description: 根据订单ID查询最新的支付信息
	 * @author 宋高俊
	 * @param orderid
	 * @return
	 * @date 2018年11月27日下午4:57:23
	 */
	WXCompanyPayment selectByOrder(String orderid);

	/**
	 * @Description: 查询该订单的所有支付记录
	 * @author 宋高俊
	 * @param orderid
	 * @return
	 * @date 2018年11月30日下午3:39:55
	 */
	List<WXCompanyPayment> selectByOrderLog(String orderid);

}
