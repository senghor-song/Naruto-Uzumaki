<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<title><ruiec:title/></title>
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
        	<jsp:include page="../common/leftNav5.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                    <form id="search_form" action="/admin/loginLog/list.shtml" method="get">
                        <div class="tit_menu_list">
                            <a href="/admin/common/main.shtml" class="m_menu_item">
                                <i class="iconfont">&#xe62a;</i>
                                <em>首页</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item refresh_html">
                                <em>系统操作日志</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item refresh_html">
                                <em>登录日志</em>
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
                                        <input type="text" name="name" maxLength="5" class="w130 text_inp Validform_error" placeholder="请输入操作用户" autocomplete="off" value="${name }" datatype="/[\u4e00-\u9fa5·]$/" errormsg="请输入正确的姓名"/>
                                    </dd>
                                </dl>
                                <dl class="fl mgl10 search_item">
                                    <dt></dt>
                                    <dd>
                                        <input type="text" name="ip" class="w130 text_inp" maxLength="15" placeholder="请输入IP" autocomplete="off" value="${ip }" />
                                        <a href="javascript:document.getElementById('search_form').submit();" class="btn curr fr mgl10">搜索</a>
                                        <button type="reset" class="btn fr mgl10 resetBtn" onClick="resetBtn()">重置</button>
                                    </dd>
                                </dl>
                            </div>
                        </div>
                        </form>
                        <form action="/admin/loginLog/list.shtml" method="get">
                        <input type="hidden" name="startDate" value="${startDate }"/>
                        <input type="hidden" name="endDate" value="${endDate }"/>
                        <input type="hidden" name="name" value="${name }"/>
                        <input type="hidden" name="ip" value="${ip }"/>
                        <div class="m_bg_ef mgt20 Allcrumb importantC">
                            <div class="fl crumb_item">
                            </div>
                        </div>
                        <div class="mgt10 importantNList">
                            <table class="tab">
                                <colgroup>
                                    <col width="3%"/>
                                    <col width="16%"/>
                                    <col width="20%"/>
                                    <col width="20%"/>
                                    <col width="20%"/>
                                    <col width="20%"/>
                                </colgroup>
                                <thead>
                                <tr>
                                    <th>&nbsp;</th>
                                    <th>操作用户</th>
                                    <th>所在单位</th>
                                    <th>IP地址</th>
                                    <th>备注</th>
                                    <th>登录时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="list" items="${page.list}">
                                    <tr>
                                        <td style="text-align: center"></td>
                                        <td>${list.userName}</td>
                                        <td>${list.unitName }</td>
                                        <td>${list.ip }</td>
                                        <td>${list.remark }</td>
                                        <td><fmt:formatDate value="${list.createDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
                    </form>
                </div>
            </div>
        </section>

    <!--js-->
    <script src="${base }/resources/admin/js/jquery-1.9.1.min.js"></script>
	<script src="${base }/resources/admin/js/layer.js"></script> 
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
	// 重置
	$(function(){
		/* $(".resetBtn").click(function(){
			$('select').each(function(){
				$(this).find("option").attr("selected",false);
				$(this).find("option").eq(0).attr("selected","selected");
			});	 
			$(".rule-single-select").ruleSingleSelect(); 
			$("input").not("#goto,:hidden").attr("value","");
		}); */
   	});
	</script>
</body>
</html>