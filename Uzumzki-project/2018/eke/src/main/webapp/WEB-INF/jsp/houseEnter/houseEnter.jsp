<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>出售房源录入</title>
<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/saleImport_2863c1add81bbc61b654f8408bbd6b46.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/selectPic_ec02fc16ad89e3d22179bdb53e8585a0.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/uploadVideo_b15384dd5709eab85e69b6f07aab493c.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/Validform.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/lightbox.min.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/introjs_03b43e32a5901af129087d7ae282fea4.css' rel='stylesheet' type='text/css'></link>
<link href="<%=path%>/Static/Css/uploadify.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/Static/Js/plugins/artDialog/skins/green.css?4.1.7" type="text/css" rel="stylesheet"/>
<style type="text/css">
.item {
	width: 116px;
	height: 116px;
	float: left;
	position: relative;
	margin: 20px;
}

.addImg {
	width: 116px;
	height: 116px;
	position: absolute;
	left: 0;
	top: 0;
	z-index: 2;
	cursor: pointer;
}

.preview,.preBlock {
	position: absolute;
	display: block;
	width: 116px;
	height: 116px;
	left: 0;
	top: 0;
}

.delete {
	width: 18px;
	position: absolute;
	right: 0px;
	cursor: pointer;
	display: none;
}
.setHeadImg{
	width: 18px;
	position: absolute;
	cursor: pointer;
	display: none;
}
.preBlock img {
	display: block;
	width: 116px;
	height: 116px;
}

