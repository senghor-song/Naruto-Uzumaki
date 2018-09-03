<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>后台管理系统</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/WebRelease/admin/static/css/site.css" rel="stylesheet">
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom" id="topSidebar"></nav>
    <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="15"></aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper pt-3">

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card card-secondary">
                            <!--<div class="card-header">
                                <h3 class="card-title">
                                    <div class="row">
                                        <div class="col-lg-4 btncaozuo">
                                            <button class="btn btn-primary btn-sm" id="importData">新增</button>
                                        </div>
                                    </div>
                                </h3>
                                <div class="card-tools">
                                    <div class="input-group input-group-sm" style="width: 250px;">
                                        <input class="form-control float-right" id="keyword" name="table_search" type="text" placeholder>
                                        <div class="input-group-append">
                                            <button class="btn btn-default" id="btnSearch" type="submit">
                                                <i class="fa fa-search"></i> 搜索
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>-->

                            <div class="card-body table-responsive p-0">
                                <table id="tableList"></table>
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
<script src="/WebRelease/admin/static/plugins/jquery/jquery.min.js"></script>
<script src="/WebRelease/admin/static/js/layout.js"></script>
<script src="/WebRelease/admin/static/plugins/jQueryUI/jquery-ui.min.js"></script>
<script>
    $.widget.bridge('uibutton', $.ui.button)
