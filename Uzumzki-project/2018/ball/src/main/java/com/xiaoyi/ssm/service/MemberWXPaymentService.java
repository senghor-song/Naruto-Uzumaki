package com.xiaoyi.ssm.service;

import java.util.List;

import com.xiaoyi.ssm.model.MemberWXPayment;

/**
 * @Description: 统计日志业务逻辑层
 * @author 宋高俊
 * @date 2018年11月27日下午2:47:43
 */
public interface MemberWXPaymentService extends BaseService<MemberWXPayment, String> {
	
	/**
	 * @Description: 保存用户明细
	 * @author 宋高俊
	 * @param memberId
	 * @param content
	 * @param price
	 * @param remainPrice
	 * @return
	 * @date 2018年12月7日下午4:18:03
	 */
	int saveMemberWXPayment(String memberId, String content, double price, double remainPrice);

	/**
	 * @Description: 根据用户ID查询额度明细
	 * @author 宋高俊
	 * @param memberId
	 * @return
	 * @date 2018年12月7日下午4:33:45
	 */
	List<MemberWXPayment> selectByMemberId(String memberId);
}
