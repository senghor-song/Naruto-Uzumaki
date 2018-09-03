package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**  
 * @Description: 支付记录
 * @author 宋高俊  
 * @date 2018年7月30日 上午9:25:49 
 */ 
public class MassEmpPay implements Serializable{
	
	/** 经济人信息 */
	private Employee employee ;
	
    public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 推房->经纪人续费流水
     */
    private String id;

    /**
     * 经纪人ID
     */
    private String empid;

    /**
     * 购买会员类型
     */
    private String type;

    /**
     * 购买时长(季付，半年付，年付)
     */
    private String buyduration;

    /**
     * 订单时间
     */
    private Date paytime;

    /**
     * 预计到期时间
     */
    private Date endtime;

    /**
     * 支付方式(0=微信1=支付宝)
     */
    private Integer paytype;

    /**
     * 订单金额
     */
    private Double payset;

    /**
     * 需付金额
     */
    private Double payact;

    /**
     * 支付状态(0=待支付1=支付成功2=订单取消3=订单超时)
     */
    private Integer payresult;

    /**
     * 支付接口返回内容
     */
    private String remark;

    /**
     * 推房->经纪人续费流水
     * @return ID 推房->经纪人续费流水
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->经纪人续费流水
     * @param id 推房->经纪人续费流水
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 经纪人ID
     * @return EmpID 经纪人ID
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * 经纪人ID
     * @param empid 经纪人ID
     */
    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
    }

    /**
     * 购买会员类型
     * @return Type 购买会员类型
     */
    public String getType() {
        return type;
    }

    /**
     * 购买会员类型
     * @param type 购买会员类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 购买时长(季付，半年付，年付)
     * @return BuyDuration 购买时长(季付，半年付，年付)
     */
    public String getBuyduration() {
        return buyduration;
    }

    /**
     * 购买时长(季付，半年付，年付)
     * @param buyduration 购买时长(季付，半年付，年付)
     */
    public void setBuyduration(String buyduration) {
        this.buyduration = buyduration == null ? null : buyduration.trim();
    }

    /**
     * 订单时间
     * @return PayTime 订单时间
     */
    public Date getPaytime() {
        return paytime;
    }

    /**
     * 订单时间
     * @param paytime 订单时间
     */
    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    /**
     * 预计到期时间
     * @return EndTime 预计到期时间
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * 预计到期时间
     * @param endtime 预计到期时间
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * 支付方式(0=微信1=支付宝)
     * @return PayType 支付方式(0=微信1=支付宝)
     */
    public Integer getPaytype() {
        return paytype;
    }

    /**
     * 支付方式(0=微信1=支付宝)
     * @param paytype 支付方式(0=微信1=支付宝)
     */
    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    /**
     * 订单金额
     * @return PaySet 订单金额
     */
    public Double getPayset() {
        return payset;
    }

    /**
     * 订单金额
     * @param payset 订单金额
     */
    public void setPayset(Double payset) {
        this.payset = payset;
    }

    /**
     * 需付金额
     * @return PayAct 需付金额
     */
    public Double getPayact() {
        return payact;
    }

    /**
     * 需付金额
     * @param payact 需付金额
     */
    public void setPayact(Double payact) {
        this.payact = payact;
    }

    /**
     * 支付状态(0=待支付1=支付成功2=订单取消3=订单超时)
     * @return PayResult 支付状态(0=待支付1=支付成功2=订单取消3=订单超时)
     */
    public Integer getPayresult() {
        return payresult;
    }

    /**
     * 支付状态(0=待支付1=支付成功2=订单取消3=订单超时)
     * @param payresult 支付状态(0=待支付1=支付成功2=订单取消3=订单超时)
     */
    public void setPayresult(Integer payresult) {
        this.payresult = payresult;
    }

    /**
     * 支付接口返回内容
     * @return Remark 支付接口返回内容
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 支付接口返回内容
     * @param remark 支付接口返回内容
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}