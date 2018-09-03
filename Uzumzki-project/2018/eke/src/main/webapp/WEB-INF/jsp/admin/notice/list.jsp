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
        <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="25"></aside>

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
                                        <div class="card-header">
                                            <h3 class="card-title">
                                                <div class="row">
                                                    <div class="col-lg-4 btncaozuo">
                                                        <button class="btn btn-primary btn-sm" id="importData">创建</button>
                                                    </div>
                                                </div>
                                            </h3>
                                            <!--<div class="card-tools">
                                                <div class="input-group input-group-sm" style="width: 350px;">
                                                    <select class="form-control float-right" id="selectType">
                                                        <option value="0">经纪人</option>
                                                        <option value="1">经纪人编号</option>
                                                        <option value="2">商户</option>
                                                    </select>
                                                    <input class="form-control float-right" id="keyword" name="table_search" type="text" placeholder>

                                                    <div class="input-group-append">
                                                        <button class="btn btn-default" id="btnSearch" type="submit">
                                                            <i class="fa fa-search"></i> 搜索
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>-->
                                        </div>

                                        <div class="card-body table-responsive p-0">
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
                            url: '/WebRelease/admin/notice/list',
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
                                    { field: 'modtime', title: '最后修改', sort: true },
                                    { field: 'noticeLogSum', title: '日志', sort: true },
                                    { field: 'title', title: '标题', sort: true },
                                    { field: 'noticeSum', title: '分发', sort: true },
                                    { field: 'noticeReadSum', title: '已读', sort: true },
                                    { field: 'remark', title: '备注', sort: true },
                                ]
                            ]
                        },
                        menuItem: {
                            item1: {
                                name: "分发", callback: function (key, opt) {
                                    $.showContentMenu(key, opt);

                                    $.tableObject({
                                        tableId: 'tableff',
                                        tableOption: {
                                            url: '/WebRelease/admin/notice/employee/list?id='+dataList[$(this).attr('data-index')].id,
                                            page: true,
                                            height: $("#tableff").parents(".layui-layer-content").height() - 90,
                                            where: {},
                                            cols: [
                                                [
                                                    { field: 'id', title: '', type: 'checkbox' },
                                                    { field: 'city', title: '城市', sort: true },
                                                    { field: 'empstore', title: '商户', sort: true },
                                                    { field: 'emp', title: '经纪人', sort: true },
                                                    { field: 'empno', title: '经纪人编号', sort: true },
                                                    { field: 'remark', title: '备注', sort: true },
                                                ]
                                            ]
                                        }
                                    });
                                }
                            },
                            item2: {
                                name: "修改", callback: function (key, opt) {
                                	$.showContentMenuAjax(key, opt, 450, "/WebRelease/admin/notice/edit", dataList[$(this).attr('data-index')].id, "look");
                                }
                            },
                            item3: {
                                name: "经纪端明细", callback: function (key, opt) {
                                    $.showContentMenu(key, opt)

                                    $.tableObject({
                                        tableId: 'tablejjrmx',
                                        tableOption: {
                                            url: '/WebRelease/admin/notice/massNoticeEmp/list?id='+dataList[$(this).attr('data-index')].id,
                                            page: true,
                                            height: $("#tablejjrmx").parents(".layui-layer-content").height() - 30,
                                            where: {},
                                            cols: [
                                                [
                                                    { field: 'staff', title: '分发人', sort: true },
                                                    { field: 'createtime', title: '分发时间', sort: true },
                                                    { field: 'emp', title: '经纪人', sort: true },
                                                    { field: 'empno', title: '经纪人编号', sort: true },
                                                    { field: 'state', title: '状态', sort: true },
                                                    { field: 'receivetime', title: '查阅时间', sort: true },
                                                    { field: 'remark', title: '备注', sort: true },
                                                ]
                                            ]
                                        }
                                    });
                                }
                            },
                            item4: {
                                name: "变更日志", callback: function (key, opt) {
                                    $.showContentMenu(key, opt)
                                    $.tableObject({
                                        tableId: 'tablelog',
                                        tableOption: {
                                            url: '/WebRelease/admin/notice/massNoticeLog/list?id='+dataList[$(this).attr('data-index')].id,
                                            page: true,
                                            height: $("#tablelog").parents(".layui-layer-content").height() - 30,
                                            where: {},
                                            cols: [
                                                [
                                                    { field: 'modtime', title: '变更时间', sort: true },
                                                    { field: 'staff', title: '操作人', sort: true },
                                                    { field: 'remark', title: '内容', sort: true },
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
                $.ajax({  
                    type : "POST",  //提交方式  
                    url : "/WebRelease/admin/notice/add",//路径  
                    data : {},//数据这里使用的是Json格式进行传输  
                    dataType:"html",
                    success : function(result) {//返回数据根据结果进行相应的处理  
                   		layer.open({
                            type: 1,
                            area: [$(document).width() + 'px', '470px'],
                            offset: 'b',
                            title: "创建公告",
                            resize: true,
                            anim: 1,
                            content: result,
                            maxmin: false,
                            shadeClose: true,
                            cancel: function (index, layero) {
                                layer.close(index);
                            }
                        });
                    }  
                });
            });
        });
    </script>

    <div class="contextMenuDialog hide" id="dataContent">
        <div class="card-body">
            <div class="row lh-38">
                <div class="col-lg-1">公告标题</div>
                <div class="col-lg-11">
                    <div class="row">
                        <div class="col-lg-9">
                            <input type="text" class="form-control">
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <div class="row lh-38">
                <div class="col-lg-1">内容</div>
                <div class="col-lg-11">
                    <textarea name="" class="form-control" id="" cols="30" style="height: 280px;"></textarea>
                </div>
            </div>
            <br>
            <div class="row lh-38">
                <div class="col-lg-1">&nbsp;</div>
                <div class="col-lg-11">
                    <button class="form-control btn btn-primary w-25">保存</button>
                </div>
            </div>
        </div>
    </div>

    <div class="contextMenuDialog hide" id="item1">
        <div class="card-body">
            <div class="row lh-38">
                <div class="col-lg-6">公告标题：重磅消息！已与百度糯米达成战略合作</div>
                <div class="col-lg-3">公告编号：102910</div>
                <div class="col-lg-3">
                    <button class="form-control btn btn-primary btn-sm w-50">确定</button>
                </div>
            </div>
            <br>
            <div class="row">
                <table id="tableff"></table>
            </div>
        </div>
    </div>
    <div class="contextMenuDialog hide" id="item3">
        <div class="card-body">
            <div class="row">
                <table id="tablejjrmx"></table>
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