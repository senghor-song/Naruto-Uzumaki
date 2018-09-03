<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>后台管理系统</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/WebRelease/admin/static/css/site.css" rel="stylesheet">
</head>

<body class="hold-transition sidebar-mini">
    <div class="wrapper">
        <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom" id="topSidebar"></nav>
        <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="28"></aside>

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
                                            <div class="col-lg-4 btncaozuo">
                                                <button class="btn btn-primary btn-sm" id="importData">添加</button>
                                            </div>
                                        </div>
                                    </h3>
                                    <div class="card-tools">
                                        <div class="input-group input-group-sm" style="width: 350px;">
                                            <select class="form-control float-right" id="selectType">
                                                <option value="0">编号</option>
                                                <option value="1">标题</option>
                                            </select>
                                            <input class="form-control float-right" id="keyword" name="table_search" type="text" placeholder>

                                            <div class="input-group-append">
                                                <button class="btn btn-default" id="btnSearch" type="submit">
                                                    <i class="fa fa-search"></i> 搜索
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="card-body table-responsive p-0">
                                    <table id="tableList"></table>
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
    <script src="/WebRelease/admin/static/plugins/jquery/jquery.min.js"></script>
    <script src="/WebRelease/admin/static/js/layout.js"></script>
    <script src="/WebRelease/admin/static/plugins/jQueryUI/jquery-ui.min.js"></script>
    <script>
        $.widget.bridge('uibutton', $.ui.button)
    </script>
    <script src="/WebRelease/admin/static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="/WebRelease/admin/static/js/raphael-min.js"></script>
    <script src="/WebRelease/admin/static/plugins/morris/morris.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    <script src="/WebRelease/admin/static/plugins/knob/jquery.knob.js"></script>
    <script src="/WebRelease/admin/static/plugins/moment/moment.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/daterangepicker/daterangepicker.js"></script>
    <script src="/WebRelease/admin/static/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="/WebRelease/admin/static/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/fastclick/fastclick.js"></script>
    <script src="/WebRelease/admin/static/plugins/js/pages/dashboard.js"></script>
    <script src="/WebRelease/admin/static/plugins/js/demo.js"></script>
    <script src="/WebRelease/admin/static/js/echarts.min.js"></script>
    <script src="/WebRelease/admin/static/js/jqBootstrapValidation-1.3.7.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/layui/layui.all.js"></script>
    <script src="/WebRelease/admin/static/plugins/jQuery-contextMenu/jquery.contextMenu.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="/WebRelease/admin/static/js/jq-ext.js"></script>
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
                            url: '/WebRelease/admin/newsCust/list',
                            page: true,
                            height: $(window).height() - 150,
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
                                    { field: 'newscustno', title: '编号', sort: true },
                                    { field: 'status', title: '状态', sort: true },
                                    { field: 'title', title: '标题', sort: true },
                                    { field: 'logSum', title: '日志', sort: true },
                                    { field: 'sort', title: '排序', sort: true },
                                ]
                            ]
                        },
                        menuItem: {
                        	 item1: {
                                 name: "编辑", callback: function (key, opt) {
                                 	$.showContentMenuAjax(key, opt, 260, "/WebRelease/admin/newsCust/editNewsCust", dataList[$(this).attr('data-index')].id, "update");
                                 }
                             },
                             item2: {
                                 name: "预览", callback: function (key, opt) {
                                 	$.showContentMenuAjax(key, opt, 260, "/WebRelease/admin/newsCust/editNewsCust", dataList[$(this).attr('data-index')].id, "look");
                                 }
                             },
                             item3: {
                                 name: "删除", callback: function (key, opt) {
                                 	var id = dataList[$(this).attr('data-index')].id;
                                 	var title = dataList[$(this).attr('data-index')].title == undefined ? "" : dataList[$(this).attr('data-index')].title;
                                 	layer.open({
                                         title: '删除确认框',
                                         content: "确定删除"+title+"吗?",
                                         btn: ['是', '否'],
                                         yes: function (index) {
                                         	$.ajax({  
                                                 type : "POST",  //提交方式  
                                                 url : "/WebRelease/admin/newsCust/delNewsCust",//路径  
                                                 data : {id: id},//数据，这里使用的是Json格式进行传输  
                                                 dataType:"json",
                                                 success : function(result) {//返回数据根据结果进行相应的处理 
                                                 	layer.close(index);
                                                 	$.reload(self.obj);
                                                 }
                                             });
                                         }
                                     });
                                 }
                             },
                            item4: {
                                name: "日志", callback: function (key, opt) {
                                    $.showContentMenu(key, opt)
                                    $.tableObject({
                                        tableId: 'tablelog',
                                        tableOption: {
                                            url: '/WebRelease/admin/newsCust/newsCustLog/list?id='+dataList[$(this).attr('data-index')].id,
                                            page: true,
                                            height: $("#tablelog").parents(".layui-layer-content").height() - 30,
                                            where: {},
                                            cols: [
                                                [
                                                    { field: 'time', title: '时间', sort: true },
                                                    { field: 'rname', title: '操作人', sort: true },
                                                    { field: 'content', title: '内容', sort: true },
                                                ]
                                            ]
                                        }
                                    });
                                }
                            },

                        }
                    });
                }
            }
            tableObj.init();

            $("#importData").on("click", function () {
                var obj = $("#dataContent");
                obj.removeClass("hide");
                layer.open({
                    type: 1,
                    area: [$(document).width() + 'px', '500px'],
                    offset: 'b',
                    title: '大数据',
                    resize: true,
                    anim: 1,
                    maxmin: true,
                    content: obj,
                    cancel: function (index, layero) {
                        $(".contextMenuDialog").addClass("hide");
                    }
                });

                $.tableObject({
                    tableId: 'tabledata',
                    tableOption: {
                        url: 'data/data.html',
                        page: true,
                        height: $("#tabledata").parents(".layui-layer-content").height() - 85,
                        where: {},
                        cols: [
                            [
                                { field: 'zz', title: '', type: 'checkbox' },
                                { field: 'aa', title: '加入时间', sort: true },
                                { field: 'bb', title: '标题)', sort: true },
                                { field: 'cc', title: '内容', sort: true },
                                { field: 'dd', title: '新闻时间', sort: true },
                                { field: 'ee', title: '来源', sort: true },
                            ]
                        ]
                    }
                });
            });
        });
    </script>

    <div class="contextMenuDialog hide" id="dataContent">
        <div class="card-body">
            <div class="row">
                <div class="input-group col-lg-3">
                    <div class="custom-file">
                        <input class="custom-file-input" id="exampleInputFile" type="file">
                        <label class="custom-file-label" for="exampleInputFile">选择Excel文件</label>
                    </div>
                </div>
                <div class="col-lg-3">
                    <input class="btn btn-primary" type="button" value="导入Excel">
                </div>
                <div class="col-lg-3 layui-col-xs-offset3">
                    <div class="row">
                        <div class="text-right">
                            <div class="clearfix">
                                <div class="float-left mr-1">
                                    <button class="form-control btn btn-primary">加入</button>
                                </div>
                                <div class="float-left mr-1">
                                    <button class="form-control btn btn-primary">预览</button>
                                </div>
                                <div class="float-left mr-1">
                                    <button class="form-control btn btn-primary">Excel规范模板下载</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-lg-7">
                    <div class="row">
                        <table id="tabledata"></table>
                    </div>
                </div>
                <div class="col-lg-5">
                    <textarea class="p-md-2 w-100 h-100" id="progress" name="progress" rows="10">2018-09-08 09:08:07 张三丰 添加头条新闻:新闻1</textarea>
                </div>
            </div>
        </div>
    </div>
    <div class="contextMenuDialog hide" id="item3">
        <div class="card-body">
            <div class="row">
                <table id="tablescore"></table>
            </div>
        </div>
    </div>
    <div class="contextMenuDialog hide" id="item4">
        <div class="card-body">
            <div class="row">
                <table id="tablelog"></table>
            </div>
        </div>
    </div>
</body>

</html>