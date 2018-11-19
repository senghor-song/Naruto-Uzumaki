package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class TrainCourseCollect implements Serializable {
    /** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 课程ID */
    private String trainCourseId;

    /** 用户ID */
    private String memberId;

    /**
     * TrainCourseCollect
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
     * 课程ID
     * @return Train_course_id 课程ID
     */
    public String getTrainCourseId() {
        return trainCourseId;
    }

    /**
     * 课程ID
     * @param trainCourseId 课程ID
     */
    public void setTrainCourseId(String trainCourseId) {
        this.trainCourseId = trainCourseId == null ? null : trainCourseId.trim();
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