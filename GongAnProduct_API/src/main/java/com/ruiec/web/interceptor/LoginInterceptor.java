package com.ruiec.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ruiec.web.common.ApiReturn;
import com.ruiec.web.entity.User;

/**
 * 登录拦截器
 * @author 陈靖原<br>
 * @date 2017年12月18日 下午5:59:15
 */
public class LoginInterceptor implements HandlerInterceptor{
	/**
	 * 在请求处理之前执行
	 * @author 陈靖原<br>
	 * @date 2017年12月18日 下午6:00:22
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        //获取Session  
        HttpSession session = request.getSession();  
        User user = (User)session.getAttribute("user");
//        return true;
        if(user != null){  
        	if(null != MySessionContext.getSession(user.getIsOnline())||user.getId()==1) {
            	return true;
            }
        }
        ApiReturn apiReturn=new ApiReturn(400, "未登录,请重新登录");
        JSONObject json=JSONObject.fromObject(apiReturn);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(json);
		return false;
//        //不符合条件的，跳转到登录界面 
//        response.sendRedirect("/admin/view.shtml");
//		return false;
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
