package com.ruiec.springboot.pojo;

public class VarietyResources {
    /**编号*/
    private Long id;

    /**综艺ID*/
    private Long varietyId;

    /**节目名称（xx月xx日：标题）*/
    private String programName;

    /**清晰度*/
    private String clarity;

    /**视频大小*/
    private Double size;

    /**下载方式(1迅雷2百度云)*/
    private Boolean downlodMode;

    /**资源种子或资源链接*/
    private String downlodLink;

    /**创建时间*/
    private Long createOn;

    /**编号*/
    public Long getId() {
        return id;
    }

    /**编号*/
    public void setId(Long id) {
        this.id = id;
    }

    /**综艺ID*/
    public Long getVarietyId() {
        return varietyId;
    }

    /**综艺ID*/
    public void setVarietyId(Long varietyId) {
        this.varietyId = varietyId;
    }

    /**节目名称（xx月xx日：标题）*/
    public String getProgramName() {
        return programName;
    }

    /**节目名称（xx月xx日：标题）*/
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    /**清晰度*/
    public String getClarity() {
        return clarity;
    }

    /**清晰度*/
    public void setClarity(String clarity) {
        this.clarity = clarity;
    }

    /**视频大小*/
    public Double getSize() {
        return size;
    }

    /**视频大小*/
    public void setSize(Double size) {
        this.size = size;
    }

    /**下载方式(1迅雷2百度云)*/
    public Boolean getDownlodMode() {
        return downlodMode;
    }

    /**下载方式(1迅雷2百度云)*/
    public void setDownlodMode(Boolean downlodMode) {
        this.downlodMode = downlodMode;
    }

    /**资源种子或资源链接*/
    public String getDownlodLink() {
        return downlodLink;
    }

    /**资源种子或资源链接*/
    public void setDownlodLink(String downlodLink) {
        this.downlodLink = downlodLink;
    }

    /**创建时间*/
    public Long getCreateOn() {
        return createOn;
    }

    /**创建时间*/
    public void setCreateOn(Long createOn) {
        this.createOn = createOn;
    }
}