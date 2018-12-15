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
    <style type="text/css">
	    .contextMenuDialog .listrow {
	    	padding-top: 10px;
		}
    </style>
    
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
                                        <div class="col-lg-9 btncaozuo">
                    						<c:if test="${btn225 == 1}">
                                            	<button class="btn btn-primary btn-sm" id="venueSave">入驻申请</button>
                                            </c:if>
                    						<c:if test="${btn226 == 1}">
                                           		<button class="btn btn-primary btn-sm" id="venueImport">导入场馆</button>
                                            </c:if>
                    						<c:if test="${btn2210 == 1}">
                                           		<button class="btn btn-primary btn-sm" id="venueInsert">新增场馆</button>
                                            </c:if>
                    						<c:if test="${btn227 == 1}">
                                            	<!-- <button class="btn btn-primary btn-sm" id="venueLog">场馆日志</button> -->
                                            	<button class="btn btn-primary btn-sm" id="venueCheck">待审核场地(机构添加)</button>
                                            </c:if>
                    						<c:if test="${btn228 == 1}">
                                            	<button class="btn btn-primary btn-sm" id="venueAnalyze">模板分析</button>
                                            </c:if>
                    						<c:if test="${btn229 == 1}">
                                            	<button class="btn btn-primary btn-sm" id="venueTrim">模板梳理</button>
                                            </c:if>
                                        </div>
                                        <div class="input-group input-group-sm float-right col-lg-3" style="width: 350px;">
											<select class="form-control float-right" id="venueSelectType">
												<option value="0">城市</option>
												<option value="1">场馆名</option>
												<option value="2">编号</option>
												<option value="3">入驻机构</option>
											</select> 
											<input class="form-control float-right" id="venueKeyword" name="table_search" type="text" 
												placeholder="请输入关键字" maxlength="20">
											<div class="input-group-append">
												<button class="btn btn-default" id="venueSearch" type="submit">
													<i class="fa fa-search"></i> 搜索
												</button>
											</div>
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
	var map=null;
	var mapmarker=null;
	var cityName = "";
	var districtName = "";
	var venueLng = 0.0;
	var venueLat = 0.0;
    var updateMarker = null;
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
                $("#venueSearch").unbind().on("click", function () {
                    $.reload(self.obj);
                });
            },
            tableList: function () {
                var self = this;
                self.obj = $.tableObject({
                    tableId: 'tableList',
                    tableOption: {
                        url: '/admin/venue/list',
                        page: true,
                        height: $(window).height() - 150,
                        where: {
                            selectType: function () {
                                return $("#venueSelectType").val()
                            },
                            keyword: function () {
                                return $("#venueKeyword").val()
                            }
                        },
                        cols: [
                            [
                    			{field: 'id', title: 'id', hide:true},
                 				{field: 'lng', title: 'lng', hide:true},
                 				{field: 'lat', title: 'lat', hide:true},
                                {field: 'modifytime', title: '修改时间', sort: true, width:180},
                                {field: 'city', title: '城市', sort: true, width:100},
                                {field: 'district', title: '区县', sort: true, width:100},
                                {field: 'name', title: '场馆', sort: true, width:200},
                                {field: 'venueno', title: '编号', sort: true, width:100},
                                {field: 'type', title: '类型', sort: true, width:80},
                                {field: 'trainName', title: '入驻机构', sort: true, width:200},
                                {field: 'reserveShow', title: '订场入口', sort: true, width:100},
                                {field: 'reservePaySms', title: '订场支付短信', sort: true, width:140},
                                {field: 'lnglat', title: '坐标', sort: true, width:80},
                                {field: 'contactPhone', title: '订场电话', sort: true, width:120},
                                {field: 'informPhone', title: '订场短信通知', sort: true, width:140},
                                {field: 'venueError', title: '报错', sort: true, width:80},
                                {field: 'venuelogSum', title: '日志', sort: true, width:80},
                                {field: 'showflag', title: '状态', sort: true, width:80},
                            ]
                        ]
                    },
                    menuItem: {
						<c:if test="${btn221 == 1}">
                        item1: {
                            name: "编辑", callback: function (key, opt) {
                            	$.showAjaxContent("编辑", "40%", "/admin/venue/update/view", $(this).find("td").eq(0).attr('title'));
                            	var mapFlag = $(this).find("td").eq(12).attr('title');
                            	var lng = $(this).find("td").eq(1).attr('title');
                            	var lat = $(this).find("td").eq(2).attr('title');
                            	$("#citymapDiv").removeClass("hide");
                            	layer.open({
                             	  title: "位置", 
                				  type: 1,
                				  anim: 1,
                				  shade: 0.3,
                				  closeBtn: 0,
                				  offset: '20px',
                				  title: false,
                				  move: false,
                	              shadeClose : true,
                				  resize: false,
                				  area: ['30%', '50%'],
                				  content: $('#citymap'),
                                  cancel: function (index, layero) {
                                	  layer.closeAll();
                                      $(".contextMenuDialog").addClass("hide");
                          			  $.reload(self.obj);
                                  },
                                  end: function () {
                                	  layer.closeAll();
                                      $(".contextMenuDialog").addClass("hide");
                          			  $.reload(self.obj);
                                  },
                				  success: function(){

                					    map = new BMap.Map("citymap");    // 创建Map实例
	                					map.centerAndZoom(new BMap.Point(114.056386, 22.592976), 15);  // 初始化地图,设置中心点坐标和地图级别
                						//添加地图类型控件
                						map.addControl(new BMap.MapTypeControl({
                							mapTypes:[
                						        BMAP_NORMAL_MAP,
                						        BMAP_HYBRID_MAP
                						    ]}));	  
                						map.setCurrentCity("深圳");          // 设置地图显示的城市 此项是必须设置的
                						map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
                						map.disableDoubleClickZoom();

                						// 获取地址对象
                					    var geoc = new BMap.Geocoder();    
                						
                						// 删除上次使用的标注
                						if(mapmarker != null){
                							map.removeOverlay(mapmarker);
                						}
                						if(mapFlag == "是"){
	               	   			        	var point = new BMap.Point(lng, lat);
	               	   			      		map.centerAndZoom(point, 16);
	               	   			      		var marker = new BMap.Marker(point);// 创建标注
		               	   			      	var venueIcon = new BMap.Icon("https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/baseImage/map-venue1-100.png", new BMap.Size(36,36));
		               	   			      	venueIcon.setInfoWindowAnchor(new BMap.Size(12,0));
		               	   			        venueIcon.setImageSize(new BMap.Size(36,36));
	               	    			      	var marker = new BMap.Marker(point,{icon:venueIcon});// 创建标注
	               	   			      		mapmarker = marker;
	               	   			      		map.addOverlay(marker);// 将标注添加到地图中
                					  		venueLng = lng;//存贮点击的经度
                					  		venueLat = lat;//存贮点击的维度
                					  		
	               	   			      		geoc.getLocation(point, function(rs){
	          					           	 	//addressComponents对象可以获取到详细的地址信息
	          					            	var addComp = rs.addressComponents;
	          					            	var site = addComp.city + ", " + addComp.district;
	          					            	cityName = addComp.city;
	          					            	districtName = addComp.district;
	                  							$("#addressStrEdit").text(addComp.city + addComp.district + addComp.street + addComp.streetNumber);
          					        		});  
                					  		
	               	   			   			// 定义一个控件类,即function
											function ZoomControl(){
												// 默认停靠位置和偏移量
												this.defaultAnchor = BMAP_ANCHOR_BOTTOM_LEFT;
												this.defaultOffset = new BMap.Size(10, 10);
											}
	
			               	   			  	// 通过JavaScript的prototype属性继承于BMap.Control
			               	   			  	ZoomControl.prototype = new BMap.Control();
		
			               	   			  	// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
			               	   			  	// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
			               	   			  	ZoomControl.prototype.initialize = function(map){
												// 创建一个DOM元素
												var div = document.createElement("div");
												// 添加文字说明
												div.appendChild(document.createTextNode("回到当前位置"));
												// 设置样式
												div.style.cursor = "pointer";
												div.style.border = "1px solid gray";
												div.style.backgroundColor = "white";
			               	   			  	  
				               	   			  	div.onclick = function(even){
				               	   			  		map.panTo(point, false);
				               	   			  	};
			               	   			  	    // 添加DOM元素到地图中
			               	   			  	    map.getContainer().appendChild(div);
			               	   			  	    // 将DOM元素返回
			               	   			  	    return div;
			               	   			  	}
		               	   			  		// 创建控件
			               	   			  	var myZoomCtrl = new ZoomControl();
			               	   			  	// 添加到地图当中
			               	   			  	map.addControl(myZoomCtrl);
                					  		
                                     	}

                						//单击事件
                					  	map.addEventListener("dblclick",function(e){
                							//map.clearOverlays();//清除所有标注
                							if(updateMarker != null){
                								delMarker(updateMarker);
                							}
                					  		var point = new BMap.Point(e.point.lng, e.point.lat);
                					  		venueLng = e.point.lng;//存贮点击的经度
                					  		venueLat = e.point.lat;//存贮点击的维度
                					  		map.centerAndZoom(point, map.getZoom());
                                            var myIcon = new BMap.Icon("/admin/static/image/updateVenue.png", new BMap.Size(24,36));
                                            myIcon.setInfoWindowAnchor(new BMap.Size(12,0));
                                            var marker = new BMap.Marker(point,{icon:myIcon});// 创建标注
                                            updateMarker = marker;//存贮marker对象,方便后续点击删除上一次点击的标注
                					  		//marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                							mapmarker = marker;
                					  		map.addOverlay(marker);// 将标注添加到地图中
                					  		
                					  		geoc.getLocation(point, function(rs){
                					            //addressComponents对象可以获取到详细的地址信息
                					            var addComp = rs.addressComponents;
                					            var site = addComp.city + ", " + addComp.district;
                					            cityName = addComp.city;
                					            districtName = addComp.district;
                        						$("#addressEdit").val(addComp.city + addComp.district + addComp.street + addComp.streetNumber);
                        						$("#addressStrEdit").text(addComp.city + addComp.district + addComp.street + addComp.streetNumber);
                					        });  
                						});

                						// 编写自定义函数,删除标注
                						function delMarker(marker){
                							map.removeOverlay(marker);
                						}
                						
                				   }
                				});
                            }
                        },
                        </c:if>
						<c:if test="${btn222 == 1}">
                        item2: {
                            name: "日志", callback: function (key, opt) {
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tablevenuelog',
                                    tableOption: {
                                        url: '/admin/venue/venueloglist?venueid='+$(this).find("td").eq(0).attr('title'),
                                        page: false,
                                        height: $("#tablevenuelog").parents(".layui-layer-content").height() - 30,
                                        where: {},
                                        cols: [
                                            [
                                                { field: 'content', title: '内容', sort: true },
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                        </c:if>
						<c:if test="${btn223 == 1}">
                        item3: {
                            name: "纠错", callback: function (key, opt) {
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tableVenueError',
                                    tableOption: {
                                        url: '/admin/venue/venueErrorList?venueid='+$(this).find("td").eq(0).attr('title'),
                                        page: false,
                                        height: $("#tableVenueError").parents(".layui-layer-content").height() - 30,
                                        where: {},
                                        cols: [
                                            [
                                                { field: 'createtime', title: '时间', sort: true },
                                                { field: 'member', title: '操作人', sort: true },
                                                { field: 'content', title: '内容', sort: true },
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                        </c:if>
						<c:if test="${btn224 == 1}">
                        item4: {
                            name: "模板", callback: function (key, opt) {
                            	layer.open({
                                    type: 2,
                                    area: ['100%', '40%'],
                                    offset: 'b',
                                    title: '模板',
                                    resize: true,
                                    anim: 1,
                                    shadeClose : true,
                                    content: "/admin/venue/venueTemplate?venueid="+$(this).find("td").eq(0).attr('title'),
                                    cancel: function (index, layero) {
                                    },
                                    end: function () {
                    	        	}
                                });
                            }
                        },
                        </c:if>
                        item5: {
                            name: "解除入驻", callback: function (key, opt) {
                            	var venueid = $(this).find("td").eq(0).attr('title');
                            	var phone = "${phone}";
                            	if($(this).find("td").eq(9).attr('title') != ""){
                            		//加载层-风格4

                            		var indexCodeLoad = layer.msg('验证码发送中...', {
                            		  icon: 16 ,
                            		  shade: 0.01,
                                      time:false //取消自动关闭
                            		});
                            		$.ajax({
            	        				type : "POST", //提交方式  
            	        				url : "/admin/venue/getSMSCode",//路径  
            	        				data : {phone:phone,venueid:venueid},//数据，这里使用的是Json格式进行传输  
            	        				dataType : "json",
            	        				success : function(result) {//返回数据根据结果进行相应的处理  
   		   	                   				layer.msg(result.msg);
		  	                   				layer.close(indexCodeLoad);
			  	                   			var indexOpen = layer.open({
		                               		  	type: 1,
		                               		 	shadeClose:true,
		                               		 	resize:true,
		                               		 	title:"解除入驻",
		                               		  	skin: 'layui-layer-rim', //加上边框
		                               	  		area: ['300px', '200px'], //宽高
		                               		  	content: '<div style="padding: 10px;"> <input class="form-control" type="text" placeholder="请输入4位短信验证码" value="" id="phoneCode"> </div>',
		                               		  	btn: ['提交', '取消'], 
		                               		  	yes: function (layero, index) {
			                               		  	var indexVenueLoad = layer.msg('场馆初始化中...', {
			                                  		  icon: 16 , shade: 0.01, time:false //取消自动关闭
			                                  		});
		                               		  		var phoneCode = $('#phoneCode').val();
		                               		  		
		                    	           		  	$.ajax({
		                    	        				type : "POST", //提交方式  
		                    	        				url : "/admin/venue/relieveVenue",//路径  
		                    	        				data : {
		                    	        					venueid : venueid,
		                    	        					phoneCode : phoneCode
		                    	        				},//数据，这里使用的是Json格式进行传输  
		                    	        				dataType : "json",
		                    	        				success : function(result) {//返回数据根据结果进行相应的处理  
		                    	        					if (result.code == 200) {
			    	   		   	                   				layer.msg("解除成功!");
			    			  	                   				layer.close(indexOpen);
			    			  	                   				layer.close(indexVenueLoad);
			    			                        			$.reload(self.obj);
															} else {
			    	   		   	                   				layer.msg(result.msg);
			    			  	                   				layer.close(indexVenueLoad);
															}
		    	   	                   					},
		    	   	                   					error : function(e) {//返回数据根据结果进行相应的处理  
		    			  	                   				layer.close(indexload);
		    	   		   	                   				layer.msg("系统繁忙!");
		    			  	                   				layer.close(indexVenueLoad);
		    	   	                        			    $.reload(self.obj);
		    	   	                   					}
		                    	        			});
		                    	                }, btn2: function () {
		                    	                    var index = parent.layer.getFrameIndex(window.name);
		                    	                    parent.layer.close(index);
		                    	                }
		                               		});
   	                   					},error:function(){
   		   	                   				layer.msg("系统繁忙");
		  	                   				layer.close(indexCodeLoad);
   	                   					}
            	        			});
                            	}else{
	   	                   			layer.msg("该场馆暂无机构入驻！");
                            	}
                            }
                        }
                    }
                });
            }
        }
        tableObj.init();

        $("#venueSave").on("click", function () {
        	$("#citymapDiv").removeClass("hide");
        	
        	layer.open({
         	  title: "位置", 
			  type: 1,
			  anim: 1,
			  shade: 0.3,
			  closeBtn: 0,
			  offset: '20px',
			  title: false,
			  move: false,
              shadeClose : true,
			  resize: false,
			  area: ['30%', '40%'],
			  content: $('#citymap'),
              cancel: function (index, layero) {
            	  layer.closeAll();
                  $(".contextMenuDialog").addClass("hide");
      			  $.reload(self.obj);
              },
              end: function () {
            	  layer.closeAll();
                  $(".contextMenuDialog").addClass("hide");
      			  $.reload(self.obj);
              },
			  success: function(){
	        	map = new BMap.Map("citymap");    // 创建Map实例
				map.centerAndZoom(new BMap.Point(114.056386, 22.592976), 15);  // 初始化地图,设置中心点坐标和地图级别
				//添加地图类型控件
				map.addControl(new BMap.MapTypeControl({
					mapTypes:[
				        BMAP_NORMAL_MAP,
				        BMAP_HYBRID_MAP
				    ]}));	  
				map.setCurrentCity("深圳");          // 设置地图显示的城市 此项是必须设置的
				map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
				map.disableDoubleClickZoom();
	
				// 获取地址对象
			    var geoc = new BMap.Geocoder();    
				
				// 删除上次使用的标注
				if(mapmarker != null){
					map.clearOverlays();
				}
			  }
			});
			// 表格数据
            var obj = $("#button1");
            obj.removeClass("hide");
            layer.open({
                type: 1,
                area: ['100%', '50%'],
                offset: 'b',
                title: '入驻申请',
                resize: true,
  			    shade: 0,
                anim: 1,
                content: obj,
                cancel: function (index, layero) {
                    $(".contextMenuDialog").addClass("hide");
        			$.reload(tableObj.obj);
                },
                end: function () {
                    $(".contextMenuDialog").addClass("hide");
        			$.reload(tableObj.obj);
	        	}
            });
			venueEnterTableInit(0);
        });
        
        function venueEnterTableInit(checkFlag){
				$('#venueEnterId').val("");
        		var tableVenueEnter = {
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
                            tableId: 'tableAllVenueEnter',
                            tableOption: {
                                url: '/admin/venue/venueEnter/list?checkFlag='+checkFlag,
                                page: true,
                                height: 300,
                                where: {  },
                                cols: [
                                    [
    	                                {field: 'id', title: 'id', hide:true},
    	                                {field: 'createTime', title: '申请时间', sort: true},
    	                                {field: 'appnickname', title: '操作人', sort: true},
    	                                {field: 'phone', title: '操作人手机', sort: true},
    	                                {field: 'title', title: '场馆', sort: true},
    	                                {field: 'trainTeamFlag', title: '机构添加', sort: true},
    	                                {field: 'trainTeamName', title: '机构', sort: true},
    	                                {field: 'cityName', title: '城市', sort: true},
    	                                {field: 'districtName', title: '区县', sort: true},
    	                                {field: 'mainName', title: '负责人', sort: true},
    	                                {field: 'mainPhone', title: '负责人电话', sort: true},
    	                                {field: 'ballType', title: '类别', sort: true},
    	                                {field: 'checkFlag', title: '审核状态', sort: true},
    	                                {field: 'rname', title: '审核人', sort: true},
    	                                {field: 'checkTime', title: '审核时间', sort: true}
                                    ]
                                ]
                            }
                        });
                    }
                }
        		tableVenueEnter.init();
        		
        	
                layui.table.on('tool(tableAllVenueEnter)', function(obj) {
    	   			var data = obj.data;
    	   			var layEvent = obj.event;
    	   			var tr = obj.tr;
    	   			var content = tr.find("td").eq(13).find("div").text();
    	   		   	
    	   			// 判断意见是否填写
    	   			if (content != '') {
    	   				var check = 0;
           				if (layEvent == 'yes') {
           					check = 1;
           				} else if (layEvent == 'no') {
           					check = 2;
           				}
           				$.ajax({
           					type : "POST", //提交方式  
           					url : "/admin/venue/venueEnter/check",//路径  
           					data : {
           						id : data.id,
           						check : check,
    	   						content : content
           					},//数据，这里使用的是Json格式进行传输  
           					dataType : "json",
           					success : function(result) {//返回数据根据结果进行相应的处理  
           						if(result.code == 200){
                   					layer.msg("操作成功");
           						}else {
           							layer.confirm(result.msg, {
           								btn : [ '确定' ]
           							//按钮
           							});
           						}
           					}
           				});
           				$.reload(tableVenueEnter.obj);
    	   			} else {
    	   				layer.msg("请填写意见后操作!");
    	   			}
                });
   			  	
   			  	var myZoomCtrl = null;
                
                //监听行单击事件
   				layui.table.on('row(tableAllVenueEnter)', function(obj){
   					$('#venueEnterId').val(obj.data.id);
   					
   					$.ajax({
   						type : "POST", //提交方式  
   						url : "/admin/venue/getVenueEnter",//路径  
   						data : {
   							id : obj.data.id
   						},//数据，这里使用的是Json格式进行传输  
   						dataType : "json",
   						success : function(result) {//返回数据根据结果进行相应的处理  
   		   	        		$('#contactPhoneEnter').val(result.data.contactPhone);
   		   	        		$('#informPhoneEnter').val(result.data.informPhone);
   		   	        		$('#venueAddressEnter').val(result.data.venueAddress);
   		   	        		$('#checkContentEnter').val(result.data.checkContent);
   		   	        		$('#trainTeamNameEnter').val(result.data.trainTeamName);
   		   	        		
	   		   	        	var mainFlags = $("#mainFlag").find("option");
				            for(var i=0;i<mainFlags.length;i++){
				            	var mainFlagOption = mainFlags.eq(i);
				            	if($(mainFlagOption).val() == result.data.mainFlag){
									$(mainFlagOption).attr("selected",true);
				            	}else{
									$(mainFlagOption).attr("selected",false);
				            	}
				            }
				            var ballSums = $("#ballSum").find("option");
				            for(var i=0;i<ballSums.length;i++){
				            	var ballSumOption = ballSums.eq(i);
								if($(ballSumOption).val() == result.data.ballSum){
									$(ballSumOption).attr("selected",true);
				            	}else{
									$(ballSumOption).attr("selected",false);
				            	}
				            }
   						}
   					});
   					
   					
   					if(mapmarker != null){
   				     	map.clearOverlays();
   					}
   					// 百度坐标系坐标(地图中需要使用这个)
   				    var bPoints = new Array();
   					
  			      	var point = new BMap.Point(obj.data.lng, obj.data.lat);
  			      	var venueIcon = new BMap.Icon("https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/baseImage/map-venue1-100.png", new BMap.Size(36,36));
  			      	venueIcon.setInfoWindowAnchor(new BMap.Size(12,0));
  			        venueIcon.setImageSize(new BMap.Size(36,36));
   			      	var marker = new BMap.Marker(point,{icon:venueIcon});// 创建标注
   			      	map.addOverlay(marker);// 将标注添加到地图中
  			        bPoints.push(point);

   			      	
  			      	var point1 = new BMap.Point(obj.data.userLng, obj.data.userLat);
  			      	var userIcon = new BMap.Icon("https://ekeae-image.oss-cn-shenzhen.aliyuncs.com/baseImage/map-user3.png", new BMap.Size(36,36));
  			      	userIcon.setInfoWindowAnchor(new BMap.Size(12,0));
  			      	userIcon.setImageSize(new BMap.Size(36,36));
  			      	
   			      	var marker1 = new BMap.Marker(point1,{icon:userIcon});// 创建标注
   			      	map.addOverlay(marker1);// 将标注添加到地图中
  			        bPoints.push(point1);

   			      	mapmarker = marker;
   			      	
   			  		// 根据点的数组自动调整缩放级别
					function setZoom(bPoints) {
						var view = map.getViewport(eval(bPoints));
						var mapZoom = view.zoom;
						var centerPoint = view.center;
						map.centerAndZoom(centerPoint, mapZoom);
					}
					setTimeout(function () {
				        setZoom(bPoints);
				    }, 1000)
				    
					// 通过JavaScript的prototype属性继承于BMap.Control
	   			  	ZoomControl.prototype = new BMap.Control();
	   			  	
					if (myZoomCtrl != null) {
						// 删除控件
	   	   			  	map.removeControl(myZoomCtrl);
					}
					
				 	// 定义一个控件类,即function
					function ZoomControl(){
						// 默认停靠位置和偏移量
						this.defaultAnchor = BMAP_ANCHOR_BOTTOM_LEFT;
						this.defaultOffset = new BMap.Size(10, 10);
					}

   	   			  	// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
   	   			  	// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
   	   			  	ZoomControl.prototype.initialize = function(map){
						// 创建一个DOM元素
						var div = document.createElement("div");
						// 添加文字说明
						
						var area = map.getDistance(point ,point1);
						div.appendChild(document.createTextNode("直线距离:"+parseInt(area)+"米"));
						// 设置样式
						div.style.cursor = "pointer";
						div.style.border = "1px solid gray";
						div.style.backgroundColor = "white";
   	   			  	  
       	   			  	div.onclick = function(even){
       	   			  		map.panTo(point, false);
       	   			  	};
   	   			  	    // 添加DOM元素到地图中
   	   			  	    map.getContainer().appendChild(div);
   	   			  	    // 将DOM元素返回
   	   			  	    return div;
   	   			  	}
	   			  	// 创建控件
   	   			  	myZoomCtrl = new ZoomControl();
   	   			  	// 添加到地图当中
   	   			  	map.addControl(myZoomCtrl);
   				});
        	}

		$("#selectVenueEnterType").on("change", function () {
			var venueEnterType = $(this).val();
			if (venueEnterType != 0) {
        		$('#contactPhoneEnter').attr("readonly","readonly");
        		$('#informPhoneEnter').attr("readonly","readonly");
        		$('#venueAddressEnter').attr("readonly","readonly");
        		$('#checkContentEnter').attr("readonly","readonly");
        		$('#mainFlagEnter').attr("readonly","readonly");
        		$('#ballSumEnter').attr("readonly","readonly");
        		$('#checkEnterPass').attr("disabled","disabled");
        		$('#checkEnterDisable').attr("disabled","disabled");
			} else {
        		$('#contactPhoneEnter').removeAttr("readonly");
        		$('#informPhoneEnter').removeAttr("readonly");
        		$('#venueAddressEnter').removeAttr("readonly");
        		$('#checkContentEnter').removeAttr("readonly");
        		$('#mainFlagEnter').removeAttr("readonly");
        		$('#ballSumEnter').removeAttr("readonly");
        		$('#checkEnterPass').removeAttr("disabled");
        		$('#checkEnterDisable').removeAttr("disabled");
			}
			venueEnterTableInit($("#selectVenueEnterType").val());
        });
        
        
        $("#venueCheck").on("click", function () {
            var obj = $("#button4");
            obj.removeClass("hide");
            layer.open({
                type: 1,
                area: ['100%', '52%'],
                offset: 'b',
                title: '待审核场地',
                resize: true,
                anim: 1,
                shadeClose : true,
                content: obj,
	            cancel: function (index, layero) {
                    $(".contextMenuDialog").addClass("hide");
                    layer.closeAll(); 
	                obj.css("display","none");
	    			$.reload(tableObj.obj);
	            },
	            end: function (index, layero) {
                    $(".contextMenuDialog").addClass("hide");
                    layer.closeAll(); 
	                obj.css("display","none");
	    			$.reload(tableObj.obj);
	            }
            });
            
            var tableLog = {
    	            obj: null,
    	            init: function () {
    	                var self = this;
    	                self.tableList();
    	                self.search();
    	            },
                    search: function () {
                        var self = this;
                        $("#btnSearch4").unbind().on("click", function () {
                            $.reload(self.obj);
                        });
                    },
                    tableList: function () {
                        var self = this;
                        self.obj = $.tableObject({
                            tableId: 'tableVenueCheck',
                            tableOption: {
                                url: '/admin/venue/venueCheckList',
                                page: true,
                                height: 410,
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
                         				{field: 'id', title: 'id', hide:true},
                         				{field: 'lng', title: 'lng', hide:true},
                         				{field: 'lat', title: 'lat', hide:true},
                                        {field: 'createTime', title: '时间', sort: true},
                                        {field: 'trainTeam', title: '上传机构', sort: true},
                                        {field: 'title', title: '场馆名', sort: true},
                                        {field: 'type', title: '类型', sort: true},
                                        {field: 'content', title: '描述', sort: true},
                                        {fixed: 'right', title: '操作', align:'center', toolbar: '#venueCheckBar'}
                                    ]
                                ]
                            }
                        });
                    }
                }
            	tableLog.init();

            	$("#citymapDiv").removeClass("hide");
            	layer.open({
             	  title: "位置", 
				  type: 1,
				  anim: 1,
				  shade: 0,
				  closeBtn: 0,
				  offset: 't',
				  move: false,
	              shadeClose : true,
				  resize: false,
				  area: ['30%', '45%'],
				  content: $('#citymap'),
				  success: function(){
					    map = new BMap.Map("citymap");    // 创建Map实例
						map.centerAndZoom(new BMap.Point(114.056386,22.592976), 20);  // 初始化地图,设置中心点坐标和地图级别
						//添加地图类型控件
						map.addControl(new BMap.MapTypeControl({
							mapTypes:[
						        BMAP_NORMAL_MAP,
						        BMAP_HYBRID_MAP
						    ]}));	  
						map.setCurrentCity("深圳");          // 设置地图显示的城市 此项是必须设置的
						map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
				   }
				});
            	layui.table.on('tool(tableVenueCheck)', function(obj) {
		   			var data = obj.data;
		   			var layEvent = obj.event;
		   			var tr = obj.tr;
		   			
	   				var check = 0;
	   				if (layEvent == 'yes') {
	   					layer.msg("通过审核");
	   					check = 1;
	   				} else if (layEvent == 'no') {
	   					layer.msg("无效场馆");
	   					check = 2;
	   				}
	   				$.ajax({
	   					type : "POST", //提交方式  
	   					url : "/admin/venue/venueCheck",//路径  
	   					data : {
	   						id : data.id,
	   						check : check
	   					},//数据，这里使用的是Json格式进行传输  
	   					dataType : "json",
	   					success : function(result) {//返回数据根据结果进行相应的处理  
	   	
	   					}
	   				});
	   				$.reload(tableLog.obj);
	            });
            	
   				//监听行单击事件
   				layui.table.on('row(tableVenueCheck)', function(obj){
   					if(mapmarker != null){
   				        map.removeOverlay(mapmarker);
   					}
   					
   			        if(obj.data.lng != '' && obj.data.lat != ''){
   			        	var point = new BMap.Point(obj.data.lng, obj.data.lat);
   			      		map.centerAndZoom(point, 16);
   			      		var marker = new BMap.Marker(point);// 创建标注
   			      		mapmarker = marker;
   			      		map.addOverlay(marker);// 将标注添加到地图中
   			        }else{
   			        	layer.msg("暂无地址信息");
   			        }
   				});
        });
        
        $("#venueLog").on("click", function () {
            var obj = $("#button3");
            obj.removeClass("hide");
            layer.open({
                type: 1,
                area: ['100%', '500px'],
                offset: 'b',
                title: '场馆日志',
                resize: true,
                anim: 1,
                shadeClose : true,
                content: obj,
                cancel: function (index, layero) {
                    $(".contextMenuDialog").addClass("hide");
                }
            });

            var tableLog = {
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
                        tableId: 'tableAllLog',
                        tableOption: {
                            url: '/admin/venue/venuelogAllList',
                            page: true,
                            height: 370,
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
                                    {field: 'createtime', title: '时间', sort: true},
                                    {field: 'name', title: '场馆', sort: true},
                                    {field: 'venueno', title: '场馆编号', sort: true},
                                    {field: 'content', title: '内容', sort: true},
                                ]
                            ]
                        }
                    });
                }
            }
        	tableLog.init();
        });
        
        
        $("#venueImport").on("click", function () {
            layer.open({
                type: 2,
                area: ['100%', '50%'],
                offset: 'b',
                title: '导入场馆',
                resize: true,
                anim: 1,
                shadeClose : true,
                content: "/admin/venue/importView",
                cancel: function (index, layero) {
                },
                end: function () {
	        	}
            });
        });
        
        $("#venueTrim").on("click", function () {
        	layer.open({
                type: 2,
                area: ['100%', '60%'],
                offset: 'b',
                title: '模板梳理',
                resize: true,
                anim: 1,
                shadeClose : true,
                content: "/admin/venue/venueTemplateAll",
                cancel: function (index, layero) {
                },
                end: function () {
	        	}
            });
        });
        $("#venueAnalyze").on("click", function () {
        	layer.open({
                type: 2,
                area: ['100%', '60%'],
                offset: 'b',
                title: '模板分析',
                resize: true,
                anim: 1,
                shadeClose : true,
                content: "/admin/venue/venueAnalyze",
                cancel: function (index, layero) {
                },
                end: function () {
	        	}
            });
        });
        $("#venueInsert").on("click", function () {
        	$.showAjaxContent("新增", "40%", "/admin/venue/add/view", "");
        	$("#citymapDiv").removeClass("hide");
        	layer.open({
         	  title: "位置", 
			  type: 1,
			  anim: 1,
			  shade: 0.3,
			  closeBtn: 0,
			  offset: '20px',
			  title: false,
			  move: false,
              shadeClose : true,
			  resize: false,
			  area: ['30%', '50%'],
			  content: $('#citymap'),
              cancel: function (index, layero) {
            	  layer.closeAll();
                  $(".contextMenuDialog").addClass("hide");
      			  $.reload(tableObj.obj);
              },
              end: function () {
            	  layer.closeAll();
                  $(".contextMenuDialog").addClass("hide");
      			  $.reload(tableObj.obj);
              },
			  success: function(){
				    map = new BMap.Map("citymap");    // 创建Map实例
					map.centerAndZoom(new BMap.Point(114.056386, 22.592976), 15);  // 初始化地图,设置中心点坐标和地图级别
					//添加地图类型控件
					map.addControl(new BMap.MapTypeControl({
						mapTypes:[
					        BMAP_NORMAL_MAP,
					        BMAP_HYBRID_MAP
					    ]}));	  
					map.setCurrentCity("深圳");          // 设置地图显示的城市 此项是必须设置的
					map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
					map.disableDoubleClickZoom();
					
					// 删除上次使用的标注
					if(mapmarker != null){
						mapmarker = null;
					}
					
					// 获取地址对象
				    var geoc = new BMap.Geocoder();    
					var indexMarker = null;
					//单击事件
				  	map.addEventListener("dblclick",function(e){
						//map.clearOverlays();//清除所有标注
						if(indexMarker != null){
							delMarker(indexMarker);
						}
				  		var point = new BMap.Point(e.point.lng, e.point.lat);
				  		lng = e.point.lng;//存贮点击的经度
						lat = e.point.lat;//存贮点击的维度
				  		map.centerAndZoom(point, map.getZoom());
				  		var marker = new BMap.Marker(point);// 创建标注
				  		indexMarker = marker;//存贮marker对象,方便后续点击删除上一次点击的标注
				  		//marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
						mapmarker = marker;
				  		map.addOverlay(marker);// 将标注添加到地图中
				  		
				  		geoc.getLocation(point, function(rs){
				            //addressComponents对象可以获取到详细的地址信息
				            var addComp = rs.addressComponents;
				            cityName = addComp.city;
				            districtName = addComp.district;
      						$("#addressStrAdd").text(addComp.city + addComp.district + addComp.street + addComp.streetNumber);
      						$("#addressAdd").val(addComp.city + addComp.district + addComp.street + addComp.streetNumber);
				            /* var citys = $("#cityid").find("option");
				            for(var i=0;i<citys.length;i++){
				            	var city = citys.eq(i);
								$(city).attr("selected",false);;
				            }
				            for(var i=0;i<citys.length;i++){
				            	var city = citys.eq(i);
								if(addComp.city.substring(0,addComp.city.length-1) == $(city).text()){
									$(city).attr("selected",true);
									$.ajax({
										type : "POST",
										url : "/admin/city/district/list",
										data : {
											id : $(city).val()
										},
										dataType : "json",
										success : function(result) {
											$("#districtid").empty();
											var list = result.data;
											for (var j = 0; j < list.length; j++) {
												//先创建好select里面的option元素
												var option = document.createElement("option");
												//转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
												$(option).val(list[j].id);
												//给option的text赋值,这就是你点开下拉框能够看到的东西
												$(option).text(list[j].district);
												
												if(addComp.district.substring(0,addComp.district.length-1) == list[j].district){
													$(option).attr("selected",true);;
												}
												
												//获取select 下拉框对象,并将option添加进select
												$('#districtid').append(option);
											}
										}
									});
								}
				            } */
				        });  
					});

					// 编写自定义函数,删除标注
					function delMarker(marker){
						map.removeOverlay(marker);
					}
			 	}
			});
        });

        $("#checkEnterPass").on("click", function () {
        	var venueEnterId = $('#venueEnterId').val();
        	if(venueEnterId == ""){
        		layer.msg("请选择要操作的申请");
        		return;
        	}
        	var contactPhone = $('#contactPhone').val();
        	var informPhone = $('#informPhone').val();
        	var venueAddress = $('#venueAddress').val();
        	var checkContent = $('#checkContent').val();
        	var mainFlag = $('#mainFlag').val();
        	var ballSum = $('#ballSum').val();
        	if(contactPhone == ""){
        		layer.msg("请输入场馆订场电话");
        		return;
        	}
        	if(informPhone == ""){
        		layer.msg("请输入订场短信通知");
        		return;
        	}
        	if(venueAddress == ""){
        		layer.msg("请输入场馆地址");
        		return;
        	}
        	if(checkContent == ""){
        		layer.msg("请输入审核意见");
        		return;
        	}
        	var indexLoad = layer.load();
        	$.ajax({
				type : "POST", //提交方式  
				url : "/admin/venue/venueEnter/check",//路径  
				data : {
					id : venueEnterId,
					check : 1,
					contactPhone : contactPhone ,
	        		informPhone : informPhone,
	        		venueAddress : venueAddress,
	        		mainFlag : mainFlag,
	        		ballSum : ballSum,
	        		checkContent : checkContent
				},//数据，这里使用的是Json格式进行传输  
				dataType : "json",
				success : function(result) {//返回数据根据结果进行相应的处理  
					if (result.code == 200) {
						layer.msg("通过审核");
						venueEnterTableInit($("#selectVenueEnterType").val());
					} else {
						layer.msg(result.msg);
					}
					layer.close(indexLoad);
				},
				error : function(){
					layer.close(indexLoad);
				}
			});
        });
        $("#checkEnterDisable").on("click", function () {
        	var venueEnterId = $('#venueEnterId').val();
        	if(venueEnterId == ""){
        		layer.msg("请选择要操作的申请");
        		return;
        	}
        	var checkContent = $('#checkContent').val();
        	if(checkContent == ""){
        		layer.msg("请输入审核意见");
        		return;
        	}
        	
        	$.ajax({
				type : "POST", //提交方式  
				url : "/admin/venue/venueEnter/check",//路径  
				data : {
					id : venueEnterId,
					check : 2,
	        		checkContent : checkContent
				},//数据，这里使用的是Json格式进行传输  
				dataType : "json",
				success : function(result) {//返回数据根据结果进行相应的处理  
					layer.msg("已处理为无效申请");
					venueEnterTableInit($("#selectVenueEnterType").val());
				}
			});
        });
        
    });
