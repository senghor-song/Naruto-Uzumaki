package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 房源表实体
 */
public class MassPropertyPublish implements Serializable {
	/** 经纪人 */
	private Employee employee;
	/** 房源 */
	private Property property;
	/** 商户 */
	private EmpStore empStore;
	
    public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public EmpStore getEmpStore() {
		return empStore;
	}

	public void setEmpStore(EmpStore empStore) {
		this.empStore = empStore;
	}
    /** 推房->房库群发任务单 */
    private String id;

    /** 房源ID */
    private String masspropertyid;

    /** 群发时间 */
    private Date publishtime;

    /** 发布的第三方网站 */
    private String site;

    /** 发布状态（0=失败，1=成功） */
    private Byte status;

    /** 状态信息 */
    private String statumes;

    /** 房源链接（第三方网站） */
    private String publishurl;

    /** 经纪人ID(辅助) */
    private String empid;

    /** 经纪人(辅助) */
    private String emp;

    /**  */
    private String remark;

    /**
     * MassPropertyPublish
     */
    private static final long serialVersionUID = 1L;

    /**
     * 推房->房库群发任务单
     * @return ID 推房->房库群发任务单
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->房库群发任务单
     * @param id 推房->房库群发任务单
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 房源ID
     * @return MassPropertyID 房源ID
     */
    public String getMasspropertyid() {
        return masspropertyid;
    }

    /**
     * 房源ID
     * @param masspropertyid 房源ID
     */
    public void setMasspropertyid(String masspropertyid) {
        this.masspropertyid = masspropertyid == null ? null : masspropertyid.trim();
    }

    /**
     * 群发时间
     * @return PublishTime 群发时间
     */
    public Date getPublishtime() {
        return publishtime;
    }

    /**
     * 群发时间
     * @param publishtime 群发时间
     */
    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    /**
     * 发布的第三方网站
     * @return Site 发布的第三方网站
     */
    public String getSite() {
        return site;
    }

    /**
     * 发布的第三方网站
     * @param site 发布的第三方网站
     */
    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    /**
     * 发布状态（0=失败，1=成功）
     * @return Status 发布状态（0=失败，1=成功）
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 发布状态（0=失败，1=成功）
     * @param status 发布状态（0=失败，1=成功）
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 状态信息
     * @return StatuMes 状态信息
     */
    public String getStatumes() {
        return statumes;
    }

    /**
     * 状态信息
     * @param statumes 状态信息
     */
    public void setStatumes(String statumes) {
        this.statumes = statumes == null ? null : statumes.trim();
    }

    /**
     * 房源链接（第三方网站）
     * @return PublishUrl 房源链接（第三方网站）
     */
    public String getPublishurl() {
        return publishurl;
    }

    /**
     * 房源链接（第三方网站）
     * @param publishurl 房源链接（第三方网站）
     */
    public void setPublishurl(String publishurl) {
        this.publishurl = publishurl == null ? null : publishurl.trim();
    }

    /**
     * 经纪人ID(辅助)
     * @return EmpID 经纪人ID(辅助)
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * 经纪人ID(辅助)
     * @param empid 经纪人ID(辅助)
     */
    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
    }

    /**
     * 经纪人(辅助)
     * @return Emp 经纪人(辅助)
     */
    public String getEmp() {
        return emp;
    }

    /**
     * 经纪人(辅助)
     * @param emp 经纪人(辅助)
     */
    public void setEmp(String emp) {
        this.emp = emp == null ? null : emp.trim();
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