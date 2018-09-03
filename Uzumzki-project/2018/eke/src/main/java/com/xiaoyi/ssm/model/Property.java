package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 房源表实体
 */
public class Property implements Serializable {
	
	/** 房库表 */
	private MassProperty massProperty;

	public MassProperty getMassProperty() {
		return massProperty;
	}

	public void setMassProperty(MassProperty massProperty) {
		this.massProperty = massProperty;
	}

	/** 租售->房源 */
    private String id;

    /** 发送时间 */
    private Date massdate;

    /** 删除标识 */
    private Byte flagdeleted;

    /** 删除时间 */
    private Date deletedtime;

    /** 查看次数（定时统计） */
    private Integer viewtimes;

    /** 排序分数(排序依据) */
    private Integer sortscore;

    /** 房库类型(0=售1=租) */
    private Integer masspropertytype;

    /** 房库ID */
    private String masspropertyid;

    /**
     * Property
     */
    private static final long serialVersionUID = 1L;

    /**
     * 租售->房源
     * @return ID 租售->房源
     */
    public String getId() {
        return id;
    }

    /**
     * 租售->房源
     * @param id 租售->房源
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 发送时间
     * @return MassDate 发送时间
     */
    public Date getMassdate() {
        return massdate;
    }

    /**
     * 发送时间
     * @param massdate 发送时间
     */
    public void setMassdate(Date massdate) {
        this.massdate = massdate;
    }

    /**
     * 删除标识
     * @return FlagDeleted 删除标识
     */
    public Byte getFlagdeleted() {
        return flagdeleted;
    }

    /**
     * 删除标识
     * @param flagdeleted 删除标识
     */
    public void setFlagdeleted(Byte flagdeleted) {
        this.flagdeleted = flagdeleted;
    }

    /**
     * 删除时间
     * @return DeletedTime 删除时间
     */
    public Date getDeletedtime() {
        return deletedtime;
    }

    /**
     * 删除时间
     * @param deletedtime 删除时间
     */
    public void setDeletedtime(Date deletedtime) {
        this.deletedtime = deletedtime;
    }

    /**
     * 查看次数（定时统计）
     * @return ViewTimes 查看次数（定时统计）
     */
    public Integer getViewtimes() {
        return viewtimes;
    }

    /**
     * 查看次数（定时统计）
     * @param viewtimes 查看次数（定时统计）
     */
    public void setViewtimes(Integer viewtimes) {
        this.viewtimes = viewtimes;
    }

    /**
     * 排序分数(排序依据)
     * @return SortScore 排序分数(排序依据)
     */
    public Integer getSortscore() {
        return sortscore;
    }

    /**
     * 排序分数(排序依据)
     * @param sortscore 排序分数(排序依据)
     */
    public void setSortscore(Integer sortscore) {
        this.sortscore = sortscore;
    }

    /**
     * 房库类型(0=售1=租)
     * @return MassPropertyType 房库类型(0=售1=租)
     */
    public Integer getMasspropertytype() {
        return masspropertytype;
    }

    /**
     * 房库类型(0=售1=租)
     * @param masspropertytype 房库类型(0=售1=租)
     */
    public void setMasspropertytype(Integer masspropertytype) {
        this.masspropertytype = masspropertytype;
    }

    /**
     * 房库ID
     * @return MassPropertyID 房库ID
     */
    public String getMasspropertyid() {
        return masspropertyid;
    }

    /**
     * 房库ID
     * @param masspropertyid 房库ID
     */
    public void setMasspropertyid(String masspropertyid) {
        this.masspropertyid = masspropertyid == null ? null : masspropertyid.trim();
    }
}