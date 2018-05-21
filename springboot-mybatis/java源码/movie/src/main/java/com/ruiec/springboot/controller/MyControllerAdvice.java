package com.ruiec.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.springboot.exception.MyException;

/**
 * 全局异常处理类
 * @author qinzhitian<br>
 * @date 2017年11月20日 下午3:13:09
 */
//@ControllerAdvice
public class MyControllerAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyControllerAdvice.class);
	/**
	 * 全局异常捕捉处理
	 * @author qinzhitian<br>
	 * @date 2017年11月20日 下午3:16:36
	 */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> errorHandler(Exception ex) {
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        LOGGER.error(ex.getMessage());
        return map;
    }
    
    /**
     * 拦截捕捉自定义异常 MyException.class
     * @author qinzhitian<br>
     * @date 2017年11月20日 下午3:16:30
     */
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public Map<String, Object> myErrorHandler(MyException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());
        LOGGER.error(ex.getMsg());
        return map;
    }
    
    /*@ExceptionHandler(value = MyException.class)
    public ModelAndView myErrorHandler(MyException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("code", ex.getCode());
        modelAndView.addObject("msg", ex.getMsg());
        return modelAndView;
    }*/
}
