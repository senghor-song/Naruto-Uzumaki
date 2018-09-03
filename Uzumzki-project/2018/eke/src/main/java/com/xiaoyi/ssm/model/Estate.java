package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 小区表实体
 */
public class Estate implements Serializable {
	
	private City cityT;
	private District districtT;
	private Area areaT;
	
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

	public Area getAreaT() {
		return areaT;
	}

	public void setAreaT(Area areaT) {
		this.areaT = areaT;
	}

	/** 租售->小区 */
    private String id;

    /** 小区名 */
    private String estate;

    /** 小区编号 */
    private String estateno;

    /** 城市 */
    private String city;

    /** 区县 */
    private String district;

    /** 片区 */
    private String area;

    /** 城市ID（辅助） */
    private String cityid;

    /** 区县ID（辅助） */
    private String districtid;

    /** 片区ID（辅助） */
    private String areaid;

    /** 拼音 */
    private String spell;

    /** 封面 */
    private String headimg;

    /** 经度(0为宏定义，代表缺经纬信息) */
    private Double latitude;

    /** 维度（同上） */
    private Double longitude;

    /** 地址(由经纬度生成) */
    private String address;

    /** 物业电话 */
    private String mgttel;

    /** 小区信息修改时间 */
    private Date modtime;

    /** 逻辑删除 */
    private Byte flagdeleted;

    /** 删除时间 */
    private Date deletedtime;

    /** <详情>小区描述 */
    private String moredescribe;

    /** <详情>建房年代 */
    private String morecompleteyear;

    /** <详情>建筑类型 */
    private String moretype;

    /** <详情>停车位 */
    private String moreparking;

    /** <详情>物业费 */
    private String morefee;

    /** <详情>开发商 */
    private String moredeveloper;

    /** <详情>物业公司 */
    private String moremgt;

    /** <详情>总户数 */
    private Integer moreroom;

    /** 小区公交 */
    private String configbus;

    /** 小区幼儿园 */
    private String configkindergarten;

    /** 小区中学 */
    private String configsecondary;

    /** 小区大学 */
    private String configuniversity;

    /** 小区商场 */
    private String configshopping;

    /** 小区商场 */
    private String confighospital;

    /** 小区邮局 */
    private String configemail;

    /** 小区银行 */
    private String configbank;

    /** 小区其他 */
    private String configelse;

    /** 小区内部配套 */
    private String configinterior;

    /** 匹配小区-58 */
    private String match58;

    /** 匹配小区-房天下 */
    private String matchfang;

    /** 匹配小区-安居客 */
    private String matchan;

    /** 备注 */
    private String remark;

    /**
     * Estate
     */
    private static final long serialVersionUID = 1L;

    /**
     * 租售->小区
     * @return ID 租售->小区
     */
    public String getId() {
        return id;
    }

    /**
     * 租售->小区
     * @param id 租售->小区
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 小区名
     * @return Estate 小区名
     */
    public String getEstate() {
        return estate;
    }

    /**
     * 小区名
     * @param estate 小区名
     */
    public void setEstate(String estate) {
        this.estate = estate == null ? null : estate.trim();
    }

    /**
     * 小区编号
     * @return EstateNo 小区编号
     */
    public String getEstateno() {
        return estateno;
    }

    /**
     * 小区编号
     * @param estateno 小区编号
     */
    public void setEstateno(String estateno) {
        this.estateno = estateno == null ? null : estateno.trim();
    }

    /**
     * 城市
     * @return City 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 城市
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 区县
     * @return District 区县
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 区县
     * @param district 区县
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * 片区
     * @return Area 片区
     */
    public String getArea() {
        return area;
    }

    /**
     * 片区
     * @param area 片区
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * 城市ID（辅助）
     * @return CityID 城市ID（辅助）
     */
    public String getCityid() {
        return cityid;
    }

    /**
     * 城市ID（辅助）
     * @param cityid 城市ID（辅助）
     */
    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    /**
     * 区县ID（辅助）
     * @return DistrictID 区县ID（辅助）
     */
    public String getDistrictid() {
        return districtid;
    }

    /**
     * 区县ID（辅助）
     * @param districtid 区县ID（辅助）
     */
    public void setDistrictid(String districtid) {
        this.districtid = districtid == null ? null : districtid.trim();
    }

    /**
     * 片区ID（辅助）
     * @return AreaID 片区ID（辅助）
     */
    public String getAreaid() {
        return areaid;
    }

    /**
     * 片区ID（辅助）
     * @param areaid 片区ID（辅助）
     */
    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    /**
     * 拼音
     * @return Spell 拼音
     */
    public String getSpell() {
        return spell;
    }

