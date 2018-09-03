<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>帮我写标题</title>
<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/saleImport_e68b7b21f7093530ac0c3b8237ffcc3e.css' rel='stylesheet' type='text/css'></link>
</head>
<body>
<div class="autoTitle">
	<ul class="autoTitle-state">
		<li id="common-title" class="autoTile-li active" webId="0">
			<a href="javascript:getTitle()">通用标题</a>
		</li>
	</ul>
	<div class="title-main">
		<div class="common-title houseTitle-tab">
			<ul class="autoTitle-content commonTitle-content">
			</ul>
			<div class="float-r">
				<input type="button" class="btn-green" value="下一批" onclick="getTitle()"></input>
			</div>
		</div>
	</div>
</div>
<script>
$(function(){
	$(".autoTitle-state li").click(function(){
		var id = $(this).attr("id");
		$(".autoTile-li").removeClass("active");
		$(this).addClass("active");
		$(".houseTitle-tab").hide();
		$("." + id).show();
	});
});

function getTitle() {
	$.ajax({
		url: "/WebRelease/massProperty/getTitleList",
		data : $("#bForm").serialize(),
		type : "post",
		cache : false,
		dataType: "json",
		success: function(data) {
			var html = "";
			var list = data.data;
			for (var i = 0; i < list.length; i++) {
				html +='<li onclick="changTitle(this)"><a>' + list[i] + '</a></li>';
			}
			$(".commonTitle-content").html(html);
		},
		error: function(jqXHR) {
			alert($.parseJSON(jqXHR.responseText).msg);
		}
	});
}

function getWebTitle(webId,xmsWebId,pageNow){
		pageNow = (pageNow == undefined) ? 1 : pageNow;
		$.ajax({
			url: $("#basePath").val() + "/ajax/import/getWuBaTitle.do",
			data : {
				"cellCode" : $("#cellCode").val(),
				"pageNow" : pageNow,
				"webId" : webId,
				"xmsWebId" : xmsWebId,
				"postType" : $("#postType").val()
			},
			type : "post",
			cache : false,
			dataType: "json",
			success: function(data) {
				initCommonTitle(xmsWebId);
				var html = "";
				if (data.ishave == 0) {
					html += '<div class="houseTitle-font">亲，没有该小区的标题，请查看其它网站的标题</div>';
					$(".autoTitle-bottom").hide();
				} else {
					var list = data.titles;
					if (list.length > 0 ){
						for (var i = 0; i < list.length; i++) {
							html +='<li onclick="changTitle(this)"><a>' + data.defaultDesc + list[i].title + '</a></li>';
						}
						$(".autoTitle-bottom").show();
						initPage(data.count, pageNow);
					} else {
						html += '<div class="houseTitle-font">亲，没有该小区的标题，请查看其它网站的标题</div>';
						$(".autoTitle-bottom").hide();
					}
				}
				$(".wubaTitle-content").html(html);
			},
			error: function(jqXHR) {
				alert($.parseJSON(jqXHR.responseText).msg);
			}
		});
}

function initCommonTitle(xmsWebId){
	$(".wuba-title").show();
	$(".common-title").hide();
	$(".autoTile-li").removeClass("active");
	$("#web_" + xmsWebId).addClass("active");
}

function init(){
	$(".autoTile-li").eq(0).addClass("active");
	var webId = $(".autoTile-li").eq(0).attr("webId");
	var xmsWebId = $(".autoTile-li").eq(0).attr("xmsWebId");
	if (webId > 0) {
		getWebTitle(webId, xmsWebId);
	} else {
		$(".wuba-title").hide();
		$(".common-title").show();
		getTitle();
	}
}

init();

function initPage(count, pageIndex){
	if (count > 0) {
		$("#count").html(count);
		var pageNum = parseInt((count - 1) / 10) + 1;
		/*翻页控件*/
		$("#wubaTitle-fanye").paginate({
			count 		: pageNum,
			start 		: pageIndex,
			display     : 6,
			border					: false,
			text_color  			: '#50b63f',
			text_hover_color		: '#fff',
			background_color    	: '#fff',	
			background_hover_color	: '#50b63f',  
			images		: false,
			mouse		: 'click',
			onChange	: function(){
				getWebTitle($(".active").attr("webId"),
						$(".active").attr("xmsWebId"),
						$(".jPag-current").html());
			}
		});
	}
}

function changTitle(thisObj) {
	$("#saleHouse-title").val($(thisObj).find("a").html());
	focusAndBlur("saleHouse-title");
	autoDialog.close();
}
</script>
</body>
</html>