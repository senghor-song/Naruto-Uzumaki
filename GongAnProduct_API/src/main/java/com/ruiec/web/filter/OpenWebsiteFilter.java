/**
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruiec.web.util.SettingUtils;


/**
 * 网站开启/关闭过滤器
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2016年1月12日
 */
public class OpenWebsiteFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse)response;
		HttpServletRequest req = (HttpServletRequest) request;
		if(SettingUtils.get().getIsOpenWebsite()){
			chain.doFilter(request, response);
		}else{
			String path = req.getRequestURI();
			if (path.startsWith(req.getContextPath() + "/admin/") || path.startsWith(req.getContextPath() + "/maintenance")) {
				chain.doFilter(req, res);
				return;
			}
			//res.addHeader("loginStatus", "accessDenied");
			//res.sendError(405);
			res.sendRedirect(req.getContextPath() + "/maintenance/view.shtml");
			return ;
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	public void destroy() {
	}
}
