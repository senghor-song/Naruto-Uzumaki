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
</head>
<body class="bodyCss">
        <jsp:include page="../common/header.jsp"></jsp:include> 
        <!---->
        <section>
        	<jsp:include page="../common/leftNav.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                    <form action="/admin/controlPerson/list.shtml" method="get" id="searchform">
                     	<input type="hidden" name="personnelType" value="${controlPerson.personnelType}"/>
                        <div class="tit_menu_list">
                            <a href="/admin/common/main.shtml" class="m_menu_item nav_list">
                                <i class="iconfont"></i>
                                <em>首页</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item refresh_html">
                                <em>人员管理</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="/admin/controlPerson/list.shtml?personnelType=${controlPerson.personnelType}" class="m_menu_item">
                                <em>
                                	<c:if test="${controlPerson.personnelType eq '1'}">重点人员列表</c:if>
                                	<c:if test="${controlPerson.personnelType eq '2'}">关注人员列表</c:if>
                                	<c:if test="${controlPerson.personnelType eq '3'}">外地人员列表</c:if>
 								</em>
                            </a>
                        </div>
                        <div class="mgt15 importantC">
                            <div id="rylbCount">
                                <div class="rule-single-select single-select">
                                    <select name="sex" style="display: none;" onchange="findForm()">
                                    	<option value="">性别</option>
                                        <option value="男" <c:if test="${controlPerson.sex == '男'}">selected="selected"</c:if>>男</option>
                                        <option value="女" <c:if test="${controlPerson.sex == '女'}">selected="selected"</c:if>>女</option>
                                    </select>
                                </div>
                                <div class="rule-single-select single-select">
                                    <select name="isControl" style="display: none;" onchange="findForm()">
                                    	<option value="">在控状态</option>
	                                    <c:forEach var="list" items="${isControls}">
                                        	<option value="${list.id}" <c:if test="${controlPerson.isControl eq list.id}">selected="selected"</c:if>>${list.itemName}</option>
	                                    </c:forEach>
                                    </select>
                                </div>
                                <div class="rule-single-select single-select">
                                    <select name="columnTubeState" style="display: none;" onchange="findForm()">
                                    	<option value="">列管状态</option>
                                       	<option value="0" <c:if test="${controlPerson.columnTubeState eq 0}">selected="selected"</c:if>>撤管</option>
                                    	<option value="1" <c:if test="${controlPerson.columnTubeState eq 1}">selected="selected"</c:if>>列管</option>
                                    </select>
                                </div>
                                <div class="rule-single-select single-select">
                                    <select name="isAuditKeyPersonColumnTub" style="display: none;" onchange="findForm()">
                                    	<option value="">审核状态</option>
                                        <option value="1" <c:if test="${controlPerson.isAuditKeyPersonColumnTub eq '1'}">selected="selected"</c:if>>待审核</option>
                                        <option value="2" <c:if test="${controlPerson.isAuditKeyPersonColumnTub eq '2'}">selected="selected"</c:if>>已审核</option>
                                        <option value="3" <c:if test="${controlPerson.isAuditKeyPersonColumnTub eq '3'}">selected="selected"</c:if>>未通过审核</option>
                                    </select>
                                </div>
                                <div class="rule-single-select single-select">
                                    <select name="dangerousLevel" style="display: none;" onchange="findForm()">
                                    	<option value="">危险级别</option>
                                    	<c:forEach var="list" items="${dangerousLevels}">
                                        	<option value="${list.id}" <c:if test="${controlPerson.dangerousLevel eq list.id}">selected="selected"</c:if>>${list.itemName}</option>
	                                    </c:forEach>
                                    </select>
                                </div>
                                <div class="rule-single-select single-select">
                                    <select name="personTypeId" style="display: none;" onchange="findForm()">
                                    	<option value="">人员类别</option>
                                        <c:forEach var="list" items="${dataitemIds}">
                                        	<option value="${list.id}" <c:if test="${controlPersonDTO.personTypeId eq list.id}">selected="selected"</c:if>>${list.itemName}</option>
	                                    </c:forEach>
                                    </select>
                                </div>
                                <c:if test="${!empty sessionScope.loginUserUnit.unitIds}">
	                                <div class="rule-single-select single-select">
	                                    <select name="areaUnitId" style="display: none;" onchange="findForm()">
	                                    	<option value="">单位</option>
	                                         <c:forEach var="list" items="${units}">
	                                        	<option value="${list.id}" <c:if test="${controlPersonDTO.areaUnitId eq list.id}">selected="selected"</c:if>>${list.unitName}</option>
		                                    </c:forEach>
	                                    </select>
	                                </div>
                                </c:if>
                                <div class="rule-single-select single-select">
                                    <select name="outType" style="display: none;" onchange="findForm()">
                                        <option value="">下发状态</option>
                                        <option value="1" <c:if test="${controlPersonDTO.outType == 1 }">selected="selected"</c:if>>未下发</option>
                                        <option value="2" <c:if test="${controlPersonDTO.outType == 2 }">selected="selected"</c:if>>已下发</option>
                                        <option value="3" <c:if test="${controlPersonDTO.outType == 3 }">selected="selected"</c:if>>已下发未完善</option>
                                        <option value="4" <c:if test="${controlPersonDTO.outType == 4 }">selected="selected"</c:if>>已下发已完善</option>
                                    </select>
                                </div>

                                <div class="searh_list">
                                    <dl class="search_item">
                                        <dt>姓名：</dt>
                                        <dd class="mgr30">
                                            <input type="text" name="name" class="text_inp" value="${controlPerson.name}" ignore="ignore"
                                            maxlength="20" datatype="/[\u4e00-\u9fa5·]$/" errormsg="请输入正确的姓名"/>
                                        </dd>
                                    </dl>
                                    <dl class="search_item">
                                        <dt>身份证号：</dt>
                                        <dd class="mgr30">
                                            <input type="text" name="idCard" class="text_inp" value="${controlPerson.idCard}" maxlength="18" ignore="ignore" errormsg="请输入正确的身份证号" onkeyup="idCardKeyUp($(this))" />
                                        </dd>
                                    </dl>
                                    <dl class="search_item">
                                        <dt>登记时间：</dt>
                                        <dd>
                                            <input name="startDate" class="Wdate text_inp " id="quote_time" autocomplete="off" type="text" 
                                                value="<fmt:formatDate value="${controlPersonDTO.startDate}" pattern="yyyy-MM-dd HH:mm"/>"/>
                                            <em class="offDivide">至</em>
                                            <input name="endDate" class="Wdate text_inp" id="quote_time1" autocomplete="off" type="text" 
                                             value="<fmt:formatDate value="${controlPersonDTO.endDate}" pattern="yyyy-MM-dd HH:mm"/>"/>
                                            <button class="btn curr fr mgl10" type="submit" value="搜索" id="searchformsubmit">搜索</button>
                                         	<button type="reset" class="btn fr mgl10 resetBtn" onClick="resetBtn()">重置</button>
                                        </dd>
                                    </dl>
                                </div>
                            </div>
                        </div>

                        <div class="mgt15 importantN">
                            <div class="m_bg_ef Allcrumb">
                                <div class="fl crumb_item">
                                    <a href="javascript:void(0);" class="item_a" onclick="checkAll(this)">全选</a>
                                    <a href="javascript:void(0);" class="item_a" onclick="del()">删除</a> 
                                </div>
                                <span class="fl key_Personnel">
                                	<c:if test="${controlPerson.personnelType eq '1'}">当前重点人员数量：<i class="m_blue">${page.totalCount }</i>人</c:if>
	                             	<c:if test="${controlPerson.personnelType eq '2'}">当前关注人员数量：<i class="m_blue">${page.totalCount }</i>人</c:if>
	                             	<c:if test="${controlPerson.personnelType eq '3'}">当前外地人员数量：<i class="m_blue">${page.totalCount }</i>人</c:if> 
                                </span>
                                <div class="fr crumb_item">
                                    <a href="javascript:void(0);" class="item_a" onclick="openAdd()">导入</a>
                                    <a href="/admin/controlPerson/add.shtml?personnelType=${controlPerson.personnelType}" class="item_a">新建</a>
                                    <a at="/admin/controlPerson/columnTubeState.shtml?personnelType=${controlPerson.personnelType}?state=1" class="item_a Evacuated" onclick="Evacuated('Evacuated')">撤管</a>
                                    <a at="/admin/controlPerson/columnTubeState.shtml?personnelType=${controlPerson.personnelType}?state=2" class="item_a Shell" onclick="Evacuated('Shell')">列管</a>
                                </div>
                            </div>
                            <div class="mgt15 importantNList">
                                <table class="tab tab_ellipsis">
                                    <colgroup>
                                        <col width="3%"/>
                                        <col width="4%"/>
                                        <col width="6%"/>
                                        <col width="4%"/>
                                        <col width="10%"/>
                                        <col width="12%"/>
                                        <col width="8%"/>
                                        <col width="8%"/>
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
                                            <th>人员类别</th>
                                            <th>身份证号</th>
                                            <th>管控责任单位</th>
                                            <th>入库时间</th>
                                            <th>责任民警</th>
                                            <th>在控状态</th>
                                            <th>审核状态</th>
                                            <th class="textl">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
	                                    <c:forEach var="list" items="${page.list}">
	                                        <tr>
	                                            <td style="text-align: center"><input type="checkbox" class="checkItem" name="ids" value="${list.id}"></td>
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
	                                            	<c:if test="${empty list.controlPersonTypes}">无</c:if>
	                                            	<em class="tab_detail" ><c:forEach var="dict" items="${list.controlPersonTypes}" varStatus="l"><ruiec:dictionary var="dictionary" dictionaryId="${dict.dictionaryId}">${dictionary == null?'无':dictionary.itemName}<c:if test="${!l.last}">,</c:if></ruiec:dictionary></c:forEach></em>
	                                             	<div class="hideThis ellipsis_dateil"><c:forEach var="dict" items="${list.controlPersonTypes}" varStatus="l"><ruiec:dictionary var="dictionary" dictionaryId="${dict.dictionaryId}">${dictionary == null?'无':dictionary.itemName}<c:if test="${!l.last}">,</c:if></ruiec:dictionary></c:forEach></div>
                                             	</td>
	                                            <td>
		                                            <em class="tab_detail" >${list.idCard}</em>
		                                            <div class="hideThis ellipsis_dateil">${list.idCard}</div>
	                                            </td>
	                                            <td>
		                                            <em class="tab_detail" >
		                                           	<ruiec:unit var="unit" unitId="${list.unitId}">${unit == null?'无':unit.unitName}</ruiec:unit>
		                                            </em>
		                                            <div class="hideThis ellipsis_dateil">
		                                           	<ruiec:unit var="unit" unitId="${list.unitId}">${unit == null?'无':unit.unitName}</ruiec:unit>
													</div>
	                                            </td>
	                                            <td>
		                                            <em class="tab_detail" ><fmt:formatDate value="${list.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></em>
		                                            <div class="hideThis ellipsis_dateil"><fmt:formatDate value="${list.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
	                                            </td>
	                                            <td>
		                                           	<ruiec:user var="police" userId="${list.userId}">${police == null?'无':police.policeName}</ruiec:user>
	                                            </td>
	                                            <td>
	                                            	<ruiec:dictionary var="personType" dictionaryId="${list.isControl}">${personType == null?'无':personType.itemName}</ruiec:dictionary>
	                                            </td>
	                                            <td>${list.isAuditKeyPersonColumnTub eq '1'?'待审核':list.isAuditKeyPersonColumnTub eq '2'?'已审核':'未通过审核' }</td>
	                                            <td>
	                                                <a href="/admin/controlPerson/edit.shtml?id=${list.id}&personnelType=${list.personnelType}" class="m_blue">编辑</a>
	                                                <i class="m_e8">|</i>
	                                                <a href="/admin/controlPerson/look.shtml?id=${list.id}&personnelType=${list.personnelType}&viewType=0" class="m_blue">查看</a>
	                                                <i class="m_e8">|</i>
	                                                <a href="/admin/controlPersonRelation/list.shtml?controlPersonId=${list.id}&personnelType=${list.personnelType}" class="m_blue">关系</a>
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
                    </form><%-- 
                    <!-- 上传功能 -->
                    <form action="/admin/controlPerson/toLeadExcel.shtml" method="post" enctype="multipart/form-data" id="toLeadExcel">
                     	<input type="hidden" name="personnelType" id="personnelType" value="${controlPerson.personnelType}"/>
                    	<input type="file" name="execlFile" id="execlFileUrl">
                    	<input class="item_a" type="submit" value="提交">
                    </form> --%>
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

    function openAdd(){
    	layer.open({
		    type: 2,
		    title: '导入重点人表格', //不显示标题
		    area: ['500px', '300px'], //宽高
		    closeBtn:0,
		    content: "/admin/controlPerson/goToLeadExcel.shtml?personnelType="+${controlPerson.personnelType},  //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
		    success: function(){
	            $(document).on('keydown', function(e){  //document为当前元素，限制范围，如果不限制的话会一直有事件
	                if(e.keyCode == 13){
	        		    return false;
	                }
	            })
		    } 
		});  
   	}
	function findForm(){
		$('#searchformsubmit').click();
	}
   	</script>
</body>
</html>