<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>房产易推房登陆页-房产中介公司营销管理的软件</title>
<link href="<%=path%>/Static/Image/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/bootstrap_cc68b783fbb356589b6d8b02fb3bee6a.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/respond_65fe5ca926df7bccfa49751f52e534be.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/forgotPass_5a4756ac1800a8d0764d6d4bb3eee087.css' rel='stylesheet' type='text/css'></link>
</head>
<body>
<div class="forgotPass-bg">
	<input id="basePath" value='' type="hidden"/>
	<div class="forgotPass">
		<div class="returnIndex-div">
			<span class="returnIndex"><a href="//xms.4846.com/loginPage.do">登录</a></span>
		</div>
		<div class="main">
			<form id="forgotPwdForm" method="post">
				<input id="isphone" name="isphone" value="0" type="hidden">
				<ul class="form">
					<li>
						<input type="text" class="regInputPHP inputw160 float-l" id="phone" name="phone" onkeyup="checkPhone()" title="易推房账号"></input>
						<a href="javascript:getToken()" class="link_a float-r" id="link_a">获取验证码</a>
					</li>
					<li>
						<input type="text" class="regInputPHP inputw334" id="verCode" name="token" title="验证码"></input>
					</li>
					<li>
						<input type="text" class="regInputPHP inputw334" id="passwordtext" title="密码"></input>
						<input type="password" class="regInputPHP inputw334" id="password" name="password" style="display:none"></input>
					</li>
					<li>
						<input type="text" class="regInputPHP inputw334" id="enterPasswordtext" title="确认密码"></input>
						<input type="password" class="regInputPHP inputw334" id="enterPassword" style="display:none"></input>
					</li>
					<li style="height:45px;line-height:45px;margin-top:60px">
						<a href="javascript:forgotPass()" class="btnsubmit"></a>
					</li>
				</ul>
			</form>
		</div>
	</div>
	<div id="contach" style="display:none"></div>
</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.timers-1.2.js' type='text/javascript'></script>
<script src="<%=path%>/Static/Js/layer/layer.js" type="text/javascript"></script>
<script src='<%=path%>/Static/Js/forgotPass_1e79e3ab232cde6aba4e8de9ac51be86.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/plugins/artDialog/plugins/iframeTools.source.js' type='text/javascript'></script>
</body>

</html>