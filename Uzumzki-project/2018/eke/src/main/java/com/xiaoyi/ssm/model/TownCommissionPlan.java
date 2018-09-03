package com.xiaoyi.ssm.model;

import java.io.Serializable;

/**
 * 新盘佣金方案实体
 */
public class TownCommissionPlan implements Serializable {
    /** 新房->新盘佣金方案 */
    private String id;

    /** 隶属新盘ID */
    private String townid;

    /** （辅助） */
    private String town;

    /** 方案名 */
    private String title;

    /** 方案详情 */
    private String detail;

    /** 备注 */
    private String remark;

    /**
     * TownCommissionPlan
     */
    private static final long serialVersionUID = 1L;

    /**
     * 新房->新盘佣金方案
     * @return ID 新房->新盘佣金方案
     */
    public String getId() {
        return id;
    }

    /**
     * 新房->新盘佣金方案
     * @param id 新房->新盘佣金方案
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 隶属新盘ID
     * @return TownID 隶属新盘ID
     */
    public String getTownid() {
        return townid;
    }

    /**
     * 隶属新盘ID
     * @param townid 隶属新盘ID
     */
    public void setTownid(String townid) {
        this.townid = townid == null ? null : townid.trim();
    }

    /**
     * （辅助）
     * @return Town （辅助）
     */
    public String getTown() {
        return town;
    }

    /**
     * （辅助）
     * @param town （辅助）
     */
    public void setTown(String town) {
        this.town = town == null ? null : town.trim();
    }

    /**
     * 方案名
     * @return Title 方案名
     */
    public String getTitle() {
        return title;
    }

    /**
     * 方案名
     * @param title 方案名
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 方案详情
     * @return Detail 方案详情
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 方案详情
     * @param detail 方案详情
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
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