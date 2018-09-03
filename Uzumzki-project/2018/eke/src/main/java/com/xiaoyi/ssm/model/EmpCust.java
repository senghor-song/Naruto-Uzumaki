package com.xiaoyi.ssm.model;

import java.io.Serializable;

/**
 * 经纪人私客表实体
 */
public class EmpCust implements Serializable {
    /** 商户->经纪人私客 */
    private String id;

    /** 所属经纪ID(为空表示为公客) */
    private String empid;

    /** 客户ID，为空说明是自建客户 */
    private String custid;

    /** 客户记录名 */
    private String cust;

    /** 性别 */
    private String sex;

    /** 手机号 */
    private String mobile;

    /** 客户等级 */
    private String level;

    /** 核心意向-最大预算 */
    private Integer budgetmax;

    /** 核心意向-最小预算 */
    private Integer budgetmin;

    /** 核心意向-最大面积 */
    private Integer squaremax;

    /** 核心意向-最小面积 */
    private Integer squaremin;

    /** 核心意向-户型 */
    private String layout;

    /** 核心意向-区域 */
    private String region;

    /** 核心意向-卫 */
    private Integer countw;

    /** 更多信息-客户来源 */
    private String custsource;

    /** 更多信息-朝向要求 */
    private String direction;

    /** 更多信息-装修要求 */
    private String decoration;

    /** 更多信息-楼层要求 */
    private String floor;

    /** 更多信息-电梯要求 */
    private String elevator;

    /** 更多信息-购房目的 */
    private String purchase;

    /** 更多信息-房屋用途 */
    private String use;

    /** 更多信息-房屋结构 */
    private String structure;

    /** 更多信息-付款方式 */
    private String payment;

    /** 更多信息-买家身份 */
    private String identity;

    /** 更多信息-名下房产 */
    private String amount;

    /** 更多信息-户口情况 */
    private String registered;

    /** 更多信息-微信号码 */
    private String wx;

    /** 更多信息-入住人口 */
    private String population;

    /** 更多信息-婚姻状况 */
    private String marriage;

    /** 需求类型-新房 */
    private Boolean newhouse;

    /** 需求类型-二手房 */
    private Boolean sale;

    /** 需求类型-租房 */
    private Boolean rent;

    /** 私客描述信息 */
    private String describe;

    /** 备注 */
    private String remark;

    /**
     * EmpCust
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商户->经纪人私客
     * @return ID 商户->经纪人私客
     */
    public String getId() {
        return id;
    }

