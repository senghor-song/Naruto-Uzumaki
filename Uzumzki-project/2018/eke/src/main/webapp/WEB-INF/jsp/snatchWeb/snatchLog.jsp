<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>操作日志</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/moveHouse_d07764cfb3d88b754ac805791764e99d.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>
</head>
<body>
	<input type="hidden" id="basePath" value="/snatch-web" ></input>
	<div class="saleHouse" id="moveHouse">
		<div class="saleHouse-main">
			<ul class="sale-tab-sub">
				<li class="sale-sub-menu">
					<a href="/WebRelease/snatch/houseSecretary">房源秘书</a>
				</li>
 				<li class="sale-sub-menu"> 
					<a href="/WebRelease/snatch/repetitionHouse">重复房源</a>
				</li> 
				<li class="sale-sub-menu selected">
					<a href="/WebRelease/snatch/snatchLog">操作日志</a>
				</li>
			</ul>
			<!-- 查询区 -->
			<ul class="xms-common-search" style="margin:8px 8px 0">
				<li class="xms-common-li">
					<input type="hidden" id="webId" value="-1"></input>
					<ul class="xms-common-searchul" id="website-content">
						<li class="xms-common-searchli"><span class="manage_wd_default website_span">全部网站</span><i></i></li>
						<li class="xms-common-position">
							<ul class="xms-common-searchcontent manage_content_wd_default website-content" style="display:none">
								<li value="">全部网站</li>
								
									<li value="65">赶集放心房</li>
								
									<li value="34">焦点网</li>
								
									<li value="7">VIP赶集</li>
								
									<li value="103">安居客新版</li>
								
									<li value="104">新版58(安居客)</li>
								
									<li value="41">列表网</li>
								
									<li value="105">ganji新版(安居客)</li>
								
									<li value="73">中房网</li>
								
									<li value="47">安居客</li>
								
									<li value="112">第一时间房源网</li>
								
									<li value="51">房产超市</li>
								
									<li value="149">新版58(58)</li>
								
									<li value="22">今题网</li>
								
									<li value="150">ganji新版(ganji)</li>
								
									<li value="23">去114网</li>
								
									<li value="55">搜房帮</li>
								
									<li value="24">新浪网</li>
								
									<li value="61">Q房网</li>
								
							</ul>
						</li>
					</ul>
				</li>
				<li class="xms-common-li">
					<input type="hidden" id="operType" value=""></input>
					<ul class="xms-common-searchul" id="operType-content">
						<li class="xms-common-searchli"><span class="manage_wd_60 operType_span">操作类型</span><i></i></li>
						<li class="xms-common-position">
							<ul class="xms-common-searchcontent manage_content_wd_60 operType-content" style="display:none">
								<li aValue="">操作类型</li>
								
									<li aValue="HOUSE_EDIT">编辑</li>
								
									<li aValue="HOUSE_WEIGUI_DELETE">违规删除</li>
								
									<li aValue="HOUSE_TAG">急推</li>
								
									<li aValue="HOUSE_PUSH">房源推广</li>
								
									<li aValue="REPEAT_HOUSE_EDIT">重复编辑</li>
								
									<li aValue="HOUSE_REFRESH">刷新</li>
								
									<li aValue="HOUSE_REPOST_UPLOAD_VIDEO">视频重发</li>
								
									<li aValue="HOUSE_DELETE">删除</li>
								
									<li aValue="HOUSE_DELETE_UNPUSH">未推广删除</li>
								
									<li aValue="HOUSE_REPOST">重发</li>
								
									<li aValue="HOUSE_REPOST_VIDEO">视频重发</li>
								
							</ul>
						</li>
					</ul>
				</li>
				<li class="xms-common-li">
					<input type="hidden" id="buildingType" value="-1"></input>
					<ul class="xms-common-searchul" id="buildingType-content">
						<li class="xms-common-searchli"><span class="manage_wd_60 buildingType_span">房屋类型</span><i></i></li>
						<li class="xms-common-position">
							<ul class="xms-common-searchcontent manage_content_wd_60 buildingType-content" style="display:none">
								<li value="-1">房屋类型</li>
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
					<input type="hidden" id="postType" value="-1"></input>
					<ul class="xms-common-searchul" id="postType-content">
						<li class="xms-common-searchli"><span class="manage_wd_60 postType_span">租售类型</span><i></i></li>
						<li class="xms-common-position">
							<ul class="xms-common-searchcontent manage_content_wd_60 postType-content" style="display:none">
								<li value="-1">租售类型</li>
								<li value="0">出售</li>
								<li value="1">出租</li>
							</ul>
						</li>
					</ul>
				</li>
				<li class="xms-common-li">
					<input id="operTime" value="" class="Wdate snatchLog-time-tip" type="text"
						onclick="WdatePicker({onpicked :function(dp){searchSnatchLog();},
							minDate:'2016-06-15',maxDate:'%y-%M-%d'})"
					 	readonly="readonly"  style="border:1px solid #64ac58;height:26px"></input>
				</li>
				<li class="xms-common-li">
					<input class="xms-common-text iw150" placeholder="请输入标题、小区或账号" id="snatchLog-key" type="text"></input>
				</li>
				<li class="xms-common-li">
					<button class="button button_primary button_pay" onclick="searchSnatchLog()" style="margin:0px;width:100px;">查询</button>
				</li>
			</ul>	
			<!-- 表格区 -->
			<div class="moveHouse-content clear mr8 ml8" id="snatchLog-table"></div>
		</div>
	</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/snatchOperLog_bb62b754a0c1d5e65baee33e26e8fe9f.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.artDialog-chrome.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.paginate.js' type='text/javascript'></script>
<script src="<%=path%>/Static/Js/WdatePicker.js" type="text/javascript"></script>
</body>
</html>