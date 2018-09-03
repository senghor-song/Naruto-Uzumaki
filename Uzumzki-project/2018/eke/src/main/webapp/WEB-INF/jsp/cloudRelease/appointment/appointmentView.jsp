<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>房源导入</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/reserveRel_d54edd6874dc565f78c29851b22b094e.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/xmsSelect_0687a4d524bc0ea4a5a2ff201f642e0a.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/introjs_03b43e32a5901af129087d7ae282fea4.css' rel='stylesheet' type='text/css'></link>
</head>

<body>
<div class="saleHouse">
	<input type="hidden" id="basePath" value="/release-web"/>
	<input type="hidden" id="staticPath" value="//demo.com"/>
	<input type="hidden" id="postType" value="0"/>
	<input type="hidden" id="pageIndex" value="1"/>
	<input type="hidden" id="hour" value=""/>
	<input type="hidden" id="minute" value=""/>
	<input type="hidden" id="guideIndex" value="0"/>
	<input type="hidden" id="appNum" value="0"></input>
	<div class="saleHouse-main">
		<!-- TAB切换 -->
		<div class="sale-tab-bg clear">
			<ul class="sale-tab-sub house-log">
				
					
							<li id="tab0" class="sale-sub-menu selected">
								<a href="javascript:" onclick="switchTab(this,0)">出售预约</a>
							</li>
							<li id="tab1" class="sale-sub-menu">
								<a href="javascript:" onclick="switchTab(this,1)">出租预约</a>
							</li>
					
					
				
				<li id="tab2" class="sale-sub-menu">
					<a href="javascript:" onclick="switchTab(this,2)">预约日志</a>
				</li>
			</ul>
		</div>
		<div class="release-main">
		<!-- 提示信息 -->
		<ul class="sale-content-tip">
			<li><span class="sale-tip-img"></span><span>贴心提示:</span></li>
			<li>1.预约发布的房源会占用您预约日期当天的发布条数。</li>
			<li>2.显示您所有的发布推送任务记录,在发布界面设置为“预约发布”时将自动生成预约发布任务,还没有执行的预约发布任务可以修改发布时间。</li>
		</ul>
		
		<!-- 日志筛选条件 -->
		<div id="selectDiv" style="display: none">
			<ul class="houseLog-select">
				<li>
					<select class="xmsSelect tbable100" id="handleType" onchange="getAppointmentLog(1)">
						<option value="">操作类型</option>
						<option value="A">新增</option>
						<option value="UT">修改时间</option>
						<option value="UB">删除房源</option>
						<option value="D">删除</option>
						<option value="E">执行</option>
					</select>
				</li>
				<li>
					<ul class="houseLog-list houseLog-time" >
						<li>
						<input id="log_date" value="" class="Wdate houseLog-time-tip" type="text"
							onclick="WdatePicker({
								onpicked :function(dp){getAppointmentLog(1);},
								oncleared :function(dp){getAppointmentLog(1);}
							})"
						 	readonly="readonly"></input></li>
					</ul>
				</li>
			</ul>
		</div>
		
		<div id="saleReserve" class="con1">
			
			<table class="h_list clear" width="100%" cellspacing="0" cellpadding="0s">
				<thead><tr><td>编号</td><td>执行时间</td><td>相关房源</td><td>相关网站</td></tr></thead>
				<tbody id="appTable">
					
				</tbody>
			</table>
			<div class="saleManager-bottom">
				<div class="qx"><input type="checkbox" id="reserveRel-all" onclick="selectAll()"></input><label for="reserveRel-all">全选</label></div>
				<div class="float-l">
					<input type="button" class="btn_g" value="删除" onclick="deleteApp()" id="del_button"></input>
				</div>
				<div class="float-r">
					<div id="saleManager-fanye" class="commom-fanye">
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>
</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/map.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.paginate.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js?skin=green' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/select2.full.min.js' type='text/javascript'></script>
<script src="<%=path%>/Static/Js/WdatePicker.js" type="text/javascript"></script>
<script src='<%=path%>/Static/Js/appointment_5beb57a7702b336b44791ffae4957cd4.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/intro_e523b61a7b0f0aa4d1bcc380363a6bba.js' type='text/javascript'></script> 
<script src='<%=path%>/Static/Js/introConfig_release_appointment_474706eedcd10354cd679973d42d0989.js' type='text/javascript'></script>
	
<script>
	/*翻页控件*/
	if ('0' != 0) {
		$("#saleManager-fanye").paginate({
			count 		: '0',
			start 		: '1',
			display     : 6,
			border					: false,
			text_color  			: '#50b63f',
			text_hover_color		: '#fff',
			background_color    	: '#fff',	
			background_hover_color	: '#50b63f',  
			images		: false,
			mouse		: 'click',
			onChange	: function(){
				getAppointment($(".jPag-current").html());
			}
		});
	}
	
	$("#handleType").select2({minimumResultsForSearch: -1});
</script>
</body>
</html>