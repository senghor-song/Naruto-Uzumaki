package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class TrainBase implements Serializable {
    /** 培训底图 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 图片地址 */
    private String image;

    /** html模板内容 */
    private String content;

    /**
     * TrainBase
     */
    private static final long serialVersionUID = 1L;

    /**
     * 培训底图
     * @return ID 培训底图
     */
    public String getId() {
        return id;
    }

    /**
     * 培训底图
     * @param id 培训底图
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
     * 图片地址
     * @return Image 图片地址
     */
    public String getImage() {
        return image;
    }

    /**
     * 图片地址
     * @param image 图片地址
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * html模板内容
     * @return Content html模板内容
     */
    public String getContent() {
        return content;
    }

    /**
     * html模板内容
     * @param content html模板内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}