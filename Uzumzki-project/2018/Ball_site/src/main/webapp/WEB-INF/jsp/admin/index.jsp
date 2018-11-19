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
        <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="0"></aside>

        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper pt-3">

            <!-- Main content -->
            <section class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card card-secondary">
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
                            url: '/WebBackAPI/admin/common/operationLog/list',
                            page: true,
                            height: $(window).height() - 90,
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
                                    { field: 'content', title: '内容'},
                                ]
                            ]
                        }
                    });
                }
            }
            tableObj.init();
        });
    </script>
</body>
</html>