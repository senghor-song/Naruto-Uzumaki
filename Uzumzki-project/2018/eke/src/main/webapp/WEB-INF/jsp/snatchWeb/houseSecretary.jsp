<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>房源秘书</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/moveHouse_d07764cfb3d88b754ac805791764e99d.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>	
	<link href='<%=path%>/Static/Css/introjs_03b43e32a5901af129087d7ae282fea4.css' rel='stylesheet' type='text/css'></link>
	<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/map.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/mathUtil_1467fc23826ddcf3f42074cff60da098.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/moveHouse_e3f0074bacd79cd48fba213a793d6a7e.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/jquery.artDialog-chrome.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/jquery.paginate.js' type='text/javascript'></script>
	<script src="<%=path%>/Static/Js/WdatePicker.js" type="text/javascript"></script>
	<style>
		html,body{height: 100%;}
	</style>
</head>
<body>
	<input type="hidden" id="staticPath" value="//s.img.xms.4846.com" ></input>
	<input type="hidden" id="basePath" value="/snatch-web" ></input>
	<input type="hidden" id="videoBuilding" value="1" ></input>
	<input type="hidden" id="videoReport" value="1" ></input>
	<input type="hidden" id="synSuccess" value="1" ></input>
	<div class="saleHouse" id="moveHouse" style="height: 100%;
    border-top: 0px;
    top: -1px;">
		<div class="saleHouse-main">
			<ul class="sale-tab-sub">
				<li class="sale-sub-menu selected">
					<a href="/WebRelease/snatch/houseSecretary">房源秘书</a>
				</li>
				<li class="sale-sub-menu">
					<a href="/WebRelease/snatch/repetitionHouse">重复房源</a>
				</li>
				<li class="sale-sub-menu">
					<a href="/WebRelease/snatch/snatchLog">操作日志</a>
				</li>
			</ul>
			<div class="moveHouse-main">
				<ul class="sale-content-tip">
					<li>
						<span class="sale-tip-img"></span>
						<span>贴心提示:</span>
						<span class="sale-tip-colse"></span>
					</li>
					<li>1.本功能针对安居客，可按发布时间由新到旧进行排序，每天重发最旧的2~4套房源，提高房源的新鲜度，增加点击量;</li>
					<li>2.本功能针对搜房帮等支持刷新的付费端口，将自动获取该房源在本小区排名，帮助经纪人更有针对性的刷新房源;</li>
					<li>3."重发"功能就是把该房源删除，新增一条一样的房源，发布时间变成最新的（算当天新增）;</li>
					<li>4.365租售宝、搜房帮进行重发或刷新操作会消耗套餐刷新条数，新浪、安居客、58付费、赶集付费不消耗刷新条数;</li>
				</ul>
				<!-- 查询区 -->
				<div class="moveHouse-select">
					<input type="hidden" id="moveHouse-website-content" value=""></input>
					<input type="hidden" id="loginName" value=""></input>
					<input type="hidden" id="status" value=""></input>
					<input type="hidden" id="city" value="" ></input>
					<input type="hidden" id="available" value="" ></input>
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
							<input type="hidden" id="synType" value="0"></input>
							<ul class="xms-common-searchul" id="synType-content">
								<li class="xms-common-searchli"><span class="manage_wd_60 synType_span">导入类型</span><i></i></li>
								<li class="xms-common-position">
									<ul class="xms-common-searchcontent manage_content_wd_60 synType-content" style="display:none">
										<li value="0" title="推广中房源">推广中房源</li>
										<li value="1" title="推广中重复房源">推广中重复房源</li>
										<li value="2" class='push' style="display:none" title="未推广房源">未推广房源</li>
										<li value="3" class='push' style="display:none" title="未推广中重复房源">未推广中重复房源</li>
									</ul>
								</li>
							</ul>
						</li>
						<li class="xms-common-li">
							<input type="hidden" id="area" value=""></input>
							<ul class="xms-common-searchul" id="area-content" style="width:117px;">
								<li class="xms-common-searchli all_house"><span class="manage_wd_60 area_span">全部小区</span><i></i></li>
								<li class="xms-common-position">
									<ul class="xms-common-searchcontent manage_content_wd_60 area-content" style="display:none;width:116px;">
										<li val="">全部小区</li>
									</ul>
								</li>
							</ul>
						</li>
						<li class="xms-common-li">
							<input type="hidden" id="buildingType" value="0"></input>
							<ul class="xms-common-searchul" id="buildingType-content">
								<li class="xms-common-searchli"><span class="manage_wd_60 buildingType_span">房屋类型</span><i></i></li>
								<li class="xms-common-position">
									<ul class="xms-common-searchcontent manage_content_wd_60 buildingType-content" style="display:none">
										<li value="0">房屋类型</li>
										<li value="1">住宅</li>
										<li value="2">别墅</li>
										<li value="3">商铺</li>
										<li value="4">写字楼</li>
										<li value="5">厂房</li>
										<li value="10">放心房</li>
									</ul>
								</li>
							</ul>
						</li>
						<li class="xms-common-li">
							<input type="hidden" id="postType" value="2"></input>
							<ul class="xms-common-searchul" id="postType-content">
								<li class="xms-common-searchli"><span class="manage_wd_60 postType_span">租售类型</span><i></i></li>
								<li class="xms-common-position">
									<ul class="xms-common-searchcontent manage_content_wd_60 postType-content" style="display:none">
										<li value="2">租售类型</li>
										<li value="0">出售</li>
										<li value="1">出租</li>
									</ul>
								</li>
							</ul>
						</li>
						<li class="xms-common-li">
							<input class="xms-common-text iw80" placeholder="请输入房源编号" id="syn-key" type="text"></input>
						</li>
						<li class="xms-common-li">
							<button class="button button_primary button_pay" style="margin:0px;width:75px;" id="syncBtn">同步列表</button>
						</li>
					</ul>
				</div>
				<!-- 表格区 -->
				<div class="moveHouse-content clear" id="webBuilding-table">
					<div class="div_tips">正在获取房源中，请稍后...</div>
				</div>
			</div>
		</div>
	</div>

<script src='<%=path%>/Static/Js/intro_e523b61a7b0f0aa4d1bcc380363a6bba.js' type='text/javascript'></script> 
<script src='<%=path%>/Static/Js/introConfig_snatch_b9c3b04f0849d43918c24e663c2a7100.js' type='text/javascript'></script>
	 
<script>
var houseCopy = '1';
var introIndex = parent.$("#intro_index").val();
if (introIndex != 5) {
	findDetails();
}
</script>
</body>
</html>