package com.xiaoyi.ssm.dto;

import org.apache.commons.lang3.StringUtils;

/**  
 * @Description: 房源录入传递参数对象
 * @author 宋高俊  
 * @date 2018年6月30日 下午4:33:18 
 */ 
public class HouseEnterDto {
	/** 房源ID */
	private String houseId;
	/** 录入类型0=出售1=出租 */
	private String postType;
	/** 录入数据ID */
	private String buildingIds;
	/** 城市ID */
	private String city;
	/** 房源类型 */
	private String buildingType;
	/** 查询内容 */
	private String inputStr;
	/** 基础设施 */
	private String[] basicequipList;
	/** 配套设施 */
	private String[] advequipList;
	/** 小区ID */
	private String cellCode;
	/** 合租类型 */
	private String rentType;
	/** 经纪人ID */
	private String empId;
	/** 房源来源 */
	private String sourcetype;
	/** 房源标签 */
	private String tagstip;
	/** 是否视频 */
	private String flagvideo;
	/** 是否多图 */
	private String flagmoreimage;
	/** 最小价格 */
	private Double priceMin;
	/** 最大价格 */
	private Double priceMax;
	/** 关键字 */
	private String keyword;
	/** 居室数 */
	private Integer countf;
	/** 排序方式(0=更新时间1=录入时间2=推送时间3=价格高到低4=价格低到高5=面积大到小6=面积小到大7=标签) */
	private int orderBy;
	
	/** 删除操作的执行类型0=物理删除1=数据删除 3=数据还原4=清空回收站*/
	private int deleteType;
	/** 房源状态0=发布中1回收站2=草稿箱 */
	private String isType;
	
	/** 小区名称 */
	private String estateName;
	
	/** 室内图被删除id字符串 */
	private String indoorUrls;
	/** 房型图被删除id字符串 */
	private String houseTypeUrls;
	/** 小区图被删除id字符串 */
	private String estateUrls;

	/** 室内图被删除id数组 */
	private String[] delIndoorUrls = {};
	/** 房型图被删除id数组 */
	private String[] delHouseTypeUrls = {};
	/** 小区图被删除id数组 */
	private String[] delEstateUrls = {};
	

	public String[] getDelIndoorUrls() {
		if (!StringUtils.isBlank(indoorUrls)) {
			return indoorUrls.split(",");
		}
		return delIndoorUrls;
	}

	public void setDelIndoorUrls(String[] delIndoorUrls) {
		this.delIndoorUrls = delIndoorUrls;
	}

	public String[] getDelHouseTypeUrls() {
		if (!StringUtils.isBlank(houseTypeUrls)) {
			return houseTypeUrls.split(",");
		}
		return delHouseTypeUrls;
	}

	public void setDelHouseTypeUrls(String[] delHouseTypeUrls) {
		this.delHouseTypeUrls = delHouseTypeUrls;
	}

	public String[] getDelEstateUrls() {
		if (!StringUtils.isBlank(estateUrls)) {
			return estateUrls.split(",");
		}
		return delEstateUrls;
	}

	public void setDelEstateUrls(String[] delEstateUrls) {
		this.delEstateUrls = delEstateUrls;
	}

	public String getIndoorUrls() {
		return indoorUrls;
	}

	public void setIndoorUrls(String indoorUrls) {
		this.indoorUrls = indoorUrls;
	}

	public String getHouseTypeUrls() {
		return houseTypeUrls;
	}

	public void setHouseTypeUrls(String houseTypeUrls) {
		this.houseTypeUrls = houseTypeUrls;
	}

	public String getEstateUrls() {
		return estateUrls;
	}

	public void setEstateUrls(String estateUrls) {
		this.estateUrls = estateUrls;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getEstateName() {
		return estateName;
	}

	public void setEstateName(String estateName) {
		this.estateName = estateName;
	}

	public String getIsType() {
		return isType;
	}

	public void setIsType(String isType) {
		this.isType = isType;
	}

	public int getDeleteType() {
		return deleteType;
	}

	public void setDeleteType(int deleteType) {
		this.deleteType = deleteType;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

	public String getInputStr() {
		return inputStr;
	}

	public void setInputStr(String inputStr) {
		this.inputStr = inputStr;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public String[] getBasicequipList() {
		return basicequipList;
	}

	public void setBasicequipList(String[] basicequipList) {
		this.basicequipList = basicequipList;
	}

	public String[] getAdvequipList() {
		return advequipList;
	}

	public void setAdvequipList(String[] advequipList) {
		this.advequipList = advequipList;
	}

	public String getCellCode() {
		return cellCode;
	}

	public void setCellCode(String cellCode) {
		this.cellCode = cellCode;
	}

	public String getRentType() {
		return rentType;
	}

	public void setRentType(String rentType) {
		this.rentType = rentType;
	}

	public String getSourcetype() {
		return sourcetype;
	}

	public void setSourcetype(String sourcetype) {
		this.sourcetype = sourcetype;
	}

	public String getTagstip() {
		return tagstip;
	}

	public void setTagstip(String tagstip) {
		this.tagstip = tagstip;
	}

	public String getFlagvideo() {
		return flagvideo;
	}

	public void setFlagvideo(String flagvideo) {
		this.flagvideo = flagvideo;
	}

	public String getFlagmoreimage() {
		return flagmoreimage;
	}

	public void setFlagmoreimage(String flagmoreimage) {
		this.flagmoreimage = flagmoreimage;
	}

	public Double getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(Double priceMin) {
		this.priceMin = priceMin;
	}

	public Double getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(Double priceMax) {
		this.priceMax = priceMax;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getCountf() {
		return countf;
	}

	public void setCountf(Integer countf) {
		this.countf = countf;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public String getBuildingIds() {
		return buildingIds;
	}

	public void setBuildingIds(String buildingIds) {
		this.buildingIds = buildingIds;
	}

}
