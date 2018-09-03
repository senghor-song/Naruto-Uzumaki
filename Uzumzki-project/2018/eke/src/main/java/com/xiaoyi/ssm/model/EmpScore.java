package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 经纪人积分明细表实体
 */
public class EmpScore implements Serializable {
    /** 商户->经纪人积分明细表 */
    private String id;

    /** 经纪ID */
    private String empid;

    /** 变化时间 */
    private Date time;

    /** 变化量(可正可负) */
    private Integer change;

    /** 变化后积分 */
    private Integer score;

    /** 备注 */
    private String remark;

    /**
     * EmpScore
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商户->经纪人积分明细表
     * @return ID 商户->经纪人积分明细表
     */
    public String getId() {
        return id;
    }

    /**
     * 商户->经纪人积分明细表
     * @param id 商户->经纪人积分明细表
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 经纪ID
     * @return EmpID 经纪ID
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * 经纪ID
     * @param empid 经纪ID
     */
    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
    }

    /**
     * 变化时间
     * @return Time 变化时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 变化时间
     * @param time 变化时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 变化量(可正可负)
     * @return Change 变化量(可正可负)
     */
    public Integer getChange() {
        return change;
    }

    /**
     * 变化量(可正可负)
     * @param change 变化量(可正可负)
     */
    public void setChange(Integer change) {
        this.change = change;
    }

    /**
     * 变化后积分
     * @return Score 变化后积分
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 变化后积分
     * @param score 变化后积分
     */
    public void setScore(Integer score) {
        this.score = score;
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