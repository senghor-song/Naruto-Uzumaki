package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 小区日志实体
 */
public class NewsCustLog implements Serializable {
	/** 管理员 */
	private Staff staff;
	
    public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	/** 运营->客户端新闻修改日志 */
    private String id;

    /** 新闻ID */
    private String newscustid;

    /** 操作人(staff) */
    private String editid;

    /** 日志时间 */
    private Date logtime;

    /** 内容 */
    private String content;

    /**
     * NewsCustLog
     */
    private static final long serialVersionUID = 1L;

    /**
     * 运营->客户端新闻修改日志
     * @return ID 运营->客户端新闻修改日志
     */
    public String getId() {
        return id;
    }

    /**
     * 运营->客户端新闻修改日志
     * @param id 运营->客户端新闻修改日志
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 新闻ID
     * @return NewsCustID 新闻ID
     */
    public String getNewscustid() {
        return newscustid;
    }

    /**
     * 新闻ID
     * @param newscustid 新闻ID
     */
    public void setNewscustid(String newscustid) {
        this.newscustid = newscustid == null ? null : newscustid.trim();
    }

    /**
     * 操作人(staff)
     * @return EditID 操作人(staff)
     */
    public String getEditid() {
        return editid;
    }

    /**
     * 操作人(staff)
     * @param editid 操作人(staff)
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