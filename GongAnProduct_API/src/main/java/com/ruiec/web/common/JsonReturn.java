package com.ruiec.web.common;

/**
 * 手机端公用返回操作结果的bean
 * 
 * @author 张俊<br>
 * @date 2017年11月22日 下午5:49:29
 */
public class JsonReturn {

	/**操作返回码*/
	private int code;
	/**返回语句*/
	private String msg="";
	/**返回数据**/
	private Object data=null;
	/**返回数据**/
	private String url=null;
	
	
	public JsonReturn(Object data){
		this.data=data;
		
	}
	public JsonReturn(int code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	public JsonReturn(int code,String msg,Object data){
		this.code=code;
		this.msg=msg;
		this.data=data;
	}
	
	public JsonReturn(int code,String msg,String url){
		this.code=code;
		this.msg=msg;
		this.url=url;
	}
	/**
	 * 操作返回码
	 */
	public int getCode() {
		return code;
	}
	/**
	 * 操作返回码
	 */
	public void setCode(int code) {
		this.code = code;
	}
	/**返回语句*/
	public String getMsg() {
		return msg;
	}
	/**返回语句*/
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("ApiReturn[code=]");
		builder.append(code);
		builder.append(",returnResult=");
		builder.append(msg);
		builder.append(",data=");
		builder.append(data);
		builder.append(",url=");
		builder.append(url);
		builder.append("]");
		return msg;
		
	}
	
}
