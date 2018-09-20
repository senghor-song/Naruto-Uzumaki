package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 约球表实体
 */
public class InviteBall implements Serializable {
    /** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 报名截止时间 */
    private Date endTime;

    /** 活动时间 */
    private Date activeTime;

    /** 标题 */
    private String title;

    /** 场地名称 */
    private String venueName;

    /** 场地详细地址 */
    private String venueAddress;

    /** 经度 */
    private Double longitude;

    /** 纬度 */
    private Double latitude;

    /** 最大人数 */
    private Integer maxBoy;

    /** 最小人数 */
    private Integer minBoy;

    /** 成团要求(1=不设置，人再少也不取消.2=人不够活动自动取消) */
    private Integer toRequire;

    /** 是否预收费用(0=否1=是) */
    private Integer receiveFlag;

    /** 预收金额 */
    private BigDecimal receiveAmount;

    /** 预收类型(1=固定收费2=AA收费) */
    private Integer receiveType;

    /** 是否允许退出(0=否1=是) */
    private Integer exitFlag;

    /** 退出类型(1=直接退出2=需发起人同意) */
    private Integer exitType;

    /** 退出手续费 */
    private Integer exitFee;

    /** 是否允许被附近的人看见(0=否1=是) */
    private Integer nearbyFlag;

    /** 姓名是否必填(0=否1=是) */
    private Integer nameFlag;

    /** 手机号是否必填(0=否1=是) */
    private Integer phoneFlag;

    /** 微信号是否必填(0=否1=是) */
    private Integer wechatFlag;

    /** 详情内容 */
    private String content;

    /** 发起人 */
    private String memberId;

    /** 约球状态(0=发起中1=到期截止2=提前截止3=取消活动) */
    private Integer ballType;

    /**
     * InviteBall
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * @return ID ID
     */
    public String getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 创建时间
     * @return Create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     * @return Modify_time 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 报名截止时间
     * @return End_time 报名截止时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 报名截止时间
     * @param endTime 报名截止时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 活动时间
     * @return Active_time 活动时间
     */
    public Date getActiveTime() {
        return activeTime;
    }

    /**
     * 活动时间
     * @param activeTime 活动时间
     */
    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    /**
     * 标题
     * @return Title 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 场地名称
     * @return Venue_name 场地名称
     */
    public String getVenueName() {
        return venueName;
    }

    /**
     * 场地名称
     * @param venueName 场地名称
     */
    public void setVenueName(String venueName) {
        this.venueName = venueName == null ? null : venueName.trim();
    }

    /**
     * 场地详细地址
     * @return Venue_address 场地详细地址
     */
    public String getVenueAddress() {
        return venueAddress;
    }

