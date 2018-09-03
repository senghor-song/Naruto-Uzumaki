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
    <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="2"></aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper pt-3">
        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card card-secondary">
                            <div class="card-header" style="height: 48px;">
                                <!-- <h3 class="card-title">
                                    <div class="row">
                                        <div class="col-lg-4 btncaozuo">
                                            <button class="btn btn-primary btn-sm" id="importData">申请合作</button>
                                        </div>
                                    </div>
                                </h3> -->
                                <div class="card-tools">
                                    <div class="input-group input-group-sm" style="width: 350px;">
                                        <select class="form-control float-right" id="selectType">
                                            <option value="0">商户</option>
                                            <option value="1">商户编号</option>
                                        </select>
                                        <input class="form-control float-right" id="keyword" name="table_search"
                                               type="text" placeholder>

                                        <div class="input-group-append">
                                            <button class="btn btn-default" id="btnSearch" type="submit">
                                                <i class="fa fa-search"></i> 搜索
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.card-header -->
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
                        url: '/WebRelease/admin/store/list',
                        page: true,
                        height: $(window).height() - 145,
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
                                {field: 'city', title: '城市', sort: true},
                                {field: 'empStore', title: '商户', sort: false},
                                {field: 'status', title: '合作状态', sort: true},
                                {field: 'view1', title: '耗材订单', sort: true},
                                {field: 'propertySum', title: '租售', sort: true},
                                {field: 'empSum', title: '经纪人', sort: false},
                                {field: 'empAvgStore', title: '人均积分', sort: true},
                                {field: 'empStoreLogSum', title: '日志', sort: true},
                                {field: 'empStoreVerify', title: '续期申请', sort: true},
                            ]
                        ]
                    },
                    menuItem: {
                        item1: {
                            name: "查看", callback: function (key, opt) {
                                $.showContentMenuPer(key, opt, '49%');
                                $("#item1Img").removeClass("hide");
                                var empStoreVerifyList = dataList[$(this).attr('data-index')].empStoreVerifyList;
                                var lng = null,lat = null;
                                var mapindex = layer.open({
                               	  title: "位置", 
								  type: 1,
								  anim: 1,
								  shade: 0,
								  closeBtn: 0,
								  offset: 'rt',
								  move: false,
								  resize: false,
								  area: ['40%', '50%'],
								  content: $('#storemap'),
								  success: function(){
									    map = new BMap.Map("storemap");    // 创建Map实例
										map.centerAndZoom(new BMap.Point(114.056386,22.592976), 20);  // 初始化地图,设置中心点坐标和地图级别
										//添加地图类型控件
										map.addControl(new BMap.MapTypeControl({
											mapTypes:[
										        BMAP_NORMAL_MAP,
										        BMAP_HYBRID_MAP
										    ]}));	  
										map.setCurrentCity("深圳");          // 设置地图显示的城市 此项是必须设置的
										map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
										if(mapmarker != null){
									        map.removeOverlay(mapmarker);
										}
										if(empStoreVerifyList.length == 0){
											layer.msg("暂无地址信息");
										}
										debugger;
										for(var i=0 ; i<empStoreVerifyList.length ; i++){
											if(empStoreVerifyList[i].longitudeAndLatitude == '有'){
												lng = empStoreVerifyList[i].lng;
												lat = empStoreVerifyList[i].lat;
									        	var point = new BMap.Point(lng, lat);
									      		map.centerAndZoom(point, 16);
									      		var marker = new BMap.Marker(point);// 创建标注
									      		mapmarker = marker;
									      		var label = new BMap.Label(i, {
									                offset : new BMap.Size(5, 4)
									            }); 
											    label.setStyle({
											    	background:'none',color:'#fff',border:'none'//只要对label样式进行设置就可达到在标注图标上显示数字的效果
											    });
												var marker = new BMap.Marker(point);
											    marker.setLabel(label);
									      		map.addOverlay(marker);// 将标注添加到地图中
									      		var opts = {
								      			  width : 100,     // 信息窗口宽度
								      			  height: 50,     // 信息窗口高度
								      			  title : "时间" , // 信息窗口标题
								      			  enableMessage:true,//设置允许信息窗发送短息
								      			  message:""
								      			}
								      			var infoWindow = new BMap.InfoWindow("2018-12-20", opts);  // 创建信息窗口对象 
								      			marker.addEventListener("click", function(){          
								      				map.openInfoWindow(infoWindow,point); //开启信息窗口
								      			});
									        }
										}
								   }
								})
                                $('.empstore').text(dataList[$(this).attr('data-index')].empStore);
                                $.tableObject({
                                    tableId: 'tableview',
                                    tableOption: {
                                        url: '/WebRelease/admin/store/details?id='+dataList[$(this).attr('data-index')].id,
                                        page: true,
                                        height: $("#tableview").parents(".layui-layer-content").height() - 100,
                                        where: {},
                                        cols: [
                                            [
                                                {field: 'emp', title: '经纪人', sort: true},
                                                {field: 'empno', title: '编号', sort: true},
                                                {field: 'storemanage', title: '是否是管理', sort: true},
                                                {field: 'tel', title: '电话', sort: true},
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                        item2: {
                            name: "商洽记录", callback: function (key, opt) {
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tablelog',
                                    tableOption: {
                                        url: 'data/data.html',
                                        page: true,
                                        height: $("#tablelog").parents(".layui-layer-content").height() - 100,
                                        where: {},
                                        cols: [
                                            [
                                                {field: 'aa', title: '时间', sort: true},
                                                {field: 'bb', title: '类别', sort: true},
                                                {field: 'cc', title: '内容', sort: true},
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                        item3: {
                            name: "续期认证", callback: function (key, opt) {
                                $.showContentMenu(key, opt, 785)

                                $.tableObject({
                                    tableId: 'tableyz1',
                                    tableOption: {
                                        url: 'data/data.html',
                                        page: true,
                                        height: 250,
                                        where: {},
                                        cols: [
                                            [
                                                {field: 'aa', title: '订单时间', sort: true},
                                                {field: 'bb', title: '金额', sort: true},
                                                {field: 'cc', title: '类别', sort: true},
                                                {field: 'dd', title: '经纪人', sort: true},
                                                {field: 'ee', title: '备注', sort: true},
                                            ]
                                        ]
                                    }
                                });

                                $.tableObject({
                                    tableId: 'tableyz2',
                                    tableOption: {
                                        url: 'data/data.html',
                                        page: true,
                                        height: 250,
                                        where: {},
                                        cols: [
                                            [
                                                {field: 'aa', title: '认证日期', sort: true},
                                                {field: 'bb', title: '类别', sort: true},
                                                {field: 'cc', title: '城市', sort: true},
                                                {field: 'dd', title: '商户', sort: true},
                                                {field: 'ee', title: '位置', sort: true},
                                                {field: 'ff', title: '常驻小区', sort: true},
                                                {field: 'gg', title: '商洽', sort: true},
                                                {field: 'hh', title: '门店码', sort: true},
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                        item4: {
                            name: "耗材订单", callback: function (key, opt) {
                                $.showContentMenu(key, opt, 500)
                                $.tableObject({
                                    tableId: 'tablehc1',
                                    tableOption: {
                                        url: 'data/data.html',
                                        page: true,
                                        height: 290,
                                        where: {},
                                        cols: [
                                            [
                                                {field: 'aa', title: '下单时间', sort: true},
                                                {field: 'bb', title: '状态', sort: true},
                                                {field: 'cc', title: '金额', sort: true},
                                                {field: 'dd', title: '商户经纪', sort: true},
                                                {field: 'ee', title: '耗材', sort: true},
                                                {field: 'ff', title: '收货地址', sort: true},
                                            ]
                                        ]
                                    }
                                });
                                $.tableObject({
                                    tableId: 'tablehc2',
                                    tableOption: {
                                        url: 'data/data.html',
                                        page: true,
                                        height: 290,
                                        where: {},
                                        cols: [
                                            [
                                                {field: 'aa', title: '时间', sort: true},
                                                {field: 'bb', title: '内容', sort: true},
                                            ]
                                        ]
                                    }
                                });
                            }
                        }
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
                area: [$(document).width() + 'px', '600px'],
                offset: 'b',
                title: '申请合作',
                resize: true,
                anim: 1,
                maxmin: true,
                content: obj,
                cancel: function (index, layero) {
                    $(".contextMenuDialog").addClass("hide");
                }
            });

            $.tableObject({
                tableId: 'tablehz',
                tableOption: {
                    url: 'data/data.html',
                    page: true,
                    height: $("#tablehz").parents(".layui-layer-content").height() - 150,
                    where: {},
                    cols: [
                        [
                            {field: 'aa', title: '申请时间', sort: true},
                            {field: 'bb', title: '城市', sort: true},
                            {field: 'cc', title: '商户', sort: true},
                            {field: 'dd', title: '申请人', sort: true},
                            {field: 'ee', title: '申请人电话', sort: true},
                            {field: 'ff', title: '备注', sort: true},
                        ]
                    ]
                }
            });
        });
    });
</script>

<div id="item1Img" class="otherDialog hide">
    <div class="row">
        <div class="col-12" id="storemap" style="width:100%;height: 100%;">
        </div>
    </div>
</div>
<div class="contextMenuDialog hide" id="dataContent">
    <div class="card-body">
        <div class="row">
            <div class="input-group col-lg-6">
                <div class="row">
                    <div class="col-lg-4 listrow">
                        <span class="nti">商户名：</span>
                        <span class="ncon">数据</span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">店长：</span>
                        <span class="ncon">数据</span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">店长电话：</span>
                        <span class="ncon">数据</span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">人数规模：</span>
                        <span class="ncon">数据</span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">申请人：</span>
                        <span class="ncon">数据</span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">申请人电话：</span>
                        <span class="ncon">数据</span>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="row text-left clearfix">
                    <input class="form-control float-left mr-2 w-50" type="text" placeholder="备注信息">
                    <button class="btn btn-primary float-left">保存</button>
                </div>
                <br>
                <div class="row text-right">
                    <select class="form-control w-50">
                        <option value="正常">正常</option>
                        <option value="申请合作">申请合作</option>
                        <option value="加入等待">加入等待</option>
                        <option value="异常">异常</option>
                    </select>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-12">
                <div class="row">
                    <table id="tablehz"></table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="contextMenuDialog hide" id="item1">
    <div class="card-body">
        <div class="row">
            <div class="input-group col-lg-12">
                <div class="row w-100">
                    <div class="col-lg-3 listrow">
                        <span class="nti">商户：</span>
                        <span class="ncon empstore">数据</span>
                    </div>
                    <!-- <div class="col-lg-3 listrow">
                        <span class="nti">累计审核：</span>
                        <span class="ncon">数据</span>
                    </div>
                    <div class="col-lg-3 listrow">
                        <span class="nti">状态：</span>
                        <span class="ncon">
                                <select class="form-control">
			                        <option value="正常">正常</option>
			                        <option value="申请合作">申请合作</option>
			                        <option value="加入等待">加入等待</option>
			                        <option value="异常">异常</option>
                                </select>
                            </span>
                    </div>
                    <div class="col-lg-3 listrow">
                        <input class="form-control float-left mr-2 w-50" type="text" placeholder="备注信息">
                        <button class="btn btn-primary float-left">保存</button>
                    </div> -->
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-12">
                <div class="row">
                    <table id="tableview"></table>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="contextMenuDialog hide" id="item2">
    <div class="card-body">
        <div class="row form-inline">
            <span class="">类别：</span>
            <select class="form-control mr-2">
                <option selected="">后台去电</option>
            </select>
            <input class="form-control mr-2" type="text" placeholder>
            <button class="btn btn-primary">搜索</button>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-12">
                <div class="row">
                    <table id="tablelog"></table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="contextMenuDialog hide" id="item3">
    <div class="card-body">
        <div class="row form-inline">
            <input class="form-control mr-2" type="text" placeholder="客户可见信息">
            <select class="form-control mr-2">
                <option>需期</option>
                <option>退回</option>
                <option>返回</option>
            </select>
            <input class="form-control mr-2" type="text" placeholder="内部备注信息">
            <button class="btn btn-primary">搜索</button>
        </div>
        <br>
        <div class="row w-100">
            <div class="card card-secondary w-100">
                <div class="card-header">
                    <h3 class="card-title">交易历史</h3>
                </div>
                <div class="card-body p-0">
                    <table id="tableyz1"></table>
                </div>

            </div>

        </div>

        <br>
        <div class="row w-100">
            <div class="card card-secondary w-100">
                <div class="card-header">
                    <h3 class="card-title">认证历史</h3>
                </div>
                <div class="card-body p-0">
                    <table id="tableyz2"></table>
                </div>

            </div>

        </div>
    </div>
</div>
<div class="contextMenuDialog hide" id="item4">
    <div class="card-body">
        <div class="row">
            <div class="col-lg-3 lh-38 form-inline">
                <span class="">订单状态：</span>
                <select class="form-control mr-2">
                    <option selected="">已发货</option>
                    <option selected="">待发货</option>
                    <option selected="">关闭</option>
                </select>
                <button class="btn btn-primary">保存</button>
            </div>
            <div class="col-lg-7 form-inline">
                <span class="">发货状态：</span>
                <select class="form-control mr-2">
                    <option selected="">已完成</option>
                </select>
                <span class="">快递公司：</span>
                <label class="mr-2">
                    <input name="optionsRadiosinline" type="radio" value="option1"> 顺丰
                    <input name="optionsRadiosinline" type="radio" value="option1"> 中通
                    <input name="optionsRadiosinline" type="radio" value="option1"> 其他
                </label>
                <span class="">快递单号：</span>
                <input class="form-control mr-2" type="text" placeholder>
                <button class="btn btn-primary">保存</button>
            </div>
            <div class="col-lg-2 form-inline mt-2">
                <input class="form-control mr-2 w-50" type="text" placeholder="备注信息">
                <button class="btn btn-primary">添加备注</button>
            </div>

        </div>
        <br>
        <div class="row">
            <div class="col-lg-8">
                <div class="row">
                    <div class="card card-secondary w-100">
                        <div class="card-header">
                            <h3 class="card-title">订单</h3>
                        </div>
                        <div class="card-body p-0">

                            <table id="tablehc1"></table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="row ml-1">
                    <div class="card card-secondary w-100">
                        <div class="card-header">
                            <h3 class="card-title">日志</h3>
                        </div>
                        <div class="card-body p-0">
                            <table id="tablehc2"></table>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>

</html>