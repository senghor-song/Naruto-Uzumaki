<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>后台管理系统</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/WebBackAPI/admin/static/css/site.css" rel="stylesheet">
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom" id="topSidebar"></nav>
    <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="1"></aside>

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
                        url: '/WebBackAPI/admin/venue/list',
                        page: true,
                        height: $(window).height() - 145,
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
                                {field: 'city', title: '城市', sort: true},
                                {field: 'district', title: '区县', sort: true},
                                {field: 'area', title: '街道', sort: true},
                                {field: 'name', title: '场馆', sort: true},
                                {field: 'fieldSum', title: '场地', sort: true},
                                {field: 'coachSum', title: '教练', sort: true},
                                {field: 'managerSum', title: '管理员', sort: true},
                                {field: 'venueTemplateSum', title: '模板', sort: true},
                                {field: 'venuelogSum', title: '日志', sort: true},
                                {field: 'amount', title: '累计金额', sort: true},
                                {field: 'balance', title: '存余金额', sort: true},
                                {field: 'freezeamount', title: '冻结金额', sort: true},
                                {field: 'view11', title: '培训课程', sort: true},
                            ]
                        ]
                    },
                    menuItem: {
                        item1: {
                            name: "管理员", callback: function (key, opt) {
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tablemanager',
                                    tableOption: {
                                        url: '/WebBackAPI/admin/venue/managerlist?venueid='+dataList[$(this).attr('data-index')].id,
                                        page: true,
                                        height: $("#tablemanager").parents(".layui-layer-content").height() - 30,
                                        where: {},
                                        cols: [
                                            [
                                                { field: 'createtime', title: '创建时间', sort: true },
                                                { field: 'name', title: '姓名', sort: true },
                                                { field: 'realname', title: '实名', sort: true },
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                        item2: {
                            name: "日志", callback: function (key, opt) {
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tablevenuelog',
                                    tableOption: {
                                        url: '/WebBackAPI/admin/venue/venueloglist?venueid='+dataList[$(this).attr('data-index')].id,
                                        page: false,
                                        height: $("#tablevenuelog").parents(".layui-layer-content").height() - 30,
                                        where: {},
                                        cols: [
                                            [
                                                { field: 'createtime', title: '时间', sort: true },
                                                { field: 'manager', title: '操作人', sort: true },
                                                { field: 'content', title: '内容', sort: true },
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
            <table id="tablemanager"></table>
        </div>
    </div>
</div>
<div class="contextMenuDialog hide" id="item2">
    <div class="card-body">
        <div class="row">
            <table id="tablevenuelog"></table>
        </div>
    </div>
</div>
</body>

</html>