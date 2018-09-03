package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 小区图https://img.ekeae.com实体
 */
public class EstateImageStorage implements Serializable {
    /** 租售->小区图（仓储） */
    private String id;

    /** 城市 */
    private String city;

    /** 小区 */
    private String estate;

    /** 上传时间 */
    private Date addtime;

    /** URL */
    private String path;

    /** 来源（如：安居客） */
    private String source;

    /** 备注 */
    private String remark;

    /**
     * EstateImageStorage
     */
    private static final long serialVersionUID = 1L;

    /**
     * 租售->小区图（仓储）
     * @return ID 租售->小区图（仓储）
     */
    public String getId() {
        return id;
    }

    /**
     * 租售->小区图（仓储）
     * @param id 租售->小区图（仓储）
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 城市
     * @return City 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 城市
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 小区
     * @return Estate 小区
     */
    public String getEstate() {
        return estate;
    }

    /**
     * 小区
     * @param estate 小区
     */
    public void setEstate(String estate) {
        this.estate = estate == null ? null : estate.trim();
    }

    /**
     * 上传时间
     * @return AddTime 上传时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 上传时间
     * @param addtime 上传时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * URL
     * @return Path URL
     */
    public String getPath() {
        return path;
    }

    /**
     * URL
     * @param path URL
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 来源（如：安居客）
     * @return Source 来源（如：安居客）
     */
    public String getSource() {
        return source;
    }

    /**
     * 来源（如：安居客）
     * @param source 来源（如：安居客）
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
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