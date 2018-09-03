<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>房源导入</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/houseImport_ca6a9a5e5954c2ff13e2b2b262f107a5.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>
</head>
<body>
<!--  隐藏参数   -->
<div>
	<input type="hidden" id="buildingType" value="" />
	<input type="hidden" id="userId" value=""></input>
	<input type="hidden" id="basePath" value="/release-web" />
	<input type="hidden" id="staticPath" value="//demo.com" />
	<input type="hidden" id="contacter" value=""></input>
</div>
<div id="saleHouse" class="saleHouse">
	<div class="saleHouse-main">
		<div class="main_right">
			<!-- TAB切换部分 -->
			<div class="selectHouse">
				<ul class="sale-tab-sub manager-top">
					<li id="tab1" class="sale-sub-menu selected">
						<a id="table_1" href="javascript:" onclick="switchTab(1)">住宅</a>
					</li>
					<li id="tab2" class="sale-sub-menu">
						<a id="table_2" href="javascript:" onclick="switchTab(2)">别墅</a>
					</li>
					<li id="tab3" class="sale-sub-menu">
						<a id="table_3" href="javascript:" onclick="switchTab(3)">商铺</a>
					</li>
					<li id="tab4" class="sale-sub-menu">
						<a id="table_4" href="javascript:" onclick="switchTab(4)">写字楼</a>
					</li>
					<li id="tab5" class="sale-sub-menu">
						<a id="table_5" href="javascript:" onclick="switchTab(5)">厂房</a>
					</li>
				</ul>
			</div>
			<div class="houseManager-main">
				<ul class="sale-content-tip">
					<li>
						<span class="sale-tip-img"></span>
						<span>贴心提示:</span>
						<span class="sale-tip-colse"></span>
					</li>
					<li>1.快速复制同事易推房帐号里的房源。</li>
				</ul>
				<!-- 查询区 -->
				<ul class="saleManager-select">
					<li class="mb8">
						<input type="text" class="saleManager-search-input" placeholder="同事的易推房账号" id="xmsPhone" onkeyup="checkPhone()"></input>
						<input type="password" class="saleManager-search-input" placeholder="同事的易推房密码" id="xmsPass"></input>
						<span id="phoneTip" class="col-f60"></span>
					</li>
					<li>
						<ul class="saleManager-key float-l">
							<li>
								<ul class="manage-list">
									<li class="manage-tip manage-postType-tip" onclick="selectOption('manage-postType-tip')"><span class="manage_wd_60" style="width:60px">租售类型</span><img width="17" height="26" src="../../Static/Picture/add_plus.jpg"></img></li>
									<li>
										
											<input id="postType" type="hidden" value=""></input>
										
										<ul class="manage-list-content manage-postType-content manage_content_wd_86" style="display:none;width:86px">
											
											<li value="" title="不限">不限</li>
											<li value="0" title="录入">出售</li>
											
											<li value="1" title="秒录">出租</li>
										</ul>
									</li>
								</ul>
							</li>
							<li>
								<ul class="manage-list">
									<li class="manage-tip manage-time-tip" onclick="selectOption('manage-time-tip')"><span class="manage_wd_default" style="width:76px">更新时间排序</span><img width="17" height="26" src="../../Static/Picture/add_plus.jpg"></img></li>
									<li>
										<input id="sort" type="hidden" value=""></input>
										<ul class="manage-list-content manage-time-content manage_content_wd_default" style="display:none;width:102px">
											<li value="0" title="更新时间排序">更新时间排序</li>
											<li value="1" title="推送时间排序">推送时间排序</li>
											<li value="2" title="价格从大到小">价格从大到小</li>
											<li value="3" title="价格从小到大">价格从小到大</li>
											<li value="4" title="面积从大到小">面积从大到小</li>
											<li value="5" title="面积从小到大">面积从小到大</li>
											<li value="6" title="标签排序">标签排序</li>
										</ul>
									</li>
								</ul>
							</li>
							<li>
								<ul class="manage-list">
									<li class="manage-tip manage-sourseType-tip" onclick="selectOption('manage-sourseType-tip')"><span class="manage_wd_60" style="width:60px">房源来源</span><img width="17" height="26" src="../../Static/Picture/add_plus.jpg"></img></li>
									<li>
										<input id="sourseType" type="hidden" value=""></input>
										<ul class="manage-list-content manage-sourseType-content manage_content_wd_86" style="display:none;width:86px">
											<li value="" title="不限">不限</li>
											<li value="1" title="录入">录入</li>
											<li value="7" title="秒录">秒录</li>
											<li value="2" title="克隆">克隆</li>
											<li value="3" title="另存">另存</li>
											<li value="4" title="复制 ">复制</li>
										</ul>
									</li>
								</ul>
							</li>
							<li>
								<ul class="manage-list">
									<li class="manage-tip manage-tags-tip" onclick="selectOption('manage-tags-tip')"><span class="manage_wd_60" style="width:60px">房源标签</span><img width="17" height="26" src="../../Static/Picture/add_plus.jpg"></img></li>
									<li>
										<input id="tagsTip" type="hidden" value=""></input>
										<ul class="manage-list-content manage-tags-content manage_content_wd_86" style="display:none;max-height:200px;width:86px">
											<li tags="" title="不限">不限</li>
											
												<li tags="yrz" title="已认证">已认证</li>
											
												<li tags="new" title="新房">新房</li>
											
												<li tags="focus" title="集攻">集攻</li>
											
												<li tags="recommend" title="推荐">推荐</li>
											
												<li tags="secure" title="放心">放心</li>
											
												<li tags="hot" title="急推">急推</li>
											
											<li tags="duotu" title="多图">多图</li>
											<li tags="notTags" title="无标签">无标签</li>
											<li tags="dantu" title="非多图">非多图</li>
											<li tags="videoFlag" title="视频">视频</li>
										</ul>
									</li>
								</ul>
							</li>
							
								
								
									<li class="float-l" style="width:134px"><input class="saleManager-price" id="price1" value="" placeholder="价格(元)"></input>-<input class="saleManager-price" type="text" id="price2" value="" placeholder="价格(元)"></input></li>
								
							
							<li>
								<div class="saleManager-search-style">
									<span id="search-key-name">房源标题</span>
									<img src="../../Static/Picture/tobottom.png" width="5" height="3" style="margin-bottom:3px"></img>
									<input id="search-key-type" type="hidden" value="0"></input>
									<ul class="saleManager-search-content">
										<li aValue="0">房源标题</li>
										<li aValue="1">房源小区</li>
										<li aValue="2">房源编号</li>
										<li aValue="5">内部编号</li>
									</ul>
								</div>
								<input class="saleManager-search-key" type="text" id="title" value="" placeholder="请输入房源标题"></input>
							</li>
						</ul>
						<ul class="float-r">
							<li class="float-l"><input class="btn-search" type="button" onclick="javascript:getHousebyCondition()"></input></li>
						</ul>
					</li>
				</ul>
				<!-- 表格区 -->
				<div id="orgHouseManager" style="display:none">
					<table class="h_list" width="100%" cellspacing="0" cellpadding="0s">
						<thead><tr><td>房源编号</td><td>基本信息</td><td>更新时间</td><td>推送时间</td><td>操作</td></tr></thead>
						<tbody id="houseTable">
						</tbody>
					</table>
					<!-- 底部 -->
					<div class="saleManager-bottom">
						<div class="qx"><input type="checkbox" id="saleManager-all"></input><label for="saleManager-all">全选</label></div>
						<div>
							<div id="buttonDiv0" class="btns float-l">
								<input type="button" class="btn_o" value="批量导入" onclick="batchClone()"></input>
							</div>
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
</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/placeholder.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/map.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.lazyload.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/lazyload_b48db04a5a2ab61ce6f6ec45530c3bfb.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.paginate.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js?skin=green' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/houseImport_c9bd335f26087b1563e333390e25dd12.js' type='text/javascript'></script>
<script>
var tagMap = new Map();
$(document).ready(function() {
	switchTab($("#buildingType").val(), 0);
	tagMap = new Map();
	
		tagMap.put('yrz', '已认证');
	
		tagMap.put('new', '新房');
	
		tagMap.put('focus', '集攻');
	
		tagMap.put('recommend', '推荐');
	
		tagMap.put('secure', '放心');
	
		tagMap.put('hot', '急推');
	
});
</script>
</body>
</html>