package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 小区表实体
 */
public class EstateCorrect implements Serializable {
    /** 租售->小区信息纠错 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 小区ID */
    private String estateid;

    /** 小区名(辅助) */
    private String estatename;

    /** 项 */
    private String item;

    /** 内容 */
    private String content;

    /** 提出者标识 (经纪人填ID) 和内容同时唯一 */
    private String presenter;

    /**
     * EstateCorrect
     */
    private static final long serialVersionUID = 1L;

    /**
     * 租售->小区信息纠错
     * @return ID 租售->小区信息纠错
     */
    public String getId() {
        return id;
    }

    /**
     * 租售->小区信息纠错
     * @param id 租售->小区信息纠错
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
     * 小区ID
     * @return EstateID 小区ID
     */
    public String getEstateid() {
        return estateid;
    }

    /**
     * 小区ID
     * @param estateid 小区ID
     */
    public void setEstateid(String estateid) {
        this.estateid = estateid == null ? null : estateid.trim();
    }

    /**
     * 小区名(辅助)
     * @return EstateName 小区名(辅助)
     */
    public String getEstatename() {
        return estatename;
    }

    /**
     * 小区名(辅助)
     * @param estatename 小区名(辅助)
     */
    public void setEstatename(String estatename) {
        this.estatename = estatename == null ? null : estatename.trim();
    }

    /**
     * 项
     * @return Item 项
     */
    public String getItem() {
        return item;
    }

    /**
     * 项
     * @param item 项
     */
    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
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

    /**
     * 提出者标识 (经纪人填ID) 和内容同时唯一
     * @return Presenter 提出者标识 (经纪人填ID) 和内容同时唯一
     */
    public String getPresenter() {
        return presenter;
    }

    /**
     * 提出者标识 (经纪人填ID) 和内容同时唯一
     * @param presenter 提出者标识 (经纪人填ID) 和内容同时唯一
     */
    public void setPresenter(String presenter) {
        this.presenter = presenter == null ? null : presenter.trim();
    }
}