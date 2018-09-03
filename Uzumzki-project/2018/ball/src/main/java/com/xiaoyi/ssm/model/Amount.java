package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class Amount implements Serializable {

	/** 城市 */
	private City city;
	/** 场馆 */
	private Venue venue;
	/** 管理员 */
	private Manager manager;
	
    public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	
    /** 提现 */
    private String id;

    /** 编号 */
    private Integer amountno;

    /** 创建时间 */
    private Date createtime;

    /** 修改时间 */
    private Date modifytime;

    /** 场馆ID */
    private String venueid;

    /** 金额 */
    private Integer amount;

    /** 管理员ID */
    private String managerid;

    /** 预计到账时间 */
    private Date estimatedtime;

    /** 提现状态(0=申请中1=提现成功2=提现失败) */
    private Integer status;

    /**
     * Amount
     */
    private static final long serialVersionUID = 1L;

    /**
     * 提现
     * @return ID 提现
     */
    public String getId() {
        return id;
    }

    /**
     * 提现
     * @param id 提现
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 编号
     * @return AmountNO 编号
     */
    public Integer getAmountno() {
        return amountno;
    }

    /**
     * 编号
     * @param amountno 编号
     */
    public void setAmountno(Integer amountno) {
        this.amountno = amountno;
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
     * 预计到账时间
     * @return EstimatedTime 预计到账时间
     */
    public Date getEstimatedtime() {
        return estimatedtime;
    }

    /**
     * 预计到账时间
     * @param estimatedtime 预计到账时间
     */
    public void setEstimatedtime(Date estimatedtime) {
        this.estimatedtime = estimatedtime;
    }

    /**
     * 提现状态(0=申请中1=提现成功2=提现失败)
     * @return Status 提现状态(0=申请中1=提现成功2=提现失败)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 提现状态(0=申请中1=提现成功2=提现失败)
     * @param status 提现状态(0=申请中1=提现成功2=提现失败)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}