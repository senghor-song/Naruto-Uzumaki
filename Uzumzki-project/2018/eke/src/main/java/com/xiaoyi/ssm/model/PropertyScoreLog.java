package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 房源跟进表实体
 */
public class PropertyScoreLog implements Serializable {
    /** 租售->房源积分明细 */
    private String id;

    /** 房源ID */
    private String propertyid;

    /** 获分来源 */
    private String source;

    /** 获分项目 */
    private String sourceitem;

    /** 分值 */
    private Integer score;

    /** 时间 */
    private Date createtime;

    /** 备注 */
    private String remark;

    /**
     * PropertyScoreLog
     */
    private static final long serialVersionUID = 1L;

    /**
     * 租售->房源积分明细
     * @return ID 租售->房源积分明细
     */
    public String getId() {
        return id;
    }

    /**
     * 租售->房源积分明细
     * @param id 租售->房源积分明细
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 房源ID
     * @return PropertyID 房源ID
     */
    public String getPropertyid() {
        return propertyid;
    }

    /**
     * 房源ID
     * @param propertyid 房源ID
     */
    public void setPropertyid(String propertyid) {
        this.propertyid = propertyid == null ? null : propertyid.trim();
    }

    /**
     * 获分来源
     * @return Source 获分来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 获分来源
     * @param source 获分来源
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * 获分项目
     * @return SourceItem 获分项目
     */
    public String getSourceitem() {
        return sourceitem;
    }

    /**
     * 获分项目
     * @param sourceitem 获分项目
     */
    public void setSourceitem(String sourceitem) {
        this.sourceitem = sourceitem == null ? null : sourceitem.trim();
    }

    /**
     * 分值
     * @return Score 分值
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 分值
     * @param score 分值
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 时间
     * @return CreateTime 时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 时间
     * @param createtime 时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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