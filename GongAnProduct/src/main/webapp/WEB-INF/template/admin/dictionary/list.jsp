<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<title><ruiec:title/></title>
    <link rel="stylesheet" href="${base }/resources/admin/css/layer.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/common.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/style.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/index.css" />
	<link rel="stylesheet" href="${base }/resources/admin/css/quote.css" />
    <link href="${base }/resources/admin/js/tooltips/tooltips.css" rel="stylesheet" id="skin">
</head>
<body class="bodyCss">
        <!--头部-->
        <jsp:include page="../common/header.jsp"></jsp:include> 
        <!---->
        <section>
        	<jsp:include page="../common/leftNav5.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                    <form action="/admin/dictionary/list.shtml" method="get">
                    <input type="hidden" name="dataItemId" value="${dataItemId }"/> 
                        <div class="tit_menu_list">
                            <a href="/admin/common/main.shtml" class="m_menu_item">
                                <i class="iconfont">&#xe62a;</i>
                                <em>首页</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item refresh_html">
                                <em>字典库管理</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item refresh_html">
                                <em>字典数据列表</em>
                            </a>
                        </div>
                        <div class="mgt15 table-container">
                            <div class="fl layui-tree">
                                <ul>
                                <c:forEach var="dictionaryType" items="${dictionaryTypes}">
									<li <c:if test="${dictionaryType.id==dataItemId }">class="active"</c:if>>
										<a href="/admin/dictionary/list.shtml?dataItemId=${dictionaryType.id}&&itemCode=${dictionaryType.itemCode}"
										class="m_c5 selected">${dictionaryType.itemName}</a>
									</li>
								</c:forEach>
                                </ul>
                            </div>
                            <div class="fr importantN importantC">
                                <div class="m_bg_ef Allcrumb">
                                    <div class="fl crumb_item">
                                        <a href="javascript:void(0);" class="item_a" onclick="checkAll(this)">全选</a>
                                    	<a href="javascript:void(0);" class="item_a" onclick="del()">删除</a>
                                    </div>
                                    <div class="fr crumb_item">
	                                    <c:if test="${itemCode eq 'personClass'}">
	                                    	<a href="javascript:void(0);" class="item_a" onclick="openAdd()">导入</a>
										</c:if>
                                        <a href="javascript:;" onclick="add(${dataItemId})" class="item_a">新建</a>
                                    </div>
                                </div>
                                <div class="mgt15 importantNList">
                                    <table class="tab dictionary_tab tab_ellipsis">
                                        <colgroup>
                                            <col width="9%"/>
                                            <col width="15%"/> 
                                            <col width="10%"/>
                                            <col width="7%"/>
                                            <%-- <col width="8%"/> --%>
                                            <col width="10%"/>
                                            <col width="15%"/>
                                            <col width="16%"/>
                                            <%-- <col width="12%"/> --%>
                                            <col width="10%"/>
                                        </colgroup>
                                        <thead>
                                        <tr>
                                            <th>&nbsp;</th>
                                            <th>字典名称</th> 
                                            <th>字典值</th> 
                                            <th>字典排序</th>
                                            <!-- <th>字典描述</th> -->
                                            <th>能否添加下级</th>
                                            <th>父级字典名称</th>
                                            <th>修改时间</th>
                                            <!-- <th>所属字典类型</th> -->
                                            <th class="textl">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="dictionaryList">
                                        <c:forEach var="list" items="${page.list}">
                                            <tr unitId="${list.id }" parId="${list.id }" state="${list.state}"
                                             <c:if test="${list.state==1}">class="dictionary_list"</c:if>
                                             <c:if test="${list.state==0}">class="no_dictionary_list"</c:if> leval="0">
                                                <td class="pdl15">
                                                    <input type="checkbox" class="checkItem" name="ids" value="${list.id }">
                                                    <i class="folder_star"></i> 
                                                </td> 
                                                <td>${list.name}</td> 
                                                <c:if test="${itemCode ne 'warningLevel'}">
                                                	<td>${list.itemValue==null?'暂无':list.itemValue}</td>
                                                </c:if>
                                                <c:if test="${itemCode eq 'warningLevel'}">
                                                	<td><div style="width: 20px;height: 20px;background:${list.itemValue}"></div></td>
                                                </c:if>
												<td>${list.sortCode}</td>
												<%-- <td>${list.description}</td> --%>
												<td><em class="m_blue" >${list.isDef==1?'是':'否'}</em></td>
												<td>无</td>
												<td>${list.modifyDate }</td>
												<%-- <td>${list.typeName}</td> --%>
                                                <td>
                                                	<c:if test="${list.isDef==1 }">
                                                    <a href="/admin/dictionary/add.shtml?id=${list.id }&&dataItemId=${dataItemId}&&level=1" class="m_df">添加下级</a>
                                                    <i class="m_e8">|</i>
                                                    </c:if>
                                                    <a href="/admin/dictionary/edit.shtml?id=${list.id }&&dataItemId=${dataItemId}&&level=0&&itemCode=${itemCode}" class="m_df">编辑</a>
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
		                        </div>
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
	<!--日期-->
	<script src="${base }/resources/admin/js/My97DatePicker/lang/zh-cn.js"></script>
	<script src="${base }/resources/admin/js/My97DatePicker/WdatePicker.js"></script>
	
    <script src="${base }/resources/admin/js/tooltips/bootstrap-tooltip.js"></script>
    <script src="${base }/resources/admin/js/tooltips/bootstrap-affix.js"></script>
    <script src="${base }/resources/admin/js/tooltips/application.js"></script>
    <script src="${base }/resources/admin/js/tooltips/jquery.pure.tooltips.js"></script> 
	<!---下拉框 -->
	<script src="${base }/resources/admin/js/common.js"></script>
	<script type="text/javascript">
	//批量删除
	function del(){
	    var $ids = $(".tab input[name='ids']:enabled:checked");
	    var m = '是否将此信息删除?';
	    var result = false;
	    if ($ids.size() < 1) {
	    	layer.msg('请选择要删除的选项');
	        return;
	    }
	    $.ajax({
            type:"get",
            url:"check.shtml",
            data:$ids.serialize(),
            async:false,
            dataType:"json",
            success:function(data) {  	 	 
            	if(data.code == 200){
            		result = true;
                	m='此数据含有下级数据，点击删除将同时删除下级数据，是否将此信息删除?';
            	}else if(data.code == 400){
                	layer.msg(data.msg,{icon: 2});
            	} else {
            		result = true;
            	}
            },
            error:function(data) {
            	layer.msg("删除失败",{icon: 2}); 
            }
       }); 
	   if(result){
		 	//询问框
		    layer.confirm(m, {btn: ['确认','取消']},function(index, layero){
		    	console.log(index, layero);
		        $.ajax({
		            type:"get",
		            url:"delete.shtml",
		            data:$ids.serialize(),
		            dataType:"json",
		            success:function(data) {  
		            	if(data.code == 200){  
		                	var $idsList = $ids.serialize().split("&ids=");  
		                	for( var j=0;j<$idsList.length;j++){
		                		if( $idsList[j] !="" && $idsList[j] != undefined && $idsList[j] !=null ){
		                			if( j == 0){
		                				var $idsO = $idsList[0].split("ids="); 
		                				if( $("#dictionaryList tr[unitid="+$idsO[1]+"]").size() >0 ){
		                					//判断是否存在子集
		                					var stare = $("#dictionaryList tr[unitid="+$idsO[1]+"]").attr("stare");
		                					var leval = $("#dictionaryList tr[unitid="+$idsO[1]+"]").attr("leval");
			                				if( stare == "true" ){
			                					if( leval == "0" ){
			                						$("#dictionaryList").find("tr[parid="+$idsO[1]+"]").remove();
			                					}else if( leval == "1" ){
			                						$("#dictionaryList").find("tr[unitid="+$idsO[1]+"]").remove();
			                						$("#dictionaryList").find("tr[pid="+$idsO[1]+"]").remove();
			                					}else{
			                						$("#dictionaryList").find("tr[unitid="+$idsO[1]+"]").remove();	
			                					};
			                				}else{
			                					$("#dictionaryList").find("tr[unitid="+$idsO[1]+"]").remove();
			                				}	
			                			} 
		                			}else{
		                				if( $("#dictionaryList tr[unitid="+$idsList[j]+"]").size() >0 ){
		                					//判断是否存在子集
		                					var stare = $("#dictionaryList tr[unitid="+$idsList[j]+"]").attr("stare"); 
		                					//判断层级
		                					var leval = $("#dictionaryList tr[unitid="+$idsList[j]+"]").attr("leval");
			                				if( stare == "true" ){
			                					if( leval == "0" ){
			                						$("#dictionaryList").find("tr[parid="+$idsList[j]+"]").remove();
			                					}else if( leval == "1" ){
			                						$("#dictionaryList").find("tr[unitid="+$idsList[j]+"]").remove();
			                						$("#dictionaryList").find("tr[pid="+$idsList[j]+"]").remove();
			                					}else{
			                						$("#dictionaryList").find("tr[unitid="+$idsList[j]+"]").remove();	
			                					};
			                				}else{
			                					$("#dictionaryList").find("tr[unitid="+$idsList[j]+"]").remove();
			                				}	 
		                					//console.log($("#dictionaryList tr[unitid="+$idsList[j]+"]"));
			                			} 
		                			}
		                			
		                		}
		                	} 
		                	layer.msg(data.msg,{icon:1})
		                	window.setTimeout(function(){location.reload();},500);
		            	}else if(data.code == 400){
		                	layer.msg(data.msg,{icon: 2}); 
		            	}
		            },
		            error:function(data) {
		            	layer.msg("删除失败",{icon: 2});  
		            } 
		       });
		    },function(index){});   
	   }
	    
	}
	
	function openAdd(){
    	layer.open({
		    type: 2,
		    title: '导入人员类别', //不显示标题
		    area: ['500px', '300px'], //宽高
		    closeBtn:0,
		    content: "/admin/dictionary/goToLeadExcel.shtml",  //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
		    success: function(){
	            $(document).on('keydown', function(e){  //document为当前元素，限制范围，如果不限制的话会一直有事件
	                if(e.keyCode == 13){
	        		    return false;
	                }
	            })
		    } 
		});  
   	}
	var itemCode = '${itemCode}'
	function add(dataItemId){
		if(dataItemId == ''|| dataItemId == null){
			layer.msg("请先添加字典类型")
		} else {
			window.location.href="/admin/dictionary/add.shtml?dataItemId="+dataItemId+"&&itemCode="+itemCode; 
		}
   	}
	</script>
</body>
</html>