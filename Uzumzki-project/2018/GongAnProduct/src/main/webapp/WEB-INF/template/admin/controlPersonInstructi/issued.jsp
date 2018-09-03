<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<title><ruiec:title/></title>
    <link rel="stylesheet" href="${base }/resources/admin/css/layer.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/common.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/style.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/index.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/quote.css" />
</head>
<body>
    <div class="pd15_25 lay_wind lay_wind4" id="lay_wind4">
        <div class="lay_form">
        	 <!--  -->
            <form action="/admin/controlPersonInstructi/issued.shtml" method="post" id="form_layer">
            	<input type="hidden" name="type" value="${type }"/>
            	<input type="hidden" name="id" value="${id }"/>
                <div class="lay_form_wrap"> 
                	<style>
                		.lay_form_chose{ font-size:14px; line-height:25px; overflow:hidden; }
                		.lay_form_chose a{ float:left; display:inline-block; padding:0 20px; color:#333333; border-bottom:1px solid #ddd;}
                		.lay_form_chose a.curr{ color:#0073d0; border-bottom:1px solid #0073d0;}
                		.lay_cont_chose{ overflow:hidden;} 
                	</style>
	                <div class="lay_form_chose">
	                	<c:if test="${unitList.size()>0 }">
	                		<a class="lay_chose_btn curr">单位</a>
	                	</c:if>
	                	<c:if test="${userList.size()>0 }">
	                		<a class="lay_chose_btn <c:if test="${unitList.size()==0  }">curr</c:if>">警员</a>
	                	</c:if>
	                </div>
	                <div class="mgt25 layform_cont_chose">
	                	<c:if test="${unitList.size()>0  }">
		                <div class="layform_cont">
			                <c:forEach items="${unitList }" var="list" varStatus="l">
			                    <label class="label_tocsin">
			                        <input type="checkbox" class="check" onclick="checkById(${list.id },this)" name="ids" value="${list.id }"<c:if test="${l.count==1}">datatype="choseIssued" nullmsg="请选择下发单位"</c:if>/>
			                        <em>${list.name }</em>
			                    </label>
			                </c:forEach>
		                </div>
		                </c:if>
		                <c:if test="${userList.size()>0 }">
		                <div class=" <c:if test="${unitList.size()>0  }">hideThis</c:if> layform_cont">
			                <c:forEach items="${userList }" var="list" varStatus="l">
			                    <label class="label_tocsin">
			                        <input type="checkbox" class="check" name="userIds" value="${list.id }"<c:if test="${l.count==1}">nullmsg="请选择下发警员"</c:if>/>
			                        <em>${list.name }</em>
			                    </label>
			                </c:forEach>
		                </div> 
		                </c:if>
	                </div> 
                </div>
                <div class="bar_line"></div>
                <div class="pdl0 lay_form_item">
                    <dl class="lay_form_list">
                        <dt>执行内容：</dt>
                        <dd>
                            <textarea name="content" datatype="*" nullmsg="请填写执行内容" maxLength="100" class="textarea" onkeyup="checkLen(this)"></textarea>
                        	<div class="mgl10 add_btn_wrap lhnormail">  
								<span class="tips"><i class="m_242"><em class="text_keyup_num">0</em>/100</i>（最多可输入100字）</span>
                            </div>
                        </dd>
                    </dl>
                </div>
                <div class="sub_btn_box">
                    <dl class="lay_form_list">
                        <dt>&nbsp;</dt>
                        <dd>
                            <input type="button" value="关闭" class="back_next"/>
                            <input type="submit" value="确认下发" class="sub_btn"/>
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
    $(".lay_form_chose .lay_chose_btn").click(function(){
    	var _index = $(this).index();
    	$(this).addClass("curr").siblings().removeClass("curr"); 
    	
    	var _theObj = $(this).parents(".lay_form_wrap").find(".layform_cont_chose");
    	_theObj.find(".layform_cont").addClass("hideThis");
    	/* "content" */ 
    	_theObj.find(".layform_cont").eq(_index).removeClass("hideThis");
    	//清除文本框验证 
    	$("textarea[name='content']").val("");
    	//清除对应checkbox验证
    	if(_index == "0"){
    		_theObj.find(".layform_cont").find("input[name='userIds']").removeAttr("datatype");
    		_theObj.find(".layform_cont").eq(_index).find("input[name='ids']").eq(0).attr("datatype","choseIssued");
    	}else{
    		_theObj.find(".layform_cont").find("input[name='ids']").removeAttr("datatype");
    		_theObj.find(".layform_cont").eq(_index).find("input[name='userIds']").eq(0).attr("datatype","choseUser");
    	};
    	
    	
    });
	//检查管理人员是否存在
	function checkById(id,_this){
	    $.ajax({
			url:'/admin/manager/checkById.shtml',
			type:"post",
			data:{"id":id},
			async:false,
			dataType: "json",
			success: function(data) {
				if(data.code==400){
					layer.msg(data.msg, { icon: 2});
					_this.checked=false;
				}
			},
			error : function(data) {  
				layer.msg("管理员查询失败", { icon: 2});  
				_this.checked=false;
		       } 
		});
	}    
	</script>
</body>
</html>