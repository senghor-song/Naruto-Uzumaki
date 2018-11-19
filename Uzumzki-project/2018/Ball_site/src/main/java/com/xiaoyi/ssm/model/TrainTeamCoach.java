package com.xiaoyi.ssm.model;

import java.io.Serializable;

/**
 * 培训机构拥有教练表实体
 */
public class TrainTeamCoach implements Serializable {
	private TrainCoach trainCoach;
	private TrainTeam trainTeam;
	
    public TrainTeam getTrainTeam() {
		return trainTeam;
	}

	public void setTrainTeam(TrainTeam trainTeam) {
		this.trainTeam = trainTeam;
	}

	public TrainCoach getTrainCoach() {
		return trainCoach;
	}

	public void setTrainCoach(TrainCoach trainCoach) {
		this.trainCoach = trainCoach;
	}

	/** ID */
    private String id;

    /** 培训机构ID */
    private String trainTeamId;

    /** 教练ID */
    private String trainCoachId;

    /** 教练所属机构身份0=外聘1=店长2=管理 */
    private Integer manager;

    /** 是否允许进入培训机构0=禁用1=正常 */
    private Integer showFlag;

    /** 教学身份1=主教2=助教3=内勤 */
    private Integer teachType;

    /**
     * TrainTeamCoach
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
     * 教练ID
     * @return Train_coach_id 教练ID
     */
    public String getTrainCoachId() {
        return trainCoachId;
    }

    /**
     * 教练ID
     * @param trainCoachId 教练ID
     */
    public void setTrainCoachId(String trainCoachId) {
        this.trainCoachId = trainCoachId == null ? null : trainCoachId.trim();
    }

    /**
     * 教练所属机构身份0=外聘1=店长2=管理
     * @return Manager 教练所属机构身份0=外聘1=店长2=管理
     */
    public Integer getManager() {
        return manager;
    }

    /**
     * 教练所属机构身份0=外聘1=店长2=管理
     * @param manager 教练所属机构身份0=外聘1=店长2=管理
     */
    public void setManager(Integer manager) {
        this.manager = manager;
    }

    /**
     * 是否允许进入培训机构0=禁用1=正常
     * @return Show_flag 是否允许进入培训机构0=禁用1=正常
     */
    public Integer getShowFlag() {
        return showFlag;
    }

    /**
     * 是否允许进入培训机构0=禁用1=正常
     * @param showFlag 是否允许进入培训机构0=禁用1=正常
     */
    public void setShowFlag(Integer showFlag) {
        this.showFlag = showFlag;
    }

    /**
     * 教学身份1=主教2=助教3=内勤
     * @return Teach_type 教学身份1=主教2=助教3=内勤
     */
    public Integer getTeachType() {
        return teachType;
    }

    /**
     * 教学身份1=主教2=助教3=内勤
     * @param teachType 教学身份1=主教2=助教3=内勤
     */
    public void setTeachType(Integer teachType) {
        this.teachType = teachType;
    }
}