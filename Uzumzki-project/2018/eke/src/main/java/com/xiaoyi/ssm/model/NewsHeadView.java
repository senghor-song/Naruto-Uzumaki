package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 房源查看(次数)表实体
 */
public class NewsHeadView implements Serializable {
    /** 运营->新闻查看次数 */
    private String id;

    /** 新闻ID */
    private String newsheadid;

    /** 查看时间 */
    private Date date;

    /**
     * NewsHeadView
     */
    private static final long serialVersionUID = 1L;

    /**
     * 运营->新闻查看次数
     * @return ID 运营->新闻查看次数
     */
    public String getId() {
        return id;
    }

    /**
     * 运营->新闻查看次数
     * @param id 运营->新闻查看次数
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 新闻ID
     * @return NewsHeadID 新闻ID
     */
    public String getNewsheadid() {
        return newsheadid;
    }

    /**
     * 新闻ID
     * @param newsheadid 新闻ID
     */
    public void setNewsheadid(String newsheadid) {
        this.newsheadid = newsheadid == null ? null : newsheadid.trim();
    }

    /**
     * 查看时间
     * @return Date 查看时间
     */
    public Date getDate() {
        return date;
    }

    /**
     * 查看时间
     * @param date 查看时间
     */
    public void setDate(Date date) {
        this.date = date;
    }
}