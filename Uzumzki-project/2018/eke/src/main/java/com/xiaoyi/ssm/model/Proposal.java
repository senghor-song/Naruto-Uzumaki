package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 意见建议表实体
 */
public class Proposal implements Serializable {

	/** 管理员 */
	private Staff staff;
	
    public Staff getStaff() {
		return staff;
	}

    /** 运营->建议 */
    private String id;

    /** 意见编号 */
    private Integer proposalno;

    /** 提交时间 */
    private Date revtime;

    /** 提交人ID */
    private String userid;

    /** 提交途径(0=官网1=经济移动端2客户移动端) */
    private Integer type;

    /** 建议内容 */
    private String detail;

    /** 处理人 */
    private String disposestaff;

    /** 处理意见 */
    private String disposeopinion;

    /** 处理时间 */
    private Date disposetime;

    /**  */
    private String remark;

    /**
     * Proposal
     */
    private static final long serialVersionUID = 1L;

    /**
     * 运营->建议
     * @return ID 运营->建议
     */
    public String getId() {
        return id;
    }

    /**
     * 运营->建议
     * @param id 运营->建议
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 意见编号
     * @return ProposalNO 意见编号
     */
    public Integer getProposalno() {
        return proposalno;
    }

    /**
     * 意见编号
     * @param proposalno 意见编号
     */
    public void setProposalno(Integer proposalno) {
        this.proposalno = proposalno;
    }

    /**
     * 提交时间
     * @return RevTime 提交时间
     */
    public Date getRevtime() {
        return revtime;
    }

    /**
     * 提交时间
     * @param revtime 提交时间
     */
    public void setRevtime(Date revtime) {
        this.revtime = revtime;
    }

    /**
     * 提交人ID
     * @return UserID 提交人ID
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 提交人ID
     * @param userid 提交人ID
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * 提交途径(0=官网1=经济移动端2客户移动端)
     * @return Type 提交途径(0=官网1=经济移动端2客户移动端)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 提交途径(0=官网1=经济移动端2客户移动端)
     * @param type 提交途径(0=官网1=经济移动端2客户移动端)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 建议内容
     * @return Detail 建议内容
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 建议内容
     * @param detail 建议内容
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    /**
     * 处理人
     * @return DisposeStaff 处理人
     */
    public String getDisposestaff() {
        return disposestaff;
    }

    /**
     * 处理人
     * @param disposestaff 处理人
     */
    public void setDisposestaff(String disposestaff) {
        this.disposestaff = disposestaff == null ? null : disposestaff.trim();
    }

    /**
     * 处理意见
     * @return DisposeOpinion 处理意见
     */
    public String getDisposeopinion() {
        return disposeopinion;
    }

    /**
     * 处理意见
     * @param disposeopinion 处理意见
     */
    public void setDisposeopinion(String disposeopinion) {
        this.disposeopinion = disposeopinion == null ? null : disposeopinion.trim();
    }

    /**
     * 处理时间
     * @return DisposeTime 处理时间
     */
    public Date getDisposetime() {
        return disposetime;
    }

    /**
     * 处理时间
     * @param disposetime 处理时间
     */
    public void setDisposetime(Date disposetime) {
        this.disposetime = disposetime;
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