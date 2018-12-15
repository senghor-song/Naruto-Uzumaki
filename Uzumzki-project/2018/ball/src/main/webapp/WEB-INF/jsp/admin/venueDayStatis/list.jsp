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
    <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="15"></aside>

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
                        url: '/admin/venueDayStatis/list',
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
                                {field: 'createTime', title: '对账时间', sort: true , width : 190},
                                {field: 'nowDate', title: '交易日', sort: true , width : 140},
                                {field: 'systemFlag', title: '系统风评', sort: true , width : 100},
                                {field: 'realityAmount', title: '拟转用户', sort: true , width : 100},
                                {field: 'paySubsidy', title: '额度补贴', sort: true, width : 100},
                                {field: 'profitAmount', title: '当日盈亏', sort: true, width : 100},
                                {field: 'userFlag', title: '人工风评', sort: true, width : 100},
                                {field: 'countLog', title: '日志', sort: true, width : 80},
                                {field: 'dealIncome', title: '基本户-交易收入', sort: true, width : 160},
                                {field: 'dealRefund', title: '基本户-交易退款', sort: true, width : 160},
                                {field: 'dealFee', title: '基本户-微信手续费', sort: true, width : 180},
                                {field: 'basicToOperation', title: '基本户-基本转运营', sort: true, width : 180},
                                {field: 'operationToBasic', title: '基本户-运营转基本', sort: true, width : 180},
                                {field: 'accountAmount', title: '基本户-当日结余', sort: true, width : 160},
                                {field: 'restsIncome', title: '基本户-扫码充值', sort: true, width : 160},
                                {field: 'paymentAmountOperation', title: '运营户-企业支付', sort: true, width : 160},
                                {field: 'basicToOperationOperation', title: '运营户-基本转运营', sort: true, width : 180},
                                {field: 'operationToBasicOperation', title: '运营户-运营转基本', sort: true, width : 180},
                                {field: 'accountAmountOperation', title: '运营户-当日结余', sort: true, width : 160},
                            ]
                        ]
                    },
                    menuItem: {
                        item1: {
                            name: "日志", callback: function (key, opt) {
                            	$('#venueStatisId').val($(this).find("td").eq(0).attr('title'));
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tableVenuDayLog',
                                    tableOption: {
                                        url: '/admin/venueDayStatis/log/list?id='+$(this).find("td").eq(0).attr('title'),
                                        page: false,
                                        height: $("#tableVenuDayLog").parents(".layui-layer-content").height() - 30,
                                        where: {},
                                        cols: [
                                            [
                                                { field: 'createTime', title: '记录时间', sort: true },
                                                { field: 'name', title: '记录人', sort: true },
                                                { field: 'content', title: '内容', sort: true },
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

        $("#addVenueDayLog").click(function () {
			var content = $('#content').val();
			var venueDayStatisId = $('#venueStatisId').val();
			$.ajax({  
                type : "POST",  //提交方式  
                url : "/admin/venueDayStatis/log/add",//路径  
				data : {
					content : content,
					venueDayStatisId : venueDayStatisId
				},//数据，这里使用的是Json格式进行传输 
				dataType : "json",
				success : function(result) {//返回数据根据结果进行相应的处理  
					if (result.code == 200) {
		    			layer.table.reload('tableVenuDayLog', {
		    				  where: { } ,page: {
		    				    curr: 1 //重新从第 1 页开始
		    				  }
		    			});
					}
				}
			})
        });
    });
</script>

<div class="contextMenuDialog hide" id="item1">
    <div class="card-body">
    	<div class="card-tools" style="padding-bottom: 10px;">
			<div class="input-group input-group-sm" style="width: 450px;">
				<input class="form-control float-right" id="content" name="content" type="text" placeholder="请输入内容" maxlength="100">
				<input  id="venueStatisId" name="venueStatisId" type="hidden">
				<div class="input-group-append">
					<button class="btn btn-default" id="addVenueDayLog" type="button">
						<i class="fa fa-plus"></i> 添加
					</button>
				</div>
			</div>
		</div>
        <div class="row">
            <table id="tableVenuDayLog"></table>
        </div>
    </div>
</div>
</body>

</html>