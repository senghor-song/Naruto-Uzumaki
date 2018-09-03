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
</head>
<body class="rest_select2">
    <div class="pd15_25 lay_wind lay_wind5" id="lay_wind1">
        <div class="lay_form">
            <form action="/admin/unit/update.shtml" method="post" id="form_layer">
                <div class="lay_form_wrap">
                    <dl class="lay_form_list">
                        <dt>单位编号：</dt>
                        <dd>
                            <div class="fl left_item">
                                <input type="text" isnot="true" acttype="edit" name="code" value="${code }" datatype="/^[A-Za-z0-9]+$/" class="txt_in " maxlength="2"
                                placeholder="输入填写单位编号" nullmsg="请填写单位编号" errormsg="单位编码请填写两位字母或数据">
                                <input type="hidden" isnot="true" acttype="edit" name="id" value="${unit.id }" class="txt_in ">
                                <input type="hidden" name="isPoliceSection" value="${unit.isPoliceSection }">
                            </div>
                        </dd>
                    </dl>
                    <dl class="lay_form_list">
                        <dt>单位名称：</dt>
                        <dd>
                            <div class="fl left_item">
                                <input type="text" isnot="true" acttype="edit" name="unitName" value="${unit.unitName }" datatype="*" class="txt_in "
                                maxlength="20" placeholder="输入填写单位名称" nullmsg="请填写单位名称">
                            </div>
                        </dd>
                    </dl>
                    <c:if test="${ isNextSelect == '1'}">
	                    <dl class="lay_form_list">
	                        <dt>所属上级部门</dt>
	                        <dd>
	                            <div class="select_box">
		                        	<select name="policeUnitId" class="js-example-basic-multiple" id="exampleItem">
										<c:if test="${ isNextSelect == '1' and isPoliceSection == '0'}">
			                            	<option value="">暂无</option>
										</c:if>
			                            <c:if test="${ isNextSelect == '0'}">
			                            	<option value="">暂无</option>
										</c:if>
			                        </select>
		                        </div>
	                        </dd>
	                    </dl>
					</c:if>
                </div>
                <div class="sub_btn_box">
                    <dl class="lay_form_list">
                        <dt>&nbsp;</dt>
                        <dd>
                            <input type="button" value="关闭" class="back_next">
                            <input type="submit" value="保存" class="sub_btn">
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
    <script type="text/javascript">
    	$(function(){
        	var str =""; 
    		$.post("/admin/unit/getPoliceUnit.shtml?isPolice=${unit.isPoliceSection}",function(data){
				var optionList = data.data;
				if( optionList !=null && optionList !="" && optionList != undefined　){ 
					for( var i=0; i<optionList.length;i++ ){ 
						var parentId;
						if(${unit.isPoliceSection}==1){
							parentId=${unit.policeTypesParentId==null?1:unit.policeTypesParentId};
						}else{
							parentId=${unit.parentId==null?1:unit.parentId}	;
						}
						if(parentId==optionList[i].id){
    						str +="<option value="+optionList[i].id+" selected='selected'>"+optionList[i].name+"</option>"; 
						}else{
    						str +="<option value="+optionList[i].id+">"+optionList[i].name+"</option>"; 
						}
					}
					$("#exampleItem").empty().append($(str)); 
				} 
			});
    		//$(".js-example-basic-multiple").click(function () { alert("Selected value is: "+$(".js-example-basic-multiple").select2("val"));});
    		$("#exampleY_N").change(function(){
    			unitIsPolice();
    		})
    		function unitIsPolice(){
    			var theVal = $("#exampleY_N").val();
    			str =""; 
    			var url = "";
    			if( theVal == 1 ){
    				url = "/admin/unit/getPoliceUnit.shtml?isPolice=1";
    			}else{
    				url = "/admin/unit/getPoliceUnit.shtml?isPolice=0";
    			}
    			$.post(url,function(data){
					var optionList = data.data;
					if( optionList !=null && optionList !="" && optionList != undefined　){ 
						for( var i=0; i<optionList.length;i++ ){ 
							var parentId;
							if(${unit.isPoliceSection}==1){
								parentId=${unit.policeTypesParentId==null?1:unit.policeTypesParentId};
							}else{
								parentId=${unit.parentId==null?1:unit.parentId}	;
							}
							if(parentId==optionList[i].id){
	    						str +="<option value="+optionList[i].id+" selected='selected'>"+optionList[i].name+"</option>"; 
							}else{
	    						str +="<option value="+optionList[i].id+">"+optionList[i].name+"</option>"; 
							}
						}
						$("#exampleItem").empty().append($(str)); 
					} 
				}); 
        	}
    	})
    </script>
</body>
</html>