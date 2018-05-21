package com.ruiec.springboot.pojo;

public class Movie {
    /**编号*/
    private Long id;

    /**电影名称*/
    private String name;

    /**又名*/
    private String asName;
    
    /**封面图片*/
    private String coverImg;

    /**是否为热门(0否1是)*/
    private Boolean isHot;

    /**简要介绍*/
    private String brief;

    /**演员*/
    private String actors;

    /**电影类型ID*/
    private Integer typeId;

    /**电影语言ID*/
    private Integer languageId;

    /**电影地区ID*/
    private Integer areaId;

    /**上映年份*/
    private Integer year;

    /**导演*/
    private String director;

    /**上映日期*/
    private String releaseDate;

    /**更新日期*/
    private String updatedDate;

    /**电影时长*/
    private Integer timeSize;

    /**豆瓣评分*/
    private Double watercressScore;

    /**剧情介绍*/
    private String synopsis;

    /**特殊标签*/
    private String specialLabel;

    /**视频截图*/
    private String videoCapture;

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

    /**电影名称*/
    public String getName() {
        return name;
    }

    /**电影名称*/
    public void setName(String name) {
        this.name = name;
    }

    /**又名*/
    public String getAsName() {
        return asName;
    }

    /**又名*/
    public void setAsName(String asName) {
        this.asName = asName;
    }
    
    /**封面图片*/
    public String getCoverImg() {
		return coverImg;
	}
    
    /**封面图片*/
	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}

	/**是否为热门(0否1是)*/
    public Boolean getIsHot() {
        return isHot;
    }

    /**是否为热门(0否1是)*/
    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    /**简要介绍*/
    public String getBrief() {
        return brief;
    }

    /**简要介绍*/
    public void setBrief(String brief) {
        this.brief = brief;
    }

    /**演员*/
    public String getActors() {
        return actors;
    }

    /**演员*/
    public void setActors(String actors) {
        this.actors = actors;
    }

    /**电影类型ID*/
    public Integer getTypeId() {
        return typeId;
    }

    /**电影类型ID*/
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**电影语言ID*/
    public Integer getLanguageId() {
        return languageId;
    }

    /**电影语言ID*/
    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    /**电影地区ID*/
    public Integer getAreaId() {
        return areaId;
    }

    /**电影地区ID*/
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**上映年份*/
    public Integer getYear() {
        return year;
    }

    /**上映年份*/
    public void setYear(Integer year) {
        this.year = year;
    }

    /**导演*/
    public String getDirector() {
        return director;
    }

    /**导演*/
    public void setDirector(String director) {
        this.director = director;
    }

    /**上映日期*/
    public String getReleaseDate() {
        return releaseDate;
    }

    /**上映日期*/
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**更新日期*/
    public String getUpdatedDate() {
        return updatedDate;
    }

    /**更新日期*/
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**电影时长*/
    public Integer getTimeSize() {
        return timeSize;
    }

    /**电影时长*/
    public void setTimeSize(Integer timeSize) {
        this.timeSize = timeSize;
    }

    /**豆瓣评分*/
    public Double getWatercressScore() {
        return watercressScore;
    }

    /**豆瓣评分*/
    public void setWatercressScore(Double watercressScore) {
        this.watercressScore = watercressScore;
    }

    /**剧情介绍*/
    public String getSynopsis() {
        return synopsis;
    }

    /**剧情介绍*/
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**特殊标签*/
    public String getSpecialLabel() {
        return specialLabel;
    }

    /**特殊标签*/
    public void setSpeciallabel(String specialLabel) {
        this.specialLabel = specialLabel;
    }

    /**视频截图*/
    public String getVideoCapture() {
        return videoCapture;
    }

    /**视频截图*/
    public void setVideoCapture(String videoCapture) {
        this.videoCapture = videoCapture;
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