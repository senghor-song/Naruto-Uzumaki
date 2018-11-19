<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>小易运维</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
	<link rel="icon" href="/WebBackAPI/admin/static/image/logo.png" type="image/x-icon"/>
    <link href="/WebBackAPI/admin/css/jquery.lineProgressbar.css" rel="stylesheet">
    <link href="/WebBackAPI/admin/static/css/site.css" rel="stylesheet">
    <link href="/WebBackAPI/admin/static/plugins/webuploader/webuploader.css" rel="stylesheet">
    <style type="text/css">
    </style>
    
</head>

<body>
	<script src="/WebBackAPI/admin/static/plugins/jquery/jquery.min.js"></script>
	<script src="/WebBackAPI/admin/static/js/jq-ext.js"></script>
	<script src="/WebBackAPI/admin/static/plugins/layui/layui.all.js"></script>
    <script src="/WebBackAPI/admin/static/plugins/webuploader/webuploader.js"></script>
    <script src="/WebBackAPI/admin/static/js/jquery.lineProgressbar.js"></script>
<script>
    $(function () {
		//绑定页面的上传的JS
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
  							if(result.data.current == 100) { 
  								$.ajax({  
  	  	  		  			        type : "POST",  //提交方式  
  	  	  		  			        url : "/WebBackAPI/admin/common/deleteRedisupload?redisname=venue",//路径  
  	  	  		  			        dataType:"json",
  	  	  		  			        success : function(result) {}
  	  	  		  			    });
  								clearInterval(interval); 
  							}
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
    	                		msg += "导入成功日志:</br>" + data.data.succeedlist;
    	                		msg += "导入失败日志:</br>" + data.data.errorlist;
    	                		
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

<div id="ExcelUpload">
	<div id="uploader" class="wu-example">
    <!--用来存放文件信息-->
	<div id="thelist" class="uploader-list"></div>
	    <div class="btns">
	        <div id="picker">选择文件</div>
	    </div>
	</div>
	<div class="float-left mr-1">
           <a class="form-control btn btn-primary" 
           href="https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/ekeResource%2Fdownload%2F%E5%9C%BA%E9%A6%86%E6%A8%A1%E6%9D%BF.xlsx" download="场馆模板.xlsx">Excel规范模板下载</a>
       </div>
       <br>
       <br>
	<div id="dataPage">0/0</div>
   	<div id="progressbar1"style="width:50%;"></div>
	<div id="uploader_btn"></div>
	<div id="datamsg"></div>
</div>

</body>

</html>