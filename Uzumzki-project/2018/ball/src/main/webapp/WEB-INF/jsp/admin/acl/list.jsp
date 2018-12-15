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
    <style>
        .gradeMenu-wrap{
            width: 100%;
            overflow: auto;
            white-space: nowrap;
        }
        .gradeMenu-col{
            display: inline-block;
            margin-right: -5px;
            width: 50px;
        }
        .gradeMenu-item-hd{
            white-space: normal;
            font-size: 12px;
            text-align: center;
            height: 100px;
            border: 1px solid;
            background: #ffffcc;
            position: relative;
            padding-bottom: 24px;
        }
        .gradeMenu-name{

        }
        .gradeMenu-type{
            border-top: 1px solid;
            position: absolute;
            bottom: 0px;
            width: 100%;
            line-height: 2;
        }
        .gradeMenu-item{
            display: block;
            text-align: center;
            height: 30px;
            line-height: 30px;
        }
        .gradeMenu-item:nth-of-type(even){
            background: rgba(0, 0, 0, 0.05);
        }
    </style>
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom" id="topSidebar"></nav>
    <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="11"></aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper pt-3">
        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card card-secondary">
                            <div class="card-body table-responsive p-0">
                                <form action="">
							        <!-- <input type="text"> -->
							    </form>
								<div class="gradeMenu-wrap">
									<ul id="gradeMenuWrap">
										<li class="gradeMenu-col">
											<div class="gradeMenu-item-hd">
											</div>
											<div class="gradeMenu-item">一级</div>
											<div class="gradeMenu-item">二级</div>
											<div class="gradeMenu-item">三级</div>
											<div class="gradeMenu-item">四级</div>
											<div class="gradeMenu-item">五级</div>
											<div class="gradeMenu-item">六级</div>
											<div class="gradeMenu-item">七级</div>
										</li>
										<c:forEach var="data" items="${list}">
											<li class="gradeMenu-col">
												<input type="hidden" value="${data.id}" id="dataid">
												<div class="gradeMenu-item-hd">
													<div class="gradeMenu-name">${data.title }</div>
													<div class="gradeMenu-type">
													${data.menuButton == 1 ? '菜单' : '按钮'}
													</div>
												</div>
												<div class="gradeMenu-item">
													<input class="btuRight" data="1" type="checkbox" <c:if test="${data.rightType1 == 1 }">checked="checked"</c:if>>
												</div>
												<div class="gradeMenu-item">
													<input class="btuRight" data="2" type="checkbox" <c:if test="${data.rightType2 == 1 }">checked="checked"</c:if>>
												</div>
												<div class="gradeMenu-item">
													<input class="btuRight" data="3" type="checkbox" <c:if test="${data.rightType3 == 1 }">checked="checked"</c:if>>
												</div>
												<div class="gradeMenu-item">
													<input class="btuRight" data="4" type="checkbox" <c:if test="${data.rightType4 == 1 }">checked="checked"</c:if>>
												</div>
												<div class="gradeMenu-item">
													<input class="btuRight" data="5" type="checkbox" <c:if test="${data.rightType5 == 1 }">checked="checked"</c:if>>
												</div>
												<div class="gradeMenu-item">
													<input class="btuRight" data="6" type="checkbox" <c:if test="${data.rightType6 == 1 }">checked="checked"</c:if>>
												</div>
												<div class="gradeMenu-item">
													<input class="btuRight" data="7" type="checkbox" <c:if test="${data.rightType7 == 1 }">checked="checked"</c:if> disabled="disabled">
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
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
<script type="text/javascript">
$(".btuRight").on("click", function () {
	var check = 0;
	if($(this).prop("checked")){
		check = 1;
	}
	
	var dataid = $(this).parent().parent().find("#dataid").val();
	var rightType = $(this).attr("data");
	$.ajax({
			type : "POST", //提交方式  
			url : "/admin/permission/update",//路径  
			data : {
				check : check,
				dataid : dataid,
				rightType : rightType
			},//数据，这里使用的是Json格式进行传输  
			dataType : "json",
			success : function(result) {//返回数据根据结果进行相应的处理  
			}
		});
	});
</script>
</body>

</html>