/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台重定向
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2016年1月12日
 */
public class AdminRedirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/admin/common/main.shtml");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
