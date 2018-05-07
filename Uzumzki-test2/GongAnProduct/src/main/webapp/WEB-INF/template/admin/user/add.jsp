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
    <style type="text/css">
    	/* .select2-container--default .select2-selection--single{ height:34px;} */
    	/* .select2-container--default .select2-selection--single .select2-selection__rendered{ line-height:34px;} */
    	.select2-container .select2-selection--single{ max-width:233px;}
    	.select2-container--default .select2-selection--single .select2-selection__arrow{ height:34px;}
    	.searh_list .select_box .select2-container{ float:left; z-index: 9999;}
    	.searh_list .select_box .select2-container--default .select2-selection--single{ height:30px;}
    	.searh_list .select_box .select2-container--default .select2-selection--single .select2-selection__rendered{ line-height:30px;}
    	/* .searh_list .select_box .select2-container--default .select2-selection--single .select2-selection__arrow{ height:30px;} */
   		.select2-container{ width:230px;}
   		.select2-container--default .select2-results > .select2-results__options {
		  font-size:12px;
		  font-family:"微软雅黑" !important;
		  -webkit-text-stroke-width:0;	
		  max-height: 150px;
		  overflow-y: auto; }
    </style>
</head>
<body>
    <div class="pd25_40 lay_wind" id="lay_wind1">
        <div class="lay_form">
            <form id="form_layer" action="/admin/user/save.shtml" method="post">
            	<input type="hidden" name="id" value="${userz.id}">
                <div class="lay_form_wrap">
                	<dl class="lay_form_list">
                        <dt>警员姓名：</dt>
                        <dd>
                            <div class="fl left_item">
                                <input name="policeName" type="text" isnot="true" acttype="edit" datatype="*" nullmsg="填写姓名" class="txt_in" value="${userz.policeName}">
                            </div>
                        </dd>
                    </dl>
                    <dl class="lay_form_list">
                        <dt>警员性别：</dt>
                        <dd>
                            <div class="fl left_item">
                               <select name="sex" class="sel" datatype="sex" nullmsg="填写性别">
                            		<option value="男">男</option>
                            		<option value="女" <c:if test="${userz.sex == '女'}">selected="selected"</c:if>>女</option>
                                </select>
                            </div>
                        </dd>
                    </dl>
                    <dl class="lay_form_list">
                        <dt>电话：</dt>
                        <dd>
                            <div class="fl left_item">
                                <input name="phone" type="text" isnot="true" acttype="edit" datatype="m" nullmsg="填写电话" errormsg="请填写正确格式的手机号" class="txt_in " value="${userz.phone}"> 
                            </div>
                        </dd>
                    </dl>
                    <dl class="lay_form_list">
                        <dt>身份证号：</dt>
                        <dd>
                            <div class="fl left_item">
                                <input name="idCard" type="text" verify="isPwd" isnot="true" acttype="edit" datatype="isIDCard" errormsg="填写正确格式身份证号码" nullmsg="填写身份证号码" class="txt_in " value="${userz.idCard}"> 
                            </div>
                        </dd>
                    </dl>
		            <c:if test="${userz.id == null}">
		            	<dl class="lay_form_list">
		                    <dt>密码：</dt>
		                    <dd>
		                        <div class="fl left_item">
		                            <input name="password" type="password" verify="isPwd" datatype="*6-16" nullmsg="请输入密码" errormsg="请输入6-16位密码" class="txt_in " >
		                        </div>
		                    </dd>
		                </dl>
		            </c:if>
	                <dl class="lay_form_list">
                         <dt>单位：</dt>
                         <dd>
                             <div class="select_box">
                                 <select name="unitName1" class="unit1" id="unit1">
                                 	<c:if test="${unitOne==null && fn:length(unitOne) == 0}">
                                 		<option value='-1'>暂无</option>
                                 	</c:if>
                                 	<c:forEach items="${unitOne}" var="list">
                                 		<option value="${list.id}">${list.unitName}</option>
                                 	</c:forEach>
                                 </select>
                                </div>
                         </dd>
                     </dl>
                     <dl class="lay_form_list">
                    	<dt>&nbsp;</dt>
                      	<dd>
                           	 <div class="select_box">
                                 <select name="unitName2" class="unit2" id="unit2">
                                 	<%-- <c:forEach items="${unitTwo}" var="list">
                                 		<option value="${list.id}">${list.name}</option>
                                 	</c:forEach> --%>
                                 </select>
                             </div>
                         </dd>
                      </dl>
                </div>
                <div class="sub_btn_box">
                    <dl class="lay_form_list">
                        <dt>&nbsp;</dt>
                        <dd>
                            <input type="button" value="关闭" class="back_next">
                            <input type="submit" value="保存" class="sub_btn" id="sub">
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
    <script type="text/javascript" th:inline="javascript">
	    $(function(){
	    	var unit1 = '${unit1}';
	    	if(unit1 != ''){
	    		$('#unit1').val(${unit1}).trigger('change');
	    	}else{
	    		$('#unit1').trigger('change');
	    	}
	    	$('#unit1').select2();
	    	$('#unit2').select2();
		});
		$('#unit1').on('change',function(){
			if($('#unit1').val()!=''){
				$.ajax({
		   		    url:'/admin/unit/getNextUnit.shtml',
		   		    type:'POST',
		   		    data:{
		   		        id:$('#unit1').val()
		   		    },
		   		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		   		    success:function(data,textStatus,jqXHR){
		   		    	if(data.code == '200'){
		   		    		$('#unit2').html('');
		   		    		$('#unit2').append("<option value='-1'>暂无</option>");
		   		    		for(var i=0;i<data.data.length;i++){
		                       	$('#unit2').append("<option value="+data.data[i].id+">"+data.data[i].name+"</option>");
		                    }
		   		    		var unit2 = ${unit2} + '';
		   		    		if(unit2 != ''){
		   		    			$('#unit2').val(${unit2}).trigger('change');
		   		    		}
		   		    	}else{
		   		    		$('#unit2').append("<option value='-1'>暂无</option>");
		   		    		/* layer.msg(data.msg,{icon: 2}); */
		   		    	}
		   		    },
		   		})	
			}else{
				$('#unit2').html('');
				$('#unit2').append("<option value='-1'>暂无</option>");
			}
		});
    </script>
</body>
</html>