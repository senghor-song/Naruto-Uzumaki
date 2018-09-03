package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 房源表实体
 */
public class MassNotice implements Serializable {
    /** 推房->公告原文 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 修改时间 */
    private Date modifytime;

    /** 公告编号 */
    private Integer noticeno;

    /** 标题 */
    private String title;

    /** 0=系统公告，1=网站公告，2=客户公告，3=公司公告，4=站内信息 */
    private Integer type;

    /** 创建人 */
    private String staffid;

    /**  */
    private String remark;

    /** 内容 */
    private String content;

    /**
     * MassNotice
     */
    private static final long serialVersionUID = 1L;

    /**
     * 推房->公告原文
     * @return ID 推房->公告原文
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->公告原文
     * @param id 推房->公告原文
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
     * 修改时间
     * @return ModifyTime 修改时间
     */
    public Date getModifytime() {
        return modifytime;
    }

    /**
     * 修改时间
     * @param modifytime 修改时间
     */
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * 公告编号
     * @return NoticeNo 公告编号
     */
    public Integer getNoticeno() {
        return noticeno;
    }

    /**
     * 公告编号
     * @param noticeno 公告编号
     */
    public void setNoticeno(Integer noticeno) {
        this.noticeno = noticeno;
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
     * 0=系统公告，1=网站公告，2=客户公告，3=公司公告，4=站内信息
     * @return Type 0=系统公告，1=网站公告，2=客户公告，3=公司公告，4=站内信息
     */
    public Integer getType() {
        return type;
    }

    /**
     * 0=系统公告，1=网站公告，2=客户公告，3=公司公告，4=站内信息
     * @param type 0=系统公告，1=网站公告，2=客户公告，3=公司公告，4=站内信息
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 创建人
     * @return StaffID 创建人
     */
    public String getStaffid() {
        return staffid;
    }

    /**
     * 创建人
     * @param staffid 创建人
     */
    public void setStaffid(String staffid) {
        this.staffid = staffid == null ? null : staffid.trim();
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