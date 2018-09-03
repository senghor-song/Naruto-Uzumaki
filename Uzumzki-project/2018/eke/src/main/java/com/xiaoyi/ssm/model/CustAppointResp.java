package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户预约看房反馈实体
 */
public class CustAppointResp implements Serializable {
	
	/** 客户 */
	private Cust cust;
	/** 小区 */
	private Estate estate;
	/** 经纪人 */
	private Employee employee;
	
    public Cust getCust() {
		return cust;
	}

	public void setCust(Cust cust) {
		this.cust = cust;
	}

	public Estate getEstate() {
		return estate;
	}

	public void setEstate(Estate estate) {
		this.estate = estate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/** 运营->客户预约看房反馈 */
    private String id;

    /** 客户ID */
    private String custid;

    /** 预约时间 */
    private Date appointtime;

    /** 预约时客户经度(可填) */
    private Double custpositionj;

    /** 预约时客户纬度(可填) */
    private Double custpositionw;

    /** 类型 */
    private String type;

    /** 被预约经纪ID */
    private String empid;

    /** 预约小区ID */
    private String estateid;

    /** 预约房源ID(可为空) */
    private String propertyid;

    /** 响应时间 */
    private Date resptime;

    /** 响应内容 */
    private String respcontent;

    /** 期待看房时间 */
    private String expecttime;

    /** 其它看房要求 */
    private String custsay;

    /** 备注 */
    private String remark;

    /**
     * CustAppointResp
     */
    private static final long serialVersionUID = 1L;

    /**
     * 运营->客户预约看房反馈
     * @return ID 运营->客户预约看房反馈
     */
    public String getId() {
        return id;
    }

    /**
     * 运营->客户预约看房反馈
     * @param id 运营->客户预约看房反馈
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 客户ID
     * @return CustID 客户ID
     */
    public String getCustid() {
        return custid;
    }

    /**
     * 客户ID
     * @param custid 客户ID
     */
    public void setCustid(String custid) {
        this.custid = custid == null ? null : custid.trim();
    }

    /**
     * 预约时间
     * @return AppointTime 预约时间
     */
    public Date getAppointtime() {
        return appointtime;
    }

    /**
     * 预约时间
     * @param appointtime 预约时间
     */
    public void setAppointtime(Date appointtime) {
        this.appointtime = appointtime;
    }

    /**
     * 预约时客户经度(可填)
     * @return CustPositionJ 预约时客户经度(可填)
     */
    public Double getCustpositionj() {
        return custpositionj;
    }

    /**
     * 预约时客户经度(可填)
     * @param custpositionj 预约时客户经度(可填)
     */
    public void setCustpositionj(Double custpositionj) {
        this.custpositionj = custpositionj;
    }

    /**
     * 预约时客户纬度(可填)
     * @return CustPositionW 预约时客户纬度(可填)
     */
    public Double getCustpositionw() {
        return custpositionw;
    }

    /**
     * 预约时客户纬度(可填)
     * @param custpositionw 预约时客户纬度(可填)
     */
    public void setCustpositionw(Double custpositionw) {
        this.custpositionw = custpositionw;
    }

    /**
     * 类型
     * @return Type 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 类型
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 被预约经纪ID
     * @return EmpID 被预约经纪ID
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * 被预约经纪ID
     * @param empid 被预约经纪ID
     */
    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
    }

    /**
     * 预约小区ID
     * @return EstateID 预约小区ID
     */
    public String getEstateid() {
        return estateid;
    }

    /**
     * 预约小区ID
     * @param estateid 预约小区ID
     */
    public void setEstateid(String estateid) {
        this.estateid = estateid == null ? null : estateid.trim();
    }

    /**
     * 预约房源ID(可为空)
     * @return PropertyID 预约房源ID(可为空)
     */
    public String getPropertyid() {
        return propertyid;
    }

    /**
     * 预约房源ID(可为空)
     * @param propertyid 预约房源ID(可为空)
     */
    public void setPropertyid(String propertyid) {
        this.propertyid = propertyid == null ? null : propertyid.trim();
    }

    /**
     * 响应时间
     * @return RespTime 响应时间
     */
    public Date getResptime() {
        return resptime;
    }

    /**
     * 响应时间
     * @param resptime 响应时间
     */
    public void setResptime(Date resptime) {
        this.resptime = resptime;
    }

    /**
     * 响应内容
     * @return RespContent 响应内容
     */
    public String getRespcontent() {
        return respcontent;
    }

    /**
     * 响应内容
     * @param respcontent 响应内容
     */
    public void setRespcontent(String respcontent) {
        this.respcontent = respcontent == null ? null : respcontent.trim();
    }

    /**
     * 期待看房时间
     * @return ExpectTime 期待看房时间
     */
    public String getExpecttime() {
        return expecttime;
    }

    /**
     * 期待看房时间
     * @param expecttime 期待看房时间
     */
    public void setExpecttime(String expecttime) {
        this.expecttime = expecttime == null ? null : expecttime.trim();
    }

    /**
     * 其它看房要求
     * @return CustSay 其它看房要求
     */
    public String getCustsay() {
        return custsay;
    }

    /**
     * 其它看房要求
     * @param custsay 其它看房要求
     */
    public void setCustsay(String custsay) {
        this.custsay = custsay == null ? null : custsay.trim();
    }

    /**
     * 备注
     * @return Remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}