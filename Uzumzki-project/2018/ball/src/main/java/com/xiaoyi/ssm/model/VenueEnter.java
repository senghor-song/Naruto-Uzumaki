package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 场馆申请入驻表实体
 */
public class VenueEnter implements Serializable {
	private Staff staff;
	private Member member;
	
    public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
    /** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 申请人 */
    private String memberId;

    /** 来源0=客户端1=短信 */
    private Integer sourceFlag;

    /** 场馆ID */
    private String venueId;

    /** 球场名称 */
    private String title;

    /** 经度 */
    private Double longitude;

    /** 纬度 */
    private Double latitude;

    /** 地址 */
    private String address;

    /** 球场类型(1=网球场2=足球场3=羽毛球馆4=篮球场) */
    private Integer ballType;

    /** 审核状态0=待审核1=审核通过2=审核拒绝 */
    private Integer checkFlag;

    /** 审核时间 */
    private Date checkTime;

    /** 审核人 */
    private String checkStaff;

    /** 审核意见 */
    private String content;

    /** 其他联系人 */
    private String mainName;

    /** 其他联系人电话 */
    private String mainPhone;

    /** 联系人关系(1=负责人2=同事3=业主或其他) */
    private Integer mainFlag;

    /** 球场片数 */
    private Integer ballSum;

    /** 封面图 */
    private String headImage;

    /** 城市名 */
    private String cityName;

    /** 区县名称 */
    private String districtName;

    /** 机构名称 */
    private String trainTeamName;

    /** 机构ID */
    private String trainTeamId;

    /** 用户位置经度 */
    private Double userLng;

    /** 用户位置纬度 */
    private Double userLat;

    /**
     * VenueEnter
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
     * 申请人
     * @return Member_id 申请人
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 申请人
     * @param memberId 申请人
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     * 来源0=客户端1=短信
     * @return Source_flag 来源0=客户端1=短信
     */
    public Integer getSourceFlag() {
        return sourceFlag;
    }

    /**
     * 来源0=客户端1=短信
     * @param sourceFlag 来源0=客户端1=短信
     */
    public void setSourceFlag(Integer sourceFlag) {
        this.sourceFlag = sourceFlag;
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
     * 球场名称
     * @return Title 球场名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 球场名称
     * @param title 球场名称
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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
     * 球场类型(1=网球场2=足球场3=羽毛球馆4=篮球场)
     * @return Ball_type 球场类型(1=网球场2=足球场3=羽毛球馆4=篮球场)
     */
    public Integer getBallType() {
        return ballType;
    }

    /**
     * 球场类型(1=网球场2=足球场3=羽毛球馆4=篮球场)
     * @param ballType 球场类型(1=网球场2=足球场3=羽毛球馆4=篮球场)
     */
    public void setBallType(Integer ballType) {
        this.ballType = ballType;
    }

    /**
     * 审核状态0=待审核1=审核通过2=审核拒绝
     * @return Check_flag 审核状态0=待审核1=审核通过2=审核拒绝
     */
    public Integer getCheckFlag() {
        return checkFlag;
    }

    /**
     * 审核状态0=待审核1=审核通过2=审核拒绝
     * @param checkFlag 审核状态0=待审核1=审核通过2=审核拒绝
     */
    public void setCheckFlag(Integer checkFlag) {
        this.checkFlag = checkFlag;
    }

    /**
     * 审核时间
     * @return Check_time 审核时间
     */
    public Date getCheckTime() {
        return checkTime;
    }

    /**
     * 审核时间
     * @param checkTime 审核时间
     */
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    /**
     * 审核人
     * @return Check_staff 审核人
     */
    public String getCheckStaff() {
        return checkStaff;
    }

    /**
     * 审核人
     * @param checkStaff 审核人
     */
    public void setCheckStaff(String checkStaff) {
        this.checkStaff = checkStaff == null ? null : checkStaff.trim();
    }

    /**
     * 审核意见
     * @return Content 审核意见
     */
    public String getContent() {
        return content;
    }

    /**
     * 审核意见
     * @param content 审核意见
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 其他联系人
     * @return Main_name 其他联系人
     */
    public String getMainName() {
        return mainName;
    }

    /**
     * 其他联系人
     * @param mainName 其他联系人
     */
    public void setMainName(String mainName) {
        this.mainName = mainName == null ? null : mainName.trim();
    }

    /**
     * 其他联系人电话
     * @return Main_phone 其他联系人电话
     */
    public String getMainPhone() {
        return mainPhone;
    }

    /**
     * 其他联系人电话
     * @param mainPhone 其他联系人电话
     */
    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone == null ? null : mainPhone.trim();
    }

    /**
     * 联系人关系(1=负责人2=同事3=业主或其他)
     * @return Main_flag 联系人关系(1=负责人2=同事3=业主或其他)
     */
    public Integer getMainFlag() {
        return mainFlag;
    }

    /**
     * 联系人关系(1=负责人2=同事3=业主或其他)
     * @param mainFlag 联系人关系(1=负责人2=同事3=业主或其他)
     */
    public void setMainFlag(Integer mainFlag) {
        this.mainFlag = mainFlag;
    }

    /**
     * 球场片数
     * @return Ball_sum 球场片数
     */
    public Integer getBallSum() {
        return ballSum;
    }

    /**
     * 球场片数
     * @param ballSum 球场片数
     */
    public void setBallSum(Integer ballSum) {
        this.ballSum = ballSum;
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
     * 城市名
     * @return City_name 城市名
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 城市名
     * @param cityName 城市名
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * 区县名称
     * @return District_name 区县名称
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * 区县名称
     * @param districtName 区县名称
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    /**
     * 机构名称
     * @return Train_team_name 机构名称
     */
    public String getTrainTeamName() {
        return trainTeamName;
    }

    /**
     * 机构名称
     * @param trainTeamName 机构名称
     */
    public void setTrainTeamName(String trainTeamName) {
        this.trainTeamName = trainTeamName == null ? null : trainTeamName.trim();
    }

    /**
     * 机构ID
     * @return Train_team_id 机构ID
     */
    public String getTrainTeamId() {
        return trainTeamId;
    }

    /**
     * 机构ID
     * @param trainTeamId 机构ID
     */
    public void setTrainTeamId(String trainTeamId) {
        this.trainTeamId = trainTeamId == null ? null : trainTeamId.trim();
    }

    /**
     * 用户位置经度
     * @return User_lng 用户位置经度
     */
    public Double getUserLng() {
        return userLng;
    }

    /**
     * 用户位置经度
     * @param userLng 用户位置经度
     */
    public void setUserLng(Double userLng) {
        this.userLng = userLng;
    }

    /**
     * 用户位置纬度
     * @return User_lat 用户位置纬度
     */
    public Double getUserLat() {
        return userLat;
    }

    /**
     * 用户位置纬度
     * @param userLat 用户位置纬度
     */
    public void setUserLat(Double userLat) {
        this.userLat = userLat;
    }
}