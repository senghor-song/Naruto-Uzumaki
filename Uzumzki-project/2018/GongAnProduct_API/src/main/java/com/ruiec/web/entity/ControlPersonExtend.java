package com.ruiec.web.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 重点人员扩展实体
 * 
 * @author Senghor<br>
 * @date 2017年12月1日 上午11:01:12
 */
@Entity
@Table(name = "T_COR_CONTROL_PERSON_EXTEND")
@DynamicUpdate(true)
public class ControlPersonExtend implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 所属重点人实体，一对一 */
	private ControlPerson controlPerson;
	private Integer id;
	/** 备注 */
	private String remark;
	/** 户籍地址 */
	private String householdRegisterPlace;
	/** 经常居住地 */
	private String habitualResidence;
	/** 事由 */
	@NotBlank
	@Size(min = 1, max = 200)
	private String reason;
	/** 民族 */
	@NotNull
	private Integer nation;
	/** QQ */
	private String qq;
	/** 车牌号 */
	private String plateNumber;
	/** 微信号 */
	private String wechat;
	/** 其他地址 */
	private String otherAddress;
	/** 照片 */
	@NotBlank
	private String photo;
	/** 籍贯 */
	private String nativePlace;
	/** 其他证件号 */
	private String otherDocumentNumber;
	/** 现住地区 */
	private String nowLiveArea;
	/** 户籍地区 */
	private String householdRegisterArea;
	/** 职业 */
	private String occupation;
	/** 所在社区 */
	private String community;
	/** 警综联系方式 */
	private String policeComprehensiveContactI;
	/** 出生日期 */
	private String birthDate;

	/** 所属重点人实体，一对一 */
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	// 如果不加此注解，hibernate会在数据库默认生成一条article_id属性
	public ControlPerson getControlPerson() {
		return this.controlPerson;
	}

	/** 所属重点人实体，一对一 */
	public void setControlPerson(ControlPerson controlPerson) {
		this.controlPerson = controlPerson;
	}

	@Id
	@GenericGenerator(name = "foreignKey",// 生成器名称
	strategy = "foreign",// 使用hibernate的外键策略
	parameters = @Parameter(value = "controlPerson", name = "property"))
	// 指定成员属性中的article所在类的主键为本类的主键,这里的参数属性name必须为"property"
	@GeneratedValue(generator = "foreignKey")
	// 使用上述定义的id生成器
	@Column(name = "PRIKEY")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/** 备注 */
	@Column(name = "REMARK", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	/** 备注 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/** 户籍地址 */
	@Column(name = "HOUSEHOLD_REGISTER_PLACE")
	public String getHouseholdRegisterPlace() {
		return this.householdRegisterPlace;
	}

	/** 户籍地址 */
	public void setHouseholdRegisterPlace(String householdRegisterPlace) {
		this.householdRegisterPlace = householdRegisterPlace;
	}

	/** 经常居住地 */
	@Column(name = "HABITUAL_RESIDENCE")
	public String getHabitualResidence() {
		return this.habitualResidence;
	}

	/** 经常居住地 */
	public void setHabitualResidence(String habitualResidence) {
		this.habitualResidence = habitualResidence;
	}

	/** 事由 */
	@Column(name = "REASON", length = 1000)
	public String getReason() {
		return this.reason;
	}

	/** 事由 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/** 民族 */
	@Column(name = "NATION", precision = 11, scale = 0)
	public Integer getNation() {
		return this.nation;
	}

	/** 民族 */
	public void setNation(Integer nation) {
		this.nation = nation;
	}

	/** QQ */
	@Column(name = "QQ")
	public String getQq() {
		return this.qq;
	}

	/** QQ */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/** 车牌号 */
	@Column(name = "PLATE_NUMBER")
	public String getPlateNumber() {
		return this.plateNumber;
	}

	/** 车牌号 */
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	/** 微信号 */
	@Column(name = "WECHAT")
	public String getWechat() {
		return this.wechat;
	}

	/** 微信号 */
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	/** 其他地址 */
	@Column(name = "OTHER_ADDRESS")
	public String getOtherAddress() {
		return this.otherAddress;
	}

	/** 其他地址 */
	public void setOtherAddress(String otherAddress) {
		this.otherAddress = otherAddress;
	}

	/** 照片 */
	@Column(name = "PHOTO")
	public String getPhoto() {
		return this.photo;
	}

	/** 照片 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/** 籍贯 */
	@Column(name = "NATIVE_PLACE")
	public String getNativePlace() {
		return this.nativePlace;
	}

	/** 籍贯 */
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	/** 其他证件号 */
	@Column(name = "OTHER_DOCUMENT_NUMBER")
	public String getOtherDocumentNumber() {
		return this.otherDocumentNumber;
	}

	/** 其他证件号 */
	public void setOtherDocumentNumber(String otherDocumentNumber) {
		this.otherDocumentNumber = otherDocumentNumber;
	}

	/** 现住地区 */
	@Column(name = "NOW_LIVE_AREA")
	public String getNowLiveArea() {
		return this.nowLiveArea;
	}

	/** 现住地区 */
	public void setNowLiveArea(String nowLiveArea) {
		this.nowLiveArea = nowLiveArea;
	}

	/** 户籍地区 */
	@Column(name = "HOUSEHOLD_REGISTER_AREA")
	public String getHouseholdRegisterArea() {
		return this.householdRegisterArea;
	}

	/** 户籍地区 */
	public void setHouseholdRegisterArea(String householdRegisterArea) {
		this.householdRegisterArea = householdRegisterArea;
	}

	/** 职业 */
	@Column(name = "OCCUPATION")
	public String getOccupation() {
		return this.occupation;
	}

	/** 职业 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/** 所在社区 */
	@Column(name = "COMMUNITY")
	public String getCommunity() {
		return this.community;
	}

	/** 所在社区 */
	public void setCommunity(String community) {
		this.community = community;
	}

	/** 警综联系方式 */
	@Column(name = "POLICE_COMPREHENSIVE_CONTACT_I")
	public String getPoliceComprehensiveContactI() {
		return this.policeComprehensiveContactI;
	}

	/** 警综联系方式 */
	public void setPoliceComprehensiveContactI(String policeComprehensiveContactI) {
		this.policeComprehensiveContactI = policeComprehensiveContactI;
	}

	/** 出生日期 */
	@Column(name = "BIRTH_DATE")
	public String getBirthDate() {
		return this.birthDate;
	}

	/** 出生日期 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

}