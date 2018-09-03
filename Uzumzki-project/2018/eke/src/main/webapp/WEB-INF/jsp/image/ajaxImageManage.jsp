<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>

<c:forEach items="${page.list}" var="list">
	<ul class="myImgManage-content">
		<li>
			<img class="pointer" src="${list.path }" width="120" data-original="${list.path }" height="90">
		</li>
		<li class="myImgManage-input">
			<span class="myImgManage-font" title="${list.estate } ${list.countf }室">${list.estate } ${list.countf }室</span><br>
			<span>
			<input type="checkbox" name="image_check" imgurl="${list.id }"></span>
		</li>
	</ul>
</c:forEach>
			
<script>
var viewer = new Viewer(document.getElementById('images'), {
	url: 'data-original'
});
if ('${page.pages}' != null && '${page.pages}' != 0) {
	$(function(){
		$("#myImgManage-fanye").paginate({
			count 		: '${page.pages}',
			start 		: '${page.pageNum}',
			display     : 6,
			border					: false,
			text_color  			: '#50b63f',
			text_hover_color		: '#fff',
			background_color    	: '#fff',	
			background_hover_color	: '#50b63f',  
			images		: false,
			mouse		: 'click',
			onChange	: function(){
				pageNow = $(".jPag-current").html();
				getMyImagesByCon(pageNow);
			}
		});
	});
}
</script>
</html>