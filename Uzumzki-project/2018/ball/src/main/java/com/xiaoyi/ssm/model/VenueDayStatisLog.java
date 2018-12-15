package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class VenueDayStatisLog implements Serializable {
    /** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 统计表ID */
    private String venueDayStatisId;

    /** 日志内容 */
    private String content;

    /** 记录人 */
    private String staffId;

    /**
     * VenueDayStatisLog
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
     * @return Venue_day_statis_id 统计表ID
     */
    public String getVenueDayStatisId() {
        return venueDayStatisId;
    }

    /**
     * 统计表ID
     * @param venueDayStatisId 统计表ID
     */
    public void setVenueDayStatisId(String venueDayStatisId) {
        this.venueDayStatisId = venueDayStatisId == null ? null : venueDayStatisId.trim();
    }

    /**
     * 日志内容
     * @return Content 日志内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 日志内容
     * @param content 日志内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
}