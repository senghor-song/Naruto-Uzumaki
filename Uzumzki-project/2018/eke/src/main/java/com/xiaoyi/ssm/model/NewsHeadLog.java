package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 小区日志实体
 */
public class NewsHeadLog implements Serializable {
	/** 管理员 */
	private Staff staff;
	
    public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	/** 运营->楼讯修改日志 */
    private String id;

    /** 楼讯ID */
    private String newsheadid;

    /** 修改人(Staff) */
    private String editid;

    /** 日志时间 */
    private Date logtime;

    /** 内容 */
    private String content;

    /**
     * NewsHeadLog
     */
    private static final long serialVersionUID = 1L;

    /**
     * 运营->楼讯修改日志
     * @return ID 运营->楼讯修改日志
     */
    public String getId() {
        return id;
    }

    /**
     * 运营->楼讯修改日志
     * @param id 运营->楼讯修改日志
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 楼讯ID
     * @return NewsHeadID 楼讯ID
     */
    public String getNewsheadid() {
        return newsheadid;
    }

    /**
     * 楼讯ID
     * @param newsheadid 楼讯ID
     */
    public void setNewsheadid(String newsheadid) {
        this.newsheadid = newsheadid == null ? null : newsheadid.trim();
    }

    /**
     * 修改人(Staff)
     * @return EditID 修改人(Staff)
     */
    public String getEditid() {
        return editid;
    }

    /**
     * 修改人(Staff)
     * @param editid 修改人(Staff)
     */
    public void setEditid(String editid) {
        this.editid = editid == null ? null : editid.trim();
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