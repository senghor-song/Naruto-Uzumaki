<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<body class="bodyCss">
       	<jsp:include page="../common/header.jsp"></jsp:include>
        <!---->
        <section>
        	<jsp:include page="../common/leftNav.jsp"></jsp:include>
            <div class="mainRight">
                <div class="pancl_pd">
                    <div class="new-people-box early_warning_box">
                        <form id="form1" action="/admin/password.shtml" method="post">
                            <div class="tit_menu_list">
                                <a href="javascript:;" class="m_menu_item">
                                    <i class="iconfont"></i>
                                    <em>首页</em>
                                </a>
                                <i class="fl split_gt">&gt;</i>
                                <a href="/admin/pwview.shtml" class="m_menu_item">
                                    <em>修改密码</em>
                                </a>
                            </div>
                            <div class="mgt20 lieguan-box">
                                <dl class="new_peo_list">
                                    <dt>原密码：</dt>
                                    <dd>
                                        <input type="password" name="oldPw" value="" datatype="*4-10" nullmsg="请填写原密码" class="txt_in txt">
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
                                    <dt>新密码：</dt>
                                    <dd>
                                        <input type="password" name="newPw" value="" datatype="*4-10" nullmsg="请填写新密码" class="txt_in txt">

                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
                                    <dt>确认新密码：</dt>
                                    <dd>
                                        <input type="password" name="newPw2" value="" datatype="*4-10" recheck="newPw" nullmsg="请再次输入确认新密码" errormsg="两次密码输入不一致" class="txt_in txt">
                                    </dd>
                                </dl>
                            </div>
                            <div class="state-save sub_btn_box">
                                <input type="button" onclick="javascript:history.back(-1);" value="返回上一页" class="back_next" />
                                <input type="submit" value="保存" class="sub_btn" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    <!--js-->
    <script src="${base }/resources/admin/js/jquery-1.9.1.min.js"></script>
    <script src="${base }/resources/admin/js/layer.js"></script>
    <!--验证-->
    <script src="${base }/resources/admin/js/jquery.form.js"></script>
    <script src="${base }/resources/admin/js/Validform_v5.3.2/Validform_v5.3.2.js"></script>
    <!--自写-->
    <script src="${base }/resources/admin/js/layout.js"></script>
    <!--拖拽-->
    <script src="${base }/resources/admin/js/gridster/jquery.gridster.min.js"></script>
    <!---下拉框 -->
    <script src="${base }/resources/admin/js/common.js"></script>
    <!--日期-->
    <script src="${base }/resources/admin/js/My97DatePicker/lang/zh-cn.js"></script>
    <script src="${base }/resources/admin/js/My97DatePicker/WdatePicker.js"></script>
</body>
</html>