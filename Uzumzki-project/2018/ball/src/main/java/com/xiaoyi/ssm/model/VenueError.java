package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 场馆纠错表实体
 */
public class VenueError implements Serializable {
    /** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 用户ID */
    private String memberId;

    /** 场馆ID */
    private String venueId;

    /** 报错内容 */
    private String content;

    /**
     * VenueError
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
     * 报错内容
     * @return Content 报错内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 报错内容
     * @param content 报错内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}