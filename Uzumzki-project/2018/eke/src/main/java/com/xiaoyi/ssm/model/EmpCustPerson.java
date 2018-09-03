package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

public class EmpCustPerson implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 经纪人收藏房源表
     */
    private String id;

    /**
     * 经纪人ID
     */
    private String empid;

    /**
     * 房源ID
     */
    private String houseid;

    /**
     * 租售类型
     */
    private String housetype;

    /**
     * 收藏时间
     */
    private Date createtime;

    /**
     * 经纪人收藏房源表
     * @return ID 经纪人收藏房源表
     */
    public String getId() {
        return id;
    }

    /**
     * 经纪人收藏房源表
     * @param id 经纪人收藏房源表
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 经纪人ID
     * @return EmpId 经纪人ID
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
     * 房源ID
     * @return HouseId 房源ID
     */
    public String getHouseid() {
        return houseid;
    }

    /**
     * 房源ID
     * @param houseid 房源ID
     */
    public void setHouseid(String houseid) {
        this.houseid = houseid == null ? null : houseid.trim();
    }

    /**
     * 租售类型
     * @return HouseType 租售类型
     */
    public String getHousetype() {
        return housetype;
    }

    /**
     * 租售类型
     * @param housetype 租售类型
     */
    public void setHousetype(String housetype) {
        this.housetype = housetype == null ? null : housetype.trim();
    }

    /**
     * 收藏时间
     * @return CreateTime 收藏时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 收藏时间
     * @param createtime 收藏时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}