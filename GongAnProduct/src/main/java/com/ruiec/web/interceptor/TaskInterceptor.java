package com.ruiec.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ruiec.web.common.LoginUserUnit;
import com.ruiec.web.entity.User;

/**
 * 合成研判拦截器
 * @author Senghor<br>
 * @date 2018年1月11日 下午5:00:04
 */
public class TaskInterceptor implements HandlerInterceptor{
	
	/**
	 * 在请求处理之前执行
	 * @author Senghor<br>
	 * @date 2018年1月11日 下午5:00:04
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        //获取Session  
        User user= (User)  request.getSession().getAttribute("user");
        LoginUserUnit loginUserUnit= (LoginUserUnit)  request.getSession().getAttribute("loginUserUnit");
        if(user != null){
        	if(user.getId()==1) {
        		return true;  	
        	}
        }  
        if (loginUserUnit.getUnitIds()!=null && loginUserUnit.getUnitIds().size()>0) {
    		return true;  	
		}
        //不符合条件的，跳转到首页
        response.sendRedirect("/admin/common/main.shtml");
		return false;
	}
	/**
	 * 该方法将在Controller执行之后
	 * @author Senghor<br>
	 * @date 2018年1月11日 下午5:00:04
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	/**
	 * 该方法将在整个请求完成之后
	 * @author Senghor<br>
	 * @date 2018年1月11日 下午5:00:04
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
}
