<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>小区签到</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/cellSign_bc7286b60183c56acd6999f510406141.css' rel='stylesheet' type='text/css'></link>
</head>
<body>
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
												</div>
											</td>
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
	<div class="saleHouse" style="min-height: 842px;">
	<div class="saleHouse-main">
		<ul class="sale-tab-sub">
			<li class="sale-sub-menu selected">
				<a href="/WebRelease/snatch/cellSign">小区签到</a>
			</li>
			<li class="sale-sub-menu">
				<a href="/WebRelease/snatch/cellSignResult">签到结果</a>
			</li>
		</ul>
		<div class="cellSign">
			<input id="basePath" type="hidden" value="/snatch-web">
			<input id="cellCode" type="hidden" value="">
			<input id="cellName" type="hidden" value="">
			<input id="coordx" type="hidden" value="">
			<input id="coordy" type="hidden" value="">
			<ul class="sale-content-tip">
				<li>
					<span class="sale-tip-img"></span>
					<span>贴心提示:</span>
					<span class="sale-tip-colse"></span>
				</li>
				<li>1.帮您在移动经纪人手机端进行小区签到。</li>
				<li>2.您可以选择移动经纪人上的任意小区，不局限地理位置。</li>
				<li>3.每个账号最多设置<span class="col-f60 font-bold" id="peakValue">10</span>个小区(不限位置)。</li>
				<li class="col-f60 font-bold">4.为了您设置的小区能及时签到，请在10:00，15:00，20:00前30分钟设置好小区</li>
			</ul>
			
				
					<div class="cellSignCondition">
						<ul class="xms-common-search" style="height:36px">
							<li class="xms-common-li">
								<ul class="xms-common-searchul" id="userweb-content">
									<li class="xms-common-searchli"><span class="manage_wd_160 userweb_span">安居客新版 13923734655</span><i></i></li>
									<li class="xms-common-position">
										<input type="hidden" id="loginName" value="13923734655">
										<input type="hidden" id="city" value="755">
										<input type="hidden" id="webId" value="103">
										<ul class="xms-common-searchcontent manage_content_wd_160 userweb-content" style="display:none">
											
												<li title="13923734655">
													<span class="userWeb-loginName max_width_150 tableOverflow float-l" city="755" webid="103" loginname="13923734655">安居客新版 13923734655</span>
													
														<span class="float-r">(深圳)</span>
													
												</li>
											
										</ul>
									</li>
								</ul>
							</li>
							<li class="xms-common-li">
								<input class="xms-common-text iw200" id="cellNameInput" name="cellName" type="text" placeholder="请输入小区" onkeyup="getCellsByInput(this)">
								<div class="bP_keyWordBox clear"><dl class="bP_keyList"></dl></div>
							</li>
							<li class="xms-common-li">
								<button class="button button_primary button_pay" onclick="addAnjukeCellSing()" style="margin:0px">添加小区</button>
							</li>
							<li class="xms-common-li">
								<button class="button button_primary button_pay" onclick="getMyCell()" style="margin:0px">获取我的小区</button>
							</li>
<!-- 							<li class="xms-common-li"> -->
<!-- 								<button class="button button_primary button_pay" onclick="testAnjukeCellSign()" style="margin:0px">发送任务</button> -->
<!-- 							</li> -->
							<li class="clear"></li>
						</ul>
					</div>
				
				
			
			<div id="myWebCell" class="clear"></div>			
			<div id="cellSignTable" class="clear mt8"><fieldset><legend>自动签到的小区(最多可以设置10个小区)</legend><div class="noData">您没有设置签到小区</div></fieldset></div>
		</div>
	</div>
</div>	
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/cellSign_c125f898e18d80326a3853ed343501f9.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.artDialog-chrome.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
</html>