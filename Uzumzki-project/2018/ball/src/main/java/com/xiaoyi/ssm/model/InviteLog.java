package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 约球日志实体
 */
public class InviteLog implements Serializable {
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

    /** 约球ID */
    private String inviteballId;

    /** 内容 */
    private String content;

    /** 类型(0=留言1=通知) */
    private Integer type;

    /**
     * InviteLog
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
     * 约球ID
     * @return InviteBall_id 约球ID
     */
    public String getInviteballId() {
        return inviteballId;
    }

    /**
     * 约球ID
     * @param inviteballId 约球ID
     */
    public void setInviteballId(String inviteballId) {
        this.inviteballId = inviteballId == null ? null : inviteballId.trim();
    }

    /**
     * 内容
     * @return Content 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 类型(0=留言1=通知)
     * @return Type 类型(0=留言1=通知)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型(0=留言1=通知)
     * @param type 类型(0=留言1=通知)
     */
    public void setType(Integer type) {
        this.type = type;
    }
}