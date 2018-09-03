package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**  
 * @Description: 片区实体
 * @author 宋高俊  
 * @date 2018年6月27日 上午8:57:00 
 */ 
public class Area implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 系统->片区
     */
    private String id;

    /**
     * 片区名
     */
    private String area;

    /**
     * 片区编号
     */
    private Integer areano;

    /**
     * 所属区县ID
     */
    private String districtid;

    /**
     * (辅助)
     */
    private String district;

    /**
     * 所属城市ID
     */
    private String cityid;

    /**
     * (辅助)
     */
    private String city;

    /**
     * 中心点经度
     */
    private Double latitude;

    /**
     * 中心点维度
     */
    private Double longitude;

    /**
     * 范围坐标
     */
    private String range;

    /**
     * 总数-租
     */
    private Integer totalrent;

    /**
     * 总数-售
     */
    private Integer totalsale;

    /**
     * 总数-新盘
     */
    private Integer totaltown;

    /**
     * 三个统计计数更新时间
     */
    private Date totalrefresh;

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
     * (辅助)
     * @return District (辅助)
     */
    public String getDistrict() {
        return district;
    }

    /**
     * (辅助)
     * @param district (辅助)
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
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

    /**
     * (辅助)
     * @return City (辅助)
     */
    public String getCity() {
        return city;
    }

    /**
     * (辅助)
     * @param city (辅助)
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 中心点经度
     * @return Latitude 中心点经度
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 中心点经度
     * @param latitude 中心点经度
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * 中心点维度
     * @return Longitude 中心点维度
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 中心点维度
     * @param longitude 中心点维度
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 范围坐标
     * @return Range 范围坐标
     */
    public String getRange() {
        return range;
    }

    /**
     * 范围坐标
     * @param range 范围坐标
     */
    public void setRange(String range) {
        this.range = range == null ? null : range.trim();
    }

    /**
     * 总数-租
     * @return TotalRent 总数-租
     */
    public Integer getTotalrent() {
        return totalrent;
    }

    /**
     * 总数-租
     * @param totalrent 总数-租
     */
    public void setTotalrent(Integer totalrent) {
        this.totalrent = totalrent;
    }

    /**
     * 总数-售
     * @return TotalSale 总数-售
     */
    public Integer getTotalsale() {
        return totalsale;
    }

    /**
     * 总数-售
     * @param totalsale 总数-售
     */
    public void setTotalsale(Integer totalsale) {
        this.totalsale = totalsale;
    }

    /**
     * 总数-新盘
     * @return TotalTown 总数-新盘
     */
    public Integer getTotaltown() {
        return totaltown;
    }

    /**
     * 总数-新盘
     * @param totaltown 总数-新盘
     */
    public void setTotaltown(Integer totaltown) {
        this.totaltown = totaltown;
    }

    /**
     * 三个统计计数更新时间
     * @return TotalRefresh 三个统计计数更新时间
     */
    public Date getTotalrefresh() {
        return totalrefresh;
    }

    /**
     * 三个统计计数更新时间
     * @param totalrefresh 三个统计计数更新时间
     */
    public void setTotalrefresh(Date totalrefresh) {
        this.totalrefresh = totalrefresh;
    }
}