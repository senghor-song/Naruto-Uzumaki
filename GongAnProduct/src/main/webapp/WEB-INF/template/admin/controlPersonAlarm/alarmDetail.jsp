<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
        	<jsp:include page="../common/leftNav2.jsp"></jsp:include>
            <div class="mainRight">
                <div class="pancl_pd">
                    <form>
                        <div class="tit_menu_list">
                            <a href="/admin/common/main.shtml" class="m_menu_item">
                                <i class="iconfont">&#xe62a;</i>
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
                            <a href="/admin/controlPersonAlarm/alarmDetail.shtml?id=${id}&pid=${pid}&iid=${iid}" class="m_menu_item">
                                <em>预警详情</em>
                            </a>
                            <span class="fr">
	                        	<input type="button" onclick="javascript:history.back(-1);" class="fr back_btn" value="返回上一页"/>
	                        </span>
                        </div>
                        <div class="mgt20 m_Tab_Control">
                            <a href="/admin/controlPersonAlarm/alarmDetail.shtml?id=${id}&pid=${pid}&iid=${iid}" class="active">预警信息</a>
                            <c:if test="${sessionScope.loginUserUnit.userName!='警员'}">
								<a href="/admin/controlPersonInstructi/instructionRecord.shtml?id=${id}&pid=${pid}&iid=${iid}">下发签收记录</a>
								<a href="/admin/controlPersonAlarm/alarmHistorical.shtml?id=${id}&pid=${pid}&iid=${iid}">历史预警数据</a>
							</c:if>
                        </div>
                        <div class="mgt20 personal_details m_Warning_Info ">
                            <div class="b0 personal_details_peo">
                                <span class="fl">
                                    <img class="progres_img" src="${controlPerson.controlPersonExtend.photo!=null?controlPerson.controlPersonExtend.photo:'/resources/admin/images/Avatar.png'}"/>
                                </span>
                                <div class="fl personal_details_list personal_details_list1">
                                    <dl>
                                        <dt>姓名：</dt>
                                        <dd>${controlPerson.name==null?'暂无':controlPerson.name}</dd>
                                    </dl>
                                    <dl>
                                        <dt>身份证：</dt>
                                        <dd>${controlPerson.idCard==null?'暂无':controlPerson.idCard}</dd>
                                    </dl>
                                    <dl>
                                        <dt>责任民警：</dt>
                                        <dd><ruiec:user var="police" userId="${controlPerson.userId}">${police==null?'暂无':police.policeName==null?'暂无':police.policeName}</ruiec:user></dd>
                                    </dl>
                                    <dl>
                                        <dt>户籍详址：</dt>
                                        <dd>${controlPerson.controlPersonExtend.householdRegisterPlace==null?'暂无':controlPerson.controlPersonExtend.householdRegisterPlace}</dd>
                                    </dl>
                                    <c:if test="${json != '' and json != null}">
                                    <dl>
                                        <dt>处理要求：</dt>
                                        <dd>
                                        	<em class="tab_detail">${json}</em>
											<div class="hideThis ellipsis_dateil">${json}</div>
										</dd>
	                                    <!-- <dd></dd> -->
                                    </dl>
                                    </c:if>
                                </div>
                                <div class="fl personal_details_list personal_details_list2">
                                   <dl>
                                       <dt>联系方式：</dt>
                                       <dd>${controlPerson.phone==null?'暂无':controlPerson.phone}</dd>
                                   </dl>
                                    <dl>
                                        <dt>民族：</dt>
                                        <dd><ruiec:dictionary var="dictionary" dictionaryId="${controlPerson.controlPersonExtend.nation}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary></dd>
                                    </dl>
                                    <dl>
                                        <dt>管辖单位：</dt>
                                        <dd><ruiec:unit var="unit" unitId="${controlPerson.unitId}">${unit.unitName==null?'暂无':unit.unitName}</ruiec:unit></dd>
                                    </dl>
                                </div>
                                <div class="fl personal_details_list personal_details_list3">
                                    <dl>
                                        <dt>性别 ：</dt>
                                        <dd>${controlPerson.sex==null?'暂无':controlPerson.sex}</dd>
                                    </dl>
                                    <dl>
                                        <dt>籍贯 ：</dt>
                                        <dd>${controlPerson.controlPersonExtend.nativePlace==null?'暂无':controlPerson.controlPersonExtend.nativePlace}</dd>
                                    </dl>
                                    <dl>
                                        <dt>管控理由 ：</dt>
                                        <dd>${controlPerson.controlPersonExtend.reason==null?'暂无':controlPerson.controlPersonExtend.reason}</dd>
                                    </dl>
                                    <dl>
                                        <dt>现住详址 ：</dt>
                                        <dd>${controlPerson.controlPersonExtend.nowLiveArea==null?'暂无':controlPerson.controlPersonExtend.nowLiveArea}</dd>
                                    </dl>
                                </div>
                                <div class="fl personal_details_list personal_details_list4">
                                    <dl>
                                        <dt>人员类别 ：</dt>
                                        <dd>
                                        	<c:if test="${empty controlPersonz.controlPersonTypes}">
                                        		暂无
                                        	</c:if>
                                        	<c:if test="${not empty controlPersonz.controlPersonTypes}">
                                        	<c:forEach items="${controlPersonz.controlPersonTypes}" var="list">
                                        		<ruiec:dictionary var="dictionary" dictionaryId="${list.dictionaryId}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary>
                                    		</c:forEach>
                                    		</c:if>
                                   		</dd>
                                    </dl>
                                    <dl>
                                        <dt>入库时间 ：</dt>
                                        <dd><fmt:formatDate value="${controlPerson.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
                                    </dl>
                                </div>
                            </div>

                            <div class="mgt15 m_early_info">
                                <div class="ptd10 catalogue_tit">
                                    <span class="Contact_info_tit">重点人员预警信息</span>
                                </div>
                                <div class="m_early_info_wrap">
                                    <div class="m_early_id_info">
                                        <dl class="w250">
                                            <dt>预警级别：</dt>
                                            <dd><ruiec:dictionary var="dictionary" dictionaryId="${controlPersonAlarm.warningLevel }">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary></dd>
                                        </dl>
                                        <dl class="w250">
                                            <dt>签收时间：</dt>
                                            <dd>${controlPersonAlarm.signTime==null?'暂无':controlPersonAlarm.signTime}</dd>
                                        </dl>
                                        <dl class="w250">
                                            <dt>反馈时间：</dt>
                                            <dd>${controlPersonAlarm.feedbackTime==null?'暂无':controlPersonAlarm.feedbackTime}</dd>
                                        </dl>
                                        <dl class="w250">
                                            <dt>联系方式：</dt>
                                            <dd>
												<c:if test="${fn:length(InstructiPerson)==0}">
													暂无
												</c:if>
												<c:if test="${fn:length(InstructiPerson)>0}">
													<c:forEach items="${InstructiPerson}" var="list">
														<ruiec:user var="police" userId="${list.instructionRecipientPrikey}">${police==null?'无':police.phone}</ruiec:user>
													</c:forEach>
												</c:if>
											</dd>
                                        </dl>
                                    </div>
                                    <div class="m_early_info_wrap_4">
                                        <dl class="w_12">
                                            <dt>签收单位：</dt>
                                            <dd>
                                            	<c:if test="${fn:length(InstructiUnit)==0}">
													暂无
												</c:if>
												<c:if test="${fn:length(InstructiUnit)>0}">
	                                            	<c:forEach items="${InstructiUnit}" var="list">
														<span><ruiec:unit var="unit" unitId="${list.instructionRecipientUnitId}">${unit == null?'无':unit.unitName}</ruiec:unit></span>
													</c:forEach>
												</c:if>
                                            </dd>
                                        </dl>
                                        <dl class="w_12">
                                            <dt>签收民警：</dt>
                                            <dd>
                                            	<c:if test="${fn:length(InstructiPerson)==0}">
                                            		暂无
                                            	</c:if>
                                            	<c:if test="${fn:length(InstructiPerson)>0}">
                                            		<c:forEach items="${InstructiPerson}" var="list">
														<span><ruiec:user var="police" userId="${list.instructionRecipientPrikey}">${police == null?'无':police.policeName}</ruiec:user></span>
													</c:forEach>
                                            	</c:if>
                                            </dd>
                                        </dl>
                                    </div>
                                    <div class="m_early_id_info">
                                    <!-- <dl class="w250">
                                            <dt>身份证：</dt>
                                            <dd>420626199403154215</dd>
                                        </dl>
                                        <dl class="w250">
                                            <dt>报警相似度：</dt>
                                            <dd>80%</dd>
                                        </dl>
                                        <dl class="w250">
                                            <dt>抓拍时间：</dt>
                                            <dd>2017-11-27</dd>
                                        </dl>
                                        <dl class="w250">
                                            <dt>其他证件号：</dt>
                                            <dd>420626199403154215</dd>
                                        </dl>
                                    </div> -->
                                    <%-- <div class="m_early_adress">
                                        <!-- <dl class="w470">
                                            <dt>抓拍摄像机名称（位置）：</dt>
                                            <dd>火车站进站口3</dd>
                                        </dl> -->
                                        <dl class="w253">
                                            <dt>动态信息类别：</dt>
                                            <dd><ruiec:dictionary var="dictionary" dictionaryId="${controlPersonAlarm.dynamicInfoPrikey}">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary></dd>
                                        </dl>
                                        <!-- <dl class="w230">
                                            <dt>被布控人姓名：</dt>
                                            <dd>李警</dd>
                                        </dl> -->
                                    </div> --%>
                                    <div class="m_early_info_wrap_img">
                                   		<c:forEach items="${l}" var="li">
                                   			<c:if test="${li.type == 'string'}">
                                    			<dl class="w_12">
		                                            <dt>${li.key}:</dt>
		                                            <dd>${li.value!=''?li.value:'暂无'}</dd>
		                                        </dl>
                                   			</c:if>
                                   			<c:if test="${li.type == 'image'}">
                                   				<dl class="w_12">
		                                            <dt>${li.key}:</dt>
		                                            <dd>
		                                                <img src="${li.value!=null?li.value:'/resources/admin/images/Avatar.png'}" class="early_img" />
		                                            </dd>
		                                        </dl>
                                   			</c:if>
                                   		</c:forEach>
                                        <%-- <dl class="w_12">
                                            <dt>布控图片 ：</dt>
                                            <dd><img src="${base }/resources/admin/img/early1.png" class="early_img" /> </dd>
                                        </dl>
                                        <dl class="w_12">
                                            <dt>抓拍人脸图片 ：</dt>
                                            <dd>
                                                <img src="${base }/resources/admin/img/early2.png" class="early_img" />
                                                <img src="${base }/resources/admin/img/early2.png" class="early_img" />
                                            </dd>

                                        </dl>
                                        <dl class="w_12">
                                            <dt>抓拍场景图 ：</dt>
                                            <dd><img src="${base }/resources/admin/img/early1.png" class="early_img" /></dd>
                                        </dl> --%>
                                    </div> 
                                </div>
                            </div>
                        </div>
                    </form>
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
	   /* $(function(){
	    	if( $(".tab_detail").size()>0 ){ 
	    		$(".tab_detail").each(function(){
	                var tooltips = $('.ellipsis_dateil').text();
	                $(this).pt({
	                    position: 'r', // 默认属性值
	                    align: 'c',	   // 默认属性值
	                    content: tooltips,
	                });
	            }); 
	    	};
	    })  */
    </script>
</body>
</html>