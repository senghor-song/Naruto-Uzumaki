 package com.ruiec.springboot.pojo;

public class MovieArea {
    /**编号*/
    private Integer id;

    /**地区名称*/
    private String areaName;

    /**排序*/
    private Integer sort;

    /**创建时间*/
    private Long createOn;

    /**编号*/
    public Integer getId() {
        return id;
    }

    /**编号*/
    public void setId(Integer id) {
        this.id = id;
    }

    /**地区名称*/
    public String getAreaName() {
        return areaName;
    }

    /**地区名称*/
    public void setAreaName(String areaName) {
        this.areaName = areaName;
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
    public Long getCreateOn() {
        return createOn;
    }

    /**创建时间*/
    public void setCreateon(Long createOn) {
        this.createOn = createOn;
    }
}