</script>

<div id="citymapDiv" class="contextMenuDialog otherDialog hide">
    <div class="row">
        <div class="col-12" id="citymap" style="width:100%;height: 100%;">
        </div>
    </div>
</div>


<script type="text/html" id="venueEnterBar">
  <!-- 这里同样支持 laytpl 语法，如： -->
  {{# if(d.checkFlag == '待核'){ }}
    <a class="layui-btn layui-btn-xs" lay-event="yes" style="color: #fff;" id="yes">通过</a>
    <a class="layui-btn layui-btn-xs" lay-event="no" style="color: #fff;" id="no">无效</a>
  {{# } }}
</script>

<script type="text/html" id="venueCheckBar">
  <!-- 这里同样支持 laytpl 语法，如： -->
    <a class="layui-btn layui-btn-xs" lay-event="yes" style="color: #fff;" id="yes">通过</a>
    <a class="layui-btn layui-btn-xs" lay-event="no" style="color: #fff;" id="no">不通过</a>
</script>

<div class="contextMenuDialog hide" id="button1">
    <div class="card-body">
		<div class="card-tools" style="padding-bottom: 10px;">
			当前入驻机构:<input class="form-control " style="display: table-row;width:200px;margin-bottom: 20px;" id="trainTeamNameEnter" name="trainTeamNameEnter" readonly="readonly" type="text"  placeholder="当前入驻机构" maxlength="30">
			<div class=" float-left" style="line-height: 38px;">
				审核状态: <select class="form-control float-right" id="selectVenueEnterType" style="display: table-row;width:150px" >
					<option value="0">待核</option>
					<option value="1">通过</option>
					<option value="2">无效</option>
				</select>
			</div>
			<br/>
			
			<div class=" float-left" style="line-height: 38px;">
				其他联系人关系: <select class="form-control float-right" id="mainFlagEnter" style="display: table-row;width:150px" >
					<option value="1">负责人</option>
					<option value="2">同事</option>
					<option value="3">业主或其他</option>
				</select>
			</div>
			<div class=" float-left" style="line-height: 38px;">
				球场片数: <select class="form-control float-right" id="ballSumEnter" style="display: table-row;width:150px" >
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select>
			</div>
			场馆订场电话:<input class="form-control " style="display: table-row;width:150px" id="contactPhoneEnter" name="contactPhoneEnter" type="text"  placeholder="场馆订场电话" maxlength="30">
			订场短信通知:<input class="form-control " style="display: table-row;width:150px" id="informPhoneEnter" name="informPhoneEnter" type="text"  placeholder="订场短信通知" maxlength="20">
			场馆地址:<input class="form-control " style="display: table-row;width:200px" id="venueAddressEnter" name="venueAddressEnter" type="text"  placeholder="场馆地址" maxlength="100">
			审核意见:<input class="form-control " style="display: table-row;width:200px" id="checkContentEnter" name="checkContentEnter" type="text"  placeholder="审核意见" maxlength="100">
			<input id="venueEnterId" type="hidden" value="" >
			<button class="btn btn-primary btn-sm" id="checkEnterPass">通过</button>
			<button class="btn btn-primary btn-sm" id="checkEnterDisable">无效</button>
		</div>
		<div class="row">
			<table id="tableAllVenueEnter"></table>
		</div>
	</div>
</div>

<div class="contextMenuDialog hide" id="button3">
    <div class="card-body">
			<div class="card-tools" style="padding-bottom:  10px;">
				<div class="input-group input-group-sm" style="width: 350px;">
					<select class="form-control float-right" id="selectType">
						<option value="0">场馆名</option>
						<option value="1">场馆编号</option>
						<option value="2">内容</option>
					</select> 
					<input class="form-control float-right" id="keyword" name="table_search" type="text" placeholder>

					<div class="input-group-append">
						<button class="btn btn-default" id="btnSearch" type="submit">
							<i class="fa fa-search"></i> 搜索
						</button>
					</div>
				</div>
			</div>
			<div class="row">
            <div class="card-body table-responsive p-0">
                <table id="tableAllLog">
                </table>
            </div>
        </div>
    </div>
</div>

	<div class="contextMenuDialog hide" id="button4">
		<div class="card card-secondary">
			<div class="card-body table-responsive p-0">
				<table id="tableList">
				</table>
			</div>
		</div>
		<div class="card-body table-responsive p-0">
			<table id="tableVenueCheck">
			</table>
		</div>
	</div>

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
<div class="contextMenuDialog hide" id="item3">
    <div class="card-body">
        <div class="row">
            <table id="tableVenueError"></table>
        </div>
    </div>
</div>

</body>

</html>