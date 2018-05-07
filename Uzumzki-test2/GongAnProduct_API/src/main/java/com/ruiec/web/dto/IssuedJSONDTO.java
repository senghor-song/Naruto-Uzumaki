package com.ruiec.web.dto;

import java.io.Serializable;

import net.sf.json.JSONObject;
/**
 * 下发反馈json
 * @author qinzhitian<br>
 * @date 2017年12月8日 下午9:01:16
 */
public class IssuedJSONDTO implements Serializable{

	private static final long serialVersionUID = -2676793665862249702L;

	/** 下发/反馈时间 */
	private String createDate;
	/** 下发/反馈内容 */
	private String content;
	/** 下发/反馈部门 */
	private String department;
	/** 下发/反馈警员 */
	private String policeName;
	/** 下发/反馈类型（1=下发；2=反馈） */
	private String type;
	/** 反馈信息json */
	private JSONObject json = new JSONObject();
	
	/** 下发/反馈时间 */
	public String getCreateDate() {
		return createDate;
	}
	/** 下发/反馈时间 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/** 下发/反馈内容 */
	public String getContent() {
		return content;
	}
	/** 下发/反馈内容 */
	public void setContent(String content) {
		this.content = content;
	}
	/** 下发/反馈部门 */
	public String getDepartment() {
		return department;
	}
	/** 下发/反馈部门 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/** 下发/反馈警员 */
	public String getPoliceName() {
		return policeName;
	}
	/** 下发/反馈警员 */
	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}
	/** 下发/反馈类型（1=下发；2=反馈） */
	public String getType() {
		return type;
	}
	/** 下发/反馈类型（1=下发；2=反馈） */
	public void setType(String type) {
		this.type = type;
	}
	/** 反馈信息json */
	public JSONObject getJson() {
		return json;
	}
	/** 反馈信息json */
	public void setJson(JSONObject json) {
		this.json = json;
	}
	
	
}
