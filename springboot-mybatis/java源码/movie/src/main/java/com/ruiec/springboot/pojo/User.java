package com.ruiec.springboot.pojo;

public class User {
    /**编号*/
    private Integer id;

    /**手机号*/
    private Integer phone;

    /**密码*/
    private String password;

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

    /**手机号*/
    public Integer getPhone() {
        return phone;
    }

    /**手机号*/
    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    /**密码*/
    public String getPassword() {
        return password;
    }

    /**密码*/
    public void setPassword(String password) {
        this.password = password;
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