<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<form action="/admin/controlPerson/look.shtml?id=${id}&typeId=${typeId}" method="post">
	<div class="mgt10 importantN importantC">
		<div class="importantNList">
			<table class="tab tab_ellipsis">
				<c:if test="${typeId==0}">
					<thead>
						<tr>
							<th class="pdl25">预警级别</th>
							<th>预警时间</th>
							<th>人员姓名</th>
							<th>证件号码</th>
							<th>人员类别</th>
							<th>动态信息类别</th>
							<c:if test="${viewType eq 0}">
								<th class="textl">操作</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${page.list}">
							<tr>
								<td class="pdl25">
									<ruiec:dictionary var="dictionary" dictionaryId="${list.warningLevel }">
									<i class="level m_level_blue" style="background-color:${dictionary == null?'无':dictionary.itemValue}">
                                  		${dictionary == null?'无':dictionary.itemName}
                                  	</i>
									</ruiec:dictionary>
								</td>
								<td class="textl"><fmt:formatDate value="${list.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${list.controlPerson.name }</td>
								<td>
									<em class="tab_detail">
										${list.controlPerson.idCard }
									</em>
									<div class="hideThis ellipsis_dateil">
										${list.controlPerson.idCard }
									</div>
								</td>
								<td>
									<em class="tab_detail" ><c:forEach var="dict" items="${list.controlPerson.controlPersonTypes}" varStatus="l"><ruiec:dictionary var="dictionary" dictionaryId="${dict.dictionaryId}">${dictionary == null?'无':dictionary.itemName}<c:if test="${!l.last}">,</c:if></ruiec:dictionary></c:forEach></em>
                                	<div class="hideThis ellipsis_dateil"><c:forEach var="dict" items="${list.controlPerson.controlPersonTypes}" varStatus="l"><ruiec:dictionary var="dictionary" dictionaryId="${dict.dictionaryId}">${dictionary == null?'无':dictionary.itemName}<c:if test="${!l.last}">,</c:if></ruiec:dictionary></c:forEach></div>
                               	</td>
								<td>
									<em class="tab_detail">
										<ruiec:apiConfig var="apiConfig" apiId="${list.dynamicInfoId }">${apiConfig == null?'无':apiConfig.name}</ruiec:apiConfig>
									</em>
									<div class="hideThis ellipsis_dateil">
										<ruiec:apiConfig var="apiConfig" apiId="${list.dynamicInfoId }">${apiConfig == null?'无':apiConfig.name}</ruiec:apiConfig>
									</div>
								</td>
								<c:if test="${viewType == 0}">
									<td><a href="/admin/controlPersonAlarm/alarmDetail.shtml?id=${list.id}&pid=${list.controlPerson.id}"
										class="m_blue">详细</a></td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
				<c:if test="${typeId ne '0'}">
					<thead>
						<tr>
							<c:forEach var="list" items="${titles}" varStatus="item">
								<th <c:if test="${item.count==1}">class="pdl25"</c:if>>${list}</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="data" items="${page.list}">
							<tr>
								<c:forEach var="list" items="${data}" varStatus="item">
									<td <c:if test="${item.count==1}">class="pdl25"</c:if>>
									<em class="tab_detail">
										${list.value}
									</em>
									<div class="hideThis ellipsis_dateil">
										${list.value}
									</div>
									</td>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
			<c:if test="${ page.pageCount > 1 }">
				<div class="foot-cont">
					<div class="pagenation fr">
						<jsp:include page="../common/withinPage.jsp"></jsp:include>
					</div>
				</div>
			</c:if>
			<c:if test="${ empty page.list }">
				<div class="tc no_data_img">
					<img src="${base }/resources/admin/img/no_data_img.png"
						class="no_img_data">
					<p class="mgt10 nodata_text">~暂时没有数据呢~</p>
				</div>
			</c:if>
		</div>
	</div>
</form>

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
