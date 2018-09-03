package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class TrainJoin implements Serializable {
	/** 培训数据 */
	private Train train;
	/** 会员 */
	private Member member;
	/** 教练 */
	private Manager manager;

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
    /** 培训加入表 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 修改时间 */
    private Date modifytime;

    /** 培训ID */
    private String trainid;

    /** 会员ID */
    private String memberid;

    /** 支付金额 */
    private Double price;

    /** 所选课程 */
    private String content;

    /** 订单状态(0=待支付1=支付成功2=支付取消3=支付超时4=已退款) */
    private Integer type;

    /** 支付方式0=支付宝1=微信支付 */
    private Integer paytype;

    /** 支付结果内容(仅供效验) */
    private String payremark;

    /** 提现ID */
    private String amountid;

    /** 提现状态(0=未提现1=正在提现2=已提现) */
    private Integer amounttype;

    /**
     * TrainJoin
     */
    private static final long serialVersionUID = 1L;

    /**
     * 培训加入表
     * @return ID 培训加入表
     */
    public String getId() {
        return id;
    }

    /**
     * 培训加入表
     * @param id 培训加入表
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 创建时间
     * @return CreateTime 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 修改时间
     * @return ModifyTime 修改时间
     */
    public Date getModifytime() {
        return modifytime;
    }

    /**
     * 修改时间
     * @param modifytime 修改时间
     */
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * 培训ID
     * @return TrainID 培训ID
     */
    public String getTrainid() {
        return trainid;
    }

    /**
     * 培训ID
     * @param trainid 培训ID
     */
    public void setTrainid(String trainid) {
        this.trainid = trainid == null ? null : trainid.trim();
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
     * 所选课程
     * @return Content 所选课程
     */
    public String getContent() {
        return content;
    }

    /**
     * 所选课程
     * @param content 所选课程
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    /**
     * 提现ID
     * @return AmountID 提现ID
     */
    public String getAmountid() {
        return amountid;
    }

    /**
     * 提现ID
     * @param amountid 提现ID
     */
    public void setAmountid(String amountid) {
        this.amountid = amountid == null ? null : amountid.trim();
    }

    /**
     * 提现状态(0=未提现1=正在提现2=已提现)
     * @return AmountType 提现状态(0=未提现1=正在提现2=已提现)
     */
    public Integer getAmounttype() {
        return amounttype;
    }

    /**
     * 提现状态(0=未提现1=正在提现2=已提现)
     * @param amounttype 提现状态(0=未提现1=正在提现2=已提现)
     */
    public void setAmounttype(Integer amounttype) {
        this.amounttype = amounttype;
    }
}