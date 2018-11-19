<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="/WebBackAPI/admin/static/image/logo.png" type="image/x-icon"/>
    <link rel="stylesheet" href="/WebBackAPI/admin/static/css/site.css">
    <style>
        body .login0{background:#e9ecef}
        body .login1{background:#fff;border-radius:5px;}
        body .login4{width:300px;margin-top: -68px; margin-left: -15px;}
        .login-wrap{width: 270px; height: 260px; overflow: hidden; transform: scale(0.9);}
	    #date{ color: #fff; position: fixed; bottom: 2%; right: 47%; }
    </style>
</head>

<body class="h-100 login0" style="background: url(/WebBackAPI/admin/static/image/bg2.jpg) no-repeat center center;" onmousedown="holdDown()" onmouseup="holdUp()">
    <div class="login-box shadow-lg h-100 login1" style="margin: 13% auto !important;width: 269px;">
        <div class="login-wrap">
	        <div id="wxQRcode" class="login4">
	        </div>
        </div>
    </div>
    <span id="date">${dateFlag}</span>

    <!-- jQuery -->
    <script src="/WebBackAPI/admin/static/plugins/jquery/jquery.min.js"></script>
    <script src="/WebBackAPI/admin/static/plugins/verify/js/verify.min.js"></script>
    <script src="/WebBackAPI/admin/static/js/jqBootstrapValidation-1.3.7.min.js"></script>
    <script src="/WebBackAPI/admin/static/js/jq-ext.js"></script>
    <script src="/WebBackAPI/admin/static/plugins/layer/layer.js"></script>
    <script src="https://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
    <script>
		$(function() {
			var message = "${message}";
			if(message != ""){
				layer.confirm(message, {
        			btn: ['确定'] //按钮
        		});
			}
			var obj = new WxLogin({
				self_redirect : false,
				id : "wxQRcode",
				appid : "wxa6c95fe48935f880",
				scope : "snsapi_login",
				redirect_uri : "https://ball.ekeae.com/WebBackAPI/admin/common/authLogin",
				state : "",
				style : "black",
				href : ""
			});
		});
		var timeStart, timeEnd, time;//申明全局变量
		
		function getTimeNow()//获取此刻时间
		{
			var now = new Date();
			return now.getTime();
		}
		
		function holdDown()//鼠标按下时触发
		{
			timeStart = getTimeNow();//获取鼠标按下时的时间
			time = setInterval(function()//setInterval会每100毫秒执行一次
			{
				timeEnd = getTimeNow();//也就是每100毫秒获取一次时间
				if (timeEnd - timeStart > 1000)//如果此时检测到的时间与第一次获取的时间差有1000毫秒
				{
					clearInterval(time);//便不再继续重复此函数 （clearInterval取消周期性执行）
            		window.location.href="/WebBackAPI/admin/common/register";
				}
			}, 100);
		}
		function holdUp() {
			clearInterval(time);//如果按下时间不到1000毫秒便弹起，
		}
	</script>
</body>

</html>