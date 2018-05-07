package com.ruiec.web.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 封装任务详情接口
 * @author Senghor<br>
 * @date 2018年1月12日 下午5:38:08
 */
public class TaskDetailsDTO implements Serializable{

	/**  */
	private static final long serialVersionUID = -1428619202067213535L;
	/** 任务id */
	private Integer taskId;
    /** 任务详情id */
    private Integer taskDetailsId;
    /** 任务状态 */
    private String status;
	/** 任务名称 */
	private String taskName;
	/** 处置要求 */
	private String handleMode;
	/** 处置时间 */
	private String handleTime;
	/** 任务详情 */
	private String details;
	/** 任务附件数组 */
	private String[] fileUrls;
	/** 任务反馈数据集合 */
	private List<Map<String, Object>> feedbackList;
	/** 是否能审核 */
	private boolean checkAudit;
	
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getHandleMode() {
		return handleMode;
	}
	public void setHandleMode(String handleMode) {
		this.handleMode = handleMode;
	}
	public String getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String[] getFileUrls() {
		return fileUrls;
	}
	public void setFileUrls(String[] fileUrls) {
		this.fileUrls = fileUrls;
	}
	public List<Map<String, Object>> getFeedbackList() {
		return feedbackList;
	}
	public void setFeedbackList(List<Map<String, Object>> feedbackList) {
		this.feedbackList = feedbackList;
	}
	public boolean isCheckAudit() {
		return checkAudit;
	}
	public void setCheckAudit(boolean checkAudit) {
		this.checkAudit = checkAudit;
	}
    /** taskDetailsId */
    public Integer getTaskDetailsId() {
        return taskDetailsId;
    }
    /** taskDetailsId */
    public void setTaskDetailsId(Integer taskDetailsId) {
        this.taskDetailsId = taskDetailsId;
    }
    /** status */
    public String getStatus() {
        return status;
    }
    /** status */
    public void setStatus(String status) {
        this.status = status;
    }
	
}
