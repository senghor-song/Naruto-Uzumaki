<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>微信支付页面</title>
<link href="<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/Static/Css/member_514696a1abfdd2c166dd8033d5b3e8e2.css" rel="stylesheet" type="text/css">
<script src="<%=path%>/Static/Js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="<%=path%>/Static/Js/jquery.qrcode.min.js" type="text/javascript"></script>
</head>
<body style="overflow:hidden">
<input type="hidden" id="basePath" value="">
	<div class="xm_main">
		<input type="hidden" id="param" value="weixin://wxpay/bizpayurl?pr=XjEp5eH ">
		<div class="topbar">
		    <div class="topbar-wrap">
		        <span class="topbar-link-first">您好，欢迎使用微信付款！</span>
		    </div>
		</div>
		<div class="header">
			<div class="header-wrap">
				<div class="wx-logo"><img src="//s.img.xms.4846.com/infoServer/payment/images/bank_wx_1.png"></div>
				<div class="wx-title">我的收银台</div>
			</div>
		</div>
		<div class="container">
			<div class="container-header">
				<ul class="account-info float-l">
					<li>正在使用即时到账交易</li>
					<li><span>套餐:AP_5396</span><span class="ml8">收款方：*志刚</span></li>
				</ul>
				<div class="float-r"><span class="account-fee">240</span>元</div>
			</div>
			<div class="container-content">
				<ul class="wx-1">
					<li>扫一扫付款（元）</li>
					<li class="account-fee">240</li>
				</ul>
				<div class="qrcode-img-wrapper">
					<div id="output"></div>
		        	<div class="qrcode-tip">
		        		<img class="qrcode-img" src="//s.img.xms.4846.com/infoServer/payment/images/wx-pay.png" alt="扫一扫标识">
			        	<div class="float-l ml8">打开手机微信<br>扫扫继续付款</div>
		        	</div>
		   		</div>
			</div>
		</div>
		<div class="main_bottom clear">
			 <!-- 底部 -->
			版权所有：易推房科技 &nbsp;&nbsp;&nbsp;&nbsp;电话：400-044-0408&nbsp;&nbsp;&nbsp;&nbsp; 地址：厦门软件园观日路40号3楼
		</div>
	</div>
	<script type="text/javascript">
		jQuery(function(){
	
			jQuery('#output').qrcode('${QRCode}');
	
		})
	</script>

</html>