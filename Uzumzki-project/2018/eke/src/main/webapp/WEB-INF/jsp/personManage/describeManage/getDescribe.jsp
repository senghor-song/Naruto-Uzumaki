<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../../common/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>房源描述</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/saleImport_2863c1add81bbc61b654f8408bbd6b46.css' rel='stylesheet' type='text/css'></link>
  </head>
<body >
<div class="desc" id="houseDesc">
	<div class="houseDesc-select"><input type="text" class="houseDesc-input" placeholder="请输入房源描述标题" value="${title }"></input></div>
	<div class="desc-main">
		<input type="hidden" id="pageNum" value="${pageInfo.pageNum }"></input>
		<input type="hidden" id="postType" value="0"></input>
		<input type="hidden" id="pageCount" value="${pageInfo.pages }"></input>
		<input type="hidden" id="curType" value=${templateType }></input>
		<div class="desc-content" style="height:350px">
			<c:forEach var="list" items="${pageInfo.list}">
				<div class="desc-list">
					<p class="desc-name">${list.title }</p>
					<ul>
						<c:if test="${templateType eq 'describedata'}">
							<li>
								<h5>房源描述</h5>
								<div class="desc_descContent">${list.describedata }</div>
							</li>
							<br/>
						</c:if>
						<c:if test="${templateType eq 'describedata' or templateType eq 'mentality'}">
							<li>
								<h5>业主心态</h5>
								<div class="desc_yeZhuXingTai">${list.mentality }</div>
							</li>
							<br/>
						</c:if>
						<c:if test="${templateType eq 'describedata' or templateType eq 'environment'}">
							<li>
								<h5>小区配套</h5>
								<div class="desc_xiaoQuPeiTao">${list.environment }</div>
							</li>
							<br/>
						</c:if>
						<c:if test="${templateType eq 'describedata' or templateType eq 'serve'}">
							<li>
								<h5>服务介绍</h5>
								<div class="desc_fuWuJieShao">${list.serve }</div>
							</li>
							<br/>
						</c:if>
						<c:if test="${templateType eq 'describedata' or templateType eq 'taxation'}">
							<li>
								<h5>税费信息</h5>
								<div class="desc_shuiFeiXinxi">${list.taxation }</div>
							</li>
						</c:if>
					</ul>
				</div>
			</c:forEach>
		</div>
		<div class="saleManager-bottom">
			<div class="float-l">
				<div id="desc-fanye" class="commom-fanye jPaginate" style="padding-left: 64px;"></div>
			</div>
		</div>
	</div>
</div>

<script src='<%=path%>/Static/Js/houseDesc_97584a0478307124e4a0e2473d6dc5a2.js' type='text/javascript'></script>

</body>
</html>