package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 培训评价表实体
 */
public class TrainOrderComment implements Serializable {
	 private Member member;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
    /** 培训评价表 */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间(作为评价时间) */
    private Date modifyTime;

    /** 订单ID */
    private String trainOrderId;

    /** 发送人 */
    private String trainCoachId;

    /** 接收人 */
    private String memberId;

    /** 当前课时数 */
    private Integer classHour;

    /** 评价状态0=待评价1=已评价2=评价超时 */
    private Integer state;

    /** 评价内容 */
    private String content;

    /** 评价选择(1=好评2=中评3=差评4=拒绝评价) */
    private Integer commentSelect;

    /**
     * TrainOrderComment
     */
    private static final long serialVersionUID = 1L;

    /**
     * 培训评价表
     * @return ID 培训评价表
     */
    public String getId() {
        return id;
    }

    /**
     * 培训评价表
     * @param id 培训评价表
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
     * 修改时间(作为评价时间)
     * @return Modify_time 修改时间(作为评价时间)
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间(作为评价时间)
     * @param modifyTime 修改时间(作为评价时间)
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 订单ID
     * @return Train_order_id 订单ID
     */
    public String getTrainOrderId() {
        return trainOrderId;
    }

    /**
     * 订单ID
     * @param trainOrderId 订单ID
     */
    public void setTrainOrderId(String trainOrderId) {
        this.trainOrderId = trainOrderId == null ? null : trainOrderId.trim();
    }

    /**
     * 发送人
     * @return Train_coach_id 发送人
     */
    public String getTrainCoachId() {
        return trainCoachId;
    }

    /**
     * 发送人
     * @param trainCoachId 发送人
     */
    public void setTrainCoachId(String trainCoachId) {
        this.trainCoachId = trainCoachId == null ? null : trainCoachId.trim();
    }

    /**
     * 接收人
     * @return Member_id 接收人
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 接收人
     * @param memberId 接收人
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     * 当前课时数
     * @return Class_hour 当前课时数
     */
    public Integer getClassHour() {
        return classHour;
    }

    /**
     * 当前课时数
     * @param classHour 当前课时数
     */
    public void setClassHour(Integer classHour) {
        this.classHour = classHour;
    }

    /**
     * 评价状态0=待评价1=已评价2=评价超时
     * @return State 评价状态0=待评价1=已评价2=评价超时
     */
    public Integer getState() {
        return state;
    }

    /**
     * 评价状态0=待评价1=已评价2=评价超时
     * @param state 评价状态0=待评价1=已评价2=评价超时
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 评价内容
     * @return Content 评价内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 评价内容
     * @param content 评价内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 评价选择(1=好评2=中评3=差评4=拒绝评价)
     * @return Comment_select 评价选择(1=好评2=中评3=差评4=拒绝评价)
     */
    public Integer getCommentSelect() {
        return commentSelect;
    }

    /**
     * 评价选择(1=好评2=中评3=差评4=拒绝评价)
     * @param commentSelect 评价选择(1=好评2=中评3=差评4=拒绝评价)
     */
    public void setCommentSelect(Integer commentSelect) {
        this.commentSelect = commentSelect;
    }
}