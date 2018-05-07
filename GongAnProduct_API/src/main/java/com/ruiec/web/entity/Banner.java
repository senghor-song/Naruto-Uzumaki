/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ruiec.framework.server.support.entity.BaseEntity;

/**
 * 首页滚动图片管理
 * @author Senghor<br>
 * @date 2017年11月28日 下午3:56:03
 */
@Entity
@Table(name="T_SYS_BANNER")
public class Banner extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** 图片 */
    private String image;
    
    /** 超链接地址 */
    private String link;
    
    /** 宽度 */
    private String width;
    
    /** 高度 */
    private String height;
    
    /** 是否显示 */
    private Integer isshow;
    
    /** 是否是新图片 */
    private Integer isNew;
   
    /** 是否在app显示 */
    private Integer isShowApp;


    /** 图片 */
    @Column(name="IMAGE", nullable=false)
    public String getImage() {
        return this.image;
    }

    /** 图片 */
    public void setImage(String image) {
        this.image = image;
    }

    /** 超链接地址 */
    @Column(name="LINK")
    public String getLink() {
        return this.link;
    }

    /**  超链接地址 */
    public void setLink(String link) {
        this.link = link;
    }

    /** 宽度 */
    @Column(name="WIDTH")
    public String getWidth() {
        return this.width;
    }

    /** 宽度 */
    public void setWidth(String width) {
        this.width = width;
    }

    /** 高度 */
    @Column(name="HEIGHT")
    public String getHeight() {
        return this.height;
    }

    /** 高度 */
    public void setHeight(String height) {
        this.height = height;
    }

    /** 是否显示 */
    @Column(name="ISSHOW", nullable=false, precision=22, scale=0)
    public Integer getIsshow() {
        return this.isshow;
    }

    /** 是否显示 */
    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

	/** 是否是新图片 */
    @Column(name="IS_NEW",  precision=22, scale=0)
	public Integer getIsNew() {
		return isNew;
	}

	/** 是否是新图片 */
	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	/** 是否在app显示 */
    @Column(name="IS_SHOW_APP", precision=22, scale=0)
	public Integer getIsShowApp() {
		return isShowApp;
	}

	/** 是否在app显示 */
	public void setIsShowApp(Integer isShowApp) {
		this.isShowApp = isShowApp;
	}

}