package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 小区发现实体
 */
public class EstateFind implements Serializable {
	/** 城市 */
	private City city;

	/** 管理员 */
	private Staff staff;
	
    public Staff getStaff() {
		return staff;
	}

	/** 经纪人 */
	private Employee employee;
	
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

    /** 租售->小区发现 */
    private String id;

    /** 城市ID */
    private String cityid;

    /** 创建经纪ID */
    private String empid;

    /** 小区名 */
    private String estate;

    /** 创建时间 */
    private Date createtime;

    /** 来源类型 */
    private String type;

    /** 中心点经度 */
    private Double lon;

    /** 中心点经度 */
    private Double lat;

    /** 处理人 */
    private String disposestaff;

    /** 处理意见 */
    private String disposeopinion;

    /** 处理时间 */
    private Date disposetime;

    /** 备注 */
    private String remark;

    /**
     * EstateFind
     */
    private static final long serialVersionUID = 1L;

    /**
     * 租售->小区发现
     * @return ID 租售->小区发现
     */
    public String getId() {
        return id;
    }

    /**
     * 租售->小区发现
     * @param id 租售->小区发现
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
     * 创建经纪ID
     * @return EmpID 创建经纪ID
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * 创建经纪ID
     * @param empid 创建经纪ID
     */
    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
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
     * 来源类型
     * @return Type 来源类型
     */
    public String getType() {
        return type;
    }

    /**
     * 来源类型
     * @param type 来源类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 中心点经度
     * @return Lon 中心点经度
     */
    public Double getLon() {
        return lon;
    }

    /**
     * 中心点经度
     * @param lon 中心点经度
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * 中心点经度
     * @return Lat 中心点经度
     */
    public Double getLat() {
        return lat;
    }

    /**
     * 中心点经度
     * @param lat 中心点经度
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * 处理人
     * @return DisposeStaff 处理人
     */
    public String getDisposestaff() {
        return disposestaff;
    }

    /**
     * 处理人
     * @param disposestaff 处理人
     */
    public void setDisposestaff(String disposestaff) {
        this.disposestaff = disposestaff == null ? null : disposestaff.trim();
    }

    /**
     * 处理意见
     * @return DisposeOpinion 处理意见
     */
    public String getDisposeopinion() {
        return disposeopinion;
    }

    /**
     * 处理意见
     * @param disposeopinion 处理意见
     */
    public void setDisposeopinion(String disposeopinion) {
        this.disposeopinion = disposeopinion == null ? null : disposeopinion.trim();
    }

    /**
     * 处理时间
     * @return DisposeTime 处理时间
     */
    public Date getDisposetime() {
        return disposetime;
    }

    /**
     * 处理时间
     * @param disposetime 处理时间
     */
    public void setDisposetime(Date disposetime) {
        this.disposetime = disposetime;
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