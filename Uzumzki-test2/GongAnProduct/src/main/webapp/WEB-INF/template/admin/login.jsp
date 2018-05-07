<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<title><ruiec:title/></title>
    <link rel="stylesheet" href="${base }/resources/admin/css/layer.css" >
    <link rel="stylesheet" href="${base }/resources/admin/css/common.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/style.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/index.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/quote.css"> 
    <style>
		body{ min-height: 100%; height: 100%; overflow:auto;} 
	</style>
</head>
<body>
    <!-- 模糊样式style="float: left;width: 100%;background-repeat: no-repeat;background-position: center;background-size: cover;filter: blur(15px);" -->
    <div class="login_fiexd" >
      <img name="image" id="images" src="/resources/admin/img/m_loginbg.png"/>
    </div>
    <div class="login_bg" id="m_windowHei">
        <div class="tc login_box">
            <form class="register_form" id="register_form">
                <span class="m_logo"><img src="${base }/resources/admin/img/m_logo.png" /></span>
                <div class="login_cont">
                    <dl>
                        <dt><i class="iconfont">&#xe617;</i></dt>
                        <dd>
                            <input id="idCard" type="text" name="idCard" class="txt" placeholder="请输入您的身份证号码" verify="" isnot="true" msg="请输入您的身份证号码" value=""/>
                        </dd>
                    </dl>
                    <dl>
                        <dt><i class="iconfont">&#xe622;</i></dt>
                        <dd>
                            <input id="password" type="password" id="mima" name="password" class="txt" placeholder="请输入您的登录密码" verify="" isnot="true" value="" msg="请填写6-16位，字母与数字组合的密码">
                        </dd>
                    </dl>
                    <input id="but1" type="button" class="mgt20 sub_btn button" value="登 录" style="cursor: pointer;color: white;">
                </div>
            </form>
        </div>
    </div>
    <!--js-->
    <script src="${base }/resources/admin/js/jquery-1.9.1.min.js"></script>
    <script src="${base }/resources/admin/js/layer.js"></script>
    
    <%-- <script src="${base }/resources/admin/js/jquery.validate.js"></script> --%>
    <%-- <script src="${base }/resources/admin/js/jquery-validate-1.js"></script>  --%>
    <!--验证-->
    <%-- <script src="${base }/resources/admin/js/jquery.form.min.js"></script>
    <script src="${base }/resources/admin/js/jquery.ruiValidate.js"></script> --%>
    <!--自写-->
    <script src="${base }/resources/admin/js/layout.js"></script>
    <!--拖拽-->
    <script src="${base }/resources/admin/js/gridster/jquery.gridster.min.js"></script>
    <!---下拉框 -->
    <script src="${base }/resources/admin/js/common.js"></script>
    <!--日期-->
    <script src="${base }/resources/admin/js/My97DatePicker/lang/zh-cn.js"></script>
    <script src="${base }/resources/admin/js/My97DatePicker/WdatePicker.js"></script>
    <script>
        $(function(){
            var _winHei = $(window).height();
            //$("#m_windowHei").css({"min-height":_winHei});  
            var _hei = $(".login_box").height();
            var _Marhei = (_winHei-_hei-50)/2;
            $(".login_box").css({"margin-top":""+_Marhei+"px"}); 
        //})  
        //$(function(){
        	$(document).keydown(function(event){ 
		    	if(event.keyCode==13){ 
					$("#but1").click(); 
		        } 
        	}); 
	   		$('#but1').on('click',function(){
	   			clearCookie("headHref");
	        	clearCookie("navHref");
	   			$.ajax({
	    		    url:'/admin/login.shtml',
	    		    type:'POST',
	    		    data:{
	    		    	idCard:$('#idCard').val(),
	    		    	password:$('#password').val()
	    		    },
	    		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    		    success:function(data,textStatus,jqXHR){
	    		    	if(data.code == '200'){
	    		    		layer.msg(data.msg,{icon: 1, shade: 0.5 });  
	    		        	if( data.url !="" && data.url !=null && data.url != undefined ){ 
								setTimeout(function(){
									window.location.href = data.url;
								},1000);
							}  
	    		    	}else{
	    		    		layer.msg(data.msg,{icon: 2});
	    		    	}
	    		    },
	    		})
	   		})
	   	})
    </script>
    <script type="text/javascript">
    $(function(){
    	$.ajax({
		    url:'/admin/banner/defaultPic.shtml',
		    type:'POST',
		    dataType:'json',
		    success:function(data){
		    	$("#images").attr('src',data.msg);
		    }
		})
    })
    
    </script>
</body>
</html>