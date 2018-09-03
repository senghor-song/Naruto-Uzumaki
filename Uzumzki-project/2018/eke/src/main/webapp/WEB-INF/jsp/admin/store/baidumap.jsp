<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div id="baiduMap"  style="width:100%;height:100%">
        	
    </div>
<script src="/WebRelease/admin/static/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript">

					var flag = ${empStore.longitude == 0 && empStore.latitude == 0 };
					var lng = ${empStore.longitude};
					var lat = ${empStore.latitude};
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
						if(flag){
    						var point = new BMap.Point(114.055326, 22.557414);// 创建点坐标
    						map.centerAndZoom(point,13);                 
    						map.enableScrollWheelZoom();//启用滚轮放大缩小
						}else{
    						var point = new BMap.Point(lng, lat);// 创建点坐标
    						map.centerAndZoom(point,20);                 
    						map.enableScrollWheelZoom();//启用滚轮放大缩小
    						var pt = new BMap.Point(lng, lat);
    						var initmarker = new BMap.Marker(pt);  // 创建标注
    						
						  	//创建右键菜单
							var initmarkerMenu=new BMap.ContextMenu();
							initmarkerMenu.addItem(new BMap.MenuItem('删除',removeMarker.bind(initmarker)));
							initmarker.addContextMenu(initmarkerMenu);
    						map.addOverlay(initmarker); // 将标注添加到地图中
    						indexMarker = initmarker;
						}
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
</script>

