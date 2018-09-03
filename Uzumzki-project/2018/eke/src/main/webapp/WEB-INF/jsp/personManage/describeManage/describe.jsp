<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../../common/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>我的描述管理</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/saleImport_2863c1add81bbc61b654f8408bbd6b46.css' rel='stylesheet' type='text/css'></link>
  </head>
<body >
<div id="myDescs" class="saleHouse">
	<ul class="sale-tab-sub">
		<li class="sale-sub-menu selected">
			<a href="javascript:">我的描述管理</a>
		</li>
	</ul>
	<div class="saleHouse-main ml8 mr8">
		<div style="margin-top: 5px;"><input type="button" onclick="addDesc()" class="btn_g" value="新增" id="shuo_add"/></div>
		<table class="h_list" width="100%" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<td width="80">编号</td>
					<td>房源描述模板名称</td>
					<td width='80'>模板</td>
					<td width="80">类型</td>
					<td width="120">操作</td>
				</tr>
			</thead>
			<tbody id="houseTable">
				<c:forEach var="obj" items="${list}" varStatus="index" >
					<tr>
						<td class="td_id">${index.index + 1}</td>
						<td>${obj.title }</td>
						<td>描述</td>
						<td>${obj.type == 0 ? '通用' : obj.type == 1 ? '出售' : '出租'}</td>
						<td>
							<a name="upd_houseDesc" href="/WebRelease/massEmpSaleTemplate/updateDescribe?id=${obj.id }">修改</a>&nbsp;
							<a href="javascript:void(0);" onclick="delDescribe('${obj.id }')">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js?skin=green' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/plugins/ckeditor/ckeditor.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/checkInput.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/layer/layer.js' type='text/javascript'></script>
<script>
$(function(){
	CKEDITOR.replace("descContent", {
        toolbar : [
       		 ['Font','FontSize'],['TextColor','BGColor'],
       		 ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
       		 ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
       		 ['Maximize']
       	]
    });
});
function delDescribe(id){
	$.ajax({
		url: "/WebRelease/massEmpSaleTemplate/delDescribe",
		type: "post",
		dataType : "json",
		data: {
			id: id
		},
		success: function(data) {
			if(data.code == 200){
				layer.msg(data.msg,{icon:1,shade: 0.3});
				setTimeout(function () {
					window.location.href="/WebRelease/massEmpSaleTemplate/describe";
				}, 2000);
			}else{
				layer.msg(data.msg,{icon:2});
			}
		},
		error: function(jqXHR) {
			layer.msg("删除失败",{icon:2});
		}
	});
}
function addDesc() {
	window.location.href="/WebRelease/massEmpSaleTemplate/addDescribe";
}
</script>
</body>
</html>