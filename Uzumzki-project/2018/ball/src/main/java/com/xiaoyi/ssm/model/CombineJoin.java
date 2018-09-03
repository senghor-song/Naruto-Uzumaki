package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class CombineJoin implements Serializable {
	
	/** 会员 */
	private Member member;
	
    public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	/** 散拼加入 */
    private String id;

    /** 创建时间 */
    private Date createtimetime;

    /** 散拼活动ID */
    private String combineid;

    /** 会员ID */
    private String memberid;

    /** 支付金额 */
    private Double price;

    /** 订单状态(0=待支付1=支付成功2=支付取消3=支付超时4=已退款) */
    private Integer type;

    /** 支付方式0=支付宝1=微信支付 */
    private Integer paytype;

    /** 支付结果内容(仅供效验) */
    private String payremark;

    /**
     * CombineJoin
     */
    private static final long serialVersionUID = 1L;

    /**
     * 散拼加入
     * @return ID 散拼加入
     */
    public String getId() {
        return id;
    }

    /**
     * 散拼加入
     * @param id 散拼加入
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 创建时间
     * @return CreateTimeTime 创建时间
     */
    public Date getCreatetimetime() {
        return createtimetime;
    }

    /**
     * 创建时间
     * @param createtimetime 创建时间
     */
    public void setCreatetimetime(Date createtimetime) {
        this.createtimetime = createtimetime;
    }

    /**
     * 散拼活动ID
     * @return CombineID 散拼活动ID
     */
    public String getCombineid() {
        return combineid;
    }

    /**
     * 散拼活动ID
     * @param combineid 散拼活动ID
     */
    public void setCombineid(String combineid) {
        this.combineid = combineid == null ? null : combineid.trim();
    }

    /**
     * 会员ID
     * @return MemberID 会员ID
     */
    public String getMemberid() {
        return memberid;
    }

    /**
     * 会员ID
     * @param memberid 会员ID
     */
    public void setMemberid(String memberid) {
        this.memberid = memberid == null ? null : memberid.trim();
    }

    /**
     * 支付金额
     * @return Price 支付金额
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 支付金额
     * @param price 支付金额
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 订单状态(0=待支付1=支付成功2=支付取消3=支付超时4=已退款)
     * @return Type 订单状态(0=待支付1=支付成功2=支付取消3=支付超时4=已退款)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 订单状态(0=待支付1=支付成功2=支付取消3=支付超时4=已退款)
     * @param type 订单状态(0=待支付1=支付成功2=支付取消3=支付超时4=已退款)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 支付方式0=支付宝1=微信支付
     * @return PayType 支付方式0=支付宝1=微信支付
     */
    public Integer getPaytype() {
        return paytype;
    }

    /**
     * 支付方式0=支付宝1=微信支付
     * @param paytype 支付方式0=支付宝1=微信支付
     */
    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    /**
     * 支付结果内容(仅供效验)
     * @return PayRemark 支付结果内容(仅供效验)
     */
    public String getPayremark() {
        return payremark;
    }

    /**
     * 支付结果内容(仅供效验)
     * @param payremark 支付结果内容(仅供效验)
     */
    public void setPayremark(String payremark) {
        this.payremark = payremark == null ? null : payremark.trim();
    }
}