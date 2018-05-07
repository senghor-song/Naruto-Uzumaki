<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
        	<c:if test="${sessionScope.user.id == 1}">
        		<jsp:include page="../common/leftNav5.jsp"></jsp:include>
        	</c:if>
        	<c:if test="${sessionScope.user.id != 1}">
        		<jsp:include page="../common/leftNav.jsp"></jsp:include>
        	</c:if>
            <div class="mainRight">
                <div class="pancl_pd">
                    <div class="tit_menu_list">
                        <a href="/admin/common/main.shtml" class="m_menu_item">
                            <i class="iconfont">&#xe62a;</i>
                            <em>首页</em>
                        </a>
                         <i class="fl split_gt">&gt;</i>
                        <a href="javascript:;" class="m_menu_item">
                            <em>宣传管理</em>
                        </a>
                        <c:if test="${sessionScope.user.id != 1}">
                        <i class="fl split_gt">&gt;</i>
                        <a href="/admin/notice/noticelist.shtml" class="m_menu_item">
                            <em>公告列表</em>
                        </a>
                        </c:if>
                        <c:if test="${sessionScope.user.id == 1}">
                        <i class="fl split_gt">&gt;</i>
                        <a href="/admin/notice/list.shtml" class="m_menu_item">
                            <em>公告列表</em>
                        </a>
                        </c:if>
                        <i class="fl split_gt">&gt;</i>
                        <a href="javascript:;" class="m_menu_item">
                            <em>公告详情</em>
                        </a>
                       <span class="fr">
                       	<a href="/admin/notice/noticelist.shtml" type="button" class="fr back_btn">
                            <em>返回上一页</em>    
                        </a>
                      </span>
                    </div>
                    <div class="tc pd20 notic_dateil">
                        <div class="mgt10 notic_dateil_tit">
                            <h3 class="tit">${notice.title }</h3>
                            <p class="tit_data"><fmt:formatDate value="${notice.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                        </div>
                        <div class="mgt15 notic_cont">
                           <p>${notice.content}</p>
                        </div>
                    </div>
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
    <script type="text/javascript">
	    function findForm(){
	    	$('#findForm').submit();
	   	}
   	</script>
</body>
</html>