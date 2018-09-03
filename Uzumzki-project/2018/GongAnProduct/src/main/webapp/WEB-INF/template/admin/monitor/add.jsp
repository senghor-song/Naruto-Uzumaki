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
    <style type="text/css">
    	.img_shows{ overflow: hidden;}
		.img_shows .img_boxs{ display: inline-block; border-radius: 3px; border:1px solid #ddd; width:79px; height:92px; overflow: hidden;}
		.img_shows .img_boxs img{ width: 100%; height: 100%;}
		.img_shows .upload_btn{ position: relative; display: inline-block; margin: 60px 0px 0px 12px; padding:0px 15px; border:1px solid #ddd; border-radius: 3px; overflow: hidden;}
		.img_shows .upload_btn .upd_btn{ color: #777;}
		.new-people-box.xzzdr .new-nan-top{ position: relative;}
		.new-people-box.xzzdr .new-nan-top:after{ content: ''; display: block; width: 100%; height: 1px; overflow: hidden;}
		.new_peo_tit{ margin-bottom: 25px; color: #666666; font-size: 14px;}
		.new_peo_tit span{ padding-left: 10px; border-left: 7px solid transparent; }
		.new-nan-top .img_shows { overflow: hidden;  position: absolute;  left: 50%;  top: 50%;  margin-top: -140px;}
		#shenfengzheng_card{display: inline-block; width:100%; max-width: 228px; overflow: hidden; }
		.tab input[type=checkbox]{-webkit-appearance:none;appearance:none;width:20px;height:20px;margin:0;cursor:pointer;/* vertical-align:bottom; */background:#eee;border:1px solid #e5e5e5;border-radius:3px;box-sizing:border-box;position:relative}
    </style>
</head>
<body class="bodyCss"> 
        <jsp:include page="../common/header.jsp"></jsp:include> 
        <!---->
        <section>
        	<jsp:include page="../common/leftNav6.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                    <div class="new-people-box xzzdr">
                        <form id="form1" action="/admin/monitor/save.shtml" method="post">
                        	<div class="tit_menu_list">
                                <a href="/admin/common/main.shtml" class="m_menu_item">
                                    <i class="iconfont"></i>
                                    <em>首页</em>
                                </a>
	                            <i class="fl split_gt">&gt;</i>
	                            <a href="javascript:;" class="m_menu_item">
	                                <em>布控管理</em>
	                            </a>
	                            <i class="fl split_gt">&gt;</i>
	                            <a href="/admin/monitor/list.shtml" class="m_menu_item">
	                                <em>布控人员列表</em>
                               	</a>
                                <i class="fl split_gt">&gt;</i>
                                <a href="/admin/monitor/add.shtml" class="m_menu_item">
                                    <em>新增布控人员信息</em>
                                </a>
                                <span class="fr">
	                             	<input type="button" onclick="javascript:history.back(-1);" class="fr back_btn" value="返回上一页"/>
                                </span>
                            </div>
                            <div class="pdt65 new-nan-top">
                                <div class="img_shows">
                                    <span class="fl img_boxs">
                                        <img id="face_pic" src="${base }/resources/admin/images/Avatar.png" />
                                    </span>
                                    <sapn class="upload_btn">
                                        <a class="upd_btn" id="uploaderFace">上传头像</a>
                                    </sapn>
                       				<input type="file" id="upload_file" style="display: none;" />
                  				 	<input id="fileUrl"  datatype="*" isNot="true" type="hidden" name="photo" value="" nullmsg="请上传头像">
                                </div>
                                <dl class="new_peo_list">
	                                <dt><span style="color:red">*</span>姓名：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <input type="text" name="name" value="" class="txt_in " datatype="/[\u4e00-\u9fa5·]$/" nullmsg="请输入姓名" 
                                            placeholder="输入名字" id="name" maxlength="20" errormsg="请输入正确的姓名">
                                        </div>
                                        <div class="fl mgl100 right_item">
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
	                                <dt><span style="color:red">*</span>身份证号：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                        	<!-- onblur="selByIdCard($(this))" -->
                                        	<!--  datatype="isIDCard" -->
                                            <input type="text" isnot="true" maxlength="18" id="shenfengzheng_card" acttype="edit" name="idCard" datatype="isIDCard" nullmsg="请输入身份证号" placeholder="请输入身份证号" class="txt_in"  onkeyup="idCardKeyUp($(this))" />
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
                                	<dt><span style="color:red">*</span>民族：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <select class="sel" id="nation" name="nationNumber" datatype="*" nullmsg="请在字典添加民族数据！">
                                            	<c:if test="${fn:length(nations) == 0}">
                                             		<option value="-1">暂无</option>
                                            	</c:if>
                                                <c:forEach var="list" items="${nations}">
                                                	<option value="${list.id }" >${list.itemName }</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="fl mgl100 right_item">
                                           <label class="fl">
		                                    	<span class="fl"> <i style="color:red">*</i>性别：</span>
                                                <label class="radio_i label_item m_b">
	                                                <input type="radio" value="男" name="sex" checked="checked">
		                                            <i class="iconfont"></i>
		                                            <font class="middle">男</font>
												</label>
                                                <label class="radio_i label_item">
	                                                <input type="radio" value="女" name="sex">
		                                            <i class="iconfont"></i>
		                                            <font class="middle">女</font>
		                                        </label>
		                                    </label>
                                        </div>
                                    </dd>
                                    
                                </dl>
                                <dl class="new_peo_list">
                                	<dt><span style="color:red">*</span>人员类型：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <div class="">
                                                <select class="fl sel" id="personnelType" disabled="disabled">
	                                                	<c:forEach var="list" items="${monitorType}">
			                                                <option value="${list.id }"<c:if test="${list.itemName == '布控人员'}">selected="selected"</c:if>>${list.itemName }</option>
		                                               	</c:forEach>
		                                            <option value="4">布控人员</option>
	                                               	<option value="1">重点人员</option>
	                                               	<option value="2">关注人员</option>
	                                               	<option value="3">外地人员</option>
                                                </select>
                                                <input id="pt" type="hidden" name="personTypeId">
                                            </div>
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
                                	<dt>车牌号：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <input id="licensePlateNumber" type="text" isnot="true" name="licensePlateNumber" value="" placeholder="请输入车牌号" class="txt_in fl txt" maxlength="10">
                                        </div>
                                        <div class="fl mgl100 right_item">
                                            <label>
                                                <span>联系方式：</span>
                                                <input id="contactInfo" type="text" isnot="true" placeholder="请输入联系方式" name="contactInfo" value="" class="txt_in" maxlength="11"
                                                ignore="ignore" datatype="m" errormsg="请输入正确的联系方式">
                                            </label>
                                        </div>
                                    </dd>
                                </dl>

                                <dl class="new_peo_list">
                                    <dt>现住地址：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <input id="currentAddress" type="text" isnot="true" name="currentAddress" value="" placeholder="请输入现住地址" class="txt_in txt" maxlength="50">
                                        </div>
                                        <div class="fl mgl100 right_item">
                                            <label>
                                                <span>户籍地址：</span>
                                                <input id="nativeAddress" type="text" name="nativeAddress" placeholder="请输入户籍地址" value="" class="txt_in txt" maxlength="50">
                                            </label>
                                        </div>
                                    </dd>
                                </dl>

                            </div>

                            <div class="bar_line"></div>
                            <div class="new-nan-top lieguan-box">
                                <div class="new_peo_tit">
                                    <span>布控信息</span>
                                </div>

                                <dl class="new_peo_list" id="choseCategories">
                                    <dt><span style="color:red">*</span>选择类别：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <select class="sel " name="dataitemId" id="chose_itemId" onchange="unitIdList('chose_itemId','chose_itemId2','chose_itemId3','2')">
                                             	<c:if test="${fn:length(dataitemIds) == 0}">
                                             		<option value="-1">暂无</option>
                                             	</c:if>
                                             	<c:forEach var="list" items="${dataitemIds}">
	                                                <option value="${list.id }" >${list.itemName }</option>
                                               	</c:forEach>
                                            </select>
                                        </div>
                                        <div class="chose_btn" id="Categories_btn">
                                            <input type="button" class="chose_tn" value="选择" />
                                        </div>
                                    </dd>
                                </dl>
								<dl class="new_peo_list" id="Categories_peo">
                                    <dt><span style="color:red">*</span>人员类别：</dt>
                                    <dd>
                                    	<input type="hidden" value="" name="Categories" id="Categories" datatype="*" nullmsg="至少选择一个人员类别"/>
                                    	<input type="hidden" value="" id="Categoriess"/>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
                                    <dt><span style="color:red">*</span>布控单位：</dt>
                                    <dd>
                                     	<input type="hidden" value="" name="parentUserId" id="parentUserId"/>
                                     	<input type="hidden" id="parentUnitId">
                                     	<input type="hidden" name="userId" disabled="true" id="userId">
                                    	<c:if test="${empty isUser}">
	                                        <div class="fl left_item">
	                                            <select class="sel " name="unitId" id="selunitId" onchange="unitIdList('selunitId','selunitId2','seluserId','1')">
	                                           		<c:forEach var="list" items="${units}">
		                                                <option value="${list.id }" >${list.unitName }</option>
	                                               	</c:forEach>
	                                            </select>
	                                        </div>
	                                        <div class="fl left_item">
	                                            <select class="sel" name="unitId2" id="selunitId2" onchange="userIdBind('selunitId','selunitId2','seluserId','1')">
	                                                <option value="-1">暂无</option>
	                                            </select>
	                                        </div>
	                                        <div class="fl left_item">
	                                            <select class="sel" name="userId" id="seluserId" datatype="*" nullmsg="至少选择一个警员">
	                                                <option value="-1">暂无</option>
	                                            </select>
	                                        </div>
	                                        <div class="fl left_item tab">
	                                        	<input id="check" type="checkbox" class="checkItem">
	                                        	<span>选择自己</span>
	                                        </div>
                                        </c:if>
                                    	<c:if test="${!empty isUser}">
	                                        <div class="fl left_item">
	                                            <select class="sel " name="unitId" id="unitId">
	                                                <option value="${unit1.id }">${unit1.unitName }</option>
	                                            </select>
	                                        </div>
	                                        <div class="fl left_item">
	                                            <select class="sel" name="unitId2" id="unitId2">
	                                            	<c:if test="${!empty unit2}">
	                                                	<option value="${unit2.id }">${unit2.unitName }</option>
	                                            	</c:if>
	                                            	<c:if test="${empty unit2}">
	                                                	<option value="-1">暂无</option>
	                                            	</c:if>
	                                            </select>
	                                        </div>
	                                        <div class="fl left_item">
	                                            <select class="sel " name="userId">
	                                                <option value="${user.id }">${user.policeName }</option>
	                                            </select>
	                                        </div>
	                                        <div class="fl left_item">
	                                        	<input id="check" type="checkbox">
	                                        	<span>选择自己</span>
	                                        </div>
                                        </c:if>
                                    </dd>
                                </dl>
                                
                                <dl class="new_peo_list">
                                    <dt><span style="color:red">*</span>布控时间：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <select class="sel " name="duration" id="duration">
                                                <option value="10">10天</option>
                                                <option value="20">20天</option>
                                                <option value="30">30天</option>
                                            </select>
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
                                    <dt><span style="color:red">*</span>布控措施：</dt>
                                    <dd>
                                    	<select class="sel " name="handleMode" id="handleMode" datatype="*" nullmsg="请选择一个布控措施！">
                                    		<c:if test="${fn:length(disposalMeasure) == 0}">
                                             		<option value="-1">暂无</option>
                                            </c:if>
                                    		<c:forEach items="${disposalMeasure}" var="list">
                                    			<option value="${list.id }">${list.itemName }</option>
                                    		</c:forEach>
                                        </select>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
                                    <dt>民警电话：</dt>
                                    <dd>
                                        <div class="fl mgl200 right_item">
                                            <input id="contactsPolice" type="text" isnot="true" value="" placeholder="请输入民警电话" name="contactsPolice" class="txt_in"
                                            maxlength="11" ignore="ignore" datatype="m" errormsg="请输入正确的民警电话">
	                                    </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
                                    <dt><span style="color:red">*</span>布控理由：</dt>
                                    <dd>
                                        <textarea id="reason" name="reason" isnot="true" class="fl textarea text_keyup" placeholder="请输入布控理由"  maxlength="200" onkeyup="checkLen(this)"
                                        nullmsg="请输入布控理由" errormsg="请输入布控理由" maxlength="200" datatype="*"></textarea>
                                        <div class="fl mgl10 mgt93 add_btn_wrap">  
											<span class="tips"><i class="m_242"><em class="text_keyup_num">0</em>/200</i>（最多可输入200字）</span>
                                        </div>	
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
    <%-- <script src="${base }/resources/admin/js/datetimepicker/timer.js"></script> --%>
	<script src="${base }/resources/admin/js/webuploader/webuploader.min.js"></script>
	<script src="${base }/resources/admin/js/webuploader/myuploader.js"></script>
    <script type="text/javascript">
    $(function(){
    	$('#check').on('click',function(){
    		if($('#check').prop('checked')){
    			$('#selunitId,#selunitId2,#seluserId').attr('disabled',true);
    			$('#selunitId,#selunitId2,#seluserId').css('background-color','#C8C8C8');
    			$('#userId').attr('disabled',false);
    			$('#userId').val('${sessionScope.user.id}');
    		}else{
    			$('#selunitId,#selunitId2,#seluserId').attr('disabled',false);
    			$('#selunitId,#selunitId2,#seluserId').removeAttr('style');
    			$('#userId').attr('disabled',true);
    			$('#userId').val('');
    		}
    	})
		//初始化列空单位
		var chose_itemIdVal = $("#chose_itemId").find("option:selected").attr("value");
		unitIdList('chose_itemId','chose_itemId2','chose_itemId3','2',chose_itemIdVal); 
		var selunitIdVal = $("#selunitId").find("option:selected").attr("value");
		unitIdList('selunitId','selunitId2','seluserId','1',selunitIdVal);   
		//绑定页面的上传图片的JS
		var Myuploader = new $.MyWebUploader();
		if($(".img_shows").length>0){
			$(".upload_btn").each(function(){
				var _btnId = $(this).find(".upd_btn").eq(0).attr("id");
				var _thumbContId = $(".img_boxs").find("img").eq(0).attr("id");
				var _thumbInputId = $(".img_shows").find("input[name='photo']").eq(0).attr("id");
				var _option = {
					btnId: _btnId, //按钮id
					thumbContId:_thumbContId,//关联缩略图的id
					thumbInputId:_thumbInputId
				}
				Myuploader.Init(_option);
			});
		}
		$("#shenfengzheng_card").blur(function(){
			$(this).attr("readonly","readonly"); 
			var _val = $(this).val();
			$.ajax({
			    url:'/admin/monitor/selByIdCard.shtml',
			    type:'POST',
			    data:{
			    	idCard:_val
			    },
			    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
			    success:function(data,textStatus,jqXHR){ 
			    	if(data.code=='200'){
			    		layer.open({
				   	         type: 2,
				   	         title: '选择布控人信息', //不显示标题
				   	         area: ['1100px', '650px'], //宽高
				   	         content: "/admin/monitor/selByIdCards.shtml?idCard="+_val,  
					   	     cancel: function(index, layero){    
					   	    		var body = layer.getChildFrame('body', index); 
					   	    	    layer.close(index);//这块是点击确定关闭这个弹出层 
					   	    		parent.$("#shenfengzheng_card").attr("readonly",false);

					   	     },
			 	             end: function () {
			 	            	$("#shenfengzheng_card").attr("readonly",false);
			 	             }	 
				   	    });
			    	}else{
			    		$("#shenfengzheng_card").attr("readonly",false);
			    	}
			    },
			}) 
		})
		$('#personnelType').trigger('change');
		$('#pt').val($('#personnelType').val());
	})
	function unitIdList(selID,selTwoId,userIdList,_choseType,firstVal){
		//初始化val值是否存在
		if( firstVal !="" && firstVal != null && firstVal != undefined ){
			var areaId = { id:firstVal} 
		}else{
			var areaId = { id:$("#" + selID).find("option:selected").attr("value")}
		} 
		if( _choseType == "1" || _choseType == 1 ){
			var _columnUrl ="/admin/unit/getNextUnit.shtml"; 
		}else{
			var _columnUrl ="/admin/dictionary/subSet.shtml";
		}
   		$.ajax({
   			type:"POST",
   			url:_columnUrl,
   			data:areaId,
   			datatype:"json",
   			success:function(data){ 
   				var strVar = "";
   				var _unitId2List = data.data; 
   				if( _unitId2List !="" && _unitId2List != null && _unitId2List != undefined ){ 
   					strVar +="<option value='-1'>暂无</option>";
   					for( var i=0; i< _unitId2List.length; i++ ){  
   					 strVar +="<option value='"+_unitId2List[i].id+"'";
   					 if(_choseType == '1' || _choseType == 1){
          					var personUnit=$("#parentUnitId").val();
          					if(personUnit == _unitId2List[i].id){
              					strVar +="selected='selected'";
              				}
              			 }
	   					 strVar +=">"+_unitId2List[i].name+"</option>";
	   				}
    				$("#" +selTwoId).empty().append(strVar);
    				userIdBind(selID,selTwoId,userIdList,_choseType,firstVal);
   				}else{
   					strVar ="<option value='-1'>暂无</option>";
   					$("#" +selTwoId).empty().append(strVar);
   					userIdBind(selID,selTwoId,userIdList,_choseType,firstVal);
   				}
   			}
   		})
	}
		
		function userIdBind(selID,selTwoId,userIdList,_choseType,firstVal){
		//初始化val值是否存在
			var townId = $("#" + selTwoId).find("option:selected").attr("value");
		if( firstVal !="" && firstVal != null && firstVal != undefined ){ 
			if( _choseType == "1" || _choseType == 1 ){ 
   				var _columnUrl ="/admin/unit/getUnitPerson.shtml";
   				var dataList = { areaId:firstVal,townId:townId };
   			}else{
   				var _columnUrl = "/admin/dictionary/subSet.shtml";
   				var dataList = { id:townId};
   			} 
		}else{
			/* var areaId = { id:$("#" + selTwoId).find("option:selected").attr("value")} */
			if( _choseType == "1" || _choseType == 1 ){ 
				var areaId = $("#" + selID).find("option:selected").attr("value");
   				var _columnUrl ="/admin/unit/getUnitPerson.shtml";
   				var dataList = { areaId:areaId,townId:townId };
   			}else{
   				var _columnUrl = "/admin/dictionary/subSet.shtml";
   				var dataList = { id:townId};
   			} 
		} 
			$.ajax({
   			type:"POST",
   			url:_columnUrl,
   			data: dataList,
   			datatype:"json",
   			success:function(data){ 
   				var strVar = "";
   				var _unitId2List = data.data; 
   				if( _unitId2List !="" && _unitId2List != null && _unitId2List != undefined ){ 
   					strVar +="<option value='-1'>暂无</option>";
   					for( var i=0; i< _unitId2List.length; i++ ){ 
   						if(_unitId2List[i].id==1){
   	   						continue;
   	   	   				}
   						strVar +="<option value='"+_unitId2List[i].id+"' ";
       					if($('#parentUserId').val() == _unitId2List[i].id){
           					strVar +="selected='selected'";
       					}
       					strVar +=">"+_unitId2List[i].name+"</option>";
       				}
       				$("#" +userIdList).empty().append(strVar); 
   				}else{
   					strVar ="<option value='-1'>暂无</option>";
   					$("#" +userIdList).empty().append(strVar); 
   				}
   			}
   		})
		}
		$('#Categoriess').on('click',function(){
			$('#Categories_peo').find("div").remove(".select_checked");
			var _v = $('#Categories').val().split(",");
			var _val = $(this).val().split(",");
			var str = "";
			for(var i=1;i<_val.length;i++){
				str +=	"<div class='mgr10 select_checked' valid='"+_v[i-1]+"'><em class='m_b'>"+
				_val[i-1]
				+"</em><i class='iconfont icon_close'>&#xe619;</i></div>";
			}
			$('#Categories_peo').find('dd').append(str);
		}) 
    </script>
</body>
</html>