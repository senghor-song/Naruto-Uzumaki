<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="mui-bar mui-bar-tab">
	<a class="mui-tab-item mui-active" href="${base }/index.shtml">
		<span class="mui-icon iconfont ">&#xe609;</span>
		<span class="mui-tab-label">交易</span>
	</a>
	<a class="mui-tab-item" href="${base }/popularize/view.shtml">
		<span class="mui-icon iconfont">&#xe611;</span>
		<span class="mui-tab-label">我的推广</span>
	</a>
	<a class="mui-tab-item" href="${base }/userCenter/view.shtml">
		<span class="mui-icon iconfont">&#xe60c;</span>
		<span class="mui-tab-label">我的账户</span>
	</a>
</nav>