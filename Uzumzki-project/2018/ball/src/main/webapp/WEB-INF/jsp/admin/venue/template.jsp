<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<style>
    .cell-input{
        box-sizing: border-box;
        display: block;
        width: 100%;
        margin: 0 auto;
        border: none;
        outline: none;
        text-align: center;
    }
    .template-table{
        border-top: 1px solid;
        border-left: 1px solid;
    }
    .template-table td{
        /* 强制换行 */
        word-break: break-all;  
        /* word-wrap:break-word; */
        /* overflow-wrap: break-word; */
        text-align: center;
        width: 6.25%;
        border-right: 1px solid;
        border-bottom: 1px solid
        /* border: 1px solid; */
    }
    .title{
        font-size:19px;
    }
    .template-table>tbody{
        border: 1px solid;
    }
</style>

<body>
    <table class="template-table" cellspacing="0">
        <tbody>
            <tr class="table-time-group title">
            	<c:forEach items="${listMaps}" var="list" varStatus="" end="15">
	                <td>${list.time}</td>
            	</c:forEach>
            </tr>
            <tr class="table-price-group inputPrice" >
            	<c:forEach items="${listMaps}" var="list" varStatus="" end="15">
                	<td><input type="text" name="price" class="cell-input" placeholder="不可订" value="${list.price}"></td>
            	</c:forEach>
            </tr>
        </tbody>

        <tbody>
            <tr class="table-time-group title">
            	<c:forEach items="${listMaps}" var="list" varStatus="index" begin="16" end="31">
	                <td>${list.time}</td>
            	</c:forEach>
            </tr>
            <tr class="table-price-group inputPrice2">
            	<c:forEach items="${listMaps}" var="list" varStatus="index" begin="16" end="31">
                	<td><input type="text" name="price" class="cell-input" placeholder="不可订" value="${list.price}"></td>
            	</c:forEach>
            </tr>
        </tbody>
        
        <tbody>
            <tr class="table-time-group title">
            	<c:forEach items="${listMaps}" var="list" varStatus="index" begin="32">
	                <td>${list.time}</td>
            	</c:forEach>
            </tr>
            <tr class="table-price-group inputPrice2">
            	<c:forEach items="${listMaps}" var="list" varStatus="index" begin="32">
                	<td><input type="text" name="price" class="cell-input" placeholder="不可订" value="${list.price}"></td>
            	</c:forEach>
            </tr>
        </tbody>
        <div class="">
		    <button class="" id="saveVenueTemplate">保存</button>
		</div>
    </table>
	<script src="/admin/static/plugins/jquery/jquery.min.js"></script>
	<script src="/admin/static/plugins/layui/layui.all.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#saveVenueTemplate").click(function () {
			var priceArr = new Array();
	        $('input').each(function(){
	            priceArr.push($(this).val());//添加至数组
	        });
	        console.log(priceArr);
			var venueid = "${venueid}";
	       	$.ajax({  
	            type : "POST",  //提交方式  
	            url : "/admin/venue/saveVenueTemplate",//路径  
	            data : {priceArr : priceArr, venueid : venueid},//数据，这里使用的是Json格式进行传输 
	            dataType:"json",
	            success : function(result) {//返回数据根据结果进行相应的处理  
	                if ( result.code == 200 ) {  
						layer.msg("修改成功");
	                } else {
	            		layer.confirm(result.msg, {
	            			btn: ['确定'] //按钮
	            		});
	                }
	            }
	        });
		});
	});
	</script>
</body>

</html>