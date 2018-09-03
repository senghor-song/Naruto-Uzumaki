<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../../common/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>我的描述管理</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/saleImport_2863c1add81bbc61b654f8408bbd6b46.css' rel='stylesheet' type='text/css'></link>
  </head>
<body>
<div id="updDesc" class="saleHouse" style="min-height: 871px;">
	<ul class="sale-tab-sub">
		<li class="sale-sub-menu selected">
			<a id="modifyTitle" href="javascript:">修改我的描述</a>
		</li>
	</ul>
	<input type="hidden" id="updId" value="${massEmpSaleTemplate.id }">
	<div class="saleHouse-main ml8 mr8">
		<div class="houseDescManage-input-content" style="font-size: 14px;">
			标题：<input id="descName" class="houseDescManage-input" value="${massEmpSaleTemplate.title }">
			类型：<select id="postType" style="height: 26px;width: 60px;font-size: 14px;">
			<option value="0">通用</option>
			<option value="1">出售</option>
			<option value="2">出租</option>
			</select>
		</div>
		<div class="saleHouse-textarea">
			<textarea id="descContent" name="descContent" style="visibility: hidden; display: none;resize:none;">
			${massEmpSaleTemplate.describedata }
			</textarea>
			<input type="hidden" id="isShowAnjukeXinBan" value="true">
			
				<div class="anjukexinbanDesc claer" style="">
					<div class="ajkxbDesc js-yeZhuXingTai">
						<span class="descHeader"><span class="font-bold">业主心态:</span>
						<span class="col-999">从房东卖房原因、是否急售等方面进行描述</span>
						<span class="col-f60">(字数限制20-300)</span></span>
						<textarea id="yeZhuXingTai" name="yeZhuXingTai" class="ajkxbTextArea">${massEmpSaleTemplate.mentality }</textarea>  
					</div>
					<div class="ajkxbDesc js-xiaoQuPeiTao">
						<span class="descHeader"><span class="font-bold">小区配套:</span>
						<span class="col-999">从交通、教育、医疗、内部环境、车位情况、安保措施方面来描述</span>
						<span class="col-f60">(字数限制20-300)</span></span>
						<textarea id="xiaoQuPeiTao" name="xiaoQuPeiTao" class="ajkxbTextArea">${massEmpSaleTemplate.environment }</textarea>  
					</div>
					<div class="ajkxbDesc js-fuWuJieShao">
						<span class="descHeader"><span class="font-bold">服务介绍:</span>
						<span class="col-999">多角度描述您的服务优势，例如：行业年限、专业经验、服务态度、可提供的服务种类（比如代办贷款）等</span>
						<span class="col-f60">(字数限制20-300)</span></span>
						<textarea id="fuWuJieShao" name="fuWuJieShao" class="ajkxbTextArea">${massEmpSaleTemplate.serve }</textarea>  
					</div>
					<div class="ajkxbDesc js-shuiFeiXinxi">
						<span class="descHeader"><span class="font-bold">税费信息:</span>
						<span class="col-999">相关费用，描述越详尽您的房源质量就会越高，展示给网友的机会就会增加(仅搜房帮)</span>
						<span class="col-f60">(字数限制20-300)</span></span>
						<textarea id="shuiFeiXinxi" name="shuiFeiXinxi" class="ajkxbTextArea">${massEmpSaleTemplate.taxation }</textarea>  
					</div>
				</div>
			
		</div>
		<div class="saleManager-bottom center">
			<button id="btn_update" class="btn_g">修改</button>
			<button id="btn_cancel" class="btn_g">取消</button>
		</div>
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
	
	$("#btn_cancel").click(function() {
		$("#myDescs").show();
		$("#updDesc").hide();
	});
	
	$("#btn_update").click(function() {
		var params = [];
		var postType = $("#postType").val();
		var yeZhuXingTai = $("#yeZhuXingTai").val();
		var xiaoQuPeiTao = $("#xiaoQuPeiTao").val();
		var fuWuJieShao = $("#fuWuJieShao").val();
		var shuiFeiXinxi = $("#shuiFeiXinxi").val();
		var bValid = true;
		if (yeZhuXingTai != undefined && yeZhuXingTai != '') {
			bValid = bValid && checkRangeWithHint(yeZhuXingTai.length, 20, 300, "业主兴态");
			params.push({name : "mentality", value : yeZhuXingTai});
		}
		
		if (xiaoQuPeiTao != undefined && xiaoQuPeiTao != '') {
			bValid = bValid && checkRangeWithHint(xiaoQuPeiTao.length, 20, 300, "小区配套");
			params.push({name : "environment", value : xiaoQuPeiTao});
		}
		
		if (fuWuJieShao != undefined && fuWuJieShao != '') {
			bValid = bValid && checkRangeWithHint(fuWuJieShao.length, 20, 300, "服务介绍");
			params.push({name : "serve", value : fuWuJieShao});
		}
		
		if (shuiFeiXinxi != undefined && shuiFeiXinxi != '') {
			bValid = bValid && checkRangeWithHint(shuiFeiXinxi.length, 20, 300, "税费信息");
			params.push({name : "taxation", value : shuiFeiXinxi});
		}
		var descName = $("#descName").val();
		bValid = bValid && checkRangeWithHint(descName.length, 1, 35, "模板名称");
		params.push({name : "title", value : descName});
		var contentLen = strlen(CKEDITOR.instances.descContent.document.getBody().getText());
		if(contentLen > 0 ){
			bValid = bValid && checkRangeWithHint(contentLen, 30, 3000, "房源描述内容");
			params.push({name : "describedata", value : CKEDITOR.instances.descContent.getData()});
		}
		
		if (! bValid) return;

		params.push({name : "type", value : postType});
		params.push({name : "id", value : $("#updId").val()});
		var url = "/WebRelease/massEmpSaleTemplate/editDescribe";
		
		$.ajax({
			url: url,
			type: "post",
			data: params,
			dataType: "json",
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
				layer.msg("请求异常",{icon:2});
			}
		});
	});
	
	$("#postType").change(function(){
		var postType = $("#postType").val();
		if ($("#isShowAnjukeXinBan").val() == 'true') {
			if (postType == 2) {
				$(".anjukexinbanDesc").hide();
			} else {
				$(".anjukexinbanDesc").show();
			}
		}
	});
});
</script>
</body>
</html>