package com.ruiec.springboot.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import lm.com.framework.webmvc.result.JsonResult;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.ruiec.springboot.util.RequestDTO;
import com.ruiec.springboot.util.ResponseDTO;

/**
 * 基础控制器
 * @author qinzhitian<br>
 * @date 2017年11月16日 上午11:27:17
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
	
	/**
	 * 数据合法验证
	 * @param entity 待验证的对象
	 * @author qinzhitian<br>
	 * @date 2017年11月16日 上午11:26:15
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
	 * @author qinzhitian<br>
	 * @date 2017年11月16日 上午11:26:45
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
	 * @author qinzhitian<br>
	 * @date 2017年11月16日 上午11:26:56
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
	 * 获取请求传输对象
	 * @author qinzhitian<br>
	 * @date 2017年11月21日 下午9:02:05
	 */
	protected Map<String, Object> getMap(HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO();
		Map<String, String[]> parameters = request.getParameterMap();
		if (null != parameters) {
			for (Entry<String, String[]> item : parameters.entrySet()) {
				if ("columnPattern".equalsIgnoreCase(item.getKey()) || "fields".equalsIgnoreCase(item.getKey()))
					requestDTO.put("columnPattern", item.getValue()[0]);
				else if ("pageIndex".equalsIgnoreCase(item.getKey()) || "page".equalsIgnoreCase(item.getKey()))
					requestDTO.put("pageIndex", item.getValue()[0]);
				else if ("pageSize".equalsIgnoreCase(item.getKey()) || "per_page".equalsIgnoreCase(item.getKey()))
					requestDTO.put("pageSize", item.getValue()[0]);
				else if ("isStatCount".equalsIgnoreCase(item.getKey()) || "stat".equalsIgnoreCase(item.getKey()))
					requestDTO.put("isStatCount", item.getValue()[0]);
				else {
					if (item.getValue().length > 1)
						requestDTO.put(item.getKey(), item.getValue());
					else
						requestDTO.put(item.getKey(), item.getValue()[0]);
				}
			}
		}
		return requestDTO.getData();
	}

	/**
	 * 返回结果
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午2:16:55
	 */
	protected <T> JsonResult toJsonResult(ResponseDTO responseDTO) {
		return new JsonResult(responseDTO.isSuccess(), responseDTO.getCode(), responseDTO.getMessage(),
				responseDTO.getData());
	}
}
