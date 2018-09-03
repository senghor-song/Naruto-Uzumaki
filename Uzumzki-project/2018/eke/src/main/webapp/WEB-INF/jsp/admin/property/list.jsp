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
        <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="9"></aside>

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
                                        <div class="input-group input-group-sm" style="width: 400px;">
                                            <div style="position: relative;top: 8px;margin-right: 8px;">
                                                <input type="checkbox" class="form-check-input" name="" id="" value="0" checked> 下架</div>
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
                            url: '/WebRelease/admin/property/list',
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
                                    { field: 'city', title: '城市', sort: true },
                                    { field: 'estate', title: '小区', sort: true },
                                    { field: 'propertyno', title: '房源编号', sort: true },
                                    { field: 'status', title: '状态', sort: true },
                                    { field: 'trade', title: '交易', sort: true },
                                    { field: 'emp', title: '经纪人', sort: true },
                                    { field: 'store', title: '商户', sort: true },
                                    { field: 'propertyImageSum', title: '室内图', sort: true },
                                    { field: 'propertyFalseSum', title: '投诉', sort: true },
                                    { field: 'sortscore', title: '排序积分', sort: true },
                                    { field: 'propertyFollowSum', title: '跟进', sort: true },
                                ]
                            ]
                        },
                        menuItem: {
                            item1: {
                                name: "查看", callback: function (key, opt) {
                                	$.showContentMenuAjax(key, opt, 250, "/WebRelease/admin/property/details", dataList[$(this).attr('data-index')].id, "look");
                                }
                            },
                            item2: {
                                name: "室内图", callback: function (key, opt) {
                                	$.showContentMenuAjax(key, opt, $(window).height(), "/WebRelease/admin/property/propertyImg", dataList[$(this).attr('data-index')].id, "look");
                                }
                            },
                            item3: {
                                name: "跟进明细", callback: function (key, opt) {
                                    $.showContentMenu(key, opt, 500)
                                    $.tableObject({
                                        tableId: 'tablegj',
                                        tableOption: {
                                            url: '/WebRelease/admin/property/propertyFollow/list?id='+dataList[$(this).attr('data-index')].empid,
                                            page: true,
                                            height: 430,
                                            where: {},
                                            cols: [
                                                [
                                                    { field: 'followdate', title: '跟进时间', sort: true },
                                                    { field: 'emp', title: '跟进人', sort: true },
                                                    { field: 'theme', title: '主题', sort: true },
                                                    { field: 'content', title: '内容', sort: true },
                                                ]
                                            ]
                                        }
                                    });
                                }
                            },
                            item4: {
                                name: "排序积分", callback: function (key, opt) {
                                    $.showContentMenu(key, opt, 500)

                                    $.tableObject({
                                        tableId: 'tablescore',
                                        tableOption: {
                                            url: '/WebRelease/admin/property/propertyScoreLog/list?id='+dataList[$(this).attr('data-index')].id,
                                            page: true,
                                            height: $("#tablescore").parents(".layui-layer-content").height()-40,
                                            where: {},
                                            cols: [
                                                [
                                                    { field: 'createTime', title: '时间', sort: true },
                                                    { field: 'source', title: '获分来源', sort: true },
                                                    { field: 'sourceitem', title: '获分项目', sort: true },
                                                    { field: 'score', title: '分值', sort: true },
                                                    { field: 'remark', title: '备注', sort: true },
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

    
    <div class="contextMenuDialog hide" id="item2">
		<div class="card-body">
	        <div class="row">
	            <div id="showImg" style="max-height: 600px; overflow: auto;">
	                <div class="img">
	                    <div class="img1">
	                        <img src="/WebRelease/admin/static/image/u6894.jpg" alt="">
	                        <div class="show">展示</div>
	                        <div class="setfm">封面</div>
	                    </div>
	                    <div>2018-09-08 09:07:06　张晓晓</div>
	                </div>
	                <div class="img">
	                    <div>
	                        <img src="/WebRelease/admin/static/image/u6894.jpg" alt="">
	                    </div>
	                    <div>2018-09-08 09:07:06　张晓晓</div>
	                </div>
	            </div>
	        </div>
	        <div class="row mt-3">
	            <button class="form-control btn btn-primary mr-2" style="width: 120px;">设为封面</button>
	            <button class="form-control btn btn-primary mr-2" style="width: 120px;">上传</button>
	            <button class="form-control btn btn-primary mr-2" style="width: 120px;">删除</button>
	            <button class="form-control btn btn-primary mr-2" style="width: 120px;">刷新</button>
	            <button class="form-control btn btn-primary mr-2" style="width: 120px;">返回</button>
	            <button class="form-control btn btn-primary mr-2" style="width: 120px;">从库存导入</button>
	        </div>
	        <div class="row mt-2">
	            <table id="tableimg"></table>
	        </div>
    	</div>
    </div>
    <div class="contextMenuDialog hide" id="item3">
	    <div class="card-body">
	        <div class="row">
	            <table id="tablegj"></table>
	        </div>
	    </div>
	</div>
    <div class="contextMenuDialog hide" id="item4">
        <div class="card-body">
            <div class="row">
                <table id="tablescore"></table>
            </div>
        </div>
    </div>
    
</body>

</html>