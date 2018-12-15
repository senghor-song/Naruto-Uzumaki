package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.WXCompanyPayment;

public interface WXCompanyPaymentMapper extends BaseMapper<WXCompanyPayment, String>{

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