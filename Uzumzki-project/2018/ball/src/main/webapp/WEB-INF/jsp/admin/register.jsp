<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>注册</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="/admin/static/image/logo.png" type="image/x-icon"/>
    <link rel="stylesheet" href="/admin/static/css/site.css">
    <style>
        body .login0 { background: #e9ecef; }
        body .login1 {
            width: 431px;
            margin-top: 7%;
            background: #fff;
            border-radius: 5px;
        }
        body .login2 {
            width: 431px;
            height: 80px;
            border-bottom: 1px solid #ececec
        }
        body .login3 {
            font-size: 24px;
            top: 28px;
            position: relative;
        }
        body .login4 {
            width: 431px;
            padding: 0px 32px 10px 32px;
        }
        body .login5 {
            line-height: 30px;
        }
        body .login6 {
        	margin-bottom: 10px;
            width: 60%;
            float: left;
        }
        body .login7 {
            margin: 0px 0px 0px 15px !important;
    		line-height: 38px !important;
        }
        body .login14 {
            margin-top: 21px;
        }
        body .login15 {
            margin-top: 20px;
        }
        body .codeBtn {
	        margin-left: 10px;
        }
        .btn[disabled] {
	        cursor:not-allowed;
	        opacity:.65;
	        box-shadow:0 0 black;
        }
        .login-wrap{width: 270px;
	    height: 268px;
	    margin: 0 auto;
	    overflow: hidden;
	    transform: scale(0.88);}
	    .loginWxQRcode{
	    width:300px;margin-top: -68px; margin-left: -15px;
	    }
    </style>
</head>

<body class="h-100 login0" style="background: url(/admin/static/image/bg2.jpg) no-repeat center center;">
    <div class="login-box shadow-lg h-100 login1">
        <div class="clearfix login2">
            <div class="text-center login3">后台注册</div>
        </div>
        <div class="login-wrap">
	        <div id="wxQRcode" class="loginWxQRcode">
	        </div>
        </div>
        <form action="/admin/staff/register" method="post" class="login4" >
            <div class="has-feedback clearfix login5">
				<input type="text" class="form-control login6" placeholder="真实姓名" id="name">
                <label class="login7">真实姓名</label>
            </div>
            <div class="has-feedback clearfix login5">
                <input type="text" class="form-control login6" placeholder="推荐码" id="pushCode" value="8031">
                <label class="login7">推荐码</label>
            </div>
            <div class="has-feedback clearfix login5">
                <input type="text" class="form-control login6" placeholder="手机号码" id="phone">
                <label class="login7">手机号码</label>
            </div>
            <div class="has-feedback clearfix login5">
                <input type="text" class="form-control login6" placeholder="验证码" id="code">
                <input id="btnSendCode1" type="button" class="btn btn-default codeBtn" value="获取验证码" onclick="sendMessage()" />
                <label class="login7"></label>
            </div>
            <div class="form-group has-feedback clearfix login14">
                <div class=" login15">
                    <button type="button" id="postBtn" class="btn btn-primary btn-block btn-flat">提交注册</button>
                </div>
            </div>
        </form>

    </div>

    <!-- jQuery -->
    <script src="/admin/static/plugins/jquery/jquery.min.js"></script>
    <script src="/admin/static/plugins/verify/js/verify.min.js"></script>
    <script src="/admin/static/js/jqBootstrapValidation-1.3.7.min.js"></script>
    <script src="/admin/static/js/jq-ext.js"></script>
    <script src="/admin/static/plugins/layer/layer.js"></script>
    <script src="https://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
    <script>
    var phoneReg = /(^1[3|4|5|7|8]\d{9}$)|(^09\d{8}$)/;//手机号正则 
	var count = 60; //间隔函数，1秒执行
	var InterValObj1; //timer变量，控制时间
	var curCount1;//当前剩余秒数
	/*第一*/
	function sendMessage() {
		curCount1 = count;		 		 
		var phone = $.trim($('#phone').val());
		if (!phoneReg.test(phone)) {
			layer.msg(" 请输入有效的手机号码"); 
			return false;
		}
		//向后台发送处理数据
		var phone = $("#phone").val();
    	$.ajax({  
            type : "POST",  //提交方式  
            url : "/admin/common/getSMSCode",//路径  
            data : {  
                "phone" : phone
            },//数据这里使用的是Json格式进行传输  
            dataType:"json",
            success : function(result) {//返回数据根据结果进行相应的处理  
            	if ( result.code == 200 ) { 
                	layer.msg("发送成功,请注意查收");

            		//设置button效果，开始计时
            		$("#btnSendCode1").attr("disabled", "true");
            		$("#btnSendCode1").val( + curCount1 + "秒再获取");
            		InterValObj1 = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
                } else {  
            		layer.confirm(result.msg, {
            			btn: ['确定'] //按钮
            		});
                }  
            }  
        });
	}
	function SetRemainTime() {
		if (curCount1 == 0) {                
			window.clearInterval(InterValObj1);//停止计时器
			$("#btnSendCode1").removeAttr("disabled");//启用按钮
			$("#btnSendCode1").val("重新发送");
		}
		else {
			curCount1--;
			$("#btnSendCode1").val( + curCount1 + "秒再获取");
		}
	} 
    $(function(){
    	
		var obj = new WxLogin({
			self_redirect : true,
			id : "wxQRcode",
			appid : "wxa6c95fe48935f880",
			scope : "snsapi_login",
			redirect_uri : "https://beta.ball.ekeae.com/admin/common/authLogin",
			state : "${state}",
			style : "black",
			href : ""
		});
        
        $("#postBtn").click(function () {
        	var name = $("#name").val();
        	var phone = $("#phone").val();
        	var code = $("#code").val();
        	var pushCode = $("#pushCode").val();
        	var state = "${state}";
        	if(name == ''){
        		layer.msg("姓名必须填写");
        		return;
        	}
    		if(phone == ''){
        		layer.msg("手机号必须填写");
        		return;
        	}
    		if(code == ''){
        		layer.msg("验证码必须填写");
        		return;
        	}
    		if(pushCode == ''){
        		layer.msg("推荐码必须填写");
        		return;
        	}
            $.ajax({  
                type : "POST",  //提交方式  
                url : "/admin/staff/register",//路径  
                data : {  
                    "name" : name,
                    "tel" : phone,
                    "code" : code,
                    "pushCode" : pushCode,
                    "state" : state
                },//数据，这里使用的是Json格式进行传输  
                dataType:"json",
                success : function(result) {//返回数据根据结果进行相应的处理  
                    if ( result.code == 200 ) {  
                		layer.msg(result.msg);
                		window.location.href="/admin/common/login";
                    } else {  
                		layer.confirm(result.msg, {
                			btn: ['确定'] //按钮
                		});
                    }  
                }  
            });
        });
    })

    function draw(show_num) {
        var canvas_width=$('#canvas').width();
        var canvas_height=$('#canvas').height();
        var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
        var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
        canvas.width = canvas_width;
        canvas.height = canvas_height;
        var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
        var aCode = sCode.split(",");
        var aLength = aCode.length;//获取到数组的长度
        
        for (var i = 0; i <= 3; i++) {
            var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
            var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
            var txt = aCode[j];//得到随机的一个内容
            show_num[i] = txt.toLowerCase();
            var x = 10 + i * 20;//文字在canvas上的x坐标
            var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
            context.font = "bold 23px 微软雅黑";

            context.translate(x, y);
            context.rotate(deg);

            context.fillStyle = randomColor();
            context.fillText(txt, 0, 0);

            context.rotate(-deg);
            context.translate(-x, -y);
        }
        for (var i = 0; i <= 5; i++) { //验证码上显示线条
            context.strokeStyle = randomColor();
            context.beginPath();
            context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.stroke();
        }
        for (var i = 0; i <= 30; i++) { //验证码上显示小点
            context.strokeStyle = randomColor();
            context.beginPath();
            var x = Math.random() * canvas_width;
            var y = Math.random() * canvas_height;
            context.moveTo(x, y);
            context.lineTo(x + 1, y + 1);
            context.stroke();
        }
    }

    function randomColor() {//得到随机的颜色值
        var r = Math.floor(Math.random() * 256);
        var g = Math.floor(Math.random() * 256);
        var b = Math.floor(Math.random() * 256);
        return "rgb(" + r + "," + g + "," + b + ")";
    }
        
    </script>
</body>

</html>