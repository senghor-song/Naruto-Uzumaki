<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/ruiec-tags" prefix="ruiec" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<title><ruiec:title/></title>
    <link rel="stylesheet" href="${base }/resources/admin/js/select/select2.css"> 
    <link rel="stylesheet" href="${base }/resources/admin/js/datetimepicker/jquery.datetimepicker.css" >
    <link href="${base }/resources/admin/js/tooltips/tooltips.css" rel="stylesheet" id="skin">
    <link rel="stylesheet" href="${base }/resources/admin/css/layer.css" >
    <link rel="stylesheet" href="${base }/resources/admin/css/common.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/style.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/index.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/quote.css">
    <style> 
  		.select2-container.select2-container--default{ width:240px !important; height:30px !important;; }
  		.select2-results__option[aria-selected]{padding: 6px 20px 6px 6px !important;} 
  		.select2-container--default .select2-selection--multiple{position: relative; border:1px solid #eee !important; padding: 0 30px 0px 0px;} 
  		.select2-container--default.select2-container--focus .select2-selection--multiple{ padding:0px 30px 0px 0px; border:1px solid #eee !important;}
  		.select2-results__options.iconfont{ font-size:12px; font-weight:500; font-family: "微软雅黑"!important;} 
  		.select2-container--default .select2-results__option[aria-selected=true]:after{ font-family: "../iconfont/iconfont" !important; position: absolute; top:7px; right: 0; content: "\e607"; color:#666; font-size:14px;}
  		.select2-container--default .select2-selection--multiple .select2-selection__choice{ margin-top:0px !important; border:0px !important; color:#999; background-color:#fff !important; padding:0px !important; float:none !important;}
  		.select2-container--default .select2-selection--multiple .select2-selection__rendered{ height:30px;}
  		.select2-container .select2-search--inline{display: inline-block; height: 30px; line-height: 30px; padding: 0; margin-top: -3px; color: #999; } 
  		.select2-container .select2-selection--multiple .select2-selection__rendered{ display: inline !important; line-height: 30px !important; max-width:100%; padding-left: 8px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;} 
  		.select2-selection.select2-selection--multiple .icon-xiala{position: absolute; right: -3px; top: -3px; right: 0px\9; top: 0px\9; display: block; width: 30px; height: 100%; border-left: 1px solid #eee; color: #787878; font-size: 18px; text-align: center; background: #eee; -webkit-transform: scale(0.833); bottom: 0; height: 35px; line-height: 35px; height: 33px\9; line-height: 33px\9;}
  		.select2-selection.select2-selection--multiple .icon-xiala:after{ }
  		.select2-container--default .select2-search--inline .select2-search__field{ margin:0; width:100% !important; padding:0 8px;}
  		.select2-search__field::-webkit-input-placeholder{ color: #999999;}
		.select2-search__field:-moz-placeholder{color: #999999;}
		.select2-search__field::-moz-placeholder{color: #999999;}
		.select2-search__field:-ms-input-placeholder{color: #999999;}
		.approve_select{ position: absolute; z-index: 99999;  height: 30px; line-height: 30px; padding: 0 30px 0px 6px; color:#999; display:none;}
  	</style>
</head>
<body class="bodyCss">
        <jsp:include page="../common/header.jsp"></jsp:include>
        <!---->
        <section> 
        	<jsp:include page="../common/leftNav2.jsp"></jsp:include>
            <div class="mainRight">
                <div class="pancl_pd">
                    <form method="GET" action="/admin/controlPersonAlarm/list.shtml" id="searchform">
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
                        </div>
                        <div class="mgt20 importantC">
                            <div class="m_early">
                                <span class="fl m_early_sum">
                                    <em class="fl">预警总数：</em>
                                    <i class="m_cred" id="totalCount"></i>
                                </span>
                                <span class="fl mgl45 m_today_early">
                                   <em class="fl">今日推送预警：</em>
                                   <label>
                                    </label>
                                </span>
                            </div>
                            <div class="mgt20" id="rylbCount">
                            	<input type="hidden" name="todayAlarm" value="${todayAlarm}"/>
                            	<div class="fl rule-single-select single-select">
                                    <select name="signStatus" style="display: none;" onchange="findForm()">
                                    	<option value="">是否签收</option>
                                   		<option value="2" <c:if test="${signStatus == '2'}">selected="selected"</c:if>>已签收</option>
                                   		<option value="1" <c:if test="${signStatus == '1'}">selected="selected"</c:if>>未签收</option>
                                    </select>
                                </div>
                                <div class="fl mgl10 rule-single-select single-select">
                                    <select name="feedbackTime" style="display: none;" onchange="findForm()">
                                    	<option value="">是否反馈</option>
                                   		<option value="yes" <c:if test="${feedbackTime == 'yes'}">selected="selected"</c:if>>已反馈</option>
                                    	<option value="no" <c:if test="${feedbackTime == 'no'}">selected="selected"</c:if>>未反馈</option>
                                    </select>
                                </div>
                                <div class="fl mgl10 rule-single-select single-select">
                                    <select name="personType" style="display: none;" onchange="findForm()">
                                    	<option value="">人员类别</option>
                                    	<c:forEach items="${dataitem}" var="list">
                                    		<option value="${list.id}" <c:if test="${list.id == personType}">selected="selected"</c:if>>${list.itemName}</option>
                                    	</c:forEach>
                                    </select>
                                </div>
                                <div class="fl mgl10 select_box">
                                    <select name="unitId" style="display: none; width:150px;" onchange="findForm()" id="units">
                                    	<option value="">预警地区</option>
                                    	<c:forEach items="${units }" var="list">
                                        	<option value="${list.id }" <c:if test="${list.id == unitId}">selected="selected"</c:if>>${list.unitName }</option>
                                        </c:forEach>
                                    </select>
                                </div> 
                                <div class="fl mgl10 select_box"> 
                                    <select name="dynamicTypeIdz" class="js-example-placeholder-multiple js-states form-control" id="select_multiple" multiple="multiple" style="display:none; width:150px;" onchange="findForm()">
                                    	<c:forEach items="${dynamicType}" var="list">
                                        	<option value="${list.id}" 
                                        		<c:forEach items="${dynamicTypeIdz}" var="a">
													<c:if test="${list.id == a}">selected="selected"</c:if>
                                        		</c:forEach>
                                        	>${list.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="searh_list">
                                    <dl class="search_item">
                                        <dt>姓名：</dt>
                                        <dd class="mgr30">
                                            <input type="text" class="text_inp" name="name" datatype="/^[\u4e00-\u9fa5]{1,}$/" value="${name}" errormsg="请输入中文" ignore="ignore"/>
                                        </dd>
                                    </dl>
                                    <dl class="search_item">
                                        <dt>身份证号：</dt>
                                        <dd class="mgr30">
                                            <input type="text" class="text_inp" name="idCard" onkeyup="idCardKeyUp($(this))" value="${idCard}" maxlength="18"  ignore="ignore"/>
                                        </dd>
                                    </dl>
                                    <dl class="search_item">
                                        <dt>登记时间：</dt>
                                        <dd>
                                        	<input name="startDate" class="Wdate text_inp " id="quote_time" autocomplete="off" type="text" 
                                                value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd HH:mm"/>"/>
                                            <em class="offDivide">至</em>
                                            <input name="endDate" class="Wdate text_inp" id="quote_time1" autocomplete="off" type="text" 
                                             value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd HH:mm"/>"/>
                                            <button type="submit" class="btn curr fr mgl10" style="cursor: pointer;">搜索</button>
                                            <button type="reset" class="btn fr mgl10 resetBtn" onClick="resetBtn()">重置</button>
                                        </dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
                        <div class="mgt20 importantC">
                            <div class="importantNList">
                                <table class="tab tab_ellipsis">
                                    <colgroup> 
                                        <col width="8%"/>
                                        <col width="12%"/> 
                                        <col width="8%"/>
                                        <col width="10%"/>
                                        <col width="11%"/>
                                        <col width="12%"/>
                                        <col width="10%"/>
                                        <col width="8%"/>
                                        <col width="18%"/>
                                    </colgroup>
                                    <thead>
                                        <tr> 
                                            <th class="pdl15">预警级别</th>
                                            <th>预警时间</th>
                                            <th>姓名</th>
                                            <th>人员类别</th>
                                            <th>动态信息类别</th>
                                            <th>管控责任中心 </th>
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
	                                            <td><fmt:formatDate value="${list.createDate }" pattern="yyyy-MM-dd HH:mm"/></td>
	                                            <td>${list.controlPerson.name }</td>
	                                            <td>
	                                            	<em class="tab_detail" ><c:forEach var="dict" items="${list.controlPerson.controlPersonTypes}" varStatus="l"><ruiec:dictionary var="dictionary" dictionaryId="${dict.dictionaryId}">${dictionary == null?'无':dictionary.itemName}<c:if test="${!l.last}">,</c:if></ruiec:dictionary></c:forEach></em>
	                                             	<div class="hideThis ellipsis_dateil"><c:forEach var="dict" items="${list.controlPerson.controlPersonTypes}" varStatus="l"><ruiec:dictionary var="dictionary" dictionaryId="${dict.dictionaryId}">${dictionary == null?'无':dictionary.itemName}<c:if test="${!l.last}">,</c:if></ruiec:dictionary></c:forEach></div>
                                             	</td>
	                                            <td>
	                                            	<em class="tab_detail"><ruiec:apiConfig var="api" apiId="${list.dynamicInfoTypeId}">${api == null?'无':api.name}</ruiec:apiConfig></em>
	                                            	<div class="hideThis ellipsis_dateil"><ruiec:apiConfig var="api" apiId="${list.dynamicInfoTypeId}">${api == null?'无':api.name}</ruiec:apiConfig></div>
	                                            </td>
	                                            <td><ruiec:unit var="unit" unitId="${list.controlUnit}">${unit.unitName}</ruiec:unit></td>
	                                            <td><ruiec:unit var="unit" unitId="${list.alarmUnit}">${unit.unitName}</ruiec:unit></td>
	                                            <td><ruiec:user var="listUser" userId="${list.signPrikey}">${listUser == null?'':listUser.policeName}</ruiec:user></td>
	                                            <td>
	                                            	<c:if test="${list.signPrikey == sessionScope.user.id and list.remark == null}">
		                                                <a href="/admin/controlPersonAlarm/alarmFeedback.shtml?id=${list.id}" class="m_blue">未反馈</a>
		                                                <i class="m_e8">|</i>
	                                                </c:if>
	                                                <c:if test="${list.signPrikey == sessionScope.user.id and list.remark != null}">
		                                                <a href="/admin/controlPersonAlarm/alarmFeedback.shtml?id=${list.id}" class="m_blue">补充反馈</a>
		                                                <i class="m_e8">|</i>
	                                                </c:if>
	                                                <c:if test="${list.signPrikey == null}">
		                                                <a href="javascript:void(0);" class="m_blue sign" val="${list.id}">签收</a>
		                                                <i class="m_e8">|</i>
	                                                </c:if>
	                                                <c:if test="${list.signPrikey == sessionScope.user.id and sessionScope.loginUserUnit != null}">
		                                                <a href="#" onclick="issued(${list.id});" class="m_blue">下发</a>
		                                                <i class="m_e8">|</i>
	                                                </c:if>
	                                                <c:if test="${list.remark != null}">
	                                                	<a href="/admin/controlPersonAlarm/alarmFeedbackDetail.shtml?id=${list.id}" class="m_blue">反馈详情</a>
	                                                	<i class="m_e8">|</i>
	                                                </c:if>
	                                                <a href="#" onclick="isPower(${list.id},${list.controlPerson.id})" class="m_blue">详细</a>
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
                </div>
            </div>
        </section>
    <!--js-->
    <script src="${base }/resources/admin/js/jquery-1.9.1.min.js"></script>
    <script src="${base }/resources/admin/js/layer.js"></script>
    <!---下拉框 -->
    <script src="${base }/resources/admin/js/select/select2.js"></script>  
    <script src="${base }/resources/admin/js/datetimepicker/jquery.datetimepicker.js"></script>
    <script src="${base }/resources/admin/js/datetimepicker/timer.js"></script> 
    <!--验证-->
    <script src="${base }/resources/admin/js/jquery.form.js"></script>
    <script src="${base }/resources/admin/js/Validform_v5.3.2/Validform_v5.3.2.js"></script>
    <!--自写-->
    <script src="${base }/resources/admin/js/layout.js"></script> 
    <script src="${base }/resources/admin/js/common.js"></script> 
    <!--时间--> 
    <link href="${base }/resources/admin/js/tooltips/tooltips.css" rel="stylesheet" id="skin">
    <script src="${base }/resources/admin/js/tooltips/bootstrap-tooltip.js"></script>
    <script src="${base }/resources/admin/js/tooltips/bootstrap-affix.js"></script>
    <script src="${base }/resources/admin/js/tooltips/application.js"></script>
    <script src="${base }/resources/admin/js/tooltips/jquery.pure.tooltips.js"></script> 
    <script type="text/javascript">
   	function issued(id){
   		$.ajax({
		    url:'/admin/controlPersonInstructi/isIssued.shtml',
		    type:'POST',
		    data:{
		        id:id
		    },
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data,textStatus,jqXHR){
		    	if(data.code == '200'){
		    		layer.open({
			   	         type: 2,
			   	         title: '选择下发单位', //不显示标题
			   	         area: ['660px', '500px'], //宽高
			   	         content: "/admin/controlPersonInstructi/issuedInit.shtml?id="+id,  
			   	    });
		    	}else{
		    		layer.msg(data.msg,{icon: 2});
		    	}
		    },
		}) 
   	}
	$(function(){
		$.ajax({
		    url:'/admin/controlPersonAlarm/alarmCount.shtml',
		    type:'POST',
		    data:{},
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data,textStatus,jqXHR){
		    	if(data.code != '400'){
		    		$('#totalCount').text(data.data.count);
		    	}else{
		    		layer.msg(data.msg,{icon: 2});
		    	}
		    },
		})
		$.ajax({
		    url:'/admin/controlPersonAlarm/alarmCounts.shtml',
		    type:'POST',
		    data:{},
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data,textStatus,jqXHR){
		    	if(data.code != '400'){
		    		for (var i=0;i<data.data.length;i++){
		    			var count = "";
		    			if(data.data[i].count == undefined || data.data[i].count == null){
		    				count = 0;
		    			}else{
		    				count = data.data[i].count;
		    			}
		    			var str = "<a href='javascript:void(0)' class='search_type' value='"+data.data[i].id+"' style='background-color:";
		    			if(data.data[i].itemValue == "" || data.data[i].itemValue == null){
		    				str += "#000000' onclick='searchLevel(this)'>"+count+"</a>"
		    			}else{
		    				str += data.data[i].itemValue+"' onclick='searchLevel(this)'>"+count+"</a>"
			    		}
		    			$('.m_today_early label').append(str);
		    		}
		    	}else{
		    		layer.msg(data.msg,{icon: 2});
		    	}
		    },
		})
		$(".js-example-placeholder-multiple").select2({
   		    placeholder: "请选择轨迹类型"
   		}); 
		$('#units').select2();
   		$('.sign').on('click',function(){
   			$.ajax({
    		    url:'/admin/controlPersonAlarm/receiveAlarm.shtml',
    		    type:'POST',
    		    data:{
    		        id:$(this).attr('val')
    		    },
    		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
    		    success:function(data,textStatus,jqXHR){
    		    	if(data.code == '200'){
    		    		layer.msg(data.msg,{icon: 1});
    		    		setTimeout(function(){
    		    			window.location.reload();
    		    		},1000)
    		    	}else{
    		    		layer.msg(data.msg,{icon: 2});
    		    	}
    		    },
    		})
   		})
   		if( $("select[name='dynamicTypeIdz']").find("option:selected").size()<=0 ){
   			$(".select2-search").show();
   		}else{
   			$(".select2-search").hide();
   		};
   		/* $('.search_type').on('click',function(){
   			
   		});  */
   		if( $("#searchform").size()>0 ){
   	    	$.Tipmsg.r=null;
   	    	$("#searchform").Validform({
   				tiptype:function(msg){ 
   					layer.msg(msg);
   				}, 
   				tipSweep:true,
   				callback:function(form){}
   			});	
   	    }
	})
	function searchLevel(obj){
		$obj = $(obj);
		$("input[name='todayAlarm']").attr('value',$obj.attr('value'));
		$('#searchform').submit();
	}
	
	function isPower(id,pid){
   		$.ajax({
		    url:'/admin/controlPersonAlarm/isPower.shtml',
		    type:'POST',
		    data:{
		        id:id,
		        pid:pid
		    },
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data,textStatus,jqXHR){
		    	if(data.code == '200'){
		    		window.location.href="/admin/controlPersonAlarm/alarmDetail.shtml?id="+id+"&pid="+pid;
		    	}else{
		    		layer.msg(data.msg,{icon: 2});
		    	}
		    },
		})
   	}
   	 
   	function findForm(){
   		$(".approve_select").hide();
    	$('#searchform').submit();
	};
    </script>
</body>
</html>