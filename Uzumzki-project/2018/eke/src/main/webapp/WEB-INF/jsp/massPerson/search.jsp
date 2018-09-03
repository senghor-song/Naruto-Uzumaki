<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>  
  <head>
    <title>常用搜索记录</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/personHouse_f486f899a5f59ffda462616b5cedc6a4.css' rel='stylesheet' type='text/css'></link>
	<style>
	#areaList{height: 26px;line-height: 26px;width: 300px;border: 1px solid #64ac58;border-radius: 3px;}
	.collectSearch >ul >li{clear:both}
	.bP_district li{float:left;}
	.bP_district{left:60px;}
	</style>
  </head>
<body >
<div class="collectSearch" style='height:auto;'>
		<ul class=''>
		<li>
			<span class="collectSearch-left left-span">区域:</span>
			<div id="cs_district" class="distr_list" onchange="getCsAreaList()">
				<c:forEach var="district" items="${districts }">
					<input type="checkbox" name="district"  value="${district.id}"/><span>${district.district} </span>
				</c:forEach>
				<!-- <input type="checkbox" name="district"  value="75502"/><span>福田 </span>
					<input type="checkbox" name="district"  value="75503"/><span>南山 </span>
					<input type="checkbox" name="district"  value="75504"/><span>盐田 </span>
					<input type="checkbox" name="district"  value="75505"/><span>宝安 </span>
					<input type="checkbox" name="district"  value="75506"/><span>龙岗区 </span>
					<input type="checkbox" name="district"  value="75507"/><span>布吉 </span>
					<input type="checkbox" name="district"  value="75508"/><span>坪山新区 </span>
					<input type="checkbox" name="district"  value="75509"/><span>光明新区 </span>
					<input type="checkbox" name="district"  value="75510"/><span>龙华新区 </span>
					<input type="checkbox" name="district"  value="75511"/><span>大鹏新区 </span>
					<input type="checkbox" name="district"  value="75512"/><span>深圳周边 </span> -->
			</div>
		</li>
		<li class="js-Section">
			<span class="collectSearch-left left-span">路段:</span>
			<input type="text" id="areaList" value="" name="areaList" readonly="true" data-did=""></input>
              <div class="xms-relative">
             	<div class="bP_district bP_areaList">
	             	<i class="tip-img"></i>
		            <ul>
		             </ul>
             	</div>
            </div> 
		</li>
		<li>
			<span class="collectSearch-left left-span">网站:</span>
			<div id="cs_website" class="distr_list"  style="height:32px;">
							<input type="checkbox"  value="58同城"/><span>58同城 </span>
							<input type="checkbox"  value="第一时间房产网"/><span>第一时间房产网 </span>
							<input type="checkbox"  value="赶集网"/><span>赶集网 </span>
							<input type="checkbox"  value="今题网"/><span>今题网 </span>
							<input type="checkbox"  value="列表网"/><span>列表网 </span>
			</div>
		</li>
		<li>
			<span class="collectSearch-left left-span">类型:</span>
			<span><input type="radio" id="houseType1" name="cs_houseType" value="" checked="checked"></input><label for="houseType1">不限</label></span>
			<span><input type="radio" id="houseType2" name="cs_houseType" value="住宅"></input><label for="houseType2">住宅</label></span>
			<span><input type="radio" id="houseType3" name="cs_houseType" value="别墅"></input><label for="houseType3">别墅</label></span>
			<span><input type="radio" id="houseType4" name="cs_houseType" value="店面"></input><label for="houseType4">店面</label></span>
			<span><input type="radio" id="houseType5" name="cs_houseType" value="写字楼"></input><label for="houseType5">写字楼</label></span>
			<span><input type="radio" id="houseType6" name="cs_houseType" value="厂房"></input><label for="houseType6">厂房</label></span>
		</li>
		<li>
			<span class="collectSearch-left left-span">户型:</span>
			<span><input type="radio" id="room1" name="cs_room" value="" checked="checked"></input><label for="room1">不限</label></span>
			<span><input type="radio" id="room2" name="cs_room" value="1"></input><label for="room2">一居</label></span>
			<span><input type="radio" id="room3" name="cs_room" value="2"></input><label for="room3">二居</label></span>
			<span><input type="radio" id="room4" name="cs_room" value="3"></input><label for="room4">三居</label></span>
			<span><input type="radio" id="room5" name="cs_room" value="4"></input><label for="room5">四居</label></span>
			<span><input type="radio" id="room6" name="cs_room" value="5"></input><label for="room6">五居</label></span>
		</li>
		<li>
			<span class="collectSearch-left">价格:</span>
			<span><input id="cs_price1" type="text" class="personHouse-text iw48" value=""></input>- <input id="cs_price2" type="text" class="personHouse-text iw48" value=""></input></span>
					万元
		</li>
 		<li> 
 			<span class="collectSearch-left">面积:</span> 
			<span> 
				<input id="minHouseArea" type="text" class="personHouse-text iw48" value=""></input>- 
				<input id="maxHouseArea" type="text" class="personHouse-text iw48" value=""></input> ㎡
			</span> 
 		</li>
		<li>
			<span class="collectSearch-left">关键字:</span>
			<span><input id="cs_key" type="text" class="personHouse-text iw200" value=""></input></span>
		</li>
		<li>
			<span class="collectSearch-left">默认:</span>
			<span><label><input type="radio" name="cs_isDefault" value="1"></input>是</label></span>
			<span><label><input type="radio" name="cs_isDefault" value="0" checked="checked"></input>否</label></span>
		</li>
	</ul>
