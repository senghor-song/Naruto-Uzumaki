<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>小区透视</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/personInfo_716f3c480b3be948522f4876b329690d.css' rel='stylesheet' type='text/css'></link>
	<link href="<%=path%>/Static/Css/favicon.css" rel="shortcut icon" type="image/x-icon" ></link>
	<link href='<%=path%>/Static/Css/common_d07ab908b3dd36582756ae7b20a30b19.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/marketView_5634d05ef9f055995576c749f2d0b56c.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/WdatePicker.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/style.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/jquery.mCustomScrollbar.css' rel='stylesheet' type='text/css'></link>
</head>
<body>
	<div class="saleHouse">
		<div class="saleHouse-main">
			<div class="main_hd">
				<ul class="sale-tab-sub">
					<li class="sale-sub-menu selected" id="personInfo">
						<a href="javascript:">小区透视</a>
					</li>
				</ul>
			</div>
			<div id="cellExact" style="height:800px">
			<div class="portAnalysis marketMain" style="overflow: hidden; ">
		<ul class="sale-content-tip">
			<li>
				<span class="sale-tip-img"></span>
				<span>贴心提示:</span>
				<span class="sale-tip-colse"></span>
			</li>
			<li>1.查看某小区同一类型房源的报价区间，提升房源报价优势;</li>
			<li>2.有针对性的查看某小区其他公司在售，而我们公司没有的房源。</li>
		</ul>
		<div class="marketView-search">
			<div>
			
				<span style="float:left">
					<select id="cid" style="height:28px;line-height:28px;border:1px solid #6bb05f;">
					
						<option value="20">广州</option>
					
						<option value="752">惠州</option>
					
						<option value="755" selected="">深圳</option>
					
						<option value="760">中山</option>
					
						<option value="769">东莞</option>
					
					</select>&nbsp;
				</span>
			
				<div class="keyWordBox">
					<input id="search-input" class="search-input" placeholder="在此输入您关注的小区" onfocus="areaKeyUp()" style="margin-right:8px">
					<dl class="keyList">
					</dl>
				</div>
				<input type="button" class="btn-see" id="btn-search" onclick="get_house_stat_repeat(1)">
				<input type="hidden" id="peakValue" value="20">
				<input type="hidden" id="remaining" value="20">
				
					<span>今日还可查询<span id="remaining-span">20</span>次</span>
				
			</div>
			<ul class="search-list clear">
				<li class="search-list-eare search-web">
					<span>网站：</span>
					<a wid="2" xmswid="47" class="selected"> 安居客</a>
					<a wid="1" xmswid="55"> 房天下</a>
					<a wid="4" xmswid="20"> 58同城</a>
					<a wid="6" xmswid="24"> 新浪网</a>
					
				</li>
				
					<li class="search-list-eare search-demandType 
						>" wid="2">
						<span>类别：</span>
						<a did="0" class="selected"> 二手房</a>
						
					</li>
				
					<li class="search-list-eare search-demandType 
						
						hidden
						>" wid="1">
						<span>类别：</span>
						<a did="0" class="selected"> 二手房</a>
						
					</li>
				
					<li class="search-list-eare search-demandType 
						
						hidden
						>" wid="4">
						<span>类别：</span>
						<a did="0" class="selected"> 二手房</a>
						
					</li>
				
					<li class="search-list-eare search-demandType 
						
						hidden
						>" wid="6">
						<span>类别：</span>
						<a did="0" class="selected"> 二手房</a>
						
					</li>
				
				<li class="search-list-content search-time selectDay" rel="1">
					<span>日期：</span>
					<input id="date" value="2018-07-04" class="select-text Wdate" >
				</li>
				<li class="search-list-content">
<!-- 				<li class="search-list-content orgOption"> -->
					<span>户型：</span>
					<span>
						<select class="width_40 selectInput" name="fromRoom">
							<option value="-1" selected="selected"></option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
						</select>
						-
						<select class="width_40 selectInput" name="toRoom">
							<option value="-1" selected="selected"></option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
						</select>室
					</span>&nbsp;
					<span>面积：</span>
					<span>
						<input type="text" class="selectInput width_40" name="fromArea">
						-
						<input type="text" class="selectInput width_40" name="toArea">平米</span>&nbsp;
					<span>排序：</span>
					<select id="order" style="height:24px;line-height:24px;border:1px solid #6bb05f">
						<option value="room desc, hall desc">按户型从大到小排序</option>
						<option value="room, hall">按户型从小到大排序</option>
						<option value="houseArea desc">按面积从大到小排序</option>
						<option value="houseArea">按面积从小到大排序</option>
						<option value="repeatCnt desc" selected="">按重复数从多到少排序</option>
						<option value="ourCnt desc">按本公司重复数从多到少排序</option>
						<option value="ourCnt">按本公司重复数从少到多排序</option>
					</select>
				</li>
			</ul>
			<input type="hidden" id="district" value="">
			<input type="hidden" id="area" value="">
			<input type="hidden" id="cell" value="">
		</div>
		<div class="data-container">
		
		</div>
		<!-- 表格区 -->
		<div class="hint-content">亲，请选择小区后再点“查看”按钮^_^</div>
		<div class="table-content" style="min-height:400px; display: none;">
			<table id="repeat-table" class="h_list" width="100%" cellspacing="0" cellpadding="0s">
				<thead>
					<tr>
						<td>户型</td>
						<td id="curFloor_td">楼层</td>
						<td>面积</td>
		<!-- 				<td>朝向</td> -->
		<!-- 				<td>装修程度</td> -->
						<td>价格区间</td>
						<td>重复数</td>
						<td>本公司重复数</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody id="tb_datas">
				</tbody>
			</table>
		</div>
		<div class="float-r mt8">
			<div id="repeat-fanye" class="commom-fanye">
			</div>
		</div>
	</div>
			</div>
		</div>
	</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_b016234725ebd1612a8f62bf4261c244.js' type='text/javascript'></script>
<script>
function initWidth(){
	var height = document.documentElement.clientHeight;
	$("#cellExcat-iframe").css("min-height",height-42);
}
</script>
</body>
</html>