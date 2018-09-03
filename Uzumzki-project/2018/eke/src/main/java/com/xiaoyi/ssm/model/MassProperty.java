package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 房库表实体
 */
public class MassProperty implements Serializable {
	/** 一对多对象集合 */
    private List<MassPropertyPublish> massPropertyPublishs;
    
    /** 住宅对象 */
    private MassHouse massHouse;

	private Estate estateT;
	private City cityT;
	private District districtT;
	private Area areaT;
	private Employee employeeT;
	private EmpStore empStoreT;
	
	/** 一对多对象集合 */
	public List<MassPropertyPublish> getMassPropertyPublishs() {
		return massPropertyPublishs;
	}
	
	/** 一对多对象集合 */
	public void setMassPropertyPublishs(List<MassPropertyPublish> massPropertyPublishs) {
		this.massPropertyPublishs = massPropertyPublishs;
	}
	
	/** 住宅对象 */
	public MassHouse getMassHouse() {
		return massHouse;
	}
	
	/** 住宅对象 */
	public void setMassHouse(MassHouse massHouse) {
		this.massHouse = massHouse;
	}
	
    public Estate getEstateT() {
		return estateT;
	}

	public void setEstateT(Estate estateT) {
		this.estateT = estateT;
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

	public Area getAreaT() {
		return areaT;
	}

	public void setAreaT(Area areaT) {
		this.areaT = areaT;
	}

	public Employee getEmployeeT() {
		return employeeT;
	}

	public void setEmployeeT(Employee employeeT) {
		this.employeeT = employeeT;
	}

	public EmpStore getEmpStoreT() {
		return empStoreT;
	}

	public void setEmpStoreT(EmpStore empStoreT) {
		this.empStoreT = empStoreT;
	}
	
    /** 推房->房库 */
    private String id;

    /** 登记时间 */
    private Date regdate;

    /** 录入方式(0=非手动录入，1=手动录入) */
    private Byte regtype;

    /** 登记经纪人ID */
    private String empid;

    /** 房源编号 */
    private Integer propertyno;

    /** 房源租售类型(0=售1=租) */
    private Integer trade;

    /** 小区ID */
    private String estateid;

    /** 小区名（辅助） */
    private String estate;

    /** 小区地址 */
    private String address;

    /** 栋座类型(1=栋2=弄3座4=号5=号楼6=胡同) */
    private Integer buildingtype;

    /** 栋座值 */
    private String buildingvalue;

    /** 单元类型(1=栋2=幢3=号4=号楼5=单元) */
    private Integer unittype;

    /** 单元值 */
    private String unitvalue;

    /** 房号 */
    private String roomno;

    /** 状态 */
    private String status;

    /** 房 */
    private Integer countf;

    /** 厅 */
    private Integer countt;

    /** 卫 */
    private Integer countw;

    /** 厨 */
    private Integer countc;

    /** 阳 */
    private Integer county;

    /** 建造年代 */
    private Integer ddlusedyear;

    /** 物业用途 */
    private String usage;

    /** 朝向 */
    private String direction;

    /** 看房方式 */
    private String look;

    /** 装修程度 */
    private String decoration;

    /** 建筑面积 */
    private BigDecimal squarej;

    /** 使用面积 */
    private Integer squares;

    /** 价格 */
    private Integer price;

    /** 房源标签(空格隔开，最多6个，2-5字) */
    private String houselabel;

    /** 配套标签(空格隔开，最多4个，2-5字) */
    private String celllabel;

    /** 所在层 */
    private Integer floor;

    /** 总楼层 */
    private Integer floorall;

    /** 封面URL */
    private String headimgpath;

    /** 租赁方式(分租,0=整租1=分租) */
    private Byte flagsublet;

    /** 几户合租(分租) */
    private Integer subletnumber;

    /** 条件(分租) */
    private String subletcondition;

    /** 房型(分租) */
    private String subletroom;

    /** 支付方式(分租) */
    private String subletpaytype;

    /** 删除标识(0=否1=是) */
    private Byte flagdeleted;

    /** 删除时间 */
    private Date deletedtime;

    /** 承诺客户佣金 */
    private BigDecimal feecust;

    /** 房源来源 */
    private Integer sourcetype;

    /** 是否视频(1=是0=否) */
    private Byte flagvideo;

    /** 是否多图(1=是0=否) */
    private Byte flagmoreimage;

    /** 特点修改时间 */
    private Date featuremodtime;

    /** 查看次数（定时统计） */
    private Integer viewtimes;

    /** 房库修改时间 */
    private Date modifytime;

    /** 推送时间 */
    private Date sendtime;

    /** 内部编号 */
    private String internalnum;

    /** 备案编号 */
    private String fileno;

    /** 城市ID */
    private String cityid;

    /** 城市名称 */
    private String cityname;

    /** 区县ID */
    private String districtid;

    /** 区县名称 */
    private String districtname;

    /** 片区ID */
    private String areaid;

    /** 片区名称 */
    private String areaname;

    /** 标题 */
    private String title;

    /** 业主心态 */
    private String mentality;

    /** 小区配套 */
    private String environment;

    /** 服务介绍 */
    private String serve;

    /** 税费信息 */
    private String taxation;

    /** 经纪人推荐词 */
    private String empfollowsay;

    /** 业主名 */
    private String ower;

    /** 业主手机号 */
    private String owertel;

    /** 业主其它联系方式等可以放在这里 */
    private String remark;

    /** 信息描述 */
    private String description;

    /**
     * MassProperty
     */
    private static final long serialVersionUID = 1L;

    /**
     * 推房->房库
     * @return ID 推房->房库
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->房库
     * @param id 推房->房库
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
     * 录入方式(0=非手动录入，1=手动录入)
     * @return RegType 录入方式(0=非手动录入，1=手动录入)
     */
    public Byte getRegtype() {
        return regtype;
    }

    /**
     * 录入方式(0=非手动录入，1=手动录入)
     * @param regtype 录入方式(0=非手动录入，1=手动录入)
     */
    public void setRegtype(Byte regtype) {
        this.regtype = regtype;
    }

    /**
     * 登记经纪人ID
     * @return EmpID 登记经纪人ID
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * 登记经纪人ID
     * @param empid 登记经纪人ID
     */
    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
    }

