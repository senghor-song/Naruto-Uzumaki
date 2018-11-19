(function (webuploader, $) {
    var MyWebUploader = function () {
        this.Option = false;
    }

    MyWebUploader.prototype = {
        /**
        * @content 初始化Webuploader插件
        */
        Init: function (sendOption) {

            //合并默认对象与自定义对象，作为全局变量
            this.Option = this.ExtendOptions(sendOption);

            //添加WebUploader的监听事件
            this.CreateAttachevent();

        },
        /**
        * @content 深度合并默认对象与自定义对象
        * @return object 全局变量Option
        */
        ExtendOptions: function (sendOption) {
            var defaultOptions = { 
                btnId: "default_btn", //按钮id
                thumbContId: "",//关联缩略图的id
                thumbInputId:"",//关联input的id
                width: 140, //按钮大小
                height: 25, //按钮高度
                maxsize:30 * 1024 * 1024,//最大上传文件30M
                minsize: 1 * 1024,//最小上传文件1K
                uploadtype: 1,//默认为图片
                errMsg: "上传失败",//错误提示信息
                tipMsg: "上传失败",//其他错误信息
                fileFID: "1",
                serverApi: "/userCenter/upload.jsp",
                CheckVerifyCallBack:"",
                accept: {
                    extensions: 'jpg,jpeg,bmp,png,JPG,JPEG,BMP,PNG'  //允许上传的类型
                },
                bucketName: "",
                callback:"",
                moduleType:"usercenter",
                moduleLabel:"defaultlocal",//不同文件夹
                OSSAuthority:""
            } 
            return $.extend(true, true, {}, defaultOptions, sendOption); 
        },
        /**
        * @content 初始化Webuploader插件
        */
        CreateAttachevent: function () {
            var _Options = this.Option || "";
            if (_Options != "") {
                var _theTime = new Date().getTime(); 
                var _thisUploader = _Options.btnId + "_Rd" + Math.floor(Math.random() * 1000) + "_T" + _theTime;
                this.Option[_thisUploader] = WebUploader.create({
                    auto: true,
                    width: _Options.width,
                    fileSingleSizeLimit:_Options.maxsize,
                    height: _Options.height,
                    swf: '/resources/admin/js/webuploader/Uploader.swf',
                    server:"/userCenter/upload.jsp",
                    pick: {
                        id: '#' + _Options.btnId,
                        multiple:false
                    }, 
                    duplicate: true
                });
                /**
                 * 验证文件格式以及文件大小
                 */ 
            	this.Option[_thisUploader].on('error', function (type) {
                    console.log(type)
                    if (type == "Q_TYPE_DENIED") {
                        layer.msg("请上传jpg,jpeg,bmp,png格式的文件！", { icon: 2 });
                        return;
                    } else if (type == "F_EXCEED_SIZE") {
                        layer.msg("文件大小不能超过30M", { icon: 2 });
                        return;
                    }
                }); 
            	this.Option[_thisUploader].on('onError', function (code) {
                    console.log(code)
                    if (code == "Q_TYPE_DENIED") {
                        layer.msg("请上传jpg,jpeg,bmp,png格式的文件！", { icon: 2 });
                        return;
                    } else if (code == "F_EXCEED_SIZE") {
                        layer.msg("文件大小不能超过30M", { icon: 2 });
                        return;
                    }
                }); 
                //图片加入队列时的触发事件 
                this.Option[_thisUploader].on('filesQueued', function (file) {
                    console.log(file);
                    if( file != "" && file != null && file != undefined ){
                    	var _format =file[0].ext; 
                    	if( _format == "jpg" || _format == "jpeg" || _format == "bmp" || _format == "png" || _format == "JPG" || _format == "JPEG" || _format == "BMP" || _format == "PNG"  ){
                    		
                    	}else{
                    		layer.msg("请上传jpg,jpeg,bmp,png格式的文件！", { icon: 2 }); 
                    		return;
                    	}; 
                    };  
                }); 
                //图片提交的成功的监听事件
                this.Option[_thisUploader].on('uploadSuccess', function (file, data) {
                    if (_Options.callback != "" && (typeof _Options.callback == "Function" || typeof _Options.callback == "function")) {
                        _Options.callback(data, _Options, file);
                    } else { 
                    	/*layer.msg('上传中', {icon: 16,shade: 0.1});*/
                    	if( file != "" && file != null && file != undefined ){
                        	var _format =file.ext; 
                        	if( _format == "jpg" || _format == "jpeg" || _format == "bmp" || _format == "png" || _format == "JPG" || _format == "JPEG" || _format == "BMP" || _format == "PNG"  ){
                        		if (_Options.btnId != "" && $("#" + _Options.btnId).size() > 0 && data.code == "200"){ 
                                    var _theThumbUrl = data.data || "";
                                    var btnIdSpit = _Options.btnId.split("_")[0];  //上传id截取判断
                                    var btnIdSpit1 = _Options.btnId.split("_")[1];  //上传id截取判断
                                    if(_theThumbUrl!=""){ 
                                    	if( btnIdSpit == "uploader" ){
                                    		//向文件种追加数据
                                    		var str = "";
                                    			str +="<span class='files_item'><img src='"+_theThumbUrl+"' class='files_img' /><i class='iconfont icon_close'>&#xe619;</i></span>";
                                    			var _btnObj = $("#" + _Options.btnId).parent();
                                    			var imgAppend = _btnObj.find(".files_pend");
                                    			imgAppend.append($(str));
                                    			var imgStorage = $("#" + btnIdSpit1).val();
                                    			if( imgStorage != "" && imgStorage != undefined ){
                                    				imgStorage += ","+_theThumbUrl;
                                    				//var strVar ='<input type="hidden" edit_type="thumb" name="idcard_'+btnIdSpit1+'" value="" id='+btnIdSpit1+' value='+imgStorage+'>';
                                    				$("#" + btnIdSpit1).val(imgStorage).change();
                                    			}else{
                                    				imgStorage = _theThumbUrl;
                                    				var strVar ='<input type="hidden" edit_type="thumb" name="idcard_'+btnIdSpit1+'" value='+imgStorage+' id='+btnIdSpit1+' />';
                                    				_btnObj.append($(strVar));
                                    			}; 
                                    	}else{
                                    		//改变缩略图
                                    		$("#" + _Options.thumbContId).attr("src", _theThumbUrl);
                                            if (_Options.thumbInputId != "" && $("#" + _Options.thumbInputId).size() > 0) {
                                                $("#" + _Options.thumbInputId).val(_theThumbUrl).change();
                                            } 
                                            $("#" + _Options.thumbContId).load();
                                            $('#' + _Options.btnId).find(".Uploadloading").remove();	
                                    	} 
                                    }; 
                                	layer.msg(data.msg,{icon:1,time:1000})
                                }else{
                                	layer.msg(data.msg,{icon:2})
                                };
                        	};   
                        	/*else{
                        		layer.msg(data.message, { icon: 2 }); 
                        		return;
                        	}*/
                    	};
                    };	
                });
 
                //文件上传失败，显示上传出错。
                this.Option[_thisUploader].on('uploadError', function (file) {
                    console.log(file, "error", arguments);
                    if (typeof com != "undefined") {
                        layer.msg("文件上传失败");
                    }
                }); 
            }
        },
        /**
        * @content 销毁WebUploader
        */
        DestoryWebUploader: function () {
            try {
                this.Option[_thisUploader].destory();
            } catch (e) {
                console.log(e);
            }
        }
    }
    
    $.extend({MyWebUploader:MyWebUploader});
})(WebUploader, jQuery); 