package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class ManagerVenue implements Serializable {
    /** 管理员所管理的场馆 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 管理员ID */
    private String managerid;

    /** 场馆ID */
    private String venueid;

    /**
     * ManagerVenue
     */
    private static final long serialVersionUID = 1L;

    /**
     * 管理员所管理的场馆
     * @return ID 管理员所管理的场馆
     */
    public String getId() {
        return id;
    }

    /**
     * 管理员所管理的场馆
     * @param id 管理员所管理的场馆
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
     * 管理员ID
     * @return ManagerID 管理员ID
     */
    public String getManagerid() {
        return managerid;
    }

    /**
     * 管理员ID
     * @param managerid 管理员ID
     */
    public void setManagerid(String managerid) {
        this.managerid = managerid == null ? null : managerid.trim();
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