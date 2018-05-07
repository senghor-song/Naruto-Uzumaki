<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<title><ruiec:title/></title>
    <link rel="stylesheet" href="${base }/resources/admin/js/select/select2.css" >
    <link rel="stylesheet" href="${base }/resources/admin/css/layer.css" >
    <link rel="stylesheet" href="${base }/resources/admin/css/common.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/style.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/index.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/quote.css"> 
	<!-- 鼠标右键点击 -->
	<link rel="stylesheet" href="${base }/resources/admin/js/basicContext/css/normalize.css" />
	<link rel="stylesheet" href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
	<link rel="stylesheet" href="${base }/resources/admin/js/basicContext/css/jq22-demo.css">
	<link rel="stylesheet" href="${base }/resources/admin/js/basicContext/dist/basicContext.min.css">
	<link rel="stylesheet" href="${base }/resources/admin/js/basicContext/dist/themes/default.min.css">
    <style>
    	.select2-container .select2-selection--single{ max-width:262px;}
	    .webuploader-pick{border:1px solid #CCCCCC}
    	#fileUrls a {padding: 0px 5px;float: left;}
    </style>
</head>
<body class="rest_select2">
    <div class="pd15_25 lay_wind" id="lay_wind1">
    	<style>
    		.lay_wind .lay_form_list dt{ width:27%;}
    		.lay_wind .lay_form_list dd{ width:60%;}
    	</style>
        <div class="lay_form">
            <form action="/admin/syntheticAnalysis/saveFeedback.shtml?taskDetailsId=${taskDetailsId}" method="post" id="form_layer">
                <div class="lay_form_wrap" style="width: auto; height: 480px; overflow: auto;">
                    <dl class="lay_form_list">
                        <dt>反馈内容：</dt>
                        <dd>
                            <div class="fl left_item">
                                <textarea name="content" isnot="true" class="fl textarea text_keyup" placeholder="请输入反馈内容"  maxlength="200" onkeyup="checkLen(this)"
                               	 	nullmsg="请输入反馈内容" errormsg="请输入反馈内容" maxlength="200" datatype="*"></textarea>
                                <div class="fl add_btn_wrap">  
								<span class="tips"><i class="m_242"><em class="text_keyup_num">0</em>/200</i>（最多可输入200字）</span>
                                </div>	
                            </div>
                        </dd>
                    </dl>
                    <dl class="lay_form_list">
                        <dt>上传附件：</dt>
                        <dd>
                            <sapn class="upload_btn">
                                <a class="upd_btn" id="uploaderFace">上传附件</a>
                            </sapn>
		                    <span style="color:red">请上传bmp、doc、gif、jpeg、jpg、png、ppt、pptx、rar、txt、xls、xlsx、zip格式文件</span>
               				<input type="file" id="upload_file" style="display: none;" />
          				 	<input id="fileUrl" isNot="true" type="hidden">
          				 	<div id="fileUrls">
          				 		
          				 	</div>
                        </dd>
                    </dl>
                </div>
                <div class="sub_btn_box">
                    <dl class="lay_form_list">
                        <dt>&nbsp;</dt>
                        <dd>
                            <input type="button" value="关闭" class="back_next">
                            <input type="submit" value="提交反馈" class="sub_btn">
                        </dd>
                    </dl>
                </div>
            </form>
        </div>
    </div>
    <!--js-->
    <script src="${base }/resources/admin/js/jquery-1.9.1.min.js"></script>
    <script src="${base }/resources/admin/js/layer/layer.js"></script>
    <script src="${base }/resources/admin/js/jquery.form.js"></script>
    <script src="${base }/resources/admin/js/Validform_v5.3.2/Validform_v5.3.2.js"></script>
    <script src="${base }/resources/admin/js/common.js"></script>
    <!--下拉框--> 
    <script src="${base }/resources/admin/js/select/select2.js"></script>
	<script src="${base }/resources/admin/js/webuploader/webuploader.min.js"></script>
	<script src="${base }/resources/admin/js/webuploader/myuploader.js"></script>
    <script src="${base }/resources/admin/imagesMagnify/js/jquery.lightbox-0.5.js"></script>
	<!-- 右键点击事件 -->
	<script src="${base }/resources/admin/js/basicContext/dist/basicContext.min.js"></script>
	<script type="text/javascript"> 
	var onClick = function(e) {
		var img =  $(this);
		var clicked = function() {$(img).remove();}

		var items = [
			{ title: '删除', icon: 'ion-android-delete', fn: clicked }
		]
		basicContext.show(items, e.originalEvent)

	}
    $(function(){
		var index;
		var webuploader = WebUploader.create({
	   		swf:"/resources/admin/js/webuploader/Uploader.swf",
	   		server:"/fileUpload.jsp",
	   		auto:true,
	   		pick:"#uploaderFace",
	   		fileNumLimit:10,
            duplicate: true,
            accept: {
                title: 'Images',
                extensions: 'bmp,doc,gif,jpeg,jpg,png,ppt,pptx,rar,txt,xls,xlsx,zip'
            }
		}); 
		webuploader.on("error",function (type){
	        if (type=="Q_TYPE_DENIED"){
	        	layer.msg("请上传bmp、doc、gif、jpeg、jpg、png、ppt、pptx、rar、txt、xls、xlsx、zip格式文件");
	        }
	        if (type=="Q_EXCEED_NUM_LIMIT"){
	        	layer.msg("最多上传10个文件");
	        }
	    });
		webuploader.on( 'uploadStart', function(file) {
			index = layer.msg('上传中', {
			  	icon: 16
			  	,shade: 0.1
			  	,time:false
			});
		});
		webuploader.on("uploadSuccess",function(file,data){
	  		if (data.code == 200) {
	  			var sum = $('#fileUrls a').length+1;
		  		var str = "<a ";
				var fileType=data.data.split('.');
		  		if(fileType[4]=='bmp' || fileType[4]=='gif' || fileType[4]=='jpeg' || fileType[4]=='jpg' || fileType[4]=='png'){
			  		str += "class='fileimg' href="+data.data+" del="+sum+"><input id='fileUrl' type='hidden' name='fileUrls' value='"+data.data+"'/><img src='/resources/admin/images/ruiec_"+fileType[4]+".png' width='60px'></a>";
	         		$("#fileUrls").append(str);
	                $('#fileUrls .fileimg').lightBox();
				}else {
					str += "href="+data.data+" del="+sum+"><input id='fileUrl' type='hidden' name='fileUrls' value='"+data.data+"'/><img src='/resources/admin/images/ruiec_"+fileType[4]+".png' width='60px'></a>";
	         		$("#fileUrls").append(str);
				}
        		$('#fileUrls a[del='+sum+']').on('contextmenu', onClick);
    			layer.close(index);
          	} else {
    			layer.close(index);
          		layer.msg(data.msg);
          	}
	  	});
	  	webuploader.on("uploadError",function(){
          	layer.msg("上传文件异常!请联系管理员!");
	  	}); 
	})
	function fileRemove(_this){
		$(_this).remove();
	}
	</script>
</body>
</html>