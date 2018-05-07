package com.ruiec.web.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 字典类型实体
 * 
 * @author qinzhitian<br>
 * @date 2017年11月29日 上午10:09:44
 */
@Entity
@Table(name = "T_SYS_DICTIONARY_TYPE", uniqueConstraints = @UniqueConstraint(columnNames = "ITEMNAME"))
public class DictionaryType extends BaseEntity {

	private static final long serialVersionUID = -1611071783001453750L;

	/** 字典类型名称 */
	private String itemName;
	/** 描述 */
	private String describe;
	/** 排序 */
	private Integer sort;
	/** 是否启用 */
	private Integer isUse;
	/** 字典类型别名 */
	private String itemCode;
	/** 字典数据 */
	private List<Dictionary> dictionarys;

	/** 字典类型名称 */
	@Column(name = "ITEMNAME", unique = true, nullable = false)
	public String getItemName() {
		return this.itemName;
	}

	/** 字典类型名称 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/** 描述 */
	@Column(name = "DESCRIBE")
	public String getDescribe() {
		return this.describe;
	}

	/** 描述 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	/** 排序 */
	@Column(name = "SORT", nullable = false, precision = 22, scale = 0)
	public Integer getSort() {
		return this.sort;
	}

	/** 排序 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/** 是否启用 */
	@Column(name = "IS_USE", nullable = false, precision = 22, scale = 0)
	public Integer getisUse() {
		return this.isUse;
	}

	/** 是否启用 */
	public void setisUse(Integer isUse) {
		this.isUse = isUse;
	}

	/** 字典数据 */
	@OneToMany(fetch = FetchType.LAZY)
	public List<Dictionary> getDictionarys() {
		return dictionarys;
	}

	/** 字典数据 */
	public void setDictionarys(List<Dictionary> dictionarys) {
		this.dictionarys = dictionarys;
	}

	/** 字典类型别名 */
	@Column(name = "ITEMCODE", unique = true, nullable = true)
	public String getItemCode() {
		return itemCode;
	}

	/** 字典类型别名 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

}