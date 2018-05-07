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
        	<jsp:include page="../common/leftNav.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                    <form action="/admin/controlPersonRelation/list.shtml?controlPersonId=${ controlPersonId }&personnelType=${personnelType}" method="get">
                        <div class="tit_menu_list importantC">
							<a href="/admin/common/main.shtml" class="m_menu_item"> <i
								class="iconfont"></i> <em>首页</em>
							</a> <i class="fl split_gt">&gt;</i> <a href="javascript:;"
								class="m_menu_item"> <em>人员管理</em>
							</a>
							<c:if test="${personnelType eq '1'}">
								<i class="fl split_gt">&gt;</i>
								<a href="/admin/controlPerson/list.shtml?personnelType=${personnelType}"
									class="m_menu_item"> <em>重点人员列表</em>
								</a>
							</c:if>
							<c:if test="${personnelType eq '2'}">
								<i class="fl split_gt">&gt;</i>
								<a href="/admin/controlPerson/list.shtml?personnelType=${personnelType}"
									class="m_menu_item"> <em>关注人员列表</em>
								</a>
							</c:if>
							<c:if test="${personnelType eq '3'}">
								<i class="fl split_gt">&gt;</i>
								<a href="/admin/controlPerson/list.shtml?personnelType=${personnelType}"
									class="m_menu_item"> <em>外地人员列表</em>
								</a>
							</c:if>
							<i class="fl split_gt">&gt;</i> 
							<a href="/admin/controlPersonRelation/list.shtml?controlPersonId=${controlPersonId}&personnelType=${personnelType}" class="m_menu_item"> <em>
								<c:if test="${personnelType eq '1'}">重点人员关系列表</c:if> 
								<c:if test="${personnelType eq '2'}">关注人员关系列表</c:if> 
								<c:if test="${personnelType eq '3'}">外地人员关系列表</c:if>
							</em>
							</a>
	                        <span class="fr">
	                   	    <input type="button" onclick="javascript:history.back(-1);" class="fr back_btn" value="返回上一页"/>
	                        </span>
						</div>
                        <div class="mgt20 importantN">
                            <div class="m_bg_ef Allcrumb">
                                <div class="fl crumb_item">
                                    <a href="javascript:void(0);" class="item_a" onclick="checkAll(this)">全选</a>
                                    <a href="javascript:void(0);" class="item_a" onclick="del()">删除</a>
                                </div>
                                <div class="fr crumb_item">
                                    <a onclick="openAdd(${controlPersonId})" class="item_a">新建</a>
                                </div>
                            </div>
                            <div class="mgt15 importantNList">
                                <table class="tab tab_ellipsis">
                                    <colgroup>
                                        <col width="3%"/>
                                        <col width="10%"/>
                                        <col width="10%"/>
                                        <col width="10%"/>
                                        <col width="10%"/>
                                        <col width="20%"/>
                                        <col width="5%"/>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>&nbsp;</th>
                                            <th>关系</th>
                                            <th>姓名</th>
                                            <th>手机号码 </th>
                                            <th>身份证号码</th>
                                            <th>备注信息</th>
                                            <th class="textl">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
	                                    <c:forEach var="list" items="${page.list}">
	                                        <tr>
	                                            <td style="text-align: center"><input type="checkbox" class="checkItem" name="ids" value="${list.id }"></td>
	                                            <td class="textl">
	                                            	<ruiec:dictionary var="dictionary" dictionaryId="${list.typeId }">${dictionary == null?'无':dictionary.itemName}</ruiec:dictionary>
	                                            </td>
	                                            <td>
		                                            <em class="tab_detail" >${list.name }</em>
		                                            <div class="hideThis ellipsis_dateil">${list.name }</div>
	                                            </td>
	                                            <td>${list.phone }</td>
	                                            <td>${list.idCard }</td>
	                                            <td>
		                                            <em class="tab_detail" >${list.remark }</em>
		                                            <div class="hideThis ellipsis_dateil">${list.remark }</div>
		                                        </td>
	                                            <td>
	                                                <a class="m_blue" onclick="openEdit(${list.id})">编辑</a>
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
	    function openEdit(id){
	    	layer.open({
			    type: 2,
			    title: '编辑', //不显示标题
			    area: ['500px', '400px'], //宽高
			    content: "/admin/controlPersonRelation/edit.shtml?id="+id, 
			    //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
			    success: function(){
		            $(document).on('keydown', function(e){  //document为当前元素，限制范围，如果不限制的话会一直有事件
		                if(e.keyCode == 13){
		        		    return false;
		                }
		            })
			    } 
			});  
	   	}
	    function openAdd(controlPersonId){
	    	layer.open({
			    type: 2,
			    title: '添加', //不显示标题
			    area: ['500px', '400px'], //宽高
			    content: "/admin/controlPersonRelation/add.shtml?controlPersonId="+controlPersonId,  //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
			    success: function(){
		            $(document).on('keydown', function(e){  //document为当前元素，限制范围，如果不限制的话会一直有事件
		                if(e.keyCode == 13){
		        		    return false;
		                }
		            })
			    }   
			});  
	   	}
   	</script>
</body>
</html>