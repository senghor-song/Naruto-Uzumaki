// JavaScript Document

$(document).ready(function() {
	/*--图表1--*/
	var chart;
	chart = new Highcharts.Chart({
		chart: {
			renderTo: 'chart1',
			defaultSeriesType: 'line',
			marginRight: 130,
			marginBottom: 25
		},
		title: {
			text: '源中瑞后台统计标题',
			x: -20 //center
		},
		subtitle: {
			text: '网站访问量，会员数据统计',
			x: -20
		},
		xAxis: {
			categories: ['一月', '二月', '三月', '四月', '五月', '六月', 
				'七月', '八月', '九月', '十月', '十一月', '十二月kyo']
		},
		yAxis: {
			title: {
				text: '人数'
			},
			plotLines: [{
				value: 0,
				width: 1,
				color: '#808080'
			}]
		},
		tooltip: {
			formatter: function() {
					return '<b>'+ this.series.name +'</b><br/>'+
					this.x +': '+ this.y +'°C';
			}
		},
		legend: {
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'top',
			x: -10,
			y: 100,
			borderWidth: 0
		},
		series: [{
			name: 'ruiec.com',
			data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
		}, {
			name: 'ruiec.net',
			data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
		}, {
			name: 'ruiec.cn',
			data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
		}, {
			name: 'ledoem.cn',
			data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
		}]
	});
	/*--图表2--*/
	var chart2;
	chart2 = new Highcharts.Chart({
		chart: {
			renderTo: 'chart2',
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false
		},
		title: {
			text: '源中瑞后台统计标题'
		},
		tooltip: {
			formatter: function() {
				return '<b>'+ this.point.name +'</b>: '+ this.y +' %';
			}
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: true,
					color: '#000000',
					connectorColor: '#000000',
					formatter: function() {
						return '<b>'+ this.point.name +'</b>: '+ this.y +' %';
					}
				}
			}
		},
		series: [{
			type: 'pie',
			name: 'Browser share',
			data: [
				['Firefox',   45.0],
				['IE',       26.8],
				{
					name: 'Chrome',    
					y: 12.8,
					sliced: true,
					selected: true
				},
				['Safari',    8.5],
				['Opera',     6.2],
				['Others',   0.7]
			]
		}]
	});
	/*--图表3--*/
	var chart3;
	chart3 = new Highcharts.Chart({
		chart: {
			renderTo: 'chart3',
			defaultSeriesType: 'spline',
			marginRight: 10,
			events: {
				load: function() {
	
					// set up the updating of the chart each second
					var series = this.series[0];
					setInterval(function() {
						var x = (new Date()).getTime(), // current time
							y = Math.random();
						series.addPoint([x, y], true, true);
					}, 1000);
				}
			}
		},
		title: {
			text: '源中瑞后台统计标题'
		},
		xAxis: {
			type: 'datetime',
			tickPixelInterval: 150
		},
		yAxis: {
			title: {
				text: 'Value'
			},
			plotLines: [{
				value: 0,
				width: 1,
				color: '#808080'
			}]
		},
		tooltip: {
			formatter: function() {
					return '<b>'+ this.series.name +'</b><br/>'+
					Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+ 
					Highcharts.numberFormat(this.y, 2);
			}
		},
		legend: {
			enabled: false
		},
		exporting: {
			enabled: false
		},
		series: [{
			name: 'Random data',
			data: (function() {
				// generate an array of random data
				var data = [],
					time = (new Date()).getTime(),
					i;
				for (i = -19; i <= 0; i++) {
					data.push({
						x: time + i * 1000,
						y: Math.random()
					});
				}
				return data;
			})()
		}]
	});
				
});