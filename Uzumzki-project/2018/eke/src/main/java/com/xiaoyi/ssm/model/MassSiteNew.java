package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 房源表实体
 */
public class MassSiteNew implements Serializable {
	/** 经纪人 */
	private Employee employee;
	
	/** 管理员 */
	private Staff staff;
	
    public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
    /** 推房->网站推荐 */
    private String id;

    /** 经纪人ID */
    private String empid;

    /** 新网站网址 */
    private String siteurl;

    /** 新网站名 */
    private String sitename;

    /** 添加时间 */
    private Date addtime;

    /** 账户 */
    private String account;

    /** 账户密码 */
    private String pwd;

    /** 类型（0=采集，1=发布） */
    private Byte type;

    /** 补充说明 */
    private String mes;

    /** 处理人 */
    private String disposestaff;

    /** 处理意见 */
    private String disposeopinion;

    /** 处理时间 */
    private Date disposetime;

    /**  */
    private String remark;

    /**
     * MassSiteNew
     */
    private static final long serialVersionUID = 1L;

    /**
     * 推房->网站推荐
     * @return ID 推房->网站推荐
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->网站推荐
     * @param id 推房->网站推荐
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
     * 新网站网址
     * @return SiteUrl 新网站网址
     */
    public String getSiteurl() {
        return siteurl;
    }

    /**
     * 新网站网址
     * @param siteurl 新网站网址
     */
    public void setSiteurl(String siteurl) {
        this.siteurl = siteurl == null ? null : siteurl.trim();
    }

    /**
     * 新网站名
     * @return SiteName 新网站名
     */
    public String getSitename() {
        return sitename;
    }

    /**
     * 新网站名
     * @param sitename 新网站名
     */
    public void setSitename(String sitename) {
        this.sitename = sitename == null ? null : sitename.trim();
    }

    /**
     * 添加时间
     * @return AddTime 添加时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 添加时间
     * @param addtime 添加时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 账户
     * @return Account 账户
     */
    public String getAccount() {
        return account;
    }

    /**
     * 账户
     * @param account 账户
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 账户密码
     * @return Pwd 账户密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 账户密码
     * @param pwd 账户密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    /**
     * 类型（0=采集，1=发布）
     * @return Type 类型（0=采集，1=发布）
     */
    public Byte getType() {
        return type;
    }

    /**
     * 类型（0=采集，1=发布）
     * @param type 类型（0=采集，1=发布）
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 补充说明
     * @return Mes 补充说明
     */
    public String getMes() {
        return mes;
    }

    /**
     * 补充说明
     * @param mes 补充说明
     */
    public void setMes(String mes) {
        this.mes = mes == null ? null : mes.trim();
    }

    /**
     * 处理人
     * @return DisposeStaff 处理人
     */
    public String getDisposestaff() {
        return disposestaff;
    }

    /**
     * 处理人
     * @param disposestaff 处理人
     */
    public void setDisposestaff(String disposestaff) {
        this.disposestaff = disposestaff == null ? null : disposestaff.trim();
    }

    /**
     * 处理意见
     * @return DisposeOpinion 处理意见
     */
    public String getDisposeopinion() {
        return disposeopinion;
    }

    /**
     * 处理意见
     * @param disposeopinion 处理意见
     */
    public void setDisposeopinion(String disposeopinion) {
        this.disposeopinion = disposeopinion == null ? null : disposeopinion.trim();
    }

    /**
     * 处理时间
     * @return DisposeTime 处理时间
     */
    public Date getDisposetime() {
        return disposetime;
    }

    /**
     * 处理时间
     * @param disposetime 处理时间
     */
    public void setDisposetime(Date disposetime) {
        this.disposetime = disposetime;
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