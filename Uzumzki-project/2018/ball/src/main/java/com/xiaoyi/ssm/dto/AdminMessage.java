package com.xiaoyi.ssm.dto;

import java.util.List;

/**  
 * @Description: 用于接口返回数据格式
 * @author song  
 * @date 2018年7月18日 下午4:16:59 
 */ 
public class AdminMessage {
	/** 状态码 */
	private int code;
	/** 提示内容 */
	private String msg;
	/** 返回数据 */
	private Object data;
	/** 数据总数 */
	private String count;
	/** 数据层数,默认第一层0,第二层100,第三层200 */
	private int listType;
	/** 是否添加左击事件 */
	private int leftClick;

	/**
	 * 默认返回分页数据
	 * @param pageInfo
	 */
	public AdminMessage(long total, List list) {
		this.code = 0;
		this.listType = 0;
		this.leftClick = 0;
		this.count = String.valueOf(total);
		this.data = list;
	}
	
	/**
	 * 增加左击事件返回分页数据
	 * @param pageInfo
	 */
	public AdminMessage(long total, List list, int leftClick) {
		this.code = 0;
		this.listType = 0;
		this.leftClick = leftClick;
		this.count = String.valueOf(total);
		this.data = list;
	}
	
	/**
	 * 增加数据层数返回分页数据<br>
	 * 数据层数,默认第一层0,第二层100,第三层200
	 * @param pageInfo
	 */
	public AdminMessage(int listType, long total, List list) {
		this.code = 0;
		this.leftClick = 0;
		this.listType = listType;
		this.count = String.valueOf(total);
		this.data = list;
	}

	/**
	 * 增加左击事件和数据层数返回分页数据<br>
	 * 数据层数,默认第一层0,第二层100,第三层200
	 * @param pageInfo
	 */
	public AdminMessage(int listType, long total, List list, int leftClick) {
		this.code = 0;
		this.listType = listType;
		this.leftClick = leftClick;
		this.count = String.valueOf(total);
		this.data = list;
	}

	public int getLeftClick() {
		return leftClick;
	}

	public void setLeftClick(int leftClick) {
		this.leftClick = leftClick;
	}

	public int getListType() {
		return listType;
	}

	public void setListType(int listType) {
		this.listType = listType;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
