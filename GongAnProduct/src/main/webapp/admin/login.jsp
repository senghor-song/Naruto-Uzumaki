<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="css/layer.css" >
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/quote.css">
</head>
<body>
    <div class="login_fiexd"><img src="img/m_loginbg.png" /></div>
    <div class="login_bg" id="m_windowHei">
        <div class="tc login_box">
            <form class="register_form" id="register_form" checkby_ruivalidate="" action="/admin/login.shtml" method="post">
                <input id="password2" type="hidden" name="password">
                <span class="m_logo"><img src="img/m_logo.png" /></span>
                <div class="login_cont">
                    <dl>
                        <dt><i class="iconfont">&#xe617;</i></dt>
                        <dd>
                            <input type="text" value="" id="username" name="username" class="txt" verify="isLoginName" isnot="true" placeholder="请输入您的身份证号码" msg="请输入您的身份证号码">
                        </dd>
                    </dl>
                    <dl>
                        <dt><i class="iconfont">&#xe622;</i></dt>
                        <dd>
                            <input type="password" id="mima" class="txt" verify="isPwd" isnot="true" onpaste="return false" onselectstart="return false" placeholder="请输入您的登录密码" msg="请填写6-16位，字母与数字组合的密码">
                        </dd>
                    </dl>
                    <dl class="Code_inp">
                        <dt><i class="iconfont">&#xe60f;</i></dt>
                        <dd>
                            <input type="text;" id="" class="txt " isnot="true" onpaste="return false" onselectstart="return false" placeholder="请输入验证码" msg="请填写图片验证码">
                            <a class="imgCode">
                                <img class="code" src="img/img_code.jpg" />
                            </a>
                        </dd>
                    </dl>
                    <input id="but1" type="submit" class="mgt20 sub_btn button" value="登 录">
                </div>
            </form>
        </div>
    </div>
    <!--js-->
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/layer.js"></script>
    <script src="js/jquery.validate.js"></script>
    <!--自写-->
    <script src="js/layout.js"></script>
    <!--拖拽-->
    <script src="js/gridster/jquery.gridster.min.js"></script>
    <!---下拉框 -->
    <script src="js/common.js"></script>
    <!--日期-->
    <script src="js/My97DatePicker/lang/zh-cn.js"></script>
    <script src="js/My97DatePicker/WdatePicker.js"></script>
    <script>
        $(function(){
            var _winHei = $(window).height();
            $("#m_windowHei").css({"min-height":_winHei});
        })
    </script>
</body>
</html>