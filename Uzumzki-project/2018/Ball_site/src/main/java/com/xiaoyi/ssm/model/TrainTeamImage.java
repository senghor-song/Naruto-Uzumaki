package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 培训机构图片表实体
 */
public class TrainTeamImage implements Serializable {
    /** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 图片地址 */
    private String path;

    /** 培训机构ID */
    private String trainTeamId;

    /**
     * TrainTeamImage
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
     * 修改时间
     * @return Modify_time 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 图片地址
     * @return Path 图片地址
     */
    public String getPath() {
        return path;
    }

    /**
     * 图片地址
     * @param path 图片地址
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
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
}