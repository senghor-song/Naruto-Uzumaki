/**
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.common;

/**
 * ajax响应消息bean
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2016年1月11日
 */
public class Message {

	private Type type;
	private String message;
	private String info;
	public String status;
	public String msg;
	public String url="";
	private Object data;
	
	public Message(){
		
	}
	/**
	 * 提示信息
	 * @author 杨磊<br>
	 * Date: 2016年1月19日
	 */
 public String getInfo() {
		return info;
	}
/**
 * 提示信息
 * @author 杨磊<br>
 * Date: 2016年1月19日
 */
	public void setInfo(String info) {
		this.info = info;
	}

/**
  * 传回的数据
  * @author 杨磊<br>
  * Date: 2016年1月19日
  */
	public Object getData() {
		return data;
	}
	
 /**
  * 传回的数据
  * @author 杨磊<br>
  * Date: 2016年1月19日
  */
	public void setData(Object data) {
		this.data = data;
	}

	public Message(Type type, String message) {
		this.type = type;
		this.message = message;
	}

	public Message(String status, String msg, String url) {
		this.status = status;
		this.msg = msg;
		this.url = url;
	}

	public static Message warn(String message) {
		return new Message(Type.warn, message);
	}

	public static Message warn(String msg, String url) {
		return new Message("0", msg, url);
	}

	public static Message error(String message) {
		return new Message(Type.error, message);
	}

	public static Message error(String msg, String url) {
		return new Message("0", msg, url);
	}

	public static Message info(String message) {
		return new Message(Type.info, message);
	}

	public static Message info(String msg, String url) {
		return new Message("1", msg, url);
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public enum Type {
		warn, error, info
	}

}
