package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class AmountLog implements Serializable {
	private Staff staff;
	
    public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	/** 提现日志表 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 提现ID */
    private String amountid;

    /** 管理员ID */
    private String staffid;

    /** 类别 */
    private Integer type;

    /** 内容 */
    private String content;

    /**
     * AmountLog
     */
    private static final long serialVersionUID = 1L;

    /**
     * 提现日志表
     * @return ID 提现日志表
     */
    public String getId() {
        return id;
    }

    /**
     * 提现日志表
     * @param id 提现日志表
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
     * 提现ID
     * @return AmountID 提现ID
     */
    public String getAmountid() {
        return amountid;
    }

    /**
     * 提现ID
     * @param amountid 提现ID
     */
    public void setAmountid(String amountid) {
        this.amountid = amountid == null ? null : amountid.trim();
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
     * 类别
     * @return Type 类别
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类别
     * @param type 类别
     */
    public void setType(Integer type) {
        this.type = type;
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