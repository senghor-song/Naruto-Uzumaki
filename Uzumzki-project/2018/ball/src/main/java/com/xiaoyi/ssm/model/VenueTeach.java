package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class VenueTeach implements Serializable {
    /** 场地教学表 */
    private String id;

    /** 创建时间 */
    private Date createtime;

    /** 内容 */
    private String content;

    /** 是否设置(0=否1=是) */
    private Integer setting;

    /** 管理员ID */
    private String managerid;

    /** 教学时间段 */
    private String venuetimeframe;

    /**
     * VenueTeach
     */
    private static final long serialVersionUID = 1L;

    /**
     * 场地教学表
     * @return ID 场地教学表
     */
    public String getId() {
        return id;
    }

    /**
     * 场地教学表
     * @param id 场地教学表
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
     * 是否设置(0=否1=是)
     * @return Setting 是否设置(0=否1=是)
     */
    public Integer getSetting() {
        return setting;
    }

    /**
     * 是否设置(0=否1=是)
     * @param setting 是否设置(0=否1=是)
     */
    public void setSetting(Integer setting) {
        this.setting = setting;
    }

    /**
     * 管理员ID
     * @return ManagerID 管理员ID
     */
    public String getManagerid() {
        return managerid;
    }

    /**
     * 管理员ID
     * @param managerid 管理员ID
     */
    public void setManagerid(String managerid) {
        this.managerid = managerid == null ? null : managerid.trim();
    }

    /**
     * 教学时间段
     * @return VenueTimeFrame 教学时间段
     */
    public String getVenuetimeframe() {
        return venuetimeframe;
    }

    /**
     * 教学时间段
     * @param venuetimeframe 教学时间段
     */
    public void setVenuetimeframe(String venuetimeframe) {
        this.venuetimeframe = venuetimeframe == null ? null : venuetimeframe.trim();
    }
}