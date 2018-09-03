package com.xiaoyi.ssm.model;

import java.io.Serializable;

public class MassEmpSet implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 推房->房源
     */
    private String id;

    /**
     * 经纪人ID
     */
    private String empid;

    /**
     * 允许推送满时删除一条旧房源再发布的网站
     */
    private Byte old;

    /**
     * 网站房源重复发送处理设置:（预约发布时，统一为先删除后发送）
     */
    private String repeat;

    /**
     * 发布房源至58时，默认推广天数
     */
    private Integer days;

    /**
     * 安居客、58 非手机标签发布，默认不删除精推和置顶房源
     */
    private Byte top;

    /**
     * 推送房源时，是否在房源图片上加入‘效果图’水印
     */
    private Byte designsketch;

    /**
     * 发布房源时，房源描述后面增加业主心态等信息
     */
    private Byte mentality;

    /**
     * 发布房源时，使用动态封面图(新浪网)
     */
    private Byte dynamic;

    /**
     * 发布房源至58(付费端口)，使用小蜜书的手机号和姓名作为联系方式
     */
    private Byte contact;

    /**
     * 排班发布-禁止加入
     */
    private Byte release;

    /**
     * 排班刷新-禁止加入
     */
    private Byte refresh;

    /**
     * 秒录站点
     */
    private String quick;

    /**
     * 水 印
     */
    private Byte watermark;

    /**
     * 透明度
     */
    private Float transparency;

    /**
     * 
     */
    private String remark;

    /**
     * 推房->房源
     * @return ID 推房->房源
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->房源
     * @param id 推房->房源
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
     * 允许推送满时删除一条旧房源再发布的网站
     * @return Old 允许推送满时删除一条旧房源再发布的网站
     */
    public Byte getOld() {
        return old;
    }

    /**
     * 允许推送满时删除一条旧房源再发布的网站
     * @param old 允许推送满时删除一条旧房源再发布的网站
     */
    public void setOld(Byte old) {
        this.old = old;
    }

    /**
     * 网站房源重复发送处理设置:（预约发布时，统一为先删除后发送）
     * @return Repeat 网站房源重复发送处理设置:（预约发布时，统一为先删除后发送）
     */
    public String getRepeat() {
        return repeat;
    }

    /**
     * 网站房源重复发送处理设置:（预约发布时，统一为先删除后发送）
     * @param repeat 网站房源重复发送处理设置:（预约发布时，统一为先删除后发送）
     */
    public void setRepeat(String repeat) {
        this.repeat = repeat == null ? null : repeat.trim();
    }

    /**
     * 发布房源至58时，默认推广天数
     * @return Days 发布房源至58时，默认推广天数
     */
    public Integer getDays() {
        return days;
    }

    /**
     * 发布房源至58时，默认推广天数
     * @param days 发布房源至58时，默认推广天数
     */
    public void setDays(Integer days) {
        this.days = days;
    }

    /**
     * 安居客、58 非手机标签发布，默认不删除精推和置顶房源
     * @return Top 安居客、58 非手机标签发布，默认不删除精推和置顶房源
     */
    public Byte getTop() {
        return top;
    }

    /**
     * 安居客、58 非手机标签发布，默认不删除精推和置顶房源
     * @param top 安居客、58 非手机标签发布，默认不删除精推和置顶房源
     */
    public void setTop(Byte top) {
        this.top = top;
    }

    /**
     * 推送房源时，是否在房源图片上加入‘效果图’水印
     * @return DesignSketch 推送房源时，是否在房源图片上加入‘效果图’水印
     */
    public Byte getDesignsketch() {
        return designsketch;
    }

    /**
     * 推送房源时，是否在房源图片上加入‘效果图’水印
     * @param designsketch 推送房源时，是否在房源图片上加入‘效果图’水印
     */
    public void setDesignsketch(Byte designsketch) {
        this.designsketch = designsketch;
    }

    /**
     * 发布房源时，房源描述后面增加业主心态等信息
     * @return Mentality 发布房源时，房源描述后面增加业主心态等信息
     */
    public Byte getMentality() {
        return mentality;
    }

    /**
     * 发布房源时，房源描述后面增加业主心态等信息
     * @param mentality 发布房源时，房源描述后面增加业主心态等信息
     */
    public void setMentality(Byte mentality) {
        this.mentality = mentality;
    }

    /**
     * 发布房源时，使用动态封面图(新浪网)
     * @return Dynamic 发布房源时，使用动态封面图(新浪网)
     */
    public Byte getDynamic() {
        return dynamic;
    }

    /**
     * 发布房源时，使用动态封面图(新浪网)
     * @param dynamic 发布房源时，使用动态封面图(新浪网)
     */
    public void setDynamic(Byte dynamic) {
        this.dynamic = dynamic;
    }

    /**
     * 发布房源至58(付费端口)，使用小蜜书的手机号和姓名作为联系方式
     * @return Contact 发布房源至58(付费端口)，使用小蜜书的手机号和姓名作为联系方式
     */
    public Byte getContact() {
        return contact;
    }

    /**
     * 发布房源至58(付费端口)，使用小蜜书的手机号和姓名作为联系方式
     * @param contact 发布房源至58(付费端口)，使用小蜜书的手机号和姓名作为联系方式
     */
    public void setContact(Byte contact) {
        this.contact = contact;
    }

    /**
     * 排班发布-禁止加入
     * @return Release 排班发布-禁止加入
     */
    public Byte getRelease() {
        return release;
    }

    /**
     * 排班发布-禁止加入
     * @param release 排班发布-禁止加入
     */
    public void setRelease(Byte release) {
        this.release = release;
    }

    /**
     * 排班刷新-禁止加入
     * @return Refresh 排班刷新-禁止加入
     */
    public Byte getRefresh() {
        return refresh;
    }

    /**
     * 排班刷新-禁止加入
     * @param refresh 排班刷新-禁止加入
     */
    public void setRefresh(Byte refresh) {
        this.refresh = refresh;
    }

    /**
     * 秒录站点
     * @return Quick 秒录站点
     */
    public String getQuick() {
        return quick;
    }

    /**
     * 秒录站点
     * @param quick 秒录站点
     */
    public void setQuick(String quick) {
        this.quick = quick == null ? null : quick.trim();
    }

    /**
     * 水 印
     * @return Watermark 水 印
     */
    public Byte getWatermark() {
        return watermark;
    }

    /**
     * 水 印
     * @param watermark 水 印
     */
    public void setWatermark(Byte watermark) {
        this.watermark = watermark;
    }

    /**
     * 透明度
     * @return Transparency 透明度
     */
    public Float getTransparency() {
        return transparency;
    }

    /**
     * 透明度
     * @param transparency 透明度
     */
    public void setTransparency(Float transparency) {
        this.transparency = transparency;
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