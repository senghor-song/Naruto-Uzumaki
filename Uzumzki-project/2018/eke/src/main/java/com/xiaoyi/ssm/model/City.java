package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**  
 * @Description: 城市实体
 * @author 宋高俊  
 * @date 2018年6月27日 上午8:57:11 
 */ 
public class City implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 系统->城市
     */
    private String id;

    /**
     * 城市名
     */
    private String city;

    /**
     * 
     */
    private String province;

    /**
     * 拼音首字母简称(sz.gz.bj.sh)
     */
    private String shortpy;

    /**
     * 地图使能
     */
    private Byte flagmap;

    /**
     * 城市编号
     */
    private Integer cityno;

    /**
     * 二手房
     */
    private Byte channelsale;

    /**
     * 租房
     */
    private Byte channelrent;

    /**
     * 新盘
     */
    private Byte channeltown;

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
     * 系统->城市
     * @return ID 系统->城市
     */
    public String getId() {
        return id;
    }

    /**
     * 系统->城市
     * @param id 系统->城市
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 城市名
     * @return City 城市名
     */
    public String getCity() {
        return city;
    }

    /**
     * 城市名
     * @param city 城市名
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 
     * @return Province 
     */
    public String getProvince() {
        return province;
    }

    /**
     * 
     * @param province 
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 拼音首字母简称(sz.gz.bj.sh)
     * @return ShortPY 拼音首字母简称(sz.gz.bj.sh)
     */
    public String getShortpy() {
        return shortpy;
    }

    /**
     * 拼音首字母简称(sz.gz.bj.sh)
     * @param shortpy 拼音首字母简称(sz.gz.bj.sh)
     */
    public void setShortpy(String shortpy) {
        this.shortpy = shortpy == null ? null : shortpy.trim();
    }

    /**
     * 地图使能
     * @return FlagMap 地图使能
     */
    public Byte getFlagmap() {
        return flagmap;
    }

    /**
     * 地图使能
     * @param flagmap 地图使能
     */
    public void setFlagmap(Byte flagmap) {
        this.flagmap = flagmap;
    }

    /**
     * 城市编号
     * @return CityNo 城市编号
     */
    public Integer getCityno() {
        return cityno;
    }

    /**
     * 城市编号
     * @param cityno 城市编号
     */
    public void setCityno(Integer cityno) {
        this.cityno = cityno;
    }

    /**
     * 二手房
     * @return ChannelSale 二手房
     */
    public Byte getChannelsale() {
        return channelsale;
    }

    /**
     * 二手房
     * @param channelsale 二手房
     */
    public void setChannelsale(Byte channelsale) {
        this.channelsale = channelsale;
    }

    /**
     * 租房
     * @return ChannelRent 租房
     */
    public Byte getChannelrent() {
        return channelrent;
    }

    /**
     * 租房
     * @param channelrent 租房
     */
    public void setChannelrent(Byte channelrent) {
        this.channelrent = channelrent;
    }

    /**
     * 新盘
     * @return ChannelTown 新盘
     */
    public Byte getChanneltown() {
        return channeltown;
    }

    /**
     * 新盘
     * @param channeltown 新盘
     */
    public void setChanneltown(Byte channeltown) {
        this.channeltown = channeltown;
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