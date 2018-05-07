<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/ruiec-tags" prefix="ruiec" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
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
	        <jsp:include page="../common/header.jsp"></jsp:include>
	        <!---->
	        <section>
        	<jsp:include page="../common/leftNav2.jsp"></jsp:include>
            <div class="mainRight">
                <div class="pancl_pd">
                    <form action="/admin/controlPersonInstructi/instructionRecord.shtml" method="GET">
                    	<div class="tit_menu_list">
	                        <a href="/admin/common/main.shtml" class="m_menu_item">
	                            <i class="iconfont">&#xe62a;</i>
	                            <em>首页</em>
	                        </a>
	                        <i class="fl split_gt">&gt;</i>
	                        <a href="/admin/controlPersonAlarm/list.shtml" class="m_menu_item">
	                            <em>重点人预警</em>
	                        </a>
	                        <i class="fl split_gt">&gt;</i>
	                        <a href="/admin/controlPersonAlarm/list.shtml" class="m_menu_item">
	                            <em>预警列表</em>
	                        </a>
	                        <i class="fl split_gt">&gt;</i>
	                        <a href="/admin/controlPersonInstructi/instructionRecord.shtml?id=${id}&pid=${pid}&iid=${iid}" class="m_menu_item">
	                            <em>下发签收记录</em>
	                        </a>
	                        <span class="fr">
	                        	<input type="button" onclick="javascript:history.back(-1);" class="fr back_btn" value="返回上一页"/>
	                        </span>
	                    </div>
                        <div class="mgt20 m_Tab_Control">
                            <a href="/admin/controlPersonAlarm/alarmDetail.shtml?id=${id}&pid=${pid}&iid=${iid}" >预警信息</a>
                            <c:if test="${sessionScope.loginUserUnit.userName!='警员'}">
								<a href="/admin/controlPersonInstructi/instructionRecord.shtml?id=${id}&pid=${pid}&iid=${iid}" class="active">下发签收记录</a>
								<a href="/admin/controlPersonAlarm/alarmHistorical.shtml?id=${id}&pid=${pid}&iid=${iid}">历史预警数据</a>
							</c:if>
                        </div>
						<input type="hidden" name="id" value="${id}">
						<input type="hidden" name="pid" value="${pid}">
                        <div class="mgt20 importantC">
                            <div class="importantNList">
                                <table class="tab tab_ellipsis">
                                    <colgroup>
                                        <col width="8%"/>
                                        <col width="16%"/>
                                        <col width="12%"/>
                                        <col width="16%"/>
                                        <col width="12%"/>
                                        <col width="12%"/>
                                        <col width="15%"/>
                                        <col width="7%"/>
                                        <col width="12%"/>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th class="pdl15">预警级别</th>
                                            <th>下发时间</th>
                                            <th>人员姓名 </th>
                                            <th>证件号码</th>
                                            <th>人员类别</th>
                                            <th>签收单位</th>
                                            <th>动态信息</th>
                                            <th>签收状态</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${page.list}" var="list">
	                                        <tr>
	                                        	<td class="pdl15">
													<ruiec:dictionary var="dictionary" dictionaryId="${list.controlPersonAlarm.warningLevel }">
	                                            	<i class="level m_level_blue" style="background-color:${dictionary == null?'无':dictionary.itemValue}">
	                                            		${dictionary == null?'无':dictionary.itemName}
	                                            	</i>
	                                            	</ruiec:dictionary>
												</td>
	                                            <td><fmt:formatDate value="${list.createDate }" pattern="yyyy-MM-dd HH:mm"/></td>
	                                            <td>${list.controlPerson.name}</td>
	                                            <td>${list.controlPerson.idCard}</td>
	                                            <td>
	                                            	<em class="tab_detail">
														<c:forEach items="${list.controlPerson.controlPersonTypes}" var="d" varStatus="l">
															<ruiec:dictionary var="dictionary" dictionaryId="${d.dictionaryId}">${dictionary == null?'无':dictionary.itemName}<c:if test="${!l.last}">,</c:if></ruiec:dictionary>
														</c:forEach>
													</em>
													<div class="hideThis ellipsis_dateil">
														<c:forEach items="${list.controlPerson.controlPersonTypes}" var="d" varStatus="l">
															<ruiec:dictionary var="dictionary" dictionaryId="${d.dictionaryId}">${dictionary == null?'无':dictionary.itemName}<c:if test="${!l.last}">,</c:if></ruiec:dictionary>
														</c:forEach>
													</div>
	                                            </td>
	                                            <td><ruiec:unit var="unit" unitId="${list.instructionRecipientUnitId}">${unit == null?'无':unit.unitName}</ruiec:unit></td>
	                                            <td>
	                                            	<em class="tab_detail"><ruiec:apiConfig var="api" apiId="${list.controlPersonAlarm.dynamicInfoTypeId}">${api == null?'无':api.name}</ruiec:apiConfig></em>
	                                            	<div class="hideThis ellipsis_dateil"><ruiec:apiConfig var="api" apiId="${list.controlPersonAlarm.dynamicInfoTypeId}">${api == null?'无':api.name}</ruiec:apiConfig></div>
	                                            </td>
	                                            <td>
	                                            	<c:if test="${list.instructionsState==0}">未签收</c:if>
	                                            	<c:if test="${list.instructionsState==1}">已签收</c:if>
	                                            	<c:if test="${list.instructionsState==2}">待审核</c:if>
	                                            	<c:if test="${list.instructionsState==3}">审核通过</c:if>
	                                            	<c:if test="${list.instructionsState==4}"><span style="color: red;">审核拒绝</span></c:if>
	                                            </td>
	                                            <td>
	                                            	<c:if test="${list.instructionsState==0 or list.instructionsState==1}">
	                                            		<a href="javascript:void(0);" class="m_blue">暂无</a>
	                                            	</c:if>
	                                            	<c:if test="${list.instructionsState!=0 and list.instructionsState!=1}">
	                                            	<a href="/admin/controlPersonInstructi/detail.shtml?id=${list.id}" class="m_blue">反馈详情</a>
	                                            	</c:if>
	                                            	<c:if test="${list.instructionsState==2 and list.issuedPolicePrikey==sessionScope.user.id}">
	                                            	<a href="/admin/controlPersonInstructi/feedBackAgain.shtml?id=${list.id}&state=${list.instructionsState}" class="m_blue">审核</a>
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
    <script src="${base }/resources/admin/js/jquery.validate.js"></script>
    <!--自写-->
    <script src="${base }/resources/admin/js/layout.js"></script>
    <!--拖拽-->
    <script src="${base }/resources/admin/js/gridster/jquery.gridster.min.js"></script>
    <!---下拉框 -->
    <script src="${base }/resources/admin/js/common.js"></script>
    <!--日期-->
    <script src="${base }/resources/admin/js/My97DatePicker/lang/zh-cn.js"></script>
    <script src="${base }/resources/admin/js/My97DatePicker/WdatePicker.js"></script>
    <link href="${base }/resources/admin/js/tooltips/tooltips.css" rel="stylesheet" id="skin">
    <script src="${base }/resources/admin/js/tooltips/bootstrap-tooltip.js"></script>
    <script src="${base }/resources/admin/js/tooltips/bootstrap-affix.js"></script>
    <script src="${base }/resources/admin/js/tooltips/application.js"></script>
    <script src="${base }/resources/admin/js/tooltips/jquery.pure.tooltips.js"></script> 
    <script type="text/javascript">
  	//签收
    function sign(id,state){
    	$.ajax({
			url:'/admin/controlPersonInstructi/sign.shtml',
			type:"post",
			data:{"id":id,"state":state},
			async:false,
			dataType: "json",
			success: function(data) {
				if(data.code==200){
		        	layer.msg(data.msg, { icon: 1, shade: 0.5 });
					setTimeout(function(){
						window.location.reload(); //刷新页面
					}		
					, 1000);
	        	}else{
	        		layer.msg(data.msg, { icon: 2});
	        	}
			},
			error : function(data) {  
				layer.msg(data.msg, { icon: 2});  
	        } 
		});
    }
    </script>
</body>
</html>