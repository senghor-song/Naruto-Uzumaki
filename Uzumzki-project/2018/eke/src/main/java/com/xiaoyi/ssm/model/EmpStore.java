package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 商户表实体
 */
public class EmpStore implements Serializable {
	
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
	
    /** 商户->商户 */
    private String id;

    /** 商户名 */
    private String empstore;

    /** 门店码 */
    private Integer empstoreno;

    /** 商户类型 */
    private String type;

    /** 城市(季审时更新) */
    private String cityid;

    /** 区县(季审时更新) */
    private String districtid;

    /** 片区(季审时更新) */
    private String areaid;

    /** 位置(由经纬度生成) */
    private String address;

    /** 位置经度 */
    private Double longitude;

    /** 位置维度 */
    private Double latitude;

    /** 状态 */
    private String status;

    /** 逻辑删除(0=否1=是) */
    private Byte flagdeleted;

    /** 删除时间 */
    private Date deletedtime;

    /** 图片地址 */
    private String imageurl;

    /** 当前使能LOGO，缺省为32个0 */
    private String logoid;

    /** 经纪人总数 */
    private Integer empsum;

    /** 备注 */
    private String remark;

    /**
     * EmpStore
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商户->商户
     * @return ID 商户->商户
     */
    public String getId() {
        return id;
    }

    /**
     * 商户->商户
     * @param id 商户->商户
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 商户名
     * @return EmpStore 商户名
     */
    public String getEmpstore() {
        return empstore;
    }

    /**
     * 商户名
     * @param empstore 商户名
     */
    public void setEmpstore(String empstore) {
        this.empstore = empstore == null ? null : empstore.trim();
    }

    /**
     * 门店码
     * @return EmpStoreNo 门店码
     */
    public Integer getEmpstoreno() {
        return empstoreno;
    }

    /**
     * 门店码
     * @param empstoreno 门店码
     */
    public void setEmpstoreno(Integer empstoreno) {
        this.empstoreno = empstoreno;
    }

    /**
     * 商户类型
     * @return Type 商户类型
     */
    public String getType() {
        return type;
    }

    /**
     * 商户类型
     * @param type 商户类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 城市(季审时更新)
     * @return CityID 城市(季审时更新)
     */
    public String getCityid() {
        return cityid;
    }

    /**
     * 城市(季审时更新)
     * @param cityid 城市(季审时更新)
     */
    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    /**
     * 区县(季审时更新)
     * @return DistrictID 区县(季审时更新)
     */
    public String getDistrictid() {
        return districtid;
    }

    /**
     * 区县(季审时更新)
     * @param districtid 区县(季审时更新)
     */
    public void setDistrictid(String districtid) {
        this.districtid = districtid == null ? null : districtid.trim();
    }

    /**
     * 片区(季审时更新)
     * @return AreaID 片区(季审时更新)
     */
    public String getAreaid() {
        return areaid;
    }

    /**
     * 片区(季审时更新)
     * @param areaid 片区(季审时更新)
     */
    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    /**
     * 位置(由经纬度生成)
     * @return Address 位置(由经纬度生成)
     */
    public String getAddress() {
        return address;
    }

    /**
     * 位置(由经纬度生成)
     * @param address 位置(由经纬度生成)
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 位置经度
     * @return Longitude 位置经度
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 位置经度
     * @param longitude 位置经度
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 位置维度
     * @return Latitude 位置维度
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 位置维度
     * @param latitude 位置维度
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
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
     * 逻辑删除(0=否1=是)
     * @return FlagDeleted 逻辑删除(0=否1=是)
     */
    public Byte getFlagdeleted() {
        return flagdeleted;
    }

    /**
     * 逻辑删除(0=否1=是)
     * @param flagdeleted 逻辑删除(0=否1=是)
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
     * 图片地址
     * @return ImageUrl 图片地址
     */
    public String getImageurl() {
        return imageurl;
    }

    /**
     * 图片地址
     * @param imageurl 图片地址
     */
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl == null ? null : imageurl.trim();
    }

    /**
     * 当前使能LOGO，缺省为32个0
     * @return LogoID 当前使能LOGO，缺省为32个0
     */
    public String getLogoid() {
        return logoid;
    }

    /**
     * 当前使能LOGO，缺省为32个0
     * @param logoid 当前使能LOGO，缺省为32个0
     */
    public void setLogoid(String logoid) {
        this.logoid = logoid == null ? null : logoid.trim();
    }

    /**
     * 经纪人总数
     * @return EmpSum 经纪人总数
     */
    public Integer getEmpsum() {
        return empsum;
    }

    /**
     * 经纪人总数
     * @param empsum 经纪人总数
     */
    public void setEmpsum(Integer empsum) {
        this.empsum = empsum;
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