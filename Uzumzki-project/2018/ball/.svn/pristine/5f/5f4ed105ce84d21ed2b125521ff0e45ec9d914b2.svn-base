package com.xiaoyi.ssm.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;

/**  
 * @Description: 用于接口返回数据格式
 * @author song  
 * @date 2018年7月18日 下午4:16:59 
 */ 
public class ApiMessage {
	/** 状态码 */
	private int code;
	/** 提示内容 */
	private String msg;
	/** 返回数据 */
	private Object data;

	/**  
	 * @Description: 默认成功返回
	 * @author song  
	 * @date 2018年7月18日 下午4:16:18 
	 */ 
	public static ApiMessage succeed() {
		return new ApiMessage(200, "操作成功");
	}

	/**  
	 * @Description: 默认成功返回带数据
	 * @author song  
	 * @date 2018年7月18日 下午4:16:18 
	 */ 
	public static ApiMessage succeed(Object data) {
		return new ApiMessage(200, "操作成功", JSON.parse(JSON.toJSONString(data, filter)));
	}

	/**  
	 * @Description: 默认错误返回
	 * @author song  
	 * @date 2018年7月18日 下午4:16:18 
	 */ 
	public static ApiMessage error() {
		return new ApiMessage(400, "操作错误");
	}

	/**  
	 * @Description: 默认错误返回带数据
	 * @author song  
	 * @date 2018年7月18日 下午4:16:18 
	 */ 
	public static ApiMessage error(Object data) {
		return new ApiMessage(400, "操作错误", JSON.parse(JSON.toJSONString(data, filter)));
	}
	
	/**
	 * 只返回状态码
	 * @param code
	 * @param msg
	 */
	public ApiMessage(int code) {
		this.code = code;
	}
	
	/**
	 * 只返回状态码以及提示消息
	 * @param code
	 * @param msg
	 */
	public ApiMessage(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 返回状态码、提示消息和自定义参数
	 * @param code
	 * @param msg
	 * @param data
	 */
	public ApiMessage(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = JSON.parse(JSON.toJSONString(data, filter));
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
		this.data = JSON.parse(JSON.toJSONString(data, filter));
	}

	
	private static ValueFilter filter = new ValueFilter() {
		@Override
		public Object process(Object obj, String s, Object v) {
			if (v == null)
				return "";
			return v;
		}
	};

}