    /**
     * 房源编号
     * @return PropertyNo 房源编号
     */
    public Integer getPropertyno() {
        return propertyno;
    }

    /**
     * 房源编号
     * @param propertyno 房源编号
     */
    public void setPropertyno(Integer propertyno) {
        this.propertyno = propertyno;
    }

    /**
     * 房源租售类型(0=售1=租)
     * @return Trade 房源租售类型(0=售1=租)
     */
    public Integer getTrade() {
        return trade;
    }

    /**
     * 房源租售类型(0=售1=租)
     * @param trade 房源租售类型(0=售1=租)
     */
    public void setTrade(Integer trade) {
        this.trade = trade;
    }

    /**
     * 小区ID
     * @return EstateID 小区ID
     */
    public String getEstateid() {
        return estateid;
    }

    /**
     * 小区ID
     * @param estateid 小区ID
     */
    public void setEstateid(String estateid) {
        this.estateid = estateid == null ? null : estateid.trim();
    }

    /**
     * 小区名（辅助）
     * @return Estate 小区名（辅助）
     */
    public String getEstate() {
        return estate;
    }

    /**
     * 小区名（辅助）
     * @param estate 小区名（辅助）
     */
    public void setEstate(String estate) {
        this.estate = estate == null ? null : estate.trim();
    }

    /**
     * 小区地址
     * @return Address 小区地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 小区地址
     * @param address 小区地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 栋座类型(1=栋2=弄3座4=号5=号楼6=胡同)
     * @return BuildingType 栋座类型(1=栋2=弄3座4=号5=号楼6=胡同)
     */
    public Integer getBuildingtype() {
        return buildingtype;
    }

