package com.xiaoyi.ssm.model;

import java.io.Serializable;

public class MassEstateXray implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 推房->小区透视房源采集（采集时间毫秒级）
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
     * 入库时间
     */
    private String register;

    /**
     * 房源地址
     */
    private String propertyurl;

    /**
     * 经纪公司
     */
    private String company;

    /**
     * 经纪人
     */
    private String emp;

    /**
     * 标题
     */
    private String title;

    /**
     * 分类ID
     */
    private String category;

    /**
     * 
     */
    private String remark;

    /**
     * 推房->小区透视房源采集（采集时间毫秒级）
     * @return ID 推房->小区透视房源采集（采集时间毫秒级）
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->小区透视房源采集（采集时间毫秒级）
     * @param id 推房->小区透视房源采集（采集时间毫秒级）
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
     * 入库时间
     * @return Register 入库时间
     */
    public String getRegister() {
        return register;
    }

    /**
     * 入库时间
     * @param register 入库时间
     */
    public void setRegister(String register) {
        this.register = register == null ? null : register.trim();
    }

    /**
     * 房源地址
     * @return PropertyUrl 房源地址
     */
    public String getPropertyurl() {
        return propertyurl;
    }

    /**
     * 房源地址
     * @param propertyurl 房源地址
     */
    public void setPropertyurl(String propertyurl) {
        this.propertyurl = propertyurl == null ? null : propertyurl.trim();
    }

    /**
     * 经纪公司
     * @return Company 经纪公司
     */
    public String getCompany() {
        return company;
    }

    /**
     * 经纪公司
     * @param company 经纪公司
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * 经纪人
     * @return Emp 经纪人
     */
    public String getEmp() {
        return emp;
    }

    /**
     * 经纪人
     * @param emp 经纪人
     */
    public void setEmp(String emp) {
        this.emp = emp == null ? null : emp.trim();
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
     * 分类ID
     * @return Category 分类ID
     */
    public String getCategory() {
        return category;
    }

    /**
     * 分类ID
     * @param category 分类ID
     */
    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
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