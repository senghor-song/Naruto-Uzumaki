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
    <style type="text/css">
    .list_box{width:610px;height:355px;overflow:auto}
    .list_box .list_em{float:left;width:150px;height:30px;line-height:30px}
    </style>
</head>

<body>
    <div class="pd15_25 lay_wind" id="lay_wind2">
        <div class="lay_form">
            <form action="/admin/unit/saveLinkPolice.shtml" method="post" id="form_layer">
            	<input type="hidden" value="${unitId}" name="unitId">
            	<ul class="list_box">
	                <c:forEach var="list" items="${polices}">
	            		<li class="tab list_em" >
	            			<input type="checkbox" name="policesIds" value="${list.id }"
	            			<c:forEach var="type" items="${linkPolices}">
		                		<c:if test="${list.id eq type.dictionaryId}">
                        			checked="checked" class="checkbox"
                        		</c:if>
                   			</c:forEach>>
                   			<span calss="polices" onclick="checked($(this))">${list.itemName }</span>
	            		</li>
                    </c:forEach>
            	</ul>
                <div class="sub_btn_box">
                    <dl class="lay_form_list">
                        <dt>&nbsp;</dt>
                        <dd>
                            <input type="button" value="关闭" class="back_next">
                            <input type="submit" value="确认" class="sub_btn">
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
    <script type="text/javascript">
    function checked(police){ 
    	if($(police).parent().find("input[type='checkbox']").is(":checked")){
    		$(police).parent().find("input[type='checkbox']").prop('checked',false);
    		$(police).parent().find("input[type='checkbox']").removeClass("checkbox");
        }else{
        	var size = $(".checkbox").length + 1;
        	if(size<=10){
        		$(police).parent().find("input[type='checkbox']").prop('checked',true);
        		$(police).parent().find("input[type='checkbox']").addClass("checkbox");
            }else{
    			layer.msg("最多关联10个警种");
            }
        }
    	
    };
    </script>
</body>
</html>