package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class Coach implements Serializable {
    /** 教练 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 修改时间 */
    private Date modifytime;

    /** 场馆ID */
    private String venueid;

    /** 姓名 */
    private String name;

    /** 教练图片 */
    private String image;

    /** 每时段单价 */
    private Integer price;

    /** 教练介绍 */
    private String introduce;

    /**
     * Coach
     */
    private static final long serialVersionUID = 1L;

    /**
     * 教练
     * @return ID 教练
     */
    public String getId() {
        return id;
    }

    /**
     * 教练
     * @param id 教练
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 创建时间
     * @return CreateTime 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 修改时间
     * @return ModifyTime 修改时间
     */
    public Date getModifytime() {
        return modifytime;
    }

    /**
     * 修改时间
     * @param modifytime 修改时间
     */
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * 场馆ID
     * @return VenueID 场馆ID
     */
    public String getVenueid() {
        return venueid;
    }

    /**
     * 场馆ID
     * @param venueid 场馆ID
     */
    public void setVenueid(String venueid) {
        this.venueid = venueid == null ? null : venueid.trim();
    }

    /**
     * 姓名
     * @return Name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 教练图片
     * @return Image 教练图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 教练图片
     * @param image 教练图片
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * 每时段单价
     * @return Price 每时段单价
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 每时段单价
     * @param price 每时段单价
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * 教练介绍
     * @return Introduce 教练介绍
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 教练介绍
     * @param introduce 教练介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }
}