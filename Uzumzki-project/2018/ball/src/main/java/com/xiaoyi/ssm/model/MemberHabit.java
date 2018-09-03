package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class MemberHabit implements Serializable {
    /** 会员习惯场馆 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 会员ID */
    private String memberid;

    /** 场馆ID */
    private String venueid;

    /**
     * MemberHabit
     */
    private static final long serialVersionUID = 1L;

    /**
     * 会员习惯场馆
     * @return ID 会员习惯场馆
     */
    public String getId() {
        return id;
    }

    /**
     * 会员习惯场馆
     * @param id 会员习惯场馆
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
     * 会员ID
     * @return MemberID 会员ID
     */
    public String getMemberid() {
        return memberid;
    }

    /**
     * 会员ID
     * @param memberid 会员ID
     */
    public void setMemberid(String memberid) {
        this.memberid = memberid == null ? null : memberid.trim();
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
}