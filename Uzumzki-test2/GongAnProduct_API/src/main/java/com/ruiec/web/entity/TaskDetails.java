package com.ruiec.web.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ruiec.framework.server.support.entity.BaseEntity;
import com.ruiec.web.dto.FeedbackDTO;

/**
 * 任务详情表
 * 
 * @author Senghor<br>
 * @date 2018年1月6日 下午5:40:10
 */
@Entity
@Table(name = "T_COR_TASK_DETAILS")
public class TaskDetails extends BaseEntity {

	private static final long serialVersionUID = 1600468030412366775L;
	/** 积分 */
	private Integer points;
	/** 任务反馈状态 0为未签收 1为已签收/待反馈 2为待审核 3审核通过 4审核拒绝 */
	private Integer feedbackStatus;
	/** 任务接收单位ID */
	private Integer receiveUnitId;
	/** 任务接收人ID */
	private Integer receiveUserId;
	/** 反馈内容 */
	private String feedbackDetails;
	/** 反馈时间 */
	private Date feedbackTime;
	/** 关联任务ID */
	private Task task;
	/** 签收时间 */
	private Date signTime;
	/** 任务反馈数据 */
	private List<FeedbackDTO> feedbackDTO;

	/** 关联任务ID */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TASK_ID", nullable = false)
	public Task getTask() {
		return this.task;
	}

	/** 关联任务ID */
	public void setTask(Task task) {
		this.task = task;
	}

	/** 积分 */
	@Column(name = "POINTS", nullable = false, precision = 2, scale = 0)
	public Integer getPoints() {
		return this.points;
	}

	/** 积分 */
	public void setPoints(Integer points) {
		this.points = points;
	}

	/** 任务反馈状态 0为未签收 1为已签收/待反馈 2为待审核 3审核通过 4审核拒绝 */
	@Column(name = "FEEDBACK_STATUS", nullable = false, precision = 11, scale = 0)
	public Integer getFeedbackStatus() {
		return this.feedbackStatus;
	}

	/** 任务反馈状态 0为未签收 1为已签收/待反馈 2为待审核 3审核通过 4审核拒绝 */
	public void setFeedbackStatus(Integer feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}

	/** 任务接收单位ID */
	@Column(name = "RECEIVE_UNIT_ID", nullable = false, precision = 11, scale = 0)
	public Integer getReceiveUnitId() {
		return this.receiveUnitId;
	}

	/** 任务接收单位ID */
	public void setReceiveUnitId(Integer receiveUnitId) {
		this.receiveUnitId = receiveUnitId;
	}

	/** 任务接收人ID */
	@Column(name = "RECEIVE_USER_ID", precision = 11, scale = 0)
	public Integer getReceiveUserId() {
		return this.receiveUserId;
	}

	/** 任务接收人ID */
	public void setReceiveUserId(Integer receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	/** 反馈内容 */
	@Column(name = "FEEDBACK_DETAILS")
	public String getFeedbackDetails() {
		return this.feedbackDetails;
	}

	/** 反馈内容 */
	public void setFeedbackDetails(String feedbackDetails) {
		this.feedbackDetails = feedbackDetails;
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

	/** 签收时间 */
	@Column(name = "SIGN_TIME", length = 7)
	public Date getSignTime() {
		return this.signTime;
	}

	/** 签收时间 */
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	/** 任务反馈数据 */
	@Transient
	public List<FeedbackDTO> getFeedbackDTO() {
		return feedbackDTO;
	}

	/** 任务反馈数据 */
	public void setFeedbackDTO(List<FeedbackDTO> feedbackDTO) {
		this.feedbackDTO = feedbackDTO;
	}

}