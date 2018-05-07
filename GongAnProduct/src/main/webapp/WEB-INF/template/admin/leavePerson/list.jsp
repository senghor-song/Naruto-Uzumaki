<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html>
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
        	<jsp:include page="../common/leftNav2.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                        <div class="tit_menu_list">
                            <a href="/admin/common/main.shtml" class="m_menu_item">
                                <i class="iconfont">&#xe62a;</i>
                                <em>首页</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item refresh_html">
                                <em>关注人员筛查</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item refresh_html">
                                <em>离市人员</em>
                            </a>
                        </div>
                        <form id="search_form" action="/admin/leavePerson/list.shtml" method="get">
                        <div class="mgt15 importantC">
                            <div class="mgt15" id="rylbCount">
                                <div class="fl rule-single-select single-select">
                                    <select name="isSign" style="display: none;">
                                    	<option value="">签收状态</option>
                                        <option <c:if test="${isSign==0 }">selected="selected"</c:if> value="0">未签收</option>
                                        <option <c:if test="${isSign==1 }">selected="selected"</c:if> value="1">已签收</option>
                                    </select>
                                </div>
                                <div class="fl mgl10 rule-single-select single-select">
                                    <select name="unitId" style="display: none;">
                                        <option value="">所在区域</option>
                                        <c:forEach var="unit" items="${unitList}">
                                        <option <c:if test="${unit.id==unitId }">selected="selected"</c:if> value="${unit.id }">${unit.name }</option>
										</c:forEach>
                                    </select>
                                </div>

                                <div class="fl mgl10 rule-single-select single-select">
                                    <select name="dynamicInfoId" style="display: none;">
                                        <option value="">轨迹类型</option>
                                        <c:forEach var="dynamicInfo" items="${dynamicInfoList}">
                                        <option <c:if test="${dynamicInfo.id==dynamicInfoId }">selected="selected"</c:if> value="${dynamicInfo.id }">${dynamicInfo.name }</option>
										</c:forEach>
                                    </select>
                                </div>
                                <div class="fl mgl45 ">
                                    <dl class="search_item">
                                        <dt>出行时间：</dt>
                                        <dd> 
                                            <input class="Wdate text_inp " value="${startDate }" id="quote_time" autocomplete="off" type="text" name="startDate"> 
                                            <em class="lh30 offDivide">至</em> 
                                            <input class="Wdate text_inp" value="${endDate }"  id="quote_time1" autocomplete="off" type="text" name="endDate">
                                            <a href="javascript:document.getElementById('search_form').submit();"  class="btn curr fr mgl10 search">搜索</a>
		                                    <button type="reset" class="btn fr mgl10 resetBtn" onClick="resetBtn()">重置</button>
                                        </dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
						</form>
						<form action="/admin/leavePerson/list.shtml" method="get">
						<input type="hidden" name="isSign" value="${isSign }"/>
                        <input type="hidden" name="unitId" value="${unitId }"/>
                        <input type="hidden" name="dynamicInfoId" value="${dynamicInfoId }"/>
                        <input type="hidden" name="startDate" value="${startDate }"/>
                        <input type="hidden" name="endDate" value="${endDate }"/>
                        <div class="mgt10 importantC">
                            <div class="importantNList">
                                <table class="tab tab_ellipsis">
                                    <colgroup>
                                        <col width="3%"/>
                                        <col width="8%"/>
                                        <col width="5%"/>
                                        <col width="4%"/>
                                        <col width="9%"/>
                                        <col width="7%"/>
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
                                            <th>入库时间</th>
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
                                    	<tr class="">
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
                                            <td><fmt:formatDate value="${list.createDate }" pattern="yyyy-MM-dd" /></td>
                                            <td>${list.name }</td>
                                            <td>${list.sex }</td>
                                            <%-- <td>
                                            	<em class="tab_detail" >${list.idCard}</em>
		                                        <div class="hideThis ellipsis_dateil">${list.idCard}</div>
                                            </td> --%>
                                            <td>${list.idCard }</td>
                                            <td><ruiec:apiConfig var="api" apiId="${list.dynamicInfoId}">${api == null?'无':api.name}</ruiec:apiConfig></td>
                                            <td>${list.departureTime }</td>
                                            <td>${list.startPlace }</td>
                                            <td>${list.endPlace }</td>
                                            <td>${list.nativePlace }</td>
                                            <td class="">${list.nativePlacePoliceStationCo }</td>
                                            <td><ruiec:user var="signPerson" userId="${list.signPersonId}">${signPerson == null?'无':signPerson.policeName}</ruiec:user></td>
                                            <td><ruiec:unit var="unit" unitId="${list.signUnitId}">${unit == null?'无':unit.unitName}</ruiec:unit></td>
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
	<!--验证-->
	<script src="${base }/resources/admin/js/jquery.form.js"></script>
	<script src="${base }/resources/admin/js/Validform_v5.3.2/Validform_v5.3.2.js"></script> 
	<!--日期-->
	<script src="${base }/resources/admin/js/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="${base }/resources/admin/js/datetimepicker/timer.js"></script>
	<!--自写-->
	<script src="${base }/resources/admin/js/layout.js"></script>
	<!---下拉框 -->
	<script src="${base }/resources/admin/js/common.js"></script> 
	<!--tooltip-->
    <script src="${base }/resources/admin/js/tooltips/bootstrap-tooltip.js"></script>
    <script src="${base }/resources/admin/js/tooltips/bootstrap-affix.js"></script>
    <script src="${base }/resources/admin/js/tooltips/application.js"></script>
    <script src="${base }/resources/admin/js/tooltips/jquery.pure.tooltips.js"></script>
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
        // 提交搜索表单
		$('.rule-single-select').click(function(){
			$("#search_form").submit();
		});
		// 重置
		/* $(function(){
			$(".resetBtn").click(function(){
				$('select').each(function(){
					$(this).find("option").attr("selected",false);
					$(this).find("option").eq(0).attr("selected","selected");
				});	 
				$(".rule-single-select").ruleSingleSelect(); 
				$("input").not("#goto,:hidden").attr("value","");
			});
	   	}); */
	</script>
</body>
</html>