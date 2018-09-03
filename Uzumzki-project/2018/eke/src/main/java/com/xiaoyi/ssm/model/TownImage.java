package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 新盘图https://img.ekeae.com实体
 */
public class TownImage implements Serializable {
    /** 新房->新盘图https://img.ekeae.com */
    private String id;

    /** 上传时间 */
    private Date uploadtime;

    /** 上传人ID */
    private String staffid;

    /** 新盘ID */
    private String townid;

    /** （辅助） */
    private String town;

    /** 大小(单位KB) */
    private Integer size;

    /** URL */
    private String path;

    /** 备注 */
    private String remark;

    /**
     * TownImage
     */
    private static final long serialVersionUID = 1L;

    /**
     * 新房->新盘图https://img.ekeae.com
     * @return ID 新房->新盘图https://img.ekeae.com
     */
    public String getId() {
        return id;
    }

    /**
     * 新房->新盘图https://img.ekeae.com
     * @param id 新房->新盘图https://img.ekeae.com
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 上传时间
     * @return UploadTime 上传时间
     */
    public Date getUploadtime() {
        return uploadtime;
    }

    /**
     * 上传时间
     * @param uploadtime 上传时间
     */
    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    /**
     * 上传人ID
     * @return StaffID 上传人ID
     */
    public String getStaffid() {
        return staffid;
    }

    /**
     * 上传人ID
     * @param staffid 上传人ID
     */
    public void setStaffid(String staffid) {
        this.staffid = staffid == null ? null : staffid.trim();
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
     * 大小(单位KB)
     * @return Size 大小(单位KB)
     */
    public Integer getSize() {
        return size;
    }

    /**
     * 大小(单位KB)
     * @param size 大小(单位KB)
     */
    public void setSize(Integer size) {
        this.size = size;
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