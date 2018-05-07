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
                    <form  action="/admin/operationLog/list.shtml" method="get"  id="findForm">
                         <div class="tit_menu_list">
                            <a href="/admin/common/main.shtml" class="m_menu_item">
                                <i class="iconfont">&#xe62a;</i>
                                <em>首页</em>
                            </a>
                             <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item">
                                <em>系统操作日志</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="/admin/operationLog/list.shtml" class="m_menu_item">
                                <em>操作日志列表</em>
                            </a>
                        </div>
                        <div class="mgt20 importantC">
                            <div class="mgt20" id="rylbCount">
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
                                        <input type="text" class="w130 text_inp" placeholder="请输入操作用户" maxlength="10" autocomplete="off" value="${policeName}" name="policeName"/>
                                    </dd>
                                </dl>
                                <dl class="fl mgl10 search_item">
                                    <dt></dt>
                                    <dd>
                                        <input type="text" class="w130 text_inp" placeholder="请输入所在单位" maxlength="20" autocomplete="off" value="${operationLog.unitName}" name="unitName"/>
                                    </dd>
                                </dl>
                                <dl class="fl mgl10 search_item">
                                    <dt></dt>
                                    <dd>
                                        <input type="text" class="w130 text_inp" placeholder="请输入备注内容" maxlength="15" autocomplete="off" value="${operationLog.remark}" name="remark"/>
                                    </dd>
                                </dl>
                                <dl class="fl mgl10 search_item">
                                    <dt></dt>
                                    <dd>
                                        <input type="text" class="w130 text_inp" placeholder="请输入IP" maxlength="15" autocomplete="off" value="${operationLog.ip}" name="ip"/>
                                        <button class="btn curr fr mgl10" type="submit" value="搜索" id="search">搜索</button>
                                       	<button type="reset" class="btn fr mgl5 resetBtn" onClick="resetBtn()">重置</button>
                                    </dd>
                                </dl>
                            </div>
                        </div>
                       <div class="mgt15 importantNList">
                           <table class="tab tab_ellipsis">
                                <colgroup>
                                    <col width="15%"/>
                                    <col width="15%"/>
                                    <col width="15%"/>
                                    <col width="35%"/>
                                    <col width="20%"/>
                                </colgroup>
                                <thead>
                                <tr>
                                    <th class="pdl25">操作用户</th>
                                    <th>所在单位</th>
                                    <th>IP地址</th>
                                    <th>备注</th>
                                    <th>操作时间</th>
                                </tr>
                                </thead>
                                    <tbody>
                                    	<c:forEach var="list" items="${page.list }">
                                        <tr>
                                            <td class="pdl25">${list.userId}</td>
                                            <td>
                                             <em class="tab_detail">${list.unitName}</em>
                                             <div class="hideThis ellipsis_detail"> ${list.unitName}</div>
                                            </td>
                                            <td>${list.ip}</td>
                                            <td>
                                                <em class="tab_detail">${list.remark}</em>
                                                <div class="hideThis ellipsis_dateil">${list.remark}</div>
                                            </td>
                                            <td><fmt:formatDate value="${list.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
        $(".demoT6").each(function(){
            var tooltips = $(this).attr("title");
            $(this).pt({
                position: 'r', // 默认属性值
                align: 'c',	   // 默认属性值
                content: tooltips,
            });
        });
    </script>
    <script type="text/javascript">
	    function findForm(){
	    	$('#findForm').submit();
	   	}
   	</script>
</body>
</html>