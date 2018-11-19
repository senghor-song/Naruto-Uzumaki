package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 约球图片表瀹炰綋
 */
public class InviteImage implements Serializable {
    /** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 约球ID */
    private String inviteId;

    /** 图片地址 */
    private String url;

    /** 是否是封面图0=否1=是 */
    private Integer headImage;

    /**
     * InviteImage
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
     * 约球ID
     * @return Invite_id 约球ID
     */
    public String getInviteId() {
        return inviteId;
    }

    /**
     * 约球ID
     * @param inviteId 约球ID
     */
    public void setInviteId(String inviteId) {
        this.inviteId = inviteId == null ? null : inviteId.trim();
    }

    /**
     * 图片地址
     * @return Url 图片地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 图片地址
     * @param url 图片地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 是否是封面图0=否1=是
     * @return Head_image 是否是封面图0=否1=是
     */
    public Integer getHeadImage() {
        return headImage;
    }

    /**
     * 是否是封面图0=否1=是
     * @param headImage 是否是封面图0=否1=是
     */
    public void setHeadImage(Integer headImage) {
        this.headImage = headImage;
    }
}