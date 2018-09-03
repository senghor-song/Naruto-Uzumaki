package com.ruiec.web.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 布控表
 * 
 * @author Senghor<br>
 * @date 2018年1月6日 下午5:39:24
 */
@Entity
@Table(name = "T_COR_MONITOR")
public class Monitor extends BaseEntity {

	private static final long serialVersionUID = 1600468030412366775L;
	/** 身份证号 */
	private String idCard;
	/** 车牌号 */
	private String licensePlateNumber;
	/** 人员照片 */
	private String photo;
	/** 布控理由 */
	private String reason;
	/** 布控时间(最长一个月) */
	private String duration;
	/** 联系民警(电话) */
	private String contactsPolice;
	/** 电话号码(预警接收人) */
	/* private String telNumber; */
	/** 布控状态(0：未审核,1：已审核,2：审核拒绝,3：初审) */
	private Integer status;
	/** 审批单位ID */
	private String approvalUnitId;
	/** 审批人ID */
	private String approvalUserId;
	/** 审批时间 */
	private String approvalTime;
	/** 警员ID */
	private Integer userId;
	/** 布控单位ID */
	private Integer unitId;
	/** 姓名 */
	private String name;
	/** 性别 */
	private String sex;
	/** 联系方式(布控人) */
	private String contactInfo;
	/** 户籍地址 */
	private String nativeAddress;
	/** 现住地址 */
	private String currentAddress;
	/** 人员类别集合 */
	private List<MonitorType> monitorTypes;
	/** 是否撤销(0否，1是) */
	private Integer isRevoke;
	/** 民族编号 */
	private Integer nationNumber;
	/** 备注 */
	private String remark;
	/** 处置措施 */
	private String handleMode;
	/** 人员类型 (1.重点人,2.关注,3.外地,4.布控) */
	private Integer personTypeId;
	/** 列控状态(未知) */
	private Integer controlStatus;
	/** 逻辑删除(1.已删除,0.未删除) */
	private Integer isDelete;
	/** 布控申请人 */
	private Integer proposer;

	/** 身份证号 */
	@Column(name = "ID_CARD")
	public String getIdCard() {
		return this.idCard;
	}

	/** 身份证号 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/** 车牌号 */
	@Column(name = "LICENSE_PLATE_NUMBER")
	public String getLicensePlateNumber() {
		return this.licensePlateNumber;
	}

