<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="" id="item8">
        <div class="item8-wrap">
            <span class="item8-close"><a href="javascript:;">关闭</a></span>
            <div class="publicbg-title">1.图片
				<button class="layui-btn test" lay-data="{url: '/WebBackAPI/admin/common/uploadImage'}" name="file">上传图片</button>
			</div>
            <div class="publicbg-row image1">
            	<c:if test="${!empty image}">
	                <div class="publicbg-box"><img class="publicbg-img" src="${image}"></div>
            	</c:if>
            </div>
        </div>
    </div>
    <style>
        #item8 .item8-wrap{ padding-left: 50px;}
        #item8 .item8-close{ color: #ffffff; position: absolute; top:35px; right:35px;}
        #item8 .item8-close a{ color: #ffffff;}
        #item8 .item8-close a:after{ color: #ffffff;}
        .publicbg-title{ color:#000000; font-size: 20px; line-height: 24px; margin: 10px 0; }
        .publicbg-title input[type=text]{ padding: 0 10px; margin-left: 10px; width: 200px;}
        .publicbg-row:after{ content: " "; height: 0; overflow: hidden; clear: both; float: none; display: block;}
       .publicbg-box{ width:150px; height: 150px; position: relative; float: left; margin: 0 20px 20px 0;}
       .publicbg-img{ background-size: cover; background-repeat: no-repeat; background-position: center; position: absolute; width: 100%; height: 100%; top:0; left:0;}
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
				done: function(res, index, upload) {
					layer.msg("上传成功");
					$('.image1').html("<div class=\"publicbg-box\"><img class=\"publicbg-img\" src=\""+res.data+"\"></div>");
					$.ajax({  
                        type : "POST",  //提交方式  
                        url : "/WebBackAPI/admin/newsBanner/saveImage",//路径  
                        data : {id : id, url : res.data},//数据，这里使用的是Json格式进行传输  
                        dataType:"json",
                        success : function(result) {//返回数据根据结果进行相应的处理  
                        }  
                    }); 
				}
			})
		});
	</script>