<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<c:if test="${!empty massNotices}">

	<c:forEach var="list" items="${massNotices}">
		<ul id="notice-lis-content" class="notice-list clear">
			<li>
			<a href="/WebRelease/massNotice/details?noticeId=${list.id }" class="noticeTitle" id="${list.id }">
			【${list.type == 0 ? '系统公告' : list.type == 1 ? '网站公告' : list.type == 2 ? '客户公告' : list.type == 3 ? '公司公告' : '站内信息'} 】
			${list.title } </a>
			<span class="noticeTime"><fmt:formatDate value="${list.modifytime }" pattern="yy-MM-dd HH:mm"/></span>
			</li>
		</ul>	
	</c:forEach>	
</c:if>
<c:if test="${empty massNotices}">
	<div class="noticeTip-font">亲，暂时没有该类型的公告！</div>
</c:if>
			