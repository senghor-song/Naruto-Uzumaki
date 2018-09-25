/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 重点人关系实体
 * 
 * @author Senghor<br>
 * @date 2017年11月28日 下午3:54:37
 */
@Entity
@Table(name = "T_COR_CONTROL_PERSON_RELATION", uniqueConstraints = @UniqueConstraint(columnNames = { "ID_CARD", "CONTROL_PERSON_ID" }))
public class ControlPersonRelation extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -8225115895351208576L;

	/** 姓名 */
	private String name;

	/** 电话 */
	private String phone;

	/** 身份证号 */
	private String idCard;

	/** 备注 */
	private String remark;

	/** 重点人id */
	private Integer controlPersonId;

	/** 类型id */
	private Integer typeId;

	/** 重点人id */
	private ControlPerson controlPerson;

	/** 姓名 */
	@Column(name = "NAME", nullable = false)
	public String getName() {
		return this.name;
	}

	/** 姓名 */
	public void setName(String name) {
		this.name = name;
	}

	/** 电话 */
	@Column(name = "PHONE", nullable = false)
	public String getPhone() {
		return this.phone;
	}

	/** 电话 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/** 身份证号 */
	@Column(name = "ID_CARD", nullable = false)
	public String getIdCard() {
		return this.idCard;
	}

	/** 身份证号 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/** 备注 */
	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	/** 备注 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/** 重点人id */
	@Column(name = "CONTROL_PERSON_ID", nullable = false, precision = 22, scale = 0)
	public Integer getControlPersonId() {
		return this.controlPersonId;
	}

	/** 重点人id */
	public void setControlPersonId(Integer controlPersonId) {
		this.controlPersonId = controlPersonId;
	}

	/** 类型id */
	@Column(name = "TYPE_ID", nullable = false, precision = 22, scale = 0)
	public Integer getTypeId() {
		return this.typeId;
	}

	/** 类型id */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/** 重点人id */
	@JoinColumn(name = "CONTROL_PERSON_ID", insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	public ControlPerson getControlPerson() {
		return controlPerson;
	}

	/** 重点人id */
	public void setControlPerson(ControlPerson controlPerson) {
		this.controlPerson = controlPerson;
	}
}