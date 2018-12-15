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
	<link rel="stylesheet" href="/admin/static/plugins/bootstrap-validator/dist/css/bootstrapValidator.css" />
	<link rel="stylesheet" href="/admin/static/plugins/bootstrap-validator/vendor/bootstrap/css/dataValidator.css" />
    <link href="/admin/static/plugins/webuploader/webuploader.css" rel="stylesheet">
    <style>
        #tblist .layui-table-body .layui-table-cell {
            height: 100px !important;
            line-height : 100px;
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
        <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="13"></aside>

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
													<c:if test="${btn334 == 1}">
														<div class="col-lg-4 btncaozuo">
															<button class="btn btn-primary btn-sm" id="addNews">创建</button>
														</div>
													</c:if>
												</div>
                                            </h3>
                                            <!-- <div class="card-tools">
                                                <div class="input-group input-group-sm" style="width: 300px;">
                                                    <input class="form-control float-right" id="keyword" name="table_search" type="text" placeholder>

                                                    <div class="input-group-append">
                                                        <button class="btn btn-default" id="btnSearch" type="submit">
                                                            <i class="fa fa-search"></i> 搜索
                                                        </button>
                                                    </div>
                                                </div>
                                            </div> -->
                                        </div>

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
    <script src="/admin/static/plugins/layer/layer.js"></script>
    <script src="/admin/static/plugins/webuploader/webuploader.js"></script>
	<script type="text/javascript" src="/admin/static/plugins/bootstrap-validator/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/admin/static/plugins/bootstrap-validator/dist/js/bootstrapValidator.js"></script>
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
                            url: '/admin/news/list',
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
                  					{field: 'id', title: 'id', hide:true},
                                    { field: 'newsNo', title: '编号', sort: true,width: 100},
                                    { field: 'headImage', title: '封面', align: "center",width: 220, height: 100, templet: '<div><img src="{{d.headImage}}" alt="" style="width:180px; height:100px;"></div>'},
                                    { field: 'title', title: '标题', sort: true },
                                    { field: 'content', title: '摘要', sort: true },
                                    { field: 'newsLogSum', title: '日志', sort: true ,width: 100},
                                    { field: 'newsErrorSum', title: '纠错', sort: true ,width: 100},
                                    { field: 'showFlag', title: '使能', sort: true ,width: 100},
                                    { field: 'remark', title: '备注', sort: true },
                                ]
                            ]
                        },
                        menuItem: {
    						<c:if test="${btn331 == 1}">
                            item1: {
                                name: "编辑", callback: function (key, opt) {
                                	$.showContentMenuOpen(key, opt, 500, "/admin/news/edit?id="+ $(this).find("td").eq(0).attr('title'), "look");
                                }
                            },
                            </c:if>
    						<c:if test="${btn332 == 1}">
                            item2: {
                                name: "日志", callback: function (key, opt) {
                                    $.showContentMenu(key, opt)
                                    $.tableObject({
                                        tableId: 'tablelog',
                                        tableOption: {
                                            url: '/admin/news/newsLog/list?id=' + $(this).find("td").eq(0).attr('title'),
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
                            </c:if>
    						<c:if test="${btn333 == 1}">
                            item3: {
                                name: "纠错", callback: function (key, opt) {
                                    $.showContentMenu(key, opt)
                                    $.tableObject({
                                        tableId: 'tableError',
                                        tableOption: {
                                            url: '/admin/news/newsError/list?id=' + $(this).find("td").eq(0).attr('title'),
                                            page: false,
                                            height: $("#tableError").parents(".layui-layer-content").height() - 30,
                                            where: {},
                                            cols: [
                                                [
                                                    { field: 'createTime', title: '时间', sort: true },
                                                    { field: 'member', title: '操作人', sort: true },
                                                    { field: 'content', title: '内容', sort: true },
                                                ]
                                            ]
                                        }
                                    });
                                }
                            },
                            </c:if>

                        }
                    });
                }
            }
            tableObj.init();
            $("#addNews").on("click", function () {
                layer.open({
                    type: 2,
                    area: ['100%', '500px'],
                    offset: 'b',
                    title: '新增资讯',
                    resize: true,
                    anim: 1,
                    shadeClose : true,
                    content: "/admin/news/add",
                    cancel: function (index, layero) {
    	    			$.reload(tableObj.obj);
                    },
                    end: function () {
    	    			$.reload(tableObj.obj);
    	        	}
                });
            });
        });
    </script>

    <div class="contextMenuDialog hide" id="item2">
        <div class="card-body">
            <div class="row">
                <table id="tablelog"></table>
            </div>
        </div>
    </div>
    <div class="contextMenuDialog hide" id="item3">
        <div class="card-body">
            <div class="row">
                <table id="tableError"></table>
            </div>
        </div>
    </div>
</body>

</html>