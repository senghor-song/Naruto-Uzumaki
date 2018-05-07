/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.framework.web.support.controller;

import java.util.Date;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.ruiec.framework.web.support.controller.editor.DateEditor;
import com.ruiec.web.util.RuiecDateUtils;
/**
 * 后台基础控制器
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月23日
 */
@RequestMapping("/admin")
public class BaseAdminController extends BaseController{
	
	protected static final String ERROR = "/admin/common/error";
	protected static final String LIST = "redirect:list.shtml";
	
	/**
	 * 注册数据转换器
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	@InitBinder
	protected void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		webDataBinder.registerCustomEditor(Date.class, new DateEditor(true));
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
	
	/**
	 * 获取年份数组
	 * @author 刘立雯
	 * Date：2016年8月23日
	 * @return
	 */
	protected String[] getYearStr() {
		int yearNum=Integer.parseInt(RuiecDateUtils.format_yyyy(new Date()));
		int index=(yearNum-2016)+1;
		//创建保存年份的数组
		String[] yearStr=new String[index];
		for(int i=0;i<=yearNum-2016;i++){
			yearStr[i]=Integer.toString(yearNum);
			yearNum--;
		}
		return yearStr;
	}
}
