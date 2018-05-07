/**
 * @content 创建Column-bar的图表
 * @constructor
 */
function CreateColumnBarChart(result){
    try{
        var data = result.axis_y;
        $('#'+result.chartId).highcharts({
            chart: {
                type: 'bar'
            },
            title: {
                text:result.title
            },
            xAxis: {
                categories:result.axis_x,
                title: {
                    text:null
                }
            },
            yAxis:{
                title: {
                    text:null
                }
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true,
                        allowOverlap: true
                    }
                }
            },
            legend:{
                enabled:false
            },
            credits: {
                enabled: false
            },
            series:data
        });
    }catch(e){
        console.log(e);
    }
}

/**
 * @content 创建Column-bar1的图表
 * @constructor
 */
function CreateColumnBarChart1(result){
    try{
        var data = result.axis_y;
        $('#'+result.chartId).highcharts({
            chart: {
                type: 'bar'
            },
            title: {
                text:result.title
            },
            tooltip: {
                pointFormat: '{point.y}',
                valueSuffix: ' 人',
                shared: true
            },
            xAxis: {
                categories:result.axis_x,
                title: {
                    text:null
                }
            },
            yAxis:{
                title: {
                    text:null
                }
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true,
                        allowOverlap: true
                    }
                }
            },
            legend:{
                enabled:false
            },
            credits: {
                enabled: false
            },
            series:data
        });
    }catch(e){
        console.log(e);
    }
}

/**
 * @content 创建Column 的图表
 * @constructor
 */
function CreateColumnChart(result){
    var chart = Highcharts.chart(result.chartId, {
        title: {
            text: result.title
        },
        subtitle: {
            text:null
        },
        tooltip: {
            pointFormat: '{point.y}',
            valueSuffix: ' 人',
            shared: true
        },
        yAxis:{
            lineWidth: 1,
            title:null
        },
        xAxis: {
            categories: result.axis_x
        },
        credits: {
            enabled: false
        },
        series: [{
            type: 'column',
            colorByPoint: true,
            data:result.axis_y,
            showInLegend: false
        }]
    });
}

/**
 * @content 创建Pie 的图表
 * @constructor
 */
function CreatePieChart(result){
    (function($,result,highcharts){
        $('#'+result.chartId).highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: result.title
            },
            credits: {
                enabled: false
            },
            tooltip: {
                headerFormat: '{series.name}<br>',
                pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                name:result.title,
                data:result.data
            }]
        });
    })(jQuery,result,Highcharts);
}

/**
 * @content 创建PieCircle 的图表
 * @constructor
 */
function CreatePieCircleChart(result){
    (function($,result,highcharts){
        $('#'+result.chartId).highcharts({
            chart: {
                type: 'variablepie'
            },
            title: {
                floating:true,
                text: result.title
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            fontSize:12,
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    },
                    point: {
                        events: {
                            mouseOver: function(e) {  // 鼠标滑过时动态更新标题
                                // 标题更新函数，API 地址：https://api.hcharts.cn/highcharts#Chart.setTitle
                                chart.setTitle({
                                    text: e.target.name+ '\t'+ e.target.y + ' %'
                                });
                            }
                        }
                    }
                }
            },
            credits: {
                enabled: false
            },
            series: [{
                type: 'pie',
                innerSize: '60%',
                name: result.title,
                data:result.data
            }]
        }, function(c) {
            // 环形图圆心
            var centerY = c.series[0].center[1],
                titleHeight = parseInt(c.title.styles.fontSize);
            c.setTitle({
                y:centerY + titleHeight/2
            });
            chart = c;
        });
    })(jQuery,result,Highcharts);
}

/**
 * @content 创建平滑曲线
 */
