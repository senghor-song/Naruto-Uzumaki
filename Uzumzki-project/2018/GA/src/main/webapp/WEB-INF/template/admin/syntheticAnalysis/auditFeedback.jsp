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
	<link rel="stylesheet" href="${base }/resources/admin/css/starability-minified/starability-all.min.css"/>
    <style>
    	.select2-container .select2-selection--single{ max-width:262px;}
    	/* all sign style */
		.ratyli .rate{color: #ccc; font-size: 24px;}
		
		/* empty sign style */
		.ratyli .rate-empty{color: #666;}
		
		/* full sign style after rating*/
		.ratyli.rated .rate-full{color: #fe5845;}
		
		/* active signs (hover)*/
		.ratyli .rate-active{color: #a94039;}
    </style>
</head>
<body class="rest_select2">
    <div class="pd15_25 lay_wind" id="lay_wind1">
    	<style>
    		.lay_wind .lay_form_list dt{ width:27%;}
    		.lay_wind .lay_form_list dd{ width:60%;}
    	</style>
        <div class="lay_form">
            <form action="/admin/syntheticAnalysis/saveAuditFeedback.shtml" method="post" id="form_layer">
                <div class="mgt15 lay_form_wrap">
                    <input type="hidden" name="taskDetailsId" value="${taskDTO.taskDetailsId }">
                    <input type="hidden" name="type" value="${type}">
                    <dl class="lay_form_list">
                        <dt>评分：</dt>
                        <dd>
                            <div class="fl left_item">
                            	<fieldset class="starability-growRotate"> 
								  <input type="radio" id="rate5-4" name="rating" value="5" onclick="radiopoints(this)"/>
								  <label for="rate5-4" title="满意"></label>
								  <input type="radio" id="rate4-4" name="rating" value="4" onclick="radiopoints(this)"/>
								  <label for="rate4-4" title="很好"></label>
								  <input type="radio" id="rate3-4" name="rating" value="3" onclick="radiopoints(this)"/>
								  <label for="rate3-4" title="一般"></label>
								  <input type="radio" id="rate2-4" name="rating" value="2" onclick="radiopoints(this)"/>
								  <label for="rate2-4" title="不好"></label>
								  <input type="radio" id="rate1-4" name="rating" value="1" onclick="radiopoints(this)"/>
								  <label for="rate1-4" title="很差"></label>
								</fieldset>
							    <!-- <span class="ratyli" data-rate="0"></span>
                                <input type="hidden" id="points" name="points" datatype="n" nullmsg="请选择评分" errormsg="请选择评分" maxlength="1"/> -->
                                <input type="hidden" id="points" name="points" datatype="n" nullmsg="请选择评分" errormsg="请选择评分" maxlength="1"/>
                            </div>
                        </dd>
                    </dl>
                </div>
                <div class="sub_btn_box">
                    <dl class="lay_form_list">
                        <dt>&nbsp;</dt>
                        <dd>
                            <input type="button" value="关闭" class="back_next">
                            <input type="submit" value="提交" class="sub_btn">
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
    <script src="${base }/resources/admin/js/select/select2.js"></script>
	<%-- <script src="${base }/resources/admin/js/ratyli/jquery.ratyli.min.js"></script>
    <script type="text/javascript">
	    $(function() {
			$(".ratyli").ratyli();
		});
    </script> --%>
    <script type="text/javascript">
	    function radiopoints(radio) {
		    var val=$(radio).val();
	    	$('#points').val(val);
            if(val==5){
            	$('#commentReason').remove();
            }else{
            	var comment = $('#commentReason').val();
            	if(comment == undefined){
                	var html="<dl class='lay_form_list' id='commentReason'>"+
		                        "<dt>扣分理由：</dt>"+
		                        "<dd>"+
		                            "<div class='fl left_item'>"+
		                                "<input type='text' name='comment' datatype='*' nullmsg='请输入扣分理由' errormsg='请输入扣分理由' maxlength='200' "+
		                                "placeholder='请输入扣分理由' class='txt_in '>"+
		                            "</div>"+
		                        "</dd>"+
		                    "</dl>";
                	
                	$('.lay_form_wrap').append(html);
            	}
            }
		};
    </script>
</body>
</html>