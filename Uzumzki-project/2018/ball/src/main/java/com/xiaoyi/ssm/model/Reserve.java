package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

import com.xiaoyi.ssm.util.DateUtil;

/**
 * 实体
 */
public class Reserve implements Serializable {
	public static void main(String[] args) {
		// 当前日期
		Date date = DateUtil.getParse("2018-8-30 2:40", "yyyy-MM-dd HH:mm");
		String hour = DateUtil.getFormat(date, "HH");
		String minute = DateUtil.getFormat(date, "mm");
		int time = (int) (Integer.valueOf(hour)/0.5);
		if (Integer.valueOf(minute) < 30) {
			time--;
		}
		System.out.println(hour+":"+minute);
		System.out.println(time);
	}
	/** 订单信息 */
	private Order order;

	/** 场地信息 */
	private Field field;

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	/** 预定 */
	private String id;

	/** 创建时间 */
	private Date createtime;

	/** 场地ID */
	private String fieldid;

	/** 预约时段 */
	private String reservetimeframe;

	/** 预约金额 */
	private Double reserveamount;

	/** 所属订单ID */
	private String orderid;

	/**
	 * Reserve
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 预定
	 * 
	 * @return ID 预定
	 */
	public String getId() {
		return id;
	}

	/**
	 * 预定
	 * 
	 * @param id 预定
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
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
	 * 预约时段
	 * 
	 * @return ReserveTimeFrame 预约时段
	 */
	public String getReservetimeframe() {
		return reservetimeframe;
	}

	/**
	 * 预约时段
	 * 
	 * @param reservetimeframe 预约时段
	 */
	public void setReservetimeframe(String reservetimeframe) {
		this.reservetimeframe = reservetimeframe == null ? null : reservetimeframe.trim();
	}

	/**
	 * 预约金额
	 * 
	 * @return ReserveAmount 预约金额
	 */
	public Double getReserveamount() {
		return reserveamount;
	}

	/**
	 * 预约金额
	 * 
	 * @param reserveamount 预约金额
	 */
	public void setReserveamount(Double reserveamount) {
		this.reserveamount = reserveamount;
	}

	/**
	 * 所属订单ID
	 * 
	 * @return OrderID 所属订单ID
	 */
	public String getOrderid() {
		return orderid;
	}

	/**
	 * 所属订单ID
	 * 
	 * @param orderid 所属订单ID
	 */
	public void setOrderid(String orderid) {
		this.orderid = orderid == null ? null : orderid.trim();
	}
}