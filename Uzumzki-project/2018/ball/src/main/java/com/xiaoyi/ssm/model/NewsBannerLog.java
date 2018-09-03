package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 小区日志实体
 */
public class NewsBannerLog implements Serializable {
	private Staff staff;
	
    public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	/** 运营->横幅新闻日志 */
    private String id;

    /** 横幅新闻ID */
    private String newsbannerid;

    /** 日志时间 */
    private Date logtime;

    /** 操作人ID */
    private String editid;

    /** 内容 */
    private String content;

    /**
     * NewsBannerLog
     */
    private static final long serialVersionUID = 1L;

    /**
     * 运营->横幅新闻日志
     * @return ID 运营->横幅新闻日志
     */
    public String getId() {
        return id;
    }

    /**
     * 运营->横幅新闻日志
     * @param id 运营->横幅新闻日志
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 横幅新闻ID
     * @return NewsBannerID 横幅新闻ID
     */
    public String getNewsbannerid() {
        return newsbannerid;
    }

    /**
     * 横幅新闻ID
     * @param newsbannerid 横幅新闻ID
     */
    public void setNewsbannerid(String newsbannerid) {
        this.newsbannerid = newsbannerid == null ? null : newsbannerid.trim();
    }

    /**
     * 日志时间
     * @return LogTime 日志时间
     */
    public Date getLogtime() {
        return logtime;
    }

    /**
     * 日志时间
     * @param logtime 日志时间
     */
    public void setLogtime(Date logtime) {
        this.logtime = logtime;
    }

    /**
     * 操作人ID
     * @return EditID 操作人ID
     */
    public String getEditid() {
        return editid;
    }

    /**
     * 操作人ID
     * @param editid 操作人ID
     */
    public void setEditid(String editid) {
        this.editid = editid == null ? null : editid.trim();
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