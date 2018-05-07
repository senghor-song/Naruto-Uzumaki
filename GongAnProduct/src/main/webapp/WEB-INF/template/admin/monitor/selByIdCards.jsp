<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html>
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
    <style type="text/css">
    </style>
</head>
<body>
        <!---->
        <section>
            <div class="lay_form pd15_25">
                 <div class="lay_form_wrap"> 
                	<style>
                		.lay_form_chose{ font-size:14px; line-height:25px; overflow:hidden; }
                		.lay_form_chose a{ float:left; display:inline-block; padding:0 20px; color:#333333; border-bottom:1px solid #ddd;}
                		.lay_form_chose a.curr{ color:#0073d0; border-bottom:1px solid #0073d0;}
                		.lay_cont_chose{ overflow:hidden;} 
                	</style>
	                <div class="lay_form_chose">
                		<a class="lay_chose_btn" href="/admin/monitor/selByIdCards.shtml?idCard=${idCard}&personType=0">布控人员</a>
                		<a class="lay_chose_btn <c:if test="${personType == 1}">curr</c:if>" href="/admin/monitor/selByIdCards.shtml?idCard=${idCard}&personType=1">重点人员</a>
                		<a class="lay_chose_btn <c:if test="${personType == 2}">curr</c:if>" href="/admin/monitor/selByIdCards.shtml?idCard=${idCard}&personType=2">关注人员</a>
                		<a class="lay_chose_btn <c:if test="${personType == 3}">curr</c:if>" href="/admin/monitor/selByIdCards.shtml?idCard=${idCard}&personType=3">外地人员</a>
	                </div>
	                <div class="mgt25 layform_cont_chose">
	                 	<div class="layform_cont">
	                 	<c:if test="${personType == 0 or empty personType}">
	                 	<form action="/admin/monitor/selByIdCards.shtml" method="get" id="searchform">
		                 	 <input type="hidden" value="${idCard}" name="idCard"/>
		                 	 <input type="hidden" value="${personType}" name="personType"/>
		                     <div class="importantC">
		                         <div class="importantNList">
	                             <table class="tab tab_ellipsis">
	                                 <colgroup>
	                                     <col width="3%"/>
	                                     <col width="4%"/>
	                                     <col width="6%"/>
	                                     <col width="4%"/>
	                                     <col width="12%"/>
	                                     <col width="8%"/>
	                                     <col width="8%"/>
	                                     <col width="7%"/>
	                                     <col width="10%"/>
	                                 </colgroup>
	                                 <thead>
	                                     <tr>
	                                         <th>&nbsp;</th>
	                                         <th>照片</th>
	                                         <th>姓名</th>
	                                         <th>性别</th>
	                                         <th>身份证号</th>
	                                         <th>布控单位</th>
	                                         <th>入库时间</th>
	                                         <th>联系民警</th>
	                                         <th class="textl">操作</th>
	                                     </tr>
	                                 </thead>
	                                 <tbody>
	                                  <c:forEach var="list" items="${page.list}">
	                                      <tr>
	                                          <td style="text-align: center"><%-- <input type="checkbox" class="checkItem" name="ids" value="${list.id}"> --%></td>
	                                          <td>
	                                   	<c:if test="${!empty list.photo}">
	                                          	<img src="${list.photo}" width="30" height="34" />
	                                   	</c:if>
	                                   	<c:if test="${empty list.photo}">
	                                          	<img src="${base }/resources/admin/images/Avatar.png" width="30" height="34" />
	                                   	</c:if></td>
	                                          <td>${list.name}</td>
	                                          <td>${list.sex}</td>
	                                          <td>
	                                           <em class="tab_detail" >${list.idCard}</em>
	                                           <div class="hideThis ellipsis_dateil">${list.idCard}</div>
	                                          </td>
	                                          <td>
	                       	                    <c:if test="${empty list.unitId}">无</c:if>
	                                           <em class="tab_detail" ><ruiec:unit var="unit" unitId="${list.unitId}">${unit == null?'无':unit.unitName}</ruiec:unit></em>
	                                           <div class="hideThis ellipsis_dateil"><ruiec:unit var="unit" unitId="${list.unitId}">${unit == null?'无':unit.unitName}</ruiec:unit></div>
	                                          </td>
	                                          <td>
	                                           <em class="tab_detail" ><fmt:formatDate value="${list.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></em>
	                                           <div class="hideThis ellipsis_dateil"><fmt:formatDate value="${list.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
	                                          </td>
	                                          <td>
	                                          	<ruiec:user var="user" userId="${list.userId}">${user == null?'无':user.policeName}</ruiec:user>
	                                          </td>
	                                          <td>
	                                              <a href="javascript:void(0)" val="${list.id}" class="m_blue choose">选择</a>
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
	                 	</c:if>
	                 	<c:if test="${personType != null and personType != 0}">
	                 	<form action="/admin/monitor/selByIdCards.shtml" method="get" id="searchform">
		                 	 <input type="hidden" value="${idCard}" name="idCard"/>
		                 	 <input type="hidden" value="${personType}" name="personType"/>
		                     <div class="mgt15 importantNList">
                                <table class="tab tab_ellipsis">
                                    <colgroup>
                                        <col width="3%"/>
                                        <col width="4%"/>
                                        <col width="6%"/>
                                        <col width="4%"/>
                                        <col width="12%"/>
                                        <col width="8%"/>
                                        <col width="8%"/>
                                        <col width="7%"/>
                                        <col width="10%"/>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>&nbsp;</th>
                                            <th>照片</th>
                                            <th>姓名</th>
                                            <th>性别</th>
                                            <th>身份证号</th>
                                            <th>管控责任单位</th>
                                            <th>入库时间</th>
                                            <th>责任民警</th>
                                            <th class="textl">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
	                                    <c:forEach var="list" items="${page.list}">
	                                        <tr>
	                                            <td style="text-align: center"></td>
	                                            <td>
		                                    	<c:if test="${!empty list.controlPersonExtend.photo}">
	                                            	<img src="${list.controlPersonExtend.photo}" width="30" height="34" />
		                                    	</c:if>
		                                    	<c:if test="${empty list.controlPersonExtend.photo}">
	                                            	<img src="${base }/resources/admin/images/Avatar.png" width="30" height="34" />
		                                    	</c:if></td>
	                                            <td>${list.name}</td>
	                                            <td>${list.sex}</td>
	                                            <td>
		                                            <em class="tab_detail" >${list.idCard}</em>
		                                            <div class="hideThis ellipsis_dateil">${list.idCard}</div>
	                                            </td>
	                                            <td>
	                         	                    <c:if test="${empty list.unitId}">无</c:if>
		                                            <em class="tab_detail" ><ruiec:unit var="unit" unitId="${list.unitId}">${unit.unitName}</ruiec:unit></em>
		                                            <div class="hideThis ellipsis_dateil"><ruiec:unit var="unit" unitId="${list.unitId}">${unit.unitName}</ruiec:unit></div>
	                                            </td>
	                                            <td>
		                                            <em class="tab_detail" ><fmt:formatDate value="${list.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></em>
		                                            <div class="hideThis ellipsis_dateil"><fmt:formatDate value="${list.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
	                                            </td>
	                                            <td>
	                                            	<c:if test="${empty list.userId}">无</c:if>
	                                            	<ruiec:user var="user" userId="${list.userId}">${user.policeName}</ruiec:user>
	                                            </td>
	                                            <td>
	                                            	<a href="javascript:void(0)" val="${list.id}" ptype="0" class="m_blue choose">选择</a>
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
		                </form>
	                 	</c:if>
		                </div>
	                </div> 
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
	<script src="${base }/resources/admin/js/webuploader/webuploader.min.js"></script>
	
	<link rel="stylesheet" href="${base }/resources/admin/js/select/select2.css">
	<script src="${base }/resources/admin/js/select/select2.js"></script>

    <link href="${base }/resources/admin/js/tooltips/tooltips.css" rel="stylesheet" id="skin">
    <script src="${base }/resources/admin/js/tooltips/bootstrap-tooltip.js"></script>
    <script src="${base }/resources/admin/js/tooltips/bootstrap-affix.js"></script>
    <script src="${base }/resources/admin/js/tooltips/application.js"></script>
    <script src="${base }/resources/admin/js/tooltips/jquery.pure.tooltips.js"></script> 
    <script type="text/javascript">
    $(".lay_form_chose .lay_chose_btn").click(function(){
    	/* var _index = $(this).index();
    	var _person = $(this).parents(".lay_form_wrap").find(".layform_cont_chose");
    	$(this).addClass("curr").siblings().removeClass("curr");  */
    	/* if(_index > 0){
    		var _theObj = $(this).parents(".lay_form_wrap").find(".layform_cont_chose");
        	_theObj.find(".layform_cont").eq(0).addClass("hideThis");
        	_theObj.find(".layform_cont").eq(1).removeClass("hideThis");
    	}else{
    		var _theObj = $(this).parents(".lay_form_wrap").find(".layform_cont_chose");
        	_theObj.find(".layform_cont").addClass("hideThis");
        	_theObj.find(".layform_cont").eq(_index).removeClass("hideThis");
    	} */
    });
    	$(function(){
    		var personType = "${personType}";
    		if(personType == "" || personType == 0){
    			$('.lay_chose_btn:eq(0)').addClass("curr")
    		}
    		$('.choose').on('click',function(){
    			var id = $(this).attr('val');
    			var ptype = $(this).attr('ptype');
    			$.ajax({
    				type:"POST",
    				url:"/admin/monitor/selOneByIdCard.shtml",
    				data: {id:id,ptype:ptype},
    				datatype:"json",
    				success:function(data){ 
    					if( data.code == '200'){
    						parent.$("#name").val(data.data.name);
    						parent.$("#face_pic").attr("src",data.data.photo);
    						parent.$("#shenfengzheng_card").val(data.data.idCard);
    						parent.$("#nation").val(data.data.nationNumber);
    						if(data.data.sex == '男'){
    							parent.$("input[name='sex']:eq(0)").trigger('click');
    						}else{
    							parent.$("input[name='sex']:eq(1)").trigger('click');
    						}
    						if (ptype == 0){
    							parent.$("#personnelType").val(data.data.personTypeIdz);
    							parent.$("#pt").val(data.data.personTypeIdz);
    						}else{
    							parent.$("#personnelType").val(data.data.personTypeId);
    							parent.$("#pt").val(data.data.personTypeId);
    						}
    						parent.$("#licensePlateNumber").val(data.data.licensePlateNumber);
    						parent.$("#contactInfo").val(data.data.contactInfo);
    						parent.$("#currentAddress").val(data.data.currentAddress);
    						parent.$("#nativeAddress").val(data.data.nativeAddress);
    						parent.$("#reason").val(data.data.reason);
    						parent.$("#reason").trigger('keyup');
    						parent.$("#contactsPolice").val(data.data.contactsPolice);
    						parent.$("#Categories").val(data.data.personTypes);
    						parent.$("#Categoriess").val(data.data.personType);
    						parent.$("#Categoriess").trigger('click');
    						parent.$("#handleMode").val(data.data.handleMode);
    						parent.$("#duration").val(data.data.duration);
    						parent.$("#parentUserId").val(data.data.userId);
    						parent.$("#parentUnitId").val(data.data.unit2);
    						if(data.data.unit2 != undefined){
    							parent.$("#selunitId").val(data.data.unit).trigger('change');
    							parent.$("#selunitId2").val(data.data.unit2).trigger('change');
    						}else{
    							parent.$("#selunitId").val(data.data.unit).trigger('change');
    						}
    						parent.$("#fileUrl").val(data.data.photo);
    		        		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    		        		setTimeout(parent.layer.close(index),1000);
    					}else{
    						layer.msg(data.msg);
    					}
    				}
    	   		})
        	})
    	})
   	</script>
</body>
</html>