    /**
     * 栋座类型(1=栋2=弄3座4=号5=号楼6=胡同)
     * @param buildingtype 栋座类型(1=栋2=弄3座4=号5=号楼6=胡同)
     */
    public void setBuildingtype(Integer buildingtype) {
        this.buildingtype = buildingtype;
    }

    /**
     * 栋座值
     * @return BuildingValue 栋座值
     */
    public String getBuildingvalue() {
        return buildingvalue;
    }

    /**
     * 栋座值
     * @param buildingvalue 栋座值
     */
    public void setBuildingvalue(String buildingvalue) {
        this.buildingvalue = buildingvalue == null ? null : buildingvalue.trim();
    }

    /**
     * 单元类型(1=栋2=幢3=号4=号楼5=单元)
     * @return UnitType 单元类型(1=栋2=幢3=号4=号楼5=单元)
     */
    public Integer getUnittype() {
        return unittype;
    }

    /**
     * 单元类型(1=栋2=幢3=号4=号楼5=单元)
     * @param unittype 单元类型(1=栋2=幢3=号4=号楼5=单元)
     */
    public void setUnittype(Integer unittype) {
        this.unittype = unittype;
    }

    /**
     * 单元值
     * @return UnitValue 单元值
     */
    public String getUnitvalue() {
        return unitvalue;
    }

    /**
     * 单元值
     * @param unitvalue 单元值
     */
    public void setUnitvalue(String unitvalue) {
        this.unitvalue = unitvalue == null ? null : unitvalue.trim();
    }

    /**
     * 房号
     * @return RoomNo 房号
     */
    public String getRoomno() {
        return roomno;
    }

    /**
     * 房号
     * @param roomno 房号
     */
    public void setRoomno(String roomno) {
        this.roomno = roomno == null ? null : roomno.trim();
    }

    /**
     * 状态
     * @return Status 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 房
     * @return CountF 房
     */
    public Integer getCountf() {
        return countf;
    }

    /**
     * 房
     * @param countf 房
     */
    public void setCountf(Integer countf) {
        this.countf = countf;
    }

    /**
     * 厅
     * @return CountT 厅
     */
    public Integer getCountt() {
        return countt;
    }

    /**
     * 厅
     * @param countt 厅
     */
    public void setCountt(Integer countt) {
        this.countt = countt;
    }

    /**
     * 卫
     * @return CountW 卫
     */
    public Integer getCountw() {
        return countw;
    }

    /**
     * 卫
     * @param countw 卫
     */
    public void setCountw(Integer countw) {
        this.countw = countw;
    }

    /**
     * 厨
     * @return CountC 厨
     */
    public Integer getCountc() {
        return countc;
    }

    /**
     * 厨
     * @param countc 厨
     */
    public void setCountc(Integer countc) {
        this.countc = countc;
    }

    /**
     * 阳
     * @return CountY 阳
     */
    public Integer getCounty() {
        return county;
    }

    /**
     * 阳
     * @param county 阳
     */
    public void setCounty(Integer county) {
        this.county = county;
    }

    /**
     * 建造年代
     * @return DdlUsedYear 建造年代
     */
    public Integer getDdlusedyear() {
        return ddlusedyear;
    }

    /**
     * 建造年代
     * @param ddlusedyear 建造年代
     */
    public void setDdlusedyear(Integer ddlusedyear) {
        this.ddlusedyear = ddlusedyear;
    }

    /**
     * 物业用途
     * @return Usage 物业用途
     */
    public String getUsage() {
        return usage;
    }

    /**
     * 物业用途
     * @param usage 物业用途
     */
    public void setUsage(String usage) {
        this.usage = usage == null ? null : usage.trim();
    }

    /**
     * 朝向
     * @return Direction 朝向
     */
    public String getDirection() {
        return direction;
    }

