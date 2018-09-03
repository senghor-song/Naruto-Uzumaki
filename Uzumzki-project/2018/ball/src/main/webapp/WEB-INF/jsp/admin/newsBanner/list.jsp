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
    <style>
        #tblist .layui-table-body .layui-table-cell {
            height: 150px !important;
        }

        #tblist .layui-table img {
            max-width: 200px !important;
            width: 200px;
        }
    </style>
</head>

<body class="hold-transition sidebar-mini">
    <div class="wrapper">
        <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom" id="topSidebar"></nav>
        <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="6"></aside>

        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper pt-3">
            <!-- Main content -->
            <section class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="card card-secondary">
                                        <!-- <div class="card-header">
                                            <h3 class="card-title">
                                                <div class="row">
                                                    <div class="col-lg-4 btncaozuo">
                                                        <button class="btn btn-primary btn-sm" id="importData">容器</button>
                                                    </div>
                                                </div>
                                            </h3> -->
                                            <!--<div class="card-tools">
                                                <div class="input-group input-group-sm" style="width: 300px;">
                                                    <input class="form-control float-right" id="keyword" name="table_search" type="text" placeholder>

                                                    <div class="input-group-append">
                                                        <button class="btn btn-default" id="btnSearch" type="submit">
                                                            <i class="fa fa-search"></i> 搜索
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>-->

                                        <div class="card-body table-responsive p-0" id="tblist">
                                            <table id="tableList"></table>
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
                            url: '/WebBackAPI/admin/newsBanner/list',
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
                                    { field: 'bannerno', title: '编号', sort: true },
                                    { field: 'coverpath', title: '横幅', align: "center",width: 300, height: 150, templet: '<div><img src="{{d.coverpath}}" alt="" style="width:300px; height:150px;"></div>'},
                                    { field: 'content', title: '容器', sort: true },
                                    { field: 'status', title: '类别', sort: true },
                                    { field: 'newsBannerLogSum', title: '日志', sort: true  },
                                    { field: 'remark', title: '备注', sort: true },
                                ]
                            ]
                        },
                        menuItem: {
                            item1: {
                                name: "编辑", callback: function (key, opt) {
                                    $.showContentMenu(key, opt, 530);
                                }
                            },
                            item4: {
                                name: "修改日志", callback: function (key, opt) {
                                    $.showContentMenu(key, opt)
                                    $.tableObject({
                                        tableId: 'tablelog',
                                        tableOption: {
                                            url: '/WebBackAPI/admin/newsBanner/newsBannerLog/list?id=' + dataList[$(this).attr('data-index')].id,
                                            page: false,
                                            height: $("#tablelog").parents(".layui-layer-content").height() - 30,
                                            where: {},
                                            cols: [
                                                [
                                                    { field: 'logtime', title: '时间', sort: true },
                                                    { field: 'staff', title: '操作人', sort: true },
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

        });
    </script>

    <script id="titleTpl" type="text/html">
    <img src="/WebBackAPI/admin/static/image/u78455.jpg" alt="" style="width200px; height:100px;">
  	</script>

    <div class="contextMenuDialog hide" id="dataContent">
        <div class="card-body">
            <div class="row">
                <div class="input-group clearfix col-lg-5 lh-38">
                    <div class="float-left mr-1">绑定横幅</div>
                    <div class="float-left">
                        <select class="form-control" id name>
                            <option value="">横幅1</option>
                        </select>
                    </div>
                </div>
                <div class="input-group clearfix col-lg-5 lh-38">
                    <div class="float-left mr-1">添加备注</div>
                    <div class="float-left">
                        <input class="form-control" type="text">
                    </div>
                </div>
                <div class="col-lg-2 lh-38">
                    <button class="form-control btn btn-primary">保存</button>
                </div>

            </div>
            <br>
            <div class="row">
                <div class="col-lg-12">
                    <div class="row">
                        <table id="tabledata"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="contextMenuDialog hide" id="item1">
        <div class="card-body">
            <div class="row">
                <div class="col-lg-6">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="row">
                                <div class="col-lg-12 listrow">
                                    <span class="nti">来源：</span>
                                    <span class="ncon">新浪房产</span>
                                </div>
                                <div class="col-lg-12 listrow">
                                    <span class="nti">展示时间：</span>
                                    <span class="ncon">2019-08-07</span>
                                </div>
                                <div class="col-lg-12 listrow">
                                    <span class="nti">平台：</span>
                                    <span class="ncon">客户</span>
                                </div>
                                <div class="col-lg-12 listrow">
                                    <span class="nti">状态：</span>
                                    <span class="ncon">
                                        <input name="status" type="radio"> 展示
                                        <input name="status" type="radio"> 下架
                                    </span>
                                </div>
                                <div class="col-lg-12 listrow">
                                    <span class="nti">置顶：</span>
                                    <span class="ncon">
                                        <input name="status" type="radio"> 是
                                        <input name="status" type="radio"> 否
                                    </span>
                                </div>
                                <div class="col-lg-12 listrow">
                                    <span class="nti">标题：</span>
                                    <span>
                                        <textarea class="form-control" id name cols="30" rows="6"></textarea>
                                    </span>
                                </div>
                            </div>

                        </div>
                        <div class="col-lg-6">
                            <div style="margin-top: 172px;">
                                <input class="btn btn-file p-0 m-0" type="file">
                            </div>
                            <div>
                                <img src="/WebBackAPI/admin/static/image/u34875.jpg" alt style="height: 200px; border:1px solid #eee;">
                            </div>
                        </div>
                    </div>

                </div>
                <div class="col-lg-6 text-center">
                    <div class="p-1">
                        <textarea class="form-control" id name cols="30" style="height: 400px;"></textarea>
                    </div>
                </div>
            </div>
            <div class="row text-center">
                <button class="form-control btn btn-primary w-25">保存</button>
            </div>
        </div>
    </div>
    <div class="contextMenuDialog hide" id="item2">
        <div class="card-body">
            <div class="row">
                <div class="col-lg-6">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="row">
                                <div class="col-lg-12 listrow">
                                    <span class="nti">来源：</span>
                                    <span class="ncon">新浪房产</span>
                                </div>
                                <div class="col-lg-12 listrow">
                                    <span class="nti">展示时间：</span>
                                    <span class="ncon">2019-08-07</span>
                                </div>
                                <div class="col-lg-12 listrow">
                                    <span class="nti">平台：</span>
                                    <span class="ncon">客户</span>
                                </div>
                                <div class="col-lg-12 listrow">
                                    <span class="nti">状态：</span>
                                    <span class="ncon">展示</span>
                                </div>
                                <div class="col-lg-12 listrow">
                                    <span class="nti">置顶：</span>
                                    <span class="ncon">是</span>
                                </div>
                                <div class="col-lg-12 listrow lh-38">
                                    <span class="nti">标题：</span>
                                    <span>标题标题标题标题</span>
                                </div>
                            </div>

                        </div>
                        <div class="col-lg-6">
                            <divv style="margin-top: 172px;">
                                <img src="/WebBackAPI/admin/static/image/u34875.jpg" alt style="height: 200px; border:1px solid #eee;">
                            </divv>
                        </div>
                    </div>

                </div>
                <div class="col-lg-6 text-center">
                    <div class="p-1 text-left">
                        备注备注备注备注备注备注备注备注备注
                    </div>
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