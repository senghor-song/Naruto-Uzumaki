package com.xiaoyi.ssm.model;

import java.io.Serializable;

/**
 * 城市表实体
 */
public class City implements Serializable {
	
	// 网球开放数量
	private Integer ball1;
	// 足球开放数量
	private Integer ball2;
	// 羽毛球开放数量
	private Integer ball3;
	// 蓝球开放数量
	private Integer ball4;
	
    public Integer getBall1() {
		return ball1;
	}

	public void setBall1(Integer ball1) {
		this.ball1 = ball1;
	}

	public Integer getBall2() {
		return ball2;
	}

	public void setBall2(Integer ball2) {
		this.ball2 = ball2;
	}

	public Integer getBall3() {
		return ball3;
	}

	public void setBall3(Integer ball3) {
		this.ball3 = ball3;
	}

	public Integer getBall4() {
		return ball4;
	}

	public void setBall4(Integer ball4) {
		this.ball4 = ball4;
	}

	/** 系统->城市 */
    private String id;

    /** 城市编号 */
    private Integer cityno;

    /** 城市名 */
    private String city;

    /** 城市名首字母 */
    private String initial;

    /** 是否热门0=否1=是 */
    private Integer hotflag;

    /** 是否地图0=否1=是 */
    private Integer mapflag;

    /** 是否开放0=否1=是 */
    private Integer cityflag;

    /** 场馆数 */
    private Integer venuesum;

    /**
     * City
     */
    private static final long serialVersionUID = 1L;

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
     * 城市名首字母
     * @return Initial 城市名首字母
     */
    public String getInitial() {
        return initial;
    }

    /**
     * 城市名首字母
     * @param initial 城市名首字母
     */
    public void setInitial(String initial) {
        this.initial = initial == null ? null : initial.trim();
    }

    /**
     * 是否热门0=否1=是
     * @return HotFlag 是否热门0=否1=是
     */
    public Integer getHotflag() {
        return hotflag;
    }

    /**
     * 是否热门0=否1=是
     * @param hotflag 是否热门0=否1=是
     */
    public void setHotflag(Integer hotflag) {
        this.hotflag = hotflag;
    }

    /**
     * 是否地图0=否1=是
     * @return MapFlag 是否地图0=否1=是
     */
    public Integer getMapflag() {
        return mapflag;
    }

    /**
     * 是否地图0=否1=是
     * @param mapflag 是否地图0=否1=是
     */
    public void setMapflag(Integer mapflag) {
        this.mapflag = mapflag;
    }

    /**
     * 是否开放0=否1=是
     * @return CityFlag 是否开放0=否1=是
     */
    public Integer getCityflag() {
        return cityflag;
    }

    /**
     * 是否开放0=否1=是
     * @param cityflag 是否开放0=否1=是
     */
    public void setCityflag(Integer cityflag) {
        this.cityflag = cityflag;
    }

    /**
     * 场馆数
     * @return VenueSum 场馆数
     */
    public Integer getVenuesum() {
        return venuesum;
    }

    /**
     * 场馆数
     * @param venuesum 场馆数
     */
    public void setVenuesum(Integer venuesum) {
        this.venuesum = venuesum;
    }
}