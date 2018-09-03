package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

public class MassRefreshLog implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 推房->云刷新详情
     */
    private String id;

    /**
     * 网站名
     */
    private String webname;

    /**
     * 账号
     */
    private String account;

    /**
     * 房源类型
     */
    private String housetype;

    /**
     * 刷新时间
     */
    private Date refreshtime;

    /**
     * 刷新条数
     */
    private Integer refreshsum;

    /**
     * 刷新剩余条数
     */
    private Integer refreshremain;

    /**
     * 刷新结果
     */
    private String refreshresult;

    /**
     * 刷新房源
     */
    private String refreshhouse;

    /**
     * 计划号
     */
    private String projectname;

    /**
     * 
     */
    private String remark;

    /**
     * 推房->云刷新详情
     * @return ID 推房->云刷新详情
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->云刷新详情
     * @param id 推房->云刷新详情
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 网站名
     * @return WebName 网站名
     */
    public String getWebname() {
        return webname;
    }

    /**
     * 网站名
     * @param webname 网站名
     */
    public void setWebname(String webname) {
        this.webname = webname == null ? null : webname.trim();
    }

    /**
     * 账号
     * @return Account 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 账号
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 房源类型
     * @return HouseType 房源类型
     */
    public String getHousetype() {
        return housetype;
    }

    /**
     * 房源类型
     * @param housetype 房源类型
     */
    public void setHousetype(String housetype) {
        this.housetype = housetype == null ? null : housetype.trim();
    }

    /**
     * 刷新时间
     * @return RefreshTime 刷新时间
     */
    public Date getRefreshtime() {
        return refreshtime;
    }

    /**
     * 刷新时间
     * @param refreshtime 刷新时间
     */
    public void setRefreshtime(Date refreshtime) {
        this.refreshtime = refreshtime;
    }

    /**
     * 刷新条数
     * @return RefreshSum 刷新条数
     */
    public Integer getRefreshsum() {
        return refreshsum;
    }

    /**
     * 刷新条数
     * @param refreshsum 刷新条数
     */
    public void setRefreshsum(Integer refreshsum) {
        this.refreshsum = refreshsum;
    }

    /**
     * 刷新剩余条数
     * @return RefreshRemain 刷新剩余条数
     */
    public Integer getRefreshremain() {
        return refreshremain;
    }

    /**
     * 刷新剩余条数
     * @param refreshremain 刷新剩余条数
     */
    public void setRefreshremain(Integer refreshremain) {
        this.refreshremain = refreshremain;
    }

    /**
     * 刷新结果
     * @return RefreshResult 刷新结果
     */
    public String getRefreshresult() {
        return refreshresult;
    }

    /**
     * 刷新结果
     * @param refreshresult 刷新结果
     */
    public void setRefreshresult(String refreshresult) {
        this.refreshresult = refreshresult == null ? null : refreshresult.trim();
    }

    /**
     * 刷新房源
     * @return RefreshHouse 刷新房源
     */
    public String getRefreshhouse() {
        return refreshhouse;
    }

    /**
     * 刷新房源
     * @param refreshhouse 刷新房源
     */
    public void setRefreshhouse(String refreshhouse) {
        this.refreshhouse = refreshhouse == null ? null : refreshhouse.trim();
    }

    /**
     * 计划号
     * @return ProjectName 计划号
     */
    public String getProjectname() {
        return projectname;
    }

    /**
     * 计划号
     * @param projectname 计划号
     */
    public void setProjectname(String projectname) {
        this.projectname = projectname == null ? null : projectname.trim();
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