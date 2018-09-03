<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>支付确认页面</title>
<link href="<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/Static/Css/member_514696a1abfdd2c166dd8033d5b3e8e2.css" rel="stylesheet" type="text/css">
</head>
<body><div class="" style="display: none; position: absolute;"><div class="aui_outer"><table class="aui_border"><tbody><tr><td class="aui_nw"></td><td class="aui_n"></td><td class="aui_ne"></td></tr><tr><td class="aui_w"></td><td class="aui_c"><div class="aui_inner"><table class="aui_dialog"><tbody><tr><td colspan="2" class="aui_header"><div class="aui_titleBar"><div class="aui_title" style="cursor: move;"></div><a class="aui_close" href="javascript:/*artDialog*/;">×</a></div></td></tr><tr><td class="aui_icon" style="display: none;"><div class="aui_iconBg" style="background: none;"></div></td><td class="aui_main" style="width: 400px; height: 150px;"><div class="aui_content" style="padding: 20px 25px;"></div></td></tr><tr><td colspan="2" class="aui_footer"><div class="aui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="aui_e"></td></tr><tr><td class="aui_sw"></td><td class="aui_s"></td><td class="aui_se" style="cursor: se-resize;"></td></tr></tbody></table></div></div>
<div id="saleHouse" class="saleHouse" style="min-height: 873px;">
	<div class="saleHouse-main">
		<ul class="sale-tab-sub">
			<li class="sale-sub-menu selected">
				<a href="javascript:">支付确认页面</a>
			</li>
		</ul>
		<div class="payment">
			<input type="hidden" value="" id="basePath">
			<input type="hidden" value="3600" id="timeout">
			<h5>请在确认您的支付信息无误后,开始支付.</h5>
			<form name="alipayment" action="/WebRelease/pay/alipay" onsubmit="return checkForm();" method="post" target="_blank" id="payForm">
			<div class="payment-content">
				<input name="membershipCode" type="hidden" value="AP_5396">
				<input name="forUserId" type="hidden" value="2032256">
				<input name="totalPrice" type="hidden" value="680.00">
				<input name="payPrice" type="hidden" value="679.86">
				<input name="midouPrice" type="hidden" value="0">
				<input name="couponInstId" type="hidden" value="">
				<input name="priceId" type="hidden" value="96437">
				<input name="payment" type="hidden" value="年付">
				<input type="hidden" name="uuid" value="${uuid }">
				<input type="hidden" name="payType" value="${payType }">
				<div class="payment-list">
					<div class="payment-list-header">
						<span>订单信息</span>
					</div>
					<table class="pt_list">
						<tbody>
							<tr>
								<td>账号</td>
								<td>姓名</td>
								<td>选择套餐</td>
								<td>当前账号有效期</td>
								<td>购买期限</td>
							</tr>
							<tr>
								<td>${sessionScope.loginuser.tel}</td>
								<td>${sessionScope.loginuser.emp}</td>
								<td>${massType}</td>
								<td><fmt:formatDate value="${sessionScope.loginuser.massvalidity}" pattern="yyyy-MM-dd"/></td>
								<td>${massvalidity}</td>
							</tr>
							<tr>
								<td colspan="6" style="text-align:right">
									<span>订单金额：<span class="col-f60 font-bold" id="payOrderPrice">${price}</span>元</span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
					
				<div class="payment-list">
					<div class="payment-list-header">
						<span>支付方式</span>
					</div>
					<table class="pt_list">
						
						<tbody><tr>
							<td>
								<div class="float-l">
									<input type="radio" checked="checked" class="paymentType" name="paymentType" value="1" id="bank_zfb" style="margin:15px 5px"
									onclick="changeForm('/WebRelease/pay/alipay')">
									<label for="bank_zfb" class="float-l"><img src="//s.img.xms.4846.com/infoServer/payment/images/bank_zfb.gif" height="40" width="100"></label>
									<span style="line-height:43px;float:left;margin-left:8px"></span>
								</div>
							</td>
						</tr>
						
						<tr id="wx_payment">
							<td>
								<div style="float: left;">
									<input type="radio" class="paymentType" name="paymentType" value="0" id="bank_wx" style="margin:15px 5px"
									onclick="changeForm('/WebRelease/pay/payWXVerify')">
		 							<label for="bank_wx" class="float-l"><img src="//s.img.xms.4846.com/infoServer/payment/images/bank_wx.png" height="40" width="100"></label> 
									<span style="line-height:43px;float:left;margin-left: 8px;"></span>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<button id="payNow" class="float-r button button_primary button_pay" type="submit">立即支付</button>
							</td>
						</tr>
					</tbody></table>
				</div>
			</div>
			</form>
			<ul class="sale-content-tip">
				<li><span class="sale-tip-img"></span><span>贴心提示:</span></li>
				<li>1.支付成功以后,系统会自动延长账号的使用期;</li>
				<li>2.若在支付的过程中遇到问题,及时联系我们的客服,谢谢合作！</li>
			</ul>
		</div>
	</div>
</div>
<script src="<%=path%>/Static/Js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="<%=path%>/Static/Js/mathUtil_1467fc23826ddcf3f42074cff60da098.js" type="text/javascript"></script>
<script src="<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js" type="text/javascript"></script>
<script src="<%=path%>/Static/Js/payment_4be68a12d94b8b341f53ce00cbac4f91.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=path%>/Static/Js/plugins/artDialog/skins/chrome.css">
<script src="<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js?skin=chrome" type="text/javascript"></script>
<script src="<%=path%>/Static/Js/iframeTools.source.js" type="text/javascript"></script>
<script>
	function changeForm(url){
        $("#payForm").attr('action',url);    //通过jquery为action属性赋值
	}
</script>
</body>
</html>