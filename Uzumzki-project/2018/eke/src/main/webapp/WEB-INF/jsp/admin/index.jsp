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
	<link rel="stylesheet" href="/WebRelease/admin/static/plugins/bootstrap-validator/dist/css/bootstrapValidator.css" />
	<link rel="stylesheet" href="/WebRelease/admin/static/plugins/bootstrap-validator/vendor/bootstrap/css/dataValidator.css" />
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">

    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom" id="topSidebar"></nav>
    <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="0"></aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content" id="contentbody" style="height: 0px; overflow: hidden;">
            <div class="container-fluid">
                <!-- Small boxes (Stat box) -->
                <div class="row mt-3">
                        <div class="col-lg-3 col-12">
                            <div class="card">
                                <div class="card-body card-body-nolrp">
                                    <div class="chart" id="chart1"></div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body card-body-nolrp">
                                    <div class="chart" id="chart2"></div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body card-body-nolrp">
                                    <div class="chart" id="chart3"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-12">
                            <div class="card">
                                <div class="card-body card-body-nolrp">
                                    <div class="chart" id="chart4"></div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body card-body-nolrp">
                                    <div class="chart" id="chart5"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-12">
                            <div class="card">
                                <div class="card-body card-body-nolrp">
                                    <div class="chart" id="chart6"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-12">
                            <div class="card">
                                <div class="card-body card-body-nolrp">
                                    <div class="chart" id="chart7"></div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body card-body-nolrp">
                                    <div class="chart" id="chart8"></div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body card-body-nolrp">
                                    <div class="chart" id="chart9"></div>
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
<!-- ./wrapper -->

<script src="/WebRelease/admin/static/plugins/jquery/jquery.min.js"></script>
<script src="/WebRelease/admin/static/plugins/jQueryUI/jquery-ui.min.js"></script>
<script src="/WebRelease/admin/static/js/layout.js"></script>
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

<script src="/WebRelease/admin/static/js/desk.js"></script>

<script type="text/javascript" src="/WebRelease/admin/static/plugins/bootstrap-validator/vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/WebRelease/admin/static/plugins/bootstrap-validator/dist/js/bootstrapValidator.js"></script>
<script>
    $(function () {
        $("#contentbody").css({height: $(window).height() - 80, "overflow-y": "auto"});
    });
</script>

<div id="chart1Div" class="hide otherDialog w-100 h-100">
    <div id="chart1DivChart" class="h-100"></div>
</div>
<div id="chart2Div" class="hide otherDialog w-100 h-100">
    <div id="chart2DivChart" class="h-100"></div>
</div>
<div id="chart3Div" class="hide otherDialog w-100 h-100">
    <div id="chart3DivChart" class="h-100"></div>
</div>
<div id="chart4Div" class="hide otherDialog w-100 h-100">
    <div id="chart4DivChart" class="h-100"></div>
</div>
<div id="chart5Div" class="hide otherDialog w-100 h-100">
    <div id="chart5DivChart" class="h-100"></div>
</div>
<div id="chart6Div" class="hide otherDialog w-100 h-100">
    <div id="chart6DivChart" class="h-100"></div>
</div>
<div id="chart7Div" class="hide otherDialog w-100 h-100">
    <div id="chart7DivChart" class="h-100"></div>
</div>
<div id="chart8Div" class="hide otherDialog w-100 h-100">
    <div id="chart8DivChart" class="h-100"></div>
</div>
<div id="chart9Div" class="hide otherDialog w-100 h-100">
    <div id="chart9DivChart" class="h-100"></div>
</div>
</body>

</html>