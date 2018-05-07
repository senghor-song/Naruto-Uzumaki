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
	                <div class="mgt15 pdl0 lay_form_item">
	                    <dl class="lay_form_list">
                            <dt>开始时间：</dt>
                            <dd> 
	                            <div class="fl left_item">
	                           	 	<input class="Wdate text_inp input_from" id="quote_time" autocomplete="off" type="text" name="startDate"> 
	                            </div>
                            </dd>
                         </dl>
                         <dl class="lay_form_list">
                            <dt>结束时间：</dt>
                            <dd> 
	                            <div class="fl left_item">
	                            	<input class="Wdate text_inp input_from" id="quote_time1" autocomplete="off" type="text" name="endDate">
	                            </div>
                            </dd>
                         </dl>
	                </div>
	                <div class="mgt15 sub_btn_box button">
	                    <dl class="lay_form_list">
	                        <dt>&nbsp;</dt>
	                        <dd>
	                            <input type="button" value="关闭" class="back_next input_from">
	                            <input type="button" value="确认" Style="color:white" class="sub_btn input_from">
	                        </dd>
	                    </dl>
	                </div>
	                <div class="pdl0 lay_form_item progressbar" style="display:none;">
						<div id="progressbar"></div>
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
    var websocket = null;
    if ('WebSocket' in window) {
   	 	//判断当前浏览器是否支持WebSocket
        websocket = new WebSocket("ws://192.168.20.207:8888/websocket");
    } else {
        //alert('当前浏览器 Not support websocket')
    }
	function init(sum){
    	$('#progressbar').LineProgressbar({
  		  percentage: sum,
  		  fillBackgroundColor: '#f1c40f',
  		  height: '10px',
  		  radius: '50px'
  		});
    }
	function add(size){
		$('.proggress').animate( 
			{ width:size+"%" },
			{ step: function(size) {
				var sum = Math.round(size);
				if(sum>100){
					sum = 100;
				}
				console.info(sum+"-----");
				$(".percentCount").text(sum + "%");
			},
		} );/* 
		$('.proggress').css("width",size+"%");
		$(".percentCount").text(size + "%"); */
    }
  	// 导入数据
	$(".sub_btn").click(function(){
		$(".progressbar").toggle();
    	init(0); 
    	if(websocket.readyState!=1){
    		if ('WebSocket' in window) {
    	   	 	//判断当前浏览器是否支持WebSocket
    	        websocket = new WebSocket("ws://192.168.20.207:8888/websocket");
    	    } else {
    	        //alert('当前浏览器 Not support websocket')
    	    }
    	}
        //发送消息
		websocket.send("开始导入");
    	//加载层
  		/* layer.msg('导入中', {
  		    icon: 16,
  		    shade: 0.1,
  			time:false
  		}); */
		$('.input_from').attr('disabled',true);
		$.ajax({
			url:'/admin/dbTable/importTable.shtml',
			type:'POST',
			data: $('#import_form').serialize(),
			dataType: "json",
			//async:false,
			success: function(data) {
				if(data.code==200){
			    	add(100); 
			    	/* setTimeout(function(){
						
					}		
					, 1000); */
					layer.alert(data.msg, {
						skin: 'layui-layer-molv', //样式类名
						closeBtn: 0,
						anim: 4 //动画类型
					});
				}else{
					layer.msg(data.msg, { icon: 2});
				}
				$('.input_from').attr('disabled',false);
              	//layer.closeAll('loading'); 
			},
			error : function(data) {  
				layer.msg("数据库保存失败", { icon: 2});  
				$('.input_from').attr('disabled',false);
              	layer.closeAll('loading'); 
		    } 
		});
	});
	
   

    //连接发生错误的回调方法
    websocket.onerror = function () {
        //setMessageInnerHTML("WebSocket连接发生错误");
    	add(100);
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
        //init(0);
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        //setMessageInnerHTML(event.data);
    	percentage = event.data;
    	console.info(percentage);
    	add(percentage);
    	/* if(percentage >= 1){
    		closeWebSocket();
    	} */
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
        /* layer.msg("WebSocket已关闭");   */
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }

    //关闭WebSocket连接
    function closeWebSocket() {
    	if(websocket.readyState==1){
    		websocket.close();
    	}
    }

	</script>
</body>
</html>