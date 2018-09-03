package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 商户表实体
 */
public class CustEmpStore implements Serializable {
	private EmpStore empStoreT;
	
    public EmpStore getEmpStoreT() {
		return empStoreT;
	}

	public void setEmpStoreT(EmpStore empStoreT) {
		this.empStoreT = empStoreT;
	}

	/** 商户->客户顾问商户 */
    private String id;

    /** 客户ID */
    private String custid;

    /** 商户ID */
    private Integer empstoreid;

    /** 商户名(辅助) */
    private String empstore;

    /** 添加时间 */
    private Date addtime;

    /** 备注 */
    private String remark;

    /**
     * CustEmpStore
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商户->客户顾问商户
     * @return ID 商户->客户顾问商户
     */
    public String getId() {
        return id;
    }

    /**
     * 商户->客户顾问商户
     * @param id 商户->客户顾问商户
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
     * 商户ID
     * @return EmpStoreID 商户ID
     */
    public Integer getEmpstoreid() {
        return empstoreid;
    }

    /**
     * 商户ID
     * @param empstoreid 商户ID
     */
    public void setEmpstoreid(Integer empstoreid) {
        this.empstoreid = empstoreid;
    }

    /**
     * 商户名(辅助)
     * @return EmpStore 商户名(辅助)
     */
    public String getEmpstore() {
        return empstore;
    }

    /**
     * 商户名(辅助)
     * @param empstore 商户名(辅助)
     */
    public void setEmpstore(String empstore) {
        this.empstore = empstore == null ? null : empstore.trim();
    }

    /**
     * 添加时间
     * @return AddTime 添加时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 添加时间
     * @param addtime 添加时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
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