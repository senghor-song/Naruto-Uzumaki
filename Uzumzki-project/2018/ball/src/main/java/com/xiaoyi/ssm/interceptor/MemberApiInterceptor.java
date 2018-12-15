package com.xiaoyi.ssm.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;

/**
 * @Description: 微信端拦截器
 * @author 宋高俊
 * @date 2018年8月16日 下午4:56:21
 */
public class MemberApiInterceptor extends HandlerInterceptorAdapter {
	
    private static Logger logger = Logger.getLogger(MemberApiInterceptor.class.getName());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//记录此次拦截的url
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		
		String token = request.getParameter("token");
		if (StringUtil.isBank(token)) {
			logger.info("session:" + request.getSession().getId());
			logger.info("公众号访问openid:" + (String) request.getSession().getAttribute("openid"));
			token = (String) request.getSession().getAttribute("openid");
		}else {
			logger.info("小程序访问token:" + token);
		}
		
		if ("/api/common/member/login".equals(requestUri)) {
			return true;
		}
		
		// 判断是否有token
		if (!StringUtil.isBank(token)) {
			Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
			if (member != null) {
				logger.info("token:" + token);
				request.setAttribute("token", token);
				return true;
			}
		}

		logger.info("requestUri:" + requestUri);
		logger.info("contextPath:" + contextPath);
		logger.info("url:" + url);
//		response.sendRedirect("https://beta.ball.ekeae.com:8443/dist/login");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		json.put("code", 500);
		json.put("msg", "请登录后操作");
		out = response.getWriter();
		out.append(json.toString());
//
//			//设置服务器端以UTF-8编码进行输出
//		response.setCharacterEncoding("UTF-8");
//		//设置浏览器以UTF-8编码进行接收,解决中文乱码问题
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();
		//获取浏览器访问访问服务器时传递过来的cookie数组
//		Cookie[] cookies = request.getCookies();
//		//如果用户是第一次访问，那么得到的cookies将是null
//		if (cookies!=null) {
//		    out.write("您上次访问的时间是：");
//		    for (int i = 0; i < cookies.length; i++) {
//		        Cookie cookie = cookies[i];
//		        if (cookie.getName().equals("lastAccessTime")) {
//		            Long lastAccessTime =Long.parseLong(cookie.getValue());
//		            Date date = new Date(lastAccessTime);
//		            out.write(date.toLocaleString());
//		        }
//		    }
//		}else {
//		    out.write("这是您第一次访问本站！");
//		}
		
//		//用户访问过之后重新设置用户的访问时间，存储到cookie中，然后发送到客户端浏览器
//		Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis()+"");//创建一个cookie，cookie的名字是lastAccessTime
//		//将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
//		response.addCookie(cookie);
		return false;
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//	        if(modelAndView != null){  //加入当前时间
//	            modelAndView.addObject("var", "测试postHandle");
//	        }
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 *
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
