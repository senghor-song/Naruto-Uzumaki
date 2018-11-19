package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 培训机构表实体
 */
public class TrainTeam implements Serializable {
    /** 培训机构 */
    private String id;

    /** 培训机构编号 */
    private Integer trainTeamNo;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 名称 */
    private String title;

    /** 是否可以修改名称(0=否1=是) */
    private Integer titleFlag;

    /** 电话 */
    private String phone;

    /** 封面图 */
    private String headImage;

    /** 品牌故事 */
    private String brandContent;

    /** 办学特色(使用;隔开) */
    private String trait;

    /** 是否有免费课程(0=否1=是) */
    private Integer freeClasses;

    /** 是否有免费停车(0=否1=是) */
    private Integer freeParking;

    /** 城市ID */
    private String cityId;

    /** 地址 */
    private String address;

    /** 经度 */
    private Double longitude;

    /** 纬度 */
    private Double latitude;

    /** 开课球馆数量 */
    private Integer venueNumber;

    /** 开设科目逗号隔开(网球,足球,羽毛球) */
    private String teachClass;

    /** 评级 */
    private Integer level;

    /** 评级修改时间 */
    private Date levelTime;

    /** 最低价格 */
    private Double minAmount;

    /** 网球课时长度 */
    private Integer ballHour1;

    /** 足球课时长度 */
    private Integer ballHour2;

    /** 羽毛球课时长度 */
    private Integer ballHour3;

    /** 状态0=禁用1=正常 */
    private Integer typeFlag;

    /** 回款教练ID */
    private String trainCoachId;

    /**
     * TrainTeam
     */
    private static final long serialVersionUID = 1L;

    /**
     * 培训机构
     * @return ID 培训机构
     */
    public String getId() {
        return id;
    }

    /**
     * 培训机构
     * @param id 培训机构
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 培训机构编号
     * @return Train_team_no 培训机构编号
     */
    public Integer getTrainTeamNo() {
        return trainTeamNo;
    }

