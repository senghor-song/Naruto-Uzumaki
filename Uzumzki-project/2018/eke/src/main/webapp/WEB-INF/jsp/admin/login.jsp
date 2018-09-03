<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/WebRelease/admin/static/css/site.css">
    <style>
        body .login0 {
            background: #e9ecef;
        }

        body .login1 {
            width: 431px;
            margin: 15% auto;
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
            padding: 34px 32px;
        }

        body .login5 {
            line-height: 30px;
        }

        body .login6 {
            width: 60%;
            float: left;
        }

        body .login7 {
            font-weight: normal;
            margin-top: 7px;
            margin-left: 15px;
        }

        body .login8 {
            margin-bottom: 1px;
            line-height: 44px;
            height: 44px;
        }

        body .login10 {
            width: 80px;
            float: left;
            position: relative;
            top: 5px;
            left: 12px;
        }

        body .login11 {
            clear: both;
        }

        body .login13 {
            margin: 17px;
            position: relative;
            top: 8px;
        }

        body .login14 {
            margin-top: 21px;
        }

        body .login15 {
            margin-top: 20px;
        }

        body .login17 {
            color: #222;
        }
    </style>
</head>

<body class="h-100 login0" style="background: url(/WebRelease/admin/static/image/bg1.jpg) no-repeat center center;">
    <div class="login-box shadow-lg h-100 login1">
        <div class="clearfix login2">
            <div class="text-center login3">后台登录</div>
        </div>
        <form action="/WebRelease/admin/staff/login" method="post" class="login4" >
            <div class="has-feedback clearfix login5">
                <input type="text" class="form-control login6" required="" placeholder="英文名" name="ename" id="ename" value="admin">
                <label class="login7">
                    <input type="checkbox"> 记住
                </label>
            </div>
            <div class="has-feedback clearfix login8">
                <input type="text" class="form-control login6" required="" placeholder="验证码">
                <div id="mpanel1" class="login10">
                </div>
            </div>
            <div class="form-group has-feedback clearfix login11">
                <input type="password" class="form-control login6" required="" placeholder="登录密码" name="password" id="password" value="admin">
                <a href="password.html" class="login13">忘记密码</a>
            </div>
            <div class="form-group has-feedback clearfix login14">
                <div class=" login15">
                    <button type="button" id="postBtn" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
            </div>
            <p class="clearfix login16">
                <a href="/WebRelease/admin/common/register" class="text-center login17">新用户注册</a>
            </p>
        </form>

    </div>

    <!-- jQuery -->
    <script src="/WebRelease/admin/static/plugins/jquery/jquery.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/verify/js/verify.min.js"></script>
    <script src="/WebRelease/admin/static/js/jqBootstrapValidation-1.3.7.min.js"></script>
    <script src="/WebRelease/admin/static/js/jq-ext.js"></script>
    <script src="/WebRelease/admin/static/plugins/layer/layer.js"></script>
    <script>
    $("#postBtn").click(function () {
    	var ename = $("#ename").val();
    	var password = $("#password").val();
    	if(ename == ''){
    		layer.msg("英文名必须填写");
    		return;
    	}
		if(password == ''){
    		layer.msg("密码必须填写");
    		return;
    	}
    	$.ajax({  
            type : "POST",  //提交方式  
            url : "/WebRelease/admin/staff/login",//路径  
            data : {  
                "ename" : ename,
                "password" : password
            },//数据，这里使用的是Json格式进行传输  
            dataType:"json",
            success : function(result) {//返回数据根据结果进行相应的处理  
                if ( result.code == 200 ) {  
            		layer.msg(result.msg);
            		window.location.href="/WebRelease/admin/common/index";
                } else {  
            		layer.confirm(result.msg, {
            			btn: ['确定'] //按钮
            		});
                }  
            }  
        }); 
    });
     
        /* $(function () {
            $('#mpanel1').codeVerify({

                //常规验证码type=1， 运算验证码type=2
                type: 1,

                //验证码宽度
                width: '80px',
                codeLength: 4,
                fontSize: '16px',
                //验证码高度
                height: '30px',

                //......更多参数设置请查阅文档

                //提交按钮的id名称
                btnId: 'postBtn',
                ready: function() {
                },
               	success : function() {
                    var code = $(".verify-code").text();
                    console.log(code)
                },
                error : function() {
                    var code = $(".verify-code").text();
                    console.log(code)
		    	}
            });

            $("#postBtn").click(function () {
                return true;
            });
        }); */
        
        
    </script>
</body>

</html>