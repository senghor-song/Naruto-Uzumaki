package com.ruiec.springboot.pojo;

public class Variety {
    /**编号*/
    private Long id;

    /**综艺名称*/
    private Long name;

    /**又名*/
    private String asname;

    /**是否为热门(0否1是)*/
    private Boolean ishot;

    /**最近更新一期的名称*/
    private String recent;

    /**更新周期*/
    private String updatecycle;

    /**演员*/
    private String actors;

    /**类型ID*/
    private Integer typeid;

    /**地区ID*/
    private Integer areaid;

    /**导演*/
    private String director;

    /**上映年份*/
    private Integer year;

    /**上映日期*/
    private String releasedate;

    /**更新日期*/
    private String updateddate;

    /**时长*/
    private Integer timesize;

    /**豆瓣评分*/
    private Double watercressscore;

    /**剧情介绍*/
    private String synopsis;

    /**视频截图*/
    private String videocapture;

    /**创建时间*/
    private Long createon;

    /**编号*/
    public Long getId() {
        return id;
    }

    /**编号*/
    public void setId(Long id) {
        this.id = id;
    }

    /**综艺名称*/
    public Long getName() {
        return name;
    }

    /**综艺名称*/
    public void setName(Long name) {
        this.name = name;
    }

    /**又名*/
    public String getAsname() {
        return asname;
    }

    /**又名*/
    public void setAsname(String asname) {
        this.asname = asname;
    }

    /**是否为热门(0否1是)*/
    public Boolean getIshot() {
        return ishot;
    }

    /**是否为热门(0否1是)*/
    public void setIshot(Boolean ishot) {
        this.ishot = ishot;
    }

    /**最近更新一期的名称*/
    public String getRecent() {
        return recent;
    }

    /**最近更新一期的名称*/
    public void setRecent(String recent) {
        this.recent = recent;
    }

    /**更新周期*/
    public String getUpdatecycle() {
        return updatecycle;
    }

    /**更新周期*/
    public void setUpdatecycle(String updatecycle) {
        this.updatecycle = updatecycle;
    }

    /**演员*/
    public String getActors() {
        return actors;
    }

    /**演员*/
    public void setActors(String actors) {
        this.actors = actors;
    }

    /**类型ID*/
    public Integer getTypeid() {
        return typeid;
    }

    /**类型ID*/
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**地区ID*/
    public Integer getAreaid() {
        return areaid;
    }

    /**地区ID*/
    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    /**导演*/
    public String getDirector() {
        return director;
    }

    /**导演*/
    public void setDirector(String director) {
        this.director = director;
    }

    /**上映年份*/
    public Integer getYear() {
        return year;
    }

    /**上映年份*/
    public void setYear(Integer year) {
        this.year = year;
    }

    /**上映日期*/
    public String getReleasedate() {
        return releasedate;
    }

    /**上映日期*/
    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    /**更新日期*/
    public String getUpdateddate() {
        return updateddate;
    }

    /**更新日期*/
    public void setUpdateddate(String updateddate) {
        this.updateddate = updateddate;
    }

    /**时长*/
    public Integer getTimesize() {
        return timesize;
    }

    /**时长*/
    public void setTimesize(Integer timesize) {
        this.timesize = timesize;
    }

    /**豆瓣评分*/
    public Double getWatercressscore() {
        return watercressscore;
    }

    /**豆瓣评分*/
    public void setWatercressscore(Double watercressscore) {
        this.watercressscore = watercressscore;
    }

    /**剧情介绍*/
    public String getSynopsis() {
        return synopsis;
    }

    /**剧情介绍*/
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**视频截图*/
    public String getVideocapture() {
        return videocapture;
    }

    /**视频截图*/
    public void setVideocapture(String videocapture) {
        this.videocapture = videocapture;
    }

    /**创建时间*/
    public Long getCreateon() {
        return createon;
    }

    /**创建时间*/
    public void setCreateon(Long createon) {
        this.createon = createon;
    }
}