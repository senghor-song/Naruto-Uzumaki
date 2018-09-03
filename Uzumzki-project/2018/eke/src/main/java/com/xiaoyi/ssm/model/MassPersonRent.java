package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MassPersonRent implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;

	/** 多对一收藏对象 */
	private EmpCustPerson empCustPerson;

	/** 多对一收藏对象 */
    public EmpCustPerson getEmpCustPerson() {
		return empCustPerson;
	}

	/** 多对一收藏对象 */
	public void setEmpCustPerson(EmpCustPerson empCustPerson) {
		this.empCustPerson = empCustPerson;
	}
    /**
     * 推房->个人出租
     */
    private String id;

    /**
     * 采集时间（当前）
     */
    private Date collectdate;

    /**
     * 网站
     */
    private String site;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    /**
     * 片区
     */
    private String area;

    /**
     * 小区
     */
    private String estate;

    /**
     * 标题
     */
    private String title;

    /**
     * 房
     */
    private Integer countf;

    /**
     * 厅
     */
    private Integer countt;

    /**
     * 卫
     */
    private Integer countw;

    /**
     * 图数
     */
    private Integer img;

    /**
     * 类型
     */
    private String type;

    /**
     * 朝向
     */
    private String direction;

    /**
     * 装修
     */
    private String decoration;

    /**
     * 面积
     */
    private BigDecimal square;

    /**
     * 售价
     */
    private Integer price;

    /**
     * 原房源时间（网站）
     */
    private String regdate;

    /**
     * 登记人姓名
     */
    private String regname;

    /**
     * 登记人电话
     */
    private String regtel;

    /**
     * 所在层
     */
    private Integer floor;

    /**
     * 总楼层
     */
    private Integer floorall;

    /**
     * 详情URL(重复比对关键字)
     */
    private String detailpath;

    /**
     * 删除标识
     */
    private Byte flagdeleted;

    /**
     * 删除时间
     */
    private Date deletedtime;

    /**
     * 
     */
    private String remark;

    /**
     * 推房->个人出租
     * @return ID 推房->个人出租
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->个人出租
     * @param id 推房->个人出租
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 采集时间（当前）
     * @return CollectDate 采集时间（当前）
     */
    public Date getCollectdate() {
        return collectdate;
    }

    /**
     * 采集时间（当前）
     * @param collectdate 采集时间（当前）
     */
    public void setCollectdate(Date collectdate) {
        this.collectdate = collectdate;
    }

    /**
     * 网站
     * @return Site 网站
     */
    public String getSite() {
        return site;
    }

    /**
     * 网站
     * @param site 网站
     */
    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
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
     * 小区
     * @return Estate 小区
     */
    public String getEstate() {
        return estate;
    }

    /**
     * 小区
     * @param estate 小区
     */
    public void setEstate(String estate) {
        this.estate = estate == null ? null : estate.trim();
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
     * 图数
     * @return Img 图数
     */
    public Integer getImg() {
        return img;
    }

    /**
     * 图数
     * @param img 图数
     */
    public void setImg(Integer img) {
        this.img = img;
    }

    /**
     * 类型
     * @return Type 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 类型
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
     * 装修
     * @return Decoration 装修
     */
    public String getDecoration() {
        return decoration;
    }

    /**
     * 装修
     * @param decoration 装修
     */
    public void setDecoration(String decoration) {
        this.decoration = decoration == null ? null : decoration.trim();
    }

    /**
     * 面积
     * @return Square 面积
     */
    public BigDecimal getSquare() {
        return square;
    }

    /**
     * 面积
     * @param square 面积
     */
    public void setSquare(BigDecimal square) {
        this.square = square;
    }

    /**
     * 售价
     * @return Price 售价
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 售价
     * @param price 售价
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * 原房源时间（网站）
     * @return RegDate 原房源时间（网站）
     */
    public String getRegdate() {
        return regdate;
    }

    /**
     * 原房源时间（网站）
     * @param regdate 原房源时间（网站）
     */
    public void setRegdate(String regdate) {
        this.regdate = regdate == null ? null : regdate.trim();
    }

    /**
     * 登记人姓名
     * @return RegName 登记人姓名
     */
    public String getRegname() {
        return regname;
    }

    /**
     * 登记人姓名
     * @param regname 登记人姓名
     */
    public void setRegname(String regname) {
        this.regname = regname == null ? null : regname.trim();
    }

    /**
     * 登记人电话
     * @return RegTel 登记人电话
     */
    public String getRegtel() {
        return regtel;
    }

    /**
     * 登记人电话
     * @param regtel 登记人电话
     */
    public void setRegtel(String regtel) {
        this.regtel = regtel == null ? null : regtel.trim();
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
     * 详情URL(重复比对关键字)
     * @return DetailPath 详情URL(重复比对关键字)
     */
    public String getDetailpath() {
        return detailpath;
    }

    /**
     * 详情URL(重复比对关键字)
     * @param detailpath 详情URL(重复比对关键字)
     */
    public void setDetailpath(String detailpath) {
        this.detailpath = detailpath == null ? null : detailpath.trim();
    }

    /**
     * 删除标识
     * @return FlagDeleted 删除标识
     */
    public Byte getFlagdeleted() {
        return flagdeleted;
    }

    /**
     * 删除标识
     * @param flagdeleted 删除标识
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
     * 
     * @return Remark 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * @param remark 
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}