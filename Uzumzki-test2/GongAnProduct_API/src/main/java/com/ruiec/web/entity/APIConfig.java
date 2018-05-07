package com.ruiec.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 轨迹数据API接口配置
 * 
 * @author bingo<br>
 * @date 2017年12月26日 下午3:24:02
 */
@Entity
@Table(name = "T_SYS_API_CONFIG")
public class APIConfig extends BaseEntity {

	private static final long serialVersionUID = 1187968379222005393L;
	/** API接口地址 */
	private String apiUrl;
	/** 口令 */
	private String token;
	/** 开始标记 */
	private String start;
	/** 偏移量 */
	private String offset;
	/** 备注 */
	private String remark;
	/** 表ID */
	private String tableId;
	/** 条件筛选 */
	private String rowFilter;
	/** 开关 */
	private Boolean powerSwitch;
	/** 轨迹名称 */
	private String name;

	/** API接口地址 */
	@Column(name = "API_URL")
	public String getApiUrl() {
		return apiUrl;
	}

	/** API接口地址 */
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	/** 口令 */
	@Column(name = "TOKEN")
	public String getToken() {
		return token;
	}

	/** 口令 */
	public void setToken(String token) {
		this.token = token;
	}

	/** 开始标记 */
	@Column(name = "START_MARK")
	public String getStart() {
		return start;
	}

	/** 开始标记 */
	public void setStart(String start) {
		this.start = start;
	}

	/** 偏移量 */
	@Column(name = "OFFSET")
	public String getOffset() {
		return offset;
	}

	/** 偏移量 */
	public void setOffset(String offset) {
		this.offset = offset;
	}

	/** 备注 */
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	/** 备注 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/** 表ID */
	@Column(name = "TABLE_ID")
	public String getTableId() {
		return tableId;
	}

	/** 表ID */
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	/** 条件筛选 */
	@Column(name = "ROW_FILTER")
	public String getRowFilter() {
		return rowFilter;
	}

	/** 条件筛选 */
	public void setRowFilter(String rowFilter) {
		this.rowFilter = rowFilter;
	}

	/** 开关 */
	@Column(name = "POWER_SWITCH")
	public Boolean getPowerSwitch() {
		return powerSwitch;
	}

	/** 开关 */
	public void setPowerSwitch(Boolean powerSwitch) {
		this.powerSwitch = powerSwitch;
	}

	/** 轨迹名称 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	/** 轨迹名称 */
	public void setName(String name) {
		this.name = name;
	}

}
