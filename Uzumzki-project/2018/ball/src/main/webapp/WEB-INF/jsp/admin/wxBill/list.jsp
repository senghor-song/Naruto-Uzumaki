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
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom" id="topSidebar"></nav>
    <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="18"></aside>

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
                        url: '/admin/wxBill/list',
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
                    			{width:100, field: 'id', title: 'id', hide:true},
                                {width:180, field: 'createtime', title: '创建时间', sort: true},
                                {width:180, field: 'dealtime', title: '交易时间', sort: true},
                                {width:140, field: 'wxAppid', title: '公众账号ID', sort: true},
                                {width:100, field: 'wxMchid', title: '商户号', sort: true},
                                {width:100, field: 'sonMchid', title: '子账户号', sort: true},
                                {width:100, field: 'deviceInfo', title: '设备号', sort: true},
                                {width:120, field: 'wxOrderid', title: '微信订单号', sort: true},
                                {width:120, field: 'storeOrderid', title: '商户订单号', sort: true},
                                {width:100, field: 'userFlag', title: '用户标识', sort: true},
                                {width:100, field: 'payType', title: '交易类型', sort: true},
                                {width:100, field: 'payStatus', title: '交易状态', sort: true},
                                {width:100, field: 'paymentBank', title: '付款银行', sort: true},
                                {width:100, field: 'currencyType', title: '货币种类', sort: true},
                                {width:140, field: 'oughtOrderAmount', title: '应结订单金额', sort: true},
                                {width:120, field: 'cashCoupon', title: '代金券金额', sort: true},
                                {width:140, field: 'wxRefundOrderid', title: '微信退款单号', sort: true},
                                {width:140, field: 'storeRefundOrderid', title: '商户退款单号', sort: true},
                                {width:100, field: 'refundAmount', title: '退款金额', sort: true},
                                {width:160, field: 'cashCouponRefund', title: '充值卷退款金额', sort: true},
                                {width:100, field: 'refundType', title: '退款类型', sort: true},
                                {width:100, field: 'refundStatus', title: '退款状态', sort: true},
                                {width:100, field: 'goodsName', title: '商品名称', sort: true},
                                {width:120, field: 'storeData', title: '商户数据包', sort: true},
                                {width:100, field: 'feeAmount', title: '手续费', sort: true},
                                {width:100, field: 'rate', title: '费率', sort: true},
                                {width:100, field: 'orderAmount', title: '订单金额', sort: true},
                                {width:120, field: 'applyRefundAmount', title: '申请退款金额', sort: true},
                                {width:100, field: 'rateRemark', title: '费率备注', sort: true},
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