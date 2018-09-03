<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>帮我写描述</title>
<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/saleImport_e68b7b21f7093530ac0c3b8237ffcc3e.css' rel='stylesheet' type='text/css'></link>
</head>
<body>
<div class="desc">
	<table width="100%" class="ptable" id="autoDesc-tab" style="margin:0px 0px 8px 0">
		<tbody>
			<tr>
				<td groupName="通用">通用</td>
			</tr>
		</tbody>
	</table>
	<div class="desc-content autoDesc-content" style="height:350px">
		<div class="float-r mt10">
			<input type="button" class="btn-green" value="下一批" onclick="getDesc()"></input>
		</div>
	</div>
<script>
$("#autoDesc-tab td").die().live("click",function(){
	$(this).siblings().removeClass("selected");
	$(this).addClass("selected");
	var groupName = $(this).attr("groupName");
	$("input[name=groupName]").val(groupName);
	getDesc();
});

function getDesc() {
	$.ajax({
		url: "/WebRelease/massProperty/getDescList",
		data : $("#bForm").serialize(),
		type : "post",
		cache : false,
		dataType: "json",
		success: function(data) {
			var html = "";
			list = data.data;
			for (var i = 0; i < list.length; i++) {
				html +='<span style="font-size: 15px; color: green">#' + i + '</span><ul class="desc-list"><li onclick="changDesc(this)" class="desc-list-content">' + list[i] + '</li></ul>';
			}
			
			$(".desc-content").html(html);
		},
		error: function(jqXHR) {
			alert($.parseJSON(jqXHR.responseText).msg);
		}
	});
}

function changDesc(thisObj) {
	CKEDITOR.instances.houseDescribe.setData($(thisObj).html());
	autoDialog.close();
}
$(function(){
	getDesc();
});

</script>
</body>
</html>