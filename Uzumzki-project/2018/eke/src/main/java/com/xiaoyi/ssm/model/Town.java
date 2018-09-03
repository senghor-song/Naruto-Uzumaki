package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 新盘实体
 */
public class Town implements Serializable {
	
	private City cityT;
	
    public City getCityT() {
		return cityT;
	}

	public void setCityT(City cityT) {
		this.cityT = cityT;
	}

	/** 新房->新房 */
    private String id;

    /** 新房名 */
    private String town;

    /** 城市ID */
    private String cityid;

    /** （辅助） */
    private String city;

    /** 区县ID */
    private String districtid;

    /** （辅助） */
    private String district;

    /** 片区ID */
    private String areaid;

    /** （辅助） */
    private String area;

    /** 排序 */
    private Integer sort;

    /** 状态(0=展示1=下架) */
    private Integer status;

    /** 中心点经度 */
    private String longitude;

    /** 中心点纬度 */
    private String latitude;

    /** 地址(由经纬度生成) */
    private String address;

    /** 标注售价 */
    private Integer price;

    /** 特点ID，数组格式 */
    private String feature;

    /** 展示日期 */
    private Date show;

    /** 户型 */
    private String layout;

    /** 封面 */
    private String headimg;

    /** 登记时间 */
    private Date regdate;

    /** 驻场联系人 */
    private String contact;

    /** 驻场联系人电话 */
    private String contacttel;

    /** 开盘时间 */
    private Date open;

    /** 合作期(开始) */
    private Date cooperationstart;

    /** 合作期(结束) */
    private Date cooperationend;

    /** 佣金结算类型(0=七天后佣) */
    private Integer commissionbilling;

    /** 交通配套 */
    private String traffic;

    /** 教育资源 */
    private String education;

    /** 医疗健康 */
    private String medical;

    /** 生活娱乐 */
    private String entertainment;

    /** 交房时间 */
    private Date handoverdate;

    /** 开发商 */
    private String devcompany;

    /** 物业公司 */
    private String mgtcompany;

    /** 售楼部电话 */
    private String mgttel;

    /** 总建筑面积 */
    private Double square;

    /** 绿化率 */
    private String green;

    /** 总户数 */
    private Integer room;

    /** 车位数 */
    private Integer park;

    /** 均价 */
    private Double averageprice;

    /** 物业费 */
    private Double mgtprice;

    /** 建筑类型 */
    private String propertytype;

    /** 装修状态 */
    private String decoration;

    /** 产权年限 */
    private Integer landgrade;

    /** 新盘类型(0=住房1=别墅2=商铺) */
    private Integer towntype;

    /** 查看次数更新时间 */
    private Date viewtimesflash;

    /** 备注 */
    private String remark;

    /**
     * Town
     */
    private static final long serialVersionUID = 1L;

    /**
     * 新房->新房
     * @return ID 新房->新房
     */
    public String getId() {
        return id;
    }

    /**
     * 新房->新房
     * @param id 新房->新房
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 新房名
     * @return Town 新房名
     */
    public String getTown() {
        return town;
    }

    /**
     * 新房名
     * @param town 新房名
     */
    public void setTown(String town) {
        this.town = town == null ? null : town.trim();
    }

    /**
     * 城市ID
     * @return CityID 城市ID
     */
    public String getCityid() {
        return cityid;
    }

    /**
     * 城市ID
     * @param cityid 城市ID
     */
    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    /**
     * （辅助）
     * @return City （辅助）
     */
    public String getCity() {
        return city;
    }

    /**
     * （辅助）
     * @param city （辅助）
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 区县ID
     * @return DistrictID 区县ID
     */
    public String getDistrictid() {
        return districtid;
    }

    /**
     * 区县ID
     * @param districtid 区县ID
     */
    public void setDistrictid(String districtid) {
        this.districtid = districtid == null ? null : districtid.trim();
    }

    /**
     * （辅助）
     * @return District （辅助）
     */
    public String getDistrict() {
        return district;
    }

    /**
     * （辅助）
     * @param district （辅助）
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * 片区ID
     * @return AreaID 片区ID
     */
    public String getAreaid() {
        return areaid;
    }

    /**
     * 片区ID
     * @param areaid 片区ID
     */
    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    /**
     * （辅助）
     * @return Area （辅助）
     */
    public String getArea() {
        return area;
    }

    /**
     * （辅助）
     * @param area （辅助）
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * 排序
     * @return Sort 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 状态(0=展示1=下架)
     * @return Status 状态(0=展示1=下架)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态(0=展示1=下架)
     * @param status 状态(0=展示1=下架)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 中心点经度
     * @return Longitude 中心点经度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 中心点经度
     * @param longitude 中心点经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    /**
     * 中心点纬度
     * @return Latitude 中心点纬度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 中心点纬度
     * @param latitude 中心点纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    /**
     * 地址(由经纬度生成)
     * @return Address 地址(由经纬度生成)
     */
    public String getAddress() {
        return address;
    }

