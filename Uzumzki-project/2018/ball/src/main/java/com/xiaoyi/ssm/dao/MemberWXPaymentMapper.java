package com.xiaoyi.ssm.dao;

import java.util.List;

import com.xiaoyi.ssm.model.MemberWXPayment;

public interface MemberWXPaymentMapper extends BaseMapper<MemberWXPayment, String>{

	/**
	 * @Description: 根据用户ID查询额度明细
	 * @author 宋高俊
	 * @param memberId
	 * @return
	 * @date 2018年12月7日下午4:33:45
	 */
	List<MemberWXPayment> selectByMemberId(String memberId);
}