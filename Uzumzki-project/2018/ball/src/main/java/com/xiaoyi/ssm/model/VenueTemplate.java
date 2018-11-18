package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class VenueTemplate implements Serializable {

	/** 场馆模板 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 修改时间 */
    private Date modifytime;

    /** 场馆ID */
    private String venueid;

    /** 模板名 */
    private String name;

    /** 时段价格字符串(逗号隔开,不可预订为-1已预约为0) */
    private String price;

    /** 是否为默认模板(0=否1=是) */
    private Integer defaultflag;

    /**
     * VenueTemplate
     */
    private static final long serialVersionUID = 1L;

    /**
     * 场馆模板
     * @return ID 场馆模板
     */
    public String getId() {
        return id;
    }

    /**
     * 场馆模板
     * @param id 场馆模板
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
     * 修改时间
     * @return ModifyTime 修改时间
     */
    public Date getModifytime() {
        return modifytime;
    }

    /**
     * 修改时间
     * @param modifytime 修改时间
     */
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
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
     * 模板名
     * @return Name 模板名
     */
    public String getName() {
        return name;
    }

    /**
     * 模板名
     * @param name 模板名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 时段价格字符串(逗号隔开,不可预订为-1已预约为0)
     * @return Price 时段价格字符串(逗号隔开,不可预订为-1已预约为0)
     */
    public String getPrice() {
        return price;
    }

    /**
     * 时段价格字符串(逗号隔开,不可预订为-1已预约为0)
     * @param price 时段价格字符串(逗号隔开,不可预订为-1已预约为0)
     */
    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    /**
     * 是否为默认模板(0=否1=是)
     * @return DefaultFlag 是否为默认模板(0=否1=是)
     */
    public Integer getDefaultflag() {
        return defaultflag;
    }

    /**
     * 是否为默认模板(0=否1=是)
     * @param defaultflag 是否为默认模板(0=否1=是)
     */
    public void setDefaultflag(Integer defaultflag) {
        this.defaultflag = defaultflag;
    }
}