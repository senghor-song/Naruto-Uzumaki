package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 房源表实体
 */
public class PropertyFalse implements Serializable {
    /** 租售->假房源（投诉） */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 投诉来源 */
    private String source;

    /** 创建人ID */
    private String founderid;

    /** 房源ID */
    private String propertyid;

    /** 类型描述 */
    private String type;

    /** 详细描述 */
    private String note;

    /**
     * PropertyFalse
     */
    private static final long serialVersionUID = 1L;

    /**
     * 租售->假房源（投诉）
     * @return ID 租售->假房源（投诉）
     */
    public String getId() {
        return id;
    }

    /**
     * 租售->假房源（投诉）
     * @param id 租售->假房源（投诉）
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
     * 投诉来源
     * @return Source 投诉来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 投诉来源
     * @param source 投诉来源
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * 创建人ID
     * @return FounderID 创建人ID
     */
    public String getFounderid() {
        return founderid;
    }

    /**
     * 创建人ID
     * @param founderid 创建人ID
     */
    public void setFounderid(String founderid) {
        this.founderid = founderid == null ? null : founderid.trim();
    }

    /**
     * 房源ID
     * @return PropertyID 房源ID
     */
    public String getPropertyid() {
        return propertyid;
    }

    /**
     * 房源ID
     * @param propertyid 房源ID
     */
    public void setPropertyid(String propertyid) {
        this.propertyid = propertyid == null ? null : propertyid.trim();
    }

    /**
     * 类型描述
     * @return Type 类型描述
     */
    public String getType() {
        return type;
    }

    /**
     * 类型描述
     * @param type 类型描述
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 详细描述
     * @return Note 详细描述
     */
    public String getNote() {
        return note;
    }

    /**
     * 详细描述
     * @param note 详细描述
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}