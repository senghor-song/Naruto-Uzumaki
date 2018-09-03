package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 小区日志实体
 */
/**  
 * @Description: 
 * @author 宋高俊  
 * @date 2018年7月30日 下午12:12:23 
 */ 
public class EstateLog implements Serializable {
	
	/** 伙伴详情 */
	private Staff staffT;
	
    public Staff getStaffT() {
		return staffT;
	}

	public void setStaffT(Staff staffT) {
		this.staffT = staffT;
	}

	/** 租售->小区日志 */
    private String id;

    /** 小区ID */
    private String estateid;

    /** 日志时间 */
    private Date logtime;

    /** 伙伴ID */
    private String staffid;

    /** 伙伴 */
    private String staff;

    /** 内容 */
    private String content;

    /**
     * EstateLog
     */
    private static final long serialVersionUID = 1L;

    /**
     * 租售->小区日志
     * @return ID 租售->小区日志
     */
    public String getId() {
        return id;
    }

    /**
     * 租售->小区日志
     * @param id 租售->小区日志
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 小区ID
     * @return EstateID 小区ID
     */
    public String getEstateid() {
        return estateid;
    }

    /**
     * 小区ID
     * @param estateid 小区ID
     */
    public void setEstateid(String estateid) {
        this.estateid = estateid == null ? null : estateid.trim();
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
     * 伙伴ID
     * @return StaffID 伙伴ID
     */
    public String getStaffid() {
        return staffid;
    }

    /**
     * 伙伴ID
     * @param staffid 伙伴ID
     */
    public void setStaffid(String staffid) {
        this.staffid = staffid == null ? null : staffid.trim();
    }

    /**
     * 伙伴
     * @return Staff 伙伴
     */
    public String getStaff() {
        return staff;
    }

    /**
     * 伙伴
     * @param staff 伙伴
     */
    public void setStaff(String staff) {
        this.staff = staff == null ? null : staff.trim();
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