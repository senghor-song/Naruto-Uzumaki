package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class Venue implements Serializable {
	
	private City cityT;
	private District districtT;
	private Area areaT;
	
    public City getCityT() {
		return cityT;
	}

	public void setCityT(City cityT) {
		this.cityT = cityT;
	}

	public District getDistrictT() {
		return districtT;
	}

	public void setDistrictT(District districtT) {
		this.districtT = districtT;
	}

	public Area getAreaT() {
		return areaT;
	}

	public void setAreaT(Area areaT) {
		this.areaT = areaT;
	}
    /** 场馆 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 修改时间 */
    private Date modifytime;

    /** 球场编号 */
    private Integer venueno;

    /** 场馆名 */
    private String name;

    /** 地址 */
    private String address;

    /** 城市 */
    private String cityid;

    /** 区县 */
    private String districtid;

    /** 街道 */
    private String areaid;

    /** 联系电话 */
    private String tel;

    /** 场地数量 */
    private Integer ballsum;

    /** 封面 */
    private String image;

    /** 温馨提醒 */
    private String warmreminder;

    /** 回款银行 */
    private String amountbank;

    /** 回款账户 */
    private String amountaccount;

    /** 回款账号 */
    private String amountaccountnumber;

    /** 场馆总收入 */
    private Double amount;

    /** 剩余可提现金额 */
    private Double balance;

    /** 冻结金额(已预订,但未消费) */
    private Double freezeamount;

    /** 订场确认(0=人工确认1=自动确认) */
    private Integer orderverify;

    /**
     * Venue
     */
    private static final long serialVersionUID = 1L;

    /**
     * 场馆
     * @return ID 场馆
     */
    public String getId() {
        return id;
    }

    /**
     * 场馆
     * @param id 场馆
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
     * 球场编号
     * @return VenueNO 球场编号
     */
    public Integer getVenueno() {
        return venueno;
    }

    /**
     * 球场编号
     * @param venueno 球场编号
     */
    public void setVenueno(Integer venueno) {
        this.venueno = venueno;
    }

    /**
     * 场馆名
     * @return Name 场馆名
     */
    public String getName() {
        return name;
    }

    /**
     * 场馆名
     * @param name 场馆名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 地址
     * @return Address 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 地址
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 城市
     * @return CityID 城市
     */
    public String getCityid() {
        return cityid;
    }

    /**
     * 城市
     * @param cityid 城市
     */
    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    /**
     * 区县
     * @return DistrictID 区县
     */
    public String getDistrictid() {
        return districtid;
    }

    /**
     * 区县
     * @param districtid 区县
     */
    public void setDistrictid(String districtid) {
        this.districtid = districtid == null ? null : districtid.trim();
    }

    /**
     * 街道
     * @return AreaID 街道
     */
    public String getAreaid() {
        return areaid;
    }

    /**
     * 街道
     * @param areaid 街道
     */
    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    /**
     * 联系电话
     * @return Tel 联系电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 联系电话
     * @param tel 联系电话
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * 场地数量
     * @return BallSum 场地数量
     */
    public Integer getBallsum() {
        return ballsum;
    }

    /**
     * 场地数量
     * @param ballsum 场地数量
     */
    public void setBallsum(Integer ballsum) {
        this.ballsum = ballsum;
    }

    /**
     * 封面
     * @return Image 封面
     */
    public String getImage() {
        return image;
    }

    /**
     * 封面
     * @param image 封面
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * 温馨提醒
     * @return WarmReminder 温馨提醒
     */
    public String getWarmreminder() {
        return warmreminder;
    }

    /**
     * 温馨提醒
     * @param warmreminder 温馨提醒
     */
    public void setWarmreminder(String warmreminder) {
        this.warmreminder = warmreminder == null ? null : warmreminder.trim();
    }

    /**
     * 回款银行
     * @return AmountBank 回款银行
     */
    public String getAmountbank() {
        return amountbank;
    }

    /**
     * 回款银行
     * @param amountbank 回款银行
     */
    public void setAmountbank(String amountbank) {
        this.amountbank = amountbank == null ? null : amountbank.trim();
    }

    /**
     * 回款账户
     * @return AmountAccount 回款账户
     */
    public String getAmountaccount() {
        return amountaccount;
    }

    /**
     * 回款账户
     * @param amountaccount 回款账户
     */
    public void setAmountaccount(String amountaccount) {
        this.amountaccount = amountaccount == null ? null : amountaccount.trim();
    }

    /**
     * 回款账号
     * @return AmountAccountNumber 回款账号
     */
    public String getAmountaccountnumber() {
        return amountaccountnumber;
    }

    /**
     * 回款账号
     * @param amountaccountnumber 回款账号
     */
    public void setAmountaccountnumber(String amountaccountnumber) {
        this.amountaccountnumber = amountaccountnumber == null ? null : amountaccountnumber.trim();
    }

    /**
     * 场馆总收入
     * @return Amount 场馆总收入
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 场馆总收入
     * @param amount 场馆总收入
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * 剩余可提现金额
     * @return Balance 剩余可提现金额
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * 剩余可提现金额
     * @param balance 剩余可提现金额
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * 冻结金额(已预订,但未消费)
     * @return FreezeAmount 冻结金额(已预订,但未消费)
     */
    public Double getFreezeamount() {
        return freezeamount;
    }

    /**
     * 冻结金额(已预订,但未消费)
     * @param freezeamount 冻结金额(已预订,但未消费)
     */
    public void setFreezeamount(Double freezeamount) {
        this.freezeamount = freezeamount;
    }

    /**
     * 订场确认(0=人工确认1=自动确认)
     * @return OrderVerify 订场确认(0=人工确认1=自动确认)
     */
    public Integer getOrderverify() {
        return orderverify;
    }

    /**
     * 订场确认(0=人工确认1=自动确认)
     * @param orderverify 订场确认(0=人工确认1=自动确认)
     */
    public void setOrderverify(Integer orderverify) {
        this.orderverify = orderverify;
    }
}