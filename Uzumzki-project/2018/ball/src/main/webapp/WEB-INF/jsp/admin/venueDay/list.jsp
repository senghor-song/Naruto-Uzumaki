<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
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
    <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="14"></aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper pt-3">
        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                	<div class="col-lg-2">
                        <div class="card card-secondary">
                            <div class="card-body table-responsive" style="line-height: 25px;padding-left: 10px;margin-bottom: 13px;">    
	                            
	                            <b>风控管理</b><br>
	                            
	                            系统风评:<span style="${systemFlag eq '正常' ? 'color: #28a745;' : 'color: #ef0000cc;' }" >${systemFlag}</span><br>
	                            人工风评:<span style="${confirmFlag eq '正常' ? 'color: #28a745;' : 'color: #ef0000cc;' }" >${confirmFlag}</span><br>
	                            拟转用户:<span style="${realityAmount >= 0 ? 'color: #28a745;' : 'color: #ef0000cc;' }" >
										<fmt:formatNumber type="number" value="${realityAmount }" pattern="0.00" maxFractionDigits="2"/>
	                            		</span><br>
	                            额度补贴:<span style="${paySubsidyFlag ? 'color: #28a745;' : 'color: #ef0000cc;' }" >
										<fmt:formatNumber type="number" value="${paySubsidy }" pattern="0.00" maxFractionDigits="2"/>
	                            		</span><br>
	                            全部存留待转:<span style="${realityAmountSum > 0 ? 'color: #28a745;' : 'color: #ef0000cc;' }" >
										<fmt:formatNumber type="number" value="${realityAmountSum }" pattern="0.00" maxFractionDigits="2"/>
	                            		</span><br>
	                            当日平台费总额:<span style="${profitAmount >= 0 ? 'color: #28a745;' : 'color: #ef0000cc;' }" >
										<fmt:formatNumber type="number" value="${profitAmount }" pattern="0.00" maxFractionDigits="2"/>
	                            		</span><br>
	                            <br>
	                            <b>基本账户</b><br>
	                            当日实际消费:<span style="color: #28a745;" >${showFlag ? daySumAmount : '-----' }</span><br>
	                            交易合计收入:<span style="color: #28a745;" >${showFlag ? dealIncome : '-----' }</span><br>
	                            交易当日收入:<span style="color: #28a745;" >${showFlag ? nowDayAmount : '-----' }</span><br>
	                            
	                            微信手续费:<span style="color: #28a745;" >${showFlag ? dealFee : '-----' }</span><br>
	                            提现:<span style="color: #28a745;" >${showFlag ? withdrawalCash : '-----' }</span><br>
	                            基本转运营:<span style="color: #28a745;" >${showFlag ? basicToOperation : '-----' }</span><br>
	                            运营转基本:<span style="color: #28a745;" >${showFlag ? operationToBasic : '-----' }</span><br>
	                            扫码充值:<span style="color: #28a745;" >${showFlag ? restsIncome : '-----' }</span><br>
	                            交易退款:<span style="color: #28a745;" >${showFlag ? dealRefund : '-----' }</span><br>
	
	                            预付存余:<span style="color: #28a745;" >${showFlag ? oldDayAmount : '-----' }</span><br>
	                            预付当日:<span style="color: #28a745;" >${showFlag ? nowDayOrderAmount : '-----' }</span><br>
	                            基本户当日结余:<span style="color: #28a745;" >${showFlag ? accountAmount : '-----' }</span><br>
	                            <br>
	                            <b>运营账户</b><br>
	                            基本转运营:<span style="color: #28a745;" >${showFlag ? basicToOperationOperation : '-----' }</span><br>
	                            运营转基本:<span style="color: #28a745;" >${showFlag ? operationToBasicOperation : '-----' }</span><br>
	                            提现:<span style="color: #28a745;" >${showFlag ? withdrawalCashOperation : '-----' }</span><br>
	                            扫码充值:<span style="color: #28a745;" >${showFlag ? restsIncomeOperation : '-----' }</span><br>
	                            企业支付:<span style="color: #28a745;" >${showFlag ? paymentAmountOperation : '-----' }</span><br>
	                            运营户当日结余:<span style="color: #28a745;" >${showFlag ? accountAmountOperation : '-----' }</span><br>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-10">
                        <div class="card card-secondary">
                        	<div class="card-header">
                                <h3 class="card-title">
                                    <div class="row">
                                        <div class="col-lg-9 btncaozuo">
	                            			<span style="font-size: 15px;">交易日:${nowDate }</span>
                            				<button class="btn btn-primary btn-sm" id="getBillData" >获取资金对账数据</button>
				                            <button class="btn btn-primary btn-sm" id="venuePay" >批量转账</button>
				                            <button class="btn btn-primary btn-sm" id="venuePayOrder"  >当日实际消费</button>
				                            <button class="btn btn-primary btn-sm" id="prevDate" >前一天</button>
				                            <button class="btn btn-primary btn-sm" id="nextDate" >后一天</button>
				                            <c:if test="${showFlag && confirmFlag eq ''}">
					                            <button class="btn btn-primary btn-sm" id="confirmData" >对账</button>
				                            </c:if>
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
                        url: '/admin/venueDay/list?dateStr=${nowDate}',
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
                                {field: 'createTime', title: '结算时间', sort: true},
                                {field: 'appnickname', title: '回款用户', sort: true},
                                {field: 'memberno', title: '用户编号', sort: true},
                                {field: 'oughtAmount', title: '回款应付', sort: true},
                                {field: 'realityAmount', title: '回款实付', sort: true},
                                {field: 'paySubsidy', title: '额度补贴', sort: true},
                                {field: 'memberFee', title: '剩余额度', sort: true},
                                {field: 'typeFlag', title: '转账状态', sort: true},
                                {field: 'typeMsg', title: '状态信息', sort: true},
                                {field: 'memberDayLogSum', title: '转账日志', sort: true},
                                {field: 'orderCount', title: '子订单', sort: true},
                            ]
                        ]
                    },
                    menuItem: {
                        item1: {
                            name: "日志", callback: function (key, opt) {
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tableVenuDayLog',
                                    tableOption: {
                                        url: '/admin/venueDay/log/list?id='+$(this).find("td").eq(0).attr('title'),
                                        page: false,
                                        height: $("#tableVenuDayLog").parents(".layui-layer-content").height() - 30,
                                        where: {},
                                        cols: [
                                            [
                                                { field: 'createTime', title: '转账时间', sort: true ,width:200},
                                                { field: 'name', title: '操作人', sort: true ,width:100},
                                                { field: 'payType', title: '状态', sort: true ,width:100},
                                                { field: 'typeFlag', title: '方式', sort: true ,width:100},
                                                { field: 'payMsg', title: '状态信息', sort: true },
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                        item2: {
                            name: "转账", callback: function (key, opt) {
                            	var confirmFlag = "${confirmFlag}";
                            	var showFlag = "${showFlag}";
                            	if(!showFlag){
	                				layer.msg("请先对账再进行操作！");
	                        		return;
	                        	}
	                        	if(confirmFlag == ""){
	                				layer.msg("请先对账再进行操作！");
	                        		return;
	                        	}
                            	var type = $(this).find("td").eq(8).attr('title');
                            	var index = layer.load();
                            	if(type == '失败' || type == ''){
                            		$.ajax({  
                                        type : "POST",  //提交方式  
                                        url : "/admin/venueDay/venuePayOne?venueDayId="+$(this).find("td").eq(0).attr('title'),
                        				data : { },//数据，这里使用的是Json格式进行传输 
                        				dataType : "json",
                        				success : function(result) {//返回数据根据结果进行相应的处理
                        					if (result.code == 200) {
                        						layer.close(index);
                        						layer.msg(result.msg);
                        						
                        						layui.table.reload('tableList', {
                        		    				  where: { } ,page: {
                        		    				    curr: 1 //重新从第 1 页开始
                        		    				  }
                        		    			});
                        					} else {
                        						layer.close(index);
                        						layer.msg(result.msg);
                        						
                        						layui.table.reload('tableList', {
                      		    				  where: { } ,page: {
                      		    				    curr: 1 //重新从第 1 页开始
                      		    				  }
                      		    				});
                        					}
                        				},
                        				error : function(e){
                    						layer.close(index);
                    						layer.msg("内部错误");
                        				}
                        			})
                            	} else if(type == '成功'){
            						layer.close(index);
                            		layer.msg("该订单已支付");
                            	}
                            }
                        },
                        item3: {
                            name: "状态变更", callback: function (key, opt) {
                            	var venueDayId = $(this).find("td").eq(0).attr('title');
                            	$("#memberDayId").val(venueDayId);
                            	debugger;
                            	if($(this).find("td").eq(8).attr('title') == '成功'){
                            		layer.msg("该笔交易已成功!不能操作");
                            		return;
                            	}
                                var obj = $("#item4");
                                obj.removeClass("hide");
                                layer.open({
                                    type: 1,
                                    area: ['100%', '30%'],
                                    offset: 'b',
                                    title: '状态变更',
                                    resize: true,
                                    anim: 1,
                                    shadeClose : true,
                                    content: obj,
                    	            cancel: function (index, layero) {
                                        $(".contextMenuDialog").addClass("hide");
                    	            },
                    	            end: function (index, layero) {
                                        $(".contextMenuDialog").addClass("hide");
                    	            }
                                });
                            }
                        },
                        item4: {
                            name: "子订单", callback: function (key, opt) {
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tableOrderList',
                                    tableOption: {
                                        url: '/admin/venueDay/sonOrder/list?amountid='+$(this).find("td").eq(0).attr('title'),
                                        page: false,
                                        height: $("#tableOrderList").parents(".layui-layer-content").height() - 30,
                                        where: {},
                                        cols: [
                                            [
                                                { field: 'orderNo', title: '订单编号', sort: true ,width : 100},
                                                { field: 'orderType', title: '类别', sort: true  ,width : 80},
                                                { field: 'oughtAmount', title: '订单应付', sort: true  ,width : 100},
                                                { field: 'realityAmount', title: '订单实付', sort: true  ,width : 100},
                                                { field: 'amountRefund', title: '用户退款', sort: true  ,width : 100},
                                                { field: 'venuePayAmount', title: '场馆回款', sort: true  ,width : 100},
                                                { field: 'priceFee', title: '平台费', sort: true  ,width : 100},
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
        $("#venuePay").on("click", function () {
        	var confirmFlag = "${confirmFlag}";
        	var showFlag = "${showFlag}";
        	if(!showFlag){
				layer.msg("请先对账再进行操作！");
        		return;
        	}
        	if(confirmFlag == ""){
				layer.msg("请先对账再进行操作！");
        		return;
        	}
        	var index = layer.load();
        	
        	$.ajax({  
                type : "POST",  //提交方式  
                url : "/admin/venueDay/venuePay?dateStr=${nowDate}",//路径  
				data : { },//数据，这里使用的是Json格式进行传输 
				dataType : "json",
				success : function(result) {//返回数据根据结果进行相应的处理
					if (result.code == 200) {
						layer.close(index);
						layer.msg(result.msg);
						
						layui.table.reload('tableList', {
		    				  where: { } ,page: {
		    				    curr: 1 //重新从第 1 页开始
		    				  }
		    			});
					} else {
						layer.close(index);
						layer.msg(result.msg);
					}
				}
			})
        });
        $("#venueCheck").on("click", function () {
            layer.open({
                type: 2,
                area: ['90%', '90%'],
                offset: 'auto',
                title: '审核单',
                resize: true,
                anim: 1,
                shadeClose : true,
                content: "/admin/venueDay/check",
	            cancel: function (index, layero) {
	            },
	            end: function (index, layero) {
	            }
            });
        });
        $("#confirmData").on("click", function () {
        	var confirmFlag = "${confirmFlag}";
        	if(confirmFlag == 1){
        		layer.msg("你已对账!不能重复操作");
        		return;
        	}
        	
            var obj = $("#item2");
            obj.removeClass("hide");
            layer.open({
                type: 1,
                area: ['100%', '30%'],
                offset: 'b',
                title: '对账',
                resize: true,
                anim: 1,
                shadeClose : true,
                content: obj,
	            cancel: function (index, layero) {
                    $(".contextMenuDialog").addClass("hide");
	            },
	            end: function (index, layero) {
                    $(".contextMenuDialog").addClass("hide");
	            }
            });
        });
        $("#saveVenueDay").on("click", function () {
        	var index = layer.load();
        	var selectType = $('#selectType').val();
        	var content = $('#content').val();
        	var dateStr = "${nowDate}";
        	$.ajax({  
                type : "POST",  //提交方式  
                url : "/admin/venueDay/saveVenueDay",//路径  
				data : {selectType:selectType,content:content,dateStr:dateStr },//数据，这里使用的是Json格式进行传输
				dataType : "json",
				success : function(result) {//返回数据根据结果进行相应的处理
					if (result.code == 200) {
						layer.close(index);
						layer.msg(result.msg);
			        	window.location.href = "/admin/venueDay/listview?dateStr=${nowDate}&size=0";
					} else {
						layer.close(index);
						layer.msg(result.msg);
					}
				}
			})
        });
        $("#prevDate").on("click", function () {
        	window.location.href = "/admin/venueDay/listview?dateStr=${nowDate}&size=-1";
        });
        $("#nextDate").on("click", function () {
        	window.location.href = "/admin/venueDay/listview?dateStr=${nowDate}&size=1";
        });
        
        $("#venuePayOrder").on("click", function () {
        	var obj = $("#item3");
            obj.removeClass("hide");
            layer.open({
                type: 1,
                area: ['100%', '50%'],
                offset: 'b',
                title: '当日实际消费',
                resize: true,
                anim: 1,
                shadeClose : true,
                content: obj,
                cancel: function (index, layero) {
                    $(".contextMenuDialog").addClass("hide");
                },
                end: function () {
                    $(".contextMenuDialog").addClass("hide");
	        	}
            });
            var venuePayOrder = {
    	            obj: null,
    	            init: function () {
    	                var self = this;
    	                self.tableList();
    	                self.search();
    	            },
                    search: function () {
                        var self = this;
                    },
                    tableList: function () {
                        var self = this;
                        self.obj = $.tableObject({
                            tableId: 'tableVenuDayOrder',
                            tableOption: {
                                url: '/admin/venueDay/orderDate/list?dateStr=${nowDate}',
                                page: true,
                                height: 390,
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
                                        {field: 'wxOrderid', title: '微信订单号', sort: true},
                                        {field: 'soreOrderid', title: '商户订单号', sort: true},
                                        {field: 'orderAmount', title: '订单实付', sort: true},
                                        {field: 'refundAmount', title: '用户退款', sort: true},
                                        {field: 'amount', title: '实际消费', sort: true},
                                        {field: 'payAmount', title: '场馆回款', sort: true},
                                        {field: 'orderFee', title: '平台费', sort: true},
                                        {field: 'orderType', title: '类型', sort: true},
                                        {field: 'memberNo', title: '用户编号', sort: true},
                                        {field: 'payTime', title: '支付时间', sort: true},
                                        {field: 'orderDate', title: '用场日期', sort: true},
                                        {field: 'venueNo', title: '场馆编号', sort: true},
                                    ]
                                ]
                            }
                        });
                    }
                }
            venuePayOrder.init();
        });
        
        $("#getBillData").on("click", function () {
        	var loadIndex = layer.load();
        	$.ajax({  
                type : "POST",  //提交方式  
                url : "/admin/wxBill/getBillData",//路径  
				data : {},//数据，这里使用的是Json格式进行传输 
				dataType : "json",
				success : function(result) {//返回数据根据结果进行相应的处理  
					if (result.code == 200) {
						layer.close(loadIndex);
						layer.msg("获取成功");
						window.location.reload();
					}else{
						layer.confirm("是否重复获取数据", function(index){
							layer.close(index);
							loadIndex = layer.load();
				        	$.ajax({  
				                type : "POST",  //提交方式  
				                url : "/admin/wxBill/getBillData?deleteFlag=false",//路径  
								data : {},//数据，这里使用的是Json格式进行传输 
								dataType : "json",
								success : function(result) {//返回数据根据结果进行相应的处理  
									if (result.code == 200) {
										layer.close(loadIndex);
										layer.msg("获取成功");
										window.location.reload();
									}
								}
							})
						},function(index){
							layer.close(loadIndex);
						});
					}
				}
			})
        });
        $("#updateMemeberDayType").on("click", function () {
        	var memberDayId = $("#memberDayId").val();
        	var memberDaySelectType = $('#memberDaySelectType').val();
        	var memberDayContent = $('#memberDayContent').val();
        	if(memberDayContent == ""){
        		layer.msg("请输入备注");
        		return;
        	}

        	$.ajax({
				type : "POST", //提交方式  
				url : "/admin/venueDay/updateMemeberDayType",//路径  
				data : {
					memberDayId : memberDayId,
					selectType : memberDaySelectType,
					content : memberDayContent
				},//数据，这里使用的是Json格式进行传输  
				dataType : "json",
				success : function(result) {//返回数据根据结果进行相应的处理  
					if (result.code = 200) {
						layer.closeAll();
						layer.msg(result.msg);
						//上述方法等价于
						layui.table.reload('tableList', {
						  where: { } ,page: { curr: 1 }
						});
					} else {
						layer.msg(result.msg);
					}
				}
			});
        });

        $("#venueAnalyze").on("click", function () {
        	//layui.table.exportFile(title, values, 'xls'); data 为该实例中的任意数量的数据
        });
    });
</script>

<div class="contextMenuDialog hide" id="item1">
    <div class="card-body">
        <div class="row">
            <table id="tableVenuDayLog"></table>
        </div>
    </div>
</div>

<div class="contextMenuDialog hide" id="item3">
    <div class="card-body">
        <div class="row">
            <table id="tableVenuDayOrder"></table>
        </div>
    </div>
</div>

<div class="contextMenuDialog hide" id="item4">
    <div class="card-body">
        <div class="row">
            <table id="tableOrderList"></table>
        </div>
    </div>
</div>

<div class="contextMenuDialog hide" id="item2">
	<div class="card-body">
		<div class="row">
			<div class="col-lg-4 listrow" style="border: none;">
				<span class="nti">人工风评：</span> 
				<span class="ncon"> 
					<select
						class="form-control" name="selectType" id="selectType">
							<option value="正常">正常</option>
							<option value="异常">异常</option>
							<option value="其他">其他</option>
					</select>
				</span>
			</div>
            <div class="col-lg-8 listrow" style="border: none;">
            </div>
			<div class="col-lg-4 listrow" style="padding-top: 20px;border: none;">
				<span class="nti">当日小结：</span> 
				<span class="ncon" style="width: 60%;">
					<input class="form-control" type="text" value="" name="content" id="content" placeholder="当日小结" maxlength="100">
				</span>
			</div>
            <div class="col-lg-8 listrow" style="border: none;">
            </div>
            
            <div class="col-lg-4 listrow" style="padding-top: 20px;border: none;">
                <span class="nti">&nbsp;</span>
                <button class="form-control btn btn-primary w-25" id="saveVenueDay">保存</button>
            </div>
		</div>
	</div>
</div>

<div class="contextMenuDialog hide" id="item4">
	<div class="card-body">
		<div class="row">
			<input type="hidden" value="" id="memberDayId" >
			<div class="col-lg-4 listrow" style="border: none;">
				<span class="nti">状态变更为：</span> 
				<span class="ncon"> 
					<select class="form-control" name="memberDaySelectType" id="memberDaySelectType">
						<option value="3">结束</option>
						<option value="4">其他</option>
					</select>
				</span>
			</div>
            <div class="col-lg-8 listrow" style="border: none;">
            </div>
			<div class="col-lg-4 listrow" style="padding-top: 20px;border: none;">
				<span class="nti">备注：</span> 
				<span class="ncon" style="width: 60%;">
					<input class="form-control" type="text" value="" name="memberDayContent" id="memberDayContent" placeholder="备注" maxlength="100">
				</span>
			</div>
            <div class="col-lg-8 listrow" style="border: none;">
            </div>
            
            <div class="col-lg-4 listrow" style="padding-top: 20px;border: none;">
                <span class="nti">&nbsp;</span>
                <button class="form-control btn btn-primary w-25" id="updateMemeberDayType">保存</button>
            </div>
		</div>
	</div>
</div>
</body>

</html>