<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>预约重发</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/reReleasePlan_4c541f0bee152061ff7fac2b2f5cef2b.css' rel='stylesheet' type='text/css'></link>
</head>
<body>
<input type="hidden" id="basePath" value="/snatch-web"></input>
<input type="hidden" id="staticPath" value="//s.img.xms.4846.com"/>
<div class="saleHouse">
	<ul class="sale-tab-sub">
		<li class="sale-sub-menu selected">
			<a href="/WebRelease/snatch/reReleasePlan">预约重发</a>
		</li>
		<li class="sale-sub-menu">
			<a href="/WebRelease/snatch/reReleaseTask">重发日志</a>
		</li>
		<li class="sale-sub-menu">
			<a href="/WebRelease/snatch/reReleaseOpenLog">操作日志</a>
		</li>
	</ul>
	<div class="reReleasePlan reReleasePlan-main">
		<ul class="sale-content-tip">
			<li>
				<span class="sale-tip-img"></span>
				<span>贴心提示:</span>
				<span class="sale-tip-colse"></span>
			</li>
			<li>1.预约重发支持365租售宝、搜房帮、58网邻通、赶集推广、新浪二手房等端口;</li>
			<li>2.系统会帮您把端口里的房源发布日期，改为当天新发（算当天新增），提高房源的新鲜度，增加点击量;</li>
			<li>3.设置一次每天可以自动的重发，无需每天手工繁琐的操作;</li>
			<li>4."智能补发"您可以设定每天几点前若没有新增，易推房自动帮您新增，非常强大;</li>
			<li>5.当365租售宝、搜房帮端口没有剩余刷新次数时，则当天不再进行重发;</li>
			<li>6.带有新推、加急等标签以及设置了竞价、置顶的房源不会进行重发。</li>
		</ul>
		<div class="reRelease-content"><div class="reRelease-content clear"><a href="http://agent.fang.com/" target="_blank"><img class="reRelease-logo" src="http://pic.4846.com/pic/logo/soufun.png"></a><div class="reRelease-content-main"><ul class="reRelease-left"><li class="reRelease-account">缺少账号</li><li><button class="button button_primary" onclick="addAccount()">立即添加</button></li></ul></div></div><div class="reRelease-content clear"><a href="http://vip.anjuke.com/login/" target="_blank"><img class="reRelease-logo" src="http://pic.4846.com/pic/logo/anjukeNew.png"></a><div class="reRelease-content-main"><ul class="reRelease-left"><li class="reRelease-account">缺少账号</li><li><button class="button button_primary" onclick="addAccount()">立即添加</button></li></ul></div></div><div class="reRelease-content clear"><a href="http://j.esf.sina.com.cn/login?ver=5.0" target="_blank"><img class="reRelease-logo" src="http://pic.4846.com/pic/logo/sina.png"></a><div class="reRelease-content-main"><ul class="reRelease-left"><li class="reRelease-account">缺少账号</li><li><button class="button button_primary" onclick="addAccount()">立即添加</button></li></ul></div></div><div class="reRelease-content clear"><a href="http://agent.fang.com/" target="_blank"><img class="reRelease-logo" src="http://pic.4846.com/pic/logo/soufunwl.png"></a><div class="reRelease-content-main"><ul class="reRelease-left"><li class="reRelease-account">缺少账号</li><li><button class="button button_primary" onclick="addAccount()">立即添加</button></li></ul></div></div></div>
		<div class="reRelease-height"></div>
	</div>
</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/map.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/checkInput.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/reReleasePlan_f54a30f7bd4b2ddc3e898ec41a4f3b55.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.artDialog-chrome.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/highcharts.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/exporting.js' type='text/javascript'></script>
</body>
</html>