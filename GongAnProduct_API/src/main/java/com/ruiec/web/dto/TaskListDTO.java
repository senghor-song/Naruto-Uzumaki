package com.ruiec.web.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 封装任务列表dto
 * @author Senghor<br>
 * @date 2018年1月12日 下午4:17:38
 */
public class TaskListDTO implements Serializable{
	/**  */
	private static final long serialVersionUID = 2225011960855605137L;
	/** 任务id */
	private Integer taskId;
	/** 任务名称 */
	private String taskName;
	/** 开始时间 */
	private String createDate;
	/** 结束时间 */
	private String overDate;
	/** 任务状态(0=待审核、1=待签收、2=进行中、3=已结束) */
	private String status;
	/** 签收单位集合unitName单位名称isSign单位是否签收任务 */
	private List<Map<String, Object>> taskUnit;

	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Map<String, Object>> getTaskUnit() {
		return taskUnit;
	}
	public void setTaskUnit(List<Map<String, Object>> taskUnit) {
		this.taskUnit = taskUnit;
	}
	
	
	
}
