package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class CityLog implements Serializable {
	private Staff staff;
	
    public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	/** 城市日志表 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 城市ID */
    private String cityid;

    /** 管理员ID */
    private String staffid;

    /** 内容 */
    private String content;

    /**
     * CityLog
     */
    private static final long serialVersionUID = 1L;

    /**
     * 城市日志表
     * @return ID 城市日志表
     */
    public String getId() {
        return id;
    }

    /**
     * 城市日志表
     * @param id 城市日志表
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
     * 城市ID
     * @return CityID 城市ID
     */
    public String getCityid() {
        return cityid;
    }

    /**
     * 城市ID
     * @param cityid 城市ID
     */
    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    /**
     * 管理员ID
     * @return StaffID 管理员ID
     */
    public String getStaffid() {
        return staffid;
    }

    /**
     * 管理员ID
     * @param staffid 管理员ID
     */
    public void setStaffid(String staffid) {
        this.staffid = staffid == null ? null : staffid.trim();
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