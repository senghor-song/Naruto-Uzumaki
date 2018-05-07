package com.ruiec.web.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 预警查询条件实体
 * 
 * @author 陈靖原<br>
 * @date 2017年12月22日 上午9:24:55
 */
public class AlarmDTO implements Serializable {

	private static final long serialVersionUID = 5875862482709758377L;
	/** 人员类别 */
	Integer personType;
	/** 预警地区 */
	Integer unitId;
	/** 开始日期 */
	Date startDate;
	/** 结束日期 */
	Date endDate;
	/** 动态信息 */
	Integer[] dynamicTypeIdz;
	/** 预警级别（当天） */
	String todayAlarm;
	/** 预警级别 */
	String warningLevel;
	/** 目的地 **/
	String location;
	/** 稳控 **/
	String stableControl;
	/** 姓名 **/
	String name;
	/** 身份证 **/
	String idCard;
	/** 是否反馈（1：是，0：否） **/
	Integer isFeedback;
	/** 是否签收（1：未签收，2：已签收） **/
	Integer signStatus;

	/** 人员类别 */
	public Integer getPersonType() {
		return personType;
	}

	/** 人员类别 */
	public void setPersonType(Integer personType) {
		this.personType = personType;
	}

	/** 预警地区 */
	public Integer getUnitId() {
		return unitId;
	}

	/** 预警地区 */
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	/** 开始日期 */
	public Date getStartDate() {
		return startDate;
	}

	/** 开始日期 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/** 结束日期 */
	public Date getEndDate() {
		return endDate;
	}

	/** 结束日期 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/** 动态信息 */
	public Integer[] getDynamicTypeIdz() {
		return dynamicTypeIdz;
	}

	/** 动态信息 */
	public void setDynamicTypeIdz(Integer[] dynamicTypeIdz) {
		this.dynamicTypeIdz = dynamicTypeIdz;
	}

	/** 预警级别（当天） */
	public String getTodayAlarm() {
		return todayAlarm;
	}

	/** 预警级别（当天） */
	public void setTodayAlarm(String todayAlarm) {
		this.todayAlarm = todayAlarm;
	}

	/** 预警级别 */
	public String getWarningLevel() {
		return warningLevel;
	}

	/** 预警级别 */
	public void setWarningLevel(String warningLevel) {
		this.warningLevel = warningLevel;
	}

	/** 目的地 **/
	public String getLocation() {
		return location;
	}

	/** 目的地 **/
	public void setLocation(String location) {
		this.location = location;
	}

	/** 稳控 **/
	public String getStableControl() {
		return stableControl;
	}

	/** 稳控 **/
	public void setStableControl(String stableControl) {
		this.stableControl = stableControl;
	}

	/** 姓名 **/
	public String getName() {
		return name;
	}

	/** 姓名 **/
	public void setName(String name) {
		this.name = name;
	}

	/** 身份证 **/
	public String getIdCard() {
		return idCard;
	}

	/** 身份证 **/
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/** 是否反馈（1：是，0：否） **/
	public Integer getIsFeedback() {
		return isFeedback;
	}

	/** 是否反馈（1：是，0：否） **/
	public void setIsFeedback(Integer isFeedback) {
		this.isFeedback = isFeedback;
	}

	/** 是否签收（1：未签收，2：已签收） **/
	public Integer getSignStatus() {
		return signStatus;
	}

	/** 是否签收（1：未签收，2：已签收） **/
	public void setSignStatus(Integer signStatus) {
		this.signStatus = signStatus;
	}

}
