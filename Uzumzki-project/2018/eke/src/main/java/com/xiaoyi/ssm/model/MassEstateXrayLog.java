package com.xiaoyi.ssm.model;

import java.io.Serializable;

public class MassEstateXrayLog implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 推房->小区透视经纪人使用量
     */
    private String id;

    /**
     * 经纪人ID
     */
    private String empid;

    /**
     * 调用时间
     */
    private String request;

    /**
     * 
     */
    private String remark;

    /**
     * 推房->小区透视经纪人使用量
     * @return ID 推房->小区透视经纪人使用量
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->小区透视经纪人使用量
     * @param id 推房->小区透视经纪人使用量
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
     * 调用时间
     * @return Request 调用时间
     */
    public String getRequest() {
        return request;
    }

    /**
     * 调用时间
     * @param request 调用时间
     */
    public void setRequest(String request) {
        this.request = request == null ? null : request.trim();
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