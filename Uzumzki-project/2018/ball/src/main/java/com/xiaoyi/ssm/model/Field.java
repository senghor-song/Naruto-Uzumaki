package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class Field implements Serializable {
    /** 场地 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 修改时间 */
    private Date modifytime;

    /** 场馆ID */
    private String venueid;

    /** 场地名 */
    private String name;

    /** 默认使用模板ID */
    private String defaultTemplate;

    /**
     * Field
     */
    private static final long serialVersionUID = 1L;

    /**
     * 场地
     * @return ID 场地
     */
    public String getId() {
        return id;
    }

    /**
     * 场地
     * @param id 场地
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
     * @return modifyTime 修改时间
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
     * 场地名
     * @return Name 场地名
     */
    public String getName() {
        return name;
    }

    /**
     * 场地名
     * @param name 场地名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 默认使用模板ID
     * @return Default_template 默认使用模板ID
     */
    public String getDefaultTemplate() {
        return defaultTemplate;
    }

    /**
     * 默认使用模板ID
     * @param defaultTemplate 默认使用模板ID
     */
    public void setDefaultTemplate(String defaultTemplate) {
        this.defaultTemplate = defaultTemplate == null ? null : defaultTemplate.trim();
    }
}