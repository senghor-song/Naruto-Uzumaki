package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 房源预登记表实体
 */
public class PropertyPre implements Serializable {
	/** 对应小区数据 */
	private Estate estateT;
	
    public Estate getEstateT() {
		return estateT;
	}

	public void setEstateT(Estate estateT) {
		this.estateT = estateT;
	}

	/** 租售->客户房源预登记 */
    private String id;

    /** 登记时间 */
    private Date regdate;

    /** 登记渠道 */
    private String regway;

    /** 小区ID(非库存楼盘=32个0) */
    private String estateid;

    /** （辅助） */
    private String estate;

    /** 租售 */
    private String trade;

    /** 状态 */
    private String status;

    /** 面积 */
    private BigDecimal square;

    /** 报价 */
    private Integer price;

    /** 登记人ID（客户） */
    private String custid;

    /** 登记人 */
    private String cust;

    /** 登记人电话 */
    private String custtel;

    /** 分租标识 */
    private Byte flagsublet;

    /** 付佣/不付佣 */
    private Byte flagpay;

    /**  */
    private String remark;

    /**
     * PropertyPre
     */
    private static final long serialVersionUID = 1L;

    /**
     * 租售->客户房源预登记
     * @return ID 租售->客户房源预登记
     */
    public String getId() {
        return id;
    }

    /**
     * 租售->客户房源预登记
     * @param id 租售->客户房源预登记
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 登记时间
     * @return RegDate 登记时间
     */
    public Date getRegdate() {
        return regdate;
    }

    /**
     * 登记时间
     * @param regdate 登记时间
     */
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    /**
     * 登记渠道
     * @return RegWay 登记渠道
     */
    public String getRegway() {
        return regway;
    }

    /**
     * 登记渠道
     * @param regway 登记渠道
     */
    public void setRegway(String regway) {
        this.regway = regway == null ? null : regway.trim();
    }

    /**
     * 小区ID(非库存楼盘=32个0)
     * @return EstateID 小区ID(非库存楼盘=32个0)
     */
    public String getEstateid() {
        return estateid;
    }

    /**
     * 小区ID(非库存楼盘=32个0)
     * @param estateid 小区ID(非库存楼盘=32个0)
     */
    public void setEstateid(String estateid) {
        this.estateid = estateid == null ? null : estateid.trim();
    }

    /**
     * （辅助）
     * @return Estate （辅助）
     */
    public String getEstate() {
        return estate;
    }

    /**
     * （辅助）
     * @param estate （辅助）
     */
    public void setEstate(String estate) {
        this.estate = estate == null ? null : estate.trim();
    }

    /**
     * 租售
     * @return Trade 租售
     */
    public String getTrade() {
        return trade;
    }

    /**
     * 租售
     * @param trade 租售
     */
    public void setTrade(String trade) {
        this.trade = trade == null ? null : trade.trim();
    }

    /**
     * 状态
     * @return Status 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 面积
     * @return Square 面积
     */
    public BigDecimal getSquare() {
        return square;
    }

    /**
     * 面积
     * @param square 面积
     */
    public void setSquare(BigDecimal square) {
        this.square = square;
    }

    /**
     * 报价
     * @return Price 报价
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 报价
     * @param price 报价
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * 登记人ID（客户）
     * @return CustID 登记人ID（客户）
     */
    public String getCustid() {
        return custid;
    }

    /**
     * 登记人ID（客户）
     * @param custid 登记人ID（客户）
     */
    public void setCustid(String custid) {
        this.custid = custid == null ? null : custid.trim();
    }

    /**
     * 登记人
     * @return Cust 登记人
     */
    public String getCust() {
        return cust;
    }

    /**
     * 登记人
     * @param cust 登记人
     */
    public void setCust(String cust) {
        this.cust = cust == null ? null : cust.trim();
    }

    /**
     * 登记人电话
     * @return CustTel 登记人电话
     */
    public String getCusttel() {
        return custtel;
    }

    /**
     * 登记人电话
     * @param custtel 登记人电话
     */
    public void setCusttel(String custtel) {
        this.custtel = custtel == null ? null : custtel.trim();
    }

    /**
     * 分租标识
     * @return FlagSublet 分租标识
     */
    public Byte getFlagsublet() {
        return flagsublet;
    }

    /**
     * 分租标识
     * @param flagsublet 分租标识
     */
    public void setFlagsublet(Byte flagsublet) {
        this.flagsublet = flagsublet;
    }

    /**
     * 付佣/不付佣
     * @return FlagPay 付佣/不付佣
     */
    public Byte getFlagpay() {
        return flagpay;
    }

    /**
     * 付佣/不付佣
     * @param flagpay 付佣/不付佣
     */
    public void setFlagpay(Byte flagpay) {
        this.flagpay = flagpay;
    }

    /**
     * 
     * @return Remark 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * @param remark 
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}