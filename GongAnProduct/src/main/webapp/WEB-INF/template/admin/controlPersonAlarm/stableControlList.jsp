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
    <link rel="stylesheet" href="${base }/resources/admin/js/datetimepicker/jquery.datetimepicker.css" >
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
        	<jsp:include page="../common/leftNav2.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                    <form action="/admin/controlPersonAlarm/stableControlList.shtml" method="GET" id="searchform">
                        <div class="tit_menu_list">
                            <a href="/admin/common/main.shtml" class="m_menu_item">
                                <i class="iconfont">&#xe62a;</i>
                                <em>首页</em>
                            </a>
                             <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item">
                                <em>重点人预警</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item">
                                <em>稳控列表</em>
                            </a>
                        </div>
                        <div class="mgt20 importantC">
						<div class="mgt20" id="rylbCount">
							<div class="rule-single-select single-select">
								<select name="destination" style="display: none;" onchange="findForm()">
									<option value="">目的地</option>
									<c:forEach items="${destinationList}" var="list">
									   <option value="${list}" <c:if test="${list == destination}">selected="selected"</c:if> >${list}</option> 
									</c:forEach>           
								</select>
							</div>
							<div class="rule-single-select single-select">
								<select name="feedbackTime" style="display: none;" onchange="findForm()">
									<option value="">是否反馈</option>
									<option value="yes" <c:if test="${feedbackTime == 'yes'}">selected="selected"</c:if>>已反馈</option>
									<option value="no" <c:if test="${feedbackTime == 'no'}">selected="selected"</c:if>>未反馈</option>
								</select>
							</div>
							<div class="rule-single-select single-select">
								<select name="personType" style="display: none;" onchange="findForm()">
									<option value="">人员类别</option>
									<c:forEach items="${dataitem}" var="list">
										<option value="${list.id}" <c:if test="${list.id == personType}">selected="selected"</c:if>>${list.itemName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="rule-single-select single-select">
								    <select name="unitId" style="display: none;" onchange="findForm()">
                                    	<option value="">预警地区</option>
                                    	<c:forEach items="${units}" var="list">
                                           <option value="${list.id}" <c:if test="${list.id == unitId}">selected="selected"</c:if>>${list.unitName }</option>
                                        </c:forEach>
                                    </select>
							</div>
							<div class="rule-single-select single-select">
								<select name="signStatus" style="display: none;" onchange="findForm()">
									<option value="">是否签收</option>
									<option value="2"<c:if test="${signStatus == '2'}">selected="selected"</c:if>>已签收</option>
									<option value="1"<c:if test="${signStatus == '1'}">selected="selected"</c:if>>未签收</option>
								</select>
							</div> 
							<div class="searh_list">
								<dl class="fl mgl10 search_item">
									<dt>预警时间：</dt>
									<dd>
										<input name="startDate" class="Wdate text_inp " id="quote_time" autocomplete="off" type="text"
											value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd HH:mm"/>" />
										<em class="offDivide">至</em> <input name="endDate" class="Wdate text_inp" id="quote_time1" autocomplete="off" type="text"
											value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd HH:mm"/>" />
									</dd>
								</dl>
								<div class="fl mgl10 rule-single-select single-select">
									<select name="search" class="hideThis">
										<option value="name" <c:if test="${search == 'name'}">selected="selected"</c:if>>姓名</option>
										<option value="idCard" <c:if test="${search == 'idCard'}">selected="selected"</c:if>>身份证</option>
									</select>
								</div>
								<dl class="fl mgl5 search_item"> 
									<dd class="mgr30">
										<input type="text" maxlength="18" name="searchType" class="text_inp" value="${searchType}" ignore="ignore" id="forSearch" />
										<button type="submit" class="btn curr fr mgl10" id="search" style="cursor: pointer;">搜索</button>
									    <button type="button" id="reset" class="btn fr mgl10" onClick="resetBtn()">重置</button> 
									</dd>
								</dl>
							</div>
						</div>
					</div>
                        <div class="mgt20 importantN">
                            <div class="importantNList">
                              <input type="hidden" name="id" value="${list.id}"/>
                                <table class="tab tab_ellipsis">
                                    <colgroup>
                                        <col width="13%"/>
                                        <col width="8%"/>
                                        <col width="10%"/>
                                        <col width="10%"/>
                                        <col width="10%"/>
                                        <col width="10%"/>
                                        <col width="7%"/>
                                        <col width="7%"/>
                                        <col width="8%"/>
                                        <col width="6%"/>
                                        <col width="5%"/>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th class="pdl15">预警时间</th>
                                            <th>姓名</th>
                                            <th>人员类别</th>
                                            <th>动态信息类别</th>
                                            <th>管控责任中心 </th>
                                            <th>预警地派出所</th>
                                            <th>出发地</th>
                                            <th>目的地</th>
                                            <th>签收人</th>
                                            <th class="">稳控状态</th>
                                            <th class="textl">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
									<c:forEach items="${page.list}" var="list">
										<tr>
											<td class="pdl15"><fmt:formatDate value="${list.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${list.controlPerson.name }</td>
											<td>
											    <em class="tab_detail"> 
											        <c:forEach items="${list.controlPerson.controlPersonTypes}" var="d">
														<ruiec:dictionary var="dictionary" dictionaryId="${d.dictionaryId}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary>
													</c:forEach>
											    </em>
												<div class="hideThis ellipsis_dateil">
													<c:forEach items="${list.controlPerson.controlPersonTypes}" var="d">
														<ruiec:dictionary var="dictionary" dictionaryId="${d.dictionaryId}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary>
													</c:forEach>
												</div>
											</td>
											<td>
                                            	<em class="tab_detail"><ruiec:apiConfig var="api" apiId="${list.dynamicInfoTypeId}">${api == null?'无':api.name}</ruiec:apiConfig></em>
                                            	<div class="hideThis ellipsis_dateil"><ruiec:apiConfig var="api" apiId="${list.dynamicInfoTypeId}">${api == null?'无':api.name}</ruiec:apiConfig></div>
	                                        </td>
											<td> 
												<em class="tab_detail"><ruiec:unit var="unit" unitId="${list.controlUnit}">${unit.unitName}</ruiec:unit></em>
												<div class="hideThis ellipsis_dateil"><ruiec:unit var="unit" unitId="${list.controlUnit}">${unit.unitName}</ruiec:unit></div>
											</td>
											<td> 
											    <em class="tab_detail"><ruiec:unit var="unit" unitId="${list.alarmUnit}">${unit.unitName}</ruiec:unit></em>
											    <div class="hideThis ellipsis_dateil"><ruiec:unit var="unit" unitId="${list.alarmUnit}">${unit.unitName}</ruiec:unit></div>
											</td>
											<td>${list.origin}</td>
											<td>${list.destination }</td>
											<td><ruiec:user var="showUser" userId="${list.signPrikey}">${showUser == null?'':showUser.policeName}</ruiec:user></td>
											<td><ruiec:dictionary var="dictionary" dictionaryId="${list.status}">${list.status == null?'---':dictionary.itemName}</ruiec:dictionary></td>
											<td><a href="/admin/controlPersonAlarm/alarmDetail.shtml?id=${list.id}&pid=${list.controlPerson.id}"class="m_blue">详细</a></td>
										</tr>
									</c:forEach>
								</tbody>
                                </table>
	                        	<c:if test="${ page.pageCount > 1}">
	                                <div class="foot-cont">
	                                    <div class="pagenation fr">
	                                        <jsp:include page="../common/page.jsp" ></jsp:include>
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
    <!--自写-->
    <script src="${base }/resources/admin/js/layout.js"></script>
    <!--时间-->
    <script src="${base }/resources/admin/js/datetimepicker/jquery.datetimepicker.js"></script>
    <script src="${base }/resources/admin/js/datetimepicker/timer.js"></script>
    <!--验证-->
    <script src="${base }/resources/admin/js/jquery.form.js"></script>
    <script src="${base }/resources/admin/js/Validform_v5.3.2/Validform_v5.3.2.js"></script>
    <!---下拉框 -->
    <script src="${base }/resources/admin/js/common.js"></script> 
    
    <link href="${base }/resources/admin/js/tooltips/tooltips.css" rel="stylesheet" id="skin">
    <script src="${base }/resources/admin/js/tooltips/bootstrap-tooltip.js"></script>
    <script src="${base }/resources/admin/js/tooltips/bootstrap-affix.js"></script>
    <script src="${base }/resources/admin/js/tooltips/application.js"></script>
    <script src="${base }/resources/admin/js/tooltips/jquery.pure.tooltips.js"></script>
     <script type="text/javascript">
     $(function() {
	    $('select[name="search"]').trigger('change');
		})
		$('select[name="search"]').on('change',
			function() {
				var sea = $(this).find('option:selected').val();
				if (sea == 'name') {
					$("input[name='searchType']").attr('datatype','/[\u4e00-\u9fa5·]$/');
					$("input[name='searchType']").attr('errormsg','请输入中文');
					
				} else {
					$("input[name='searchType']").attr('datatype', 'isIDCard');
					$("input[name='searchType']").removeAttr('errormsg');
				}
			})
			
	     if( $("#searchform").size()>0 ){
	     	$.Tipmsg.r=null;
	     	$("#searchform").Validform({
	 			tiptype:function(msg){
		 			if(msg != ""){
		 				layer.msg(msg);
			 		} 
	 			}, 
	 			tipSweep:true,
	 			callback:function(form){  
	 				//btnSubmit.submit();
	 			}
	 		});	
	     }

	    $(function(){
	    	if( $("#searchform").size()>0 ){
		     	$.Tipmsg.r=null;
		     	$("#searchform").Validform({
		 			tiptype:function(msg){ 
		 				if( msg != "" && msg != null && msg != undefined  ){
		 					layer.msg(msg);	
		 				}
		 			}, 
		 			tipSweep:true,
		 			callback:function(form){}
		 		});	
		     }; 
	   	}); 
	     function findForm(){
	     	$('#searchform').submit(); 
	     }
	   
   	</script>
</body>
</html>