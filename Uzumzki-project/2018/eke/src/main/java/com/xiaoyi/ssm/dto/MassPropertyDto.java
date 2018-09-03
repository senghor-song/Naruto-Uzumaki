package com.xiaoyi.ssm.dto;


/**
 * @Description: 首页房源录入统计返回数据对象
 * @author 宋高俊
 * @date 2018年6月28日 下午12:14:48
 */
public class MassPropertyDto {
	/** 录入房源数(出售) */
	private Integer propertySum1;
	/** 今日手动录入房源数(出售) */
	private Integer propertyDaySumManual1;
	/** 今日非手动录入房源数(出售) */
	private Integer propertyDaySumNoManual1;
	/** 录入房源数(出租) */
	private Integer propertySum2;
	/** 今日手动录入房源数(出租) */
	private Integer propertyDaySumManual2;
	/** 今日非手动录入房源数(出租) */
	private Integer propertyDaySumNoManual2;
	/** 今日发布 */
	private Integer propertyDaySum;
	/** 绑定网站个数 */
	private Integer bindWebSum;
	/** 异常账号个数 */
	private Integer exceptionAccount;
	/** 开始时间 */
	private String startDate;
	/** 结束时间 */
	private String endDate;
	/** 经济人id */
	private String empId;
	/** 发布中总数 */
	private Integer releaseSum;
	/** 草稿箱总数 */
	private Integer draftSum;
	/** 回收站总数 */
	private Integer recycleSum;
	/** 录入类型0=出售1=出租 */
	private String postType;
	
	
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	public Integer getPropertySum1() {
		return propertySum1;
	}
	public void setPropertySum1(Integer propertySum1) {
		this.propertySum1 = propertySum1;
	}
	public Integer getPropertyDaySumManual1() {
		return propertyDaySumManual1;
	}
	public void setPropertyDaySumManual1(Integer propertyDaySumManual1) {
		this.propertyDaySumManual1 = propertyDaySumManual1;
	}
	public Integer getPropertyDaySumNoManual1() {
		return propertyDaySumNoManual1;
	}
	public void setPropertyDaySumNoManual1(Integer propertyDaySumNoManual1) {
		this.propertyDaySumNoManual1 = propertyDaySumNoManual1;
	}
	public Integer getPropertySum2() {
		return propertySum2;
	}
	public void setPropertySum2(Integer propertySum2) {
		this.propertySum2 = propertySum2;
	}
	public Integer getPropertyDaySumManual2() {
		return propertyDaySumManual2;
	}
	public void setPropertyDaySumManual2(Integer propertyDaySumManual2) {
		this.propertyDaySumManual2 = propertyDaySumManual2;
	}
	public Integer getPropertyDaySumNoManual2() {
		return propertyDaySumNoManual2;
	}
	public void setPropertyDaySumNoManual2(Integer propertyDaySumNoManual2) {
		this.propertyDaySumNoManual2 = propertyDaySumNoManual2;
	}
	public Integer getPropertyDaySum() {
		return propertyDaySum;
	}
	public void setPropertyDaySum(Integer propertyDaySum) {
		this.propertyDaySum = propertyDaySum;
	}
	public Integer getBindWebSum() {
		return bindWebSum;
	}
	public void setBindWebSum(Integer bindWebSum) {
		this.bindWebSum = bindWebSum;
	}
	public Integer getExceptionAccount() {
		return exceptionAccount;
	}
	public void setExceptionAccount(Integer exceptionAccount) {
		this.exceptionAccount = exceptionAccount;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public Integer getReleaseSum() {
		return releaseSum;
	}
	public void setReleaseSum(Integer releaseSum) {
		this.releaseSum = releaseSum;
	}
	public Integer getDraftSum() {
		return draftSum;
	}
	public void setDraftSum(Integer draftSum) {
		this.draftSum = draftSum;
	}
	public Integer getRecycleSum() {
		return recycleSum;
	}
	public void setRecycleSum(Integer recycleSum) {
		this.recycleSum = recycleSum;
	}
	
}
