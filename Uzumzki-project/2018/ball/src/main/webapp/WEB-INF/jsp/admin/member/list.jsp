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
	<link rel="icon" href="/admin/static/image/logo.png" type="image/x-icon"/>
    <link href="/admin/static/css/site.css" rel="stylesheet">
    <style>

        #tblist .layui-table img {
            max-width: 200px !important;
            width: 200px;
        }
    </style>
</head>

<body class="hold-transition sidebar-mini">
    <div class="wrapper">
        <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom" id="topSidebar"></nav>
        <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="3"></aside>

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
    <script src="/admin/static/plugins/jquery/jquery.min.js"></script>
    <script src="/admin/static/js/layout.js"></script>
    <script src="/admin/static/plugins/jQueryUI/jquery-ui.min.js"></script>
    <script>
        $.widget.bridge('uibutton', $.ui.button)
    </script>
    <script src="/admin/static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="/admin/static/js/raphael-min.js"></script>
    <script src="/admin/static/plugins/morris/morris.min.js"></script>
    <script src="/admin/static/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script src="/admin/static/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="/admin/static/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    <script src="/admin/static/plugins/knob/jquery.knob.js"></script>
    <script src="/admin/static/plugins/moment/moment.min.js"></script>
    <script src="/admin/static/plugins/daterangepicker/daterangepicker.js"></script>
    <script src="/admin/static/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="/admin/static/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
    <script src="/admin/static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="/admin/static/plugins/fastclick/fastclick.js"></script>
    <script src="/admin/static/plugins/js/pages/dashboard.js"></script>
    <script src="/admin/static/plugins/js/demo.js"></script>
    <script src="/admin/static/js/jqBootstrapValidation-1.3.7.min.js"></script>
    <script src="/admin/static/plugins/layui/layui.all.js"></script>
    <script src="/admin/static/plugins/jQuery-contextMenu/jquery.contextMenu.min.js"></script>
    <script src="/admin/static/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="/admin/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="/admin/static/js/jq-ext.js"></script>
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
                            url: '/admin/member/list',
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
	                                 { field: 'createtime', title: '注册时间', sort: true , style:"line-height: 135px;" },
	                                 { field: 'appnickname', title: '微信昵称', sort: true },
	                                 { field: 'appavatarurl', title: '头像', align: "center",width: 60, templet: '<div><img src="{{d.appavatarurl}}" alt="" style="width:25px; height:25px;"></div>'},
	                                 { field: 'memberno', title: '用户编号', sort: true },
	                                 { field: 'Wxpayment', title: '剩余余额', sort: true },
                                ]
                            ]
                        },
                        menuItem: {
                            item1: {
                                name: "额度明细", callback: function (key, opt) {
                                    $.showContentMenu(key, opt);
                                    $.tableObject({
                                        tableId: 'tableMemberWXPayment',
                                        tableOption: {
                                            url: '/admin/member/memberWXPayment/list?memberId=' + $(this).find("td").eq(0).attr('title'),
                                            page: false,
                                            height: $("#tableMemberWXPayment").parents(".layui-layer-content").height() - 30,
                                            where: {},
                                            cols: [
                                                [
                                                    { field: 'createTime', title: '时间'},
                                                    { field: 'price', title: '额度使用'},
                                                    { field: 'remainPrice', title: '剩余额度'},
                                                    { field: 'content', title: '内容'},
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
	            <table id="tableMemberWXPayment"></table>
	        </div>
	    </div>
	</div>

</body>

</html>