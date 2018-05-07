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
	<!-- 鼠标右键点击 -->
	<link rel="stylesheet" href="${base }/resources/admin/js/basicContext/css/normalize.css" />
	<link rel="stylesheet" href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
	<link rel="stylesheet" href="${base }/resources/admin/js/basicContext/css/jq22-demo.css">
	<link rel="stylesheet" href="${base }/resources/admin/js/basicContext/dist/basicContext.min.css">
	<link rel="stylesheet" href="${base }/resources/admin/js/basicContext/dist/themes/default.min.css">
    <style type="text/css">
	    .list_box .list_em{float:left;width:306px;height:30px;line-height:30px;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;}
	    .list_box{border-top:1px solid #CCCCCC;float:left;width:918px;}
	    a.more.curr{color:red;font-weight: bold;}
	    .webuploader-pick{border:1px solid #CCCCCC}
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
                        <form id="form1" action="/admin/syntheticAnalysis/update.shtml" method="post">
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
	                            <a href="/admin/syntheticAnalysis/edit.shtml" class="m_menu_item">
	                                <em>编辑任务</em>
	                            </a>
	                            <span class="fr">
	                        	   <input type="button" onclick="javascript:history.back(-1);" class="fr back_btn" value="返回上一页"/>
	                            </span>
                            </div>
                            <div class="pdt65 new-nan-top">
                            	<input value="${taskDTO.taskId }" name="taskId" type="hidden">
                            	<input value="${task.taskLevel == '紧急' ? 1 : 2 }" name="urgency" type="hidden">
                                <dl class="new_peo_list">
	                                <dt><span style="color:red">*</span>任务名称：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <input type="text" isnot="true" id="name" acttype="edit" name="name" class="txt_in" datatype="*"
                                            placeholder="输入任务名称 " maxlength="50" nullmsg="请输入任务名称" errormsg="请输入任务名称" value="${task.name }"/>
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
	                                <dt><span style="color:red">*</span>任务类型：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                       		<select name="typeNumber" class="sel" datatype="*" nullmsg="请先去字典数据种添加任务类型">
                                               	<c:forEach var="list" items="${taskTypes}">
	                                                <option value="${list.id }" <c:if test="${task.typeNumber eq list.id}">selected="selected"</c:if>>${list.itemName }</option>
                                            	</c:forEach>
                                            	<c:if test="${empty taskTypes}">
	                                                <option value="">暂无</option>
                                            	</c:if>
                                            </select>
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
	                                <dt><span style="color:red">*</span>处置要求：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <input type="text" isnot="true" id="handleMode" acttype="edit" name="handleMode" class="txt_in" 
                                            datatype="*" placeholder="输入处置要求 " maxlength="100" nullmsg="请输入处置要求" errormsg="请输入处置要求" value="${task.handleMode}"/>
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
	                                <dt><span style="color:red">*</span>反馈期限：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                        	<input name="startDate" class="Wdate text_inp " id="quote_date" autocomplete="off" type="hidden" 
                                                value="<fmt:formatDate value="${newDate}" pattern="yyyy-MM-dd"/>"/>
                                            <input name="handleTime" class="Wdate text_inp txt_in" id="quote_date1" autocomplete="off" type="text" 
                                            value="<fmt:formatDate value="${task.handleTime}" pattern="yyyy-MM-dd"/>"  datatype="*"
                                            placeholder="输入反馈期限 " maxlength="100" nullmsg="请输入反馈期限" errormsg="请输入反馈期限" />
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
	                                <dt><span style="color:red">*</span>附件上传：</dt>
                                    <dd>
                                        <div class="fl">
	                                        <sapn class="upload_btn">
		                                        <a class="upd_btn" id="uploaderFace">上传附件</a>
		                                    </sapn>
		                                    <span style="color:red;position: absolute;top: 165px;left: 226px;">请上传bmp、doc、docx、gif、jpeg、jpg、png、ppt、pptx、rar、txt、xls、xlsx、zip格式文件</span>
                                            <input type="file" id="upload_file" style="display: none;" />
		                  				 	<input id="fileUrl" isNot="true" type="hidden">
		                  				 	<div id="fileUrls">
			                  				 	<c:forEach var="list" items="${fileUrls}" varStatus="filesum">
			                  				 		<c:set var="fileType" value="${fn:split(list, '.')}" />
											  		<a 
											  		<c:if test="${fileType[4] == 'bmp' or fileType[4] == 'gif' or fileType[4] == 'jpeg' or fileType[4] == 'jpg' or fileType[4] == 'png'}">
											  			class="fileimg" 
											  		</c:if>
											  		href="${list }" download="${list}" del="${filesum.index+1}">
			                  				 			<input id="fileUrl" type="hidden" name="fileUrls" value="${list }">
										  				<img src="/resources/admin/images/ruiec_${fileType[4]}.png" width="60px">
										  			</a>
			                  				 	</c:forEach>
		                  				 	</div>
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
                                    <dt><span style="color:red">*</span>任务内容：</dt>
                                    <dd>
                                        <textarea name="details" isnot="true" class="fl textarea text_keyup" placeholder="请输入任务内容"  maxlength="200" onkeyup="checkLen(this)"
                                        nullmsg="请输入任务内容" errormsg="请输入任务内容" maxlength="200" datatype="*">${task.details}</textarea>
                                        <div class="fl mgl10 mgt93 add_btn_wrap">  
											<span class="tips"><i class="m_242"><em class="text_keyup_num">0</em>/200</i>（最多可输入200字）</span>
                                        </div>	
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
	                                <dt><span style="color:red">*</span>分县局单位：</dt>
                                    <dd>
	                                    <ul class="list_box">
	                                    	<c:forEach var="list" items="${areaUnit}">
							            		<li class="tab list_em area_list" >
	                                        		<input type="checkbox" class="checkItem" name="areaUnitIds" value="${list.id }" onclick="checkById(${list.id },this)"
	                                        		<c:forEach var="unit" items="${unitOne}">
								                		<c:if test="${unit eq list.id}">
						                        			checked="checked"
						                        		</c:if>
					                   				</c:forEach>>
	                                        		<span onclick="checked(${list.id },$(this))">${list.unitName }</span>
	                                        		<a href="javascript:;" class="more" unitId="${list.id }" isPolice="0"
	                                        		unitCode="${list.provinceCode.concat(list.cityCode.concat(list.areaCode.concat(list.townCode.concat(list.other1Code.concat(list.other2Code)))))}">(更多)</a>
							            		</li>
	                                    	</c:forEach>
						            	</ul>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
	                                <dt>派出所单位：</dt>
                                    <dd id="townList">
                                    <c:forEach var="unit" items="${unitTwo}">
                                    	<input type="hidden" calss="townList" unitId="${unit.id }" parentId="${unit.parentId }" policeParentId="${unit.policeTypesParentId}" > 
                                    </c:forEach>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
	                                <dt><span style="color:red">*</span>支队单位：</dt>
                                    <dd>
	                                    <ul class="list_box">
	                                        <c:forEach var="list" items="${areaUnitPolice}">
	                                        	<li class="tab list_em poloce_unit_list" >
	                                        		<input type="checkbox" class="checkItem" name="areaUnitIds" value="${list.id }" onclick="checkById(${list.id },this)"
	                                        		<c:forEach var="unit" items="${unitOne}">
								                		<c:if test="${unit eq list.id}">
						                        			checked="checked"
						                        		</c:if>
					                   				</c:forEach>>
	                                        		<span onclick="checked(${list.id },$(this))">${list.unitName }</span>	
	                                        		<a href="javascript:;" class="more" unitId="${list.id }" isPolice="1"
		                                        	unitCode="${list.provinceCode.concat(list.cityCode.concat(list.areaCode.concat(list.townCode.concat(list.other1Code.concat(list.other2Code)))))}">(更多)</a>
							            		</li>
	                                    	</c:forEach>
                                    	</ul>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
	                                <dt>大队单位：</dt>
                                    <dd id="policeTownList">
                                    </dd>
                                </dl>
                            </div>
                            <div class="state-save sub_btn_box">
                                <input type="button" onclick="javascript:history.back(-1);" value="关闭" class="back_next" />
                                <input type="submit" value="保存" class="sub_btn" />
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
    <!-- 图片放大效果 -->
    <script type="text/javascript" src="${base }/resources/admin/imagesMagnify/js/jquery.lightbox-0.5.js"></script>
	<!-- 右键点击事件 -->
	<script src="${base }/resources/admin/js/basicContext/dist/basicContext.min.js"></script>
	<script type="text/javascript"> 
	var webuploader = WebUploader.create({
        swf:"/resources/admin/js/webuploader/Uploader.swf",
        server:"/fileUpload.jsp",
        auto:true,
        pick:"#uploaderFace",
        fileNumLimit:10,
        duplicate: true,
        accept: {
            title: 'Images',
            extensions: 'bmp,doc,docx,gif,jpeg,jpg,png,ppt,pptx,rar,txt,xls,xlsx,zip'
        }
    }); 
	var onClick = function(e) {
		var img =  $(this);
		var clicked = function() {$(img).remove();}

		var items = [
			{ title: '删除', icon: 'ion-android-delete', fn: clicked }
		]
		basicContext.show(items, e.originalEvent)
        webuploader.trigger('fileDequeued');
	}
    $(function() {
	
		$('#fileUrls a').on('contextmenu', onClick);
        
        $('#fileUrls .fileimg').lightBox();
	});
	//检查管理人员是否存在
	function checkById(id,_this){
	    $.ajax({
			url:'/admin/manager/checkById.shtml',
			type:"post",
			data:{"id":id},
			async:false,
			dataType: "json",
			success: function(data) {
				if(data.code==400){
					layer.msg(data.msg, { icon: 2});
					_this.checked=false;
				}
			},
			error : function(data) {  
				layer.msg("管理员查询失败", { icon: 2});  
				_this.checked=false;
		       } 
		});
	}    
	$(".more").click(function(){ 
		var _checkId = $(this).attr("unitId");
		var _isPolice = $(this).attr("isPolice");
		if(!$(this).hasClass("curr")){
			$.ajax({
				url:"/admin/unit/getUnitIsPolice.shtml",
				type:"POST",
				data:{"id":_checkId},
				dataType:"json",
				success:function(data){
					var dataList = data.data;
					if(dataList.length>0){
					    var strVar = "";
					    strVar += "<ul class='list_box' unit_id="+_checkId+">\n";
				    	for( var i=0; i<dataList.length; i++ ){
				    		strVar += "<li class='tab list_em'><input type='checkbox' class='checkItem' name='areaUnitIds' value='"+dataList[i].id+"' onclick='checkById("+dataList[i].id+",this)'><span onclick='checked("+dataList[i].id+",$(this))'' title='"+dataList[i].name+"'>"+dataList[i].name+"</span><\/li>";
				    	} 
					    strVar += "<\/ul>\n";
					    if(_isPolice=="0"){
						    $("#townList").append(strVar);
						}else{
						    $("#policeTownList").append(strVar);
						}
					} 
				}
			})
			$(this).addClass("curr");
		}else{
			if(_isPolice=="0"){
				$("#townList").find("ul[unit_id="+_checkId+"]").remove();
			}else{
				$("#policeTownList").find("ul[unit_id="+_checkId+"]").remove();
			}
			$(this).removeClass("curr");
		}
	})
	$(function(){
		var index;
		webuploader.on( 'beforeFileQueued', function() {
			if($("input[name='fileUrls']").length>=10){
				layer.msg("最多上传10个文件");
				return false;
			}
        });
		webuploader.on("error",function (type){
	        if (type=="Q_TYPE_DENIED"){
	        	layer.msg("请上传bmp、doc、docx、gif、jpeg、jpg、png、ppt、pptx、rar、txt、xls、xlsx、zip格式文件");
	        }
	        if (type=="Q_EXCEED_NUM_LIMIT"){
	        	layer.msg("最多上传10个文件");
	        }
	    });
		webuploader.on( 'uploadStart', function(file) {
			index = layer.msg('上传中', {
			  	icon: 16
			  	,shade: 0.1
			  	,time:false
			});
		});
		webuploader.on("uploadSuccess",function(file,data){
	  		if (data.code == 200) {
	  			var sum = $('#fileUrls a').length+1;
		  		var str = "<a ";
		  		var fileType=data.data.split('.');
		  		if(fileType[4]=='bmp' || fileType[4]=='gif' || fileType[4]=='jpeg' || fileType[4]=='jpg' || fileType[4]=='png'){
			  		str += "class='fileimg' href="+data.data+" del="+sum+"><input id='fileUrl' type='hidden' name='fileUrls' value='"+data.data+"'/><img src='/resources/admin/images/ruiec_"+fileType[4]+".png' width='60px'></a>";
	         		$("#fileUrls").append(str);
	                $('#fileUrls .fileimg').lightBox();
				}else {
					str += "href="+data.data+" download='"+data.data+"' del="+sum+"><input id='fileUrl' type='hidden' name='fileUrls' value='"+data.data+"'/><img src='/resources/admin/images/ruiec_"+fileType[4]+".png' width='60px'></a>";
	         		$("#fileUrls").append(str);
				}
        		$('#fileUrls a[del='+sum+']').on('contextmenu', onClick);
    			layer.close(index);
          	} else {
    			layer.close(index);
          		layer.msg(data.msg);
          	}
	  	});
	  	webuploader.on("uploadError",function(){
          	layer.msg("上传文件异常!请联系管理员!");
	  	}); 
	})
	function fileRemove(_this){
		$(_this).remove();
	}
	function checked(id,police){ 
        if($(police).parent().find("input[type='checkbox']").is(":checked")){
    		$(police).parent().find("input[type='checkbox']").prop('checked',false);
        }else{
        	$.ajax({
				url:'/admin/manager/checkById.shtml',
				type:"post",
				data:{"id":id},
				async:false,
				dataType: "json",
				success: function(data) {
					if(data.code==400){
						layer.msg(data.msg, { icon: 2});
					}else{
			    		$(police).parent().find("input[type='checkbox']").prop('checked',true);
					}
				},
				error : function(data) {  
					layer.msg("管理员查询失败", { icon: 2});  
			    } 
			});
        }
    };
    $(function(){
        $('input[calss="townList"]').each(function(){     
            var _unitId = $(this).attr('unitId');
            var _parentId = $(this).attr('parentId');
            var _policeParentId = $(this).attr('policeParentId');
            var areaUnit = null;
            if(_parentId == null || _parentId == ""){
                areaUnit = $(".poloce_unit_list").find("a[unitId="+_policeParentId+"]");
            }else{
                areaUnit = $(".area_list").find("a[unitId="+_parentId+"]");
            }
            if(areaUnit != undefined){
                if(!$(areaUnit).hasClass("curr")){
                    var _checkId = $(areaUnit).attr("unitId");
                    $.ajax({
                        url:"/admin/unit/getUnitIsPolice.shtml",
                        type:"POST",
                        data:{"id":_checkId},
                        dataType:"json",
                        async:false,
                        success:function(data){
                            var dataList = data.data;
                            var strVar = "";
                            if(dataList.length>0){
                                strVar += "<ul class='list_box' unit_id="+_checkId+">\n";
                                for( var i=0; i<dataList.length; i++ ){
                                    strVar += "<li class='tab list_em'><input type='checkbox' class='checkItem' name='areaUnitIds' value='"+dataList[i].id+"' onclick='checkById("+dataList[i].id+",this)'>"+dataList[i].name+"<\/li>";
                                } 
                                strVar += "<\/ul>\n";
                                
                            } 
                            if(_parentId == null || _parentId == ""){
                                $("#policeTownList").append(strVar);
                                $("#policeTownList").find("ul[unit_id="+_policeParentId+"]").find("input[name='areaUnitIds']").each(function(){
                                    if($(this).val() == _unitId){
                                        $(this).prop('checked',true);
                                    }
                                });
                            }else{
                                $("#townList").append(strVar);
                                $("#townList").find("ul[unit_id="+_parentId+"]").find("input[name='areaUnitIds']").each(function(){
                                    if($(this).val() == _unitId){
                                        $(this).prop('checked',true);
                                    }
                                });
                            }
                        }
                    })
                    $(areaUnit).addClass("curr");
                }else {
                    if(_parentId == null || _parentId == ""){
                        $("#policeTownList").find("ul[unit_id="+_policeParentId+"]").find("input[name='areaUnitIds']").each(function(){
                            if($(this).val() == _unitId){
                                $(this).prop('checked',true);
                            }
                        });
                    }else{
                        $("#townList").find("ul[unit_id="+_parentId+"]").find("input[name='areaUnitIds']").each(function(){
                            if($(this).val() == _unitId){
                                $(this).prop('checked',true);
                            }
                        });
                    }
                }
            }
        });
    });
	</script>
</body>
</html>