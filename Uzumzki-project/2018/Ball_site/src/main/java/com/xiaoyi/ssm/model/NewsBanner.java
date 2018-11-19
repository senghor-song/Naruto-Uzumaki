package com.xiaoyi.ssm.model;

import java.io.Serializable;

/**
 * 新闻表实体
 */
public class NewsBanner implements Serializable {
    /** 运营->横幅新闻容器 */
    private String id;

    /** 容器编号 */
    private Integer bannerno;

    /** 容器位置描述 */
    private String banner;

    /** 展示平台 */
    private String showway;

    /** 封面URL */
    private String coverpath;

    /** 内容URL */
    private String contentpath;

    /** 备注 */
    private String remark;

    /**
     * NewsBanner
     */
    private static final long serialVersionUID = 1L;

    /**
     * 运营->横幅新闻容器
     * @return ID 运营->横幅新闻容器
     */
    public String getId() {
        return id;
    }

    /**
     * 运营->横幅新闻容器
     * @param id 运营->横幅新闻容器
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 容器编号
     * @return BannerNO 容器编号
     */
    public Integer getBannerno() {
        return bannerno;
    }

    /**
     * 容器编号
     * @param bannerno 容器编号
     */
    public void setBannerno(Integer bannerno) {
        this.bannerno = bannerno;
    }

    /**
     * 容器位置描述
     * @return Banner 容器位置描述
     */
    public String getBanner() {
        return banner;
    }

    /**
     * 容器位置描述
     * @param banner 容器位置描述
     */
    public void setBanner(String banner) {
        this.banner = banner == null ? null : banner.trim();
    }

    /**
     * 展示平台
     * @return ShowWay 展示平台
     */
    public String getShowway() {
        return showway;
    }

    /**
     * 展示平台
     * @param showway 展示平台
     */
    public void setShowway(String showway) {
        this.showway = showway == null ? null : showway.trim();
    }

    /**
     * 封面URL
     * @return CoverPath 封面URL
     */
    public String getCoverpath() {
        return coverpath;
    }

    /**
     * 封面URL
     * @param coverpath 封面URL
     */
    public void setCoverpath(String coverpath) {
        this.coverpath = coverpath == null ? null : coverpath.trim();
    }

    /**
     * 内容URL
     * @return ContentPath 内容URL
     */
    public String getContentpath() {
        return contentpath;
    }

    /**
     * 内容URL
     * @param contentpath 内容URL
     */
    public void setContentpath(String contentpath) {
        this.contentpath = contentpath == null ? null : contentpath.trim();
    }

    /**
     * 备注
     * @return Remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}