    /**
     * 商户->经纪人私客
     * @param id 商户->经纪人私客
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 所属经纪ID(为空表示为公客)
     * @return EmpID 所属经纪ID(为空表示为公客)
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * 所属经纪ID(为空表示为公客)
     * @param empid 所属经纪ID(为空表示为公客)
     */
    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
    }

    /**
     * 客户ID，为空说明是自建客户
     * @return CustID 客户ID，为空说明是自建客户
     */
    public String getCustid() {
        return custid;
    }

    /**
     * 客户ID，为空说明是自建客户
     * @param custid 客户ID，为空说明是自建客户
     */
    public void setCustid(String custid) {
        this.custid = custid == null ? null : custid.trim();
    }

    /**
     * 客户记录名
     * @return Cust 客户记录名
     */
    public String getCust() {
        return cust;
    }

    /**
     * 客户记录名
     * @param cust 客户记录名
     */
    public void setCust(String cust) {
        this.cust = cust == null ? null : cust.trim();
    }

    /**
     * 性别
     * @return Sex 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 手机号
     * @return Mobile 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机号
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 客户等级
     * @return Level 客户等级
     */
    public String getLevel() {
        return level;
    }

    /**
     * 客户等级
     * @param level 客户等级
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     * 核心意向-最大预算
     * @return BudgetMax 核心意向-最大预算
     */
    public Integer getBudgetmax() {
        return budgetmax;
    }

    /**
     * 核心意向-最大预算
     * @param budgetmax 核心意向-最大预算
     */
    public void setBudgetmax(Integer budgetmax) {
        this.budgetmax = budgetmax;
    }

    /**
     * 核心意向-最小预算
     * @return BudgetMin 核心意向-最小预算
     */
    public Integer getBudgetmin() {
        return budgetmin;
    }

    /**
     * 核心意向-最小预算
     * @param budgetmin 核心意向-最小预算
     */
    public void setBudgetmin(Integer budgetmin) {
        this.budgetmin = budgetmin;
    }

    /**
     * 核心意向-最大面积
     * @return SquareMax 核心意向-最大面积
     */
    public Integer getSquaremax() {
        return squaremax;
    }

    /**
     * 核心意向-最大面积
     * @param squaremax 核心意向-最大面积
     */
    public void setSquaremax(Integer squaremax) {
        this.squaremax = squaremax;
    }

    /**
     * 核心意向-最小面积
     * @return SquareMin 核心意向-最小面积
     */
    public Integer getSquaremin() {
        return squaremin;
    }

    /**
     * 核心意向-最小面积
     * @param squaremin 核心意向-最小面积
     */
    public void setSquaremin(Integer squaremin) {
        this.squaremin = squaremin;
    }

    /**
     * 核心意向-户型
     * @return Layout 核心意向-户型
     */
    public String getLayout() {
        return layout;
    }

    /**
     * 核心意向-户型
     * @param layout 核心意向-户型
     */
    public void setLayout(String layout) {
        this.layout = layout == null ? null : layout.trim();
    }

    /**
     * 核心意向-区域
     * @return Region 核心意向-区域
     */
    public String getRegion() {
        return region;
    }

    /**
     * 核心意向-区域
     * @param region 核心意向-区域
     */
    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    /**
     * 核心意向-卫
     * @return CountW 核心意向-卫
     */
    public Integer getCountw() {
        return countw;
    }

    /**
     * 核心意向-卫
     * @param countw 核心意向-卫
     */
    public void setCountw(Integer countw) {
        this.countw = countw;
    }

    /**
     * 更多信息-客户来源
     * @return CustSource 更多信息-客户来源
     */
    public String getCustsource() {
        return custsource;
    }

    /**
     * 更多信息-客户来源
     * @param custsource 更多信息-客户来源
     */
    public void setCustsource(String custsource) {
        this.custsource = custsource == null ? null : custsource.trim();
    }

    /**
     * 更多信息-朝向要求
     * @return Direction 更多信息-朝向要求
     */
    public String getDirection() {
        return direction;
    }

    /**
     * 更多信息-朝向要求
     * @param direction 更多信息-朝向要求
     */
    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    /**
     * 更多信息-装修要求
     * @return Decoration 更多信息-装修要求
     */
    public String getDecoration() {
        return decoration;
    }

    /**
     * 更多信息-装修要求
     * @param decoration 更多信息-装修要求
     */
    public void setDecoration(String decoration) {
        this.decoration = decoration == null ? null : decoration.trim();
    }

    /**
     * 更多信息-楼层要求
     * @return Floor 更多信息-楼层要求
     */
    public String getFloor() {
        return floor;
    }

    /**
     * 更多信息-楼层要求
     * @param floor 更多信息-楼层要求
     */
    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    /**
     * 更多信息-电梯要求
     * @return Elevator 更多信息-电梯要求
     */
    public String getElevator() {
        return elevator;
    }

    /**
     * 更多信息-电梯要求
     * @param elevator 更多信息-电梯要求
     */
    public void setElevator(String elevator) {
        this.elevator = elevator == null ? null : elevator.trim();
    }

    /**
     * 更多信息-购房目的
     * @return Purchase 更多信息-购房目的
     */
    public String getPurchase() {
        return purchase;
    }

    /**
     * 更多信息-购房目的
     * @param purchase 更多信息-购房目的
     */
    public void setPurchase(String purchase) {
        this.purchase = purchase == null ? null : purchase.trim();
    }

    /**
     * 更多信息-房屋用途
     * @return Use 更多信息-房屋用途
     */
    public String getUse() {
        return use;
    }

    /**
     * 更多信息-房屋用途
     * @param use 更多信息-房屋用途
     */
    public void setUse(String use) {
        this.use = use == null ? null : use.trim();
    }

    /**
     * 更多信息-房屋结构
     * @return Structure 更多信息-房屋结构
     */
    public String getStructure() {
        return structure;
    }

    /**
     * 更多信息-房屋结构
     * @param structure 更多信息-房屋结构
     */
    public void setStructure(String structure) {
        this.structure = structure == null ? null : structure.trim();
    }

    /**
     * 更多信息-付款方式
     * @return Payment 更多信息-付款方式
     */
    public String getPayment() {
        return payment;
    }

    /**
     * 更多信息-付款方式
     * @param payment 更多信息-付款方式
     */
    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }

    /**
     * 更多信息-买家身份
     * @return Identity 更多信息-买家身份
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * 更多信息-买家身份
     * @param identity 更多信息-买家身份
     */
    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    /**
     * 更多信息-名下房产
     * @return Amount 更多信息-名下房产
     */
    public String getAmount() {
        return amount;
    }

    /**
     * 更多信息-名下房产
     * @param amount 更多信息-名下房产
     */
    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    /**
     * 更多信息-户口情况
     * @return Registered 更多信息-户口情况
     */
    public String getRegistered() {
        return registered;
    }

    /**
     * 更多信息-户口情况
     * @param registered 更多信息-户口情况
     */
    public void setRegistered(String registered) {
        this.registered = registered == null ? null : registered.trim();
    }

    /**
     * 更多信息-微信号码
     * @return WX 更多信息-微信号码
     */
    public String getWx() {
        return wx;
    }

    /**
     * 更多信息-微信号码
     * @param wx 更多信息-微信号码
     */
    public void setWx(String wx) {
        this.wx = wx == null ? null : wx.trim();
    }

    /**
     * 更多信息-入住人口
     * @return Population 更多信息-入住人口
     */
    public String getPopulation() {
        return population;
    }

    /**
     * 更多信息-入住人口
     * @param population 更多信息-入住人口
     */
    public void setPopulation(String population) {
        this.population = population == null ? null : population.trim();
    }

    /**
     * 更多信息-婚姻状况
     * @return Marriage 更多信息-婚姻状况
     */
    public String getMarriage() {
        return marriage;
    }

    /**
     * 更多信息-婚姻状况
     * @param marriage 更多信息-婚姻状况
     */
    public void setMarriage(String marriage) {
        this.marriage = marriage == null ? null : marriage.trim();
    }

    /**
     * 需求类型-新房
     * @return NewHouse 需求类型-新房
     */
    public Boolean getNewhouse() {
        return newhouse;
    }

    /**
     * 需求类型-新房
     * @param newhouse 需求类型-新房
     */
    public void setNewhouse(Boolean newhouse) {
        this.newhouse = newhouse;
    }

    /**
     * 需求类型-二手房
     * @return Sale 需求类型-二手房
     */
    public Boolean getSale() {
        return sale;
    }

    /**
     * 需求类型-二手房
     * @param sale 需求类型-二手房
     */
    public void setSale(Boolean sale) {
        this.sale = sale;
    }

    /**
     * 需求类型-租房
     * @return Rent 需求类型-租房
     */
    public Boolean getRent() {
        return rent;
    }

    /**
     * 需求类型-租房
     * @param rent 需求类型-租房
     */
    public void setRent(Boolean rent) {
        this.rent = rent;
    }

    /**
     * 私客描述信息
     * @return Describe 私客描述信息
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 私客描述信息
     * @param describe 私客描述信息
     */
    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
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