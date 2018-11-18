package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class VenueDay implements Serializable {
    /** 每天统计场馆收入 */
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

    /** 金额 */
    private Double countAmount;

    /** 回款人ID */
    private String memberId;

    /** 状态(0=申请中1=提现成功2=提现失败) */
    private Integer typeFlag;

    /** 备注 */
    private String remark;

    /**
     * VenueDay
     */
    private static final long serialVersionUID = 1L;

    /**
     * 每天统计场馆收入
     * @return ID 每天统计场馆收入
     */
    public String getId() {
        return id;
    }

    /**
     * 每天统计场馆收入
     * @param id 每天统计场馆收入
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
     * 金额
     * @return Count_amount 金额
     */
    public Double getCountAmount() {
        return countAmount;
    }

    /**
     * 金额
     * @param countAmount 金额
     */
    public void setCountAmount(Double countAmount) {
        this.countAmount = countAmount;
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
     * 状态(0=申请中1=提现成功2=提现失败)
     * @return Type_flag 状态(0=申请中1=提现成功2=提现失败)
     */
    public Integer getTypeFlag() {
        return typeFlag;
    }

    /**
     * 状态(0=申请中1=提现成功2=提现失败)
     * @param typeFlag 状态(0=申请中1=提现成功2=提现失败)
     */
    public void setTypeFlag(Integer typeFlag) {
        this.typeFlag = typeFlag;
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