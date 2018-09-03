package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 楼讯分享表实体
 */
public class NewsHeadShare implements Serializable {
    /** 运营->楼讯分享（软文营销） */
    private String id;

    /** 楼讯ID */
    private String newsheadid;

    /** 楼讯标题（辅助） */
    private String newshead;

    /** 分享时间 */
    private Date sharetime;

    /** 分享方式（微信、QQ、朋友圈） */
    private String shareto;

    /** 发起端 */
    private String way;

    /** 经纪or客户ID */
    private String userid;

    /** 房源ID（经纪人软文营销专用） */
    private String propertyid;

    /** 备注 */
    private String remark;

    /**
     * NewsHeadShare
     */
    private static final long serialVersionUID = 1L;

    /**
     * 运营->楼讯分享（软文营销）
     * @return ID 运营->楼讯分享（软文营销）
     */
    public String getId() {
        return id;
    }

    /**
     * 运营->楼讯分享（软文营销）
     * @param id 运营->楼讯分享（软文营销）
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 楼讯ID
     * @return NewsHeadID 楼讯ID
     */
    public String getNewsheadid() {
        return newsheadid;
    }

    /**
     * 楼讯ID
     * @param newsheadid 楼讯ID
     */
    public void setNewsheadid(String newsheadid) {
        this.newsheadid = newsheadid == null ? null : newsheadid.trim();
    }

    /**
     * 楼讯标题（辅助）
     * @return NewsHead 楼讯标题（辅助）
     */
    public String getNewshead() {
        return newshead;
    }

    /**
     * 楼讯标题（辅助）
     * @param newshead 楼讯标题（辅助）
     */
    public void setNewshead(String newshead) {
        this.newshead = newshead == null ? null : newshead.trim();
    }

    /**
     * 分享时间
     * @return ShareTime 分享时间
     */
    public Date getSharetime() {
        return sharetime;
    }

    /**
     * 分享时间
     * @param sharetime 分享时间
     */
    public void setSharetime(Date sharetime) {
        this.sharetime = sharetime;
    }

    /**
     * 分享方式（微信、QQ、朋友圈）
     * @return ShareTo 分享方式（微信、QQ、朋友圈）
     */
    public String getShareto() {
        return shareto;
    }

    /**
     * 分享方式（微信、QQ、朋友圈）
     * @param shareto 分享方式（微信、QQ、朋友圈）
     */
    public void setShareto(String shareto) {
        this.shareto = shareto == null ? null : shareto.trim();
    }

    /**
     * 发起端
     * @return Way 发起端
     */
    public String getWay() {
        return way;
    }

    /**
     * 发起端
     * @param way 发起端
     */
    public void setWay(String way) {
        this.way = way == null ? null : way.trim();
    }

    /**
     * 经纪or客户ID
     * @return UserID 经纪or客户ID
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 经纪or客户ID
     * @param userid 经纪or客户ID
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * 房源ID（经纪人软文营销专用）
     * @return PropertyID 房源ID（经纪人软文营销专用）
     */
    public String getPropertyid() {
        return propertyid;
    }

    /**
     * 房源ID（经纪人软文营销专用）
     * @param propertyid 房源ID（经纪人软文营销专用）
     */
    public void setPropertyid(String propertyid) {
        this.propertyid = propertyid == null ? null : propertyid.trim();
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