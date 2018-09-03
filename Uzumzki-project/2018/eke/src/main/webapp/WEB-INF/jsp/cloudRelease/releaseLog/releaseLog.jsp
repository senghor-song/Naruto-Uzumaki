<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>发布日志</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/houseLog_46dd40baed72155ff6aaca6ff031b922.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/xmsSelect_0687a4d524bc0ea4a5a2ff201f642e0a.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/introjs_03b43e32a5901af129087d7ae282fea4.css' rel='stylesheet' type='text/css'></link>
</head>

<body>
<!--  隐藏参数   -->
<div>
	<input type="hidden" id="index" value="4" ></input>
	<input type="hidden" id="pageIndex" value="1" ></input>
	<input type="hidden" id="basePath" value="/release-web" />
	<input type="hidden" id="buildingId" value="0" />
	<input type="hidden" id="today" value="2018-05-15" />
	<input type="hidden" id="guideIndex" value="0"></input>
	<input type="hidden" id="guideRelease" value="0"></input>
</div>

<div class="saleHouse">
	<div class="saleHouse-main">
		<!-- TAB切换 -->
		<div class="sale-tab-bg clear">
			<ul class="sale-tab-sub house-log">
				<li id="tab4" class="sale-sub-menu selected">
					<a href="javascript:ajaxSub2(4)">发布统计</a>
				</li>
				<li id="tab0" class="sale-sub-menu">
					<a href="javascript:ajaxSub2(0)">成功记录</a>
				</li>
				<li id="tab1" class="sale-sub-menu">
					<a href="javascript:ajaxSub2(1)">日志查看</a>
				</li>
				<li id="tab2" class="sale-sub-menu">
					<a href="javascript:ajaxSub2(2)">房源查看</a>
				</li>
				<li id="tab3" class="sale-sub-menu">
					<a href="javascript:ajaxSub2(3)">网站查看</a>
				</li>
			</ul>
		</div>
		<div id="selectDiv" class="release-main" style="display: none;">
		<ul class="houseLog-select">
			<li>
				<select class="xmsSelect tbable120" id="cell" onchange="ajaxSub()">
					<option value="">全部小区</option>
					
				</select>
			</li>
			<li>
				<select class="xmsSelect tbable100" id="webId" onchange="ajaxSub()">
					<option value="0">全部网站</option>
					
				</select>
			</li>
			<li id="statusLi">
				<select class="xmsSelect tbable100" id="status" onchange="ajaxSub()">
					<option value="-1">全部状态</option>
					<option value="0">发布中</option>
					<option value="1">发布成功</option>
					<option value="5">修改中</option>
					<option value="6">修改成功</option>
					<option value="2">删除中</option>
					<option value="3">删除成功</option>
					<option value="4">失败</option>
					<option value="7">修改发布成功</option>
				</select>
			</li>
			<li>
				<ul class="houseLog-list houseLog-time" >
					<li>
					<input id="time" value="2018-05-15" class="Wdate houseLog-time-tip" style="width: 90px;" type="text"
						onclick="WdatePicker({
							onpicked :function(dp){ajaxSub();},
							isShowClear: false,
							minDate:'2018-04-16',
							maxDate:'2018-05-15'
						})"
					 	readonly="readonly"></input></li>
				</ul>
			</li>
		</ul>
		<ul class="float-r query_input">
			<li><input class="saleManager-search-key" style="width: 100px;" type="text" id="loginName" value="" onkeydown="enterSumbit()" placeholder="网站账号"></input></li>
			<li><input class="saleManager-search-key" style="width: 125px;" type="text" id="title" value="" onkeydown="enterSumbit()" placeholder="请输入标题或房源编号"></input></li>
			<li><input class="btn-search" type="button" onclick="ajaxSub()"></input></li>
		</ul>
	</div>
	<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/placeholder.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/jquery.timers-1.2.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/highcharts.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/exporting.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/jquery.paginate.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js?skin=green' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/select2.full.min.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/tableSort.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/stat.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/releaseLog_9c4e65a69a3578ebf1a25df8dce56884.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/intro_e523b61a7b0f0aa4d1bcc380363a6bba.js' type='text/javascript'></script> 
	<script src='<%=path%>/Static/Js/introConfig_releaseLog_cb14a7bdf2ca1c9b65f07411fce60e61.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/WdatePicker.js" type="text/javascript'></script>
	<script src='<%=path%>/Static/Js/echarts-2.2.7/build/dist/echarts.js'></script>
	<script src='<%=path%>/Static/Js/echarts-2.2.7/src/chart/line.js' type='text/javascript'></script>
	<!-- 显示区域 -->
	<div id="logDiv" class="release-main">
	
		<!-- 发布统计 -->
		<div class="houseLog lookAsCount conDiv con4">
			<div id="zy_Pushcount" style="width: 100%;height: 500px">
				
			</div>
			<div id="lookAsCount-time" >
				
			</div>
		</div>
		<!-- 成功记录 -->
		<div id="release-success" class="houseLog lookAsLog conDiv con0" style="display: none;">
			<table class="h_list clear" width="100%" cellspacing="0" cellpadding="0s">
				<thead><tr><td>房源编号</td><td>基本信息</td><td>目标网站</td><td>操作类型</td><td>发布账号</td><td>状态</td><td>操作时间</td></tr></thead>
				<tbody>
					
				</tbody>
			</table>
			<!-- 表格下面 -->
			<div class="saleManager-bottom">
				<div class="qx"><input type="checkbox" id="houseLog-all"><label for="houseLog-all">全选</label></div>
				<div class="float-l">
					<input type="button" class="btn_g" value="删除" onclick="checkBuildingNum()" id="del_button">
				</div>
				<div class="float-r">
					<div id="saleManager-fanye" class="commom-fanye jPaginate" style="padding-left: 64px;"><div class="jPag-control-back"><a class="jPag-first" style="color: rgb(80, 182, 63); background-color: rgb(255, 255, 255);">首页</a><span class="jPag-sprevious">«</span></div><div style="overflow: hidden; width: 20px;"><ul class="jPag-pages" style="width: 18px;"><li><span class="jPag-current" style="color: rgb(255, 255, 255); background-color: rgb(80, 182, 63);">1</span></li></ul></div><div class="jPag-control-front" style="left: 88px;"><span class="jPag-snext">»</span><a class="jPag-last" style="color: rgb(80, 182, 63); background-color: rgb(255, 255, 255);">尾页</a></div></div>
				</div>
			</div>
		</div>
		
		<!-- 查看日记 -->
		<div class="houseLog lookAsLog conDiv con1" style="display: none;">
			<table class="h_list clear" width="100%" cellspacing="0" cellpadding="0s">
				<thead><tr><td>房源编号</td><td>基本信息</td><td>目标网站</td><td>操作类型</td><td>发布账号</td><td>状态</td><td>操作时间</td></tr></thead>
				<tbody>
					
				</tbody>
			</table>
			<!-- 表格下面 -->
			<div class="saleManager-bottom">
				<div class="float-r">
					<div id="saleManager-fanye" class="commom-fanye jPaginate" style="padding-left: 64px;"><div class="jPag-control-back"><a class="jPag-first" style="color: rgb(80, 182, 63); background-color: rgb(255, 255, 255);">首页</a><span class="jPag-sprevious">«</span></div><div style="overflow: hidden; width: 20px;"><ul class="jPag-pages" style="width: 18px;"><li><span class="jPag-current" style="color: rgb(255, 255, 255); background-color: rgb(80, 182, 63);">1</span></li></ul></div><div class="jPag-control-front" style="left: 88px;"><span class="jPag-snext">»</span><a class="jPag-last" style="color: rgb(80, 182, 63); background-color: rgb(255, 255, 255);">尾页</a></div></div>
				</div>
			</div>
		</div>
		
		<!-- 房源查看 -->
		<div class="houseLog lookAsHouse conDiv con2" style="display: none;">
			<table class="h_list" width="100%" cellspacing="0" cellpadding="0s">
				<thead><tr><td>房源编号</td><td>标题</td><td>次数</td><td>面积</td><td>价格</td><td>楼层</td></tr></thead>
				<tbody>
					
				</tbody>
			</table>
			<!-- 表格下面 -->
			<div class="saleManager-bottom">
				<div class="float-r">
					<div id="saleManager-fanye" class="commom-fanye jPaginate" style="padding-left: 64px;"><div class="jPag-control-back"><a class="jPag-first" style="color: rgb(80, 182, 63); background-color: rgb(255, 255, 255);">首页</a><span class="jPag-sprevious">«</span></div><div style="overflow: hidden; width: 20px;"><ul class="jPag-pages" style="width: 18px;"><li><span class="jPag-current" style="color: rgb(255, 255, 255); background-color: rgb(80, 182, 63);">1</span></li></ul></div><div class="jPag-control-front" style="left: 88px;"><span class="jPag-snext">»</span><a class="jPag-last" style="color: rgb(80, 182, 63); background-color: rgb(255, 255, 255);">尾页</a></div></div>
				</div>
			</div>
		</div>
		
		<!-- 网站查看 -->
		<div class="houseLog lookAsWebsite conDiv con3" style="display: none;">
			<table class="h_list" width="100%" cellspacing="0" cellpadding="0s">
				<thead><tr>
					<td>网站列表</td>
					
						<td>05月10日</td>
					
						<td>05月11日</td>
					
						<td>05月12日</td>
					
						<td>05月13日</td>
					
						<td>05月14日</td>
					
						<td>05月15日</td>
					
						<td>05月16日</td>
					
					<td>合计</td></tr>
				</thead>
				<tbody>
					
				</tbody>
			</table>
		</div>
	</div>
