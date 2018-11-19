package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 场馆退费申请表实体
 */
public class VenueRefund implements Serializable {
    /** 退款 */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 退款理由 */
    private String content;

    /** 总金额 */
    private Double amountSum;

    /** 退款金额 */
    private Double amountRefund;

    /** 退款手续费 */
    private Double amountFee;

    /** 最高费率 */
    private Integer amountRate;

    /** 来源ID */
    private String orderId;

    /** 退款时间 */
    private Date refundTime;

    /** 退款状态(0=申请中1=退款成功2=退款失败) */
    private Integer refundStatus;

    /** 备注 */
    private String remark;

    /**
     * VenueRefund
     */
    private static final long serialVersionUID = 1L;

    /**
     * 退款
     * @return ID 退款
     */
    public String getId() {
        return id;
    }

    /**
     * 退款
     * @param id 退款
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
     * 退款理由
     * @return Content 退款理由
     */
    public String getContent() {
        return content;
    }

    /**
     * 退款理由
     * @param content 退款理由
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 总金额
     * @return Amount_sum 总金额
     */
    public Double getAmountSum() {
        return amountSum;
    }

    /**
     * 总金额
     * @param amountSum 总金额
     */
    public void setAmountSum(Double amountSum) {
        this.amountSum = amountSum;
    }

    /**
     * 退款金额
     * @return Amount_refund 退款金额
     */
    public Double getAmountRefund() {
        return amountRefund;
    }

    /**
     * 退款金额
     * @param amountRefund 退款金额
     */
    public void setAmountRefund(Double amountRefund) {
        this.amountRefund = amountRefund;
    }

    /**
     * 退款手续费
     * @return Amount_fee 退款手续费
     */
    public Double getAmountFee() {
        return amountFee;
    }

    /**
     * 退款手续费
     * @param amountFee 退款手续费
     */
    public void setAmountFee(Double amountFee) {
        this.amountFee = amountFee;
    }

    /**
     * 最高费率
     * @return Amount_rate 最高费率
     */
    public Integer getAmountRate() {
        return amountRate;
    }

    /**
     * 最高费率
     * @param amountRate 最高费率
     */
    public void setAmountRate(Integer amountRate) {
        this.amountRate = amountRate;
    }

    /**
     * 来源ID
     * @return Order_id 来源ID
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 来源ID
     * @param orderId 来源ID
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 退款时间
     * @return Refund_time 退款时间
     */
    public Date getRefundTime() {
        return refundTime;
    }

    /**
     * 退款时间
     * @param refundTime 退款时间
     */
    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    /**
     * 退款状态(0=申请中1=退款成功2=退款失败)
     * @return Refund_status 退款状态(0=申请中1=退款成功2=退款失败)
     */
    public Integer getRefundStatus() {
        return refundStatus;
    }

    /**
     * 退款状态(0=申请中1=退款成功2=退款失败)
     * @param refundStatus 退款状态(0=申请中1=退款成功2=退款失败)
     */
    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    /**
     * 备注
     * @return Remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}