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
    <link rel="stylesheet" href="${base }/resources/admin/js/datetimepicker/jquery.datetimepicker.css" />
    <link rel="stylesheet" href="${base }/resources/admin/css/layer.css" >
    <link rel="stylesheet" href="${base }/resources/admin/css/common.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/style.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/index.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/quote.css" />
</head>
<body class="bodyCss">
        <!--头部-->
        <jsp:include page="../common/header.jsp"></jsp:include> 
        <!---->
        <section>
        	<jsp:include page="../common/leftNav5.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                    <form action="/admin/dbTable/list.shtml" method="GET">
                        <input type="hidden" name="dbId" value="${dbId }" />
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
                            <a href="/admin/db/list.shtml" class="m_menu_item">
                                <em>数据源列表</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item refresh_html">
                                <em>数据导入表列表</em>
                            </a>
                            <span class="fr">
	                        	<input type="button" onclick="javascript:history.back(-1);" class="fr back_btn" value="返回上一页"/>
	                        </span>
                        </div>
                        <div class="searh_list" id="rylbCount">
                            <div id="rylbCount">
                            <dl class="search_item">
                                <dt>时间段：</dt>
                                <dd>
                                    <input name="startDate" class="Wdate text_inp input_from" id="quote_datetime" autocomplete="off" type="text" 
                                        value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
                                    <em class="offDivide">至</em>
                                    <input name="endDate" class="Wdate text_inp input_from" id="quote_datetime1" autocomplete="off" type="text" 
                                     value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
                                </dd>
                            </dl>
                            </div>
                        </div>
                        <div class="mgt15 importantC">
                            <div class="m_bg_ef Allcrumb">
                                <div class="fl crumb_item">
                                	<a href="javascript:void(0);" class="item_a" onclick="checkAll(this)">全选</a>
                                    <a href="javascript:void(0);" class="item_a" onclick="del()">删除</a>
                                </div>
                                <div class="fr crumb_item">
                                    <a href="/admin/dbTable/add.shtml?dbId=${dbId }" class="item_a">新建</a>
                                </div>
                            </div>
                            <div class=" mgt15 importantNList">
                                <table class="tab">
                                    <colgroup>
                                        <col width="3%"/>
                                        <col width="15%"/>
                                        <col width="15%"/>
                                        <col width="15%"/>
                                        <col width="15%"/>
                                        <col width="20%"/>
                                        <col width="17%"/>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>&nbsp;</th>
                                            <th>表名</th>
                                            <th>对应我方表名</th>
                                            <th>中文名</th>
                                            <th>是否启用</th>
                                            <th>创建时间</th>
                                            <th class="textl">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       <c:forEach var="list" items="${page.list }">
                                        <tr>
			                                <td style="text-align: center"><input type="checkbox" class="checkItem" name="ids" value="${list.id }"/></td>
                                            <td>${list.tableName}</td>
                                            <td>${list.dbShow.dbName}</td>
                                            <td>${list.dbShow.name}</td>
                                            <td>${list.isUse==1?'是':'否'}</td>
                                            <td><fmt:formatDate value="${list.createDate }" pattern="yyyy-MM-dd" /></td>
                                            <td>
                                                <a href="edit.shtml?id=${list.id}&dbId=${dbId}" class="m_b">编辑</a>
                                                <i class="m_e8">|</i>
                                                <a href="/admin/dbField/list.shtml?id=${list.id}&dbId=${dbId}" class="m_b">表字段</a>
                                                <i class="m_e8">|</i>
                                                <a href="javascript:;" onclick="openWin(${list.id});" class="m_b">导入</a>
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
	<script src="${base }/resources/admin/js/jquery.validate.js"></script>
	<!--自写-->
	<script src="${base }/resources/admin/js/layout.js"></script>
	<!--拖拽-->
	<script src="${base }/resources/admin/js/gridster/jquery.gridster.min.js"></script>
	<!---下拉框 -->
	<script src="${base }/resources/admin/js/common.js"></script>
    <!--日期-->
	<script src="${base }/resources/admin/js/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="${base }/resources/admin/js/datetimepicker/timer.js"></script>
    <script type="text/javascript">
	
	function importTable(id){
		layer.msg('导入中', { icon: 16 ,shade: 0.1 });
		$.ajax({
			url:'/admin/dbTable/importTable.shtml?id='+id,
			type:'POST',
			dataType: "json",
			success: function(data) {
				if(data.code==200){
					layer.msg(data.msg, { icon: 1});
				}else{
					layer.msg(data.msg, { icon: 2});
				}
			},
			error : function(data) {  
				layer.msg("数据库保存失败", { icon: 2});  
		       } 
		});
	}
	// 导入数据
	/* $(".sub_btn").click(function(){
		layer.msg('导入中', { icon: 16 ,shade: 0.1 });
		$.ajax({
			url:'/admin/db/dataImport.shtml',
			type:'POST',
			data: $('#import_form').serialize(),
			dataType: "json",
			//async:false,
			success: function(data) {
				if(data.code==200){
					layer.msg(data.msg, { icon: 1});
				}else{
					layer.msg(data.msg, { icon: 2});
				}
			},
			error : function(data) {  
				layer.msg("数据库保存失败", { icon: 2});  
		       } 
		});
		//layer.closeAll('loading');
		
	});*/
  	// 导入数据弹窗
	function openWin(id){
		var startDate = $("input[name='startDate']").val();
		var endDate = $("input[name='endDate']").val();
		/* date = new Date;
		if(startDate==""){
			startDate = date.getFullYear()
			+ "-01-01 00:00:00"
		}
		if(endDate==""){
			endDate = date.getFullYear()
			+ "-"// "年"
            + ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
                    + (date.getMonth() + 1))
            + "-"// "月"
            + (date.getDate() < 10 ? "0" + date.getDate() : date
                    .getDate())
            + " "
            + (date.getHours() < 10 ? "0" + date.getHours() : date
                    .getHours())
            + ":"
            + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date
                    .getMinutes())
            + ":"
            + (date.getSeconds() < 10 ? "0" + date.getSeconds() : date
                    .getSeconds());
		} */
		layer.open({
            type: 2,
            title: startDate+'到'+endDate+'的数据导入', //不显示标题
            area: ['500px', '270px'], //宽高
		    closeBtn:0,
            content: "/admin/dbTable/importInit.shtml?id="+id+ "&startDate="+startDate+ "&endDate="+endDate, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		    success: function(){
	            $(document).on('keydown', function(e){  //document为当前元素，限制范围，如果不限制的话会一直有事件
	                if(e.keyCode == 13){
	        		    return false;
	                }
	            })
		    } 
        });
	} 
   	</script>
</body>
</html>