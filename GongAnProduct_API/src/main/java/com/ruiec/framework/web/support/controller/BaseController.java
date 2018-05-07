package com.ruiec.framework.web.support.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.metadata.Scope;

import org.springframework.core.task.TaskExecutor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ruiec.web.common.Message;

/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 基础控制器
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月24日
 */
public class BaseController {
	
	/**
	 * 默认显示的每页条数 （最小）
	 */
	public static final int DEFAULT_PAGE_SIZE = 5;
	/**
	 * 默认显示的每页条数（中等）
	 */
	public static final int MIDDLE_PAGE_SIZE = 10;
	/**
	 * 默认显示的每页条数（最大）
	 */
	public static final int MAX_PAGE_SIZE = 20;

	@Resource
	private Validator validator;
	@Resource
	protected TaskExecutor taskExecutor;

	
	/**
	 * 数据合法验证
	 * @param entity 待验证的对象
	 * @author 肖学良<br>
	 * Date: 2015年5月23日
	 */
	protected boolean validate(Object entity){
		Set<ConstraintViolation<Object>> localSet = validator.validate(entity);
		if(localSet.isEmpty()){
			return true;
		}
		RequestAttributes localRequestAttributes = RequestContextHolder.currentRequestAttributes();
	    localRequestAttributes.setAttribute("constraintViolations", localSet, RequestAttributes.SCOPE_REQUEST);
		return false;
		
	}
	/**
	 * 数据合法验证
	 * @param entity 待验证的对象
	 * @param ignoreProperty 不需要验证的字段
	 * @author 肖学良<br>
	 * Date: 2015年5月23日
	 */
	protected boolean validate(Object entity, String[] ignoreProperty){
		Set<ConstraintViolation<Object>> localSet = validator.validate(entity);
		if(localSet.isEmpty()){
			return true;
		}
		Set<String> errors = new HashSet<String>();
		Map<String, String> fields = new HashMap<String, String>();
		for(String field: ignoreProperty){
			fields.put(field, "");
		}
		for(ConstraintViolation<Object> o : localSet){
			String name = o.getPropertyPath().toString();
			if(fields.get(name) == null){
				errors.add(name + o.getMessage());
			}
		}
		if(errors.isEmpty()){
			return true;
		}
		RequestAttributes localRequestAttributes = RequestContextHolder.currentRequestAttributes();
	    localRequestAttributes.setAttribute("constraintViolations", localSet, RequestAttributes.SCOPE_REQUEST);
		return false;
		
	}
	/**
	 * 数据合法验证
	 * @param entity 待验证的对象
	 * @param includeProperty 需要验证的字段
	 * @author 肖学良<br>
	 * Date: 2015年5月23日
	 */
	protected boolean validateInclude(Object entity, String[] includeProperty){
		Set<ConstraintViolation<Object>> localSet = validator.validate(entity);
		if(localSet.isEmpty()){
			return true;
		}
		Set<String> errors = new HashSet<String>();
		Map<String, String> fields = new HashMap<String, String>();
		for(String field: includeProperty){
			fields.put(field, "");
		}
		for(ConstraintViolation<Object> o : localSet){
			String name = o.getPropertyPath().toString();
			if(fields.get(name) != null){
				errors.add(name + o.getMessage());
			}
		}
		if(errors.isEmpty()){
			return true;
		}
		RequestAttributes localRequestAttributes = RequestContextHolder.currentRequestAttributes();
	    localRequestAttributes.setAttribute("constraintViolations", localSet, RequestAttributes.SCOPE_REQUEST);
		return false;
	}

	/**
	 * 输出图片 
	 * @author 肖学良<br>
	 * Date: 2015年12月24日
	 */
	protected void imageOut(BufferedImage bufferedImage, HttpServletResponse response) {
		response.setHeader("pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("expires", 0);
		response.setContentType("image/jpeg");
		try {
			ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
