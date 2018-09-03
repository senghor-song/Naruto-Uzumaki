<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>重发日志</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/reReleasePlan_4c541f0bee152061ff7fac2b2f5cef2b.css' rel='stylesheet' type='text/css'></link>
</head>
<body>
<input type="hidden" id="basePath" value="/snatch-web"></input>
<div class="saleHouse">
	<ul class="sale-tab-sub">
		<li class="sale-sub-menu">
			<a href="/WebRelease/snatch/reReleasePlan">预约重发</a>
		</li>
		<li class="sale-sub-menu selected">
			<a href="/WebRelease/snatch/reReleaseTask">重发日志</a>
		</li>
		<li class="sale-sub-menu">
			<a href="/WebRelease/snatch/reReleaseOpenLog">操作日志</a>
		</li>
	</ul>
	<div class="reReleaseTask">
		<!-- 查询区 -->
		<ul class="xms-common-search" style="margin:8px 8px 0px">
			<li class="xms-common-li">
				<input type="hidden" id="webId" value="0"></input>
				<ul class="xms-common-searchul" id="webId-content">
					<li class="xms-common-searchli"><span class="manage_wd_default webId_span">-网站-</span><i></i></li>
					<li class="xms-common-position">
						<ul class="xms-common-searchcontent manage_content_wd_default webId-content" style="display:none">
							<li value="">不限</li>
							
								<li value="24">新浪网</li>
							
								<li value="55">搜房帮</li>
							
								<li value="62">搜房帮无线</li>
							
								<li value="103">安居客新版</li>
							
								<li value="104">58新版(安居客)</li>
							
								<li value="105">赶集新版(安居客)</li>
							
								<li value="149">58新版(58)</li>
							
								<li value="150">赶集新版(赶集)</li>
							
						</ul>
					</li>
				</ul>
			</li>
			<li class="xms-common-li">
				<input type="hidden" id="planNo" value=""></input>
				<ul class="xms-common-searchul" id="planNo-content">
					<li class="xms-common-searchli"><span class="manage_wd_default planNo_span">-计划号-</span><i></i></li>
					<li class="xms-common-position">
						<ul class="xms-common-searchcontent manage_content_wd_default planNo-content" style="display:none">
							<li value="-1">不限</li>
							<li value="0">计划一</li>
							<li value="1">计划二</li>
							<li value="2">计划三</li>
							<li value="3">计划四</li>
							<li value="4">计划五</li>
							<li value="5">计划六</li>
						</ul>
					</li>
				</ul>
			</li>
			<li class="xms-common-li">
				<input type="hidden" id="postType" value="-1"></input>
				<ul class="xms-common-searchul" id="postType-content">
					<li class="xms-common-searchli"><span class="manage_wd_default postType_span">-租售类型-</span><i></i></li>
					<li class="xms-common-position">
						<ul class="xms-common-searchcontent manage_content_wd_default postType-content" style="display:none">
							<li value="-1">不限</li>
							<li value="0">出售</li>
							<li value="1">出租</li>
						</ul>
					</li>
				</ul>
			</li>
			<li class="xms-common-li">
                <input type="hidden" id="buildingType" value="-1"></input>
                <ul class="xms-common-searchul" id="buildingType-content">
                    <li class="xms-common-searchli"><span class="manage_wd_default buildingType_span">-房源类型-</span><i></i></li>
                    <li class="xms-common-position">
                        <ul class="xms-common-searchcontent manage_content_wd_default buildingType-content" style="display:none">
                            <li value="-1">不限</li>
                            <li value="1">住宅</li>
                            <li value="2">别墅</li>
                            <li value="3">商铺</li>
                            <li value="4">写字楼</li>
                            <li value="5">厂房</li>
                        </ul>
                    </li>
                </ul>
            </li>
			<li class="xms-common-li">
				<input id="operTime" value="" class="Wdate xms-common-text manage_content_wd_default" type="text"
								onclick="WdatePicker({
									onpicked :function(dp){getReReleaseTask();},
									minDate:'2016-06-15',
									maxDate:'%y-%M-%d'
								})"
							 	readonly="readonly"  style="border:1px solid #64ac58;height:26px"></input>
			</li>
			<li class="xms-common-li">
				<input class="xms-common-text manage_content_wd_100" id="operKey" type="text" placeholder="请输入账号或小区"></input>
			</li>
			<li class="xms-common-li">
				<button class="button button_primary button_pay" onclick="getReReleaseTask()" style="margin:0px;width:106px;">查询</button>
			</li>
		</ul>
	</div>
	
	<!-- 操作日志表格 -->
	<div id="reReleaseTaskTable" class="tb_font"><div class="release-font">没有找到满足条件的日志</div><div class="reReleaseBottom"><div class="float-r"><div id="reRelTask-fanye" class="commom-fanye"></div></div></div></div>
</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/reReleaseTask_0bc517943bdd79d54f8b306f1cf2e202.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.paginate.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.artDialog-chrome.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script src="<%=path%>/Static/Js/WdatePicker.js" type="text/javascript"></script>
</body>
</html>