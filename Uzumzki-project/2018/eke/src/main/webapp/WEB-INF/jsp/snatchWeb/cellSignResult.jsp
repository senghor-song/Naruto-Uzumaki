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
			<li class="sale-sub-menu">
				<a href="/WebRelease/snatch/cellSign">小区签到</a>
			</li>
			<li class="sale-sub-menu selected">
				<a href="/WebRelease/snatch/cellSignResult">签到结果</a>
			</li>
		</ul>
		<div class="cellSignTask">
			<input type="hidden" id="basePath" value="/snatch-web">
			<ul class="xms-common-search">
				<li class="xms-common-li">
				<input type="hidden" id="webId" value="">
				<input type="hidden" id="loginName" value="">
				<select class="xmsSelect select2-hidden-accessible" id="website-content" style="width:100px;" tabindex="-1" aria-hidden="true">
					<option>不限</option>
					<option value="103@_@13923734655@_@安居客新版">安居客新版 13923734655
							(深圳)
					</option>
				</select><span class="select2 select2-container select2-container--default" dir="ltr" style="width: 100px;"><span class="selection"><span class="select2-selection select2-selection--single" role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="0" aria-labelledby="select2-website-content-container"><span class="select2-selection__rendered" id="select2-website-content-container" title="不限">不限</span><span class="select2-selection__arrow" role="presentation"><b role="presentation"></b></span></span></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
				</li>
				<li class="xms-common-li">
					<select class="xmsSelect select2-hidden-accessible" id="failReason-content" style="width:100px;" tabindex="-1" aria-hidden="true">
						<option>签到结果</option>
						<option value="-1">不限</option>
						<option value="0">成功</option>
						<option value="1">失败</option>
					</select><span class="select2 select2-container select2-container--default" dir="ltr" style="width: 100px;"><span class="selection"><span class="select2-selection select2-selection--single" role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="0" aria-labelledby="select2-failReason-content-container"><span class="select2-selection__rendered" id="select2-failReason-content-container" title="签到结果">签到结果</span><span class="select2-selection__arrow" role="presentation"><b role="presentation"></b></span></span></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
				</li>
				<li class="xms-common-li">
					<input id="createTime" value="" class="Wdate xms-common-text manage_content_wd_default" type="text" onclick="WdatePicker({onpicked :function(dp){getCellSignTask();},
									minDate:'2016-06-15',maxDate:'%y-%M-%d'})" readonly="readonly" style="border:1px solid #64ac58;height:26px">
			 	</li>
			 	<li class="xms-common-li">
					<input class="xms-common-text manage_content_wd_120 select2-hidden-accessible" id="cell" type="text" placeholder="请输入小区名称" tabindex="-1" aria-hidden="true"><span class="select2 select2-container select2-container--default" dir="ltr" style="width: 146px;"><span class="selection"><span class="select2-selection select2-selection--single" role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="0" aria-labelledby="select2-cell-container"><span class="select2-selection__rendered" id="select2-cell-container"></span><span class="select2-selection__arrow" role="presentation"><b role="presentation"></b></span></span></span><span class="dropdown-wrapper" aria-hidden="true"></span></span>
				</li>
				<li class="xms-common-li">
					<button class="button button_primary button_pay" onclick="getCellSignTask()" style="margin:0px;width:106px;">查询</button>
				</li>
			</ul>
			<div id="cellSignTaskTable"><table class="h_list clear" width="100%" cellspacing="0" cellpadding="0s"><thead><tr><td>账号</td><td>小区名称</td><td>签到名次</td><td>签到时间</td><td>签到结果</td><td>败因</td></tr></thead><tbody><tr id="249267248"><td>13923734655</td><td>金地梅陇镇</td><td>16</td><td>2018-07-05 10:00:00</td><td>成功</td><td>正常</td></tr><tr id="249267248"><td>13923734655</td><td>融创海湾半岛</td><td>2</td><td>2018-07-05 10:00:00</td><td>成功</td><td>正常</td></tr><tr id="249054859"><td>13923734655</td><td>融创海湾半岛</td><td>2</td><td>2018-07-04 20:00:00</td><td>成功</td><td>正常</td></tr><tr id="249054859"><td>13923734655</td><td>金地梅陇镇</td><td>16</td><td>2018-07-04 20:00:00</td><td>成功</td><td>正常</td></tr><tr id="248802788"><td>13923734655</td><td>融创海湾半岛</td><td>2</td><td>2018-07-04 15:00:00</td><td>成功</td><td>正常</td></tr><tr id="248802788"><td>13923734655</td><td>金地梅陇镇</td><td>16</td><td>2018-07-04 15:00:00</td><td>成功</td><td>正常</td></tr></tbody></table><div class="cellSignBottom"><div class="float-r"><div id="cellSign-fanye" class="commom-fanye"></div></div></div></div>
		</div>
	</div>
</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/cellSign_c125f898e18d80326a3853ed343501f9.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.artDialog-chrome.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
</html>