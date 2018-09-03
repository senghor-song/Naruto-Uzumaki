package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.util.Date;

public class MassPersonBuyWantEmpCondi implements Serializable{
	
    /** 序列化 */
	private static final long serialVersionUID = -5969845388015917453L;
	
    /**
     * 推房->经纪人检索条件(个人求购)一个人最多40条记录
     */
    private String id;

    /**
     * 经纪人ID
     */
    private String empid;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 网站（单选）
     */
    private String site;

    /**
     * 网站（多选）
     */
    private String sites;

    /**
     * 区县ID（多选）--字段名少了一个ID后面再说
     */
    private String district;

    /**
     * 区县（多选）
     */
    private String districts;

    /**
     * 片区ID（多选）
     */
    private String areaid;

    /**
     * 片区（多选）
     */
    private String area;

    /**
     * 类型
     */
    private String type;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 房数
     */
    private Integer countf;

    /**
     * 最大价格
     */
    private Integer pricemax;

    /**
     * 最小价格
     */
    private Integer pricemin;

    /**
     * 最大面积
     */
    private Integer sizemax;

    /**
     * 最小面积
     */
    private Integer sizemin;

    /**
     * 缺省（0=否，1=是）
     */
    private Byte def;

    /**
     * 
     */
    private String remark;

    /**
     * 推房->经纪人检索条件(个人求购)一个人最多40条记录
     * @return ID 推房->经纪人检索条件(个人求购)一个人最多40条记录
     */
    public String getId() {
        return id;
    }

    /**
     * 推房->经纪人检索条件(个人求购)一个人最多40条记录
     * @param id 推房->经纪人检索条件(个人求购)一个人最多40条记录
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 经纪人ID
     * @return EmpID 经纪人ID
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * 经纪人ID
     * @param empid 经纪人ID
     */
    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
    }

    /**
     * 添加时间
     * @return AddTime 添加时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 添加时间
     * @param addtime 添加时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 网站（单选）
     * @return Site 网站（单选）
     */
    public String getSite() {
        return site;
    }

    /**
     * 网站（单选）
     * @param site 网站（单选）
     */
    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    /**
     * 网站（多选）
     * @return Sites 网站（多选）
     */
    public String getSites() {
        return sites;
    }

    /**
     * 网站（多选）
     * @param sites 网站（多选）
     */
    public void setSites(String sites) {
        this.sites = sites == null ? null : sites.trim();
    }

    /**
     * 区县ID（多选）--字段名少了一个ID后面再说
     * @return District 区县ID（多选）--字段名少了一个ID后面再说
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 区县ID（多选）--字段名少了一个ID后面再说
     * @param district 区县ID（多选）--字段名少了一个ID后面再说
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * 区县（多选）
     * @return Districts 区县（多选）
     */
    public String getDistricts() {
        return districts;
    }

    /**
     * 区县（多选）
     * @param districts 区县（多选）
     */
    public void setDistricts(String districts) {
        this.districts = districts == null ? null : districts.trim();
    }

    /**
     * 片区ID（多选）
     * @return AreaID 片区ID（多选）
     */
    public String getAreaid() {
        return areaid;
    }

    /**
     * 片区ID（多选）
     * @param areaid 片区ID（多选）
     */
    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    /**
     * 片区（多选）
     * @return Area 片区（多选）
     */
    public String getArea() {
        return area;
    }

    /**
     * 片区（多选）
     * @param area 片区（多选）
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * 类型
     * @return Type 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 类型
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 关键字
     * @return KeyWord 关键字
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 关键字
     * @param keyword 关键字
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    /**
     * 房数
     * @return CountF 房数
     */
    public Integer getCountf() {
        return countf;
    }

    /**
     * 房数
     * @param countf 房数
     */
    public void setCountf(Integer countf) {
        this.countf = countf;
    }

    /**
     * 最大价格
     * @return PriceMax 最大价格
     */
    public Integer getPricemax() {
        return pricemax;
    }

    /**
     * 最大价格
     * @param pricemax 最大价格
     */
    public void setPricemax(Integer pricemax) {
        this.pricemax = pricemax;
    }

    /**
     * 最小价格
     * @return PriceMin 最小价格
     */
    public Integer getPricemin() {
        return pricemin;
    }

    /**
     * 最小价格
     * @param pricemin 最小价格
     */
    public void setPricemin(Integer pricemin) {
        this.pricemin = pricemin;
    }

    /**
     * 最大面积
     * @return SizeMax 最大面积
     */
    public Integer getSizemax() {
        return sizemax;
    }

    /**
     * 最大面积
     * @param sizemax 最大面积
     */
    public void setSizemax(Integer sizemax) {
        this.sizemax = sizemax;
    }

    /**
     * 最小面积
     * @return SizeMin 最小面积
     */
    public Integer getSizemin() {
        return sizemin;
    }

    /**
     * 最小面积
     * @param sizemin 最小面积
     */
    public void setSizemin(Integer sizemin) {
        this.sizemin = sizemin;
    }

    /**
     * 缺省（0=否，1=是）
     * @return Def 缺省（0=否，1=是）
     */
    public Byte getDef() {
        return def;
    }

    /**
     * 缺省（0=否，1=是）
     * @param def 缺省（0=否，1=是）
     */
    public void setDef(Byte def) {
        this.def = def;
    }

    /**
     * 
     * @return Remark 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * @param remark 
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}