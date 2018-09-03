<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>快速入门</title>
<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/member_514696a1abfdd2c166dd8033d5b3e8e2.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/tip-yellowsimple_d6a155759956acdb4b49b381d5bf5fbf.css' rel='stylesheet' type='text/css'></link>
</head>
<body ><div class="aui_state_focus" style="position: absolute; left: -9999em; top: 268px; width: auto; z-index: 1987;"><div class="aui_outer"><table class="aui_border"><tbody><tr><td class="aui_nw"></td><td class="aui_n"></td><td class="aui_ne"></td></tr><tr><td class="aui_w"></td><td class="aui_c"><div class="aui_inner"><table class="aui_dialog"><tbody><tr><td colspan="2" class="aui_header"><div class="aui_titleBar"><div class="aui_title" style="cursor: move;">消息</div><a class="aui_close" href="javascript:/*artDialog*/;">×</a></div></td></tr><tr><td class="aui_icon" style="display: none;"><div class="aui_iconBg" style="background: none;"></div></td><td class="aui_main" style="width: auto; height: auto;"><div class="aui_content" style="padding: 20px 25px;"><div class="aui_loading"><span>loading..</span></div></div></td></tr><tr><td colspan="2" class="aui_footer"><div class="aui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="aui_e"></td></tr><tr><td class="aui_sw"></td><td class="aui_s"></td><td class="aui_se" style="cursor: se-resize;"></td></tr></tbody></table></div></div>
<input type="hidden" id="basePath" value="">
<input type="hidden" id="dayTime" value="41">
<input type="hidden" id="oldPrice" value="350.0">
<input type="hidden" id="oldPayment" value="季付">
<input type="hidden" id="oldMembershipCode" value="AP_5396">
<input type="hidden" id="oldMembershipName" value="菁英版">
<input type="hidden" id="offerpriceId" value="96441">
<input type="hidden" id="isFree" value="true">
<input type="hidden" id="city" value="755">
<input type="hidden" id="subsidy" value="110.0">
<input type="hidden" id="payOfferInst" value="1">
<input type="hidden" id="paymentUser" value="AP_5396_季付">
<input type="hidden" id="payType" value="5">
<div id="saleHouse" class="saleHouse" style="min-height: 873px;">
	<div class="saleHouse-main">
		<ul class="sale-tab-sub">
			<li class="sale-sub-menu selected">
				<a href="javascript:">支付页面</a>
			</li>
		</ul>
		<div class="member" style="margin:8px">
			<div class="member-price">
				<div style="padding-top: 8px;">
						<input id="forSelf" type="radio" name="forUser" value="2032256" checked="checked" style="display: none;">
				</div>
				<div class="keyWordBox">
					<dl class="keyList"></dl>
				</div>
				<table class="h_list clear" width="100%" cellspacing="0" cellpadding="0s">
					<thead>
						<tr>
							<td>会员级别</td>
							<td>年付</td>
							<td>半年付</td>
							<td>季付</td>
						</tr>
					</thead>
					<tbody>
					   	<!-- <tr>
							<td><span class="membershipName">视频看房版</span></td>
							<td>
								<div>
								<input class="年付" name="paymentType" type="radio" id="AP_32206_年付" payment="年付" membershipcode="0" value="960.00" subsidy="0.00" offerpriceid="108131" largess="2" largessunit="月">
								<label for="AP_32206_年付">960.00</label>
									(<span style="color: #ff6600;">活动优惠：买1年送2月</span>)
								</div>
							</td>
							<td>
								<div>
									<input class="半年付" name="paymentType" type="radio" id="AP_32206_半年付" payment="半年付" membershipcode="1" value="580.00" subsidy="0.00" offerpriceid="108133" largess="1" largessunit="月">
									<label for="AP_32206_半年付">580.00</label>
									(<span style="color: #ff6600;">活动优惠：买半年宋1月</span>)
								</div>
							</td>
							<td>-</td>
						</tr> -->
					   	<tr>
							<td><span class="membershipName">菁英版</span></td>
							<td>
								<div>
								<input class="年付" name="paymentType" type="radio" id="AP_5396_年付" payment="年付" membershipcode="3" value="0.03" subsidy="280.00" offerpriceid="96437" largess="2" largessunit="月">
								<label for="AP_5396_年付">0.03</label>
									(<span style="color: #ff6600;">活动优惠：买1年送2月</span>)
								</div>
							</td>
							<td>
								<div>
								<input class="半年付" name="paymentType" type="radio" id="AP_5396_半年付" payment="半年付" membershipcode="4" value="0.02" subsidy="180.00" offerpriceid="96439" largess="1" largessunit="月">
								<label for="AP_5396_半年付">0.02</label>
									(<span style="color: #ff6600;">活动优惠：买半年送1月</span>)
								</div>
							</td>
							<td>
								<div>
								<input class="季付" name="paymentType" type="radio" id="AP_5396_季付" payment="季付" membershipcode="5" value="0.01" subsidy="110.00" offerpriceid="96441" largess="0" largessunit="月" checked="checked">
								<label for="AP_5396_季付">0.01</label>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="payment-tip">
					<div class="payment-tip-content" style="height: 78px;">
						<ul class="paymenttip-left">
							<li>当前套餐是"<span class="col-f60">${sessionScope.loginuser.masstype}</span>",您选择的套餐是"<span class="col-f60">青英版</span>",您所要支付的费用包括:
							</li>
							<li style="text-indent: 24px">续订费用:<span class="col-f60">240</span>元(从2018-08-16至2018-11-16的续订费用);
							</li>
							<li style="text-indent: 24px" class="col-f60">公司全员普及或团体采购，优惠价请联系销售QQ</li>
						</ul>
						<ul class="paymenttip-right">
							<li><span>个人支付:</span><span class="member-text">240</span>元/季</li>
							<li><input type="button" class="button button_primary button_pay float-r" value="下一步" id="payBtn"></li>
						</ul>
					</div>
				</div>
				<ul class="subsidy-tip">
					<li>补贴部分由易推房和公司总部统一核算。</li>
					<li>支付前请确保您是该公司的员工，在享受公司补贴的同时，公司有权限锁定该易推房账号。</li>
				</ul>
			<div class="member-desc">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>套餐信息</title>
