package com.ruiec.web.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 数据源实体
 * @author qinzhitian<br>
 * @date 2017年12月18日 下午4:38:04
 */
@Entity
@Table(name = "T_SYS_DB")
public class Db extends BaseEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1066773375894983817L;

	/** 数据源地址 */
	private String url;
	/** 用户名 */
	private String userName;
	/** 密码 */
	private String password;
	/** 数据库类型（1：mysql,2：oracle） */
	private Integer type;
	/** 编码格式 */
	private String code;
	/** 数据库中的表 */
	private List<DbTable> dbTables;

	/** 数据源地址 */
	@Column(name = "URL", unique = true, nullable = false)
	public String getUrl() {
		return this.url;
	}

	/** 数据源地址 */
	public void setUrl(String url) {
		this.url = url;
	}

	/** 用户名 */
	@Column(name = "USERNAME", nullable = false)
	public String getUserName() {
		return this.userName;
	}

	/** 用户名 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** 密码 */
	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {
		return this.password;
	}

	/** 密码 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 数据库类型（1：mysql,2：oracle） */
	@Column(name = "TYPE", nullable = false, precision = 8, scale = 0)
	public Integer getType() {
		return this.type;
	}

	/** 数据库类型（1：mysql,2：oracle） */
	public void setType(Integer type) {
		this.type = type;
	}

	/** 编码格式 */
	@Column(name = "CODE")
	public String getCode() {
		return this.code;
	}

	/** 编码格式 */
	public void setCode(String code) {
		this.code = code;
	}

	/** 数据库中的表 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "db")
	public List<DbTable> getDbTables() {
		return dbTables;
	}

	/** 数据库中的表 */
	public void setDbTables(List<DbTable> dbTables) {
		this.dbTables = dbTables;
	}

}