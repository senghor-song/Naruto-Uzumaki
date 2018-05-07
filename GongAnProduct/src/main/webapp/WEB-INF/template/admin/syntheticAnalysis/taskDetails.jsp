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
	<link rel="stylesheet" href="${base }/resources/admin/imagesMagnify/css/jquery.lightbox-0.5.css" media="screen" />
    <style type="text/css">
    .list_box .list_em{float:left;width:306px;height:30px;line-height:30px;}
    .list_box{border-top:1px solid #CCCCCC;float:left;width:918px;}
    a.more.curr{color:red;font-weight: bold;}
    .webuploader-pick{border:1px solid #CCCCCC}
	.ratyli .rate{font-size: 24px;}
	.rate-empty{color: #000;}
	.rate-full{color: #f5bd23;}
	.ratyli .rate-active{color: #a94039;}
    #fileUrls a {padding: 0px 5px;float: left;}
    </style>
</head>
<body class="bodyCss">
        <jsp:include page="../common/header.jsp"></jsp:include> 
        <!---->
        <section>
        	<jsp:include page="../common/leftNav7.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                    <div class="new-people-box xzzdr">
                        <form id="form1" action="/admin/syntheticAnalysis/save.shtml" method="post">
                        	<div class="tit_menu_list">
                                <a href="/admin/common/main.shtml" class="m_menu_item nav_list">
                                <i class="iconfont"></i>
                                <em>首页</em>
	                            </a>
	                            <i class="fl split_gt">&gt;</i>
	                            <a href="javascript:;" class="m_menu_item refresh_html">
	                                <em>合成研判</em>
	                            </a>
	                            <i class="fl split_gt">&gt;</i>
	                            <a href="/admin/syntheticAnalysis/taskList.shtml" class="m_menu_item">
	                                <em>任务列表</em>
	                            </a>
	                            <i class="fl split_gt">&gt;</i>
	                            <a href="javascript:;" class="m_menu_item refresh_html">
	                                <em>任务详情</em>
	                            </a>
	                            <span class="fr">
	                        	    <input type="button" onclick="javascript:history.back(-1);" class="fr back_btn" value="返回上一页"/>
	                            </span>
                            </div>
                            <div class="mgt15 new-nan-top">
                            	<input value="${taskDTO.taskId }" name="taskId" type="hidden">
                            	<input value="${taskDTO.taskDetailsId }" name="taskDetailsId" type="hidden">
                                <dl class="new_peo_list">
	                                <dt>任务名称:</dt>
                                    <dd>
                                        <div class="fl" <c:if test="${task.taskLevel eq '紧急'}">style="color: red"</c:if>>
                                        	${task.name }
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
	                                <dt>下发单位:</dt>
                                    <dd>
                                       	<c:forEach items="${task.taskDetails}" var="detailsList">
                                           	<ruiec:unit var="unit" unitId="${detailsList.receiveUnitId}">${unit == null?'无':unit.unitName}</ruiec:unit>
                                           	(${detailsList.feedbackStatus == 0 ? '待签收':'已签收'})<br>
                                        </c:forEach>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
	                                <dt>处置要求:</dt>
                                    <dd>
                                        <div class="fl" style="word-wrap:break-word; word-break:break-all;">
                                        	${task.handleMode }
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
	                                <dt>反馈期限:</dt>
                                    <dd>
										<fmt:formatDate value="${task.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>至
										<fmt:formatDate value="${task.handleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
	                                <dt>任务状态:</dt>
                                    <dd>
                                        <div class="fl">
                                        	
                                        	<c:if test="${task.isCheck ne 0}">
                                           		<c:if test="${task.status ne 0}">
	                                            		${task.status eq '1' ? '待签收' : task.status eq '2' ? '进行中' : '已结束'}
	                                           	</c:if>
	                                           	<c:if test="${task.status eq 0}">
	                                           		${task.status eq 3 ? "已结束" : task.isCheck == 3 ? "待初审" : task.isCheck == 2 and empty task.checkUserId ? "待审核" : "待复审" }
	                                           	</c:if>
                                           	</c:if>
                                           	<c:if test="${task.isCheck eq 0}">
                                           		未通过审核<div style="color: red;word-wrap:break-word; word-break:break-all;">(${task.checkRemark})</div>
                                           	</c:if>
                                        </div>
                                    </dd>
                                </dl>
                                <c:if test="${task.status==3}">
	                                <dl class="new_peo_list">
		                                <dt>结束理由:</dt>
	                                    <dd>
	                                        <div class="fl">
                                    			<div style="word-wrap:break-word; word-break:break-all;">${task.overReason}</div>
	                                        </div>
	                                    </dd>
	                                </dl>
                                </c:if>
                                <dl class="new_peo_list">
	                                <dt>发起人:</dt>
                                    <dd>
                                        <div class="fl">
	                                    	<ruiec:user var="police" userId="${task.originatorUserId}">${police == null?'无':police.policeName}</ruiec:user>
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
	                                <dt>发起单位:</dt>
                                    <dd>
                                        <div class="fl">
                                            <ruiec:unit var="unit" unitId="${task.originatorUnitId}">${unit == null?'无':unit.unitName}</ruiec:unit>
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
                                    <dt>任务详情:</dt>
                                    <dd>
                                    	<div style="word-wrap:break-word; word-break:break-all;">${task.details}</div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
	                                <dt>接收单位:</dt>
                                    <dd>
                                        <div class="fl">
                                        	<c:forEach items="${task.taskDetails}" var="detailsList">
                                            	<ruiec:unit var="unit" unitId="${detailsList.receiveUnitId}">${unit == null?'无':unit.unitName}</ruiec:unit>
                                            	(${detailsList.feedbackStatus == 0 ? '待签收':'已签收'})<br>
                                            </c:forEach>
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
	                                <dt>附件上传：</dt>
                                    <dd>
                                        <div class="fl">
		                  				 	<div id="fileUrls">
		                  				 	<c:forEach var="list" items="${fileUrls}">
		                  				 		<c:set var="fileType" value="${fn:split(list, '.')}" />
										  		<a 
										  		<c:if test="${fileType[4] == 'bmp' or fileType[4] == 'gif' or fileType[4] == 'jpeg' or fileType[4] == 'jpg' or fileType[4] == 'png'}">
										  			class="fileimg" 
										  		</c:if>
										  		href="${list }" download="${list}">
									  				<img src="/resources/admin/images/ruiec_${fileType[4]}.png" width="60px">
									  			</a>
		                  				 	</c:forEach>
		                  				 	</div>
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
	                                <c:if test="${task.isCheck !=1}">
		                                <c:if test="${!empty succeed}">
		                                    <div class="state-save sub_btn_box">
				                                <input type="button" onclick="isCheck(${task.id},1,'任务审核通过')" value="通过" class="label_btn m_bg_blue" />
				                                <input type="button" onclick="isCheck(${task.id},2,'任务审核不通过')" value="不通过" class="label_btn" />
			                           	 	</div>
		                           	 	</c:if>
	                           	 	</c:if>
                                </dl>
                                <c:forEach items="${task.taskDetails}" var="taskDetails" varStatus="count">
	                                <c:if test="${!empty taskDetails.feedbackDTO}">
		                                <div class="new_peo_list" style="border:1px solid #CCCCCC">
	                                   		<dl class="new_peo_list">
				                                <dt>任务反馈</dt>
			                                    <dd>
			                                    </dd>
			                                </dl>
		                                    <dl class="new_peo_list">
				                                <dt>反馈单位：</dt>
			                                    <dd>
			                                        <div class="fl">
			                                        	<ruiec:unit var="unit" unitId="${taskDetails.receiveUnitId}">${unit == null?'无':unit.unitName}</ruiec:unit>
			                                        </div>
			                                        <div class="fl mgl100 right_item">
			                                            <label>
			                                                <span class="fl">反馈人：</span>
			                                                	<ruiec:user var="police" userId="${taskDetails.receiveUserId}">${police == null?'无':police.policeName}</ruiec:user>
			                                            </label>
			                                        </div>
			                                    </dd>
			                                </dl>
                               				<c:forEach items="${taskDetails.feedbackDTO}" var="feedbackDTO">
	                                        	<hr/>
	                               				<dl class="new_peo_list">
					                                <dt>反馈时间：</dt>
				                                    <dd>
				                                        <div class="fl">
				                                        	${feedbackDTO.createTime}
				                                        </div>
				                                    </dd>
				                                </dl>
				                                <dl class="new_peo_list">
					                                <dt>反馈内容：</dt>
				                                    <dd>
				                                        <div class="fl right_item" style="word-wrap:break-word; word-break:break-all;">
				                                        	${feedbackDTO.content }
				                                        </div>
				                                    </dd>
				                                </dl>
				                                <dl class="new_peo_list">
					                                <dt>附件：</dt>
				                                    <dd>
				                                        <div class="fl right_item">
				                                        	<c:if test="${!empty feedbackDTO.fileUrl}">
					                                        	<c:set value="${ fn:split(feedbackDTO.fileUrl, ';') }" var="fileUrls" />
					                                        	<div id="fileUrls">
								                  				 	<c:forEach var="list" items="${fileUrls}">
								                  				 		<c:set var="fileType" value="${fn:split(list, '.')}" />
																  		<a 
																  		<c:if test="${fileType[4] == 'bmp' or fileType[4] == 'gif' or fileType[4] == 'jpeg' or fileType[4] == 'jpg' or fileType[4] == 'png'}">
																  			class="fileimg" 
																  		</c:if>
																  		href="${list }" download="${list}">
															  				<img src="/resources/admin/images/ruiec_${fileType[4]}.png" width="60px">
															  			</a>
								                  				 	</c:forEach>
							                  				 	</div>
						                  				 	</c:if>
				                                        </div>
				                                    </dd>
				                                </dl>
				                                <dl class="new_peo_list">
					                                <dt></dt>
				                                    <dd>
				                                        <c:if test="${task.originatorUserId == sessionScope.user.id}">
				                                        	<c:if test="${feedbackDTO.type == 0}">
					                                        	<div class="state-save sub_btn_box">
									                                <input type="button" onclick="auditFeedback(${taskDetails.id},1)" value="通过" class="label_btn m_bg_blue" />
									                                <input type="button" onclick="auditFeedback(${taskDetails.id},2)" value="不通过" class="label_btn" />
								                           	 	</div>
				                                        	</c:if>
		                                        		</c:if>
				                                    </dd>
				                                </dl>
	                                        	<c:if test="${feedbackDTO.type != 0}">
		                                        	<dl class="new_peo_list">
						                                <dt>审核状态：</dt>
					                                    <dd>
					                                        <div class="fl right_item">
					                                        	${feedbackDTO.type == 1 ?'审核通过':'审核不通过'}
					                                        </div>
					                                    </dd>
					                                </dl>
					                                <dl class="new_peo_list">
						                                <dt>反馈评分：</dt>
					                                    <dd>
					                                        <div class="fl right_item">
		                                        				<span class="ratyli" data-rate="${feedbackDTO.points}"></span>
					                                        </div>
					                                    </dd>
					                                </dl>
					                                <c:if test="${!empty feedbackDTO.comment}">
						                                <dl class="new_peo_list">
							                                <dt>审核评价：</dt>
						                                    <dd>
						                                        <div class="fl right_item" style="word-wrap:break-word; word-break:break-all;">
						                                        	${feedbackDTO.comment }
						                                        </div>
						                                    </dd>
						                                </dl>
					                                </c:if>
	                                        	</c:if>
				                        	</c:forEach>
		                                </div>
	                                </c:if>
                                </c:forEach>
                            </div>
                        </form>
                    </div>
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
    <!---下拉框 -->
    <script src="${base }/resources/admin/js/common.js"></script>
    <!--时间-->
    <script src="${base }/resources/admin/js/datetimepicker/jquery.datetimepicker.js"></script>
    <script src="${base }/resources/admin/js/datetimepicker/timer.js"></script>
	<script src="${base }/resources/admin/js/webuploader/webuploader.min.js"></script>
	<script src="${base }/resources/admin/js/webuploader/myuploader.js"></script>
	<script src="${base }/resources/admin/js/ratyli/jquery.ratyli.min.js"></script>
	<!-- 图片放大效果 -->
    <script type="text/javascript" src="${base }/resources/admin/imagesMagnify/js/jquery.lightbox-0.5.js"></script>
    <script type="text/javascript">
	    $(function() {
			$(".ratyli").ratyli({disable:true});
	        $('#fileUrls .fileimg').lightBox();
		});
		function auditFeedback(taskDetailsId,type){
	    	layer.open({
			    type: 2,
			    title: '审核反馈', //不显示标题
			    area: ['600px', '300px'], //宽高
			    closeBtn:1,
			    content: "/admin/syntheticAnalysis/auditFeedback.shtml?taskDetailsId="+taskDetailsId +"&type="+type,  //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响 
			    success: function(){
		            $(document).on('keydown', function(e){  //document为当前元素，限制范围，如果不限制的话会一直有事件
		                if(e.keyCode == 13){
		        		    return false;
		                }
		            })
			    }  
			});  
	   	}
	   	function isCheck(taskId,isCheck){
		   	if(isCheck==1){
		   		$.ajax({
					type:"POST",
					url:"/admin/syntheticAnalysis/saveIsCheck.shtml?taskId="+taskId+"&isCheck="+isCheck,
					data: null,
					datatype:"json",
					success:function(data){ 
						if( data.code == '200' ){ 
							layer.msg(data.msg);
							$('.state-save.sub_btn_box').remove();
						}else{
							layer.msg(data.msg);
						}
					}
		   		})
			}else{
		    	layer.open({
				    type: 2,
				    title: '审核任务', //不显示标题
				    area: ['800px', '300px'], //宽高
				    closeBtn:1,
				    content: "/admin/syntheticAnalysis/isCheck.shtml?taskId="+taskId +"&isCheck="+isCheck,  //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
				    success: function(){
			            $(document).on('keydown', function(e){//document为当前元素，限制范围，如果不限制的话会一直有事件
			                if(e.keyCode == 13){
			        		    return false;
			                }
			            })
				    }
				});
			}
	   	}
	</script>
</body>
</html>