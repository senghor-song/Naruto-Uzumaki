package com.ruiec.web.entity;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 重点人预警实体
 * 
 * @author Senghor<br>
 * @date 2017年12月1日 上午11:00:41
 */
@Entity
@Table(name = "T_COR_CONTROL_PERSON_ALARM", uniqueConstraints = @UniqueConstraint(columnNames = { "ID_CARD", "CREATE_TIME" }))
public class ControlPersonAlarm extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** 身份证 */
	private String idCard;
	/** 预警级别 */
	private String warningLevel;
	/** 动态信息编码 */
	private Integer dynamicInfoTypeId;
	/** 动态信息主键 */
	private Integer dynamicInfoId;
	/** 动态信息名称 */
	private String dynamicInfoName;
	/** 下发状态 1.已下发 0.未下发 */
	private Integer distributeStatus;
	/** 签收人名称 */
	private String signName;
	/** 签收人ID */
	private Integer signPrikey;
	/** 签收状态 1.未签收 2.签收 */
	private Integer signStatus;
	/** 备注 */
	private String remark;
	/** 签收时间 */
	private String signTime;
	/** 反馈时间 */
	private String feedbackTime;
	/** 出发地 */
	private String origin;
	/** 目的地 */
	private String destination;
	/** 重点人主键 */
	private ControlPerson controlPerson;
	/** 预警地派出所 */
	private Integer alarmUnit;
	/** 管控责任单位 */
	private Integer controlUnit;
	/** 稳控状态 */
	private String status;
	/** 预警数量 */
	private Integer count;

	/** 身份证 */
	@Column(name = "ID_CARD", nullable = false)
	public String getIdCard() {
		return this.idCard;
	}

	/** 身份证 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/** 预警级别 */
	@Column(name = "WARNING_LEVEL", nullable = false)
	public String getWarningLevel() {
		return this.warningLevel;
	}

	/** 预警级别 */
	public void setWarningLevel(String warningLevel) {
		this.warningLevel = warningLevel;
	}

	/** 动态信息类别ID */
	@Column(name = "DYNAMIC_INFO_TYPE_ID")
	public Integer getDynamicInfoTypeId() {
		return this.dynamicInfoTypeId;
	}

	/** 动态信息类别ID */
	public void setDynamicInfoTypeId(Integer dynamicInfoTypeId) {
		this.dynamicInfoTypeId = dynamicInfoTypeId;
	}

	/** 动态信息主键 */
	@Column(name = "DYNAMIC_INFO_ID")
	public Integer getDynamicInfoId() {
		return this.dynamicInfoId;
	}

	/** 动态信息主键 */
	public void setDynamicInfoId(Integer dynamicInfoId) {
		this.dynamicInfoId = dynamicInfoId;
	}

	/** 动态信息名称 */
	@Column(name = "DYNAMIC_INFO_NAME")
	public String getDynamicInfoName() {
		return this.dynamicInfoName;
	}

	/** 动态信息名称 */
	public void setDynamicInfoName(String dynamicInfoName) {
		this.dynamicInfoName = dynamicInfoName;
	}

	/** 下发状态 */
	@Column(name = "DISTRIBUTE_STATUS", nullable = false, precision = 22, scale = 0)
	public Integer getDistributeStatus() {
		return this.distributeStatus;
	}

	/** 下发状态 */
	public void setDistributeStatus(Integer distributeStatus) {
		this.distributeStatus = distributeStatus;
	}

	/** 签收人名称 */
	@Column(name = "SIGN_NAME")
	public String getSignName() {
		return this.signName;
	}

	/** 签收人名称 */
	public void setSignName(String signName) {
		this.signName = signName;
	}

	/** 签收人ID */
	@Column(name = "SIGN_PRIKEY", precision = 22, scale = 0)
	public Integer getSignPrikey() {
		return this.signPrikey;
	}

	/** 签收人ID */
	public void setSignPrikey(Integer signPrikey) {
		this.signPrikey = signPrikey;
	}

	/** 签收状态 1.未签收 2.签收 */
	@Column(name = "SIGN_STATUS", nullable = false, precision = 22, scale = 0)
	public Integer getSignStatus() {
		return this.signStatus;
	}

	/** 签收状态 1.未签收 2.签收 */
	public void setSignStatus(Integer signStatus) {
		this.signStatus = signStatus;
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

	/** 签收时间 */
	@Column(name = "SIGN_TIME")
	public String getSignTime() {
		return this.signTime;
	}

	/** 签收时间 */
	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	/** 反馈时间 */
	@Column(name = "FEEDBACK_TIME")
	public String getFeedbackTime() {
		return this.feedbackTime;
	}

	/** 反馈时间 */
	public void setFeedbackTime(String feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	/** 出发地 */
	@Column(name = "ORIGIN")
	public String getOrigin() {
		return this.origin;
	}

	/** 出发地 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/** 目的地 */
	@Column(name = "DESTINATION")
	public String getDestination() {
		return this.destination;
	}

	/** 目的地 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/** 重点人主键 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTROL_PERSON_ID", nullable = false)
	public ControlPerson getControlPerson() {
		return controlPerson;
	}

	/** 重点人主键 */
	public void setControlPerson(ControlPerson controlPerson) {
		this.controlPerson = controlPerson;
	}

	/** 预警地派出所 */
	@Column(name = "LOCAL_UNIT_ID", nullable = false)
	public Integer getAlarmUnit() {
		return alarmUnit;
	}

	/** 预警地派出所 */
	public void setAlarmUnit(Integer alarmUnit) {
		this.alarmUnit = alarmUnit;
	}

	/** 管控责任单位 */
	@Column(name = "UNIT_ID", nullable = false)
	public Integer getControlUnit() {
		return controlUnit;
	}

	/** 管控责任单位 */
	public void setControlUnit(Integer controlUnit) {
		this.controlUnit = controlUnit;
	}

	/** 稳控状态 */
	@Transient
	public String getStatus() {
		return status;
	}

	/** 稳控状态 */
	public void setStatus(String status) {
		this.status = status;
	}

	/** 预警数量 */
	@Transient
	public Integer getCount() {
		return count;
	}

	/** 预警数量 */
	public void setCount(Integer count) {
		this.count = count;
	}

}