package com.ruiec.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 警员信息实体
 * 
 * @author 陈靖原<br>
 * @date 2017年11月28日 下午5:03:52
 */
@Entity
@Table(name = "T_SYS_USER")
public class User extends BaseEntity {

	private static final long serialVersionUID = -3576741164920192840L;
	
	/** 警员姓名 */
	private String policeName;
	/** 性别 */
	private String sex;
	/** 级别 */
	private String grade;
	/** 电话 */
	private String phone;
	/** 身份证号 */
	private String idCard;
	/** 警号 */
	private String policeNumber;
	/** 保留关键字1 */
	private String reservedKeywordsOne;
	/** 保留关键字2 */
	private String reservedKeywordsTwo;
	/** 密码 */
	private String password;
	/** 阅读过的公告*/
	private String unreadNotice;
	/** 是否在线 1.在线,0.离线*/
	private String isOnline;
	/** 关联单位(多对一) */
	private Integer unitId;
	/** 重点人表集合(关联重点人表) */

	/** 警员姓名 */
	@Column(name = "POLICE_NAME", nullable = false)
	public String getPoliceName() {
		return this.policeName;
	}

	/** 警员姓名 */
	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	/** 性别 */
	@Column(name = "SEX", nullable = false, length = 20)
	public String getSex() {
		return this.sex;
	}

	/** 性别 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/** 级别 */
	@Column(name = "GRADE", length = 100)
	public String getGrade() {
		return this.grade;
	}

	/** 级别 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/** 电话 */
	@Column(name = "PHONE")
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

	/** 警号 */
	@Column(name = "POLICE_NUMBER")
	public String getPoliceNumber() {
		return this.policeNumber;
	}

	/** 警号 */
	public void setPoliceNumber(String policeNumber) {
		this.policeNumber = policeNumber;
	}

	/** 保留关键字1 */
	@Column(name = "RESERVED_KEYWORDS_ONE")
	public String getReservedKeywordsOne() {
		return this.reservedKeywordsOne;
	}

	/** 保留关键字1 */
	public void setReservedKeywordsOne(String reservedKeywordsOne) {
		this.reservedKeywordsOne = reservedKeywordsOne;
	}

	/** 保留关键字2 */
	@Column(name = "RESERVED_KEYWORDS_TWO")
	public String getReservedKeywordsTwo() {
		return this.reservedKeywordsTwo;
	}

	/** 保留关键字2 */
	public void setReservedKeywordsTwo(String reservedKeywordsTwo) {
		this.reservedKeywordsTwo = reservedKeywordsTwo;
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

	/** 阅读过的公告*/
	@Column(name = "UNREAD_NOTICE")
	public String getUnreadNotice() {
		return unreadNotice;
	}
	
	/** 阅读过的公告*/
	public void setUnreadNotice(String unreadNotice) {
		this.unreadNotice = unreadNotice;
	}
	
	/** 是否在线*/
	@Column(name = "IS_ONLINE")
	public String getIsOnline() {
		return isOnline;
	}
	
	/** 是否在线*/
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}

	/** 单位id */
	@Column(name = "UNIT_ID")
	public Integer getUnitId() {
		return unitId;
	}

	/** 单位id */
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

}