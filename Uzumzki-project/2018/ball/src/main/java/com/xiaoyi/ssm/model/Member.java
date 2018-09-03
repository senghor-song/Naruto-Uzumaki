package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
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

    /** 微信支付用的openid */
    private String openid;

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
     * 微信支付用的openid
     * @return OpenID 微信支付用的openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 微信支付用的openid
     * @param openid 微信支付用的openid
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }
}