    /**
     * 朝向
     * @param direction 朝向
     */
    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    /**
     * 看房方式
     * @return Look 看房方式
     */
    public String getLook() {
        return look;
    }

    /**
     * 看房方式
     * @param look 看房方式
     */
    public void setLook(String look) {
        this.look = look == null ? null : look.trim();
    }

    /**
     * 装修程度
     * @return Decoration 装修程度
     */
    public String getDecoration() {
        return decoration;
    }

    /**
     * 装修程度
     * @param decoration 装修程度
     */
    public void setDecoration(String decoration) {
        this.decoration = decoration == null ? null : decoration.trim();
    }

    /**
     * 建筑面积
     * @return SquareJ 建筑面积
     */
    public BigDecimal getSquarej() {
        return squarej;
    }

    /**
     * 建筑面积
     * @param squarej 建筑面积
     */
    public void setSquarej(BigDecimal squarej) {
        this.squarej = squarej;
    }

    /**
     * 使用面积
     * @return SquareS 使用面积
     */
    public Integer getSquares() {
        return squares;
    }

    /**
     * 使用面积
     * @param squares 使用面积
     */
    public void setSquares(Integer squares) {
        this.squares = squares;
    }

    /**
     * 价格
     * @return Price 价格
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 价格
     * @param price 价格
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * 房源标签(空格隔开，最多6个，2-5字)
     * @return HouseLabel 房源标签(空格隔开，最多6个，2-5字)
     */
    public String getHouselabel() {
        return houselabel;
    }

    /**
     * 房源标签(空格隔开，最多6个，2-5字)
     * @param houselabel 房源标签(空格隔开，最多6个，2-5字)
     */
    public void setHouselabel(String houselabel) {
        this.houselabel = houselabel == null ? null : houselabel.trim();
    }

    /**
     * 配套标签(空格隔开，最多4个，2-5字)
     * @return CellLabel 配套标签(空格隔开，最多4个，2-5字)
     */
    public String getCelllabel() {
        return celllabel;
    }

    /**
     * 配套标签(空格隔开，最多4个，2-5字)
     * @param celllabel 配套标签(空格隔开，最多4个，2-5字)
     */
    public void setCelllabel(String celllabel) {
        this.celllabel = celllabel == null ? null : celllabel.trim();
    }

    /**
     * 所在层
     * @return Floor 所在层
     */
    public Integer getFloor() {
        return floor;
    }

    /**
     * 所在层
     * @param floor 所在层
     */
    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    /**
     * 总楼层
     * @return FloorAll 总楼层
     */
    public Integer getFloorall() {
        return floorall;
    }

    /**
     * 总楼层
     * @param floorall 总楼层
     */
    public void setFloorall(Integer floorall) {
        this.floorall = floorall;
    }

    /**
     * 封面URL
     * @return HeadImgPath 封面URL
     */
    public String getHeadimgpath() {
        return headimgpath;
    }

    /**
     * 封面URL
     * @param headimgpath 封面URL
     */
    public void setHeadimgpath(String headimgpath) {
        this.headimgpath = headimgpath == null ? null : headimgpath.trim();
    }

    /**
     * 租赁方式(分租,0=整租1=分租)
     * @return FlagSublet 租赁方式(分租,0=整租1=分租)
     */
    public Byte getFlagsublet() {
        return flagsublet;
    }

    /**
     * 租赁方式(分租,0=整租1=分租)
     * @param flagsublet 租赁方式(分租,0=整租1=分租)
     */
    public void setFlagsublet(Byte flagsublet) {
        this.flagsublet = flagsublet;
    }

    /**
     * 几户合租(分租)
     * @return SubletNumber 几户合租(分租)
     */
    public Integer getSubletnumber() {
        return subletnumber;
    }

    /**
     * 几户合租(分租)
     * @param subletnumber 几户合租(分租)
     */
    public void setSubletnumber(Integer subletnumber) {
        this.subletnumber = subletnumber;
    }

