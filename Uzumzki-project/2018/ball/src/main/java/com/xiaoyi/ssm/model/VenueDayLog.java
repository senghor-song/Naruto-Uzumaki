package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class VenueDayLog implements Serializable {
    /** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 统计表ID */
    private String venueDayId;

    /** 记录人 */
    private String staffId;

    /** 支付方式(0=系统1=人工) */
    private Integer typeFlag;

    /**
     * VenueDayLog
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
     * @return Venue_day_id 统计表ID
     */
    public String getVenueDayId() {
        return venueDayId;
    }

    /**
     * 统计表ID
     * @param venueDayId 统计表ID
     */
    public void setVenueDayId(String venueDayId) {
        this.venueDayId = venueDayId == null ? null : venueDayId.trim();
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
}