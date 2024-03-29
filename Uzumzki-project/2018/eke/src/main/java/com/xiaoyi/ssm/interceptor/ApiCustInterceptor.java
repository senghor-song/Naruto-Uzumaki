package com.xiaoyi.ssm.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;

import net.sf.json.JSONObject;

/**  
 * @Description: 客户APP端登录拦截器
 * @author 宋高俊  
 * @date 2018年7月18日 下午4:31:11 
 */ 
public class ApiCustInterceptor extends HandlerInterceptorAdapter{
    private final Logger log = LoggerFactory.getLogger(ApiCustInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());

        String token = request.getParameter("token");
        if (RedisUtil.findRedisOne(Global.redis_cust, token)) {
        	return true;
		}else {
			log.info("requestUri:"+requestUri);
			log.info("contextPath:"+contextPath);
			log.info("url:"+url);
			
			response.setCharacterEncoding("UTF-8");  
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = null ;
			JSONObject json = new JSONObject();
			json.put("code","400");
			json.put("msg","登录令牌不存在");
			out = response.getWriter();
			out.append(json.toString());
			return false;
		}

//        Employee employee =  (Employee) request.getSession().getAttribute("loginuser");
//        if(employee == null){
//            response.setCharacterEncoding("UTF-8");
//            log.info("非法请求：跳转到登录页面！");
//            response.sendRedirect("/WebRelease/common/login");
//            return false;
//        }else{
//            return true;
//        }
    }
 
    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//        if(modelAndView != null){  //加入当前时间
//            modelAndView.addObject("var", "测试postHandle");
//        }
    }
 
    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
 
}
