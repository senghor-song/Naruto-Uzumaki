<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
</head>
<body class="bodyCss">
	<!--头部-->
	<jsp:include page="../common/header.jsp"></jsp:include>
	<!---->
	<section>
		<jsp:include page="../common/leftNav2.jsp"></jsp:include>
		<div class="mainRight">
			<%-- <input type="hidden" value="${jsonStr}" /> --%> <input type="hidden"
				id="find_hide_id" name="prikey" value="${id}">
			<div class="pancl_pd">
				<div class="new-people-box early_warning_box">
					<div class="tit_menu_list">
						<a href="/admin/common/main.shtml" class="m_menu_item"> <i
							class="iconfont"></i> <em>首页</em>
						</a> 
						<i class="fl split_gt">&gt;</i>
                        <a href="/admin/controlPersonAlarm/list.shtml" class="m_menu_item">
                            <em>重点人预警</em>
                        </a>
                        <i class="fl split_gt">&gt;</i>
                        <a href="/admin/controlPersonAlarm/list.shtml" class="m_menu_item">
                            <em>预警列表</em>
                        </a>
						<i class="fl split_gt">&gt;</i> 
						<a href="javascript:;"
							class="m_menu_item"> <em>预警反馈</em>
						</a>
						<span class="fr">
	                       <input type="button" onclick="javascript:history.back(-1);" class="fr back_btn" value="返回上一页"/>
	                    </span>
					</div>
					<dl class="mgt20 new_peo_list">
						<dt>目标状态：</dt>
						<dd>
							<a class="label_btn m_bg_blue">已发现</a> <a class="label_btn">未发现</a>
						</dd>
					</dl>
					<!-- 平时反馈已发现 -->
					<div class="lieguan-box find">
						<form id="found_form" method="post">
							<input type="hidden" name="columnTubeMode" value="已发现" />
							<div class="not_found_box">
								<dl class="new_peo_list">
									<dt>同行人员：</dt>
									<dd>
										<input type="hidden" name="tx_userinfo" />
										<div class="app_inp_box">
											<input type="text" maxLength="10" placeholder="请输入姓名" verify="isZhName" msg="请输入姓名" class="w130 txt_in text_name"> 
											<input type="text" maxLength="18" placeholder="请输入身份证号" verify="isIdcard" msg="请输入身份证号" class="w130 mgl10 txt_in text_id"> 
											<input type="text" maxLength="11" placeholder="请输入手机号码" verify="isPhone" msg="请填写手机号码" class="w130 mgl10 txt_in text_phone" onkeyup="Phone_data($(this))">
											<div class="fl mgl10 add_btn_wrap"> 
	                                            <input type="button" class="add_pend" value="新增">
	                                            <a class="m_242 mgl5 tips">（温馨提示：请填写完同行人员的基本信息后，点击新增来添加）</a>
	                                        </div> 
										</div>
										<div class="add_pend_wrap"></div>
									</dd>
								</dl>
								<dl class="new_peo_list">
									<dt>手机号码：</dt>
									<dd>
										<input type="text" name="phone" value="" isnot="true" maxLength="11"
											verify="isPhone" msg="请填写手机号码" class="txt_in txt">
									</dd>
								</dl>
								<dl class="new_peo_list">
									<dt>稳控状态：</dt>
									<dd>
										<label> <select name="contro" class="sel steady1">
												<c:if test="${steady1 == null}">
													<option value="">暂无</option>
												</c:if>
												<c:forEach items="${steady1}" var="list">
													<option value="${list.id}">${list.itemName}</option>
												</c:forEach>
										</select>
										</label> <label> <select name="contro2"
											class="mgl10 sel steady2">
												<c:if test="${steady2 == null}">
													<option value="">暂无</option>
												</c:if>
												<c:forEach items="${steady2}" var="list">
													<option value="${list.id}">${list.name}</option>
												</c:forEach>
										</select>
										</label>
									</dd>
								</dl>
								<dl class="new_peo_list">
									<dt>反馈内容：</dt>
									<dd> 
										<textarea name="feedback" class="fl textarea text_keyup" isnot="true" verify="isAll" msg="请填写反馈内容" maxlength="500" onkeyup="checkLen(this)"></textarea>
										<div class="fl mgl10 mgt93 add_btn_wrap">  
											<span class="tips"><i class="m_242"><em class="text_keyup_num">0</em>/500</i>（最多可输入500字）</span>
                                        </div>	
									</dd>
								</dl>
							</div>
							<div class="state-save sub_btn_box">
								<input type="button" onclick="javascript:history.back(-1);" value="关闭" class="back_next" />
								<input type="submit" value="提交反馈" class="sub_btn" />
							</div>
						</form>
					</div>
					<!-- 平时反馈已发现 -->
					<!-- 未发现反馈 -->
					<div class="hideThis lieguan-box not_find">
						<form id="notfound_form" method="post">
							<input type="hidden" name="columnTubeMode" value="未发现" />
							<div class="not_found_box">
								<dl class="new_peo_list">
									<dt>稳控状态：</dt>
									<dd>
										<label> <select name="contro" class="sel steady1">
												<c:if test="${steady1 == null}">
													<option value="">暂无</option>
												</c:if>
												<c:forEach items="${steady1}" var="list">
													<option value="${list.id}">${list.itemName}</option>
												</c:forEach>
										</select>
										</label> <label> <select name="contro2"
											class="mgl10 sel steady2">
												<c:if test="${steady2 == null}">
													<option value="">暂无</option>
												</c:if>
												<c:forEach items="${steady2}" var="list">
													<option value="${list.id}">${list.name}</option>
												</c:forEach>
										</select>
										</label>
									</dd>
								</dl>
								<dl class="new_peo_list">
									<dt>反馈内容：</dt>
									<dd>
										<textarea name="feedback" isnot="true" verify="isAll" msg="请填写反馈内容" class="fl textarea" maxlength="500" onkeyup="checkLen(this)"></textarea>
										<div class="fl mgl10 mgt93 add_btn_wrap">  
											<span class="tips"><i class="m_242"><em class="text_keyup_num">0</em>/500</i>（最多可输入500字）</span>
                                        </div>	
									</dd>
								</dl>
							</div>
							<div class="state-save sub_btn_box">
								<input type="button" onclick="javascript:history.back(-1);" value="关闭" class="back_next" /> 
								<input type="submit" value="提交反馈" class="sub_btn" />
							</div>
						</form>
					</div>
					<!-- 未发现反馈 -->
				</div>
			</div>
		</div>
	</section>
	<!--js-->
	<script src="${base }/resources/admin/js/jquery-1.9.1.min.js"></script>
	<script src="${base }/resources/admin/js/layer.js"></script> 
	<!--验证-->
	<script src="${base }/resources/admin/js/jquery.form.js"></script>
	<script src="${base }/resources/admin/js/ruiec_validate/jquery.ruiValidate.js"></script>
	<!--自写-->
	<script src="${base }/resources/admin/js/layout.js"></script>  
	<script src="${base }/resources/admin/js/common.js"></script> 
	<script type="text/javascript">
	  //tab切换
		$(".early_warning_box .new_peo_list .label_btn").click(function(){
			var _theshow = $(".early_warning_box .lieguan-box");
			var _eq = $(this).index();
			$(this).addClass("m_bg_blue").siblings().removeClass("m_bg_blue");
			_theshow.addClass("hideThis");
			_theshow.eq(_eq).removeClass("hideThis");
		});
	    //稳控状态切换
    	$('.steady1').on('change',function(){ 
    		var c = $(this);
    		 $.ajax({
    		    url:'/admin/controlPersonAlarm/steady.shtml',
    		    type:'POST',
    		    data:{
    		        id:$(this).val()
    		    },
    		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
    		    success:function(data,textStatus,jqXHR){
    		  	   var b = c.parent().parent().find("select[name='contro2']");
    		 	   b.html('');
    		 	   /* if(data.data.length == 1){
    		 		  var a = "<option value=''>暂无</option>";
  		        	  b.append(a);
    		 	   } */
    		       for(var i=0;i<data.data.length;i++){
    		          var a = "<option value="+data.data[i].id+">"+data.data[i].name+"</option>";
    		          b.append(a);
    		       }
    		    },
    		}) 
    	});
    	
    	/**************************/
    	//绑定页面提交事件
		var ruiValidate = new $.rui_validate(); 
		var TheState = true;
		//已反馈提交整合数据
		function AjaxSubmit_findInfo(){
			var _AjaxData = {}; 
			//获取身份证号码的图片
			$("#found_form *[name]").each(function(){
				var _theName = $(this).attr("name"); 
				//debugger;
				if(_theName!=""&&_theValue!=""){
					var _theValue = $(this).val();
					switch(_theName){  
						case "tx_userinfo":
							//调取同行人员的数据
							_AjaxData[_theName] = {};
							_AjaxData[_theName].title = _theValue; //同行人员描述
							var tx_user_list = [];
							
							for(var k = 0 ; k < $("#found_form .select_checked").length ; k++){
								var _theNames = $("#found_form .select_checked:eq("+k+")").find(".clone_name").eq(0).text();  
								var _theSFZH = $("#found_form .select_checked:eq("+k+")").find(".clone_id").eq(0).text();  
								var _themobile_phone = $("#found_form .select_checked:eq("+k+")").find(".clone_phone").eq(0).text();   
								
								if(_theNames !="" || _theSFZH !="" || _themobile_phone !=""){
									if(_theNames =="" || _theSFZH =="" || _themobile_phone =="" ){
										TheState = false; 
									}else{
										TheState = true; 
										var lineObj = {
											name:_theNames,
											sfzh:_theSFZH,
											phone:_themobile_phone
										};
										tx_user_list.push(lineObj);
									} 
								} 
							}
							_AjaxData[_theName].userlist = tx_user_list;
							break; 
						case "":
							break;
						default:
							
							_AjaxData[_theName] = _theValue;
						console.log(_AjaxData[_theName]);
							break;
					}
				}
			});
			
			if(!TheState){
				layer.msg("同行人员资料必须要完善");
				return false;
			} 
			console.log(_AjaxData);
			//return false;
			try{
				var _AjaxData_Str = JSON.stringify(_AjaxData);
			}catch(e){
				var _AjaxData_Str = "";
			}
			//return false;
			if(_AjaxData_Str!=""){  
				//debugger;
				$.ajax({
					type:"post",
					url:"/admin/controlPersonAlarm/feedback.shtml",
					data:{
						jsonStr:_AjaxData_Str, 
						id:$("#find_hide_id").val()
					},
					dataType:"json",
					success:function(data){
						if(data.code=="200"){
							layer.msg(data.msg,{ icon: 1, shade: 0.5 });
							if( data.url !="" ){
								setTimeout(function(){
									window.location.href = data.url;
								},500);
							} 
						}else{
							layer.msg(data.msg,{ icon:2, shade: 0.5 });
						}
					}
				})  
			}
		}
		//未反馈提交整合数据
		function AjaxSubmit_findInfo2(){
			var _AjaxData = {}; 
			//获取身份证号码的图片
			$("#notfound_form *[name]").each(function(){
				var _theName = $(this).attr("name"); 
				if(_theName!=""&&_theValue!=""){
					var _theValue = $(this).val();
					 if(_theName!="prikey"){
						_AjaxData[_theName] = _theValue;
					} 
				}
			});
			
			try{
				var _AjaxData_Str = JSON.stringify(_AjaxData);
			}catch(e){
				var _AjaxData_Str = "";
			}
			
			if(_AjaxData_Str!=""){  
				$.ajax({
					type:"post",
					url:"/admin/controlPersonAlarm/feedback.shtml",
					data:{
						jsonStr:_AjaxData_Str,
						id:$("#find_hide_id").val()
					},
					dataType:"json",
					success:function(data){
						if(data.code=="200"){
							layer.msg(data.msg,{icon: 1,shade:0.5});
							if( data.url !="" ){
								setTimeout(function(){
									window.location.href = data.url;
								},500);
							} 
						}else{
							layer.msg(data.msg,{icon: 2,shade:0.5});
						}
					}
				})  
			}
		}


		//初始化已发现的反馈内容提交
		ruiValidate.initForm({ 
			FocusTip: false,	//获取焦点则进行提示，显示输入规则（ boolen ） 
			ShowTip: "alert_layer",	//显示提示信息的类型：Bubble（气泡）；IconText( 图标加文字 ) ; Text（仅是文字）; Icon（正确或错误的图标）； Highlights 聚焦高亮 ;
			FormObj: $("#found_form"),	//验证的表单容器
			FormIdName: 'found_form',  //form的ID名称   
			ajaxSubmit:false,
			SubBtn: 'sub_btn',
			CallBack: AjaxSubmit_findInfo
		})

		//初始化未发现的反馈内容提交
		ruiValidate.initForm({ 
			FocusTip: false,	//获取焦点则进行提示，显示输入规则（ boolen ） 
			ShowTip: "alert_layer",	//显示提示信息的类型：Bubble（气泡）；IconText( 图标加文字 ) ; Text（仅是文字）; Icon（正确或错误的图标）； Highlights 聚焦高亮 ;
			FormObj: $("#notfound_form"),	//验证的表单容器
			FormIdName: 'notfound_form',  //form的ID名称
			ajaxSubmit:false,   
			SubBtn: 'sub_btn',
			CallBack: AjaxSubmit_findInfo2
		})
    </script>
</body>
</html>