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
                    <div class="new-people-box xzzdr">
                        <form id="form1" action="/admin/controlPerson/save.shtml" method="post">
                        	<div class="tit_menu_list">
                                <a href="/admin/common/main.shtml" class="m_menu_item">
                                    <i class="iconfont"></i>
                                    <em>首页</em>
                                </a>
	                            <i class="fl split_gt">&gt;</i>
	                            <a href="javascript:;" class="m_menu_item refresh_html">
	                                <em>人员管理</em>
	                            </a>
	                            <c:if test="${personnelType eq '1'}">
		                            <i class="fl split_gt">&gt;</i>
		                            <a href="/admin/controlPerson/list.shtml?personnelType=${personnelType}" class="m_menu_item">
		                                <em>重点人员列表</em>
                                	</a>
	                            </c:if>
	                            <c:if test="${personnelType eq '2'}">
		                            <i class="fl split_gt">&gt;</i>
		                            <a href="/admin/controlPerson/list.shtml?personnelType=${personnelType}" class="m_menu_item">
		                                <em>关注人员列表</em>
                                	</a>
	                            </c:if>
	                            <c:if test="${personnelType eq '3'}">
		                            <i class="fl split_gt">&gt;</i>
		                            <a href="/admin/controlPerson/list.shtml?personnelType=${personnelType}" class="m_menu_item">
		                                <em>外地人员列表</em>
                                	</a>
	                            </c:if>
                                <i class="fl split_gt">&gt;</i>
                                <a href="/admin/controlPerson/add.shtml" class="m_menu_item">
                                    <em>
	                                    <c:if test="${personnelType eq '1'}">新增重点人员信息</c:if>
	                                	<c:if test="${personnelType eq '2'}">新增关注人员信息</c:if>
	                                	<c:if test="${personnelType eq '3'}">新增外地人员信息</c:if>
                                        <c:if test="${empty personnelType}">新增人员信息</c:if>
                                	</em>
                                </a>
                                <span class="fr">
	                        	   <input type="button" onclick="javascript:history.back(-1);" class="fr back_btn" value="返回上一页"/>
	                            </span>
                            </div>
                            <input type="hidden" value="1" name="isAuditKeyPersonColumnTub" >
                            <div class="pdt65 new-nan-top">
                                <div class="img_show">
                                    <span class="fl img_box">
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
                                        <div class="fl left_item" style="width: 400px; ">
                                            <input type="text" isnot="true" maxlength="18" id="shenfengzheng_card" acttype="edit" name="idCard"
                                            datatype="isIDCard" nullmsg="请输入身份证号" placeholder="请输入身份证号" class="txt_in" onchange="isIdCardKeyUp($(this))" />
                                            <span id="idCardMsg" style="color: red;"></span>
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
                                	<dt>经常居住地：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <input type="text" name="habitualResidence" value="" class="txt_in " placeholder="输入经常居住地" id="name"
                                            maxlength="60">
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
	                                                <input type="radio" value="女" name="sex" >
		                                            <i class="iconfont"></i>
		                                            <font class="middle">女</font>
		                                        </label>
		                                    </label>
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
                                	<dt><span style="color:red">*</span>民族：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <select class="sel" id="nation" name="nation" datatype="*" nullmsg="请选择民族" errormsg="请选择民族">
                                                <c:forEach var="list" items="${nations}">
                                                	<option value="${list.id }" 
                                                	<c:if test="${list.id eq controlPerson.controlPersonExtend.nation}">selected="selected"</c:if>>${list.itemName }</option>
                                                </c:forEach>
                                               	<c:if test="${empty nations}">
                                               	<option value="">暂无</option>
                                               	</c:if>
                                            </select>
                                        </div>
                                        <div class="fl mgl100 right_item">
                                            <label>
                                                <span class="fl"> 出生日期：</span>
                                                <input type="test" name="birthDate" class="Wdate text_inp txt_in" id="quote_date" autocomplete="off"
                                                placeholder="输入出生日期 ">
                                            </label>
                                        </div>
                                    </dd>
                                    
                                </dl>
                                <dl class="new_peo_list">
                                	<dt><span style="color:red">*</span>人员类型：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <div class="">
                                                <select class="fl sel" id="personnelType" name="personnelType" datatype="*" nullmsg="请选择人员类型" errormsg="请选择人员类型">
                                                	<c:if test="${1 == personnelType}">
                                                		<option value="1" >重点人员</option>
													</c:if>
                                                	<c:if test="${2 == personnelType}">
                                                		<option value="2" >关注人员</option>
													</c:if>
                                                	<c:if test="${3 == personnelType}">
                                                		<option value="3" >外地人员</option>
													</c:if>
                                                	<c:if test="${empty personnelType}">
	                                                	<option value="1">重点人员</option>
	                                                	<option value="2">关注人员</option>
	                                                	<option value="3">外地人员</option>
													</c:if>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="fl mgl100 right_item">
                                            <label class="fl">
                                            	<span>籍贯：</span>
                                            	<input type="text" name="nativePlace" value="" placeholder="请输入籍贯" class="txt_in" maxlength="60">
                                            </label>
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
                                    <dt>现住地址：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <input type="text" isnot="true" name="nowLiveArea" value="" placeholder="请输入现住地址" class="txt_in txt" maxlength="60">
                                        </div>
                                        <div class="fl mgl100 right_item">
                                            <label>
                                                <span>联系方式：</span>
                                                <input type="text" isnot="true" placeholder="请输入联系方式" name="phone" value="" class="txt_in" maxlength="11"
                                                ignore="ignore" datatype="m" errormsg="请输入正确的联系方式">
                                            </label>
                                        </div>
                                    </dd>
                                </dl>

                                <dl class="new_peo_list">
                                    <dt>QQ号码：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <input type="text" datatype="/[1-9][0-9]{4,}/" isnot="true" name="qq" value="" placeholder="请输入QQ号码" 
                                            class="txt_in fl txt" maxlength="11" errormsg="请输入正确的QQ号码" ignore="ignore">
                                        </div>
                                        <div class="fl mgl100 right_item">
                                            <label>
                                                <span>户籍地址：</span>
                                                <input type="text" name="householdRegisterPlace" placeholder="请输入户籍地址" value="" class="txt_in txt" maxlength="60">
                                            </label>
                                        </div>
                                    </dd>
                                </dl>

                                <dl class="new_peo_list">
                                    <dt>车牌号：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <input type="text" isnot="true" name="plateNumber" value="" placeholder="请输入车牌号" class="txt_in fl txt" maxlength="10">
                                        </div>
                                        <div class="fl mgl100 right_item">
                                            <label>
                                                <span>微信号码：</span>
                                                <input type="text" name="wechat" value="" placeholder="请输入微信号码" class="txt_in txt" maxlength="50">
                                            </label>
                                        </div>
                                    </dd>
                                </dl>

                                <dl class="new_peo_list">
                                    <dt>单位（职业）：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <input type="text" name="occupation" value="" placeholder="请输入单位或职业" class="fl txt_in" maxlength="60">
                                        </div>
                                        <div class="fl mgl100 right_item">
                                            <label>
                                                <span>其他住址：</span>
                                                <input type="text" name="otherAddress" value="" placeholder="请输入其他住址" class="txt_in txt" maxlength="60">
                                            </label>
                                        </div>
                                    </dd>
                                </dl>
                            </div>

                            <div class="bar_line"></div>
                            <div class="new-nan-top lieguan-box">
                                <div class="new_peo_tit">
                                    <span>重点人员资料编辑</span>
                                </div>
                                <dl class="new_peo_list">
                                    <dt><span style="color:red">*</span>列控方式：</dt>
                                    <dd>
                                        <div class="fl left_item chose_stare">
                                        	<input type="hidden" name="columnTubeMode" value="公开" />
		                                    <a class="label_btn m_bg_blue">公开</a>
		                                    <a class="label_btn">秘密</a>
                                        </div>
                                    </dd>
                                </dl>

                                <dl class="new_peo_list">
                                    <dt><span style="color:red">*</span>在控状态：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <label>
                                                <select id="isControl" name="isControl" class="sel" datatype="*" nullmsg="请选择在控状态" errormsg="请选择在控状态">
                                                	<c:forEach var="list" items="${isControls}">
		                                                <option value="${list.id }" 
	                                                	<c:if test="${list.id eq controlPerson.isControl}">selected="selected"</c:if>>${list.itemName }</option>
	                                               	</c:forEach>
	                                               	<c:if test="${empty isControls}">
	                                               	<option value="">暂无</option>
	                                               	</c:if>
                                                </select>
                                            </label>
                                        </div>
                                        <div class="fl mgl100 right_item">
                                            <label>
                                                <span><i style="color:red">*</i>列控级别：</span>
                                                <select id="columnTubeLevel" name="columnTubeLevel" class="sel" datatype="*" nullmsg="请选择列控级别" errormsg="请选择列控级别">
                                                    <c:forEach var="list" items="${columnTubeLevels}">
		                                                <option value="${list.id }" 
	                                                	<c:if test="${list.id eq controlPerson.columnTubeLevel}">selected="selected"</c:if>>${list.itemName }</option>
	                                               	</c:forEach>
	                                               	<c:if test="${empty columnTubeLevels}">
	                                               	<option value="">暂无</option>
	                                               	</c:if>
                                                </select>
                                            </label>
                                        </div>
                                    </dd>
                                </dl>

                                <dl class="new_peo_list">
                                    <dt><span style="color:red">*</span>危险等级：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <select class="sel " id="dangerousLevel" name="dangerousLevel" datatype="*" nullmsg="请选择危险等级" errormsg="请选择危险等级">
                                                <c:forEach var="list" items="${dangerousLevels}">
	                                                <option value="${list.id }" 
                                                	<c:if test="${list.id eq controlPerson.dangerousLevel}">selected="selected"</c:if>>${list.itemName }</option>
                                               	</c:forEach>
                                               	<c:if test="${empty dangerousLevels}">
                                               	<option value="">暂无</option>
                                               	</c:if>
                                            </select>
                                        </div>
                                        <div class="fl mgl100 right_item">
                                            <label>
                                                <span class="fl">民警电话：</span>
                                                <input type="text" isnot="true" value="" placeholder="请输入民警电话" name="policeComprehensiveContactI" class="txt_in"
                                                maxlength="11" ignore="ignore" datatype="m" errormsg="请输入正确的民警电话">
                                            </label>
                                        </div>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list" id="choseCategories">
                                    <dt><span style="color:red">*</span>选择类别：</dt>
                                    <dd>
                                        <div class="fl left_item">
                                            <select class="sel " name="dataitemId" id="chose_itemId" onchange="unitIdList('chose_itemId','chose_itemId2','chose_itemId3','2')">
                                             	<c:forEach var="list" items="${dataitemIds}">
	                                                <option value="${list.id }" >${list.itemName }</option>
                                               	</c:forEach>
                                               	<c:if test="${empty dataitemIds}">
                                               	<option value="">暂无</option>
                                               	</c:if>
                                            </select>
                                        </div>
                                        <div class="fl left_item">
                                            <select class="sel " name="dataitemId2" id="chose_itemId2" onchange="userIdBind('chose_itemId','chose_itemId2','chose_itemId3','2')">
                                                <option value="">暂无</option>
                                            </select>
                                        </div>
                                        <div class="fl left_item">
                                            <select class="sel " name="dataitemId3"  id="chose_itemId3">
                                                <option value="">暂无</option>
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
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
                                	<input type="hidden" name="policeUserId" id="policeUserId" disabled="disabled"/>
                                    <dt><span style="color:red">*</span>列控单位：</dt>
                                    <dd>
                                    	<c:if test="${empty isUser}">
	                                        <div class="fl left_item">
	                                            <select class="sel " name="areaUnitId" id="selunitId" onchange="unitIdList('selunitId','selunitId2','seluserId','1')">
	                                           		<c:forEach var="list" items="${units}">
		                                                <option value="${list.id }" >${list.unitName }</option>
	                                               	</c:forEach>
	                                            </select>
	                                        </div>
	                                        <div class="fl left_item">
	                                            <select class="sel" name="townUnitId" id="selunitId2" onchange="userIdBind('selunitId','selunitId2','seluserId','1')">
	                                                <option value="-1">暂无</option>
	                                            </select>
	                                        </div>
	                                        <div class="fl left_item">
	                                            <select class="sel " name="policeUserId" id="seluserId">
	                                                <option value="-1">暂无</option>
	                                            </select>
	                                        </div>
	                                        <div class="fl left_item tab" style="width: 100px">
	                                        	<input id="check" type="checkbox" class="checkItem">
	                                        	<span>选择自己</span>
	                                        </div>
                                        </c:if>
                                    	<c:if test="${!empty isUser}">
	                                        <div class="fl left_item">
	                                            <select class="sel " name=""areaUnitId"" id="unitId">
	                                                <option value="${unit1.id }">${unit1.unitName }</option>
	                                            </select>
	                                        </div>
	                                        <div class="fl left_item">
	                                            <select class="sel" name="townUnitId" id="unitId2">
	                                            	<c:if test="${!empty unit2}">
	                                                	<option value="${unit2.id }">${unit2.unitName }</option>
	                                            	</c:if>
	                                            	<c:if test="${empty unit2}">
	                                                	<option value="-1">暂无</option>
	                                            	</c:if>
	                                            </select>
	                                        </div>
	                                        <div class="fl left_item">
	                                            <select class="sel " name="policeUserId" id="userId">
	                                                <option value="${policeUser.id }">${policeUser.policeName }</option>
	                                            </select>
	                                        </div>
	                                        <div class="fl left_item tab" style="width: 100px">
	                                        	<input id="check" type="checkbox" class="checkItem">
	                                        	<span>选择自己</span>
	                                        </div>
                                        </c:if>
                                    </dd>
                                </dl>
                                <dl class="new_peo_list">
                                    <dt><span style="color:red">*</span>列控理由：</dt>
                                    <dd>
                                        <textarea name="reason" isnot="true" class="fl textarea text_keyup" placeholder="请输入列控理由"  maxlength="200" onkeyup="checkLen(this)"
                                        nullmsg="请输入列控理由" errormsg="请输入列控理由" maxlength="200" datatype="*"></textarea>
                                        <div class="fl mgl10 mgt93 add_btn_wrap">  
											<span class="tips"><i class="m_242"><em class="text_keyup_num">0</em>/200</i>（最多可输入200字）</span>
                                        </div>	
                                    </dd>
                                </dl>
                            </div>
                            <div class="state-save sub_btn_box">
                                <input type="button" onclick="javascript:history.back(-1);" value="关闭" class="back_next" />
                                <input type="submit" value="保存" class="sub_btn"/>
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
    <script type="text/javascript">
    $(function(){
    	$('#check').on('click',function(){
    		if($('#check').prop('checked')){
    			$('#selunitId,#selunitId2,#seluserId').attr('disabled',true);
    			$('#selunitId,#selunitId2,#seluserId').css('background-color','#C8C8C8');
    			$('#policeUserId').attr('disabled',false);
    			$('#policeUserId').val('${sessionScope.user.id}');
    		}else{
    			$('#selunitId,#selunitId2,#seluserId').attr('disabled',false);
    			$('#selunitId,#selunitId2,#seluserId').removeAttr('style');
    			$('#policeUserId').attr('disabled',true);
    			$('#policeUserId').val('');
    		}
    	})
		//初始化列空单位
		var chose_itemIdVal = $("#chose_itemId").find("option:selected").attr("value");
		unitIdList('chose_itemId','chose_itemId2','chose_itemId3','2',chose_itemIdVal); 
		var selunitIdVal = $("#selunitId").find("option:selected").attr("value");
		unitIdList('selunitId','selunitId2','seluserId','1',selunitIdVal);   
		//绑定页面的上传图片的JS
		var Myuploader = new $.MyWebUploader();
		if($(".img_show").length>0){
			$(".upload_btn").each(function(){
				var _btnId = $(this).find(".upd_btn").eq(0).attr("id");
				var _thumbContId = $(".img_box").find("img").eq(0).attr("id");
				var _thumbInputId = $(".img_show").find("input[name='photo']").eq(0).attr("id");
				var _option = {
					btnId: _btnId, //按钮id
					thumbContId:_thumbContId,//关联缩略图的id
					thumbInputId:_thumbInputId
				}
				Myuploader.Init(_option);
			});
		}
		var msg = "请先去字典里添加数据";
		var nation = $('#nation').val();//民族
		var isControl = $('#isControl').val();//在控状态
		var columnTubeLevel = $('#columnTubeLevel').val();//列控级别
		var dangerousLevel = $('#dangerousLevel').val();//危险等级
		var chose_itemId = $('#chose_itemId').val();//人员类别
		var sub_string = "<input type='button' value='保存' class='sub_btn sub_btn_msg' style='color: #fff'/>";
		if(isNull(nation)){
			$('.sub_btn').remove()
			$('.state-save.sub_btn_box').append(sub_string);
			msg = "请先去字典数据里添加民族数据";
		}else if(isNull(isControl)){
			$('.sub_btn').remove()
			$('.state-save.sub_btn_box').append(sub_string);
			msg = "请先去字典数据里添加在控状态数据";
		}else if(isNull(columnTubeLevel)){
			$('.sub_btn').remove()
			$('.state-save.sub_btn_box').append(sub_string);
			msg = "请先去字典数据里添加列控级别数据";
		}else if(isNull(dangerousLevel)){
			$('.sub_btn').remove()
			$('.state-save.sub_btn_box').append(sub_string);
			msg = "请先去字典数据里添加危险等级数据";
		}else if(isNull(chose_itemId)){
			$('.sub_btn').remove()
			$('.state-save.sub_btn_box').append(sub_string);
			msg = "请先去字典数据里添加人员类别数据";
		}
		$('.sub_btn.sub_btn_msg').click(function(){
			layer.msg(msg);
		});
	})
	function isNull(string){
		if(string == undefined || string == null ){
			return true;
		}else{
			if(string == "" || string.length == 0){
				return true;
			}
		}
		return false;
    }
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
   					strVar +="<option value>暂无</option>";
    				for( var i=0; i< _unitId2List.length; i++ ){  
    					 strVar +="<option value="+_unitId2List[i].id+">"+_unitId2List[i].name+"</option>";
    				}
    				$("#" +selTwoId).empty().append(strVar);
    				userIdBind(selID,selTwoId,userIdList,_choseType,firstVal);
   				}else{
   					strVar ="<option value>暂无</option>";
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
		/* var areaId = $("#" + selID).find("option:selected").attr("value"); */
		
		/* if( _choseType == "1" || _choseType == 1 ){
			var _columnUrl ="/admin/unit/getUnitPerson.shtml";
			var dataList = { areaId:areaId,townId:townId };
		}else{
			var _columnUrl = "/admin/dictionary/subSet.shtml";
			var dataList = { id:townId};
		} */
		$.ajax({
  			type:"POST",
  			url:_columnUrl,
  			data: dataList,
  			datatype:"json",
  			success:function(data){ 
  				var strVar = "";
  				var _unitId2List = data.data; 
  				if( _unitId2List !="" && _unitId2List != null && _unitId2List != undefined ){ 
  					strVar +="<option value>暂无</option>";
  					for( var i=0; i< _unitId2List.length; i++ ){ 
  	   					if(_unitId2List[i].id==1){
  	   						continue;
  	   	   				}
      					strVar +="<option value="+_unitId2List[i].id+">"+_unitId2List[i].name+"</option>";
      				}
      				$("#" +userIdList).empty().append(strVar); 
  				}else{
  					strVar ="<option value>暂无</option>";
  					$("#" +userIdList).empty().append(strVar); 
  				}
  				
  			}
  		})
	}
	//身份证输入验证
	function isIdCardKeyUp(obj){
		var _val = $(obj).val();
		_val = _val.replace(/[^\d|xX]/g,"");
		$(obj).val(_val);
		if(_val.length==18 || _val.length==15){
			$.ajax({
	  			type:"POST",
	  			url:"/admin/controlPerson/isIdCard.shtml",
	  			data: {idCard:_val},
	  			datatype:"json",
	  			success:function(data){ 
	  				if( data.code == 200 ){ 
	      				$("#idCardMsg").html(""); 
	  				}else{
	      				$("#idCardMsg").html(data.msg); 
	  				}
	  			}
	  		})
		}else{
			$("#idCardMsg").html(""); 
		}
	}
    </script>
</body>
</html>