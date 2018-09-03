package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 新盘日志实体
 */
public class TownLog implements Serializable {
    /** 新房->新盘日志 */
    private String id;

    /** 新盘ID */
    private String townid;

    /** （辅助） */
    private String town;

    /**  */
    private Date logtime;

    /** 内容 */
    private String content;

    /**
     * TownLog
     */
    private static final long serialVersionUID = 1L;

    /**
     * 新房->新盘日志
     * @return ID 新房->新盘日志
     */
    public String getId() {
        return id;
    }

    /**
     * 新房->新盘日志
     * @param id 新房->新盘日志
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
     * 
     * @return LogTime 
     */
    public Date getLogtime() {
        return logtime;
    }

    /**
     * 
     * @param logtime 
     */
    public void setLogtime(Date logtime) {
        this.logtime = logtime;
    }

    /**
     * 内容
     * @return Content 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}