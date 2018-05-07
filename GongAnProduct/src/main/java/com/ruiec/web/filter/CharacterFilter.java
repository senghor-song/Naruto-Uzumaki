package com.ruiec.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ruiec.web.common.TxRequest;
/**
 * 非法字符过滤器
 * @author qinzhitian<br>
 * @date 2018年2月6日 下午5:11:46
 */
public class CharacterFilter implements Filter {

	 @Override
     public void destroy() {
    }

     @Override
     public void doFilter(ServletRequest req, ServletResponse res,
                       FilterChain chain) throws IOException, ServletException {
             HttpServletRequest request = (HttpServletRequest)req;
             TxRequest wrapRequest= new TxRequest(request,request.getParameterMap());
             chain.doFilter(wrapRequest, res);
    }

     @Override
     public void init(FilterConfig arg0) throws ServletException {
    }

     
}
