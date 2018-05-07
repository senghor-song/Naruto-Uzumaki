<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<title><ruiec:title/></title>
    <link rel="stylesheet" href="${base }/resources/admin/css/layer.css">
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
                    <form id="form1">
                        <div class="tit_menu_list">
                            <a href="/admin/common/main.shtml" class="m_menu_item">
                                <i class="iconfont">&#xe62a;</i>
                                <em>首页</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item refresh_html">
                                <em>字典库管理</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="/admin/dictionary/list.shtml" class="m_menu_item">
                                <em>字典数据列表</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item refresh_html">
                                <c:if test="${parentId==null }"><em>新增字典数据</em></c:if>
    							<c:if test="${parentId!=null }"><em>添加下级</em></c:if>
                            </a>
                            <span class="fr">
	                        	<input type="button" onclick="javascript:history.back(-1);" class="fr back_btn" value="返回上一页"/>
	                        </span>
                        </div>
                        <input type="hidden" name="dictionaryType.id" value="${dataItemId}" />
                    	<input type="hidden" name="parentId" value="${parentId }" />
                        <div class="mgt15 new-people-box form_box">
                            <dl class="new_peo_list">
                                <dt>字典名称：</dt>
                                <dd>
                                    <input type="text" class="txt_in" maxLength="20" datatype="minName" nullmsg="请输入中文名称" style="width: 175px" name="itemName" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" />
                                </dd>
                            </dl>
                            <dl class="new_peo_list">
                                <dt>字典值：</dt>
                                <dd>
								<c:if test="${isPersonClass==1 }">
									<input type="text" class="txt_in" maxLength="12" style="width: 175px" name="itemValue" />
									<em class="fl mgl20"><font>*</font>人员类别字典值必须为12位编码</em>
								</c:if>
								<c:if test="${isPersonClass!=1 }">
									<input type="text" class="txt_in" maxLength="50" style="width: 175px" <c:if test="${type == 'warningLevel'}">readonly="readonly"</c:if> name="itemValue" />
								</c:if>
								<c:if test="${type == 'warningLevel'}">
									<input type="color" class="txt_in" id="color" style="width: 65px" />
								</c:if>
                                </dd>
                            </dl>
                            <dl class="new_peo_list">
                                <dt>字典排序：</dt>
                                <dd>
                                    <div class="fl left_item">
                                        <input type="text" maxLength="2" datatype="isNum" nullmsg="请输入整数" name="sortCode" class="txt_in" onkeyup='this.value=this.value.replace(/\D/gi,"")'>
                                    </div>
                                    <em class="fl mgl20"><font>*</font>数字越小越靠前</em>
                                </dd>
                            </dl>
                            <dl class="new_peo_list">
                                <dt>能否添加下级：</dt>
                                <dd>
                                    <div class="fl rule-single-select single-select">
                                        <select class="sel" name="isDef">
                                            <option value="0">否</option>
                                            <c:if test="${level==null || level lt 2}">
                                            <option value="1">是</option>
                                            </c:if>
                                        </select>
                                    </div>
                                </dd>
                            </dl>
                            <dl class="new_peo_list">
                                <dt>字典描述：</dt>
                                <dd>
                                    <textarea name="description" datatype="*" nullmsg="请输入字典描述" maxlength="100" class="fl textarea" onkeyup="checkLen(this)"></textarea>
                                    <div class="fl mgl10 mgt93 add_btn_wrap">  
										<span class="tips"><i class="m_242"><em class="text_keyup_num">0</em>/100</i>（最多可输入100字）</span>
                                    </div>
                                </dd>
                            </dl>
                        </div>
                        <div class="state-save sub_btn_box">
                            <input type="button" value="返回上一页" class="back_next">
                            <input type="button" value="保存" style="color:white;" class="sub_btn">
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
		var itemCode = '${itemCode}'
		/* var type = '${type}' */
		$(function(){
			$('#color').on('change',function(){
				$("input[name='itemValue']").val($(this).val());
			})
			/* if(type == 'warningLevel'){
				$("input[name='itemValue']").keypress(function(event){
					var eventObj = event || e;
					var keyCode = eventObj.keyCode || eventObj.which;
					if ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || (event.shiftKey && keyCode == 51))
					    return true;
					else
					    return false;
				})
				$("input[name='itemValue']").on('keyup',function(){
					$('#color').val($(this).val());
				})
			} */
			$('#color').trigger('change');
		})
		$(".back_next").click(function(){
			window.history.back();
		}); 
		 $('.sub_btn').click(function(){
			 var dataItemId = $('input[name="dictionaryType.id"]').val();
		    $.ajax({
				url:'/admin/dictionary/save.shtml',
				type:'POST',
				data: $('#form1').serialize(),
				dataType: "json",
				success: function(data) {
					if(data.code==200){
						layer.msg(data.msg, { icon: 1,shade: 0.5});
						setTimeout(function(){
							window.location.href="/admin/dictionary/list.shtml?dataItemId="+dataItemId+"&&itemCode="+itemCode; 
						}		
						, 1000);
					}else{
						layer.msg(data.msg, { icon: 2});
					}
				},
				error : function(data) {  
					layer.msg("字典数据保存失败", { icon: 2});  
			    } 
			});
		})   
		
		//禁用Enter键表单自动提交  
        document.onkeydown = function(event) {  
            var target, code, tag;  
            if (!event) {  
                event = window.event; //针对ie浏览器  
                target = event.srcElement;  
                code = event.keyCode;  
                if (code == 13) {  
                    tag = target.tagName;  
                    if (tag == "TEXTAREA") { return true; }  
                    else { return false; }  
                }  
            }  
            else {  
                target = event.target; //针对遵循w3c标准的浏览器，如Firefox  
                code = event.keyCode;  
                if (code == 13) {  
                    tag = target.tagName;  
                    if (tag == "INPUT") { return false; }  
                    else { return true; }  
                }  
            }  
        };  
	</script>
</body>
</html>