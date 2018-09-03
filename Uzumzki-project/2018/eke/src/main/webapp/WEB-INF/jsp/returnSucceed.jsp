<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>房产易推房登陆页-房产中介公司营销管理的软件</title>
	<link href="<%=path %>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		.footer-tip {
		    color: green;
		    font-size: 20px;
		    font-weight: bold;
		    margin: 150px auto;
		    text-align:center
		}
		.footer-tip  li{margin:20px 0}
		.footer-tip a:hover{text-decoration:underline}
		.btn-result{width:120px;height:32px;line-height:32px;text-align:center;font-size:14px;font-weight:bold;color:#fff;background-color:#72c962;border-radius:10px;border:none;cursor:pointer;}
	</style>
</head>
<body>
	<input type="hidden" id="basePath" value="/release-web">
	<input type="hidden" id="buildingId" value="161727613">
	<input type="hidden" id="guideIndex" value="0">
	<div class="saleHouse personConfig" style="min-height: 818px;">
		<ul class="sale-tab-sub">
			<li class="sale-sub-menu selected"><a href="javascript:">房源保存成功</a></li>
		</ul>
		<ul class="footer-tip" id="importResult">
			<li><h1>恭喜您，保存成功！</h1></li>
			<li><input onclick="redirectImport(0)" class="btn-result" value="继续录入"> <input onclick="redirectManager(0, 1, 0)" class="btn-result"
				value="立即推送"> <input onclick="redirectManager(0, 1, 1)" class="btn-result" value="查看房源列表"></li>
		</ul>
	</div>
	<script src="<%=path %>/Static/Js/jquery-1.8.3.js" type="text/javascript"></script>
	<script src="<%=path %>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js" type="text/javascript"></script>
	<script type="text/javascript">
		//guideIndex是否是向导,editType是否是秒录,importFrom是否是房秘书秒录
		var city = '755';
		document.domain = "4846.com";
		$(document).ready(
				function() {
					top.loadingHide();
					if ('0' == 0) {
						var introIndex = parent.$("#intro_index").val();
						var guideIndex = $("#guideIndex").val();
						if (introIndex == 2 || guideIndex == 1) {
							if ('DRAFT' == 'CH') {
								parent.$("#intro_index").val(0);
								parent.exitIntro_common();
								parent.checkGuideAward(2);
							}
						}

						// 			'755' == 21 || '755' == 571 || '755' == 512 || '755' == 10 || '755' == 351
						if ('DRAFT' == 'WIWJERP'
								&& ('755' == 21 || '755' == 10) && '0' == 1) {
							synBuilding('161727613', '1', '');
						}
					}
				});

		//继续复制
		function copyAgain() {
			parent.copyHouseArt.close();
		}

		//房源管理
		function redirectManager(postType, buildingType, type) {
			if (postType == 0) {
				id = "houseManagerSell";
			} else {
				id = "houseManagerRent";
			}
			top.$("#" + id).click();
			var href = top.parent.$("#" + id).attr("href");
			top.$("#" + id).parents(".menu").addClass("menu_open");
			top.$("#" + id).parents(".menu").siblings()
					.removeClass("menu_open");
			top.hrefLink(href);
		}

		//房源录入
		function redirectImport(postType) {
			if (postType == 0) {
				id = "importSell";
			} else {
				id = "importRent";
			}
			top.$("#" + id).click();
			var href = top.$("#" + id).attr("href");
			href = href.split(",")[0];
			top.$("#" + id).parents(".menu").addClass("menu_open");
			top.$("#" + id).parents(".menu").siblings()
					.removeClass("menu_open");
			top.hrefLink(href);
		}

		//我爱我家ERP同步到官网
		var isCanSubmit = true;
		function synBuilding(buildingId, buildingType, internalNum) {
			if (isCanSubmit) {
				var chk_value = [];
				chk_value.push({
					name : "buildingType",
					value : buildingType
				});
				chk_value.push({
					name : "buildingIds",
					value : buildingId
				});
				chk_value.push({
					name : "webIds",
					value : "60"
				});
				chk_value.push({
					name : "loginNames",
					value : parent.$("#loginName").val()
				});
				chk_value.push({
					name : "kcs",
					value : "1"
				});
				chk_value.push({
					name : "retrys",
					value : "2"
				});
				chk_value.push({
					name : "positions",
					value : "1"
				});
				$.ajax({
					url : $("#basePath").val()
							+ "/ajax/houman/releasehouses.do",
					data : chk_value,
					type : "post",
					cache : false,
					dataType : "json",
					beforeSend : function(XMLHttpRequest) {
						isCanSubmit = false;
						top.loadingShow();
					},
					success : function(result) {
						if (result.stats == 0) {
							parent.$("#checkbox_" + internalNum).attr(
									"hasImport", "1");
							parent.$("#hasImport_" + internalNum).html("已导入");
						}
					},
					error : function(jqXHR) {
						alert($.parseJSON(jqXHR.responseText).msg);
					},
					complete : function(XMLHttpRequest, textStatus) {
						isCanSubmit = true;
						top.loadingHide();
					}
				});
			}
		}
	</script>
</body>