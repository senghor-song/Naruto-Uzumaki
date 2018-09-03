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
        <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="12"></aside>

        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper pt-3">

            <!-- Main content -->
            <section class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card card-secondary">
                                <!--<div class="card-header">
                                    <h3 class="card-title">
                                        <div class="row">
                                            <div class="col-lg-4 btncaozuo">
                                                &nbsp;
                                            </div>
                                        </div>
                                    </h3>
                                    <div class="card-tools">
                                        <div class="input-group input-group-sm" style="width: 250px;">
                                            <input class="form-control float-right" id="keyword" name="table_search" type="text" placeholder>
                                            <div class="input-group-append">
                                                <button class="btn btn-default" id="btnSearch" type="submit">
                                                    <i class="fa fa-search"></i> 搜索
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>-->

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
        	var pageurl = "/WebRelease/admin/custAppointResp/list";
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
                            url: pageurl,
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
                                    { field: 'appointtime', title: '约看时间', sort: true },
                                    { field: 'cust', title: '客户', sort: true },
                                    { field: 'city', title: '城市', sort: true },
                                    { field: 'estate', title: '小区', sort: true },
                                    { field: 'empStore', title: '商户', sort: true },
                                    { field: 'employee', title: '经纪人', sort: true },
                                    { field: 'respcontent', title: '响应', sort: true },
                                    { field: 'resptime', title: '响应时间', sort: true },
                                ]
                            ]
                        },
                        /*menuItem: {
                            item1: {
                                name: "客户跟进", callback: function (key, opt) {
                                    $.showContentMenu(key, opt, 500);

                                    $.tableObject({
                                        tableId: 'tablelog',
                                        tableOption: {
                                            url: 'data/data.html',
                                            page: true,
                                            height: $("#tablelog").parents(".layui-layer-content").height()-100,
                                            where: {},
                                            cols: [
                                                [
                                                    { field: 'date', title: '记录时间', sort: true },
                                                    { field: 'bb', title: '类别', sort: true },
                                                    { field: 'cc', title: '内容', sort: true },
                                                ]
                                            ]
                                        }
                                    });
                                }
                            },
                            
                        }*/
                    });
                }
            }
            tableObj.init();

        });
    </script>

    <div class="contextMenuDialog hide" id="item1">
        <div class="card-body">
            <div class="row lh-38">
                <span class="col-lg-4">商户经纪：奥巴马地产>张三丰1390008877</span>
                <span class="col-lg-4 border-secondary">
                    <div class="row">
                        <span class="float-left mr-2">短信模板</span>
                        <select class="form-control float-left w-25 mr-2"></select>
                        <button class="form-control float-left w-25 btn-primary">发送短信</button>
                    </div>
                </span>
                <span class="col-lg-4 border-secondary">
                    <span class="float-left mr-2">类别</span>
                    <select class="form-control float-left w-25 mr-2">
                        <option>后台去电</option>
                    </select>
                    <input class="form-control float-left w-25 mr-2" type="text">
                    <button class="form-control float-left w-25 btn-primary">保存</button>
                </span>
            </div>
            <br>
            <div class="row">
                <table id="tablelog"></table>
            </div>
        </div>
    </div>
</body>

</html>