package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 经纪人变更日志实体
 */
public class EmpLog implements Serializable {
    /** 商户->经纪人变更日志 */
    private String id;

    /** 变更编号 */
    private Integer logno;

    /** 经纪人ID */
    private String empid;

    /** 记录时间 */
    private Date regdate;

    /** 日志内容 */
    private String content;

    /**
     * EmpLog
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商户->经纪人变更日志
     * @return ID 商户->经纪人变更日志
     */
    public String getId() {
        return id;
    }

    /**
     * 商户->经纪人变更日志
     * @param id 商户->经纪人变更日志
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 变更编号
     * @return LogNO 变更编号
     */
    public Integer getLogno() {
        return logno;
    }

    /**
     * 变更编号
     * @param logno 变更编号
     */
    public void setLogno(Integer logno) {
        this.logno = logno;
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
     * 记录时间
     * @return RegDate 记录时间
     */
    public Date getRegdate() {
        return regdate;
    }

    /**
     * 记录时间
     * @param regdate 记录时间
     */
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    /**
     * 日志内容
     * @return Content 日志内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 日志内容
     * @param content 日志内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}