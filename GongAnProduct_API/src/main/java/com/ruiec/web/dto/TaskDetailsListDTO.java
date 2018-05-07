package com.ruiec.web.dto;

import java.io.Serializable;

/**
 * 封装任务详情列表dto
 * @author Senghor<br>
 * @date 2018年1月12日 下午4:17:38
 */
public class TaskDetailsListDTO implements Serializable{
	/**  */
	private static final long serialVersionUID = 2225011960855605137L;
	/** 任务详情id */
	private Integer taskDeatailsId;
	/** 任务名称 */
	private String taskName;
	/** 开始时间 */
	private String createDate;
	/** 结束时间 */
	private String overDate;
	/** 发起单位名称 */
	private String unitName;
	/** 任务反馈状态 0为未签收 1为已签收/待反馈 2为待审核 3审核通过 4审核拒绝 */
	private Integer feedbackStatus;
	/** 任务状态(0=待审核、1=待签收、2=进行中、3=已结束) */
	private String status;
	/** 任务id */
	private Integer taskId;
    /** 发起人 */
    private String userName;
	
	public Integer getTaskDeatailsId() {
		return taskDeatailsId;
	}
	public void setTaskDeatailsId(Integer taskDeatailsId) {
		this.taskDeatailsId = taskDeatailsId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getOverDate() {
		return overDate;
	}
	public void setOverDate(String overDate) {
		this.overDate = overDate;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Integer getFeedbackStatus() {
		return feedbackStatus;
	}
	public void setFeedbackStatus(Integer feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
    /** userName */
    public String getUserName() {
        return userName;
    }
    /** userName */
    public void setUserName(String userName) {
        this.userName = userName;
    }
	
}
