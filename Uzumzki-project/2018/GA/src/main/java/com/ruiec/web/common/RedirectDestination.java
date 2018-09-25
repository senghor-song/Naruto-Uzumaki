/**
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br>
 */
package com.ruiec.web.common;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 跳转的目标页面
 * @author 熊华松<br>
 * Version 1.0<br>
 * Date: 2015-5-6
 */

public class RedirectDestination {
	
	/**
	 * 跳转链接
	 */
	private String url = "";

	/**
	 * 提示消息
	 */
	private String message;

	/**
	 * 内容
	 */
	private String content = "";
	/**
	 * 是否自动跳转
	 */
	private boolean autoRedirect = true;
	
	/**
	 * 添加跳转信息到session中
	 * @author 肖学良<br>
	 * Date: 2016年1月11日
	 */
	public void addToSession(){
		RequestAttributes localRequestAttributes = RequestContextHolder.currentRequestAttributes();
		localRequestAttributes.setAttribute("redirectDestination", this, RequestAttributes.SCOPE_SESSION);
	}
	
	/**
	 * 跳转链接
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 跳转链接
	 */
	public RedirectDestination setUrl(String url) {
		this.url = url;
		return this;
	}

	/**
	 * 提示消息
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 提示消息
	 */
	public RedirectDestination setMessage(String message) {
		this.message = message;
		return this;
	}

	/**
	 * 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 内容
	 */
	public RedirectDestination setContent(String content) {
		this.content = content;
		return this;
	}

	/**
	 * 是否自动跳转
	 * @author 肖学良<br>
	 * Date: 2015年9月30日
	 */
	public boolean getAutoRedirect() {
		return autoRedirect;
	}

	/**
	 * 是否自动跳转
	 * @author 肖学良<br>
	 * Date: 2015年9月30日
	 */
	public RedirectDestination setAutoRedirect(boolean autoRedirect) {
		this.autoRedirect = autoRedirect;
		return this;
	}

}
