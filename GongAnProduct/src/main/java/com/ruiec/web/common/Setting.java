/*
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.common;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 系统配置
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年6月2日
 */
public class Setting implements Serializable{
	
	private static final long serialVersionUID = -642919660915021672L;

	public static final String KEY = "cacheKey";
	
	//=============================基本设置Start===============================//
	private boolean isOpenWebsite;//是否启用网站
	@NotBlank
	@Length(max=300)
	private String maintenanceInfo;//维护信息
	@NotBlank
	private String siteUrl;//网站域名
	@NotBlank
	private String fileServerUrl;//文件服务器访问路径
	@NotBlank
	@Email
	private String email;//联系邮箱
	@NotBlank
	@Pattern(regexp="^([1-9]\\d*)?$", message="请填写正确的qq号")
	private String qq;//联系qq
	@NotBlank
	//@Pattern(regexp="$[(1\\d{10})?|(\\d+-?\\d+)?]^", message="请填写正确的电话")
	private String phone;//联系电话
	private String hotline;//客服热线
	private String serviceTime;//服务时间
	@NotBlank
	private String peopleName;//联系人姓名
	private String address;//联系地址
	private String siteName;//网站名称
	
	private String copyRightInfo; // 版权信息，如©2010 源中瑞网络传媒 All Right Reserved
	private String companyInfo;//公司简介
	//=============================基本设置End===============================//
	
	
	//=============================高级设置Start===============================//
	private boolean isOpenResource;//是否开启上传功能
	private String resourceType;//允许上传的文件类型
	@Min(value = 1)
	private int maxSize;//允许上传的文件大小
	private String imgType;//允许上传的图片类型
	@Min(value = 1)
	private int imgMaxSize;//允许上传的图片大小
	private int maxDelayLiquidateTime = 5; //结算最大延迟(不一定保60秒类型的全部订单都小于该参数，只是当队列长度超过该参数后将限制下单)
	private int maxQuickerLiquidateTime = 5; //结算最高提前(不一定保60秒类型的全部订单都小于该参数，只是当队列长度小于该参数后将限制下单)
	private int minLiquidateQueueNum = 60; //60秒类型最短时间
	//=============================高级设置End===============================//
	
	//=============================验证设置Start==============================//
	@NotBlank
	@Email
	private String systemEmail;//系统邮箱,用于找回密码
	@NotBlank
	private String emailPwd;//系统邮箱的密码
	private String smtp;//SMTP服务器
	private int smtpPort;//SMTP端口
	private String smsSign;//短信标识
	private String smsUrl; // 短信接口地址
	private String smsUid; // 短信平台用户名
	private String smsKey; // 短信接口安全密码
	//==============================验证设置End===============================//
	
	//==============================首页SEO设置Start=============================//
	@NotBlank
	@Length(max=80)
	private String indexTitle;//SEO标题
	@NotBlank
	private String indexKeywords;//SEO关键词
	@NotBlank
	private String indexDescription;//SEO描述
	//==============================首页SEO设置End=============================//
	
