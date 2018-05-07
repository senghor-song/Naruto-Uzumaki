package com.ruiec.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 消息表
 * @author 贺云<br>
 * @date 2018年1月8日 下午4:18:31
 */
@Entity
@Table(name="T_COR_MESSAGE")
public class Message extends BaseEntity{

	private static final long serialVersionUID = 1600468030412366775L;
	/**消息标题*/
	private String title;
	/**消息类型1=重点人预警2布控预警3=合成研判4=公告消息5=app首页图片*/
	private Integer messageType;
	/**消息内容*/
	private String content;
	/**是否已读（1已读，0未读）*/
	private Integer isRead;
	/**发布人ID*/
	private Integer releaseId;
	/**接收人ID*/
	private Integer receiveId;
	/**消息来源ID*/
	private Integer sourceId;
	/**是否推送*/
	private Integer isPush;

    /**消息标题*/
	@Column(name = "TITLE")
	public String getTitle() {
		return this.title;
	}

    /**消息标题*/
	public void setTitle(String title) {
		this.title = title;
	}

    /**消息类型1=重点人预警2布控预警3=合成研判4=公告消息5=app首页图片*/
	@Column(name = "MESSAGE_TYPE", precision = 11, scale = 0)
	public Integer getMessageType() {
		return this.messageType;
	}

    /**消息类型1=重点人预警2布控预警3=合成研判4=公告消息5=app首页图片*/
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

    /**消息内容*/
	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

    /**消息内容*/
	public void setContent(String content) {
		this.content = content;
	}

    /**是否已读（1已读，0未读）*/
	@Column(name = "IS_READ", precision = 11, scale = 0)
	public Integer getIsRead() {
		return this.isRead;
	}

    /**是否已读（1已读，0未读）*/
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

    /**发布人ID*/
	@Column(name = "RELEASE_ID", precision = 11, scale = 0)
	public Integer getReleaseId() {
		return this.releaseId;
	}

    /**发布人ID*/
	public void setReleaseId(Integer releaseId) {
		this.releaseId = releaseId;
	}

    /**接收人ID*/
	@Column(name = "RECEIVE_ID", precision = 11, scale = 0)
	public Integer getReceiveId() {
		return this.receiveId;
	}

    /**接收人ID*/
	public void setReceiveId(Integer receiveId) {
		this.receiveId = receiveId;
	}

    /**消息来源ID*/
	@Column(name = "SOURCE_ID", precision = 11, scale = 0)
	public Integer getSourceId() {
		return sourceId;
	}

    /**消息来源ID*/
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

    /**是否推送*/
	@Column(name = "IS_PUSH", precision = 11, scale = 0)
	public Integer getIsPush() {
		return isPush;
	}

    /**是否推送*/
	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}
	
}