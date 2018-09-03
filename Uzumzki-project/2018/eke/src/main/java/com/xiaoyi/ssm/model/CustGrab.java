package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 抢客实体
 */
public class CustGrab implements Serializable {
    /** 商户->抢客 */
    private String id;

    /** 经纪ID */
    private String empid;

    /** 抢客时间 */
    private Date grabtime;

    /** 客户ID */
    private String custid;

    /** 备注 */
    private String remark;

    /**
     * CustGrab
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商户->抢客
     * @return ID 商户->抢客
     */
    public String getId() {
        return id;
    }

    /**
     * 商户->抢客
     * @param id 商户->抢客
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 经纪ID
     * @return EmpID 经纪ID
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * 经纪ID
     * @param empid 经纪ID
     */
    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
    }

    /**
     * 抢客时间
     * @return GrabTime 抢客时间
     */
    public Date getGrabtime() {
        return grabtime;
    }

    /**
     * 抢客时间
     * @param grabtime 抢客时间
     */
    public void setGrabtime(Date grabtime) {
        this.grabtime = grabtime;
    }

    /**
     * 客户ID
     * @return CustID 客户ID
     */
    public String getCustid() {
        return custid;
    }

    /**
     * 客户ID
     * @param custid 客户ID
     */
    public void setCustid(String custid) {
        this.custid = custid == null ? null : custid.trim();
    }

    /**
     * 备注
     * @return Remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}