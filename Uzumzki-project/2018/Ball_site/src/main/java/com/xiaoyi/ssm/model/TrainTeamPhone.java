package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 培训机构拨打电话记录表实体
 */
public class TrainTeamPhone implements Serializable {
    /** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 培训机构ID */
    private String trainTeamId;

    /** 用户ID */
    private String memberId;

    /**
     * TrainTeamPhone
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
     * 用户ID
     * @return Member_id 用户ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 用户ID
     * @param memberId 用户ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }
}