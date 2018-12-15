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
    <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="17"></aside>

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
										<div class="input-group input-group-sm float-right col-lg-3"
											style="width: 350px;">
											<select class="form-control float-right" id="WXFundflowSelectType">
												<option value="1">基本户</option>
												<option value="2">运营户</option>
											</select>
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
            </div>
        </section>
    </div>
    <aside class="control-sidebar control-sidebar-dark">
    </aside>
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
                        url: '/admin/wxFundflow/list',
                        page: true,
                        height: $(window).height() - 150,
                        where: {
                        	accountType: function () {
                                return $("#WXFundflowSelectType").val()
                            }
                        },
                        cols: [
                            [
                    			{field: 'id', title: 'id', hide:true},
                                {width:180, field: 'createTime', title: '创建时间', sort: true},
                                {width:180, field: 'recordTime', title: '记账时间', sort: true},
                                {width:200, field: 'wxPayOrderid', title: '微信支付业务单号', sort: true},
                                {width:140, field: 'wxFundflowId', title: '资金流水单号', sort: true},
                                {width:100, field: 'workName', title: '业务名称', sort: true},
                                {width:100, field: 'workType', title: '业务类型', sort: true},
                                {width:100, field: 'dealType', title: '收支类型', sort: true},
                                {width:140, field: 'dealAmount', title: '收支金额(元)', sort: true},
                                {width:140, field: 'accountAmount', title: '账户结余(元)', sort: true},
                                {width:200, field: 'applyUser', title: '资金变更提交申请人', sort: true},
                                {width:100, field: 'remark', title: '备注', sort: true},
                                {width:120, field: 'workId', title: '业务凭证号', sort: true},
                                {width:120, field: 'accountType', title: '账号类型', sort: true},
                            ]
                        ]
                    }
                });
            }
        }
        
        tableObj.init();
        
        $("#WXFundflowSelectType").on("change", function () {
			layui.table.reload('tableList', {
   				  where: { } ,page: {
   				    curr: 1 //重新从第 1 页开始
   				  }
   			});
        });
    });
</script>

</body>

</html>