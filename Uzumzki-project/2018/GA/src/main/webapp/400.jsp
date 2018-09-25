<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body>
    <style>
        body{ height: 100%; overflow: auto;}
        #m_windowHei{ overflow:hidden;}
        .four_wrap{ margin:0px auto 0 auto; overflow: hidden;}
        .four_wrap .four_img{ margin:auto; height: 185px; width:597px;}
        .four_wrap .four_img img{ width: 100%; height: 100%;}
        .four_href{ margin-top: 8px;}
        .four_href a{ font-size:18px; margin-right: 10px; color:#0073d0; text-decoration: underline;}
    </style>
    <div id="m_windowHei">
        <div class="tc four_wrap">
            <div class="four_img">
                <img src="${base }/resources/admin/img/404_IMG.png"/>
            </div>
            <div class="four_href">
                <a href="/admin/common/main.shtml">返回首页</a>
                <a href="">刷新等待</a>
            </div>
        </div>
    </div>
    <!--js-->
    <script src="${base }/resources/admin/js/jquery-1.9.1.min.js"></script>
    <script src="${base }/resources/admin/js/layer.js"></script>
    <script src="${base }/resources/admin/js/jquery.validate.js"></script>
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
            $("#m_windowHei").css({"min-height":_winHei});
            var _hei = $(".four_wrap").height();
            var _marTop = (_winHei-_hei-100)/2;
            $(".four_wrap").css({"margin-top":_marTop});
        });
    </script>
</body>
</html>