<link href="<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css" rel="stylesheet" type="text/css">
<style>
.none{width: 20px; height: 20px; display: inline-block;background: url("//s.img.xms.4846.com/infoServer/payment/images/x.png");}
.has{width: 20px; height: 20px; display: inline-block;background: url("//s.img.xms.4846.com/infoServer/payment/images/gou.png");}
</style>


<div>
	<table class="h_list" width="100%" cellspacing="0" cellpadding="0s">
		<thead id="m_thead"><tr><td>套餐权限</td><td>视频看房版</td><td>菁英版</td><td>免费会员</td></tr></thead>
		<tbody id="m_tbody"><tr><td>每日推送房源上限</td><td>400</td><td>200</td><td>10</td></tr><tr><td>库存房源上限</td><td>400</td><td>200</td><td>50</td></tr><tr><td>克隆房源上限</td><td>999</td><td>999</td><td>6</td></tr><tr><td>单网站账号数</td><td>3</td><td>3</td><td>1</td></tr><tr><td>每日小区透视次数</td><td>10</td><td>20</td><td>0</td></tr><tr><td>每日秒录房源次数</td><td>无上限</td><td>无上限</td><td>3</td></tr><tr><td>云采集订阅条数</td><td>10</td><td>3</td><td>0</td></tr><tr><td>每日安居客手机推送次数</td><td>60</td><td>60</td><td>0</td></tr><tr><td>小区签到上限</td><td>10</td><td>10</td><td>0</td></tr><tr><td>每日推送视频房源上限</td><td>20</td><td>0</td><td>0</td></tr><tr><td>视频库存上限</td><td>80</td><td>0</td><td>0</td></tr><tr><td>每日秒录视频房源上限</td><td>20</td><td>0</td><td>0</td></tr><tr><td>每日重发视频房源上限</td><td>20</td><td>0</td><td>0</td></tr><tr><td>云刷新</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="none"></span></td></tr><tr><td>云发布</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="has"></span></td></tr><tr><td>绑定多账号</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="none"></span></td></tr><tr><td>小区透视</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="none"></span></td></tr><tr><td>秒录房源</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="has"></span></td></tr><tr><td>云采集</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="has"></span></td></tr><tr><td>房源秘书</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="none"></span></td></tr><tr><td>实勘真房源</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="none"></span></td></tr><tr><td>房源导入</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="none"></span></td></tr><tr><td>安居客精推</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="none"></span></td></tr><tr><td>是否验证账号</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="none"></span></td></tr><tr><td>ERP导入</td><td><span class="has"></span></td><td><span class="none"></span></td><td><span class="none"></span></td></tr><tr><td>房源订阅</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="none"></span></td></tr><tr><td>安居客手机标签</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="none"></span></td></tr><tr><td>预约重发</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="none"></span></td></tr><tr><td>多城市</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="none"></span></td></tr><tr><td>个性水印</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="none"></span></td></tr><tr><td>安居客小区签到</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="none"></span></td></tr><tr><td>房源搬家</td><td><span class="has"></span></td><td><span class="has"></span></td><td><span class="none"></span></td></tr><tr><td>视频房源</td><td><span class="has"></span></td><td><span class="none"></span></td><td><span class="none"></span></td></tr><tr><td>重发视频房源</td><td><span class="has"></span></td><td><span class="none"></span></td><td><span class="none"></span></td></tr><tr><td>考核提醒</td><td><span class="has"></span></td><td><span class="none"></span></td><td><span class="none"></span></td></tr></tbody>
	</table>
