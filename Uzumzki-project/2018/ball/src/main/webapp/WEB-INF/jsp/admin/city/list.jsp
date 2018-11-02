<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
                                {field: 'cityno', title: '编号', sort: true},
                                {field: 'initial', title: '字母', sort: true},
                                {field: 'hotflag', title: '热门', sort: true},
                                {field: 'city', title: '城市', sort: true},
                                {field: 'map', title: '地图', sort: true},
                                {field: 'distinct', title: '下属区县', sort: true},
                                {field: 'cityLogSum', title: '日志', sort: true},
                                {fixed: 'right', title: '操作', align:'center', toolbar: '#cityBar'}
                            ]
                        ]
                    },
                    menuItem: {
                    	item0: {
                            name: "区县", callback: function (key, opt) {
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
                                             	{ field: 'districtno', title: '编号', sort: true },
                                                { field: 'district', title: '区县名', sort: true },
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
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
                    }
                });
                
            }
        }
        tableObj.init();
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
   					tr.find("td").eq(8).find("div").find("a").eq(0).text("取消热门");
   					tr.find("td").eq(8).find("div").find("a").eq(0).attr("lay-event","noHot");
   				} else if (layEvent == 'noHot') {
   					obj.update({ hotflag: '否' });
   					layer.msg("取消热门");
   					check = 0;
   					tr.find("td").eq(8).find("div").find("a").eq(0).text("设为热门");
   					tr.find("td").eq(8).find("div").find("a").eq(0).attr("lay-event","yesHot");
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
					obj.update({ map: '是' });
   					layer.msg("设为地图");
   					check = 1;
   					tr.find("td").eq(8).find("div").find("a").eq(1).text("取消地图");
   					tr.find("td").eq(8).find("div").find("a").eq(1).attr("lay-event","noMap");
   				} else if (layEvent == 'noMap') {
					obj.update({ map: '否' });
   					layer.msg("取消地图");
   					check = 0;
   					tr.find("td").eq(8).find("div").find("a").eq(1).text("设为地图");
   					tr.find("td").eq(8).find("div").find("a").eq(1).attr("lay-event","yesMap");
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
			}/* 
			$.reload(tableObj.obj); */
        });
    });
</script>
<script type="text/html" id="cityBar">
  <!-- 这里同样支持 laytpl 语法，如： -->
  {{# if(d.hotflag == '否'){ }}
    <a class="layui-btn layui-btn-xs" lay-event="yesHot" style="color: #fff;">设为热门</a>
  {{# } }}
  {{# if(d.hotflag == '是'){ }}
    <a class="layui-btn layui-btn-xs" lay-event="noHot" style="color: #fff;">取消热门</a>
  {{# } }}
  {{# if(d.map == '否'){ }}
    <a class="layui-btn layui-btn-xs" lay-event="yesMap" style="color: #fff;">设为地图</a>
  {{# } }}
  {{# if(d.map == '是'){ }}
    <a class="layui-btn layui-btn-xs" lay-event="noMap" style="color: #fff;">取消地图</a>
  {{# } }}
</script>
<div class="contextMenuDialog hide" id="item0">
    <div class="card-body">
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