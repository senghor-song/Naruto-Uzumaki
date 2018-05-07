<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html >
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
        <!--头部-->
        <jsp:include page="../common/header.jsp"></jsp:include> 
        <!---->
        <section>
        	<jsp:include page="../common/leftNav5.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                    <form action="/admin/dbField/list.shtml" method="GET">
                        <input type="hidden" name="dbId" value="${dbId }" />
                        <input type="hidden" name="id" value="${tid }" />
                        <div class="tit_menu_list">
                            <a href="/admin/common/main.shtml" class="m_menu_item">
                                <i class="iconfont">&#xe62a;</i>
                                <em>首页</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item refresh_html">
                               <em>数据导入</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="/admin/db/list.shtml" class="m_menu_item">
                                <em>数据源列表</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="/admin/dbTable/list.shtml?id=${dbId }" class="m_menu_item">
                                <em>数据导入表列表</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item refresh_html">
                                <em>数据导入字段列表</em>
                            </a>
                            <span class="fr">
	                        	<input type="button" onclick="javascript:history.back(-1);" class="fr back_btn" value="返回上一页"/>
	                        </span>
                        </div>
                        <div class="mgt15 importantC">
                            <div class="m_bg_ef Allcrumb">
                                <div class="fl crumb_item">
                                	<a href="javascript:void(0);" class="item_a" onclick="checkAll(this)">全选</a>
                                    <a href="javascript:void(0);" class="item_a" onclick="del()">删除</a>
                                </div>
                                <div class="fr crumb_item">
                                    <a href="/admin/dbField/add.shtml?dbId=${dbId }&tid=${tid}" class="item_a">新建</a>
                                </div>
                            </div>
                            <div class=" mgt15 importantNList">
                                <table class="tab">
                                    <colgroup>
                                        <col width="3%"/>
                                        <col width="20%"/>
                                        <col width="20%"/>
                                        <col width="15%"/>
                                        <col width="10%"/>
                                        <col width="15%"/>
                                        <col width="17%"/>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>&nbsp;</th>
                                            <th>字段名</th>
                                            <th>对应我方字段名 </th>
                                            <th>字段类型</th>
                                            <th>是否必须</th>
                                            <th>创建时间</th>
                                            <th class="textl">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       <c:forEach var="list" items="${page.list }">
                                        <tr>
			                                <td style="text-align: center"><input type="checkbox" class="checkItem" name="ids" value="${list.id }"/></td>
                                            <td>${list.fieldName}</td>
                                            <td>${list.dbShow.name}</td>
                                            <td>
												<c:if test="${list.fieldType==0 }">整型</c:if>
												<c:if test="${list.fieldType==1 }">字符串</c:if>
												<c:if test="${list.fieldType==2 }">日期</c:if>
											</td>
											<td>${list.isNeed==1?'是':'否'}</td>
                                            <td><fmt:formatDate value="${list.createDate }" pattern="yyyy-MM-dd" /></td>
                                            <td>
                                                <a href="edit.shtml?id=${list.id}&dbId=${dbId}&tid=${tid}" class="m_b">编辑</a>
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
</body>
</html>