package com.ruiec.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 单位信息实体
 * 
 * @author 陈靖原<br>
 * @date 2017年11月28日 下午5:04:27
 */
@Entity
@Table(name = "T_SYS_UNIT")
@DynamicUpdate(true)
public class Unit extends BaseEntity {

	private static final long serialVersionUID = -3359149305563100301L;
	/** 单位名称 */
	@NotBlank
	private String unitName;
	/** 单位级别 */
	private String unitRank;
	/** 父ID */
	private Integer parentId;
	/** 是否警种部门 */
	@NotNull
	private Integer isPoliceSection;
	/** 警钟所属父ID */
	private Integer policeTypesParentId;
	/** 省级编码 */
	private String provinceCode;
	/** 市级编码 */
	private String cityCode;
	/** 区级编码 */
	private String areaCode;
	/** 镇级编码 */
	private String townCode;
	/** 其他编码1 */
	private String other1Code;
	/** 其他编码2 */
	private String other2Code;

	/** 单位名称 */
	@Column(name = "UNIT_NAME", nullable = false, length = 100)
	public String getUnitName() {
		return this.unitName;
	}

	/** 单位名称 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/** 单位级别 */
	@Column(name = "UNIT_RANK", nullable = false, length = 100)
	public String getUnitRank() {
		return this.unitRank;
	}

	/** 单位级别 */
	public void setUnitRank(String unitRank) {
		this.unitRank = unitRank;
	}

	/** 父ID */
	@Column(name = "PARENT_ID", precision = 22, scale = 0)
	public Integer getParentId() {
		return this.parentId;
	}

	/** 父ID */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/** 是否警种部门 */
	@Column(name = "IS_POLICE_SECTION", nullable = false, precision = 22, scale = 0)
	public Integer getIsPoliceSection() {
		return this.isPoliceSection;
	}

	/** 是否警种部门 */
	public void setIsPoliceSection(Integer isPoliceSection) {
		this.isPoliceSection = isPoliceSection;
	}

	/** 警种所属父ID */
	@Column(name = "POLICE_TYPES_PARENT_ID", precision = 22, scale = 0)
	public Integer getPoliceTypesParentId() {
		return this.policeTypesParentId;
	}

	/** 警种所属父ID */
	public void setPoliceTypesParentId(Integer policeTypesParentId) {
		this.policeTypesParentId = policeTypesParentId;
	}

	/** 省级编码 */
	@Column(name = "PROVINCE_CODE", precision = 22, scale = 0)
	public String getProvinceCode() {
		return this.provinceCode;
	}

	/** 省级编码 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/** 市级编码 */
	@Column(name = "CITY_CODE", precision = 22, scale = 0)
	public String getCityCode() {
		return this.cityCode;
	}

	/** 市级编码 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/** 区级编码 */
	@Column(name = "AREA_CODE", precision = 22, scale = 0)
	public String getAreaCode() {
		return this.areaCode;
	}

	/** 区级编码 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/** 镇级编码 */
	@Column(name = "TOWN_CODE", precision = 22, scale = 0)
	public String getTownCode() {
		return this.townCode;
	}

	/** 镇级编码 */
	public void setTownCode(String townCode) {
		this.townCode = townCode;
	}

	/** 其他编码1 */
	@Column(name = "OTHER1_CODE", precision = 22, scale = 0)
	public String getOther1Code() {
		return this.other1Code;
	}

	/** 其他编码1 */
	public void setOther1Code(String other1Code) {
		this.other1Code = other1Code;
	}

	/** 其他编码2 */
	@Column(name = "OTHER2_CODE", precision = 22, scale = 0)
	public String getOther2Code() {
		return this.other2Code;
	}

	/** 其他编码2 */
	public void setOther2Code(String other2Code) {
		this.other2Code = other2Code;
	}

	@Override
	public String toString() {
		return "Unit [unitName=" + unitName + ", unitRank=" + unitRank + ", parentId=" + parentId + ", isPoliceSection=" + isPoliceSection
				+ ", policeTypesParentId=" + policeTypesParentId + ", provinceCode=" + provinceCode + ", cityCode=" + cityCode + ", areaCode=" + areaCode
				+ ", townCode=" + townCode + ", other1Code=" + other1Code + ", other2Code=" + other2Code + "]";
	}

	/**
	 * 返回单位编码
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月16日 下午4:48:12
	 */
	public static String getUnitCode(Unit unitCode) {
		if (unitCode == null) {
			return null;
		}
		return unitCode.getProvinceCode() + unitCode.getCityCode() + unitCode.getAreaCode() + unitCode.getTownCode() + unitCode.getOther1Code()
				+ unitCode.getOther2Code();
	}

}