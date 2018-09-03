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
    <link rel="stylesheet" href="${base }/resources/admin/js/datetimepicker/jquery.datetimepicker.css" />
    <link rel="stylesheet" href="${base }/resources/admin/css/layer.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/common.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/style.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/index.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/quote.css" /> 
	<link href="${base }/resources/admin/js/tooltips/tooltips.css" rel="stylesheet" id="skin">
	<style>
		.pt .cont{ width:480px !important;}
	</style>
</head>
<body class="bodyCss">
         <!--头部-->
        <jsp:include page="../common/header.jsp"></jsp:include> 
        <!---->
        <section>
        	<jsp:include page="../common/leftNav3.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                    <form action="/admin/systemSelect/leavePersonList.shtml" method="get" id="searchform">
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
                                <em>离市查询</em>
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
                            <div class="importantNList">
                               <div class="importantN">
                                <table class="tab tab_ellipsis">
                                   <colgroup>
                                        <col width="3%"/>
                                        <col width="8%"/>
                                        <col width="5%"/>
                                        <col width="4%"/>
                                        <col width="11%"/>
                                        <col width="8%"/>
                                        <col width="7%"/>
                                        <col width="7%"/>
                                        <col width="5%"/>
                                        <col width="7%"/>
                                        <col width="8%"/>
                                        <col width="8%"/>
                                        <col width="7%"/>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>&nbsp;</th>
                                            <th class="pdl15">入库时间</th>
                                            <th>姓名</th>
                                            <th>性别</th>
                                            <th>身份证号 </th>
                                            <th>轨迹类型</th>
                                            <th>出行时间</th>
                                            <th>出发地</th>
                                            <th>目的地</th>
                                            <th>户籍地址 </th>
                                            <th class="">户籍地派出所</th>
                                            <th>签收人</th>
                                            <th>签收地址</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach var="list" items="${page.list}">
                                        <tr>
                                          <td style="text-align: center">
                                                <i class="iconfont m_star demoT6"><c:if test="${list.controlPersonRelations!=null }">&#xe611;</c:if></i>
												<div class="hideThis star_wrap">
													<c:forEach var="controlPersonRelation" items="${list.controlPersonRelations}" varStatus = "status">
													<c:if test="${status.count%2==1}">
														<div class="star_item_wrap">
													</c:if>
															<div class="star_item">
																<p><i class="m_tit">姓名：</i><i>${controlPersonRelation.controlPerson.name}</i></p>
																<p><i class="m_tit">关系：</i><i><ruiec:dictionary var="dictionary" dictionaryId="${controlPersonRelation.typeId}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary></i></p>
																<p><i class="m_tit">身份证：</i><i>${controlPersonRelation.controlPerson.idCard}</i></p>
															</div>
													<c:if test="${status.count%2==0}">
														</div>
													</c:if>
													<c:set var="v_count" value="${status.count}" /> 	  
													</c:forEach> 
													<c:if test="${v_count%2==1}">
															<div class="star_item"></div>
														</div>
													</c:if>													
												</div>
                                            </td>
                                            <td class="pdl15"><fmt:formatDate value="${list.createDate }" pattern="yyyy-MM-dd" /></td>
                                            <td>${list.name }</td>
                                            <td>${list.sex }</td>
                                            <td>${list.idCard }</td>
                                           <%--  <td>
                                               <em class="tab_detail"><ruiec:dictionary var="dictionary" dictionaryId="${list.dynamicInfoId}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary></em>
                                                <div class="hideThis ellipsis_dateil">
                                                   <ruiec:dictionary var="dictionary" dictionaryId="${list.dynamicInfoId}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary>
                                                 </div>
                                            </td> --%>
                                            <td>
                                                 <em class="tab_detail"> <ruiec:apiConfig var="api" apiId="${list.dynamicInfoId}">${api == null?'无':api.name}</ruiec:apiConfig></em>
                                                 <div class="hideThis ellipsis_dateil">
                                                     <ruiec:apiConfig var="api" apiId="${list.dynamicInfoId}">${api == null?'无':api.name}</ruiec:apiConfig>
                                                 </div>
                                            </td>
                                            <td> 
                                               <em class="tab_detail">${list.departureTime}</em>
                                               <div class="hideThis ellipsis_dateil">${list.departureTime}</div>
                                            </td>
                                            <td>${list.startPlace }</td>
                                            <td>${list.endPlace }</td>
                                            <td>
                                              <em class="tab_detail">${list.nativePlace}</em>
                                              <div class="hideThis ellipsis_dateil">${list.nativePlace}</div>
                                            </td>
                                            <td class="">
                                               <em class="tab_detail">${list.nativePlacePoliceStationCo}</em>
                                               <div class="hideThis ellipsis_dateil">${list.nativePlacePoliceStationCo}</div>
                                            </td>
                                            <td><ruiec:user var="showUser" userId="${list.signPersonId}">${showUser == null?'无':showUser.policeName}</ruiec:user></td>
                                            <td><ruiec:unit var="unit" unitId="${list.signUnitId}">${unit == null?'无':unit.unitName}</ruiec:unit></td>
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
    <!--验证-->
    <script src="${base }/resources/admin/js/jquery.form.js"></script>
    <script src="${base }/resources/admin/js/Validform_v5.3.2/Validform_v5.3.2.js"></script> 
    <!--自写-->
    <script src="${base }/resources/admin/js/layout.js"></script>
    <!--拖拽-->
    <script src="${base }/resources/admin/js/gridster/jquery.gridster.min.js"></script>
    <!---下拉框 -->
    <script src="${base }/resources/admin/js/common.js"></script>
    <!--日期-->
    <script src="${base }/resources/admin/js/My97DatePicker/lang/zh-cn.js"></script>
    <script src="${base }/resources/admin/js/My97DatePicker/WdatePicker.js"></script>
    
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
        $(".demoT6").each(function(){
            var tooltips = $(this).next().html();//$(this).attr("data-tit");
            console.log(tooltips);
            $(this).pt({
                position: 'r', // 默认属性值
                align: 'c',	   // 默认属性值
                content: tooltips,
            });
        });    
	</script>
</body>
</html>