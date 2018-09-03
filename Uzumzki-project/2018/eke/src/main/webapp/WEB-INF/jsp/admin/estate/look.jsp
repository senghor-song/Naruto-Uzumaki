<%@ page contentType="text/html;charset=UTF-8" language="java"%>
    <link href="/WebRelease/admin/static/css/site.css" rel="stylesheet">
<div class="contextMenuDialog" id="item1">
        <div class="card-body form-control" style="border:none;">
            <div class="row">
                <div class="col-lg-6" id="baiduMap"  style="width:400px;height:400px;">
                	
                </div>
                <div class="col-lg-6">
                    <div class="row mt-2">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">小区</div>
                                    <div class="col-lg-8">${estate.estate}</div>
                                </div>
                            </div>
                            <!-- <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">户数</div>
                                    <div class="col-lg-8">找不到</div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">物业类型</div>
                                    <div class="col-lg-8">找不到</div>
                                </div>
                            </div> -->
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">建筑年代</div>
                                    <div class="col-lg-8">${estate.morecompleteyear}年</div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">物业费</div>
                                    <div class="col-lg-8">${estate.morefee}元/平米</div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">总户数</div>
                                    <div class="col-lg-8">${estate.moreroom}</div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">建筑类型</div>
                                    <div class="col-lg-8">${estate.moretype}</div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">停车位</div>
                                    <div class="col-lg-8">${estate.moreparking }</div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">物业电话</div>
                                    <div class="col-lg-8">${estate.mgttel}</div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">开发商</div>
                                    <div class="col-lg-8">${estate.moredeveloper }</div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">城市</div>
                                    <div class="col-lg-8">${estate.cityT.city }</div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">片区</div>
                                    <div class="col-lg-8">${estate.districtT.district }</div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">路段</div>
                                    <div class="col-lg-8">${estate.areaT.area }</div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-2 nametl">物业公司</div>
                                    <div class="col-lg-10">${estate.moremgt }</div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-2 nametl">小区描述</div>
                                    <div class="col-lg-10">${estate.moredescribe}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <script type="text/javascript">
               		var lng = ${estate.longitude};
                   	var lat = ${estate.latitude};
                	//百度地图API功能
					function loadJScript() {
						var script = document.createElement("script");
						script.type = "text/javascript";
						script.src = "http://api.map.baidu.com/api?v=2.0&ak=${key}&callback=init";
						document.body.appendChild(script);
					}
					function init() {
						var map = new BMap.Map("baiduMap");// 创建Map实例
						var point = new BMap.Point(lng, lat);// 创建点坐标
						map.centerAndZoom(point,20);                 
						map.enableScrollWheelZoom();//启用滚轮放大缩小
						var pt = new BMap.Point(lng, lat);
						var marker = new BMap.Marker(pt);  // 创建标注
						map.addOverlay(marker); // 将标注添加到地图中
					}  
					window.onload = loadJScript;  //异步加载地图
                	
                </script>
            </div>
        </div>
    </div>