function CreateSplineChart(result){
    var formats='%Y-%m-%d';
    $('#'+result.chartId).highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text:result.title
        },
        credits: {
            enabled: false
        },
        xAxis: {
            type: 'datetime',
            labels: {
                formatter: function () {
                    return Highcharts.dateFormat(formats,this.value);
                },
                rotation:-30
            }
        },
        tooltip:{
            useHTML: true,
            headerFormat: '<table><tr><td style="color:green">时间：{point.key}</td></tr>',
            pointFormat: '<tr><td><b>预警量：{point.y}</b></td></tr>',
            footerFormat: '</table>',
            xDateFormat:formats,
            borderRadius:10,
            crosshairs: {
                width: 1,
                dashStyle: 'solid',
            }
        },
        plotOptions: {
            spline: {
                lineWidth: 4,
                states: {
                    hover: {
                        lineWidth: 5
                    }
                },
                marker: {
                    enabled: false
                },
                pointStart: Date.UTC(2017, 8,20),
                pointInterval: 24 * 3600 * 1000 // one day
            },
            series: {
                dataLabels: {
                    enabled: true,
                    format: '{y}'
                }
            }
        },
        legend:{
            enabled:false
        },
        series: result.data,
        navigation: {
            menuItemStyle: {
                fontSize: '10px'
            }
        }
    });
}

function CreateColumnSplineChart(result){
    var formats='%Y-%m-%d';
    $('#'+result.chartId).highcharts({
        title: {
            text:result.title
        },
        credits: {
            enabled: false
        },
        xAxis: {
            categories: result.axis_x
        },
        legend:{
            enabled:false
        },
        series: result.data
    });
}

/**
 * @content 创建散点列图
 * @param result
 * @constructor
 */
function CreateScatterChart(result){
    $('#'+result.chartId).highcharts({
        chart: {
            type: 'scatter',
            zoomType: 'xy'
        },
        title: {
            text:result.title
        },
        subtitle: {
            text: null
        },
        xAxis: {
            title: {
                enabled: true,
                text: '年龄'
            },
            startOnTick: true,
            endOnTick: true,
            showLastLabel: true
        },
        yAxis: {
            title: {
                text: '数量'
            }
        },
        legend: {
            layout: 'vertical',
            align: 'left',
            verticalAlign: 'top',
            x: 100,
            y: 70,
            floating: true,
            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF',
            borderWidth: 1
        },
        plotOptions: {
            scatter: {
                marker: {
                    radius: 5,
                    states: {
                        hover: {
                            enabled: true,
                            lineColor: 'rgb(100,100,100)'
                        }
                    }
                },
                states: {
                    hover: {
                        marker: {
                            enabled: false
                        }
                    }
                },
                tooltip: {
                    enabled:false
                }
            }
        },
        series:result.data
    });
}

/**
 * @content 创建图表的区域内容
 * @constructor
 */
function CreateChartContaienr(obj,type){
    var result = {
        obj:obj
    };
    var _randid  = new Date() ,  _ChartHtml , _W,_H;
    _W = obj.width() - 10 || 0;
    _H = obj.height() - 10 || 0;
    if(_W>10&&_H>10){
        _randid = _randid.getTime().toString().substr(7,_randid.toString().length-1);
        _randid = "Chart_"+_randid+"_"+Math.ceil(Math.random()*1000);
        _ChartHtml = "<div class='chart_"+type+"' id='"+_randid+"' width='"+_W+"px' height='"+_H+"px' style='width:"+_W+"px;height:"+_H+"px;'></div>";
        obj.append($(_ChartHtml));
        result.chartId = _randid;
        result.width = _W;
        result.height = _H;
    }else{
        result = null;
    }
    return result;
}

/**
 * @content 浏览器IE兼容版本
 * @author  梁汝翔<liangruxiang@ruiec.cn> 
 * @param MinSub 最低兼容版本数 
 * @time 2015年8月10日 10:07:09
 */
