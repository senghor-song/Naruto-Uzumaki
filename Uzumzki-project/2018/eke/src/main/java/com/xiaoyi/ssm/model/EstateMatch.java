package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 小区默认匹配其他网站小区名表实体
 */
public class EstateMatch implements Serializable {
	
	/** 网站 */
	private WebSite webSite;
	
    public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	/** 租售->小区匹配表 */
    private String id;

    /** 匹配编号 */
    private Integer matchno;

    /** 小区ID */
    private String estateid;

    /** 网站ID */
    private String webid;

    /** 对应网站小区名 */
    private String name;

    /** 创建时间 */
    private Date createtime;

    /** 修改时间 */
    private Date modifytime;

    /** 备注 */
    private String remark;

    /**
     * EstateMatch
     */
    private static final long serialVersionUID = 1L;

    /**
     * 租售->小区匹配表
     * @return ID 租售->小区匹配表
     */
    public String getId() {
        return id;
    }

    /**
     * 租售->小区匹配表
     * @param id 租售->小区匹配表
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 匹配编号
     * @return MatchNo 匹配编号
     */
    public Integer getMatchno() {
        return matchno;
    }

    /**
     * 匹配编号
     * @param matchno 匹配编号
     */
    public void setMatchno(Integer matchno) {
        this.matchno = matchno;
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
     * 网站ID
     * @return WebID 网站ID
     */
    public String getWebid() {
        return webid;
    }

    /**
     * 网站ID
     * @param webid 网站ID
     */
    public void setWebid(String webid) {
        this.webid = webid == null ? null : webid.trim();
    }

    /**
     * 对应网站小区名
     * @return Name 对应网站小区名
     */
    public String getName() {
        return name;
    }

    /**
     * 对应网站小区名
     * @param name 对应网站小区名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
}