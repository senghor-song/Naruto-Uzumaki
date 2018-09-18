package com.xiaoyi.ssm.dto;

import java.io.Serializable;

/**  
 * @Description: 地图找房查询条件
 * @author 宋高俊  
 * @date 2018年8月15日 上午11:35:17 
 */ 
public class WebsiteSearchMapDto implements Serializable {
	
	/**  */
	private static final long serialVersionUID = 1L;
	/** 数据ID */
	private String id;
	/** 城市 */
	private String city;
	/** 地图显示等级 */
	private Integer level;
	/** 房源类型 */
	private Integer type;
	/** 最小经度 */
	private double startLng;
	/** 最小纬度 */
	private double startLat;
	/** 最大经度 */
	private double endLng;
	/** 最大纬度 */
	private double endLat;
	
	public double getStartLng() {
		return startLng;
	}
	public void setStartLng(double startLng) {
		this.startLng = startLng;
	}
	public double getStartLat() {
		return startLat;
	}
	public void setStartLat(double startLat) {
		this.startLat = startLat;
	}
	public double getEndLng() {
		return endLng;
	}
	public void setEndLng(double endLng) {
		this.endLng = endLng;
	}
	public double getEndLat() {
		return endLat;
	}
	public void setEndLat(double endLat) {
		this.endLat = endLat;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