    /**
     * 培训机构编号
     * @param trainTeamNo 培训机构编号
     */
    public void setTrainTeamNo(Integer trainTeamNo) {
        this.trainTeamNo = trainTeamNo;
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
     * 名称
     * @return Title 名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 名称
     * @param title 名称
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 是否可以修改名称(0=否1=是)
     * @return Title_flag 是否可以修改名称(0=否1=是)
     */
    public Integer getTitleFlag() {
        return titleFlag;
    }

    /**
     * 是否可以修改名称(0=否1=是)
     * @param titleFlag 是否可以修改名称(0=否1=是)
     */
    public void setTitleFlag(Integer titleFlag) {
        this.titleFlag = titleFlag;
    }

    /**
     * 电话
     * @return Phone 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 电话
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 封面图
     * @return Head_image 封面图
     */
    public String getHeadImage() {
        return headImage;
    }

    /**
     * 封面图
     * @param headImage 封面图
     */
    public void setHeadImage(String headImage) {
        this.headImage = headImage == null ? null : headImage.trim();
    }

    /**
     * 品牌故事
     * @return Brand_content 品牌故事
     */
    public String getBrandContent() {
        return brandContent;
    }

    /**
     * 品牌故事
     * @param brandContent 品牌故事
     */
    public void setBrandContent(String brandContent) {
        this.brandContent = brandContent == null ? null : brandContent.trim();
    }

    /**
     * 办学特色(使用;隔开)
     * @return Trait 办学特色(使用;隔开)
     */
    public String getTrait() {
        return trait;
    }

    /**
     * 办学特色(使用;隔开)
     * @param trait 办学特色(使用;隔开)
     */
    public void setTrait(String trait) {
        this.trait = trait == null ? null : trait.trim();
    }

    /**
     * 是否有免费课程(0=否1=是)
     * @return Free_classes 是否有免费课程(0=否1=是)
     */
    public Integer getFreeClasses() {
        return freeClasses;
    }

    /**
     * 是否有免费课程(0=否1=是)
     * @param freeClasses 是否有免费课程(0=否1=是)
     */
    public void setFreeClasses(Integer freeClasses) {
        this.freeClasses = freeClasses;
    }

    /**
     * 是否有免费停车(0=否1=是)
     * @return Free_parking 是否有免费停车(0=否1=是)
     */
    public Integer getFreeParking() {
        return freeParking;
    }

    /**
     * 是否有免费停车(0=否1=是)
     * @param freeParking 是否有免费停车(0=否1=是)
     */
    public void setFreeParking(Integer freeParking) {
        this.freeParking = freeParking;
    }

    /**
     * 城市ID
     * @return City_id 城市ID
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * 城市ID
     * @param cityId 城市ID
     */
    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    /**
     * 地址
     * @return Address 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 地址
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 经度
     * @return Longitude 经度
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 经度
     * @param longitude 经度
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 纬度
     * @return Latitude 纬度
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 纬度
     * @param latitude 纬度
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * 开课球馆数量
     * @return Venue_number 开课球馆数量
     */
    public Integer getVenueNumber() {
        return venueNumber;
    }

    /**
     * 开课球馆数量
     * @param venueNumber 开课球馆数量
     */
    public void setVenueNumber(Integer venueNumber) {
        this.venueNumber = venueNumber;
    }

    /**
     * 开设科目逗号隔开(网球,足球,羽毛球)
     * @return Teach_class 开设科目逗号隔开(网球,足球,羽毛球)
     */
    public String getTeachClass() {
        return teachClass;
    }

    /**
     * 开设科目逗号隔开(网球,足球,羽毛球)
     * @param teachClass 开设科目逗号隔开(网球,足球,羽毛球)
     */
    public void setTeachClass(String teachClass) {
        this.teachClass = teachClass == null ? null : teachClass.trim();
    }

    /**
     * 评级
     * @return Level 评级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 评级
     * @param level 评级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 评级修改时间
     * @return Level_time 评级修改时间
     */
    public Date getLevelTime() {
        return levelTime;
    }

    /**
     * 评级修改时间
     * @param levelTime 评级修改时间
     */
    public void setLevelTime(Date levelTime) {
        this.levelTime = levelTime;
    }

    /**
     * 最低价格
     * @return Min_amount 最低价格
     */
    public Double getMinAmount() {
        return minAmount;
    }

    /**
     * 最低价格
     * @param minAmount 最低价格
     */
    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    /**
     * 网球课时长度
     * @return Ball_hour1 网球课时长度
     */
    public Integer getBallHour1() {
        return ballHour1;
    }

    /**
     * 网球课时长度
     * @param ballHour1 网球课时长度
     */
    public void setBallHour1(Integer ballHour1) {
        this.ballHour1 = ballHour1;
    }

    /**
     * 足球课时长度
     * @return Ball_hour2 足球课时长度
     */
    public Integer getBallHour2() {
        return ballHour2;
    }

    /**
     * 足球课时长度
     * @param ballHour2 足球课时长度
     */
    public void setBallHour2(Integer ballHour2) {
        this.ballHour2 = ballHour2;
    }

    /**
     * 羽毛球课时长度
     * @return Ball_hour3 羽毛球课时长度
     */
    public Integer getBallHour3() {
        return ballHour3;
    }

    /**
     * 羽毛球课时长度
     * @param ballHour3 羽毛球课时长度
     */
    public void setBallHour3(Integer ballHour3) {
        this.ballHour3 = ballHour3;
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
     * 回款教练ID
     * @return Train_coach_id 回款教练ID
     */
    public String getTrainCoachId() {
        return trainCoachId;
    }

    /**
     * 回款教练ID
     * @param trainCoachId 回款教练ID
     */
    public void setTrainCoachId(String trainCoachId) {
        this.trainCoachId = trainCoachId == null ? null : trainCoachId.trim();
    }
}