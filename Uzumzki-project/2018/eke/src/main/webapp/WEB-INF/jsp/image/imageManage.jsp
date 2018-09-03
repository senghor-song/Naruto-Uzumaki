<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的图库管理</title>
<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/selectPic_ec02fc16ad89e3d22179bdb53e8585a0.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>
<link href='<%=path%>/Static/Css/viewer.min.css' rel='stylesheet' type='text/css'></link>
</head>
<body>
<input type="hidden" id="estateid" value=""></input>
<input type="hidden" id="countf" value=""></input>
<input type="hidden" id="type" value=""></input>
<div id="saleHouse" class="saleHouse">
	<div class="saleHouse-main saleManager-main">
		<ul class="sale-tab-sub">
			<li class="sale-sub-menu selected">
				<a href="javascript:">我的图库管理</a>
			</li>
		</ul>
		<div class="myImgManage">
			<div class="myImgManage-header">
				<ul class="myImgManage-right float-r">
					<li class="myHouse" name="li-it" imageType="0"><a>我的小区图</a></li>
					<li id="myHome" name="li-it" imageType="1"><a>我的室内图</a></li>
					<li class="myHouse myImg-selected" name="li-it" imageType="2" class="myImg-selected"><a>我的户型图</a></li>
				</ul>
			</div>
			<div class="myImgManage-select clear">
				<select id="cell" class="myImg_select">
					<option value="">全部小区</option>
					<c:forEach items="${estateImageDtos }" var="list">
						<option value="${list.estateid }">${list.estate }</option>
					</c:forEach>
				</select>
				<select id="room" class="myImg_select">
					<option value="">全部户型</option>
					<option value="1">1房</option>
					<option value="2">2房</option>
					<option value="3">3房</option>
					<option value="4">4房</option>
					<option value="5">5房或5房以上</option>
				</select>
			</div>
			<div class="myImgManage-images claer" id="images">
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
			</div>
		</div>
		<div class="myImgManage-bottom clear">
			<div class="float-l">
				<input type="checkbox" id="selectAll"></input><label for="selectAll">全选</label>
				<!-- <input type="button" class="btn_g" value="共享"  id="btn_share"></input> -->
				<input type="button" class="btn_g" value="删除"  id="btn_delete"></input>
			</div>
			<div class="float-r">
				<div id="myImgManage-fanye" class="commom-fanye">
				</div>
			</div>
		</div>
	</div>
</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.lazyload.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/lazyload_b48db04a5a2ab61ce6f6ec45530c3bfb.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.paginate.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js?skin=green' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/myImgManage_cf7dbcb383355df87e53857623b5c47d.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/viewer.min.js' type='text/javascript'></script>
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
</body>
</html>