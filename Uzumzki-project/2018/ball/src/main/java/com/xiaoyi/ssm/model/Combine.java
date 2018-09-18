package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 实体
 */
public class Combine implements Serializable {
	/** 散拼加入列表 */
	private List<CombineJoin> combineJoins;
	/** 城市 */
	private City city;
	/** 场馆 */
	private Venue venue;
	/** 场馆 */
	private Field field;
	/** 会员 */
	private Manager manager;

	public List<CombineJoin> getCombineJoins() {
		return combineJoins;
	}

	public void setCombineJoins(List<CombineJoin> combineJoins) {
		this.combineJoins = combineJoins;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	/** 散拼 */
	private String id;

	/** 编号 */
	private Integer combineno;

	/** 创建时间 */
	private Date createtime;

	/** 修改时间 */
	private Date modifytime;

	/** 场馆ID */
	private String venueid;

	/** 场地ID */
	private String fieldid;

	/** 活动标题 */
	private String title;

	/** 管理员ID */
	private String managerid;

	/** 要求（如3.0以上） */
	private String demand;

	/** 散拼日期 */
	private Date combinedate;

	/** 散拼时段 */
	private String combinetimeframe;

	/** 人数上限 */
	private Integer boy;

	/** 活动描述 */
	private String content;

	/** 每人费用 */
	private Double amountsum;

	/** 是否取消(0=否1=是) */
	private Integer type;

	/** 提现ID */
	private String amountid;

	/** 提现状态(0=未提现1=正在提现2=已提现) */
	private Integer amounttype;

	/**
	 * Combine
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 散拼
	 * 
	 * @return ID 散拼
	 */
	public String getId() {
		return id;
	}

	/**
	 * 散拼
	 * 
	 * @param id 散拼
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * 编号
	 * 
	 * @return CombineNO 编号
	 */
	public Integer getCombineno() {
		return combineno;
	}

	/**
	 * 编号
	 * 
	 * @param combineno 编号
	 */
	public void setCombineno(Integer combineno) {
		this.combineno = combineno;
	}

	/**
	 * 创建时间
	 * 
	 * @return CreateTime 创建时间
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createtime 创建时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * 修改时间
	 * 
	 * @return ModifyTime 修改时间
	 */
	public Date getModifytime() {
		return modifytime;
	}

	/**
	 * 修改时间
	 * 
	 * @param modifytime 修改时间
	 */
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	/**
	 * 场馆ID
	 * 
	 * @return VenueID 场馆ID
	 */
	public String getVenueid() {
		return venueid;
	}

	/**
	 * 场馆ID
	 * 
	 * @param venueid 场馆ID
	 */
	public void setVenueid(String venueid) {
		this.venueid = venueid == null ? null : venueid.trim();
	}

	/**
	 * 场地ID
	 * 
	 * @return FieldID 场地ID
	 */
	public String getFieldid() {
		return fieldid;
	}

	/**
	 * 场地ID
	 * 
	 * @param fieldid 场地ID
	 */
	public void setFieldid(String fieldid) {
		this.fieldid = fieldid == null ? null : fieldid.trim();
	}

	/**
	 * 活动标题
	 * 
	 * @return Title 活动标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 活动标题
	 * 
	 * @param title 活动标题
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * 管理员ID
	 * 
	 * @return ManagerID 管理员ID
	 */
	public String getManagerid() {
		return managerid;
	}

	/**
	 * 管理员ID
	 * 
	 * @param managerid 管理员ID
	 */
	public void setManagerid(String managerid) {
		this.managerid = managerid == null ? null : managerid.trim();
	}

	/**
	 * 要求（如3.0以上）
	 * 
	 * @return Demand 要求（如3.0以上）
	 */
	public String getDemand() {
		return demand;
	}

	/**
	 * 要求（如3.0以上）
	 * 
	 * @param demand 要求（如3.0以上）
	 */
	public void setDemand(String demand) {
		this.demand = demand == null ? null : demand.trim();
	}

	/**
	 * 散拼日期
	 * 
	 * @return CombineDate 散拼日期
	 */
	public Date getCombinedate() {
		return combinedate;
	}

	/**
	 * 散拼日期
	 * 
	 * @param combinedate 散拼日期
	 */
	public void setCombinedate(Date combinedate) {
		this.combinedate = combinedate;
	}

	/**
	 * 散拼时段
	 * 
	 * @return CombineTimeFrame 散拼时段
	 */
	public String getCombinetimeframe() {
		return combinetimeframe;
	}

	/**
	 * 散拼时段
	 * 
	 * @param combinetimeframe 散拼时段
	 */
	public void setCombinetimeframe(String combinetimeframe) {
		this.combinetimeframe = combinetimeframe == null ? null : combinetimeframe.trim();
	}

	/**
	 * 人数上限
	 * 
	 * @return Boy 人数上限
	 */
	public Integer getBoy() {
		return boy;
	}

	/**
	 * 人数上限
	 * 
	 * @param boy 人数上限
	 */
	public void setBoy(Integer boy) {
		this.boy = boy;
	}

	/**
	 * 活动描述
	 * 
	 * @return Content 活动描述
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 活动描述
	 * 
	 * @param content 活动描述
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	/**
	 * 每人费用
	 * 
	 * @return AmountSum 每人费用
	 */
	public Double getAmountsum() {
		return amountsum;
	}

	/**
	 * 每人费用
	 * 
	 * @param amountsum 每人费用
	 */
	public void setAmountsum(Double amountsum) {
		this.amountsum = amountsum;
	}

	/**
	 * 是否取消(0=否1=是)
	 * 
	 * @return Type 是否取消(0=否1=是)
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 是否取消(0=否1=是)
	 * 
	 * @param type 是否取消(0=否1=是)
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 提现ID
	 * 
	 * @return AmountId 提现ID
	 */
	public String getAmountid() {
		return amountid;
	}

	/**
	 * 提现ID
	 * 
	 * @param amountid 提现ID
	 */
	public void setAmountid(String amountid) {
		this.amountid = amountid == null ? null : amountid.trim();
	}

	/**
	 * 提现状态(0=未提现1=正在提现2=已提现)
	 * 
	 * @return AmountType 提现状态(0=未提现1=正在提现2=已提现)
	 */
	public Integer getAmounttype() {
		return amounttype;
	}

	/**
	 * 提现状态(0=未提现1=正在提现2=已提现)
	 * 
	 * @param amounttype 提现状态(0=未提现1=正在提现2=已提现)
	 */
	public void setAmounttype(Integer amounttype) {
		this.amounttype = amounttype;
	}
}