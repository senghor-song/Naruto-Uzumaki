package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 约球加入表实体
 */
public class InviteJoin implements Serializable {
	
	private Member member;
	
    public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

    /** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 约球ID */
    private String inviteId;

    /** 会员ID */
    private String memberId;

    /** 支付金额 */
    private Double amount;

    /** 姓名 */
    private String name;

    /** 微信号 */
    private String wechatId;

    /** 手机号码 */
    private String phone;

    /** 报名备注 */
    private String content;

    /** 支付时间 */
    private Date payTime;

    /** 支付状态(0=待支付1=支付成功2=已取消3=已退款4=无需支付) */
    private Integer payType;

    /** 退出状态(0=未退出1=退出退费2=退出申请) */
    private Integer refundFlag;

    /** 退费理由 */
    private String refundContent;

    /** 退费金额 */
    private Double refundAmount;

    /** 退费手续费金额 */
    private Double refundFeeamount;

    /** 退费手续费率 */
    private Integer refundFee;

    /**
     * InviteJoin
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
     * 约球ID
     * @return Invite_id 约球ID
     */
    public String getInviteId() {
        return inviteId;
    }

    /**
     * 约球ID
     * @param inviteId 约球ID
     */
    public void setInviteId(String inviteId) {
        this.inviteId = inviteId == null ? null : inviteId.trim();
    }

    /**
     * 会员ID
     * @return Member_id 会员ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 会员ID
     * @param memberId 会员ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     * 支付金额
     * @return Amount 支付金额
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 支付金额
     * @param amount 支付金额
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * 姓名
     * @return Name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 微信号
     * @return WeChat_id 微信号
     */
    public String getWechatId() {
        return wechatId;
    }

    /**
     * 微信号
     * @param wechatId 微信号
     */
    public void setWechatId(String wechatId) {
        this.wechatId = wechatId == null ? null : wechatId.trim();
    }

    /**
     * 手机号码
     * @return Phone 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号码
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 报名备注
     * @return Content 报名备注
     */
    public String getContent() {
        return content;
    }

    /**
     * 报名备注
     * @param content 报名备注
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 支付时间
     * @return Pay_time 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 支付时间
     * @param payTime 支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 支付状态(0=待支付1=支付成功2=已取消3=已退款4=无需支付)
     * @return Pay_type 支付状态(0=待支付1=支付成功2=已取消3=已退款4=无需支付)
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * 支付状态(0=待支付1=支付成功2=已取消3=已退款4=无需支付)
     * @param payType 支付状态(0=待支付1=支付成功2=已取消3=已退款4=无需支付)
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * 退出状态(0=未退出1=退出退费2=退出申请)
     * @return Refund_flag 退出状态(0=未退出1=退出退费2=退出申请)
     */
    public Integer getRefundFlag() {
        return refundFlag;
    }

    /**
     * 退出状态(0=未退出1=退出退费2=退出申请)
     * @param refundFlag 退出状态(0=未退出1=退出退费2=退出申请)
     */
    public void setRefundFlag(Integer refundFlag) {
        this.refundFlag = refundFlag;
    }

    /**
     * 退费理由
     * @return Refund_content 退费理由
     */
    public String getRefundContent() {
        return refundContent;
    }

    /**
     * 退费理由
     * @param refundContent 退费理由
     */
    public void setRefundContent(String refundContent) {
        this.refundContent = refundContent == null ? null : refundContent.trim();
    }

    /**
     * 退费金额
     * @return Refund_amount 退费金额
     */
    public Double getRefundAmount() {
        return refundAmount;
    }

    /**
     * 退费金额
     * @param refundAmount 退费金额
     */
    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * 退费手续费金额
     * @return Refund_feeAmount 退费手续费金额
     */
    public Double getRefundFeeamount() {
        return refundFeeamount;
    }

    /**
     * 退费手续费金额
     * @param refundFeeamount 退费手续费金额
     */
    public void setRefundFeeamount(Double refundFeeamount) {
        this.refundFeeamount = refundFeeamount;
    }

    /**
     * 退费手续费率
     * @return Refund_fee 退费手续费率
     */
    public Integer getRefundFee() {
        return refundFee;
    }

    /**
     * 退费手续费率
     * @param refundFee 退费手续费率
     */
    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }
}