package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 培训课程报名日志实体
 */
public class TrainOrderLog implements Serializable {
    /** 培训 */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 培训ID */
    private String trainOrderId;

    /** 类别(0=系统1=人工) */
    private Integer type;

    /** 内容 */
    private String content;

    /**
     * TrainOrderLog
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
     * @return Create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 培训ID
     * @return Train_order_id 培训ID
     */
    public String getTrainOrderId() {
        return trainOrderId;
    }

    /**
     * 培训ID
     * @param trainOrderId 培训ID
     */
    public void setTrainOrderId(String trainOrderId) {
        this.trainOrderId = trainOrderId == null ? null : trainOrderId.trim();
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