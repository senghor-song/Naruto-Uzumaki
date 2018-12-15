package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 场馆表实体
 */
public class Venue implements Serializable {
	private City cityT;
	private District districtT;
	private VenueTemplate venueTemplate;
	private Integer sumTemplate;

	public Integer getSumTemplate() {
		return sumTemplate;
	}

	public void setSumTemplate(Integer sumTemplate) {
		this.sumTemplate = sumTemplate;
	}

	public VenueTemplate getVenueTemplate() {
		return venueTemplate;
	}

	public void setVenueTemplate(VenueTemplate venueTemplate) {
		this.venueTemplate = venueTemplate;
	}

	public City getCityT() {
		return cityT;
	}

	public void setCityT(City cityT) {
		this.cityT = cityT;
	}

	public District getDistrictT() {
		return districtT;
	}

	public void setDistrictT(District districtT) {
		this.districtT = districtT;
	}
    /** 场馆 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 修改时间 */
    private Date modifytime;

    /** 球场编号 */
    private Integer venueno;

    /** 场馆名 */
    private String name;

    /** 地址 */
    private String address;

    /** 城市 */
    private String cityid;

    /** 区县 */
    private String districtid;

    /** 联系人 */
    private String owner;

    /** 联系电话 */
    private String contactPhone;

    /** 通知电话 */
    private String informPhone;

    /** 场地数量 */
    private Integer ballsum;

    /** 封面 */
    private String image;

    /** 温馨提醒 */
    private String warmreminder;

    /** 订场确认(0=人工确认1=自动确认) */
    private Integer orderverify;

    /** 经度 */
    private Double longitude;

    /** 纬度 */
    private Double latitude;

    /** 球场类型(1=网球场2=足球场3=羽毛球馆4=篮球场) */
    private Integer type;

    /** 入驻状态0=空1=添加 */
    private Integer trainAddFlag;

    /** 培训机构ID */
    private String trainteam;

    /** 状态(1=正常2=屏蔽) */
    private Integer showflag;

    /** 最低价格培训 */
    private Double minAmount;

    /** 订场入口(0=否1=是) */
    private Integer reserveShow;

    /** 订场短信(0=否1=是) */
    private Integer reserveSms;

    /** 订场支付短信(0=否1=是) */
    private Integer reservePaySms;

    /** 最大显示订场天数 */
    private Integer maxDay;

    /**
     * Venue
     */
    private static final long serialVersionUID = 1L;

    /**
     * 场馆
     * @return ID 场馆
     */
    public String getId() {
        return id;
    }

    /**
     * 场馆
     * @param id 场馆
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 创建时间
     * @return CreateTime 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 修改时间
     * @return ModifyTime 修改时间
     */
    public Date getModifytime() {
        return modifytime;
    }

    /**
     * 修改时间
     * @param modifytime 修改时间
     */
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * 球场编号
     * @return VenueNO 球场编号
     */
    public Integer getVenueno() {
        return venueno;
    }

    /**
     * 球场编号
     * @param venueno 球场编号
     */
    public void setVenueno(Integer venueno) {
        this.venueno = venueno;
    }

    /**
     * 场馆名
     * @return Name 场馆名
     */
    public String getName() {
        return name;
    }

    /**
     * 场馆名
     * @param name 场馆名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
     * 城市
     * @return CityID 城市
     */
    public String getCityid() {
        return cityid;
    }

    /**
     * 城市
     * @param cityid 城市
     */
    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    /**
     * 区县
     * @return DistrictID 区县
     */
    public String getDistrictid() {
        return districtid;
    }

    /**
     * 区县
     * @param districtid 区县
     */
    public void setDistrictid(String districtid) {
        this.districtid = districtid == null ? null : districtid.trim();
    }

    /**
     * 联系人
     * @return Owner 联系人
     */
    public String getOwner() {
        return owner;
    }

    /**
     * 联系人
     * @param owner 联系人
     */
    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    /**
     * 联系电话
     * @return Contact_phone 联系电话
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * 联系电话
     * @param contactPhone 联系电话
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    /**
     * 通知电话
     * @return Inform_phone 通知电话
     */
    public String getInformPhone() {
        return informPhone;
    }

    /**
     * 通知电话
     * @param informPhone 通知电话
     */
    public void setInformPhone(String informPhone) {
        this.informPhone = informPhone == null ? null : informPhone.trim();
    }

    /**
     * 场地数量
     * @return BallSum 场地数量
     */
    public Integer getBallsum() {
        return ballsum;
    }

