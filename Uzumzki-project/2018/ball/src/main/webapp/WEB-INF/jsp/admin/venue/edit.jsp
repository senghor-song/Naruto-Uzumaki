<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
	.contextMenuDialog .listrow{border: none}
</style>
<script src="/admin/static/js/lc_switch.js" type="text/javascript"></script>
<link rel="stylesheet" href="/admin/static/css/lc_switch.css">
<div class="contextMenuDialog" id="item1">
    <div class="card-body">
        <div class="row">
            <div class="input-group col-lg-9">
                <input type="hidden" value="${venueid}" name="id" id="id" >
                <div class="row">
                    <div class="col-lg-4 listrow ">
                        <span class="nti">场馆编号：</span>
                        <span class="ncon" style="width: 60%;">
                            <input class="form-control" type="text" value="${venueno}" name="venueno" id="venueno" readonly="readonly">
                        </span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">场馆：</span>
                        <span class="ncon" style="width: 60%;">
                            <input class="form-control" type="text" value="${name}" name="venueName" id="venueName" placeholder="场馆名称" maxlength="100" readonly="readonly"
                             onmousedown="holdDown()" onmouseup="holdUp()">
                        </span>
                    </div>
                    <div class="col-lg-4 listrow ">
                        <span class="nti">状态：</span>
                        <span class="ncon">
                            <select class="form-control" name="showflag" id="showflag">
                                <option value="2" <c:if test="${showflag eq '2'}">selected="selected"</c:if>>禁用</option>
                                <option value="1" <c:if test="${showflag eq '1'}">selected="selected"</c:if>>正常</option>
                            </select>
                        </span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">入驻机构：</span>
                        <span class="ncon" style="width: 60%;">
                            <input class="form-control" type="text" value="${trainTeamName}" readonly="readonly">
                        </span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">联系人：</span>
                        <span class="ncon" style="width: 60%;">
                            <input class="form-control" type="text" value="${owner}" name=owner id="owner" placeholder="场馆经营者名称" maxlength="50">
                        </span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">场馆地址：</span>
                        <span class="ncon" style="width: 60%;">
                            <input class="form-control" type="text" value="${address}" name="addressEdit" id="addressEdit" placeholder="场馆地址" maxlength="100">
                        </span>
                    </div>
                    <%-- <div class="col-lg-4 listrow">
                        <span class="nti">城市：</span>
                        <span class="ncon">
                            <select class="form-control" name="cityid" id="cityid">
                            	<c:forEach items="${citys}" var="city">
                                	<option value="${city.id }" <c:if test="${city.id eq cityid}">selected="selected"</c:if>>${city.city }</option>
                              	</c:forEach>
                            </select>
                        </span>
                    </div> --%>
                    <div class="col-lg-4 listrow">
                        <span class="nti">订场入口:</span>
                        <span class="ncon">
                            <input type="checkbox" name="check-3" class="lcs_check" <c:if test="${reserveShow == 1}">checked="checked"</c:if> autocomplete="off" id="reserveShow"/>
                        </span>
                        <span class="nti">订场支付短信:</span>
                        <span class="ncon">
                            <input type="checkbox" name="check-3" class="lcs_check" <c:if test="${reservePaySms == 1}">checked="checked"</c:if> autocomplete="off" id="reservePaySms"/>
                        </span>
                    </div>
                    
                    <div class="col-lg-4 listrow">
                        <span class="nti">联系(订场)电话：</span>
                        <span class="ncon" style="width: 60%;">
                            <input class="form-control" type="text" value="${contactPhone}" name="contactPhone" id="contactPhone" maxlength="20" placeholder="联系(订场)电话">
                        </span>
                    </div>
                    <!-- <div class="col-lg-4 listrow">
                        <span class="nti">区县：</span>
                        <span class="ncon">
                            <select class="form-control" name="districtid" id="districtid">
                            </select>
                        </span>
                    </div> -->
                    <div class="col-lg-4 listrow">
                        <span class="nti"><button class="layui-btn" id="getAddress">使用地址</button>：</span>
                        <span class="nti" id="addressStrEdit"></span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">&nbsp;</span>
                        <button class="form-control btn btn-primary w-25" id="saveVenue">保存</button>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">短信通知电话：</span>
                        <span class="ncon" style="width: 60%;">
                            <input class="form-control" type="text" value="${informPhone}" name="informPhone" id="informPhone" maxlength="20" placeholder="短信通知电话">
                        </span>
                    </div>
                    <%-- <div class="col-lg-4 listrow">
                        <span class="nti">入驻状态：</span>
                        <span class="ncon">
                        	<c:if test="${trainAddFlag == 2}">
	                            <select class="form-control" name="trainAddFlag" id="trainAddFlag" disabled="disabled">
	                                <option>入驻</option>
	                            </select>
                        	</c:if>
                        	<c:if test="${trainAddFlag != 2}">
	                            <select class="form-control" name="trainAddFlag" id="trainAddFlag">
	                                <option value="0" <c:if test="${trainAddFlag == 0}">selected="selected"</c:if>>空</option>
	                                <option value="1" <c:if test="${trainAddFlag == 1}">selected="selected"</c:if>>添加</option>
	                            </select>
                        	</c:if>
                        </span>
                    </div> --%>
                    <div class="col-lg-4 listrow">
                        <span class="nti"><button class="layui-btn" id="setAddress">经纬度解析</button>：</span>
                        <span class="ncon" style="width: 60%;">
                            <input class="form-control" type="text" name="lnglat" id="lnglat" maxlength="50" placeholder="114.056386,22.592976">
                        </span>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 text-center">
                <div class="col-lg-12">
                	<img src="${image}" id="headImage"
                     style="width: 200px;height: 200px; border:1px solid #eee;"
                      onerror="javascript:this.src='/admin/static/image/error.png'">
                </div>
                <div class="col-lg-12">
                    <input type="hidden" value="${image}" name="image" id="image">
		            <div class="publicbg-title">
						<button class="layui-btn test" lay-data="{url: '/admin/common/uploadImage'}" name="file">上传图片</button>
						<button class="layui-btn" id="getImage">生成图片</button>
					</div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

	var timeStart, timeEnd, time;//申明全局变量
	
	function getTimeNow()//获取此刻时间
	{
		var now = new Date();
		return now.getTime();
	}
	
	function holdDown()//鼠标按下时触发
	{
		timeStart = getTimeNow();//获取鼠标按下时的时间
		time = setInterval(function()//setInterval会每100毫秒执行一次
		{
			timeEnd = getTimeNow();//也就是每100毫秒获取一次时间
			if (timeEnd - timeStart > 1000)//如果此时检测到的时间与第一次获取的时间差有1000毫秒
			{
				clearInterval(time);//便不再继续重复此函数 （clearInterval取消周期性执行）
        		$('#venueName').removeAttr("readonly");
			}
		}, 100);
	}
	function holdUp() {
		clearInterval(time);//如果按下时间不到1000毫秒便弹起，
	}
	$(function(){
		
		$('input').lc_switch();
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
					$("#image").val(res.data);
					$("#headImage").attr("src", res.data);
				},
				error: function(index, upload){
				    layer.closeAll('loading'); //关闭loading
				}
			})
		});
		
		$("#getImage").click(function () {
			var zoom = map.getZoom();
			var width = map.getSize().width;
			var height = map.getSize().height;
			var lng = map.getCenter().lng;
			var lat = map.getCenter().lat;
            var markerLng = "";
            var markerLat = "";
            if(mapmarker != null){
                markerLng = mapmarker.point.lng;
                markerLat = mapmarker.point.lat;
            }else{
                layer.msg("请先选择地图位置");
                return;
            }

            var urlStr = "https://api.map.baidu.com/staticimage?center="+lng+","+lat+"&markers="+markerLng+","+markerLat+"&zoom="+zoom+"&width=450&height=450";
            $.ajax({
                type : "POST",  //提交方式  
                url : "/admin/common/getImageHttpUrl",//路径  
				data : {
					urlStr : urlStr,
				},//数据，这里使用的是Json格式进行传输 
				dataType : "json",
				success : function(result) {//返回数据根据结果进行相应的处理  
					if (result.code == 200) {
						layer.msg("生成成功");
						$("#image").val(result.data);
						$("#headImage").attr("src", result.data);
					}
				}
			});
		})
		
		$("#getAddress").click(function () {
			if(mapmarker != null){
		        $("#addressEdit").val($("#addressStrEdit").text());
			}else{
				layer.msg("请先选择地图位置");
			}
		})
		$("#setAddress").click(function () {
		    var lnglat = $("#lnglat").val();
		    var lng = null;
            var lat = null;
		    if(lnglat != null && lnglat != ''){
                var lngAndLat = lnglat.split(",");
                lng = lngAndLat[0];
                lat = lngAndLat[1];
            }else {
                layer.msg("请输入经纬度");
                return;
		    }


            if(updateMarker != null){
                map.removeOverlay(updateMarker);
            }
            var point = new BMap.Point(lng, lat);
            venueLng = lng;//存贮点击的经度
            venueLat = lat;//存贮点击的维度
            map.centerAndZoom(point, map.getZoom());
            var myIcon = new BMap.Icon("/admin/static/image/updateVenue.png", new BMap.Size(24,36));
            myIcon.setInfoWindowAnchor(new BMap.Size(12,0));
            var marker = new BMap.Marker(point,{icon:myIcon});// 创建标注
            updateMarker = marker;//存贮marker对象,方便后续点击删除上一次点击的标注
            //marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
            mapmarker = marker;
            map.addOverlay(marker);// 将标注添加到地图中

            // 获取地址对象
            var geoc = new BMap.Geocoder();
            geoc.getLocation(point, function(rs){
                //addressComponents对象可以获取到详细的地址信息
                var addComp = rs.addressComponents;
                var site = addComp.city + ", " + addComp.district;
                cityName = addComp.city;
                districtName = addComp.district;
                $("#addressStrEdit").text(addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber);
            });
        })
		$("#saveVenue").click(function () {
			var id = $('#id').val();
			/* var cityid = $('#cityid option:selected').val() || "";
			var districtid = $('#districtid option:selected').val() || "";
			var cityName = $('#cityid option:selected').text() || "";
			var districtName = $('#districtid option:selected').text() || ""; */
			
			var showflag = $('#showflag').val();
			var venueName = $('#venueName').val();
			var informPhone = $('#informPhone').val();
			var contactPhone = $('#contactPhone').val();
			var owner = $('#owner').val();
			var image = $('#image').val();
			var address = $('#addressEdit').val();
			
			var reserveShow = $("#reserveShow").is(':checked');
			var reserveSms = $("#reserveSms").is(':checked');
			var reservePaySms = $("#reservePaySms").is(':checked');
			if(venueName == ''){
				layer.msg("场馆名称不能为空");
        		return;
			}
			if(address == ''){
				layer.msg("场地地址不能为空");
        		return;
			}
			if(contactPhone == ''){
				layer.msg("联系电话不能为空");
        		return;
			}
			if(informPhone == ''){
				layer.msg("通知电话不能为空");
        		return;
			}
           	$.ajax({
                type : "POST",  //提交方式  
                url : "/admin/venue/update/venue",//路径  
				data : {
					id : id,
					/* cityid : cityid,
					districtid : districtid, */
					showflag : showflag,
					venueName : venueName,
					contactPhone : contactPhone,
					informPhone : informPhone,
					image : image,
					owner : owner,
					reserveShow : reserveShow,
					reserveSms : reserveSms,
					reservePaySms : reservePaySms,
					cityName : cityName,
					districtName : districtName,
					address : address,
					lng : venueLng,
					lat : venueLat
				},//数据，这里使用的是Json格式进行传输 
				dataType : "json",
				success : function(result) {//返回数据根据结果进行相应的处理  
					if (result.code == 200) {
						layer.msg("修改成功");
                  	    layer.closeAll();
	                    $(".contextMenuDialog").addClass("hide");
	        			$.reload(tableObj.obj);
					} else {
						layer.confirm(result.msg, {
							btn : [ '确定' ]
						//按钮
						});
					}
				}
			});
		})
		getDistrict();
		$("#cityid").change(function() {
			getDistrict();
		})

		function getDistrict() {
			var cityid = $('#cityid').val();
			$.ajax({
				type : "POST",
				url : "/admin/city/district/list",
				data : {
					id : cityid
				},
				dataType : "json",
				success : function(result) {
					$("#districtid").empty();
					var list = result.data;
					for (var i = 0; i < list.length; i++) {
						//先创建好select里面的option元素
						var option = document.createElement("option");
						//转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
						$(option).val(list[i].id);
						//给option的text赋值,这就是你点开下拉框能够看到的东西
						$(option).text(list[i].district);
						
						var districtid = "${districtid}";
						if(districtid == list[i].id){
							$(option).attr("selected",true);;
						}
						//获取select 下拉框对象,并将option添加进select
						$('#districtid').append(option);
					}
				}
			});
		}
	})
</script>