package com.ruiec.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 数据库信息展示实体
 * 
 * @author qinzhitian<br>
 * @date 2017年12月18日 下午4:32:03
 */
@Entity
@Table(name = "T_SYS_DB_SHOW")
public class DbShow extends BaseEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4219054759087057864L;
	/** 中文名称 */
	private String name;
	/** 字段或表名称 */
	private String dbName;
	/** 父级id */
	private Integer parentId;
	/** 字段类型 (0：number；1：varchar；2：date)*/
	private Integer fieldType;
	/** 是否必须 */
	private Integer isNeed;

	/** 中文名称 */
	@Column(name = "NAME", nullable = false)
	public String getName() {
		return this.name;
	}

	/** 中文名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 字段或表名称 */
	@Column(name = "DB_NAME", nullable = false)
	public String getDbName() {
		return this.dbName;
	}

	/** 字段或表名称 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/** 父级id */
	@Column(name = "PARENTID", precision = 11, scale = 0)
	public Integer getParentId() {
		return this.parentId;
	}

	/** 父级id */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	/** 字段类型 */
	@Column(name = "FIELD_TYPE", nullable = true)
	public Integer getFieldType() {
		return fieldType;
	}
	
	/** 字段类型 */
	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}

	/** 是否必须 */
	@Column(name = "ISNEED", nullable = true)
	public Integer getIsNeed() {
		return isNeed;
	}

	/** 是否必须 */
	public void setIsNeed(Integer isNeed) {
		this.isNeed = isNeed;
	}

}