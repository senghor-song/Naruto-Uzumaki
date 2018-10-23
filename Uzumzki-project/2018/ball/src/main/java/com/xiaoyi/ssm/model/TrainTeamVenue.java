package com.xiaoyi.ssm.model;

import java.io.Serializable;

/**
 * 培训机构使用场地表实体
 */
public class TrainTeamVenue implements Serializable {
    /** ID */
    private String id;

    /** 教学场地ID */
    private String trainVenueId;

    /** 培训机构ID */
    private String trainTeamId;

    /**
     * TrainTeamVenue
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
     * 教学场地ID
     * @return Train_venue_id 教学场地ID
     */
    public String getTrainVenueId() {
        return trainVenueId;
    }

    /**
     * 教学场地ID
     * @param trainVenueId 教学场地ID
     */
    public void setTrainVenueId(String trainVenueId) {
        this.trainVenueId = trainVenueId == null ? null : trainVenueId.trim();
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