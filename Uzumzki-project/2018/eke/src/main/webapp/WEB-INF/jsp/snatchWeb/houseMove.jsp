<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>房源搬家</title>
<link href='<%=path %>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path %>/Static/Css/houseMove_b611f7a688cda14af4beedf424334706.css' rel='stylesheet' type='text/css'></link>
</head>
<body>
<input type="hidden" value="/snatch-web" id="basePath"></input>
<div class="saleHouse">
	<div class="saleHouse-main">
		<div class="main_hd">
			<ul class="sale-tab-sub">
				<li class="sale-sub-menu selected">
					<a href="">房源搬家</a>
				</li>
				<li class="sale-sub-menu">
					<a href="houseList.html" id="houseMoveList">搬家列表</a>
				</li>
				<li class="sale-sub-menu">
					<a href="moveOperLog.html">搬家日志</a>
				</li>
			</ul>
		</div>
		<div class="houseMove" id="houseMove">
			<input type="hidden" id="city" value="755"></input>
			
				<ul class="xms-common-search" style="margin:0px">
					<li class="xms-common-li">
						<ul class="xms-common-searchul" id="city-content">
							<li class="xms-common-searchli"><span class="manage_wd_default city_span">切换城市</span><i></i></li>
							<li class="xms-common-position">
								<ul class="xms-common-searchcontent manage_content_wd_default city-content" style="display:none">
									
									<li value="755">深圳</li>
							
									<li value="769">东莞</li>
							
									<li value="760">中山</li>
							
									<li value="752">惠州</li>
							
									<li value="20">广州</li>
							
									<li value="114">惠东</li>
							
									<li value="762">河源</li>
							
									<li value="115">博罗</li>
									
								</ul>
							</li>
						</ul>
					</li>
				 </ul>	
			 <div id="houseMoveContent" class="clear"><div class="houseMoveList" id="houseMoveList_7"><img width="100" height="40" src="http://pic.4846.com/pic/logo/ganjivip2.png" class="logoImg" onclick="webUrl(7)"><input type="text" class="keyWord" placeholder="支持小区名称和手机号码"><select class="postType"><option value="0">出售</option><option value="1">出租</option><option value="2">出售视频</option></select><select class="buildingType"><option value="1">住宅</option><option value="3">商铺</option><option value="4">写字楼</option><option value="5">厂房</option></select><button class="button button_primary button_search" type="buttom" onclick="sentHouseMoveTask(7)">搜索</button></div><div class="houseMoveList" id="houseMoveList_20"><img width="100" height="40" src="http://static.fcxms.com/release/images/logo/58tc.png" class="logoImg" onclick="webUrl(20)"><input type="text" class="keyWord" placeholder="支持小区名称和手机号码"><select class="postType"><option value="0">出售</option><option value="1">出租</option><option value="2">出售视频</option></select><select class="buildingType"><option value="1">住宅</option><option value="3">商铺</option><option value="4">写字楼</option><option value="5">厂房</option></select><button class="button button_primary button_search" type="buttom" onclick="sentHouseMoveTask(20)">搜索</button></div><div class="houseMoveList" id="houseMoveList_24"><img width="100" height="40" src="http://pic.4846.com/pic/logo/sina.png" class="logoImg" onclick="webUrl(24)"><input type="text" class="keyWord" placeholder="只支持小区名称搜索"><select class="postType"><option value="0">出售</option><option value="1">出租</option></select><select class="buildingType"><option value="1">住宅</option><option value="2">别墅</option><option value="3">商铺</option><option value="4">写字楼</option></select><button class="button button_primary button_search" type="buttom" onclick="sentHouseMoveTask(24)">搜索</button></div><div class="houseMoveList" id="houseMoveList_34"><img width="100" height="40" src="http://pic.4846.com/pic/logo/focus.jpg" class="logoImg" onclick="webUrl(34)"><input type="text" class="keyWord" placeholder="只支持小区名称搜索"><select class="postType"><option value="0">出售</option><option value="1">出租</option></select><select class="buildingType"><option value="1">住宅</option></select><button class="button button_primary button_search" type="buttom" onclick="sentHouseMoveTask(34)">搜索</button></div><div class="houseMoveList" id="houseMoveList_47"><img width="100" height="40" src="http://pic.4846.com/pic/logo/anjuke.png" class="logoImg" onclick="webUrl(47)"><input type="text" class="keyWord" placeholder="支持小区名称和手机号码"><select class="postType"><option value="0">出售</option><option value="1">出租</option><option value="2">出售视频</option><option value="3">出租视频</option></select><select class="buildingType"><option value="1">住宅</option><option value="3">商铺</option><option value="4">写字楼</option></select><button class="button button_primary button_search" type="buttom" onclick="sentHouseMoveTask(47)">搜索</button></div><div class="houseMoveList" id="houseMoveList_55"><img width="100" height="40" src="http://pic.4846.com/pic/logo/soufun.png" class="logoImg" onclick="webUrl(55)"><input type="text" class="keyWord" placeholder="只支持小区名称搜索"><select class="postType"><option value="0">出售</option><option value="1">出租</option></select><select class="buildingType"><option value="1">住宅</option><option value="3">商铺</option><option value="4">写字楼</option></select><button class="button button_primary button_search" type="buttom" onclick="sentHouseMoveTask(55)">搜索</button></div><div class="houseMoveList" id="houseMoveList_77"><img width="100" height="40" src="http://ali.nimg.fcxms.com/201712/9e8fe6ccb825faa6fa387d2d6a1f5a37.png" class="logoImg" onclick="webUrl(77)"><input type="text" class="keyWord" placeholder="只支持小区名称搜索"><select class="postType"><option value="0">出售</option><option value="1">出租</option></select><select class="buildingType"><option value="1">住宅</option></select><button class="button button_primary button_search" type="buttom" onclick="sentHouseMoveTask(77)">搜索</button></div><div class="houseMoveList" id="houseMoveList_292"><img width="100" height="40" src="http://pic.4846.com/pic/logo/beike.jpg" class="logoImg" onclick="webUrl(292)"><input type="text" class="keyWord" placeholder="只支持小区名称搜索"><select class="postType"><option value="0">出售</option><option value="1">出租</option></select><select class="buildingType"><option value="1">住宅</option></select><button class="button button_primary button_search" type="buttom" onclick="sentHouseMoveTask(292)">搜索</button></div></div>
		</div>
	</div>		
</div>	
<script src='<%=path %>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path %>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path %>/Static/Js/houseMove_f4a4c39e0080399071ad3a75efe3600c.js' type='text/javascript'></script>
<script src='<%=path %>/Static/Js/jquery.artDialog-chrome.js' type='text/javascript'></script>
<script src='<%=path %>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script>
$(function(){
	$(".city_span").html($(".city-content").find("li[value='755']").html());
	getHouseMoveInit();
});
</script>
</body>
</html>