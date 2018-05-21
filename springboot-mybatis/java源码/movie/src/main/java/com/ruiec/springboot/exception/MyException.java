package com.ruiec.springboot.exception;

/**
 * 自定义异常类
 * @author qinzhitian<br>
 * @date 2017年11月20日 下午3:03:06
 */
public class MyException extends RuntimeException{

	private static final long serialVersionUID = -320440554868668793L;

	/**
	 * 
	 */
	public MyException(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	/**
	 * 错误代码
	 */
	private int code;
	
	/**
	 * 错误信息
	 */
	private String msg;

	/**
	 * 错误代码
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 错误代码
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 错误信息
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 错误信息
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