</div>
<script src="<%=path%>/Static/Js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="<%=path%>/Static/Js/map.js" type="text/javascript"></script>
	<script>
	$.ajax({
		url: "../ajax/membership/findMembershipInfo.do",
		type: "post",
		data: {
			membershipCode: 'AP_32206,AP_5396'
		},
		dataType: "json",
		success: function(rs) {
			var accumMap = new Map();
			var privilegeMap = new Map();
			var codeArr = [];
			var html = '<tr><td>套餐权限</td>';
			$.each(rs.memberships, function(i, m) {
				html += '<td>' + m.membershipName + '</td>';
				codeArr.push(m.membershipCode);
				$.each(m.accums, function(j, a) {
					accumMap.put(m.membershipCode + '_' + a.accumType, a);
				});
				$.each(m.privileges, function(k, p) {
					privilegeMap.put(m.membershipCode + '_' + p.privilegeCode, p);
				});
			});
			html += '</tr>';
			$('#m_thead').html(html);
			
			html = '';
			$.each(rs.accumCodes, function(i, a) {
				var trHtml = '';
				trHtml += '<tr>';
				trHtml += '<td>' + a.accumName + '</td>';
				var aFlag = false;
				$.each(codeArr, function(i, c) {
					var peakValue = 0;
					if (accumMap.get(c + '_' + a.accumType) == null) {
						if (a.accumType == 'DZH') {
							peakValue = 1;
						}
					} else {
						aFlag = true;
						if (accumMap.get(c + '_' + a.accumType).peakValue == 999999) {
							peakValue = '无上限';
						} else {
							peakValue = accumMap.get(c + '_' + a.accumType).peakValue;
						}
					}
					trHtml += '<td>' + peakValue + '</td>';
				});
				trHtml += '</tr>';
				if (aFlag) {
					html += trHtml;
				}
			});
			$.each(rs.privilegeCodes, function(i, p) {
				var trHtml = '';
				trHtml += '<tr>';
				trHtml += '<td>' + p.privilegeName + '</td>';
				var pFlag = false;
				$.each(codeArr, function(i, c) {
					trHtml += '<td>' + (privilegeMap.get(c + '_' + p.privilegeCode) == null
							? '<span class="none"></span>' : '<span class="has"></span>') + '</td>';
					if (privilegeMap.get(c + '_' + p.privilegeCode) != null) {
						pFlag = true;
					}
				});
				trHtml += '</tr>';
				if (pFlag) {
					html += trHtml;
				}
			});
			$('#m_tbody').html(html);
		}
	});
	</script>


			</div>
		</div>
	</div>
</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/map.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/mathUtil_1467fc23826ddcf3f42074cff60da098.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/member_6c01b4127b084e7262915ddaa16a4d8f.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.artDialog-chrome.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.poshytip.min.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/placeholder.js' type='text/javascript'></script>
<script>
$(function(){
	$("#AP_5396_季付").trigger("click");
});
if ('' != null && '' != '') {
	$("#forOther").attr("checked", true);
	$("#input-phone").attr("disabled", false);
	$("#input-phone").val('' + ' ' + '');
	$("#forOther").val('');
} else {
	$("#forSelf").attr("checked", true);
}
var couponInstId = '';
var minusYear = '0';
var minusHalfYear = '0';
var minusSeason = '0';
var minusMonth = '0';
</script>
</body>
</html>