package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class NewsLog implements Serializable {
	
	private Staff staff;
	
    public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	/** 订场日志 */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 资讯ID */
    private String newsId;

    /** 操作人 */
    private String staffId;

    /** 内容 */
    private String content;

    /**
     * NewsLog
     */
    private static final long serialVersionUID = 1L;

    /**
     * 订场日志
     * @return ID 订场日志
     */
    public String getId() {
        return id;
    }

    /**
     * 订场日志
     * @param id 订场日志
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
     * 资讯ID
     * @return News_id 资讯ID
     */
    public String getNewsId() {
        return newsId;
    }

    /**
     * 资讯ID
     * @param newsId 资讯ID
     */
    public void setNewsId(String newsId) {
        this.newsId = newsId == null ? null : newsId.trim();
    }

    /**
     * 操作人
     * @return Staff_id 操作人
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * 操作人
     * @param staffId 操作人
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
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