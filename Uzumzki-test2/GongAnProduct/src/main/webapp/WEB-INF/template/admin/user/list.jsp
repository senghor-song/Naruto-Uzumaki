<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<title><ruiec:title/></title>
  	<link rel="stylesheet" href="${base }/resources/admin/js/select/select2.css" >
    <link rel="stylesheet" href="${base }/resources/admin/css/layer.css" >
    <link rel="stylesheet" href="${base }/resources/admin/css/common.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/style.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/index.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/quote.css"> 
    <style type="text/css">
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
<body class="bodyCss">
		<jsp:include page="../common/header.jsp"></jsp:include>
        <!---->
        <section>
        	<jsp:include page="../common/leftNav5.jsp"></jsp:include>
            <div class="mainRight">
                <div class="pancl_pd">
                    <form method="GET" action="/admin/user/list.shtml" id="searchform">
                        <div class="tit_menu_list">
                            <a href="/admin/common/main.shtml" class="m_menu_item">
                                <i class="iconfont">&#xe62a;</i>
                                <em>首页</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="javascript:;" class="m_menu_item">
                                <em>权限管理</em>
                            </a>
                            <i class="fl split_gt">&gt;</i>
                            <a href="/admin/user/list.shtml" class="m_menu_item">
                                <em>用户列表</em>
                            </a>
                        </div>
                        <div class="searh_list" id="rylbCount">
                            <div class="fl select_box">
                                <select name="unitId" onchange="findForm()" class="hideThis" id="units">
                                	<option value="">单位</option>
                                	<c:forEach items="${unitlist}" var="list">
                                		<option value="${list.id}" <c:if test="${list.id == unitId}">selected="selected"</c:if>>${list.unitName}</option>
                                	</c:forEach>
                                </select>
                            </div>
                            <div class="fl mgl10 rule-single-select single-select">
                                <select name="search" class="hideThis">
                                    <option value="policeName" <c:if test="${search == 'policeName'}">selected="selected"</c:if>>警员名</option>
                                    <option value="idCard" <c:if test="${search == 'idCard'}">selected="selected"</c:if>>身份证</option>
                                </select>
                            </div>
                            <dl class="mgl20 search_item">
                                <dt>关键词：</dt>
                                <dd class="mgr30">
                                    <input type="text" name="forSearch" class="text_inp" datatype="minName" value="${forSearch}" ignore="ignore" id="forSearch">
                                    <button type="submit" href="javascript:void(0);" class="btn curr fr mgl10" id="search" style="cursor: pointer;">搜索</a>
                                    <button type="button" id="reset" class="btn fr mgl10" onClick="resetBtn()">重置</button>
                                </dd>
                            </dl> 
                        </div>
                        <div class="mgt20 importantC">
                            <div class="m_bg_ef Allcrumb">
                                <div class="fl crumb_item">
                                    <a href="javascript:void(0);" class="item_a" onclick="checkAll(this)">全选</a>
                                    <a href="javascript:void(0);" class="item_a" onclick="del()">删除</a>
                                </div>
                                <div class="fr crumb_item crumb_item_hover">
                                    <a class="item_a" id="handInit">手动刷新</a>
                                    <a href="javascript:void(0);" class="item_a add" val="">新建</a>
                                </div>
                            </div>
                            <div class="mgt15 importantNList">
                                <table class="tab">
                                    <colgroup>
                                        <col width="3%"/>
                                        <col width="17%"/>
                                        <col width="30%"/>
                                        <col width="12%"/>
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>&nbsp;</th>
                                            <th>警员名</th>
                                            <th>所属单位</th>
                                            <th class="textl">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${page.list}" var="list">
                                        	<c:if test="${list.id != 1}">
                                        		<tr>
		                                            <td style="text-align: center"><input type="checkbox" class="checkItem" name="ids" value="${list.id}"></td>
		                                            <td>${list.policeName }</td>
		                                            <td><ruiec:unit var="unit" unitId="${list.unitId}">${unit.unitName}</ruiec:unit></td>
		                                            <td>
		                                                <a href="javascript:void(0);" class="m_blue reset" val="${list.id }">重置密码</a>
		                                                <i class="m_e8">|</i>
		                                                <a href="#" class="m_blue edits" val="${list.id }">编辑</a>
		                                            </td>
		                                        </tr>
                                        	</c:if>
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
    <!--验证-->
    <script src="${base }/resources/admin/js/jquery.form.js"></script>
    <script src="${base }/resources/admin/js/Validform_v5.3.2/Validform_v5.3.2.js"></script> 
    <!--自写-->
    <script src="${base }/resources/admin/js/layout.js"></script>
    <!--拖拽-->
    <script src="${base }/resources/admin/js/gridster/jquery.gridster.min.js"></script>
    <!---下拉框 -->
    <script src="${base }/resources/admin/js/common.js"></script>
    <script type="text/javascript" src="${base }/resources/admin/js/select/select2.js"></script>
    <!--日期-->
    <script src="${base }/resources/admin/js/My97DatePicker/lang/zh-cn.js"></script>
    <script src="${base }/resources/admin/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function () {
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
        	$('#units').select2();
            $(".add,.edits").on('click', box);
	   		$('.reset').on('click',function(){
	   			$.ajax({
	    		    url:'/admin/user/reset.shtml',
	    		    type:'POST',
	    		    data:{
	    		        id:$(this).attr('val')
	    		    },
	    		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    		    success:function(data,textStatus,jqXHR){
	    		    	if(data.code == '200'){
	    		    		layer.msg(data.msg,{icon: 1});
	    		    	}else{
	    		    		layer.msg(data.msg,{icon: 2});
	    		    	}
	    		    },
	    		})
	   		})
	   		$('select[name=search]').val($('select[name=search]').val()).trigger('change');
        });
        function box(){
        	$(this).blur();
           	var id = $(this).attr('val');
           	var ti = '';
           	if(id==''){
           		ti = '添加用户'
           	}else{
           		ti = '编辑用户';
           	}
      			var index =layer.open({
          		    type: 2,
          		    title: ti, //不显示标题
          			area: ['600px', '500px'], //宽高
          		    content: "/admin/user/edit.shtml?id="+id  //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
          		});
            	$("#form1").Validform();
       	}
         $('select[name=search]').on('change',function(){
        	var search = $(this).find('option:selected').val();
        	if(search=='policeName'){
        		$("input[name='forSearch']").attr('datatype','minName');
        		$("input[name='forSearch']").attr('errormsg','请输入中文');
        		$("input[name='forSearch']").removeAttr('onkeyup');
        		$("input[name='forSearch']").removeAttr('maxlength','18');
        	}else{
        		$("input[name='forSearch']").attr('onkeyup','idCardKeyUp($(this))');
        		$("input[name='forSearch']").attr('maxlength','18');
        		$("input[name='forSearch']").removeAttr("datatype",'isIDCard');
        		$("input[name='forSearch']").removeAttr('errormsg');
        	}
        })
        function findForm(){
	    	$('#searchform').submit();
	   	}
    </script>
</body>
</html>