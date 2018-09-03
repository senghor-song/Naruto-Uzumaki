package com.ruiec.web.dto;

import javax.validation.constraints.Size;

/**
 * 反馈信息封装类
 * @author Senghor<br>
 * @date 2018年1月9日 下午2:50:42
 */
public class FeedbackDTO {
	
	/** 反馈时间 */
	private String createTime;
	/** 反馈内容 */
	@Size(min = 0, max = 200)
	private String content;
	/** 反馈附件 */
	private String fileUrl;
	/** 是否审核  0=未审核、1=审核通过、2=审核不通过 */
	private int type=0;
	/** 反馈评分 */
	private Integer points;
	/** 反馈评价 */
	private String comment;
	/** 反馈审核时间 */
	private String auditTime;
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	
}
