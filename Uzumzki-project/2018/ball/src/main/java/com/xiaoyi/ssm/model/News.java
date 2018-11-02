package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 新闻资讯表实体
 */
public class News implements Serializable {
	private Integer collectFlag;//0=未收藏1=已收藏
	
    public Integer getCollectFlag() {
		return collectFlag;
	}

	public void setCollectFlag(Integer collectFlag) {
		this.collectFlag = collectFlag;
	}

	/** ID */
    private String id;

    /** 资讯编号 */
    private Integer newsNo;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 标题 */
    private String title;

    /** 摘要 */
    private String contentSimple;

    /** 封面图 */
    private String headImage;

    /** 使能0=否1=是 */
    private Integer showFlag;

    /** 备注 */
    private String remark;

    /** 内容 */
    private String content;

    /**
     * News
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * @return ID ID
     */
    public String getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 资讯编号
     * @return News_no 资讯编号
     */
    public Integer getNewsNo() {
        return newsNo;
    }

    /**
     * 资讯编号
     * @param newsNo 资讯编号
     */
    public void setNewsNo(Integer newsNo) {
        this.newsNo = newsNo;
    }

    /**
     * 创建时间
     * @return Create_time 创建时间
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
     * @return Modify_time 修改时间
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
     * 摘要
     * @return Content_simple 摘要
     */
    public String getContentSimple() {
        return contentSimple;
    }

    /**
     * 摘要
     * @param contentSimple 摘要
     */
    public void setContentSimple(String contentSimple) {
        this.contentSimple = contentSimple == null ? null : contentSimple.trim();
    }

    /**
     * 封面图
     * @return Head_image 封面图
     */
    public String getHeadImage() {
        return headImage;
    }

    /**
     * 封面图
     * @param headImage 封面图
     */
    public void setHeadImage(String headImage) {
        this.headImage = headImage == null ? null : headImage.trim();
    }

    /**
     * 使能0=否1=是
     * @return Show_flag 使能0=否1=是
     */
    public Integer getShowFlag() {
        return showFlag;
    }

    /**
     * 使能0=否1=是
     * @param showFlag 使能0=否1=是
     */
    public void setShowFlag(Integer showFlag) {
        this.showFlag = showFlag;
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

    /**
     * 内容
     * @return Content 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}