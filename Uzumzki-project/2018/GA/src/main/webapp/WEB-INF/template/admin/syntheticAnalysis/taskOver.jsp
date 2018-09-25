<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<title><ruiec:title/></title>
    <link rel="stylesheet" href="${base }/resources/admin/js/select/select2.css" >
    <link rel="stylesheet" href="${base }/resources/admin/css/layer.css" >
    <link rel="stylesheet" href="${base }/resources/admin/css/common.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/style.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/index.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/quote.css"> 
    <style>
    	.select2-container .select2-selection--single{ max-width:262px;}
    </style>
</head>
<body class="rest_select2">
    <div class="pd15_25 lay_wind" id="lay_wind1">
    	<style>
    		.lay_wind .lay_form_list dt{ width:27%;}
    		.lay_wind .lay_form_list dd{ width:60%;}
    	</style>
        <div class="lay_form">
            <form action="/admin/syntheticAnalysis/saveTaskOver.shtml?taskId=${taskDTO.taskId}" method="post" id="form_layer">
                <div class="lay_form_wrap">
                    <dl class="lay_form_list">
                        <dt>结束理由：</dt>
                        <dd>
                            <div class="fl left_item">
                                <textarea name="overReason" isnot="true" class="fl textarea text_keyup" placeholder="请输入结束理由"  maxlength="200" onkeyup="checkLen(this)"
                               	 	nullmsg="请输入结束理由" errormsg="请输入结束理由" maxlength="200" datatype="*"></textarea>
                                <div class="fl  add_btn_wrap">  
								<span class="tips"><i class="m_242"><em class="text_keyup_num">0</em>/200</i>（最多可输入200字）</span>
                                </div>	
                            </div>
                        </dd>
                    </dl>
                </div>
                <div class="sub_btn_box">
                    <dl class="lay_form_list">
                        <dt>&nbsp;</dt>
                        <dd>
                            <input type="button" value="关闭" class="back_next">
                            <input type="submit" value="结束" class="sub_btn">
                        </dd>
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
    <!--下拉框--> 
    <script type="text/javascript" src="${base }/resources/admin/js/select/select2.js"></script>
</body>
</html>