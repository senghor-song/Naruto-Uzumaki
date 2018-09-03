package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 */
public class Train implements Serializable {
	/** 城市 */
	private City city;
	/** 场馆 */
	private Venue venue;
	/** 管理员 */
	private Manager manager;
	
    public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}
    /** 培训 */
    private String id;

    /** 编号 */
    private Integer trainno;

    /** 创建时间 */
    private Date createtime;

    /** 修改时间 */
    private Date modifytime;

    /** 场馆ID */
    private String venueid;

    /** 创建人(管理员) */
    private String managerid;

    /** 培训内容 */
    private String contenttext;

    /** 报名费用(单价) */
    private Double price;

    /** 使用底图ID */
    private String trainbaseid;

    /** 生成海报的地址 */
    private String image;

    /** 培训表格 */
    private String content;

    /**
     * Train
     */
    private static final long serialVersionUID = 1L;

    /**
     * 培训
     * @return ID 培训
     */
    public String getId() {
        return id;
    }

    /**
     * 培训
     * @param id 培训
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 编号
     * @return TrainNO 编号
     */
    public Integer getTrainno() {
        return trainno;
    }

    /**
     * 编号
     * @param trainno 编号
     */
    public void setTrainno(Integer trainno) {
        this.trainno = trainno;
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
     * 修改时间
     * @return ModifyTime 修改时间
     */
    public Date getModifytime() {
        return modifytime;
    }

    /**
     * 修改时间
     * @param modifytime 修改时间
     */
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * 场馆ID
     * @return VenueID 场馆ID
     */
    public String getVenueid() {
        return venueid;
    }

    /**
     * 场馆ID
     * @param venueid 场馆ID
     */
    public void setVenueid(String venueid) {
        this.venueid = venueid == null ? null : venueid.trim();
    }

    /**
     * 创建人(管理员)
     * @return ManagerID 创建人(管理员)
     */
    public String getManagerid() {
        return managerid;
    }

    /**
     * 创建人(管理员)
     * @param managerid 创建人(管理员)
     */
    public void setManagerid(String managerid) {
        this.managerid = managerid == null ? null : managerid.trim();
    }

    /**
     * 培训内容
     * @return ContentText 培训内容
     */
    public String getContenttext() {
        return contenttext;
    }

    /**
     * 培训内容
     * @param contenttext 培训内容
     */
    public void setContenttext(String contenttext) {
        this.contenttext = contenttext == null ? null : contenttext.trim();
    }

    /**
     * 报名费用(单价)
     * @return Price 报名费用(单价)
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 报名费用(单价)
     * @param price 报名费用(单价)
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 使用底图ID
     * @return TrainBaseID 使用底图ID
     */
    public String getTrainbaseid() {
        return trainbaseid;
    }

    /**
     * 使用底图ID
     * @param trainbaseid 使用底图ID
     */
    public void setTrainbaseid(String trainbaseid) {
        this.trainbaseid = trainbaseid == null ? null : trainbaseid.trim();
    }

    /**
     * 生成海报的地址
     * @return Image 生成海报的地址
     */
    public String getImage() {
        return image;
    }

    /**
     * 生成海报的地址
     * @param image 生成海报的地址
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * 培训表格
     * @return Content 培训表格
     */
    public String getContent() {
        return content;
    }

    /**
     * 培训表格
     * @param content 培训表格
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}