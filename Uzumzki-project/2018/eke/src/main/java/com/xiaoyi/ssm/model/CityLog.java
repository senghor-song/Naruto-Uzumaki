package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class CityLog implements Serializable {
    /** 系统->城市日志(包含区县及片区) */
    private String id;

    /** 城市ID */
    private String cityid;

    /** （辅助） */
    private String city;

    /** 日志时间 */
    private Date logtime;

    /** 内容 */
    private String content;

    /**
     * CityLog
     */
    private static final long serialVersionUID = 1L;

    /**
     * 系统->城市日志(包含区县及片区)
     * @return ID 系统->城市日志(包含区县及片区)
     */
    public String getId() {
        return id;
    }

    /**
     * 系统->城市日志(包含区县及片区)
     * @param id 系统->城市日志(包含区县及片区)
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 城市ID
     * @return CityID 城市ID
     */
    public String getCityid() {
        return cityid;
    }

    /**
     * 城市ID
     * @param cityid 城市ID
     */
    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    /**
     * （辅助）
     * @return City （辅助）
     */
    public String getCity() {
        return city;
    }

    /**
     * （辅助）
     * @param city （辅助）
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 日志时间
     * @return LogTime 日志时间
     */
    public Date getLogtime() {
        return logtime;
    }

    /**
     * 日志时间
     * @param logtime 日志时间
     */
    public void setLogtime(Date logtime) {
        this.logtime = logtime;
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