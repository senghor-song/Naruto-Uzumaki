package com.xiaoyi.ssm.model;

import java.io.Serializable;

/**
 * 第三方账户绑定表实体
 */
public class MassBind implements Serializable {
	/** 网站信息 */
	private WebSite webSite;
	
    public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	/** 推房->第三方账户绑定（每网站最多三个账户） */
    private String id;

    /** 经纪人ID */
    private String empid;

    /** 第三方网站 */
    private String platform;

    /** 账户 */
    private String account;

    /** 账户密码 */
    private String pwd;

    /** 账户附加信息（部分网站使用，下划线隔开） */
    private String accountmes;

    /** 后台可用性检查（0=异常，1=正常） */
    private Byte checkavailable;

    /** 后台可用性检查提示信息 */
    private String checkmes;

    /** 存储加密密码 */
    private String remark;

    /**
     * MassBind
     */
    private static final long serialVersionUID = 1L;

    /**
     * 推房->第三方账户绑定（每网站最多三个账户）
     * @return ID 推房->第三方账户绑定（每网站最多三个账户）
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->第三方账户绑定（每网站最多三个账户）
     * @param id 推房->第三方账户绑定（每网站最多三个账户）
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
     * 第三方网站
     * @return Platform 第三方网站
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * 第三方网站
     * @param platform 第三方网站
     */
    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
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
     * 账户附加信息（部分网站使用，下划线隔开）
     * @return AccountMes 账户附加信息（部分网站使用，下划线隔开）
     */
    public String getAccountmes() {
        return accountmes;
    }

    /**
     * 账户附加信息（部分网站使用，下划线隔开）
     * @param accountmes 账户附加信息（部分网站使用，下划线隔开）
     */
    public void setAccountmes(String accountmes) {
        this.accountmes = accountmes == null ? null : accountmes.trim();
    }

    /**
     * 后台可用性检查（0=异常，1=正常）
     * @return CheckAvailable 后台可用性检查（0=异常，1=正常）
     */
    public Byte getCheckavailable() {
        return checkavailable;
    }

    /**
     * 后台可用性检查（0=异常，1=正常）
     * @param checkavailable 后台可用性检查（0=异常，1=正常）
     */
    public void setCheckavailable(Byte checkavailable) {
        this.checkavailable = checkavailable;
    }

    /**
     * 后台可用性检查提示信息
     * @return CheckMes 后台可用性检查提示信息
     */
    public String getCheckmes() {
        return checkmes;
    }

    /**
     * 后台可用性检查提示信息
     * @param checkmes 后台可用性检查提示信息
     */
    public void setCheckmes(String checkmes) {
        this.checkmes = checkmes == null ? null : checkmes.trim();
    }

    /**
     * 存储加密密码
     * @return Remark 存储加密密码
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 存储加密密码
     * @param remark 存储加密密码
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}