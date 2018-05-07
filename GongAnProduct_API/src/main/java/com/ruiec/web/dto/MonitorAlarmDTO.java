package com.ruiec.web.dto;

/**
 * 布控预警参数实体
 * @author Senghor<br>
 * @date 2018年4月11日 下午2:17:41
 */
public class MonitorAlarmDTO {
	/** 预警id */
	private Integer monitorAlarmId;
	/** 手机号 */
	private String phone;
	/** 目标状态 */
	private String columnTubeMode;
	/** 反馈内容 */
	private String feedback;
	/** 稳控状态大类 */
	private String contro;
	/** 稳控状态小类 */
	private String contro2;
	/** 预警id */
	public Integer getMonitorAlarmId() {
		return monitorAlarmId;
	}
	/** 预警id */
	public void setMonitorAlarmId(Integer monitorAlarmId) {
		this.monitorAlarmId = monitorAlarmId;
	}
	/** 目标状态 */
	public String getColumnTubeMode() {
		return columnTubeMode;
	}
	/** 目标状态 */
	public void setColumnTubeMode(String columnTubeMode) {
		this.columnTubeMode = columnTubeMode;
	}
	/** 反馈内容 */
	public String getFeedback() {
		return feedback;
	}
	/** 反馈内容 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	/** 稳控状态大类 */
	public String getContro() {
		return contro;
	}
	/** 稳控状态大类 */
	public void setContro(String contro) {
		this.contro = contro;
	}
	/** 稳控状态小类 */
	public String getContro2() {
		return contro2;
	}
	/** 稳控状态小类 */
	public void setContro2(String contro2) {
		this.contro2 = contro2;
	}
	/** 手机号 */
	public String getPhone() {
		return phone;
	}
	/** 手机号 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