</div>
<script>
$(function(){
	initPageData();
	$("#areaList").val('');
	$("#areaList").attr("data-did",'');
	$("#areaList").die().live("click", function() {
		$(".bP_areaList").find("li").removeClass("selected");
		var dId = String($("#areaList").attr("data-did"))
		if (dId != '') {
			var ids = dId.split(",");
			$.each(ids, function(i, id){
				$("#areaList_" + id).addClass("selected");
			});
		}
	    $(".bP_areaList").toggle();  
	}); 
	$(".bP_areaList li").die().live("click", function(){
		
		
		if ($(this).hasClass("selected")) {
			$(this).removeClass("selected");
		} else {
			if($(".bP_areaList").find(".selected").length>=5){
				art.dialog.alert("最多选择5个");
				return false;
			}
			$(this).addClass("selected");
		}
		
		var objs = $(".bP_areaList").find("li.selected");
		var districtName = '';
		var districtId = '';
		$.each(objs, function(i, obj){
			districtName += "," + $(obj).html();
			districtId += "," + $(obj).data("did");
		});
		$("#areaList").attr("data-did",districtId.substring(1))
		$("#areaList").val(districtName.substring(1));
	});
	$(".bP_areaList").die().live("mouseleave", function() { 
	    $(".bP_areaList").hide();  
	});  
});

function initPageData(){
	//$("#cs_district").val('');
	if($("#districtNas").val()==""){
		$("#cs_district [value='']").attr("checked",'checked');
	}else{
		var arr=$("#districtNas").val().split(","); 
		$.each(arr, function(key, val){
			$("#cs_district span").each(function(){
				var distext=$(this).text().replace(/\s/g, "");
				if(distext == val){
					$(this).prev().attr("checked","checked");
					 if( $('#cs_district input[type=checkbox]:checked').length >1){
		        		 $(".js-Section").hide();
		        	 }else{
		        		 $(".js-Section").show();
		        	 }
				}
			})
		})
	}
	if($("#webNames").val()==""){
		$("#cs_website [value='']").attr("checked",'checked');
	}else{
		var webarr=$("#webNames").val().split(",");
		$.each(webarr, function(key, val){
			$("#cs_website span").each(function(){
				var webtext=$(this).text().replace(/\s/g, "");
				if(webtext == val){
					$(this).prev().attr("checked","checked");
				}
			})
		})
	}
	$("input[name=cs_houseType][value='']").attr("checked",'checked');
	$("input[name=cs_room][value='']").attr("checked",'checked');
}
</script>
</body>
</html>