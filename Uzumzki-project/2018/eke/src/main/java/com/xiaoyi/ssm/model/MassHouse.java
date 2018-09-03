package com.xiaoyi.ssm.model;

import java.io.Serializable;

public class MassHouse implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 房源录入->住宅表
     */
    private String id;

    /**
     * 房屋类别
     */
    private String housetype;

    /**
     * 住宅子类型
     */
    private String housesubtype;

    /**
     * 房屋产权
     */
    private String houseproperty;

    /**
     * 产权年限
     */
    private String landyear;

    /**
     * 房屋结构
     */
    private String housestructure;

    /**
     * 房屋年限(0=满两年1=满五年2=不满两年)
     */
    private Integer houseyear;

    /**
     * 是否唯一住房(0=否1=是)
     */
    private Integer onlyhouse;

    /**
     * 基础设施(逗号隔开)
     */
    private String basicequip;

    /**
     * 配套设施(逗号隔开)
     */
    private String advequip;

    /**
     * 房源录入->住宅表
     * @return ID 房源录入->住宅表
     */
    public String getId() {
        return id;
    }

    /**
     * 房源录入->住宅表
     * @param id 房源录入->住宅表
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 房屋类别
     * @return HouseType 房屋类别
     */
    public String getHousetype() {
        return housetype;
    }

    /**
     * 房屋类别
     * @param housetype 房屋类别
     */
    public void setHousetype(String housetype) {
        this.housetype = housetype == null ? null : housetype.trim();
    }

    /**
     * 住宅子类型
     * @return HouseSubType 住宅子类型
     */
    public String getHousesubtype() {
        return housesubtype;
    }

    /**
     * 住宅子类型
     * @param housesubtype 住宅子类型
     */
    public void setHousesubtype(String housesubtype) {
        this.housesubtype = housesubtype == null ? null : housesubtype.trim();
    }

    /**
     * 房屋产权
     * @return HouseProperty 房屋产权
     */
    public String getHouseproperty() {
        return houseproperty;
    }

    /**
     * 房屋产权
     * @param houseproperty 房屋产权
     */
    public void setHouseproperty(String houseproperty) {
        this.houseproperty = houseproperty == null ? null : houseproperty.trim();
    }

    /**
     * 产权年限
     * @return LandYear 产权年限
     */
    public String getLandyear() {
        return landyear;
    }

    /**
     * 产权年限
     * @param landyear 产权年限
     */
    public void setLandyear(String landyear) {
        this.landyear = landyear == null ? null : landyear.trim();
    }

    /**
     * 房屋结构
     * @return HouseStructure 房屋结构
     */
    public String getHousestructure() {
        return housestructure;
    }

    /**
     * 房屋结构
     * @param housestructure 房屋结构
     */
    public void setHousestructure(String housestructure) {
        this.housestructure = housestructure == null ? null : housestructure.trim();
    }

    /**
     * 房屋年限(0=满两年1=满五年2=不满两年)
     * @return HouseYear 房屋年限(0=满两年1=满五年2=不满两年)
     */
    public Integer getHouseyear() {
        return houseyear;
    }

    /**
     * 房屋年限(0=满两年1=满五年2=不满两年)
     * @param houseyear 房屋年限(0=满两年1=满五年2=不满两年)
     */
    public void setHouseyear(Integer houseyear) {
        this.houseyear = houseyear;
    }

    /**
     * 是否唯一住房(0=否1=是)
     * @return OnlyHouse 是否唯一住房(0=否1=是)
     */
    public Integer getOnlyhouse() {
        return onlyhouse;
    }

    /**
     * 是否唯一住房(0=否1=是)
     * @param onlyhouse 是否唯一住房(0=否1=是)
     */
    public void setOnlyhouse(Integer onlyhouse) {
        this.onlyhouse = onlyhouse;
    }

    /**
     * 基础设施(逗号隔开)
     * @return BasicEquip 基础设施(逗号隔开)
     */
    public String getBasicequip() {
        return basicequip;
    }

    /**
     * 基础设施(逗号隔开)
     * @param basicequip 基础设施(逗号隔开)
     */
    public void setBasicequip(String basicequip) {
        this.basicequip = basicequip == null ? null : basicequip.trim();
    }

    /**
     * 配套设施(逗号隔开)
     * @return AdvEquip 配套设施(逗号隔开)
     */
    public String getAdvequip() {
        return advequip;
    }

    /**
     * 配套设施(逗号隔开)
     * @param advequip 配套设施(逗号隔开)
     */
    public void setAdvequip(String advequip) {
        this.advequip = advequip == null ? null : advequip.trim();
    }
}