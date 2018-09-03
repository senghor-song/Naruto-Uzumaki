<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>重复房源</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/moveHouse_d07764cfb3d88b754ac805791764e99d.css' rel='stylesheet' type='text/css'></link>
	<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/map.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/repeatHouse_3a618cf1ee8e6e20fa4bd683075ad041.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/jquery.artDialog-chrome.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/template.js' type='text/javascript'></script>
	<style>
	html, body {
		height: 100%;
	}
	</style>
</head>
<body>
	<input type="hidden" id="staticPath" value=""></input>
	<input type="hidden" id="basePath" value=""></input>
	<input type="hidden" id="synSuccess" value="1" ></input>
	<div class="saleHouse" style="height: 100%;
    top: -2px;">
		<div class="saleHouse-main">
			<ul class="sale-tab-sub">
				<li class="sale-sub-menu">
					<a href="/WebRelease/snatch/houseSecretary">房源秘书</a>
				</li>
				<li class="sale-sub-menu selected">
					<a href="/WebRelease/snatch/repetitionHouse">重复房源</a>
				</li>
				<li class="sale-sub-menu">
					<a href="/WebRelease/snatch/snatchLog">操作日志</a>
				</li>
			</ul>
			<div class="moveHouse-main">
				<!-- 查询区 -->
				<div class="moveHouse-select">
					<input type="hidden" id="moveHouse-website-content" value=""></input>
					<input type="hidden" id="loginName" value=""></input>
					<input type="hidden" id="status" value=""></input>
					<input type="hidden" id="city" value="" ></input>
					<ul class="xms-common-search" style="margin:8px 0px 0">
						<li class="xms-common-li">
							<ul class="xms-common-searchul" id="website-content">
								<li class="xms-common-searchli"><span class="manage_wd_160 website_span" style="width:180px"> </span><i></i></li>
								<li class="xms-common-position">
									<ul class="xms-common-searchcontent manage_content_wd_160 website-content" style="display:none;width:204px">
										
									</ul>
								</li>
							</ul>
						</li>
						<li class="xms-common-li">
							<button class="button button_primary button_pay" style="margin:0px;width:100px;" id="syncBtn">同步列表</button>
						</li>
					</ul>
				</div>
				<!-- 表格区 -->
				<div class="moveHouse-content clear" id="webBuilding-table"><div class="div_tips">您还未绑定账号，请先绑定</div></div>
			</div>
		</div>
	</div>
</body>
</html>