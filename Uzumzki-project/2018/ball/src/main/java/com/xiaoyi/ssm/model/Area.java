package com.xiaoyi.ssm.model;

import java.io.Serializable;

/**
 * 片区表实体
 */
public class Area implements Serializable {
    /** 系统->片区 */
    private String id;

    /** 片区编号 */
    private Integer areano;

    /** 片区名 */
    private String area;

    /** 所属区县ID */
    private String districtid;

    /** 所属城市ID */
    private String cityid;

    /**
     * Area
     */
    private static final long serialVersionUID = 1L;

    /**
     * 系统->片区
     * @return ID 系统->片区
     */
    public String getId() {
        return id;
    }

    /**
     * 系统->片区
     * @param id 系统->片区
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 片区编号
     * @return AreaNo 片区编号
     */
    public Integer getAreano() {
        return areano;
    }

    /**
     * 片区编号
     * @param areano 片区编号
     */
    public void setAreano(Integer areano) {
        this.areano = areano;
    }

    /**
     * 片区名
     * @return Area 片区名
     */
    public String getArea() {
        return area;
    }

    /**
     * 片区名
     * @param area 片区名
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * 所属区县ID
     * @return DistrictID 所属区县ID
     */
    public String getDistrictid() {
        return districtid;
    }

    /**
     * 所属区县ID
     * @param districtid 所属区县ID
     */
    public void setDistrictid(String districtid) {
        this.districtid = districtid == null ? null : districtid.trim();
    }

    /**
     * 所属城市ID
     * @return CityID 所属城市ID
     */
    public String getCityid() {
        return cityid;
    }

    /**
     * 所属城市ID
     * @param cityid 所属城市ID
     */
    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }
}