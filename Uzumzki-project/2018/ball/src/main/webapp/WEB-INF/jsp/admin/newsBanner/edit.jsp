<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<div class="" id="item8">
        <div class="item8-wrap">
            
            <div class="col-lg-6">
            	<div class="col-lg-6">
	                <div class="row has-feedback">
	                    <div class="col-lg-4 nametl">容器编号</div>
	                    <div class="col-lg-8">
	                        <input readonly="readonly" class="form-control" type="text" class="w-100" placeholder="容器编号" value="${bannerno}">
	                    </div>
	                </div>
	            </div>
            	<div class="col-lg-6">
	                <div class="row has-feedback">
	                    <div class="col-lg-4 nametl">容器位置</div>
	                    <div class="col-lg-8">
	                        <input readonly="readonly" class="form-control" type="text" class="w-100" placeholder="容器位置" value="${banner}">
	                    </div>
	                </div>
	            </div>
            	<div class="col-lg-6">
	                <div class="row has-feedback">
	                    <div class="col-lg-4 nametl">平台</div>
	                    <div class="col-lg-8">
	                        <input readonly="readonly" class="form-control" type="text" class="w-100" placeholder="平台" value="${showway}">
	                    </div>
	                </div>
	            </div>
                <div class="col-lg-6">
	                <div class="row has-feedback">
	                    <div class="col-lg-4 nametl">备注</div>
	                    <div class="col-lg-8">
	                        <input name="remark" id="remark" class="form-control" type="text" class="w-100" placeholder="备注" value="${remark}">
	                    </div>
	                </div>
	            </div>
	            <div class="col-lg-6">
	                <div class="row has-feedback">
	                    <div class="col-lg-4 nametl">跳转地址</div>
	                    <div class="col-lg-8">
	                        <input name="contentpath" id="contentpath" class="form-control" type="text" class="w-100" placeholder="跳转地址" value="${contentpath}">
	                    </div>
	                </div>
	            </div>
	            <div class="col-lg-6">
	                <div class="row has-feedback">
	                    <div class="col-lg-4 nametl" style="line-height: 150px;"> 横幅</div>
	                    <div class="col-lg-8">
				            <div class="publicbg-row image1" style="float: left;">
				            	<c:if test="${!empty image}">
					                <div class="publicbg-box"><img class="publicbg-img" src="${image}"></div>
				            	</c:if>
				            </div>
				            <div class="publicbg-title">
	                        	<input id="coverpath" type="hidden" value="${image}">
								<button class="layui-btn test" lay-data="{url: '/admin/common/uploadImage'}" name="file">上传图片</button>
							</div>
	                    </div>
	                </div>
	            </div>
                <div class="col-lg-12 mt-2">
                    <div class="row form-group has-feedback">
                        <div class="col-lg-2 nametl"></div>
                        <div class="col-lg-10">
                            <button class="form-control btn btn-primary w-25" id="saveNewsBanner">保存</button>
                        </div>
                    </div>
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
    </style>
	<script>
	    $(function(){
	        $('.item8-close').on('click',function(){
	            $('#item8').hide(300);
	        })
	    })
	    var id = "${id}";
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
				    layer.closeAll('loading'); //关闭loading
					layer.msg("上传成功");
					$('.image1').html("<div class=\"publicbg-box\"><img class=\"publicbg-img\" src=\""+res.data+"\"></div>");
					$('#coverpath').val(res.data);
				},
				error: function(index, upload){
				    layer.closeAll('loading'); //关闭loading
				}
			})
		});
		
		$(function(){
	        $("#saveNewsBanner").click(function () {
	        	var remark = $("#remark").val();
	        	var contentpath = $("#contentpath").val();
	        	var coverpath = $('#coverpath').val();
	            $.ajax({  
	                type : "POST",  //提交方式  
	                url : "/admin/newsBanner/saveNewsBanner",//路径  
	                data : {  
	                	id : id,
	                    remark : remark,
	                    contentpath : contentpath,
	                    coverpath : coverpath
	                },//数据，这里使用的是Json格式进行传输  
	                dataType:"json",
	                success : function(result) {//返回数据根据结果进行相应的处理  
	                    if ( result.code == 200 ) {  
	                		layer.msg("保存成功");/* 
                        	parent.window.location.href="/admin/newsBanner/listview"; */
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