	/** 车牌号 */
	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}

	/** 人员照片 */
	@Column(name = "PHOTO")
	public String getPhoto() {
		return this.photo;
	}

	/** 人员照片 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/** 布控理由 */
	@Column(name = "REASON")
	public String getReason() {
		return this.reason;
	}

	/** 布控理由 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/** 布控时间(最长一个月) */
	@Column(name = "DURATION")
	public String getDuration() {
		return this.duration;
	}

	/** 布控时间(最长一个月) */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/** 联系民警(电话) */
	@Column(name = "CONTACTS_POLICE")
	public String getContactsPolice() {
		return this.contactsPolice;
	}

	/** 联系民警(电话) */
	public void setContactsPolice(String contactsPolice) {
		this.contactsPolice = contactsPolice;
	}

	/** 电话号码(预警接收人) */
	/*
	 * @Column(name = "TEL_NUMBER") public String getTelNumber() { return
	 * this.telNumber; }
	 */
	/** 电话号码(预警接收人) */
	/*
	 * public void setTelNumber(String telNumber) { this.telNumber = telNumber;
	 * }
	 */

	/** 布控状态(0：未审核,1：已审核,2：审核拒绝,3：初审) */
	@Column(name = "STATUS", precision = 11, scale = 0)
	public Integer getStatus() {
		return this.status;
	}

	/** 布控状态(0：未审核,1：已审核,2：审核拒绝,3：初审) */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 审批单位ID */
	@Column(name = "APPROVAL_UNIT_ID", precision = 11, scale = 0)
	public String getApprovalUnitId() {
		return this.approvalUnitId;
	}

	/** 审批单位ID */
	public void setApprovalUnitId(String approvalUnitId) {
		this.approvalUnitId = approvalUnitId;
	}

	/** 审批人ID */
	@Column(name = "APPROVAL_USER_ID", precision = 11, scale = 0)
	public String getApprovalUserId() {
		return this.approvalUserId;
	}

	/** 审批人ID */
	public void setApprovalUserId(String approvalUserId) {
		this.approvalUserId = approvalUserId;
	}

	/** 审批时间 */
	@Column(name = "APPROVAL_TIME")
	public String getApprovalTime() {
		return this.approvalTime;
	}

	/** 审批时间 */
	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}

	/** 警员ID */
	@Column(name = "USER_ID", precision = 11, scale = 0)
	public Integer getUserId() {
		return this.userId;
	}

	/** 警员ID */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/** 布控单位ID */
	@Column(name = "UNIT_ID", precision = 11, scale = 0)
	public Integer getUnitId() {
		return this.unitId;
	}

	/** 布控单位ID */
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	/** 姓名 */
	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	/** 姓名 */
	public void setName(String name) {
		this.name = name;
	}

	/** 性别 */
	@Column(name = "SEX")
	public String getSex() {
		return this.sex;
	}

	/** 性别 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/** 联系方式(布控人) */
	@Column(name = "CONTACT_INFO")
	public String getContactInfo() {
		return this.contactInfo;
	}

	/** 联系方式(布控人) */
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	/** 户籍地址 */
	@Column(name = "NATIVE_ADDRESS")
	public String getNativeAddress() {
		return this.nativeAddress;
	}

	/** 户籍地址 */
	public void setNativeAddress(String nativeAddress) {
		this.nativeAddress = nativeAddress;
	}

	/** 现住地址 */
	@Column(name = "CURRENT_ADDRESS")
	public String getCurrentAddress() {
		return this.currentAddress;
	}

	/** 现住地址 */
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	/** 人员类别 */
	@Column(name = "MONITOR_ID", precision = 11, scale = 0)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "monitor")
	public List<MonitorType> getMonitorTypes() {
		return this.monitorTypes;
	}

	/** 人员类别 */
	public void setMonitorTypes(List<MonitorType> monitorTypes) {
		this.monitorTypes = monitorTypes;
	}

	/** 是否撤销(0否，1是) */
	@Column(name = "IS_REVOKE", precision = 11, scale = 0)
	public Integer getIsRevoke() {
		return this.isRevoke;
	}

	/** 是否撤销(0否，1是) */
	public void setIsRevoke(Integer isRevoke) {
		this.isRevoke = isRevoke;
	}

	/** 民族编号 */
	@Column(name = "NATION_NUMBER")
	public Integer getNationNumber() {
		return this.nationNumber;
	}

	/** 民族编号 */
	public void setNationNumber(Integer nationNumber) {
		this.nationNumber = nationNumber;
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

	/** 处置措施 */
	@Column(name = "HANDLE_MODE")
	public String getHandleMode() {
		return this.handleMode;
	}

	/** 处置措施 */
	public void setHandleMode(String handleMode) {
		this.handleMode = handleMode;
	}

	/** 人员类型 */
	@Column(name = "PERSON_TYPE_ID")
	public Integer getPersonTypeId() {
		return this.personTypeId;
	}

	/** 人员类型 */
	public void setPersonTypeId(Integer personTypeId) {
		this.personTypeId = personTypeId;
	}

	/** 列控状态(未知) */
	@Column(name = "CONTROL_STATUS", precision = 11, scale = 0)
	public Integer getControlStatus() {
		return this.controlStatus;
	}

	/** 列控状态(未知) */
	public void setControlStatus(Integer controlStatus) {
		this.controlStatus = controlStatus;
	}

	/** 逻辑删除(1.已删除,0.未删除) */
	@Column(name = "ISDELETE", precision = 11, scale = 0)
	public Integer getIsDelete() {
		return isDelete;
	}

	/** 逻辑删除(1.已删除,0.未删除) */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	/** 布控申请人 */
	@Column(name = "PROPOSER", precision = 11, scale = 0)
	public Integer getProposer() {
		return proposer;
	}

	/** 布控申请人 */
	public void setProposer(Integer proposer) {
		this.proposer = proposer;
	}

}