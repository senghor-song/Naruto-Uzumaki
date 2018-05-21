package com.ruiec.springboot.pojo;

public class VarietyType {
    /**编号*/
    private Long id;

    /**类型名称*/
    private String typeName;

    /**排序*/
    private Integer sort;

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

    /**类型名称*/
    public String getTypeName() {
        return typeName;
    }

    /**类型名称*/
    public void setTypename(String typeName) {
        this.typeName = typeName;
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
    public void setCreateOn(Long createOn) {
        this.createOn = createOn;
    }
}