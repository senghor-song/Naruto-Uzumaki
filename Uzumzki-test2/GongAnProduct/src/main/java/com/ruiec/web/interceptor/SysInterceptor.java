package com.ruiec.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ruiec.web.entity.User;

/**
 * 系统操作拦截器
 * @author 陈靖原<br>
 * @date 2017年12月22日 下午1:42:10
 */
public class SysInterceptor implements HandlerInterceptor{
	/**
	 * 在请求处理之前执行
	 * @author 陈靖原<br>
	 * @date 2017年12月18日 下午6:00:22
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        //获取Session  
        User user= (User)  request.getSession().getAttribute("user");
        if(user != null){
        	if(user.getId()==1) {
        		return true;  	
        	}
        }  
        //不符合条件的，跳转到登录界面 
        response.sendRedirect("/admin/common/main.shtml");
		return false;
	}
	/**
	 * 该方法将在Controller执行之后
	 * @author 陈靖原<br>
	 * @date 2017年12月18日 下午6:00:22
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	/**
	 * 该方法将在整个请求完成之后
	 * @author 陈靖原<br>
	 * @date 2017年12月18日 下午6:00:22
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
}
