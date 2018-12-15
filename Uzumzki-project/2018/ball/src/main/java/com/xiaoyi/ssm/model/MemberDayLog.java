package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 统计对账表转账日志实体
 */
public class MemberDayLog implements Serializable {
    /** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 统计表ID */
    private String memberDayId;

    /** 记录人 */
    private String staffId;

    /** 支付方式(0=系统1=人工) */
    private Integer typeFlag;

    /** 账单状态(1=成功2=失败3=结束4=其它) */
    private Integer memberDayType;

    /** 状态信息 */
    private String content;

    /**
     * MemberDayLog
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
     * 统计表ID
     * @return Member_day_id 统计表ID
     */
    public String getMemberDayId() {
        return memberDayId;
    }

    /**
     * 统计表ID
     * @param memberDayId 统计表ID
     */
    public void setMemberDayId(String memberDayId) {
        this.memberDayId = memberDayId == null ? null : memberDayId.trim();
    }

    /**
     * 记录人
     * @return Staff_id 记录人
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * 记录人
     * @param staffId 记录人
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    /**
     * 支付方式(0=系统1=人工)
     * @return Type_flag 支付方式(0=系统1=人工)
     */
    public Integer getTypeFlag() {
        return typeFlag;
    }

    /**
     * 支付方式(0=系统1=人工)
     * @param typeFlag 支付方式(0=系统1=人工)
     */
    public void setTypeFlag(Integer typeFlag) {
        this.typeFlag = typeFlag;
    }

    /**
     * 账单状态(1=成功2=失败3=结束4=其它)
     * @return Member_day_type 账单状态(1=成功2=失败3=结束4=其它)
     */
    public Integer getMemberDayType() {
        return memberDayType;
    }

    /**
     * 账单状态(1=成功2=失败3=结束4=其它)
     * @param memberDayType 账单状态(1=成功2=失败3=结束4=其它)
     */
    public void setMemberDayType(Integer memberDayType) {
        this.memberDayType = memberDayType;
    }

    /**
     * 状态信息
     * @return Content 状态信息
     */
    public String getContent() {
        return content;
    }

    /**
     * 状态信息
     * @param content 状态信息
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}