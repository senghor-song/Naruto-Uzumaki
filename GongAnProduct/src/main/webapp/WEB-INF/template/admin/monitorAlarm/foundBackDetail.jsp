<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<title><ruiec:title/></title>
    <link rel="stylesheet" href="${base}/resources/admin/css/layer.css" >
    <link rel="stylesheet" href="${base}/resources/admin/css/common.css">
    <link rel="stylesheet" href="${base}/resources/admin/css/style.css">
    <link rel="stylesheet" href="${base}/resources/admin/css/index.css">
    <link rel="stylesheet" href="${base}/resources/admin/css/quote.css">
</head>
<body class="bodyCss">
        <!--头部-->
        <jsp:include page="../common/header.jsp"></jsp:include> 
        <!---->
        <section>
        	<jsp:include page="../common/leftNav6.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                    <div class="new-people-box early_warning_box">
                        <form>
                            <div class="tit_menu_list">
                                <a href="/admin/common/main.shtml" class="m_menu_item">
                                    <i class="iconfont"></i>
                                    <em>首页</em>
                                </a>
                                <i class="fl split_gt">&gt;</i>
	                            <a href="/admin/controlPersonAlarm/list.shtml" class="m_menu_item">
	                                <em>重点人预警</em>
	                            </a>
	                            <i class="fl split_gt">&gt;</i>
	                            <a href="/admin/controlPersonAlarm/list.shtml" class="m_menu_item">
	                                <em>预警列表</em>
	                            </a>
                                <i class="fl split_gt">&gt;</i>
                                <a href="javascript:;" class="m_menu_item">
                                    <em>反馈详情</em>
                                </a>
                                <span class="fr">
	                        	    <input type="button" onclick="javascript:history.back(-1);" class="fr back_btn" value="返回上一页"/>
	                            </span>
                            </div>
                        </form>
                    </div>
                    <div class="during">
                    </div>
                </div>
            </div>
        </section>
    <!--js-->
    <script src="${base }/resources/admin/js/jquery-1.9.1.min.js"></script> 	
    <!--自写-->
    <script src="${base }/resources/admin/js/layout.js"></script> 
    <!---下拉框 -->
    <script src="${base }/resources/admin/js/common.js"></script> 
    <script type="text/javascript">
		$(function(){
			var _url = "/admin/monitorAlarm/alarmFeedbackDetails.shtml?id=${id}";
			var datailId = $("#datailId").val();
			FeedbackDetail(_url)	
		})
	</script>
</body>
</html>