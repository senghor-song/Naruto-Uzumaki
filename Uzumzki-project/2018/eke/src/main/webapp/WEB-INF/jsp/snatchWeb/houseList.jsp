<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>搬家列表</title>
	<link href='<%=path %>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path %>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>	
	<link href='<%=path %>/Static/Css/houseMove_b611f7a688cda14af4beedf424334706.css' rel='stylesheet' type='text/css'></link>
</head>
<body>
<input type="hidden" value="/snatch-web" id="basePath"></input>
<input type="hidden" value="1" id="videoBuilding"></input>
<div class="saleHouse">
	<div class="saleHouse-main">
		<div class="main_hd">
			<ul class="sale-tab-sub">
				<li class="sale-sub-menu">
					<a href="houseMove.html">房源搬家</a>
				</li>
				<li class="sale-sub-menu selected">
					<a href="">搬家列表</a>
				</li>
				<li class="sale-sub-menu">
					<a href="moveOperLog.html">搬家日志</a>
				</li>
			</ul>
		</div>
		<div class="houseMove">
			<input type="hidden" id="city" value="755">
			<ul class="xms-common-search">
				
					<li class="xms-common-li">
						<ul class="xms-common-searchul" id="cityHouseList-content">
							<li class="xms-common-searchli"><span class="manage_wd_60 cityHouseList_span">深圳</span><i></i></li>
							<li class="xms-common-position">
								<ul class="xms-common-searchcontent manage_content_wd_60 cityHouseList-content" style="display:none">
									
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
				
				<li class="xms-common-li">
					<input type="hidden" id="website" value="0">
					<ul class="xms-common-searchul" id="website-content">
						<li class="xms-common-searchli"><span class="manage_wd_60 website_span">全部网站</span><i></i></li>
						<li class="xms-common-position">
							<ul class="xms-common-searchcontent manage_content_wd_60 website-content" style="display:none">
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
					<input type="hidden" id="buildingType" value="0">
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
							</ul>
						</li>
					</ul>
				</li>
				<li class="xms-common-li">
					<input type="hidden" id="postType" value="2">
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
					<input type="hidden" id="tags" value="0">
					<ul class="xms-common-searchul" id="tags-content">
						<li class="xms-common-searchli"><span class="manage_wd_60 tags_span">视频房源</span><i></i></li>
						<li class="xms-common-position">
							<ul class="xms-common-searchcontent manage_content_wd_60 tags-content" style="display:none">
								<li value="0">视频房源</li>
								<li value="1">视频</li>
								<li value="2">非视频</li>
							</ul>
						</li>
					</ul>
				</li>
				<li class="xms-common-li">
					<input type="hidden" id="cell" value="">
					<ul class="xms-common-searchul" id="cell-content">
						<li class="xms-common-searchli"><span class="manage_wd_140 cell_span">全部小区</span><i></i></li>
						<li class="xms-common-position">
							<ul class="xms-common-searchcontent manage_content_wd_140 cell-content" style="display:none">
								<li>全部小区</li>
								<li>华南西苑</li>
								<li>嘉华星际湾</li>
								<li>金达花园</li>
							</ul>
						</li>
					</ul>
				</li>
				<li class="xms-common-li">
					<button class="button button_primary" style="margin:0px;width:100px;float:right" onclick="getHouseMoveDetail()">查询</button>
				</li>
			</ul>
			<div id="houseListTable" class="clear"><table id="tableId" class="h_list" width="100%" cellspacing="0" cellpadding="0s"><thead><tr><td>房源编号</td><td>网站</td><td>租售类型</td><td>房屋类型</td><td>房源信息</td><td>同步时间</td><td>状态</td></tr></thead><tbody><tr><td><span class="tableOverflow tbable100" title="3083187874"><input type="checkbox" value="3083187874" id="cb_3083187874" url="http://sz.ganji.com/fang5/3083187874x.htm" posttype="0" webid="7" webbuildingid="3083187874" city="755" name="cb_checkbox"><label for="cb_3083187874">3083187874</label></span></td><td>赶集网</td><td>出售</td><td>住宅</td><td><a href="http://sz.ganji.com/fang5/3083187874x.htm" target="_blank" class="tableOverflow" style="width:240px;display:inline-block;float:left" title="金达花园，豪华装修111平大三房，买来直接可以入住">金达花园，豪华装修111平大三房，买来直接可以入住</a></td><td>2018-05-16 16:26:27</td><td><a href="javascript:importMove('3083187874',0,7,0)">一键导入</a></td></tr><tr><td><span class="tableOverflow tbable100" title="3092072527"><input type="checkbox" value="3092072527" id="cb_3092072527" url="http://sz.ganji.com/fang5/3092072527x.htm" posttype="0" webid="7" webbuildingid="3092072527" city="755" name="cb_checkbox"><label for="cb_3092072527">3092072527</label></span></td><td>赶集网</td><td>出售</td><td>住宅</td><td><a href="http://sz.ganji.com/fang5/3092072527x.htm" target="_blank" class="tableOverflow" style="width:240px;display:inline-block;float:left" title="金达花园，豪华装修111平大三房，买来直接可以入住">金达花园，豪华装修111平大三房，买来直接可以入住</a></td><td>2018-05-16 16:26:27</td><td><a href="javascript:importMove('3092072527',0,7,0)">一键导入</a></td></tr><tr><td><span class="tableOverflow tbable100" title="3142595092"><input type="checkbox" value="3142595092" id="cb_3142595092" url="http://sz.ganji.com/fang5/3142595092x.htm" posttype="0" webid="7" webbuildingid="3142595092" city="755" name="cb_checkbox"><label for="cb_3142595092">3142595092</label></span></td><td>赶集网</td><td>出售</td><td>住宅</td><td><a href="http://sz.ganji.com/fang5/3142595092x.htm" target="_blank" class="tableOverflow" style="width:240px;display:inline-block;float:left" title="金达花园，豪华装修111平大三房。">金达花园，豪华装修111平大三房。</a></td><td>2018-05-16 16:26:27</td><td><a href="javascript:importMove('3142595092',0,7,0)">一键导入</a></td></tr><tr><td><span class="tableOverflow tbable100" title="2986016547"><input type="checkbox" value="2986016547" id="cb_2986016547" url="http://sz.ganji.com/fang5/2986016547x.htm" posttype="0" webid="7" webbuildingid="2986016547" city="755" name="cb_checkbox"><label for="cb_2986016547">2986016547</label></span></td><td>赶集网</td><td>出售</td><td>住宅</td><td><a href="http://sz.ganji.com/fang5/2986016547x.htm" target="_blank" class="tableOverflow" style="width:240px;display:inline-block;float:left" title="AAA楼层好，视野广，学位房出售，嘉华星际湾 158万 3室">AAA楼层好，视野广，学位房出售，嘉华星际湾 158万 3室</a></td><td>2018-05-16 16:26:27</td><td><a href="javascript:importMove('2986016547',0,7,0)">一键导入</a></td></tr><tr><td><span class="tableOverflow tbable100" title="2985993627"><input type="checkbox" value="2985993627" id="cb_2985993627" url="http://sz.ganji.com/fang5/2985993627x.htm" posttype="0" webid="7" webbuildingid="2985993627" city="755" name="cb_checkbox"><label for="cb_2985993627">2985993627</label></span></td><td>赶集网</td><td>出售</td><td>住宅</td><td><a href="http://sz.ganji.com/fang5/2985993627x.htm" target="_blank" class="tableOverflow" style="width:240px;display:inline-block;float:left" title="AAAA香港嘉华集团力作，107方三房配置，一个家庭的选择。">AAAA香港嘉华集团力作，107方三房配置，一个家庭的选择。</a></td><td>2018-05-16 16:26:27</td><td><a href="javascript:importMove('2985993627',0,7,0)">一键导入</a></td></tr><tr><td><span class="tableOverflow tbable100" title="3007886065"><input type="checkbox" value="3007886065" id="cb_3007886065" url="http://sz.ganji.com/fang5/3007886065x.htm" posttype="0" webid="7" webbuildingid="3007886065" city="755" name="cb_checkbox"><label for="cb_3007886065">3007886065</label></span></td><td>赶集网</td><td>出售</td><td>住宅</td><td><a href="http://sz.ganji.com/fang5/3007886065x.htm" target="_blank" class="tableOverflow" style="width:240px;display:inline-block;float:left" title="华南西苑111平大3房2卫 业主急售170万 低市场价20万">华南西苑111平大3房2卫 业主急售170万 低市场价20万</a></td><td>2018-05-16 16:26:27</td><td><a href="javascript:importMove('3007886065',0,7,0)">一键导入</a></td></tr><tr><td><span class="tableOverflow tbable100" title="2994104509"><input type="checkbox" value="2994104509" id="cb_2994104509" url="http://sz.ganji.com/fang5/2994104509x.htm" posttype="0" webid="7" webbuildingid="2994104509" city="755" name="cb_checkbox"><label for="cb_2994104509">2994104509</label></span></td><td>赶集网</td><td>出售</td><td>住宅</td><td><a href="http://sz.ganji.com/fang5/2994104509x.htm" target="_blank" class="tableOverflow" style="width:240px;display:inline-block;float:left" title="AAAA嘉华星际湾 石龙西湖di铁口上盖 和谐号 火车站 时">AAAA嘉华星际湾 石龙西湖di铁口上盖 和谐号 火车站 时</a></td><td>2018-05-16 16:26:27</td><td><a href="javascript:importMove('2994104509',0,7,0)">一键导入</a></td></tr><tr><td><span class="tableOverflow tbable100" title="2994115318"><input type="checkbox" value="2994115318" id="cb_2994115318" url="http://sz.ganji.com/fang5/2994115318x.htm" posttype="0" webid="7" webbuildingid="2994115318" city="755" name="cb_checkbox"><label for="cb_2994115318">2994115318</label></span></td><td>赶集网</td><td>出售</td><td>住宅</td><td><a href="http://sz.ganji.com/fang5/2994115318x.htm" target="_blank" class="tableOverflow" style="width:240px;display:inline-block;float:left" title="AAAA香港嘉华集团力作，一线江景   地铁口物业">AAAA香港嘉华集团力作，一线江景   地铁口物业</a></td><td>2018-05-16 16:26:27</td><td><a href="javascript:importMove('2994115318',0,7,0)">一键导入</a></td></tr></tbody></table><div><label class="float-l" style="line-height:28px"><input type="checkbox" id="cb_all">全选</label><button class="button button_primary" style="margin:0px 0 0 8px;width:120px;float:left" onclick="batchImportMove(7,0)">批量导入房源</button><button class="button button_primary" style="margin:0px 0 0 8px;width:120px;float:left" onclick="clearHouseDetail()">清空历史记录</button><div class="float-r"><div id="saleManager-fanye" class="commom-fanye"></div></div></div></div>
		</div>
	</div>		
</div>	
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/houseMove_f4a4c39e0080399071ad3a75efe3600c.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.paginate.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.artDialog-chrome.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script>
$(function(){
	$("#website").val($(".website-content").find("li[value='0']").val());
	$(".website_span").html($(".website-content").find("li[value='0']").html());
	
	
	$("#buildingType").val($(".buildingType-content").find("li[value='0']").val());
	$(".buildingType_span").html($(".buildingType-content").find("li[value='0']").html());
	
	
	$("#postType").val($(".postType-content").find("li[value='2']").val());
	$(".postType_span").html($(".postType-content").find("li[value='2']").html());
	
	$("#tags").val($(".tags-content").find("li[value='0']").val());
	$(".tags_span").html($(".tags-content").find("li[value='0']").html());
	
	$(".cityHouseList_span").html($(".cityHouseList-content").find("li[value='755']").html());
	
	getHouseMoveDetail();
});
</script>
</body>
</html>