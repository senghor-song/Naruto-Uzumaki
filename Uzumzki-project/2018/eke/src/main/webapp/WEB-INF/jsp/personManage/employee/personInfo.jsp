<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../../common/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>我的易推房--房产易推房</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/lightbox.min.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/xmsSelect_0687a4d524bc0ea4a5a2ff201f642e0a.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/personInfo_716f3c480b3be948522f4876b329690d.css' rel='stylesheet' type='text/css'></link>
  </head>
<body >
<input type="hidden" id="basePath" value=""></input>
	<input type="hidden" id="orgId" value="755012005"></input>
	<div class="saleHouse">
		<div class="saleHouse-main">
			<div class="main_hd">
				<ul class="sale-tab-sub">
					<li class="sale-sub-menu selected" id="personInfo">
						<a href="javascript:">个人资料</a>
					</li>
					<li class="sale-sub-menu" id="personPass">
						<a href="javascript:">修改密码</a>
					</li>
					<li class="sale-sub-menu" id="checkChange">
						<a href="javascript:">变更记录</a>
					</li>
				</ul>
			</div>
			
			<!-- 经纪人信息 -->
			<div class="personInfo tab">
				<div class="personInfoTab">
					<h5>经纪人信息</h5>
					<ul class="personInfo-ul">
						<li style="border-top-width:0">
							<span class="personInfo-left">真实姓名</span>
							<span class="personInfo-center">${sessionScope.loginuser.emp}</span>
							<a class="float-r" href="javascript:modifyTrueName()">修改</a>
						</li>
						<li>
							<span class="personInfo-left">手机号码</span>
							<span class="personInfo-center">${sessionScope.loginuser.tel}</span>
							<a class="float-r" href="javascript:modifyPhone()">修改</a>
						</li>
						<li>
							<span class="personInfo-left">所属门店</span>
							<span class="personInfo-center">${sessionScope.loginuser.empStore.empstore}</span>
							<a class="float-r" href="javascript:applyChange('org')">申请变更</a>
						</li>
						<%-- <li>
							<span class="personInfo-left">所属公司</span>
							<span class="personInfo-center">${sessionScope.loginuser.empStore.empstore}</span>
							<a class="float-r" href="javascript:applyChange('company')">申请变更</a>
						</li> --%>
					</ul>
				</div>
				<ul class="sale-content-tip">
					<li>
						<span class="sale-tip-img"></span>
						<span>贴心提示:</span>
						<span class="sale-tip-colse"></span>
					</li>
					<li>1、提供的在职证明需包含您的新公司logo、姓名、电话、门店</li>
					<li>2、组织变更一个月内只能修改一次，申请时请慎重填写信息</li>
					<li>3、在职证明可提供名片或工牌或erp系统个人信息截图</li>
					<li>4、您所提交的在职证明仅用于核实信息，不会挪作他用，请放心上传</li>
					<li>5、您确认要修改此信息吗？修改后一个月后信息无法改回</li>
					<li>6、此账号套餐不能共享使用，有更多需求联系服务人员</li>
				</ul>
			</div>
			
			<!-- 修改密码 -->
			<div class="personPass tab" style="display:none">
				<ul class="modifyPass-content">
			   		<li>
			    		<label for="ordPassword"><span class="col-f60">*</span>旧密码：</label>
			    		<input type="password" name=ordPassword id="ordPassword" value="" class="modifyPass-input"/>
			    		<span>密码字母有大小写区分，<span class="col-f60 font-16">5-20</span>个字符。</span>
			   		</li>
			       	<li>
			        	<label for="newPassword"><span class="col-f60">*</span>新密码：</label>
			        	<input type="password" name="newPassword" id="newPassword" value="" class="modifyPass-input"/>	
			        	<span>请输入您的新密码</span>	               
			       	</li>
			       	<li>
			        	<label for="enterPassword"><span class="col-f60">*</span>确认密码：</label>
			        	<input type="password" name="enterPassword" id="enterPassword" value="" class="modifyPass-input"/>
			        	<span>请重新输入一遍您的新密码</span>		               
			       	</li>	
			       	 <li class="modifyPass-btn">
						<button  id="btn_upd_pwd" class="btn_g">确认</button>
						<button  id="btn_reset_pwd" class="btn_g">重置</button>
					 </li>
				 </ul> 
			</div>
			
			<div class="checkChange tab" style="display:none" id="tableData">
				
			</div>
		</div>
	</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/toolJs.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.timers-1.2.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js?skin=green' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/template.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/select2.full.min.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.paginate.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/lightbox.min_7036adb83f9be3e168896fb077b8e4f0.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/checkInput.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/constants_3318a849899f02bb3b56fc5c5f5b34eb.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/personInfo_ee9ae2661fb89e4acd58f5380344bf88.js' type='text/javascript'></script>
<script src="<%=path%>/Static/Js/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">

$("#state").select2({minimumResultsForSearch: -1});
$("#applyType").select2({minimumResultsForSearch: -1});
$("#dealUserId").select2({minimumResultsForSearch: -1});
if ('true' ==  'false') {
	$("#personInfo").hide();
	$(".personInfo").hide();
	$("#personPass").addClass("selected");
	$(".personPass").show();
	$("#checkChange").hide();
}

</script>
</body>
</html>