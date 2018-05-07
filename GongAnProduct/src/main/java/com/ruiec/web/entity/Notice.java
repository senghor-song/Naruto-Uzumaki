package com.ruiec.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 公告实体
 * @author qinzhitian<br>
 * @date 2017年11月29日 上午9:51:34
 */
@Entity
@Table(name = "T_SYS_NOTICE")
public class Notice  extends BaseEntity {

	private static final long serialVersionUID = -2341135667111537351L;

	/** 标题 */
	private String title;
	/** 发布人 */
	private String publisher;
	/** 内容 */
	private String content;


	/** 标题 */
	@Column(name = "TITLE", nullable = false)
	public String getTitle() {
		return this.title;
	}

	/** 标题 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** 发布人 */
	@Column(name = "PUBLISHER", nullable = false)
	public String getPublisher() {
		return this.publisher;
	}

	/** 发布人 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	/** 内容 */
	@Column(name = "CONTENT", nullable = false)
	public String getContent() {
		return this.content;
	}

	/** 内容 */
	public void setContent(String content) {
		this.content = content;
	}

}