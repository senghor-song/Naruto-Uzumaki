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
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
	<link rel="icon" href="/admin/static/image/logo.png" type="image/x-icon"/>
    <link href="/admin/static/css/site.css" rel="stylesheet">
    
</head>

<body class="">
	<div class="card card-secondary">
		<div class="card-header" style="background-color: #ffffff !important;">
			<h3 class="card-title">
				<div class="row">
					<div class="input-group input-group-sm float-right col-lg-12">
						<select class="form-control float-right" id="cityid">
							<option value="">城市</option>
							<c:forEach var="city" items="${cities}">
								<option value="${city.id }">${city.city }</option>
							</c:forEach>
						</select> 
						<select class="form-control float-right" id="sumTemplate">
							<option value="">模板数</option>
							<c:forEach var="i" begin="0" end="99">
								<option value="${i}">${i}</option>
							</c:forEach>
						</select> 
						<select class="form-control float-right" id="trainAddFlag">
							<option value="">入驻状态</option>
							<option value="0">空</option>
							<option value="1">添加</option>
							<option value="2">入驻</option>
						</select> 
						<select class="form-control float-right" id="ballType">
							<option value="">球馆类型</option>
							<option value="1">网球</option>
							<option value="2">足球</option>
							<option value="3">羽毛球</option>
						</select>
						<div class="input-group-append">
							<button class="btn btn-default" id="venueSearch" type="button">
								<i class="fa fa-search"></i> 搜索
							</button>
						</div>
						<div class="input-group-append">
							<button class="btn btn-default" id="venueAnalyze" type="button">
								<i class="fa fa-table"></i> 导出
							</button>
						</div>
					</div>
				</div>
			</h3>
		</div>
		<div class="card-body table-responsive" style="padding:  20px;">
			<div id="tableList"></div>
		</div>
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
	<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=ITcG0S4URK9aokGSOhTNnSXCO9o7fK8D"></script>
<script>
    $(function () {
    	var title = [];
    	var values = [];
    	tableVenue();
    	function tableVenue() {
    		var cityid = $('#cityid').val();
    		var sumTemplate = $('#sumTemplate').val();
    		var trainAddFlag = $('#trainAddFlag').val();
    		var ballType = $('#ballType').val();
	    	$.ajax({
				type : "POST", //提交方式  
				url : "/admin/venue/venueAnalyzeList",//路径  
				data : {
					cityid : cityid,
					sumTemplate : sumTemplate,
					trainAddFlag : trainAddFlag,
					ballType : ballType,
				},//数据，这里使用的是Json格式进行传输  
				dataType : "json",
				success : function(result) {//返回数据根据结果进行相应的处理  
					title = result.data.title;
					values = result.data.values;
					var tableList = "";
					for(var i=0;i<result.data.values.length;i++){
						tableList += result.data.values[i]+"</br>";
					}
					$("#tableList").html(tableList);
				}
			});
    	}
        $("#venueSearch").on("click", function () {
        	tableVenue();
        });
        $("#venueAnalyze").on("click", function () {
        	layui.table.exportFile(title, values, 'xls'); //data 为该实例中的任意数量的数据
        });
    });
</script>

</body>

</html>