package com.xiaoyi.ssm.dto;

/**  
 * @Description: 官网房源检索条件
 * @author 宋高俊  
 * @date 2018年8月15日 上午10:39:34 
 */ 
public class WebsitePropertyDto {

	/** 类型0=二手房,新房1=出租 */
	private Integer massPropertyType;
	/** 区域 */
	private String districtid;
	/** 售价最小 */
	private String startPrice;
	/** 售价最大 */
	private String endPrice;
	/** 面积最小 */
	private String startSquarej;
	/** 面积最大 */
	private String endSquarej;
	/** 房型数 */
	private String room;
	/** 建造年份 */
	private Integer year;
	/** 房源类型0=住房 */
	private Integer propertyType;
	/** 最小楼层 */
	private Integer startFloor;
	/** 最高楼层 */
	private Integer endFloor;
	/** 朝向 */
	private String direction;
	/** 装修 */
	private String decoration;
	/** 排序方式0=默认1=最新时间 */
	private Integer orderby;
	
	public String getDistrictid() {
		return districtid;
	}
	public void setDistrictid(String districtid) {
		this.districtid = districtid;
	}
	public Integer getMassPropertyType() {
		return massPropertyType;
	}
	public void setMassPropertyType(Integer massPropertyType) {
		this.massPropertyType = massPropertyType;
	}
	public String getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(String startPrice) {
		this.startPrice = startPrice;
	}
	public String getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(String endPrice) {
		this.endPrice = endPrice;
	}
	public String getStartSquarej() {
		return startSquarej;
	}
	public void setStartSquarej(String startSquarej) {
		this.startSquarej = startSquarej;
	}
	public String getEndSquarej() {
		return endSquarej;
	}
	public void setEndSquarej(String endSquarej) {
		this.endSquarej = endSquarej;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(Integer propertyType) {
		this.propertyType = propertyType;
	}
	public Integer getStartFloor() {
		return startFloor;
	}
	public void setStartFloor(Integer startFloor) {
		this.startFloor = startFloor;
	}
	public Integer getEndFloor() {
		return endFloor;
	}
	public void setEndFloor(Integer endFloor) {
		this.endFloor = endFloor;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getDecoration() {
		return decoration;
	}
	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}
	public Integer getOrderby() {
		return orderby;
	}
	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}
	
}