</div>
<script>
$("#cell").select2();
$("#webId").select2();
$("#status").select2({minimumResultsForSearch: -1});

function ajaxSub2(number){
	$(".conDiv").hide();
	$(".con"+number).show();
	$(".sale-sub-menu").removeClass("selected");
	$("#tab"+number).addClass("selected");
}
require.config({
    paths: {
        echarts: '<%=path%>/Static/Js/echarts-2.2.7/build/dist'
    }
});
require(
    [
        'echarts',
        'echarts/chart/line',
		'echarts/chart/bar'
    ],
    function (ec) {
        // 基于准备好的dom，初始化echarts图表
        var myChart = ec.init(document.getElementById('lookAsCount-time')); 
        
        var option = {
			tooltip : {
				trigger: 'axis'
			},
			title : {
				text: '最近15天推送统计',
				x:'center'
			},
			legend: {
				data:['成功','失败'],
				x:'left'
			},
			calculable : true,
			xAxis : [
				{
					type : 'category',
					boundaryGap : false,
					data : ['周一','周二','周三','周四','周五','周六','周日']
				}
			],
			yAxis : [
				{
					type : 'value'
				}
			],
			series : [
				{
					name:'成功',
					type:'line',
					stack: '总量',
					data:[120, 132, 101, 134, 90, 230, 210]
				},
				{
					name:'失败',
					type:'line',
					stack: '总量',
					data:[220, 182, 191, 234, 290, 330, 310]
				}
			]
		};
            

        // 为echarts对象加载数据 
        myChart.setOption(option); 
    }
);
require(
        [
            'echarts',
            'echarts/chart/line',
			'echarts/chart/bar',
			'echarts/chart/pie',
			'echarts/chart/funnel'
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart2 = ec.init(document.getElementById('zy_Pushcount')); 
            
            var option2 = {
				title : {
					text: '今日推送统计',
					subtext: '发布总数0',
					x:'center'
				},
				tooltip : {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				legend: {
					orient : 'vertical',
					x : 'left',
					data:['进行中','成功数','失败数']
				},
				toolbox: {
					feature : {
						mark : {show: true},
						dataView : {show: true, readOnly: false},
						magicType : {
							show: true, 
							type: ['pie', 'funnel'],
							option: {
								funnel: {
									x: '25%',
									width: '50%',
									funnelAlign: 'left',
									max: 1548
								}
							}
						},
						restore : {show: true},
						saveAsImage : {show: true}
					}
				},
				calculable : true,
				series : [
					{
						name:'',
						type:'pie',
						radius : '55%',
						center: ['50%', '60%'],
						data:[
							{value:2, name:'进行中'},
							{value:3, name:'成功数'},
							{value:2, name:'失败数'}
						]
					}
				]
			};
            // 为echarts对象加载数据 
            myChart2.setOption(option2); 
        }
    );
</script>
</body>
</html>