function Compatible_Promptbanben(MinSub) { 
	if (MinSub == undefined) {
		MinSub = 9;
	}

	var agent = navigator.userAgent.toLowerCase();
	var regStr_ie = /msie [\d.]+;/gi;
	var regStr_ff = /firefox\/[\d.]+/gi
	var regStr_chrome = /chrome\/[\d.]+/gi;
	var regStr_saf = /safari\/[\d.]+/gi;
	var _theBrowser = null ;
	//IE
	if (agent.indexOf("msie") > 0) {
		_theBrowser = agent.match(regStr_ie);
	}
	//firefox
	if (agent.indexOf("firefox") > 0) {
		_theBrowser = agent.match(regStr_ff);
	}
	//Chrome
	if (agent.indexOf("chrome") > 0) {
		_theBrowser = agent.match(regStr_chrome);
	}
	//Safari
	if (agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0) {
		_theBrowser = agent.match(regStr_saf);
	}

	if(!_theBrowser) _theBrowser = "";
	_theBrowser = _theBrowser.toString();  
	var theIeLengthLength = _theBrowser.indexOf("msie");  //判断是否为IE浏览器 
	
	var theBanben = 12;
	if (theIeLengthLength >= 0) {  //是否为IE浏览器
		//获取浏览器的版本 
		theBanben = _theBrowser.replace(/[^0-9.]/ig, ""); 
		theBanben = isNaN(Number($.trim(theBanben))) ? 11 : Number($.trim(theBanben)); 
	} 

	return theBanben;
}

