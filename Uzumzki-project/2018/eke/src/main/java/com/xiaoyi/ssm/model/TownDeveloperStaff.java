package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class TownDeveloperStaff implements Serializable {
    /** 新房->开发商管理员账号 */
    private String id;

    /** 开发商管理员（如：现场张经理） */
    private String towndeveloperstaff;

    /** 绑定手机号 */
    private String tel;

    /** 新盘ID */
    private String townid;

    /** (辅助) */
    private String town;

    /** 状态 */
    private String status;

    /** 登录名 */
    private String loginname;

    /** 登录密码 */
    private String loginpassword;

    /** 最近登录时间 */
    private Date logindate;

    /** 删除标志 */
    private Byte flagdeleted;

    /** 删除时间 */
    private Date deletedtime;

    /** 备注 */
    private String remark;

    /**
     * TownDeveloperStaff
     */
    private static final long serialVersionUID = 1L;

    /**
     * 新房->开发商管理员账号
     * @return ID 新房->开发商管理员账号
     */
    public String getId() {
        return id;
    }

    /**
     * 新房->开发商管理员账号
     * @param id 新房->开发商管理员账号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 开发商管理员（如：现场张经理）
     * @return TownDeveloperStaff 开发商管理员（如：现场张经理）
     */
    public String getTowndeveloperstaff() {
        return towndeveloperstaff;
    }

    /**
     * 开发商管理员（如：现场张经理）
     * @param towndeveloperstaff 开发商管理员（如：现场张经理）
     */
    public void setTowndeveloperstaff(String towndeveloperstaff) {
        this.towndeveloperstaff = towndeveloperstaff == null ? null : towndeveloperstaff.trim();
    }

    /**
     * 绑定手机号
     * @return Tel 绑定手机号
     */
    public String getTel() {
        return tel;
    }

    /**
     * 绑定手机号
     * @param tel 绑定手机号
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * 新盘ID
     * @return TownID 新盘ID
     */
    public String getTownid() {
        return townid;
    }

    /**
     * 新盘ID
     * @param townid 新盘ID
     */
    public void setTownid(String townid) {
        this.townid = townid == null ? null : townid.trim();
    }

    /**
     * (辅助)
     * @return Town (辅助)
     */
    public String getTown() {
        return town;
    }

    /**
     * (辅助)
     * @param town (辅助)
     */
    public void setTown(String town) {
        this.town = town == null ? null : town.trim();
    }

    /**
     * 状态
     * @return Status 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 登录名
     * @return LoginName 登录名
     */
    public String getLoginname() {
        return loginname;
    }

    /**
     * 登录名
     * @param loginname 登录名
     */
    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    /**
     * 登录密码
     * @return LoginPassword 登录密码
     */
    public String getLoginpassword() {
        return loginpassword;
    }

    /**
     * 登录密码
     * @param loginpassword 登录密码
     */
    public void setLoginpassword(String loginpassword) {
        this.loginpassword = loginpassword == null ? null : loginpassword.trim();
    }

    /**
     * 最近登录时间
     * @return LoginDate 最近登录时间
     */
    public Date getLogindate() {
        return logindate;
    }

    /**
     * 最近登录时间
     * @param logindate 最近登录时间
     */
    public void setLogindate(Date logindate) {
        this.logindate = logindate;
    }

    /**
     * 删除标志
     * @return FlagDeleted 删除标志
     */
    public Byte getFlagdeleted() {
        return flagdeleted;
    }

    /**
     * 删除标志
     * @param flagdeleted 删除标志
     */
    public void setFlagdeleted(Byte flagdeleted) {
        this.flagdeleted = flagdeleted;
    }

    /**
     * 删除时间
     * @return DeletedTime 删除时间
     */
    public Date getDeletedtime() {
        return deletedtime;
    }

    /**
     * 删除时间
     * @param deletedtime 删除时间
     */
    public void setDeletedtime(Date deletedtime) {
        this.deletedtime = deletedtime;
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