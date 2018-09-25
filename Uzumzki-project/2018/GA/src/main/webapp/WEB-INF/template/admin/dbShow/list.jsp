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
                    <form action="/admin/dbShow/list.shtml" method="GET" >
                    <input type="hidden" name="pid" value="${pid }" />
                        <div class="tit_menu_list">
                            <a href="/admin/common/main.shtml" class="m_menu_item">
                                <i class="iconfont">&#xe62a;</i>
                                <em>首页</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item refresh_html">
                               <em>数据导入</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="/admin/dbShow/list.shtml" class="m_menu_item">
                                <em>可导入表展示列表</em>
                            </a>
                            <c:if test="${pid!=null }">
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item refresh_html">
                                <em>可导入字段展示列表</em>
                            </a>
                            </c:if>
                        </div>
                        <div class="mgt15 importantC">
                            <div class="m_bg_ef Allcrumb">
                                <div class="fl crumb_item">
                                	<a href="javascript:void(0);" class="item_a" onclick="checkAll(this)">全选</a>
                                    <a href="javascript:void(0);" class="item_a" onclick="del()">删除</a>
                                </div>
                                <div class="fr crumb_item">
                                    <a href="/admin/dbShow/add.shtml?pid=${pid }" class="item_a">新建</a>
                                </div>
                            </div>
                            <div class=" mgt15 importantNList">
                                <table class="tab">
                                    <colgroup>
                                        <col width="3%"/>
                                        <col width="20%"/>
                                        <col width="20%"/>
                                        <col width="20%"/>
                                        <col width="20%"/>
                                        <col width="17%"/>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>&nbsp;</th>
                                            <th>显示名字</th>
                                            <c:if test="${pid==null }"><th>对应表名 </th></c:if>
                                            <c:if test="${pid!=null }"><th>对应字段名 </th></c:if>
                                            <th>字段类型</th>
                                            <c:if test="${pid==null }"><th>创建时间</th></c:if>
                                            <c:if test="${pid!=null }"><th>是否必须</th></c:if>
                                            <th class="textl">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       <c:forEach var="list" items="${page.list }">
                                        <tr>
			                                <td style="text-align: center"><input type="checkbox" class="checkItem" name="ids" value="${list.id }"/></td>
                                            <td>${list.name}</td>
                                            <td>${list.dbName}</td>
                                            <td>
												<c:if test="${list.fieldType==0 }">整型</c:if>
												<c:if test="${list.fieldType==1 }">字符串</c:if>
												<c:if test="${list.fieldType==2 }">日期</c:if>
												<c:if test="${ empty list.fieldType }">无</c:if>
											</td>
											<c:if test="${pid==null }">
                                            <td><fmt:formatDate value="${list.createDate }" pattern="yyyy-MM-dd" /></td>
                                            </c:if>
                                            <c:if test="${pid!=null }"><td>${list.isNeed==1?'是':'否'}</td></c:if>
                                            <td>
                                                <a href="/admin/dbShow/edit.shtml?id=${list.id}" class="m_b">修改</a>
                                                <c:if test="${pid==null }">
                                                <i class="m_e8">|</i>
                                                <a href="/admin/dbShow/list.shtml?pid=${list.id}" class="m_b">字段列表</a>
                                            	</c:if>
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
	<!--验证-->
    <script src="${base }/resources/admin/js/jquery.form.js"></script>
    <script src="${base }/resources/admin/js/Validform_v5.3.2/Validform_v5.3.2.js"></script>
	<!--自写-->
	<script src="${base }/resources/admin/js/layout.js"></script> 
	<!---下拉框 -->
	<script src="${base }/resources/admin/js/common.js"></script> 
    <script type="text/javascript">
  //批量删除
	function del(){
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
            		result = true;
                	m='此数据含有下级数据，点击删除将同时删除下级数据，是否将此信息删除?';
            	}else if(data.code == 400){
                	layer.msg(data.msg,{icon: 2});
            	} else {
            		result = true;
            	}
            },
            error:function(data) {
            	layer.msg("删除失败",{icon: 2}); 
            }
       })
       if(result){
		 	//询问框
		    layer.confirm(m, {
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
		                	$.each($ids, function(index, value) {
		                        var $this = $(this);
		                        $this.closest("tr").remove();
		                    });
		            	}else if(data.code == 400){
		                	layer.msg(data.msg,{icon: 2});
		            	}
		            },
		            error:function(data) {
		            	layer.msg("删除失败",{icon: 2}); 
		            }
		       });
		    }); 
       }  
	}
   	</script> 
</body>
</html>