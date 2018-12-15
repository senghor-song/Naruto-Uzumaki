package com.xiaoyi.ssm.dto;

public class MemberDayDto {
	
	// 应付金额
	private double oughtAmount;
	// 实付金额
	private double realityAmount;
	// 补贴金额
	private double paySubsidy;
	// 留存待转金额
	private double realityAmountSum;
	
	public double getOughtAmount() {
		return oughtAmount;
	}
	public void setOughtAmount(double oughtAmount) {
		this.oughtAmount = oughtAmount;
	}
	public double getRealityAmount() {
		return realityAmount;
	}
	public void setRealityAmount(double realityAmount) {
		this.realityAmount = realityAmount;
	}
	public double getPaySubsidy() {
		return paySubsidy;
	}
	public void setPaySubsidy(double paySubsidy) {
		this.paySubsidy = paySubsidy;
	}
	public double getRealityAmountSum() {
		return realityAmountSum;
	}
	public void setRealityAmountSum(double realityAmountSum) {
		this.realityAmountSum = realityAmountSum;
	}
	
}