	//==============================业务设置Start=============================//
	private int bonusCycle;//分红周期
	private BigDecimal highestBackRate;//最高返点等级
	private BigDecimal lowestBackRate;//最低返点等级
	private BigDecimal maxBackRateDifference;//最大返点等级差
	private BigDecimal normalPalyerBackrate;//普通注册玩家返点数
	private BigDecimal withdrawHandlingFeeRate;//提现手续费率
	private BigDecimal withdrawVirtualCoinHandlingFeeRate;//提币手续费率
	private BigDecimal rmbConverToUSDRate;//人民币转换成美元的汇率(入金)
	private BigDecimal usdConverToRMBRate;//美元转换成人民币的汇率(出金)
	//==============================业务设置End=============================//
	
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getImgType() {
		return imgType;
	}
	public void setImgType(String imgType) {
		this.imgType = imgType;
	}
	public int getImgMaxSize() {
		return imgMaxSize;
	}
	public void setImgMaxSize(int imgMaxSize) {
		this.imgMaxSize = imgMaxSize;
	}
	public int getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	public String getFileServerUrl() {
		return fileServerUrl;
	}
	public void setFileServerUrl(String fileServerUrl) {
		this.fileServerUrl = fileServerUrl;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPeopleName() {
		return peopleName;
	}
	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 网站名称
	 * @author 李俊鸿
	 * @date 2015年6月29日
	 */
	public String getSiteName() {
		return siteName;
	}

	/**
	 * 网站名称
	 * @author 李俊鸿
	 * @date 2015年6月29日
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public boolean getIsOpenWebsite() {
		return isOpenWebsite;
	}
	public void setIsOpenWebsite(boolean isOpenWebsite) {
		this.isOpenWebsite = isOpenWebsite;
	}
	
	
	/**
	 * 维护信息
	 */
	public String getMaintenanceInfo() {
		return maintenanceInfo;
	}
	/**
	 * 维护信息
	 */
	public void setMaintenanceInfo(String maintenanceInfo) {
		this.maintenanceInfo = maintenanceInfo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getIsOpenResource() {
		return isOpenResource;
	}
	public void setIsOpenResource(boolean isOpenResource) {
		this.isOpenResource = isOpenResource;
	}
	public String getIndexTitle() {
		return indexTitle;
	}
	public void setIndexTitle(String indexTitle) {
		this.indexTitle = indexTitle;
	}
	public String getIndexKeywords() {
		return indexKeywords;
	}
	public void setIndexKeywords(String indexKeywords) {
		this.indexKeywords = indexKeywords;
	}
	public String getIndexDescription() {
		return indexDescription;
	}
	public void setIndexDescription(String indexDescription) {
		this.indexDescription = indexDescription;
	}



	/**
	 * 获得 客服热线
	 * @return
	 */
	public String getHotline() {
		return hotline;
	}
	/**
	 * 设置 客服热线
	 * @param hotline
	 */
	public void setHotline(String hotline) {
		this.hotline = hotline;
	}
	/**
	 * 系统邮箱,用于找回密码
	 */
	public String getSystemEmail() {
		return systemEmail;
	}
	/**
	 * 系统邮箱,用于找回密码
	 */
	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}
	/**
	 * 系统邮箱的密码
	 */
	public String getEmailPwd() {
		return emailPwd;
	}
	/**
	 * 系统邮箱的密码
	 */
	public void setEmailPwd(String emailPwd) {
		this.emailPwd = emailPwd;
	}
	/**
	 * SMTP服务器
	 */
	public String getSmtp() {
		return smtp;
	}
	/**
	 * SMTP服务器
	 */
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}
	/**
	 * SMTP端口
	 */
	public int getSmtpPort() {
		return smtpPort;
	}
	/**
	 * SMTP端口
	 */
	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}
	/**
	 * 短信标识
	 */
	public String getSmsSign() {
		return smsSign;
	}
	/**
	 * 短信标识
	 */
	public void setSmsSign(String smsSign) {
		this.smsSign = smsSign;
	}
	/**
	 * 短信接口地址
	 */
	public String getSmsUrl() {
		return smsUrl;
	}
	/**
	 * 短信接口地址
	 */
	public void setSmsUrl(String smsUrl) {
		this.smsUrl = smsUrl;
	}
	/**
	 * 短信平台用户名
	 */
	public String getSmsUid() {
		return smsUid;
	}
	/**
	 * 短信平台用户名
	 */
	public void setSmsUid(String smsUid) {
		this.smsUid = smsUid;
	}
	/**
	 * 短信接口安全密码
	 */
	public String getSmsKey() {
		return smsKey;
	}
	/**
	 * 短信接口安全密码
	 */
	public void setSmsKey(String smsKey) {
		this.smsKey = smsKey;
	}
	/**
	 * 结算最大延迟(不一定保60秒类型的全部订单都小于该参数，只是当队列长度超过该参数后将限制下单)
	 */
	@Min(0)
	public int getMaxDelayLiquidateTime() {
		return maxDelayLiquidateTime;
	}
	/**
	 * 结算最大延迟(不一定保60秒类型的全部订单都小于该参数，只是当队列长度超过该参数后将限制下单)
	 */
	public void setMaxDelayLiquidateTime(int maxDelayLiquidateTime) {
		this.maxDelayLiquidateTime = maxDelayLiquidateTime;
	}
	/**
	 * 结算最多提前(不一定保60秒类型的全部订单都小于该参数，只是当队列长度小于该参数后将限制下单)
	 */
	@Min(0)
	public int getMaxQuickerLiquidateTime() {
		return maxQuickerLiquidateTime;
	}
	/**
	 * 结算最多提前(不一定保60秒类型的全部订单都小于该参数，只是当队列长度小于该参数后将限制下单)
	 */
	public void setMaxQuickerLiquidateTime(int maxQuickerLiquidateTime) {
		this.maxQuickerLiquidateTime = maxQuickerLiquidateTime;
	}
	/**
	 * 60秒类型最短时间
	 */
	@Min(50)
	public int getMinLiquidateQueueNum() {
		return minLiquidateQueueNum;
	}
	/**
	 * 60秒类型最短时间
	 */
	public void setMinLiquidateQueueNum(int minLiquidateQueueNum) {
		this.minLiquidateQueueNum = minLiquidateQueueNum;
	}
	/**
	 * 获取版权信息
	 * @return
	 * @author 欧阳飞
	 */
	public String getCopyRightInfo() {
		return copyRightInfo;
	}

	/**
	 * 设置版权信息
	 * @param copyRightInfo
	 * @author 欧阳飞
	 */
	public void setCopyRightInfo(String copyRightInfo) {
		this.copyRightInfo = copyRightInfo;
	}

	/**
	 * 公司简介
	 * @author 李俊鸿
	 * @date 2015年7月24日
	 */
	public String getCompanyInfo() {
		return companyInfo;
	}

	/**
	 * 公司简介
	 * @author 李俊鸿
	 * @date 2015年7月24日
	 */
	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}
	
	/**
	 * 获取服务时间
	 * @return
	 * @author 欧阳飞
	 */
	public String getServiceTime() {
		return serviceTime;
	}
	/**
	 * 设置服务时间
	 * @param serviceTime
	 * @author 欧阳飞
	 */
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	
	/**
	 * 分红周期
	 * @author 杨龙香
	 * @date 2016年7月4日
	 */
	public int getBonusCycle() {
		return bonusCycle;
	}
	
	/**
	 * 分红周期
	 * @author 杨龙香
	 * @date 2016年7月4日
	 */
	public void setBonusCycle(int bonusCycle) {
		this.bonusCycle = bonusCycle;
	}
	
	/**
	 * 获取最高返点等级
	 * @return
	 * @author 杨龙香
	 */
	@Column(precision=19, scale=4)
	public BigDecimal getHighestBackRate() {
		return highestBackRate;
	}
	
	/**
	 * 获取最高返点等级
	 * @return
	 * @author 杨龙香
	 */
	public void setHighestBackRate(BigDecimal highestBackRate) {
		this.highestBackRate = highestBackRate;
	}
	
	/**
	 * 获取最低返点等级
	 * @return
	 * @author 杨龙香
	 */
	@Column(precision=19, scale=4)
	public BigDecimal getLowestBackRate() {
		return lowestBackRate;
	}
	
	/**
	 * 获取最低返点等级
	 * @return
	 * @author 杨龙香
	 */
	public void setLowestBackRate(BigDecimal lowestBackRate) {
		this.lowestBackRate = lowestBackRate;
	}
	
	/**
	 * 获取最大返点等级差
	 * @return
	 * @author 杨龙香
	 */
	@Column(precision=19, scale=4)
	public BigDecimal getMaxBackRateDifference() {
		return maxBackRateDifference;
	}
	
	/**
	 * 获取最大返点等级差
	 * @return
	 * @author 杨龙香
	 */
	public void setMaxBackRateDifference(BigDecimal maxBackRateDifference) {
		this.maxBackRateDifference = maxBackRateDifference;
	}
	
	/**
	 * 普通注册玩家返点数
	 * @return
	 * @author 杨龙香
	 */
	public BigDecimal getNormalPalyerBackrate() {
		return normalPalyerBackrate;
	}
	
	/**
	 * 普通注册玩家返点数
	 * @return
	 * @author 杨龙香
	 */
	public void setNormalPalyerBackrate(BigDecimal normalPalyerBackrate) {
		this.normalPalyerBackrate = normalPalyerBackrate;
	}

	/**
	 * 提现手续费率
	 * @author 杨龙香
	 */
	public BigDecimal getWithdrawHandlingFeeRate() {
		return withdrawHandlingFeeRate;
	}
	
	/**
	 * 提现手续费率
	 * @author 杨龙香
	 */
	public void setWithdrawHandlingFeeRate(BigDecimal withdrawHandlingFeeRate) {
		this.withdrawHandlingFeeRate = withdrawHandlingFeeRate;
	}
	
	/**
	 * 提币手续费率
	 * @author 熊华松
	 */
	public BigDecimal getWithdrawVirtualCoinHandlingFeeRate() {
		return withdrawVirtualCoinHandlingFeeRate;
	}
	
	/**
	 * 提币手续费率
	 * @author 熊华松
	 */
	public void setWithdrawVirtualCoinHandlingFeeRate(BigDecimal withdrawVirtualCoinHandlingFeeRate) {
		this.withdrawVirtualCoinHandlingFeeRate = withdrawVirtualCoinHandlingFeeRate;
	}
	
	/**
	 * 人民币转换成美元的汇率(入金)
	 * @return
	 * @author 熊华松
	 */
	@Column(precision=19, scale=4)
	public BigDecimal getRmbConverToUSDRate() {
		return rmbConverToUSDRate;
	}

	/**
	 * 人民币转换成美元的汇率(入金)
	 * @return
	 * @author 熊华松
	 */
	public void setRmbConverToUSDRate(BigDecimal rmbConverToUSDRate) {
		this.rmbConverToUSDRate = rmbConverToUSDRate;
	}
	
	/**
	 * 美元转换成人民币的汇率(出金)
	 * @return
	 * @author 熊华松
	 */
	@Column(precision=19, scale=4)
	public BigDecimal getUsdConverToRMBRate() {
		return usdConverToRMBRate;
	}
	
	/**
	 * 美元转换成人民币的汇率(出金)
	 * @return
	 * @author 熊华松
	 */
	public void setUsdConverToRMBRate(BigDecimal usdConverToRMBRate) {
		this.usdConverToRMBRate = usdConverToRMBRate;
	}
	
}
