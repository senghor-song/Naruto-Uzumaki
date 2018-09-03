var laypage = layui.laypage,
    layer = layui.layer;
var element = layui.element;
var carousel = layui.carousel;
var dataList = null;
var dataList2 = null;
var dataList3 = null;

function redisRefresh(){
	$.ajax({  
        type : "POST",  //提交方式  
        url : "/WebRelease/admin/common/countTop",//路径  
        data : {},//数据，这里使用的是Json格式进行传输  
        dataType:"json",
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.code == 200 ) {  
            	$('#proposalSum').html(result.data.proposalSum);
            	$('#estateFindSum').html(result.data.estateFindSum);
            	$('#empStoreVerifySum').html(result.data.empStoreVerifySum);
            } else {  
            	$('#proposalSum').html("0");
            	$('#estateFindSum').html("0");
            	$('#empStoreVerifySum').html("0");
            }  
        }  
    }); 
}
$(function () {
	redisRefresh();
	window.setInterval(redisRefresh,60000); 
	
    $("input,select,textarea").not("[type=submit]").jqBootstrapValidation();

    $('input[type="file"]').on("change", function (a, b, c) {
        var v = a.currentTarget.value;
        $(this).parent().find("label").text(v).css({
            "text-align": "left"
        });
    });

    $("#logout").on("click", function () {
        var url = $(this).data("href");
        layer.open({
            title: '确认对话框',
            content: '确定退出系统登录吗？',
            btn: ['是', '否'],
            yes: function (index) {
            	$.ajax({  
                    type : "POST",  //提交方式  
                    url : "/WebRelease/admin/staff/loginout",//路径  
                    data : {},//数据，这里使用的是Json格式进行传输  
                    dataType:"json",
                    success : function(result) {//返回数据根据结果进行相应的处理  
                    	window.location.href="/WebRelease/admin/common/login";
                    }  
                }); 
            }
        });

    });

    var removeBgRow = function (tableClass) {
        $('.' + tableClass + ' .selectMenuBg').removeClass('selectMenuBg');
        $('.' + tableClass + ' .selectLeftBg').removeClass('selectLeftBg');
    };

    var hideDiaolog = function () {
        layer.closeAll();
        setTimeout(function () {
            $(".contextMenuDialog,.contextLeftDialog,.otherDialog").addClass("hide");
        }, 0);
    };

    $(document).on("keyup", function (a) {
        if (a.keyCode == 27) {
            hideDiaolog();
        }
    });

    $.extend({
        showContentLeft: function (id, title, width) {
            $('.selectLeftBg').removeClass('selectLeftBg');
            //$(opt.$trigger).addClass('selectLeftBg');
            var w = width || $(window).width() / 3;
            var obj = $("#" + id);
            obj.removeClass("hide");
            layer.open({
                type: 1,
                area: [w + 'px', $(window).height() + 'px'],
                offset: 'l',
                title: title,
                resize: true,
                maxmin: false,
                shadeClose: true,
                anim: 1,
                content: obj,
                cancel: function (index, layero) {
                    hideDiaolog();
                }
            });
        },
        showContentMenuLeft: function (key, opt, width) {
            $('.selectLeftBg').removeClass('selectLeftBg');
            $(opt.$trigger).addClass('selectLeftBg');
            var w = width || $(window).width() / 3;
            var obj = $("#" + key);
            obj.removeClass("hide");
            layer.open({
                type: 1,
                area: [w + 'px', $(window).height() + 'px'],
                offset: 'b',
                title: opt.items[key].name,
                resize: true,
                anim: 1,
                content: obj,
                maxmin: false,
                shadeClose: true,
                cancel: function (index, layero) {
                    hideDiaolog();
                }
            });
        },
        showContentMenu: function (key, opt, height) {
            var h = height || 500;
            var obj = $("#" + key);
            obj.removeClass("hide");
            layer.open({
                type: 1,
                area: ['100%', h + 'px'],
                offset: 'b',
                title: opt.items[key].name,
                resize: true,
                anim: 1,
                content: obj,
                maxmin: false,
				resize: false,
                shadeClose: true,
                cancel: function (index, layero) {
                    hideDiaolog();
                },
            	end: function () {
                    hideDiaolog();
            	}
            });
        },
        showContentMenuPer: function (key, opt, height) {
            var obj = $("#" + key);
            obj.removeClass("hide");
            layer.open({
                type: 1,
                area: ['100%', height],
                offset: 'b',
                title: opt.items[key].name,
                resize: true,
                anim: 1,
                content: obj,
                maxmin: false,
				resize: false,
				move: false,
                shadeClose: true,
                cancel: function (index, layero) {
                    hideDiaolog();
                },
                end: function (index, layero) {
                    hideDiaolog();
                }
            });
        },
        showContentMenuAjax: function (key, opt, height, url, id, type) {
            var h = height || 500;
            $.ajax({  
                type : "POST",  //提交方式  
                url : url,//路径  
                data : {  
                    "id" : id,
                    "type" : type
                },//数据这里使用的是Json格式进行传输  
                dataType:"html",
                success : function(result) {//返回数据根据结果进行相应的处理  
               		layer.open({
                        type: 1,
                        area: [$(document).width() + 'px', h + 'px'],
                        offset: 'b',
                        title: opt.items[key].name,
                        resize: true,
                        anim: 1,
                        content: result,
                        maxmin: false,
                        shadeClose: true,
                        cancel: function (index, layero) {
                            hideDiaolog();
                        }
                    });
                }  
            });
        },
        showContentMenuOpen: function (key, opt, height, url) {
            var h = height || 500;
       		layer.open({
                type: 2,
                area: [$(document).width() + 'px', h + 'px'],
                offset: 'b',
                title: opt.items[key].name,
                resize: true,
                anim: 1,
                content: url,
                maxmin: false,
                shadeClose: true,
                cancel: function (index, layero) {
                    hideDiaolog();
                }
            });
        },
        showContentMenuDemo: function (key, title, height) {
            var h = height || $(window).height() / 3;
            var obj = $("#" + key);
            obj.removeClass("hide");
            layer.open({
                type: 1,
                area: [$(document).width() + 'px', h + 'px'],
                offset: 'b',
                title: title,
                resize: true,
                anim: 1,
                content: obj,
                maxmin: false,
                shadeClose: true,
                cancel: function (index, layero) {
                    hideDiaolog();
                }
            });
        },
        tableObject: function (option) {
        	var msgindex = layer.msg('数据请求中', {
        		  icon: 16,
        		  time:false
        	});
            var obj = {
                tableIns: null,
                options: null,
            };
            var tableIns;
            var tableObj = {
                tableId: '',
                tableOption: {
                    url: '',
                    page: false,
                    where: {},
                    cols: [
                        []
                    ]
                },
                menuItem: null,
                dialogHight: null,
                init: function () {
                    var self = this;
                    $.extend(self, option);
                    self.initView();
                    self.render();
                    self.sort();
                },
                initView: function () {
                    var self = this;
                    $.extend(self, option);
                    var obj = $("#" + self.tableId).parent();
                    if (!obj.hasClass(self.tableId)) {
                        obj.addClass(self.tableId);
                    }
                    if (!$('#' + self.tableId).attr('lay-filter')) {
                        $('#' + self.tableId).attr('lay-filter', self.tableId);
                    }
                },
                render: function () {
                    var self = this;
                    var table = layui.table;
                    var opt = {
                        elem: '#' + self.tableId,
                        even: true,
                        skin: 'row',
                        limit: 50,
                        limits: [50, 200, 500, 999999],
                        done: function (res, curr, count) {
                        	self.menu();
                        	self.title();
                        	if(res.listType == 0){
                            	dataList = res.data;
                        	}else if(res.listType == 100){
                            	dataList2 = res.data;
                                if(res.leftClick == 1){
                                	self.menuclick();
                                }
                        	}else if(res.listType == 200){
                            	dataList3 = res.data;
                                if(res.leftClick == 1){
                                	self.menuclickSon();
                                }
                        	}
                        	layer.close(msgindex);
                            // self.width();
                        }
                    };
                    $.extend(opt, self.tableOption);
                    obj.tableIns = table.render(opt);
                    obj.opt;
                },
                menuclick: function (){
                    var self = this;
                    $('.' + self.tableId + ' .layui-table-body table tr').unbind().on("click", function () {
                    	leftClick($(this))
                    });
                },
                menuclickSon: function (){
                    var self = this;
                    $('.' + self.tableId + ' .layui-table-body table tr').unbind().on("click", function () {
                    	leftClickSetAreaId($(this))
                    });
                },
                menu: function () {
                    var self = this;
                    if (!$.isEmptyObject(self.menuItem)) {
                        $.contextMenu({
                            selector: '.' + self.tableId + ' .layui-table-body table tr',
                            items: self.menuItem,
                            events: {
                                show: function (options) {
                                    removeBgRow(self.tableId);
                                    this.addClass("selectMenuBg");
                                }
                            },
                            autoHide: true
                        });
                    }
                    $('.' + self.tableId + ' .layui-table-body table tr').unbind().on("click", function () {
                        removeBgRow(self.tableId);
                        $(this).addClass("selectMenuBg");
                    });
                },
                title: function () {
                    var self = this;
                    $('.' + self.tableId + " tbody .layui-table-cell").each(function () {
                        $(this).parent().attr("title", $(this).text());
                    });
                },
                width: function () {
                    var self = this;
                    $('.' + self.tableId + " tbody .layui-table-cell").each(function () {
                        var w = $(this).parent().width();
                        $(this).css({
                            width: "auto",
                            "max-width": 300,
                            padding: "0 1px 0 2px"
                        }).attr("title", $(this).text());
                    });
                    $('.' + self.tableId + " thead .layui-table-cell").each(function () {
                        var w = $(this).parent().width();
                        $(this).css({
                            width: "auto",
                            "max-width": 300,
                            padding: "0 1px 0 2px"
                        }).attr("title", $(this).text());
                    });

                    var tdw = [];
                    var objTd = $('.' + self.tableId + " tbody tr").first().find("td");
                    objTd.each(function () {
                        var w = $(this).width();
                        tdw.push(w);
                    });

                    var thw = [];
                    var objTh = $('.' + self.tableId + " .layui-table-header thead tr").first().find("th");
                    objTh.each(function () {
                        var w = $(this).width();
                        thw.push(w);
                    });

                    for (var i = 0; i < tdw.length; i++) {
                        console.log(tdw[i], thw[i])
                        if (tdw[i] > thw[i]) {
                            $(objTh[i]).css({
                                width: tdw[i],
                                padding: "0 1px 0 2px"
                            }).find(">div").css({
                                width: tdw[i]
                            });
                        } else if (tdw[i] < thw[i]) {
                            $(objTd[i]).css({
                                width: thw[i],
                                padding: "0 1px 0 2px"
                            }).find(">div").css({
                                width: thw[i]
                            });;
                        }
                    }
                },
                sort: function () {
                    var self = this;
                    var table = layui.table;
                    var filterName = $('#' + self.tableId).attr('lay-filter');
                    table.on('sort(' + filterName + ')', function (obj) {
                        var where = $.extend({}, self.tableOption.where || {}, {
                            field: obj.field,
                            order: obj.type
                        });
                        table.reload(self.tableId, {
                            initSort: obj,
                            where: where
                        });
                    });
                }
            }
            tableObj.init();
            return obj;
        },
        reload: function (obj) {
            obj.tableIns.reload(obj.opt);
        }
    });

    //个人资料
    var html = $("#myinfoDiv").html();
    $("body").append("<div id='myinfoDiv2' class='otherDialog hide'>" + html + "</div>");
    $("#myinfoDiv").html("");

    $(".myinfo").on("click", function () {
        var obj = $("#myinfoDiv2");
        obj.removeClass("hide");
        layer.open({
            type: 1,
            area: ['500px', '420px'],
            title: '个人资料',
            resize: true,
            anim: 1,
            maxmin: false,
			move: false,
            shadeClose: true,
            content: obj,
            zIndex: 19891016,
            cancel: function (index, layero) {
                hideDiaolog();
            }
        });
        return false;
    });

    $("#changePwd").on("click", function () {
        var b = $("#changePwd").prop("checked");
        if (b) {
            $("#infoForm").removeClass("hide");
        } else {
            $("#infoForm").addClass("hide");
        }
    });

    //短信
    var html = $("#messageDiv").html();
    $("body").append("<div id='messageDiv2' class='otherDialog hide'>" + html + "</div>");
    $("#messageDiv").html("");

    $(".mymessage").on("click", function () {
        var obj = $("#messageDiv2");
        obj.removeClass("hide");
        layer.open({
            type: 1,
            area: ['500px', '390px'],
            title: '发送短信',
            resize: true,
            anim: 1,
            maxmin: false,
            shadeClose: true,
            content: obj,
            zIndex: 19891016,
            cancel: function (index, layero) {
                hideDiaolog();
            }
        });
        return false;
    });

    //地图
    var html = $("#mapDiv").html();
    $("body").append("<div id='mapDiv2' class='otherDialog hide'>" + html + "</div>");
    $("#mapDiv").html("");

    $(".mymap").on("click", function () {
        var obj = $("#mapDiv2");
        obj.removeClass("hide");
        layer.open({
            type: 2,
            area: [$(window).width() + 17 + 'px', $(window).height() + 'px'],
            title: '地图',
            offset: 't',
            resize: true,
            anim: 1,
            maxmin: false,
            shadeClose: true,
            scrollbar: false,
            content: "/WebRelease/admin/baiduMap/index",
            zIndex: 19891016,
            cancel: function (index, layero) {
                hideDiaolog();
            }
        });
        return false;
    });
});