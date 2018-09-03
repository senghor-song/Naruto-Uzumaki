<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/WebRelease/admin/static/css/site.css" rel="stylesheet">
<link rel="stylesheet" href="/WebRelease/admin/static/plugins/bootstrap-validator/dist/css/bootstrapValidator.css" />
<link rel="stylesheet" href="/WebRelease/admin/static/plugins/bootstrap-validator/vendor/bootstrap/css/dataValidator.css" />
<div class="contextMenuDialog" id="createcontent">
        <div class="card-body form-control" style="border:none;">
            <div class="row">
                <div class="col-lg-6" id="baiduMap"  style="width:400px;height:95%">
                	
                </div>
                <div class="col-lg-6">
                	<form action="#" id="form" class="form-horizontal">
                    	<div class="row mt-2">
	                        <div class="col-lg-6">
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-4 nametl">小区</div>
	                                <div class="col-lg-8">
	                                	<input name="estate" id="estate" class="form-control" type="text" class="w-100" placeholder="">
	                                </div>
	                            </div>
	                        </div>
	                        <!-- <div class="col-lg-6">
	                            <div class="row">
	                                <div class="col-lg-4 nametl">户数</div>
	                                <div class="col-lg-8">
	                                    <input name="" id="estate" class="form-control" type="text" class="w-100" placeholder="">
	                                </div>
	                            </div>
	                        </div>
	                        <div class="col-lg-6">
	                            <div class="row">
	                                <div class="col-lg-4 nametl">物业类型</div>
	                                <div class="col-lg-8">
	                                    <select name="" id="" class="form-control" id="">
	                                        <option value="">住宅</option>
	                                    </select>
	                                </div>
	                            </div>
	                        </div> -->
	                        <div class="col-lg-6">
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-4 nametl">建筑年代</div>
	                                <div class="col-lg-8">
	                                    <select name="morecompleteyear" class="form-control" id="morecompleteyear">
	                                        <option value="2000">2000年</option>
                                            <option value="2001">2001年</option>
                                            <option value="2002">2002年</option>
                                            <option value="2003">2003年</option>
                                            <option value="2004">2004年</option>
                                            <option value="2005">2005年</option>
                                            <option value="2006">2006年</option>
                                            <option value="2007">2007年</option>
                                            <option value="2008">2008年</option>
                                            <option value="2009">2009年</option>
                                            <option value="2010">2010年</option>
                                            <option value="2011">2011年</option>
                                            <option value="2012">2012年</option>
                                            <option value="2013">2013年</option>
                                            <option value="2014">2014年</option>
                                            <option value="2015">2015年</option>
                                            <option value="2016">2016年</option>
                                            <option value="2017">2017年</option>
                                            <option value="2018">2018年</option>
	                                    </select>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="col-lg-6">
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-4 nametl">物业费</div>
	                                <div class="col-lg-8">
	                                    <input name="morefee" id="morefee" class="form-control" type="text" class="w-100" placeholder="元/平米">
	                                </div>
	                            </div>
	                        </div>
	                        <div class="col-lg-6">
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-4 nametl">总户数</div>
	                                <div class="col-lg-8">
	                                    <input name="moreroom" id="moreroom" class="form-control" type="text" class="w-100" placeholder="">
	                                </div>
	                            </div>
	                        </div>
	                        <div class="col-lg-6">
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-4 nametl">建筑类型</div>
	                                <div class="col-lg-8">
	                                    <select name="moretype" class="form-control" id="moretype">
	                                        <option value="多层，板楼">多层，板楼</option>
	                                    </select>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="col-lg-6">
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-4 nametl">停车位</div>
	                                <div class="col-lg-8">
	                                    <input name="moreparking" id="moreparking" class="form-control" type="text" class="w-100" placeholder="">
	                                </div>
	                            </div>
	                        </div>
	                        <div class="col-lg-6">
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-4 nametl">物业电话</div>
	                                <div class="col-lg-8">
	                                    <input name="mgttel" id="mgttel" class="form-control" type="text" class="w-100" placeholder="">
	                                </div>
	                            </div>
	                        </div>
	                        <div class="col-lg-6">
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-4 nametl">开发商</div>
	                                <div class="col-lg-8">
	                                    <input name="moredeveloper" id="moredeveloper" class="form-control" type="text" class="w-100" placeholder="">
	                                </div>
	                            </div>
	                        </div>
                            <div class="col-lg-6">
                                <div class="row form-group has-feedback">
                                    <div class="col-lg-4 nametl">城市</div>
                                    <div class="col-lg-8">
                                        <select name="cityid" class="form-control" id="city" onchange="citySelect()" disabled="disabled">
                                        	<c:forEach items="${citys}" var="city">
	                                            <option value="${city.id }" >${city.city }</option>
                                        	</c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row form-group has-feedback">
                                    <div class="col-lg-4 nametl">片区</div>
                                    <div class="col-lg-8">
                                        <select name="districtid" class="form-control" id="district" onchange="districtSelect()">
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row form-group has-feedback">
                                    <div class="col-lg-4 nametl">路段</div>
                                    <div class="col-lg-8">
                                        <select name="areaid" class="form-control" id="area">
                                        </select>
                                    </div>
                                </div>
                            </div>
	                        <div class="col-lg-12">
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-2 nametl">物业公司</div>
	                                <div class="col-lg-10">
	                                    <input name="moremgt" id="moremgt" class="form-control" type="text" class="w-100" placeholder="">
	                                </div>
	                            </div>
	                        </div>
	                        <div class="col-lg-12">
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-2 nametl">小区描述</div>
	                                <div class="col-lg-10">
	                                    <textarea name="moredescribe" id="moredescribe" class="form-control" id="" cols="30" rows="5"></textarea>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="col-lg-12 mt-2">
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-2 nametl"></div>
	                                <div class="col-lg-10">
	                                    <button class="form-control btn btn-primary w-25" id="addEstate">创建</button>
	                                </div>
	                            </div>
	                        </div>
                            <input type="hidden" value="" id="lng" name="longitude">
                            <input type="hidden" value="" id="lat" name="latitude">
                            <input type="hidden" value="" id="address" name="address">
                    	</div>
	            	</form>
                </div>
            </div>
        </div>
    </div>
    
    <script src="/WebRelease/admin/static/plugins/jquery/jquery.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/layer/layer.js"></script>
	<script type="text/javascript" src="/WebRelease/admin/static/plugins/bootstrap-validator/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/WebRelease/admin/static/plugins/bootstrap-validator/dist/js/bootstrapValidator.js"></script>
	<script type="text/javascript">
                	//百度地图API功能
					function loadJScript() {
						var script = document.createElement("script");
						script.type = "text/javascript";
						script.src = "http://api.map.baidu.com/api?v=2.0&ak=${key}&callback=init";
						document.body.appendChild(script);
					}
					function init() {
						var map = new BMap.Map("baiduMap", {enableMapClick:false});// 创建Map实例
						var indexMarker = null;
						//右键删除事件
						var removeMarker = function(e,ee,marker){
							$("#lng").val("");
							$("#lat").val("");
							map.removeOverlay(initmarker);
						}
   						var point = new BMap.Point(114.055326, 22.557414);// 创建点坐标
   						map.centerAndZoom(point,13);                 
   						map.enableScrollWheelZoom();//启用滚轮放大缩小
						//单击事件
					  	map.addEventListener("click",function(e){
    						//定位拾坐标
    					    var geoc = new BMap.Geocoder();
							//map.clearOverlays();//清除所有标注
							if(indexMarker != null){
								delMarker(indexMarker);
							}
					  		var point = new BMap.Point(e.point.lng, e.point.lat);
					  		lng = e.point.lng;//存贮点击的经度
							lat = e.point.lat;//存贮点击的维度
							$("#lng").val(lng);
							$("#lat").val(lat);
					  		map.centerAndZoom(point, 20);
					  		var marker = new BMap.Marker(point);// 创建标注
					  		indexMarker = marker;//存贮marker对象,方便后续点击删除上一次点击的标注
					  		geoc.getLocation(point, function (rs) {
					            var addComp = rs.addressComponents;
					            var address = rs.address;
    							$("#address").val(address);
    							
    							$("#city option").each(function(){  //遍历所有option  
    			            		var channlVal= $(this).text();   //获取option值   
    			            		var city = addComp.city.substring(0,addComp.city.length-1);
			            			$(this).attr("selected", false);
    			            		if(channlVal == city){
    			            			$(this).attr("selected", true);
    		            			}
    		            		});
			            		citySelect();
					        });
						  	//创建右键菜单
							var markerMenu=new BMap.ContextMenu();
							markerMenu.addItem(new BMap.MenuItem('删除',removeMarker.bind(marker)));
							marker.addContextMenu(markerMenu);
							
					  		map.addOverlay(marker);// 将标注添加到地图中
						});

						// 编写自定义函数,删除标注
						function delMarker(marker){
						  map.removeOverlay(marker);
						}
						
					}  
					window.onload = loadJScript;  //异步加载地图
                	
	            	$(function () {
	            		citySelect();
	                	$('#form').bootstrapValidator({
//	                      live: 'disabled',
	                      message: '请输入正确的内容',
	                      feedbackIcons: {
	                          valid: 'glyphicon glyphicon-ok',
	                          invalid: 'glyphicon glyphicon-remove',
	                          validating: 'glyphicon glyphicon-refresh'
	                      },
	                      fields: {
	                    	  estate: {
	                              validators: {
	                                  notEmpty: {
	                                      message: '小区名不能为空'
	                                  }
	                              }
	                          },
	                          morecompleteyear: {
	                              validators: {
	                                  notEmpty: {
	                                      message: '建筑年代不能为空'
	                                  }
	                              }
	                          },
	                          morefee: {
	                              validators: {
	                                  notEmpty: {
	                                      message: '物业费不能为空'
	                                  }
	                              }
	                          },
	                          moreroom: {
	                              validators: {
	                                  notEmpty: {
	                                      message: '总户数不能为空'
	                                  }
	                              }
	                          },
	                          moretype: {
	                              validators: {
	                                  notEmpty: {
	                                      message: '建筑类型不能为空'
	                                  }
	                              }
	                          },
	                          moreparking: {
	                              validators: {
	                                  notEmpty: {
	                                      message: '停车位不能为空'
	                                  }
	                              }
	                          },
	                          mgttel: {
	                              validators: {
	                                  notEmpty: {
	                                      message: '物业电话不能为空'
	                                  }
	                              }
	                          },
	                          moredeveloper: {
	                              validators: {
	                                  notEmpty: {
	                                      message: '开发商不能为空'
	                                  }
	                              }
	                          },
	                          moremgt: {
	                              validators: {
	                                  notEmpty: {
	                                      message: '物业公司不能为空'
	                                  }
	                              }
	                          },
	                          moredescribe: {
	                              validators: {
	                                  notEmpty: {
	                                      message: '小区描述不能为空'
	                                  }
	                              }
	                          },
	                          areaid: {
	                              validators: {
	                                  notEmpty: {
	                                      message: '请选择路段'
	                                  }
	                              }
	                          },
	                          districtid: {
	                              validators: {
	                                  notEmpty: {
	                                      message: '请选择片区'
	                                  }
	                              }
	                          }
	                      }
	                  });
	            	});
	            	$("#addEstate").click(function () {
	            		$("#city").attr("disabled",false);
	                    $('#form').bootstrapValidator('validate');
	                    //是否通过校验
	                    if($('#form').data('bootstrapValidator').isValid()){  
		                	$.ajax({  
		                        type : "POST",  //提交方式  
		                        url : "/WebRelease/admin/estate/save",//路径  
		                        data : $("form").serialize(),//数据，这里使用的是Json格式进行传输  
		                        dataType:"json",
		                        success : function(result) {//返回数据根据结果进行相应的处理  
		                            if ( result.code == 200 ) {  
		                            	parent.window.location.href="/WebRelease/admin/estate/listview";
		                            } else {  
		                        		layer.confirm(result.msg, {
		                        			btn: ['确定'] //按钮
		                        		});
		                            }  
		                        }  
		                    }); 
	                    }
	                });
	            	function citySelect(){
	            		var city = $("#city").val();
	            		$.ajax({  
	                        type : "POST",  //提交方式  
	                        url : "/WebRelease/admin/city/getDistrictList",//路径  
	                        data : {id : city},//数据，这里使用的是Json格式进行传输  
	                        dataType:"json",
	                        success : function(result) {//返回数据根据结果进行相应的处理  
	                        	var list = result.data;
	                        	$("#district").empty();
	                        	if(list.length > 0){
		    						$.each(list,function(n,value) {
			    						$("#district").append("<option value='"+value.id+"'>"+value.district+"</option>");
		    						});
	                        	}
	    						districtSelect();
	                        }  
	                    }); 
	            	}
	            	function districtSelect(){
	            		var district = $("#district").val();
	            		$.ajax({  
	                        type : "POST",  //提交方式  
	                        url : "/WebRelease/admin/city/getAreaList",//路径  
	                        data : {id : district},//数据，这里使用的是Json格式进行传输  
	                        dataType:"json",
	                        success : function(result) {//返回数据根据结果进行相应的处理  
	                        	var list = result.data;
	                        	$("#area").empty();
	                        	if(list.length > 0){
		    						$.each(list,function(n,value) {  
			    						$("#area").append("<option value='"+value.id+"'>"+value.area+"</option>");
		    						});
	                        	}
	                        }  
	                    }); 
	            	};
	            	</script>