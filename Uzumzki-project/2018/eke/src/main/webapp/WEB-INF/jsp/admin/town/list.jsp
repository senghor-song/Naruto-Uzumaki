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
    <aside class="main-sidebar sidebar-dark-primary elevation-4" id="leftSidebar" data-selectindex="4"></aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper pt-3">

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card card-secondary">
                            <div class="card-header">
                                <h3 class="card-title">
                                    <div class="row">
                                        <div class="col-lg-4 btncaozuo">
                                            <button class="btn btn-primary btn-sm" id="importData">创建</button>
                                        </div>
                                    </div>
                                </h3>
                                <div class="card-tools">
                                    <div class="input-group input-group-sm" style="width: 400px;">
                                        <div style="position: relative;top: 8px;margin-right: 8px;">
                                            <input type="checkbox" class="form-check-input" name="" id="" value="0" checked> 含线下
                                        </div>
                                        <select class="form-control float-right" id="selectType">
                                            <option value="0">新盘</option>
                                        </select>
                                        <input class="form-control float-right" id="keyword" name="table_search"
                                               type="text" placeholder="搜索内容">
                                        <div class="input-group-append">
                                            <button class="btn btn-default" id="btnSearch" type="submit">
                                                <i class="fa fa-search"></i> 搜索
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>

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
<script>
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
                        url: '/WebRelease/admin/town/list',
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
                                {field: 'town', title: '新盘', sort: true},
                                {field: 'townImageSum', title: '新盘图', sort: true},
                                {field: 'townPosterSum', title: '海报', sort: true},
                                {field: 'averageprice', title: '均价', sort: true},
                                {field: 'townCommissionPlanSum', title: '佣金方案', sort: true},
                                {field: 'view1', title: '主力户型', sort: true},
                                {field: 'status', title: '状态', sort: true},
                                {field: 'view1', title: '维护伙伴', sort: true},
                                {field: 'townDeveloperStaffSum', title: '开发商账号', sort: true},
                                {field: 'townLogSum', title: '日志', sort: true},
                            ]
                        ]
                    },
                    menuItem: {
                        item1: {
                            name: "查看", callback: function (key, opt) {
                                $.showContentMenu(key, opt, 440);
                            }
                        },
                        item2: {
                            name: "编辑", callback: function (key, opt) {
                                $.showContentMenu(key, opt, 440)
                            }
                        },
                        item3: {
                            name: "新盘图", callback: function (key, opt) {
                                $.showContentMenu(key, opt, 226)
                            }
                        },
                        item4: {
                            name: "主力户型", callback: function (key, opt) {
                                $.showContentMenu(key, opt, 236)
                            }
                        },
                        item5: {
                            name: "佣金方案", callback: function (key, opt) {
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tableyj',
                                    tableOption: {
                                        url: 'data/data.html',
                                        page: true,
                                        height: $("#tableyj").parents(".layui-layer-content").height() - 40,
                                        where: {},
                                        cols: [
                                            [
                                                {field: 'aa', title: '有效期', sort: true},
                                                {field: 'bb', title: '标题', sort: true},
                                                {field: 'cc', title: '内容', sort: true},
                                                {field: 'dd', title: '备注', sort: true},
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                        item6: {
                            name: "新盘动态", callback: function (key, opt) {
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tablexinpan',
                                    tableOption: {
                                        url: 'data/data.html',
                                        page: true,
                                        height: $("#tablexinpan").parents(".layui-layer-content").height() - 40,
                                        where: {},
                                        cols: [
                                            [
                                                {field: 'aa', title: '动态时间', sort: true},
                                                {field: 'bb', title: '发布人', sort: true},
                                                {field: 'cc', title: '状态', sort: true},
                                                {field: 'dd', title: '权限', sort: true},
                                                {field: 'dd', title: '内容', sort: true},
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                        item7: {
                            name: "商洽跟进", callback: function (key, opt) {
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tableqiatan',
                                    tableOption: {
                                        url: 'data/data.html',
                                        page: true,
                                        height: $("#tableqiatan").parents(".layui-layer-content").height() - 40,
                                        where: {},
                                        cols: [
                                            [
                                                {field: 'aa', title: '动态时间', sort: true},
                                                {field: 'bb', title: '发布人', sort: true},
                                                {field: 'cc', title: '内容', sort: true},
                                                {field: 'dd', title: '备注', sort: true},
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                        item8: {
                            name: "新盘日志", callback: function (key, opt) {
                                $.showContentMenu(key, opt)
                                $.tableObject({
                                    tableId: 'tablelog',
                                    tableOption: {
                                        url: 'data/data.html',
                                        page: true,
                                        height: $("#tablelog").parents(".layui-layer-content").height() - 40,
                                        where: {},
                                        cols: [
                                            [
                                                {field: 'aa', title: '时间', sort: true},
                                                {field: 'bb', title: '操作人', sort: true},
                                                {field: 'cc', title: '项', sort: true},
                                                {field: 'dd', title: '内容', sort: true},
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
                        item9: {
                            name: "开发商账号", callback: function (key, opt) {
                                $.showContentMenu(key, opt, 500)
                                $.tableObject({
                                    tableId: 'tableaccount',
                                    tableOption: {
                                        url: 'data/data.html',
                                        page: true,
                                        height: $("#tableaccount").parents(".layui-layer-content").height() - 40,
                                        where: {},
                                        cols: [
                                            [
                                                {field: 'aa', title: '登录账号', sort: true},
                                                {field: 'bb', title: '用户', sort: true},
                                                {field: 'cc', title: '用户电话', sort: true},
                                                {field: 'dd', title: '状态', sort: true},
                                                {field: 'ee', title: '最近登录', sort: true},
                                                {field: 'ff', title: '备注', sort: true},
                                            ]
                                        ]
                                    }
                                });
                            }
                        },
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
                area: [$(document).width() + 'px', '440px'],
                offset: 'b',
                title: '创建',
                resize: true,
                anim: 1,
                maxmin: true,
                content: obj,
                cancel: function (index, layero) {
                    $(".contextMenuDialog").addClass("hide");
                }
            });
        });

        $("#showImg img").hover(function () {
            var src = $(this).attr("src");
            $("#showImgDiv").attr("src", src).removeClass("hide");
        }, function () {
            $("#showImgDiv").addClass("hide");
        });

        $("#showImg1 img").hover(function () {
            var src = $(this).attr("src");
            $("#showImgDiv").attr("src", src).removeClass("hide");
            $("#img2div").removeClass("hide");
        }, function () {
            $("#showImgDiv").addClass("hide");
        });
    });
</script>

<div style="position: absolute; top: 0px; text-align: center; z-index: 9999999999; text-align: center; width: 100%;">
    <img id="showImgDiv" class="hide" src="" alt="" style="margin: auto; height: 650px;">
</div>
<div id="img2div" class="contextMenuDialog hide"
     style="position: absolute;top: 0px; right: 0px; width: 200px; z-index: 99999999999; background: #fff; padding: 12px; border: 1px;">
    <form action="" class="form-inline"></form>
    <div class="row">

        <div class="col-lg-4 nametl">厅</div>
        <div class="col-lg-8">
            <input class="form-control" type="text" class="w-100" placeholder="必填">
        </div>

        <div class="col-lg-4 nametl">房</div>
        <div class="col-lg-8">
            <input class="form-control" type="text" class="w-100" placeholder="">
        </div>
        <div class="col-lg-4 nametl">卫</div>
        <div class="col-lg-8">
            <input class="form-control" type="text" class="w-100" placeholder="">
        </div>
        <div class="col-lg-4 nametl">面积</div>
        <div class="col-lg-8">
            <input class="form-control" type="text" class="w-100" placeholder="">
        </div>
        <div class="col-lg-4 nametl">起步价</div>
        <div class="col-lg-8">
            <input class="form-control" type="text" class="w-100" placeholder="">
        </div>
        <div class="col-lg-4 nametl">朝向</div>
        <div class="col-lg-8">
            <select name="" class="form-control" id="">
                <option>东</option>
                <option>南</option>
                <option>西</option>
                <option>北</option>
            </select>
        </div>
        <div class="col-lg-4 nametl">得房率</div>
        <div class="col-lg-8">
            <input class="form-control" type="text" class="w-100" placeholder="">
        </div>
        <div class="col-lg-4 nametl"></div>
        <div class="col-lg-8">
            <button type="submit" class="form-control btn btn-primary">保存</button>
        </div>
    </div>
    </form>
</div>
<style>
    #showImg img,
    #showImg1 img {
        width: 120px;
        height: 100px;
    }
</style>
<div class="contextMenuDialog hide" id="dataContent">
    <div class="card-body form-control p-1">
        <div class="row p-0 m-0">
            <div class="input-group col-lg-5">
                <div class="card card-secondary">
                    <div class="card-header">
                        <h3 class="card-title">基础信息</h3>
                    </div>
                    <div class="card-body card-body-nolrp">
                        <div class="row">
                            <div class="col-lg-5">
                                <div class="row">
                                    <div class="col-lg-4 nametl">编号</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="必填">
                                    </div>

                                    <div class="col-lg-4 nametl">状态</div>
                                    <div class="col-lg-8">

                                        <select name="status" class="form-control" required="required">
                                            <option value="">上线</option>
                                        </select>

                                    </div>
                                    <div class="col-lg-4 nametl">均价</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                    <div class="col-lg-4 nametl">联系人</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-3">
                                <div class="col-lg-12">特点(最多4)</div>
                                <div>
                                    <textarea class="form-control"></textarea>
                                </div>
                                <div class="col-lg-12">可选</div>
                                <div>
                                    <select name="" class="form-control" id="">
                                        <option>按接口返回</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-lg-4">
                                <img src="/WebRelease/admin/static/image/u43079.png" style="max-width: 100%; max-height: 80%;"/>
                                <div>深圳市深南大道102号</div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <div class="input-group col-lg-4">
                <div class="card card-secondary w-100">
                    <div class="card-header">
                        <h3 class="card-title">详细信息</h3>
                    </div>
                    <div class="card-body card-body-nolrp">
                        <div class="row">

                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">开盘时间</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">建筑面积</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">交房时间</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">容积率</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">开发商</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">绿化率</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">开发商品牌</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">总户数</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">物业公司</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">车位数</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">售楼部电话</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

            </div>

            <div class="input-group col-lg-1">
                <div class="card card-secondary w-100">
                    <div class="card-header">
                        <h3 class="card-title">住宅参数</h3>
                    </div>
                    <div class="card-body card-body-nolrp">
                        <div class="row">

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">均价</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">物业费</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">建筑类型</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">装修状况</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">产权年限</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>

            </div>

            <div class="input-group col-lg-1">
                <div class="card card-secondary w-100">
                    <div class="card-header">
                        <h3 class="card-title">商铺参数</h3>
                    </div>
                    <div class="card-body card-body-nolrp">
                        <div class="row">

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">均价</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">物业费</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">建筑类型</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">装修状况</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">产权年限</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>

            </div>
            <div class="input-group col-lg-1">
                <div class="card card-secondary w-100">
                    <div class="card-header">
                        <h3 class="card-title">别墅参数</h3>
                    </div>
                    <div class="card-body card-body-nolrp">
                        <div class="row">

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">均价</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">物业费</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">建筑类型</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">装修状况</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">产权年限</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<div class="contextMenuDialog hide" id="item1">
    <div class="card-body form-control p-1">
        <div class="row p-0 m-0">
            <div class="input-group col-lg-5">
                <div class="card card-secondary">
                    <div class="card-header">
                        <h3 class="card-title">基础信息</h3>
                    </div>
                    <div class="card-body card-body-nolrp">
                        <div class="row">
                            <div class="col-lg-5">
                                <div class="row">
                                    <div class="col-lg-4 nametl">编号</div>
                                    <div class="col-lg-8">
                                        星河盛世花园
                                    </div>

                                    <div class="col-lg-4 nametl">状态</div>
                                    <div class="col-lg-8">
                                        上线
                                    </div>
                                    <div class="col-lg-4 nametl">均价</div>
                                    <div class="col-lg-8">
                                        21000
                                    </div>
                                    <div class="col-lg-4 nametl">联系人</div>
                                    <div class="col-lg-8">
                                        张小姐13320020920
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-3">
                                <div class="col-lg-12" style="color: #999;">特点(最多4)</div>
                                <div class="col-lg-12">
                                    近地铁
                                    <br>低密度
                                </div>
                            </div>

                            <div class="col-lg-4">
                                <img src="/WebRelease/admin/static/image/u43079.png" style="max-width: 100%; max-height: 80%;"/>
                                <div>深圳市深南大道102号</div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <div class="input-group col-lg-4">
                <div class="card card-secondary w-100">
                    <div class="card-header">
                        <h3 class="card-title">详细信息</h3>
                    </div>
                    <div class="card-body card-body-nolrp">
                        <div class="row">

                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">开盘时间</div>
                                    <div class="col-lg-8">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">建筑面积</div>
                                    <div class="col-lg-8">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">交房时间</div>
                                    <div class="col-lg-8">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">容积率</div>
                                    <div class="col-lg-8">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">开发商</div>
                                    <div class="col-lg-8">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">绿化率</div>
                                    <div class="col-lg-8">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">开发商品牌</div>
                                    <div class="col-lg-8">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">总户数</div>
                                    <div class="col-lg-8">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">物业公司</div>
                                    <div class="col-lg-8">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">车位数</div>
                                    <div class="col-lg-8">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">售楼部电话</div>
                                    <div class="col-lg-8">
                                        数据
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

            </div>

            <div class="input-group col-lg-1">
                <div class="card card-secondary w-100">
                    <div class="card-header">
                        <h3 class="card-title">住宅参数</h3>
                    </div>
                    <div class="card-body card-body-nolrp">
                        <div class="row">

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">均价</div>
                                    <div class="col-lg-6">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">物业费</div>
                                    <div class="col-lg-6">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">建筑类型</div>
                                    <div class="col-lg-6">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">装修状况</div>
                                    <div class="col-lg-6">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">产权年限</div>
                                    <div class="col-lg-6">
                                        数据
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>

            </div>

            <div class="input-group col-lg-1">
                <div class="card card-secondary w-100">
                    <div class="card-header">
                        <h3 class="card-title">商铺参数</h3>
                    </div>
                    <div class="card-body card-body-nolrp">
                        <div class="row">

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">均价</div>
                                    <div class="col-lg-6">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">物业费</div>
                                    <div class="col-lg-6">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">建筑类型</div>
                                    <div class="col-lg-6">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">装修状况</div>
                                    <div class="col-lg-6">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">产权年限</div>
                                    <div class="col-lg-6">
                                        数据
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>

            </div>
            <div class="input-group col-lg-1">
                <div class="card card-secondary w-100">
                    <div class="card-header">
                        <h3 class="card-title">别墅参数</h3>
                    </div>
                    <div class="card-body card-body-nolrp">
                        <div class="row">

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">均价</div>
                                    <div class="col-lg-6">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">物业费</div>
                                    <div class="col-lg-6">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">建筑类型</div>
                                    <div class="col-lg-6">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">装修状况</div>
                                    <div class="col-lg-6">
                                        数据
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">产权年限</div>
                                    <div class="col-lg-6">
                                        数据
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<div class="contextMenuDialog hide" id="item2">
    <div class="card-body form-control p-1">
        <div class="row p-0 m-0">
            <div class="input-group col-lg-5">
                <div class="card card-secondary">
                    <div class="card-header">
                        <h3 class="card-title">基础信息</h3>
                    </div>
                    <div class="card-body card-body-nolrp">
                        <div class="row">
                            <div class="col-lg-5">
                                <div class="row">
                                    <div class="col-lg-4 nametl">编号</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="必填">
                                    </div>

                                    <div class="col-lg-4 nametl">状态</div>
                                    <div class="col-lg-8">

                                        <select name="status" class="form-control" required="required">
                                            <option value="">上线</option>
                                        </select>

                                    </div>
                                    <div class="col-lg-4 nametl">均价</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                    <div class="col-lg-4 nametl">联系人</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-3">
                                <div class="col-lg-12">特点(最多4)</div>
                                <div>
                                    <textarea class="form-control"></textarea>
                                </div>
                                <div class="col-lg-12">可选</div>
                                <div>
                                    <select name="" class="form-control" id="">
                                        <option>按接口返回</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-lg-4">
                                <img src="/WebRelease/admin/static/image/u43079.png" style="max-width: 100%; max-height: 80%;"/>
                                <div>深圳市深南大道102号</div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <div class="input-group col-lg-4">
                <div class="card card-secondary w-100">
                    <div class="card-header">
                        <h3 class="card-title">详细信息</h3>
                    </div>
                    <div class="card-body card-body-nolrp">
                        <div class="row">

                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">开盘时间</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">建筑面积</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">交房时间</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">容积率</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">开发商</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">绿化率</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">开发商品牌</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">总户数</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">物业公司</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">车位数</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-4 nametl">售楼部电话</div>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

            </div>

            <div class="input-group col-lg-1">
                <div class="card card-secondary w-100">
                    <div class="card-header">
                        <h3 class="card-title">住宅参数</h3>
                    </div>
                    <div class="card-body card-body-nolrp">
                        <div class="row">

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">均价</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">物业费</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">建筑类型</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">装修状况</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">产权年限</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>

            </div>

            <div class="input-group col-lg-1">
                <div class="card card-secondary w-100">
                    <div class="card-header">
                        <h3 class="card-title">商铺参数</h3>
                    </div>
                    <div class="card-body card-body-nolrp">
                        <div class="row">

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">均价</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">物业费</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">建筑类型</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">装修状况</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">产权年限</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>

            </div>
            <div class="input-group col-lg-1">
                <div class="card card-secondary w-100">
                    <div class="card-header">
                        <h3 class="card-title">别墅参数</h3>
                    </div>
                    <div class="card-body card-body-nolrp">
                        <div class="row">

                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">均价</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">物业费</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">建筑类型</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">装修状况</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-lg-6 nametl">产权年限</div>
                                    <div class="col-lg-6">
                                        <input class="form-control" type="text" class="w-100" placeholder="">
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<div class="contextMenuDialog hide" id="item3">
    <div class="card-body">
        <div class="row mb-2">
            <button class="btn btn-primary float-left mr-2">添加</button>
            <button class="btn btn-danger float-left">删除</button>
        </div>
        <div class="row">
            <div id="showImg">
                <img src="/WebRelease/admin/static/image/u6894.jpg" alt="">
                <img src="/WebRelease/admin/static/image/u6894.jpg" alt="">
                <img src="/WebRelease/admin/static/image/u6894.jpg" alt="">
                <img src="/WebRelease/admin/static/image/u6894.jpg" alt="">
                <img src="/WebRelease/admin/static/image/u6894.jpg" alt="">
                <img src="/WebRelease/admin/static/image/u6894.jpg" alt="">
            </div>
        </div>
    </div>
</div>
<div class="contextMenuDialog hide" id="item4">
    <div class="card-body">
        <div class="row mb-2">
            <button class="btn btn-primary float-left mr-2">添加</button>
            <button class="btn btn-danger float-left">删除</button>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div id="showImg1">
                    <img src="/WebRelease/admin/static/image/u43393.jpg" alt="">
                    <img src="/WebRelease/admin/static/image/u43393.jpg" alt="">
                    <img src="/WebRelease/admin/static/image/u43393.jpg" alt="">
                    <img src="/WebRelease/admin/static/image/u43393.jpg" alt="">
                    <img src="/WebRelease/admin/static/image/u43393.jpg" alt="">
                    <img src="/WebRelease/admin/static/image/u43393.jpg" alt="">
                </div>
            </div>
        </div>
    </div>
</div>
<div class="contextMenuDialog hide" id="item5">
    <div class="card-body">
        <div class="row">
            <div class="col-lg-9">
                <table id="tableyj"></table>
            </div>
            <div class="col-lg-3">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-4 nametl">有效期</div>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" class="w-100" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-4 nametl">标题</div>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" class="w-100" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-4 nametl">内容</div>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" class="w-100" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-4 nametl">备注</div>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" class="w-100" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-4 nametl"></div>
                            <div class="col-lg-8">
                                <button type="submit" class="btn-primary form-control">保存</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="contextMenuDialog hide" id="item6">
    <div class="card-body">
        <div class="row">
            <div class="col-lg-9">
                <table id="tablexinpan"></table>
            </div>
            <div class="col-lg-3">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-4 nametl">含无效</div>
                            <div class="col-lg-8 text-left">
                                <input type="checkbox" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-4 nametl">作废</div>
                            <div class="col-lg-8 text-left">
                                <select name="" id="" class="form-control">
                                    <option>是</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-4 nametl">内容</div>
                            <div class="col-lg-8">
                                <textarea name="" class="form-control" placeholder="内容" id="" cols="30"
                                          rows="5"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-4 nametl"></div>
                            <div class="col-lg-8">
                                <button type="submit" class="btn-primary form-control">保存</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="contextMenuDialog hide" id="item7">
    <div class="card-body">
        <div class="row">
            <div class="col-lg-9">
                <table id="tableqiatan"></table>
            </div>
            <div class="col-lg-3">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-4 nametl">内容</div>
                            <div class="col-lg-8">
                                <textarea name="" class="form-control" placeholder="内容" id="" cols="30"
                                          rows="5"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12 mt-2">
                        <div class="row">
                            <div class="col-lg-4 nametl"></div>
                            <div class="col-lg-8">
                                <button type="submit" class="btn-primary form-control">保存</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="contextMenuDialog hide" id="item8">
    <div class="card-body">
        <div class="row">
            <div class="col-lg-12">
                <table id="tablelog"></table>
            </div>
        </div>
    </div>
</div>

<div class="contextMenuDialog hide" id="item9">
    <div class="card-body">
        <div class="row">
            <div class="col-lg-9">
                <table id="tableaccount"></table>
            </div>
            <div class="col-lg-3">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-4 nametl">账号</div>
                            <div class="col-lg-8">
                                abc1309102
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-4 nametl">用户</div>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" class="w-100" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-4 nametl">用户手机</div>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" class="w-100" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-4 nametl">用户状态</div>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" class="w-100" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-4 nametl">备注</div>
                            <div class="col-lg-8">
                                <textarea name="" class="form-control" placeholder="备注" id="" cols="30"
                                          rows="3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12 mt-2">
                        <div class="row">
                            <div class="col-lg-4 nametl"></div>
                            <div class="col-lg-8">
                                <button type="submit" class="btn-primary form-control mb-2">保存</button>
                                <button type="submit" class="btn-danger form-control">删除</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>