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
    <%-- <link rel="stylesheet" href="${base }/resources/admin/js/webuploader/webuploader.css" > --%>
    <link rel="stylesheet" href="${base }/resources/admin/js/select/select2.css" >
    <link rel="stylesheet" href="${base }/resources/admin/css/layer.css" >
    <link rel="stylesheet" href="${base }/resources/admin/css/common.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/style.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/index.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/quote.css"> 
    <style type="text/css">
    	body{ height:100%; overflow:auto;}
    </style>
</head>
<body class="rest_select2">
    <div class="pd15_25 lay_wind" id="lay_wind1">
        <div class="lay_form">
			<form action="/admin/controlPerson/toLeadExcel.shtml" method="post" enctype="multipart/form-data" id="toLeadExcel">
                <input type="hidden" value="${ id }" name="id">
                <div class="lay_form_wrap">
                    <dl class="lay_form_list">
                        <dt>下载模板：</dt>
                        <dd>
                			<a href="${base }/resources/admin/execl/人员表格模板.xlsx" download="人员表格模板.xlsx"
                			style="border: solid 1px #ddd;padding: 4px;border-radius: 3px;color: #888888;">点击下载</a>
                        </dd>
                    </dl>
                </div>
                <div class="lay_form_wrap"> 
                    <dl class="lay_form_list">
                        <dt>导入execl表格：</dt>
                        <dd>
                            <!-- 上传功能 -->
                			<input type="hidden" name="personnelType" id="personnelType" value="${personnelType}"/>
	                    	
	                    	<!-- <input type="file" name="execlFile" id="execlFileUrl" accept=".xls,.xlsx,.xlt,.xlsm"> -->
	                    	<div class=" uploader_wrap">
                                <sapn class="uploader_btn" id="uploader_btn">上传</sapn> 
                                <div class="mgt10 excel_pend"></div>
                            </div>
                        </dd>
                    </dl>
                </div>
                <div class="sub_btn_box">
                    <dl class="lay_form_list">
                        <dt>&nbsp;</dt>
                        <dd>
                            <input type="button" value="关闭" class="back_next">
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
    <script src="${base }/resources/admin/js/webuploader/webuploader.min.js"></script>
    <script src="${base }/resources/admin/js/webuploader/myuploader.js"></script>
    <!--下拉框--> 
    <script src="${base }/resources/admin/js/select/select2.js"></script>
    <script type="text/javascript">
    	<!--导入-->
	  //绑定页面的上传图片的JS
	  if($(".uploader_wrap").length>0){
		  var _btnId = $(".uploader_wrap").find(".uploader_btn").eq(0).attr("id");
		  var Myuploader = WebUploader.create({
              auto: true,   
              swf: '/resources/admin/js/webuploader/Uploader.swf',
              server:"/admin/controlPerson/toLeadExcel.shtml?personnelType=1", 
              pick: {
                  id: '#' + _btnId,
                  multiple:false
              }, 
              duplicate: true
          });/* 
		  //验证格式以及大小
		  Myuploader.on('error', function (type) {
              if (type == "Q_TYPE_DENIED") {
                  layer.msg("请上传xls,xlsx,xlt,xlsm格式的文件！", { icon: 2 });
                  return;
              }
          }); */
		  //上传缩略图
		  Myuploader.on('filesQueued', function (file) {
              //console.log(file);
              if( file != "" && file != null && file != undefined ){
              		var _format =file[0].ext; 
	              	if( _format == "xls" || _format == "xlsx" || _format == "xlt" || _format == "xlsm" || _format == "XLS" || _format == "XLSX" || _format == "XLT" || _format == "XLSM"  ){
	              		//加载层
	              		layer.msg('导入中', {
	              		    icon: 16,
	              		    shade: 0.1,
	              			time:false
	              		});
		            }else{
	              		/* layer.msg("请上传xls,xlsx,xlt,xlsm格式的文件！", { icon: 2 });  */
	              		return;
	              	}; 
              };    	
          }); 
		  //上传成功
		  Myuploader.on('uploadSuccess', function (file, data){ 
			if( file != "" && file != null && file != undefined ){
               var _format =file.ext; 
               var _name =file.name; 
	                if( _format == "xls" || _format == "xlsx" || _format == "xlt" || _format == "xlsm" || _format == "XLS" || _format == "XLSX" || _format == "XLT" || _format == "XLSM"  ){
                        if(data.code == 200){
		                	layer.alert(data.msg, {
	    						skin: 'layui-layer-molv', //样式类名
	    						closeBtn: 0,
	    						anim: 4, //动画类型
	    						yes:function(){
	                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引 再执行关闭 
	                                if( data.url !="" ){
	                                    parent.layer.close(index);
	                                    //在父页面 刷新
	                                    window.parent.location.href= data.url; 
	                                }
	                                layer.closeAll('loading'); 
								}
	    					});
                        }else{
                        	layer.alert(data.msg, {
                                skin: 'layui-layer-molv', //样式类名
                                closeBtn: 0,
                                anim: 4 //动画类型
                            });
                            layer.closeAll('loading'); 
                            return;
                        }
	              	}else{
	              		layer.alert("请上传xls,xlsx,xlt,xlsm格式的文件！", {
							skin: 'layui-layer-molv', //样式类名
							closeBtn: 0,
							anim: 4 //动画类型
						});
		              	layer.closeAll('loading'); 
	              		return;
	              	};  
               }; 
		  });
		  //文件上传失败，显示上传出错。
          Myuploader.on('uploadError', function (file) {
              console.log(file, "error", arguments);
              if (typeof com != "undefined") {
                  layer.msg("文件上传失败");
              }
          }); 
	  }; 
    </script>
</body>
</html>