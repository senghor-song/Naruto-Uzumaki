<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>房产易推房登陆页-房产中介公司营销管理的软件</title>
    <link href="<%=path%>/Static/Image/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <link href="<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css" rel="stylesheet" type="text/css">
    <link href="<%=path%>/Static/Css/bootstrap_cc68b783fbb356589b6d8b02fb3bee6a.css" rel="stylesheet" type="text/css">
    <link href="<%=path%>/Static/Css/respond_65fe5ca926df7bccfa49751f52e534be.css" rel="stylesheet" type="text/css">
	<link href='<%=path%>/Static/Css/registerPC_d9f137f6d97503db8b67fa0a44b3f422.css' rel='stylesheet' type='text/css'></link>
    
</head>
<body><div class="" style="display: none; position: absolute;"><div class="aui_outer"><table class="aui_border"><tbody><tr><td class="aui_nw"></td><td class="aui_n"></td><td class="aui_ne"></td></tr><tr><td class="aui_w"></td><td class="aui_c"><div class="aui_inner"><table class="aui_dialog"><tbody><tr><td colspan="2" class="aui_header"><div class="aui_titleBar"><div class="aui_title" style="cursor: move;"></div><a class="aui_close" href="javascript:/*artDialog*/;">×</a></div></td></tr><tr><td class="aui_icon" style="display: none;"><div class="aui_iconBg" style="background: none;"></div></td><td class="aui_main" style="width: auto; height: auto;"><div class="aui_content" style="padding: 20px 25px;"></div></td></tr><tr><td colspan="2" class="aui_footer"><div class="aui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="aui_e"></td></tr><tr><td class="aui_sw"></td><td class="aui_s"></td><td class="aui_se" style="cursor: se-resize;"></td></tr></tbody></table></div></div>
<div class="register-bg">
	<input id="basePath" value="" type="hidden">
	<input id="basePath" value="0" name="regFrom" type="hidden">
	<input id="isphone" name="isphone" value="0" type="hidden">
	<input id="cityname" name="cityname" value="" type="hidden">
	<div class="registerForPHP" style="height: 945px;">
		<div class="returnIndex-div">
			<span class="returnIndex"><a href="/WebRelease/common/login">返回首页</a></span>
		</div>
		<div class="main">
			<form id="regForm" method="post">
				<ul class="form">
					<li>
						<input type="text" class="regInputPHP inputw160 float-l" id="phone" name="tel" onkeyup="checkPhone(1)" title="手机号码" value="15207108156">
						<a href="javascript:getToken()" class="link_a noEffective float-r" id="link_a">获取验证码</a>
					</li>
					<li>
						<input type="text" class="regInputPHP inputw334" id="verCode" name="token" title="验证码" value="123456">
					</li>
					<li>
						<input type="text" class="regInputPHP inputw334" id="trueName" name="emp" title="姓名" value="宋高俊">
					</li>
					<li>
						<input type="text" class="regInputPHP inputw334 password" id="passwordtext" title="密码" value="abc123456">
						<input type="password" class="regInputPHP inputw334 password" id="password" name="password" style="display:none" value="abc123456">
					</li>
					<li>
						<input type="text" class="regInputPHP inputw334 enterPassword" id="enterPasswordtext" onblur="checkPwd()" title="确认密码" value="abc123456">
						<input type="password" class="regInputPHP inputw334 enterPassword" id="enterPassword" onblur="checkPwd()" style="display:none"  value="abc123456">
					</li>
					<li>
						<select name="cityid" id="cityid" class="regInputPHP inputw160 float-l">
							<c:forEach items="${citys }" var="list">
								<option value="${list.id}" >${list.city }</option>
							</c:forEach>
						</select>
						<div class="float-r dropDown">
							<input id="store" type="text" class="regInputPHP inputw160" name="store" title="公司" onkeyup="RegisterPc.searchCompany()">
							<input id="storeid" name="storeid" type="hidden" value="">
							<dl class="dropDown-dl" id="dropDown-company" style="left: 0px;"></dl>
						</div>
					</li>
					<li> </li>
					<li> </li>
					<li style="height:36px;line-height:36px;margin-top:32px">
						<a href="javascript:register()" class="btnlogin"></a>
					</li>
				</ul>
			</form>
		</div>
	</div>
</div>
<script src="<%=path%>/Static/Js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="<%=path%>/Static/Js/layer/layer.js" type="text/javascript"></script>
<script src="<%=path%>/Static/Js/jquery.timers-1.2.js" type="text/javascript"></script>
<script src="<%=path%>/Static/Js/register_702aa8b5b43c2f9e1c888fd3904b0415.js" type="text/javascript"></script>
<script src="<%=path%>/Static/Js/registerPC_cc8a9a274eb1cb7e9a9b9daa8b4537da.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=path%>/Static/Js/plugins/artDialog/skins/chrome.css">
<script src="<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js" type="text/javascript"></script>
<script src="<%=path%>/Static/Js/plugins/artDialog/plugins/iframeTools.source.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$("#cityid").change(function(){
        	citySelect();
        });
        citySelect();
        function citySelect(select){
        	$("#cityname").val($("#cityid option:selected").text());
        }
	});
</script>
</body>
</html>