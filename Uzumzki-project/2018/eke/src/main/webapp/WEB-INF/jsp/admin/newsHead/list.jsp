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
    <link href="/WebRelease/admin/static/css/jquery.lineProgressbar.css" rel="stylesheet">
    <link href="/WebRelease/admin/static/css/site.css" rel="stylesheet">
    <link href="/WebRelease/admin/static/plugins/webuploader/webuploader.css" rel="stylesheet">
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
                                <div class="card-header">
                                    <h3 class="card-title">
                                        <div class="row">
                                            <div class="col-lg-4 btncaozuo">
                                                <button class="btn btn-primary btn-sm" id="importData">大数据</button>
                                            </div>
                                        </div>
                                    </h3>
                                    <div class="card-tools">
                                        <div class="input-group input-group-sm" style="width: 350px;">
                                            <select class="form-control float-right" id="selectType">
                                                <option value="0">编号</option>
                                                <option value="1">标题</option>
                                            </select>
                                            <input class="form-control float-right" id="keyword" name="table_search" type="text" placeholder>

                                            <div class="input-group-append">
                                                <button class="btn btn-default" id="btnSearch" type="submit">
                                                    <i class="fa fa-search"></i> 搜索
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="card-body table-responsive p-0">
                                    <table id="tableList"></table>
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
    <script src="/WebRelease/admin/static/plugins/jquery/jquery.min.js"></script>
    <script src="/WebRelease/admin/static/js/layout.js"></script>
    <script src="/WebRelease/admin/static/plugins/jQueryUI/jquery-ui.min.js"></script>
    <script>
        $.widget.bridge('uibutton', $.ui.button)
    </script>
    <script src="/WebRelease/admin/static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="/WebRelease/admin/static/js/raphael-min.js"></script>
    <script src="/WebRelease/admin/static/plugins/morris/morris.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    <script src="/WebRelease/admin/static/plugins/knob/jquery.knob.js"></script>
    <script src="/WebRelease/admin/static/plugins/moment/moment.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/daterangepicker/daterangepicker.js"></script>
    <script src="/WebRelease/admin/static/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="/WebRelease/admin/static/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/fastclick/fastclick.js"></script>
    <script src="/WebRelease/admin/static/plugins/js/pages/dashboard.js"></script>
    <script src="/WebRelease/admin/static/plugins/js/demo.js"></script>
    <script src="/WebRelease/admin/static/js/echarts.min.js"></script>
    <script src="/WebRelease/admin/static/js/jqBootstrapValidation-1.3.7.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/layui/layui.all.js"></script>
    <script src="/WebRelease/admin/static/plugins/jQuery-contextMenu/jquery.contextMenu.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="/WebRelease/admin/static/js/jq-ext.js"></script>
    <script src="/WebRelease/admin/static/plugins/webuploader/webuploader.js"></script>
    <script src="/WebRelease/admin/static/js/jquery.lineProgressbar.js"></script>
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
                            url: '/WebRelease/admin/newsHead/list',
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
                                    { field: 'newsheadno', title: '编号', sort: true },
                                    { field: 'status', title: '状态', sort: true },
                                    { field: 'title', title: '标题', sort: true },
                                    { field: 'sharecount', title: '分享', sort: true },
                                    { field: 'newsHeadLogSum', title: '日志', sort: true },
                                    { field: 'sort', title: '排序', sort: true },
                                ]
                            ]
                        },
                        menuItem: {
                            item1: {
                                name: "编辑", callback: function (key, opt) {
                                	$.showContentMenuAjax(key, opt, $(window).height(), "/WebRelease/admin/newsHead/editNewsHead", dataList[$(this).attr('data-index')].id, "update");
                                }
                            },
                            item2: {
                                name: "预览", callback: function (key, opt) {
                                	$.showContentMenuAjax(key, opt, $(window).height(), "/WebRelease/admin/newsHead/editNewsHead", dataList[$(this).attr('data-index')].id, "look");
                                }
                            },
                            item3: {
                                name: "删除", callback: function (key, opt) {
                                	var id = dataList[$(this).attr('data-index')].id;
                                	var title = dataList[$(this).attr('data-index')].title == undefined ? "" : dataList[$(this).attr('data-index')].title;
                                	layer.open({
                                        title: '删除确认框',
                                        content: "确定删除"+title+"吗?",
                                        btn: ['是', '否'],
                                        yes: function (index) {
                                        	$.ajax({  
                                                type : "POST",  //提交方式  
                                                url : "/WebRelease/admin/newsHead/delNewsHead",//路径  
                                                data : {id: id},//数据，这里使用的是Json格式进行传输  
                                                dataType:"json",
                                                success : function(result) {//返回数据根据结果进行相应的处理 
                                                	layer.close(index);
                                                	$.reload(self.obj);
                                                }
                                            });
                                        }
                                    });
                                }
                            },
                            item4: {
                                name: "日志", callback: function (key, opt) {
                                    $.showContentMenu(key, opt)
                                    $.tableObject({
                                        tableId: 'tablelog',
                                        tableOption: {
                                            url: '/WebRelease/admin/newsHead/newsHeadLog/list?id=' + dataList[$(this).attr('data-index')].id,
                                            page: true,
                                            height: $("#tablelog").parents(".layui-layer-content").height() - 30,
                                            where: {},
                                            cols: [
                                                [
                                                    { field: 'logtime', title: '时间', sort: true },
                                                    { field: 'rname', title: '操作人', sort: true },
                                                    { field: 'content', title: '内容', sort: true },
                                                ]
                                            ]
                                        }
                                    });
                                }
                            },

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
	        /* function importExcel(){
		        $("#form1").ajaxSubmit({
					type: 'POST',  
			        url: $("#form1").attr("action"),  
			        success:function(data, status, err) {
			        	if( data.code == "400" ){
			        		layer.msg(data.msg, { icon: 2, time: 2000 });
			        	}else{
		        			layer.msg(data.msg, { icon: 1, shade: 0.5 });
			        	}  
			        } ,    
			        dataType: 'json',  
			        error : function(xhr, status, err) {  
			        	layer.msg(xhr.msg);             
			        }  
				})
				return false;   //防止表单自动提交   
	        } */
          			//绑定页面的上传图片的JS
        		  var Myuploader = WebUploader.create({
                      auto: true,   
                      swf: '/WebRelease/admin/static/plugins/webuploader/Uploader.swf',
                      server: "/WebRelease/admin/newsHead/importExcel", 
                      pick: '#picker'
                  });
        		var interval = null;
      			var current = 0;
      			//上传前触发
      			Myuploader.on('uploadStart', function (file){ 
      				interval = setInterval(increment,1000);
      			})
      			$('#progressbar1').LineProgressbar({
					percentage: 0,
					fillBackgroundColor: '#1abc9c'
				});
      			function increment(){
      				$.ajax({  
      			        type : "POST",  //提交方式  
      			        url : "/WebRelease/admin/common/redisupload?redisname=newshead",//路径  
      			        data : {},//数据，这里使用的是Json格式进行传输  
      			        dataType:"json",
      			        success : function(result) {//返回数据根据结果进行相应的处理  
      			            if ( result.code == 200 ) {  
      							if(result.data.current == 100) { clearInterval(interval); }
      							$('.proggress').animate({width:result.data.current+'%'}); 
      							$('.percentCount').html(result.data.current+'%'); 
      			            } else {  
      							$('.proggress').animate({width:'100%'}); 
      							$('.percentCount').html('100%'); 
      			            }  
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
    <div id="ExcelUpload" >
	    <div id="uploader" class="wu-example">
		    <!--用来存放文件信息-->
			<div id="thelist" class="uploader-list"></div>
			    <div class="btns">
			        <div id="picker">选择文件</div>
			    </div>
			</div>
			<div class="float-left mr-1">
                <a class="form-control btn btn-primary" href="https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/ekeResource/download/%E6%A5%BC%E8%AE%AF%E6%A8%A1%E6%9D%BF.xlsx" download="楼讯模板">Excel规范模板下载</a>
            </div>
            <br>
            <br>
  			<div id="progressbar1"style="width:50%;"></div>
		<div id="uploader_btn"></div>
		<div id="datamsg"></div>
    </div>
    <form action="/WebRelease/admin/newsHead/importExcel" enctype="multipart/form-data" id="form1" method="post">
    <div class="contextMenuDialog hide" id="dataContent">
        <div class="card-body">
            <div class="row">
                <div class="input-group col-lg-3">
                    <div class="custom-file">
                        <input class="custom-file-input" id="exampleInputFile" type="file" name="file"
                        accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel,.csv">
                        <label class="custom-file-label" for="exampleInputFile">选择Excel文件</label>
                    </div>
                </div>
                <div class="col-lg-3">
                    <input class="btn btn-primary" type="submit" value="导入Excel" id="importExcel">
                </div>
                <div class="col-lg-3 layui-col-xs-offset3">
                    <div class="row">
                        <div class="text-right">
                            <div class="clearfix">
                                <!-- <div class="float-left mr-1">
                                    <button class="form-control btn btn-primary">加入</button>
                                </div>
                                <div class="float-left mr-1">
                                    <button class="form-control btn btn-primary">预览</button>
                                </div> -->
                                <div class="float-left mr-1">
                                    <a class="form-control btn btn-primary" href="https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/ekeResource/download/%E6%A5%BC%E8%AE%AF%E6%A8%A1%E6%9D%BF.xlsx" download="楼讯模板">Excel规范模板下载</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-lg-7">
                    <div class="row">
                        <!-- <table id="tabledata">
						  <thead>
						    <tr>
						      <th lay-data="{field:'count'}">总条数</th>
						      <th lay-data="{field:'succeed'}">成功条数</th>
						      <th lay-data="{field:'error'}">错误条数</th>
						    </tr> 
						  </thead>
						  <tbody>
						    <tr>
						      <td id="count"></td>
						      <td id="succeed"></td>
						      <td id="error"></td>
						    </tr>
						  </tbody>
						</table> -->
                    </div>
                </div>
                <div class="col-lg-5">
                    <textarea class="p-md-2 w-100 h-100" id="progress" name="progress" rows="10">失败列行数:1,2</textarea>
                </div>
            </div>
        </div>
    </div>
    </form>

    <div class="contextMenuDialog hide" id="item3">
        <div class="card-body">
            <div class="row">
                <table id="tablescore"></table>
            </div>
        </div>
    </div>
    <div class="contextMenuDialog hide" id="item4">
        <div class="card-body">
            <div class="row">
                <table id="tablelog"></table>
            </div>
        </div>
    </div>
</body>

</html>