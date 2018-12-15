package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 场馆每天统计收入金额实体
 */
public class VenueDay implements Serializable {
    /** ID */
    private String id;

    /** 编号 */
    private Integer venueDayNo;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 场馆ID */
    private String venueId;

    /** 统计日期 */
    private Date countDay;

    /** 应付金额 */
    private Double oughtAmount;

    /** 实付金额 */
    private Double realityAmount;

    /** 扣除手续费金额 */
    private Double countFee;

    /** 订单总数 */
    private Integer countOrder;

    /** 回款人ID */
    private String memberId;

    /** 剩余额度 */
    private Double memberFee;

    /** 备注 */
    private String remark;

    /** 操作状态(0=提现中1=提现成功2=提现失败) */
    private Integer typeFlag;

    /** 补贴金额 */
    private Double paySubsidy;

    /**
     * VenueDay
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
     * 编号
     * @return Venue_day_no 编号
     */
    public Integer getVenueDayNo() {
        return venueDayNo;
    }

    /**
     * 编号
     * @param venueDayNo 编号
     */
    public void setVenueDayNo(Integer venueDayNo) {
        this.venueDayNo = venueDayNo;
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
     * 场馆ID
     * @return Venue_id 场馆ID
     */
    public String getVenueId() {
        return venueId;
    }

    /**
     * 场馆ID
     * @param venueId 场馆ID
     */
    public void setVenueId(String venueId) {
        this.venueId = venueId == null ? null : venueId.trim();
    }

    /**
     * 统计日期
     * @return Count_day 统计日期
     */
    public Date getCountDay() {
        return countDay;
    }

    /**
     * 统计日期
     * @param countDay 统计日期
     */
    public void setCountDay(Date countDay) {
        this.countDay = countDay;
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
     * 扣除手续费金额
     * @return Count_fee 扣除手续费金额
     */
    public Double getCountFee() {
        return countFee;
    }

    /**
     * 扣除手续费金额
     * @param countFee 扣除手续费金额
     */
    public void setCountFee(Double countFee) {
        this.countFee = countFee;
    }

    /**
     * 订单总数
     * @return Count_order 订单总数
     */
    public Integer getCountOrder() {
        return countOrder;
    }

    /**
     * 订单总数
     * @param countOrder 订单总数
     */
    public void setCountOrder(Integer countOrder) {
        this.countOrder = countOrder;
    }

    /**
     * 回款人ID
     * @return Member_id 回款人ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 回款人ID
     * @param memberId 回款人ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     * 剩余额度
     * @return Member_fee 剩余额度
     */
    public Double getMemberFee() {
        return memberFee;
    }

    /**
     * 剩余额度
     * @param memberFee 剩余额度
     */
    public void setMemberFee(Double memberFee) {
        this.memberFee = memberFee;
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

    /**
     * 操作状态(0=提现中1=提现成功2=提现失败)
     * @return Type_flag 操作状态(0=提现中1=提现成功2=提现失败)
     */
    public Integer getTypeFlag() {
        return typeFlag;
    }

    /**
     * 操作状态(0=提现中1=提现成功2=提现失败)
     * @param typeFlag 操作状态(0=提现中1=提现成功2=提现失败)
     */
    public void setTypeFlag(Integer typeFlag) {
        this.typeFlag = typeFlag;
    }

    /**
     * 补贴金额
     * @return Pay_subsidy 补贴金额
     */
    public Double getPaySubsidy() {
        return paySubsidy;
    }

    /**
     * 补贴金额
     * @param paySubsidy 补贴金额
     */
    public void setPaySubsidy(Double paySubsidy) {
        this.paySubsidy = paySubsidy;
    }
}