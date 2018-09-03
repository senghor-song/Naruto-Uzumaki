<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>公告列表</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/notice_bf8b3a2904797cabab142a1644c2b494.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>

  </head>
<body >
<div class="saleHouse">
	<input type="hidden" value="0" id="noticeType"></input>
	<div class="saleHouse-main">
		<div class="sale-tab-bg clear">
			<ul class="sale-tab-sub">
				<li id="notice-system" class="sale-sub-menu selected">
					<a href="javascript:getNotice()">系统公告</a>
				</li>
				<li id="notice-website" class="sale-sub-menu">
					<a href="javascript:getNotice()">网站公告</a>
				</li>
				<li id="notice-user" class="sale-sub-menu">
					<a href="javascript:getNotice()">客户公告</a>
				</li>
				<li id="notice-company" class="sale-sub-menu">
					<a href="javascript:getNotice()">公司公告</a>
				</li>
				<li id="notice-message" class="sale-sub-menu">
					<a href="javascript:getNotice()">站内消息</a>
				</li>
			</ul>
		</div>
		<input type="hidden" id="basePath" value="" />
		<div class="noticeList-main">
			<div class="noticeListData">
			
			</div>
			<div class="float-r">
				<div id="notice-fanye" class="commom-fanye notice-font"></div>
			</div>
		</div>
	</div>
</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_b016234725ebd1612a8f62bf4261c244.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/notice_544d3e9e7db1df8e796b69419dbdd4e7.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.paginate.js' type='text/javascript'></script>
<script>
		if ('1' != null & '1' != 1) {
		/*翻页控件*/
		$("#notice-fanye").paginate({
			count 		: '1',
			start 		: '1',
			display     : 6,
			border					: false,
			text_color  			: '#50b63f',
			text_hover_color		: '#fff',
			background_color    	: '#fff',	
			background_hover_color	: '#50b63f',  
			images		: false,
			mouse		: 'click',
			onChange	: function(){
				getNotice($(".jPag-current").html());
			}
		});
	}
</script>
<script>
	$(function(){
		var noticeType = $("#noticeType").val();
		if(noticeType == '0'){
			$("#notice-system").addClass("selected");
		}else if(noticeType == '1'){
			$("#notice-website").addClass("selected");
		}else if(noticeType == '2'){
			$("#notice-user").addClass("selected");
		}else if(noticeType == '3'){
			$("#notice-company").addClass("selected");
		}else if(noticeType == '3'){
			$("#notice-message").addClass("selected");
		}
		
		$("#notice-system").click(function(){
			$("#notice-system").addClass("selected");
			$("#notice-system").siblings(".sale-sub-menu").removeClass("selected");
			$("#noticeType").val('0');
		});
		$("#notice-website").click(function(){
			$("#notice-website").addClass("selected");
			$("#notice-website").siblings(".sale-sub-menu").removeClass("selected");
			$("#noticeType").val('1');
		});
		$("#notice-user").click(function(){
			$("#notice-user").addClass("selected");
			$("#notice-user").siblings(".sale-sub-menu").removeClass("selected");
			$("#noticeType").val('2');
		});
		$("#notice-company").click(function(){
			$("#notice-company").addClass("selected");
			$("#notice-company").siblings(".sale-sub-menu").removeClass("selected");
			$("#noticeType").val('3');
		});
		$("#notice-message").click(function(){
			$("#notice-message").addClass("selected");
			$("#notice-message").siblings(".sale-sub-menu").removeClass("selected");
			$("#noticeType").val('4');
		});
		getNotice();
	});
</script>
</body>
</html>