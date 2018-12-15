package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.AmountRefund;

public interface AmountRefundMapper extends BaseMapper<AmountRefund, String>{
	/**
	 * @Description: 根据订单ID查询最新的退款数据
	 * @author 宋高俊
	 * @param id
	 * @return
	 * @date 2018年12月13日下午3:37:00
	 */
	AmountRefund selectByNowSourceId(String sourceId);
}