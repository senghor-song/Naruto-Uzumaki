<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<title><ruiec:title/></title>
    <link rel="stylesheet" href="${base}/resources/admin/js/select/select2.css" />
    <link rel="stylesheet" href="${base}/resources/admin/css/layer.css" />
    <link rel="stylesheet" href="${base}/resources/admin/css/common.css" />
    <link rel="stylesheet" href="${base}/resources/admin/css/style.css" />
    <link rel="stylesheet" href="${base}/resources/admin/css/index.css" />
    <link rel="stylesheet" href="${base}/resources/admin/css/quote.css" /> 
    <style>
    	.select2-container{ z-index:99999999;}
    	/* .select2-container--default .select2-selection--single{ height:34px;} */
    	/* .select2-container--default .select2-selection--single .select2-selection__rendered{ line-height:34px;} */
    	.select2-container .select2-selection--single{ max-width:233px;}
    	.select2-container--default .select2-selection--single .select2-selection__arrow{ height:34px;}
    	.searh_list .select_box .select2-container{ float:left; z-index: 9999;}
    	.searh_list .select_box .select2-container--default .select2-selection--single{ height:30px;}
    	.searh_list .select_box .select2-container--default .select2-selection--single .select2-selection__rendered{ line-height:30px;}
    	/* .searh_list .select_box .select2-container--default .select2-selection--single .select2-selection__arrow{ height:30px;} */
   		.select2-container{ width:230px;}
    </style>
