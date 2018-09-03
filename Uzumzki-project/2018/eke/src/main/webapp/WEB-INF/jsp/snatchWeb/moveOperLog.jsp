<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>搬家日志</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/houseMove_b611f7a688cda14af4beedf424334706.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>	
</head>
<body>
<input type="hidden" value="/snatch-web" id="basePath"></input>
<div class="saleHouse">
	<div class="saleHouse-main">
		<div class="main_hd">
			<ul class="sale-tab-sub">
				<li class="sale-sub-menu">
					<a href="houseMove.html">房源搬家</a>
				</li>
				<li class="sale-sub-menu">
					<a href="houseList.html" id="houseMoveList">搬家列表</a>
				</li>
				<li class="sale-sub-menu selected">
					<a href="">搬家日志</a>
				</li>
			</ul>
		</div>
		<div class="houseMove" id="moveOperLog">
			<ul class="xms-common-search">
				<li class="xms-common-li">
					<input type="hidden" id="webId" value="0"></input>
					<ul class="xms-common-searchul" id="webId-content">
						<li class="xms-common-searchli"><span class="manage_wd_default website_span">全部网站</span><i></i></li>
						<li class="xms-common-position">
							<ul class="xms-common-searchcontent manage_content_wd_default webId-content" style="display:none">
								<li value="0">全部网站</li>
								
										<li value="34">焦点网</li>
								
										<li value="20">58同城</li>
								
										<li value="292">贝壳找房</li>
								
										<li value="7">赶集网</li>
								
										<li value="55">搜房帮</li>
								
										<li value="24">新浪网</li>
								
										<li value="77">链家在线</li>
								
										<li value="47">安居客</li>
								
							</ul>
						</li>
					</ul>
				</li>
				<li class="xms-common-li">
					<ul class="xms-common-searchul" id="failReason-content">
						<li class="xms-common-searchli"><span class="manage_wd_default failReason_span">搬家结果</span><i></i></li>
						<li class="xms-common-position">
							<input type="hidden" id="failReason" value="-1"></input>
							<ul class="xms-common-searchcontent manage_content_wd_default failReason-content" style="display:none">
								<li value="-1">搬家结果</li>
								<li value="0">成功</li>
								<li value="1">失败</li>
							</ul>
						</li>
					</ul>
				</li>
				<li class="xms-common-li">
					<button class="button button_primary" style="margin:0px;width:100px;float:right" onclick="getMoveOperLog()">查询</button>
				</li>
			</ul>
			<div id="moveOperLogTable" class="clear"><table id="tableId" class="h_list" width="100%" cellspacing="0" cellpadding="0s"><thead><tr><td>任务编号</td><td>房源编号</td><td>房源地址</td><td>网站</td><td>搬家时间</td><td>状态</td></tr></thead><tbody></tbody></table><div class="float-r"><div id="saleManager-fanye" class="commom-fanye"></div></div></div>
		</div>
	</div>		
</div>	
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/houseMove_f4a4c39e0080399071ad3a75efe3600c.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.paginate.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.artDialog-chrome.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script src="<%=path%>/Static/Js/WdatePicker.js" type="text/javascript"></script>
<script>
$(function(){
	getMoveOperLog();
});
</script>
</body>
</html>