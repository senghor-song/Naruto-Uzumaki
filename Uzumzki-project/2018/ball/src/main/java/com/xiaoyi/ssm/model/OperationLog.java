package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class OperationLog implements Serializable {
	private Staff staff;
	
    public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	/** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 操作人 */
    private String staffId;

    /** 操作内容 */
    private String content;

    /** 操作IP */
    private String ip;

    /**
     * OperationLog
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
     * 操作内容
     * @return Content 操作内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 操作内容
     * @param content 操作内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 操作IP
     * @return Ip 操作IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * 操作IP
     * @param ip 操作IP
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
}