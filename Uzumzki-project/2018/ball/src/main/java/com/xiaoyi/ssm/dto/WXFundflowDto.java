package com.xiaoyi.ssm.dto;

/**
 * @Description: 资金账单查询参数封装类
 * @author 宋高俊
 * @date 2018年11月29日下午7:02:41
 */
public class WXFundflowDto {
	// 交易收入
	private double dealIncome;
	// 交易退款
	private double dealRefund;
	// 微信手续费
	private double dealFee;
	// 提现
	private double withdrawalCash;
	// 基本转运营
	private double basicToOperation;
	// 运营转基本
	private double operationToBasic;
	// 其他收入
	private double restsIncome;
	// 基本户当日结余
	private double accountAmount;
	// 预定当日订场,当日支付金额
	private double nowDayAmount;
	// 非预定当日订场，预付订场金额
	private double oldDayAmount;
	// 预定当日订场,非当日支付金额
	private double nowDayOrderAmount;
	
	// 企业支付
	private double paymentAmountOperation;
	// 基本转运营
	private double basicToOperationOperation;
	// 运营转基本
	private double operationToBasicOperation;
	// 运营户当日结余
	private double accountAmountOperation;
	// 提现
	private double withdrawalCashOperation;
	// 扫码充值
	private double restsIncomeOperation;
	
	

	public double getDealIncome() {
		return dealIncome;
	}
	public void setDealIncome(double dealIncome) {
		this.dealIncome = dealIncome;
	}
	public double getDealRefund() {
		return dealRefund;
	}
	public void setDealRefund(double dealRefund) {
		this.dealRefund = dealRefund;
	}
	public double getDealFee() {
		return dealFee;
	}
	public void setDealFee(double dealFee) {
		this.dealFee = dealFee;
	}
	public double getWithdrawalCash() {
		return withdrawalCash;
	}
	public void setWithdrawalCash(double withdrawalCash) {
		this.withdrawalCash = withdrawalCash;
	}
	public double getBasicToOperation() {
		return basicToOperation;
	}
	public void setBasicToOperation(double basicToOperation) {
		this.basicToOperation = basicToOperation;
	}
	public double getOperationToBasic() {
		return operationToBasic;
	}
	public void setOperationToBasic(double operationToBasic) {
		this.operationToBasic = operationToBasic;
	}
	public double getRestsIncome() {
		return restsIncome;
	}
	public void setRestsIncome(double restsIncome) {
		this.restsIncome = restsIncome;
	}
	public double getAccountAmount() {
		return accountAmount;
	}
	public void setAccountAmount(double accountAmount) {
		this.accountAmount = accountAmount;
	}
	public double getPaymentAmountOperation() {
		return paymentAmountOperation;
	}
	public void setPaymentAmountOperation(double paymentAmountOperation) {
		this.paymentAmountOperation = paymentAmountOperation;
	}
	public double getBasicToOperationOperation() {
		return basicToOperationOperation;
	}
	public void setBasicToOperationOperation(double basicToOperationOperation) {
		this.basicToOperationOperation = basicToOperationOperation;
	}
	public double getOperationToBasicOperation() {
		return operationToBasicOperation;
	}
	public void setOperationToBasicOperation(double operationToBasicOperation) {
		this.operationToBasicOperation = operationToBasicOperation;
	}
	public double getAccountAmountOperation() {
		return accountAmountOperation;
	}
	public void setAccountAmountOperation(double accountAmountOperation) {
		this.accountAmountOperation = accountAmountOperation;
	}
	public double getNowDayAmount() {
		return nowDayAmount;
	}
	public void setNowDayAmount(double nowDayAmount) {
		this.nowDayAmount = nowDayAmount;
	}
	public double getOldDayAmount() {
		return oldDayAmount;
	}
	public void setOldDayAmount(double oldDayAmount) {
		this.oldDayAmount = oldDayAmount;
	}
	public double getNowDayOrderAmount() {
		return nowDayOrderAmount;
	}
	public void setNowDayOrderAmount(double nowDayOrderAmount) {
		this.nowDayOrderAmount = nowDayOrderAmount;
	}
	public double getWithdrawalCashOperation() {
		return withdrawalCashOperation;
	}
	public void setWithdrawalCashOperation(double withdrawalCashOperation) {
		this.withdrawalCashOperation = withdrawalCashOperation;
	}
	public double getRestsIncomeOperation() {
		return restsIncomeOperation;
	}
	public void setRestsIncomeOperation(double restsIncomeOperation) {
		this.restsIncomeOperation = restsIncomeOperation;
	}
	
}
