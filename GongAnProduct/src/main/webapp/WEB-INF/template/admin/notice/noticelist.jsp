<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="/ruiec-tags" prefix="ruiecz" %>
<%@taglib uri="/ruiec-functions" prefix="ruiec" %>
<!DOCTYPE html >
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<title><ruiecz:title/></title>
    <link rel="stylesheet" href="${base }/resources/admin/js/datetimepicker/jquery.datetimepicker.css" />
    <link rel="stylesheet" href="${base }/resources/admin/css/layer.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/common.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/style.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/index.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/quote.css" />
</head>
<body class="bodyCss">
          <!--头部-->
        <jsp:include page="../common/header.jsp"></jsp:include> 
        <!---->
        <section>
        	<c:if test="${sessionScope.user.id == 1}">
        		<jsp:include page="../common/leftNav5.jsp"></jsp:include>
        	</c:if>
        	<c:if test="${sessionScope.user.id != 1}">
        		<jsp:include page="../common/leftNav.jsp"></jsp:include>
        	</c:if>
            <div class="mainRight">
                <div class="pancl_pd">
                   <form id="findForm" action='/admin/notice/noticelist.shtml' method="GET"> 
                        <div class="tit_menu_list importantC">
                            <a href="/admin/common/main.shtml" class="m_menu_item">
                                <i class="iconfont">&#xe62a;</i>
                                <em>首页</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item">
                                <em>宣传管理</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="/admin/notice/noticelist.shtml" class="m_menu_item">
                                <em>公告列表</em>
                            </a>
                        </div>
                        <div class="mgt15 importantC">
                            <div class="mgt15" id="rylbCount">
                                <dl class="fl search_item">
                                    <dt>创建时间：</dt>
                                   <dd>
                                        <input class="Wdate text_inp " value="${startDate }" id="quote_time" autocomplete="off" type="text" name="startDate"> 
                                        <em class="lh30 offDivide">至</em> 
                                        <input class="Wdate text_inp" value="${endDate }" id="quote_time1" autocomplete="off" type="text" name="endDate">
                                    </dd>
                                </dl>
                                <dl class="fl mgl10 search_item">
                                    <dt></dt>
                                    <dd>
                                         <button type="submit" href="javascript:void(0);" class="btn curr fr mgl10" id="search" style="cursor: pointer;">搜索</a>
                                         <button type="reset" class="btn fr mgl10" onclick="resetBtn()">重置</button>
                                    </dd>
                                </dl>
                            </div>
                        </div>
                        <div class="mgt20 importantC">
                           
                            <div class="mgt15 importantNList">
                                <table class="tab tab_ellipsis notic_tab">
                                    <colgroup>
	                                    <col width="3%">
	                                    <col width="22%">
	                                    <col width="30%">
	                                    <col width="35%">
	                                    <col width="10%">
	                                </colgroup>
                                    <thead>
                                        <tr>
                                        	<th>&nbsp;</th>
                                            <th>标题</th>
                                            <th>发布人</th>
                                            <th>创建时间</th>
                                            <th class="textl">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       <c:forEach var="list" items="${page.list}">
                                       <tr
                                       	<c:if test="${!ruiec:isRead(list.id,unread)}">class="no_unread"</c:if>
                                       >
                                       		<td>&nbsp;</td>
                                           	<td>
                                           		<c:if test="${!ruiec:isRead(list.id,unread)}">
                                           			<i class="notic_radius"><img src="${base}/resources/admin/img/notic_radius.png" class="" /></i>
                                           		</c:if>
                                           		<em class="" title="">${list.title}</em>
                                           	</td>
                                           	<td>${list.publisher}</td>
                                           	<td><fmt:formatDate value="${list.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                           	<td>
                                               <a href="detail.shtml?id=${list.id}" class="m_b" 
                                               <c:if test="${!ruiec:isRead(list.id,unread)}">style="color: blue;"</c:if>
                                               >查看</a>
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
	    function findForm(){
	    	$('#findForm').submit();
	   	}
   	</script>
</body>
</html>