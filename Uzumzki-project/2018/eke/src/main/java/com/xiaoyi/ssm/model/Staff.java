package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 伙伴表实体
 */
public class Staff implements Serializable {
    /** 伙伴->伙伴 */
    private String staffid;

    /** 伙伴编号 */
    private Integer staffno;

    /** 英文名(登录) */
    private String ename;

    /** 中文名(真实) */
    private String rname;

    /** 部门ID(暂未启用) */
    private String deptid;

    /** 登录密码 */
    private String password;

    /** 电话 */
    private String tel;

    /** 状态 */
    private String status;

    /** 岗位 */
    private String position;

    /** 平台权限(最高级七级) */
    private Integer power;

    /** 性别 */
    private String sex;

    /** 身份证号 */
    private String idcard;

    /** 家庭住址 */
    private String address;

    /** 注册邮箱 */
    private String email;

    /** 推荐人 */
    private String referee;

    /** 用户头像URL */
    private String icon;

    /** 当前登录状态（1=已登录/0=未登录） */
    private Byte loginstate;

    /** 登录切换时间 */
    private Date loginchange;

    /** 备注 */
    private String remark;

    /**
     * Staff
     */
    private static final long serialVersionUID = 1L;

    /**
     * 伙伴->伙伴
     * @return StaffID 伙伴->伙伴
     */
    public String getStaffid() {
        return staffid;
    }

    /**
     * 伙伴->伙伴
     * @param staffid 伙伴->伙伴
     */
    public void setStaffid(String staffid) {
        this.staffid = staffid == null ? null : staffid.trim();
    }

    /**
     * 伙伴编号
     * @return StaffNo 伙伴编号
     */
    public Integer getStaffno() {
        return staffno;
    }

    /**
     * 伙伴编号
     * @param staffno 伙伴编号
     */
    public void setStaffno(Integer staffno) {
        this.staffno = staffno;
    }

    /**
     * 英文名(登录)
     * @return EName 英文名(登录)
     */
    public String getEname() {
        return ename;
    }

    /**
     * 英文名(登录)
     * @param ename 英文名(登录)
     */
    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    /**
     * 中文名(真实)
     * @return RName 中文名(真实)
     */
    public String getRname() {
        return rname;
    }

    /**
     * 中文名(真实)
     * @param rname 中文名(真实)
     */
    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }

    /**
     * 部门ID(暂未启用)
     * @return DeptID 部门ID(暂未启用)
     */
    public String getDeptid() {
        return deptid;
    }

    /**
     * 部门ID(暂未启用)
     * @param deptid 部门ID(暂未启用)
     */
    public void setDeptid(String deptid) {
        this.deptid = deptid == null ? null : deptid.trim();
    }

    /**
     * 登录密码
     * @return Password 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 登录密码
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 电话
     * @return Tel 电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 电话
     * @param tel 电话
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
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
     * 岗位
     * @return Position 岗位
     */
    public String getPosition() {
        return position;
    }

    /**
     * 岗位
     * @param position 岗位
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * 平台权限(最高级七级)
     * @return Power 平台权限(最高级七级)
     */
    public Integer getPower() {
        return power;
    }

    /**
     * 平台权限(最高级七级)
     * @param power 平台权限(最高级七级)
     */
    public void setPower(Integer power) {
        this.power = power;
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
     * 身份证号
     * @return IDCard 身份证号
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 身份证号
     * @param idcard 身份证号
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    /**
     * 家庭住址
     * @return Address 家庭住址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 家庭住址
     * @param address 家庭住址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 注册邮箱
     * @return EMail 注册邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 注册邮箱
     * @param email 注册邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 推荐人
     * @return Referee 推荐人
     */
    public String getReferee() {
        return referee;
    }

    /**
     * 推荐人
     * @param referee 推荐人
     */
    public void setReferee(String referee) {
        this.referee = referee == null ? null : referee.trim();
    }

    /**
     * 用户头像URL
     * @return Icon 用户头像URL
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 用户头像URL
     * @param icon 用户头像URL
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 当前登录状态（1=已登录/0=未登录）
     * @return LoginState 当前登录状态（1=已登录/0=未登录）
     */
    public Byte getLoginstate() {
        return loginstate;
    }

    /**
     * 当前登录状态（1=已登录/0=未登录）
     * @param loginstate 当前登录状态（1=已登录/0=未登录）
     */
    public void setLoginstate(Byte loginstate) {
        this.loginstate = loginstate;
    }

    /**
     * 登录切换时间
     * @return LoginChange 登录切换时间
     */
    public Date getLoginchange() {
        return loginchange;
    }

    /**
     * 登录切换时间
     * @param loginchange 登录切换时间
     */
    public void setLoginchange(Date loginchange) {
        this.loginchange = loginchange;
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