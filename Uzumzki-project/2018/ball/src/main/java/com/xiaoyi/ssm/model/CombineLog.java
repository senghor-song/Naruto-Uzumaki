package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class CombineLog implements Serializable {
    /** 散拼日志 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 散拼记录ID */
    private String combineid;

    /** 类别 */
    private Integer type;

    /** 内容 */
    private String content;

    /**
     * CombineLog
     */
    private static final long serialVersionUID = 1L;

    /**
     * 散拼日志
     * @return ID 散拼日志
     */
    public String getId() {
        return id;
    }

    /**
     * 散拼日志
     * @param id 散拼日志
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 创建时间
     * @return CreateTime 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 散拼记录ID
     * @return CombineID 散拼记录ID
     */
    public String getCombineid() {
        return combineid;
    }

    /**
     * 散拼记录ID
     * @param combineid 散拼记录ID
     */
    public void setCombineid(String combineid) {
        this.combineid = combineid == null ? null : combineid.trim();
    }

    /**
     * 类别
     * @return Type 类别
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类别
     * @param type 类别
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 内容
     * @return Content 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}