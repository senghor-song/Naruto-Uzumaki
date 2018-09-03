package com.xiaoyi.ssm.model;

import java.io.Serializable;

/**
 * 新盘开发商海报实体
 */
public class TownPoster implements Serializable {
    /** 新房->新盘开发商海报 */
    private String id;

    /** 新盘ID */
    private String townid;

    /** （辅助） */
    private String town;

    /** 海报地址 */
    private String path;

    /** 排序号 */
    private Integer sort;

    /**
     * TownPoster
     */
    private static final long serialVersionUID = 1L;

    /**
     * 新房->新盘开发商海报
     * @return ID 新房->新盘开发商海报
     */
    public String getId() {
        return id;
    }

    /**
     * 新房->新盘开发商海报
     * @param id 新房->新盘开发商海报
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
     * 海报地址
     * @return Path 海报地址
     */
    public String getPath() {
        return path;
    }

    /**
     * 海报地址
     * @param path 海报地址
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 排序号
     * @return Sort 排序号
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序号
     * @param sort 排序号
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}