    /**
     * 场地详细地址
     * @param venueAddress 场地详细地址
     */
    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress == null ? null : venueAddress.trim();
    }

    /**
     * 经度
     * @return Longitude 经度
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 经度
     * @param longitude 经度
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 纬度
     * @return Latitude 纬度
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 纬度
     * @param latitude 纬度
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * 最大人数
     * @return Max_boy 最大人数
     */
    public Integer getMaxBoy() {
        return maxBoy;
    }

    /**
     * 最大人数
     * @param maxBoy 最大人数
     */
    public void setMaxBoy(Integer maxBoy) {
        this.maxBoy = maxBoy;
    }

    /**
     * 最小人数
     * @return Min_boy 最小人数
     */
    public Integer getMinBoy() {
        return minBoy;
    }

    /**
     * 最小人数
     * @param minBoy 最小人数
     */
    public void setMinBoy(Integer minBoy) {
        this.minBoy = minBoy;
    }

    /**
     * 成团要求(1=不设置，人再少也不取消.2=人不够活动自动取消)
     * @return To_require 成团要求(1=不设置，人再少也不取消.2=人不够活动自动取消)
     */
    public Integer getToRequire() {
        return toRequire;
    }

    /**
     * 成团要求(1=不设置，人再少也不取消.2=人不够活动自动取消)
     * @param toRequire 成团要求(1=不设置，人再少也不取消.2=人不够活动自动取消)
     */
    public void setToRequire(Integer toRequire) {
        this.toRequire = toRequire;
    }

    /**
     * 是否预收费用(0=否1=是)
     * @return Receive_flag 是否预收费用(0=否1=是)
     */
    public Integer getReceiveFlag() {
        return receiveFlag;
    }

    /**
     * 是否预收费用(0=否1=是)
     * @param receiveFlag 是否预收费用(0=否1=是)
     */
    public void setReceiveFlag(Integer receiveFlag) {
        this.receiveFlag = receiveFlag;
    }

    /**
     * 预收金额
     * @return Receive_amount 预收金额
     */
    public BigDecimal getReceiveAmount() {
        return receiveAmount;
    }

    /**
     * 预收金额
     * @param receiveAmount 预收金额
     */
    public void setReceiveAmount(BigDecimal receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    /**
     * 预收类型(1=固定收费2=AA收费)
     * @return Receive_type 预收类型(1=固定收费2=AA收费)
     */
    public Integer getReceiveType() {
        return receiveType;
    }

    /**
     * 预收类型(1=固定收费2=AA收费)
     * @param receiveType 预收类型(1=固定收费2=AA收费)
     */
    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    /**
     * 是否允许退出(0=否1=是)
     * @return Exit_flag 是否允许退出(0=否1=是)
     */
    public Integer getExitFlag() {
        return exitFlag;
    }

    /**
     * 是否允许退出(0=否1=是)
     * @param exitFlag 是否允许退出(0=否1=是)
     */
    public void setExitFlag(Integer exitFlag) {
        this.exitFlag = exitFlag;
    }

    /**
     * 退出类型(1=直接退出2=需发起人同意)
     * @return Exit_type 退出类型(1=直接退出2=需发起人同意)
     */
    public Integer getExitType() {
        return exitType;
    }

    /**
     * 退出类型(1=直接退出2=需发起人同意)
     * @param exitType 退出类型(1=直接退出2=需发起人同意)
     */
    public void setExitType(Integer exitType) {
        this.exitType = exitType;
    }

    /**
     * 退出手续费
     * @return Exit_fee 退出手续费
     */
    public Integer getExitFee() {
        return exitFee;
    }

    /**
     * 退出手续费
     * @param exitFee 退出手续费
     */
    public void setExitFee(Integer exitFee) {
        this.exitFee = exitFee;
    }

    /**
     * 是否允许被附近的人看见(0=否1=是)
     * @return Nearby_flag 是否允许被附近的人看见(0=否1=是)
     */
    public Integer getNearbyFlag() {
        return nearbyFlag;
    }

    /**
     * 是否允许被附近的人看见(0=否1=是)
     * @param nearbyFlag 是否允许被附近的人看见(0=否1=是)
     */
    public void setNearbyFlag(Integer nearbyFlag) {
        this.nearbyFlag = nearbyFlag;
    }

    /**
     * 姓名是否必填(0=否1=是)
     * @return Name_flag 姓名是否必填(0=否1=是)
     */
    public Integer getNameFlag() {
        return nameFlag;
    }

    /**
     * 姓名是否必填(0=否1=是)
     * @param nameFlag 姓名是否必填(0=否1=是)
     */
    public void setNameFlag(Integer nameFlag) {
        this.nameFlag = nameFlag;
    }

    /**
     * 手机号是否必填(0=否1=是)
     * @return Phone_flag 手机号是否必填(0=否1=是)
     */
    public Integer getPhoneFlag() {
        return phoneFlag;
    }

    /**
     * 手机号是否必填(0=否1=是)
     * @param phoneFlag 手机号是否必填(0=否1=是)
     */
    public void setPhoneFlag(Integer phoneFlag) {
        this.phoneFlag = phoneFlag;
    }

    /**
     * 微信号是否必填(0=否1=是)
     * @return WeChat_flag 微信号是否必填(0=否1=是)
     */
    public Integer getWechatFlag() {
        return wechatFlag;
    }

    /**
     * 微信号是否必填(0=否1=是)
     * @param wechatFlag 微信号是否必填(0=否1=是)
     */
    public void setWechatFlag(Integer wechatFlag) {
        this.wechatFlag = wechatFlag;
    }

    /**
     * 详情内容
     * @return Content 详情内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 详情内容
     * @param content 详情内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 发起人
     * @return Member_id 发起人
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 发起人
     * @param memberId 发起人
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     * 约球状态(0=发起中1=到期截止2=提前截止3=取消活动)
     * @return Ball_type 约球状态(0=发起中1=到期截止2=提前截止3=取消活动)
     */
    public Integer getBallType() {
        return ballType;
    }

    /**
     * 约球状态(0=发起中1=到期截止2=提前截止3=取消活动)
     * @param ballType 约球状态(0=发起中1=到期截止2=提前截止3=取消活动)
     */
    public void setBallType(Integer ballType) {
        this.ballType = ballType;
    }
}