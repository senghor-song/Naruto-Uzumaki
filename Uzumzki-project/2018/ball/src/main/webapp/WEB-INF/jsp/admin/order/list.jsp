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
    <link href="/admin/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom" id="topSidebar"></nav>
    <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="2"></aside>

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
                                    	<div class="col-lg-9 btncaozuo">
                                        </div>
                                        <div class="input-group input-group-sm float-right col-lg-3" style="width: 350px;">
											<select class="form-control float-right" id="venueSelectType">
												<option value="0">订单状态</option>
												<option value="1">用场日期</option>
												
											</select> 
											<select class="form-control float-right" id="selectOrderType">
												<option value="">全部</option>
												<option value="0">待支付</option>
												<option value="1">订单完成</option>
												<option value="4">已退款</option>
												<option value="5">已支付待确认</option>
												<option value="6">已确认待消费</option>
											</select> 
											<input class="form-control float-right" id="dateStr" name="dateStr" type="text" placeholder="请选择日期">
											<div class="input-group-append">
												<button class="btn btn-default" id="venueSearch" type="submit">
													<i class="fa fa-search"></i> 搜索
												</button>
											</div>
										</div>
                                    </div>
                                </h3>
                            </div>
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
<script src="/admin/css/bootstrap-datetimepicker.min.js"></script>
<script>
    $(function () {
    	$.fn.datetimepicker.dates['zdy'] = {
	        days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
	        daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
	        daysMin:  ["日", "一", "二", "三", "四", "五", "六"],
	        months: ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],
	        monthsShort: ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],
	        today: "今天",
	        format:"yyyy-mm-dd",
	        titleFormat:"yyyy-mm-",
	        weekStart:1,
	        suffix: [],
	        meridiem: ["上午", "下午"]
	      };
    	var sdate = '2000-07-04';
    	$('#dateStr').datetimepicker({
            language:  'zdy',
            weekStart: 1,
            todayBtn:  1,
            autoclose: 1,
            startDate:sdate,
            minView:2,
            maxView:3,
            onRenderDay: function(date) {
              var date1 = date.getFullYear()+'-'
                +(date.getMonth()<9?'0'+(date.getMonth()+1):date.getMonth()+1)
                +'-'
                +(date.getDate()<10?'0'+(date.getDate()-1):date.getDate()-1);
            }
          });

        var tableObj = {
            obj: null,
            init: function () {
                var self = this;
                self.tableList();
                self.search();
            },
            search: function () {
                var self = this;
                $("#venueSearch").unbind().on("click", function () {
                    $.reload(self.obj);
                });
            },
            tableList: function () {
                var self = this;
                self.obj = $.tableObject({
                    tableId: 'tableList',
                    tableOption: {
                        url: '/admin/order/list',
                        page: true,
                        height: $(window).height() - 150,
                        where: {
                        	venueSelectType: function () {
                                return $("#venueSelectType").val()
                            },
                            selectOrderType: function () {
                                return $("#selectOrderType").val()
                            },
                            dateStr: function () {
                                return $("#dateStr").val()
                            }
                        },
                        cols: [
                            [
                             	{field: 'id', title: 'id', hide:true},
                                {field: 'createtime', title: '订场时间', sort: true},
                                {field: 'orderdate', title: '用场日期', sort: true},
                                {field: 'orderno', title: '订单编号', sort: true},
                                {field: 'city', title: '城市', sort: true},
                                {field: 'venue', title: '场馆编号', sort: true},
                                {field: 'member', title: '用户编号', sort: true},
                                {field: 'showPrice', title: '订单应付', sort: true},
                                {field: 'price', title: '订单实付', sort: true},
                                {field: 'amountRefund', title: '用户退款', sort: true},
                                {field: 'venuePayAmount', title: '场馆回款', sort: true},
                                {field: 'priceFee', title: '平台费', sort: true},
                                {field: 'type', title: '状态', sort: true},
                                {field: 'orderlog', title: '日志', sort: true},
                            ]
                        ]
                    },
                    menuItem: {
                        item2: {
                            name: "查看", callback: function (key, opt) {
                                $.showContentMenu(key, opt);
                                $.tableObject({
                                    tableId: 'tableOrderDetails',
                                    tableOption: {
                                        url: '/admin/order/orderDetails?id=' + $(this).find("td").eq(0).attr('title'),
                                        page: false,
                                        height: $("#tableOrderDetails").parents(".layui-layer-content").height() - 30,
                                        where: {},
                                        cols: [
                                            [
                                                { field: 'venueName', title: '场馆', sort: true },
                                                { field: 'type', title: '项目', sort: true },
                                                { field: 'timestr', title: '内容', sort: true },
                                                { field: 'price', title: '金额', sort: true },
                                                { field: 'fieldName', title: '备注', sort: true },
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                    	<c:if test="${btn111 == 1}">
                        item1: {
                            name: "日志", callback: function (key, opt) {
                            	
                                $.showContentMenu(key, opt);
                                $.tableObject({
                                    tableId: 'tableorderlog',
                                    tableOption: {
                                        url: '/admin/order/orderloglist?orderid=' + $(this).find("td").eq(0).attr('title'),
                                        page: false,
                                        height: $("#tableorderlog").parents(".layui-layer-content").height() - 30,
                                        where: {},
                                        cols: [
                                            [
                                                { field: 'createtime', title: '时间', sort: true },
                                                { field: 'type', title: '类别', sort: true },
                                                { field: 'content', title: '内容', sort: true },
                                            ]
                                        ]
                                    }
                                });
                            }
                        }
                    	</c:if>
                    }
                });
            }
        }
        tableObj.init();
    	$("#selectOrderType").css("display","");
    	$("#dateStr").css("display","none");
        $("#venueSelectType").on("change", function () {
            var select = $(this).val();
            if(select == 0){
            	$("#selectOrderType").css("display","");
            	$("#dateStr").css("display","none");
            } else {
            	$("#selectOrderType").css("display","none");
            	$("#dateStr").css("display","");
            }
        });
    });
</script>

<div class="contextMenuDialog hide" id="item1">
    <div class="card-body">
        <div class="row">
            <table id="tableorderlog"></table>
        </div>
    </div>
</div>
<div class="contextMenuDialog hide" id="item2">
    <div class="card-body">
        <div class="row">
            <table id="tableOrderDetails"></table>
        </div>
    </div>
</div>
</body>

</html>