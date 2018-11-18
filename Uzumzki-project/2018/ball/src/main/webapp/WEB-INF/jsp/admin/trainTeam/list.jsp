<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>小易运维</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="/WebBackAPI/admin/static/image/logo.png" type="image/x-icon"/>
    <link href="/WebBackAPI/admin/static/css/site.css" rel="stylesheet">
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom" id="topSidebar"></nav>
    <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="21"></aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper pt-3">
        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card card-secondary">
                        	<div class="card-header">
                                <h3 class="card-title">
                                    <div class="row">
										<c:if test="${btn235 == 1}">
	                                        <div class="col-lg-9 btncaozuo">
	                                            <button class="btn btn-primary btn-sm" id="trainEnter">入驻申请</button>
                                        	</div>
                       					</c:if>			
                                        <div class="input-group input-group-sm float-right col-lg-3" style="width: 350px;">
											<select class="form-control float-right" id="trainSelectType">
												<option value="0">城市</option>
												<option value="1">机构名</option>
											</select> 
											<input class="form-control float-right" id="trainKeyword" name="table_search" type="text"
												placeholder="请输入关键字" maxlength="20">
						
											<div class="input-group-append">
												<button class="btn btn-default" id="trainSearch" type="submit">
													<i class="fa fa-search"></i> 搜索
												</button>
											</div>
										</div>
                                    </div>
                                </h3>
                            </div>
                            <div class="card-body table-responsive p-0">
                                <table id="tableList">
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->
        </section>
        <!-- /.content -->
    </div>


    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->
</div>

<!-- jQuery -->
<script src="/WebBackAPI/admin/static/plugins/jquery/jquery.min.js"></script>
<script src="/WebBackAPI/admin/static/js/layout.js"></script>
<script src="/WebBackAPI/admin/static/plugins/jQueryUI/jquery-ui.min.js"></script>
<script>
    $.widget.bridge('uibutton', $.ui.button)
