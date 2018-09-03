<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>黑名单</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/personHouse_36f13665516440aaad6239ca8edc23c9.css' rel='stylesheet' type='text/css'></link>
</head>
<body>
<input type="hidden" id="basePath" value="/houseCollect"/>
<div class="saleHouse">
	<div class="saleHouse-main">
		<ul class="sale-tab-sub">
			<li class="sale-sub-menu selected">
				<a href="http://house.xms.demo.com/houseCollect/subScribe/subscribeConfigView.do">黑名单设置</a>
			</li>
		</ul>
		<div class="subscribe">
			<ul class="sale-content-tip">
				<li>
					<span class="sale-tip-img"></span>
					<span>贴心提示:</span>
					<span class="sale-tip-colse"></span>
				</li>
				<li>1.添加黑名单后,房源采集会根据您设置的黑名单列表,过滤个人采集的房源</li>
			</ul>
			<div class="sub-header">
				<span>黑名单设置</span>
				<a class="btn-green" href="javascript:ForBidPhone.showAdd()">添加</a>
			</div>
			<div class="sub-content">
				<div id="subTable" class="sub-table-list">
					<table class="h_list" width="100%" cellspacing="0" cellpadding="0s">
						<thead>
							<tr>
								<td>电话</td>
								<td>备注</td>
								<td>操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list }" var="list">
								<tr id="tr_${list.id }">
									<td><input type="checkbox" value="${list.id }" name="sProblem"><span class="phone">${list.tel }</span></td>
									<td>${list.mes }</td>
									<td><a href="javascript:void(0)" onclick="delBlackList('${list.id}')">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="sub-header sub-operation">
						<input type="checkbox" name="c0" id="all" value="全选" onclick="change()" class="sub-header">全选
						<a class="btn-green" href="javascript:void(0)"   onclick="delBlackList(getTheCheckBoxValue())">删除</a>
					</div>
					<div class="float-r">
						<div id="saleManager-fanye" class="commom-fanye "></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function getTheCheckBoxValue(){  
		var test = $("input[name='sProblem']:checked");  
		var checkBoxValue = "";   
		test.each(function(){  
		    checkBoxValue += $(this).val()+",";  
		})  
		return checkBoxValue.substring(0,checkBoxValue.length-1);
	}
	
	//获取checkbox按钮组
	var allpro = document.getElementsByName("sProblem");
	//全选方法
	function change() {
		//获取全选按钮
		var all = document.getElementById("all");
		//全选按钮被选中时，遍历所有按钮
		if (all.checked) {
			for (var i = 0; i < allpro.length; i++) {
				if (allpro[i].type == "checkbox") {
					allpro[i].checked = true;
				}
			}
			//全选按钮未被选中时
		} else {
			for (var i = 0; i < allpro.length; i++) {
				if (allpro[i].type == "checkbox") {
					allpro[i].checked = false;
				}
			}
		}
	}
</script>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/forbidPhone_153243499fdb7550089c877d49293891.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.paginate.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js?skin=green' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/template.js' type='text/javascript'></script>
<script>
	if ('${page.pages}' != null && '${page.pages}' != 0) {
		/*翻页控件*/
		$("#saleManager-fanye").paginate({
			count 		: '${page.pages}',
			start 		: '${page.pageNum}',
			display     : 10,
			border					: false,
			text_color  			: '#50b63f',
			text_hover_color		: '#fff',
			background_color    	: '#fff',	
			background_hover_color	: '#50b63f',
			images		: false,
			mouse		: 'click',
			onChange	: function(){
				$("#pageNow").val($(".jPag-current").html());
	            window.location = "/WebRelease/blacklist/blacklist?pageNum="+$(".jPag-current").html();
			}
		});
	}
</script>
</body>
</html>