.upload_input {
	display: block;
	width: 0;
	height: 0;
	-webkit-opacity: 0.0;
  /* Netscape and Older than Firefox 0.9 */
	-moz-opacity: 0.0;
  /* Safari 1.x (pre WebKit!) 老式khtml内核的Safari浏览器*/
	-khtml-opacity: 0.0;
  /* IE9 + etc...modern browsers */
	opacity: .0;
  /* IE 4-9 */
	filter: alpha(opacity=0);
  /*This works in IE 8 & 9 too*/
	-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
  /*IE4-IE9*/
	filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=0);
}
</style>
</head>
<body onload="changeLoadFlag(false)" onkeydown="return disableBackSpack(event)" id="importPage">
	<div class="" style="display: none; position: absolute;">
		<div class="aui_outer">
			<table class="aui_border">
				<tbody>
					<tr>
						<td class="aui_nw"></td>
						<td class="aui_n"></td>
						<td class="aui_ne"></td>
					</tr>
					<tr>
						<td class="aui_w"></td>
						<td class="aui_c">
							<div class="aui_inner">
								<table class="aui_dialog">
									<tbody>
										<tr>
											<td colspan="2" class="aui_header">
											<div class="aui_titleBar">
												<div class="aui_title" style="cursor: move;"></div>
												<a class="aui_close" href="javascript:/*artDialog*/;">×</a>
											</div></td>
										</tr>
										<tr>
											<td class="aui_icon" style="display: none;"><div class="aui_iconBg" style="background: none;"></div></td>
											<td class="aui_main" style="width: auto; height: auto;"><div class="aui_content" style="padding: 20px 25px;"></div></td>
										</tr>
										<tr>
											<td colspan="2" class="aui_footer"><div class="aui_buttons" style="display: none;"></div></td>
										</tr>
									</tbody>
								</table>
							</div>
						</td>
						<td class="aui_e"></td>
					</tr>
					<tr>
						<td class="aui_sw"></td>
						<td class="aui_s"></td>
						<td class="aui_se" style="cursor: se-resize;"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div id="saleHouse" class="saleHouse" style="min-height: 902px;">
        <input type="hidden" value="/WebRelease" id="basePath">
        <input type="hidden" value="//demo.com" id="staticPath">
        <!-- 出售房源管理Tab切换 -->
        <div class="saleHouse-main">
            <ul class="sale-tab-sub">
                <li id="saleHouse-basic-house" class="sale-sub-menu selected">
                    <a href="javascript:">住　宅</a>
                </li>
               <!--  <li id="saleHouse-basic-villa" class="sale-sub-menu">
                    <a href="javascript:">别　墅</a>
                </li>
                <li id="saleHouse-basic-shop" class="sale-sub-menu">
                    <a href="javascript:">商　铺</a>
                </li>
                <li id="saleHouse-basic-office" class="sale-sub-menu">
                    <a href="javascript:">写字楼</a>
                </li>
                <li id="saleHouse-basic-factory" class="sale-sub-menu">
                    <a href="javascript:">厂　房</a>
                </li> -->
            </ul>
            <ul class="import-keyway sale-tab-sub" style="display: none;">
                <li id="tip_basic" class="sale-sub-menu selected"><a class="tip_basic" href="javascript:importScroll('basic')">基本信息</a></li>
                <li id="tip_houseType" class="sale-sub-menu"><a class="tip_houseType" srcolltype="houseType" href="javascript:importScroll('houseType')">住宅信息</a></li>
                <li id="tip_house" class="sale-sub-menu"><a class="tip_house" href="javascript:importScroll('house')">房源介绍</a></li>
                <li id="tip_pic" class="sale-sub-menu"><a class="tip_pic" href="javascript:importScroll('pic')">图片信息</a></li>
            </ul>
            <div class="import-main">
            	
                <form id="bForm" action="/WebRelease/massProperty/saveHouseEnter" 
                onsubmit="return checkLoading()" method="post" onkeydown="return disableEnter(event)" enctype="multipart/form-data" >
                    <div>
                        <input type="hidden" id="houseId" name="houseId" value="${massProperty.id }">
                        <input type="hidden" id="city" name="city" value="755">
                        <input type="hidden" name="postType" id="postType" value="${postType }">
                        <input type="hidden" name="buildingType" id="buildingType" value="1">
                        <input type="hidden" name="isChange" id="isChange" value="0">
                        <input type="hidden" name="closeTip" id="closeTip" value="0">
                        <input type="hidden" name="currentTimeMillis" id="currentTimeMillis" value="">
                        <input type="hidden" name="draftType" id="draftType" value="">
                        <input type="hidden" name="copyPic" id="copyPic" value="0">
                        <input type="hidden" name="picChange" id="picChange" value="0">
                        <input type="hidden" name="editType" id="editType" value="A">
                        <input type="hidden" name="from" id="from" value="0">
                        <input type="hidden" id="mwebUrl" value="//demo.com/mweb">
                        <input type="hidden" id="hourTime" value="191715225">
                        <input type="hidden" name="autoSaveDraft" id="autoSaveDraft" value="0">
                        <input type="hidden" name="guideIndex" id="guideIndex" value="0">
                        <input type="hidden" name="sourceType" value="1">
                        <input type="hidden" name="groupName" value="">
                        <input type="hidden" name="soufunHouseLabel" id="soufunHouseLabel">
                        <input type="hidden" name="serviceLabel" id="serviceLabel">
                        <input type="hidden" name="ajkxbTextArea" id="ajkxbTextArea" value="0">
                        <input type="hidden" name="houseTag" id="houseTag" value="true">
                        <input type="hidden" name="webTag" id="webTag" value="true">
                        <input type="hidden" name="releaseOptionDesc" value="0" id="releaseOptionDesc">
                        <input type="hidden" name="optionValue" value="-1" id="optionValue">
                        <input type="hidden" name="companyId" value="755012" id="companyId">
                        <input type="hidden" name="changeImgSize" value="0">
                        <input type="hidden" name="indoorUrls" id="indoorUrls" value="">
                        <input type="hidden" name="houseTypeUrls" id="houseTypeUrls" value="">
                        <input type="hidden" name="estateUrls" id="estateUrls" value="">
                        <input type="hidden" name="estateid" id="estateid" value="${massProperty.estateid }">
                        <input type="hidden" name="districtname" id="districtname" value="${massProperty.districtname }">
                        <input type="hidden" name="areaname" id="areaname" value="${massProperty.areaname }">
                        <input type="hidden" name="area" id="area" value="${massProperty.areaid }">
                    </div>

                    <!-- 基本信息内容区 -->
                    <div class="saleHouse-basic-content mt8">

                        <ul class="sale-content-tip">
                            <li>
                                <span class="sale-tip-img"></span>
                                <span>贴心提示:</span>
                                <span class="sale-tip-colse"></span>
                            </li>
                            <li style="height:24px;line-height:24px">
                                <span>1.随时抓取经纪人在外网发布的任意一套房源信息，方便、快捷、高效。</span>
                            </li>
                            <li style="height:24px;line-height:24px">
                                <span>2.针对房源图片可以完美去除网站LOGO水印。</span><!-- 
                                <a class="btn-green" href="javascript:copyHouseArt(1)" id="copyHouse-guide" style="display:inline-block;text-align:center">秒录房源</a> -->
                            </li>
                        </ul>

                        <div class="sale-nav">
                            <span class="saleHouse-basic-header" id="basic">基本信息</span>
                            <input class="btn-red-return" type="button" value="返回房源管理" onclick="javascript:redirectManager()">
                        </div>
                        <div class="saleHouse-basic clear">
                            <ul>
                                <li>
                                    <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>小区:</span>
                                    <span>
								<input id="estate" name="estate" type="text" class="iw200" 
								value="${massProperty.estate }" datatype="cell">
								<span style="margin-left: 10px;" id="showPrice"></span>
                                    <span class="Validform_checktip"></span></span>
                                    <div class="keyWordBox">
                                        <dl class="keyList"></dl>
                                    </div>
                                    <input type="hidden" name="cellCode" id="cellCode" value="755">
                                </li>
                                <li class="price_span" style="height:auto;display:none;line-height:24px;"></li>
                                <li class="cellmapping_li" style="height:auto;display:none;line-height:24px;">
                                    <div class="cellmapping_tab">
                                        <span id="cellmappingSpan"></span>
                                        <a class="cellmapping_li_0 cellmapping_link_tab" href="javascript:changeCellmapping(1)" style="display:none">外网小区匹配</a>
                                        <a class="cellmapping_li_1 cellmapping_link_tab" href="javascript:changeCellmapping(0)">收起小区匹配</a>
                                    </div>
                                    <div id="cellmapping_list" style="width:750px;margin-bottom:6px">
                                    
                                    </div>
                                </li>
                                <li class="clear">
									<span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>区域:</span>
                                    <span>
										<select id="districtSelect" name="districtid" class="iw92" datatype="*">
											<c:forEach items="${districts }" var="list">
												<option value="${list.id}" 
												<c:if test="${massProperty.districtid == list.id}">selected="selected"</c:if>>${list.district}</option>
											</c:forEach>
										</select>
									</span>
                                    <span class="col-fd6e05 mr5">*</span>路段:<span>
										<select id="areaSelect" name="areaid" class="iw92" datatype="*" >
											<option value="">请选择</option>
										</select>
									</span>
                                </li>
                                <li>
                                    <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>地址:</span><span>
									<input id="address" name="address" type="text" class="iw400" datatype="addr" 
									value="${massProperty.address }">
									<span class="Validform_checktip"></span></span>
                                    <div class="import-titleTip">
                                        <!-- 标题信息提示 -->
                                        <div id="addrTipDiv" class="hdtop" style="display:none;left: 108px;top:3px;">
                                            <div class="vldtop"></div>
                                            <div class="titleTip-close">
                                                <!-- <a href="javascript:titleWindowClose('addrTipDiv')">
                                                    <span class="tip-img"></span>
                                                </a> -->
                                            </div>
                                            <ul>
                                            </ul>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>建筑面积:</span>
                                    <span><input id="squarej" name="squarej" type="text" class="iw92" 
                                    value="${massProperty.squarej }"
										onkeypress="return checkFloat(event, this);" datatype="houseArea">平方米<span class="Validform_checktip"></span></span>
								</li>
                                <li>
                                    <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>使用面积:</span>
                                    <span><input id="squares" name="squares" type="text" class="iw92" 
                                    value="${massProperty.squares }" 
                                    onkeypress="return checkFloat(event, this);" datatype="areaUsed">平方米<span class="Validform_checktip"></span></span>
                                </li>
                                <li>
                                    <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>户型:</span>
                                    <span>
									<select class="iw48" name="countf" id="countf" datatype="room"><option value="1" selected="selected">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
									</select>室 
									<select class="iw48" name="countt" id="countt"><option value="0" selected="selected">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option></select>厅
									<select class="iw48" name="countw" id="countw"><option value="0" selected="selected">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option></select>厨
									<select class="iw48" name="countc" id="countc"><option value="0" selected="selected">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option></select>卫
									<select class="iw48" name="county" id="county"><option value="0" selected="selected">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option></select>阳台
									<span class="Validform_checktip"></span>
									</span>
                                </li>
                                <li class="anjuke-hsh" style="">
                                    <span class="saleHouse-basic-left">户室号:</span>
                                    <span>
										<input name="buildingvalue" type="text" class="iw48"  datatype="buildingValueAjk"
										value="${massProperty.buildingvalue }"> 
											<select class="iw48" name="buildingtype" id="buildingtype">
												<option value="1" selected="selected">栋</option>
												<option value="2">弄</option>
												<option value="3">座</option>
												<option value="4">号</option>
												<option value="5">号楼</option>
												<option value="6">胡同</option>
											</select> 
											<input name="unitvalue" type="text" class="iw48" datatype="unitAjk"
											value="${massProperty.unitvalue }"> 
											<select class="iw48" name="unittype" id="unittype">
												<option value="5">单元</option>
												<option value="1">栋</option>
												<option value="2">幢</option>
												<option value="3">号</option>
												<option value="4" selected="selected">号楼</option>
											</select> 
											<input name="roomno" type="text" class="iw48" datatype="houseNumberAjk"
											value="${massProperty.roomno }">室
											 <span class="col-999 ml8">未填写会随机生成</span> <span class="Validform_checktip"></span>
									</span>
                                </li>
                                <li class="clear">
                                    <input type="hidden" id="yuegong" name="yuegong" value="0">
                                    <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>价格:</span>
                                    <span><input id="price" name="price" type="text" class="iw92" onkeypress="return checkFloat(event, this);" datatype="price"
                                    value="${massProperty.price }">
									<span>
									<span id="priceTypeSpan" >${postType == 0 ? "万元" : "元/月"}</span>
                                    </span>
									<c:if test="${postType == 0 }">
	                                    <a href="javascript:changemoreprice(0)" id="moreprice_0">定制价格</a>
	                                    <a href="javascript:changemoreprice(1)" id="moreprice_1" style="display:none">收起</a>
									</c:if>
                                    <span class="Validform_checktip"></span></span>
                                </li>
                                <c:if test="${postType == 1}">
	                                <li id="joinType" class="clear">
										<span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>租赁方式:</span>
										<span>
											<input name="rentType" value="0" type="radio" id="rentType1" 
											<c:if test="${empty massProperty.flagsublet or massProperty.flagsublet == '0'}">checked="checked"</c:if>><label for="rentType1">整租</label>
											<input name="rentType" value="1" type="radio" id="rentType2"
											<c:if test="${massProperty.flagsublet == '1' }">checked="checked"</c:if>><label for="rentType2">合租</label>
										</span>
									</li>
									<li id="joinRent" class="clear" style="display: none;">
										<span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>合租方式:</span>
										<span>
											<select id="subletnumber" name="subletnumber" class="iw48">
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
												<option value="6">6</option>
												<option value="7">7</option>
												<option value="8">8</option>
												<option value="9">9</option>
											</select>户合租
										</span>
										<span>
											<select id="subletroom" name="subletroom" style="width: 80px;">
												<option value="主卧">主卧</option>
												<option value="次卧">次卧</option>
												<option value="床位">床位</option>
												<option value="隔断间">隔断间</option>
											</select>
										</span>
										<span>
											<select id="subletcondition" name="subletcondition" style="width: 80px;">
												<option value="性别不限">性别不限</option>
												<option value="限女生">限女生</option>
												<option value="限男生">限男生</option>
												<option value="限夫妻">限夫妻</option>
											</select>
										</span>
									</li>
									<li class="clear">
										<span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>支付方式:</span>
										<span>
											<select id="subletpaytype" name="subletpaytype" class="iw92">
												<option value="押一付一">押一付一</option>
												<option value="押一付二">押一付二</option>
												<option value="押一付三" selected="selected">押一付三</option>
												<option value="押二付一">押二付一</option>
												<option value="押二付二">押二付二</option>
												<option value="押二付三">押二付三</option>
												<option value="押三付一">押三付一</option>
												<option value="押三付三">押三付三</option>
												<option value="半年付">半年付</option>
												<option value="年付">年付</option>
												<option value="面议">面议</option>
											</select>
										</span>
									</li>
								</c:if>
                                <li class="avg_Price" style="height:auto;display:none;line-height:24px;"><span id="avgPriceTip" class="col-f60 avgpriceSpan"></span></li>

                                <li class="moreprice" style="display:none">
                                    <span class="saleHouse-basic-left">安居客新版价格:</span>
                                    <span>
									<input type="hidden" name="webId" value="47">
									<input name="webPrice" type="text" class="iw92" onkeypress="return checkFloat(event, this);" id="webprice_47" datatype="webprice">
								<span class="Validform_checktip"></span></span>
                                    <span id="webpriceTip_47" class="col-f60"></span>
                                </li>

                                <li class="moreprice" style="display:none">
                                    <span class="saleHouse-basic-left">58同城价格:</span>
                                    <span>
									<input type="hidden" name="webId" value="6">
									<input name="webPrice" type="text" class="iw92" onkeypress="return checkFloat(event, this);" id="webprice_6" datatype="webprice">
								<span class="Validform_checktip"></span></span>
                                    <span id="webpriceTip_6" class="col-f60"></span>
                                </li>

                                <li class="moreprice" style="display:none">
                                    <span class="saleHouse-basic-left">新浪网价格:</span>
                                    <span>
									<input type="hidden" name="webId" value="24">
									<input name="webPrice" type="text" class="iw92" onkeypress="return checkFloat(event, this);" id="webprice_24" datatype="webprice">
								<span class="Validform_checktip"></span></span>
                                    <span id="webpriceTip_24" class="col-f60"></span>
                                </li>

                                <li class="moreprice" style="display:none">
                                    <span class="saleHouse-basic-left">无线搜房帮价格:</span>
                                    <span>
									<input type="hidden" name="webId" value="55">
									<input name="webPrice" type="text" class="iw92" onkeypress="return checkFloat(event, this);" id="webprice_55" datatype="webprice">
								<span class="Validform_checktip"></span></span>
                                    <span id="webpriceTip_55" class="col-f60"></span>
                                </li>



                                <li id="lowPayLi" style="display: none">
                                    <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>最低首付:</span>
                                    <span><input id="lowPay" name="lowPay" type="text" class="iw92" value="0" onkeypress="return checkNumber(event);">
										<span class="col-f60">注：最低首付最少为价格的30%</span>
                                    </span>
                                </li>
                                <li class="anjuke-hsh">
                                    <span class="saleHouse-basic-left">佣金比例:</span>
                                    <span><span class="import-bd">≤</span>
                                    <input name="feecust" type="text" class="iw92" value="2" datatype="commissionAutoAjk"><span class="import-bd">%</span>
                                    <span class="col-999 ml8">请尽量填写佣金最高比例,未填写会默认2% </span>
                                    <span class="Validform_checktip"></span></span>
                                </li>

                                <li class="sale-year clear">
                                    <span class="saleHouse-basic-left float-l"><span class="col-fd6e05 mr5">*</span>建造年代:</span>
                                    <span class="float-l ml5 mt5">
                                    <input id="saleHouse-basic-year" type="text" name="ddlusedyear" class="iw48 float-l" onkeypress="return checkNumber(event);" datatype="saleHouse-basic-year"
                                     value="${massProperty.ddlusedyear }">
                                    <img src="<%=path%>/Static/Picture/upd.png" border="0" usemap="#Map7" class="sale-yeae-img float-l">
                                    <map name="Map7" id="Map7">
								    <area shape="rect" coords="1,1,14,13" href="javascript:addone('saleHouse-basic-year');"><area shape="rect" coords="1,13,19,23" href="javascript:minusone('saleHouse-basic-year');">
									</map>
									年<span class="Validform_checktip"></span></span>
                                </li>
                                <li class="clear sale-floor">
                                    <span class="saleHouse-basic-left float-l"><span class="col-fd6e05 mr5">*</span>所在楼层:</span>
                                    <span class="float-l ml5 mt5">
									<input id="saleHouse-basic-flooron" type="text" name="floor" class="iw48 float-l" onkeypress="return checkNumber(event);" datatype="floor"
									 value="${massProperty.floor}">
									<img src="<%=path%>/Static/Picture/upd.png" border="0" usemap="#Map8" class="sale-yeae-img float-l">
									<map name="Map8" id="Map8"><area shape="rect" coords="1,1,13,13" href="javascript:addone('saleHouse-basic-flooron','saleHouse-basic-floorall',true);"><area shape="rect" coords="1,13,13,24" href="javascript:minusone('saleHouse-basic-flooron','saleHouse-basic-floorall',true);"></map>
									<span class="float-l">楼,共</span>
                                    <input id="saleHouse-basic-floorall" type="text" name="floorall" class="iw48 float-l" onkeypress="return checkNumber(event);" datatype="floor"
                                    value="${massProperty.floorall }">
                                    <img src="<%=path%>/Static/Picture/upd.png" border="0" usemap="#Map9" class="sale-yeae-img float-l">
                                    <map name="Map9" id="Map9"><area shape="rect" coords="0,2,13,13" href="javascript:addone('saleHouse-basic-floorall','saleHouse-basic-flooron',false);"><area shape="rect" coords="1,13,14,24" href="javascript:minusone('saleHouse-basic-floorall','saleHouse-basic-flooron',false);"></map>
                                    <span class="float-l">楼</span><span class="Validform_checktip"></span></span>
                                </li>
                                <li class="clear">
                                    <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>朝向:</span>
                                    <span id="direction">
										<input name="direction" value="东" type="radio" id="pointTo1"><label for="pointTo1">东</label>
										<input name="direction" value="南" type="radio" id="pointTo2"><label for="pointTo2">南</label>
										<input name="direction" value="西" type="radio" id="pointTo3"><label for="pointTo3">西</label>
										<input name="direction" value="北" type="radio" id="pointTo4"><label for="pointTo4">北</label>
										<input name="direction" value="东南" type="radio" id="pointTo5"><label for="pointTo5">东南</label>
										<input name="direction" value="东西" type="radio" id="pointTo6"><label for="pointTo6">东西</label>
										<input name="direction" value="西南" type="radio" id="pointTo7"><label for="pointTo7">西南</label>
										<input name="direction" value="西北" type="radio" id="pointTo8"><label for="pointTo8">西北</label>
										<input name="direction" value="东北" type="radio" id="pointTo9"><label for="pointTo9">东北</label>
										<input name="direction" value="南北" type="radio" id="pointTo10" checked="checked"><label for="pointTo10">南北</label>
									</span>
                                </li>
                                <li class="clear">
                                    <span class="saleHouse-basic-left">看房时间:</span>
                                    <span id="look">
										<input name="look" value="随时看房" type="radio" id="lookTime1"><label for="lookTime1">随时看房</label>
										<input name="look" value="非工作时间" type="radio" id="lookTime2"><label for="lookTime2">非工作时间</label>
										<input name="look" value="电话预约" type="radio" id="lookTime3" checked="checked"><label for="lookTime3">电话预约</label>
										<input name="look" value="其它" type="radio" id="lookTime4"><label for="lookTime4">其它</label>
									</span>
                                </li>
                                <li>
                                    <span class="saleHouse-basic-left">装修程度:</span>
                                    <span id="decoration">
										<input name="decoration" value="豪华装修" type="radio" id="housezx1"><label for="housezx1">豪华装修</label>
										<input name="decoration" value="精装修" type="radio" id="housezx2"><label for="housezx2">精装修</label>
										<input name="decoration" value="中等装修" type="radio" id="housezx3" checked="checked"><label for="housezx3">中等装修</label>
										<input name="decoration" value="简装修" type="radio" id="housezx4"><label for="housezx4">简装修</label>
										<input name="decoration" value="毛坯" type="radio" id="housezx5"><label for="housezx5">毛坯</label>
									</span>
                                </li>
                                <li>
                                    <span class="saleHouse-basic-left">内部编号:</span>
                                    <span><input id="internalNum" name="internalnum" type="text" class="iw92" datatype="internalNum" 
                                    value="${massProperty.internalnum }">
                                    <span class="Validform_checktip"></span></span>
                                </li>
                                <li id="fileNoLi">
                                    <span class="saleHouse-basic-left">备案编号:</span>
                                    <span><input id="fileNo" name="fileno" type="text" class="iw92" datatype="fileNo" 
                                    value="${massProperty.fileno }">
                                    <span class="Validform_checktip"></span></span>
                                </li>
                                <li id="commonTag" class="clear common-tag">
                                    <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>房源标签:</span>
                                    <span><input id="saleHouse-house-tip" type="text" name="houselabel" class="iw400 saleHouse-house-tip" datatype="saleHouse-house-tip" 
                                    value="${massProperty.houselabel }"><span class="Validform_checktip"></span></span>
                                </li>
                                <li class="clear" id="wubaBasic-guide">
                                    <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>配套设施:</span>
                                    <span><input id="saleHouse-eare-tip" type="text" name="celllabel" class="iw400 saleHouse-eare-tip" datatype="saleHouse-eare-tip" 
                                    value="${massProperty.celllabel }"><span class="Validform_checktip"></span></span>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <!-- 独有信息内容区 -->
                    <div class="saleHouse-special-content mt8">
                        <!-- 住宅 -->
                        <div class="saleHouse-basic-house">
                            <div class="sale-nav">
                                <span class="saleHouse-basic-header" id="houseType">住宅信息</span>
                            </div>
                            <div class="saleHouse-basic clear">
                                <ul>
                                    <li>
                                        <span class="saleHouse-basic-left">房屋类别:</span>
                                        <span id="housetype">
											<input name="housetype" type="radio" value="公寓" id="houseType1"><label for="houseType1">公寓</label>
											<input name="housetype" type="radio" value="普通住宅" id="houseType2" checked="checked"><label for="houseType2">普通住宅</label>
											<input name="housetype" type="radio" value="平房" id="houseType3"><label for="houseType3">平房</label>
											<input name="housetype" type="radio" value="其它" id="houseType4"><label for="houseType4">其它</label>
										</span>
                                    </li>
                                    <c:if test="${postType == 0 }">
	                                    <li>
	                                        <span class="saleHouse-basic-left">住宅子类型:</span>
	                                        <span><select class="iw92" name="housesubtype" id="housesubtype"><option value="多层[1-7层]">多层[1-7层]</option><option value="多层（带电梯）">多层（带电梯）</option><option value="小高层[8-20层]">小高层[8-20层]</option><option value="高层[20层以上]">高层[20层以上]</option></select></span>
	                                    </li>
	                                    <li>
	                                        <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>房屋产权:</span>
	                                        <span><select class="iw92" name="houseproperty" id="houseproperty"><option value="商品房" selected="selected">商品房</option><option value="商住两用">商住两用</option><option value="经济适用房">经济适用房</option><option value="使用权">使用权</option><option value="公房">公房</option><option value="其它">其它</option></select></span>
	                                    </li>
	                                    <li>
	                                        <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>产权年限:</span>
	                                        <span id="landyear">
												<input name="landyear" type="radio" value="70年产权" id="landYear1" checked="checked"><label for="landYear1">70年产权</label>
												<input name="landyear" type="radio" value="50年产权" id="landYear2"><label for="landYear2">50年产权</label>
												<input name="landyear" type="radio" value="40年产权" id="landYear3"><label for="landYear3">40年产权</label>
											</span>
	                                    </li>
	                                    <li class="clear">
	                                        <span class="saleHouse-basic-left float-l mr5">房屋结构:</span>
	                                        <span id="housestructure">
												<input name="housestructure" value="板楼" type="radio" id="houseStructure1" checked="checked"><label for="houseStructure1">板楼</label>
												<input name="housestructure" value="塔楼" type="radio" id="houseStructure2"><label for="houseStructure2">塔楼</label>
												<input name="housestructure" value="板塔结合" type="radio" id="houseStructure3"><label for="houseStructure3">板塔结合</label>
												<input name="housestructure" value="砖混" type="radio" id="houseStructure4"><label for="houseStructure4">砖混</label>
												<input name="housestructure" value="其它" type="radio" id="houseStructure5"><label for="houseStructure5">其它</label>
											</span>
	                                    </li>
	                                    <li>
	                                        <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>房屋年限:</span>
	                                        <span id="houseyear">
												<input name="houseyear" type="radio" value="1" checked="checked"><label>满五年</label>
												<input name="houseyear" type="radio" value="0"><label>满二年</label>
												<input name="houseyear" type="radio" value="2"><label>不满二年</label>
												<span class="col-999">用于计算增值税</span>
	                                        </span>
	                                    </li>
	                                    <li>
	                                        <span class="saleHouse-basic-left">唯一住房:</span>
	                                        <span>
												<input name="onlyhouse" type="radio" value="1" id="onlyHouse1"
												<c:if test="${massProperty.massHouse.onlyhouse == '1' or empty massProperty.massHouse.onlyhouse}">
												 checked="checked"
												</c:if>><label for="onlyHouse1">是</label>
												<input name="onlyhouse" type="radio" value="0" id="onlyHouse2"
												<c:if test="${massProperty.massHouse.onlyhouse == '0'}">
												 checked="checked"
												</c:if>><label for="onlyHouse2">否</label>
												<span class="col-999">是否为业主唯一住房，用于计算个税</span>
	                                        </span>
	                                    </li>
                                    </c:if>
                                    <li>
                                        <span class="saleHouse-basic-left">基础设施:</span>
                                        <span id="basicequipList">
											<input name="basicequipList" value="水" type="checkbox" id="basicEquip1" checked="checked"><label for="basicEquip1">水</label>
											<input name="basicequipList" value="电" type="checkbox" id="basicEquip2" checked="checked"><label for="basicEquip2">电</label>
											<input name="basicequipList" value="煤气/天然气" type="checkbox" id="basicEquip3" checked="checked"><label for="basicEquip3">煤气/天然气</label>
											<input name="basicequipList" value="有线电视" type="checkbox" id="basicEquip9" checked="checked"><label for="basicEquip9">有线电视</label>
											<input name="basicequipList" value="暖气" type="checkbox" id="basicEquip4"><label for="basicEquip4">暖气</label>
											<input name="basicequipList" value="电梯" type="checkbox" id="basicEquip5"><label for="basicEquip5">电梯</label>
											<input name="basicequipList" value="车位" type="checkbox" id="basicEquip6"><label for="basicEquip6">车位</label>
											<input name="basicequipList" value="露台" type="checkbox" id="basicEquip7"><label for="basicEquip7">露台</label>
											<input name="basicequipList" value="阁楼" type="checkbox" id="basicEquip8"><label for="basicEquip8">阁楼</label>
											<input name="basicequipList" value="储藏室/地下室" type="checkbox" id="basicEquip10"><label for="basicEquip10">储藏室/地下室</label>
											<input onclick="selectAll('basicequipList')" type="button" value="全选 ">
											<input onclick="clearAll('basicequipList')" type="button" value="清空">
										</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left">配套设施:</span>
                                        <span id="advequipList">
											<input name="advequipList" value="电话" type="checkbox" id="advEquip1" checked="checked"><label for="advEquip1">电话</label>
											<input name="advequipList" value="热水器" type="checkbox" id="advEquip2" checked="checked"><label for="advEquip2">热水器</label>
											<input name="advequipList" value="彩电" type="checkbox" id="advEquip3"><label for="advEquip3">彩电</label>
											<input name="advequipList" value="空调" type="checkbox" id="advEquip0" checked="checked"><label for="advEquip0">空调</label>
											<input name="advequipList" value="冰箱" type="checkbox" id="advEquip4"><label for="advEquip4">冰箱</label>
											<input name="advequipList" value="洗衣机" type="checkbox" id="advEquip5"><label for="advEquip5">洗衣机</label>
											<input name="advequipList" value="家具" type="checkbox" id="advEquip6" checked="checked"><label for="advEquip6">家具</label>
											<input name="advequipList" value="床" type="checkbox" id="advEquip8" checked="checked"><label for="advEquip8">床</label>
											<input name="advequipList" value="宽带网" type="checkbox" id="advEquip7"><label for="advEquip7">宽带网</label>
											<input name="advequipList" value="微波炉" type="checkbox" id="advEquip9"><label for="advEquip9">微波炉</label>
											<input name="advequipList" value="衣柜" type="checkbox" id="advEquip10"><label for="advEquip10">衣柜</label>
										</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left"></span>
                                        <span>
											<input name="advequipList" value="沙发" type="checkbox" id="advEquip11"><label for="advEquip11">沙发</label>
											<input name="advequipList" value="厨具" type="checkbox" id="advEquip12"><label for="advEquip12">厨具(可做饭)</label>
											<input name="advequipList" value="独立卫生间" type="checkbox" id="advEquip13"><label for="advEquip13">独立卫生间</label>
											<input onclick="selectAll('advequipList')" type="button" value="全选 ">
											<input onclick="clearAll('advequipList')" type="button" value="清空">
										</span>
                                    </li>


                                </ul>
                            </div>
                        </div>
                        <!-- 标签提示 -->
                        <div class="tip-window cellLabel-window" style="display:none"></div>
                        <!-- 标签提示 -->
                        <div class="tip-window houseLabel-window" style="display:none"></div>
                        <!-- 别墅 -->
                        <!-- <div class="saleHouse-basic-villa" style="display:none">
                            <div class="sale-nav" id="villaType">
                                <span class="saleHouse-basic-header">别墅信息</span>
                            </div>
                            <div class="saleHouse-basic clear">
                                <ul>
                                    <li>
                                        <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>别墅形式:</span>
                                        <span>
									<input name="villaType" value="独栋" type="radio" checked="checked" id="villaxs1"><label for="villaxs1">独栋</label>
									<input name="villaType" value="双拼" type="radio" id="villaxs2"><label for="villaxs2">双拼</label>
									<input name="villaType" value="联排" type="radio" id="villaxs3"><label for="villaxs3">联排</label>
									<input name="villaType" value="叠加" type="radio" id="villaxs4"><label for="villaxs4">叠加</label>
									<input name="villaType" value="其它" type="radio" id="villaxs5"><label for="villaxs5">其它</label>
								</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left">厅结构:</span>
                                        <span>
									<input name="hallType" value="平层" type="radio" checked="checked" id="hallType1"><label for="hallType1">平层</label>
									<input name="hallType" value="挑高" type="radio" id="hallType2"><label for="hallType2">挑高</label>
									<input name="hallType" value="其它" type="radio" id="hallType3"><label for="hallType3">其它</label>
								</span>
                                    </li>

                                    <li>
                                        <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>产权年限:</span>
                                        <span>
										<input name="landYear1" type="radio" value="70年产权" id="landYear21" checked="checked"><label for="landYear21">70年产权</label>
										<input name="landYear1" type="radio" value="50年产权" id="landYear22"><label for="landYear22">50年产权</label>
										<input name="landYear1" type="radio" value="40年产权" id="landYear23"><label for="landYear23">40年产权</label>
									</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>房屋年限:</span>
                                        <span>
										<label><input name="period1" type="radio" value="1" checked="checked">满五年</label>
										<label><input name="period1" type="radio" value="0">满二年</label>
										<label><input name="period1" type="radio" value="2">不满二年</label>
										<span class="col-999">用于计算增值税</span>
                                        </span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left">唯一住房:</span>
                                        <span>
										<input name="onlyHouse1" type="radio" value="1" id="onlyHouse21" checked="checked"><label for="onlyHouse21">是</label>
										<input name="onlyHouse1" type="radio" value="0" id="onlyHouse22"><label for="onlyHouse22">否</label>
										<span class="col-999">是否为业主唯一住房，用于计算个税</span>
                                        </span>
                                    </li>

                                    <li>
                                        <span class="saleHouse-basic-left">地下室:</span>
                                        <span>
									<input name="basement" type="radio" value="1" id="villadxs1"><label for="villadxs1">有</label>
									<input name="basement" type="radio" value="0" id="villadxs2" checked="checked"><label for="villadxs2">无</label>
								</span>
                                        <span class="js-villa-new-attr display-none" style="margin-left: 20px;">
									<span class="col-fd6e05 mr5">*</span>面积：
                                        <input name="basementArea" id="basementArea" class="iw120" placeholder="请输入地下室面积" datatype="basementArea"> 平方米
                                        <span class="Validform_checktip"></span></span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left">花园:</span>
                                        <span>
									<input name="garden" type="radio" value="1" id="villahy1"><label for="villahy1">有</label>
									<input name="garden" type="radio" value="0" id="villahy2" checked="checked"><label for="villahy2">无</label>
								</span>
                                        <span class="js-villa-new-attr display-none" style="margin-left: 20px;">
									<span class="col-fd6e05 mr5">*</span>面积：
                                        <input name="gardenArea" id="gardenArea" class="iw120" placeholder="请输入花园面积" datatype="gardenArea"> 平方米
                                        <span class="Validform_checktip"></span></span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left">车库:</span>
                                        <span>
									<input name="garage" type="radio" value="1" id="villack1"><label for="villack1">有</label>
									<input name="garage" type="radio" value="0" id="villack2" checked="checked"><label for="villack2">无</label>
								</span>
                                        <span class="js-villa-new-attr display-none" data-val="1" style="margin-left: 20px;">
									<span class="col-fd6e05 mr5">*</span>个数：
                                        <input name="garageNum" id="garageNum" class="iw120" placeholder="请输入车库个数" value="1" datatype="garageNum"> 个
                                        <span class="Validform_checktip"></span></span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left">停车位:</span>
                                        <span>
									<input name="parkLot" type="radio" value="1" id="villatcw1"><label for="villatcw1">有</label>
									<input name="parkLot" type="radio" value="0" id="villatcw2" checked="checked"><label for="villatcw2">无</label>
								</span>
                                        <span class="js-villa-new-attr display-none" data-val="1" style="margin-left: 20px;">
									<span class="col-fd6e05 mr5">*</span>个数：
                                        <input name="parkLotNum" id="parkLotNum" class="iw120" placeholder="请输入车位个数" value="1" datatype="parkLotNum"> 个
                                        <span class="Validform_checktip"></span></span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left">配套设施:</span>
                                        <span>
									<input name="basicEquip1" value="露台" type="checkbox" id="villasb1"><label for="villasb1">露台</label>
									<input name="basicEquip1" value="楼阁" type="checkbox" id="villasb2"><label for="villasb2">楼阁</label>
									<input name="basicEquip1" value="游泳池" type="checkbox" id="villasb3"><label for="villasb3">游泳池</label>
									<input name="basicEquip1" value="阳光房" type="checkbox" id="villasb4"><label for="villasb4">阳光房</label>
									<input onclick="selectAll('basicEquip1')" type="button" value="全选 ">
									<input onclick="clearAll('basicEquip1')" type="button" value="清空">
								</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left">室内设施:</span>
                                        <span>
									<input name="advEquip1" value="电话" type="checkbox" id="advEquip11" checked="checked"><label for="advEquip11">电话</label>
									<input name="advEquip1" value="彩电" type="checkbox" id="advEquip12" checked="checked"><label for="advEquip12">彩电</label>
									<input name="advEquip1" value="热水器" type="checkbox" id="advEquip13" checked="checked"><label for="advEquip13">热水器</label>
									<input name="advEquip1" value="洗衣机" type="checkbox" id="advEquip14"><label for="advEquip14">洗衣机</label>
									<input name="advEquip1" value="冰箱" type="checkbox" id="advEquip15"><label for="advEquip15">冰箱</label>
									<input name="advEquip1" value="空调" type="checkbox" id="advEquip16" checked="checked"><label for="advEquip16">空调</label>
									<input name="advEquip1" value="家具" type="checkbox" id="advEquip17" checked="checked"><label for="advEquip17">家具</label>
									<input name="advEquip1" value="床" type="checkbox" id="advEquip24" checked="checked"><label for="advEquip24">床</label>
									<input name="advEquip1" value="宽带网" type="checkbox" id="advEquip18"><label for="advEquip18">宽带网</label>
									<input name="advEquip1" value="微波炉" type="checkbox" id="advEquip19"><label for="advEquip19">微波炉</label>
									<input name="advEquip1" value="衣柜" type="checkbox" id="advEquip20"><label for="advEquip20">衣柜</label>
								</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left"></span>
                                        <span>
									<input name="advEquip1" value="沙发" type="checkbox" id="advEquip21"><label for="advEquip21">沙发</label>
									<input name="advEquip1" value="厨具" type="checkbox" id="advEquip22"><label for="advEquip22">厨具(可做饭)</label>
									<input name="advEquip1" value="独立卫生间" type="checkbox" id="advEquip23"><label for="advEquip23">独立卫生间</label>
									<input onclick="selectAll('advEquip1')" type="button" value="全选 ">
									<input onclick="clearAll('advEquip1')" type="button" value="清空">
								</span>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        商铺
                        <div class="saleHouse-basic-shop" style="display:none">
                            <div class="sale-nav" id="shopType">
                                <span class="saleHouse-basic-header">商铺信息</span>
                            </div>
                            <div class="saleHouse-basic clear">
                                <ul>
                                    <li>
                                        <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>商铺类型:</span>
                                        <span>
									<select name="shopType" class="iw92">
										<option value="住宅底商">住宅底商</option>
										<option value="商业街商铺" selected="selected">商业街商铺</option>
										<option value="旅游商铺">旅游商铺</option>
										<option value="写字楼配套底商">写字楼配套底商</option>
										<option value="主题卖场">主题卖场</option>
										<option value="酒店底商">酒店底商</option>
										<option value="百货/购物中心">百货/购物中心</option>
										<option value="地下商场">地下商场</option>
										<option value="其它">其它</option>
									</select>
								</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>商铺状态:</span>
                                        <span>
									<input name="shopStatus" type="radio" value="营业中" id="shopStatus1" checked="checked"><label for="shopStatus1">营业中</label>
									<input name="shopStatus" type="radio" value="闲置中" id="shopStatus2"><label for="shopStatus2">闲置中</label>
									<input name="shopStatus" type="radio" value="新铺" id="shopStatus3"><label for="shopStatus3">新铺</label>
								</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left">目标业态:</span>
                                        <span>
									<input name="targetField" value="百货超市" type="checkbox" id="targetField1" checked="checked"><label for="targetField1">百货超市</label>
									<input name="targetField" value="酒店宾馆" type="checkbox" id="targetField2"><label for="targetField2">酒店宾馆</label>
									<input name="targetField" value="家居建材" type="checkbox" id="targetField3"><label for="targetField3">家居建材</label>
									<input name="targetField" value="服饰鞋包" type="checkbox" id="targetField4"><label for="targetField4">服饰鞋包</label>
									<input name="targetField" value="生活服务" type="checkbox" id="targetField5"><label for="targetField5">生活服务</label>
								</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left"></span>
                                        <span>
									<input name="targetField" value="美容美发" type="checkbox" id="targetField6"><label for="targetField6">美容美发</label>
									<input name="targetField" value="餐饮美食" type="checkbox" id="targetField7"><label for="targetField7">餐饮美食</label>
									<input name="targetField" value="休闲娱乐" type="checkbox" id="targetField8"><label for="targetField8">休闲娱乐</label>
									<input name="targetField" value="其它" type="checkbox" id="targetField9"><label for="targetField9">其它</label>
									<input onclick="selectAll('targetField')" type="button" value="全选 ">
									<input onclick="clearAll('targetField')" type="button" value="清空">
								</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left">物业费:</span>
                                        <span>
									<input id="fee2" name="fee2" type="text" class="iw92" value="0" datatype="fee" onkeypress="return checkFloat(event, this);">元/平米·月
									<span class="col-f60">注：若没有物业费时，为0。</span>
                                        <span class="Validform_checktip"></span></span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left">是否分割:</span>
                                        <span>
									<label><input name="divide2" value="1" type="radio" checked="checked">是</label>
									<label><input name="divide2" value="0" type="radio">否</label>
								</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left">是否临街:</span>
                                        <span>
									<label><input name="isFrontage" value="1" type="radio" checked="checked">是</label>
									<label><input name="isFrontage" value="2" type="radio">否</label>
								</span>
                                    </li>

                                    <li>
                                        <span class="saleHouse-basic-left">增值税:</span>
                                        <span>
									<label><input name="feeType" value="2" type="radio">有增值税</label>
									<label><input name="feeType" value="1" type="radio" checked="checked">无增值税</label>
								</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left">个税:</span>
                                        <span>
									<label><input name="taxType" value="2" type="radio">有个税</label>
									<label><input name="taxType" value="1" type="radio" checked="checked">无个税</label>
								</span>
                                    </li>

                                    <li>
                                        <span class="saleHouse-basic-left">配套设施:</span>
                                        <span>
									<label><input name="basicEquip2" value="客梯" type="checkbox" checked="checked">客梯</label>
									<label><input name="basicEquip2" value="货梯" type="checkbox" checked="checked">货梯</label>
									<label><input name="basicEquip2" value="扶梯" type="checkbox" checked="checked">扶梯</label>
									<label><input name="basicEquip2" value="暖气" type="checkbox">暖气</label>
									<label><input name="basicEquip2" value="空调" type="checkbox">空调</label>
									<label><input name="basicEquip2" value="停车位" type="checkbox">停车位</label>
									<label><input name="basicEquip2" value="水" type="checkbox" checked="checked">上水</label>
									<label><input name="basicEquip2" value="燃气" type="checkbox">燃气</label>
									<label><input name="basicEquip2" value="网络" type="checkbox">网络</label>
								</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left"></span>
                                        <span>
									<label><input name="basicEquip2" value="下水" type="checkbox">下水</label>
									<label><input name="basicEquip2" value="排烟" type="checkbox">排烟</label>
									<label><input name="basicEquip2" value="排污" type="checkbox">排污</label>
									<label><input name="basicEquip2" value="管煤" type="checkbox">管煤</label>
									<label><input name="basicEquip2" value="380V" type="checkbox">380V</label>
									<label><input name="basicEquip2" value="可明火" type="checkbox">可明火</label>
									<label><input name="basicEquip2" value="可外摆" type="checkbox">可外摆</label>
									<input onclick="selectAll('basicEquip2')" type="button" value="全选 ">
									<input onclick="clearAll('basicEquip2')" type="button" value="清空">
								</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>面宽:</span>
                                        <span>
									<input id="faceWidth" name="faceWidth" type="text" class="iw92" value="5" onkeypress="return checkInteger(event);" datatype="faceWidth">米
								<span class="Validform_checktip"></span></span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>层高:</span>
                                        <span>
									<input id="floorHigh" name="floorHigh" type="text" class="iw92" value="3" onkeypress="return checkInteger(event);" datatype="floorHigh">米
								<span class="Validform_checktip"></span></span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>进深:</span>
                                        <span>
									<input id="spatialDepth" name="spatialDepth" type="text" class="iw92" value="4" onkeypress="return checkInteger(event);" datatype="spatialDepth">米
								<span class="Validform_checktip"></span></span>
                                    </li>
                                </ul>
                            </div>
                        </div>


                        写字楼
                        <div class="saleHouse-basic-office" style="display:none">
                            <div class="sale-nav" id="officeType">
                                <span class="saleHouse-basic-header">写字楼信息</span>
                            </div>
                            <div class="saleHouse-basic clear">
                                <ul>
                                    <li>
                                        <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>写字楼类别:</span>
                                        <span>
									<select name="officeType" class="iw92">
										<option value="纯写字楼" selected="selected">纯写字楼</option>
										<option value="商住楼">商住楼</option>
										<option value="商业综合体楼">商业综合体楼</option>
										<option value="酒店写字楼">酒店写字楼</option>
										<option value="其它">其它</option>
									</select>
								</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>写字楼级别:</span>
                                        <span>
									<input name="officeLevel" value="甲级" type="radio" id="officeLevel1" checked="checked"><label for="officeLevel1">甲级</label>
									<input name="officeLevel" value="乙级" type="radio" id="officeLevel2"><label for="officeLevel2">乙级</label>
									<input name="officeLevel" value="丙级" type="radio" id="officeLevel3"><label for="officeLevel3">丙级</label>
									<input name="officeLevel" value="其它" type="radio" id="officeLevel4"><label for="officeLevel4">其它</label>
								</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left">物业费:</span>
                                        <span>
									<input type="text" id="fee3" name="fee3" class="iw92" value="0" datatype="fee" onkeypress="return checkFloat(event, this);">元/平米·月
									<span class="col-f60">注：若没有物业费时，为0。</span>
                                        <span class="Validform_checktip"></span></span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left">是否分割:</span>
                                        <span>
									<input name="divide3" value="1" type="radio" id="officefg1" checked="checked"><label for="officefg1">是</label>
									<input name="divide3" value="0" type="radio" id="officefg2"><label for="officefg2">否</label>
								</span>
                                    </li>

                                    <li>
                                        <span class="saleHouse-basic-left">增值税:</span>
                                        <span>
									<label><input name="feeType1" value="2" type="radio">有增值税</label>
									<label><input name="feeType1" value="1" type="radio" checked="checked">无增值税</label>
								</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left">个税:</span>
                                        <span>
									<label><input name="taxType1" value="2" type="radio">有个税</label>
									<label><input name="taxType1" value="1" type="radio" checked="checked">无个税</label>
								</span>
                                    </li>

                                    <li>
                                        <span class="saleHouse-basic-left">基础设施:</span>
                                        <span>
									<input name="basicEquip3" value="客梯" type="checkbox" id="basicEquip31" checked="checked"><label for="basicEquip31">客梯</label>
									<input name="basicEquip3" value="货梯" type="checkbox" id="basicEquip32" checked="checked"><label for="basicEquip32">货梯</label>
									<input name="basicEquip3" value="扶梯" type="checkbox" id="basicEquip33"><label for="basicEquip33">扶梯</label>
									<input name="basicEquip3" value="暖气" type="checkbox" id="basicEquip34"><label for="basicEquip34">暖气</label>
									<input name="basicEquip3" value="空调" type="checkbox" id="basicEquip35"><label for="basicEquip35">空调</label>
									<input name="basicEquip3" value="停车位" type="checkbox" id="basicEquip36"><label for="basicEquip36">停车位</label>
									<input name="basicEquip3" value="水" type="checkbox" id="basicEquip37"><label for="basicEquip37">水</label>
									<input name="basicEquip3" value="燃气" type="checkbox" id="basicEquip38"><label for="basicEquip38">燃气</label>
									<input name="basicEquip3" value="网络" type="checkbox" id="basicEquip39"><label for="basicEquip39">网络</label>
									<input onclick="selectAll('basicEquip3')" type="button" value="全选 ">
									<input onclick="clearAll('basicEquip3')" type="button" value="清空">
								</span>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        厂房
                        <div class="saleHouse-basic-factory" style="display:none">
                            <div class="sale-nav" id="factoryType">
                                <span class="saleHouse-basic-header">厂房信息</span>
                            </div>
                            <div class="saleHouse-basic clear">
                                <ul>
                                    <li>
                                        <span class="saleHouse-basic-left"><span class="col-fd6e05 mr5">*</span>类型:</span>
                                        <span>
									<input name="factoryType" value="厂房" type="radio" id="factoryType1" checked="checked"><label for="factoryType1">厂房</label>
									<input name="factoryType" value="仓库" type="radio" id="factoryType2"><label for="factoryType2">仓库</label>
									<input name="factoryType" value="车位" type="radio" id="factoryType3"><label for="factoryType3">车位</label>
									<input name="factoryType" value="土地" type="radio" id="factoryType4"><label for="factoryType4">土地</label>
									<input name="factoryType" value="其它" type="radio" id="factoryType5"><label for="factoryType5">其它</label>
								</span>
                                    </li>
                                    <li>
                                        <span class="saleHouse-basic-left">基础设施:</span>
                                        <span>
									<input name="basicEquip4" value="水" type="checkbox" id="basicEquip41" checked="checked"><label for="basicEquip41">水</label>
									<input name="basicEquip4" value="电" type="checkbox" id="basicEquip42" checked="checked"><label for="basicEquip42">电</label>
									<input name="basicEquip4" value="煤气" type="checkbox" id="basicEquip43"><label for="basicEquip43">煤气</label>
									<input name="basicEquip4" value="暖气" type="checkbox" id="basicEquip44"><label for="basicEquip44">暖气</label>
									<input name="basicEquip4" value="有线电视" type="checkbox" id="basicEquip45"><label for="basicEquip45">有线电视</label>
									<input name="basicEquip4" value="宽带" type="checkbox" id="basicEquip46" checked="checked"><label for="basicEquip46">宽带</label>
									<input name="basicEquip4" value="电话" type="checkbox" id="basicEquip47"><label for="basicEquip47">电话</label>
									<input name="basicEquip4" value="空调" type="checkbox" id="basicEquip48"><label for="basicEquip48">空调</label>
									<input onclick="selectAll('basicEquip4')" type="button" value="全选 ">
									<input onclick="clearAll('basicEquip4')" type="button" value="清空">
								</span>
                                    </li>
                                </ul>
                            </div>
                        </div> -->
                    </div>

                    <!-- 房源介绍内容区 -->
                    <div class="saleHouse-intro-content">
                        <div class="sale-nav" id="house">
                            <span>房源介绍</span>
                        </div>
                        <ul class="sale-content-tip">
                            <li>
                                <span class="sale-tip-img"></span>
                                <span>贴心提示:</span>
                                <span class="sale-tip-colse"></span>
                            </li>
                            <li>1.房源标题目的：突出房子的主要特点，吸引客户，增加点击量和电话量。</li>
                            <li>2.完整的房源标题应该是：<span class="col-fd6e05">商圈+卖点+楼盘名称+户型+卖点。</span></li>
                            <li>3.易推房会自动检测标题与描述中的敏感词,<a href="javascript:getKeyFilter()">查看敏感词</a>(继续修改时，房源描述中的敏感词会“标红”)</li>
                        </ul>
                        <ul class="saleHouse-intro-main">
                            <li>
                                <span class="font-bold"><span class="col-fd6e05 mr5">*</span>信息标题:</span>
                                <span>好的标题是增加点击，吸引眼球第一步！<input class="btn-green" onclick="getAutoView(1)" type="button" value="帮我写标题">
							<input type="hidden" value="1" id="pageIndex" name="pageIndex">
						</span>
                            </li>
                            <li class="import-titleTip">
                                <!-- 标题信息提示 -->
                                <div id="titleTipDiv" class="hdtop" style="display:none">
                                    <div class="vldtop"></div>
                                    <div class="titleTip-close">
                                        <a href="javascript:titleWindowClose('titleTipDiv')">
                                            <span class="tip-img"></span>
                                        </a>
                                    </div>
                                    <ul>
                                    </ul>
                                </div>
                                <div id="titie-guide" style="height:32px;line-height:32px">
                                    <input id="saleHouse-title" name="title" class="saleHouse-title iw400" 
                                    value="​${massProperty.title }" type="text" datatype="saleHouse-title" >
                                    <span id="titleNumsSpan" class="col-fd6e05 mr5"></span>
                                <span class="Validform_checktip"></span></div>
                            </li>
                            <li>
                                <span class="font-bold"><span class="col-fd6e05 mr5">*</span>信息描述:</span>
                                <span> 30-300字效果为最佳，<span class="font-bold col-fd6e05">多彩文本</span>更容易引起客户关注
                                <input class="btn-green" onclick="getAutoView(2)" type="button" value="帮我写描述">
                                <input class="btn-green" type="button" value="我的描述" onclick="getAutoArt(1)">
                                <input class="btn-green" type="button" value="保存描述" id="save_desc_btn">
                                <!-- <input class="btn-green" type="button" value="中介模板" onclick="getAutoArt(2)"> -->
                                </span>
                            </li>
                        </ul>
                        <div class="saleHouse-textarea clear" id="houseDescribe-guide">
                            <textarea id="houseDescribe" name="description" style="visibility: hidden; display: none;">​${massProperty.description }</textarea>
                            <div style="margin-top: 8px;"><span class="col-fd6e05" id="houseDescribeText"></span></div>
                        </div>
                        <div class="houseDesc-tip">信息描述内容避免使用敏感字符; 部分网站不允许使用特殊字符: ▲◎☆★◇◆□■▽▼●○△▲ 《》♀♂⊕⊙＊※【】‖︻ ︼</div>
						<div class="anjukexinbanDesc claer">
							<div class="ajkxbDesc">
								<span class="descHeader"><span class="font-bold">业主心态:</span><span class="col-999">从房东卖房原因、是否急售等方面进行描述</span><span class="col-f60">(字数限制20-300)</span></span>
								<input class="btn-green js-btn-mytpl" data-tpltitle="我的业主心态" type="button" value="我的模板"> 
								<input class="btn-green js-btn-tplsave" type="button" value="保存模板">
								<textarea id="yeZhuXingTai" name="mentality" class="ajkxbTextArea" onfocus="removeOnkey()" onblur="addOnkey(this)">${massProperty.mentality }</textarea>
							</div>
							<div class="ajkxbDesc">
								<span class="descHeader"><span class="font-bold">小区配套:</span><span class="col-999">从交通、教育、医疗、内部环境、车位情况、安保措施方面来描述</span><span
									class="col-f60">(字数限制20-300)</span></span> <input class="btn-green js-btn-mytpl" data-tpltitle="我的小区配套" type="button" value="我的模板"> <input
									class="btn-green js-btn-tplsave" type="button" value="保存模板">
								<textarea id="xiaoQuPeiTao" name="environment" class="ajkxbTextArea" onfocus="removeOnkey()" onblur="addOnkey(this)">${massProperty.environment }</textarea>
							</div>
							<div class="ajkxbDesc">
								<span class="descHeader"><span class="font-bold">服务介绍:</span><span class="col-999">多角度描述您的服务优势，例如：行业年限、专业经验、服务态度、可提供的服务种类等</span><span
									class="col-f60">(字数限制20-300)</span></span> <input class="btn-green js-btn-mytpl" data-tpltitle="我的服务介绍" type="button" value="我的模板"> <input
									class="btn-green js-btn-tplsave" type="button" value="保存模板">
								<textarea id="fuWuJieShao" name="serve" class="ajkxbTextArea" onfocus="removeOnkey()" onblur="addOnkey(this)">${massProperty.serve }</textarea>
							</div>
							<div class="ajkxbDesc">
								<span class="descHeader"><span class="font-bold">税费信息:</span><span class="col-999">相关费用，描述越详尽您的房源质量就会越高，展示给网友的机会就会增加(仅搜房帮)</span><span
									class="col-f60">(字数限制20-300)</span></span> <input class="btn-green js-btn-mytpl" data-tpltitle="税费信息介绍" type="button" value="我的模板"> <input
									class="btn-green js-btn-tplsave" type="button" value="保存模板">
								<textarea id="taxation" name="taxation" class="ajkxbTextArea" onfocus="removeOnkey()" onblur="addOnkey(this)">${massProperty.taxation }</textarea>
							</div>
						</div>
					</div>

                    <!-- 图片信息内容区 -->
                    <div class="sale-pic-content">
                        <div class="sale-nav" id="pic">
                            <span>图片信息</span>
                        </div>
                        <ul class="sale-content-tip">
                            <li>
                                <span class="sale-tip-img">
						</span><span>贴心提示:</span>
                                <span class="sale-tip-colse"></span>
                            </li>
                            <li><span class="float-l">1.上传1张房型图，3张室内图，房源描述需要30个字以上的房源，就能获得</span><img width="30" height="18" src="<%=path%>/Static/Picture/duotu.jpg" class="float-l"><span class="float-l">标签。多图房源能获得比一般房源高出27.3%的点击率</span>。</li>
                            <li>2.上传的照片应大于300*300像素，非大图压缩方式支持小于1M的图片，大图模式可支持小于5M的图片(只支持压缩jpg、jpeg格式的图片)。</li>
                            <li>3.目前支持 gif、jpg、jpeg、png 4种格式的图片上传，bmp格式图片暂不支持</li>
                            <li><span class="float-l">4.提供一些免费的软件</span>
                                <img src="<%=path%>/Static/Picture/untitled1.jpg" style="float:left;margin:2px 0 0"><span class="float-l">(<a href="https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/ekeResource/download/PicCompress.rar">图片压缩软件下载</a>)、</span>
                                <img src="<%=path%>/Static/Picture/untitled1.jpg" style="float:left;margin:2px 0 0"><span class="float-l">(<a href="https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/ekeResource/download/ImgWater.rar">加水印软件下载</a>)、</span>
                                <img src="<%=path%>/Static/Picture/untitled1.jpg" style="float:left;margin:2px 0 0"><span class="float-l">(<a href="https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/ekeResource/download/conew.rar">去水印软件下载</a>)。</span>
                            </li>
                            <li>5.帮助：<a href="javascript:getHelp()">上传照片规则</a></li>
                        </ul>
                        <div class="sale-pic-main">
                            <div class="sale-uploadType clear">
                                <div class="sale-main-left float-l">上传方式:</div>
                                <div class="sale-uploadType-right float-l"><!-- 
                                    <input name="picupload" value="0" type="radio" id="picupload1" onchange="singleClick()"><label for="picupload1">普通上传</label>
                                    <input name="picupload" value="1" type="radio" id="picupload2" onchange="mutilDivClick()"><label for="picupload2">批量上传</label> -->
                                    <input name="picupload" value="2" type="radio" id="picupload3" onchange="compressClick()" checked="checked"><label for="picupload3">大图压缩批量上传</label>
                                </div>
                                <div class="float-l" style="margin: 5px 5px 0 5px; cursor: pointer;border: 1px solid #48B510;">
                                    <img onclick="weixinClick()" src="<%=path%>/Static/Picture/wxup.jpg">
                                </div>
                                <div class="float-l"><!-- 
                                    <span class="col-fd6e05 picupload2-tip">大图压缩批量上传为此电脑最佳上传方式</span>
                                    <span class="col-fd6e05 picupload1-tip" style="display:none">您没有安装flash,请立即安装,谢谢。</span> -->
                                </div>
                            </div>
                            <div id="watermark-pic-position" class="sale-uploadType clear">
                                <div class="sale-main-left float-l">水印位置:</div>
                                <div class="sale-watermark-pic-right float-l">
                                    <ul class="sale-watermark-content">
                                        <li>
                                            <input name="watermark-position" type="radio" id="watermark-no" value="0" checked="checked"><label for="watermark-no">不加水印</label>
                                            <a href="javascript:getWatermark()" class="ml8">设置</a>
                                        </li>
                                    </ul>
                                    <img class="sale-watermark-pic mb8" src="" url="" style="display: none">
                                    <input type="hidden" name="alpha" id="alpha" value="1">
                                </div>
                            </div>

                            <div class="sale-uploadType clear">
                                <ul class="float-l" id="indexPic-guide">
                                    <li>
                                        <div class="sale-main-left float-l">封 面 图:</div>
                                        <ul class="sale-indexPic-right float-l">
                                            <li>点击希望设定为封面的图片右下角的封面按钮<span></span>即可设定。</li>
                                            <!-- 封面图 -->
											<li id="coverDiv">
												<c:if test="${empty (massProperty.headimgpath) }">
													<div class="item import-pic" style="float: left; margin: 2px 8px 8px 0px; display: inline">
														<img class="addImg" onclick="clickImg(this);" src="<%=path%>/Static/Image/addImg.png" />
														<input name="HeadImg" type="file" class="upload_input" onchange="change(this,'HeadImg')" />
														<div class="preBlock">
															<img class="preview" id="preview" alt="" name="pic" width="116" height="116"/>
														</div>
														<img class="delete" onclick="deleteImg(this)" src="<%=path%>/Static/Image/delete.png" />
													</div>
												</c:if>
												<c:if test="${!empty (massProperty.headimgpath) }">
													<div class="item import-pic" style="float: left; margin: 2px 8px 8px 0px; display: inline">
														<img class="addImg" onclick="clickImg(this);" src="<%=path%>/Static/Image/addImg.png" style="display: none;"/>
														<input name="HeadImg" type="file" class="upload_input" onchange="change(this,'HeadImg')" />
														<div class="preBlock">
															<img class="preview" id="preview" alt="" name="pic" width="116" height="116" 
															src="${massProperty.headimgpath }"/>
														</div>
														<img class="delete" onclick="deleteImg(this)" src="<%=path%>/Static/Image/delete.png"style="display: inline;" />
													</div>
												</c:if>
											</li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>

                            <iframe name="hidden_frame" id="hidden_frame" style="display:none"></iframe>

                            <input type="hidden" id="temp_images">
                            <input type="hidden" id="personConfig-watermark" value="0">
                            <input type="hidden" id="personConfig-pos" value="1">
                            <input type="hidden" id="personConfig-img">
                            <!-- 室内照片 -->
                            <div class="sale-housePic clear">
                                <div class="sale-main-left float-l">室内照片:</div>
                                <ul class="float-l">
                                    <li>
                                        <span>最多10张。您可以</span>
                                        <span class="col-419d31">从我的图库选择</span>
                                        <input class="btn-green my_img" type="button" value="我的图库" imagetype="IMG_I"><!-- 
                                        <input class="btn-green my_indoor" type="button" value="高清图库" imagetype="IMG_I">
                                        <input class="btn-green realsurvey_img" type="button" value="真实图库" imagetype="IMG_I"> -->
                                        <span>客厅/卧室/厨房等3张以上照片可帮助您获得较好效果！</span>
                                    </li>
                                    <li class="clear">
                                        <div id="div_IMG_I" class="div-img">
                                        	<c:if test="${fn:length(indoorUrls) != 0}">
                                        		<c:forEach items="${indoorUrls }" var="list">
	                                        		<div class="item import-pic div_IMG_I" style="float: left; margin: 2px 8px 8px 0px; display: inline">
														<img class="addImg" onclick="clickImg(this);" src="<%=path%>/Static/Image/addImg.png" style="display: none;"/>
														<input name="div_IMG_I" type="file" class="upload_input" onchange="change(this,'div_IMG_I')" />
														<div class="preBlock">
															<img class="preview" id="preview" alt="" name="pic" width="116" height="116" src="${list.path}"/>
														</div>
														<img class="delete" onclick="deleteImg(this)" src="<%=path%>/Static/Image/delete.png" style="display: inline;" imageId="${list.id}" name="indoorUrls"/>
													</div>
                                        		</c:forEach>
                                        	</c:if>
                                        	<c:if test="${fn:length(indoorUrls) < 10}">
                                        		<div class="item import-pic div_IMG_I" style="float: left; margin: 2px 8px 8px 0px; display: inline">
													<img class="addImg" onclick="clickImg(this);" src="<%=path%>/Static/Image/addImg.png" />
													<input name="div_IMG_I" type="file" class="upload_input" onchange="change(this,'div_IMG_I')" />
													<div class="preBlock">
														<img class="preview" id="preview" alt="" name="pic" width="116" height="116"/>
													</div>
													<img class="delete" onclick="deleteImg(this)" src="<%=path%>/Static/Image/delete.png" />
												</div>
											</c:if>
										</div>
									</li>
                                </ul>
                            </div>
                            
                            <!-- 房 型 图 -->
                            <div class="sale-housePic clear">
                                <div class="sale-main-left float-l">房 型 图:</div>
                                <ul class="float-l">
                                    <li class="clear" style="height:23px;line-height:23px;margin:6px 0">
                                        <span>最多10张。您可以</span>
                                        <span class="col-419d31">从我的图库选择</span> 或者
                                        <span class="col-419d31">从房型图库选择</span>
                                        <input class="btn-green my_img" type="button" value="我的图库" imagetype="IMG_M">
                                        <input class="btn-pink public_img" type="button" value="房型图库" imagetype="IMG_M"><!-- 
                                        <input class="btn-green internet_img" type="button" value="真实房型图" imagetype="IMG_M">
                                        <input class="btn-green" type="button" value="在线绘制" onclick="javascript:window.open('//s.img.xms.demo.com/plugins/flash/drawtool.swf');"> -->
                                    </li>
                                    <li class="clear" id="container_IMG_M-content" style="display:none;">
                                        <div class="container-content-font" style="height:24px;line-height:24px">易推房图库推荐(选择您要的图片，即可出现在房源图列中):</div>
                                        <div id="container_IMG_M" imgtype="IMG_M" class="containerImg"></div>
                                    </li>
                                    <li class="clear">
                                        <div id="div_IMG_M" class="div-img">
                                        	<c:if test="${fn:length(houseTypeUrls) != 0}">
                                        		<c:forEach items="${houseTypeUrls }" var="list">
	                                        		<div class="item import-pic div_IMG_M" style="float: left; margin: 2px 8px 8px 0px; display: inline">
														<img class="addImg" onclick="clickImg(this);" src="<%=path%>/Static/Image/addImg.png" style="display: none;"/>
														<input name="div_IMG_M" type="file" class="upload_input" onchange="change(this,'div_IMG_M')" />
														<div class="preBlock">
															<img class="preview" id="preview" alt="" name="pic" width="116" height="116" src="${list.path}"/>
														</div>
														<img class="delete" onclick="deleteImg(this)" src="<%=path%>/Static/Image/delete.png" style="display: inline;" 
														imageId="${list.id}" name="houseTypeUrls"/>
													</div>
                                        		</c:forEach>
                                        	</c:if>
                                        	<c:if test="${fn:length(houseTypeUrls) < 10}">
                                        		<div class="item import-pic div_IMG_M" style="float: left; margin: 2px 8px 8px 0px; display: inline">
													<img class="addImg" onclick="clickImg(this);" src="<%=path%>/Static/Image/addImg.png" />
													<input name="div_IMG_M" type="file" class="upload_input" onchange="change(this,'div_IMG_M')" />
													<div class="preBlock">
														<img class="preview" id="preview" alt="" name="pic" width="116" height="116"/>
													</div>
													<img class="delete" onclick="deleteImg(this)" src="<%=path%>/Static/Image/delete.png" />
												</div>
											</c:if>
										</div>
                                    </li>
                                </ul>
                            </div>
                            <!-- 小区照片 -->
                            <div class="sale-housePic clear">
                                <div class="sale-main-left float-l">小区照片:</div>
                                <ul class="float-l">
                                    <li class="clear" style="height:23px;line-height:23px;margin:6px 0">
                                        <span>最多10张。您可以</span>
                                        <span class="col-419d31">从我的图库选择</span> 或者
                                        <span class="col-419d31">从小区图库选择</span>
                                        <input class="btn-green my_img" type="button" value="我的图库" imagetype="IMG_O">
                                        <input class="btn-green my_area" type="button" value="小区图库" imagetype="IMG_O">

                                        <!-- 								<span>如果没有上传，发布后目标网站会默认配小区图</span> -->
                                    </li>
                                    <li class="clear" id="container_IMG_O-content" style="display:none;">
                                        <div class="container-content-font">易推房图库推荐(选择您要的图片，即可出现在房源图列中):</div>
                                        <div id="container_IMG_O" imgtype="IMG_O" class="containerImg"></div>
                                    </li>
                                    <li class="clear">
                                        <div id="div_IMG_O" class="div-img">
                                        	<c:if test="${fn:length(estateUrls) != 0}">
                                        		<c:forEach items="${estateUrls }" var="list">
	                                        		<div class="item import-pic div_IMG_O" style="float: left; margin: 2px 8px 8px 0px; display: inline">
														<img class="addImg" onclick="clickImg(this);" src="<%=path%>/Static/Image/addImg.png" style="display: none;"/>
														<input name="div_IMG_O" type="file" class="upload_input" onchange="change(this,'div_IMG_O')" />
														<div class="preBlock">
															<img class="preview" id="preview" alt="" name="pic" width="116" height="116" src="${list.path}"/>
														</div>
														<img class="delete" onclick="deleteImg(this)" src="<%=path%>/Static/Image/delete.png" style="display: inline;" 
														imageId="${list.id}" name="estateUrls"/>
													</div>
                                        		</c:forEach>
                                        	</c:if>
                                        	<c:if test="${fn:length(estateUrls) < 10}">
                                        		<div class="item import-pic div_IMG_O" style="float: left; margin: 2px 8px 8px 0px; display: inline">
													<img class="addImg" onclick="clickImg(this);" src="<%=path%>/Static/Image/addImg.png" />
													<input name="div_IMG_O" type="file" class="upload_input" onchange="change(this,'div_IMG_O')" />
													<div class="preBlock">
														<img class="preview" id="preview" alt="" name="pic" width="116" height="116"/>
													</div>
													<img class="delete" onclick="deleteImg(this)" src="<%=path%>/Static/Image/delete.png" />
												</div>
											</c:if>
										</div>
                                    </li>
                                </ul>
                            </div>

                            <!-- 全景图 -->

                            <!-- <div class="xmsVideo xmsImport" id="xms_video">
                                <div class="left">房源视频:</div>
                                <ul class="right">
                                    <li style="line-height:23px;margin:6px 0 0">
                                       	 视频大小在50M内，视频长度在1-3分钟为最佳，视频过短新三网审核不予通过。
                                       	 <input class="btn-green" onclick="getMyVideo()" type="button" value="添加视频">
                                    </li>
                                    <li id="videoUploaded" style="width:180px"></li>
                                </ul>
                            </div>
                            <div class="videoUpload xmsImport" style="display:none">
                                <span class="left">视频下载:</span>
                                <div class="right">
                                    <input type="hidden" value="" name="videoOriUrl">
                                    <label><input type="checkbox" name="videoCheck" value="1">下载秒录的视频</label>
                                    <div class="col-999">下载视频会消耗视频秒录累积量与视频库存累积量,您今日还可秒录 <span class="col-red font-bold" id="vVemaining">5</span>条视频 ,剩余 <span class="col-red font-bold" id="vRemaining">20</span>条视频库存</div>
                                </div>
                            </div> -->


                            <!-- 底部按钮 -->
                            <ul class="footer-button clear">
                                <li>
                                    <!--<span id="synWiwj_span" style="display:none"><input type="checkbox" id="synWiwj" name="wiwjerp" value="1"></input><label for="synWiwj">同步上架官网</label></span> -->
                                    <input class="btn-saveHouse" type="submit" value="保存房源" id="importSave-guide" onclick="saveDraft(2)">
                                    <input class="btn-saveHouse" type="button" value="保存草稿" onclick="saveDraft(2)">
                                </li>
                            </ul>

                            <!-- 批量打水印 -->
                            <div id="batch-logo" style="display: none;">
                                <a>logo</a>
                                <ul class="batch-logo-ul">
                                    <li onclick="addWaterMarks()">批量添加水印</li>
                                    <li onclick="restoreBatch()">批量恢复原图</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" xmlns:c="http://www.w3.org/1999/XSL/Transform">
    <script type="text/template" id="tpl_random_img">
        <div id="randomImg-content">
            {{each images as value i}}
            <ul class="selectPic-content">
                <li class="publicimg_li importImgInfo">
                    <a href="{{value.imgUrl}}" data-lightbox="imgSet" data-title="{{value.imgDescribe}}">
                        <img src="{{value.smallImgUrl}}" width="120" height="120"></img>
                    </a>
                    {{if value.imgWidth > 0 && value.imgHeight > 0 }}
                    <span class="imgSizeInfo">{{value.imgWidth }}x{{value.imgHeight }}</span> {{/if}}
                </li>
                <li class="select-content-input" style="height:24px;line-height:24px">
                    <label title="{{value.imgDescribe}}">
					<input type="checkbox" name="random-image_check" imgUrl="{{value.imgUrl}}" imgWidth="{{value.imgWidth}}" imgHeight="{{value.imgHeight}}"/>
					
				</label>
                </li>
            </ul>
            {{/each}}
        </div>
    </script>


    <script type="text/template" id="tpl_Indoor_img">
        <div class="selectPic" id="myImg-images" style="height:380px;">
            <div id="myImg-list">
                <table width="100%" class="ptable" id="myImg-tab">
                    <tbody>
                        <tr>
                            <td class="{{if room == " 1 "}}selected{{/if}}" room="1">1室</td>
                            <td class="{{if room == " 2 "}}selected{{/if}}" room="2">2室</td>
                            <td class="{{if room == " 3 "}}selected{{/if}}" room="3">3室</td>
                            <td class="{{if room == " 4 "}}selected{{/if}}" room="4">4室</td>
                            <td class="{{if room == " 5 "}}selected{{/if}}" room="5">5室</td>
                            <td class="{{if room > 5}}selected{{/if}}" room="6">5+室</td>
                        </tr>
                    </tbody>
                </table>
                <div class="surveyimg-list" id="myImg-content" style="height:360px;">
                    {{if images.length==0}}
                    <div class="houseTitle-font">抱歉：没有找到图片！</div>{{/if}} {{if images.length>0}} {{each images as value i}}
                    <input type="hidden" id="id_{{value.bogusHouseId}}" room="{{value.room}}" hall="{{value.hall}}" houseArea="{{value.houseArea}}"></input>
                    <ul class="selectPic-content internetImg-content" onclick="getMyImagesDetail('{{value.bogusHouseId}}')">
                        <li>
                            <img src="{{value.coverUrl}}" width="112" height="112"></img>
                        </li>
                        <li style="height:24px;line-height:24px">
                            {{value.room}}室{{value.hall}}厅 {{value.houseArea }}平
                        </li>
                        <li style="height:24px;line-height:24px">
                            {{value.curFloor}}/{{value.maxFloor}}
                        </li>
                    </ul>
                    {{/each}} {{/if}}
                </div>
            </div>
            <div id="myImg-detail" style="display:none">

            </div>
        </div>
    </script>
    <!-- begin: 标签定制 -->
    <!-- begin: 标签定制 -->
    <style>
        .clear {
            clear: both
        }
        
        .display-none {
            display: none
        }
        
        .customLabel-window {
            width: 580px;
            height: 350px;
            left: 116px;
            top: 254px;
            padding: 0;
        }
        
        .tip-window ul {
            padding: 0 2px;
        }
        
        .tag-main-box {
            border: 0px solid red;
            width: 100%;
            overflow: hidden;
            display: block;
        }
        
        .tag-main-box .title {
            height: 30px;
            line-height: 30px;
            font-weight: bold;
            font-size: 14px;
            text-indent: 15px;
            background: #F0F0F0;
        }
        
        .tag-main-box .title .close {
            float: right;
            margin-right: 10px;
            cursor: pointer;
            font-size: 16px;
        }
        
        .tag-web-site {
            font-size: 14px;
            border-bottom: 1px solid #f0f0f0;
            overflow: hidden;
            margin-top: 5px;
        }
        
        .tag-web-site li {
            float: left;
            padding: 2px 15px;
            cursor: pointer;
        }
        
        .tag-web-site li.on {
            background: #72C962;
            color: #fff;
        }
        /*安居客子菜单*/
        
        .tag-anjuke-sub-title {
            border-bottom: 1px solid #f0f0f0;
            overflow: hidden;
            margin-top: 5px;
        }
        
        .tag-anjuke-sub-title li {
            float: left;
            padding: 3px 10px;
            margin-left: 5px;
            cursor: pointer;
            background: #eee;
            border-radius: 5px 5px 0 0;
            height: 16px;
            line-height: 16px
        }
        
        .tag-anjuke-sub-title li.on {
            background: #72C962;
            color: #fff;
        }
        
        .tag-list-box {
            height: 125px;
            overflow: auto;
        }
        
        .tag-list-box ul li,
        .tag-anjuke-selected-list li {
            width: 80px;
            padding: 2px 0;
            border: 1px solid #72C962;
            border-radius: 5px;
            text-align: center;
            height: 20px;
            line-height: 20px;
            margin-top: 5px;
            margin-left: 10px;
            cursor: pointer;
        }
        
        .tag-list-box ul li.selected,
        .tag-anjuke-selected-list li.selected {
            background: #72C962;
            color: #fff;
        }
        
        .tag-custom {
            clear: both;
            position: absolute;
            bottom: 50px;
            margin-left: 20px;
        }
        
        .tag-custom input {
            height: 18px;
            line-height: 18px;
        }
        
        .bottom-tip {
            height: 30px;
            line-height: 30px;
            position: absolute;
            bottom: 0;
            left: 0;
            background: #eee;
            width: 100%;
            text-indent: 10px;
            color: red;
        }
    </style>

    <!-- 标签定制弹出层 -->
    <!-- <div class="tip-window customLabel-window" style="display:none"></div> -->
    <div class="tip-window customLabel-window" id="customTagBox" style="display:none;">
        <div class="tag-main-box">
            <div class="title"><span class="close">X</span><span class="web-name">标签定制</span></div>
            <div class="tag-web-site">
                <ul></ul>
            </div>
            <div class="tag-anjuke-sub-title display-none"></div>
            <div class="tag-list-box clear">
                <ul></ul>
            </div>
            <div class="tag-anjuke-selected-list  display-none">
                <ul></ul>
            </div>
            <div class="tag-custom"><input placeholder="自定义标签" id="customTagInp"> <a class="btn-green js-add-custom-tag">确定</a></div>
            <div class="bottom-tip"><strong>提示：</strong><span></span></div>
        </div>
    </div>

    <!-- end: 标签定制 -->
    <!-- end: 标签定制 -->


    <script>
        var filterCellMatch = [];
    </script>

    <script src="<%=path%>/Static/Js/jquery-1.8.3.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/jquery.form.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/ajaxfileupload.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/map.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/mathUtil_1467fc23826ddcf3f42074cff60da098.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/placeholder.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/jquery.cs.calculator1.5.beta.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/jquery.cookie.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/plugins/ckeditor/ckeditor.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js?skin=green" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/plugins/artDialog/plugins/iframeTools.source.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/iframeTools.source.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/jquery.lazyload.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/Validform_v5.3.2_min_854caf875a95c2779cb780e0b6ee6d3a.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/template.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/lightbox.min_7036adb83f9be3e168896fb077b8e4f0.js" type="text/javascript"></script><div id="lightboxOverlay" class="lightboxOverlay" style="display: none;"></div><div id="lightbox" class="lightbox" style="display: none;"><div class="lb-outerContainer"><div class="lb-container"><img class="lb-image" src="data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw=="><div class="lb-nav"><a class="lb-prev" href=""></a><a class="lb-next" href=""></a></div><div class="lb-loader"><a class="lb-cancel"></a></div></div></div><div class="lb-dataContainer"><div class="lb-data"><div class="lb-details"><span class="lb-caption"></span><span class="lb-number"></span></div><div class="lb-closeContainer"><a class="lb-close"></a></div></div></div></div>
    <script src="<%=path%>/Static/Js/myImgIndoor_7261fb65c168e00846bc1e4352de3bda.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/import_34f0ea69ebc0152d0706a456ad0ac50d.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/cellmapping_147daa8f63e4f9f94fd06f810f836a85.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/copyInitData_f5416c156167f8c859f034ad4082892c.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/checkImport_21c151958d3ad857655c2e32acd3c364.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/checkInput.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/customTag2_5e3a8058f4f8d84809758603e568724f.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/customShowByUserWeb_dd1ea32965c22deaf57b33450f280097.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/jquery.paginate.js" type="text/javascript"></script>

    <script src="<%=path%>/Static/Js/plupload.full.min.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/zh_CN.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/jquery.uploadify.min.js" type="text/javascript"></script>

    <script src="<%=path%>/Static/Js/intro_e523b61a7b0f0aa4d1bcc380363a6bba.js" type="text/javascript"></script>
    <script src="<%=path%>/Static/Js/introConfig_release_import_e733abb94e71a0c17c6e546306706ba6.js" type="text/javascript"></script>
    
   

    <script>
        var cityMap = new Map();
        cityMap.put("755", '深圳');

        cityMap.put("769", '东莞');

        cityMap.put("760", '中山');

        cityMap.put("752", '惠州');

        cityMap.put("20", '广州');

        cityMap.put("114", '惠东');

        cityMap.put("762", '河源');

        cityMap.put("115", '博罗');
    </script>
    <script>
    $(function(){
        selectSelected("countf","${massProperty.countf }");
        selectSelected("countt","${massProperty.countt }");
        selectSelected("countw","${massProperty.countw }");
        selectSelected("countc","${massProperty.countc }");
        selectSelected("county","${massProperty.county }");
        selectSelected("unittype","${massProperty.unittype }");
        selectSelected("buildingtype","${massProperty.buildingtype }");
        selectSelected("housesubtype","${massProperty.massHouse.housesubtype }");
        selectSelected("houseproperty","${massProperty.massHouse.houseproperty }");
        
        
        selectRadio("direction","${massProperty.direction }");
        selectRadio("look","${massProperty.look }");
        selectRadio("decoration","${massProperty.decoration }");
        selectRadio("housetype","${massProperty.massHouse.housetype }");
        selectRadio("landyear","${massProperty.massHouse.landyear }");
        selectRadio("housestructure","${massProperty.massHouse.housestructure }");
        selectRadio("houseyear","${massProperty.massHouse.houseyear }");
        
        checkboxChecked("basicequipList","${massProperty.massHouse.basicequip }");
        checkboxChecked("advequipList","${massProperty.massHouse.advequip }");
        $("#districtSelect").change(function(){
        	districtSelect($(this));
        });
        districtSelect($("#districtSelect"));
        function districtSelect(select){
        	var district = select.val();
        	$.ajax({
                url: "/WebRelease/area/getAreaList",
                type: "post",
                data: {district:district},
                dataType: "json",
                async:false, 
                success: function(data) {
					if(data.code == 200){
						var list = new Array();
						list = data.data;
						$("#areaSelect").empty();
						$("#areaSelect").append("<option value=''>请选择</option>");
						$.each(list,function(n,value) {
							var areaid = $("#area").val();
							if(areaid == value.id){
								$("#areaSelect").append("<option value='"+value.id+"' selected='selected' >"+value.area+"</option>");
							}else{
								$("#areaSelect").append("<option value='"+value.id+"'>"+value.area+"</option>");
							}
						});
					}else{
						alert("获取失败");
					}
                },
                error: function() {
					alert("获取失败");
                }
        	});
        }
    });
    <c:if test="${postType == 1}">
	    $(function(){
	        selectSelected("subletnumber","${massProperty.subletnumber}");
	        selectSelected("subletroom","${massProperty.subletroom}");
	        selectSelected("subletcondition","${massProperty.subletcondition}");
	        selectSelected("subletpaytype","${massProperty.subletpaytype}");
	    });
    </c:if>
    function changeLoadFlag(openSource) {
        if ('0' == 0) {
            if (openSource) {
                CKEDITOR.replace("houseDescribe", {
                    toolbar: [
                        ['Source'],
                        ['Font', 'FontSize', 'lineheight'],
                        ['TextColor', 'BGColor'],
                        ['Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript', 'Superscript'],
                        ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
                        ['RemoveFormat'],
                        ['Maximize']
                    ],
                    height: '460px'
                });


            } else {
                CKEDITOR.replace("houseDescribe", {
                    toolbar: [
                        ['Font', 'FontSize', 'lineheight'],
                        ['TextColor', 'BGColor'],
                        ['Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript', 'Superscript'],
                        ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
                        ['RemoveFormat'],
                        ['Maximize']
                    ],
                    height: '460px'
                });
            }
            loadingFlag = true;
        }
        loadingFlag = true;
        /* setTimeout(function() {
            CKEDITOR.instances.houseDescribe.document.on("keyup", function() {
                var descContent = CKEDITOR.instances.houseDescribe.getData();
                setShowLength(descContent, "houseDescribeText")
            })
        }, 500) */
        
    }
        function checkLoading() {
            /* if (!loadingFlag) {
                art.dialog({
                    title: "提示",
                    content: "页面未加载完成，请耐心等待",
                    ok: true
                });
                return false;
            }
            if (subNum == 0) {
                subNum++;
                vaildForm.resetStatus();
            } */
            return true;
        }

  		//点击
	  var clickImg = function(obj){
	    $(obj).parent().find('.upload_input').click();
	  }
	  //删除
	  var deleteImg = function(obj){
		  if($(obj).parent().length == 1){
			    var imageId = $(obj).attr("imageId");
				var name = $(obj).attr("name");
				var ids = $("#" + name).val();
				$("#" + name).val( ids + imageId + ",");
				$(obj).parent().find('input').val('');
				$(obj).parent().find('img.preview').attr("src","");
				//IE9以下
				$(obj).parent().find('img.preview').css("filter","");
				$(obj).hide();
				$(obj).parent().find('.addImg').show();
		  }else{
			  	$(obj).parent().remove();
		  }
	  }
	  
	  var setHeadImgUrl = function(obj){
  		var url = $(obj).parent().find(".preview").attr("src");
  		$("#setHeadImg").attr("src",url);
  	  }
	  //选择图片
	  function change(file,className) {
	      //预览
	      var pic = $(file).parent().find(".preview");
	      //添加按钮
	      var addImg = $(file).parent().find(".addImg");
	      //删除按钮
	      var deleteImg = $(file).parent().find(".delete");
	      //设置封面按钮
	      var setHeadImg = $(file).parent().find(".setHeadImg");
	
	      var ext=file.value.substring(file.value.lastIndexOf(".")+1).toLowerCase();
	
	       // gif在IE浏览器暂时无法显示
	       if(ext!='png'&&ext!='jpg'&&ext!='jpeg'){
	          if (ext != '') {
	            alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
	          } 
	          return;
	       }
	       //判断IE版本
	       var isIE = navigator.userAgent.match(/MSIE/)!= null,
	           isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;
	           isIE10 = navigator.userAgent.match(/MSIE 10.0/)!= null;
	       if(isIE && !isIE10) {
	          file.select();
	          var reallocalpath = document.selection.createRange().text;
	           // IE6浏览器设置img的src为本地路径可以直接显示图片
	           if (isIE6) {
	              pic.attr("src",reallocalpath);
	           }else{
	              // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现             
	              pic.css("filter","progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src=\"" + reallocalpath + "\")");
	              // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
	              pic.attr('src','data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==');             
	           }
	           addImg.hide();
	           deleteImg.show();
	       }else {
	          html5Reader(file,pic,addImg,deleteImg,setHeadImg);
	       }
	       if(!(className == 'HeadImg')){
	    	   if($('.' + className).length < 10){
		           var strVar = "";
		           strVar += "<div class=\"item import-pic ";
		           strVar += className + "\" style=\"float: left; margin: 2px 8px 8px 0px; display: inline\"><img class=\"addImg\" onclick=\"clickImg(this);\" src=\"<%=path%>/Static/Image/addImg.png\" /><input name=\"";
		           strVar += className + "\" type=\"file\" class=\"upload_input\" onchange=\"change(this,\'";
		           strVar += className + "\');\" /><div class=\"preBlock\"><img class=\"preview\" id=\"preview\" alt=\"\" name=\"pic\" width=\"190\" height=\"190\" /><\/div><img class=\"delete\" onclick=\"deleteImg(this)\" src=\"<%=path%>/Static/Image/delete.png\" /><\/div>";
		           $(file).parent().parent().append(strVar);
		       }
	       }
	  }
	//H5渲染
	function html5Reader(file,pic,addImg,deleteImg,setHeadImg){
	   var file = file.files[0];
	   var reader = new FileReader();
	   reader.readAsDataURL(file);
	   reader.onload = function(e){
	       pic.attr("src",this.result);
	   }
	   addImg.hide();
	   deleteImg.show();
	   setHeadImg.show();
	}
	function selectSelected(id,data) {
		$("#" + id + " option").each(function() {
			var option = $(this).val();
			if(option == data){
				$(this).attr("selected",true);
			}
		});
	}
	function selectRadio(id,data) {
		$("#" + id + " input").each(function() {
			var option = $(this).val();
			if(option == data){
				$(this).attr('checked', true);
			}
		});
	}

	function checkboxChecked(name,data) {
		var datas = data.split(",");
		$("input[name=" + name + "]").each(function() {
			var checkbox = $(this);
			var checkValue = checkbox.val();
			$.each(datas,function(index,value){
				if(checkValue == value){
					checkbox.attr("checked", true);
				}
			});
	    });
	}
	
	</script>
</body>
</html>
