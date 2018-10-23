package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 课程报名订单表实体
 */
public class TrainOrder implements Serializable {
	/** 报名人的数据 */
	private Member member;
	
	/** 所报名的课程数据 */
	private TrainCourse trainCourse;
	
    public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public TrainCourse getTrainCourse() {
		return trainCourse;
	}

	public void setTrainCourse(TrainCourse trainCourse) {
		this.trainCourse = trainCourse;
	}
	/** 培训订单 */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 订单号 */
    private Integer trainOrderNo;

    /** 课程ID */
    private String trainCourseId;

    /** 手机号码 */
    private String phone;

    /** 备注 */
    private String content;

    /** 用户ID */
    private String memberId;

    /** 上课总课时 */
    private Integer classHourSum;

    /** 已上课课时 */
    private Integer classHour;

    /** 支付金额 */
    private BigDecimal amount;

    /** 支付时间 */
    private Date payTime;

    /** 支付状态(0=待支付1=支付成功2=支付取消) */
    private Integer payType;

    /** 微信支付回调参数 */
    private String wxbackContent;

    /**
     * TrainOrder
     */
    private static final long serialVersionUID = 1L;

    /**
     * 培训订单
     * @return ID 培训订单
     */
    public String getId() {
        return id;
    }

    /**
     * 培训订单
     * @param id 培训订单
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
     * 订单号
     * @return Train_order_no 订单号
     */
    public Integer getTrainOrderNo() {
        return trainOrderNo;
    }

    /**
     * 订单号
     * @param trainOrderNo 订单号
     */
    public void setTrainOrderNo(Integer trainOrderNo) {
        this.trainOrderNo = trainOrderNo;
    }

    /**
     * 课程ID
     * @return Train_course_id 课程ID
     */
    public String getTrainCourseId() {
        return trainCourseId;
    }

    /**
     * 课程ID
     * @param trainCourseId 课程ID
     */
    public void setTrainCourseId(String trainCourseId) {
        this.trainCourseId = trainCourseId == null ? null : trainCourseId.trim();
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
     * 备注
     * @return Content 备注
     */
    public String getContent() {
        return content;
    }

    /**
     * 备注
     * @param content 备注
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 用户ID
     * @return Member_id 用户ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 用户ID
     * @param memberId 用户ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     * 上课总课时
     * @return Class_hour_sum 上课总课时
     */
    public Integer getClassHourSum() {
        return classHourSum;
    }

    /**
     * 上课总课时
     * @param classHourSum 上课总课时
     */
    public void setClassHourSum(Integer classHourSum) {
        this.classHourSum = classHourSum;
    }

    /**
     * 已上课课时
     * @return Class_hour 已上课课时
     */
    public Integer getClassHour() {
        return classHour;
    }

    /**
     * 已上课课时
     * @param classHour 已上课课时
     */
    public void setClassHour(Integer classHour) {
        this.classHour = classHour;
    }

    /**
     * 支付金额
     * @return Amount 支付金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 支付金额
     * @param amount 支付金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
     * 支付状态(0=待支付1=支付成功2=支付取消)
     * @return Pay_type 支付状态(0=待支付1=支付成功2=支付取消)
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * 支付状态(0=待支付1=支付成功2=支付取消)
     * @param payType 支付状态(0=待支付1=支付成功2=支付取消)
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * 微信支付回调参数
     * @return WXBack_content 微信支付回调参数
     */
    public String getWxbackContent() {
        return wxbackContent;
    }

    /**
     * 微信支付回调参数
     * @param wxbackContent 微信支付回调参数
     */
    public void setWxbackContent(String wxbackContent) {
        this.wxbackContent = wxbackContent == null ? null : wxbackContent.trim();
    }
}