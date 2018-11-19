package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class TrainTeamLog implements Serializable {
    /** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 培训机构ID */
    private String trainTeamId;

    /** 备注内容 */
    private String content;

    /**
     * TrainTeamLog
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * @return ID ID
     */
    public String getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
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
     * 培训机构ID
     * @return Train_team_id 培训机构ID
     */
    public String getTrainTeamId() {
        return trainTeamId;
    }

    /**
     * 培训机构ID
     * @param trainTeamId 培训机构ID
     */
    public void setTrainTeamId(String trainTeamId) {
        this.trainTeamId = trainTeamId == null ? null : trainTeamId.trim();
    }

    /**
     * 备注内容
     * @return Content 备注内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 备注内容
     * @param content 备注内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}