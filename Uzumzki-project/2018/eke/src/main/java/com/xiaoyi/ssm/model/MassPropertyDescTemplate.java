package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 房源表实体
 */
public class MassPropertyDescTemplate implements Serializable {
    /** 推房->(帮我写描述)模板库 */
    private String id;

    /** 创建时间 */
    private Date regdate;

    /** 模板No */
    private String templateno;

    /** 类型0=售1=租 */
    private Integer type;

    /**  */
    private String remark;

    /** 模板 */
    private String template;

    /**
     * MassPropertyDescTemplate
     */
    private static final long serialVersionUID = 1L;

    /**
     * 推房->(帮我写描述)模板库
     * @return ID 推房->(帮我写描述)模板库
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->(帮我写描述)模板库
     * @param id 推房->(帮我写描述)模板库
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
     * 模板No
     * @return TemplateNo 模板No
     */
    public String getTemplateno() {
        return templateno;
    }

    /**
     * 模板No
     * @param templateno 模板No
     */
    public void setTemplateno(String templateno) {
        this.templateno = templateno == null ? null : templateno.trim();
    }

    /**
     * 类型0=售1=租
     * @return Type 类型0=售1=租
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型0=售1=租
     * @param type 类型0=售1=租
     */
    public void setType(Integer type) {
        this.type = type;
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

    /**
     * 模板
     * @return Template 模板
     */
    public String getTemplate() {
        return template;
    }

    /**
     * 模板
     * @param template 模板
     */
    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }
}