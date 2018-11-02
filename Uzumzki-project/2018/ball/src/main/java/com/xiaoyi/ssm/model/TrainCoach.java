package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 教练基础信息表实体
 */
public class TrainCoach implements Serializable {
	private TrainTeamCoach trainTeamCoach;
	
    public TrainTeamCoach getTrainTeamCoach() {
		return trainTeamCoach;
	}

	public void setTrainTeamCoach(TrainTeamCoach trainTeamCoach) {
		this.trainTeamCoach = trainTeamCoach;
	}

	/** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 用户ID */
    private String memberId;

    /** 姓名 */
    private String name;

    /** 性别0=未知1=男2=女 */
    private Integer sex;

    /** 教学年龄 */
    private Integer workingAge;

    /** 头像 */
    private String headImage;

    /** 讲课风格 */
    private String lectureStyle;

    /** 个人简介 */
    private String vitae;

    /** 教育背景 */
    private String education;

    /** 相关经历 */
    private String honor;

    /**
     * TrainCoach
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

    /**
     * 姓名
     * @return Name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 性别0=未知1=男2=女
     * @return Sex 性别0=未知1=男2=女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 性别0=未知1=男2=女
     * @param sex 性别0=未知1=男2=女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 教学年龄
     * @return Working_age 教学年龄
     */
    public Integer getWorkingAge() {
        return workingAge;
    }

    /**
     * 教学年龄
     * @param workingAge 教学年龄
     */
    public void setWorkingAge(Integer workingAge) {
        this.workingAge = workingAge;
    }

    /**
     * 头像
     * @return Head_image 头像
     */
    public String getHeadImage() {
        return headImage;
    }

    /**
     * 头像
     * @param headImage 头像
     */
    public void setHeadImage(String headImage) {
        this.headImage = headImage == null ? null : headImage.trim();
    }

    /**
     * 讲课风格
     * @return Lecture_style 讲课风格
     */
    public String getLectureStyle() {
        return lectureStyle;
    }

    /**
     * 讲课风格
     * @param lectureStyle 讲课风格
     */
    public void setLectureStyle(String lectureStyle) {
        this.lectureStyle = lectureStyle == null ? null : lectureStyle.trim();
    }

    /**
     * 个人简介
     * @return Vitae 个人简介
     */
    public String getVitae() {
        return vitae;
    }

    /**
     * 个人简介
     * @param vitae 个人简介
     */
    public void setVitae(String vitae) {
        this.vitae = vitae == null ? null : vitae.trim();
    }

    /**
     * 教育背景
     * @return Education 教育背景
     */
    public String getEducation() {
        return education;
    }

    /**
     * 教育背景
     * @param education 教育背景
     */
    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    /**
     * 相关经历
     * @return Honor 相关经历
     */
    public String getHonor() {
        return honor;
    }

    /**
     * 相关经历
     * @param honor 相关经历
     */
    public void setHonor(String honor) {
        this.honor = honor == null ? null : honor.trim();
    }
}