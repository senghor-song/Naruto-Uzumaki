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
	<c:if test="${viewType eq 0}">
		<jsp:include page="../common/leftNav.jsp"></jsp:include> 
	</c:if>
	<c:if test="${viewType eq 1}">
		<jsp:include page="../common/leftNav3.jsp"></jsp:include> 
	</c:if>
    <div class="mainRight">
        <div class="pancl_pd">
            <div class="tit_menu_list">
                <a href="/admin/common/main.shtml" class="m_menu_item">
                    <i class="iconfont"></i>
                    <em>首页</em>
                </a>
				<c:if test="${viewType eq 0}">
	                <i class="fl split_gt">&gt;</i>
	                <a href="javascript:;" class="m_menu_item refresh_html">
	                    <em>人员管理</em>
	                </a>
	                <c:if test="${personnelType eq '1'}">
		                <i class="fl split_gt">&gt;</i>
		                <a href="/admin/controlPerson/list.shtml?personnelType=${personnelType}" class="m_menu_item">
		                	<em>重点人员列表</em>
		                </a>
	                </c:if>
	                <c:if test="${personnelType eq '2'}">
		                <i class="fl split_gt">&gt;</i>
		                <a href="/admin/controlPerson/list.shtml?personnelType=${personnelType}" class="m_menu_item">
	                   		<em>关注人员列表</em>
	                 	</a>
	                </c:if>
	                <c:if test="${personnelType eq '3'}">
		                <i class="fl split_gt">&gt;</i>
		                <a href="/admin/controlPerson/list.shtml?personnelType=${personnelType}" class="m_menu_item">
	                    	<em>外地人员列表</em>
	                  	</a>
	                </c:if>
	        	</c:if>
				<c:if test="${viewType eq 1}">
					<i class="fl split_gt">&gt;</i>
                    <a href="javascript:;" class="m_menu_item refresh_html">
                        <em>系统查询</em>
                    </a>
                    <i class="fl split_gt">&gt;</i>
                    <a href="javascript:;" class="m_menu_item refresh_html">
                        <em>人员查询</em>
                    </a>
				</c:if>
                <i class="fl split_gt">&gt;</i>
                <a href="javascript:;" class="m_menu_item refresh_html">
                    <em>人员详情</em>
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
                    	<c:if test="${empty controlPerson.controlPersonExtend.photo}">
	                        <img src="${base }/resources/admin/images/Avatar.png"/>
                    	</c:if>
                    	<c:if test="${!empty controlPerson.controlPersonExtend.photo}">
                        	<img class="progres_img" src="${controlPerson.controlPersonExtend.photo}"/>
                    	</c:if>
                    </span>
                    <div class="fl personal_details_list personal_details_list1">
                        <dl>
                            <dt>姓名：</dt>
                            <dd>${controlPerson.name }</dd>
                        </dl>
                        <dl>
                            <dt>手机号码：</dt>
                            <dd>${controlPerson.phone}</dd>
                        </dl>
                        <dl>
                            <dt>列管单位：</dt>
                            <dd>
                               	<ruiec:unit var="unit" unitId="${controlPerson.unitId}">${unit == null?'无':unit.unitName}</ruiec:unit>
                            </dd>
                        </dl>
                        <dl>
                            <dt>是否列管：</dt>
                            <dd> ${controlPerson.columnTubeState == '1'?'列管':'撤管'}</dd>
                        </dl>
                        <dl>
                            <dt>户籍详址：</dt>
                            <dd>${controlPerson.controlPersonExtend.householdRegisterPlace}</dd>
                        </dl>
                        <dl>
                        	<dt>经常居住地：</dt>
                        	<dd>${controlPerson.controlPersonExtend.habitualResidence }</dd>
                        </dl>
                        <dl>
                        	<dt>民族：</dt>
                        	<dd>
                        		<ruiec:dictionary var="dictionary" dictionaryId="${controlPerson.controlPersonExtend.nation}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary>
                            </dd>
                        </dl>
                        
                    </div>
                    <div class="fl personal_details_list personal_details_list2">
                        <dl>
                            <dt>身份证：</dt>
                            <dd>${controlPerson.idCard }</dd>
                        </dl>
                        <dl>
                            <dt>危险级别：</dt>
                            <dd>
                            	<ruiec:dictionary var="dictionary" dictionaryId="${controlPerson.dangerousLevel}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary>
                            </dd>
                        </dl>
                        <dl>
                            <dt>责任民警：</dt>
                            <dd>
		                        <ruiec:user var="police" userId="${controlPerson.userId}">${police == null?'无':police.policeName}</ruiec:user>
		                    </dd>
                        </dl>
                        <dl>
                            <dt>是否审核：</dt>
                            <dd>
	                        <c:if test="${controlPerson.isAuditKeyPersonColumnTub eq '1'}">
	                        
	                            <c:if test="${viewType eq 0}">
	                            	<c:if test="${empty isColumn}">
				                        <div id="columnTubeMode" class="fl left_item chose_stare">
				                            <input type="hidden" name="columnTubeMode" value="${controlPerson.isAuditKeyPersonColumnTub}" />
				                            <a class="label_btn" onclick="isAuditKeyPersonColumnTub(${controlPerson.id},2)">通过</a>
				                            <a class="label_btn" onclick="isAuditKeyPersonColumnTub(${controlPerson.id},3)">不通过</a>
			                            </div>
			                        </c:if>
		                        </c:if>
                            </c:if>
	                        <c:if test="${controlPerson.isAuditKeyPersonColumnTub eq '1'}">
	                            <%-- <c:if test="${!empty isColumn}">
	                            	<em class="m_blue">待审核</em>
	                            </c:if> --%>
	                            <c:if test="${viewType eq 1}">
	                            	<em class="m_blue">待审核</em>
	                            </c:if>
                            </c:if>
                            <c:if test="${controlPerson.isAuditKeyPersonColumnTub eq '2'}">
                            <em class="m_blue">已审核</em>
                            </c:if>
                            <c:if test="${controlPerson.isAuditKeyPersonColumnTub eq '3'}">
                            <em class="m_blue">未通过审核</em>
                            </c:if>
	                        </dd>
                        </dl>
                        <dl>
                        	<dt>人员类型：</dt>
                        	<dd>
                        		<c:if test="${1 == controlPerson.personnelType}">重点人员</c:if>
                                <c:if test="${2 == controlPerson.personnelType}">关注人员</c:if>
                               	<c:if test="${3 == controlPerson.personnelType}">外地人员</c:if>
                            </dd>
                        </dl>
                        <dl>
                        	<dt>出生日期：</dt>
                        	<dd>${controlPerson.controlPersonExtend.birthDate}</dd>
                        </dl>
                        <dl>
                        	<dt>QQ号码：</dt>
                        	<dd>${controlPerson.controlPersonExtend.qq}</dd>
                        </dl>
                        
                    </div>
                    <div class="fl personal_details_list personal_details_list3">
                        <dl>
                            <dt>性别 ：</dt>
                            <dd>${controlPerson.sex}</dd>
                        </dl>
                        <dl>
                            <dt>人员类别 ：</dt>
                            <dd>
                            	<c:forEach var="dict" items="${controlPerson.controlPersonTypes}" varStatus="l">
                            	<ruiec:dictionary var="dictionary" dictionaryId="${dict.dictionaryId}">${dictionary == null?'无':dictionary.itemName}<c:if test="${!l.last}">,</c:if></ruiec:dictionary>
                            	</c:forEach></em>
	                         </dd>
                        </dl>
                        <dl>
                            <dt>民警电话 ：</dt>
                            <dd>${controlPerson.controlPersonExtend.policeComprehensiveContactI}</dd>
                        </dl>
                        <dl>
                            <dt>管控理由 ：</dt>
                            <dd>${controlPerson.controlPersonExtend.reason}</dd>
                        </dl>
                        <dl>
                            <dt>现住详址 ：</dt>
                            <dd>${controlPerson.controlPersonExtend.nowLiveArea}</dd>
                        </dl>
                        <dl>
                        	<dt>车牌号：</dt>
                        	<dd>${controlPerson.controlPersonExtend.plateNumber}</dd>
                        </dl>
                        <dl>
                        	<dt>微信号码：</dt>
                        	<dd>${controlPerson.controlPersonExtend.wechat}</dd>
                        </dl>
                        <%-- <dl>
                        	<dt>单位(职业)：</dt>
                        	<dd>${controlPerson.controlPersonExtend.occupation}</dd>
                        </dl>  --%>
                    </div>
                    <div class="fl personal_details_list personal_details_list4">
                        <dl>
                            <dt>籍贯 ：</dt>
                            <dd>${controlPerson.controlPersonExtend.nativePlace}</dd>
                        </dl>
                        <%-- <dl>
                            <dt>状态 ：</dt>
                            <dd>
                            	<ruiec:dictionary var="dictionary" dictionaryId="${controlPerson.isControl}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary>
							</dd>
                        </dl> --%>
                        <dl>
                            <dt>${controlPerson.columnTubeState == '1'?'列管':'撤管'}时间 ：</dt>
                            <dd><fmt:formatDate value="${controlPerson.columnDate}" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
                        </dl>
                        <dl>
                        	<dt>其他住址：</dt>
                        	<dd>${controlPerson.controlPersonExtend.otherAddress}</dd>
                        </dl>
                        <dl>
                        	<dt>列控方式：</dt>
                        	<dd>${controlPerson.columnTubeMode}</dd>
                        </dl>
                        <dl>
                        	<dt>在控状态：</dt>
                        	<dd>
                        		<ruiec:dictionary var="dictionary" dictionaryId="${controlPerson.isControl}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary>
                            </dd>
                        </dl>
                        <dl>
                        	<dt>列控级别：</dt>
                        	<dd>
                        		<ruiec:dictionary var="dictionary" dictionaryId="${controlPerson.columnTubeLevel}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary>
                            </dd>
                        </dl>
                        <dl>
                        	<dt>单位(职业)：</dt>
                        	<dd>${controlPerson.controlPersonExtend.occupation}</dd>
                        </dl> 
                    </div>
                </div>
                <div class="personal_details_peo personal_Contact_info">
                     <span class="fl tr Contact_info_tit">关系人信息</span>
                     <div class="fl personal_Contact_info_list">
	                     <c:forEach var="list" items="${controlPersonRelations}">
		                     <div class="fl personal_details_list personal_details_list1">
		                         <dl>
		                             <dt>姓名：</dt>
		                             <dd>${list.name}</dd>
		                         </dl>
		
		                     </div>
		                     <div class="fl personal_details_list personal_details_list2">
		                         <dl>
		                             <dt>身份证号码 ：</dt>
		                             <dd>${list.idCard }</dd>
		                         </dl>
		                     </div>
		                     <div class="fl personal_details_list personal_details_list3">
		                         <dl>
		                             <dt>所属关系：</dt>
		                             <dd>
		                             	<ruiec:dictionary var="dictionary" dictionaryId="${list.typeId }">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary>
		                             </dd>
		                         </dl>
		                     </div>
		                     <div class="fl personal_details_list personal_details_list4">
		                         <dl>
		                             <dt></dt>
		                             <dd>
		                             </dd>
		                         </dl>
		                     </div>
	                     </c:forEach>
                     </div>
                </div>
                <div class="mgt20 personal_Contact_catalogue">
                    <div class="ptd10 catalogue_tit">
                        <span class="Contact_info_tit">档案目录</span>
                    </div>
                    <div class="mgt10 personal_catalogue_box">
                    	<a href="javascript:;" onclick="loadPage(0,1)" class="personal_catalogue_list m_blue"> 
							预警信息(<i class="catalogue_num">${alarmCount }</i>)
						</a>
						<c:forEach var="list" items="${dynamicInfos}">
							<a href="javascript:;" onclick="loadPage(${list.id },1)"
								class="personal_catalogue_list"> <ruiec:dictionary
									var="dictionary" dictionaryId="${list.id }">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary>
								(<i class="catalogue_num" onclick=""></i>)
							</a>
						</c:forEach>
                    </div>
                </div>
            </div>
            <div id="div_list">
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
	});
	function isAuditKeyPersonColumnTub(id,isColumn){
		$.ajax({
			type:"POST",
			url:"/admin/controlPerson/isColumn.shtml",
			data: {id:id,isColumn:isColumn},
			datatype:"json",
			success:function(data){ 
				if( data.code == '200' ){ 
					layer.msg(data.msg);
					if(isColumn == '2'){
						$("#columnTubeMode").html('<em class="m_blue">已审核</em>'); 
					}else {
						$("#columnTubeMode").html('<em class="m_blue">未通过审核</em>');
				    	/* layer.open({
						    type: 2,
						    title: '拒绝理由', //不显示标题
						    area: ['550px', '400px'], //宽高
						    content: "/admin/controlPerson/setRefuseToPass.shtml?id="+id, 
						    //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
						});   */
					}
				}else{
					layer.msg(data.msg);
				}
			}
   		})
	}
	var queryParams = { id: ${controlPersonId}, personnelType: ${personnelType} ,viewType : ${viewType}};
	function loadPage(typeId,pageNumber) {
		layer.load();
		queryParams.typeId=typeId;
		queryParams.pageNumber=pageNumber;
        $.ajax({
            url: "/admin/controlPerson/getDynamicInfo.shtml",
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
            url: "/admin/controlPerson/countTypeList.shtml",
            type: "POST",
            data: {id: ${controlPersonId}},
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