    /**
     * 条件(分租)
     * @return SubletCondition 条件(分租)
     */
    public String getSubletcondition() {
        return subletcondition;
    }

    /**
     * 条件(分租)
     * @param subletcondition 条件(分租)
     */
    public void setSubletcondition(String subletcondition) {
        this.subletcondition = subletcondition == null ? null : subletcondition.trim();
    }

    /**
     * 房型(分租)
     * @return SubletRoom 房型(分租)
     */
    public String getSubletroom() {
        return subletroom;
    }

    /**
     * 房型(分租)
     * @param subletroom 房型(分租)
     */
    public void setSubletroom(String subletroom) {
        this.subletroom = subletroom == null ? null : subletroom.trim();
    }

    /**
     * 支付方式(分租)
     * @return SubletPayType 支付方式(分租)
     */
    public String getSubletpaytype() {
        return subletpaytype;
    }

    /**
     * 支付方式(分租)
     * @param subletpaytype 支付方式(分租)
     */
    public void setSubletpaytype(String subletpaytype) {
        this.subletpaytype = subletpaytype == null ? null : subletpaytype.trim();
    }

    /**
     * 删除标识(0=否1=是)
     * @return FlagDeleted 删除标识(0=否1=是)
     */
    public Byte getFlagdeleted() {
        return flagdeleted;
    }

    /**
     * 删除标识(0=否1=是)
     * @param flagdeleted 删除标识(0=否1=是)
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
     * 承诺客户佣金
     * @return FeeCust 承诺客户佣金
     */
    public BigDecimal getFeecust() {
        return feecust;
    }

    /**
     * 承诺客户佣金
     * @param feecust 承诺客户佣金
     */
    public void setFeecust(BigDecimal feecust) {
        this.feecust = feecust;
    }

    /**
     * 房源来源
     * @return SourceType 房源来源
     */
    public Integer getSourcetype() {
        return sourcetype;
    }

    /**
     * 房源来源
     * @param sourcetype 房源来源
     */
    public void setSourcetype(Integer sourcetype) {
        this.sourcetype = sourcetype;
    }

    /**
     * 是否视频(1=是0=否)
     * @return FlagVideo 是否视频(1=是0=否)
     */
    public Byte getFlagvideo() {
        return flagvideo;
    }

    /**
     * 是否视频(1=是0=否)
     * @param flagvideo 是否视频(1=是0=否)
     */
    public void setFlagvideo(Byte flagvideo) {
        this.flagvideo = flagvideo;
    }

    /**
     * 是否多图(1=是0=否)
     * @return FlagMoreImage 是否多图(1=是0=否)
     */
    public Byte getFlagmoreimage() {
        return flagmoreimage;
    }

    /**
     * 是否多图(1=是0=否)
     * @param flagmoreimage 是否多图(1=是0=否)
     */
    public void setFlagmoreimage(Byte flagmoreimage) {
        this.flagmoreimage = flagmoreimage;
    }

    /**
     * 特点修改时间
     * @return FeatureModTime 特点修改时间
     */
    public Date getFeaturemodtime() {
        return featuremodtime;
    }

    /**
     * 特点修改时间
     * @param featuremodtime 特点修改时间
     */
    public void setFeaturemodtime(Date featuremodtime) {
        this.featuremodtime = featuremodtime;
    }

    /**
     * 查看次数（定时统计）
     * @return ViewTimes 查看次数（定时统计）
     */
    public Integer getViewtimes() {
        return viewtimes;
    }

    /**
     * 查看次数（定时统计）
     * @param viewtimes 查看次数（定时统计）
     */
    public void setViewtimes(Integer viewtimes) {
        this.viewtimes = viewtimes;
    }

    /**
     * 房库修改时间
     * @return ModifyTime 房库修改时间
     */
    public Date getModifytime() {
        return modifytime;
    }

    /**
     * 房库修改时间
     * @param modifytime 房库修改时间
     */
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * 推送时间
     * @return SendTime 推送时间
     */
    public Date getSendtime() {
        return sendtime;
    }

