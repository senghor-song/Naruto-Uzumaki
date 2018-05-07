package com.ruiec.web.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ruiec.web.common.CommonParam;

/**
 * 接收文件过滤器
 * @author 刘立雯
 * Date：2016年09月20日
 */
public class GetFileStream implements Filter{
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		//创建文件工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//用工厂实例化上传组件,ServletFileUpload 用来解析文件请求
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		try {
			List<FileItem> list=servletFileUpload.parseRequest(request);
			for (FileItem item : list) {
				InputStream is=item.getInputStream();
				request.setAttribute("is", is);
				//放行
				chain.doFilter(request, response);
			}
		
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			doFromUrl(request, response, "", false, "出错了");
		}
		
	}
	
	private String doFromUrl(HttpServletRequest request, HttpServletResponse response, String statusCode, boolean status, String result) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String requestUri = request.getServletPath();
		String queryString = request.getQueryString();
		requestUri += queryString != null ? "?"+queryString : "";
		if (requestUri != null) {
			request.getSession().setAttribute(CommonParam.REDIRECT_URL, requestUri);
		}
		String jsonObj="{\"statusCode\": "+statusCode+",\"returnFlag\": "+status+",\"returnResult\": \""+result+"\"}";
		response.getWriter().print(jsonObj);
		return null;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
