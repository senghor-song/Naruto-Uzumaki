<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<title><ruiec:title/></title>
    <link rel="stylesheet" href="${base }/resources/admin/js/datetimepicker/jquery.datetimepicker.css" />
    <link rel="stylesheet" href="${base }/resources/admin/css/layer.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/common.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/style.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/index.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/quote.css" />
</head>
<body>
    <div class="pd15_25 lay_wind lay_wind1" id="lay_wind4">
	        <div class="lay_form">
	            <form id="import_form" action="javaScript:void(0);">
	            <input type="hidden" name="id" value="${id }" id="id"/>
	                <div class="pdl0 lay_form_item">
	                    <dl class="lay_form_list">
                            <dt>开始时间：</dt>
                            <dd> 
	                            <div class="fl left_item">
		                            <input class="Wdate text_inp " id="quote_time" autocomplete="off" type="text" name="startDate"> 
	                            </div>
                            </dd>
                         </dl>
                         <dl class="lay_form_list">
                            <dt>结束时间：</dt>
                            <dd> 
	                            <div class="fl left_item">
	                            	<input class="Wdate text_inp" id="quote_time1" autocomplete="off" type="text" name="endDate">
	                            </div>
                            </dd>
                         </dl>
	                </div>
	                <div class="mgt15 sub_btn_box">
	                    <dl class="lay_form_list">
	                        <dt>&nbsp;</dt>
	                        <dd>
	                            <input type="button" value="关闭" class="back_next">
	                            <input type="button" value="确认" Style="color:white" class="sub_btn">
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
    <!--日期-->
	<script src="${base }/resources/admin/js/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="${base }/resources/admin/js/datetimepicker/timer.js"></script>
    <script type="text/javascript"> 
  	// 导入数据
	$(".sub_btn").click(function(){
		layer.msg('导入中', { icon: 16 ,shade: 0.1 });
		$.ajax({
			url:'/admin/db/dataImport.shtml',
			type:'POST',
			data: $('#import_form').serialize(),
			dataType: "json",
			//async:false,
			success: function(data) {
				if(data.code==200){
					layer.msg(data.msg, { icon: 1});
				}else{
					layer.msg(data.msg, { icon: 2});
				}
			},
			error : function(data) {  
				layer.msg("数据库保存失败", { icon: 2});  
		       } 
		});
		//layer.closeAll('loading');
		
	});
	</script>
</body>
</html>