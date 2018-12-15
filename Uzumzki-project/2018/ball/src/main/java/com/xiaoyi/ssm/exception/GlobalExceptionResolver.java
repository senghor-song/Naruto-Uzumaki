package com.xiaoyi.ssm.exception;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xiaoyi.ssm.dto.ApiMessage;

/**
 * 错误信息统一处理
 * 对未处理的错误信息做一个统一处理
 * @author 宋高俊
 *
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    private final Logger logger = Logger.getLogger(GlobalExceptionResolver.class.getName());
	
	@ResponseBody
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		//这里有2种选择
		//跳转到定制化的错误页面
	    /*ModelAndView error = new ModelAndView("error");
		error.addObject("exMsg", ex.getMessage());
		error.addObject("exType", ex.getClass().getSimpleName().replace("\"", "'"));*/
		//返回json格式的错误信息
		try {
//			PrintWriter writer = response.getWriter();
			//这句话的意思，是让浏览器用utf8来解析返回的数据
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			//这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
			response.setCharacterEncoding("UTF-8");
			OutputStream ps = response.getOutputStream();
//			BaseResult<String> result=new BaseResult(false, ex.getMessage());
			ApiMessage apiMessage = new ApiMessage(404,"服务器正忙,请稍后再试");
			ps.write(JSON.toJSONString(apiMessage).getBytes("UTF-8"));
			ps.flush();
		} catch (Exception e) {
			logger.error("Exception:",e);
		}
		logger.error("访问" + request.getRequestURI() + " 发生错误, 错误信息:" + ex.getMessage(),ex);
		return null;
	}
	

}
