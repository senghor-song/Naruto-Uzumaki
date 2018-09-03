package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 场地使用模板表实体
 */
public class FieldTemplate implements Serializable {
	
	/** 模板内容 */
	private VenueTemplate venueTemplate;
	
    public VenueTemplate getVenueTemplate() {
		return venueTemplate;
	}

	public void setVenueTemplate(VenueTemplate venueTemplate) {
		this.venueTemplate = venueTemplate;
	}

	/** 场地使用模板 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 场地日期 */
    private Date fieldtime;

    /** 场馆ID */
    private String venueid;

    /** 场地ID */
    private String fieldid;

    /** 使用场馆模板ID */
    private String templateid;

    /**
     * FieldTemplate
     */
    private static final long serialVersionUID = 1L;

    /**
     * 场地使用模板
     * @return ID 场地使用模板
     */
    public String getId() {
        return id;
    }

    /**
     * 场地使用模板
     * @param id 场地使用模板
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
     * 场地日期
     * @return FieldTime 场地日期
     */
    public Date getFieldtime() {
        return fieldtime;
    }

    /**
     * 场地日期
     * @param fieldtime 场地日期
     */
    public void setFieldtime(Date fieldtime) {
        this.fieldtime = fieldtime;
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
     * 场地ID
     * @return FieldID 场地ID
     */
    public String getFieldid() {
        return fieldid;
    }

    /**
     * 场地ID
     * @param fieldid 场地ID
     */
    public void setFieldid(String fieldid) {
        this.fieldid = fieldid == null ? null : fieldid.trim();
    }

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

}