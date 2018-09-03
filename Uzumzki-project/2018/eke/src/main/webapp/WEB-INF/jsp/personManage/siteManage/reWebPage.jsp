<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../../common/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>站点管理</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/siteManage_2864980e527d234c6433a0ad34fd0ccf.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/main_a541ef7359aad950c46de2d36c405a9d.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/introjs_03b43e32a5901af129087d7ae282fea4.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/tip-yellowsimple_d6a155759956acdb4b49b381d5bf5fbf.css' rel='stylesheet' type='text/css'></link>
  </head>
<body >
<div class="reweb">
		<span class="retitle">向我们推荐您需要的发布或采集网站：<span class="col-f60">(只要符合要求的网站，我们会在近期给您加到网站列表里面)</span></span>
		<form id="bForm" method="post">
			<ul class="reweb-content">
				<li>
					<span class="mr8"><input type="radio" id="release" name="type" checked="checked" value="0"></input><label for="release">发布</label></span>
					<span><input type="radio" id="collect" name="type" value="1"></input><label for="collect">采集</label></span>
				</li>
				<li><span class="re-left">网站名称：</span><span class="re-right"><input id="webName" type="text" name="sitename"></input></span></li>
				<li><span class="re-left">网站地址：</span><span class="re-right"><input id="webUrl" type="text" name="siteurl"></input></span></li>
				<li><span class="re-left">网站账号：</span><span class="re-right"><input id="webAccount" name="account" type="text"></input></span></li>
				<li><span class="re-left">网站密码：</span><span class="re-right"><input id="webPassword" name="pwd" type="text"></input></span></li>
				<li>
					<span class="re-left">补充说明：</span><span><textarea id="retextarea" name="mes"></textarea></span>
				</li>
			</ul>
			<div class="clear footer-button">
				<input value="提交" class="btn-saveRe" type="button" onclick="addReweb()"></input>
			</div>
		</form>
	</div>
<script>
var autoFlag = true;
function addReweb(){
	if (checkInput()) {
		$.ajax({
			url: "/WebRelease/siteManage/saveReWebPage",
			type : "post",
			data : $("#bForm").serialize(),
			dataType: 'json',
			beforeSend: function(XMLHttpRequest) {
				top.loadingShow();
				autoFlag = false;
			},
			success: function(data) {
				art.dialog.tips("提交成功，谢谢您的建议,我们会尽快安排。");
				document.getElementById("bForm").reset(); 
			},
			complete:function(req, msg) {
				top.loadingHide();
				autoFlag = true;
			}
		});
	}
}

function jsonpCallback(data){
	art.dialog.tips("提交成功，谢谢您的建议,我们会尽快安排。");
	document.getElementById("bForm").reset(); 
}
  
function checkInput(){
	if ($("#webName").val() == "") {
		art.dialog.tips("网站名称不能为空");
		$("#webName").focus();
		return false;
	}
	
	if ($("#webName").val().length > 10) {
		art.dialog.tips("网站名称不能大于10个字");
		$("#webName").focus();
		return false;
	}
	
	if ($("#webUrl").val() == "") {
		art.dialog.tips("网站地址不能为空");
		$("#webUrl").focus();
		return false;
	}
	
	var reType = $("input[name=reType]:checked").val();
	if (reType == 0) {
		if ($("#webAccount").val() == "") {
			art.dialog.tips("网站账号不能为空");
			$("#webAccount").focus();
			return false;
		}
		
		if ($("#webPassword").val() == "") {
			art.dialog.tips("网站密码不能为空");
			$("#webPassword").focus();
			return false;
		}
	}
	return true;
}
</script>
</body>
</html>