    /**
     * 场地数量
     * @param ballsum 场地数量
     */
    public void setBallsum(Integer ballsum) {
        this.ballsum = ballsum;
    }

    /**
     * 封面
     * @return Image 封面
     */
    public String getImage() {
        return image;
    }

    /**
     * 封面
     * @param image 封面
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * 温馨提醒
     * @return WarmReminder 温馨提醒
     */
    public String getWarmreminder() {
        return warmreminder;
    }

    /**
     * 温馨提醒
     * @param warmreminder 温馨提醒
     */
    public void setWarmreminder(String warmreminder) {
        this.warmreminder = warmreminder == null ? null : warmreminder.trim();
    }

    /**
     * 订场确认(0=人工确认1=自动确认)
     * @return OrderVerify 订场确认(0=人工确认1=自动确认)
     */
    public Integer getOrderverify() {
        return orderverify;
    }

    /**
     * 订场确认(0=人工确认1=自动确认)
     * @param orderverify 订场确认(0=人工确认1=自动确认)
     */
    public void setOrderverify(Integer orderverify) {
        this.orderverify = orderverify;
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
     * 球场类型(1=网球场2=足球场3=羽毛球馆4=篮球场)
     * @return Type 球场类型(1=网球场2=足球场3=羽毛球馆4=篮球场)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 球场类型(1=网球场2=足球场3=羽毛球馆4=篮球场)
     * @param type 球场类型(1=网球场2=足球场3=羽毛球馆4=篮球场)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 入驻状态0=空1=添加
     * @return Train_add_flag 入驻状态0=空1=添加
     */
    public Integer getTrainAddFlag() {
        return trainAddFlag;
    }

    /**
     * 入驻状态0=空1=添加
     * @param trainAddFlag 入驻状态0=空1=添加
     */
    public void setTrainAddFlag(Integer trainAddFlag) {
        this.trainAddFlag = trainAddFlag;
    }

    /**
     * 培训机构ID
     * @return TrainTeam 培训机构ID
     */
    public String getTrainteam() {
        return trainteam;
    }

    /**
     * 培训机构ID
     * @param trainteam 培训机构ID
     */
    public void setTrainteam(String trainteam) {
        this.trainteam = trainteam == null ? null : trainteam.trim();
    }

    /**
     * 状态(1=正常2=屏蔽)
     * @return ShowFlag 状态(1=正常2=屏蔽)
     */
    public Integer getShowflag() {
        return showflag;
    }

    /**
     * 状态(1=正常2=屏蔽)
     * @param showflag 状态(1=正常2=屏蔽)
     */
    public void setShowflag(Integer showflag) {
        this.showflag = showflag;
    }

    /**
     * 最低价格培训
     * @return Min_amount 最低价格培训
     */
    public Double getMinAmount() {
        return minAmount;
    }

    /**
     * 最低价格培训
     * @param minAmount 最低价格培训
     */
    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    /**
     * 订场入口(0=否1=是)
     * @return Reserve_show 订场入口(0=否1=是)
     */
    public Integer getReserveShow() {
        return reserveShow;
    }

    /**
     * 订场入口(0=否1=是)
     * @param reserveShow 订场入口(0=否1=是)
     */
    public void setReserveShow(Integer reserveShow) {
        this.reserveShow = reserveShow;
    }

    /**
     * 订场短信(0=否1=是)
     * @return Reserve_sms 订场短信(0=否1=是)
     */
    public Integer getReserveSms() {
        return reserveSms;
    }

    /**
     * 订场短信(0=否1=是)
     * @param reserveSms 订场短信(0=否1=是)
     */
    public void setReserveSms(Integer reserveSms) {
        this.reserveSms = reserveSms;
    }

    /**
     * 订场支付短信(0=否1=是)
     * @return Reserve_pay_sms 订场支付短信(0=否1=是)
     */
    public Integer getReservePaySms() {
        return reservePaySms;
    }

    /**
     * 订场支付短信(0=否1=是)
     * @param reservePaySms 订场支付短信(0=否1=是)
     */
    public void setReservePaySms(Integer reservePaySms) {
        this.reservePaySms = reservePaySms;
    }

    /**
     * 最大显示订场天数
     * @return Max_day 最大显示订场天数
     */
    public Integer getMaxDay() {
        return maxDay;
    }

    /**
     * 最大显示订场天数
     * @param maxDay 最大显示订场天数
     */
    public void setMaxDay(Integer maxDay) {
        this.maxDay = maxDay;
    }
}