    /**
     * 拼音
     * @param spell 拼音
     */
    public void setSpell(String spell) {
        this.spell = spell == null ? null : spell.trim();
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
     * 经度(0为宏定义，代表缺经纬信息)
     * @return Latitude 经度(0为宏定义，代表缺经纬信息)
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 经度(0为宏定义，代表缺经纬信息)
     * @param latitude 经度(0为宏定义，代表缺经纬信息)
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * 维度（同上）
     * @return Longitude 维度（同上）
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 维度（同上）
     * @param longitude 维度（同上）
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
     * 物业电话
     * @return MgtTel 物业电话
     */
    public String getMgttel() {
        return mgttel;
    }

    /**
     * 物业电话
     * @param mgttel 物业电话
     */
    public void setMgttel(String mgttel) {
        this.mgttel = mgttel == null ? null : mgttel.trim();
    }

    /**
     * 小区信息修改时间
     * @return ModTime 小区信息修改时间
     */
    public Date getModtime() {
        return modtime;
    }

    /**
     * 小区信息修改时间
     * @param modtime 小区信息修改时间
     */
    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }

    /**
     * 逻辑删除
     * @return FlagDeleted 逻辑删除
     */
    public Byte getFlagdeleted() {
        return flagdeleted;
    }

    /**
     * 逻辑删除
     * @param flagdeleted 逻辑删除
     */
    public void setFlagdeleted(Byte flagdeleted) {
        this.flagdeleted = flagdeleted;
    }

    /**
     * 删除时间
     * @return DeletedTime 删除时间
     */
    public Date getDeletedtime() {
        return deletedtime;
    }

    /**
     * 删除时间
     * @param deletedtime 删除时间
     */
    public void setDeletedtime(Date deletedtime) {
        this.deletedtime = deletedtime;
    }

    /**
     * <详情>小区描述
     * @return MoreDescribe <详情>小区描述
     */
    public String getMoredescribe() {
        return moredescribe;
    }

    /**
     * <详情>小区描述
     * @param moredescribe <详情>小区描述
     */
    public void setMoredescribe(String moredescribe) {
        this.moredescribe = moredescribe == null ? null : moredescribe.trim();
    }

    /**
     * <详情>建房年代
     * @return MoreCompleteYear <详情>建房年代
     */
    public String getMorecompleteyear() {
        return morecompleteyear;
    }

    /**
     * <详情>建房年代
     * @param morecompleteyear <详情>建房年代
     */
    public void setMorecompleteyear(String morecompleteyear) {
        this.morecompleteyear = morecompleteyear == null ? null : morecompleteyear.trim();
    }

    /**
     * <详情>建筑类型
     * @return MoreType <详情>建筑类型
     */
    public String getMoretype() {
        return moretype;
    }

    /**
     * <详情>建筑类型
     * @param moretype <详情>建筑类型
     */
    public void setMoretype(String moretype) {
        this.moretype = moretype == null ? null : moretype.trim();
    }

    /**
     * <详情>停车位
     * @return MoreParking <详情>停车位
     */
    public String getMoreparking() {
        return moreparking;
    }

    /**
     * <详情>停车位
     * @param moreparking <详情>停车位
     */
    public void setMoreparking(String moreparking) {
        this.moreparking = moreparking == null ? null : moreparking.trim();
    }

    /**
     * <详情>物业费
     * @return MoreFee <详情>物业费
     */
    public String getMorefee() {
        return morefee;
    }

    /**
     * <详情>物业费
     * @param morefee <详情>物业费
     */
    public void setMorefee(String morefee) {
        this.morefee = morefee == null ? null : morefee.trim();
    }

    /**
     * <详情>开发商
     * @return MoreDeveloper <详情>开发商
     */
    public String getMoredeveloper() {
        return moredeveloper;
    }

    /**
     * <详情>开发商
     * @param moredeveloper <详情>开发商
     */
    public void setMoredeveloper(String moredeveloper) {
        this.moredeveloper = moredeveloper == null ? null : moredeveloper.trim();
    }

    /**
     * <详情>物业公司
     * @return MoreMgt <详情>物业公司
     */
    public String getMoremgt() {
        return moremgt;
    }

    /**
     * <详情>物业公司
     * @param moremgt <详情>物业公司
     */
    public void setMoremgt(String moremgt) {
        this.moremgt = moremgt == null ? null : moremgt.trim();
    }

    /**
     * <详情>总户数
     * @return MoreRoom <详情>总户数
     */
    public Integer getMoreroom() {
        return moreroom;
    }

