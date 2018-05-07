package com.ruiec.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 登录日志实体
 * 
 * @author qinzhitian<br>
 * @date 2017年11月29日 上午9:20:04
 */
@Entity
@Table(name = "T_SYS_LOGIN_LOG")
public class LoginLog extends BaseEntity {

	private static final long serialVersionUID = -3266533769937387912L;
	/** 操作用户 */
	private String userName;
	/** 操作单位名称 */
	private String unitName;
	/** IP地址 */
	private String ip;
	/** 备注 */
	private String remark;

	/** 操作用户 */
	@Column(name = "USERNAME", nullable = false)
	public String getUserName() {
		return this.userName;
	}

	/** 操作用户 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** 操作单位名称 */
	@Column(name = "UNIT_NAME", nullable = false)
	public String getUnitName() {
		return this.unitName;
	}

	/** 操作单位名称 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/** IP地址 */
	@Column(name = "IP")
	public String getIp() {
		return this.ip;
	}

	/** IP地址 */
	public void setIp(String ip) {
		this.ip = ip;
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

}