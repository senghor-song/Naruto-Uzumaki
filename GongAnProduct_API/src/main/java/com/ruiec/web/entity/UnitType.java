package com.ruiec.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 单位关联警种数据的关联实体
 * 
 * @author Senghor<br>
 * @date 2017年12月13日 上午10:57:23
 */
@Entity
@Table(name = "T_SYS_UNIT_TYPE")
public class UnitType extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 关联单位 */
	private Integer unitId;
	/** 关联字典数据 */
	private Integer dictionaryId;

	/** 关联单位 */
	@Column(name = "UNIT_ID")
	public Integer getUnitId() {
		return unitId;
	}

	/** 关联单位 */
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	/** 关联字典数据 */
	@Column(name = "DICTIONARY_ID")
	public Integer getDictionaryId() {
		return this.dictionaryId;
	}

	/** 关联字典数据 */
	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
}