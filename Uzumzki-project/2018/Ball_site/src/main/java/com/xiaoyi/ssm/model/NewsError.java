package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class NewsError implements Serializable {
	private Member member;
	
    public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	/** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 用户ID */
    private String memberId;

    /** 资讯ID */
    private String newsId;

    /** 报错内容 */
    private String content;

    /**
     * NewsError
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * @return ID ID
     */
    public String getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 创建时间
     * @return Create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 用户ID
     * @return Member_id 用户ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 用户ID
     * @param memberId 用户ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     * 资讯ID
     * @return News_id 资讯ID
     */
    public String getNewsId() {
        return newsId;
    }

    /**
     * 资讯ID
     * @param newsId 资讯ID
     */
    public void setNewsId(String newsId) {
        this.newsId = newsId == null ? null : newsId.trim();
    }

    /**
     * 报错内容
     * @return Content 报错内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 报错内容
     * @param content 报错内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}