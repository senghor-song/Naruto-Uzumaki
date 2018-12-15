package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户额度明细表实体
 */
public class MemberWXPayment implements Serializable {
    /** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 用户ID */
    private String memberId;

    /** 额度使用 */
    private Double price;

    /** 剩余额度 */
    private Double remainPrice;

    /** 内容 */
    private String content;

    /**
     * MemberWXPayment
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
     * 额度使用
     * @return Price 额度使用
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 额度使用
     * @param price 额度使用
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 剩余额度
     * @return Remain_price 剩余额度
     */
    public Double getRemainPrice() {
        return remainPrice;
    }

    /**
     * 剩余额度
     * @param remainPrice 剩余额度
     */
    public void setRemainPrice(Double remainPrice) {
        this.remainPrice = remainPrice;
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
}