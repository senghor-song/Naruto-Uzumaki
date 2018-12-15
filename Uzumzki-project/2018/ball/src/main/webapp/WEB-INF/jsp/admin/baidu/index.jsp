<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	 .anchorBL{display:none;}
	</style>
	<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=${key}"></script>
	<script type="text/javascript" src="/admin/static/js/GeoUtils.js"></script>
	<title>地图展示</title>
</head>
<body>
	<div id="allmap"></div>
</body>
    <script src="/admin/static/plugins/jquery/jquery.min.js"></script>
	<script type="text/javascript">
		var marker1 = new Array();
		var marker2 = new Array();
		var marker3 = new Array();
		var marker4 = new Array();
		
		var lng = null,lat = null;
		// 百度地图API功能
		var map = new BMap.Map("allmap");    // 创建Map实例
		map.centerAndZoom(new BMap.Point(114.056386,22.592976), 15);  // 初始化地图,设置中心点坐标和地图级别
		//添加地图类型控件
		map.addControl(new BMap.MapTypeControl({
			mapTypes:[
	            BMAP_NORMAL_MAP,
	            BMAP_HYBRID_MAP
	        ]}));	  
		map.setCurrentCity("深圳");          // 设置地图显示的城市 此项是必须设置的
		map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
		
		var removeMarker = function(e,ee,marker){
			map.removeOverlay(marker);
		}
		var indexMarker = null;
		//单击事件
	  	map.addEventListener("click",function(e){
			//map.clearOverlays();//清除所有标注
			console.log(indexMarker);
			if(indexMarker != null){
				delMarker(indexMarker);
			}
	  		var point = new BMap.Point(e.point.lng, e.point.lat);
	  		lng = e.point.lng;//存贮点击的经度
			lat = e.point.lat;//存贮点击的维度
	  		map.centerAndZoom(point, 15);
	  		var marker = new BMap.Marker(point);// 创建标注
	  		indexMarker = marker;//存贮marker对象,方便后续点击删除上一次点击的标注
	  		//marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	  		
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
		
		// 编写自定义函数,创建标注
		function addMarker(point){
		  var marker = new BMap.Marker(point);
		  map.addOverlay(marker);
		}
		/* // 随机向地图添加25个标注
		var bounds = map.getBounds();
		var sw = bounds.getSouthWest();
		var ne = bounds.getNorthEast();
		var lngSpan = Math.abs(sw.lng - ne.lng);
		var latSpan = Math.abs(ne.lat - sw.lat);

		for (var i = 0; i < 10; i ++) {
			var pt1 = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));
			var myIcon1 = new BMap.Icon("/admin/static/image/img1.png", new BMap.Size(128,128));
			var marker1 = new BMap.Marker(pt1,{icon:myIcon1});  // 创建标注
			map.addOverlay(marker1); // 将标注添加到地图中
		}
		for (var i = 0; i < 10; i ++) {
			var pt1 = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));
			var myIcon1 = new BMap.Icon("/admin/static/image/img2.png", new BMap.Size(128,128));
			var marker1 = new BMap.Marker(pt1,{icon:myIcon1});  // 创建标注
			map.addOverlay(marker1); // 将标注添加到地图中
		}

		for (var i = 0; i < 10; i ++) {
			var pt1 = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));
			var myIcon1 = new BMap.Icon("/admin/static/image/img3.png", new BMap.Size(128,128));
			var marker1 = new BMap.Marker(pt1,{icon:myIcon1});  // 创建标注
			map.addOverlay(marker1); // 将标注添加到地图中
		}

		for (var i = 0; i < 10; i ++) {
			var pt1 = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));
			var myIcon1 = new BMap.Icon("/admin/static/image/img4.png", new BMap.Size(128,128));
			var marker1 = new BMap.Marker(pt1,{icon:myIcon1});  // 创建标注
			map.addOverlay(marker1); // 将标注添加到地图中
		} */
		var point1 = new BMap.Point(114.062925, 22.729273);
		var point2 = new BMap.Point(114.062925, 22.63573);

		var marker1 = new BMap.Marker(point1);
		var marker2 = new BMap.Marker(point2);

  		var point = new BMap.Point(114.062925,22.626273);
		var circle = new BMap.Circle(point, 5000, {
			strokeColor: "blue",
			strokeWeight: 2,
			strokeOpacity: 0.5
		}); //创建圆
		map.addOverlay(circle);
		// 随机向地图添加25个标注
		var bounds = map.getBounds();
		var sw = bounds.getSouthWest();
		var ne = bounds.getNorthEast();
		var lngSpan = Math.abs(sw.lng - ne.lng);
		var latSpan = Math.abs(ne.lat - sw.lat);
		for (var i = 0; i < 100; i ++) {
			var point5 = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.5), ne.lat - latSpan * (Math.random() * 0.5));
			var result = BMapLib.GeoUtils.isPointInCircle(point5, circle);
			if (result == true) {
				var marker = new BMap.Marker(point5);
			    map.addOverlay(marker);
			}
		}

		$.ajax({  
            type : "POST",  //提交方式  
            url : "/admin/baiduMap/index/getTopLngLat",//路径  
            data : {},//数据，这里使用的是Json格式进行传输  
            dataType:"json",
            async:false, 
            success : function(result) {//返回数据根据结果进行相应的处理  
            	var list0 = result.data.list0;
            	var list1 = result.data.list1;
            	var list2 = result.data.list2;
            	var list3 = result.data.list3;
            	for (var i = 0; i < list0.length; i ++) {
            		if(!(list0[i].lng == 0 && list0[i].lat == 0)){
	        			var pt1 = new BMap.Point(list0[i].lng, list0[i].lat);
	        			var myIcon1 = new BMap.Icon("/admin/static/image/img1.png", new BMap.Size(40,40));
	        			var marker = new BMap.Marker(pt1,{icon:myIcon1});  // 创建标注
	        			var label = new BMap.Label("1");
	        			label.setStyle({ 
	        				backgroundColor :"0",
	        				border :"0", 
	        				opacity: 0,
	        			});
	        			marker.setLabel(label);
	        			marker1[marker1.length] = marker;
	        			map.addOverlay(marker); // 将标注添加到地图中
            		}
        		} 
            	for (var i = 0; i < list1.length; i ++) {
            		if(!(list1[i].lng == 0 && list1[i].lat == 0)){
	        			var pt1 = new BMap.Point(list1[i].lng, list1[i].lat);
	        			var myIcon1 = new BMap.Icon("/admin/static/image/img2.png", new BMap.Size(40,40));
	        			var marker = new BMap.Marker(pt1,{icon:myIcon1});  // 创建标注
	        			var label = new BMap.Label("2");
	        			label.setStyle({ 
	        				backgroundColor :"0",
	        				border :"0", 
	        				opacity: 0,
	        			});
	        			marker.setLabel(label);
	        			marker2[marker2.length] = marker;
	        			map.addOverlay(marker); // 将标注添加到地图中
            		}
        		} 
            	for (var i = 0; i < list2.length; i ++) {
            		if(!(list2[i].lng == 0 && list2[i].lat == 0)){
	        			var pt1 = new BMap.Point(list2[i].lng, list2[i].lat);
	        			var myIcon1 = new BMap.Icon("/admin/static/image/img3.png", new BMap.Size(40,40));
	        			var marker = new BMap.Marker(pt1,{icon:myIcon1});  // 创建标注
	        			var label = new BMap.Label("3");
	        			label.setStyle({ 
	        				backgroundColor :"0",
	        				border :"0", 
	        				opacity: 0,
	        			});
	        			marker.setLabel(label);
	        			marker3[marker3.length] = marker;
	        			map.addOverlay(marker); // 将标注添加到地图中
            		}
        		} 
            	for (var i = 0; i < list3.length; i ++) {
            		if(!(list3[i].lng == 0 && list3[i].lat == 0)){
	        			var pt1 = new BMap.Point(list3[i].lng, list3[i].lat);
	        			var myIcon1 = new BMap.Icon("/admin/static/image/img4.png", new BMap.Size(40,40));
	        			var marker = new BMap.Marker(pt1,{icon:myIcon1});  // 创建标注
	        			var label = new BMap.Label("4");
	        			label.setStyle({ 
	        				backgroundColor :"0",
	        				border :"0", 
	        				opacity: 0,
	        			});
	        			marker.setLabel(label);
	        			marker4[marker4.length] = marker;
	        			map.addOverlay(marker); // 将标注添加到地图中
            		}
        		} 
            	var cr = new BMap.CopyrightControl({anchor: BMAP_ANCHOR_TOP_LEFT});   //设置版权控件位置
        		map.addControl(cr); //添加版权控件

        		var bs = map.getBounds();   //返回地图可视区域
        		cr.addCopyright({id: 1, content: "<div style=\"position: absolute;"+
        			"    top: 30px;"+
        			"    left: 30px;"+
        			"    width: 400px;"+
        			"    text-align: center;"+
        			"    height: 60px;"+
        			"    border: 1px solid #ccc;"+
        			"    padding: 8px;"+
        			"    background: #fff;"+
        			"    line-height: 22px;opacity: 0.7 !important;border: 2px solid;border-radius: 10px;z-index: 9;\">"+
        			"        <div class=\"row\">"+
        			"            <div class=\"col-lg-3\" style=\"color: #FF0000;padding-left:10px;float:left;\" id=\"point1\">"+
        			"                <div>"+list0.length+"</div>"+
        			"                <div>商户(有效)</div>"+
        			"            </div>"+
        			"            <div class=\"col-lg-3\" style=\"color: #0000FF;padding-left:10px;float:left;\" id=\"point2\">"+
        			"                <div>"+list1.length+"</div>"+
        			"                <div>商户(失效)</div>"+
        			"            </div>"+
        			"            <div class=\"col-lg-3\" style=\"color: #28a745;padding-left:10px;float:left;\" id=\"point3\">"+
        			"                <div>"+list2.length+"</div>"+
        			"                <div>新盘</div>"+
        			"            </div>"+
        			"            <div class=\"col-lg-3\" style=\"color: #ffc107;padding-left:10px;float:left;\" id=\"point4\">"+
        			"                <div>"+list3.length+"</div>"+
        			"                <div>小区</div>"+
        			"            </div>"+
        			"        </div>"+
        			"    </div>", bounds: bs});  
        		

    			/* "        <div style=\"position: relative; left: 0px;\">"+
    			"            <div class=\"clearfix lh-38\">"+
    			"                <div class=\"float-left mr-1\">深圳</div>"+
    			"                <div class=\"float-left\">"+
    			"                    <input type=\"text\" class=\"form-control input-sm\" placeholder=\"小区/新盘/商户\">"+
    			"                </div>"+
    			"            </div>"+
    			"        </div>"+ */

            }  
        }); 

		map.enableInertialDragging();

		map.enableContinuousZoom();

		var size = new BMap.Size(260, 55);
		map.addControl(new BMap.CityListControl({
		    anchor: BMAP_ANCHOR_TOP_LEFT,
		    offset: size,
		    // 切换城市之间事件
		    // onChangeBefore: function(){
		    //    alert('before');
		    // },
		    // 切换城市之后事件
		    // onChangeAfter:function(){
		    //   alert('after');
		    // }
		}));
		$(function(){
			$('#point1').click(function(){
				if($(this).attr("click") == "click"){
					$(this).attr("click","noclick");
					$(this).css("color","#FF0000");
					showMarker("1");
				}else{
					$(this).attr("click","click");
					$(this).css("color","#8B8989");
					hideMarker("1");
				}
			});
			$('#point2').click(function(){
				if($(this).attr("click") == "click"){
					$(this).attr("click","noclick");
					$(this).css("color","#0000FF");
					showMarker("2");
				}else{
					$(this).attr("click","click");
					$(this).css("color","#8B8989");
					hideMarker("2");
				}
			});
			$('#point3').click(function(){
				if($(this).attr("click") == "click"){
					$(this).attr("click","noclick");
					$(this).css("color","#28a745");
					showMarker("3");
				}else{
					$(this).attr("click","click");
					$(this).css("color","#8B8989");
					hideMarker("3");
				}
			});
			$('#point4').click(function(){
				if($(this).attr("click") == "click"){
					$(this).attr("click","noclick");
					$(this).css("color","#ffc107");
					showMarker("4");
				}else{
					$(this).attr("click","click");
					$(this).css("color","#8B8989");
					hideMarker("4");
				}
			});
		})
		
		function show(id){
			$.ajax({  
	            type : "POST",  //提交方式  
	            url : "/admin/baiduMap/index/getTopLngLatById",//路径  
	            data : {id:id},//数据，这里使用的是Json格式进行传输  
	            dataType:"json",
	            async:false, 
	            success : function(result) {//返回数据根据结果进行相应的处理  
	            	var list = result.data;
	            	for (var i = 0; i < list.length; i ++) {
	            		if(!(list[i].lng == 0 && list[i].lat == 0)){
		        			var pt1 = new BMap.Point(list[i].lng, list[i].lat);
		        			var myIcon1 = new BMap.Icon("/admin/static/image/img"+id+".png", new BMap.Size(40,40));
		        			var marker1 = new BMap.Marker(pt1,{icon:myIcon1});  // 创建标注
		        			var label = new BMap.Label(id);
		        			label.setStyle({ 
		        				backgroundColor :"0",
		        				border :"0", 
		        				opacity: 0,
		        			});
		        			
		        			marker1.setLabel(label);
		        			
		        			if(id == 1){
			        			marker1.push(marker1);
		        			}else if(id == 2){
			        			marker2.push(marker1);
		        			}else if(id == 3){
			        			marker3.push(marker1);
		        			}else if(id == 4){
			        			marker4.push(marker1);
		        			}
		        			map.addOverlay(marker1); // 将标注添加到地图中
	            		}
	        		} 
	            }  
	        }); 
		}
		function showMarker(id){
			if(id == 1){
				for (var i = 0; i < marker1.length; i++){
					marker1[i].show(); 
				}
			}else if(id == 2){
				for (var i = 0; i < marker2.length; i++){
					marker2[i].show(); 
				}
			}else if(id == 3){
				for (var i = 0; i < marker3.length; i++){
					marker3[i].show(); 
				}
			}else if(id == 4){
				for (var i = 0; i < marker4.length; i++){
					marker4[i].show(); 
				}
			}
			
		}
		function hideMarker(id){
			if(id == 1){
				for (var i = 0; i < marker1.length; i++){
					marker1[i].hide(); 
				}
			}else if(id == 2){
				for (var i = 0; i < marker2.length; i++){
					marker2[i].hide(); 
				}
			}else if(id == 3){
				for (var i = 0; i < marker3.length; i++){
					marker3[i].hide(); 
				}
			}else if(id == 4){
				for (var i = 0; i < marker4.length; i++){
					marker4[i].hide(); 
				}
			}
		}
	</script>
</html>