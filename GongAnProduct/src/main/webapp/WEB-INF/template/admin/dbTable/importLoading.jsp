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
	<!--必要样式-->
	<link rel="stylesheet" href="${base }/resources/admin/js/progressbar/css/jquery.lineProgressbar.css">
</head>
<body>
    <div class="pd15_25 lay_wind lay_wind1" id="lay_wind4">
		<div class="lay_form">
		    <form id="import_form" action="javaScript:void(0);">
		    	<input type="hidden" name="id" value="${id }" id="id"/>
		        <input type="hidden" name="startDate" value="${startDate}"> 
		        <input type="hidden" name="endDate" value="${endDate}"><div class="lay_form_wrap">
                    <dl class="lay_form_list" style="text-align: center;">
						<img id="imgLoading" width="32px" height="32px" alt="" src="${base }/resources/admin/images/lightbox-ico-loading.gif"/>
                    </dl>
                    <dl class="lay_form_list" style="text-align: center;">
                    	<span id="msg">正在导入中，请稍候...</span><br/>
                    	导入数据共<span id="sum">0</span>条，已处理<span id="dispose">0</span>条，成功<span id="succeed">0</span>条，失败<span id=fail>0</span>条。
                    </dl>
                    <dl class="lay_form_list sub_btn_box button" style="text-align: center;" id="btn_hover">
                    	<input type="button" value="关闭" class="back_next input_from btn_hover">
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
	<script src="${base }/resources/admin/js/progressbar/js/jquery.lineProgressbar.js"></script>
    <script type="text/javascript"> 
    $(function(){
    var websocket = null;
    $("#btn_hover").hide();
    	if(websocket == null || websocket.readyState!=1){
    		if ('WebSocket' in window) {
    	   	 	//判断当前浏览器是否支持WebSocket
    	        websocket = new WebSocket("ws://192.168.20.217/websocket");
    	    } else {
    	        //alert('当前浏览器 Not support websocket')
    	    }
    	}
    	//连接发生错误的回调方法
        websocket.onerror = function () {
            //setMessageInnerHTML("WebSocket连接发生错误");
        };

        //连接成功建立的回调方法
        websocket.onopen = function () {
        	//发送消息
    		websocket.send("开始导入");
        }

        //接收到消息的回调方法
        websocket.onmessage = function (event) {
            //setMessageInnerHTML(event.data);
        	var data_str = event.data;
        	var data = data_str.split(",");
        	document.getElementById("sum").innerText = data[0];
        	document.getElementById("dispose").innerText = data[1];
        	document.getElementById("succeed").innerText = data[2];
        	document.getElementById("fail").innerText = data[3];
        	if(data[0]==data[1]){
				document.getElementById("msg").innerText = "数据导入完毕";
        	}
        	//console.info(data[3]);
        	//add(percentage);
        	/* if(percentage >= 1){
        		closeWebSocket();
        	} */
        }

        //连接关闭的回调方法
        websocket.onclose = function () {
            /* layer.msg("WebSocket已关闭");   */
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        /* window.onbeforeunload = function () {
            closeWebSocket();
        } */

        //关闭WebSocket连接
        function closeWebSocket() {
        	if(websocket.readyState==1){
        		websocket.close();
        	}
        }
        
		$.ajax({
			url:'/admin/dbTable/importTable.shtml',
			type:'POST',
			data: $('#import_form').serialize(),
			dataType: "json",
			//async:false,
			success: function(data) {
				if(data.code==200){
					$('#imgLoading').attr('src','${base }/resources/admin/img/sucess.png'); 
				}else{
					document.getElementById("msg").innerText = data.msg;
					$('#imgLoading').attr('src','${base }/resources/admin/img/error.png'); 
					layer.msg(data.msg, { icon: 2});
				}
		        $("#btn_hover").show();
			},
			error : function(data) {  
				document.getElementById("msg").innerText = "数据导入失败";
				layer.msg("数据库保存失败", { icon: 2});  
              	layer.closeAll('loading'); 
		    } 
		});
    });
	</script>
</body>
</html>