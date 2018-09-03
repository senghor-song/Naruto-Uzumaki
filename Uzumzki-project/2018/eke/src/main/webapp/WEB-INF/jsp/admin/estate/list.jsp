<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>后台管理系统</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/WebRelease/admin/static/css/site.css" rel="stylesheet">
    <link href="/WebRelease/admin/static/css/jquery.lineProgressbar.css" rel="stylesheet">
	<link rel="stylesheet" href="/WebRelease/admin/static/plugins/bootstrap-validator/dist/css/bootstrapValidator.css" />
	<link rel="stylesheet" href="/WebRelease/admin/static/plugins/bootstrap-validator/vendor/bootstrap/css/dataValidator.css" />
    <link href="/WebRelease/admin/static/plugins/webuploader/webuploader.css" rel="stylesheet">
</head>

<body class="hold-transition sidebar-mini">
    <div class="wrapper">
        <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom" id="topSidebar"></nav>
        <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="8"></aside>

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
                                                <button class="btn btn-primary btn-sm mr-2" id="bigData">大数据</button>
                                                <!--<button class="btn btn-primary btn-sm mr-2" id="export">发现</button>-->
                                                <button class="btn btn-primary btn-sm mr-2" id="create">创建</button>
                                            </div>
                                        </div>
                                    </h3>
                                    <!--<div class="card-tools">
                                        <div class="input-group input-group-sm" style="width: 350px;">
                                            <select class="form-control float-right" id="selectType">
                                                <option value="0">小区</option>
                                            </select>
                                            <input class="form-control float-right" id="keyword" name="table_search" type="text" placeholder>

                                            <div class="input-group-append">
                                                <button class="btn btn-default" id="btnSearch" type="submit">
                                                    <i class="fa fa-search"></i> 搜索
                                                </button>
                                            </div>
                                        </div>
                                    </div>-->
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
    <script src="/WebRelease/admin/static/plugins/layer/layer.js"></script>
    <script src="/WebRelease/admin/static/plugins/webuploader/webuploader.js"></script>
	<script type="text/javascript" src="/WebRelease/admin/static/plugins/bootstrap-validator/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/WebRelease/admin/static/plugins/bootstrap-validator/dist/js/bootstrapValidator.js"></script>
    <script src="/WebRelease/admin/static/js/jquery.lineProgressbar.js"></script>
    <script>
	    function leftClick(self){
	        self.parent().find('.selectMenuBg').removeClass('selectMenuBg');
	        self.addClass("selectMenuBg");
	        $("#estateMatchId").val(dataList2[self.attr('data-index')].id);
		}
	    function addEstateMatch(){
	    	var estateId = $("#estateId").val();
	    	var webId = $("#webId").val();
	    	var estateMatchName = $("#estateMatchName").val();
			$.ajax({  
	            type : "POST",  //提交方式  
	            url : "/WebRelease/admin/estate/addEstateMacth",//路径  
	            data : {
	            	estateId : estateId,
	            	webId : webId,
	            	name : estateMatchName
	            },//数据，这里使用的是Json格式进行传输  
	            dataType: "json",
	            success : function(result) {//返回数据根据结果进行相应的处理
	            	if(result.code == 200){
	        			layer.msg("添加成功");
	        	    	showEstateMatch(estateId);
	            	}else{
	        			layer.msg("添加失败,该网站已添加匹配值,如需添加新的匹配值,请删除旧匹配值");
	            	}
	            }
	        });
	    }
	    function delEstateMatch(){
	    	var estateId = $("#estateId").val();
	    	var estateMatchId = $("#estateMatchId").val();
			if(estateMatchId == ""){
				layer.msg("请选择要删除的匹配信息");
				return;
			}
			$.ajax({  
	            type : "POST",  //提交方式  
	            url : "/WebRelease/admin/estate/delEstateMacth",//路径  
	            data : {
	            	id : estateMatchId
	            },//数据，这里使用的是Json格式进行传输  
	            dataType: "json",
	            success : function(result) {//返回数据根据结果进行相应的处理
	            	if(result.code == 200){
	        			layer.msg("删除成功");
	        	        $("#estateMatchId").val("");
	        	    	showEstateMatch(estateId);
	            	}else{
	        			layer.msg("删除失败");
	            	}
	            }
	        });
	    }
	    
	    function showEstateMatch(id){
	    	$.tableObject({
                tableId: 'third',
                tableOption: {
                    url: "/WebRelease/admin/estate/matchEstateNameList?id="+ id,
                    page: false,
                    height: $("#third").parents(".layui-layer-content").height() - 86,
                    where: {},
                    cols: [
                        [
                            { field: 'matchno', title: '编号', sort: true },
                            { field: 'web', title: '网站', sort: true },
                            { field: 'name', title: '匹配值', sort: true },
                        ]
                    ]
                }
            });
	    }
	    
        $(function () {
        	var pageurl = "/WebRelease/admin/estate/list";
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
                            url: pageurl,
                            page: true,
                            height: $(window).height() - 145,
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
                                    { field: 'city', title: '城市', sort: true},
                                    { field: 'estate', title: '小区', sort: true},
                                    { field: 'longitudeAndLatitude', title: '经纬', sort: true},
                                    { field: 'headimg', title: '封面图', sort: true},
                                    { field: 'estateImageCount', title: '展示图', sort: true },
                                    { field: 'imageStorageCount', title: '仓库图', sort: true },
                                    { field: 'propertyCount', title: '租售', sort: true },
                                    { field: 'storeCount', title: '归属商户', sort: true },
                                    { field: 'empStoreEstateCount', title: '指定商户归属', sort: true },
                                    { field: 'estateCorrectCount', title: '信息纠错', sort: true },
                                    { field: 'estateName', title: '第三方匹配', sort: true },
                                    { field: 'estateLogSum', title: '日志', sort: true },
                                ]
                            ]
                        },
                        menuItem: {
                            item1: {
                                name: "查看", callback: function (key, opt) {
                                    /* $.showContentMenuAjax(key, opt, 405, "/WebRelease/admin/estate/datails", dataList[$(this).attr('data-index')].id, "look"); */
                                	$.showContentMenuOpen(key, opt, 505, "/WebRelease/admin/estate/datails?id=" + dataList[$(this).attr('data-index')].id + "&type=look");
                                }
                            },
                            item2: {
                                name: "编辑", callback: function (key, opt) {
                                	$.showContentMenuOpen(key, opt, $(window).height(), "/WebRelease/admin/estate/datails?id=" + dataList[$(this).attr('data-index')].id + "&type=datails");
                                }
                            },
                            item8: {
                                name: "小区图", callback: function (key, opt) {
                                	$.showContentMenuAjax(key, opt, $(window).height(), "/WebRelease/admin/estate/estateImg", dataList[$(this).attr('data-index')].id, "look");
                                }
                            },
                            item4: {
                                name: "纠错处理", callback: function (key, opt) {
                                    $.showContentMenu(key, opt, 600)

                                    $.tableObject({
                                        tableId: 'tablejc',
                                        tableOption: {
                                            url: "/WebRelease/admin/estate/estateCorrect/list?id="+ dataList[$(this).attr('data-index')].id,
                                            page: true,
                                            where: {},
                                            height: $("#tablejc").parents(".layui-layer-content").height() - 50,
                                            cols: [
                                                [
                                                    { field: 'createtime', title: '纠错时间', sort: true },
                                                    { field: 'item', title: '项', sort: true },
                                                    { field: 'content', title: '参考值', sort: true },
                                                    { field: 'presenter', title: '纠错人标识', sort: true },
                                                ]
                                            ]
                                        }
                                    });
                                }
                            },
                            item5: {
                                name: "日志", callback: function (key, opt) {
                                    $.showContentMenu(key, opt, 600)
                                    $.tableObject({
                                        tableId: 'tablelog',
                                        tableOption: {
                                            url: "/WebRelease/admin/estate/estateLog/list?id="+ dataList[$(this).attr('data-index')].id,
                                            page: true,
                                            height: $("#tablelog").parents(".layui-layer-content").height() - 40,
                                            where: {},
                                            cols: [
                                                [
                                                    { field: 'logtime', title: '时间', sort: true },
                                                    { field: 'staff', title: '操作人', sort: true },
                                                    { field: 'content', title: '内容', sort: true },
                                                ]
                                            ]
                                        }
                                    });
                                }
                            },
                            item7: {
                                name: "第三方匹配", callback: function (key, opt) {
                                    $.showContentMenu(key, opt, 450);
                                    $("#estateName").html(dataList[$(this).attr('data-index')].estate);
                                    $("#estateMatchName").val("");
                                    $("#estateId").val(dataList[$(this).attr('data-index')].id);
                                    $.tableObject({
                                        tableId: 'third',
                                        tableOption: {
                                            url: "/WebRelease/admin/estate/matchEstateNameList?id="+ dataList[$(this).attr('data-index')].id,
                                            page: false,
                                            height: $("#third").parents(".layui-layer-content").height() - 86,
                                            where: {},
                                            cols: [
                                                [
                                                    { field: 'matchno', title: '编号', sort: true },
                                                    { field: 'web', title: '网站', sort: true },
                                                    { field: 'name', title: '匹配值', sort: true },
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

            $("#bigData").on("click", function () {
                var obj = $("#ExcelUpload");
                obj.css("display","block");
                layer.open({
                    type: 1,
                    area: [$(document).width() + 'px', '500px'],
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
                    end : function (index, layero) {
                        obj.css("display","none");
                    }
                });
            });

            $("#export").on("click", function () {
                var obj = $("#exportContent");
                obj.removeClass("hide");
                layer.open({
                    type: 1,
                    area: [$(document).width() + 'px', '400px'],
                    offset: 'b',
                    title: '发现',
                    resize: true,
                    anim: 1,
                    maxmin: true,
                    content: obj,
                    cancel: function (index, layero) {
                        $(".contextMenuDialog").addClass("hide");
                    }
                });

                $.tableObject({
                    tableId: 'tableexport4',
                    tableOption: {
                        url: pageurl,
                        page: true,
                        where: {},
                        height: 269,
                        cols: [
                            [
                                { field: 'aa', title: '创建时间', sort: true },
                                { field: 'bb', title: '小区', sort: true },
                                { field: 'cc', title: '经纪人', sort: true },
                                { field: 'dd', title: '经纪人电话', sort: true },
                                { field: 'ee', title: '备注', sort: true },
                            ]
                        ]
                    }
                });
            });

            $("#create").on("click", function () {
	       		layer.open({
	                type: 2,
	                area: [$(document).width() + 'px', '100%'],
	                offset: 'b',
	                title: "创建",
	                resize: true,
	                anim: 1,
	                maxmin: true,
	                content: "/WebRelease/admin/estate/add",
	                cancel: function (index, layero) {
	                    $(".contextMenuDialog").addClass("hide");
	                }
	            });
            });

            $("#showImg .img .show").on("click", function () {
                var src = $(this).parent().find("img").attr("src");
                $("#showImgDiv").attr("src", src).removeClass("hide");
            });

            $("#showImgDiv").on("click", function () {
                $("#showImgDiv").addClass("hide");
            });
          //绑定页面的上传图片的JS
  		  var Myuploader = WebUploader.create({
                auto: true,   
                swf: '/WebRelease/admin/static/plugins/webuploader/Uploader.swf',
                server: "/WebRelease/admin/estate/importExcel", 
                pick: '#picker'
            });
			var interval = null;
			var current = 0;
			//上传前触发
			Myuploader.on('uploadStart', function (file){ 
				interval = setInterval(increment,1000);
			})
			function increment(){
				$.ajax({  
			        type : "POST",  //提交方式  
			        url : "/WebRelease/admin/common/redisupload?redisname=estate",//路径  
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
  	                		clearInterval(interval);
						$('.proggress').animate({width:'100%'}); 
						$('.percentCount').html('100%');
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
            $('#progressbar1').LineProgressbar({
				percentage: 0,
				fillBackgroundColor: '#1abc9c'
			});
			
			
        });
    </script>
    <div id="ExcelUpload">
	    <div id="uploader" class="wu-example">
		    <!--用来存放文件信息-->
			<div id="thelist" class="uploader-list"></div>
			    <div class="btns">
			        <div id="picker">选择文件</div>
			    </div>
			</div>
			<div class="float-left mr-1">
                <a class="form-control btn btn-primary" href="https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/ekeResource/download/%E5%B0%8F%E5%8C%BA%E6%A8%A1%E6%9D%BF.xlsx" download="小区模板">Excel规范模板下载</a>
            </div>
            <br>
            <br>
  			<div id="progressbar1"style="width:50%;"></div>
		<div id="uploader_btn"></div>
		<div id="datamsg"></div>
    </div>
    <div style="position: absolute; top: 0px; text-align: center; z-index: 9999999999; text-align: center; width: 100%;">
        <img id="showImgDiv" class="hide" src="" title="点击我关闭" style="margin: auto; cursor: pointer;">
    </div>
    
    <div class="contextMenuDialog hide" id="dataContent">
        <div class="card-body form-control">
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
                <div class="col-lg-6 text-right pr-2">
                    <input class="btn btn-primary" type="button" value="Excel规范模板下载">
                    <span style="width: 22px;height: 22px; display: inline-block; border-radius: 22px; background: #0c0;">&nbsp;</span>
                    <span>EXCEL</span>
                    <span style="width: 22px;height: 22px; display: inline-block; border-radius: 22px; background: #000;">&nbsp;</span>
                    <span>本地</span>
                </div>
                <div class="row w-100">
                    <div class="col-lg-12">
                        <div class="layui-tab m-2 p-2" lay-filter="filter" style="border: 1px solid #ccc; border-radius: 5px;">
                            <ul class="layui-tab-title">
                                <li class="layui-this">发现小区</li>
                                <li>小区信息填空</li>
                                <li>小区图</li>
                            </ul>
                            <div class="layui-tab-content">
                                <div class="layui-tab-item layui-show">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <table id="tableexport1"></table>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">小区</div>
                                                        <div class="col-lg-8">星河盛世花园</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">户数</div>
                                                        <div class="col-lg-8">910</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">物业类型</div>
                                                        <div class="col-lg-8">住宅</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">建筑年代</div>
                                                        <div class="col-lg-8">2004年</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">物业费</div>
                                                        <div class="col-lg-8">0.88元/平米</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">总户数</div>
                                                        <div class="col-lg-8">2000</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">建筑类型</div>
                                                        <div class="col-lg-8">多层，板楼</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">停车位</div>
                                                        <div class="col-lg-8">523</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">物业电话</div>
                                                        <div class="col-lg-8">0755-28776262</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">开发商</div>
                                                        <div class="col-lg-8">星河盛世花园</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="row">
                                                        <div class="col-lg-2 nametl">物业公司</div>
                                                        <div class="col-lg-10">星河盛世花园</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="row">
                                                        <div class="col-lg-2 nametl">小区描述</div>
                                                        <div class="col-lg-10">皓月花园深圳市宝安区龙华民治路与民康路交汇处，离梅林关仅3分钟车程，交通便利。是融合现代景观规划要素及欧式园林精华的5万平方米绿色水景园林，精心营造高尚绿色园林社区
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="row">
                                                        <div class="col-lg-2 nametl"></div>
                                                        <div class="col-lg-10">
                                                            <button class="form-control btn btn-primary w-25">添加小区</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-tab-item">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <table id="tableexport2"></table>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">小区</div>
                                                        <div class="col-lg-8">
                                                            星河盛世花园
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">户数</div>
                                                        <div class="col-lg-8">
                                                            910</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">物业费</div>
                                                        <div class="col-lg-8">
                                                            住宅</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">建筑年代</div>
                                                        <div class="col-lg-8">
                                                            2004年</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">物业费</div>
                                                        <div class="col-lg-8">
                                                            0.88元/平米</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">总户数</div>
                                                        <div class="col-lg-8">
                                                            2000</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">建筑类型</div>
                                                        <div class="col-lg-8">
                                                            多层，板楼</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">停车位</div>
                                                        <div class="col-lg-8">
                                                            523</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">物业电话</div>
                                                        <div class="col-lg-8">
                                                            0755-28776262</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">开发商</div>
                                                        <div class="col-lg-8">
                                                            星河盛世花园</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="row">
                                                        <div class="col-lg-2 nametl">物业公司</div>
                                                        <div class="col-lg-10">
                                                            星河盛世花园</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="row">
                                                        <div class="col-lg-2 nametl">小区描述</div>
                                                        <div class="col-lg-10">
                                                            皓月花园深圳市宝安区龙华民治路与民康路交汇处，离梅林关仅3分钟车程，交通便利。是融合现代景观规划要素及欧式园林精华的5万平方米绿色水景园林，精心营造高尚绿色园林社区
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="row">
                                                        <div class="col-lg-2 nametl"></div>
                                                        <div class="col-lg-10">
                                                            <button class="form-control btn btn-primary w-25">全部空白填补</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-tab-item">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <table id="tableexport3"></table>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">小区</div>
                                                        <div class="col-lg-8">
                                                            星河盛世花园
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">户数</div>
                                                        <div class="col-lg-8">
                                                            910</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">物业费</div>
                                                        <div class="col-lg-8">
                                                            住宅</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">建筑年代</div>
                                                        <div class="col-lg-8">
                                                            2004年</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">物业费</div>
                                                        <div class="col-lg-8">
                                                            0.88元/平米</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">总户数</div>
                                                        <div class="col-lg-8">
                                                            2000</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">建筑类型</div>
                                                        <div class="col-lg-8">
                                                            多层，板楼</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">停车位</div>
                                                        <div class="col-lg-8">
                                                            523</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">物业电话</div>
                                                        <div class="col-lg-8">
                                                            0755-28776262</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="row">
                                                        <div class="col-lg-4 nametl">开发商</div>
                                                        <div class="col-lg-8">
                                                            星河盛世花园</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="row">
                                                        <div class="col-lg-2 nametl">物业公司</div>
                                                        <div class="col-lg-10">
                                                            星河盛世花园</div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="row">
                                                        <div class="col-lg-2 nametl">小区描述</div>
                                                        <div class="col-lg-10">
                                                            皓月花园深圳市宝安区龙华民治路与民康路交汇处，离梅林关仅3分钟车程，交通便利。是融合现代景观规划要素及欧式园林精华的5万平方米绿色水景园林，精心营造高尚绿色园林社区
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="row">
                                                        <div class="col-lg-2 nametl"></div>
                                                        <div class="col-lg-10">
                                                            <button class="form-control btn btn-primary w-25">加入库存</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row w-100 p-2">
                    <textarea name="" class="form-control m-2" id="" cols="30" rows="10">2018-09-08 09:08:07 张三丰 深圳 温馨家园 户数：1000 变更为 2000</textarea>
                </div>
            </div>
        </div>
    </div>

    <div class="contextMenuDialog hide" id="exportContent">
        <div class="card-body form-control">
            <div class="row">
                <div class="col-lg-6">
                    <img src="/WebRelease/admin/static/image/u3.jpg" style="width: 100%; height: 308px;" alt="">
                </div>
                <div class="col-lg-6">
                    <div class="row">
                        <input type="text" class="form-control w-25" placeholder="备注信息">
                        <button class="form-control btn btn-primary ml-1" style="width: 80px;">保存</button>
                        <input type="checkbox" class="mt-3 ml-2">
                        <span style="margin-top: 10px;">已备注</span>
                        <span class="ml-5" style="margin-top: 10px;">100</span>
                    </div>
                    <div class="row mt-2">
                        <table id="tableexport4"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="contextMenuDialog hide" id="item4">
        <div class="card-body">
            <div class="row">
                <table id="tablejc"></table>
            </div>
        </div>
    </div>
    <div class="contextMenuDialog hide" id="item5">
        <div class="card-body">
            <div class="row">
                <table id="tablelog"></table>
            </div>
        </div>
    </div>
    <div class="contextMenuDialog hide" id="item7">
        <div class="card-body">
            <div class="row">
                <div class="input-group col-lg-12">
                    <div class="row w-100 mb-2">
                        <div class="col-lg-3 text-right pt-2">
                            <span class="ncon" id="estateName"></span>
                        </div>
                        <div class="col-lg-8 listrow">
                            <span class="ncon mr-2" style="min-width: 170px;">
                                <select class="form-control" id="webId">
                                	<c:forEach items="${listWeb}" var="web">
	                                    <option value="${web.id }">${web.webname }</option>
                                	</c:forEach>
                                </select>
                            </span>
                            <input class="form-control float-left mr-2 w-50" type="text" placeholder="小区名" id="estateMatchName">
                            <button class="btn btn-primary float-left mr-2" onclick="addEstateMatch()">保存</button>
                            <input type="hidden" id="estateMatchId">
                            <input type="hidden" id="estateId">
                            <button class="btn btn-danger float-left" onclick="delEstateMatch()">删除</button>
                        </div>
                        <!--<div class="col-lg-3 listrow">
                            <input class="form-control float-left mr-2 w-50" type="text" placeholder="小区名">
                            <button class="btn btn-primary float-left">保存</button>
                        </div>-->
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <table id="third"></table>
                </div>
            </div>
        </div>
    </div>
</body>

</html>