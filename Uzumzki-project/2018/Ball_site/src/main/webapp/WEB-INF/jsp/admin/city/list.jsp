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
    <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="7"></aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper pt-3">
        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card card-secondary">
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
<script>
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
                $("#btnSearch").unbind().on("click", function () {
                    $.reload(self.obj);
                });
            },
            tableList: function () {
                var self = this;
                self.obj = $.tableObject({
                    tableId: 'tableList',
                    tableOption: {
                        url: '/WebBackAPI/admin/city/list',
                        page: true,
                        height: $(window).height() - 92,
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
                                {field: 'initial', title: '字母', sort: true, width:80},
                                {field: 'hotflag', title: '热门', width:80, sort: true},
                                {field: 'city', title: '城市', sort: true},
                                {field: 'mapflag', title: '地图', width:80, sort: true},
                                {field: 'distinct', title: '下属区县', sort: true, width:120},
                                {field: 'cityLogSum', title: '日志', sort: true, width:80},
                                {field: 'coachPrice', title: '缺省网球陪练(元)', edit : "text"},
                                {field: 'cityflag', title: '入口', width:100, sort: true},
        						<c:if test="${btn323 == 1}">
                                	{fixed: 'right', title: '操作', align:'center', toolbar: '#cityBar'}
                                </c:if>
                            ]
                        ]
                    },
                    menuItem: {
						<c:if test="${btn321 == 1}">
                    	item0: {
                            name: "区县", callback: function (key, opt) {
                            	$("#cityid").val($(this).find("td").eq(0).attr('title'));
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tableDistrictList',
                                    tableOption: {
                                        url: '/WebBackAPI/admin/city/district/list?id=' + $(this).find("td").eq(0).attr('title'),
                                        page: false,
                                        height: $("#tableDistrictList").parents(".layui-layer-content").height() - 30,
                                        where: {},
                                        cols: [
                                            [
                                       			{ field: 'id', title: 'ID', hide:true},
                                             	{ field: 'districtno', title: '编号', sort: true },
                                                { field: 'district', title: '区县名', edit : "text"},
                                            	{ fixed: 'right', title: '操作', align:'center', toolbar: '#districtBar', width:80}
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                        </c:if>
						<c:if test="${btn322 == 1}">
                        item1: {
                            name: "日志", callback: function (key, opt) {
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tableCityLog',
                                    tableOption: {
                                        url: '/WebBackAPI/admin/city/cityLog/list?id=' + $(this).find("td").eq(0).attr('title'),
                                        page: false,
                                        height: $("#tableCityLog").parents(".layui-layer-content").height() - 30,
                                        where: {},
                                        cols: [
                                            [
                                             	{ field: 'cityno', title: '日志时间', sort: true },
                                                { field: 'initial', title: '操作人', sort: true },
                                                { field: 'hotflag', title: '内容', sort: true },
                                            ]
                                        ]
                                    }
                                });
                            }
                        }
                        </c:if>
                    }
                });
                
            }
        }
        tableObj.init();
        layui.table.on('edit(tableList)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
       		$.ajax({
				type : "POST", //提交方式  
				url : "/WebBackAPI/admin/city/updateCoach",//路径  
				data : {
					id : obj.data.id,
					price : obj.value
				},//数据，这里使用的是Json格式进行传输  
				dataType : "json",
				success : function(result) {//返回数据根据结果进行相应的处理  

				}
			});
       	});
        layui.table.on('tool(tableList)', function(obj) {
   			var data = obj.data;
   			var layEvent = obj.event;
   			var tr = obj.tr;
			var check = 0;
			var logSum = data.cityLogSum + 1;
			obj.update({ cityLogSum: logSum });
			if (layEvent == 'yesHot' || layEvent == 'noHot') {
				if (layEvent == 'yesHot') {
					obj.update({ hotflag: '是' });
   					layer.msg("设为热门");
   					check = 1;
   					tr.find("td").eq(9).find("div").find("a").eq(1).text("取消热门");
   					tr.find("td").eq(9).find("div").find("a").eq(1).attr("lay-event","noHot");
   				} else if (layEvent == 'noHot') {
   					obj.update({ hotflag: '否' });
   					layer.msg("取消热门");
   					check = 0;
   					tr.find("td").eq(9).find("div").find("a").eq(1).text("设为热门");
   					tr.find("td").eq(9).find("div").find("a").eq(1).attr("lay-event","yesHot");
   				} 
				$.ajax({
  					type : "POST", //提交方式  
  					url : "/WebBackAPI/admin/city/uodateHot",//路径  
  					data : {
  						id : data.id,
  						check : check
  					},//数据，这里使用的是Json格式进行传输  
  					dataType : "json",
  					success : function(result) {//返回数据根据结果进行相应的处理  
  	
  					}
   				});
			} else if (layEvent == 'yesMap' || layEvent == 'noMap') {
				if (layEvent == 'yesMap') {
					obj.update({ mapflag: '是' });
   					layer.msg("设为地图");
   					check = 1;
   					tr.find("td").eq(9).find("div").find("a").eq(2).text("取消地图");
   					tr.find("td").eq(9).find("div").find("a").eq(2).attr("lay-event","noMap");
   				} else if (layEvent == 'noMap') {
					obj.update({ mapflag: '否' });
   					layer.msg("取消地图");
   					check = 0;
   					tr.find("td").eq(9).find("div").find("a").eq(2).text("设为地图");
   					tr.find("td").eq(9).find("div").find("a").eq(2).attr("lay-event","yesMap");
   				}
				$.ajax({
  					type : "POST", //提交方式  
  					url : "/WebBackAPI/admin/city/uodateMap",//路径  
  					data : {
  						id : data.id,
  						check : check
  					},//数据，这里使用的是Json格式进行传输  
  					dataType : "json",
  					success : function(result) {//返回数据根据结果进行相应的处理  
  	
  					}
   				});
			} else if (layEvent == 'yesCity' || layEvent == 'noCity') {
                if (layEvent == 'yesCity') {
                    obj.update({ cityflag: '开放' });
                    layer.msg("开放入口");
                    check = 1;
                    tr.find("td").eq(9).find("div").find("a").eq(0).text("关闭入口");
                    tr.find("td").eq(9).find("div").find("a").eq(0).attr("lay-event","noCity");
                } else if (layEvent == 'noCity') {
                    obj.update({ cityflag: '关闭' });
                    layer.msg("关闭入口");
                    check = 0;
                    tr.find("td").eq(9).find("div").find("a").eq(0).text("开放入口");
                    tr.find("td").eq(9).find("div").find("a").eq(0).attr("lay-event","yesCity");

                    if(data.hotflag == "是"){
                    	obj.update({ hotflag: '否' });
       					tr.find("td").eq(9).find("div").find("a").eq(1).text("设为热门");
       					tr.find("td").eq(9).find("div").find("a").eq(1).attr("lay-event","yesHot");
                        $.ajax({
          					type : "POST", //提交方式  
          					url : "/WebBackAPI/admin/city/uodateHot",//路径  
          					data : {
          						id : data.id,
          						check : 0
          					},//数据，这里使用的是Json格式进行传输  
          					dataType : "json",
          					success : function(result) {//返回数据根据结果进行相应的处理  
          	
          					}
           				});
                    }
                }
                $.ajax({
                    type : "POST", //提交方式
                    url : "/WebBackAPI/admin/city/uodateCityFlag",//路径
                    data : {
                        id : data.id,
                        check : check
                    },//数据，这里使用的是Json格式进行传输
                    dataType : "json",
                    success : function(result) {//返回数据根据结果进行相应的处理

                    }
                });
            }
        });

        $("#saveDistrict").on("click", function () {
        	var cityid = $("#cityid").val();
        	var district = $("#district").val();
        	if(district == ""){
            	layer.msg("请填写名称");
            	return;
        	}
        	var url = '/WebBackAPI/admin/city/district/list?id='+cityid;
        	$.ajax({
                type : "POST", //提交方式
                url : "/WebBackAPI/admin/city/district/add",//路径
                data : {
                    cityid : cityid,
                    name : district
                },//数据，这里使用的是Json格式进行传输
                dataType : "json",
                success : function(result) {//返回数据根据结果进行相应的处理
                	if(result.code == 200){
	                	layer.msg("添加成功");
	                	$("#district").val("");
	                	layui.table.reload("tableDistrictList", { url: url,where: {}})
                	}else {
	                	layer.msg(result.msg);
                	}
                }
            });
        });
        
        layui.table.on('edit(tableDistrictList)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
        	var districtName = obj.tr.find("td").eq(2).attr("title");
        	layer.confirm('确定要将区县' + districtName + '修改为' + obj.value, function(index){
			     layer.close(index);
			     //向服务端发送删除指令
			     $.ajax({
					type : "POST", //提交方式  
					url : "/WebBackAPI/admin/city/district/update",//路径  
					data : {
						districtid : obj.data.id,
						name : obj.value
					},//数据，这里使用的是Json格式进行传输  
					dataType : "json",
					success : function(result) {//返回数据根据结果进行相应的处理  
						if(result.code == 400){
		                	layer.msg(result.msg);
		    				obj.tr.find("td").eq(2).find('input').val(districtName);
		    				obj.tr.find("td").eq(2).find('div').html(districtName);
		                	obj.update({ district: districtName });
	                	}
					}
				});
			},function(index){
				obj.tr.find("td").eq(2).find('input').val(districtName);
				obj.tr.find("td").eq(2).find('div').html(districtName);
            	obj.update({ district: districtName });
			    layer.close(index);
			});
       		
       	});
        
        layui.table.on('tool(tableDistrictList)', function(obj) {
   			var data = obj.data;
   			var layEvent = obj.event;
			if (layEvent == 'delDistrict') {
				layer.confirm('真的删除行么', function(index){
				     obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
				     layer.close(index);
				     //向服务端发送删除指令
				     $.ajax({
	  					type : "POST", //提交方式  
	  					url : "/WebBackAPI/admin/city/district/del",//路径  
	  					data : {
	  						districtid : data.id,
	  					},//数据，这里使用的是Json格式进行传输  
	  					dataType : "json",
	  					success : function(result) {//返回数据根据结果进行相应的处理  
		                	layer.msg("添加成功");
	  						obj.del();
	  					}
	   				});
				});
            }
        });
    });
