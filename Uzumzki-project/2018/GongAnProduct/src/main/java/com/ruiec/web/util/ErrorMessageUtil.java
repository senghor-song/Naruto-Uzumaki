/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.util;

/**
 * 错误消息工具类
 * @author 熊华松<br>
 * Version: 1.0<br>
 * Date: 2016年06月25日
 */
public class ErrorMessageUtil {
	
	public static ThreadLocal<String> errorMessage = new ThreadLocal<String>();

	public static String get() {
		String message = errorMessage.get();
		errorMessage.remove();
		return message;
	}

	public static void set(String errorMessage) {
		ErrorMessageUtil.errorMessage.set(errorMessage);
	}
	
}
