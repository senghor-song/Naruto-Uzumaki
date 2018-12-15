package com.xiaoyi.ssm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 微信资金对账表实体
 */
public class WXFundflow implements Serializable {
    /** ID */
    private String id;

    /** 创建时间 */
    private Date createTime;

    /** 记账时间 */
    private Date recordTime;

    /** 微信支付业务单号 */
    private String wxPayOrderid;

    /** 资金流水单号 */
    private String wxFundflowId;

    /** 业务名称 */
    private String workName;

    /** 业务类型 */
    private String workType;

    /** 收支类型 */
    private String dealType;

    /** 收支金额(元) */
    private BigDecimal dealAmount;

    /** 账户结余(元) */
    private BigDecimal accountAmount;

    /** 资金变更提交申请人 */
    private String applyUser;

    /** 备注 */
    private String remark;

    /** 业务凭证号 */
    private String workId;

    /** 1=基本账户2=运营账户3=手续费账户 */
    private Integer accountType;

    /**
     * WXFundflow
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
     * 记账时间
     * @return Record_time 记账时间
     */
    public Date getRecordTime() {
        return recordTime;
    }

    /**
     * 记账时间
     * @param recordTime 记账时间
     */
    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    /**
     * 微信支付业务单号
     * @return WX_pay_orderid 微信支付业务单号
     */
    public String getWxPayOrderid() {
        return wxPayOrderid;
    }

    /**
     * 微信支付业务单号
     * @param wxPayOrderid 微信支付业务单号
     */
    public void setWxPayOrderid(String wxPayOrderid) {
        this.wxPayOrderid = wxPayOrderid == null ? null : wxPayOrderid.trim();
    }

    /**
     * 资金流水单号
     * @return WX_fundflow_id 资金流水单号
     */
    public String getWxFundflowId() {
        return wxFundflowId;
    }

    /**
     * 资金流水单号
     * @param wxFundflowId 资金流水单号
     */
    public void setWxFundflowId(String wxFundflowId) {
        this.wxFundflowId = wxFundflowId == null ? null : wxFundflowId.trim();
    }

    /**
     * 业务名称
     * @return Work_name 业务名称
     */
    public String getWorkName() {
        return workName;
    }

    /**
     * 业务名称
     * @param workName 业务名称
     */
    public void setWorkName(String workName) {
        this.workName = workName == null ? null : workName.trim();
    }

    /**
     * 业务类型
     * @return Work_type 业务类型
     */
    public String getWorkType() {
        return workType;
    }

    /**
     * 业务类型
     * @param workType 业务类型
     */
    public void setWorkType(String workType) {
        this.workType = workType == null ? null : workType.trim();
    }

    /**
     * 收支类型
     * @return Deal_type 收支类型
     */
    public String getDealType() {
        return dealType;
    }

    /**
     * 收支类型
     * @param dealType 收支类型
     */
    public void setDealType(String dealType) {
        this.dealType = dealType == null ? null : dealType.trim();
    }

    /**
     * 收支金额(元)
     * @return Deal_amount 收支金额(元)
     */
    public BigDecimal getDealAmount() {
        return dealAmount;
    }

    /**
     * 收支金额(元)
     * @param dealAmount 收支金额(元)
     */
    public void setDealAmount(BigDecimal dealAmount) {
        this.dealAmount = dealAmount;
    }

    /**
     * 账户结余(元)
     * @return Account_amount 账户结余(元)
     */
    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    /**
     * 账户结余(元)
     * @param accountAmount 账户结余(元)
     */
    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    /**
     * 资金变更提交申请人
     * @return Apply_user 资金变更提交申请人
     */
    public String getApplyUser() {
        return applyUser;
    }

    /**
     * 资金变更提交申请人
     * @param applyUser 资金变更提交申请人
     */
    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser == null ? null : applyUser.trim();
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

    /**
     * 业务凭证号
     * @return Work_id 业务凭证号
     */
    public String getWorkId() {
        return workId;
    }

    /**
     * 业务凭证号
     * @param workId 业务凭证号
     */
    public void setWorkId(String workId) {
        this.workId = workId == null ? null : workId.trim();
    }

    /**
     * 1=基本账户2=运营账户3=手续费账户
     * @return Account_type 1=基本账户2=运营账户3=手续费账户
     */
    public Integer getAccountType() {
        return accountType;
    }

    /**
     * 1=基本账户2=运营账户3=手续费账户
     * @param accountType 1=基本账户2=运营账户3=手续费账户
     */
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
}