    /**
     * 地址(由经纬度生成)
     * @param address 地址(由经纬度生成)
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 标注售价
     * @return Price 标注售价
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 标注售价
     * @param price 标注售价
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * 特点ID，数组格式
     * @return Feature 特点ID，数组格式
     */
    public String getFeature() {
        return feature;
    }

    /**
     * 特点ID，数组格式
     * @param feature 特点ID，数组格式
     */
    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }

    /**
     * 展示日期
     * @return Show 展示日期
     */
    public Date getShow() {
        return show;
    }

    /**
     * 展示日期
     * @param show 展示日期
     */
    public void setShow(Date show) {
        this.show = show;
    }

    /**
     * 户型
     * @return Layout 户型
     */
    public String getLayout() {
        return layout;
    }

    /**
     * 户型
     * @param layout 户型
     */
    public void setLayout(String layout) {
        this.layout = layout == null ? null : layout.trim();
    }

    /**
     * 封面
     * @return HeadImg 封面
     */
    public String getHeadimg() {
        return headimg;
    }

    /**
     * 封面
     * @param headimg 封面
     */
    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

    /**
     * 登记时间
     * @return RegDate 登记时间
     */
    public Date getRegdate() {
        return regdate;
    }

    /**
     * 登记时间
     * @param regdate 登记时间
     */
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    /**
     * 驻场联系人
     * @return Contact 驻场联系人
     */
    public String getContact() {
        return contact;
    }

    /**
     * 驻场联系人
     * @param contact 驻场联系人
     */
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    /**
     * 驻场联系人电话
     * @return ContactTel 驻场联系人电话
     */
    public String getContacttel() {
        return contacttel;
    }

    /**
     * 驻场联系人电话
     * @param contacttel 驻场联系人电话
     */
    public void setContacttel(String contacttel) {
        this.contacttel = contacttel == null ? null : contacttel.trim();
    }

    /**
     * 开盘时间
     * @return Open 开盘时间
     */
    public Date getOpen() {
        return open;
    }

    /**
     * 开盘时间
     * @param open 开盘时间
     */
    public void setOpen(Date open) {
        this.open = open;
    }

    /**
     * 合作期(开始)
     * @return CooperationStart 合作期(开始)
     */
    public Date getCooperationstart() {
        return cooperationstart;
    }

    /**
     * 合作期(开始)
     * @param cooperationstart 合作期(开始)
     */
    public void setCooperationstart(Date cooperationstart) {
        this.cooperationstart = cooperationstart;
    }

    /**
     * 合作期(结束)
     * @return CooperationEnd 合作期(结束)
     */
    public Date getCooperationend() {
        return cooperationend;
    }

    /**
     * 合作期(结束)
     * @param cooperationend 合作期(结束)
     */
    public void setCooperationend(Date cooperationend) {
        this.cooperationend = cooperationend;
    }

    /**
     * 佣金结算类型(0=七天后佣)
     * @return CommissionBilling 佣金结算类型(0=七天后佣)
     */
    public Integer getCommissionbilling() {
        return commissionbilling;
    }

    /**
     * 佣金结算类型(0=七天后佣)
     * @param commissionbilling 佣金结算类型(0=七天后佣)
     */
    public void setCommissionbilling(Integer commissionbilling) {
        this.commissionbilling = commissionbilling;
    }

    /**
     * 交通配套
     * @return Traffic 交通配套
     */
    public String getTraffic() {
        return traffic;
    }

    /**
     * 交通配套
     * @param traffic 交通配套
     */
    public void setTraffic(String traffic) {
        this.traffic = traffic == null ? null : traffic.trim();
    }

    /**
     * 教育资源
     * @return Education 教育资源
     */
    public String getEducation() {
        return education;
    }

    /**
     * 教育资源
     * @param education 教育资源
     */
    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    /**
     * 医疗健康
     * @return Medical 医疗健康
     */
    public String getMedical() {
        return medical;
    }

    /**
     * 医疗健康
     * @param medical 医疗健康
     */
    public void setMedical(String medical) {
        this.medical = medical == null ? null : medical.trim();
    }

    /**
     * 生活娱乐
     * @return Entertainment 生活娱乐
     */
    public String getEntertainment() {
        return entertainment;
    }

    /**
     * 生活娱乐
     * @param entertainment 生活娱乐
     */
    public void setEntertainment(String entertainment) {
        this.entertainment = entertainment == null ? null : entertainment.trim();
    }

    /**
     * 交房时间
     * @return HandOverDate 交房时间
     */
    public Date getHandoverdate() {
        return handoverdate;
    }

    /**
     * 交房时间
     * @param handoverdate 交房时间
     */
    public void setHandoverdate(Date handoverdate) {
        this.handoverdate = handoverdate;
    }

    /**
     * 开发商
     * @return DevCompany 开发商
     */
    public String getDevcompany() {
        return devcompany;
    }

    /**
     * 开发商
     * @param devcompany 开发商
     */
    public void setDevcompany(String devcompany) {
        this.devcompany = devcompany == null ? null : devcompany.trim();
    }

    /**
     * 物业公司
     * @return MgtCompany 物业公司
     */
    public String getMgtcompany() {
        return mgtcompany;
    }

    /**
     * 物业公司
     * @param mgtcompany 物业公司
     */
    public void setMgtcompany(String mgtcompany) {
        this.mgtcompany = mgtcompany == null ? null : mgtcompany.trim();
    }

    /**
     * 售楼部电话
     * @return MgtTel 售楼部电话
     */
    public String getMgttel() {
        return mgttel;
    }

    /**
     * 售楼部电话
     * @param mgttel 售楼部电话
     */
    public void setMgttel(String mgttel) {
        this.mgttel = mgttel == null ? null : mgttel.trim();
    }

    /**
     * 总建筑面积
     * @return Square 总建筑面积
     */
    public Double getSquare() {
        return square;
    }

    /**
     * 总建筑面积
     * @param square 总建筑面积
     */
    public void setSquare(Double square) {
        this.square = square;
    }

    /**
     * 绿化率
     * @return Green 绿化率
     */
    public String getGreen() {
        return green;
    }

    /**
     * 绿化率
     * @param green 绿化率
     */
    public void setGreen(String green) {
        this.green = green == null ? null : green.trim();
    }

    /**
     * 总户数
     * @return Room 总户数
     */
    public Integer getRoom() {
        return room;
    }

    /**
     * 总户数
     * @param room 总户数
     */
    public void setRoom(Integer room) {
        this.room = room;
    }

    /**
     * 车位数
     * @return Park 车位数
     */
    public Integer getPark() {
        return park;
    }

    /**
     * 车位数
     * @param park 车位数
     */
    public void setPark(Integer park) {
        this.park = park;
    }

    /**
     * 均价
     * @return AveragePrice 均价
     */
    public Double getAverageprice() {
        return averageprice;
    }

    /**
     * 均价
     * @param averageprice 均价
     */
    public void setAverageprice(Double averageprice) {
        this.averageprice = averageprice;
    }

    /**
     * 物业费
     * @return MgtPrice 物业费
     */
    public Double getMgtprice() {
        return mgtprice;
    }

    /**
     * 物业费
     * @param mgtprice 物业费
     */
    public void setMgtprice(Double mgtprice) {
        this.mgtprice = mgtprice;
    }

    /**
     * 建筑类型
     * @return PropertyType 建筑类型
     */
    public String getPropertytype() {
        return propertytype;
    }

    /**
     * 建筑类型
     * @param propertytype 建筑类型
     */
    public void setPropertytype(String propertytype) {
        this.propertytype = propertytype == null ? null : propertytype.trim();
    }

    /**
     * 装修状态
     * @return Decoration 装修状态
     */
    public String getDecoration() {
        return decoration;
    }

    /**
     * 装修状态
     * @param decoration 装修状态
     */
    public void setDecoration(String decoration) {
        this.decoration = decoration == null ? null : decoration.trim();
    }

    /**
     * 产权年限
     * @return LandGrade 产权年限
     */
    public Integer getLandgrade() {
        return landgrade;
    }

    /**
     * 产权年限
     * @param landgrade 产权年限
     */
    public void setLandgrade(Integer landgrade) {
        this.landgrade = landgrade;
    }

    /**
     * 新盘类型(0=住房1=别墅2=商铺)
     * @return TownType 新盘类型(0=住房1=别墅2=商铺)
     */
    public Integer getTowntype() {
        return towntype;
    }

    /**
     * 新盘类型(0=住房1=别墅2=商铺)
     * @param towntype 新盘类型(0=住房1=别墅2=商铺)
     */
    public void setTowntype(Integer towntype) {
        this.towntype = towntype;
    }

    /**
     * 查看次数更新时间
     * @return ViewTimesFlash 查看次数更新时间
     */
    public Date getViewtimesflash() {
        return viewtimesflash;
    }

    /**
     * 查看次数更新时间
     * @param viewtimesflash 查看次数更新时间
     */
    public void setViewtimesflash(Date viewtimesflash) {
        this.viewtimesflash = viewtimesflash;
    }

    /**
     * 备注
     * @return Remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}