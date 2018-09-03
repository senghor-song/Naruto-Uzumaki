<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>  
  <head>
    <title>房产易推房登陆页-房产中介公司营销管理的软件</title>
    
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/personHouse_36f13665516440aaad6239ca8edc23c9.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/introjs_03b43e32a5901af129087d7ae282fea4.css' rel='stylesheet' type='text/css'></link>
	<link href="<%=path%>/Static/Js/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css"></head>
<body >
<div class="saleHouse">
		<input type="hidden" id="basePath" value=""/>
		<input type="hidden" id="cust" value="${cust}"/>
		<input type="hidden" id="staticPath" value="//demo.com"></input>
		<input type="hidden" id="snatchUrl" value="//demo.com/snatch-web"/>
		<input type="hidden" id="releaseWebUrl" value="//demo.com/release-web"/>
		<input type="hidden" id="city" value="755"></input>
		<input type="hidden" id="pageNow" value="1"/>
		<input type="hidden" id="district" value=""/>
		<input type="hidden" id="districtNa" value=""/>
		<input type="hidden" id="districtNas" value=""/>
		<input type="hidden" id="webNames" value=""/>
		
		<input type="hidden" id="area" value=""/>
		<input type="hidden" id="areaNa" value=""/>
		<input type="hidden" id="houseType" value=""/>
		<input type="hidden" id="roomType" value=""/>
		<input type="hidden" id="personHouse-Website" value=""/>
		<input type="hidden" id="postData" value=""/>
		<input type="hidden" id="houseSum" value="0"></input>
		<input type="hidden" id="guideIndex" value="0"></input>
		<input type="hidden" id="houseArea" value="0"></input>
		<input type="hidden" id="rentType" value=""/>
		<input type="hidden" id="phoneFilter" value="0"/>
		<input type="hidden" id="startTime" value=""/>
        <input type="hidden" id="endTime" value=""/>
		<input type="hidden" id="agentFilter" value="0"/>
		<input type="hidden" id="repeatTag" value="0"/>
		<input type="hidden" id="postType" value="${postType}"/>
		<div class="saleHouse-main">
		<!-- 选择房源 -->
		<div class="selectHouse">
			<!-- TAB切换部分 -->
			<div class="sale-tab-bg clear">
				<ul class="sale-tab-sub">
					<li id="personHouse0" class="sale-sub-menu selected">
						<a href="javascript:void(0)" title="" onclick="switchTab(0)">出售</a>
					</li>
					<li id="personHouse1" class="sale-sub-menu">
						<a href="javascript:void(0)" title="" onclick="switchTab(1)">出租</a>
					</li>
					<li id="personHouse2" class="sale-sub-menu">
						<a href="javascript:void(0)" title="" onclick="switchTab(2)">求购</a>
					</li>
					<li id="personHouse3" class="sale-sub-menu">
						<a href="javascript:void(0)" title="" onclick="switchTab(3)">求租</a>
					</li>
					<li class="personRefresh" id="personRefresh-guide">
						<span title="间隔时间可以修改哟">
							刷新时间间隔为
							<select id="rTimer" onchange="javascript:refreshChange()"> 
								<option value="-1">无刷新</option>
								<option value="5">5秒</option>
								<option value="15">15秒</option>
								<option value="30">30秒</option>
								<option value="60">1分钟</option>
								<option value="180">3分钟</option>
								<option value="300">5分钟</option>
								<option value="600">10分钟</option>
							</select>
							<span id="personHouse-refreshMinute">,距下次刷新<span id="autoMinute" class="refreshMinute-font">0</span>秒</span>
						</span>
					</li>
				</ul>
			</div>
			<div class="personHouse-main">
			<!-- 查询区 -->
			<ul class="personHouse-select clear">
				<li id="districtLi" class="person-select-areaList">
					<span>区域：</span><a id="nodistrictid"  class="col-f60 font-bold ps_district" href="javascript:void(0)" onclick="changeDistrict(this, '', '')">不限</a>
					
					<c:forEach var="district" items="${districts }">
						<a href="javascript:void(0)" onclick="changeDistrict(this, '${district.id}', '${district.district}')" 
						id="cs_district_${district.id}" >${district.district}</a>
					</c:forEach>
					<input id ="districtcheck" type="checkbox"  onclick="changeToDistricts(this)">多选</input>
				</li>                                                    
				<li id="districtsLi" style="display: none" class="person-select-areaList">
				<span>区域：</span><a id="nodistrictsid" class="col-f60 font-bold ps_district" href="javascript:void(0)" onclick="changeDistricts(this, '', '')">不限</a>
				
					<c:forEach var="district" items="${districts }">
                    	<a href="javascript:void(0)" onclick="changeDistricts(this, '${district.id}','${district.district}')">${district.district}</a>
                    </c:forEach>
                    <input id ="districtscheck" type="checkbox"  checked="checked" onclick="changeToDistrict(this)">多选</input>
                </li>
				<li id="areaLi" class="personHouse-select-area" style="display: none">
				</li>
				<li class="person-select-height"><span>类型：</span><a class="col-f60 font-bold ps_houseType" href="javascript:void(0)" onclick="changeHouseType(this, '')" id="cs_houseType_">不限</a>
					<a href="javascript:void(0)" onclick="changeHouseType(this, '住宅')" id="cs_houseType_住宅">住宅</a>
					<a href="javascript:void(0)" onclick="changeHouseType(this, '别墅')" id="cs_houseType_别墅">别墅</a>
					<a href="javascript:void(0)" onclick="changeHouseType(this, '店面')" id="cs_houseType_店面">店面</a>
					<a href="javascript:void(0)" onclick="changeHouseType(this, '写字楼')" id="cs_houseType_写字楼">写字楼</a>
					<a href="javascript:void(0)" onclick="changeHouseType(this, '厂房')" id="cs_houseType_厂房">厂房</a>
				</li>
				<li class="person-select-height">
					<span>居室：</span><a class="col-f60 font-bold ps_roomType" href="javascript:void(0)" onclick="changeRoomType(this, '', '')" id="cs_roomType_">不限</a>
					<a href="javascript:void(0)" onclick="changeRoomType(this, 1, '一居')" id="cs_roomType_1">一居</a>
					<a href="javascript:void(0)" onclick="changeRoomType(this, 2, '二居')" id="cs_roomType_2">二居</a>
					<a href="javascript:void(0)" onclick="changeRoomType(this, 3, '三居')" id="cs_roomType_3">三居</a>
					<a href="javascript:void(0)" onclick="changeRoomType(this, 4, '四居')" id="cs_roomType_4">四居</a>
					<a href="javascript:void(0)" onclick="changeRoomType(this, 5, '五居')" id="cs_roomType_5">五居</a>
				</li>
				<li  id="districtLi_net"  class="person-website-height">
					<span class="span_left float-l">网站：</span><span class="span_right"><a id="nodistrictid_net" class="col-f60 font-bold ps_website" href="javascript:void(0)" onclick="changeWebsite(this, '')" id="cs_website_">不限</a>
							<c:if test="${postType eq 0}">
								<a href="javascript:void(0)" onclick="changeWebsite(this, '58同城')" id="cs_website_58同城">58同城</a>
								<a href="javascript:void(0)" onclick="changeWebsite(this, '第一时间房产网')" id="cs_website_第一时间房产网">第一时间房产网</a>
								<a href="javascript:void(0)" onclick="changeWebsite(this, '赶集网')" id="cs_website_赶集网">赶集网</a>
								<a href="javascript:void(0)" onclick="changeWebsite(this, '今题网')" id="cs_website_今题网">今题网</a>
								<a href="javascript:void(0)" onclick="changeWebsite(this, '列表网')" id="cs_website_列表网">列表网</a>
							</c:if>
							<c:if test="${postType eq 1}">
								<a href="javascript:void(0)" onclick="changeWebsite(this, '58同城')" id="cs_website_58同城">58同城</a>
								<a href="javascript:void(0)" onclick="changeWebsite(this, '第一时间房产网')" id="cs_website_第一时间房产网">第一时间房产网</a>
								<a href="javascript:void(0)" onclick="changeWebsite(this, '赶集网')" id="cs_website_赶集网">赶集网</a>
								<a href="javascript:void(0)" onclick="changeWebsite(this, '今题网')" id="cs_website_今题网">今题网</a>
								<a href="javascript:void(0)" onclick="changeWebsite(this, '列表网')" id="cs_website_列表网">列表网</a>
							</c:if>
							<c:if test="${postType eq 2}">
								<a href="javascript:void(0)" onclick="changeWebsite(this, '58同城')" id="cs_website_58同城">58同城</a>
								<a href="javascript:void(0)" onclick="changeWebsite(this, '赶集网')" id="cs_website_赶集网">赶集网</a>
							</c:if>
							<c:if test="${postType eq 3}">
								<a href="javascript:void(0)" onclick="changeWebsite(this, '58同城')" id="cs_website_58同城">58同城</a>
								<a href="javascript:void(0)" onclick="changeWebsite(this, '赶集网')" id="cs_website_赶集网">赶集网</a>
							</c:if>
						<input id ="districtcheck_net" type="checkbox"  onclick="changeToDistricts_net(this)">多选</input>
					</span>
				</li>
				<li id=districtsLi_net class="person-website-height" style="display:none">
					<span class="span_left float-l">网站：</span><span class="span_right"><a id="nodistrictsid_net" class="col-f60 font-bold ps_website" href="javascript:void(0)" onclick="changeWebsite_net(this, '')" id="cs_website_">不限</a>
						
							<a href="javascript:void(0)" onclick="changeWebsite_net(this, '58同城')" id="cs_website_58同城">58同城</a>
						
							<a href="javascript:void(0)" onclick="changeWebsite_net(this, '第一时间房产网')" id="cs_website_第一时间房产网">第一时间房产网</a>
						
							<a href="javascript:void(0)" onclick="changeWebsite_net(this, '赶集网')" id="cs_website_赶集网">赶集网</a>
						
							<a href="javascript:void(0)" onclick="changeWebsite_net(this, '今题网')" id="cs_website_今题网">今题网</a>
						
						
						<input id ="districtscheck_net" type="checkbox"  checked="checked" onclick="changeToDistrict_net(this)">多选</input>
					</span>
				</li>

 				<li class="person-select-height house-area-box">
					<span>面积：</span><a class="col-f60 font-bold ps_houseArea" v='' href="javascript:" onclick="changeHouseArea(this, '')" >不限</a>
					<a href="javascript:" v='0,50'     >50㎡以下</a>
					<a href="javascript:" v='50,100'   >50-100㎡</a>
					<a href="javascript:" v='100,130'  >100-130㎡</a>
					<a href="javascript:" v='130,150'  >130-150㎡</a>
					<a href="javascript:" v='150,200'  >150-200㎡</a>
					<a href="javascript:" v='200,300'  >200-300㎡</a>
					<a href="javascript:" v='300,'     >300㎡以上</a>
					<input size='2' id='minHouseArea' class='houseAreaInp'></input> - 
					<input size='2' id='maxHouseArea' class='houseAreaInp'></input> ㎡
					<button id='searchHouseAreaBtn' style='background:#6BB05F;border:0;padding:2px 5px;color:#fff;'>确定</button>
				</li>
				
				<li class="person-select-height">
                    <span class="span_left float-l">采集时间：</span>
                    <span class="span_right">
                        <a class="col-f60 font-bold ps_time" href="javascript:void(0)" onclick="changeHouseTime(this, '','','')" id="cs_time">不限</a>
                        <a href="javascript:void(0)" onclick="changeHouseTime(this, '3','','3天以内')" id="cs_time_3">3天以内</a>
                        <a href="javascript:void(0)" onclick="changeHouseTime(this, '7','','7天以内')" id="cs_time_7">7天以内</a>
                        <a href="javascript:void(0)" onclick="changeHouseTime(this, '30','','一个月以内')" id="cs_time_30">一个月以内</a>
                        <a href="javascript:void(0)" onclick="changeHouseTime(this, '30','after','一个月以上')" id="cs_time_30_after">一个月以上</a>
                        <input id='searchStartDate' class='Wdate' style="cursor:pointer;width:100px" value=""  
                            onclick="WdatePicker({readOnly:true,minDate:'2016-01-01',maxDate:'%y-%M-%d'})"/>
                        - 
                        <input id='searchEndDate' class="Wdate input" style="cursor:pointer;width:100px" value="" 
                            onclick="WdatePicker({readOnly:true,minDate:'2016-01-01',maxDate:'%y-%M-%d'})"/>
                        <button id='searchTimeBtn' style='background:#6BB05F;border:0;padding:2px 5px;color:#fff;'>确定</button>
                    </span>
                </li>
				
				<li class="person-select-height" id="rentTypeSty">
                    <span>出租类型：</span>
                    <a  href="javascript:void(0)" class="col-f60 font-bold"  onclick="changeRentType(this, '整租','')" id="cs_rent_type">默认</a>
                    <a  href="javascript:void(0)"  onclick="changeRentType(this, '','全部')" id="cs_rent_type_all">全部 </a>
                    <a  href="javascript:void(0)"  onclick="changeRentType(this, '整租','整租')" id="cs_rent_type_entire">整租</a>
                    <a  href="javascript:void(0)"  onclick="changeRentType(this, '合租','合租')" id="cs_rent_type_share">合租</a>
                </li>
                <!-- <li class="person-select-height">
                    <span>过滤选项：</span>
                    <input type="checkbox" onclick="changePhoneFilterClick(this)"></input>显示号码房源
                    <input type="checkbox" onclick="changeAgentFilterClick(this)"></input>过滤中介房源
                    <input type="checkbox" onclick="changeRepeatTagClick(this)"></input>过滤重复房源
                </li>    
				<li class="person-select-height">
					<span>数据来源：</span>
					<a  href="javascript:void(0)" class="col-f60 font-bold"  onclick="changePostData(this, 'postData', '')" id="cs_post_data">热点房源 </a>
					<a  href="javascript:void(0)"  onclick="changePostData(this, 'postData', '更多房源')" id="cs_post_data_1">更多房源</a>
				</li> -->
				<li class="person-select-height clear">
					<p class="float-l"><span>价格：</span><input type="text" class="iw48 personHouse-text" id="price1" value=""></input>- <input type="text" class="iw48 personHouse-text" id="price2" value=""></input><span id="priceUnit">元</span></p>
					<p class="float-l"><span>&nbsp;</span>
					<!-- <select id="personHouse-order" class="personHouse-Website" onchange="javascript:personHouseOrder()">
						<option value="0">按发布时间排序</option>
						<option value="1">按刷新时间排序</option>
					</select></p> -->
					<div class="float-r" id="houseTable_search">
						<div class="float-l">
							<input type="text" id="place" value="" placeholder="可支持多个关键字搜索或使用常用的搜索条件" onkeypress="enterSumbit()" style="width:240px" class="personHouse-text" onclick="getCollectSearch()"></input>
							<div class="keyWordBox">
								<dl class="keyList"></dl>
							</div>
						</div>
						<input type="button" class="btn-search float-l mr8" onclick="ajaxSub()"></input>
						<span class="phSearch-btn float-l" onclick="getCollectSearchSum()">保存搜索条件 </span>
					</div>
				</li>
			</ul>
			<div class="phSearch-content" style="display:none">
				<span class="float-l">当前搜索条件:</span>
				<span class="selectSearch">
				</span>
			</div>
			<div id="fromList"></div>
		</div>
	</div>
</div>
</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/layer/layer.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.qrcode.min.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/map.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/placeholder.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.timers-1.2.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.zclip.min_3f343382891a2cb102fbb4b681b19729.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/personHouse_68d2032a0d35f090887fdcd66bcb3cbc.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/copyPersonHouse_5c1b29e0644e77cdc4ef6e5842137a21.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.paginate.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js?skin=green' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/intro_e523b61a7b0f0aa4d1bcc380363a6bba.js' type='text/javascript'></script> 
<script src='<%=path%>/Static/Js/introConfig_house_20ce449af20adac9f6d587ba156afe20.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/My97DatePicker/WdatePicker.js' type='text/javascript'></script>
<script>
$(function(){
	$.ajax({
	    type: 'post',
	    url: "/WebRelease/massPerson/ajaxList?postType=${postType}&cust=${cust}",
	    data: {pageNum:1,pageSize:15},
	    dataType: "text",
	    success: function(result) {
	        var resultHtml = result;
	        $("#fromList").html(resultHtml);
	    },
        error: function(jqXHR) {},
        complete: function(XMLHttpRequest, textStatus) {
            isCanSubmit = true;
        }
	})
});
</script>
</body>
</html>