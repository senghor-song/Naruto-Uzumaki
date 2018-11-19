package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体
 */
public class InviteBallOneDay implements Serializable {
    /** ID-约球 */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 约球ID */
    private String inviteBallId;

    /** 支付人ID */
    private String memberId;

    /** 报名总收入 */
    private BigDecimal amountCollect;

    /** AA退款总额 */
    private BigDecimal amountAa;

    /** 平台收取手续费 */
    private BigDecimal amountFee;

    /** 实际付款 */
    private BigDecimal amountOut;

    /** 支付状态(1=已支付2=约球数据异常不支付) */
    private Integer payType;

    /** 企业支付ID */
    private String wxcompanypayment;

    /** 金额流水详细信息 */
    private String content;

    /**
     * InviteBallOneDay
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID-约球
     * @return ID ID-约球
     */
    public String getId() {
        return id;
    }

    /**
     * ID-约球
     * @param id ID-约球
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
     * 修改时间
     * @return Modify_time 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 约球ID
     * @return Invite_ball_id 约球ID
     */
    public String getInviteBallId() {
        return inviteBallId;
    }

    /**
     * 约球ID
     * @param inviteBallId 约球ID
     */
    public void setInviteBallId(String inviteBallId) {
        this.inviteBallId = inviteBallId == null ? null : inviteBallId.trim();
    }

    /**
     * 支付人ID
     * @return Member_id 支付人ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 支付人ID
     * @param memberId 支付人ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     * 报名总收入
     * @return Amount_collect 报名总收入
     */
    public BigDecimal getAmountCollect() {
        return amountCollect;
    }

    /**
     * 报名总收入
     * @param amountCollect 报名总收入
     */
    public void setAmountCollect(BigDecimal amountCollect) {
        this.amountCollect = amountCollect;
    }

    /**
     * AA退款总额
     * @return Amount_aa AA退款总额
     */
    public BigDecimal getAmountAa() {
        return amountAa;
    }

    /**
     * AA退款总额
     * @param amountAa AA退款总额
     */
    public void setAmountAa(BigDecimal amountAa) {
        this.amountAa = amountAa;
    }

    /**
     * 平台收取手续费
     * @return Amount_fee 平台收取手续费
     */
    public BigDecimal getAmountFee() {
        return amountFee;
    }

    /**
     * 平台收取手续费
     * @param amountFee 平台收取手续费
     */
    public void setAmountFee(BigDecimal amountFee) {
        this.amountFee = amountFee;
    }

    /**
     * 实际付款
     * @return Amount_out 实际付款
     */
    public BigDecimal getAmountOut() {
        return amountOut;
    }

    /**
     * 实际付款
     * @param amountOut 实际付款
     */
    public void setAmountOut(BigDecimal amountOut) {
        this.amountOut = amountOut;
    }

    /**
     * 支付状态(1=已支付2=约球数据异常不支付)
     * @return Pay_type 支付状态(1=已支付2=约球数据异常不支付)
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * 支付状态(1=已支付2=约球数据异常不支付)
     * @param payType 支付状态(1=已支付2=约球数据异常不支付)
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * 企业支付ID
     * @return WXCompanyPayment 企业支付ID
     */
    public String getWxcompanypayment() {
        return wxcompanypayment;
    }

    /**
     * 企业支付ID
     * @param wxcompanypayment 企业支付ID
     */
    public void setWxcompanypayment(String wxcompanypayment) {
        this.wxcompanypayment = wxcompanypayment == null ? null : wxcompanypayment.trim();
    }

    /**
     * 金额流水详细信息
     * @return Content 金额流水详细信息
     */
    public String getContent() {
        return content;
    }

    /**
     * 金额流水详细信息
     * @param content 金额流水详细信息
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}