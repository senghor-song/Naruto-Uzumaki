package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 房源表实体
 */
public class MassPropertyTitleLib implements Serializable {
    /** 推房->(帮我写标题)标题库 */
    private String id;

    /** 标题类型(0=售,1=租) */
    private Integer type;

    /** 创建时间 */
    private Date regdate;

    /** 小区ID */
    private String estateid;

    /** 标题 */
    private String title;

    /**  */
    private String remark;

    /**
     * MassPropertyTitleLib
     */
    private static final long serialVersionUID = 1L;

    /**
     * 推房->(帮我写标题)标题库
     * @return ID 推房->(帮我写标题)标题库
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->(帮我写标题)标题库
     * @param id 推房->(帮我写标题)标题库
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 标题类型(0=售,1=租)
     * @return Type 标题类型(0=售,1=租)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 标题类型(0=售,1=租)
     * @param type 标题类型(0=售,1=租)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 创建时间
     * @return RegDate 创建时间
     */
    public Date getRegdate() {
        return regdate;
    }

    /**
     * 创建时间
     * @param regdate 创建时间
     */
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    /**
     * 小区ID
     * @return EstateID 小区ID
     */
    public String getEstateid() {
        return estateid;
    }

    /**
     * 小区ID
     * @param estateid 小区ID
     */
    public void setEstateid(String estateid) {
        this.estateid = estateid == null ? null : estateid.trim();
    }

    /**
     * 标题
     * @return Title 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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