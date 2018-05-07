<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html >
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<title><ruiec:title/></title>
    <link rel="stylesheet" href="${base }/resources/admin/js/datetimepicker/jquery.datetimepicker.css" >
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
                    <form action="/admin/dictionaryType/list.shtml" method="GET"  id="searchform">
                        <div class="tit_menu_list importantC">
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
                        </div>
                        <div class="mgt15 importantN importantC">
                            <div class="m_bg_ef Allcrumb">
                                <div class="fl crumb_item">
                                   <!--  <a href="javascript:void(0);" class="item_a" onclick="checkAll(this)">全选</a>
                                    <a href="javascript:void(0);" class="item_a" onclick="delType()">删除</a> -->
                                </div>
                                <div class="fr crumb_item">
                                    <a class="item_a" id="handInit">手动刷新</a>
                                    <a href="/admin/dictionaryType/add.shtml" class="item_a">新建</a>
                                </div>
                            </div>
                            <div class=" mgt15 importantNList">
                                <table class="tab tab_ellipsis">
                                    <colgroup>
                                        <col width="3%"/>
                                        <col width="15%"/>
                                        <col width="23%"/>
                                        <col width="25%"/>
                                        <col width="23%"/>
                                        <col width="8%"/>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>&nbsp;</th>
                                            <th>中文名</th>
                                            <th>描述</th>
                                            <th>排序号</th>
                                            <th>是否启用</th>
                                            <th class="textl">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       <c:forEach var="list" items="${page.list }">
                                        <tr>
			                                <td style="text-align: center">
			                                	<input type="checkbox" class="checkItem" name="ids" value="${list.id }"/>
			                                </td>
                                            <td>${list.itemName}</td>
                                            <td>
                                              <em class="tab_detail">${list.describe}</em>
                                              <div class="hideThis ellipsis_dateil">${list.describe}</div>
                                            </td>
                                            <td>${list.sort}</td>
                                            <td>
                                                <em class="m_b">
		                                            <c:if test="${list.isUse==1}">是</c:if>
		                                            <c:if test="${list.isUse==0}">否</c:if>
                                                </em>
                                            </td>
                                            <td>
                                                <a href="edit.shtml?id=${list.id}" class="m_b">编辑</a>
                                            </td>
                                        </tr>
                                       </c:forEach>
                                    </tbody>
                                </table>
		                        <c:if test="${ page.pageCount > 1}">
			                        <div class="foot-cont">
			                            <div class="pagenation fr">
			                                <jsp:include page="../common/page.jsp"></jsp:include>
			                            </div>
			                        </div>
		                        </c:if>
	                            <c:if test="${empty page.list}">
		                            <div class="tc no_data_img">
				                    	<img src="${base }/resources/admin/img/no_data_img.png" class="no_img_data">
				                    	<p class="mgt10 nodata_text">~暂时没有数据呢~</p>
				                    </div>
	                            </c:if>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </section>
     <!--js-->
    <script src="${base }/resources/admin/js/jquery-1.9.1.min.js"></script>
    <script src="${base }/resources/admin/js/layer.js"></script> 
    <!--自写-->
    <script src="${base }/resources/admin/js/layout.js"></script>
    <!--时间-->
    <script src="${base }/resources/admin/js/datetimepicker/jquery.datetimepicker.js"></script>
    <script src="${base }/resources/admin/js/datetimepicker/timer.js"></script>
    <!--验证-->
    <script src="${base }/resources/admin/js/jquery.form.js"></script>
    <script src="${base }/resources/admin/js/Validform_v5.3.2/Validform_v5.3.2.js"></script>
    <!---下拉框 -->
    <script src="${base }/resources/admin/js/common.js"></script> 
    
    <link href="${base }/resources/admin/js/tooltips/tooltips.css" rel="stylesheet" id="skin">
    <script src="${base }/resources/admin/js/tooltips/bootstrap-tooltip.js"></script>
    <script src="${base }/resources/admin/js/tooltips/bootstrap-affix.js"></script>
    <script src="${base }/resources/admin/js/tooltips/application.js"></script>
    <script src="${base }/resources/admin/js/tooltips/jquery.pure.tooltips.js"></script>
    <script type="text/javascript">
    $(function(){
    	$("#handInit").click(function () {
    		var index = layer.msg('刷新中', { icon: 16 , shade: 0.1 , time: false });
			$.ajax({
    			url:'/admin/init/initRedis.shtml',
    			type:"post",
    			data:null,
    			async:true,
    			dataType: "json",
    			success: function(data) {
    				layer.msg(data.msg, { icon: 1});
    				layer.close(index);
    			},
    			error : function(data) {  
    				layer.msg("手动刷新失败", { icon: 2});  
    				layer.close(index);
    		    } 
    		});
		});
    	if( $("#searchform").size()>0 ){
	     	$.Tipmsg.r=null;
	     	$("#searchform").Validform({
	 			tiptype:function(msg){ 
	 				if( msg != "" && msg != null && msg != undefined  ){
	 					layer.msg(msg);	
	 				}
	 			}, 
	 			tipSweep:true,
	 			callback:function(form){}
	 		});	
	     }; 
   	}); 
	function findForm(){
		$('#searchform').submit();
	}
  	//批量删除
	function delType(){
	    var $ids = $(".tab input[name='ids']:enabled:checked");
	    var m = '是否将此信息删除?';
	    var result = false;
	    if ($ids.size() < 1) {
	    	layer.msg('请选择要删除的选项');
	        return;
	    } 
	    $.ajax({
            type:"post",
            url:"check.shtml",
            data:$ids.serialize(),
            async:false,
            dataType:"json",
            success:function(data) {  	
            	if(data.code == 200){
            		//询问框
            	    layer.confirm('是否将此信息删除?', {
            	      btn: ['确认','取消'] //按钮
            	    }, function(){
            	    	$.ajax({
        		            type:"post",
        		            url:"delete.shtml",
        		            data:$ids.serialize(),
        		            dataType:"json",
        		            success:function(data) {  	 	 
        		            	if(data.code == 200){
        		                	layer.msg(data.msg,{icon: 1});
        		                	setTimeout(function(){
        		                		$.each($ids, function(index, value) {
            		                        var $this = $(this);
            		                        $this.closest("tr").remove();
            		                    });
    								},2000);
        		                	location.reload();
        		            	}else if(data.code == 400){
        		                	layer.msg(data.msg,{icon: 2});
        		            	}
        		            },
        		            error:function(data) {
        		            	layer.msg("删除失败",{icon: 2}); 
        		            }
        		       });
                	});
            	}else if(data.code == 400){
                	layer.msg(data.msg,{icon: 2});
            	}
            },
            error:function(data) {
            	layer.msg("删除失败",{icon: 2}); 
            }
       })
	}
   	</script> 
</body>
</html>