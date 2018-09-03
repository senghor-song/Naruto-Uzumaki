package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**  
 * @Description: 区县实体
 * @author 宋高俊  
 * @date 2018年6月27日 上午8:57:19 
 */ 
public class District implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 系统->区县
     */
    private String id;

    /**
     * 区县编号
     */
    private Integer districtno;

    /**
     * 区县名
     */
    private String district;

    /**
     * 位置经度
     */
    private Double latitude;

    /**
     * 位置维度
     */
    private Double longitude;

    /**
     * 所属城市ID
     */
    private String cityid;

    /**
     * （辅助）
     */
    private String city;

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
     * 系统->区县
     * @return ID 系统->区县
     */
    public String getId() {
        return id;
    }

    /**
     * 系统->区县
     * @param id 系统->区县
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 区县编号
     * @return DistrictNo 区县编号
     */
    public Integer getDistrictno() {
        return districtno;
    }

    /**
     * 区县编号
     * @param districtno 区县编号
     */
    public void setDistrictno(Integer districtno) {
        this.districtno = districtno;
    }

    /**
     * 区县名
     * @return District 区县名
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 区县名
     * @param district 区县名
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * 位置经度
     * @return Latitude 位置经度
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 位置经度
     * @param latitude 位置经度
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * 位置维度
     * @return Longitude 位置维度
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 位置维度
     * @param longitude 位置维度
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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