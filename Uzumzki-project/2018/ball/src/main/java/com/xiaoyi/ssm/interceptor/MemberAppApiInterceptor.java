package com.xiaoyi.ssm.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xiaoyi.ssm.model.Member;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;

/**
 * @Description: 微信小程序拦截器
 * @author 宋高俊
 * @date 2018年9月13日 上午10:46:32
 */
public class MemberAppApiInterceptor extends HandlerInterceptorAdapter {

	private final Logger log = LoggerFactory.getLogger(AdminInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 记录此次拦截的url
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());

		// 获取客户端的验证参数，在缓存中查询
		String token = request.getParameter("token");
		// 当token为空时
		if (StringUtil.isBank(token)) {
			log.info("session:" + request.getSession().getId());
			log.info("openid:" + (String) request.getSession().getAttribute("openid"));
			String openid = (String) request.getSession().getAttribute("openid");

			if (openid != null) {
				token = openid;
			}
		}

		Member member = (Member) RedisUtil.getRedisOne(Global.redis_member, token);
		if (member != null) {
			log.info("token:" + token);
			request.setAttribute("token", token);
			return true;
		}
		log.info("requestUri:" + requestUri);
		log.info("contextPath:" + contextPath);
		log.info("url:" + url);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		json.put("code", "500");
		json.put("msg", "请登录后操作");
		out = response.getWriter();
		out.append(json.toString());
		return false;
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// if(modelAndView != null){ //加入当前时间
		// modelAndView.addObject("var", "测试postHandle");
		// }
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 *
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

}
