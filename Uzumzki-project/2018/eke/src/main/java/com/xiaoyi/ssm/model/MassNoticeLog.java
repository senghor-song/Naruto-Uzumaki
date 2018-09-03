package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 房源表实体
 */
public class MassNoticeLog implements Serializable {
	
	/** 修改人 */
	private Staff staff;
	
    public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	/** 推房->云刷新-公告原文变更日志 */
    private String id;

    /** 变更时间 */
    private Date modtime;

    /** 变更公告ID */
    private String massnoticeid;

    /** 修改人(staff) */
    private String editid;

    /** 修改内容 */
    private String remark;

    /**
     * MassNoticeLog
     */
    private static final long serialVersionUID = 1L;

    /**
     * 推房->云刷新-公告原文变更日志
     * @return ID 推房->云刷新-公告原文变更日志
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->云刷新-公告原文变更日志
     * @param id 推房->云刷新-公告原文变更日志
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 变更时间
     * @return ModTime 变更时间
     */
    public Date getModtime() {
        return modtime;
    }

    /**
     * 变更时间
     * @param modtime 变更时间
     */
    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }

    /**
     * 变更公告ID
     * @return MassNoticeID 变更公告ID
     */
    public String getMassnoticeid() {
        return massnoticeid;
    }

    /**
     * 变更公告ID
     * @param massnoticeid 变更公告ID
     */
    public void setMassnoticeid(String massnoticeid) {
        this.massnoticeid = massnoticeid == null ? null : massnoticeid.trim();
    }

    /**
     * 修改人(staff)
     * @return EditID 修改人(staff)
     */
    public String getEditid() {
        return editid;
    }

    /**
     * 修改人(staff)
     * @param editid 修改人(staff)
     */
    public void setEditid(String editid) {
        this.editid = editid == null ? null : editid.trim();
    }

    /**
     * 修改内容
     * @return Remark 修改内容
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 修改内容
     * @param remark 修改内容
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}