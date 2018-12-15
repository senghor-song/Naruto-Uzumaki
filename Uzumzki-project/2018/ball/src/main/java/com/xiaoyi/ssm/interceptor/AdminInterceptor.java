package com.xiaoyi.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xiaoyi.ssm.model.Permission;
import com.xiaoyi.ssm.model.Staff;
import com.xiaoyi.ssm.service.PermissionService;
import com.xiaoyi.ssm.util.SpringUtils;

/**  
 * @Description: 后台登录拦截器
 * @author 宋高俊  
 * @date 2018年8月16日 上午11:56:22 
 */ 
public class AdminInterceptor extends HandlerInterceptorAdapter {
	
    private static Logger logger = Logger.getLogger(AdminInterceptor.class.getName());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//记录此次拦截的url
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());

		if ("/admin/common/login".equals(requestUri)) {
			return true;
		}

		Staff staff = (Staff) request.getSession().getAttribute("loginStaffInfo");
		if (staff == null) {
			logger.info("requestUri:" + requestUri);
			logger.info("contextPath:" + contextPath);
			logger.info("url:" + url);
			logger.info("非法请求：跳转到登录页面！");
			response.setCharacterEncoding("UTF-8");
			response.sendRedirect("/admin/common/login");
			return false;
		} else {
			String[] strings = requestUri.split("/");
			if ("listview".equals(strings[strings.length - 1 ])) {
				PermissionService permissionService = SpringUtils.getBean("permissionServiceImpl", PermissionService.class);
				Permission permission = permissionService.selectIsMenu(staff.getPower(), requestUri);
				if (permission == null) {
					response.setCharacterEncoding("UTF-8");
					response.sendRedirect("/admin/common/index");
					return false;
				}
			}
			return true;
		}
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
