package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 实体
 */
public class WebSite implements Serializable {
	
	/** 绑定账号的集合 */
    private List<MassBind> massBinds;

    /** 绑定账号的集合 */
	public List<MassBind> getMassBinds() {
		return massBinds;
	}

    /** 绑定账号的集合 */
	public void setMassBinds(List<MassBind> massBinds) {
		this.massBinds = massBinds;
	}
	
    /** 网站表 */
    private String id;

    /** 网站名称 */
    private String webname;

    /** Logo图片的URL */
    private String logourl;

    /** 网站登录URL */
    private String loginurl;

    /** 网站注册URL */
    private String registerurl;

    /** 是否可以刷新(0=否1=是) */
    private Integer refresh;

    /** 是否可以发布(0=否1=是) */
    private Integer release;

    /** 是否收费(0=否1=是) */
    private Integer charge;

    /** 排序字段 */
    private Integer sort;

    /** 创建时间 */
    private Date createtime;

    /** 修改时间 */
    private Date modifytime;

    /**
     * WebSite
     */
    private static final long serialVersionUID = 1L;

    /**
     * 网站表
     * @return ID 网站表
     */
    public String getId() {
        return id;
    }

    /**
     * 网站表
     * @param id 网站表
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 网站名称
     * @return WebName 网站名称
     */
    public String getWebname() {
        return webname;
    }

    /**
     * 网站名称
     * @param webname 网站名称
     */
    public void setWebname(String webname) {
        this.webname = webname == null ? null : webname.trim();
    }

    /**
     * Logo图片的URL
     * @return LogoUrl Logo图片的URL
     */
    public String getLogourl() {
        return logourl;
    }

    /**
     * Logo图片的URL
     * @param logourl Logo图片的URL
     */
    public void setLogourl(String logourl) {
        this.logourl = logourl == null ? null : logourl.trim();
    }

    /**
     * 网站登录URL
     * @return LoginUrl 网站登录URL
     */
    public String getLoginurl() {
        return loginurl;
    }

    /**
     * 网站登录URL
     * @param loginurl 网站登录URL
     */
    public void setLoginurl(String loginurl) {
        this.loginurl = loginurl == null ? null : loginurl.trim();
    }

    /**
     * 网站注册URL
     * @return RegisterUrl 网站注册URL
     */
    public String getRegisterurl() {
        return registerurl;
    }

    /**
     * 网站注册URL
     * @param registerurl 网站注册URL
     */
    public void setRegisterurl(String registerurl) {
        this.registerurl = registerurl == null ? null : registerurl.trim();
    }

    /**
     * 是否可以刷新(0=否1=是)
     * @return Refresh 是否可以刷新(0=否1=是)
     */
    public Integer getRefresh() {
        return refresh;
    }

    /**
     * 是否可以刷新(0=否1=是)
     * @param refresh 是否可以刷新(0=否1=是)
     */
    public void setRefresh(Integer refresh) {
        this.refresh = refresh;
    }

    /**
     * 是否可以发布(0=否1=是)
     * @return Release 是否可以发布(0=否1=是)
     */
    public Integer getRelease() {
        return release;
    }

    /**
     * 是否可以发布(0=否1=是)
     * @param release 是否可以发布(0=否1=是)
     */
    public void setRelease(Integer release) {
        this.release = release;
    }

    /**
     * 是否收费(0=否1=是)
     * @return Charge 是否收费(0=否1=是)
     */
    public Integer getCharge() {
        return charge;
    }

    /**
     * 是否收费(0=否1=是)
     * @param charge 是否收费(0=否1=是)
     */
    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    /**
     * 排序字段
     * @return Sort 排序字段
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序字段
     * @param sort 排序字段
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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
}