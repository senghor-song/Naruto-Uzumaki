package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 公盘表实体
 */
public class PropertyPub implements Serializable {
	
	private Employee employee;
	
    public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/** 租售->公盘 */
    private String id;

    /** 分发时间 */
    private Date pubtime;

    /** 对应预登记房源ID */
    private String propertypreid;

    /** 0=自动分发，1=人工分发 */
    private Byte pubtype;

    /** 经纪人ID */
    private String empid;

    /** 经纪人认领时间 */
    private Date empclaimtime;

    /** 经纪人是否认领0=否1=是 */
    private Byte empclaimflag;

    /** 备注信息 */
    private String remark;

    /**
     * PropertyPub
     */
    private static final long serialVersionUID = 1L;

    /**
     * 租售->公盘
     * @return ID 租售->公盘
     */
    public String getId() {
        return id;
    }

    /**
     * 租售->公盘
     * @param id 租售->公盘
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 分发时间
     * @return PubTime 分发时间
     */
    public Date getPubtime() {
        return pubtime;
    }

    /**
     * 分发时间
     * @param pubtime 分发时间
     */
    public void setPubtime(Date pubtime) {
        this.pubtime = pubtime;
    }

    /**
     * 对应预登记房源ID
     * @return PropertyPreID 对应预登记房源ID
     */
    public String getPropertypreid() {
        return propertypreid;
    }

    /**
     * 对应预登记房源ID
     * @param propertypreid 对应预登记房源ID
     */
    public void setPropertypreid(String propertypreid) {
        this.propertypreid = propertypreid == null ? null : propertypreid.trim();
    }

    /**
     * 0=自动分发，1=人工分发
     * @return PubType 0=自动分发，1=人工分发
     */
    public Byte getPubtype() {
        return pubtype;
    }

    /**
     * 0=自动分发，1=人工分发
     * @param pubtype 0=自动分发，1=人工分发
     */
    public void setPubtype(Byte pubtype) {
        this.pubtype = pubtype;
    }

    /**
     * 经纪人ID
     * @return EmpID 经纪人ID
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * 经纪人ID
     * @param empid 经纪人ID
     */
    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
    }

    /**
     * 经纪人认领时间
     * @return EmpClaimTime 经纪人认领时间
     */
    public Date getEmpclaimtime() {
        return empclaimtime;
    }

    /**
     * 经纪人认领时间
     * @param empclaimtime 经纪人认领时间
     */
    public void setEmpclaimtime(Date empclaimtime) {
        this.empclaimtime = empclaimtime;
    }

    /**
     * 经纪人是否认领0=否1=是
     * @return EmpClaimFlag 经纪人是否认领0=否1=是
     */
    public Byte getEmpclaimflag() {
        return empclaimflag;
    }

    /**
     * 经纪人是否认领0=否1=是
     * @param empclaimflag 经纪人是否认领0=否1=是
     */
    public void setEmpclaimflag(Byte empclaimflag) {
        this.empclaimflag = empclaimflag;
    }

    /**
     * 备注信息
     * @return Remark 备注信息
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注信息
     * @param remark 备注信息
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}