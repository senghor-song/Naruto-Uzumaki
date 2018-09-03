<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>个性水印</title>
<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/watermarkConfig_acf53edf2fe626c9366e185375703e8d.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/lightbox.min.css' rel='stylesheet' type='text/css'></link>
<link href="<%=path%>/Static/Css/uploadify.css" rel="stylesheet" type="text/css" />
</head>
<body>
<input type="hidden" id="basePath" value="/release-web"></input>
<input type="hidden" id="staticPath" value="//demo.com"></input>
<input type="hidden" id="userId" value="2073512"></input>
<div class="saleHouse watermarkConfig">
	<ul class="sale-tab-sub">
		<li class="sale-sub-menu selected" id="person">
			<a href="javascript:">个性水印</a>
		</li>
		<li class="sale-sub-menu" id="company">
			<a href="javascript:">公司水印</a>
		</li>
	</ul>
	<div class="watermarkConfig-main common-main">
		<div class="person tab">
			<ul class="sale-content-tip">
				<li>
					<span class="sale-tip-img"></span>
					<span>贴心提示:</span>
					<span class="sale-tip-colse"></span>
				</li>
				<li>1.个性水印最多可设置3张;</li>
				<li>2.个性水印可以设置平铺效果;</li>
				<li>3.个性水印的宽高不能大于300像素;</li>
				<li>4、平铺图最好是一张等宽高的图，logo灰化并且45°倾斜。<a target="_blank" href="http://ali.wimg.fcxms.com/xmslogo_t.png">参考样图</a></li>
			</ul>
			<div>
				<div id="person-main">
					
						
							<div class="xms-wxts-font">您还没有上传个性水印,赶紧上传吧！</div>
						
						
					
				</div>
					<div class="clear"></div>
					<div style="height: 20px;width: 
					90px;padding: 8px 0px 2px 20px;float:right;margin:8px 0 0 0">
						<a href="javascript:onlineGenWatermark()">在线生成水印</a>
					</div>
				<div class="watermarkBottom clear" style="display:none">
				<div class="clear"></div>
					<div class="float-l mr8">
						<input type="file" name="uploadify" id="uploadify"></input>
						<div id="fileQueue"></div>
					</div>
					<input type="button" value="保存" class="watermark-button" style="float:left" onclick="saveWatermarkConfig()"></input>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<div class="company tab" style="display:none">
			
				
					<div class="xms-wxts-font">您公司还没有上传水印</div>
				
				
			
		</div>
	</div>
</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/map.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/watermarkConfig_1286e1b6ad8f93a6e5ddb6f31399e1f0.js' type='text/javascript'></script>
<script src="<%=path%>/Static/Js/jquery.uploadify.min.js" type="text/javascript"></script>
<script src='<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js?skin=green' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/lightbox.min_7036adb83f9be3e168896fb077b8e4f0.js' type='text/javascript'></script>
<script>
	var uploadUrl = '//s.img.xms.d.com/image-web/uploadWatermark.do';
	if (uploadUrl.indexOf('http') == -1) {
		uploadUrl = window.location.protocol + uploadUrl;
	}
	
	var optionPos = 'C';
	var optionUrl = '';
	if (optionPos != '' && optionUrl != '') {
		var imgs = $(".watermark-img");
		$.each(imgs,function(i, img){
			var url = $(this).attr("src");
			if (url == optionUrl) {
				$(this).parents(".watermarklist").find(".position").val(optionPos);
				var posFont = "右下";
				if (optionPos == 'TL') {
					posFont = "左上";
				} else if (optionPos == 'TR') {
					posFont = "右上";
				} else if (optionPos == 'C') {
					posFont = "居中";
				} else if (optionPos == 'BL') {
					posFont = "左下";
				} else if (optionPos == 'BR') {
					posFont = "右下";
				} else {
					posFont = "平铺";
				}
				$(this).parents(".watermarklist").find(".watermark-span").html(posFont);
				$(this).parents(".watermarklist").find(".upload-default").hide();
				$(this).parents(".watermarklist").find(".watermark-pic").addClass("defaultWater");
			}
		});
	}
	
	
	function onlineGenWatermark(){
		var objs = $("#person-main").find(".watermarklist");
    	if (objs.length > 2) {
    		art.dialog.tips("最多只能上传三张个性水印");
    		return;
    	}
    	
		art.dialog.load("/WebRelease/watermark/onlineGenWatermark", {
			width: 770,
			height: 410,
			title: '在线生成水印',
			padding : 0,
		});
	}
</script>
</body>
</html>