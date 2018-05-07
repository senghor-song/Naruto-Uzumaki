/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.interceptor;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ruiec.web.util.CookieUtils;

/**
 * 令牌拦截器, 此拦截器将可以避免站点遭受跨站请求伪造(CSRF)的攻击
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2016年03月04日
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		/** 由于一些未知的错误, 将下面的代码加了注释, 加了注释以后, 此拦截器就会直接放行请求, 失去对站点的保护作用 **/
//		String token = null;
//		Cookie cookie = CookieUtils.getCookie("token", request);
//		if (cookie != null) {
//			token = cookie.getValue();
//		}
//		if (request.getMethod().equalsIgnoreCase("POST")) {
//			String ajaxHeader = request.getHeader("X-Requested-With");
//			if ((ajaxHeader != null) && (ajaxHeader.equalsIgnoreCase("XMLHttpRequest"))) {
//				if ((token != null) && (token.equals(request.getHeader("token")))){
//					return true;
//				}
//				response.addHeader("tokenStatus", "accessDenied");
//			} else if ((token != null) && (token.equals(request.getParameter("token")))) {
//				return true;
//			}
//			if (token == null) {
//				token = UUID.randomUUID().toString();
//				CookieUtils.addCookie("token", token, response);
//			}
//			response.sendError(403, "Bad or missing token!");
//			return false;
//		}
//		if (token == null) {
//			token = UUID.randomUUID().toString();
//			CookieUtils.addCookie("token", token, response);
//		}
//		request.setAttribute("token", token);
		return true;
	}
}
