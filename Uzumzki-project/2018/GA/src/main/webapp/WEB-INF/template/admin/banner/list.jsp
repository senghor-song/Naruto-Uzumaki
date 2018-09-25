<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><ruiec:title /></title>
<link rel="stylesheet" href="${base }/resources/admin/css/layer.css">
<link rel="stylesheet" href="${base }/resources/admin/css/common.css">
<link rel="stylesheet" href="${base }/resources/admin/css/style.css">
<link rel="stylesheet" href="${base }/resources/admin/css/index.css">
<link rel="stylesheet" href="${base }/resources/admin/css/quote.css">
</head>
<body class="bodyCss">
	<!--头部-->
	<jsp:include page="../common/header.jsp"></jsp:include>
	<!---->
	<section>
		<jsp:include page="../common/leftNav5.jsp"></jsp:include>
		<div class="mainRight">
			<div class="pancl_pd">
				<form id="findForm" action='/admin/banner/list.shtml' method="get">
					<div class="tit_menu_list importantC">
						<a href="/admin/common/main.shtml" class="m_menu_item"> 
							<i class="iconfont">&#xe62a;</i> <em>首页</em>
						</a> 
						<i class="fl split_gt">&gt;</i> 
						<a href="javascript:;" class="m_menu_item"> <em>宣传管理</em>
						</a> 
						<i class="fl split_gt">&gt;</i> 
						<a href="/admin/banner/list.shtml" class="m_menu_item"> <em>登录图片列表</em>
						</a>
					</div>
					<div class="m_bg_ef mgt20 Allcrumb">
						<div class="fl crumb_item">
							<a href="javascript:void(0);" class="item_a" onclick="checkAll(this)">全选</a> 
							<a href="javascript:void(0);" class="item_a" onclick="del()">删除</a>
							<a href="javascript:void(0);" class="item_a" onclick="showApp()">设置为app显示</a>
						</div>
						<div class="fr crumb_item">
							<a href="/admin/banner/add.shtml" class="item_a" onclick="checkAll(this)">新建</a>
						</div>
					</div>
					<div class="mgt20 importantNList">
						<table class="tab">
							<colgroup>
								<col width="3%" />
								<col width="35%" />
								<col width="31%" />
                                <col width="20%" />
								<col width="10%" />
							</colgroup>
							<thead>
								<tr>
									<th>&nbsp;</th>
									<th>图片</th>
									<th>是否作为登录背景图</th>
                                    <th>是否作为APP宣传图</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="list" items="${page.list }">
									<tr>

										<td style="text-align: center">
										<input type="checkbox" class="checkItem" name="ids" value="${list.id}"/>
										</td>
										<td><c:if test="${!empty list.image }">
												<img src="${list.image}" name="image" class="m_notic_img" style="width: 100px;height: 69px;border: 1px solid #ddd;"></img>
											</c:if> 
											<c:if test="${empty list.image }">
												<img src="${base }/resources/admin/images/Avatar.png" name="image" class="m_notic_img" style="width: 100px;height: 69px;border: 1px solid #ddd;"></img>
											</c:if>
										</td>
										<td>
										    <em class="m_b">
										        <c:if test="${list.isshow==1}">是</c:if>
											    <c:if test="${list.isshow==0}">否</c:if>
										    </em>
										</td>
                                        <td>
                                            <em class="m_b">
                                                <c:if test="${list.isShowApp==1}">是</c:if>
                                                <c:if test="${list.isShowApp==0}">否</c:if>
                                            </em>
                                        </td>
										<td><a href="edit.shtml?id=${list.id}" class="m_b">编辑</a>
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
								<img src="${base }/resources/admin/img/no_data_img.png"
									class="no_img_data">
								<p class="mgt10 nodata_text">~暂时没有数据呢~</p>
							</div>
						</c:if>
					</div>
				</form>
			</div>
		</div>
	</section>
	<!--js-->
	<script src="${base }/resources/admin/js/jquery-1.9.1.min.js"></script>
	<script src="${base }/resources/admin/js/layer.js"></script>
	<!--验证-->
	<script src="${base }/resources/admin/js/jquery.form.js"></script>
	<script
		src="${base }/resources/admin/js/Validform_v5.3.2/Validform_v5.3.2.js"></script>
	<!--自写-->
	<script src="${base }/resources/admin/js/layout.js"></script>
	<!---下拉框 -->
	<script src="${base }/resources/admin/js/common.js"></script>
	<!--日期-->
	<script
		src="${base }/resources/admin/js/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="${base }/resources/admin/js/datetimepicker/timer.js"></script>

	<link href="${base }/resources/admin/js/tooltips/tooltips.css"
		rel="stylesheet" id="skin">
	<script src="${base }/resources/admin/js/tooltips/bootstrap-tooltip.js"></script>
	<script src="${base }/resources/admin/js/tooltips/bootstrap-affix.js"></script>
	<script src="${base }/resources/admin/js/tooltips/application.js"></script>
	<script
		src="${base }/resources/admin/js/tooltips/jquery.pure.tooltips.js"></script>
	<script type="text/javascript">
		function findForm() {
			$('#findForm').submit();
		}
		//设置为app显示
		function showApp(){
		    var $ids = $(".tab input[name='ids']:enabled:checked");
		    if ($ids.size() < 1) {
		    	layer.msg('至少设置一张图片');
		        return;
		    }else if($ids.size() > 4) {
		    	layer.msg('最多设置四张图片');
		        return;
		    }
		    //询问框
		    layer.confirm('是否设置为APP显示图片?', {
		      btn: ['确认','取消'] //按钮
		    }, function(){
		       $.ajax({
		            type:"post",
		            url:"setAppBanner.shtml",
		            data:$ids.serialize(),
		            dataType:"json",
		            success:function(data) {  	 	 
		            	if(data.code == 200){
		                	layer.msg(data.msg,{icon: 1,shade:0.2});
		                	setTimeout(function(){
		                		location.reload();
	    		    		},1000)		                    
		            	}else if(data.code == 400){
		                	layer.msg(data.msg,{icon: 2});
		            	}
		            },
		            error:function(data) {
		            	layer.msg("设置失败",{icon: 2}); 
		            }
		       });
		    });
		} 
	</script>
</body>
</html>