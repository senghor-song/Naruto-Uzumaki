<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>房产易推房登陆页-房产中介公司营销管理的软件</title>
    <link href="<%=path%>/Static/Image/favicon.ico" rel="shortcut icon" type="image/x-icon">
	<link href="<%=path%>/Static/Css/favicon.css" rel="shortcut icon" type="image/x-icon" />
    <link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
    <link href='<%=path%>/Static/Css/bootstrap_cc68b783fbb356589b6d8b02fb3bee6a.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/respond_65fe5ca926df7bccfa49751f52e534be.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/login_b836716cd8c9b159e72ca380975900a9.css' rel='stylesheet' type='text/css'></link>
  </head>
<body >
<input id="basePath" value="" type="hidden"/>
  <div id="login-background">
	<div id="login-main">
		<div id="login-content">
			<div id="login-center">
				<form id="loginForm" method="post" class="form_body" >
	            	<ul>
	            		<li class="login-li">
	            			<span class="phone-icon login-tip"></span>
		            		<input type="text" name=tel id="phone" class="inputClass" placeholder="手机号码" value="13888888888"/>
	            		</li>
	                	<li class="login-li">
	                		<span class="userPwd-icon login-tip"></span>
		                	<input type="password" name="password" id="password" value="123456" class="inputClass" placeholder="密码"/>
		                	<div id="capsLockTip" class="speech-bubble speech-bubble-left">大写锁定已打开</div>	               
	                	</li>	
	                </ul>
	                <div>
	                	<a id="login_btn" onclick="login()"></a>
	                </div>
	                <div class="login-pass">
	                	<span class="float-l" style="display:block">
	                		<input type="checkbox" id="remPass" style="margin:0px 4px 0px"></input><label for="remPass" style="display:inline">记住密码</label>
	                	</span>
	                	<a href="/WebRelease/common/retrievePassword" class="float-r" target="_blank">找回密码</a>
	                	<a href="/WebRelease/common/register" class="float-r mr8" target="_blank">立即注册</a>
	                	<a href="/WebRelease/index/index" class="float-r mr8" target="_blank">返回主页</a>
	                </div>
					<div id="login-center-footer-btn">
						<a href="http://www.ekeae.com/" target="_blank">www.ekeae.com</a>
					</div>	
				</form>
			</div>
		
		  	<div id="contach" style="display:none"></div>
		</div>
	</div>
</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/constants_3318a849899f02bb3b56fc5c5f5b34eb.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/login_c11c19adcd1218023d8d057a326aa49b.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/placeholder.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/layer/layer.js' type='text/javascript'></script>
<script>
var redirect_url = '';
</script>
</body>
</html>
