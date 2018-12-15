package com.xiaoyi.ssm.dto;

/**
 * @Description: 后台查询封装类
 * @author 宋高俊
 * @date 2018年12月7日下午3:02:33
 */
public class AdminOrderDto {
	// 查询类型
	private Integer selectType;
	// 订单状态
	private Integer orderType;
	// 日期
	private String dateStr;
	
	public Integer getSelectType() {
		return selectType;
	}
	public void setSelectType(Integer selectType) {
		this.selectType = selectType;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	
}
