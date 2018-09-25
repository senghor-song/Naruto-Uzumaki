package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员表实体
 */
public class Member implements Serializable {
    /** 会员 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 修改时间 */
    private Date modifytime;

    /** 姓名 */
    private String name;

    /** 性别(0=男1=女) */
    private Integer sex;

    /** 手机号 */
    private String phone;

    /** 密码 */
    private String password;

    /** 管理员ID */
    private String managerid;

    /** 微信公众号的openid */
    private String openid;

    /** 小程序的openid */
    private String appopenid;

    /** 公众平台下的唯一标识 */
    private String unionid;

    /** 微信昵称 */
    private String appnickname;

    /** 头像URL */
    private String appavatarurl;

    /** 微信性别(0=未知1=女2=男) */
    private Integer appgender;

    /** 微信号 */
    private String wechatid;

    /** 微信转账免手续费额度 */
    private Double wxpayment;

    /**
     * Member
     */
    private static final long serialVersionUID = 1L;

    /**
     * 会员
     * @return ID 会员
     */
    public String getId() {
        return id;
    }

    /**
     * 会员
     * @param id 会员
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
     * 修改时间
     * @return ModifyTime 修改时间
     */
    public Date getModifytime() {
        return modifytime;
    }

    /**
     * 修改时间
     * @param modifytime 修改时间
     */
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * 姓名
     * @return Name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 性别(0=男1=女)
     * @return Sex 性别(0=男1=女)
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 性别(0=男1=女)
     * @param sex 性别(0=男1=女)
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 手机号
     * @return Phone 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 密码
     * @return Password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 管理员ID
     * @return ManagerID 管理员ID
     */
    public String getManagerid() {
        return managerid;
    }

    /**
     * 管理员ID
     * @param managerid 管理员ID
     */
    public void setManagerid(String managerid) {
        this.managerid = managerid == null ? null : managerid.trim();
    }

    /**
     * 微信公众号的openid
     * @return OpenID 微信公众号的openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 微信公众号的openid
     * @param openid 微信公众号的openid
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * 小程序的openid
     * @return AppOpenID 小程序的openid
     */
    public String getAppopenid() {
        return appopenid;
    }

    /**
     * 小程序的openid
     * @param appopenid 小程序的openid
     */
    public void setAppopenid(String appopenid) {
        this.appopenid = appopenid == null ? null : appopenid.trim();
    }

    /**
     * 公众平台下的唯一标识
     * @return UnionID 公众平台下的唯一标识
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * 公众平台下的唯一标识
     * @param unionid 公众平台下的唯一标识
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    /**
     * 微信昵称
     * @return AppNickName 微信昵称
     */
    public String getAppnickname() {
        return appnickname;
    }

    /**
     * 微信昵称
     * @param appnickname 微信昵称
     */
    public void setAppnickname(String appnickname) {
        this.appnickname = appnickname == null ? null : appnickname.trim();
    }

    /**
     * 头像URL
     * @return AppAvatarUrl 头像URL
     */
    public String getAppavatarurl() {
        return appavatarurl;
    }

    /**
     * 头像URL
     * @param appavatarurl 头像URL
     */
    public void setAppavatarurl(String appavatarurl) {
        this.appavatarurl = appavatarurl == null ? null : appavatarurl.trim();
    }

    /**
     * 微信性别(0=未知1=女2=男)
     * @return AppGender 微信性别(0=未知1=女2=男)
     */
    public Integer getAppgender() {
        return appgender;
    }

    /**
     * 微信性别(0=未知1=女2=男)
     * @param appgender 微信性别(0=未知1=女2=男)
     */
    public void setAppgender(Integer appgender) {
        this.appgender = appgender;
    }

    /**
     * 微信号
     * @return WechatID 微信号
     */
    public String getWechatid() {
        return wechatid;
    }

    /**
     * 微信号
     * @param wechatid 微信号
     */
    public void setWechatid(String wechatid) {
        this.wechatid = wechatid == null ? null : wechatid.trim();
    }

    /**
     * 微信转账免手续费额度
     * @return WXPayment 微信转账免手续费额度
     */
    public Double getWxpayment() {
        return wxpayment;
    }

    /**
     * 微信转账免手续费额度
     * @param wxpayment 微信转账免手续费额度
     */
    public void setWxpayment(Double wxpayment) {
        this.wxpayment = wxpayment;
    }
}