    /**
     * 推送时间
     * @param sendtime 推送时间
     */
    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    /**
     * 内部编号
     * @return InternalNum 内部编号
     */
    public String getInternalnum() {
        return internalnum;
    }

    /**
     * 内部编号
     * @param internalnum 内部编号
     */
    public void setInternalnum(String internalnum) {
        this.internalnum = internalnum == null ? null : internalnum.trim();
    }

    /**
     * 备案编号
     * @return FileNo 备案编号
     */
    public String getFileno() {
        return fileno;
    }

    /**
     * 备案编号
     * @param fileno 备案编号
     */
    public void setFileno(String fileno) {
        this.fileno = fileno == null ? null : fileno.trim();
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
     * 城市名称
     * @return CityName 城市名称
     */
    public String getCityname() {
        return cityname;
    }

    /**
     * 城市名称
     * @param cityname 城市名称
     */
    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
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
     * 区县名称
     * @return DistrictName 区县名称
     */
    public String getDistrictname() {
        return districtname;
    }

    /**
     * 区县名称
     * @param districtname 区县名称
     */
    public void setDistrictname(String districtname) {
        this.districtname = districtname == null ? null : districtname.trim();
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
     * 片区名称
     * @return AreaName 片区名称
     */
    public String getAreaname() {
        return areaname;
    }

    /**
     * 片区名称
     * @param areaname 片区名称
     */
    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
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
     * 业主心态
     * @return Mentality 业主心态
     */
    public String getMentality() {
        return mentality;
    }

    /**
     * 业主心态
     * @param mentality 业主心态
     */
    public void setMentality(String mentality) {
        this.mentality = mentality == null ? null : mentality.trim();
    }

    /**
     * 小区配套
     * @return Environment 小区配套
     */
    public String getEnvironment() {
        return environment;
    }

    /**
     * 小区配套
     * @param environment 小区配套
     */
    public void setEnvironment(String environment) {
        this.environment = environment == null ? null : environment.trim();
    }

    /**
     * 服务介绍
     * @return Serve 服务介绍
     */
    public String getServe() {
        return serve;
    }

    /**
     * 服务介绍
     * @param serve 服务介绍
     */
    public void setServe(String serve) {
        this.serve = serve == null ? null : serve.trim();
    }

    /**
     * 税费信息
     * @return Taxation 税费信息
     */
    public String getTaxation() {
        return taxation;
    }

    /**
     * 税费信息
     * @param taxation 税费信息
     */
    public void setTaxation(String taxation) {
        this.taxation = taxation == null ? null : taxation.trim();
    }

    /**
     * 经纪人推荐词
     * @return EmpFollowSay 经纪人推荐词
     */
    public String getEmpfollowsay() {
        return empfollowsay;
    }

    /**
     * 经纪人推荐词
     * @param empfollowsay 经纪人推荐词
     */
    public void setEmpfollowsay(String empfollowsay) {
        this.empfollowsay = empfollowsay == null ? null : empfollowsay.trim();
    }

    /**
     * 业主名
     * @return Ower 业主名
     */
    public String getOwer() {
        return ower;
    }

    /**
     * 业主名
     * @param ower 业主名
     */
    public void setOwer(String ower) {
        this.ower = ower == null ? null : ower.trim();
    }

    /**
     * 业主手机号
     * @return OwerTel 业主手机号
     */
    public String getOwertel() {
        return owertel;
    }

    /**
     * 业主手机号
     * @param owertel 业主手机号
     */
    public void setOwertel(String owertel) {
        this.owertel = owertel == null ? null : owertel.trim();
    }

    /**
     * 业主其它联系方式等可以放在这里
     * @return Remark 业主其它联系方式等可以放在这里
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 业主其它联系方式等可以放在这里
     * @param remark 业主其它联系方式等可以放在这里
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 信息描述
     * @return Description 信息描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 信息描述
     * @param description 信息描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}