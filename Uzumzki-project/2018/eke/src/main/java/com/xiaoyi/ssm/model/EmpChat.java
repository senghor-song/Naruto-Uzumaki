package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 私客跟进表实体
 */
public class EmpChat implements Serializable {
    /** 商户->经纪人后台交流 */
    private String id;

    /** 交流编号 */
    private Integer chatno;

    /** 经纪人ID */
    private String empid;

    /** 伙伴ID，自动客服=32个0 */
    private String staffid;

    /** 伙伴(辅助) */
    private String staff;

    /** 记录时间 */
    private Date regdate;

    /** 内容 */
    private String content;

    /**
     * EmpChat
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商户->经纪人后台交流
     * @return ID 商户->经纪人后台交流
     */
    public String getId() {
        return id;
    }

    /**
     * 商户->经纪人后台交流
     * @param id 商户->经纪人后台交流
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 交流编号
     * @return ChatNO 交流编号
     */
    public Integer getChatno() {
        return chatno;
    }

    /**
     * 交流编号
     * @param chatno 交流编号
     */
    public void setChatno(Integer chatno) {
        this.chatno = chatno;
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
     * 伙伴ID，自动客服=32个0
     * @return StaffID 伙伴ID，自动客服=32个0
     */
    public String getStaffid() {
        return staffid;
    }

    /**
     * 伙伴ID，自动客服=32个0
     * @param staffid 伙伴ID，自动客服=32个0
     */
    public void setStaffid(String staffid) {
        this.staffid = staffid == null ? null : staffid.trim();
    }

    /**
     * 伙伴(辅助)
     * @return Staff 伙伴(辅助)
     */
    public String getStaff() {
        return staff;
    }

    /**
     * 伙伴(辅助)
     * @param staff 伙伴(辅助)
     */
    public void setStaff(String staff) {
        this.staff = staff == null ? null : staff.trim();
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