package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class VenueLog implements Serializable {
	
	private Venue venue;
	private Member member;
	
    public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	/** 场馆日志 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 管理员ID */
    private String managerid;

    /** 场馆ID */
    private String venueid;

    /** 内容 */
    private String content;

    /**
     * VenueLog
     */
    private static final long serialVersionUID = 1L;

    /**
     * 场馆日志
     * @return ID 场馆日志
     */
    public String getId() {
        return id;
    }

    /**
     * 场馆日志
     * @param id 场馆日志
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
     * 管理员ID
     * @return ManagerID 管理员ID
     */
    public String getManagerid() {
        return managerid;
    }

    /**
     * 管理员ID
     * @param managerid 管理员ID
     */
    public void setManagerid(String managerid) {
        this.managerid = managerid == null ? null : managerid.trim();
    }

    /**
     * 场馆ID
     * @return VenueID 场馆ID
     */
    public String getVenueid() {
        return venueid;
    }

    /**
     * 场馆ID
     * @param venueid 场馆ID
     */
    public void setVenueid(String venueid) {
        this.venueid = venueid == null ? null : venueid.trim();
    }

    /**
     * 内容
     * @return Content 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}