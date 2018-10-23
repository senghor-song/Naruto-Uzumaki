<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/WebBackAPI/admin/static/css/site.css">
    <style>
        body .login0{background:#e9ecef}
        body .login1{width:300px;background:#fff;border-radius:5px}
        body .login2{width:300px;height:80px;border-bottom:1px solid #ececec}
        body .login3{font-size:24px;top:28px;position:relative}
        body .login4{width:300px;}
        body .login5{line-height:30px}
        body .login6{width:60%;float:left}
        body .login7{font-weight:normal;margin-top:7px;margin-left:15px}
        body .login8{margin-bottom:1px;line-height:44px;height:44px}
        body .login10{position:relative;top:5px;left:12px}
        body .login11{clear:both}
        body .login13{margin:17px;position:relative;top:8px}
        body .login14{margin-top:21px}
        body .login15{margin-top:20px}
        body .login17{color:#222}
    </style>
</head>

<body class="h-100 login0" style="background: url(/WebBackAPI/admin/static/image/bg2.jpg) no-repeat center center;">
    <div class="login-box shadow-lg h-100 login1">
        <div id="wxQRcode" class="login4" >
        </div>
    </div>

    <!-- jQuery -->
    <script src="/WebBackAPI/admin/static/plugins/jquery/jquery.min.js"></script>
    <script src="/WebBackAPI/admin/static/plugins/verify/js/verify.min.js"></script>
    <script src="/WebBackAPI/admin/static/js/jqBootstrapValidation-1.3.7.min.js"></script>
    <script src="/WebBackAPI/admin/static/js/jq-ext.js"></script>
    <script src="/WebBackAPI/admin/static/plugins/layer/layer.js"></script>
    <script src="https://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
    <script>
		$(function() {
			var obj = new WxLogin({
				self_redirect : false,
				id : "wxQRcode",
				appid : "wx57555504fc7f9fd2",
				scope : "snsapi_login",
				redirect_uri : "https://ball.ekeae.com/WebBackAPI/admin/common/authLogin",
				state : "",
				style : "black",
				href : ""
			});
		});
	</script>
</body>

</html>