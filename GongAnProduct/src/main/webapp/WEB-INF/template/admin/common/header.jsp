<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!--头部--> 
<div class="shard_box"></div>
<div class="loading" style="display: none;">
	<div class="demo">
		<i></i>
		<i></i>
		<i></i>
		<i></i>
		<i></i>
	</div>
</div>
<header>
    <div class="head">
        <a href="/admin/common/main.shtml" class="m_logo"><img src="${base }/resources/admin/img/logo.png" alt=""></a>
        <ul class="nav_list">
            <li calss="main_topnav"><a href="/admin/common/main.shtml" class="curr main_topnav" ><i class="iconfont main_topnav" >&#xe62a;</i></a></li>
            <li><a href="/admin/controlPerson/list.shtml?personnelType=1">人员</a></li>
            <li><a href="/admin/controlPersonAlarm/list.shtml">预警</a></li>
            <li><a href="/admin/monitor/list.shtml">布控管理</a></li>
            <c:if test="${!empty sessionScope.loginUserUnit.unitIds}">
            	<li><a href="/admin/syntheticAnalysis/taskList.shtml">合成研判</a></li>
            </c:if>
            <li><a href="/admin/systemSelect/controlPersonList.shtml">系统查询</a></li>
            <li><a href="/admin/informationAnalysis/information.shtml">情报分析</a></li>
            <c:if test="${sessionScope.user.id == 1}">
            	<li><a href="/admin/user/list.shtml">系统</a></li>
            </c:if>
        </ul>
        <div class="admin fr">
            <!-- <p class="admin_ietm_p"><a href="#"><i class="iconfont">&#xe62d;</i><em>5</em></a></p> -->
             <div class="admin_ietm_p post_rela notic_Show">
	           	<a href="/admin/notice/noticelist.shtml"><i class="iconfont">&#xe62d;</i><em style="color: red" id="count"></em></a>
	           	<div class="notic_itemWrap">
	           		<i class="icon_triangle"><img src="${base }/resources/admin/img/icon_triangle.png" /></i>
	           		<div class="bg_wite notic_itembox"> 
	           			<ul class="notic_itemList"></ul>
	           			<div class="notic_more">
	           				<a href="/admin/notice/noticelist.shtml">点击查看更多</a>
	           			</div>
	           		</div>
	           	</div>
	           </div>
            <p class="admin_ietm_p"><a href="#"><img src="${base }/resources/admin/img/Administrator.png" alt=""></a></p> 
            <div class="admin_wrap">
                 <div class="admin_show">
                     <h4>${sessionScope.user.policeName}</h4>
                     <span class="fz12">
                     <c:if test="${sessionScope.user.id != 1}">
		             	<ruiec:unit var="unit" unitId="${sessionScope.user.unitId}">${unit == null?'无':unit.unitName}</ruiec:unit>
                     </c:if>
                     </span>
                     <i></i>
                 </div>
                 <div class="quit_item">
                     <div class="tc quit_item_list">
                         <ul>
                             <li>
                                 <a>
                                     <img class="admin_img" src="${base }/resources/admin/img/Administrator.png" alt="">
                                     <label class="quit_item_list_label">
                                     	 <c:if test="${sessionScope.user.id != 1}">
	                                         <p class="login_Admin c_3"><i class="Admin_name">${sessionScope.user.policeName}</i></p>
	                                         <p class="m_b login_diction">（${sessionScope.loginUserUnit.userName}）</p>
										 </c:if>
										 <c:if test="${sessionScope.user.id == 1}">
	                                         <p class="login_Admin c_3">超级管理员</p>
	                                         <p class="m_b login_diction">（超级管理员）</p>
										 </c:if>
                                     </label>
                                 </a>
                             </li>
                             <li>
                                 <a class="" href="/admin/pwview.shtml">
                                     <em class="iconfont icon_pwd">&#xe622;</em><em>修改密码</em>
                                 </a>
                             </li>
                             <li>
                                 <a class="close_wind" href="/admin/logout.shtml">
                                     <em class="iconfont">&#xe67f;</em><em>退出登录</em>
                                 </a>
                             </li>
                         </ul>
                     </div>
                 </div>
             </div>
        </div>
    </div>
    <script src="${base }/resources/admin/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
    	$(function(){
        	$(".m_menu_item:first").on('click', function(){
        		$(".main_topnav").click();
        	})
    		$.ajax({
    		    url:'/admin/notice/nlist.shtml',
    		    type:'POST',
    		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
    		    success:function(data,textStatus,jqXHR){
    		    	if(data.data.data != null){
    		    		for(var i=0;i<data.data.data.length;i++){
        		    		$('.notic_itemList').append("<li><a href='/admin/notice/detail.shtml?id="+data.data.data[i].id+"&uid=${sessionScope.user.id}'><span class='fl notic_txt'>"+data.data.data[i].title.substring(0,10)+"</span><i class='fr notic_time'>"+data.data.data[i].createDate+"</i></a></li>");
        		    	}
    		    	}
    		    	$("#count").html(data.data.count);
    		    },
    		})
    	})
    </script>
</header>