<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<a href="javascript:;" onclick="loadPage(0,1)" class="personal_catalogue_list m_blue"> 
	预警信息(<i class="catalogue_num">${alarmCount }</i>)
</a>
<c:forEach var="list" items="${dynamicInfos}">
	<a href="javascript:;" onclick="loadPage(${list.id },1)"
		class="personal_catalogue_list"> <ruiec:apiConfig
			var="api" apiId="${list.id }">${api == null?'无':api.name}</ruiec:apiConfig>
		(<i class="catalogue_num" onclick="">${list.count}</i>)
	</a>
</c:forEach>
<!--js-->
<script src="${base }/resources/admin/js/jquery-1.9.1.min.js"></script>
<!---下拉框 -->
<script src="${base }/resources/admin/js/common.js"></script>
