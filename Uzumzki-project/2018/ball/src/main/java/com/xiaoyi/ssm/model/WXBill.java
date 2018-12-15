package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 微信对账表实体
 */
public class WXBill implements Serializable {
    /** 微信支付 */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 交易时间 */
    private Date dealTime;

    /** 公众账号ID */
    private String wxAppid;

    /** 商户号 */
    private String wxMchid;

    /** 子商户号 */
    private String sonMchid;

    /** 设备号 */
    private String deviceInfo;

    /** 微信订单号 */
    private String wxOrderid;

    /** 商户订单号 */
    private String storeOrderid;

    /** 用户标识 */
    private String userFlag;

    /** 交易类型 */
    private String payType;

    /** 交易状态 */
    private String payStatus;

    /** 付款银行 */
    private String paymentBank;

    /** 货币种类 */
    private String currencyType;

    /** 应结订单金额 */
    private BigDecimal oughtOrderAmount;

    /** 代金券金额 */
    private BigDecimal cashCoupon;

    /** 微信退款单号 */
    private String wxRefundOrderid;

    /** 商户退款单号 */
    private String storeRefundOrderid;

    /** 退款金额 */
    private BigDecimal refundAmount;

    /** 充值卷退款金额 */
    private BigDecimal cashCouponRefund;

    /** 退款类型 */
    private String refundType;

    /** 退款状态 */
    private String refundStatus;

    /** 商品名称 */
    private String goodsName;

    /** 商户数据包 */
    private String storeData;

    /** 手续费 */
    private BigDecimal feeAmount;

    /** 费率 */
    private String rate;

    /** 订单金额 */
    private BigDecimal orderAmount;

    /** 申请退款金额 */
    private BigDecimal applyRefundAmount;

    /** 费率备注 */
    private String rateRemark;

    /**
     * WXBill
     */
    private static final long serialVersionUID = 1L;

    /**
     * 微信支付
     * @return ID 微信支付
     */
    public String getId() {
        return id;
    }

    /**
     * 微信支付
     * @param id 微信支付
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
     * 交易时间
     * @return Deal_time 交易时间
     */
    public Date getDealTime() {
        return dealTime;
    }

    /**
     * 交易时间
     * @param dealTime 交易时间
     */
    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    /**
     * 公众账号ID
     * @return WX_appid 公众账号ID
     */
    public String getWxAppid() {
        return wxAppid;
    }

    /**
     * 公众账号ID
     * @param wxAppid 公众账号ID
     */
    public void setWxAppid(String wxAppid) {
        this.wxAppid = wxAppid == null ? null : wxAppid.trim();
    }

    /**
     * 商户号
     * @return WX_mchid 商户号
     */
    public String getWxMchid() {
        return wxMchid;
    }

    /**
     * 商户号
     * @param wxMchid 商户号
     */
    public void setWxMchid(String wxMchid) {
        this.wxMchid = wxMchid == null ? null : wxMchid.trim();
    }

    /**
     * 子商户号
     * @return Son_mchid 子商户号
     */
    public String getSonMchid() {
        return sonMchid;
    }

    /**
     * 子商户号
     * @param sonMchid 子商户号
     */
    public void setSonMchid(String sonMchid) {
        this.sonMchid = sonMchid == null ? null : sonMchid.trim();
    }

    /**
     * 设备号
     * @return Device_info 设备号
     */
    public String getDeviceInfo() {
        return deviceInfo;
    }

