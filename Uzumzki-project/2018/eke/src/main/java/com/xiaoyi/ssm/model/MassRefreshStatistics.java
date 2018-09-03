package com.xiaoyi.ssm.model;

import java.io.Serializable;

public class MassRefreshStatistics implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 推房->云刷新统计（按日）
     */
    private String id;

    /**
     * 
     */
    private String remark;

    /**
     * 推房->云刷新统计（按日）
     * @return ID 推房->云刷新统计（按日）
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->云刷新统计（按日）
     * @param id 推房->云刷新统计（按日）
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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