package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class VenueDeposit implements Serializable {
    /** 场馆押金 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 场馆ID */
    private String venueid;

    /** 金额 */
    private Integer amount;

    /**
     * VenueDeposit
     */
    private static final long serialVersionUID = 1L;

    /**
     * 场馆押金
     * @return ID 场馆押金
     */
    public String getId() {
        return id;
    }

    /**
     * 场馆押金
     * @param id 场馆押金
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 创建时间
     * @return CreateTime 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 场馆ID
     * @return VenueID 场馆ID
     */
    public String getVenueid() {
        return venueid;
    }

    /**
     * 场馆ID
     * @param venueid 场馆ID
     */
    public void setVenueid(String venueid) {
        this.venueid = venueid == null ? null : venueid.trim();
    }

    /**
     * 金额
     * @return Amount 金额
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 金额
     * @param amount 金额
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}