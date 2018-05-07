<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter" %>
<%
	String str = request.getHeader("X-Requested-With");
	if ("XMLHttpRequest".equals(str)){
		String ms = "{\"message\":\"您没有此操作权限\",\"operation\":\"您没有此操作权限\"}";
		out.print(ms);
	}else{	
%>	
	<script type="text/javascript">
		alert("您没有此操作权限！！");
		top.location="${pageContext.request.contextPath}/admin/common/main.shtml";
	</script>
<%
	} 
%>



