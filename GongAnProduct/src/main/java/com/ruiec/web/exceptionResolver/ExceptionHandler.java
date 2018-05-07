package com.ruiec.web.exceptionResolver;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ruiec.web.common.JsonReturn;

/**
 * 全站异常处理类
 * @author 贺云<br>
 * @date 2018年1月17日 上午10:52:10
 */
public class ExceptionHandler implements HandlerExceptionResolver {

	private static Logger LOGGER = Logger.getLogger(ExceptionHandler.class);

	/**
	 * 跳转错误页面
	 * @param
	 * @return
	 * @exception
	 * @author Senghor<br>
	 * @date 2018年1月17日 上午10:52:40
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse res, Object arg2, Exception ex) {
		ex.printStackTrace();
		ModelAndView modelAndView=new ModelAndView();
		LOGGER.error("出现异常：", ex);
		//错误页面
		modelAndView.setViewName("/admin/common/error");
		try {
			res.getWriter().write(new JsonReturn(400,"网站暂时走丢了,请前往其他页面!").toString());
			res.getWriter().flush();
			res.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

}
