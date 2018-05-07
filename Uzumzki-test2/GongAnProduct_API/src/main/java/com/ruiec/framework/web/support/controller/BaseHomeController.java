package com.ruiec.framework.web.support.controller;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.ruiec.framework.web.support.controller.editor.DateEditor;
import com.ruiec.framework.web.support.controller.editor.HtmlCleanEditor;
import com.ruiec.web.common.RedirectDestination;

/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 前台基础控制器
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月24日
 */
@RequestMapping("/")
public class BaseHomeController {
	
	protected static final String ERROR = "/home/common/error";
	
	/**
	 * 默认显示的每页条数 （最小）
	 */
	protected static final int DEFAULT_PAGE_SIZE = 5;
	/**
	 * 默认显示的每页条数（中等）
	 */
	protected static final int MIDDLE_PAGE_SIZE = 10;
	/**
	 * 默认显示的每页条数（最大）
	 */
	protected static final int MAX_PAGE_SIZE = 20;

	/**
	 * 注册数据转换器
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	@InitBinder
	protected void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(String.class, new HtmlCleanEditor(true, true));
		webDataBinder.registerCustomEditor(Date.class, new DateEditor(true));
	}

	/**
	 * 获取跳转对象
	 * @author 肖学良<br>
	 * Date: 2016年1月6日
	 */
	protected RedirectDestination getRedirectDestination() {
		RedirectDestination redirectDestination = new RedirectDestination();
		return redirectDestination;
	}
	
	/**
	 * 统一异常处理
	 * @author 肖学良<br>
	 * Date: 2016年1月6日
	 */
	@ExceptionHandler(Throwable.class)
	protected String throwableExceptionHandle(Throwable throwable){
		throwable.printStackTrace();
		return ERROR;
	}
	
	/**
	 * 添加request域错误信息
	 * @author 肖学良<br>
	 * Date: 2016年1月7日
	 */
	protected void addErrorMessageToRequestScope(String message){
		RequestAttributes localRequestAttributes = RequestContextHolder.currentRequestAttributes();
	    localRequestAttributes.setAttribute("error_message", message, RequestAttributes.SCOPE_REQUEST);
	}
	
	/**
	 * 添加session域错误信息
	 * @author 肖学良<br>
	 * Date: 2016年1月7日
	 */
	protected void addErrorMessageToSessionScope(String message){
		RequestAttributes localRequestAttributes = RequestContextHolder.currentRequestAttributes();
		localRequestAttributes.setAttribute("error_message", message, RequestAttributes.SCOPE_SESSION);
	}
}
