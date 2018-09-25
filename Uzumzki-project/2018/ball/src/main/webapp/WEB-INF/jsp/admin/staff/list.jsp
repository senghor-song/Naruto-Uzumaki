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
        <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="7"></aside>

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
                                                <button class="btn btn-primary btn-sm mr-2" id="create">创建</button>
                                            </div>
                                        </div>
                                    </h3>
                                </div>
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
                            url: '/WebBackAPI/admin/staff/list',
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
                                    { field: 'staffno', title: '编号', sort: true },
                                    { field: 'ename', title: '登录名', sort: true },
                                    { field: 'rname', title: '姓名', sort: true },
                                    { field: 'position', title: '岗位', sort: true },
                                    { field: 'power', title: '权级', sort: true },
                                    { field: 'status', title: '状态', sort: true },
                                    { field: 'loginchange', title: '最近登录', sort: true },
                                    { field: 'remark', title: '备注', sort: true },
                                ]
                            ]
                        },
                        menuItem: {
                            /*item1: {
                                name: "查看", callback: function (key, opt) {
                                    $.showContentMenu(key, opt, 236);
                                }
                            },*/
                            item2: {
                                name: "变更", callback: function (key, opt) {
                               		layer.open({
                                        type: 2,
                                        area: [$(document).width() + 'px', '236px'],
                                        offset: 'b',
                                        title: "变更",
                                        resize: true,
                                        anim: 1,
                                        content: "/WebBackAPI/admin/staff/editStaff?id="+dataList[$(this).attr('data-index')].id,
                                        maxmin: false,
                                        shadeClose: true,
                                        cancel: function (index, layero) {
                                            layer.close(index);
                                        }
                                    });
                                }
                            },
                            item3: {
                                name: "删除", callback: function (key, opt) {
                                	var staffId = dataList[$(this).attr('data-index')].id;
                                	var name = dataList[$(this).attr('data-index')].rname == undefined ? "" : dataList[$(this).attr('data-index')].rname;
                                	layer.open({
                                        title: '删除确认框',
                                        content: "确定删除"+name+"吗?",
                                        btn: ['是', '否'],
                                        yes: function (index) {
                                        	$.ajax({  
                                                type : "POST",  //提交方式  
                                                url : "/WebBackAPI/admin/staff/delStaff",//路径  
                                                data : {staffId: staffId},//数据，这里使用的是Json格式进行传输  
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

                        }
                    });
                }
            }
            tableObj.init();
            $("#create").on("click", function () {
            	$.ajax({  
                    type : "POST",  //提交方式  
                    url : "/WebBackAPI/admin/staff/addStaff",//路径  
                    data : { },//数据这里使用的是Json格式进行传输  
                    dataType:"html",
                    success : function(result) {//返回数据根据结果进行相应的处理  
                   		layer.open({
                            type: 1,
                            area: [$(document).width() + 'px', '320px'],
                            offset: 'b',
                            title: "新增伙伴",
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

</body>

</html>