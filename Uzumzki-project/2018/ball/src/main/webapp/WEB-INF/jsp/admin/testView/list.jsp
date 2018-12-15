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
    <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="16"></aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper pt-3">
        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                	<div class="col-lg-6">
                        <div class="card card-secondary">
                            <div class="card-body table-responsive" style="line-height: 30px;padding-left: 10px;margin-bottom: 13px;">    
	                            用户编号
	                            <input class="form-control" id="memberno" name="memberno" type="text" placeholder="用户编号" maxlength="100" style="display: -webkit-inline-box;width: 200px !important">
	                            增减
	                            <input class="form-control" id="price" name="price" type="text" placeholder="正为加,负为减" maxlength="100" style="display: -webkit-inline-box;width: 200px !important">
	                            <button class="btn btn-primary btn-sm" id="updateMember" style="margin-bottom: 12px;">确定</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="card card-secondary">
                            <div class="card-body table-responsive" style="line-height: 30px;padding-left: 10px;margin-bottom: 13px;">    
                            ${date }全天结算任务
                            <button class="btn btn-primary btn-sm" id="statisVenueDay" style="margin-bottom: 12px;">确定重启</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="card card-secondary">
                        
                            <div class="card-body table-responsive" style="line-height: 30px;padding-left: 10px;margin-bottom: 13px;">    
                            <input class="form-control float-left" id="dateStr" name="dateStr" type="text" placeholder="请选择日期" style="width: 200px;" value="${date }">
                            获取资金对账单数据
                            <button class="btn btn-primary btn-sm" id="getBillData" style="margin-bottom: 12px;">确定获取</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="card card-secondary">
                            <div class="card-body table-responsive" style="line-height: 30px;padding-left: 10px;margin-bottom: 13px;">    
	                            场馆编号
	                            <input class="form-control" id="venueNo" name="venueNo" type="text" placeholder="场馆编号" maxlength="100" style="display: -webkit-inline-box;width: 200px !important">
	                            <button class="btn btn-primary btn-sm" id="fieldList" style="margin-bottom: 12px;">确定</button>
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
        	var sdate = '2018-09-04';
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
        $("#updateMember").on("click", function () {
        	var memberno = $("#memberno").val();
        	var price = $("#price").val();
        	$.ajax({  
                type : "POST",  //提交方式  
                url : "/admin/member/updateWXpayment",//路径  
				data : {memberno : memberno,price : price},//数据，这里使用的是Json格式进行传输 
				dataType : "json",
				success : function(result) {//返回数据根据结果进行相应的处理  
					if (result.code == 200) {
						layer.msg("修改成功");
					}else{
						layer.msg(result.msg);
					}
				}
			})
        });
        $("#statisVenueDay").on("click", function () {
        	var indexVenueLoad = layer.msg('数据计算中...', {
        		  icon: 16 , shade: 0.01, time:false //取消自动关闭
        	});
        	$.ajax({  
                type : "POST",  //提交方式  
                url : "/wxapp/train/common/oneDayPayVenueJob",//路径  
				data : {},//数据，这里使用的是Json格式进行传输 
				dataType : "json",
				success : function(result) {//返回数据根据结果进行相应的处理  
					if (result.code == 200) {
             			layer.close(indexVenueLoad);
						layer.msg("重启成功");
					}else{
						
             			layer.close(indexVenueLoad);
					}
				}
			})
        });
        $("#getBillData").on("click", function () {
        	
        	var dateStr = $('#dateStr').val();
        	var loadIndex = layer.load();
        	$.ajax({  
                type : "POST",  //提交方式  
                url : "/admin/wxBill/getBillData?dateStr="+dateStr,//路径  
				data : {},//数据，这里使用的是Json格式进行传输 
				dataType : "json",
				success : function(result) {//返回数据根据结果进行相应的处理  
					if (result.code == 200) {
						layer.close(loadIndex);
						layer.msg("获取成功");
					}else{
						layer.confirm("是否重复获取数据", function(index){
							layer.close(index);
							loadIndex = layer.load();
				        	$.ajax({  
				                type : "POST",  //提交方式  
				                url : "/admin/wxBill/getBillData?deleteFlag=false&dateStr="+dateStr,//路径  
								data : {},//数据，这里使用的是Json格式进行传输 
								dataType : "json",
								success : function(result) {//返回数据根据结果进行相应的处理  
									if (result.code == 200) {
										layer.close(loadIndex);
										layer.msg("获取成功");
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
        $("#fieldList").on("click", function () {
        	var venueNo = $("#venueNo").val();
        	if (venueNo == "") {
				layer.msg("请输入场馆编号");
				return;
			}
        	$.ajax({  
                type : "POST",  //提交方式  
                url : "/admin/venue/field/flag?venueNo="+venueNo,//路径  
				data : {},//数据，这里使用的是Json格式进行传输 
				dataType : "json",
				success : function(result) {//返回数据根据结果进行相应的处理  
					if (result.code == 200) {
			            var obj = $("#button3");
			            obj.removeClass("hide");
			            layer.open({
			                type: 1,
			                area: ['100%', '50%'],
			                offset: 'b',
			                title: '场地列表',
			                resize: true,
			                anim: 1,
			                shadeClose : true,
			                content: obj,
				            cancel: function (index, layero) {
			                    $(".contextMenuDialog").addClass("hide");
			                    layer.closeAll(); 
				                obj.css("display","none");
				            },
				            end: function (index, layero) {
			                    $(".contextMenuDialog").addClass("hide");
			                    layer.closeAll(); 
				                obj.css("display","none");
				            }
			            });
			            
			            var tableField = {
			   	            obj: null,
			   	            init: function () {
			   	                var self = this;
			   	                self.tableList();
			   	                self.search();
			   	            },
			                search: function () {
			                    
			                },
			                tableList: function () {
			                    var self = this;
			                    self.obj = $.tableObject({
			                        tableId: 'tableFieldList',
			                        tableOption: {
			                            url : "/admin/venue/field/list?venueNo="+venueNo,//路径  
			                            page: true,
			                            height: 360,
			                            where: {
			                            },
			                            cols: [
			                                [
			                     				{field: 'id', title: 'id', hide:true},
			                                    {field: 'venueNo', title: '场馆编号', sort: true},
			                                    {field: 'venueName', title: '场馆名称', sort: true},
			                                    {field: 'fieldName', title: '场地名称', sort: true},
			                                    {fixed: 'right', title: '操作', align:'center', toolbar: '#fieldBar', width:150}
			                                ]
			                            ]
			                        }
			                    });
			                }
			            }
			            tableField.init();
			           	layui.table.on('tool(tableFieldList)', function(obj) {
				   			var data = obj.data;
				   			var layEvent = obj.event;
				   			var tr = obj.tr;
				   			
			   				if (layEvent == 'deleteField') {
			   					$.ajax({
				   					type : "POST", //提交方式  
				   					url : "/admin/venue/field/delete",//路径  
				   					data : {
				   						fieldId : data.id,
				   					},//数据，这里使用的是Json格式进行传输  
				   					dataType : "json",
				   					success : function(result) {//返回数据根据结果进行相应的处理
				   						layer.msg(result.msg);
						   				$.reload(tableField.obj);
				   					}
				   				});
			   				}
			            });
					}else{
						layer.msg(result.msg);
					}
				}
			})
        });

        $("#addField").on("click", function () {
        	var venueNo = $("#venueNo").val();
        	var fieldName = $("#fieldName").val();
        	if (fieldName == "") {
				layer.msg("请输入场地名称");
				return;
			}
        	$.ajax({  
                type : "POST",  //提交方式  
				url : "/admin/venue/field/add",//路径  
				data : {venueNo:venueNo,fieldName:fieldName},//数据，这里使用的是Json格式进行传输 
				dataType : "json",
				success : function(result) {//返回数据根据结果进行相应的处理  
					if (result.code == 200) {
						layer.msg("新增成功");
						layui.table.reload('tableFieldList', {
		    				  where: { } ,page: {
		    				    curr: 1 //重新从第 1 页开始
		    				  }
		    			});
					}else{
						layer.msg(result.msg);
					}
				}
			})
        });
    });
</script>

<script type="text/html" id="fieldBar">
  <!-- 这里同样支持 laytpl 语法，如： -->
    <a class="layui-btn layui-btn-xs" lay-event="deleteField" style="color: #fff;" id="deleteField">删除</a>
</script>

<div class="contextMenuDialog hide" id="button3">
    <div class="card-body">
    	<div class="card-tools" style="padding-bottom: 10px;">
			场地名称:<input class="form-control" style="display: table-row;width:150px" id="fieldName" name="fieldName" type="text"  placeholder="场地名称" maxlength="30">
			<input id="venueEnterId" type="hidden" value="" >
			<button class="btn btn-primary btn-sm" id="addField">添加场地</button>
		</div>
		<div class="row">
            <div class="card-body table-responsive p-0">
                <table id="tableFieldList">
                </table>
            </div>
        </div>
    </div>
</div>

</body>

</html>