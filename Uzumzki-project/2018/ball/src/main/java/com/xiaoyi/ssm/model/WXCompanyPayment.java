package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信企业支付流水实体
 */
public class WXCompanyPayment implements Serializable {
    /** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 商户订单号 */
    private String orderId;

    /** openid */
    private String openid;

    /** 付款金额 */
    private Double amount;

    /** 付款描述 */
    private String descContent;

    /** 支付状态(1=支付成功2=支付失败) */
    private Integer payType;

    /** 支付信息 */
    private String payMsg;

    /** 微信付款单号 */
    private String paymentNo;

    /** 付款成功时间 */
    private String paymentTime;

    /** 微信返回参数 */
    private String returnXml;

    /**
     * WXCompanyPayment
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
     * 商户订单号
     * @return Order_id 商户订单号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 商户订单号
     * @param orderId 商户订单号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * openid
     * @return Openid openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * openid
     * @param openid openid
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * 付款金额
     * @return Amount 付款金额
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 付款金额
     * @param amount 付款金额
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * 付款描述
     * @return Desc_content 付款描述
     */
    public String getDescContent() {
        return descContent;
    }

    /**
     * 付款描述
     * @param descContent 付款描述
     */
    public void setDescContent(String descContent) {
        this.descContent = descContent == null ? null : descContent.trim();
    }

    /**
     * 支付状态(1=支付成功2=支付失败)
     * @return Pay_type 支付状态(1=支付成功2=支付失败)
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * 支付状态(1=支付成功2=支付失败)
     * @param payType 支付状态(1=支付成功2=支付失败)
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * 支付信息
     * @return Pay_msg 支付信息
     */
    public String getPayMsg() {
        return payMsg;
    }

    /**
     * 支付信息
     * @param payMsg 支付信息
     */
    public void setPayMsg(String payMsg) {
        this.payMsg = payMsg == null ? null : payMsg.trim();
    }

    /**
     * 微信付款单号
     * @return Payment_no 微信付款单号
     */
    public String getPaymentNo() {
        return paymentNo;
    }

    /**
     * 微信付款单号
     * @param paymentNo 微信付款单号
     */
    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo == null ? null : paymentNo.trim();
    }

    /**
     * 付款成功时间
     * @return Payment_time 付款成功时间
     */
    public String getPaymentTime() {
        return paymentTime;
    }

    /**
     * 付款成功时间
     * @param paymentTime 付款成功时间
     */
    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime == null ? null : paymentTime.trim();
    }

    /**
     * 微信返回参数
     * @return Return_xml 微信返回参数
     */
    public String getReturnXml() {
        return returnXml;
    }

    /**
     * 微信返回参数
     * @param returnXml 微信返回参数
     */
    public void setReturnXml(String returnXml) {
        this.returnXml = returnXml == null ? null : returnXml.trim();
    }
}