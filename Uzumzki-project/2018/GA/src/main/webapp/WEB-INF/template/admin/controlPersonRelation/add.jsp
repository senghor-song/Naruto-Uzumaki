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
    <link rel="stylesheet" href="${base }/resources/admin/css/layer.css" >
    <link rel="stylesheet" href="${base }/resources/admin/css/common.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/style.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/index.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/quote.css">
</head>
<body>
    <div class="pd15_25 lay_wind" id="lay_wind2">
        <div class="lay_form">
            <form action="/admin/controlPersonRelation/save.shtml" method="post" id=form_layer>
                <div class="lay_form_wrap">
                     <input id="controlPersonId" type="hidden" name="controlPersonId" value="${controlPersonId }" />
                     <dl class="lay_form_list">
                         <dt>关系：</dt>
                         <dd>
                             <div class="fl left_item">
                                 <select class="sel" name="typeId" datatype="*" nullmsg="请先去字典数据中添加关系类型">
                                 	 <c:forEach var="list" items="${personRelations }">
                                    	<option value="${list.id}">${list.itemName }</option>
                                     </c:forEach>
                                     <c:if test="${empty personRelations }">
                                    	<option value="">暂无</option>
                                     </c:if>
                                 </select>
                             </div>
                         </dd>
                     </dl>
                     <dl class="lay_form_list">
                         <dt>姓名：</dt>
                         <dd>
                             <div class="fl left_item">
                                 <input type="text" name="name" value="" isnot="true" class="txt_in " datatype="isName" nullmsg="请输入姓名" 
                                  maxlength="20" errormsg="请填写正确的姓名" placeholder="输入姓名" />
                             </div>
                         </dd>
                     </dl>
                     <dl class="lay_form_list">
                         <dt>手机号码：</dt>
                         <dd>
                             <div class="fl left_item">
                                 <input type="text"  name="phone" value="" isnot="true" class="txt_in " datatype="m" id="" nullmsg="请输入手机号码" 
                                 errormsg="请填写正确的手机号码" placeholder="输入手机号码" maxlength="11" />
                             </div>
                         </dd>
                     </dl>
                     <dl class="lay_form_list">
                         <dt>身份证号：</dt>
                         <dd>
                             <div class="fl left_item">
                                 <input type="text" isnot="true" maxlength="18" acttype="edit" name="idCard" value="" errormsg="请填写正确的身份证号"
                                 maxlength="18" datatype="isIDCard" class="txt_in " nullmsg="请输入身份证号" placeholder="输入身份证号"
                                 onkeyup="isIdCardKeyUp($(this))" >
                                 <span id="idCardMsg" style="color: red;"></span>
                             </div>
                         </dd>
                     </dl>
                     <dl class="lay_form_list">
                         <dt>备注：</dt>
                         <dd>
                             <div class="fl left_item">
                                 <input type="text" class="txt_in" name="remark" placeholder="输入备注" maxlength="50">
                             </div>
                         </dd>
                     </dl>

                 </div>
                 <div class="state-save sub_btn_box">
                     <input type="button" value="关闭" class="back_next">
                     <input type="submit" value="保存" class="sub_btn">
                 </div>
            </form>
        </div>
    </div>
    <!--js-->
    <script src="${base }/resources/admin/js/jquery-1.9.1.min.js"></script>
    <script src="${base }/resources/admin/js/layer/layer.js"></script>
    <script src="${base }/resources/admin/js/jquery.form.js"></script>
    <script src="${base }/resources/admin/js/common.js"></script>
    <script src="${base }/resources/admin/js/Validform_v5.3.2/Validform_v5.3.2.js"></script>
    <script type="text/javascript" th:inline="javascript">
	//<![CDATA[
	$(function(){
		// from表单ajax控制提交，并接受返回的json数据
	  	var options = {   
	        type: 'POST',  
	        url: '/admin/controlPersonRelation/save.shtml',  
	        success : function(xhr, status, err) {  
	        	layer.msg(xhr.msg, { icon: 1, shade: 0.5 });
				setTimeout(function(){
					window.parent.location= "/admin/controlPersonRelation/list.shtml?controlPersonId="+$('#controlPersonId').val(); //刷新父页面
					parent.layer.closeAll();
				}		
				, 1000);
	        } ,    
	        dataType: 'json',  
	        error : function(xhr, status, err) {  
	        	layer.msg(xhr.msg);             
	        }  
	    };  
	    $("#form").submit(function(){   
	        $(this).ajaxSubmit(options);   
	        return false;   //防止表单自动提交  
	    });
	})
	//身份证输入验证
	function isIdCardKeyUp(obj){
		var _val = $(obj).val();
		var personId = "${controlPersonId}";
		_val = _val.replace(/[^\d|xX]/g,"");
		$(obj).val(_val);
		if(_val.length==18 || _val.length==15){
			$.ajax({
	  			type:"POST",
	  			url:"/admin/controlPersonRelation/isIdCard.shtml",
	  			data: {idCard:_val,personId:personId},
	  			datatype:"json",
	  			success:function(data){ 
	  				if( data.code == 200 ){ 
	      				$("#idCardMsg").html(""); 
	  				}else{
	      				$("#idCardMsg").html(data.msg); 
	  				}
	  			}
	  		})
		}else{
			$("#idCardMsg").html(""); 
		}
	}
	</script>
</body>
</html>