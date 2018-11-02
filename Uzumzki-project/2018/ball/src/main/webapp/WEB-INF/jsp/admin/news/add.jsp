<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <link href="/WebBackAPI/admin/static/css/site.css" rel="stylesheet">
	<link rel="stylesheet" href="/WebBackAPI/admin/static/plugins/bootstrap-validator/dist/css/bootstrapValidator.css" />
	<link rel="stylesheet" href="/WebBackAPI/admin/static/plugins/bootstrap-validator/vendor/bootstrap/css/dataValidator.css" />
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
    <script src="/WebBackAPI/admin/static/plugins/layer/layer.js"></script>

	<div class="" id="item8">
        <div class="item8-wrap">
        <div class="row">
            <div class="col-lg-4">
            	<div class="col-lg-12">
	                <div class="row has-feedback">
	                    <div class="col-lg-4 nametl">备注</div>
	                    <div class="col-lg-8">
	                        <input class="form-control" type="text" class="w-100" placeholder="备注" value="${remark}" id="remark">
	                    </div>
	                </div>
	            </div>
                <div class="col-lg-12">
	                <div class="row has-feedback">
	                    <div class="col-lg-4 nametl">标题</div>
	                    <div class="col-lg-8">
	                        <input name="remark" id="title" class="form-control" type="text" class="w-100" placeholder="标题" value="${title}">
	                    </div>
	                </div>
	            </div>
	            <div class="col-lg-12">
	                <div class="row has-feedback">
	                    <div class="col-lg-4 nametl">摘要</div>
	                    <div class="col-lg-8">
	                        <input name="contentpath" id="contentSimple" class="form-control" type="text" class="w-100" placeholder="摘要" value="${contentSimple}">
	                    </div>
	                </div>
	            </div>
	            <div class="col-lg-12">
	                <div class="row has-feedback">
	                    <div class="col-lg-4 nametl" style="line-height: 150px;"> 封面</div>
	                    <div class="col-lg-8">
				            <div class="publicbg-row image1" style="float: left;">
				                <div class="publicbg-box">
                				<img src="${headImage}" id="headImage" class="publicbg-img"
			                      onerror="javascript:this.src='/WebBackAPI/admin/static/image/error.png'"></div>
				            </div>
				            <div class="publicbg-title">
								<button class="layui-btn test" lay-data="{url: '/WebBackAPI/admin/common/uploadImage'}" name="file">上传图片</button>
							</div>
	                    </div>
	                </div>
	            </div>
                <div class="col-lg-12 mt-2">
                    <div class="row form-group has-feedback">
                        <div class="col-lg-2 nametl"></div>
                        <div class="col-lg-10">
                            <button class="form-control btn btn-primary w-25" id="saveNews">保存</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
    			<script id="container" name="content" type="text/plain">
    			</script>
            </div>
        </div>
        </div>
    </div>
    <style>
        #item8 .item8-wrap{ padding-left: 50px;}
        #item8 .item8-close{ color: #ffffff; position: absolute; top:35px; right:35px;}
        #item8 .item8-close a{ color: #ffffff;}
        #item8 .item8-close a:after{ color: #ffffff;}
        .publicbg-title{ color:#000000; font-size: 20px; line-height: 24px;margin: 55px 0 0 0;}
        .publicbg-title input[type=text]{ padding: 0 10px; margin-left: 10px; width: 200px;}
        .publicbg-row:after{ content: " "; height: 0; overflow: hidden; clear: both; float: none; display: block;}
        .publicbg-box{ width:150px; height: 150px; position: relative; float: left;}
        .publicbg-img{     border: 1px solid #ccc;background-size: cover; background-repeat: no-repeat; background-position: center; position: absolute; width: 100%; height: 100%; top:0; left:0;}
    	#edui1_iframeholder{    height: 300px !important;}
    </style>
	<!-- 配置文件 -->
    <script type="text/javascript" src="/WebBackAPI/admin/static/js/UEditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="/WebBackAPI/admin/static/js/UEditor/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('container');
        ue.ready(function() {
            //设置编辑器的内容
            ue.setHeight(300);
        });
		layui.use('upload', function() {
			var upload = layui.upload;
			upload.render({
				elem: '.test',
				before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
					//加载层-风格4
					layer.msg('上传中', {
					  icon: 16,shade: 0.01
					});
				},
				done: function(res, index, upload) {
					layer.msg("上传成功");
					$("#headImage").attr("src", res.data);
					layer.close(index);
				},
				error: function(index, upload){
				    layer.closeAll('loading'); //关闭loading
				}
			})
		});
		
		$(function(){
	        $("#saveNews").click(function () {
	        	var remark = $("#remark").val();
	        	var title = $("#title").val();
	        	var headImage = $("#headImage").attr("src");
	        	var contentSimple = $("#contentSimple").val();
	        	var content = UE.getEditor('container').getContent();
	            $.ajax({  
	                type : "POST",  //提交方式  
	                url : "/WebBackAPI/admin/news/saveNews",//路径  
	                data : {  
	                    remark : remark,
	                    title : title,
	                    headImage : headImage,
	                    contentSimple : contentSimple,
	                    content : content
	                },//数据，这里使用的是Json格式进行传输  
	                dataType:"json",
	                success : function(result) {//返回数据根据结果进行相应的处理  
	                    if ( result.code == 200 ) {  
	                		layer.msg("添加成功");
	                    } else {
	                		layer.confirm(result.msg, {
	                			btn: ['确定'] //按钮
	                		});
	                    }  
	                }
	            });
	        });
	    })
	</script>
