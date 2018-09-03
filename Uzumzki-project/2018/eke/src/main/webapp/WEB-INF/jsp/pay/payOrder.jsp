<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>流水记录</title>
<link href="<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/Static/Css/member_514696a1abfdd2c166dd8033d5b3e8e2.css" rel="stylesheet" type="text/css">
	<link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>
</head>
<body><div class="aui_state_focus" style="position: absolute; left: -9999em; top: 268px; width: auto; z-index: 1987;"><div class="aui_outer"><table class="aui_border"><tbody><tr><td class="aui_nw"></td><td class="aui_n"></td><td class="aui_ne"></td></tr><tr><td class="aui_w"></td><td class="aui_c"><div class="aui_inner"><table class="aui_dialog"><tbody><tr><td colspan="2" class="aui_header"><div class="aui_titleBar"><div class="aui_title" style="cursor: move;">消息</div><a class="aui_close" href="javascript:/*artDialog*/;">×</a></div></td></tr><tr><td class="aui_icon" style="display: none;"><div class="aui_iconBg" style="background: none;"></div></td><td class="aui_main" style="width: auto; height: auto;"><div class="aui_content" style="padding: 20px 25px;"><div class="aui_loading"><span>loading..</span></div></div></td></tr><tr><td colspan="2" class="aui_footer"><div class="aui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="aui_e"></td></tr><tr><td class="aui_sw"></td><td class="aui_s"></td><td class="aui_se" style="cursor: se-resize;"></td></tr></tbody></table></div></div>
<div id="saleHouse" class="saleHouse" style="min-height: 873px;">
	<div class="saleHouse-main">
		<ul class="sale-tab-sub">
			<li class="sale-sub-menu selected">
				<a href="order.do">续费订单</a>
			</li>
		</ul>
			
		<div class="member-desc" style="margin:0 8px">
			<!-- 续费流水-->
			<table class="h_list clear" width="100%" cellspacing="0" cellpadding="0s">
				<thead>
					<tr>
						<td>订单时间</td>
						<td>购买会员</td>
						<td>充值账号</td>
						<td>购买时间</td>
						<td>预计到期时间</td>
						<td>支付方式</td>
						<td>订单金额</td>
						<td>需付金额</td>
						<td>下单用户</td>
						<td>支付状态</td>
					</tr>
				</thead>
				<tbody id="descTable">
					<c:forEach items="${page.list}" var="list">
						<tr>
							<td><fmt:formatDate value="${list.paytime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td><span class="tbable80 tableOverflow" title="${list.type}">${list.type}</span></td>
							<td>${sessionScope.loginuser.tel}</td>
							<td>${list.buyduration}</td>
							<td><fmt:formatDate value="${list.endtime}" pattern="yyyy-MM-dd"/></td>
							<td>${list.paytype == 0 ? '微信' : '支付宝'}</td>
							<td>${list.payset}</td>
							<td>${list.payact}</td>
							<td>${sessionScope.loginuser.emp}</td>
							<td>
								<c:if test="${list.payresult == 0}">
									<a class="more_details" onclick="cancelOrder('${list.id}', this)">取消</a>
								</c:if>
								<c:if test="${list.payresult != 0}">
									${list.payresult == 1 ? '支付成功' : list.payresult == 2 ? '订单取消' : '订单超时'}
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="float-r">
				<div id="saleManager-fanye" class="commom-fanye "></div>
			</div>
		</div>
	</div>
</div>
<script src="<%=path%>/Static/Js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=path%>/Static/Js/plugins/artDialog/skins/chrome.css">
<script src="<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js?skin=chrome" type="text/javascript"></script>
<script src="<%=path%>/Static/Js/plugins/artDialog/plugins/iframeTools.source.js" type="text/javascript"></script>
<script src='<%=path%>/Static/Js/jquery.paginate.js' type='text/javascript'></script>
<script>
	if ('${page.pages}' != null && '${page.pages}' != 0) {
		/*翻页控件*/
		$("#saleManager-fanye").paginate({
			count 		: '${page.pages}',
			start 		: '${page.pageNum}',
			display     : 10,
			border					: false,
			text_color  			: '#50b63f',
			text_hover_color		: '#fff',
			background_color    	: '#fff',	
			background_hover_color	: '#50b63f',
			images		: false,
			mouse		: 'click',
			onChange	: function(){
				$("#pageNow").val($(".jPag-current").html());
	            window.location = "/WebRelease/pay/payOrder?pageNum="+$(".jPag-current").html();
			}
		});
	}
	function cancelOrder(orderId, thisObj) {
		$.ajax({
			url: '/WebRelease/pay/delOrder',
			data: {
				orderId: orderId
			},
			dataType: 'json',
			success: function(rs) {
				art.dialog.tips('取消订单成功', 3);
				$(thisObj).parent().html('订单取消');
			},
			error: function(jqXHR) {
				art.dialog($.parseJSON(jqXHR.responseText).msg);
			}
		});
	}
</script>
</body>
</html>