<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../../common/tag.jsp"%>
<div class="checkChange-content">
	<div id="cc-table">
		<table class="h_list" width="100%" cellspacing="0" cellpadding="0s">
			<thead>
				<tr>
					<td width="10%">编号</td>
					<td width="15%">用户信息</td>
					<td width="15%">操作时间</td>
					<td width="60%">操作内容</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${page.list }" varStatus="index">
					<tr>
						<td>${list.id }</td>
						<td>${sessionScope.loginuser.emp}</td>
						<td><fmt:formatDate value="${list.regdate }" pattern="yy-MM-dd HH:mm:ss"/></td>
						<td>${list.content }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
	<!-- 底部 -->
<div class="personHouse-bottom">
	<div class="float-r">
		<div id="saleManager-fanye" class="commom-fanye "></div>
	</div>
</div>
<script>
	if ('${page.pages}' != null && '${page.pages}' != 0) {
		/*翻页控件*/
		$("#saleManager-fanye").paginate({
			count 		: '${page.pages}',
			start 		: '${page.pageNum}',
			display     : 10,
			border					: false,
			text_color  			: '#50b63f',
			text_hover_color		: '#fff',
			background_color    	: '#fff',	
			background_hover_color	: '#50b63f',
			images		: false,
			mouse		: 'click',
			onChange	: function(){
				searchApplyChange($(".jPag-current").html());
			}
		});
	}
	 
	$("#rTimer").val('undefined');
	$("#personHouse-order").val('1');
	if ('' != null && '' != '') {
		cs_search_ajax('', '', '', '',
				'', '','','',
				'', '','','');
	}
</script>