</script>
<script src="/WebRelease/admin/static/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/WebRelease/admin/static/js/raphael-min.js"></script>
<script src="/WebRelease/admin/static/plugins/morris/morris.min.js"></script>
<script src="/WebRelease/admin/static/plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="/WebRelease/admin/static/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="/WebRelease/admin/static/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="/WebRelease/admin/static/plugins/knob/jquery.knob.js"></script>
<script src="/WebRelease/admin/static/plugins/moment/moment.min.js"></script>
<script src="/WebRelease/admin/static/plugins/daterangepicker/daterangepicker.js"></script>
<script src="/WebRelease/admin/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/WebRelease/admin/static/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script src="/WebRelease/admin/static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="/WebRelease/admin/static/plugins/fastclick/fastclick.js"></script>
<script src="/WebRelease/admin/static/plugins/js/pages/dashboard.js"></script>
<script src="/WebRelease/admin/static/plugins/js/demo.js"></script>
<script src="/WebRelease/admin/static/js/echarts.min.js"></script>
<script src="/WebRelease/admin/static/js/jqBootstrapValidation-1.3.7.min.js"></script>
<script src="/WebRelease/admin/static/plugins/layui/layui.all.js"></script>
<script src="/WebRelease/admin/static/plugins/jQuery-contextMenu/jquery.contextMenu.min.js"></script>
<script src="/WebRelease/admin/static/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/WebRelease/admin/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/WebRelease/admin/static/js/jq-ext.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=${key}"></script>
<script>
	var map=null;
	var mapmarker=null;
	
	function leftClick(self){
        $("#area").removeClass("hide");
        $("#areabutton").removeClass("hide");
        $(".tableCity2").removeClass("hide");
        self.parent().find('.selectMenuBg').removeClass('selectMenuBg');
        self.addClass("selectMenuBg");
        $("#districtId").val(dataList2[self.attr('data-index')].districtId);
        showArea();
        
		if(mapmarker != null){
	        map.removeOverlay(mapmarker);
		}
		
        if(dataList2[self.attr('data-index')].longitudeAndLatitude == '有'){
        	var point = new BMap.Point(dataList2[self.attr('data-index')].lng, dataList2[self.attr('data-index')].lat);
      		map.centerAndZoom(point, 16);
      		var marker = new BMap.Marker(point);// 创建标注
      		mapmarker = marker;
      		map.addOverlay(marker);// 将标注添加到地图中
        }else{
        	layer.msg("暂无地址信息");
        }
	}
	function leftClickSetAreaId(self){
        $("#areaId").val(dataList3[self.attr('data-index')].areaid);
        self.parent().find('.selectMenuBg').removeClass('selectMenuBg');
        self.addClass("selectMenuBg");
        
        if(mapmarker != null){
	        map.removeOverlay(mapmarker);
		}
		
        if(dataList3[self.attr('data-index')].longitudeAndLatitude == '有'){
        	var point = new BMap.Point(dataList3[self.attr('data-index')].lng, dataList3[self.attr('data-index')].lat);
      		map.centerAndZoom(point, 19);
      		var marker = new BMap.Marker(point);// 创建标注
      		mapmarker = marker;
      		map.addOverlay(marker);// 将标注添加到地图中
        }else{
        	layer.msg("暂无地址信息");
        }
	}
	
	function showDistrict(){
		$("#area").addClass("hide");
        $("#areabutton").addClass("hide");
        $(".tableCity2").addClass("hide");
        $.tableObject({
            tableId: 'tableCity1',
            tableOption: {
                url: '/WebRelease/admin/city/cityDistrictList?cityId='+$("#cityId").val(),
                page: true,
                height: $("#tableCity1").parents(".layui-layer-content").height() - 80,
                where: {},
                cols: [
                    [
                        {field: 'district', title: '县区', sort: true},
                        {field: 'areaCount', title: '下辖片区', sort: true},
                        {field: 'longitudeAndLatitude', title: '范围', sort: true},
                    ]
                ]
            }
        });
	}

	function showArea(){
    	$.tableObject({
            tableId: 'tableCity2',
            tableOption: {
                url: '/WebRelease/admin/city/cityAreaList?districtId='+$("#districtId").val(),
                page: true,
                height: $("#tableCity2").parents(".layui-layer-content").height() - 80,
                where: {},
                cols: [
                    [
                        {field: 'area', title: '片区', sort: true},
                        {field: 'longitudeAndLatitude', title: '范围', sort: true},
                    ]
                ]
            }
        });
	}
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
                $("#btnSearch").unbind().on("click", function () {
                    $.reload(self.obj);
                });
            },
            tableList: function () {
                var self = this;
                self.obj = $.tableObject({
                    tableId: 'tableList',
                    tableOption: {
                        url: '/WebRelease/admin/city/list',
                        page: true,
                        height: $(window).height() - 92,
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
                                {field: 'cityname', title: '城市', sort: true},
                                {field: 'flagmap', title: '地图', sort: true},
                                {field: 'districtCount', title: '区县', sort: true},
                                {field: 'districtScope', title: '区县范围', sort: true},
                                {field: 'areaCount', title: '片区', sort: true},
                                {field: 'areaScope', title: '片区范围', sort: true},
                                {field: 'cityLog', title: '日志', sort: true},
                                {field: 'empScoreAvg', title: '经纪人均分', sort: true},
                            ]
                        ]
                    },
                    menuItem: {
                        item0: {
                            name: "区域", callback: function (key, opt) {
                                $.showContentMenuPer(key, opt, '49%');
                                $("#item1Img").removeClass("hide");
                                layer.open({
                               	  title: "位置", 
								  type: 1,
								  anim: 1,
								  shade: 0,
								  closeBtn: 0,
								  offset: 't',
								  move: false,
								  resize: false,
								  area: ['100%', '49%'],
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
								})
                                $("#cityId").val(dataList[$(this).attr('data-index')].cityid);
                                showDistrict();
                            }
                        },
                        item1: {
                            name: "日志", callback: function (key, opt) {
                                $.showContentMenu(key, opt);
                                $.tableObject({
                                    tableId: 'tablelog',
                                    tableOption: {
                                        url: '/WebRelease/admin/city/cityLogList?cityId='+dataList[$(this).attr('data-index')].cityid,
                                        page: true,
                                        height: $("#tablelog").parents(".layui-layer-content").height() - 35,
                                        where: {},
                                        cols: [
                                            [
                                                {field: 'logTime', title: '日志时间', sort: true},
                                                {field: 'content', title: '内容', sort: true},
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                        /*item2: {
                            name: "竟品", callback: function (key, opt) {
                                $.showContentMenu(key, opt)

                                $.tableObject({
                                    tableId: 'tablejplist',
                                    tableOption: {
                                        url: 'data/data.html',
                                        page: true,
                                        height: $("#tablejplist").parents(".layui-layer-content").height() - 125,
                                        where: {},
                                        cols: [
                                            [
                                                {field: 'aa', title: '日志时间', sort: true},
                                                {field: 'bb', title: '内容', sort: true},
                                                {field: 'cc', title: '备注', sort: true},
                                            ]
                                        ]
                                    }
                                });
                            }
                        },*/
                       /* item3: {
                            name: "测绘", callback: function (key, opt) {
                                $.showContentMenu(key, opt, 700)

                            }
                        },*/
                    }
                });
            }
        }
        tableObj.init();

        $("#importData").on("click", function () {
            var obj = $("#dataContent");
            obj.removeClass("hide");
            layer.open({
                type: 1,
                area: ['100%', '500px'],
                offset: 'b',
                title: '新增',
                resize: true,
                anim: 1,
                maxmin: true,
                content: obj,
                cancel: function (index, layero) {
                    $(".contextMenuDialog").addClass("hide");
                }
            });

            $.tableObject({
                tableId: 'tablejp',
                tableOption: {
                    url: 'data/data.html',
                    page: true,
                    height: $("#tablejp").parents(".layui-layer-content").height() - 125,
                    where: {},
                    cols: [
                        [
                            {field: 'aa', title: '日志时间', sort: true},
                            {field: 'bb', title: '内容', sort: true},
                            {field: 'cc', title: '备注', sort: true},
                        ]
                    ]
                }
            });
        });
        //setTimeout(() => { $.showContentMenuDemo("item1", '新盘动态', 500) }, 0);
    });
</script>

<div class="contextMenuDialog hide" id="dataContent">
    <div class="card-body">
        <div class="row">
            <div class="input-group col-lg-6">
                <div class="row w-100">
                    <div class="col-lg-6">
                        <div class="row">
                            <div class="col-lg-3 lh-38">竞品</div>
                            <div class="col-lg-9">
                                <input type="text" class="form-control ">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="row">
                            <div class="col-lg-3 lh-38">小区信息</div>
                            <div class="col-lg-9">
                                <select name="" class="form-control" id="">
                                    <option value="">小区多，有附加信息</option>
                                    <option value="">小区少，没附加信息</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="row">
                            <div class="col-lg-3 lh-38">路径</div>
                            <div class="col-lg-9">
                                <input type="text" class="form-control ">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="row">
                            <div class="col-lg-3 lh-38">经纪人信息</div>
                            <div class="col-lg-9">
                                <select name="" class="form-control" id="">
                                    <option value="">人数少，有电话</option>
                                    <option value="">人数多，无电话</option>
                                </select>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="col-lg-6">
                <div class="row">
                    <div class="col-lg-6">
                        <textarea name="" class="form-control" id="" cols="30" rows="2" placeholder="备注"></textarea>
                    </div>
                    <div class="col-lg-6">
                        <button class="form-control btn btn-primary w-25">保存</button>
                    </div>
                </div>

            </div>
        </div>
        <br>
        <div class="row">
            <table id="tablejp"></table>
        </div>
    </div>
</div>
<div id="item1Img" class="otherDialog hide">
    <div class="row">
        <div class="col-12" id="citymap" style="width:100%;height: 100%;">
        </div>
    </div>
</div>
<div class="contextMenuDialog hide" id="item0">
    <div class="card-body">

        <div class="row mb-2">
            <div class="col-1 text-right pt-2">区县</div>
            <div class="col-4">
                <input class="form-control float-left mr-2 w-50" type="text" placeholder="输入区县名添加" id="districtName" maxlength="30">
                <button class="btn btn-primary float-left mr-2" onclick="addDistrict()">添加</button>
                <input id="cityId" type="hidden" value="">
                <input id="districtId" type="hidden" value="">
                <button class="btn btn-danger float-left" onclick="delDistrict()">删除</button>
            </div>
            <div class="col-2 text-right pt-2 hide" id="area">片区</div>
            <div class="col-4 hide"  id="areabutton">
                <input class="form-control float-left mr-2 w-50" type="text" placeholder="输入小区名添加" id="areaName" maxlength="30">
                <button class="btn btn-primary float-left mr-2" onclick="addArea()">添加</button>
                <input id="areaId" type="hidden" value="">
                <button class="btn btn-danger float-left" onclick="delArea()">删除</button>
            </div>
        </div>
        <div class="row">
            <div class="col-6"><table id="tableCity1"></table></div>
            <div class="col-6"><table id="tableCity2"></table></div>
        </div>
    </div>
</div>

<div class="contextMenuDialog hide" id="item1">
    <div class="card-body">
        <table id="tablelog"></table>
    </div>
</div>

<div class="contextMenuDialog hide" id="item2">
    <div class="card-body">
        <div class="row">
            <div class="input-group col-lg-6">
                <div class="row w-100">
                    <div class="col-lg-6">
                        <div class="row">
                            <div class="col-lg-3 lh-38">竞品</div>
                            <div class="col-lg-9">
                                <input type="text" class="form-control ">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="row">
                            <div class="col-lg-3 lh-38">小区信息</div>
                            <div class="col-lg-9">
                                <select name="" class="form-control" id="">
                                    <option value="">小区多，有附加信息</option>
                                    <option value="">小区少，没附加信息</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="row">
                            <div class="col-lg-3 lh-38">路径</div>
                            <div class="col-lg-9">
                                <input type="text" class="form-control ">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="row">
                            <div class="col-lg-3 lh-38">经纪人信息</div>
                            <div class="col-lg-9">
                                <select name="" class="form-control" id="">
                                    <option value="">人数少，有电话</option>
                                    <option value="">人数多，无电话</option>
                                </select>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="col-lg-6">
                <div class="row">
                    <div class="col-lg-6">
                        <textarea name="" class="form-control" id="" cols="30" rows="2" placeholder="备注"></textarea>
                    </div>
                    <div class="col-lg-6">
                        <button class="form-control btn btn-primary w-25">保存</button>
                    </div>
                </div>

            </div>
        </div>
        <br>
        <div class="row">
            <table id="tablejplist"></table>
        </div>
    </div>
</div>

<div class="contextMenuDialog hide" id="item3">
    <div class="card-body">
        <img src="/WebRelease/admin/static/image/u53679_state0.jpg" alt="" style="width: 100%; height: 620px;">
    </div>
</div>
</body>

<script type="text/javascript">
	function addDistrict(){
		var cityId = $("#cityId").val();
		var districtName = $("#districtName").val();
		if(districtName == ""){
			layer.msg("请输入区县名称");
			return;
		}
		$.ajax({  
            type : "POST",  //提交方式  
            url : "/WebRelease/admin/city/addDistrict",//路径  
            data : {
            	cityId:cityId,
            	districtName:districtName
            },//数据，这里使用的是Json格式进行传输  
            dataType: "json",
            success : function(result) {//返回数据根据结果进行相应的处理
            	if(result.code == 200){
        			layer.msg("添加成功");
                    showDistrict();
            	}else{
        			layer.msg("添加失败");
            	}
            }
        });
	}
	function delDistrict(){
		var districtId = $("#districtId").val();
		if(districtId == ""){
			layer.msg("请选择要删除的区县");
			return;
		}
		$.ajax({  
            type : "POST",  //提交方式  
            url : "/WebRelease/admin/city/delDistrict",//路径  
            data : {
            	districtId : districtId
            },//数据，这里使用的是Json格式进行传输  
            dataType: "json",
            success : function(result) {//返回数据根据结果进行相应的处理
            	if(result.code == 200){
        			layer.msg("删除成功");
        	        $("#districtId").val("");
                    showDistrict();
            	}else{
        			layer.msg("删除失败");
            	}
            }
        });
	}
	function addArea(){
		var districtId = $("#districtId").val();
		var areaName = $("#areaName").val();
		if(areaName == ""){
			layer.msg("请输入片区名称");
			return;
		}
		if(districtId == ""){
			layer.msg("请选择要添加片区的区县");
			return;
		}
		$.ajax({  
            type : "POST",  //提交方式  
            url : "/WebRelease/admin/city/addArea",//路径  
            data : {
            	districtId : districtId,
            	areaName : areaName
            },//数据，这里使用的是Json格式进行传输  
            dataType: "json",
            success : function(result) {//返回数据根据结果进行相应的处理
            	if(result.code == 200){
        			layer.msg("添加成功");
        	        showArea();
            	}else{
        			layer.msg("添加失败");
            	}
            }
        });
		
	}
	function delArea(){
		var areaId = $("#areaId").val();
		if(areaId == ""){
			layer.msg("请选择要删除的片区");
			return;
		}
		$.ajax({  
            type : "POST",  //提交方式  
            url : "/WebRelease/admin/city/delArea",//路径  
            data : {
            	areaId : areaId
            },//数据，这里使用的是Json格式进行传输  
            dataType: "json",
            success : function(result) {//返回数据根据结果进行相应的处理
            	if(result.code == 200){
        			layer.msg("删除成功");
        	        $("#areaId").val("");
        	        showArea();
            	}else{
        			layer.msg("删除失败");
            	}
            }
        });
	}
</script>

</html>