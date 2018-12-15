package com.xiaoyi.ssm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyi.ssm.dao.MemberWXPaymentMapper;
import com.xiaoyi.ssm.model.MemberWXPayment;
import com.xiaoyi.ssm.service.MemberWXPaymentService;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 用户额度明细业务逻辑层实现
 * @author 宋高俊
 * @date 2018年11月27日下午2:49:57
 */
@Service
public class MemberWXPaymentServiceImpl extends AbstractService<MemberWXPayment,String> implements MemberWXPaymentService{

	@Autowired
	private MemberWXPaymentMapper memberWXPaymentMapper;
	
	@Override
	public void setBaseMapper() {
	    super.setBaseMapper(memberWXPaymentMapper);
	}

	/**
	 * @Description: 保存用户明细
	 * @author 宋高俊
	 * @param memberId
	 * @param content
	 * @param price
	 * @param remainPrice
	 * @return
	 * @date 2018年12月7日下午4:18:20
	 */
	@Override
	public int saveMemberWXPayment(String memberId, String content, double price, double remainPrice) {
		MemberWXPayment memberWXPayment = new MemberWXPayment();
		memberWXPayment.setId(Utils.getUUID());
		memberWXPayment.setCreateTime(new Date());
		memberWXPayment.setMemberId(memberId);
		memberWXPayment.setPrice(price);
		memberWXPayment.setRemainPrice(remainPrice);
		memberWXPayment.setContent(content);
		return memberWXPaymentMapper.insertSelective(memberWXPayment);
	}

	/**
	 * @Description: 根据用户ID查询额度明细
	 * @author 宋高俊
	 * @param memberId
	 * @return
	 * @date 2018年12月7日下午4:33:45
	 */
	@Override
	public List<MemberWXPayment> selectByMemberId(String memberId) {
		return memberWXPaymentMapper.selectByMemberId(memberId);
	}

}