</script>
<script src="/WebBackAPI/admin/static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/WebBackAPI/admin/static/js/raphael-min.js"></script>
<script src="/WebBackAPI/admin/static/plugins/morris/morris.min.js"></script>
<script src="/WebBackAPI/admin/static/plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="/WebBackAPI/admin/static/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="/WebBackAPI/admin/static/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="/WebBackAPI/admin/static/plugins/knob/jquery.knob.js"></script>
<script src="/WebBackAPI/admin/static/plugins/moment/moment.min.js"></script>
<script src="/WebBackAPI/admin/static/plugins/daterangepicker/daterangepicker.js"></script>
<script src="/WebBackAPI/admin/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/WebBackAPI/admin/static/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script src="/WebBackAPI/admin/static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="/WebBackAPI/admin/static/plugins/fastclick/fastclick.js"></script>
<script src="/WebBackAPI/admin/static/plugins/js/pages/dashboard.js"></script>
<script src="/WebBackAPI/admin/static/plugins/js/demo.js"></script>
<script src="/WebBackAPI/admin/static/js/echarts.min.js"></script>
<script src="/WebBackAPI/admin/static/js/jqBootstrapValidation-1.3.7.min.js"></script>
<script src="/WebBackAPI/admin/static/plugins/layui/layui.all.js"></script>
<script src="/WebBackAPI/admin/static/plugins/jQuery-contextMenu/jquery.contextMenu.min.js"></script>
<script src="/WebBackAPI/admin/static/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/WebBackAPI/admin/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/WebBackAPI/admin/static/js/jq-ext.js"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=ITcG0S4URK9aokGSOhTNnSXCO9o7fK8D"></script>
<script>
var map=null;
var mapmarker=null;
    $(function () {

        var tableObj = {
            obj: null,
            init: function () {
                var self = this;
                self.tableList();
                self.search();
            },
            search: function () {
                var self = this;
                $("#trainSearch").unbind().on("click", function () {
                    $.reload(self.obj);
                });
            },
            tableList: function () {
                var self = this;
                self.obj = $.tableObject({
                    tableId: 'tableList',
                    tableOption: {
                        url: '/WebBackAPI/admin/trainTeam/list',
                        page: true,
                        height: $(window).height() - 140,
                        where: {
                            selectType: function () {
                                return $("#trainSelectType").val()
                            },
                            keyword: function () {
                                return $("#trainKeyword").val()
                            }
                        },
                        cols: [
                            [
                    			{field: 'id', title: 'id', hide:true},
                                {field: 'city', title: '城市', sort: true},
                                {field: 'title', title: '机构', sort: true},
                                {field: 'typeFlag', title: '状态', sort: true},
                                {field: 'lngAndLat', title: '经纬', sort: true},
                                {field: 'phone', title: '电话', sort: true},
                                {field: 'level', title: '评级', sort: true},
                                {field: 'levelTime', title: '当前评级', sort: true},
                                {field: 'teachClass', title: '类型', hide:true},
                                {field: 'trainCoachSum', title: '教练', sort: true},
                                {field: 'trainCourseSum', title: '课程', sort: true},
                                {field: 'dayPhoneSum', title: '近60日电话', sort: true},
                                {field: 'trainTeamFeedbackSum', title: '线下报名反馈', sort: true},
                                {field: 'dayCommentSum', title: '近60日评价', sort: true},
                                {field: 'trainTeamLogSum', title: '日志', sort: true},
                                {field: 'dayUseSum', title: '平台概合', sort: true},
                 				{field: 'lng', title: 'lng', hide:true},
                 				{field: 'lat', title: 'lat', hide:true},
                            ]
                        ]
                    },
                    menuItem: {
						<c:if test="${btn221 == 1}">
                        item1: {
                            name: "日志", callback: function (key, opt) {
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tableTrainTeamLog',
                                    tableOption: {
                                        url: '/WebBackAPI/admin/trainTeam/log/list?id='+$(this).find("td").eq(0).attr('title'),
                                        page: false,
                                        height: $("#tableTrainTeamLog").parents(".layui-layer-content").height() - 30,
                                        where: {},
                                        cols: [
                                            [
                                                { field: 'createTime', title: '时间', sort: true },
                                                { field: 'name', title: '操作人', sort: true },
                                                { field: 'content', title: '内容', sort: true },
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                        </c:if>
						<c:if test="${btn232 == 1}">
                        item2: {
                            name: "线下交易反馈", callback: function (key, opt) {
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tableTrainTeamFeedback',
                                    tableOption: {
                                        url: '/WebBackAPI/admin/trainTeam/feedback/list?id='+$(this).find("td").eq(0).attr('title'),
                                        page: false,
                                        height: $("#tableTrainTeamFeedback").parents(".layui-layer-content").height() - 30,
                                        where: {},
                                        cols: [
                                            [
	                                             { field: 'createTime', title: '时间', sort: true },
	                                             { field: 'appnickname', title: '报告客户', sort: true },
	                                             { field: 'content', title: '内容', sort: true },
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                        </c:if>
						<c:if test="${btn233 == 1}">
                        item3: {
                            name: "调整评级", callback: function (key, opt) {
                            	$.ajax({  
                                    type : "POST",  //提交方式  
                                    url : "/WebBackAPI/admin/trainTeam/updateLevel",//路径  
                                    data : {  
                                        "id" : $(this).find("td").eq(0).attr('title'),
                                    },//数据这里使用的是Json格式进行传输  
                                    dataType:"html",
                                    success : function(result) {//返回数据根据结果进行相应的处理  
                                   		 layer.open({
                                            type: 1,
                                            area: [$(document).width() + 'px', '200px'],
                                            offset: 'b',
                                            title: opt.items[key].name,
                                            resize: true,
                                            anim: 1,
                                            content: result,
                                            maxmin: false,
                                            shadeClose: true,
                                            cancel: function (index, layero) {
                                                layer.closeAll(); 
                                            	$(".contextMenuDialog").addClass("hide");
                                    			$.reload(tableObj.obj);
                                            },
                                            end: function () {
                                                layer.closeAll(); 
                                                $(".contextMenuDialog").addClass("hide");
                                    			$.reload(tableObj.obj);
                                            }
                                        });
                                    }  
                                });
                            }
                        },
                        </c:if>
						<c:if test="${btn234 == 1}">
                        item4: {
                            name: "编辑", callback: function (key, opt) {
                            	$.showAjaxContent("编辑", "35%", "/WebBackAPI/admin/trainTeam/edit", $(this).find("td").eq(0).attr('title'));
                            	var lng = $(this).find("td").eq(16).attr('title');
                            	var lat = $(this).find("td").eq(17).attr('title');
                            	$("#citymapDiv").removeClass("hide");
                            	layer.open({
                             	  title: "位置", 
                				  type: 1,
                				  anim: 1,
                				  shade: 0.3,
                				  closeBtn: 0,
                				  offset: '20px',
                				  title: false,
                				  move: false,
                	              shadeClose : true,
                				  resize: false,
                				  area: ['30%', '50%'],
                				  content: $('#citymap'),
                                  cancel: function (index, layero) {
                                	  layer.closeAll();
                                      $(".contextMenuDialog").addClass("hide");
                          			  $.reload(self.obj);
                                  },
                                  end: function () {
                                	  layer.closeAll();
                                      $(".contextMenuDialog").addClass("hide");
                          			  $.reload(self.obj);
                                  },
                				  success: function(){
                					    map = new BMap.Map("citymap");    // 创建Map实例
	                					map.centerAndZoom(new BMap.Point(114.056386, 22.592976), 15);  // 初始化地图,设置中心点坐标和地图级别
                						//添加地图类型控件
                						map.addControl(new BMap.MapTypeControl({
                							mapTypes:[
                						        BMAP_NORMAL_MAP,
                						        BMAP_HYBRID_MAP
                						    ]}));	  
                						map.setCurrentCity("深圳");          // 设置地图显示的城市 此项是必须设置的
                						map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
                						
                						if(mapmarker != null){
                	   				        map.removeOverlay(mapmarker);
                	   					}
               	   			        	var point = new BMap.Point(lng, lat);
               	   			      		map.centerAndZoom(point, 16);
               	   			      		var marker = new BMap.Marker(point);// 创建标注
               	   			      		mapmarker = marker;
               	   			      		map.addOverlay(marker);// 将标注添加到地图中
                				   }
                				});
                            }
                        },
                        </c:if>
						<c:if test="${btn236 == 1}">
                        item5: {
                            name: "教练", callback: function (key, opt) {
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tableTrainTeamCoach',
                                    tableOption: {
                                        url: '/WebBackAPI/admin/trainTeam/coach/list?id='+$(this).find("td").eq(0).attr('title'),
                                        page: false,
                                        height: $("#tableTrainTeamCoach").parents(".layui-layer-content").height() - 30,
                                        where: {},
                                        cols: [
                                            [
	                                             { field: 'coachName', title: '教练', sort: true },
	                                             { field: 'manager', title: '身份', sort: true },
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                        </c:if>
                    }
                });
            }
        }
        tableObj.init();

        $("#trainEnter").on("click", function () {
            var obj = $("#button1");
            obj.removeClass("hide");
            layer.open({
                type: 1,
                area: ['100%', '500px'],
                offset: 'b',
                title: '入驻申请',
                resize: true,
                anim: 1,
                shadeClose : true,
                content: obj,
                cancel: function (index, layero) {
                    $(".contextMenuDialog").addClass("hide");
                    layer.closeAll(); 
	            },
	        	end: function () {
                    $(".contextMenuDialog").addClass("hide");
                    layer.closeAll(); 
	        	}
            });
            trainEnterTableInit(0);
       });
       function trainEnterTableInit(checkFlag){
    	   var tableTrainEnter = {
   	            obj: null,
   	            init: function () {
   	                var self = this;
   	                self.tableList();
   	                self.search();
   	            },
                   search: function () {
                       var self = this;
                       $("#btnSearch").unbind().on("click", function () {
                           $.reload(self.obj);
                       });
                   },
                   tableList: function () {
                       var self = this;
                       self.obj = $.tableObject({
                           tableId: 'tableAllTrainEnter',
                           tableOption: {
                               url: '/WebBackAPI/admin/trainTeam/trainEnter/list?checkFlag='+checkFlag,
                               page: true,
                               height: 370,
                               where: {
                                   selectType: function () {
                                       return $("#selectType").val()
                                   },
                                   keyword: function () {
                                       return $("#keyword").val()
                                   }
                               },
                               cols: [
                                   [
                        			   {field: 'id', title: 'id', hide:true},
                                       {field: 'createTime', title: '申请时间', sort: true},
                                       {field: 'appnickname', title: '操作人', sort: true},
                                       {field: 'phone', title: '绑定', sort: true},
                                       {field: 'title', title: '机构名', sort: true},
                                       {field: 'address', title: '城市', sort: true},
                                       {field: 'mainName', title: '负责人', sort: true},
                                       {field: 'mainPhone', title: '负责人电话', sort: true},
                                       {field: 'teachClass', title: '培训科目', sort: true},
                                       {field: 'checkFlag', title: '审核状态', sort: true},
                                       {field: 'rname', title: '审核人', sort: true},
                                       {field: 'checkTime', title: '审核时间', sort: true},
                                       {field: 'content', title: '意见', edit : "text"},
                                       {fixed: 'right', title: '操作', align:'center', toolbar: '#trainEnterBar'}
                                   ]
                               ]
                           }
                       });
                   }
               }
	    	    $("#citymapDiv").removeClass("hide");
	       		layer.open({
	        	  title: "位置", 
				  type: 1,
				  anim: 1,
				  shade: 0,
				  closeBtn: 0,
				  offset: 't',
				  move: false,
	              shadeClose : true,
				  resize: false,
				  area: ['30%', '45%'],
				  content: $('#citymap'),
				  success: function(){
					    map = new BMap.Map("citymap");    // 创建Map实例
						map.centerAndZoom(new BMap.Point(114.056386,22.592976), 20);  // 初始化地图,设置中心点坐标和地图级别
						//添加地图类型控件
						map.addControl(new BMap.MapTypeControl({
							mapTypes:[
						        BMAP_NORMAL_MAP,
						        BMAP_HYBRID_MAP
						    ]}));	  
						map.setCurrentCity("深圳");          // 设置地图显示的城市 此项是必须设置的
						map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
				   }
				});
	       		
				tableTrainEnter.init();
				layui.table.on('tool(tableAllTrainEnter)', function(obj) {
		   			var data = obj.data;
		   			var layEvent = obj.event;
		   			var tr = obj.tr;
		   			var content = tr.find("td").eq(12).find("div").text();
		   	
		   			// 判断意见是否填写
		   			if (content != '') {
		   				var check = 0;
		   				if (layEvent == 'yes') {
		   					layer.msg("通过审核");
		   					check = 1;
		   				} else if (layEvent == 'no') {
		   					layer.msg("无效机构");
		   					check = 2;
		   				}
		   				$.ajax({
		   					type : "POST", //提交方式  
		   					url : "/WebBackAPI/admin/trainTeam/trainEnter/check",//路径  
		   					data : {
		   						id : data.id,
		   						check : check,
		   						content : content
		   					},//数据，这里使用的是Json格式进行传输  
		   					dataType : "json",
		   					success : function(result) {//返回数据根据结果进行相应的处理  
		   	
		   					}
		   				});
		   				$.reload(tableTrainEnter.obj);
		   			} else {
		   				layer.msg("请填写意见后操作!");
		   			}
		   		});
				//监听行单击事件
   				layui.table.on('row(tableAllTrainEnter)', function(obj){
   					if(mapmarker != null){
   				        map.removeOverlay(mapmarker);
   					}
   					
   			        if(obj.data.lng != '' && obj.data.lat != ''){
   			        	var point = new BMap.Point(obj.data.lng, obj.data.lat);
   			      		map.centerAndZoom(point, 16);
   			      		var marker = new BMap.Marker(point);// 创建标注
   			      		mapmarker = marker;
   			      		map.addOverlay(marker);// 将标注添加到地图中
   			        }else{
   			        	layer.msg("暂无地址信息");
   			        }
   				});
        }
		$("#selectType").on("change", function () {
			trainEnterTableInit($("#selectType").val());
        });
	});
</script>


<script type="text/html" id="trainEnterBar">
  <!-- 这里同样支持 laytpl 语法，如： -->
  {{# if(d.checkFlag == '待核'){ }}
    <a class="layui-btn layui-btn-xs" lay-event="yes" style="color: #fff;" id="yes">通过</a>
    <a class="layui-btn layui-btn-xs" lay-event="no" style="color: #fff;" id="no">无效</a>
  {{# } }}
</script>

<div id="citymapDiv" class="contextMenuDialog otherDialog hide">
    <div class="row">
        <div class="col-12" id="citymap" style="width:100%;height: 100%;">
        </div>
    </div>
</div>

<div class="contextMenuDialog hide" id="button1">
    <div class="card-body">
	<div class="card-tools" style="padding-bottom: 10px;">
		<div class="input-group input-group-sm" style="width: 150px;">
			审核状态: <select class="form-control float-right" id="selectType" ">
				<option value="0">待核</option>
				<option value="1">通过</option>
				<option value="2">无效</option>
			</select>
		</div>
	</div>
	<div class="row">
		<table id="tableAllTrainEnter"></table>
	</div>
	</div>
</div>

<div class="contextMenuDialog hide" id="item1">
    <div class="card-body">
        <div class="row">
            <table id="tableTrainTeamLog"></table>
        </div>
    </div>
</div>


<div class="contextMenuDialog hide" id="item2">
    <div class="card-body">
        <div class="row">
            <table id="tableTrainTeamFeedback"></table>
        </div>
    </div>
</div>

<div class="contextMenuDialog hide" id="item3">
    <div class="card-body">
        <div class="row">
            <table id="updateLevel"></table>
        </div>
    </div>
</div>

<div class="contextMenuDialog hide" id="item5">
    <div class="card-body">
        <div class="row">
            <table id="tableTrainTeamCoach"></table>
        </div>
    </div>
</div>
</body>

</html>