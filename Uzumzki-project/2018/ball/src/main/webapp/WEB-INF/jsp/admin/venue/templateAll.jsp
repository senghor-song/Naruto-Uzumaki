<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="/WebBackAPI/admin/static/css/site.css" rel="stylesheet">
    <title>小易运维</title>
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
    </table>
    
	<div class="contextMenuDialog">
		<div class="row">
	    	<div class="card-body col-lg-6">
				<div class="row">
					<div class="input-group input-group-sm float-right">
						<select class="form-control float-right" id="venueSelectType">
							<option value="0">城市</option>
							<option value="1">场馆名</option>
						</select> 
						<input class="form-control float-right" id="venueKeyword"
							name="table_search" type="text" placeholder="请输入关键字"
							maxlength="20">
						<div class="input-group-append">
							<button class="btn btn-default" id="venueSearch" type="submit">
								<i class="fa fa-search"></i> 搜索
							</button>
						</div>
					</div>
				</div>
				<div class="row">
		            <table id="tableVenue"></table>
		        </div>
	        </div>
	        
            <div class="card-body col-lg-1">
                <button class="form-control btn btn-primary" id="saveVenue" 
                	style=" background-color: #a8acb1 !important;
				    border-color: #a8acb1 !important;
				    height:  100%;
				    width:  100%; ">>>>>></br>批量执行</br>>>>>></button>
            </div>
            <div class="card-body col-lg-5">
	            <div class="return" style="
	            	border: 1px solid #a8acb1;
				    height:  100%;
				    width:  100%; 
				    border-radius: .25rem;">
	            	执行明细打印，不保存，关闭即清除
	            </div>
            </div>
	    </div>
	</div>
    
	<script src="/WebBackAPI/admin/static/plugins/jquery/jquery.min.js"></script>
	<script src="/WebBackAPI/admin/static/js/layout.js"></script>
	<script src="/WebBackAPI/admin/static/plugins/jQueryUI/jquery-ui.min.js"></script>
	<script>
	    $.widget.bridge('uibutton', $.ui.button)
	</script>
	<script src="/WebBackAPI/admin/static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="/WebBackAPI/admin/static/js/raphael-min.js"></script>
	<script src="/WebBackAPI/admin/static/js/echarts.min.js"></script>
	<script src="/WebBackAPI/admin/static/js/jqBootstrapValidation-1.3.7.min.js"></script>
	<script src="/WebBackAPI/admin/static/plugins/layui/layui.all.js"></script>
	<script src="/WebBackAPI/admin/static/plugins/jQuery-contextMenu/jquery.contextMenu.min.js"></script>
	<script src="/WebBackAPI/admin/static/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script src="/WebBackAPI/admin/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script src="/WebBackAPI/admin/static/js/jq-ext.js"></script>
	<script type="text/javascript">
		$(function() {
			var tableObj = {
				obj : null,
				init : function() {
					var self = this;
					self.tableList();
					self.search();
				},
				search : function() {
					var self = this;
					$("#venueSearch").unbind().on("click", function() {
						$.reload(self.obj);
					});
				},
				tableList : function() {
					var self = this;
					self.obj = $.tableObject({
						tableId : 'tableVenue',
						tableOption : {
							url : '/WebBackAPI/admin/venue/venueList',
							page : false,
							height : $(window).height() - 210,
							where : {
								selectType : function() {
									return $("#venueSelectType").val()
								},
								keyword : function() {
									return $("#venueKeyword").val()
								}
							},
							cols : [ [ {
								field : 'id',
								title : 'id',
								hide : true
							}, {
								field : 'id',
								title : '',
								type : "checkbox"
							}, {
								field : 'city',
								title : '城市',
								width : 100
							}, {
								field : 'name',
								title : '场馆'
							}, ] ]
						}
					});
				}
			}
			tableObj.init();
			var venues = [];
			var venueAll = 0;
			layui.table.on('checkbox(tableVenue)', function(obj) {
				if (obj.type == 'all') {
					if(obj.checked){
						venueAll = 1;
					}else{
						venueAll = 0;
					}
				} else if (obj.type == 'one') {
					if(obj.checked){
						venues.push(obj.data.id);
					}else{
						venues = venues.filter(function(e){
							return e != obj.data.id;
						});
					}
				}
				console.log(obj.checked); //当前是否选中状态
				console.log(obj.data.id); //选中行的相关数据
				console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
			});
			$("#saveVenue").click(function() {
				var selectType = $("#venueSelectType").val()
				var keyword = $("#venueKeyword").val()
				var priceArr = new Array();
		        $('.cell-input').each(function(){
		            priceArr.push($(this).val());//添加至数组
		        });
				$.ajax({
					type : "POST", //提交方式  
					url : "/WebBackAPI/admin/venue/saveVenueTemplateAll",//路径  
					data : {
						venues : venues,
						venueAll : venueAll,
						selectType : selectType,
						keyword : keyword,
						priceArr : priceArr
					},//数据，这里使用的是Json格式进行传输 
					dataType : "json",
					success : function(result) {//返回数据根据结果进行相应的处理  
						if (result.code == 200) {
							layer.msg("设置成功");
							$('.return').html(result.data);
						} else {
							layer.confirm(result.msg, {
								btn : [ '确定' ]
							//按钮
							});
						}
					}
				});
			});
		});
	</script>
</body>

</html>