</script>
<script type="text/html" id="cityBar">
  <!-- 这里同样支持 laytpl 语法，如： -->
  {{# if(d.cityflag == '关闭'){ }}
    <a class="layui-btn layui-btn-xs" lay-event="yesCity" style="color: #fff;">开放入口</a>
  {{# } }}
  {{# if(d.cityflag == '开放'){ }}
    <a class="layui-btn layui-btn-xs" lay-event="noCity" style="color: #fff;">关闭入口</a>
  {{# } }}
  {{# if(d.hotflag == '否'){ }}
    <a class="layui-btn layui-btn-xs" lay-event="yesHot" style="color: #fff;">设为热门</a>
  {{# } }}
  {{# if(d.hotflag == '是'){ }}
    <a class="layui-btn layui-btn-xs" lay-event="noHot" style="color: #fff;">取消热门</a>
  {{# } }}
  {{# if(d.mapflag == '否'){ }}
    <a class="layui-btn layui-btn-xs" lay-event="yesMap" style="color: #fff;">设为地图</a>
  {{# } }}
  {{# if(d.mapflag == '是'){ }}
    <a class="layui-btn layui-btn-xs" lay-event="noMap" style="color: #fff;">取消地图</a>
  {{# } }}
</script>

<script type="text/html" id="districtBar">
	<a class="layui-btn layui-btn-xs" lay-event="delDistrict" style="color: #fff;">删除</a>
</script>
<div class="contextMenuDialog hide" id="item0">
    <div class="card-body">
		<div class="card-tools" style="padding-bottom:  10px;">
			<div class="input-group input-group-sm" style="width: 350px;">
				<input id="cityid" type="hidden" >
				区县：
				<input class="form-control float-right" id="district" name="district" type="text" placeholder="区县名">

				<div class="input-group-append">
					<button class="btn btn-default" id="saveDistrict" type="button">
						<i class="fa fa-plus"></i> 新增
					</button>
				</div>
			</div>
		</div>
        <div class="row">
            <table id="tableDistrictList"></table>
        </div>
    </div>
</div>

<div class="contextMenuDialog hide" id="item1">
	<div class="card-body">
		<div class="row">
            <table id="tableCityLog"></table>
        </div>
    </div>
</div>

</body>

</html>