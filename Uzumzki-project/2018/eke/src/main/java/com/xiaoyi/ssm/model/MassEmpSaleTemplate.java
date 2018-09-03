package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

public class MassEmpSaleTemplate implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 推房->房源录入-出售录入5个模板表
     */
    private String id;

    /**
     * 创建时间
     */
    private Date addtime;

    /**
     * 经纪人ID
     */
    private String empid;

    /**
     * 类型(0=通用1=出售2=出租)
     */
    private Integer type;

    /**
     * 描述标题
     */
    private String title;

    /**
     * 业主心态
     */
    private String mentality;

    /**
     * 小区配置
     */
    private String environment;

    /**
     * 服务介绍
     */
    private String serve;

    /**
     * 税费信息
     */
    private String taxation;

    /**
     * 描述信息
     */
    private String describedata;

    /**
     * 推房->房源录入-出售录入5个模板表
     * @return ID 推房->房源录入-出售录入5个模板表
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->房源录入-出售录入5个模板表
     * @param id 推房->房源录入-出售录入5个模板表
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 创建时间
     * @return AddTime 创建时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 创建时间
     * @param addtime 创建时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
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
     * 类型(0=通用1=出售2=出租)
     * @return Type 类型(0=通用1=出售2=出租)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型(0=通用1=出售2=出租)
     * @param type 类型(0=通用1=出售2=出租)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 描述标题
     * @return Title 描述标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 描述标题
     * @param title 描述标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 业主心态
     * @return Mentality 业主心态
     */
    public String getMentality() {
        return mentality;
    }

    /**
     * 业主心态
     * @param mentality 业主心态
     */
    public void setMentality(String mentality) {
        this.mentality = mentality == null ? null : mentality.trim();
    }

    /**
     * 小区配置
     * @return Environment 小区配置
     */
    public String getEnvironment() {
        return environment;
    }

    /**
     * 小区配置
     * @param environment 小区配置
     */
    public void setEnvironment(String environment) {
        this.environment = environment == null ? null : environment.trim();
    }

    /**
     * 服务介绍
     * @return Serve 服务介绍
     */
    public String getServe() {
        return serve;
    }

    /**
     * 服务介绍
     * @param serve 服务介绍
     */
    public void setServe(String serve) {
        this.serve = serve == null ? null : serve.trim();
    }

    /**
     * 税费信息
     * @return Taxation 税费信息
     */
    public String getTaxation() {
        return taxation;
    }

    /**
     * 税费信息
     * @param taxation 税费信息
     */
    public void setTaxation(String taxation) {
        this.taxation = taxation == null ? null : taxation.trim();
    }

    /**
     * 描述信息
     * @return DescribeData 描述信息
     */
    public String getDescribedata() {
        return describedata;
    }

    /**
     * 描述信息
     * @param describedata 描述信息
     */
    public void setDescribedata(String describedata) {
        this.describedata = describedata == null ? null : describedata.trim();
    }
}