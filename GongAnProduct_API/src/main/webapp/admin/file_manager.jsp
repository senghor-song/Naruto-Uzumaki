<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.ruiec.framework.web.file.FileClient" %>
<%@ page import="com.ruiec.web.util.SettingUtils" %>
<%

//根目录路径，可以指定绝对路径，比如 /var/www/attached/
String rootPath = pageContext.getServletContext().getRealPath("/") + "attached/";
//根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
String rootUrl  = request.getContextPath() + "/attached/";
//图片扩展名
String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};

String dirName = request.getParameter("dir");
if (dirName != null) {
	if(!Arrays.<String>asList(new String[]{"image", "flash", "media", "file"}).contains(dirName)){
		out.println("Invalid Directory name.");
		return;
	}
	rootPath += dirName + "/";
	rootUrl += dirName + "/";
	File saveDirFile = new File(rootPath);
	if (!saveDirFile.exists()) {
		saveDirFile.mkdirs();
	}
}
//根据path参数，设置各路径和URL
String path = request.getParameter("path") != null ? request.getParameter("path") : "";

//排序形式，name or size or type
String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";
String content = FileClient.backgroundFileManager(dirName, path, order, SettingUtils.get().getFileServerUrl());
out.println(content);
%>