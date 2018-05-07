<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<%@taglib uri="/ruiec-functions" prefix="ruiez" %>
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
</head>
<body class="bodyCss">
        <jsp:include page="../common/header.jsp"></jsp:include> 
        <!---->
        <section>
        	<jsp:include page="../common/leftNav6.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                    <form action="/admin/monitor/list.shtml" method="get" id="searchform">
                     	<input type="hidden" name="personnelType" value="${controlPerson.personnelType}"/>
                        <div class="tit_menu_list">
                            <a href="/admin/common/main.shtml" class="m_menu_item nav_list">
                                <i class="iconfont"></i>
                                <em>首页</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item">
                                <em>人员管理</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="/admin/monitor/list.shtml" class="m_menu_item">
                                <em>布控人员列表</em>
                            </a>
                        </div>
                        <div class="mgt15 importantC">
                            <div id="rylbCount">
                                <div class="rule-single-select single-select">
                                    <select name="sex" style="display: none;" onchange="findForm()">
                                    	<option value="">性别</option>
                                        <option value="男" <c:if test="${sex == '男'}">selected="selected"</c:if>>男</option>
                                        <option value="女" <c:if test="${sex == '女'}">selected="selected"</c:if>>女</option>
                                    </select>
                                </div>
                                <div class="rule-single-select single-select">
                                    <select name="status" style="display: none;" onchange="findForm()">
                                    	<option value="">审核状态</option>
                                        <option value="0" <c:if test="${status eq '0'}">selected="selected"</c:if>>未审核</option>
                                        <option value="1" <c:if test="${status eq '1'}">selected="selected"</c:if>>已审核</option>
                                        <option value="2" <c:if test="${status eq '2'}">selected="selected"</c:if>>未通过审核</option>
                                    	<option value="3" <c:if test="${status eq '3'}">selected="selected"</c:if>>初审</option>
                                    </select>
                                </div>
                                <div class="rule-single-select single-select">
                                    <select name="unitId" style="display: none;" onchange="findForm()">
                                    	<option value="">单位</option>
                                         <c:forEach var="list" items="${units}">
                                        	<option value="${list.id}" <c:if test="${unitId eq list.id}">selected="selected"</c:if>>${list.unitName}</option>
	                                    </c:forEach>
                                    </select>
                                </div>
                                <div class="searh_list">
                                    <dl class="search_item">
                                        <dt>姓名：</dt>
                                        <dd class="mgr30">
                                            <input type="text" name="name" class="text_inp" value="${name}" ignore="ignore"
                                            maxlength="20" datatype="/[\u4e00-\u9fa5·]$/" errormsg="请输入正确的姓名"/>
                                        </dd>
                                    </dl>
                                    <dl class="search_item">
                                        <dt>身份证号：</dt>
                                        <dd class="mgr30">
                                            <input type="text" name="idCard" class="text_inp" value="${idCard}" maxlength="18" ignore="ignore" errormsg="请输入正确的身份证号" onkeyup="idCardKeyUp($(this))" />
                                        </dd>
                                    </dl>
                                    <dl class="search_item">
                                        <dt>入库时间：</dt>
                                        <dd>
                                            <input name="startDate" class="Wdate text_inp " id="quote_time" autocomplete="off" type="text" 
                                                value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd HH:mm"/>"/>
                                            <em class="offDivide">至</em>
                                            <input name="endDate" class="Wdate text_inp" id="quote_time1" autocomplete="off" type="text" 
                                             value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd HH:mm"/>"/>
                                            <button class="btn curr fr mgl10" type="submit" value="搜索" id="searchformsubmit">搜索</button>
                                         	<button type="reset" class="btn fr mgl10 resetBtn" onClick="resetBtn()">重置</button>
                                        </dd>
                                    </dl>
                                </div>
                            </div>
                        </div> 

                        <div class="mgt15 importantC">
                            <div class="m_bg_ef Allcrumb">
                                <div class="fl crumb_item">
                                    <a href="javascript:void(0);" class="item_a" onclick="checkAll(this)">全选</a>
                                    <a href="javascript:void(0);" class="item_a" onclick="del()">删除</a> 
                                </div>
                                <div class="fr crumb_item">
                                    <a href="/admin/monitor/add.shtml" class="item_a">新建</a>
                                </div>
                            </div>
                            <div class="mgt15 importantNList">
                                <table class="tab tab_ellipsis">
                                    <colgroup>
                                        <col width="3%"/>
                                        <col width="4%"/>
                                        <col width="6%"/>
                                        <col width="4%"/>
                                        <col width="5%"/>
                                        <col width="8%"/>
                                        <col width="8%"/>
                                        <col width="7%"/>
                                        <col width="7%"/>
                                        <col width="7%"/>
                                        <col width="6%"/>
                                        <col width="8%"/>
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
                                            <th>布控时间</th>
                                            <th>剩余天数</th>
                                            <th>是否撤销</th>
                                            <th>审核状态</th>
                                            <th class="textl">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
	                                    <c:forEach var="list" items="${page.list}">
	                                        <tr>
	                                            <td style="text-align: center"><input type="checkbox" class="checkItem" name="ids" value="${list.id}"></td>
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
	                                            	${list.duration}
	                                            </td>
	                                            <td>
	                                            	${ruiez:leftDay(list.status,list.isRevoke,list.approvalTime,list.duration)}
	                                            </td>
	                                            <td>
	                                            	${list.isRevoke eq '1'?'是':list.isRevoke eq '0'?'否':'暂无' }
	                                            </td>
	                                            <td>${list.status eq '0'?'未审核':list.status eq '1'?'已审核':list.status eq '2'?'审核拒绝':'初审' }</td>
	                                            <td>
	                                            	<c:if test="${list.status eq '1' and list.isRevoke eq '0' and sessionScope.user.id eq list.proposer}">
	                                            		<a class="m_blue" onclick="unControl(${list.id})">撤控</a>
	                                            		<i class="m_e8">|</i>
	                                            		<a class="m_blue" onclick="conControl(${list.id})">续控</a>
	                                            		<i class="m_e8">|</i>
	                                            	</c:if>
	                                            	<c:if test="${list.status eq '1' and list.isRevoke eq '1' and sessionScope.user.id eq list.proposer}">
	                                            		<a class="m_blue" href="/admin/monitor/edit.shtml?id=${list.id}" <%-- onclick="reControl(${list.id})" --%>>重新布控</a>
	                                            		<i class="m_e8">|</i>
	                                            	</c:if>
	                                            	<c:if test="${list.status eq '2' and list.isRevoke eq '0' and sessionScope.user.id eq list.proposer}">
	                                            		<a class="m_blue" href="/admin/monitor/edit.shtml?id=${list.id}" <%-- onclick="reControl(${list.id})" --%>>重新布控</a>
	                                            		<i class="m_e8">|</i>
	                                            	</c:if>
	                                                <a href="/admin/monitor/look.shtml?typeId=0&id=${list.id}" class="m_blue">查看</a>
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
	<div class="hideThis pd25_40 lay_wind" id="lay_wind1">
        <div class="lay_form">
            <form id="form1" method="post" action="/admin/monitor/conControl.shtml">
            	<input name="id" type="hidden">
                <div class="lay_form_wrap">
                    <dl class="lay_form_list">
                        <dt>续控天数：</dt>
                        <dd>
                            <div class="fl left_item">
                                <input type="text" isnot="true" acttype="edit" name="day" datatype="n1-2" class="txt_in " maxlength="2">
                            </div>
                        </dd>
                    </dl>
                </div>
                <div class="sub_btn_box">
                    <dl class="lay_form_list">
                        <dt>&nbsp;</dt>
                        <dd>
                            <input type="button" value="关闭" class="back_next">
                            <input type="submit" value="保存" class="sub_btn">
                        </dd>
                    </dl>
                </div>
            </form>
        </div>
    </div>
	<div class="hideThis pd25_40 lay_wind" id="lay_wind2">
        <div class="lay_form">
            <form id="form1" method="post" action="/admin/monitor/reControl.shtml">
            	<input name="id" type="hidden">
                <div class="lay_form_wrap">
                    <dl class="lay_form_list">
                        <dt style="width: 26%">重新布控天数：</dt>
                        <dd>
                            <div class="fl left_item">
                            	<select class="sel " name="day" id="duration" datatype="*">
                                    <option value="10">10天</option>
                                    <option value="20">20天</option>
                                    <option value="30">30天</option>
                                </select>
                            </div>
                        </dd>
                    </dl>
                </div>
                <div class="sub_btn_box">
                    <dl class="lay_form_list">
                        <dt>&nbsp;</dt>
                        <dd>
                            <input type="button" value="关闭" class="back_next">
                            <input type="submit" value="保存" class="sub_btn">
                        </dd>
                    </dl>
                </div>
            </form>
        </div>
    </div>
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
	function findForm(){
		$('#searchformsubmit').click();
	}
	function unControl(id){
		$.ajax({
			type:"POST",
			url:"/admin/monitor/unControl.shtml",
			data: {id:id},
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
	function conControl(id){
		$('#lay_wind1').find('input[name=id]').val(id);
		layer.open({
            type: 1,
            area: ['445px', '260px'], //宽高
            content: $('#lay_wind1'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
        });
	}
	/* function reControl(id){
		$('#lay_wind2').find('input[name=id]').val(id);
		layer.open({
            type: 1,
            area: ['470px', '260px'], //宽高
            content: $('#lay_wind2'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
        });
	} */
   	</script>
</body>
</html>