package com.xiaoyi.ssm.model;

import java.io.Serializable;

public class MassEstateXrayCategory implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 推房->小区透视分类
     */
    private String id;

    /**
     * 网站
     */
    private String site;

    /**
     * 房源房源编号
     */
    private String propertyno;

    /**
     * 户型
     */
    private String room;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 面积
     */
    private String square;

    /**
     * 登记时间
     */
    private String register;

    /**
     * 
     */
    private String remark;

    /**
     * 推房->小区透视分类
     * @return ID 推房->小区透视分类
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->小区透视分类
     * @param id 推房->小区透视分类
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 网站
     * @return Site 网站
     */
    public String getSite() {
        return site;
    }

    /**
     * 网站
     * @param site 网站
     */
    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    /**
     * 房源房源编号
     * @return PropertyNo 房源房源编号
     */
    public String getPropertyno() {
        return propertyno;
    }

    /**
     * 房源房源编号
     * @param propertyno 房源房源编号
     */
    public void setPropertyno(String propertyno) {
        this.propertyno = propertyno == null ? null : propertyno.trim();
    }

    /**
     * 户型
     * @return Room 户型
     */
    public String getRoom() {
        return room;
    }

    /**
     * 户型
     * @param room 户型
     */
    public void setRoom(String room) {
        this.room = room == null ? null : room.trim();
    }

    /**
     * 楼层
     * @return Floor 楼层
     */
    public String getFloor() {
        return floor;
    }

    /**
     * 楼层
     * @param floor 楼层
     */
    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    /**
     * 面积
     * @return Square 面积
     */
    public String getSquare() {
        return square;
    }

    /**
     * 面积
     * @param square 面积
     */
    public void setSquare(String square) {
        this.square = square == null ? null : square.trim();
    }

    /**
     * 登记时间
     * @return Register 登记时间
     */
    public String getRegister() {
        return register;
    }

    /**
     * 登记时间
     * @param register 登记时间
     */
    public void setRegister(String register) {
        this.register = register == null ? null : register.trim();
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