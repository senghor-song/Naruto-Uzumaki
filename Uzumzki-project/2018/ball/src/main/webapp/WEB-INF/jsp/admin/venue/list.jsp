<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>后台管理系统</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
    <link href="/WebBackAPI/admin/css/jquery.lineProgressbar.css" rel="stylesheet">
    <link href="/WebBackAPI/admin/static/css/site.css" rel="stylesheet">
    <link href="/WebBackAPI/admin/static/plugins/webuploader/webuploader.css" rel="stylesheet">
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom" id="topSidebar"></nav>
    <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="4"></aside>

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
                                        <div class="col-lg-4 btncaozuo">
                                            <button class="btn btn-primary btn-sm" id="importData">大数据</button>
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
    <script src="/WebBackAPI/admin/static/plugins/webuploader/webuploader.js"></script>
    <script src="/WebBackAPI/admin/static/js/jquery.lineProgressbar.js"></script>
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
                        url: '/WebBackAPI/admin/venue/list',
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
                                {field: 'city', title: '城市', sort: true},
                                {field: 'district', title: '区县', sort: true},
                                {field: 'name', title: '场馆', sort: true},
                                {field: 'type', title: '类型', sort: true},
                                {field: 'fieldSum', title: '场地', sort: true},
                                {field: 'coachSum', title: '教练', sort: true},
                                {field: 'managerSum', title: '管理员', sort: true},
                                {field: 'venueTemplateSum', title: '模板', sort: true},
                                {field: 'venuelogSum', title: '日志', sort: true},
                                {field: 'amount', title: '累计金额', sort: true},
                                {field: 'balance', title: '存余金额', sort: true},
                                {field: 'freezeamount', title: '冻结金额', sort: true},
                                {field: 'view11', title: '培训课程', sort: true},
                            ]
                        ]
                    },
                    menuItem: {
                        item1: {
                            name: "管理员", callback: function (key, opt) {
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tablemanager',
                                    tableOption: {
                                        url: '/WebBackAPI/admin/venue/managerlist?venueid='+dataList[$(this).attr('data-index')].id,
                                        page: true,
                                        height: $("#tablemanager").parents(".layui-layer-content").height() - 30,
                                        where: {},
                                        cols: [
                                            [
                                                { field: 'createtime', title: '创建时间', sort: true },
                                                { field: 'name', title: '姓名', sort: true },
                                                { field: 'realname', title: '实名', sort: true },
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                        item2: {
                            name: "日志", callback: function (key, opt) {
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tablevenuelog',
                                    tableOption: {
                                        url: '/WebBackAPI/admin/venue/venueloglist?venueid='+dataList[$(this).attr('data-index')].id,
                                        page: false,
                                        height: $("#tablevenuelog").parents(".layui-layer-content").height() - 30,
                                        where: {},
                                        cols: [
                                            [
                                                { field: 'createtime', title: '时间', sort: true },
                                                { field: 'manager', title: '操作人', sort: true },
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
        
        $("#importData").on("click", function () {
            var obj = $("#ExcelUpload");
            obj.css("display","block");
            layer.open({
                type: 1,
                area: ['100%', '500px'],
                offset: 'b',
                title: '大数据',
                resize: true,
                anim: 1,
                shadeClose : true,
                maxmin: false,
			    resize: false,
			    shade: true,
                content: obj,
                cancel: function (index, layero) {
                    obj.css("display","none");
                },
                end: function (index, layero) {
                    obj.css("display","none");
                }
            });
        });
		//绑定页面的上传图片的JS
		var Myuploader = WebUploader.create({
          auto: true,   
          swf: '/WebBackAPI/admin/static/plugins/webuploader/Uploader.swf',
          server: "/WebBackAPI/admin/venue/importExcel", 
          pick: '#picker'
      	});
    		var interval = null;
  			var current = 0;
  			//上传前触发
  			Myuploader.on('uploadStart', function (file){ 
				// 初始化
				$('.proggress').animate({width:'0%'}); 
				$('.percentCount').html('0%'); 
        		$('#datamsg').html("");
  				
  				interval = setInterval(increment,1000);
  			})
  			$('#progressbar1').LineProgressbar({
				percentage: 0,
				fillBackgroundColor: '#1abc9c'
			});
  			function increment(){
  				$.ajax({  
  			        type : "POST",  //提交方式  
  			        url : "/WebBackAPI/admin/common/redisupload?redisname=venue",//路径  
  			        data : {},//数据，这里使用的是Json格式进行传输  
  			        dataType:"json",
  			        success : function(result) {//返回数据根据结果进行相应的处理  
  			            if ( result.code == 200 ) {  
  							if(result.data.current == 100) { clearInterval(interval); }
  							$('.proggress').animate({width:result.data.current+'%'}); 
  							$('.percentCount').html(result.data.current+'%'); 
  							$('#dataPage').html(result.data.page); 
  			            } else {
  	  			        	$.ajax({  
  	  		  			        type : "POST",  //提交方式  
  	  		  			        url : "/WebBackAPI/admin/common/deleteRedisupload?redisname=venue",//路径  
  	  		  			        dataType:"json",
  	  		  			        success : function(result) {}
  	  		  			    }); 
  			            	layer.msg("上传失败");
  							$('.proggress').animate({width:'100%'}); 
  							$('.percentCount').html('100%'); 
  			            }  
  			        },
  			        error: function(){
  			        	$.ajax({  
  		  			        type : "POST",  //提交方式  
  		  			        url : "/WebBackAPI/admin/common/deleteRedisupload?redisname=venue",//路径  
  		  			        dataType:"json",
  		  			        success : function(result) {}
  		  			    }); 
  			        	clearInterval(interval);
  			        }
  			    }); 
  			}
    		  //上传成功
    		  Myuploader.on('uploadSuccess', function (file, data){ 
    			if( file != "" && file != null && file != undefined ){
                   var _format =file.ext; 
                   var _name =file.name; 
    	                if( _format == "xls" || _format == "xlsx" || _format == "xlt" || _format == "xlsm" || _format == "XLS" || _format == "XLSX" || _format == "XLT" || _format == "XLSM"  ){
    	                	if(data.code == 200){
    	                		var msg = "</br></br>";
    	                		msg += "导入总数量:" + data.data.count + "</br>";
    	                		msg += "导入成功数量:" + data.data.succeed + "</br>";
    	                		msg += "导入失败数量:" + data.data.error + "</br>";
    	                		msg += "导入成功日志:" + data.data.succeedlist + "</br>";
    	                		msg += "导入失败日志:" + data.data.errorlist + "</br>";
    	                		
    	                		$('#datamsg').html(msg);
    		                	var index = layer.alert(data.msg, {
    	    						skin: 'layui-layer-molv', //样式类名
    	    						closeBtn: 0,
    	    						anim: 4, //动画类型
    	    						yes:function(){
    	                                layer.close(index); 
    								}
    	    					});
                            }else{
                            	layer.alert(data.msg, {
                                    skin: 'layui-layer-molv', //样式类名
                                    closeBtn: 0,
                                    anim: 4 //动画类型
                                });
                                layer.closeAll('loading'); 
                                return;
                            }
    	              	}else{
    	              		layer.alert("请上传xls,xlsx,xlt,xlsm格式的文件！", {
    							skin: 'layui-layer-molv', //样式类名
    							closeBtn: 0,
    							anim: 4 //动画类型
    						});
    		              	layer.closeAll('loading'); 
    	              		return;
    	              	};  
                   }; 
    		  });
		//文件上传失败，显示上传出错。
        Myuploader.on('uploadError', function (file) {
            console.log(file, "error", arguments);
            if (typeof com != "undefined") {
                layer.msg("文件上传失败");
            }
        }); 

    });
</script>
<div class="contextMenuDialog hide" id="item1">
    <div class="card-body">
        <div class="row">
            <table id="tablemanager"></table>
        </div>
    </div>
</div>
<div class="contextMenuDialog hide" id="item2">
    <div class="card-body">
        <div class="row">
            <table id="tablevenuelog"></table>
        </div>
    </div>
</div>
<div id="ExcelUpload" >
	<div id="uploader" class="wu-example">
	    <!--用来存放文件信息-->
		<div id="thelist" class="uploader-list"></div>
		    <div class="btns">
		        <div id="picker">选择文件</div>
		    </div>
		</div>
		<div class="float-left mr-1">
            <a class="form-control btn btn-primary" 
            href="https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/ekeResource%2Fdownload%2F%E5%9C%BA%E9%A6%86%E6%A8%A1%E6%9D%BF.xlsx" download="场馆模板">Excel规范模板下载</a>
        </div>
        <br>
        <br>
		<div id="dataPage">0/0</div>
    	<div id="progressbar1"style="width:50%;"></div>
		<div id="uploader_btn"></div>
		<div id="datamsg"></div>
    </div>
<div class="contextMenuDialog hide" id="dataContent">
    <div class="card-body">
        <div class="row">
            <div class="input-group col-lg-3">
                <div class="custom-file">
                    <input class="custom-file-input" id="exampleInputFile" type="file">
                    <label class="custom-file-label" for="exampleInputFile">选择Excel文件</label>
                </div>
            </div>
            <div class="col-lg-3">
                <input class="btn btn-primary" type="button" value="开始">
            </div>
            <div class="col-lg-6">
                <div class="callout callout-info clearfix">
                    <h5 class="float-left">
                        <i class="fa fa-info"></i>备注：</h5>
                    <span class="float-left">excel命名规范：表名=sheet1，城市列名=城市，小区列名=小区</span>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-7">
                <div class="row">
                    <table id="tabledata"></table>
                </div>
            </div>
            <div class="col-lg-5">
                <textarea class="p-md-2" id="progress" name="progress" style="width: 100%;" rows="10"></textarea>
            </div>
        </div>
    </div>
</div>
</body>

</html>