</head>
<body class="bodyCss" onload="getUnitTown(1,0);">
		<jsp:include page="../common/header.jsp"></jsp:include>
        <!---->
        <section>
        	<jsp:include page="../common/leftNav5.jsp"></jsp:include>
            <div class="mainRight">
                <div class="pancl_pd">
                    <form action="list.shtml" method="GET" onkeydown="if(event.keyCode==13){return false;}">
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
                            <a href="/admin/manager/list.shtml" class="m_menu_item">
                                <em>管理员管理</em>
                            </a>
                        </div>
                        <div class="searh_list">
                            <div class="fl select_box">
                            	<input type="hidden" value="${dto.unitId}" id="unitId"/>
                                <select class="js-example-basic-multiple" style="width: 150px" name="unitId" id="citys">
                                    <option value="">请选择</option>
                                </select>
                            </div>
                            <div class="fl mgl10 select_box">
                                <select class="js-example-basic-multiple1" style="width: 130px" name="type">
                                    <option value="1" <c:if test='${dto.type==1}'>selected="selected"</c:if>>身份证</option>
                                    <option value="2" <c:if test='${dto.type==2}'>selected="selected"</c:if>>姓名</option>
                                </select>
                            </div> 
                            <dl class="mgl20 search_item"> 
                                <dd>
                                    <input type="text" name="searchContent" class="text_inp" value="${dto.searchContent}"> 
                                </button></dd>
                            </dl>
                            <dl class="search_item">
                                <dt></dt>
                                <dd class=""> 
                                    <input type="submit" class="btn curr fr mgl10" value="搜索"/>
                                </dd>
                            </dl>

                        </div>
                        <div class="mgt20 importantC">
                            <div class="m_bg_ef Allcrumb">
                                <div class="fl crumb_item">
                                    <a href="javascript:void(0);" class="item_a" onclick="checkAll(this)">全选</a>
                                    <a href="javascript:void(0);" class="item_a" onclick="del()">删除</a>
                                </div>
                                <div class="fr crumb_item">
                                    <a class="item_a" id="handInit">手动刷新</a>
                                    <a href="javascript:void(0);" class="item_a" id="create_manager">新建</a>
                                </div>
                            </div>
                            <div class="mgt15 importantNList">
                                <table class="tab">
                                    <colgroup>
                                        <col width="3%"/>
                                        <col width="12%"/>
                                        <col width="12%"/>
                                        <col width="18%"/>
                                        <col width="22%"/>
                                        <col width="12%"/>
                                        <col width="12%"/>
                                        <col width="6%"/>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>&nbsp;</th>
                                            <th>姓名</th>
                                            <th>身份证号</th>
                                            <th>所属单位</th>
                                            <th>管理单位</th>
                                            <th>添加时间</th>
                                            <th>调整时间</th>
                                            <th class="textl">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody id="unit_redact_box">
	                                    <c:forEach var="l" items="${page.list}">
	                                        <tr>
	                                            <td style="text-align: center"><input type="checkbox" class="checkItem" name="ids" value="${l.id}"></td>
	                                            <td>${l.user.policeName}</td>
	                                            <td>${l.user.idCard}</td>
	                                            <td>
		                                           	<ruiec:unit var="unit" unitId="${l.user.unitId}">${unit == null?'无':unit.unitName}</ruiec:unit>
	                                            </td>
	                                            <td>${l.unit.unitName}</td>
	                                            <td><fmt:formatDate value="${l.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	                                            <td><fmt:formatDate value="${l.modifyDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	                                            <td>
	                                                <a href="#" class="m_blue unit_redact">编辑</a>
	                                                <input type="hidden" class="userId" value="${l.user.id}">
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
		<div class="hideThis pd15_25 lay_wind lay_wind6" id="lay_wind6">
        <div class="lay_form lay_in_210">
            <form  onkeydown="if(event.keyCode==13){return false;}">
                <div class="lay_form_wrap">
                    <dl class="lay_form_list">
                        <dt>姓名/身份证：</dt>
                        <dd>
                            <div class="fl left_item post_rela">
                                <input type="text" isnot="true" acttype="edit" autocomplete="off" name="sfzh" value="" datatype="*" userid="" 
                                unitid="" class="txt_in" id="search_all">
                            	<div class="search_list_info">
                                    <ul>
                                        <li>
                                            <span class="sear_name">贺云</span>
                                            <span class="sear_profe">研发部</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </dd>
                    </dl>
                    <dl class="lay_form_list">
                        <dt>管理单位：</dt>
                        <dd> 
                            <div class="select_box">
                            	<select name="exanime" id="manager_unit" style="width:230px;">
                                    <option value="">请选择</option>
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
    <script src="${base}/resources/admin/js/jquery-1.9.1.min.js"></script>
    <script src="${base}/resources/admin/js/layer.js"></script>
    <script src="${base}/resources/admin/js/Validform_v5.3.2/Validform_v5.3.2.js"></script>
    <!--自写-->
    <script src="${base}/resources/admin/js/layout.js"></script>
    <!--拖拽-->
    <script src="${base}/resources/admin/js/gridster/jquery.gridster.min.js"></script>
    <!---下拉框 -->
    <script src="${base}/resources/admin/js/common.js"></script> 
    <script src="${base}/resources/admin/js/select/select2.js"></script>
    <script type="text/javascript">
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
    		$(".js-example-basic-multiple").select2();	
    		$(".js-example-basic-multiple1").select2({
    			minimumResultsForSearch: -1, 
    		}); 
    		$("#create_manager").click(function () { 
    			var _html ='<option value="">请选择</option>';
   				$("#manager_unit").empty().append(_html); 
   				$("#manager_unit").select2();
        		
    			$("#lay_wind6 .redact_inp").attr("id","search_all"); 
    			$("#search_all").removeClass("redact_inp");
    			$("#search_all").attr("readonly",false);
    			$("#search_all").val("");
    			$("#manager_unit option").attr("selected",false);
    			$("#manager_unit").select2();
    	        layer.open({
    	            type: 1,
    	            title: '新增', //不显示标题
    	            area: ['445px', '400px'], //宽高
    	            content: $('#lay_wind6'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响 
    			    success: function(){
    		            $(document).on('keydown', function(e){  //document为当前元素，限制范围，如果不限制的话会一直有事件
    		                if(e.keyCode == 13){
    		        		    return false;
    		                }
    		            })
    			    } 
    	        });
    	    });
    		$("#search_all").keyup(function(e){
    			if($("#search_all").size()>0){
    				//var key = event.keyCode;
        			var theEvent = window.event || e;
					var key = theEvent.keyCode || theEvent.which;
    				var curIndex = $('.search_list_info li.cur').size() > 0 ? $('.search_list_info li.cur').index() : -1;
        			var strVar = "";  
                    var _thisVal = $(this).val(); 
                    if( _thisVal !="" && _thisVal !=null && _thisVal != undefined ){
                    	switch(key){
    	                	case 40:
    	                		if(curIndex == -1){
    	                            $('.search_list_info li').eq(0).addClass('cur');
    	                        }else if(curIndex < $('.search_list_info li').size() - 1){
    	                            $('.search_list_info li').eq(curIndex + 1).addClass('cur').siblings('li').removeClass('cur');
    	                        }else{
    	                            $('.search_list_info li').eq(0).addClass('cur').siblings('li').removeClass('cur');
    	                        } 
    	                        $(this).val($('.search_list_info li.cur .sear_name').text());
    	                		break;
    	                	case 38:
    	                		if(curIndex > 0){ 
    	                            $('.search_list_info li').eq(curIndex - 1).addClass('cur').siblings('li').removeClass('cur');
    	                        }else if(curIndex == -1){
    	                            $('.search_list_info li').eq(0).addClass('cur');
    	                        }else{
    	                            $('.search_list_info li').eq($('.search_list_info li').size() - 1).addClass('cur').siblings('li').removeClass('cur');
    	                        }  
    	                        $(this).val($('.search_list_info li.cur .sear_name').text());
    	                        
    	                		break;
    	                	case 13:
    	                		var _text = $('.search_list_info li.cur .sear_name').text();
    	                		var sear_profe = $('.search_list_info li.cur .sear_profe').text();
    	                		var _userId = $('.search_list_info li.cur').attr("userid");
    	                    	var unitid = $('.search_list_info li.cur').attr("unitid");
    	                		$(this).val(_text);
    	                		$(".search_list_info").hide();
    	                		$("#manager_unit option").attr("selected",false);
    	                		$("#manager_unit option").each(function(){
    	                			var _theTxt = $(this).text();
    	                			if( sear_profe == _theTxt ){
    	                				$(this).attr("selected","selected");
    	                			}else{
    	                				$(this).attr("selected",false);
    	                			}
    	                		}); 
    	  	   	    			$("#search_all").attr("userid",_userId);
          	  	            	$("#search_all").attr("unitid",unitid);
    	                		$("#manager_unit").select2({});
    	                		break;
    	                	default:
    	            			if(_thisVal != '' || _thisVal != null){
    	          		   			 $.post("/admin/user/searchList.shtml",{'searchContent':_thisVal},function(data){
    	          	   				 	/* console.log(data); */ 
   	          	  	    				var user=data.data.townList;
   	          	  	    				if( user !="" && user != null && user != undefined ){
   	          	  	    					for(var i=0;i<user.length;i++){ 
   	          	  	   	    					strVar += "<li userId=\""+user[i].id+"\" unitId=\""+user[i].unitId+"\">\n";
   	          	  	   	    				    strVar += "  <span class=\"sear_name\">"+user[i].policeName+"<\/span>\n";
   	          	  	   	    				    strVar += "  <span class=\"sear_profe\">"+user[i].unitName+"<\/span>\n";
   	          	  	   	    				    strVar += "<\/li>\n";
   	          	  	   	    				}; 
   	          	  	   	    				$(".search_list_info").empty().append($(strVar)).show(); 
   	          	  	    				}; 		
    	          		    	    },"json"); 
    	                        };  
    	                }
                    }else{
                  	    $(".search_list_info").empty().hide();  
                  		$("#manager_unit option").attr("selected",false);
                  		$("#search_all").attr("userid","");
                    	$("#search_all").attr("unitid","");
                  		$("#manager_unit").select2();
                    }; 
    			}  
            }); 
            //
            $("body").on("click",".search_list_info li",function(){
            	var _this = $(this);
            	var _userId = $(this).attr("userid");
            	var unitid = $(this).attr("unitid");
            	var _text =  $(this).find(".sear_name").text(); 
            	var sear_profe = _this.find(".sear_profe").text(); 
            	selectOption(_userId);
            	$("#manager_unit option").each(function(){
        			var _theTxt = $(this).text();
        			if( sear_profe == _theTxt ){
        				$(this).attr("selected","selected");
        			}else{
        				$(this).attr("selected",false);
        			}
        		});
            	$("#search_all").val(_text).change();
            	$("#search_all").attr("userid",_userId);
            	$("#search_all").attr("unitid",unitid);
            	$(".search_list_info").hide(); 
            	$("#manager_unit").select2();
            });
    		<!--姓名/身份证模糊查询接口-->
        	$("#manager_unit").change(function(){
        		var _theVal = $(this).find("option:selected").attr("value");
        		if($("#search_all").size()>0){
        			$("#search_all").attr("unitid",_theVal);	
        		};
        		if($("#redactHiden").size()>0){
        			$("#redactHiden").attr("unitid",_theVal);	
        		};
        	})
        	<!--创建管理员接口--> 
        	$(".back_next").click(function(){
        		$("#redactHiden").remove();
        	});
    	    $(".sub_btn_box .sub_btn").click(function(){
    	    	if( $("#redactHiden").size()>0 ){ 
    	    		var redactid = $("#redactHiden").attr("redactid");
    	    		var unitid = $("#redactHiden").attr("unitid");
    	        	//编辑完后提交接口
    	        	if(  redactid != null && redactid != "" && redactid != undefined  &&  unitid != null && unitid != "" && unitid != undefined ){
    	        		$.post("/admin/manager/edit.shtml",{"id":redactid,"unitId":unitid},function(data){ 
        	        		/* var _code = data.code //返回状态码
        	    			var _msg = data.msg //返回提示消息 */
        	        		if(data.code =='200'){
	       						layer.msg(data.msg, { icon: 1, shade: 0.5 });
			        			var index = parent.layer.getFrameIndex(window.name);  
			        			setTimeout(function(){
			        				parent.layer.close(index); 
			        				//在父页面 刷新
			        				window.parent.location.reload();
								},1000);  
		       				}else{
	       						//提示添加失败，打开提交按钮。
	       						layer.msg(data.msg, { icon:2}); 
		       				};  
        	    		},"json");
    	        		return false;
    	        	} 
    	    	}else{  
	    	    	var _val = $("#search_all").val();
	    	    	var userId = $("#search_all").attr("userId");
	    	    	var unitId = $("#search_all").attr("unitid");
	    	    	if( _val != null && _val != "" && _val != undefined ){
	    	    		 $.post("/admin/manager/save.shtml",{'userId':userId,'unitId':unitId},function(data){
	       					if(data.code =='200'){
	       						layer.msg(data.msg, { icon: 1, shade: 0.5 });
			        			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引 
			        			setTimeout(function(){
			        				parent.layer.close(index);
			        				//在父页面 刷新
			        				window.parent.location.reload();
								},1000);  
	       					}else{
	       						//提示添加失败，打开提交按钮。
	       						layer.msg(data.msg, { icon:2 }); 
	       					}
		     	    	},"json"); 
	    	    	}else{
	    	    		layer.msg("请填写信息");
	    	    	} 
	    	    	return false;
    	    	}
    	    });	 
    	}) 
    	
    	//编辑前拉取此条管理员信息接口
    	$("#unit_redact_box .unit_redact").click(function(){
    		var _thObj = $(this).parents("tr");
    		var _theId = _thObj.find(".checkItem").val();
    		var _userId = _thObj.find(".userId").val();
        	selectOption(_userId);
    		/* console.log(_theId); */
    		$.post("/admin/manager/update.shtml",{"id":_theId},function(data){
    			//console.log(data);
			    /* var _code = data.code //返回状态码
				var _msg = data.msg //返回提示消息
				var _id = data.data.id;//管理员ID
				var _userId = data.data.userId;//用户ID
				var _userName = data.data.userName;//用户姓名
				var _unitId = data.data.unitId;//单位ID
				var _unitName = data.data.unitName;//单位名称  */
				var _unitId = data.data.unitId;//用户ID
				$("#lay_wind6").addClass("unit_redact_lay"); 
				var str = '<input type="hidden" value="" id="redactHiden" /> ';
				$("#lay_wind6").append($(str));
				$(".unit_redact_lay #search_all").removeAttr("id").addClass("redact_inp")
				$(".unit_redact_lay .redact_inp").val(data.data.userName);
				$(".unit_redact_lay .redact_inp").attr("readonly","readonly");
				$("#redactHiden").attr("redactId",data.data.id);
				$("#redactHiden").attr("unitId",data.data.unitId); 
				$("#manager_unit option").each(function(){
        			var _theVal = $(this).val();
        			if( _unitId == _theVal ){
        				$(this).attr("selected","selected");
        			}else{
        				$(this).attr("selected",false);
        			}
        		});
				$("#manager_unit").select2();
				layer.open({
    	            type: 1,
    	            title: '编辑', //不显示标题
    	            area: ['445px', '400px'], //宽高
    	            content: $('#lay_wind6'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响 
    			    success: function(){
    		            $(document).on('keydown', function(e){  //document为当前元素，限制范围，如果不限制的话会一直有事件
    		                if(e.keyCode == 13){
    		        		    return false;
    		                }
    		            })
    			    } 
    	        });
		    },"json");	
    	}); 
    	
    	function getUnitTown(id,level){
	    	$.post("/admin/unit/getNextUnit.shtml",
    			{'id':1},
    			function(data){
    				var citys=data.data;
    				$("#citys").empty();
    				$("#citys").append('<option value="">请选择</option>');
    				var unitId=$("#unitId").val();
    				for(var i=0;i<citys.length;i++){
    					var html='';
    					if(unitId==citys[i].id){
	    					html='<option value="'+citys[i].id+'" selected="selected">'+citys[i].name+'</option>';
    					}else{
    						html='<option value="'+citys[i].id+'">'+citys[i].name+'</option>';
    					}
    					$("#citys").append(html);
    				}
	    	},"json");
	    }
    	
    	function selectOption(userId){
    		$.ajaxSettings.async = false;
    		$.post("/admin/unit/getAdminUnit.shtml?userId="+userId,{},function(data){
   				var citys=data.data.townList;
   				//$("#manager_unit").empty();
   				var _html ='<option value="">请选择</option>';
   				var unitId=$("#unitId").val();
   				for(var i=0;i<citys.length;i++){
   					_html +='<option value="'+citys[i].id+'">'+citys[i].unitName+'</option>'; 
   				}
   				$("#manager_unit").empty().append(_html); 
   				$("#manager_unit").select2();
	    	},"json");
    	}  
    </script>
</body>
</html>