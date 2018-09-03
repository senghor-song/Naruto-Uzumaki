package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class VenueStatis implements Serializable {
    /** 场馆使用情况统计(排名用) */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 修改时间 */
    private Date modifytime;

    /** 场馆ID */
    private String venueid;

    /** 使用量 */
    private Integer score;

    /**
     * VenueStatis
     */
    private static final long serialVersionUID = 1L;

    /**
     * 场馆使用情况统计(排名用)
     * @return ID 场馆使用情况统计(排名用)
     */
    public String getId() {
        return id;
    }

    /**
     * 场馆使用情况统计(排名用)
     * @param id 场馆使用情况统计(排名用)
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 创建时间
     * @return CreateTime 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 修改时间
     * @return ModifyTime 修改时间
     */
    public Date getModifytime() {
        return modifytime;
    }

    /**
     * 修改时间
     * @param modifytime 修改时间
     */
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * 场馆ID
     * @return VenueID 场馆ID
     */
    public String getVenueid() {
        return venueid;
    }

    /**
     * 场馆ID
     * @param venueid 场馆ID
     */
    public void setVenueid(String venueid) {
        this.venueid = venueid == null ? null : venueid.trim();
    }

    /**
     * 使用量
     * @return Score 使用量
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 使用量
     * @param score 使用量
     */
    public void setScore(Integer score) {
        this.score = score;
    }
}