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
    <link rel="stylesheet" href="${base }/resources/admin/css/layer.css" >
    <link rel="stylesheet" href="${base }/resources/admin/css/common.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/style.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/index.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/quote.css">
</head>
<body class="bodyCss">
<jsp:include page="../common/header.jsp"></jsp:include> 
<!---->
<section>
	<jsp:include page="../common/leftNav6.jsp"></jsp:include> 
    <div class="mainRight">
        <div class="pancl_pd">
            <div class="tit_menu_list">
                <a href="/admin/common/main.shtml" class="m_menu_item">
                    <i class="iconfont"></i>
                    <em>首页</em>
                </a>
                <i class="fl split_gt">&gt;</i>
                <a href="javascript:;" class="m_menu_item">
                    <em>人员管理</em>
                </a>
	            <i class="fl split_gt">&gt;</i>
	            <a href="/admin/monitor/list.shtml?personnelType=${personnelType}" class="m_menu_item">
                 	<em>布控人员列表</em>
               	</a>
                <i class="fl split_gt">&gt;</i>
                <a href="javascript:;" class="m_menu_item">
                    <em>布控人员详情</em>
                </a>
                <span class="fr">
	             	<input type="button" onclick="javascript:history.back(-1);" class="fr back_btn" value="返回上一页"/>
	             </span>
            </div>
            <style>

            </style>
            <div class="mgt20 personal_details">
                <div class="personal_details_peo">
                    <span class="fl">
                    	<c:if test="${empty monitor.photo}">
	                        <img src="${base }/resources/admin/images/Avatar.png"/>
                    	</c:if>
                    	<c:if test="${!empty monitor.photo}">
                        	<img class="progres_img" src="${monitor.photo}"/>
                    	</c:if>
                    </span>
                    <div class="fl personal_details_list personal_details_list1">
                        <dl>
                            <dt>姓名：</dt>
                            <dd>${monitor.name }</dd>
                        </dl>
                        <dl>
                            <dt>身份证：</dt>
                            <dd>${monitor.idCard}</dd>
                        </dl>
                        <dl>
                            <dt>布控单位：</dt>
                            <dd><ruiec:unit var="unit" unitId="${monitor.unitId}">${unit == null?'无':unit.unitName}</ruiec:unit></dd>
                        </dl>
                        <dl>
                            <dt>人员类型：</dt>
                            <dd><ruiec:dictionary var="dictionary" dictionaryId="${monitor.personTypeId}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary></dd>
                        </dl>
                        <dl>
                            <dt>处置措施：</dt>
                            <dd><ruiec:dictionary var="dictionary" dictionaryId="${monitor.handleMode}"><dd>${dictionary == null?'无':dictionary.itemName}</dd></ruiec:dictionary></dd>
                        </dl>
                        <dl>
                            <dt>布控理由 ：</dt>
                            <dd>
                            	<em class="tab_detail">${monitor.reason}</em>
                            	<div class="hideThis ellipsis_dateil">${monitor.reason}</div>
                            </dd>
                        </dl>
                        <dl>
                            <dt>审批人：</dt>
                            <dd>
                            	<c:if test="${size==1}">
	                            	<c:forEach items="${aUserId}" var="l" varStatus="vs">
	                            		<c:if test="${monitor.status==3 or monitor.status==2}">
		                            		<ruiec:user var="user" userId="${l}">${user.policeName}</ruiec:user>
		                            		(派出所初审)
	                            		</c:if>
	                            		<c:if test="${monitor.status ne '0'}">
	                            			<c:forEach items="${units}" var="u" varStatus="vsu">
	                            				<c:if test="${u.unitRank == 'area' && monitor.status!=3}">
	                            					<ruiec:user var="user" userId="${l}">${user.policeName}</ruiec:user>
	                            					(分县局审核)
	                            				</c:if>
	                            				<c:if test="${u.unitRank == 'city'}">
	                            					<ruiec:user var="user" userId="${l}">${user.policeName}</ruiec:user>
	                            					(市局审核)
		                            			</c:if>
	                            			</c:forEach>
	                            		</c:if>
	                           		</c:forEach>
                            	</c:if>
                            	<c:if test="${size==2}">
	                            	<c:forEach items="${aUserId}" var="l" varStatus="vs">
	                            		<c:if test="${vs.count==1 && (monitor.status==1 || monitor.status==2)}">
	                            			<ruiec:user var="user" userId="${l}">${user.policeName}</ruiec:user>
		                            		(派出所初审)
	                            		</c:if>
	                            		<c:if test="${vs.count==2 && (monitor.status==1 || monitor.status==2)}">
	                            			<ruiec:user var="user" userId="${l}">${user.policeName}</ruiec:user>
		                            		--- (分县局复审)
	                            		</c:if>
	                           		</c:forEach>
                            	</c:if>
                            </dd>
                        </dl>
                    </div>
                    <div class="fl personal_details_list personal_details_list2">
                    	<dl>
                            <dt>性别 ：</dt>
                            <dd>${monitor.sex}</dd>
                        </dl>
                        <dl>
                            <dt>联系方式：</dt>
                            <dd>${monitor.contactInfo eq null?'暂无':monitor.contactInfo}</dd>
                        </dl>
                        <dl>
                            <dt>联系民警：</dt>
                            <dd><ruiec:user var="user" userId="${monitor.userId}">${user == null?'无':user.policeName}</ruiec:user></dd>
                        </dl>
                        <dl>
                            <dt>是否审核：</dt>
                            <dd>
	                        <c:if test="${isAudit ne 'noPower'}">
		                        <div id="columnTubeMode" class="fl left_item chose_stare">
		                            <input type="hidden" name="columnTubeMode" value="${monitor.status}" />
		                            <a class="label_btn" onclick="status(${monitor.id},${isAudit eq 'town'?3:isAudit eq 'area'?1:isAudit eq 'city'?1:isAudit eq 'areaTown'?1:isAudit})">通过</a>
		                            <a class="label_btn" onclick="status(${monitor.id},2)">不通过</a>
	                            </div>
                            </c:if>
                            <c:if test="${monitor.status eq '1'}">
                            <em class="m_blue">已审核</em>
                            </c:if>
                            <c:if test="${monitor.status eq '2'}">
                            <em style="color: red;">审核拒绝</em>
                            </c:if>
                            <c:if test="${monitor.status eq '3' and isAudit eq 'noPower'}">
                            <em class="m_blue">待复审</em>
                            </c:if>
                            <c:if test="${monitor.status eq '0' and isAudit eq 'noPower'}">
                            <em style="color: red;">未审核</em>
                            </c:if>
	                        </dd>
                        </dl>
                        <dl>
                            <dt>审批单位：</dt>
                            <dd>
                            	<c:if test="${size==1}">
                            		<c:if test="${monitor.status ne '0'}">
                            			<c:forEach items="${units}" var="u" varStatus="vsu">
                            				<c:if test="${monitor.status==3 or monitor.status==2}">
                            					${u.unitName}
			                            		(派出所初审)
		                            		</c:if>
                            				<c:if test="${u.unitRank == 'area' && monitor.status!=3}">
                            					${u.unitName}
                            					(分县局审核)
                            				</c:if>
                            				<c:if test="${u.unitRank == 'city'}">
                            					${u.unitName}
                            					(市局审核)
	                            			</c:if>
                            			</c:forEach>
                            		</c:if>
                            	</c:if>
                            	<c:if test="${size==2}">
	                            	<c:forEach items="${units}" var="u" varStatus="vs">
	                            		<c:if test="${vs.count==1 && (monitor.status==1 || monitor.status==2)}">
		                            		${u.unitName}
		                            		(派出所初审)
	                            		</c:if>
	                            		<c:if test="${vs.count==2 && (monitor.status==1 || monitor.status==2)}">
		                            		${u.unitName}
		                            		--- (分县局复审)
	                            		</c:if>
	                           		</c:forEach>
                            	</c:if>
                            </dd>
                        </dl>
                        <dl>
                            <dt>审批时间：</dt>
                            <dd>
                            	<c:if test="${size==1}">
	                            	<c:forEach items="${aTime}" var="l" varStatus="vs">
	                            		<c:if test="${monitor.status ne '0'}">
	                            			<c:forEach items="${units}" var="u" varStatus="vsu">
		                            			<c:if test="${monitor.status==3 or monitor.status==2}">
		                            				${l}
				                            		(派出所初审)
			                            		</c:if>
	                            				<c:if test="${u.unitRank == 'area' && monitor.status!=3}">
	                            					${l}
	                            					(分县局审核)
	                            				</c:if>
	                            				<c:if test="${u.unitRank == 'city'}">
	                            					${l}
	                            					(市局审核)
		                            			</c:if>
	                            			</c:forEach>
	                            		</c:if>
	                           		</c:forEach>
                            	</c:if>
                            	<c:if test="${size==2}">
	                            	<c:forEach items="${aTime}" var="l" varStatus="vs">
	                            		<c:if test="${vs.count==1 && (monitor.status==1 || monitor.status==2)}">
		                            		${l}
		                            		(派出所初审)
	                            		</c:if>
	                            		<c:if test="${vs.count==2 && (monitor.status==1 || monitor.status==2)}">
		                            		${l}
		                            		--- (分县局复审)
	                            		</c:if>
	                           		</c:forEach>
                            	</c:if>
                            </dd>
                        </dl>
                    </div>
                    <div class="fl personal_details_list personal_details_list3">
                    	<dl>
                        	<dt>民族：</dt>
                        	<dd>
                        		<ruiec:dictionary var="dictionary" dictionaryId="${monitor.nationNumber}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary>
                            </dd>
                        </dl>
                        <dl>
                            <dt>户籍详址 ：</dt>
                            <dd>${monitor.nativeAddress eq null?'暂无':monitor.nativeAddress}</dd>
                        </dl>
                        <dl>
                            <dt>民警电话 ：</dt>
                            <dd>${monitor.contactsPolice eq null?'暂无':monitor.contactsPolice}</dd>
                        </dl>
                        <dl>
                            <dt>是否撤控 ：</dt>
                            <dd>${monitor.isRevoke eq '0'?'否':monitor.isRevoke eq '1'?'是':''}</dd>
                        </dl>
                        <dl>
                            <dt>人员类别 ：</dt>
                            <dd>
                            	<c:forEach items="${personnelTypes}" var="list">
                            		<em class="tab_detail"><ruiec:dictionary var="dictionary" dictionaryId="${list.dictionaryId}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary></em>
                            		<div class="hideThis ellipsis_dateil"><ruiec:dictionary var="dictionary" dictionaryId="${list.dictionaryId}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary></div>
                            	</c:forEach>
                            </dd>
                        </dl>
                    </div>
                    <div class="fl personal_details_list personal_details_list4">
                        <dl>
                            <dt>车牌号 ：</dt>
                            <dd>${monitor.licensePlateNumber eq null?'暂无':monitor.licensePlateNumber}</dd>
                        </dl>
                        <dl>
                            <dt>现住地址 ：</dt>
                            <dd>
                            	<em class="tab_detail">${monitor.currentAddress eq null?'暂无':monitor.currentAddress}</em>
                            	<div class="hideThis ellipsis_dateil">${monitor.currentAddress eq null?'暂无':monitor.currentAddress}</div>
                            </dd>
                        </dl>
                        <dl>
                            <dt>布控时间 ：</dt>
                            <dd>${monitor.duration==null?0:monitor.duration}天</dd>
                        </dl>
                        <dl>
                            <dt>入库时间 ：</dt>
                            <dd><fmt:formatDate value="${monitor.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
                        </dl>
                    </div>
                </div>
                <div class="mgt20 personal_Contact_catalogue">
                    <div class="ptd10 catalogue_tit">
                        <span class="Contact_info_tit">档案目录</span>
                    </div>
                    <div class="mgt10 personal_catalogue_box">
                    </div>
                </div>
            </div>
            <div id="div_list">
            	列表数据
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
<link href="${base }/resources/admin/js/tooltips/tooltips.css" rel="stylesheet" id="skin">
<script src="${base }/resources/admin/js/tooltips/bootstrap-tooltip.js"></script>
<script src="${base }/resources/admin/js/tooltips/bootstrap-affix.js"></script>
<script src="${base }/resources/admin/js/tooltips/application.js"></script>
<script src="${base }/resources/admin/js/tooltips/jquery.pure.tooltips.js"></script> 
<script type="text/javascript">
	$(function(){
		typeList();
		loadPage(0,1);
		if( $(".tab_detail").size()>0 ){ 
			$(".tab_detail").each(function(){
	            var tooltips = $(this).next().text();
	            $(this).pt({
	                position: 'l', // 默认属性值
	                align: 'c',	   // 默认属性值
	                content: tooltips,
	            });
	        }); 
		};
	});
	function status(id,status){
		$.ajax({
			type:"POST",
			url:"/admin/monitor/isColumn.shtml",
			data: {id:id,status:status},
			datatype:"json",
			success:function(data){ 
				console.log(data);
				if( data.code == '200' ){ 
					layer.msg(data.msg);
					setTimeout(function(){location.reload();},1000);
				}else{
					layer.msg(data.msg);
				}
			}
   		})
	}
	
	var queryParams = { id: ${monitorId}};
	function loadPage(typeId,pageNumber) {
		layer.load();
		queryParams.typeId=typeId;
		queryParams.pageNumber=pageNumber;
        $.ajax({
            url: "/admin/monitor/getDynamicInfo.shtml",
            type: "POST",
            data: queryParams,
            dataType: "html",
            cache: false,
            success: function (html, textStatus, jqXHR) {
            	layer.closeAll('loading');
                $("#div_list").html(html);
            },
            error: function () {
            	layer.closeAll('loading');
                $("#div_list").append("暂无数据");
            }
        });
    }
    
	function typeList() {
		layer.load();
        $.ajax({
            url: "/admin/monitor/countTypeList.shtml",
            type: "POST",
            data: {id: ${monitorId}},
            dataType: "html",
            cache: false,
            success: function (html, textStatus, jqXHR) {
            	layer.closeAll('loading');
                $(".mgt10.personal_catalogue_box").html(html);
            },
            error: function () {
            	layer.closeAll('loading');
                $(".mgt10.personal_catalogue_box").append("暂无数据");
            }
        });
    }
</script>
</body>
</html>