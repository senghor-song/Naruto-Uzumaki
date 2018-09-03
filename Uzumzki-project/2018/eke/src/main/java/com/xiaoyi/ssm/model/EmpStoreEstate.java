package com.xiaoyi.ssm.model;

import java.io.Serializable;

/**
 * 商户责任楼盘表实体
 */
public class EmpStoreEstate implements Serializable {
    /** 商户->商户责任小区 */
    private String id;

    /** 商户ID */
    private String empstoreid;

    /** 0=自选，1=后台附加 */
    private Byte type;

    /** 责任小区(租售，不含新房)ID */
    private String estateid;

    /** 备注 */
    private String remark;

    /**
     * EmpStoreEstate
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商户->商户责任小区
     * @return ID 商户->商户责任小区
     */
    public String getId() {
        return id;
    }

    /**
     * 商户->商户责任小区
     * @param id 商户->商户责任小区
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 商户ID
     * @return EmpStoreID 商户ID
     */
    public String getEmpstoreid() {
        return empstoreid;
    }

    /**
     * 商户ID
     * @param empstoreid 商户ID
     */
    public void setEmpstoreid(String empstoreid) {
        this.empstoreid = empstoreid == null ? null : empstoreid.trim();
    }

    /**
     * 0=自选，1=后台附加
     * @return Type 0=自选，1=后台附加
     */
    public Byte getType() {
        return type;
    }

    /**
     * 0=自选，1=后台附加
     * @param type 0=自选，1=后台附加
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 责任小区(租售，不含新房)ID
     * @return EstateID 责任小区(租售，不含新房)ID
     */
    public String getEstateid() {
        return estateid;
    }

    /**
     * 责任小区(租售，不含新房)ID
     * @param estateid 责任小区(租售，不含新房)ID
     */
    public void setEstateid(String estateid) {
        this.estateid = estateid == null ? null : estateid.trim();
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