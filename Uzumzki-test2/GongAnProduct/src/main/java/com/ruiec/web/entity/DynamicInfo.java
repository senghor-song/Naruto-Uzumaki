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

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 轨迹数据实体
 * 
 * @author bingo<br>
 * @date 2017年12月22日 下午3:51:12
 */
@Entity
@Table(name="T_COR_DYNAMIC_INFO")
public class DynamicInfo extends BaseEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 4702070965362618263L;
	/** 出发地 */
	private String origin;
	/** 目的地 */
	private String destination;
	/** 出发时间/触发时间 */
	private String triggerTime;
	/** 轨迹类型 */
	private Integer type;
	/** 轨迹数据 */
	private String information;
	/** 重点人主键 */
	private Integer controlPersonId;
	/** 预警地单位 */
	private Unit alarmUnit;

	/** 获取 origin */
	public String getOrigin() {
		return origin;
	}

	/** 设置origin */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/** 获取 destination */
	public String getDestination() {
		return destination;
	}

	/** 设置destination */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/** 获取 triggerTime */
	@Column(name="TRIGGER_TIME")
	public String getTriggerTime() {
		return triggerTime;
	}

	/** 设置triggerTime */
	public void setTriggerTime(String triggerTime) {
		this.triggerTime = triggerTime;
	}

	/** 获取 type */
	public Integer getType() {
		return type;
	}

	/** 设置type */
	public void setType(Integer type) {
		this.type = type;
	}

	/** 获取 information */
	public String getInformation() {
		return information;
	}

	/** 设置information */
	public void setInformation(String information) {
		this.information = information;
	}

	/** 获取 controlPerson */
	@Column(name = "CONTROL_PERSON_ID")
	public Integer getControlPersonId() {
		return controlPersonId;
	}

	/** 设置controlPerson */
	public void setControlPersonId(Integer controlPersonId) {
		this.controlPersonId = controlPersonId;
	}
	
	/** 获取 alarmUnit */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ALARM_UNIT_ID", nullable = false)
	public Unit getAlarmUnit() {
		return alarmUnit;
	}

	/** 设置alarmUnit */
	public void setAlarmUnit(Unit alarmUnit) {
		this.alarmUnit = alarmUnit;
	}

}
