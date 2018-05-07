/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 数据字典类型数据
 * 
 * @author Senghor<br>
 * @date 2017年11月28日 下午3:54:37
 */
@Entity
@Table(name = "T_SYS_DICTIONARY", uniqueConstraints = @UniqueConstraint(columnNames = "ITEMNAME"))
public class Dictionary extends BaseEntity {

	private static final long serialVersionUID = -1686170097599002097L;

	/** 字典名称 */
	@NotBlank
	private String itemName;

	/** 字典值 */
	private String itemValue;

	/** 字典排序 */
	@NotBlank
	private Integer sortCode;

	/** 字典描述 */
	@NotBlank
	private String description;

	/** 能否添加下级 */
	@NotBlank
	private Integer isDef;

	/** 父级ID */
	private Integer parentId;
	/** 所属字典类型ID */
	// private Integer dataItemId;

	/** 字典类型实体 */
	private DictionaryType dictionaryType;

	public Dictionary() {
	}

	/** 字典名称 */
	@Column(name = "ITEMNAME", unique = true, nullable = false)
	public String getItemName() {
		return this.itemName;
	}

	/** 字典名称 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/** 字典值 */
	@Column(name = "ITEMVALUE", nullable = true)
	public String getItemValue() {
		return this.itemValue;
	}

	/** 字典值 */
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	/** 字典排序 */
	@Column(name = "SORTCODE", nullable = false, precision = 22, scale = 0)
	public Integer getSortCode() {
		return this.sortCode;
	}

	/** 字典排序 */
	public void setSortCode(Integer sortCode) {
		this.sortCode = sortCode;
	}

	/** 字典描述 */
	@Column(name = "DESCRIPTION", nullable = false)
	public String getDescription() {
		return this.description;
	}

	/** 字典描述 */
	public void setDescription(String description) {
		this.description = description;
	}

	/** 能否添加下级 */
	@Column(name = "ISDEF", nullable = false, precision = 22, scale = 0)
	public Integer getIsDef() {
		return this.isDef;
	}

	/** 能否添加下级 */
	public void setIsDef(Integer isDef) {
		this.isDef = isDef;
	}

	/** 父级ID */
	@Column(name = "PARENTID", precision = 22, scale = 0)
	public Integer getParentId() {
		return this.parentId;
	}

	/** 父级ID */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/** 所属字典类型ID */
	/*
	 * @Column(name="DATAITEMID", nullable=false, precision=22, scale=0) public
	 * Integer getDataItemId() { return this.dataItemId; }
	 *//** 所属字典类型ID */
	/*
	 * public void setDataItemId(Integer dataItemId) { this.dataItemId =
	 * dataItemId; }
	 */

	/** 字典类型实体 */
	@ManyToOne
	@JoinColumn(name = "DATAITEMID")
	public DictionaryType getDictionaryType() {
		return dictionaryType;
	}

	/** 字典类型实体 */
	public void setDictionaryType(DictionaryType dictionaryType) {
		this.dictionaryType = dictionaryType;
	}
}