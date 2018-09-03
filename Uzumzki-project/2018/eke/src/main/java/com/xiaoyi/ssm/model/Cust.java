package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

public class Cust implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;

	/**
     * 运营->平台注册用户
     */
    private String id;

    /**
     * 姓名
     */
    private String cust;

    /**
     * 编号
     */
    private String custno;

    /**
     * 性别
     */
    private String sex;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像URL
     */
    private String icon;

    /**
     * 登录密码
     */
    private String pwd;

    /**
     * 联系地址
     */
    private String contact;

    /**
     * 注册日期
     */
    private Date regdate;

    /**
     * 住址
     */
    private String address;

    /**
     * 国籍
     */
    private String country;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 个性签名
     */
    private String sign;

    /**
     * 微信OpenID
     */
    private String wxopenid;

    /**
     * 专属顾问商户
     */
    private String empstoreid;

    /**
     * （辅助）
     */
    private String empstore;

    /**
     * 备注
     */
    private String remark;

    /**
     * 运营->平台注册用户
     * @return ID 运营->平台注册用户
     */
    public String getId() {
        return id;
    }

    /**
     * 运营->平台注册用户
     * @param id 运营->平台注册用户
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 姓名
     * @return Cust 姓名
     */
    public String getCust() {
        return cust;
    }

    /**
     * 姓名
     * @param cust 姓名
     */
    public void setCust(String cust) {
        this.cust = cust == null ? null : cust.trim();
    }

    /**
     * 编号
     * @return CustNo 编号
     */
    public String getCustno() {
        return custno;
    }

    /**
     * 编号
     * @param custno 编号
     */
    public void setCustno(String custno) {
        this.custno = custno == null ? null : custno.trim();
    }

    /**
     * 性别
     * @return Sex 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 手机号
     * @return Mobile 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机号
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 头像URL
     * @return Icon 头像URL
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 头像URL
     * @param icon 头像URL
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 登录密码
     * @return Pwd 登录密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 登录密码
     * @param pwd 登录密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    /**
     * 联系地址
     * @return Contact 联系地址
     */
    public String getContact() {
        return contact;
    }

    /**
     * 联系地址
     * @param contact 联系地址
     */
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    /**
     * 注册日期
     * @return RegDate 注册日期
     */
    public Date getRegdate() {
        return regdate;
    }

    /**
     * 注册日期
     * @param regdate 注册日期
     */
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    /**
     * 住址
     * @return Address 住址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 住址
     * @param address 住址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 国籍
     * @return Country 国籍
     */
    public String getCountry() {
        return country;
    }

    /**
     * 国籍
     * @param country 国籍
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * 邮箱
     * @return Email 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 个性签名
     * @return Sign 个性签名
     */
    public String getSign() {
        return sign;
    }

    /**
     * 个性签名
     * @param sign 个性签名
     */
    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    /**
     * 微信OpenID
     * @return WXOpenID 微信OpenID
     */
    public String getWxopenid() {
        return wxopenid;
    }

    /**
     * 微信OpenID
     * @param wxopenid 微信OpenID
     */
    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid == null ? null : wxopenid.trim();
    }

    /**
     * 专属顾问商户
     * @return EmpStoreID 专属顾问商户
     */
    public String getEmpstoreid() {
        return empstoreid;
    }

    /**
     * 专属顾问商户
     * @param empstoreid 专属顾问商户
     */
    public void setEmpstoreid(String empstoreid) {
        this.empstoreid = empstoreid == null ? null : empstoreid.trim();
    }

    /**
     * （辅助）
     * @return EmpStore （辅助）
     */
    public String getEmpstore() {
        return empstore;
    }

    /**
     * （辅助）
     * @param empstore （辅助）
     */
    public void setEmpstore(String empstore) {
        this.empstore = empstore == null ? null : empstore.trim();
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