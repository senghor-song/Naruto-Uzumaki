package com.ruiec.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 布控预警表
 * 
 * @author Senghor<br>
 * @date 2018年1月6日 下午5:39:39
 */
@Entity
@Table(name = "T_COR_MONITOR_ALARM")
public class MonitorAlarm extends BaseEntity {

	private static final long serialVersionUID = 1600468030412366775L;
	/** 身份证号 */
	private String idCard;
	/** 预警级别 */
	private Integer alarmLevel;
	/** 轨迹类型 */
	private Integer dynamicInfoTypeId;
	/** 轨迹ID */
	private Integer dynamicInfoId;
	/** 签收人 */
	private Integer signUserId;
	/** 签收状态1=未签收2=已签收 */
	private Integer signStatus;
	/** 预警派出所ID */
	private Integer alarmUnitId;
	/** 预警详情 */
	private String remark;
	/** 签收时间 */
	private Date signTime;
	/** 反馈时间 */
	private Date feedbackTime;
	/** 出发地 */
	private String departure;
	/** 目的地 */
	private String destination;
	/** 布控人 */
	private Monitor monitor;

	/** 身份证号 */
	@Column(name = "ID_CARD")
	public String getIdCard() {
		return this.idCard;
	}

	/** 身份证号 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/** 预警级别 */
	@Column(name = "ALARM_LEVEL", precision = 11, scale = 0)
	public Integer getAlarmLevel() {
		return this.alarmLevel;
	}

	/** 预警级别 */
	public void setAlarmLevel(Integer alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	/** 轨迹类型 */
	@Column(name = "DYNAMIC_INFO_TYPE_ID", precision = 11, scale = 0)
	public Integer getDynamicInfoTypeId() {
		return this.dynamicInfoTypeId;
	}

	/** 轨迹类型 */
	public void setDynamicInfoTypeId(Integer dynamicInfoTypeId) {
		this.dynamicInfoTypeId = dynamicInfoTypeId;
	}

	/** 轨迹ID */
	@Column(name = "DYNAMIC_INFO_ID", precision = 11, scale = 0)
	public Integer getDynamicInfoId() {
		return this.dynamicInfoId;
	}

	/** 轨迹ID */
	public void setDynamicInfoId(Integer dynamicInfoId) {
		this.dynamicInfoId = dynamicInfoId;
	}

	/** 签收人 */
	@Column(name = "SIGN_USER_ID", precision = 11, scale = 0)
	public Integer getSignUserId() {
		return this.signUserId;
	}

	/** 签收人 */
	public void setSignUserId(Integer signUserId) {
		this.signUserId = signUserId;
	}

	/** 签收状态 */
	@Column(name = "SIGN_STATUS", precision = 11, scale = 0)
	public Integer getSignStatus() {
		return this.signStatus;
	}

	/** 签收状态 */
	public void setSignStatus(Integer signStatus) {
		this.signStatus = signStatus;
	}

	/** 预警派出所ID */
	@Column(name = "ALARM_UNIT_ID")
	public Integer getAlarmUnitId() {
		return alarmUnitId;
	}

	/** 预警派出所ID */
	public void setAlarmUnitId(Integer alarmUnitId) {
		this.alarmUnitId = alarmUnitId;
	}

	/** 预警详情 */
	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	/** 预警详情 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/** 签收时间 */
	@Column(name = "SIGN_TIME", length = 7)
	public Date getSignTime() {
		return this.signTime;
	}

	/** 签收时间 */
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	/** 反馈时间 */
	@Column(name = "FEEDBACK_TIME", length = 7)
	public Date getFeedbackTime() {
		return this.feedbackTime;
	}

	/** 反馈时间 */
	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	/** 出发地 */
	@Column(name = "DEPARTURE")
	public String getDeparture() {
		return this.departure;
	}

	/** 出发地 */
	public void setDeparture(String departure) {
		this.departure = departure;
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

	/** 布控人 */
	@JoinColumn(name = "MONITOR_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	public Monitor getMonitor() {
		return monitor;
	}

	/** 布控人 */
	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

}