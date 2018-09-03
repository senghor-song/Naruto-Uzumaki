package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 商户商洽日志实体
 */
public class EmpStoreLog implements Serializable {
    /** 商户->商户变更日志 */
    private String id;

    /** 商户ID */
    private String empstoreid;

    /** 创建时间 */
    private Date createtime;

    /** 伙伴ID */
    private String staffid;

    /** 内容 */
    private String mes;

    /** 备注 */
    private String remark;

    /**
     * EmpStoreLog
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商户->商户变更日志
     * @return ID 商户->商户变更日志
     */
    public String getId() {
        return id;
    }

    /**
     * 商户->商户变更日志
     * @param id 商户->商户变更日志
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 商户ID
     * @return EmpStoreID 商户ID
     */
    public String getEmpstoreid() {
        return empstoreid;
    }

    /**
     * 商户ID
     * @param empstoreid 商户ID
     */
    public void setEmpstoreid(String empstoreid) {
        this.empstoreid = empstoreid == null ? null : empstoreid.trim();
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
     * 内容
     * @return Mes 内容
     */
    public String getMes() {
        return mes;
    }

    /**
     * 内容
     * @param mes 内容
     */
    public void setMes(String mes) {
        this.mes = mes == null ? null : mes.trim();
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