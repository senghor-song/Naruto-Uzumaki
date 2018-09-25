package com.ruiec.framework.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

	private String FROM = "iso-8859-1";
	private String TO = "utf-8";
	
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		if (request.getMethod().equalsIgnoreCase("GET")){
			request = new GetEncodingRequest(request);
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	class GetEncodingRequest extends HttpServletRequestWrapper{

		public GetEncodingRequest(HttpServletRequest request) {
			super(request);
		}
		@Override
		public String getParameter(String name) {
			String value = super.getParameter(name);
			if(value == null){
				return null;
			}
			try {
				value = new String(value.getBytes(FROM),TO);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return value;
		}
		@Override
		public String[] getParameterValues(String name) {
			String[] values = super.getParameterValues(name);
			if(values == null){
				return null;
			}
			for(int i = 0; i<values.length; i++){
				try {
					values[i] = new String(values[i].getBytes(FROM),TO);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			return values;
		}
		
		
	}

}

