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
    <style>
        #bodycontent {
            overflow: auto;
            height: 864px;
            border: 1px solid #e6e6e6;
            border-bottom-left-radius: 5px;
            border-bottom-right-radius: 5px;
            background: #fff;
            border-top: 0;
        }

        .layui-carousel > [carousel-item] > * {
            background: #fff !important;
        }
    </style>
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom" id="topSidebar"></nav>
    <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="17"></aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper pt-3">

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="layui-tab" lay-filter="demo">
                            <ul class="layui-tab-title">
                                <li class="layui-this">增加小区</li>
                                <li>增加网站</li>
                                <li>建议</li>
                            </ul>
                            <div class="layui-tab-content" id="bodycontent">
                                <div class="layui-tab-item layui-show">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="row">
                                                <table id="addVillage"></table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-tab-item">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="row">
                                                <table id="addWeb"></table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-tab-item">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="row">
                                                <table id="Propose"></table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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

<script src="/WebRelease/admin/static/js/jquery.ztree.all.min.js"></script>
<script>
    $(function () {
        $("#bodycontent").css({height: $(window).height() - 140});

        //图库巡检
        var tab0 = {
            init: function () {
                var self = this;
                self.render();
            },
            render: function () {
                $.tableObject({
                    tableId: 'addVillage',
                    tableOption: {
                        url: '/WebRelease/admin/idea/estateAdd/list',
                        page: true,
                        height: $(window).height() - 161,
                        where: {},
                        cols: [
                            [
                                {field: 'estate', title: '建议创建小区', sort: true},
                                {field: 'createtime', title: '提交时间', sort: true},
                                {field: 'emp', title: '经纪人', sort: true},
                                {field: 'city', title: '城市', sort: true},
                                {field: 'store', title: '商户', sort: true},
                                {field: 'masstype', title: '账户类型', sort: true},
                                {field: 'empTel', title: '经纪人电话', sort: true},
                                {field: 'disposeStaff', title: '处理人', sort: true},
                                {field: 'disposeOpinion', title: '处理意见', sort: true},
                                {field: 'disposeTime', title: '处理时间', sort: true},
                            ]
                        ]
                    },
                    menuItem: {
                        item0: {
                            name: "建议处理", callback: function (key, opt) {
                                $.showContentMenu(key, opt, 540);

                            }
                        },
                    }
                });
            }
        }
        tab0.init();

        var tab1 = {
            init: function () {
                var self = this;
                self.render();
            },
            render: function () {
                $.tableObject({
                    tableId: 'addWeb',
                    tableOption: {
                        url: '/WebRelease/admin/idea/webAdd/list',
                        page: true,
                        height: $(window).height() - 161,
                        where: {},
                        cols: [
                            [
                                {field: 'siteName', title: '网站名', sort: true},
                                {field: 'createTime', title: '提交时间', sort: true},
                                {field: 'emp', title: '经纪人', sort: true},
                                {field: 'city', title: '城市', sort: true},
                                {field: 'store', title: '商户', sort: true},
                                {field: 'type', title: '类型', sort: true},
                                {field: 'siteUrl', title: '网址', sort: true},
                                {field: 'mes', title: '说明', sort: true},
                                {field: 'disposeStaff', title: '处理人', sort: true},
                                {field: 'disposeOpinion', title: '处理意见', sort: true},
                                {field: 'disposeTime', title: '处理时间', sort: true},
                            ]
                        ]
                    },
                    menuItem: {
                        item1: {
                            name: "建议处理", callback: function (key, opt) {
                                $.showContentMenu(key, opt, 540)

                            }
                        },
                    }
                });
            }
        }
        // tab2.init();

        var tab2 = {
            init: function () {
                var self = this;
                self.render();
            },
            render: function () {
                $.tableObject({
                    tableId: 'Propose',
                    tableOption: {
                        url: '/WebRelease/admin/idea/proposal/list',
                        page: true,
                        height: $(window).height() - 161,
                        where: {},
                        cols: [
                            [
                                {field: 'revtime', title: '建议时间', sort: true},
                                {field: 'type', title: '提交途径', sort: true},
                                {field: 'proposalno', title: '编号', sort: true},
                                {field: 'detail', title: '内容', sort: true},
                                {field: 'disposeStaff', title: '处理人', sort: true},
                                {field: 'disposeOpinion', title: '处理意见', sort: true},
                                {field: 'disposeTime', title: '处理时间', sort: true},
                            ]
                        ]
                    },
                    menuItem: {
                        item2: {
                            name: "建议", callback: function (key, opt) {
                                $.showContentMenu(key, opt, 170)
                            }
                        },
                    }
                });
            }
        }
        // tab2.init();


        element.on('tab(demo)', function (data) {
            switch (data.index) {
                case 0: {
                    tab0.init();
                    break;
                }
                case 1: {
                    tab1.init();
                    break;
                }
                case 2: {
                    tab2.init();
                    break;
                }
            }
        });
    });
</script>

<div class="contextMenuDialog hide" id="item0">
    <div class="card-body">
        <div class="row mb-2">
            <div class="col-2"></div>
            <div class="col-10">
                <input class="form-control float-left mr-2 w-50" type="text" placeholder="">
                <label class="mr-2 pt-2"><input type="checkbox">已阅读</label>
                <button class="btn btn-primary float-left mr-2">保存</button>
                <span class="pt-2">2</span>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <table id="tableCity0"></table>
            </div>
        </div>
    </div>
</div>
<div class="contextMenuDialog hide" id="item1">
    <div class="card-body">
        <div class="row mb-2">
            <div class="col-2"></div>
            <div class="col-10">
                <input class="form-control float-left mr-2 w-50" type="text" placeholder="">
                <label class="mr-2 pt-2"><input type="checkbox">已阅读</label>
                <button class="btn btn-primary float-left mr-2">保存</button>
                <span class="pt-2">2</span>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <table id="tableCity1"></table>
            </div>
        </div>
    </div>
</div>
<div class="contextMenuDialog hide" id="item2">
    <div class="card-body">
        <div class="row mb-2">
            <div class="col-3"></div>
            <div class="col-9">
                <input class="form-control float-left mr-2 w-50" type="text" placeholder="">
                <button class="btn btn-primary float-left mr-2">保存</button>
            </div>
        </div>
    </div>
</div>
</body>

</html>