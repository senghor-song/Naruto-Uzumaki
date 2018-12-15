package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户每天统计数据表实体
 */
public class MemberDay implements Serializable {
    /** ID */
    private String id;

    /** 编号 */
    private Integer memberDayNo;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 用户ID */
    private String memberId;

    /** 用户编号 */
    private Integer memberNo;

    /** 剩余额度 */
    private Double memberLimit;

    /** 统计日期 */
    private Date countDay;

    /** 应付金额 */
    private Double oughtAmount;

    /** 实付金额 */
    private Double realityAmount;

    /** 支付手续费 */
    private Double orderFee;

    /** 订单总数 */
    private Integer orderCount;

    /** 额度补贴 */
    private Double paySubsidy;

    /** 备注 */
    private String remark;

    /** 操作状态(0=提现中1=提现成功2=提现失败3=结束4=其它) */
    private Integer typeFlag;

    /**
     * MemberDay
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
     * @return Member_day_no 编号
     */
    public Integer getMemberDayNo() {
        return memberDayNo;
    }

    /**
     * 编号
     * @param memberDayNo 编号
     */
    public void setMemberDayNo(Integer memberDayNo) {
        this.memberDayNo = memberDayNo;
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
     * 用户编号
     * @return Member_no 用户编号
     */
    public Integer getMemberNo() {
        return memberNo;
    }

    /**
     * 用户编号
     * @param memberNo 用户编号
     */
    public void setMemberNo(Integer memberNo) {
        this.memberNo = memberNo;
    }

    /**
     * 剩余额度
     * @return Member_limit 剩余额度
     */
    public Double getMemberLimit() {
        return memberLimit;
    }

    /**
     * 剩余额度
     * @param memberLimit 剩余额度
     */
    public void setMemberLimit(Double memberLimit) {
        this.memberLimit = memberLimit;
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
     * 支付手续费
     * @return Order_fee 支付手续费
     */
    public Double getOrderFee() {
        return orderFee;
    }

    /**
     * 支付手续费
     * @param orderFee 支付手续费
     */
    public void setOrderFee(Double orderFee) {
        this.orderFee = orderFee;
    }

    /**
     * 订单总数
     * @return Order_count 订单总数
     */
    public Integer getOrderCount() {
        return orderCount;
    }

    /**
     * 订单总数
     * @param orderCount 订单总数
     */
    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
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
     * 操作状态(0=提现中1=提现成功2=提现失败3=结束4=其它)
     * @return Type_flag 操作状态(0=提现中1=提现成功2=提现失败3=结束4=其它)
     */
    public Integer getTypeFlag() {
        return typeFlag;
    }

    /**
     * 操作状态(0=提现中1=提现成功2=提现失败3=结束4=其它)
     * @param typeFlag 操作状态(0=提现中1=提现成功2=提现失败3=结束4=其它)
     */
    public void setTypeFlag(Integer typeFlag) {
        this.typeFlag = typeFlag;
    }
}