package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 每日对账统计表实体
 */
public class VenueDayStatis implements Serializable {
    /** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 交易日期 */
    private Date nowDate;

    /** 应付金额 */
    private Double oughtAmount;

    /** 实付金额 */
    private Double realityAmount;

    /** 额度补贴 */
    private Double paySubsidy;

    /** 当日盈亏 */
    private Double profitAmount;

    /** 系统风评 */
    private String systemFlag;

    /** 人工风评 */
    private String userFlag;

    /** 交易收入(基本户) */
    private Double dealIncome;

    /** 交易退款(基本户) */
    private Double dealRefund;

    /** 微信手续费(基本户) */
    private Double dealFee;

    /** 提现(基本户) */
    private Double withdrawalCash;

    /** 基本转运营(基本户) */
    private Double basicToOperation;

    /** 运营转基本(基本户) */
    private Double operationToBasic;

    /** 其他收入(基本户) */
    private Double restsIncome;

    /** 基本户当日结余(基本户) */
    private Double accountAmount;

    /** 预定当日订场,当日支付金额 */
    private Double nowDayAmount;

    /** 非预定当日订场，预付订场金额 */
    private Double oldDayAmount;

    /** 预定当日订场,非当日支付金额 */
    private Double nowDayOrderAmount;

    /** 企业支付(运营户) */
    private Double paymentAmountOperation;

    /** 基本转运营(运营户) */
    private Double basicToOperationOperation;

    /** 运营转基本(运营户) */
    private Double operationToBasicOperation;

    /** 运营户当日结余(运营户) */
    private Double accountAmountOperation;

    /**
     * VenueDayStatis
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * @return ID ID
     */
    public String getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 创建时间
     * @return Create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 交易日期
     * @return Now_date 交易日期
     */
    public Date getNowDate() {
        return nowDate;
    }

    /**
     * 交易日期
     * @param nowDate 交易日期
     */
    public void setNowDate(Date nowDate) {
        this.nowDate = nowDate;
    }

    /**
     * 应付金额
     * @return Ought_amount 应付金额
     */
    public Double getOughtAmount() {
        return oughtAmount;
    }

    /**
     * 应付金额
     * @param oughtAmount 应付金额
     */
    public void setOughtAmount(Double oughtAmount) {
        this.oughtAmount = oughtAmount;
    }

    /**
     * 实付金额
     * @return Reality_amount 实付金额
     */
    public Double getRealityAmount() {
        return realityAmount;
    }

    /**
     * 实付金额
     * @param realityAmount 实付金额
     */
    public void setRealityAmount(Double realityAmount) {
        this.realityAmount = realityAmount;
    }

    /**
     * 额度补贴
     * @return Pay_subsidy 额度补贴
     */
    public Double getPaySubsidy() {
        return paySubsidy;
    }

    /**
     * 额度补贴
     * @param paySubsidy 额度补贴
     */
    public void setPaySubsidy(Double paySubsidy) {
        this.paySubsidy = paySubsidy;
    }

    /**
     * 当日盈亏
     * @return Profit_amount 当日盈亏
     */
    public Double getProfitAmount() {
        return profitAmount;
    }

    /**
     * 当日盈亏
     * @param profitAmount 当日盈亏
     */
    public void setProfitAmount(Double profitAmount) {
        this.profitAmount = profitAmount;
    }

    /**
     * 系统风评
     * @return System_flag 系统风评
     */
    public String getSystemFlag() {
        return systemFlag;
    }

    /**
     * 系统风评
     * @param systemFlag 系统风评
     */
    public void setSystemFlag(String systemFlag) {
        this.systemFlag = systemFlag == null ? null : systemFlag.trim();
    }

    /**
     * 人工风评
     * @return User_flag 人工风评
     */
    public String getUserFlag() {
        return userFlag;
    }

    /**
     * 人工风评
     * @param userFlag 人工风评
     */
    public void setUserFlag(String userFlag) {
        this.userFlag = userFlag == null ? null : userFlag.trim();
    }

    /**
     * 交易收入(基本户)
     * @return Deal_income 交易收入(基本户)
     */
    public Double getDealIncome() {
        return dealIncome;
    }

    /**
     * 交易收入(基本户)
     * @param dealIncome 交易收入(基本户)
     */
    public void setDealIncome(Double dealIncome) {
        this.dealIncome = dealIncome;
    }

    /**
     * 交易退款(基本户)
     * @return Deal_refund 交易退款(基本户)
     */
    public Double getDealRefund() {
        return dealRefund;
    }

    /**
     * 交易退款(基本户)
     * @param dealRefund 交易退款(基本户)
     */
    public void setDealRefund(Double dealRefund) {
        this.dealRefund = dealRefund;
    }

    /**
     * 微信手续费(基本户)
     * @return Deal_fee 微信手续费(基本户)
     */
    public Double getDealFee() {
        return dealFee;
    }

    /**
     * 微信手续费(基本户)
     * @param dealFee 微信手续费(基本户)
     */
    public void setDealFee(Double dealFee) {
        this.dealFee = dealFee;
    }

