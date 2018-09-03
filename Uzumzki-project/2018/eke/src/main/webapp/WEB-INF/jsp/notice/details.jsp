<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>公告信息</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/notice_bf8b3a2904797cabab142a1644c2b494.css' rel='stylesheet' type='text/css'></link>
  </head>
<body>
<div class="saleHouse" style="min-height: 381px;">
	<ul class="sale-tab-sub">
		<li class="sale-sub-menu selected">
			<a href="javascript:">公告内容</a>
		</li>
	</ul>
	<div class="saleHouse-main noticeContent">
		<div class="noticeContent-title">${massNotice.title }</div>
		<div class="noticeContent-main">
			<div class="noticeContent-time"><span>发布时间:</span><span><fmt:formatDate value="${massNotice.modifytime }" pattern="yy-MM-dd HH:mm"/></span></div>
			
			<div class="noticeContent-content">
				${massNotice.content }
			</div>
			
			<div class="noticeContent-bottom"><a href="/WebRelease/massNotice/noticeList">公告列表&gt;&gt;</a></div>
		</div>
	</div>
</div>
<script src="<%=path%>/Static/Js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="<%=path%>/Static/Js/common_b016234725ebd1612a8f62bf4261c244.js" type="text/javascript"></script>
<script src="<%=path%>/Static/Js/notice_544d3e9e7db1df8e796b69419dbdd4e7.js" type="text/javascript"></script>

</html>