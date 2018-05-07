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
        	<jsp:include page="../common/leftNav5.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                    <form>
                        <div class="tit_menu_list">
                            <a href="/admin/common/main.shtml" class="m_menu_item">
                                <i class="iconfont">&#xe62a;</i>
                                <em>首页</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item refresh_html">
                                <em>权限管理</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="/admin/unit/list.shtml" class="m_menu_item">
                                <em>单位管理</em>
                            </a>
                        </div>

                        <div class="mgt15 list_box">
                        	<div class="m_bg_ef Allcrumb">
                                <div class="fr crumb_item">
                                    <a class="item_a" id="handInit">手动刷新</a>
                                    <a href="javascript:void(0);" class="item_a"  onclick="openAdd('')">新建</a>
                               		<a href="javascript:void(0);" class="item_a" onclick="openImport()">导入</a>
                                </div>
                            </div>
                            <div class="mgt15 tab_box_list danweizidian">
                                <div class="tab_change">
                                    <table class="tab table">
                                        <colgroup>
                                            <col width="43%" />
                                            <col width="15%" />
                                            <col width="20%" />
                                            <col width="22%" />
                                        </colgroup>
                                        <thead>
                                            <tr>
                                                <th style="text-align:left;padding-left:30px">部门名称</th>
                                                <th align="left">单位编码</th>
                                                <th align="left">关联警种</th>
                                                <th align="left" class="pdl10">操作</th>
                                            </tr>
                                        </thead>
                                        <tbody id="jinzhonglist">
                                       		<!-- 第一级 -->
											<c:forEach var="list" items="${areaList}">
	                                            <c:if test="${list.statis>0}">
		                                            <tr class="danwei_list folder" pid="0" unitId="${list.id}" level="3">
		                                                <td align="left" class="relation_jinzhong1" style="text-align:left;padding-left:65px">
		                                                    <a href="javascript:void(0)" data="">${list.unitName}</a>
		                                                </td> 
		                                                <td align="left">${list.unitCode}</td>
		                                                <td align="left">${list.polices}</td>
		                                                <td align="left">
		                                                    <a href="javascript:void(0);" class="m_blue" onclick="openLinkPolice(${list.id})">关联警种</a>
		                                                    <a href="javascript:void(0);" class="m_blue" onclick="openEdit(${list.id})">编辑</a> 
		                                                    <a href="javascript:void(0);" class="m_blue" onclick="openAdd(${list.id})">添加</a>
		                                                    <a href="javascript:void(0);" class="m_blue" onclick="deleteUnit(${list.id},this)">删除</a>
		                       			                </td>
		                                            </tr>
	                                            </c:if>
                                            </c:forEach>
                                            <c:forEach var="list" items="${areaList}">
	                                            <c:if test="${list.statis==0}">
		                                            <tr class="no_danwei_list" pid="0" unitId="${list.id}" level="3">
		                                                <td align="left" class="relation_jinzhong1" style="text-align:left;padding-left:65px">
		                                                    <a href="javascript:void(0)" data="">${list.unitName}</a>
		                                                </td> 
		                                                <td align="left">${list.unitCode}</td>
		                                                <td align="left">${list.polices}</td>
		                                                <td align="left">
		                                                    <a href="javascript:void(0);" class="m_blue" onclick="openLinkPolice(${list.id})">关联警种</a>
		                                                    <a href="javascript:void(0);" class="m_blue" onclick="openEdit(${list.id})">编辑</a> 
			                                                <a href="javascript:void(0);" class="m_blue" onclick="openAdd(${list.id})">添加</a>
		                                                    <a href="javascript:void(0);" class="m_blue" onclick="deleteUnit(${list.id},this)">删除</a>
		                       			                </td>
		                                            </tr>
	                                            </c:if>
                                            </c:forEach>
                                        </tbody>
                                    </table>
		                            <c:if test="${empty areaList}">
			                            <div class="tc no_data_img">
					                    	<img src="${base }/resources/admin/img/no_data_img.png" class="no_img_data">
					                    	<p class="mgt10 nodata_text">~暂时没有数据呢~</p>
					                    </div>
		                            </c:if>
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
    <!--自写-->
    <script src="${base }/resources/admin/js/layout.js"></script> 
    <!---下拉框 -->
    <script src="${base }/resources/admin/js/common.js"></script> 
    <script type="text/javascript">
    function openEdit(id){
    	layer.open({
		    type: 2,
		    title: '编辑', //不显示标题
		    area: ['500px', '330px'], //宽高
		    content: "/admin/unit/edit.shtml?parentId="+id, 
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
    function openAdd(id){
    	layer.open({
		    type: 2,
		    title: '添加', //不显示标题
		    area: ['500px', '330px'], //宽高
		    content: "/admin/unit/add.shtml?parentId="+id,  //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
		    success: function(){
	            $(document).on('keydown', function(e){  //document为当前元素，限制范围，如果不限制的话会一直有事件
	                if(e.keyCode == 13){
	        		    return false;
	                }
	            })
		    } 
		});  
   	}
    function openLinkPolice(id){
    	layer.open({
		    type: 2,
		    title: '关联警种', //不显示标题
		    area: ['660px', '470px'], //宽高
		    content: "/admin/unit/linkPolice.shtml?id="+id,  //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
		    success: function(){
	            $(document).on('keydown', function(e){  //document为当前元素，限制范围，如果不限制的话会一直有事件
	                if(e.keyCode == 13){
	        		    return false;
	                }
	            })
		    } 
		});  
   	}
    function deleteUnit(id,del){
    	$.ajax({
			type:"POST",
			url:"/admin/unit/delete.shtml",
			data: {id:id},
			datatype:"json",
			success:function(data){ 
				if( data.code == '200' ){ 
					layer.msg(data.msg, { icon: 1, shade: 0.5 ,time :1000 });
					if( data.url !="" ){
						setTimeout(function(){
							$(del).parent().parent().remove();
							/* window.location.href = data.url; */
						},1000);
					} 
				}else{
					layer.msg(data.msg, { icon: 2, time: 2000 });
				}
			}
   		})
   	}
	$(function(){
		$("#handInit").click(function () {
    		var index = layer.msg('刷新中', { icon: 16 , shade: 0.1 , time: false });
			$.ajax({
    			url:'/admin/init/initRedis.shtml',
    			type:"post",
    			data:null,
    			async:true,
    			dataType: "json",
    			success: function(data) {
    				layer.msg(data.msg, { icon: 1});
    				layer.close(index);
    			},
    			error : function(data) {  
    				layer.msg("手动刷新失败", { icon: 2});  
    				layer.close(index);
    		    } 
    		});
		});
		//收缩树
		function slideUpJzdanwei(_theId){
			if($("#jinzhonglist tr[pid='"+_theId+"']").length>0){
			$("#jinzhonglist tr[pid='"+_theId+"']").each(function(){
				var _has_Child = $(this).attr("child"); //是否有子集
				if(typeof _has_Child != "undefined"){
					var _theId = $(this).attr("unitId") || "";
					if(typeof _theId != "undefined" && _theId!=""){
						slideUpJzdanwei(_theId);
					}else{
						$(this).slideUp(); //收缩
					}
				}else{
					$(this).slideUp(); //收缩
				}
			});  
			}else{
			 $("#jinzhonglist tr[unitId='"+_theId+"']").slideUp();
			} 
		} 

	  //展开树
	  function showJzdanwei(_theId){
		   if($("#jinzhonglist tr[pid='"+_theId+"']").length>0){
				$("#jinzhonglist tr[pid='"+_theId+"']").each(function(){
					var _has_Child = $(this).attr("child"); //是否有子集
					if(_has_Child){
						var _theId = $(this).attr("unitId") || "";
						if(typeof _theId != "undefined" && _theId!=""){
							showJzdanwei(_theId);
						}else{
							$(this).slideDown();
						}
					}else{
						$(this).slideDown();
					}
				});  
		   }else{
			  $("#jinzhonglist tr[unitId='"+_theId+"']").slideDown();
		   } 
	  } 

	  //管控单位列表
	  $("#jinzhonglist").on("click",".danwei_list .relation_jinzhong1",function(){
		  	var _this = $(this).parent(".danwei_list"); 
			var _theId = _this.attr("unitId") || "";
			var _level = parseInt(_this.attr("level")) || "";
			_level=_level+1;
			var _isPolice = _this.attr("isPolice") || "";
			//var _this = $(this); 
			var _loaded = _this.attr("loaded");
			var _Indent = Number(_this.attr("indent")) || 20;
			if(typeof _loaded=="undefined"){
				//未加载
				if(_theId!=""){
					var _Api = "/admin/unit/getNextUnit.shtml?id="+_theId;
					$.ajax({
						type:"post",
						url:_Api,
						data:{},
						dataType:"json",
						success:function(data){
							if(typeof data != "undefined" && data.data && data.data.length>0){ 
								var _TtStr = ""; 
								_Indent += 20;
								var townList=data.data;
								 _level=_level+1;
								for(var i= 0; i < townList.length; i++){ 
									/* if(townList[i].sumDw > 1){
										var _theTr = "<tr indent='"+_Indent+"' class='danwei_list folder child_"+_Indent+"' pid='"+_theId+"' unitId='"+townList[i].unitCode+"' child='true'>";isPolice
									}else{ */
										var _theTr = "<tr indent='"+_Indent+"' class='danwei_list folder  child_41' pid='"+_theId+"' unitId='"+townList[i].id+"' level='"+_level+"'>";
									/* } */
										// _theTr += '<td align="left" class="relation_jinzhong1" style="text-align:left;padding-left:35px;text-indent:'+_Indent+'px;"><a href="javascript:void(0)" data="'+townList[i].unitCode+'" >'+townList[i].name+'</a></td>';
										 _theTr += '<td align="left" class="relation_jinzhong1" style="text-align:left;padding-left:25px;text-indent:'+_Indent+'px;"><a href="javascript:void(0)" data="'+townList[i].unitCode+'" >'+townList[i].name+'</a></td>';
										 /* if(townList[i].id){
											 var jz_text = getJinzhongTxt(townList[i].id);
												_theTr += '<td align="center" >'+jz_text+'</td>';
										 }else{
											 _theTr += '<td align="center" >&nbsp;</td>';
										 }  */
										 _theTr += '<td align="center" style="text-align:left;">'+townList[i].unitCode+'</td>';
										 _theTr += '<td align="center" style="text-align:left;">'+townList[i].polices+'</td>';
										 _theTr += '<td align="left">';
										 _theTr += '	<a href="javascript:void(0);" class="m_blue" onclick="openLinkPolice('+townList[i].id+')">关联警种</a>'; 
										 _theTr += '	<a href="javascript:void(0);" class="m_blue" onclick="openEdit('+townList[i].id+')">编辑</a>'; /* 
										 _theTr += '	<a href="javascript:void(0);" class="m_blue" onclick="openAdd('+townList[i].id+','+_level+')">添加</a>';  */
										 _theTr += '	<a href="javascript:void(0);" class="m_blue" onclick="deleteUnit('+townList[i].id+',this)">删除</a>'; 
										 _theTr += '</td>';
										_theTr += "</tr>";
									_TtStr += _theTr;
								} 
								_this.attr("loaded","true"); 
								_this.addClass("open"); 
								_this.after($(_TtStr)); 
							}
						} 
					})
				}else{
					_this.attr("loaded","true");  
				}
			}else{
				//已加载 
				if(_this.hasClass("open")){
					//如果是展开
					//收缩下面子集
					slideUpJzdanwei(_theId);
					_this.removeClass("open");
				}else{
					//展开下面的子集
					showJzdanwei(_theId);
					_this.addClass("open");
				}
			}  
	  })
	});
	function openImport(){
    	layer.open({
		    type: 2,
		    title: '导入单位', //不显示标题
		    area: ['500px', '300px'], //宽高
		    closeBtn:0,
		    content: "/admin/unit/goToLeadExcel.shtml",  //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
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