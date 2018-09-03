package com.xiaoyi.ssm.model;

import java.io.Serializable;

/**
 * 房源群发设置小区信息实体
 */
public class MassPropertyEstateName implements Serializable {
    /** 主键 */
    private String id;

    /** 网站ID */
    private String webid;

    /** 小区名称 */
    private String estatename;

    /** 房源ID */
    private String masspropertyid;

    /**
     * MassPropertyEstateName
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     * @return ID 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
     * 小区名称
     * @return EstateName 小区名称
     */
    public String getEstatename() {
        return estatename;
    }

    /**
     * 小区名称
     * @param estatename 小区名称
     */
    public void setEstatename(String estatename) {
        this.estatename = estatename == null ? null : estatename.trim();
    }

    /**
     * 房源ID
     * @return MassPropertyID 房源ID
     */
    public String getMasspropertyid() {
        return masspropertyid;
    }

    /**
     * 房源ID
     * @param masspropertyid 房源ID
     */
    public void setMasspropertyid(String masspropertyid) {
        this.masspropertyid = masspropertyid == null ? null : masspropertyid.trim();
    }
}