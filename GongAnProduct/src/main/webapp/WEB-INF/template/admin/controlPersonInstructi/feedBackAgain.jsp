<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <!--头部-->
        <jsp:include page="../common/header.jsp"></jsp:include> 
        <!---->
        <input type="hidden" name="id" value="${id }" id="detailId" />
        <section>
        	<jsp:include page="../common/leftNav2.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                        <div class="new-people-box early_warning_box">
                            <div class="tit_menu_list">
                                <a href="/admin/common/main.shtml" class="m_menu_item">
                                    <i class="iconfont"></i>
                                    <em>首页</em>
                                </a>
                                <i class="fl split_gt">&gt;</i>
                                <a href="/admin/controlPersonAlarm/list.shtml" class="m_menu_item">
	                                <em>重点人预警</em>
	                            </a>
	                            <i class="fl split_gt">&gt;</i>
	                            <c:if test="${state==2 }">
	                            <a href="/admin/controlPersonInstructi/issuedInstruction.shtml" class="m_menu_item">
	                                <em>已下发预警</em>
	                            </a>
	                            </c:if>
	                            <c:if test="${state!=2 }">
	                            <a href="/admin/controlPersonInstructi/receivedInstruction.shtml" class="m_menu_item">
	                                <em>待签收预警</em>
	                            </a>
	                            </c:if>
                                <i class="fl split_gt">&gt;</i>
                                <a href="javascript:;" class="m_menu_item refresh_html">
                                    <em>再次反馈</em>
                                </a>
                            </div>
                            <c:if test="${state==4 }">
                            	<div class="mgt20 pdl50 m_crf not_Fail">*审核未通过。原因:原因不详细</div>
                            </c:if>
                            <dl class="new_peo_list mgt20" id="columnBox">
                                <dt>目标状态：</dt>
                                <dd>
                                    <a class="label_btn">已发现</a>
                                    <a class="label_btn m_bg_blue">未发现</a>
                                </dd>
                            </dl>
                            <!-- 平时反馈已发现 -->
                            <div class="hideThis lieguan-box find">
                                <form id="found_form" method="post">
                                    <input type="hidden" name="columnTubeMode" value="已发现" />
                                    <div class="not_found_box">
                                        <dl class="new_peo_list">
                                            <dt>同行人员：</dt>
                                            <dd>
                                                <input type="hidden" name="tx_userinfo" />
                                                <c:if test="${state!=2}">
                                                <div class="app_inp_box">
                                                    <input type="text" placeholder="请输入姓名" verify="isZhName" msg="请输入姓名" class="w130 txt_in text_name">
                                                    <input type="text" maxlength="18" placeholder="请输入身份证号" verify="" msg="请输入身份证号" class="w130 mgl10 txt_in text_id" onkeyup="idCardKeyUp($(this))">
                                                    <input type="text" maxlength="11" placeholder="请输入手机号码" verify="isPhone" msg="请填写手机号码" class="w130 mgl10 txt_in text_phone" onkeyup="Phone_data($(this))">
                                                    <div class="fl mgl10 add_btn_wrap"> 
			                                            <input type="button" class="add_pend" value="新增">
			                                            <a class="m_242 mgl5 tips">（温馨提示：请填写完同行人员的基本信息后，点击新增来添加）</a>
			                                        </div> 
                                                </div>
                                                </c:if>
                                                <div class="add_pend_wrap"></div>
                                            </dd>
                                        </dl>
                                        <dl class="new_peo_list">
		                                    <dt>所持证件：</dt>
		                                    <dd>
		                                        <div class="uploader_wrap">
		                                            <span class="uploader_btn" id="uploader_btn">上传图片</span> 
		                                            <div class="files_pend"></div>
		                                        </div>
		                                    </dd>
		                                </dl>
		                                <dl class="new_peo_list">
		                                    <dt>其他资料：</dt>
		                                    <dd>
		                                        <div class="uploader_wrap">
		                                            <span class="uploader_btn" id="uploader_btn2">上传图片</span> 
		                                            <div class="files_pend"></div>
		                                        </div>
		                                    </dd>
		                                </dl>
                                        <dl class="new_peo_list">
                                            <dt>手机号码：</dt>
                                            <dd>
                                                <input type="text" name="phone" maxlength="11" value="" isnot="true" verify="isPhone" msg="请填写手机号码" class="txt_in txt" <c:if test="${state==2}">readonly="readonly"</c:if>>
                                            </dd>
                                        </dl>
                                        <dl class="new_peo_list">
		                                    <dt>QQ号码：</dt>
		                                    <dd>
		                                        <input type="text" name="QQ" value="" verify="isQQ" msg="请填写QQ号码" maxlength="11" class="txt_in txt" <c:if test="${state==2}">readonly="readonly"</c:if>>
		                                    </dd>
		                                </dl>
		                                <dl class="new_peo_list">
		                                    <dt>微信号：</dt>
		                                    <dd>
		                                        <input type="text" name="WX" value="" verify="isWX" msg="请填写微信号码" class="txt_in txt" <c:if test="${state==2}">readonly="readonly"</c:if>>
		                                    </dd>
		                                </dl>
                                        <dl class="new_peo_list">
                                            <dt class="tl">稳控状态：</dt>
                                            <dd>
                                                <label>
                                                <%--  --%>
		                                            <select name="contro" class="sel steady1" <c:if test="${state==2}">disabled="disabled"</c:if>>
				                                    	<c:forEach items="${steady1}" var="list" >
				                                    		<option value="${list.id}">${list.itemName}</option>
				                                    	</c:forEach>
				                                    	<c:if test="${empty steady1}">
		                                               		<option value="">暂无</option>
		                                               	</c:if>
				                                    </select>
		                                        </label>
		                                        <label>
		                                        <%--  --%>
		                                            <select name="contro2" class="mgl10 sel steady2" <c:if test="${state==2}">disabled="disabled"</c:if>>
				                                    	<c:forEach items="${steady2}" var="list" >
				                                    		<option value="${list.id}">${list.name}</option>
				                                    	</c:forEach>
				                                    	<c:if test="${empty steady2}">
		                                               		<option value="">暂无</option>
		                                               	</c:if>
				                                    </select>
		                                        </label>
                                            </dd>
                                        </dl>
                                        <dl class="new_peo_list">
		                                    <dt>反馈内容：</dt>
		                                    <dd>
		                                        <textarea name="feedback" class="fl textarea" isnot="true" verify="isAll" msg="请填写反馈内容" maxlength="500" onkeyup="checkLen(this)" <c:if test="${state==2}">readonly="readonly"</c:if>></textarea>
		                                    	<div class="fl mgl10 mgt93 add_btn_wrap">  
													<span class="tips"><i class="m_242"><em class="text_keyup_num">0</em>/500</i>（最多可输入500字）</span>
		                                        </div>
		                                    </dd>
		                                </dl>
                                    </div>
                                    <div class="state-save sub_btn_box">
                                    <c:if test="${state==4 }">
                                        <input type="button" value="关闭" class="back_next" onclick="goBack()"/>
                                        <input type="submit" value="提交反馈" class="sub_btn" />
                                    </c:if>
                                    <c:if test="${state==2 }">
                                        <input type="button" value="审核通过" onclick="audit(3,'')" class="audit_passed label_btn m_bg_blue" />
                                        <input type="button" value="审核不通过" class="audit_not_passed label_btn" />
                                    </c:if>
                                    </div>
                                </form>
                            </div>
                            <!-- 平时反馈已发现 -->
                            <!-- 未发现反馈 -->
                            <div class=" lieguan-box not_find">
                                <form  id="notfound_form" method="post">
                                    <input type="hidden" name="columnTubeMode" value="未发现" />
                                    <div class="not_found_box">
                                        <dl class="new_peo_list">
                                            <dt class="tl">稳控状态：</dt>
                                            <dd>
                                                <label>
                                                <%--  --%>
	                                            <select name="contro" class="sel steady1"  <c:if test="${state==2}">disabled="disabled"</c:if>>
			                                    	<c:forEach items="${steady1}" var="list">
			                                    		<option value="${list.id}">${list.itemName}</option>
			                                    	</c:forEach>
			                                    	<c:if test="${empty steady1}">
		                                               	<option value="">暂无</option>
		                                            </c:if>
			                                    </select>
	                                        </label>
	                                        <label>
	                                        <%--  --%>
	                                            <select name="contro2" class="mgl10 sel steady2" <c:if test="${state==2}">disabled="disabled"</c:if>>
			                                    	<c:forEach items="${steady2}" var="list" >
			                                    		<option value="${list.id}">${list.name}</option>
			                                    	</c:forEach>
			                                    	<c:if test="${empty steady2}">
		                                               	<option value="">暂无</option>
		                                            </c:if>
			                                    </select>
			                                </label>
                                            </dd>
                                        </dl>
                                        <dl class="new_peo_list">
		                                    <dt>反馈内容：</dt>
		                                    <dd>
		                                        <textarea name="feedback" class="fl textarea" isnot="true" verify="isAll" msg="请填写反馈内容" maxlength="500" onkeyup="checkLen(this)" <c:if test="${state==2}">readonly="readonly"</c:if>></textarea>
		                                    	<div class="fl mgl10 mgt93 add_btn_wrap">  
													<span class="tips"><i class="m_242"><em class="text_keyup_num">0</em>/500</i>（最多可输入500字）</span>
		                                        </div>
		                                    </dd>
		                                </dl>
                                    </div>
                                    <div class="state-save sub_btn_box">
                                    <c:if test="${state==4 }">
                                        <input type="button" value="关闭" class="back_next" onclick="goBack()"/>
                                        <input type="submit" value="提交反馈" class="sub_btn" />
                                    </c:if>
                                    <c:if test="${state==2 }">
                                        <input type="button" value="审核通过" onclick="audit(3,'')" class="audit_passed label_btn m_bg_blue" />
                                        <input type="button" value="审核不通过" class="audit_not_passed label_btn" />
                                    </c:if>
                                    </div>
                                </form>
                            </div>
                            <!-- 未发现反馈 -->
                        </div>
                </div>
            </div>
        </section>
        
        <div class="hideThis pd25_40 lay_wind lay_wind1" id="lay_wind4">
        <div class="lay_form">
            <form id="audit_form" action="javaScript:void(0);">
            <input type="hidden" name="id" value="${id }"/>
            <input type="hidden" name="state" value="4"/>
                <div class="pdl0 lay_form_item">
                    <dl class="lay_form_list">
                        <dt>原因：</dt>
                        <dd>
                            <textarea name="content" datatype="*" nullmsg="请填写不通过原因" maxLength="100" class="textarea"></textarea>
                        </dd>
                    </dl>
                </div>
                <div class="sub_btn_box">
                    <dl class="lay_form_list">
                        <dt>&nbsp;</dt>
                        <dd>
                            <input type="button" value="关闭" class="back_next">
                            <input type="submit" value="确认" class="sub_btn">
                        </dd>
                    </dl>
                </div>
            </form>
        </div>
    </div>
    <!--js-->
    <script src="${base }/resources/admin/js/jquery-1.9.1.min.js"></script>
    <script src="${base }/resources/admin/js/layer.js"></script> 
    <!--验证-->  
    <script src="${base }/resources/admin/js/jquery.form.js"></script>
    <script src="${base }/resources/admin/js/ruiec_validate/jquery.ruiValidate.js"></script>
    <!--上传--> 
    <script src="${base }/resources/admin/js/webuploader/webuploader.min.js"></script>
    <script src="${base }/resources/admin/js/webuploader/myuploader.js"></script>
    <!--自写-->
    <script src="${base }/resources/admin/js/layout.js"></script> 
    <!---下拉框 -->
    <script src="${base }/resources/admin/js/common.js"></script> 
    <script type="text/javascript">
    	var dataJson1 = '';
        $(function(){
        	//绑定页面的上传图片的JS
			var Myuploader = new $.MyWebUploader();
			if($(".uploader_wrap").length>0){
				$(".uploader_wrap").each(function(){
					var _btnId = $(this).find(".uploader_btn").eq(0).attr("id");
					var _thumbContId = $(this).find("img").eq(0).attr("id");
					//var _thumbInputId = $(this).find("input").eq(0).attr("id");
					var _option = {
						btnId: _btnId, //按钮id
						//thumbContId:_thumbContId,//关联缩略图的id
						//thumbInputId:_thumbInputId
					}
					Myuploader.Init(_option);
				});
			};
        	var state = ${state}+''
			if(state != ''&&state == 2){
				$('.app_inp_box input').attr('readonly','readonly');
			}
        	$.post("/admin/controlPersonInstructi/lastFeedBack.shtml",{id:$("#detailId").val()},function(data){
        		console.log(data);
        		var dataList = data.data;
        		if(dataList !=null && dataList != "" && dataList != undefined){
        			var not_data = dataList.content;
        			var _lieguan_box = $(".lieguan-box");
        			$(".not_Fail").empty().text("*审核未通过。原因:"+not_data+"");
        			var dataJson = dataList.json;
        			dataJson1 = dataJson;
        			if(dataJson!=null && dataJson != "" && dataJson != undefined){
        				var columnTubeMode = dataJson.columnTubeMode;
        				$("#columnBox .label_btn").removeClass("m_bg_blue");
        				$(".lieguan-box").removeClass("hideThis");
        				if( columnTubeMode == "已发现" ){
        					$("#columnBox .label_btn").eq(0).addClass("m_bg_blue");
        					_lieguan_box.eq(1).addClass("hideThis"); 
        					//同行人员
        					 if( dataJson.tx_userinfo != null && dataJson.tx_userinfo != undefined && dataJson.tx_userinfo != "" ){
        						var tx_userinfoList = dataJson.tx_userinfo.userlist; 
        						var strVar = ""; 
						        for( var pr=0; pr<tx_userinfoList.length; pr++ ){ 
						        	//strVar +='<div class="field_peo"><i class="clone_name">'+tx_userinfoList[pr].name+'</i>/<i class="clone_id">'+tx_userinfoList[pr].sfzh+'</i>/<i class="clone_phone">'+tx_userinfoList[pr].phone+'</i><i class="division">;</i></div>'
						        	strVar += "<div class=\"select_checked\">\n";
	        					    strVar += "	    <em class=\"c_8\"><i class=\"clone_name\">"+tx_userinfoList[pr].name+"<\/i>/<i class=\"clone_id\">"+tx_userinfoList[pr].sfzh+"<\/i>/<i class=\"clone_phone\">"+tx_userinfoList[pr].phone+"<\/i><\/em>\n";
	        					    if(state != ''&&state == 2){
	        					    	strVar += "	    <i class=\"iconfont\">&#xe619;<\/i>\n";
	        						}else{
	        							strVar += "	    <i class=\"iconfont icon_del\">&#xe619;<\/i>\n";
	        						}
	        					    strVar += "<\/div>\n";
						        };  
						        $("input[name='tx_userinfo']").val("1");
						        var theObj = $("input[name='tx_userinfo']").parents(".new_peo_list");
						        var add_pend_wrap = theObj.find(".add_pend_wrap");
						        add_pend_wrap.append($(strVar));
					        };
            				//所持证件
            				if(dataJson.idcard_btn!=null && dataJson.idcard_btn != "" && dataJson.idcard_btn != undefined){
            					var strVr = '<input type="hidden" edit_type="thumb" name="idcard_btn" value='+dataJson.idcard_btn+' id="btn">'; 
            					//向文件种追加数据
            					var imgList = dataJson.idcard_btn.split(","); 
            					var str = "";
            					for( var i=0;i<imgList.length;i++ ){ 
            						if(state != ''&&state == 2){
            							str +="<span class='files_item'><img src='"+imgList[i]+"' class='files_img' /></span>";
	        						}else{
	        							str +="<span class='files_item'><img src='"+imgList[i]+"' class='files_img' /><i class='iconfont icon_close'>&#xe619;</i></span>";
	        						}
            					} 
            					var _btnObj = $("#uploader_btn").parent(".uploader_wrap");
            					_btnObj.find(".files_pend").append($(str));
            					_btnObj.append($(strVr)); 
            					if(state != '' && state == 2){
            						$("#uploader_btn").remove();
            						$("#uploader_btn").replaceWith($("#uploader_btn").html());
            					}
            				};
            				//其他资料
            				if(dataJson.idcard_btn2!=null && dataJson.idcard_btn2 != "" && dataJson.idcard_btn2 != undefined){
            					var strVr = '<input type="hidden" edit_type="thumb" name="idcard_btn2" value='+dataJson.idcard_btn2+' id="btn2">'; 
            					//向文件种追加数据
            					var imgList = dataJson.idcard_btn2.split(","); 
            					var str = "";
            					for( var i=0;i<imgList.length;i++ ){ 
            						if(state != ''&&state == 2){
            							str +="<span class='files_item'><img src='"+imgList[i]+"' class='files_img' /></span>";
	        						}else{
	        							str +="<span class='files_item'><img src='"+imgList[i]+"' class='files_img' /><i class='iconfont icon_close'>&#xe619;</i></span>";
	        						}
            					} 
            					var _btnObj = $("#uploader_btn2").parent(".uploader_wrap");
            					_btnObj.find(".files_pend").append($(str));
            					_btnObj.append($(strVr));  
            					if(state != '' && state == 2){
            						$("#uploader_btn2").remove();
            						$("#uploader_btn2").replaceWith($("#uploader_btn2").html());
            					}
            				};
            				//手机号码
        					if(dataJson.phone!=null && dataJson.phone != "" && dataJson.phone != undefined){
            					_lieguan_box.eq(0).find("input[name='phone']").val(dataJson.phone);	
            				};
            				//QQ
        					if(dataJson.QQ!=null && dataJson.QQ != "" && dataJson.QQ != undefined){
            					_lieguan_box.eq(0).find("input[name='QQ']").val(dataJson.QQ);	
            				};
            				//微信号
        					if(dataJson.WX!=null && dataJson.WX != "" && dataJson.WX != undefined){
            					_lieguan_box.eq(0).find("input[name='WX']").val(dataJson.WX);	
            				};
            				//稳控状态
            				if(dataJson.contro!=null && dataJson.contro != "" && dataJson.contro != undefined){
            					var contro = _lieguan_box.eq(0).find(".steady1"); 
            					contro.find("option").each(function(){
            						var _this = $(this);
            						if( _this.text() == dataJson.contro ){
            							_this.attr("selected",true);
            							contro.val(contro.val()).trigger('change');
            						};
            					});
            				}
            				/* if(dataJson.contro2!=null && dataJson.contro2 != "" && dataJson.contro2 != undefined){ 
            					var contro2 = _lieguan_box.eq(0).find(".steady2");
            					contro2.find("option").each(function(){
            						debugger
            						console.info(contro2);
            						var _this = $(this);
									if( _this.text() == dataJson.contro2 ){
										_this.attr("selected",true);
            						};
            					});
            				}; */
            				//反馈内容
            				if(dataJson.feedback!=null && dataJson.feedback != "" && dataJson.feedback != undefined){
            					_lieguan_box.eq(0).find("textarea[name='feedback']").val(dataJson.feedback);	
            				}; 
        				}else{ 
        					$("#columnBox .label_btn").eq(1).addClass("m_bg_blue");
        					$("#columnBox").addClass("mgt20");
        					$(".lieguan-box").eq(0).addClass("hideThis"); 
            				if(dataJson.feedback!=null && dataJson.feedback != "" && dataJson.feedback != undefined){
            					_lieguan_box.eq(1).find("textarea[name='feedback']").val(dataJson.feedback);	
            				};
            				//稳控状态
            				if(dataJson.contro!=null && dataJson.contro != "" && dataJson.contro != undefined){
            					var contro = _lieguan_box.eq(1).find(".steady1"); 
            					contro.find("option").each(function(){
            						var _this = $(this);
            						if( _this.val() == dataJson.contro ){
            							_this.attr("selected",true);
            							contro.val(contro.val()).trigger('change');
            						}; 
            					});
            				}
        				}; 
        			}
        		}
        	});
            $(".early_warning_box .new_peo_list .label_btn").click(function(){
                var _theshow = $(".early_warning_box .lieguan-box");
                var _eq = $(this).index();
                $(this).addClass("m_bg_blue").siblings().removeClass("m_bg_blue");
                _theshow.addClass("hideThis");
                _theshow.eq(_eq).removeClass("hideThis");
            });
        	
	    	/**************************/
	    	//绑定页面提交事件
			var ruiValidate = new $.rui_validate(); 
			var TheState = true; 
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
		});  
		//已反馈提交整合数据
		function AjaxSubmit_findInfo(){
			var _AjaxData = {}; 
			//获取身份证号码的图片
			$("#found_form *[name]").each(function(){
				var _theName = $(this).attr("name"); 
				if( _theName == "contro" ){
					var _theValue = $(this).find("option:selected").val();
				}else if( _theName == "contro2" ){
					var _theValue = $(this).find("option:selected").val();	
				}else{
					var _theValue = $(this).val();
				};
				//debugger;
				if(_theName!=""&&_theValue!=""){
					
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
					url:"/admin/controlPersonInstructi/feedBack.shtml",
					data:{
						jsonStr:_AjaxData_Str, 
						id:$("#detailId").val()
					},
					dataType:"json",
					success:function(data){
						if(data.code=="200"){
							layer.msg(data.msg,{ icon: 1, shade: 0.5 });
							if( data.url !="" ){
								setTimeout(function(){
									window.location.href = "/admin/controlPersonInstructi/receivedInstruction.shtml";
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
					url:"/admin/controlPersonInstructi/feedBack.shtml",
					data:{
						jsonStr:_AjaxData_Str,
						id:$("#detailId").val()
					},
					dataType:"json",
					success:function(data){
						if(data.code=="200"){
							layer.msg(data.msg,{icon: 1,shade:0.5});
							if( data.url !="" ){
								setTimeout(function(){
									window.location.href = "/admin/controlPersonInstructi/receivedInstruction.shtml";
								},500);
							} 
						}else{
							layer.msg(data.msg,{icon: 2,shade:0.5});
						}
					}
				})  
			}
		}  
		// 审核拒绝弹窗
			$(".audit_not_passed").click(function(){
				layer.open({
	                type: 1,
	                title: '审核不通过', //不显示标题
	                area: ['660px', '300px'], //宽高
	                content: $('#lay_wind4'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
	    		    success: function(){
	    	            $(document).on('keydown', function(e){  //document为当前元素，限制范围，如果不限制的话会一直有事件
	    	                if(e.keyCode == 13){
	    	        		    return false;
	    	                }
	    	            })
	    		    } 
	            });
			})
			// 审核通过
			function audit(state,content){
				$.ajax({
					type:"post",
					url:"/admin/controlPersonInstructi/audit.shtml",
					data:{
						id:$("#detailId").val(),
						state:state,
						content:content           
					},
					dataType:"json",
					success:function(data){
						if(data.code=="200"){
							layer.msg(data.msg,{icon: 1,shade:0.5});
							if( data.url !="" ){
								setTimeout(function(){
									window.location.href = "/admin/controlPersonInstructi/issuedInstruction.shtml";
								},500);
							} 
						}else{
							layer.msg(data.msg,{icon: 2,shade:0.5});
						}
					}
				})
			}
			//审核不通过原因
			$('#audit_form').submit(function(){
			    $.ajax({
					url:'/admin/controlPersonInstructi/audit.shtml',
					type:'POST',
					data: $('#audit_form').serialize(),
					dataType: "json",
					success: function(data) {
						if(data.code==200){
							layer.msg(data.msg, { icon: 1});
							setTimeout(function(){
								window.location.href="/admin/controlPersonInstructi/issuedInstruction.shtml"; 
							}		
							, 1000);
						}else{
							layer.msg(data.msg, { icon: 2});
						}
					},
					error : function(data) {  
						layer.msg("审核失败", { icon: 2});  
				       } 
				});
			})
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
	    		  	   var b = c.parent().parent().find(".steady2");
	    		 	   b.html('');
	    		       for(var i=0;i<data.data.length;i++){
	    		        	var a = "<option value="+data.data[i].id+">"+data.data[i].name+"</option>";
	    		        	b.append(a);
	    		        }
		    		   if(dataJson1.contro2!=null && dataJson1.contro2 != "" && dataJson1.contro2 != undefined){ 
	       					b.find("option").each(function(){
									if( $(this).text() == dataJson1.contro2 || $(this).val() == dataJson1.contro2 ){
										$(this).attr("selected",true);
	       						};
	       					});
	       				};
	    		    },
	    		}) 
	    	});
			function goBack(){
				window.history.go(-1);
			}
    </script>
</body>
</html>