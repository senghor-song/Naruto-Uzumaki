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
        	<jsp:include page="../common/leftNav3.jsp"></jsp:include> 
             <div class="mainRight">
                <div class="pancl_pd">          
                    <form  action="/admin/systemSelect/controlPersonAlarmList.shtml" method="GET" id="searchform">
                         <div class="tit_menu_list">
                            <a href="/admin/common/main.shtml" class="m_menu_item">
                                <i class="iconfont">&#xe62a;</i>
                                <em>首页</em>
                            </a>
                             <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item">
                                <em>系统查询</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item">
                                <em>预警信息查询</em>
                            </a>
                        </div>
                        <div class="mgt20 importantC">
                           <div class="mgt15" id="rylbCount">
                                <div class="fl rule-single-select single-select">
                                    <select name="search" >
                                        <option value="name" <c:if test="${search == 'name'}">selected="selected"</c:if>>姓名</option>
                                        <option value="idCard" <c:if test="${search == 'idCard'}">selected="selected"</c:if>>身份证</option>
                                    </select>
                                </div>
                                <div class="fl mgl10">
                                    <dl class="search_item">
                                        <dt></dt>
                                        <dd>
                                            <input class="w250 text_inp" maxlength="18" autocomplete="off" name="searchType" value="${searchType}" type="text" ignore="ignore" id="forSearch"/>
                                            <button type="submit" href="javascript:void(0);" class="btn curr fr mgl10" id="search" style="cursor: pointer;">搜索</a>
                                            <button type="button" id="reset" class="btn fr mgl10" onClick="resetBtn()">重置</button>
                                        </dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
                        <div class="bar_line"></div>
                        <div class="mgt importantN">
                            <div class="mgt importantNList">
                               <input type="hidden" name="system" value="controlPersonAlarm" id="text"/>
                                <table class="tab tab_ellipsis">
                                    <colgroup>
                                        <col width="8%"/>
                                        <col width="12%"/>
                                        <col width="8%"/>
                                        <col width="12%"/>
                                        <col width="13%"/>
                                        <col width="11%"/>
                                        <col width="14%"/>
                                        <col width="11%"/>
                                        <col width="10%"/>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th class="pdl15">预警级别</th>
                                            <th>预警时间</th>
                                            <th>姓名</th>
                                            <th>人员类别</th>
                                            <th>动态信息类别</th> 
                                            <th>管控责任中心</th>
                                            <th>预警地派出所</th>
                                            <th>签收人</th>
                                            <th class="textl">操作</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                   	<c:forEach items="${page.list }" var="list">
                                        <tr>
                                            <td class="pdl15">
	                                   		  <ruiec:dictionary var="dictionary" dictionaryId="${list.warningLevel }">
	                                            	<i class="level m_level_blue" style="background-color:${dictionary == null?'无':dictionary.itemValue}">
	                                            		${dictionary == null?'无':dictionary.itemName}
	                                            	</i>
	                                          </ruiec:dictionary>
	                                   		</td>
                                            <td><fmt:formatDate value="${list.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <td>${list.controlPerson.name }</td>
                                            <td>
                                              <em class="tab_detail" >
                                                  <c:forEach var="d" items="${list.controlPerson.controlPersonTypes}" >
                                                     <ruiec:dictionary var="dictionary" dictionaryId="${d.dictionaryId}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary> 
                                                  </c:forEach>
                                              </em>
                                              <div class="hideThis ellipsis_dateil">
                                               <c:forEach var="d" items="${list.controlPerson.controlPersonTypes}" >
                                                 <ruiec:dictionary var="dictionary" dictionaryId="${d.dictionaryId}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary> 
                                               </c:forEach>
                                              </div>
                                            </td> 
                                           <td>
	                                           	<em class="tab_detail"><ruiec:apiConfig var="api" apiId="${list.dynamicInfoTypeId}">${api == null?'无':api.name}</ruiec:apiConfig></em>
	                                           	<div class="hideThis ellipsis_dateil"><ruiec:apiConfig var="api" apiId="${list.dynamicInfoTypeId}">${api == null?'无':api.name}</ruiec:apiConfig></div>
	                                        </td>
                                            <td>
                                               <em class="tab_detail"> <ruiec:unit var="unit" unitId="${list.controlUnit}">${unit.unitName}</ruiec:unit></em>
                                               <div class="hideThis ellipsis_dateil"> <ruiec:unit var="unit" unitId="${list.controlUnit}">${unit.unitName}</ruiec:unit></div>
                                            </td>
	                                        <td>
	                                          <em class="tab_detail"><ruiec:unit var="unit" unitId="${list.alarmUnit}">${unit.unitName}</ruiec:unit></em>
	                                          <div class="hideThis ellipsis_dateil"><ruiec:unit var="unit" unitId="${list.alarmUnit}">${unit.unitName}</ruiec:unit></div>
	                                         </td>  
                                            <td><ruiec:user var="showUser" userId="${list.signPrikey}">${showUser == null?'':showUser.policeName}</ruiec:user></td>
                                            <td>
                                                <a  class="m_blue" onclick="AOnclick(${list.id},${list.controlPerson.id},2,name)">详细</a>
                                            </td>                           
                                         </tr>
                                       </c:forEach>
                                    </tbody>
                                </table> 
	                            <c:if test="${empty page.list}">
		                            <div class="tc no_data_img">
				                    	<img src="${base }/resources/admin/img/no_data_img.png" class="no_img_data">
				                    	<p class="mgt10 nodata_text">~请输入内容查询~</p>
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
    <script src="${base }/resources/admin/js/Validform_v5.3.2/Validform_v5.3.2.js"></script>
    <!--拖拽-->
    <script src="${base }/resources/admin/js/gridster/jquery.gridster.min.js"></script>
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
		$('select[name="search"]').on('change',function() {
				var sea = $(this).find('option:selected').val();
				if (sea == 'name') {
					$("input[name='searchType']").attr('maxlength','10');
					$("input[name='searchType']").attr('datatype','/[\u4e00-\u9fa5·]$/');
					$("input[name='searchType']").attr('errormsg','请输入中文');
					$("input[name='searchType']").removeAttr('onkeyup');
				} else {
					$("input[name='searchType']").attr('onkeyup','idCardKeyUp($(this))');
	        		$("input[name='searchType']").attr('maxlength','18');
	        		$("input[name='searchType']").attr('datatype','isIDCard');
					$("input[name='searchType']").removeAttr('errormsg');
				}
			})
		if ($("#searchform").size() > 0) {
			$.Tipmsg.r = null;
			$("#searchform").Validform({
				tiptype : function(msg) {
					if(msg != ""){
		 				layer.msg(msg);
			 		} 
				},
				tipSweep : true,
				callback : function(form) {
					//btnSubmit.submit();
				}
			});
		}
		function findForm() {
			$('#searchform').submit();
		}
	</script>
	
	<script type="text/javascript">
		function AOnclick(id,pid,viewType,name){
		window.location.href="/admin/controlPersonAlarm/alarmDetail.shtml?id="+id+"&pid="+pid+"&viewType=2"+"&name="+$("#text").val();
		}
    </script>
</body>
</html>