    /**
     * 设备号
     * @param deviceInfo 设备号
     */
    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo == null ? null : deviceInfo.trim();
    }

    /**
     * 微信订单号
     * @return WX_orderid 微信订单号
     */
    public String getWxOrderid() {
        return wxOrderid;
    }

    /**
     * 微信订单号
     * @param wxOrderid 微信订单号
     */
    public void setWxOrderid(String wxOrderid) {
        this.wxOrderid = wxOrderid == null ? null : wxOrderid.trim();
    }

    /**
     * 商户订单号
     * @return Store_orderid 商户订单号
     */
    public String getStoreOrderid() {
        return storeOrderid;
    }

    /**
     * 商户订单号
     * @param storeOrderid 商户订单号
     */
    public void setStoreOrderid(String storeOrderid) {
        this.storeOrderid = storeOrderid == null ? null : storeOrderid.trim();
    }

    /**
     * 用户标识
     * @return User_flag 用户标识
     */
    public String getUserFlag() {
        return userFlag;
    }

    /**
     * 用户标识
     * @param userFlag 用户标识
     */
    public void setUserFlag(String userFlag) {
        this.userFlag = userFlag == null ? null : userFlag.trim();
    }

    /**
     * 交易类型
     * @return Pay_type 交易类型
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 交易类型
     * @param payType 交易类型
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    /**
     * 交易状态
     * @return Pay_status 交易状态
     */
    public String getPayStatus() {
        return payStatus;
    }

    /**
     * 交易状态
     * @param payStatus 交易状态
     */
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    /**
     * 付款银行
     * @return Payment_bank 付款银行
     */
    public String getPaymentBank() {
        return paymentBank;
    }

    /**
     * 付款银行
     * @param paymentBank 付款银行
     */
    public void setPaymentBank(String paymentBank) {
        this.paymentBank = paymentBank == null ? null : paymentBank.trim();
    }

    /**
     * 货币种类
     * @return Currency_type 货币种类
     */
    public String getCurrencyType() {
        return currencyType;
    }

    /**
     * 货币种类
     * @param currencyType 货币种类
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType == null ? null : currencyType.trim();
    }

    /**
     * 应结订单金额
     * @return Ought_order_amount 应结订单金额
     */
    public BigDecimal getOughtOrderAmount() {
        return oughtOrderAmount;
    }

    /**
     * 应结订单金额
     * @param oughtOrderAmount 应结订单金额
     */
    public void setOughtOrderAmount(BigDecimal oughtOrderAmount) {
        this.oughtOrderAmount = oughtOrderAmount;
    }

    /**
     * 代金券金额
     * @return Cash_coupon 代金券金额
     */
    public BigDecimal getCashCoupon() {
        return cashCoupon;
    }

    /**
     * 代金券金额
     * @param cashCoupon 代金券金额
     */
    public void setCashCoupon(BigDecimal cashCoupon) {
        this.cashCoupon = cashCoupon;
    }

    /**
     * 微信退款单号
     * @return WX_refund_orderid 微信退款单号
     */
    public String getWxRefundOrderid() {
        return wxRefundOrderid;
    }

    /**
     * 微信退款单号
     * @param wxRefundOrderid 微信退款单号
     */
    public void setWxRefundOrderid(String wxRefundOrderid) {
        this.wxRefundOrderid = wxRefundOrderid == null ? null : wxRefundOrderid.trim();
    }

    /**
     * 商户退款单号
     * @return Store_refund_orderid 商户退款单号
     */
    public String getStoreRefundOrderid() {
        return storeRefundOrderid;
    }

    /**
     * 商户退款单号
     * @param storeRefundOrderid 商户退款单号
     */
    public void setStoreRefundOrderid(String storeRefundOrderid) {
        this.storeRefundOrderid = storeRefundOrderid == null ? null : storeRefundOrderid.trim();
    }

    /**
     * 退款金额
     * @return Refund_amount 退款金额
     */
    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    /**
     * 退款金额
     * @param refundAmount 退款金额
     */
    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * 充值卷退款金额
     * @return Cash_coupon_refund 充值卷退款金额
     */
    public BigDecimal getCashCouponRefund() {
        return cashCouponRefund;
    }

    /**
     * 充值卷退款金额
     * @param cashCouponRefund 充值卷退款金额
     */
    public void setCashCouponRefund(BigDecimal cashCouponRefund) {
        this.cashCouponRefund = cashCouponRefund;
    }

    /**
     * 退款类型
     * @return Refund_type 退款类型
     */
    public String getRefundType() {
        return refundType;
    }

    /**
     * 退款类型
     * @param refundType 退款类型
     */
    public void setRefundType(String refundType) {
        this.refundType = refundType == null ? null : refundType.trim();
    }

    /**
     * 退款状态
     * @return Refund_status 退款状态
     */
    public String getRefundStatus() {
        return refundStatus;
    }

    /**
     * 退款状态
     * @param refundStatus 退款状态
     */
    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus == null ? null : refundStatus.trim();
    }

    /**
     * 商品名称
     * @return Goods_name 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 商品名称
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 商户数据包
     * @return Store_data 商户数据包
     */
    public String getStoreData() {
        return storeData;
    }

    /**
     * 商户数据包
     * @param storeData 商户数据包
     */
    public void setStoreData(String storeData) {
        this.storeData = storeData == null ? null : storeData.trim();
    }

    /**
     * 手续费
     * @return Fee_amount 手续费
     */
    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    /**
     * 手续费
     * @param feeAmount 手续费
     */
    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    /**
     * 费率
     * @return Rate 费率
     */
    public String getRate() {
        return rate;
    }

    /**
     * 费率
     * @param rate 费率
     */
    public void setRate(String rate) {
        this.rate = rate == null ? null : rate.trim();
    }

    /**
     * 订单金额
     * @return Order_amount 订单金额
     */
    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    /**
     * 订单金额
     * @param orderAmount 订单金额
     */
    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * 申请退款金额
     * @return Apply_refund_amount 申请退款金额
     */
    public BigDecimal getApplyRefundAmount() {
        return applyRefundAmount;
    }

    /**
     * 申请退款金额
     * @param applyRefundAmount 申请退款金额
     */
    public void setApplyRefundAmount(BigDecimal applyRefundAmount) {
        this.applyRefundAmount = applyRefundAmount;
    }

    /**
     * 费率备注
     * @return Rate_remark 费率备注
     */
    public String getRateRemark() {
        return rateRemark;
    }

    /**
     * 费率备注
     * @param rateRemark 费率备注
     */
    public void setRateRemark(String rateRemark) {
        this.rateRemark = rateRemark == null ? null : rateRemark.trim();
    }
}