    /**
     * 提现(基本户)
     * @return Withdrawal_cash 提现(基本户)
     */
    public Double getWithdrawalCash() {
        return withdrawalCash;
    }

    /**
     * 提现(基本户)
     * @param withdrawalCash 提现(基本户)
     */
    public void setWithdrawalCash(Double withdrawalCash) {
        this.withdrawalCash = withdrawalCash;
    }

    /**
     * 基本转运营(基本户)
     * @return Basic_to_operation 基本转运营(基本户)
     */
    public Double getBasicToOperation() {
        return basicToOperation;
    }

    /**
     * 基本转运营(基本户)
     * @param basicToOperation 基本转运营(基本户)
     */
    public void setBasicToOperation(Double basicToOperation) {
        this.basicToOperation = basicToOperation;
    }

    /**
     * 运营转基本(基本户)
     * @return Operation_to_basic 运营转基本(基本户)
     */
    public Double getOperationToBasic() {
        return operationToBasic;
    }

    /**
     * 运营转基本(基本户)
     * @param operationToBasic 运营转基本(基本户)
     */
    public void setOperationToBasic(Double operationToBasic) {
        this.operationToBasic = operationToBasic;
    }

    /**
     * 其他收入(基本户)
     * @return Rests_income 其他收入(基本户)
     */
    public Double getRestsIncome() {
        return restsIncome;
    }

    /**
     * 其他收入(基本户)
     * @param restsIncome 其他收入(基本户)
     */
    public void setRestsIncome(Double restsIncome) {
        this.restsIncome = restsIncome;
    }

    /**
     * 基本户当日结余(基本户)
     * @return Account_amount 基本户当日结余(基本户)
     */
    public Double getAccountAmount() {
        return accountAmount;
    }

    /**
     * 基本户当日结余(基本户)
     * @param accountAmount 基本户当日结余(基本户)
     */
    public void setAccountAmount(Double accountAmount) {
        this.accountAmount = accountAmount;
    }

    /**
     * 预定当日订场,当日支付金额
     * @return Now_day_amount 预定当日订场,当日支付金额
     */
    public Double getNowDayAmount() {
        return nowDayAmount;
    }

    /**
     * 预定当日订场,当日支付金额
     * @param nowDayAmount 预定当日订场,当日支付金额
     */
    public void setNowDayAmount(Double nowDayAmount) {
        this.nowDayAmount = nowDayAmount;
    }

    /**
     * 非预定当日订场，预付订场金额
     * @return Old_day_amount 非预定当日订场，预付订场金额
     */
    public Double getOldDayAmount() {
        return oldDayAmount;
    }

    /**
     * 非预定当日订场，预付订场金额
     * @param oldDayAmount 非预定当日订场，预付订场金额
     */
    public void setOldDayAmount(Double oldDayAmount) {
        this.oldDayAmount = oldDayAmount;
    }

    /**
     * 预定当日订场,非当日支付金额
     * @return Now_day_order_amount 预定当日订场,非当日支付金额
     */
    public Double getNowDayOrderAmount() {
        return nowDayOrderAmount;
    }

    /**
     * 预定当日订场,非当日支付金额
     * @param nowDayOrderAmount 预定当日订场,非当日支付金额
     */
    public void setNowDayOrderAmount(Double nowDayOrderAmount) {
        this.nowDayOrderAmount = nowDayOrderAmount;
    }

    /**
     * 企业支付(运营户)
     * @return Payment_amount_operation 企业支付(运营户)
     */
    public Double getPaymentAmountOperation() {
        return paymentAmountOperation;
    }

    /**
     * 企业支付(运营户)
     * @param paymentAmountOperation 企业支付(运营户)
     */
    public void setPaymentAmountOperation(Double paymentAmountOperation) {
        this.paymentAmountOperation = paymentAmountOperation;
    }

    /**
     * 基本转运营(运营户)
     * @return Basic_to_operation_operation 基本转运营(运营户)
     */
    public Double getBasicToOperationOperation() {
        return basicToOperationOperation;
    }

    /**
     * 基本转运营(运营户)
     * @param basicToOperationOperation 基本转运营(运营户)
     */
    public void setBasicToOperationOperation(Double basicToOperationOperation) {
        this.basicToOperationOperation = basicToOperationOperation;
    }

    /**
     * 运营转基本(运营户)
     * @return Operation_to_basic_operation 运营转基本(运营户)
     */
    public Double getOperationToBasicOperation() {
        return operationToBasicOperation;
    }

    /**
     * 运营转基本(运营户)
     * @param operationToBasicOperation 运营转基本(运营户)
     */
    public void setOperationToBasicOperation(Double operationToBasicOperation) {
        this.operationToBasicOperation = operationToBasicOperation;
    }

    /**
     * 运营户当日结余(运营户)
     * @return Account_amount_operation 运营户当日结余(运营户)
     */
    public Double getAccountAmountOperation() {
        return accountAmountOperation;
    }

    /**
     * 运营户当日结余(运营户)
     * @param accountAmountOperation 运营户当日结余(运营户)
     */
    public void setAccountAmountOperation(Double accountAmountOperation) {
        this.accountAmountOperation = accountAmountOperation;
    }
}