package com.xiaoyi.ssm.model;

import java.io.Serializable;

public class MassStatisticsPropertyOutSuccess  implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 推房->群发成功率统计表
     */
    private String id;

    /**
     * 房源ID
     */
    private String masspropertyid;

    /**
     * 
     */
    private String remark;

    /**
     * 推房->群发成功率统计表
     * @return ID 推房->群发成功率统计表
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->群发成功率统计表
     * @param id 推房->群发成功率统计表
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 房源ID
     * @return MassPropertyID 房源ID
     */
    public String getMasspropertyid() {
        return masspropertyid;
    }

    /**
     * 房源ID
     * @param masspropertyid 房源ID
     */
    public void setMasspropertyid(String masspropertyid) {
        this.masspropertyid = masspropertyid == null ? null : masspropertyid.trim();
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