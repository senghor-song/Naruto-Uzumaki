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
    <link rel="stylesheet" href="${base }/resources/admin/js/datetimepicker/jquery.datetimepicker.css" >
    <link rel="stylesheet" href="${base }/resources/admin/css/layer.css" >
    <link rel="stylesheet" href="${base }/resources/admin/css/common.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/style.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/index.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/quote.css">
</head>
<body class="bodyCss">
        <jsp:include page="../common/header.jsp"></jsp:include> 
        <!---->
        <section>
        	<jsp:include page="../common/leftNav7.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                    <form action="/admin/syntheticAnalysis/myUnitTaskList.shtml" method="get" id="searchform">
                        <div class="tit_menu_list">
                            <a href="/admin/common/main.shtml" class="m_menu_item nav_list">
                                <i class="iconfont"></i>
                                <em>首页</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item refresh_html">
                                <em>合成研判</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="/admin/syntheticAnalysis/myUnitTaskList.shtml" class="m_menu_item refresh_html">
                                <em>单位任务列表</em>
                            </a>
                            <span class="fr">
	                        	<input type="button" onclick="javascript:history.back(-1);" class="fr back_btn" value="返回上一页"/>
	                        </span>
                        </div>
                        <div class="mgt15 importantC">
                            <div id="mgt15 rylbCount">
                            	<a class="mgl10" href="/admin/syntheticAnalysis/unitTaskList.shtml">单位创建的任务</a> 
                            	<a class="mgl10 m_b" href="/admin/syntheticAnalysis/myUnitTaskList.shtml">单位接收的任务</a> 
                                <div class="fr mgl10">
                                    <dl class="search_item">
                                        <dt>任务名称查询：</dt>
                                        <dd>
                                            <input class="w250 text_inp" autocomplete="off" name="taskName"  value="${taskDTO.taskName}" type="text" ignore="ignore" id="taskName"/>
                                            <button type="submit" href="javascript:void(0);" class="btn curr fr mgl10" id="searchformsubmit" style="cursor: pointer;">搜索</a>
                                        </dd>
                                    </dl>
                                </div>
                                <div class="fr rule-single-select single-select">
                                    <select name="taskType" style="display: none;" onchange="findForm()">
                                    	<option value="">任务状态</option>
                                        <option value="5" <c:if test="${taskDTO.taskType == 5 }">selected="selected"</c:if>>已结束</option>
                                        <option value="0" <c:if test="${taskDTO.taskType == 0 }">selected="selected"</c:if>>未签收</option>
                                        <option value="1" <c:if test="${taskDTO.taskType == 1 }">selected="selected"</c:if>>已签收/待反馈</option>
                                        <option value="2" <c:if test="${taskDTO.taskType == 2 }">selected="selected"</c:if>>待审核</option>
                                        <option value="3" <c:if test="${taskDTO.taskType == 3 }">selected="selected"</c:if>>审核通过</option>
                                        <option value="4" <c:if test="${taskDTO.taskType == 4 }">selected="selected"</c:if>>审核拒绝</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="mgt15 importantN">
                            <div class="importantNList">
                                <table class="tab tab_ellipsis">
                                    <colgroup>
                                        <col width="10%"/>
                                        <col width="10%"/>
                                        <col width="10%"/>
                                        <col width="10%"/>
                                        <col width="12%"/>
                                        <col width="12%"/>
                                        <col width="13%"/>
                                        <col width="8%"/>
                                        <col width="10%"/>
                                    </colgroup>
                                    <thead>
                                    	<tr>
											<th class="pdl25">任务名称</th>
											<th>发起单位</th>
											<th>发起人</th>
											<th>接收单位</th>
											<th>接收人</th>
											<th>任务类型</th>
											<th>任务时间</th>
											<th>任务状态</th>
											<th>操作</th>
										</tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${page.list}" var="list">
	                                        <tr>
	                                            <td class="pdl25" <c:if test="${list.task.taskLevel eq '紧急'}">style="color: red"</c:if>>
	                                            	<em class="tab_detail" >
		                                            	${list.task.name}
		                                            </em>
		                                            <div class="hideThis ellipsis_dateil">
	                                            		${list.task.name}
		                                            </div>
	                                            </td>
	                                            <td><ruiec:unit var="unit" unitId="${list.task.originatorUnitId}">${unit == null?'无':unit.unitName}</ruiec:unit></td>
	                                            <td><ruiec:user var="police" userId="${list.task.originatorUserId}">${police == null?'':police.policeName}</ruiec:user></td>
	                                            <td><ruiec:unit var="unit" unitId="${list.receiveUnitId}">${unit == null?'无':unit.unitName}</ruiec:unit></td>
	                                            <td><ruiec:user var="police" userId="${list.receiveUserId}">${police == null?'':police.policeName}</ruiec:user></td>
	                                            <td>	
	                                            	<ruiec:dictionary var="dictionary" dictionaryId="${list.task.typeNumber}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary>
	                                            </td>
	                                            <td>
	                                            	开始:<fmt:formatDate value="${list.task.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/><br>
	                                            	结束:<fmt:formatDate value="${list.task.handleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
	                                            </td>
	                                            <td>
	                                            	<c:if test="${list.task.status ne '3'}">
		                                            	${list.feedbackStatus eq '0' ? '未签收' :list.feedbackStatus eq '1' ? '已签收/待反馈' : 
		                                            	list.feedbackStatus eq '2' ? '待审核' : list.feedbackStatus eq '3' ? '审核通过' : '审核拒绝'}
	                                            	</c:if>
	                                            	<c:if test="${list.task.status eq '3'}">
	                                            		已结束
	                                            	</c:if>
	                                            </td>
	                                            <td>
	                                                <a href="/admin/syntheticAnalysis/taskDetails.shtml?taskId=${list.task.id}" class="m_blue">详情</a>
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
	<script src="${base }/resources/admin/js/webuploader/webuploader.min.js"></script>
	
	<link rel="stylesheet" href="${base }/resources/admin/js/select/select2.css">
	<script src="${base }/resources/admin/js/select/select2.js"></script>

    <link href="${base }/resources/admin/js/tooltips/tooltips.css" rel="stylesheet" id="skin">
    <script src="${base }/resources/admin/js/tooltips/bootstrap-tooltip.js"></script>
    <script src="${base }/resources/admin/js/tooltips/bootstrap-affix.js"></script>
    <script src="${base }/resources/admin/js/tooltips/application.js"></script>
    <script src="${base }/resources/admin/js/tooltips/jquery.pure.tooltips.js"></script> 
    <script type="text/javascript">
	function findForm(){
		$('#searchformsubmit').click();
	}
    </script>
</body>
</html>