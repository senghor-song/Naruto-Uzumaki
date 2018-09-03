package com.xiaoyi.ssm.dto;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 个人操作房源的请求参数
 * @author 宋高俊
 * @date 2018年6月25日 下午7:28:50
 */
public class MassPersonDto {
	/** 查询类型(0=出售,1=出租,2=求购,3=求租) */
	private int postType;
	/** 区域id */
	private String districtNa;
	/** 区域名称 */
	private String districtNas;
	/** 区域多选数组 */
	private String[] districtNasList;
	/** 片区id */
	private String areaNa;
	/** 片区名称 */
	private String areaNas;
	/** 片区多选数组 */
	private String[] areaNasList;

	/** 类型 */
	private String houseType;
	/** 居室 */
	private Integer roomType;
	/** 网站 */
	private String webName;
	/** 网站多选 */
	private String webNames;
	/** 区域多选数组 */
	private String[] webNameList;
	/** 面积 */
	private String houseArea;
	/** 最小面积 */
	private Integer minHouseArea;
	/** 最大面积 */
	private Integer maxHouseArea;
	/** 最小采集时间 */
	private String startTime;
	/** 最大采集时间 */
	private String endTime;
	/** 最小价格 */
	private Integer minPrice;
	/** 最大价格 */
	private Integer maxPrice;
	/** 显示号码房源 */
	private Integer phoneFilter;
	/** 过滤中介房源 */
	private Integer agentFilter;
	/** 过滤重复房源 */
	private Integer repeatTag;
	/** 数据来源 */
	private String postData;
	/** 查询条件 */
	private String place;
	/** 是否默认 */
	private int isDefault;
	/** 经纪人ID */
	private String empId;
	/** 是否是收藏列表 */
	private int cust;
	/** 黑民单数组 */
	private String[] blacklist;

	public String[] getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(String[] blacklist) {
		this.blacklist = blacklist;
	}

	public int getCust() {
		return cust;
	}

	public void setCust(int cust) {
		this.cust = cust;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public int getPostType() {
		return postType;
	}

	public void setPostType(int postType) {
		this.postType = postType;
	}

	public String getDistrictNa() {
		return districtNa;
	}

	public void setDistrictNa(String districtNa) {
		this.districtNa = districtNa;
	}

	public String getDistrictNas() {
		return districtNas;
	}

	public void setDistrictNas(String districtNas) {
		this.districtNas = districtNas;
	}

	public String[] getDistrictNasList() {
		return districtNasList;
	}

	public void setDistrictNasList(String[] districtNasList) {
		this.districtNasList = districtNasList;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public Integer getRoomType() {
		return roomType;
	}

	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public String getWebNames() {
		return webNames;
	}

	public void setWebNames(String webNames) {
		this.webNames = webNames;
	}

	public String[] getWebNameList() {
		return webNameList;
	}

	public void setWebNameList(String[] webNameList) {
		this.webNameList = webNameList;
	}

	public String getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}

	public Integer getMinHouseArea() {
		return minHouseArea;
	}

	public void setMinHouseArea(Integer minHouseArea) {
		this.minHouseArea = minHouseArea;
	}

	public Integer getMaxHouseArea() {
		return maxHouseArea;
	}

	public void setMaxHouseArea(Integer maxHouseArea) {
		this.maxHouseArea = maxHouseArea;
	}

	public String getStartTime() {
		if (!StringUtils.isBlank(startTime)) {
			startTime = startTime + " 00:00:00";
		}
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		if (!StringUtils.isBlank(endTime)) {
			endTime = endTime + " 23:59:59";
		}
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Integer getPhoneFilter() {
		return phoneFilter;
	}

	public void setPhoneFilter(Integer phoneFilter) {
		this.phoneFilter = phoneFilter;
	}

	public Integer getAgentFilter() {
		return agentFilter;
	}

	public void setAgentFilter(Integer agentFilter) {
		this.agentFilter = agentFilter;
	}

	public Integer getRepeatTag() {
		return repeatTag;
	}

	public void setRepeatTag(Integer repeatTag) {
		this.repeatTag = repeatTag;
	}

	public String getPostData() {
		return postData;
	}

	public void setPostData(String postData) {
		this.postData = postData;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getAreaNa() {
		return areaNa;
	}

	public void setAreaNa(String areaNa) {
		this.areaNa = areaNa;
	}

	public String getAreaNas() {
		return areaNas;
	}

	public void setAreaNas(String areaNas) {
		this.areaNas = areaNas;
	}

	public String[] getAreaNasList() {
		return areaNasList;
	}

	public void setAreaNasList(String[] areaNasList) {
		this.areaNasList = areaNasList;
	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

}
