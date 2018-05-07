package com.ruiec.web.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

@Entity
@Table(name = "T_SYS_USER_UNIT")
public class UserUnit extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/** 用户 */
	private User user;
	/** 部门 */
	private Unit unit;
	
	/** 用户 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USER_ID")
	public User getUser() {
		return user;
	}
	/** 用户 */
	public void setUser(User user) {
		this.user = user;
	}
	/** 部门 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="UNIT_ID")
	public Unit getUnit() {
		return unit;
	}

	/** 部门 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}


}