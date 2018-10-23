package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 培训课程表实体
 */
public class TrainCourse implements Serializable {
    /** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 课程编号 */
    private Integer trainCourseNo;

    /** 主教ID */
    private String trainCoachId;

    /** 标题 */
    private String title;

    /** 图片封面 */
    private String headImage;

    /** 报名价格 */
    private BigDecimal amount;

    /** 适合年龄最小 */
    private Integer minAge;

    /** 适合年龄最大 */
    private Integer maxAge;

    /** 适合基础 */
    private String suitBase;

    /** 上课人数 */
    private Integer toTeachNumber;

    /** 课时 */
    private Integer classHour;

    /** 上课时间 */
    private String toTeachTime;

    /** 课程亮点(用分号间隔) */
    private String classTrait;

    /** 预约信息 */
    private String payMake;

    /** 调课说明 */
    private String payAdjust;

    /** 退款说明 */
    private String payRefund;

    /** 试课说明 */
    private String payTrial;

    /** 教学场地ID */
    private String trainVenueId;

    /** 类别(1=网球2=足球3=羽毛球) */
    private Integer ballType;

    /** 报名人数 */
    private Integer applyPeopleSum;

    /** 是否允许报名(0=否1=是) */
    private Integer applyFlag;

    /**
     * TrainCourse
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
     * 课程编号
     * @return Train_course_no 课程编号
     */
    public Integer getTrainCourseNo() {
        return trainCourseNo;
    }

    /**
     * 课程编号
     * @param trainCourseNo 课程编号
     */
    public void setTrainCourseNo(Integer trainCourseNo) {
        this.trainCourseNo = trainCourseNo;
    }

    /**
     * 主教ID
     * @return Train_coach_id 主教ID
     */
    public String getTrainCoachId() {
        return trainCoachId;
    }

    /**
     * 主教ID
     * @param trainCoachId 主教ID
     */
    public void setTrainCoachId(String trainCoachId) {
        this.trainCoachId = trainCoachId == null ? null : trainCoachId.trim();
    }

    /**
     * 标题
     * @return Title 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 图片封面
     * @return Head_image 图片封面
     */
    public String getHeadImage() {
        return headImage;
    }

    /**
     * 图片封面
     * @param headImage 图片封面
     */
    public void setHeadImage(String headImage) {
        this.headImage = headImage == null ? null : headImage.trim();
    }

    /**
     * 报名价格
     * @return Amount 报名价格
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 报名价格
     * @param amount 报名价格
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 适合年龄最小
     * @return Min_age 适合年龄最小
     */
    public Integer getMinAge() {
        return minAge;
    }

    /**
     * 适合年龄最小
     * @param minAge 适合年龄最小
     */
    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    /**
     * 适合年龄最大
     * @return Max_age 适合年龄最大
     */
    public Integer getMaxAge() {
        return maxAge;
    }

    /**
     * 适合年龄最大
     * @param maxAge 适合年龄最大
     */
    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    /**
     * 适合基础
     * @return Suit_base 适合基础
     */
    public String getSuitBase() {
        return suitBase;
    }

    /**
     * 适合基础
     * @param suitBase 适合基础
     */
    public void setSuitBase(String suitBase) {
        this.suitBase = suitBase == null ? null : suitBase.trim();
    }

    /**
     * 上课人数
     * @return To_teach_number 上课人数
     */
    public Integer getToTeachNumber() {
        return toTeachNumber;
    }

    /**
     * 上课人数
     * @param toTeachNumber 上课人数
     */
    public void setToTeachNumber(Integer toTeachNumber) {
        this.toTeachNumber = toTeachNumber;
    }

    /**
     * 课时
     * @return Class_hour 课时
     */
    public Integer getClassHour() {
        return classHour;
    }

    /**
     * 课时
     * @param classHour 课时
     */
    public void setClassHour(Integer classHour) {
        this.classHour = classHour;
    }

    /**
     * 上课时间
     * @return To_teach_time 上课时间
     */
    public String getToTeachTime() {
        return toTeachTime;
    }

    /**
     * 上课时间
     * @param toTeachTime 上课时间
     */
    public void setToTeachTime(String toTeachTime) {
        this.toTeachTime = toTeachTime == null ? null : toTeachTime.trim();
    }

    /**
     * 课程亮点(用分号间隔)
     * @return Class_trait 课程亮点(用分号间隔)
     */
    public String getClassTrait() {
        return classTrait;
    }

    /**
     * 课程亮点(用分号间隔)
     * @param classTrait 课程亮点(用分号间隔)
     */
    public void setClassTrait(String classTrait) {
        this.classTrait = classTrait == null ? null : classTrait.trim();
    }

    /**
     * 预约信息
     * @return Pay_make 预约信息
     */
    public String getPayMake() {
        return payMake;
    }

    /**
     * 预约信息
     * @param payMake 预约信息
     */
    public void setPayMake(String payMake) {
        this.payMake = payMake == null ? null : payMake.trim();
    }

    /**
     * 调课说明
     * @return Pay_adjust 调课说明
     */
    public String getPayAdjust() {
        return payAdjust;
    }

    /**
     * 调课说明
     * @param payAdjust 调课说明
     */
    public void setPayAdjust(String payAdjust) {
        this.payAdjust = payAdjust == null ? null : payAdjust.trim();
    }

    /**
     * 退款说明
     * @return Pay_refund 退款说明
     */
    public String getPayRefund() {
        return payRefund;
    }

    /**
     * 退款说明
     * @param payRefund 退款说明
     */
    public void setPayRefund(String payRefund) {
        this.payRefund = payRefund == null ? null : payRefund.trim();
    }

    /**
     * 试课说明
     * @return Pay_trial 试课说明
     */
    public String getPayTrial() {
        return payTrial;
    }

    /**
     * 试课说明
     * @param payTrial 试课说明
     */
    public void setPayTrial(String payTrial) {
        this.payTrial = payTrial == null ? null : payTrial.trim();
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
     * 类别(1=网球2=足球3=羽毛球)
     * @return Ball_type 类别(1=网球2=足球3=羽毛球)
     */
    public Integer getBallType() {
        return ballType;
    }

    /**
     * 类别(1=网球2=足球3=羽毛球)
     * @param ballType 类别(1=网球2=足球3=羽毛球)
     */
    public void setBallType(Integer ballType) {
        this.ballType = ballType;
    }

    /**
     * 报名人数
     * @return Apply_people_sum 报名人数
     */
    public Integer getApplyPeopleSum() {
        return applyPeopleSum;
    }

    /**
     * 报名人数
     * @param applyPeopleSum 报名人数
     */
    public void setApplyPeopleSum(Integer applyPeopleSum) {
        this.applyPeopleSum = applyPeopleSum;
    }

    /**
     * 是否允许报名(0=否1=是)
     * @return Apply_flag 是否允许报名(0=否1=是)
     */
    public Integer getApplyFlag() {
        return applyFlag;
    }

    /**
     * 是否允许报名(0=否1=是)
     * @param applyFlag 是否允许报名(0=否1=是)
     */
    public void setApplyFlag(Integer applyFlag) {
        this.applyFlag = applyFlag;
    }
}