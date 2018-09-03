package com.xiaoyi.ssm.model;

import java.io.Serializable;

public class MassEmpLayout implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 推房->经纪人户型图仓库
     */
    private String id;

    /**
     * 经纪人ID
     */
    private String empid;

    /**
     * 户型图URL
     */
    private String layout;

    /**
     * 
     */
    private String remark;

    /**
     * 推房->经纪人户型图仓库
     * @return ID 推房->经纪人户型图仓库
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->经纪人户型图仓库
     * @param id 推房->经纪人户型图仓库
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
     * 户型图URL
     * @return Layout 户型图URL
     */
    public String getLayout() {
        return layout;
    }

    /**
     * 户型图URL
     * @param layout 户型图URL
     */
    public void setLayout(String layout) {
        this.layout = layout == null ? null : layout.trim();
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