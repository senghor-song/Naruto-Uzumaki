<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>

<form action="http://song123.iask.in/WebBackAPI/api/common/zhima" method="post">
	<input value="1" name="token" type="hidden">
	姓名：<input type="text" name="name" value="宋高俊111111111111111111"><br>
	身份证号：<input type="text" name="idcard" value="420117199708113513">
	<input type="submit">
</form>
     
</body>
<script src="/WebBackAPI/admin/static/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript">
	$.ajax({
	    url:"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa76f9c07c99468b2&redirect_uri=https://ball.ekeae.com/WebBackAPI/api/common/weixinLogin&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect",    //请求的url地址
	    async:false,//请求是否异步，默认为异步，这也是ajax重要特性
        dataType: 'jsonp',
	    type:"POST",   //请求方式
	    beforeSend:function(){
	        //请求前的处理
	        alert("1");
	    },
	    success:function(data){
	        //请求成功时处理
	        alert(data);
	        alert("2");
	    },
	    complete:function(){
	        //请求完成的处理
	        alert("3");
	    },
	    error:function(){
	        //请求出错处理
	        alert("4");
	    }
	});
</script>
</html>