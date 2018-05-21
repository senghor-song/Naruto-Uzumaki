package com.ruiec.springboot.pojo;

public class TvResources {
    /**编号*/
    private Long id;

    /**电视剧ID*/
    private Long tvid;

    /**集数*/
    private Integer episodes;

    /**清晰度*/
    private String clarity;

    /**视频大小*/
    private Double size;

    /**下载方式(1迅雷2百度云)*/
    private Boolean downlodmode;

    /**资源种子或资源链接*/
    private String downlodlink;

    /**创建时间*/
    private Long createon;
    
    /** 电视剧 */
    private Tv tv;

    /**编号*/
    public Long getId() {
        return id;
    }

    /**编号*/
    public void setId(Long id) {
        this.id = id;
    }

    /**电视剧ID*/
    public Long getTvid() {
        return tvid;
    }

    /**电视剧ID*/
    public void setTvid(Long tvid) {
        this.tvid = tvid;
    }

    /**集数*/
    public Integer getEpisodes() {
        return episodes;
    }

    /**集数*/
    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
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
    public Boolean getDownlodmode() {
        return downlodmode;
    }

    /**下载方式(1迅雷2百度云)*/
    public void setDownlodmode(Boolean downlodmode) {
        this.downlodmode = downlodmode;
    }

    /**资源种子或资源链接*/
    public String getDownlodlink() {
        return downlodlink;
    }

    /**资源种子或资源链接*/
    public void setDownlodlink(String downlodlink) {
        this.downlodlink = downlodlink;
    }

    /**创建时间*/
    public Long getCreateon() {
        return createon;
    }

    /**创建时间*/
    public void setCreateon(Long createon) {
        this.createon = createon;
    }

    /** 电视剧 */
	public Tv getTv() {
		return tv;
	}

	 /** 电视剧 */
	public void setTv(Tv tv) {
		this.tv = tv;
	}
}