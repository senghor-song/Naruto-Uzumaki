package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class Manager implements Serializable {
    /** 管理员 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 修改时间 */
    private Date modifytime;

    /** 姓名 */
    private String name;

    /** 手机号 */
    private String phone;

    /** 密码 */
    private String password;

    /** 0=超级管理员，1=管理员 */
    private Integer type;

    /** 实名认证状态(0=否1=是) */
    private Integer realname;

    /** 支付宝app_auth_token */
    private String appauthtoken;

    /**
     * Manager
     */
    private static final long serialVersionUID = 1L;

    /**
     * 管理员
     * @return ID 管理员
     */
    public String getId() {
        return id;
    }

    /**
     * 管理员
     * @param id 管理员
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
     * 0=超级管理员，1=管理员
     * @return Type 0=超级管理员，1=管理员
     */
    public Integer getType() {
        return type;
    }

    /**
     * 0=超级管理员，1=管理员
     * @param type 0=超级管理员，1=管理员
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 实名认证状态(0=否1=是)
     * @return RealName 实名认证状态(0=否1=是)
     */
    public Integer getRealname() {
        return realname;
    }

    /**
     * 实名认证状态(0=否1=是)
     * @param realname 实名认证状态(0=否1=是)
     */
    public void setRealname(Integer realname) {
        this.realname = realname;
    }

    /**
     * 支付宝app_auth_token
     * @return AppAuthToken 支付宝app_auth_token
     */
    public String getAppauthtoken() {
        return appauthtoken;
    }

    /**
     * 支付宝app_auth_token
     * @param appauthtoken 支付宝app_auth_token
     */
    public void setAppauthtoken(String appauthtoken) {
        this.appauthtoken = appauthtoken == null ? null : appauthtoken.trim();
    }
}