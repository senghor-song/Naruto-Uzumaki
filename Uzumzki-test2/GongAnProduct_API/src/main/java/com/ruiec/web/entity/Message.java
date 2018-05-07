package com.ruiec.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 消息表
 * 
 * @author 贺云<br>
 * @date 2018年1月8日 下午4:18:31
 */
@Entity
@Table(name = "T_COR_MESSAGE")
public class Message extends BaseEntity {

	private static final long serialVersionUID = 1600468030412366775L;
	/** 消息标题 */
	private String title;
	/** 消息类型 */
	private Integer messageType;
	/** 消息内容 */
	private String content;
	/** 是否已读（1已读，0未读） */
	private Integer isRead;
	/** 发布人ID */
	private Integer releaseId;
	/** 接收人ID */
	private Integer receiveId;
	/** 消息来源ID */
	private Integer sourceId;

	@Column(name = "TITLE")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "MESSAGE_TYPE", precision = 11, scale = 0)
	public Integer getMessageType() {
		return this.messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "IS_READ", precision = 11, scale = 0)
	public Integer getIsRead() {
		return this.isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	@Column(name = "RELEASE_ID", precision = 11, scale = 0)
	public Integer getReleaseId() {
		return this.releaseId;
	}

	public void setReleaseId(Integer releaseId) {
		this.releaseId = releaseId;
	}

	@Column(name = "RECEIVE_ID", precision = 11, scale = 0)
	public Integer getReceiveId() {
		return this.receiveId;
	}

	public void setReceiveId(Integer receiveId) {
		this.receiveId = receiveId;
	}

	@Column(name = "SOURCE_ID", precision = 11, scale = 0)
	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

}