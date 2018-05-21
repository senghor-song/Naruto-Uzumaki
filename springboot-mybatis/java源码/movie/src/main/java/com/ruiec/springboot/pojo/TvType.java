package com.ruiec.springboot.pojo;

public class TvType {
    /**编号*/
    private Integer id;

    /**分类名称*/
    private String typename;

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

    /**分类名称*/
    public String getTypename() {
        return typename;
    }

    /**分类名称*/
    public void setTypename(String typename) {
        this.typename = typename;
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