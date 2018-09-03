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
        <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="10"></aside>

        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper pt-3">

            <!-- Main content -->
            <section class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card card-secondary">
                                <!--<div class="card-header">
                                    <h3 class="card-title">&nbsp;</h3>
                                    <div class="card-tools">
                                        <div class="input-group input-group-sm" style="width: 350px;">
                                            <select class="form-control float-right" id="selectType">
                                                <option value="0">房源编号</option>
                                                <option value="1">小区</option>
                                            </select>
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
                            url: '/WebRelease/admin/propertyPub/list',
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
                                    { field: 'regdate', title: '登记时间', sort: true },
                                    { field: 'regway', title: '来源', sort: true },
                                    { field: 'city', title: '城市', sort: true },
                                    { field: 'estate', title: '小区', sort: true },
                                    { field: 'square', title: '面积', sort: true },
                                    { field: 'trade', title: '类别', sort: true },
                                    { field: 'price', title: '报价', sort: true },
                                    { field: 'flagpay', title: '业主付拥', sort: true },
                                    { field: 'pubCount', title: '已分发', sort: true },
                                    { field: 'claimCount', title: '已认领', sort: true },
                                ]
                            ]
                        },
                        menuItem: {
                            item1: {
                                name: "分发", callback: function (key, opt) {
                                    $.showContentMenu(key, opt, 500);
                                    $.tableObject({
                                        tableId: 'tableview',
                                        tableOption: {
                                            url: '/WebRelease/admin/propertyPub/distribute?id='+dataList[$(this).attr('data-index')].id,
                                            page: false,
                                            where: {},
                                            height: $("#tableview").parents(".layui-layer-content").height()-40,
                                            cols: [
                                                [
                                                    { field: 'empStore', title: '商户', sort: true },
                                                    { field: 'emp', title: '经纪人', sort: true },
                                                    { field: 'view', title: '商户距离(km)', sort: true },
                                                    { field: 'view', title: '商户合作存续', sort: true },
                                                    { field: 'view', title: '经纪人积分', sort: true },
                                                    { field: 'empclaimtime', title: '认领时间', sort: true },
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
        });
    </script>

<div class="contextMenuDialog hide" id="item1">
        <div class="card-body">
            <div class="row">
                <table id="tableview"></table>
            </div>
        </div>
    </div>
    
</body>

</html>