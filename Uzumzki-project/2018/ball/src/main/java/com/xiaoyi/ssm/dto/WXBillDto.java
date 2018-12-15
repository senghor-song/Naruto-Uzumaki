package com.xiaoyi.ssm.dto;

import java.util.Date;

/**
 * @Description: 对账单查询参数封装类
 * @author 宋高俊
 * @date 2018年11月29日下午7:02:41
 */
public class WXBillDto {
	// 微信订单号
	private String wxOrderid;
	// 商户订单号
	private String soreOrderid;
	// 订单金额
	private double orderAmount;
	// 退款金额
	private double refundAmount;
	// 平台费
	private double orderFee;
	// 用户编号
	private Integer memberNo;
	// 支付时间
	private Date payTime;
	// 订场日期
	private Date orderDate;
	// 场馆编号
	private Integer venueNo;

	public String getWxOrderid() {
		return wxOrderid;
	}

	public void setWxOrderid(String wxOrderid) {
		this.wxOrderid = wxOrderid;
	}

	public String getSoreOrderid() {
		return soreOrderid;
	}

	public void setSoreOrderid(String soreOrderid) {
		this.soreOrderid = soreOrderid;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getVenueNo() {
		return venueNo;
	}

	public void setVenueNo(Integer venueNo) {
		this.venueNo = venueNo;
	}

	public double getOrderFee() {
		return orderFee;
	}

	public void setOrderFee(double orderFee) {
		this.orderFee = orderFee;
	}

}
