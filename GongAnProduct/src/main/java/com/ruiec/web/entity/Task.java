package com.ruiec.web.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 任务表
 * @author Senghor<br>
 * @date 2018年1月6日 下午5:39:47
 */
@Entity
@Table(name="T_COR_TASK" )
public class Task extends BaseEntity{

	private static final long serialVersionUID = 1600468030412366775L;
	/** 任务详情集合 */
	private List<TaskDetails> taskDetails = new ArrayList<TaskDetails>();
	/** 任务名称 */
	private String name;
	/** 类型编码 */
	private String typeNumber;
	/** 任务详情 */
	private String details;
	/** 处置要求 */
	private String handleMode;
	/** 批示附件 */
	private String approvalAttachment;
	/** 身份证号 */
	private String idCard;
	/** 关联预警ID（可以为null） */
	private Integer alarmId;
	/** 关联重点人ID（可为null） */
	private Integer controlPersonId;
	/** 任务发起人ID */
	private Integer originatorUserId;
	/** 记录日志 */
	private String log;
	/** 处置时间 */
	private Date handleTime;
	/** 任务级别 */
	private String taskLevel;
	/** 任务状态(0=待审核、1=待签收、2=进行中、3=已结束) */
	private String status;
	/** 任务发起人单位ID */
	private Integer originatorUnitId;
	/** 是否审核（0=审核拒绝、1=已审核、2=需要分县局/市局审核、3=需要分县局审核） */
	private Integer isCheck;
	/** 审核时间（每个时间对应审核人ID用逗号隔开） */
	private String checkTime;
	/** 审核人ID(每个ID对应审核时间用逗号隔开) */
	private String checkUserId;
	/** 审核备注 */
	private String checkRemark;
	/** 任务结束理由 */
	private String overReason;


	/** 任务详情集合 */
	@OneToMany(fetch=FetchType.EAGER, mappedBy="task")
    public List<TaskDetails> getTaskDetails() {
        return this.taskDetails;
    }

	/** 任务详情集合 */
    public void setTaskDetails(List<TaskDetails> taskDetails) {
        this.taskDetails = taskDetails;
    }
	
	/** 任务名称 */
	@Column(name = "NAME", nullable = false)
	public String getName() {
		return this.name;
	}

	/** 任务名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 类型编码 */
	@Column(name = "TYPE_NUMBER")
	public String getTypeNumber() {
		return this.typeNumber;
	}

	/** 类型编码 */
	public void setTypeNumber(String typeNumber) {
		this.typeNumber = typeNumber;
	}

	/** 任务详情 */
	@Column(name = "DETAILS", nullable = false, length = 4000)
	public String getDetails() {
		return this.details;
	}

	/** 任务详情 */
	public void setDetails(String details) {
		this.details = details;
	}

	/** 处置要求 */
	@Column(name = "HANDLE_MODE", nullable = false, length = 2000)
	public String getHandleMode() {
		return this.handleMode;
	}

	/** 处置要求 */
	public void setHandleMode(String handleMode) {
		this.handleMode = handleMode;
	}

	/** 批示附件 */
	@Column(name = "APPROVAL_ATTACHMENT", length = 4000)
	public String getApprovalAttachment() {
		return this.approvalAttachment;
	}

	/** 批示附件 */
	public void setApprovalAttachment(String approvalAttachment) {
		this.approvalAttachment = approvalAttachment;
	}

	/** 身份证号 */
	@Column(name = "ID_CARD", length = 1020)
	public String getIdCard() {
		return this.idCard;
	}

	/** 身份证号 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/** 关联预警ID（可以为null） */
    @Column(name="ALARM_ID", precision=11, scale=0)
	public Integer getAlarmId() {
		return this.alarmId;
	}

	/** 关联预警ID（可以为null） */
	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
	}

	/** 关联重点人ID（可为null） */
    @Column(name="CONTROL_PERSON_ID", precision=11, scale=0)
	public Integer getControlPersonId() {
		return this.controlPersonId;
	}

	/** 关联重点人ID（可为null） */
	public void setControlPersonId(Integer controlPersonId) {
		this.controlPersonId = controlPersonId;
	}

	/** 任务发起人ID */
    @Column(name="ORIGINATOR_USER_ID", nullable=false, precision=11, scale=0)
	public Integer getOriginatorUserId() {
		return this.originatorUserId;
	}

	/** 任务发起人ID */
	public void setOriginatorUserId(Integer originatorUserId) {
		this.originatorUserId = originatorUserId;
	}

	/** 记录日志 */
	@Column(name = "LOG", nullable = false)
	public String getLog() {
		return this.log;
	}

	/** 记录日志 */
	public void setLog(String log) {
		this.log = log;
	}

	/** 处置时间 */
	@Column(name = "HANDLE_TIME", nullable = false)
	public Date getHandleTime() {
		return this.handleTime;
	}

	/** 处置时间 */
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	
	/** 任务级别 */
	@Column(name = "TASK_LEVEL", nullable = false)
	public String getTaskLevel() {
		return this.taskLevel;
	}

	/** 任务级别 */
	public void setTaskLevel(String taskLevel) {
		this.taskLevel = taskLevel;
	}

	/** 任务状态(0=待审核、1=待签收、2=进行中、3=已结束) */
	@Column(name = "STATUS", nullable = false)
	public String getStatus() {
		return this.status;
	}

	/** 任务状态(0=待审核、1=待签收、2=进行中、3=已结束) */
	public void setStatus(String status) {
		this.status = status;
	}

	/** 任务发起人单位ID */
    @Column(name="ORIGINATOR_UNIT_ID", nullable=false, precision=11, scale=0)
	public Integer getOriginatorUnitId() {
		return this.originatorUnitId;
	}

	/** 任务发起人单位ID */
	public void setOriginatorUnitId(Integer originatorUnitId) {
		this.originatorUnitId = originatorUnitId;
	}

	/** 是否审核（0=审核拒绝、1=已审核、2=需要分县局/市局审核、3=需要分县局审核） */
	@Column(name = "IS_CHECK", nullable = false, precision = 11, scale = 0)
	public Integer getIsCheck() {
		return this.isCheck;
	}

	/** 是否审核（0=审核拒绝、1=已审核、2=需要分县局/市局审核、3=需要分县局审核） */
	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}

	/** 审核时间（每个时间对应审核人ID用逗号隔开） */
	@Column(name = "CHECK_TIME")
	public String getCheckTime() {
		return this.checkTime;
	}

	/** 审核时间（每个时间对应审核人ID用逗号隔开） */
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	/** 审核人ID(每个ID对应审核时间用逗号隔开) */
	@Column(name = "CHECK_USER_ID")
	public String getCheckUserId() {
		return this.checkUserId;
	}

	/** 审核人ID(每个ID对应审核时间用逗号隔开) */
	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}

	/** 审核备注 */
	@Column(name = "CHECK_REMARK")
	public String getCheckRemark() {
		return this.checkRemark;
	}

	/** 审核备注 */
	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

	/** 任务结束理由 */
	@Column(name = "OVER_REASON")
	public String getOverReason() {
		return overReason;
	}

	/** 任务结束理由 */
	public void setOverReason(String overReason) {
		this.overReason = overReason;
	}
    
}