package com.ruiec.web.common;

/**
 * 手机端公用返回操作结果的bean
 * 
 * @author 张俊<br>
 * @date 2017年11月22日 下午5:49:29
 */
public class ApiReturn {

	/** 状态返回码 */
	private int code;
	/** 返回消息 */
	private String msg = "";
	/** 返回数据 */
	private Object data = null;

	/**
	 * 构造器
	 * 
	 * @param data
	 *            返回数据
	 * @author bingo<br>
	 * @date 2018年1月8日 下午1:43:50
	 */
	public ApiReturn(Object data) {
		this.data = data;
	}

	/**
	 * 构造器
	 * 
	 * @param code
	 *            返回状态码
	 * @param msg
	 *            返回消息
	 * @author bingo<br>
	 * @date 2018年1月8日 下午1:43:50
	 */
	public ApiReturn(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 构造器
	 * 
	 * @param code
	 *            返回状态码
	 * @param msg
	 *            返回消息
	 * @param data
	 *            返回数据
	 * @author bingo<br>
	 * @date 2018年1月8日 下午1:43:50
	 */
	public ApiReturn(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/** 状态返回码 */
	public int getCode() {
		return code;
	}

	/** 状态返回码 */
	public void setCode(int code) {
		this.code = code;
	}

	/** 返回消息 */
	public String getMsg() {
		return msg;
	}

	/** 返回消息 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/** 返回数据 */
	public Object getData() {
		return data;
	}

	/** 返回数据 */
	public void setData(Object data) {
		this.data = data;
	}

}
