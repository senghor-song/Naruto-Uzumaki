package com.ruiec.springboot.pojo;

public class MovieLanguage {
    /**编号*/
    private Integer id;

    /**语言名称*/
    private String languagename;

    /**排序*/
    private Integer sort;

    /**创建时间*/
    private Long createon;

    /**编号*/
    public Integer getId() {
        return id;
    }

    /**编号*/
    public void setId(Integer id) {
        this.id = id;
    }

    /**语言名称*/
    public String getLanguagename() {
        return languagename;
    }

    /**语言名称*/
    public void setLanguagename(String languagename) {
        this.languagename = languagename;
    }

    /**排序*/
    public Integer getSort() {
        return sort;
    }

    /**排序*/
    public void setSort(Integer sort) {
        this.sort = sort;
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