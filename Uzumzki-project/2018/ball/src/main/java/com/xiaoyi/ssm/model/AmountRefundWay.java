package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 退款方式表实体
 */
public class AmountRefundWay implements Serializable {
    /** ID和场馆ID主键关联 */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** <2小时内费率 */
    private Integer fee1;

    /** 2-4小时内费率 */
    private Integer fee2;

    /** 4-6小时内费率 */
    private Integer fee3;

    /** 天气原因开始前 */
    private Integer weatherStart;

    /** 天气原因开始后 */
    private Integer weatherEnd;

    /**
     * AmountRefundWay
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID和场馆ID主键关联
     * @return ID ID和场馆ID主键关联
     */
    public String getId() {
        return id;
    }

    /**
     * ID和场馆ID主键关联
     * @param id ID和场馆ID主键关联
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 创建时间
     * @return Create_Time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     * @return Modify_Time 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * <2小时内费率
     * @return Fee1 <2小时内费率
     */
    public Integer getFee1() {
        return fee1;
    }

    /**
     * <2小时内费率
     * @param fee1 <2小时内费率
     */
    public void setFee1(Integer fee1) {
        this.fee1 = fee1;
    }

    /**
     * 2-4小时内费率
     * @return Fee2 2-4小时内费率
     */
    public Integer getFee2() {
        return fee2;
    }

    /**
     * 2-4小时内费率
     * @param fee2 2-4小时内费率
     */
    public void setFee2(Integer fee2) {
        this.fee2 = fee2;
    }

    /**
     * 4-6小时内费率
     * @return Fee3 4-6小时内费率
     */
    public Integer getFee3() {
        return fee3;
    }

    /**
     * 4-6小时内费率
     * @param fee3 4-6小时内费率
     */
    public void setFee3(Integer fee3) {
        this.fee3 = fee3;
    }

    /**
     * 天气原因开始前
     * @return Weather_start 天气原因开始前
     */
    public Integer getWeatherStart() {
        return weatherStart;
    }

    /**
     * 天气原因开始前
     * @param weatherStart 天气原因开始前
     */
    public void setWeatherStart(Integer weatherStart) {
        this.weatherStart = weatherStart;
    }

    /**
     * 天气原因开始后
     * @return weather_end 天气原因开始后
     */
    public Integer getWeatherEnd() {
        return weatherEnd;
    }

    /**
     * 天气原因开始后
     * @param weatherEnd 天气原因开始后
     */
    public void setWeatherEnd(Integer weatherEnd) {
        this.weatherEnd = weatherEnd;
    }
}