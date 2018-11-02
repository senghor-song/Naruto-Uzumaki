package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 陪练表实体
 */
public class VenueCoach implements Serializable {
	private TrainCoach trainCoach;
	
    public TrainCoach getTrainCoach() {
		return trainCoach;
	}

	public void setTrainCoach(TrainCoach trainCoach) {
		this.trainCoach = trainCoach;
	}
    /** 陪练 */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 场馆ID */
    private String venueId;

    /** 机构教练ID */
    private String trainCoachId;

    /** 状态0=禁用1=正常 */
    private Integer typeFlag;

    /** 每半小时单价 */
    private Double price;

    /**
     * VenueCoach
     */
    private static final long serialVersionUID = 1L;

    /**
     * 陪练
     * @return ID 陪练
     */
    public String getId() {
        return id;
    }

    /**
     * 陪练
     * @param id 陪练
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
     * 场馆ID
     * @return Venue_id 场馆ID
     */
    public String getVenueId() {
        return venueId;
    }

    /**
     * 场馆ID
     * @param venueId 场馆ID
     */
    public void setVenueId(String venueId) {
        this.venueId = venueId == null ? null : venueId.trim();
    }

    /**
     * 机构教练ID
     * @return Train_coach_id 机构教练ID
     */
    public String getTrainCoachId() {
        return trainCoachId;
    }

    /**
     * 机构教练ID
     * @param trainCoachId 机构教练ID
     */
    public void setTrainCoachId(String trainCoachId) {
        this.trainCoachId = trainCoachId == null ? null : trainCoachId.trim();
    }

    /**
     * 状态0=禁用1=正常
     * @return Type_flag 状态0=禁用1=正常
     */
    public Integer getTypeFlag() {
        return typeFlag;
    }

    /**
     * 状态0=禁用1=正常
     * @param typeFlag 状态0=禁用1=正常
     */
    public void setTypeFlag(Integer typeFlag) {
        this.typeFlag = typeFlag;
    }

    /**
     * 每半小时单价
     * @return Price 每半小时单价
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 每半小时单价
     * @param price 每半小时单价
     */
    public void setPrice(Double price) {
        this.price = price;
    }
}