var gridster;
$(function(){
    var GridsterStatue = true;
    var W = $(window).width();
    $(".gridster").css("width",W+"px");
    var Row_len = W / 10  ,Col_W = 0;
    Row_len = Math.floor(Row_len) - 15;
    Col_W = Math.floor(Row_len * 0.6);
	
	var liulnaqibanb = Compatible_Promptbanben();
	if(liulnaqibanb>=9){
			
		//绑定拖拽效果
		gridster = $(".gridster ul").gridster({
			widget_base_dimensions: [Row_len, Col_W],
			widget_margins: [10, 10],
			limit:{
				width:1
			},
			max_cols:10,
			resize: {
				enabled: false,
				start: function (e, ui, $widget) {
					console.log('START position: ' + ui.position.top + ' ' + ui.position.left);
				},
				resize: function (e, ui, $widget) {
					console.log('RESIZE offset: ' + ui.pointer.diff_top + ' ' + ui.pointer.diff_left);
				},
				stop: function (e, ui, $widget) {
					console.log('STOP position: ' + ui.position.top + ' ' + ui.position.left);
				}
			}
		}).data('gridster');

		//遍历展示图表
		if($(".Chart_items").size()>0){
			$(".Chart_items").each(function(){
				var _theType = $(this).attr("type") || "";
				if(_theType!=""){
					var _ChartItemObj = CreateChartContaienr($(this),_theType);
					switch (_theType){
						case "column-bar":
							_ChartItemObj.axis_x = $(this).attr("axis_x") || "";
							_ChartItemObj.axis_y = $(this).attr("axis_y") || "";
							_ChartItemObj.title  =  $(this).attr("title") || "";

							if(_ChartItemObj.axis_x.toString().indexOf("]")>=0){
								_ChartItemObj.axis_x = JSON.parse(_ChartItemObj.axis_x);
							}else if(_ChartItemObj.axis_x.toString().indexOf(",")>=0){
								_ChartItemObj.axis_x =  _ChartItemObj.axis_x.split(",");
							}

							if(_ChartItemObj.axis_y.toString().indexOf("]")>=0){
								_ChartItemObj.axis_y =  JSON.parse(_ChartItemObj.axis_y);
							}else if(_ChartItemObj.axis_x.toString().indexOf(",")>=0){
								_ChartItemObj.axis_y =  _ChartItemObj.axis_y.split(",");
								var _Data = new Array();
								for(var i = 0 ; i < _ChartItemObj.axis_y.length;i++){
									_Data.push(Number(_ChartItemObj.axis_y[i]));
								}
								_ChartItemObj.axis_y = _Data;
							}

							if(_ChartItemObj.xAxis_x!=""&&_ChartItemObj.xAxis_y!=""){
								CreateColumnBarChart(_ChartItemObj);
							}
							break;
						case "column-bar1":
							_ChartItemObj.axis_x = $(this).attr("axis_x") || "";
							_ChartItemObj.axis_y = $(this).attr("axis_y") || "";
							_ChartItemObj.title  =  $(this).attr("title") || "";

							_ChartItemObj.axis_x =  _ChartItemObj.axis_x.split(",");
							_ChartItemObj.axis_y =  JSON.parse(_ChartItemObj.axis_y);

							if(_ChartItemObj.xAxis_x!=""&&_ChartItemObj.xAxis_y!=""){
								CreateColumnBarChart1(_ChartItemObj);
							}
							break;
						case "column":
							_ChartItemObj.axis_x = $(this).attr("axis_x") || "";
							_ChartItemObj.axis_y = $(this).attr("axis_y") || "";
							_ChartItemObj.title  =  $(this).attr("title") || "";

							_ChartItemObj.axis_x =  _ChartItemObj.axis_x.split(",");
							if(_ChartItemObj.axis_y.indexOf(",")>=0){
								_ChartItemObj.axis_y =  _ChartItemObj.axis_y.split(",");
								var _Data = new Array();
								for(var i = 0 ; i < _ChartItemObj.axis_y.length;i++){
									_Data.push(Number(_ChartItemObj.axis_y[i]));
								}
								_ChartItemObj.axis_y = _Data;
							}else{
								_ChartItemObj.axis_y = "";
							}

							if(_ChartItemObj.xAxis_x!=""&&_ChartItemObj.xAxis_y!=""){
								CreateColumnChart(_ChartItemObj);
							}
							break;
						case "pie-circle":
							_ChartItemObj.title  =  $(this).attr("title") || "";
							_ChartItemObj.data = $(this).attr("data") || "";
							if(_ChartItemObj.data.toString().indexOf("]")>=0){
								_ChartItemObj.data =  eval("("+_ChartItemObj.data+")");
								CreatePieCircleChart(_ChartItemObj);
							}
							break;
						case "pie":
							_ChartItemObj.title  =  $(this).attr("title") || "";
							_ChartItemObj.data = $(this).attr("data") || "";
							if(_ChartItemObj.data.toString().indexOf("]")>=0){
								_ChartItemObj.data =  eval("("+_ChartItemObj.data+")");
								CreatePieChart(_ChartItemObj);
							}
							break;
						case "spline":
							_ChartItemObj.title  =  $(this).attr("title") || "";
							_ChartItemObj.data = $(this).attr("data") || "";
							if(_ChartItemObj.data.toString().indexOf("]")>=0){
								_ChartItemObj.data =  eval("("+_ChartItemObj.data+")");
								CreateSplineChart(_ChartItemObj);
							}
							break;
						case "spline-column":
							_ChartItemObj.title  =  $(this).attr("title") || "";
							_ChartItemObj.data = $(this).attr("data") || "";
							_ChartItemObj.axis_x = $(this).attr("axis_x") || "";
							if(_ChartItemObj.axis_x.toString().indexOf("]")>=0){
								_ChartItemObj.axis_x = JSON.parse(_ChartItemObj.axis_x);
							}else if(_ChartItemObj.axis_x.toString().indexOf(",")>=0){
								_ChartItemObj.axis_x =  _ChartItemObj.axis_x.split(",");
							}
							if(_ChartItemObj.data.toString().indexOf("]")>=0){
								_ChartItemObj.data =  eval("("+_ChartItemObj.data+")");
								CreateColumnSplineChart(_ChartItemObj);
							}
							break;
						case "scatter":
							_ChartItemObj.title  =  $(this).attr("title") || "";
							_ChartItemObj.data = $(this).attr("data") || "";
							if(_ChartItemObj.data.toString().indexOf("]")>=0){
								_ChartItemObj.data =  eval("("+_ChartItemObj.data+")");
								for(var k in  _ChartItemObj.data ){
									var Objdata = _ChartItemObj.data[k].data;
									for(var i = 0 ; i < Objdata.length; i++ ){
										var Age = Objdata[i][0];
										var RandAge = Math.random() * 10 + 118;
										if(Age>100){ Age = Math.floor(Age-RandAge)};
										_ChartItemObj.data[k].data[i][0] = Age;
									}
								}


								CreateScatterChart(_ChartItemObj);
							}
							break;
						default:
							break;
					}
				}
			})
		}
	}
	

});



