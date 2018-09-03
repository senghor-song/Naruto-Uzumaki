package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

public class BlackListEmp implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 运营->黑名单（经纪人自建）
     */
    private String id;

    /**
     * 经纪ID
     */
    private String empid;

    /**
     * 电话号码
     */
    private String tel;

    /**
     * 描述信息
     */
    private String mes;

    /**
     * 逻辑删除
     */
    private Byte flagdeleted;

    /**
     * 删除时间
     */
    private Date deletedtime;

    /**
     * 
     */
    private String remark;

    /**
     * 运营->黑名单（经纪人自建）
     * @return ID 运营->黑名单（经纪人自建）
     */
    public String getId() {
        return id;
    }

    /**
     * 运营->黑名单（经纪人自建）
     * @param id 运营->黑名单（经纪人自建）
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
     * 电话号码
     * @return Tel 电话号码
     */
    public String getTel() {
        return tel;
    }

    /**
     * 电话号码
     * @param tel 电话号码
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * 描述信息
     * @return Mes 描述信息
     */
    public String getMes() {
        return mes;
    }

    /**
     * 描述信息
     * @param mes 描述信息
     */
    public void setMes(String mes) {
        this.mes = mes == null ? null : mes.trim();
    }

    /**
     * 逻辑删除
     * @return FlagDeleted 逻辑删除
     */
    public Byte getFlagdeleted() {
        return flagdeleted;
    }

    /**
     * 逻辑删除
     * @param flagdeleted 逻辑删除
     */
    public void setFlagdeleted(Byte flagdeleted) {
        this.flagdeleted = flagdeleted;
    }

    /**
     * 删除时间
     * @return DeletedTime 删除时间
     */
    public Date getDeletedtime() {
        return deletedtime;
    }

    /**
     * 删除时间
     * @param deletedtime 删除时间
     */
    public void setDeletedtime(Date deletedtime) {
        this.deletedtime = deletedtime;
    }

    /**
     * 
     * @return Remark 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * @param remark 
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}