<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html >
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
          <!--头部-->
        <jsp:include page="../common/header.jsp"></jsp:include> 
        <!---->
        <section>
        	<jsp:include page="../common/leftNav5.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                    <form  action="/admin/notice/save.shtml" method="post" id="form1">
                        <div class="tit_menu_list">
                            <a href="/admin/common/main.shtml" class="m_menu_item">
                                <i class="iconfont">&#xe62a;</i>
                                <em>首页</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item">
                                <em>宣传管理</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="/admin/notice/list.shtml" class="m_menu_item">
                                <em>公告列表</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item">
                                <em>新增公告列表信息</em>
                            </a>
                            <span class="fr">
	                        	<input type="button" onclick="javascript:history.back(-1);" class="fr back_btn" value="返回上一页"/>
	                        </span>
                        </div>
                        <div class="mgt20 new-people-box form_box">
                            <dl class="new_peo_list">
                                <dt>标题：</dt>
                                <dd>
                                     <div class="fl left_item">
                                       <input type="text" class="txt_in" isnot="true" acttype="edit" datatype="*" nullmsg="请填写标题"  maxlength="30" name="title"/>
                                    </div>
                                </dd>
                            </dl>
	                         <dl class="new_peo_list">
	                                <dt>内容：</dt>
	                                <dd>
	                                    <textarea  maxlength="1000" isnot="true" acttype="edit" datatype="*" nullmsg="请填写内容" name="content" class="fl textarea" onkeyup="checkLen(this)"></textarea>
	                                     <div class="fl mgl10 mgt93 add_btn_wrap">  
											<span class="tips"><i class="m_242"><em class="text_keyup_num">0</em>/1000</i>（最多可输入1000字）</span>
	                                     </div>
	                                </dd>
	                            </dl>
                        </div>
                        <div class="state-save sub_btn_box">
                            <input onclick="javascript:history.back(-1);"
					          type="button" value="返回上一页" value="返回上一页" class="back_next" />
                            <input type="submit" value="保存" class="sub_btn"/>
                        </div>
                    </form>
                </div>
            </div>
        </section>
     <script src="${base }/resources/admin/js/jquery-1.9.1.min.js"></script>
    <script src="${base }/resources/admin/js/layer.js"></script>
    <!--表单验证-->
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