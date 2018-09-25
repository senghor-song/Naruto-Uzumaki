package com.ruiec.web.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ruiec.web.entity.User;

/**
 * 登录拦截器
 * 
 * @author 陈靖原<br>
 * @date 2017年12月18日 下午5:59:15
 */
public class LoginInterceptor implements HandlerInterceptor {
	/**
	 * 在请求处理之前执行
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月18日 下午6:00:22
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// LogUtil.replaceAllJs(request);
		// 获取Session
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			if (request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equals("XMLHttpRequest")) {
				response.setHeader("sessionstatus", "timeout");
				return false;
			}
		} else {
			if (null != MySessionContext.getSession(user.getIsOnline()) || user.getId() == 1) {
				return true;
			} else {
				// 如果判断是 AJAX 请求,直接设置为session超时
				if (request.getHeader("x-requested-with") != null
						&& request.getHeader("x-requested-with").equals("XMLHttpRequest")) {
					response.setHeader("sessionstatus", "timeout");
					return false;
				}
			}
		}
		// 不符合条件的，跳转到登录界面
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script>");
		out.println("window.open ('" + request.getContextPath() + "/admin/view.shtml','_top')");
		out.println("</script>");
		out.println("</html>");
		out.flush();
		out.close();
		return false;
	}

	/**
	 * 该方法将在Controller执行之后
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月18日 下午6:00:22
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 该方法将在整个请求完成之后
	 * 
	 * @author 陈靖原<br>
	 * @date 2017年12月18日 下午6:00:22
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}
