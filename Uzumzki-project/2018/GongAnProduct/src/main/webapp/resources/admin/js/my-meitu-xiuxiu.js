/**
 * 图片上传组件
 * 使用示例：$.fn.uploadImage("upload_btn", "${setting.siteUrl}/admin/aboutus/uploadIcon.shtml");
 * @param uploadId dom元素标识
 * @param uploadUrl 图片上传服务器地址
 * @param ratio 图片比例
 * @param isSelf 参数是否放在元素自己身上
 */
(function($) {
    $.fn.uploadImage = function(uploadClass, uploadUrl, initImage, ratio, isSelf, type) {
    	//var realRatio = ratio ? ratio : "200:200";
    	var realRatio = ratio ? ratio : "";
    	var initImagePath = initImage ? initImage : "";
    	var linkCss = '<style type="text/css">.xiuxiu_uploadxxl #xiuxiuEditor {position: fixed;width: 763px; z-index:10000;height: 500px;top: 50%;left: 50%;margin-left: -400px;margin-top: -250px;}'
    	+ '.xiuxiu_uploadxxl .zhegai {position: fixed;width: 100%;top: 0;left: 0;height: 100%;background: #333;filter: alpha(opacity=50);-moz-opacity: 0.5;-khtml-opacity: 0.5;opacity: 0.5;}'
    	+ '</style>';
    	$("link:last").after($(linkCss));
    	$("body").eq(0).append($('<div class="xiuxiu_uploadxxl" style="display:none"><div id="zhegai" class="zhegai"></div><div id="altContent"></div></div>'));
    	$("." + uploadClass).click(function(){
    		var $this = $(this);
    		var myRealRatio = "";
    		var targetId = "";
    		if(isSelf){
    			myRealRatio = $this.attr("realRatio");
    			targetId = $this.attr("targetId");
    		}
    		if(isSelf){
    			xiuxiu.setLaunchVars("cropPresets", myRealRatio);
    		}else{
    			if (realRatio) {
    				xiuxiu.setLaunchVars("cropPresets", realRatio);
    			}
    		}
    		$(".xiuxiu_uploadxxl").show();
    		xiuxiu.setLaunchVars("customMenu", [{"decorate":["basicEdit","effect"]}]);
    		xiuxiu.setLaunchVars("nav", "/edit");
    		if(type){
    			xiuxiu.embedSWF("altContent",type,"100%","100%");
    		}else{
    			xiuxiu.embedSWF("altContent",1,"100%","100%");
    		}
    		xiuxiu.setUploadType(2);
    		xiuxiu.setUploadDataFieldName ("file");
    		xiuxiu.setUploadURL(uploadUrl);
    		xiuxiu.onBeforeUpload = function(data, id) {
    			xiuxiu.setUploadArgs({filetype: data.type, type: "image", filename: data.name});
    		};
    	
    		xiuxiu.onInit = function() {
    			if (initImagePath) {
    				xiuxiu.loadPhoto(initImagePath);
    			}
    		};	
    		
    		xiuxiu.onUploadResponse = function(data) {
    			var date = new Date();
    			var result = $.parseJSON(data);
    			if(result.error != 0){
    				alert(result.message);
    				$(".xiuxiu_uploadxxl").hide();
    				return;
    			}
    			$("#imghead").attr("src", result.url + "?" + date.getMilliseconds());
    			if(isSelf){
    				$("#" + targetId).val(result.url);
    			}else{
    				$("#userImage").val(result.url);
    			}
    			$(".xiuxiu_uploadxxl").hide();
    		};
    		xiuxiu.onClose = function (id) {
    		    xiuxiu.remove(id);
    		    $(".xiuxiu_uploadxxl").hide();
    		};

    		
    		$("#zhegai").click(function() {
    			xiuxiu.remove("lite");
    			$(".xiuxiu_uploadxxl").hide();
    		});
    	});
    };
})(jQuery);