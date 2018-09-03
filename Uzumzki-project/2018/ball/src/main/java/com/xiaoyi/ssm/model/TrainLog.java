package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class TrainLog implements Serializable {
    /** 培训 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 培训ID */
    private String trainid;

    /** 类别(0=系统1=人工) */
    private Integer type;

    /** 内容 */
    private String content;

    /**
     * TrainLog
     */
    private static final long serialVersionUID = 1L;

    /**
     * 培训
     * @return ID 培训
     */
    public String getId() {
        return id;
    }

    /**
     * 培训
     * @param id 培训
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
     * 培训ID
     * @return TrainID 培训ID
     */
    public String getTrainid() {
        return trainid;
    }

    /**
     * 培训ID
     * @param trainid 培训ID
     */
    public void setTrainid(String trainid) {
        this.trainid = trainid == null ? null : trainid.trim();
    }

    /**
     * 类别(0=系统1=人工)
     * @return Type 类别(0=系统1=人工)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类别(0=系统1=人工)
     * @param type 类别(0=系统1=人工)
     */
    public void setType(Integer type) {
        this.type = type;
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