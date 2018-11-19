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
	<link rel="icon" href="/WebBackAPI/admin/static/image/logo.png" type="image/x-icon"/>
    <link href="/WebBackAPI/admin/static/css/site.css" rel="stylesheet">
    <style>
        @page{
            size: auto;
            margin:0mm;   
        }
        .bill-col{
            width: 24%;
            display: inline-block;
            vertical-align: text-top;
            box-sizing: border-box;
            padding-left: 40px;
        }
        html body{
            min-height: 100%;
            line-height: 1.8;
            padding: 0;
            margin: 0;
            font-size: 14px;
        }
        .bill-fd{
            position: absolute;
            bottom: 0px;
            width: 99%;
        }
        li{
            list-style: none;
        }
        ul{
            padding: 0;
        }
        hr{
            margin: 0;
        }
        th{
            font-weight: normal;
        }
        th,td{width: 25%;}
        .button-group{ display: inline-block ; margin-left: 25%;}
        .print-btn{margin-left: 100px;}
        .bill-date{padding: 0 10px;}
        .row-this{margin: 50px 50px 0 300px;}
            
        @media print{ 
            .bill-header{display: none;}
            .bill-hd{margin-top:40px;}
            .row-this{margin: 50px 50px 0 50px;}
        }
    </style>
</head>

<body class="hold-transition sidebar-mini">
    <div class="wrapper">
        <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom" id="topSidebar"></nav>
        <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="12"></aside>

                    <div class="row row-this" style="height: 700px;">
                        <div class="col-lg-12" style="height: 100%; border: 1px solid #000000; border-radius: 20px;">
							<div class="bill-header" >
						        <div class="button-group" >
						            <button class="btn btn-primary btn-sm">刷新</button>
						            <button class="btn btn-primary btn-sm print-btn"  onclick="iframePrint()">打印</button>
						        </div>
						        <div class="button-group" >
						            <button class="btn btn-primary btn-sm">前一天</button>
						            <span class="bill-date" >2018-10-22</span>
						            <button class="btn btn-primary btn-sm">后一天</button>
						        </div>
						    </div>
						    <div id="printBody">
							    <div class="bill-hd">
							        <div class="bill-col" >交易日期：2018-10-21</div>
							        <div class="bill-col" ></div>
							        <div class="bill-col" ></div>
							        <div class="bill-col" >对账时间：2018-10-22</div>
							    </div>
							    <div class="bill-row" style="border-top: 1px solid #000000;">
							        <div class="bill-col" >系统对账风评：正常/异常</div>
							        <div class="bill-col" >
							            <ul>
							                <li>提现笔数：xx</li>
							                <li>提现总额：xxxx</li>
							                <li>基本户全天入账：xxxx</li>
							            </ul>
							        </div>
							        <div class="bill-col" >
							            <ul>
							                <li>新人额度提现：xxxx</li>
							                <li>新人额度补贴：xxx</li>
							                <li>新机构额度提现：123.45</li>
							                <li>新机构额度补贴：123.45</li>
							            </ul>
							        </div>
							        <div class="bill-col" >
							            <ul>
							                <li>基本户余额(对账时)：123.45</li>
							                <li>基本户当前余额：123.45</li>
							                <li>运营户余额(对账时)：123.45</li>
							                <li>运营户当前余额：123.45</li>
							            </ul>
							        </div>
							    </div>
							    <div class="bill-row" >
							        <div class="bill-col" >当日盈亏：123.45</div>
							    </div>
							    <div class="bill-row" >
							        <div class="bill-col" >转入对公账户：123.45</div>
							        <div class="bill-col" >平台费：20.00</div>
							    </div>
							    
							    <div class="bill-fd" style="height: 42px;border-top: 1px solid #000000;">
							        <div class="bill-col" >会计：</div>
							        <div class="bill-col" >财务：</div>
							        <div class="bill-col" >审批：</div>
							        <div class="bill-col" ></div>
							    </div>
                           	</div>
                        </div>
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
		function iframePrint() { //添加打印事件
			window.print();
			/* layer.open({
				title : "位置",
				type : 1,
				anim : 1,
				shade : 0.3,
				closeBtn : 0,
				offset : '20px',
				title : false,
				move : false,
				shadeClose : true,
				resize : false,
				area : [ '50%', '70%' ],
				content : $('#printBody'),
				cancel : function(index, layero) {
				},
				end : function() {
				}
			}); */
		}
		$('#printBody').click(function(){
			window.print();
		});
	</script>
	
</body>
</html>