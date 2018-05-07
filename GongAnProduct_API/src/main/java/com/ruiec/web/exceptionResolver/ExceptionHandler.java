package com.ruiec.web.exceptionResolver;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ruiec.web.common.ApiReturn;

import net.sf.json.JSONObject;

public class ExceptionHandler implements HandlerExceptionResolver {

    private static Logger LOGGER = Logger.getLogger(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex) {
        ex.printStackTrace();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/common/error");
        LOGGER.info("错误.....", ex);
        ApiReturn apiReturn = new ApiReturn(500, "内部错误:"+ex);
        JSONObject json = JSONObject.fromObject(apiReturn);
        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().print(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

}
