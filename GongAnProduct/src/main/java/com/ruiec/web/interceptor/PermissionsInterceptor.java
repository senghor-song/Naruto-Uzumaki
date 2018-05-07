package com.ruiec.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.entity.User;
/**
 * 稳控操作拦截器
 * @author yuankai<br>
 * @date 2017年12月22日
 */
public class PermissionsInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		// 获取Session
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//LoginUserUnit loginUserUnit = (LoginUserUnit) session.getAttribute("loginUserUnit");
		LoginUserUnit loginUserUnit= (LoginUserUnit)  request.getSession().getAttribute("loginUserUnit");
		if (loginUserUnit.getUnitRank()==null) {
			// 不符合条件的，跳转到登录界面
			response.sendRedirect("/admin/common/main.shtml");
			return false;
		}
		if (user != null) {
			if (user.getId() == 1|| "city".equals(loginUserUnit.getUnitRank().getName())) {
				return true;
			}
		}
		// 不符合条件的，跳转到登录界面
		response.sendRedirect("/admin/common/main.shtml");
		return false;
	}

	/**
	 * 该方法将在Controller执行之后
	 * @author yuankai<br>
	 * @date 2017年12月23日
	 */
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 该方法将在整个请求完成之后
	 * 
	 * @author yuankai<br>
	 * @date 2017年12月23日
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)throws Exception {
	 }

}