    /**
     * <详情>总户数
     * @param moreroom <详情>总户数
     */
    public void setMoreroom(Integer moreroom) {
        this.moreroom = moreroom;
    }

    /**
     * 小区公交
     * @return ConfigBus 小区公交
     */
    public String getConfigbus() {
        return configbus;
    }

    /**
     * 小区公交
     * @param configbus 小区公交
     */
    public void setConfigbus(String configbus) {
        this.configbus = configbus == null ? null : configbus.trim();
    }

    /**
     * 小区幼儿园
     * @return ConfigKindergarten 小区幼儿园
     */
    public String getConfigkindergarten() {
        return configkindergarten;
    }

    /**
     * 小区幼儿园
     * @param configkindergarten 小区幼儿园
     */
    public void setConfigkindergarten(String configkindergarten) {
        this.configkindergarten = configkindergarten == null ? null : configkindergarten.trim();
    }

    /**
     * 小区中学
     * @return ConfigSecondary 小区中学
     */
    public String getConfigsecondary() {
        return configsecondary;
    }

    /**
     * 小区中学
     * @param configsecondary 小区中学
     */
    public void setConfigsecondary(String configsecondary) {
        this.configsecondary = configsecondary == null ? null : configsecondary.trim();
    }

    /**
     * 小区大学
     * @return ConfigUniversity 小区大学
     */
    public String getConfiguniversity() {
        return configuniversity;
    }

    /**
     * 小区大学
     * @param configuniversity 小区大学
     */
    public void setConfiguniversity(String configuniversity) {
        this.configuniversity = configuniversity == null ? null : configuniversity.trim();
    }

    /**
     * 小区商场
     * @return ConfigShopping 小区商场
     */
    public String getConfigshopping() {
        return configshopping;
    }

    /**
     * 小区商场
     * @param configshopping 小区商场
     */
    public void setConfigshopping(String configshopping) {
        this.configshopping = configshopping == null ? null : configshopping.trim();
    }

    /**
     * 小区商场
     * @return ConfigHospital 小区商场
     */
    public String getConfighospital() {
        return confighospital;
    }

    /**
     * 小区商场
     * @param confighospital 小区商场
     */
    public void setConfighospital(String confighospital) {
        this.confighospital = confighospital == null ? null : confighospital.trim();
    }

    /**
     * 小区邮局
     * @return ConfigEmail 小区邮局
     */
    public String getConfigemail() {
        return configemail;
    }

    /**
     * 小区邮局
     * @param configemail 小区邮局
     */
    public void setConfigemail(String configemail) {
        this.configemail = configemail == null ? null : configemail.trim();
    }

    /**
     * 小区银行
     * @return ConfigBank 小区银行
     */
    public String getConfigbank() {
        return configbank;
    }

    /**
     * 小区银行
     * @param configbank 小区银行
     */
    public void setConfigbank(String configbank) {
        this.configbank = configbank == null ? null : configbank.trim();
    }

    /**
     * 小区其他
     * @return ConfigElse 小区其他
     */
    public String getConfigelse() {
        return configelse;
    }

    /**
     * 小区其他
     * @param configelse 小区其他
     */
    public void setConfigelse(String configelse) {
        this.configelse = configelse == null ? null : configelse.trim();
    }

    /**
     * 小区内部配套
     * @return ConfigInterior 小区内部配套
     */
    public String getConfiginterior() {
        return configinterior;
    }

    /**
     * 小区内部配套
     * @param configinterior 小区内部配套
     */
    public void setConfiginterior(String configinterior) {
        this.configinterior = configinterior == null ? null : configinterior.trim();
    }

    /**
     * 匹配小区-58
     * @return Match58 匹配小区-58
     */
    public String getMatch58() {
        return match58;
    }

    /**
     * 匹配小区-58
     * @param match58 匹配小区-58
     */
    public void setMatch58(String match58) {
        this.match58 = match58 == null ? null : match58.trim();
    }

    /**
     * 匹配小区-房天下
     * @return MatchFang 匹配小区-房天下
     */
    public String getMatchfang() {
        return matchfang;
    }

    /**
     * 匹配小区-房天下
     * @param matchfang 匹配小区-房天下
     */
    public void setMatchfang(String matchfang) {
        this.matchfang = matchfang == null ? null : matchfang.trim();
    }

    /**
     * 匹配小区-安居客
     * @return MatchAn 匹配小区-安居客
     */
    public String getMatchan() {
        return matchan;
    }

    /**
     * 匹配小区-安居客
     * @param matchan 匹配小区-安居客
     */
    public void setMatchan(String matchan) {
        this.matchan = matchan == null ? null : matchan.trim();
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