package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户订单表实体
 */
public class Order implements Serializable {
	/** 订场详情 */
	List<Reserve> reserves;

	/** 会员 */
	private Member member;

	/** 场馆 */
	private Venue venue;

	/** 城市 */
	private City city;

	/** 城市 */
	private VenueRefund venueRefund;

	public List<Reserve> getReserves() {
		return reserves;
	}

	public void setReserves(List<Reserve> reserves) {
		this.reserves = reserves;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public VenueRefund getVenueRefund() {
		return venueRefund;
	}

	public void setVenueRefund(VenueRefund venueRefund) {
		this.venueRefund = venueRefund;
	}
    /** 订场-订单表 */
    private String id;

    /** 订单编号 */
    private Integer orderno;

    /** 创建时间 */
    private Date createtime;

    /** 修改时间 */
    private Date modifytime;

    /** 订单日期 */
    private Date orderdate;

    /** 场馆ID */
    private String venueid;

    /** 会员ID */
    private String memberid;

    /** 教练ID */
    private String coachid;

    /** 教练费用 */
    private Double coachamount;

    /** 订单总金额 */
    private Double price;

    /** 展示金额 */
    private Double showPrice;

    /** 平台费用 */
    private Double priceFee;

    /** 订单总小时数 */
    private Double timesum;

    /** 订单状态(0=待支付1=已消费2=支付取消3=支付超时4=已退款5=支付成功待确认6=已支付待消费) */
    private Integer type;

    /** 支付时间 */
    private Date paytime;

    /** 支付方式0=支付宝1=微信支付 */
    private Integer paytype;

    /** 支付结果内容(仅供效验) */
    private String payremark;

    /** 订单确认时间 */
    private Date confirmtime;

    /** 订单取消时间 */
    private Date canceltime;

    /** 订单申请退款时间 */
    private Date applytime;

    /** 订单完成退款时间 */
    private Date refundtime;

    /** 提现ID */
    private String amountid;

    /** 提现状态(0=未提现1=已提现) */
    private Integer amounttype;

    /** 是否删除0=否1=是 */
    private Integer deleteflag;

    /** 线上线下 */
    private Integer lineflag;

    /**
     * Order
     */
    private static final long serialVersionUID = 1L;

    /**
     * 订场-订单表
     * @return ID 订场-订单表
     */
    public String getId() {
        return id;
    }

    /**
     * 订场-订单表
     * @param id 订场-订单表
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 订单编号
     * @return OrderNO 订单编号
     */
    public Integer getOrderno() {
        return orderno;
    }

    /**
     * 订单编号
     * @param orderno 订单编号
     */
    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
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
     * 订单日期
     * @return OrderDate 订单日期
     */
    public Date getOrderdate() {
        return orderdate;
    }

    /**
     * 订单日期
     * @param orderdate 订单日期
     */
    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
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
     * 教练ID
     * @return CoachID 教练ID
     */
    public String getCoachid() {
        return coachid;
    }

    /**
     * 教练ID
     * @param coachid 教练ID
     */
    public void setCoachid(String coachid) {
        this.coachid = coachid == null ? null : coachid.trim();
    }

    /**
     * 教练费用
     * @return CoachAmount 教练费用
     */
    public Double getCoachamount() {
        return coachamount;
    }

    /**
     * 教练费用
     * @param coachamount 教练费用
     */
    public void setCoachamount(Double coachamount) {
        this.coachamount = coachamount;
    }

    /**
     * 订单总金额
     * @return Price 订单总金额
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 订单总金额
     * @param price 订单总金额
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 展示金额
     * @return Show_price 展示金额
     */
    public Double getShowPrice() {
        return showPrice;
    }

    /**
     * 展示金额
     * @param showPrice 展示金额
     */
    public void setShowPrice(Double showPrice) {
        this.showPrice = showPrice;
    }

    /**
     * 平台费用
     * @return Price_fee 平台费用
     */
    public Double getPriceFee() {
        return priceFee;
    }

    /**
     * 平台费用
     * @param priceFee 平台费用
     */
    public void setPriceFee(Double priceFee) {
        this.priceFee = priceFee;
    }

    /**
     * 订单总小时数
     * @return TimeSum 订单总小时数
     */
    public Double getTimesum() {
        return timesum;
    }

    /**
     * 订单总小时数
     * @param timesum 订单总小时数
     */
    public void setTimesum(Double timesum) {
        this.timesum = timesum;
    }

    /**
     * 订单状态(0=待支付1=已消费2=支付取消3=支付超时4=已退款5=支付成功待确认6=已支付待消费)
     * @return Type 订单状态(0=待支付1=已消费2=支付取消3=支付超时4=已退款5=支付成功待确认6=已支付待消费)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 订单状态(0=待支付1=已消费2=支付取消3=支付超时4=已退款5=支付成功待确认6=已支付待消费)
     * @param type 订单状态(0=待支付1=已消费2=支付取消3=支付超时4=已退款5=支付成功待确认6=已支付待消费)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 支付时间
     * @return PayTime 支付时间
     */
    public Date getPaytime() {
        return paytime;
    }

    /**
     * 支付时间
     * @param paytime 支付时间
     */
    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    /**
     * 支付方式0=支付宝1=微信支付
     * @return PayType 支付方式0=支付宝1=微信支付
     */
    public Integer getPaytype() {
        return paytype;
    }

    /**
     * 支付方式0=支付宝1=微信支付
     * @param paytype 支付方式0=支付宝1=微信支付
     */
    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    /**
     * 支付结果内容(仅供效验)
     * @return PayRemark 支付结果内容(仅供效验)
     */
    public String getPayremark() {
        return payremark;
    }

    /**
     * 支付结果内容(仅供效验)
     * @param payremark 支付结果内容(仅供效验)
     */
    public void setPayremark(String payremark) {
        this.payremark = payremark == null ? null : payremark.trim();
    }

    /**
     * 订单确认时间
     * @return ConfirmTime 订单确认时间
     */
    public Date getConfirmtime() {
        return confirmtime;
    }

    /**
     * 订单确认时间
     * @param confirmtime 订单确认时间
     */
    public void setConfirmtime(Date confirmtime) {
        this.confirmtime = confirmtime;
    }

    /**
     * 订单取消时间
     * @return CancelTime 订单取消时间
     */
    public Date getCanceltime() {
        return canceltime;
    }

    /**
     * 订单取消时间
     * @param canceltime 订单取消时间
     */
    public void setCanceltime(Date canceltime) {
        this.canceltime = canceltime;
    }

    /**
     * 订单申请退款时间
     * @return ApplyTime 订单申请退款时间
     */
    public Date getApplytime() {
        return applytime;
    }

    /**
     * 订单申请退款时间
     * @param applytime 订单申请退款时间
     */
    public void setApplytime(Date applytime) {
        this.applytime = applytime;
    }

    /**
     * 订单完成退款时间
     * @return RefundTime 订单完成退款时间
     */
    public Date getRefundtime() {
        return refundtime;
    }

    /**
     * 订单完成退款时间
     * @param refundtime 订单完成退款时间
     */
    public void setRefundtime(Date refundtime) {
        this.refundtime = refundtime;
    }

    /**
     * 提现ID
     * @return AmountID 提现ID
     */
    public String getAmountid() {
        return amountid;
    }

    /**
     * 提现ID
     * @param amountid 提现ID
     */
    public void setAmountid(String amountid) {
        this.amountid = amountid == null ? null : amountid.trim();
    }

    /**
     * 提现状态(0=未提现1=已提现)
     * @return AmountType 提现状态(0=未提现1=已提现)
     */
    public Integer getAmounttype() {
        return amounttype;
    }

    /**
     * 提现状态(0=未提现1=已提现)
     * @param amounttype 提现状态(0=未提现1=已提现)
     */
    public void setAmounttype(Integer amounttype) {
        this.amounttype = amounttype;
    }

    /**
     * 是否删除0=否1=是
     * @return DeleteFlag 是否删除0=否1=是
     */
    public Integer getDeleteflag() {
        return deleteflag;
    }

    /**
     * 是否删除0=否1=是
     * @param deleteflag 是否删除0=否1=是
     */
    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }

    /**
     * 线上线下
     * @return LineFlag 线上线下
     */
    public Integer getLineflag() {
        return lineflag;
    }

    /**
     * 线上线下
     * @param lineflag 线上线下
     */
    public void setLineflag(Integer lineflag) {
        this.lineflag = lineflag;
    }
}