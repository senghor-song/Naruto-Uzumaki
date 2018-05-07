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
                    <form  id="form2">
                        <div class="tit_menu_list">
                            <a href="/admin/common/main.shtml" class="m_menu_item">
                                <i class="iconfont">&#xe62a;</i>
                                <em>首页</em>
                            </a>
                             <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item">
                                 <em>字典库管理</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="/admin/dictionaryType/list.shtml" class="m_menu_item">
                                <em>字典类型列表</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item">
                                <em>编辑字典类型信息</em>
                            </a>
                            <span class="fr">
	                        	<input type="button" onclick="javascript:history.back(-1);" class="fr back_btn" value="返回上一页"/>
	                        </span>
                        </div>
                         <input type="hidden" name="id" value="${dictionaryType.id }" />
                         <input type="hidden" name="itemCode" value="${dictionaryType.itemCode}">
                        <div class="mgt20 new-people-box form_box">
                            <dl class="new_peo_list">
                                <dt>中文名：</dt>
                                <dd>
                                    <div class="fl left_item">
                                       <input type="text" class="txt_in"  datatype="/[\u4e00-\u9fa5·]$/" nullmsg="请填写中文名" errormsg="请填写中文" maxlength="10" name="itemName" value="${dictionaryType.itemName}" />
                                    </div>
                                </dd>
                            </dl>
                            <dl class="new_peo_list">
                                <dt>英文名：</dt>
                                <dd>
                                    <div class="fl left_item">
                                       <input type="text" class="txt_in" name="itemCode" disabled="disabled" value="${dictionaryType.itemCode}" />
                                    </div>
                                </dd>
                            </dl>
                            <dl class="new_peo_list">
                                <dt>排序号：</dt>
                                <dd>
                                    <div class="fl left_item">
                                        <input type="text" class="txt_in" name="sort" datatype="isNum" nullmsg="排序号请输入整数" errormsg="排序号请输入整数"  value="${dictionaryType.sort}"  maxlength="2" />
                                    </div>
                                </dd>
                            </dl>
                            <dl class="new_peo_list">
                                <dt>是否默认：</dt>
                                <dd>
                                    <div class="fl rule-single-select single-select">
                                        <select class="sel" name="isUse" >
                                            <option value="1">是</option>
                                            <option <c:if test="${dictionaryType.isUse==0 }">selected="selected"</c:if>value="0">否</option>
                                        </select>
                                    </div>
                                </dd>
                            </dl>
                            <dl class="new_peo_list">
                                <dt>字典描述：</dt>
                                <dd>
                                    <textarea class="fl textarea" name="describe" maxlength="80" onkeyup="checkLen(this)">${dictionaryType.describe }</textarea>
                                    <div class="fl mgl10 mgt93 add_btn_wrap">  
										<span class="tips"><i class="m_242"><em class="text_keyup_num">0</em>/80</i>（最多可输入80字）</span>
                                    </div>
                                </dd>
                            </dl>
                        </div>
                        <div class="state-save sub_btn_box">
                            <input onclick="javascript:history.back(-1);" type="button" value="返回上一页" class="back_next">
                            <input type="submit" value="保存" style="color:white;" class="sub_btn">
                        </div>
                    </form>
                </div>
            </div>
        </section>
	       <!--js-->
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
    <script type="text/javascript">
		if( $("#form2").size() > 0 ){  
			$.Tipmsg.r=null;
			$("#form2").Validform({
				tiptype:function(msg){ 
					if( msg != null && msg != "" && msg != undefined ){
						layer.msg(msg);	
					};
				}, 
				//tipSweep:true,
				callback:function(form){  
					$(form).ajaxSubmit({
						type: 'POST',  
				        url: "/admin/dictionaryType/update.shtml", 
				        dataType: 'json',   
				        success:function(data, status, err) {
				        	if( data.code == "400" ){
				        		layer.msg(data.msg, { icon: 2, time: 1000 });
				        	}else{
			        			layer.msg(data.msg, { icon: 1, shade: 0.5 });
			        			if( data.url !="" ){
									setTimeout(function(){
										window.location.href = "/admin/dictionaryType/list.shtml";
									},1000);
								} 
				        	}  
				        } ,    
				        error : function(data) {  
						   layer.msg("字典类型数据填写有误", { icon: 2});  
					    } 
					})
					return false;   //防止表单自动提交   
				}
			 }); 
		}
	</script>
</body>
</html>