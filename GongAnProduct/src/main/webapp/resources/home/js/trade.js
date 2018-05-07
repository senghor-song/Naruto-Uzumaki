;(function($,mui,H){
	
	var _This , _Option; //全局变量
	var RChart = function(){
		this.param = "";
	}
	
	RChart.prototype={
        /**
         * @content 初始化图表插件
         * @param {Object} Option 自定义配置信息
         */
		Init:function(SendOption){
			_This = this;
			_Option = _This.ExtendOption(SendOption);
			
			_This.InitChartContainer(_Option); 
		},
        /**
         * @content 配置信息继承扩展
         * @param {Object} Option 自定义配置信息
         */
		ExtendOption:function(SendOption){
			var DefaultOptions = {
					 ProId:"",
					 ChartType:"",
					 GetChartApi:"",
				}
			return $.extend(true,{},DefaultOptions,SendOption);
		},
		/**
		 * @content 创建绘制图表的区域
		 * @param options 全局配置信息
		 */
		InitChartContainer:function(_Option){
			if(_Option.ProId == "") return false;
			var _ProItemObj = $("#list").find(".product_obj[pro_code='"+_Option.ProId+"']");
			//console.log(_ProItemObj);
			var _ChartContainers = "<div class='ProChart' id='chart_box_"+_Option.ProId+"' ProId = '"+_Option.ProId+"' style='width:100%;height:100%;'><div>";
			if(_ProItemObj.find("#chart_box_"+_Option.ProId).size()>0){
				//存在图表
				//console.log("存在图表");
				_ProItemObj.find(".chart_box").eq(0).empty().append($(_ChartContainers)).show();
			}else{
				//不存在图表
				//console.log("不存在图表");
				_ProItemObj.find(".chart_box").eq(0).append($(_ChartContainers)).show();
			}
			
			_Option.ProChartObj = $("#chart_box_"+_Option.ProId);
			
			//初始化图表内容
			_This.InitChartDatas(_Option);
		},
		/**
		 * @content 初始化图表的数据
		 * @param _Option 全局对象
		 */
		InitChartDatas:function(_Option){
			//console.log("初始化K线的数据",_Option);
			var _GetKlineApi = _Option.GetChartApi;
			if(_GetKlineApi!=""){
				$.ajax({
					type:"POST",
					url:_GetKlineApi,
					data:{
						code:_Option.ProId,
						kLineType:_Option.ChartType, 
						time:"1"
					},
					dataType:"json",
					beforSend:function(){},
					success:function(data){
						if(_Option.ChartType == "1"){
							_This.CreatTimeChart(data,_Option); //创建分时图
						}else{
							_This.CreateCandlestick(data,_Option); //创建K线图
						}
					},
					compelet:function(){
						_Option.ProChartObj.show();
					}
				})
			}else{
				var data = [{ "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:54:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:53:00", "Opened": "96.45", "Lowest": "96.45", "Highest": "96.65", "Closed": "96.6", "DNum": "28500", "Newest": "2751020" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:52:00", "Opened": "96.5", "Lowest": "96.4", "Highest": "96.5", "Closed": "96.4", "DNum": "3500", "Newest": "337424" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:51:00", "Opened": "96.5", "Lowest": "96.4", "Highest": "96.5", "Closed": "96.5", "DNum": "24500", "Newest": "2362800" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:50:00", "Opened": "96.55", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.5", "DNum": "10500", "Newest": "1013360" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:49:00", "Opened": "96.6", "Lowest": "96.45", "Highest": "96.6", "Closed": "96.55", "DNum": "100000", "Newest": "9653790" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:48:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.55", "DNum": "45500", "Newest": "4393920" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:47:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "6500", "Newest": "628012" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:46:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "3005", "Newest": "290292" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:45:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.65", "Closed": "96.6", "DNum": "35000", "Newest": "3381110" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:44:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "26500", "Newest": "2559600" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:43:00", "Opened": "96.45", "Lowest": "96.45", "Highest": "96.55", "Closed": "96.55", "DNum": "17500", "Newest": "1688350" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:42:00", "Opened": "96.4", "Lowest": "96.4", "Highest": "96.45", "Closed": "96.45", "DNum": "1000", "Newest": "96424" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:41:00", "Opened": "96.45", "Lowest": "96.4", "Highest": "96.45", "Closed": "96.45", "DNum": "6500", "Newest": "626928" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:40:00", "Opened": "96.4", "Lowest": "96.4", "Highest": "96.55", "Closed": "96.5", "DNum": "13500", "Newest": "1302020" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:39:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "9000", "Newest": "868488" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:38:00", "Opened": "96.45", "Lowest": "96.45", "Highest": "96.5", "Closed": "96.5", "DNum": "16000", "Newest": "1543210" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:37:00", "Opened": "96.55", "Lowest": "96.45", "Highest": "96.55", "Closed": "96.45", "DNum": "18500", "Newest": "1786420" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:36:00", "Opened": "96.65", "Lowest": "96.65", "Highest": "96.7", "Closed": "96.7", "DNum": "2500", "Newest": "241652" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:35:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.8", "Closed": "96.65", "DNum": "27500", "Newest": "2658150" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:34:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.6", "Closed": "96.5", "DNum": "4500", "Newest": "434500" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:33:00", "Opened": "96.65", "Lowest": "96.55", "Highest": "96.65", "Closed": "96.55", "DNum": "14000", "Newest": "1352100" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:32:00", "Opened": "96.8", "Lowest": "96.65", "Highest": "96.85", "Closed": "96.65", "DNum": "25000", "Newest": "2419280" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-17 09:31:00", "Opened": "96.4", "Lowest": "96.3", "Highest": "96.8", "Closed": "96.8", "DNum": "108876", "Newest": "10506400" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 16:00:00", "Opened": "96.95", "Lowest": "96.85", "Highest": "97.15", "Closed": "96.9", "DNum": "633500", "Newest": "61388600" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:59:00", "Opened": "96.9", "Lowest": "96.9", "Highest": "97", "Closed": "96.9", "DNum": "64815", "Newest": "6282180" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:58:00", "Opened": "96.75", "Lowest": "96.75", "Highest": "96.9", "Closed": "96.9", "DNum": "47500", "Newest": "4599870" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:57:00", "Opened": "96.7", "Lowest": "96.7", "Highest": "96.8", "Closed": "96.8", "DNum": "102000", "Newest": "9865380" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:56:00", "Opened": "96.75", "Lowest": "96.6", "Highest": "96.8", "Closed": "96.6", "DNum": "75000", "Newest": "7253890" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:55:00", "Opened": "96.7", "Lowest": "96.7", "Highest": "96.8", "Closed": "96.75", "DNum": "22000", "Newest": "2128670" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:54:00", "Opened": "96.7", "Lowest": "96.7", "Highest": "96.75", "Closed": "96.7", "DNum": "10500", "Newest": "1015620" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:53:00", "Opened": "96.8", "Lowest": "96.75", "Highest": "96.85", "Closed": "96.75", "DNum": "24000", "Newest": "2322910" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:52:00", "Opened": "96.85", "Lowest": "96.8", "Highest": "96.85", "Closed": "96.8", "DNum": "7500", "Newest": "726176" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:51:00", "Opened": "96.75", "Lowest": "96.75", "Highest": "96.85", "Closed": "96.85", "DNum": "48000", "Newest": "4646690" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:50:00", "Opened": "96.85", "Lowest": "96.75", "Highest": "96.85", "Closed": "96.8", "DNum": "55000", "Newest": "5324220" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:49:00", "Opened": "96.85", "Lowest": "96.8", "Highest": "96.85", "Closed": "96.8", "DNum": "16000", "Newest": "1548960" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:48:00", "Opened": "96.85", "Lowest": "96.8", "Highest": "96.85", "Closed": "96.8", "DNum": "5500", "Newest": "532544" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:47:00", "Opened": "96.8", "Lowest": "96.8", "Highest": "96.9", "Closed": "96.85", "DNum": "11000", "Newest": "1065180" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:46:00", "Opened": "96.85", "Lowest": "96.75", "Highest": "96.85", "Closed": "96.8", "DNum": "24000", "Newest": "2323200" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:45:00", "Opened": "96.85", "Lowest": "96.85", "Highest": "96.9", "Closed": "96.85", "DNum": "17500", "Newest": "1694980" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:44:00", "Opened": "96.9", "Lowest": "96.8", "Highest": "96.9", "Closed": "96.8", "DNum": "11500", "Newest": "1113890" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:43:00", "Opened": "96.95", "Lowest": "96.9", "Highest": "96.95", "Closed": "96.9", "DNum": "18500", "Newest": "1792700" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:42:00", "Opened": "96.9", "Lowest": "96.9", "Highest": "96.9", "Closed": "96.9", "DNum": "1500", "Newest": "145344" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:41:00", "Opened": "96.95", "Lowest": "96.9", "Highest": "96.95", "Closed": "96.9", "DNum": "2500", "Newest": "242272" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:40:00", "Opened": "96.95", "Lowest": "96.85", "Highest": "96.95", "Closed": "96.95", "DNum": "84000", "Newest": "8141790" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:39:00", "Opened": "97", "Lowest": "96.95", "Highest": "97", "Closed": "96.95", "DNum": "6868", "Newest": "665792" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:38:00", "Opened": "97", "Lowest": "96.95", "Highest": "97", "Closed": "97", "DNum": "7500", "Newest": "727424" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:37:00", "Opened": "96.95", "Lowest": "96.95", "Highest": "97", "Closed": "97", "DNum": "6500", "Newest": "630464" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:36:00", "Opened": "97", "Lowest": "97", "Highest": "97", "Closed": "97", "DNum": "4000", "Newest": "387968" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:35:00", "Opened": "97", "Lowest": "96.95", "Highest": "97", "Closed": "96.95", "DNum": "5500", "Newest": "533440" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:34:00", "Opened": "97", "Lowest": "96.95", "Highest": "97", "Closed": "97", "DNum": "3500", "Newest": "339488" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:33:00", "Opened": "97", "Lowest": "97", "Highest": "97", "Closed": "97", "DNum": "3500", "Newest": "339456" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:32:00", "Opened": "96.95", "Lowest": "96.95", "Highest": "97", "Closed": "97", "DNum": "8000", "Newest": "775776" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:31:00", "Opened": "96.95", "Lowest": "96.95", "Highest": "96.95", "Closed": "96.95", "DNum": "6000", "Newest": "581696" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:30:00", "Opened": "96.95", "Lowest": "96.95", "Highest": "96.95", "Closed": "96.95", "DNum": "4000", "Newest": "387776" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:29:00", "Opened": "96.9", "Lowest": "96.9", "Highest": "96.9", "Closed": "96.9", "DNum": "15500", "Newest": "1502180" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:28:00", "Opened": "97", "Lowest": "97", "Highest": "97.05", "Closed": "97", "DNum": "19184", "Newest": "1860900" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:27:00", "Opened": "97.1", "Lowest": "97", "Highest": "97.1", "Closed": "97", "DNum": "7000", "Newest": "679360" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:26:00", "Opened": "97.1", "Lowest": "97", "Highest": "97.1", "Closed": "97.1", "DNum": "2000", "Newest": "194144" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:25:00", "Opened": "97.05", "Lowest": "96.95", "Highest": "97.1", "Closed": "97.1", "DNum": "21000", "Newest": "2038270" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:24:00", "Opened": "97.1", "Lowest": "97.1", "Highest": "97.1", "Closed": "97.1", "DNum": "20000", "Newest": "1941890" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:23:00", "Opened": "97.05", "Lowest": "97.05", "Highest": "97.15", "Closed": "97.15", "DNum": "36500", "Newest": "3544060" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:22:00", "Opened": "97.1", "Lowest": "97", "Highest": "97.1", "Closed": "97.1", "DNum": "23000", "Newest": "2231900" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:21:00", "Opened": "97.15", "Lowest": "97.1", "Highest": "97.15", "Closed": "97.1", "DNum": "28500", "Newest": "2767390" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:20:00", "Opened": "97.15", "Lowest": "97.15", "Highest": "97.15", "Closed": "97.15", "DNum": "4500", "Newest": "437184" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:19:00", "Opened": "97.1", "Lowest": "97.1", "Highest": "97.15", "Closed": "97.15", "DNum": "8500", "Newest": "825600" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:18:00", "Opened": "96.95", "Lowest": "96.95", "Highest": "97.05", "Closed": "97.05", "DNum": "4500", "Newest": "436480" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:17:00", "Opened": "97.2", "Lowest": "96.8", "Highest": "97.25", "Closed": "96.95", "DNum": "73000", "Newest": "7087780" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:16:00", "Opened": "97.2", "Lowest": "97.2", "Highest": "97.2", "Closed": "97.2", "DNum": "9000", "Newest": "874816" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:15:00", "Opened": "97.1", "Lowest": "97.1", "Highest": "97.2", "Closed": "97.2", "DNum": "59500", "Newest": "5780900" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:14:00", "Opened": "96.85", "Lowest": "96.85", "Highest": "97.15", "Closed": "97.1", "DNum": "121000", "Newest": "11731500" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:13:00", "Opened": "96.7", "Lowest": "96.7", "Highest": "96.85", "Closed": "96.85", "DNum": "73500", "Newest": "7115650" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:12:00", "Opened": "96.75", "Lowest": "96.7", "Highest": "96.75", "Closed": "96.7", "DNum": "500", "Newest": "48352" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:11:00", "Opened": "96.75", "Lowest": "96.75", "Highest": "96.75", "Closed": "96.75", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:10:00", "Opened": "96.75", "Lowest": "96.75", "Highest": "96.75", "Closed": "96.75", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:09:00", "Opened": "96.7", "Lowest": "96.7", "Highest": "96.75", "Closed": "96.75", "DNum": "1868", "Newest": "180448" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:08:00", "Opened": "96.8", "Lowest": "96.75", "Highest": "96.8", "Closed": "96.75", "DNum": "2025", "Newest": "195904" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:07:00", "Opened": "96.8", "Lowest": "96.8", "Highest": "96.8", "Closed": "96.8", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:06:00", "Opened": "96.7", "Lowest": "96.7", "Highest": "96.8", "Closed": "96.8", "DNum": "19000", "Newest": "1838270" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:05:00", "Opened": "96.75", "Lowest": "96.75", "Highest": "96.75", "Closed": "96.75", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:04:00", "Opened": "96.75", "Lowest": "96.75", "Highest": "96.75", "Closed": "96.75", "DNum": "11500", "Newest": "1112640" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:03:00", "Opened": "96.7", "Lowest": "96.7", "Highest": "96.8", "Closed": "96.8", "DNum": "18000", "Newest": "1741540" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:02:00", "Opened": "96.65", "Lowest": "96.65", "Highest": "96.7", "Closed": "96.7", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:01:00", "Opened": "96.7", "Lowest": "96.65", "Highest": "96.75", "Closed": "96.65", "DNum": "10500", "Newest": "1015330" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 15:00:00", "Opened": "96.75", "Lowest": "96.7", "Highest": "96.75", "Closed": "96.7", "DNum": "4500", "Newest": "435296" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:59:00", "Opened": "96.75", "Lowest": "96.75", "Highest": "96.75", "Closed": "96.75", "DNum": "1000", "Newest": "96736" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:58:00", "Opened": "96.75", "Lowest": "96.75", "Highest": "96.75", "Closed": "96.75", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:57:00", "Opened": "96.75", "Lowest": "96.7", "Highest": "96.75", "Closed": "96.75", "DNum": "4000", "Newest": "386880" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:56:00", "Opened": "96.7", "Lowest": "96.7", "Highest": "96.7", "Closed": "96.7", "DNum": "5000", "Newest": "483520" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:55:00", "Opened": "96.75", "Lowest": "96.75", "Highest": "96.8", "Closed": "96.8", "DNum": "5684", "Newest": "549824" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:54:00", "Opened": "96.75", "Lowest": "96.75", "Highest": "96.75", "Closed": "96.75", "DNum": "17000", "Newest": "1644770" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:53:00", "Opened": "96.75", "Lowest": "96.75", "Highest": "96.75", "Closed": "96.75", "DNum": "3000", "Newest": "290112" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:52:00", "Opened": "96.7", "Lowest": "96.7", "Highest": "96.8", "Closed": "96.7", "DNum": "18500", "Newest": "1789790" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:51:00", "Opened": "96.7", "Lowest": "96.7", "Highest": "96.75", "Closed": "96.7", "DNum": "47000", "Newest": "4545090" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:50:00", "Opened": "96.75", "Lowest": "96.75", "Highest": "96.75", "Closed": "96.75", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:49:00", "Opened": "96.75", "Lowest": "96.75", "Highest": "96.75", "Closed": "96.75", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:48:00", "Opened": "96.75", "Lowest": "96.7", "Highest": "96.75", "Closed": "96.75", "DNum": "3000", "Newest": "290208" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:47:00", "Opened": "96.7", "Lowest": "96.7", "Highest": "96.7", "Closed": "96.7", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:46:00", "Opened": "96.75", "Lowest": "96.7", "Highest": "96.75", "Closed": "96.7", "DNum": "17118", "Newest": "1655710" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:45:00", "Opened": "96.75", "Lowest": "96.75", "Highest": "96.75", "Closed": "96.75", "DNum": "11000", "Newest": "1064260" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:44:00", "Opened": "96.75", "Lowest": "96.75", "Highest": "96.75", "Closed": "96.75", "DNum": "15000", "Newest": "1451260" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:43:00", "Opened": "96.75", "Lowest": "96.7", "Highest": "96.75", "Closed": "96.75", "DNum": "2000", "Newest": "193408" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:42:00", "Opened": "96.7", "Lowest": "96.7", "Highest": "96.75", "Closed": "96.75", "DNum": "4254", "Newest": "411584" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:41:00", "Opened": "96.75", "Lowest": "96.7", "Highest": "96.75", "Closed": "96.7", "DNum": "3500", "Newest": "338592" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:40:00", "Opened": "96.65", "Lowest": "96.65", "Highest": "96.75", "Closed": "96.75", "DNum": "30000", "Newest": "2900800" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:39:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.65", "Closed": "96.65", "DNum": "1500", "Newest": "144960" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:38:00", "Opened": "96.65", "Lowest": "96.6", "Highest": "96.65", "Closed": "96.6", "DNum": "2000", "Newest": "193216" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:37:00", "Opened": "96.65", "Lowest": "96.65", "Highest": "96.65", "Closed": "96.65", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:36:00", "Opened": "96.7", "Lowest": "96.65", "Highest": "96.7", "Closed": "96.65", "DNum": "7184", "Newest": "694176" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:35:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.7", "Closed": "96.7", "DNum": "19000", "Newest": "1836420" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:34:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "4000", "Newest": "386400" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:33:00", "Opened": "96.65", "Lowest": "96.55", "Highest": "96.65", "Closed": "96.55", "DNum": "3000", "Newest": "289760" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:32:00", "Opened": "96.65", "Lowest": "96.6", "Highest": "96.65", "Closed": "96.6", "DNum": "22000", "Newest": "2125250" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:31:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.65", "Closed": "96.65", "DNum": "2000", "Newest": "193248" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:30:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.65", "Closed": "96.65", "DNum": "3000", "Newest": "289824" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:29:00", "Opened": "96.6", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "8000", "Newest": "772768" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:28:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "14000", "Newest": "1352320" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:27:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.55", "DNum": "2500", "Newest": "241472" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:26:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "1000", "Newest": "96608" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:25:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "2000", "Newest": "193184" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:24:00", "Opened": "96.6", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "3000", "Newest": "289728" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:23:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "8500", "Newest": "821088" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:22:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "500", "Newest": "48288" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:21:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.55", "DNum": "4000", "Newest": "386304" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:20:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "1500", "Newest": "144896" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:19:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "2000", "Newest": "193184" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:18:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "1000", "Newest": "96576" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:17:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "500", "Newest": "48288" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:16:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:15:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "3000", "Newest": "289696" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:14:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:13:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "20500", "Newest": "1980290" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:12:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "500", "Newest": "48288" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:11:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "1500", "Newest": "144832" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:10:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:09:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.55", "DNum": "5000", "Newest": "482784" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:08:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:07:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.55", "DNum": "6000", "Newest": "579296" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:06:00", "Opened": "96.6", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.55", "DNum": "1000", "Newest": "96576" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:05:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "500", "Newest": "48288" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:04:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "16500", "Newest": "1593180" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:03:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.5", "DNum": "3952", "Newest": "380992" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:02:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "5000", "Newest": "482496" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:01:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 14:00:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:59:00", "Opened": "96.55", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.5", "DNum": "1500", "Newest": "144736" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:58:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.55", "DNum": "1500", "Newest": "144768" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:57:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:56:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.55", "DNum": "6000", "Newest": "579040" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:55:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "7000", "Newest": "675488" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:54:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "4000", "Newest": "386208" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:53:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:52:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.55", "DNum": "9000", "Newest": "868608" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:51:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "500", "Newest": "48256" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:50:00", "Opened": "96.55", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.5", "DNum": "8000", "Newest": "772032" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:49:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "5500", "Newest": "530752" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:48:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.55", "DNum": "1500", "Newest": "144768" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:47:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "1000", "Newest": "96544" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:46:00", "Opened": "96.6", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.55", "DNum": "2000", "Newest": "193120" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:45:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.6", "Closed": "96.6", "DNum": "37500", "Newest": "3620740" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:44:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "2500", "Newest": "241248" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:43:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "8000", "Newest": "771712" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:42:00", "Opened": "96.5", "Lowest": "96.45", "Highest": "96.55", "Closed": "96.5", "DNum": "15500", "Newest": "1495740" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:41:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "1000", "Newest": "96480" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:40:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "1000", "Newest": "96512" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:39:00", "Opened": "96.45", "Lowest": "96.45", "Highest": "96.55", "Closed": "96.55", "DNum": "16000", "Newest": "1543940" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:38:00", "Opened": "96.5", "Lowest": "96.45", "Highest": "96.5", "Closed": "96.45", "DNum": "12000", "Newest": "1157980" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:37:00", "Opened": "96.5", "Lowest": "96.45", "Highest": "96.5", "Closed": "96.5", "DNum": "36500", "Newest": "3522210" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:36:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:35:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.5", "DNum": "3000", "Newest": "289504" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:34:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "1000", "Newest": "96544" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:33:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "6000", "Newest": "579296" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:32:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:31:00", "Opened": "96.55", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.5", "DNum": "6000", "Newest": "579232" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:30:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "6500", "Newest": "627616" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:29:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "500", "Newest": "48288" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:28:00", "Opened": "96.45", "Lowest": "96.45", "Highest": "96.6", "Closed": "96.6", "DNum": "18500", "Newest": "1785500" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:27:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "2000", "Newest": "192992" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:26:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "62500", "Newest": "6031230" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:25:00", "Opened": "96.55", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.5", "DNum": "2000", "Newest": "193024" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:24:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.55", "DNum": "1500", "Newest": "144768" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:23:00", "Opened": "96.55", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.5", "DNum": "1000", "Newest": "96512" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:22:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "500", "Newest": "48256" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:21:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:20:00", "Opened": "96.6", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.55", "DNum": "4000", "Newest": "386208" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:19:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "1000", "Newest": "96576" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:18:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:17:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:16:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "43500", "Newest": "4200420" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:15:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "36000", "Newest": "3475780" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:14:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:13:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "1000", "Newest": "96544" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:12:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "500", "Newest": "48288" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:11:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:10:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "1000", "Newest": "96544" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:09:00", "Opened": "96.6", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.55", "DNum": "1500", "Newest": "144864" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:08:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:07:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "2500", "Newest": "241376" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:06:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "6000", "Newest": "579392" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:05:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "500", "Newest": "48256" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:04:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "3000", "Newest": "289760" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:03:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "1000", "Newest": "96576" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:02:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 13:01:00", "Opened": "96.6", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "31684", "Newest": "3060030" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 12:00:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.65", "Closed": "96.65", "DNum": "16500", "Newest": "1594660" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:59:00", "Opened": "96.6", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.55", "DNum": "7500", "Newest": "724512" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:58:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "1500", "Newest": "144896" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:57:00", "Opened": "96.6", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.55", "DNum": "6500", "Newest": "627840" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:56:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "1868", "Newest": "180160" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:55:00", "Opened": "96.6", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "30500", "Newest": "2945440" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:54:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "1000", "Newest": "96512" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:53:00", "Opened": "96.55", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.5", "DNum": "1500", "Newest": "144736" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:52:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:51:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:50:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:49:00", "Opened": "96.55", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.55", "DNum": "16000", "Newest": "1544610" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:48:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "1500", "Newest": "144832" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:47:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:46:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:45:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:44:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "2000", "Newest": "193120" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:43:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "1000", "Newest": "96544" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:42:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:41:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "7000", "Newest": "675904" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:40:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "1000", "Newest": "96544" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:39:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:38:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "500", "Newest": "48288" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:37:00", "Opened": "96.6", "Lowest": "96.6", "Highest": "96.6", "Closed": "96.6", "DNum": "15000", "Newest": "1448900" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:36:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "500", "Newest": "48320" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:35:00", "Opened": "96.6", "Lowest": "96.5", "Highest": "96.6", "Closed": "96.55", "DNum": "2500", "Newest": "241440" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:34:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.6", "DNum": "3000", "Newest": "289760" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:33:00", "Opened": "96.6", "Lowest": "96.55", "Highest": "96.6", "Closed": "96.55", "DNum": "10500", "Newest": "1014080" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:32:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "33000", "Newest": "3186140" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:31:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.55", "DNum": "1500", "Newest": "144800" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:30:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "21500", "Newest": "2074780" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:29:00", "Opened": "96.45", "Lowest": "96.45", "Highest": "96.45", "Closed": "96.45", "DNum": "500", "Newest": "48224" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:28:00", "Opened": "96.45", "Lowest": "96.45", "Highest": "96.5", "Closed": "96.5", "DNum": "1735", "Newest": "167200" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:27:00", "Opened": "96.5", "Lowest": "96.45", "Highest": "96.5", "Closed": "96.45", "DNum": "2000", "Newest": "192928" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:26:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "5000", "Newest": "482400" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:25:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "7000", "Newest": "675360" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:24:00", "Opened": "96.45", "Lowest": "96.45", "Highest": "96.5", "Closed": "96.5", "DNum": "13500", "Newest": "1302240" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:23:00", "Opened": "96.5", "Lowest": "96.45", "Highest": "96.5", "Closed": "96.45", "DNum": "2500", "Newest": "241152" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:22:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "2500", "Newest": "241248" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:21:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "4000", "Newest": "386016" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:20:00", "Opened": "96.5", "Lowest": "96.45", "Highest": "96.5", "Closed": "96.5", "DNum": "2500", "Newest": "241184" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:19:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "4000", "Newest": "386016" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:18:00", "Opened": "96.5", "Lowest": "96.45", "Highest": "96.5", "Closed": "96.45", "DNum": "3000", "Newest": "289440" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:17:00", "Opened": "96.45", "Lowest": "96.45", "Highest": "96.5", "Closed": "96.5", "DNum": "1000", "Newest": "96480" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:16:00", "Opened": "96.45", "Lowest": "96.45", "Highest": "96.5", "Closed": "96.45", "DNum": "14000", "Newest": "1350940" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:15:00", "Opened": "96.5", "Lowest": "96.45", "Highest": "96.55", "Closed": "96.45", "DNum": "8000", "Newest": "772064" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:14:00", "Opened": "96.55", "Lowest": "96.55", "Highest": "96.55", "Closed": "96.55", "DNum": "6500", "Newest": "627584" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:13:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.55", "DNum": "2052", "Newest": "197600" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:12:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "2000", "Newest": "192992" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:11:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:10:00", "Opened": "96.45", "Lowest": "96.45", "Highest": "96.55", "Closed": "96.5", "DNum": "24000", "Newest": "2316320" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:09:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.55", "Closed": "96.5", "DNum": "32000", "Newest": "3089570" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:08:00", "Opened": "96.5", "Lowest": "96.5", "Highest": "96.5", "Closed": "96.5", "DNum": "9000", "Newest": "868512" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:07:00", "Opened": "96.45", "Lowest": "96.45", "Highest": "96.55", "Closed": "96.5", "DNum": "57500", "Newest": "5548530" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:06:00", "Opened": "96.4", "Lowest": "96.4", "Highest": "96.45", "Closed": "96.45", "DNum": "6000", "Newest": "578704" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:05:00", "Opened": "96.35", "Lowest": "96.35", "Highest": "96.4", "Closed": "96.4", "DNum": "36500", "Newest": "3517520" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:04:00", "Opened": "96.3", "Lowest": "96.3", "Highest": "96.35", "Closed": "96.35", "DNum": "2000", "Newest": "192624" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:03:00", "Opened": "96.3", "Lowest": "96.3", "Highest": "96.3", "Closed": "96.3", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:02:00", "Opened": "96.35", "Lowest": "96.3", "Highest": "96.35", "Closed": "96.3", "DNum": "11500", "Newest": "1107710" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:01:00", "Opened": "96.3", "Lowest": "96.3", "Highest": "96.35", "Closed": "96.35", "DNum": "4000", "Newest": "385408" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 11:00:00", "Opened": "96.3", "Lowest": "96.3", "Highest": "96.3", "Closed": "96.3", "DNum": "1000", "Newest": "96304" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:59:00", "Opened": "96.35", "Lowest": "96.35", "Highest": "96.35", "Closed": "96.35", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:58:00", "Opened": "96.3", "Lowest": "96.3", "Highest": "96.35", "Closed": "96.35", "DNum": "500", "Newest": "48176" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:57:00", "Opened": "96.25", "Lowest": "96.25", "Highest": "96.3", "Closed": "96.3", "DNum": "14000", "Newest": "1348190" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:56:00", "Opened": "96.2", "Lowest": "96.2", "Highest": "96.25", "Closed": "96.25", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:55:00", "Opened": "96.2", "Lowest": "96.2", "Highest": "96.2", "Closed": "96.2", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:54:00", "Opened": "96.2", "Lowest": "96.2", "Highest": "96.25", "Closed": "96.2", "DNum": "39500", "Newest": "3800540" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:53:00", "Opened": "96.2", "Lowest": "96.15", "Highest": "96.2", "Closed": "96.15", "DNum": "20000", "Newest": "1923340" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:52:00", "Opened": "96.2", "Lowest": "96.15", "Highest": "96.2", "Closed": "96.15", "DNum": "500", "Newest": "48080" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:51:00", "Opened": "96.2", "Lowest": "96.2", "Highest": "96.2", "Closed": "96.2", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:50:00", "Opened": "96.15", "Lowest": "96.15", "Highest": "96.2", "Closed": "96.2", "DNum": "5500", "Newest": "528864" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:49:00", "Opened": "96.15", "Lowest": "96.15", "Highest": "96.15", "Closed": "96.15", "DNum": "500", "Newest": "48064" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:48:00", "Opened": "96.2", "Lowest": "96.2", "Highest": "96.2", "Closed": "96.2", "DNum": "500", "Newest": "48096" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:47:00", "Opened": "96.15", "Lowest": "96.15", "Highest": "96.2", "Closed": "96.2", "DNum": "2000", "Newest": "192416" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:46:00", "Opened": "96.15", "Lowest": "96.15", "Highest": "96.15", "Closed": "96.15", "DNum": "1500", "Newest": "144224" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:45:00", "Opened": "96.15", "Lowest": "96.15", "Highest": "96.15", "Closed": "96.15", "DNum": "266", "Newest": "25584" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:44:00", "Opened": "96.15", "Lowest": "96.15", "Highest": "96.15", "Closed": "96.15", "DNum": "500", "Newest": "48080" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:43:00", "Opened": "96.15", "Lowest": "96.15", "Highest": "96.15", "Closed": "96.15", "DNum": "500", "Newest": "48064" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:42:00", "Opened": "96.15", "Lowest": "96.15", "Highest": "96.15", "Closed": "96.15", "DNum": "1000", "Newest": "96160" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:41:00", "Opened": "96.15", "Lowest": "96.15", "Highest": "96.15", "Closed": "96.15", "DNum": "4000", "Newest": "384592" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:40:00", "Opened": "96.15", "Lowest": "96.15", "Highest": "96.15", "Closed": "96.15", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:39:00", "Opened": "96.1", "Lowest": "96.1", "Highest": "96.15", "Closed": "96.15", "DNum": "1500", "Newest": "144224" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:38:00", "Opened": "96.15", "Lowest": "96.1", "Highest": "96.15", "Closed": "96.1", "DNum": "500", "Newest": "48048" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:37:00", "Opened": "96.15", "Lowest": "96.15", "Highest": "96.15", "Closed": "96.15", "DNum": "3500", "Newest": "336528" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:36:00", "Opened": "96.15", "Lowest": "96.15", "Highest": "96.15", "Closed": "96.15", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:35:00", "Opened": "96.1", "Lowest": "96.1", "Highest": "96.2", "Closed": "96.15", "DNum": "38000", "Newest": "3653100" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:34:00", "Opened": "96.05", "Lowest": "96.05", "Highest": "96.05", "Closed": "96.05", "DNum": "4000", "Newest": "384304" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:33:00", "Opened": "96.1", "Lowest": "96.1", "Highest": "96.1", "Closed": "96.1", "DNum": "2500", "Newest": "240224" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:32:00", "Opened": "96.05", "Lowest": "96.05", "Highest": "96.1", "Closed": "96.1", "DNum": "1500", "Newest": "144128" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:31:00", "Opened": "96.05", "Lowest": "96.05", "Highest": "96.05", "Closed": "96.05", "DNum": "3365", "Newest": "323152" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:30:00", "Opened": "96.15", "Lowest": "96", "Highest": "96.2", "Closed": "96.1", "DNum": "50472", "Newest": "4849940" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:29:00", "Opened": "96.1", "Lowest": "96.1", "Highest": "96.15", "Closed": "96.15", "DNum": "12500", "Newest": "1201860" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:28:00", "Opened": "96.1", "Lowest": "96.1", "Highest": "96.1", "Closed": "96.1", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:27:00", "Opened": "96.1", "Lowest": "96.1", "Highest": "96.1", "Closed": "96.1", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:26:00", "Opened": "96.1", "Lowest": "96.1", "Highest": "96.1", "Closed": "96.1", "DNum": "1500", "Newest": "144144" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:25:00", "Opened": "96.1", "Lowest": "96.1", "Highest": "96.1", "Closed": "96.1", "DNum": "3000", "Newest": "288432" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:24:00", "Opened": "96.15", "Lowest": "96.15", "Highest": "96.15", "Closed": "96.15", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:23:00", "Opened": "96.15", "Lowest": "96.15", "Highest": "96.15", "Closed": "96.15", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:22:00", "Opened": "96.1", "Lowest": "96.05", "Highest": "96.15", "Closed": "96.15", "DNum": "18500", "Newest": "1778500" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:21:00", "Opened": "96.1", "Lowest": "96.1", "Highest": "96.1", "Closed": "96.1", "DNum": "2000", "Newest": "192192" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:20:00", "Opened": "96.1", "Lowest": "96.1", "Highest": "96.1", "Closed": "96.1", "DNum": "1500", "Newest": "144160" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:19:00", "Opened": "96.1", "Lowest": "96.1", "Highest": "96.1", "Closed": "96.1", "DNum": "3000", "Newest": "288304" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:18:00", "Opened": "96.05", "Lowest": "96.05", "Highest": "96.1", "Closed": "96.1", "DNum": "11000", "Newest": "1056980" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:17:00", "Opened": "96.05", "Lowest": "96.05", "Highest": "96.05", "Closed": "96.05", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:16:00", "Opened": "96.05", "Lowest": "96.05", "Highest": "96.05", "Closed": "96.05", "DNum": "7500", "Newest": "720432" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:15:00", "Opened": "96.05", "Lowest": "96.05", "Highest": "96.05", "Closed": "96.05", "DNum": "2500", "Newest": "240128" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:14:00", "Opened": "96.1", "Lowest": "96.05", "Highest": "96.1", "Closed": "96.05", "DNum": "2830", "Newest": "271536" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:13:00", "Opened": "96.1", "Lowest": "96.05", "Highest": "96.1", "Closed": "96.1", "DNum": "2500", "Newest": "240160" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:12:00", "Opened": "96.05", "Lowest": "96.05", "Highest": "96.1", "Closed": "96.1", "DNum": "5500", "Newest": "528368" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:11:00", "Opened": "96.1", "Lowest": "96.05", "Highest": "96.1", "Closed": "96.05", "DNum": "1500", "Newest": "144080" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:10:00", "Opened": "96.05", "Lowest": "96.05", "Highest": "96.1", "Closed": "96.1", "DNum": "5700", "Newest": "547696" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:09:00", "Opened": "96.1", "Lowest": "96.1", "Highest": "96.1", "Closed": "96.1", "DNum": "8000", "Newest": "768800" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:08:00", "Opened": "96.1", "Lowest": "96.1", "Highest": "96.15", "Closed": "96.15", "DNum": "4000", "Newest": "384576" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:07:00", "Opened": "96.1", "Lowest": "96.1", "Highest": "96.1", "Closed": "96.1", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:06:00", "Opened": "96.2", "Lowest": "96.1", "Highest": "96.2", "Closed": "96.1", "DNum": "22000", "Newest": "2115550" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:05:00", "Opened": "96.2", "Lowest": "96.15", "Highest": "96.2", "Closed": "96.15", "DNum": "18000", "Newest": "1731250" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:04:00", "Opened": "96.25", "Lowest": "96.2", "Highest": "96.25", "Closed": "96.2", "DNum": "500", "Newest": "48096" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:03:00", "Opened": "96.1", "Lowest": "96.1", "Highest": "96.25", "Closed": "96.25", "DNum": "15000", "Newest": "1442530" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:02:00", "Opened": "96.15", "Lowest": "96.15", "Highest": "96.15", "Closed": "96.15", "DNum": "3000", "Newest": "288448" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:01:00", "Opened": "96.15", "Lowest": "96.1", "Highest": "96.15", "Closed": "96.15", "DNum": "8000", "Newest": "768880" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 10:00:00", "Opened": "96", "Lowest": "96", "Highest": "96.25", "Closed": "96.2", "DNum": "51500", "Newest": "4949070" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 09:59:00", "Opened": "96", "Lowest": "95.95", "Highest": "96.05", "Closed": "96", "DNum": "21000", "Newest": "2015730" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 09:58:00", "Opened": "96", "Lowest": "96", "Highest": "96", "Closed": "96", "DNum": "0", "Newest": "0" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 09:57:00", "Opened": "96", "Lowest": "96", "Highest": "96", "Closed": "96", "DNum": "500", "Newest": "48000" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 09:56:00", "Opened": "95.95", "Lowest": "95.95", "Highest": "96", "Closed": "96", "DNum": "12500", "Newest": "1199940" }, { "Symbol": "HK00001", "Name": "长和", "DealDate": "2016-10-14 09:55:00", "Opened": "96.05", "Lowest": "96", "Highest": "96.05", "Closed": "96", "DNum": "1500", "Newest": "144032" }];
				if(_Option.ChartType == "1"){
					_This.CreatTimeChart(data,_Option); //创建分时图
				}else{
					_This.CreateCandlestick(data,_Option); //创建K线图
				}
			}
		},
		/**
		 * @content 创建分时图所需要的数据
		 * @param data 绘图所需要的数据
		 * @param _Option 绘图所需要的配置文件
		 */
		CreatTimeChart:function(data,_Option){
			var dataLength = data.length;
            var start = 0;
            var volume = new Array(), //volum
                svgprice = new Array(); //Svgprice    
            
            for (i = dataLength - 1; 0 <= i ; i--) {

                /* var _open = Number(data[i].Opened), //开盘价
                _hight = Number(data[i].Highest), //最高价
                _low = Number(data[i].Lowest), //最低价
                _close = Number(data[i].Closed), //收盘价
                _quantity = Number(data[i].DNum), //交易量  
                _Newest = Number(data[i].Newest), //最新价
                
                _Time = new Date(data[i].DealDate); 
                _Time = parseInt(_Time.getTime()); */
            	
            	var _quantity = 0; // 交易量 
            	var _Time = parseInt(data[i].time) * 1000; // 时间
            	var _Newest = Number(data[i].price); // 最新价
                
                if(isNaN(_Time)){
                	var _DateSaifar_Str = data[i].DealDate.replace(/[-]/g,"/");
                	console.log(_DateSaifar_Str);
                	_Time = new Date(_DateSaifar_Str);
                	_Time = _Time.getTime();
                }

                svgprice.push([_Time, _Newest]);

                var volume_dataObj = {
                    x: _Time,
                    y: _quantity,
                    color: '#E34B50'
                }
                volume.push(volume_dataObj);  
                _Least_Data = data[i];
            }
            if (volume.length > 0) {
                _This.Show_HighFSCharts(svgprice, volume); 
            }
		},
		/**
		 * @content 绘制分时图的JS
		 * @param ohlc 分时图的数据
		 * @param volume 交易量的数据
		 */
		Show_HighFSCharts:function(svgprice, volume){
			var _ChartGlobal_Option = _This.GetGlobal_Options(), //获取全局highchart配置
            _Chart = undefined, //重置全局变量 _Chart
            _ChartObject = _Option.ProChartObj,//图表容器对象
            _PointLen = svgprice.length, //所有数据点的长度 
            _RuiChart; //创建一个空的图表对象  
	
	        //设置图表的基本配置信息
	        H.setOptions(_ChartGlobal_Option); 
	
	        var _ChartOption = _This.HStockChartOption(), //图表chart配置信息设定 
	            _NavigatorOption = _This.HStockNavigatorOption(), //处理获取的图表滚动区域的数据配置
	            _ScrollbarOption = _This.HStockScrollbarOption(), //处理获取的图表滚动区域的数据配置2 
	            _TooltipOptions = _This.HStockTooltipOption(), //处理图表当中的每个节点，获得焦点时浮动提示框的配置
	            _RangeSelector = _This.HStockRangeSelectorOption(), // 处理RangeSelect配置项
	            _Credits = _This.HStockCreditsOption(), // 处理credits配置项
	            _Exporting = _This.HStockExportingOption(); // 处理_Exporting配置项
	            
	        var _PlotOptions = {
	                line: {
	                    marker: {
	                        states: {
	                            hover: {
	                                enabled: false
	                            }
	                        }
	                    },
	                    states: {
	                        hover: {
	                            lineWidth: 1,
	                            lineWidthPlus: 1
	                        }
	                    }
	                } 
	            };
	
	        //_XAxis = _This.HStock_XAxisOption(_InitRanges);  //处理Y轴的属性以及X轴分配
	        var _XAxis = {
	            lineColor: "#2a2a2a", //x轴线的颜色
	            tickColor: "#2a2a2a", //x轴每个节点的标签颜色  
	            tickPixelInterval:25, //每个刻度之间的间隔像素
	            labels: {
	                x: -3,
	                step: 5  //间隔多少个刻度显示一个label  
	            }
	        };
	
	        var _YAxis = [{
	                crosshair: {
	                    color: "#666666",
	                    snap: false,
	                    width: 1
	                },
	                opposite: true,
	                align: "left", 
	                gridLineColor: '#232228',
	                gridLineDashStyle: 'LongDash',
	                title: {
	                    text: '',
	                    style: { color: '#666666' }
	                },
	                lineColor: "#2a2a2a", 
	                lineWidth: 1,
	                offset: 0,
	                tickColor: "#2a2a2a", //x轴每个节点的标签颜色
	                labels: {
	                    align: 'left',
	                    style: {
	                        color: '#666666'
	                    },
	                    x: 5,
	                    y: 3
	                }
	            }];  //处理Y轴的属性以及Y轴分配
	
	        var _SeriseOption = [{
	                animation: false, 
	                name: '现价',
	                data: svgprice,
	                type: 'line', 
	                color: '#a4a1b2',
	                lineWidth: 1 
	            }];  //处理Y轴的属性以及Y轴分配
	
	
	        RHChart = _ChartObject.highcharts('StockChart', {
	            chart: _ChartOption,
	            navigator: _NavigatorOption,
	            scrollbar: _ScrollbarOption,
	            plotOptions: _PlotOptions,
	            tooltip: _TooltipOptions,
	            rangeSelector: _RangeSelector,
	            exporting: _Exporting,
	            credits: _Credits,
	            xAxis: _XAxis,
	            yAxis: _YAxis,
	            series: _SeriseOption
	        });
		},
		/**
		 * @content 创建蜡烛图
		 * @param data 绘图所需要的数据
		 * @param _Option 绘图所需要的配置文件
		 */
		CreateCandlestick:function(data,_Option){ 
            var dataLength = data.length;
            var start = 0;
            var ohlc = new Array(),  //蜡烛图
                volume = new Array(),   //成交量
                GetMAArrays = new Array(), //获取MA需要的临时存储数组
                ma1 = new Array(),  //MA1
                ma5 = new Array(),  //MA5 
                ma15 = new Array(),  //MA15
                ma30 = new Array(),  //MA30
                ma60 = new Array(),  //MA60
                macd = new Array(),  //MACD
                dea = new Array(),  //Dea
                dif = new Array();  //Dif   

            for (i = dataLength-1; 0 <= i ; i--) {

                var _open = Number(data[i].open), // 开盘价
                _hight = Number(data[i].minuteHighest), // 最高价
                _low = Number(data[i].minuteLowest), // 最低价
                _close = Number(data[i].close), // 收盘价
                _quantity = 0; // 交易量  
                // _Time = new Date(data[i].DealDate); 
                // _Time = _Time.getTime();   
                
                var _Time = parseInt(data[i].time) * 1000; 
                if(isNaN(_Time)){
                	var _DateSaifar_Str = data[i].DealDate.replace(/[-]/g,"/");
                	console.log(_DateSaifar_Str);
                	_Time = new Date(_DateSaifar_Str);
                	_Time = _Time.getTime();
                }
                
                ohlc.push([_Time, _open, _hight, _low, _close]); //蜡烛图  
                if (_open < _close) {
                    var volume_dataObj = {
                        x: _Time,
                        y: _quantity,
                        color: '#E34B50'
                    }
                } else {
                    var volume_dataObj = {
                        x: _Time,
                        y: _quantity,
                        color: '#2FC297'
                    }
                }
                volume.push(volume_dataObj); //成交量  
                _Least_Data = data[i];
            }
            if (ohlc.length > 0 && volume.length > 0) {
                _This.Show_HighStrock(ohlc, volume);  
            }
		}, 
		/**
		 * @content 绘制K线图的JS
		 * @param ohlc K线图的数据
		 * @param volume 交易量的数据
		 */
		Show_HighStrock: function (ohlc, volume) {
			var _ChartGlobal_Option = _This.GetGlobal_Options(), //获取全局highchart配置
            _Chart = undefined, //重置全局变量 _Chart
            _ChartObject = _Option.ProChartObj,//图表容器对象
            _PointLen = ohlc.length, //所有数据点的长度 
            _RuiChart; //创建一个空的图表对象  
	
	        //设置图表的基本配置信息
	        H.setOptions(_ChartGlobal_Option);    
	        
	        var _ChartOption = _This.HStockChartOption(), //图表chart配置信息设定 
	            _NavigatorOption = _This.HStockNavigatorOption(), //处理获取的图表滚动区域的数据配置
	            _ScrollbarOption = _This.HStockScrollbarOption(), //处理获取的图表滚动区域的数据配置2
	            _PlotOptions = _This.HStockPlotOptions(), //处理图表当中的每个节点的自定义配置属性
	            _TooltipOptions = _This.HStockTooltipOption(), //处理图表当中的每个节点，获得焦点时浮动提示框的配置
	            _RangeSelector = _This.HStockRangeSelectorOption(), // 处理RangeSelect配置项
	            _Credits = _This.HStockCreditsOption(), // 处理credits配置项
	            _Exporting = _This.HStockExportingOption(), // 处理_Exporting配置项 
	            _XAxis = _This.HStock_XAxisOption(),  //处理Y轴的属性以及X轴分配
	            _YAxis = _This.HStock_YAxisOption(),  //处理Y轴的属性以及Y轴分配
	            _SeriseOption = _This.HStock_SeriseOption(ohlc);  //处理Y轴的属性以及Y轴分配
	
	
	        RHChart = _ChartObject.highcharts('StockChart', {
	            chart: _ChartOption,
	            navigator: _NavigatorOption,
	            scrollbar: _ScrollbarOption,
	            plotOptions: _PlotOptions,
	            tooltip: _TooltipOptions,
	            rangeSelector: _RangeSelector,
	            exporting: _Exporting,
	            credits: _Credits,
	            xAxis: _XAxis,
	            yAxis: _YAxis,
	            series: _SeriseOption
	        }); 
		}, 
        /**
         * @content 设定highchart全局基本配置数据
         * @param {Object} Option 自定义配置信息
         * @return {Object} 图表的基本配置
         */
        GetGlobal_Options: function () {
            var GlobalOption = {
                colors: ['#FF6060', '#416C9E', '#DDDF0D', '#7798BF', '#55BF3B', '#DF5353', '#aaeeee', '#ff0066', '#eeaaee', '#55BF3B', '#DF5353', '#7798BF', '#aaeeee'],
                lang: {
                    loading: 'Loading...',
                    months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                    shortMonths: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                    weekdays: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                    decimalPoint: '.',
                    numericSymbols: ['k', 'M', 'G', 'T', 'P', 'E'],
                    resetZoom: 'Reset zoom',
                    resetZoomTitle: 'Reset zoom level 1:1',
                    thousandsSep: ','
                },
                credits: {
                    enabled: false
                },
                global: {
                    useUTC: false,
                    Date: false
                }
            };

            return GlobalOption;
        },
        /**
        * @content 处理获取的图标数据使之成为一个个绘图点的对象值
        * @param {Object} Option 自定义配置信息
        */
        HStockChartOption: function () {
            var _HstockChartOption = {
                alignTicks: false,
                zoomType: 'none',
                backgroundColor: "#232228"
            };
            return _HstockChartOption;
        },
        /**
        * @content 处理获取的图表滚动区域的数据配置
        * @param {Object} Option 自定义配置信息
        */
        HStockNavigatorOption: function () {
            var _NavigatorOption = { enabled: false };
            return _NavigatorOption;
        },
        /**
        * @content 处理获取的图表滚动区域的数据配置
        * @param {Object} Option 自定义配置信息
        */
        HStockScrollbarOption: function () {
            var _ScrollbarOption = { enabled: false };
            return _ScrollbarOption;
        },
        /**
        * @content 处理图表当中的每个节点的自定义配置属性
        * @param {Object} Option 自定义配置信息
        */
        HStockPlotOptions: function () {
            var _PlotOptions = {
                candlestick: {
                    color: "#2FC297",
                    lineColor: "#2FC297",
                    upColor: "#E34B50",
                    upLineColor: "#E34B50",
                    upfillColor: "#E34B50",
                    states: {
                        hover: {
                            enabled: false
                        }
                    }
                } 
            };
            return _PlotOptions;
        },
        /**
        * @content 处理图表当中的每个节点，获得焦点时浮动提示框的配置
        * @param {Object} Option 自定义配置信息
        */
        HStockTooltipOption: function () {
            var _TooltipOptions = {
                shared: true 
            };
            return _TooltipOptions;
        },
        /**
        * @content 处理RangeSelect配置项
        * @param {Object} Option 自定义配置信息
        */
        HStockRangeSelectorOption: function () {
            var _RangeSelector = { enabled: false };
            return _RangeSelector;
        },
        /**
        * @content 处理Exporting配置项
        * @param {Object} Option 自定义配置信息
        */
        HStockExportingOption: function () {
            var _Exporting = { enabled: false, buttons: { exportButton: { enabled: false }, printButton: { enabled: true } } };
            return _Exporting;
        },
        /**
        * @content 处理credits配置项
        * @param {Object} Option 自定义配置信息
        */
        HStockCreditsOption: function () {
            var _Credits = { text: '',enabled:false };
            return _Credits;
        },
        /**
        * @content 处理X轴的属性以及X轴分配
        * @param {Object} Option 自定义配置信息
        */
        HStock_XAxisOption: function (InitRange) {
            if (typeof InitRange == "undefined") { InitRange = 8640000; }
            var _XAxisOption = {
            		crosshair: {
                        color: "#666666",
                        snap: false,
                        width: 1
                    },
	                lineColor: "#2a2a2a", //x轴线的颜色
	                tickColor: "#2a2a2a", //x轴每个节点的标签颜色  
		            tickPixelInterval:25, //每个刻度之间的间隔像素
	                labels: {
	                    x: -3,
	                    step: 5  //间隔多少个刻度显示一个label  
	                } 
            	};
            return _XAxisOption;
        },
        /**
        * @content 处理Y轴的属性以及Y轴分配
        * @param {Object} Option 自定义配置信息
        */
        HStock_YAxisOption: function () {
            var _YAxisOption = [{
                crosshair: {
                    color: "#666666",
                    snap: false,
                    width: 1
                },
                opposite: true,
                align: "left",
                showFirstLabel: true,
                showLastLabel: true,
                gridLineColor: '#232228',
                gridLineDashStyle: 'LongDash',
                title: {
                    text: '',
                    style: { color: '#666666' }
                },
                lineColor: "#2a2a2a", 
                lineWidth: 1,
                offset: 0,
                tickColor: "#2a2a2a", //x轴每个节点的标签颜色
                labels: {
                    align: 'left',
                    style: {
                        color: '#666666'
                    },
                    x: 5,
                    y: 3
                }
            }];
            return _YAxisOption;
        },
        /**
        * @content 配置serise属性的值（各类图表对象的值）
        * @param {Array} ohlc  
        */
        HStock_SeriseOption: function (ohlc) {
            var _SeriseOption = [{
                data: ohlc,
                type: 'candlestick',
                name: '价格',
                yAxis: 0,
                lineWidth: 1,
                pointAttrToOptions: {
                    fill: 'color',
                    stroke: 'lineColor'
                }
            }];
            return _SeriseOption;
        },
        /**
         * @content 处理推送过来的数据
         * @param {Array} data  
         */
        DealSocketData:function(data){
        	try{
        		data = $.parseJSON(data);
        	}catch(e){
        		console.log(e);
        	}
        	if(typeof data == "object" && data.length > 0){
        		for(var i = 0 ; i < data.length;i++){
        			var _proCode = data[i].code;	
        			//console.log(_proCode);
        			if($("#list .product_obj[pro_code='"+_proCode+"']").size()>0){
        				var latestPrice = parseFloat(data[i].price); // 最新价格
    					var priceScale = 0; // 最新价格精度
    					if (new String(latestPrice).indexOf(".") > -1) { // 最新价格包含小数点
    						priceScale = new String(latestPrice).split(".")[1].length;
    					}
    					var fixedScale = priceScale; // 确定四舍五入的精度
        				var proObj = $("#list .product_obj[pro_code='"+_proCode+"']").eq(0);
        				proObj.find(".price-up").eq(0).find("em").eq(0).empty().text(latestPrice.toFixed(fixedScale)); // 精度处理
        				proObj.find(".price-down").eq(0).empty().text(latestPrice.toFixed(fixedScale));  
        				if(proObj.find(".cont").eq(0).hasClass("curr")){
        					proObj.find(".lighest").eq(0).empty().text(parseFloat(data[i].minuteHighest).toFixed(fixedScale)); // 精度处理
        					proObj.find(".leastPrice").eq(0).empty().text(latestPrice.toFixed(fixedScale));  
        					proObj.find(".lowest").eq(0).empty().text(parseFloat(data[i].minuteLowest).toFixed(fixedScale));  
        					if(proObj.find(".chart_ico.curr").length>0){
        						var ChartObj = $("#chart_box_"+_proCode).highcharts();
        						var _theIndex = proObj.find(".chart_ico.curr").eq(0).index();
        						if(_theIndex>0){
        							var series_ohlc = ChartObj.series[0]; //蜡烛图
        							var _open = Number(data[i].minuteOpen); //开盘价
                                    var _hight = Number(data[i].minuteHighest); //最高价
                                    var _low = Number(data[i].minuteLowest); //最低价
                                    var _close = Number(data[i].minuteClose); //收盘价  
                                    var _Time = Number(data[i].time) * 1000; //时间   
                                    
                                    var _MaxTimes = ChartObj.xAxis[0].max; //x轴的最大值
                                    _MaxTimes = _MaxTimes/60000;
                                    _MaxTimes = Math.floor(_MaxTimes);
                                    
                                    var TimeStr = _Time / 60000; 
                                    TimeStr = Math.floor(TimeStr);
                                    
                                    var MinDate = ChartObj.xAxis[0].min;
                                    var ohlc_AddPoint = new Array();
                                    
                                    ohlc_AddPoint.push(_Time);
                                    ohlc_AddPoint.push(_open);
                                    ohlc_AddPoint.push(_hight);
                                    ohlc_AddPoint.push(_low);
                                    ohlc_AddPoint.push(_close);
                                    
                                    if(_MaxTimes==TimeStr){
                                    	//修改
                                    	var _theLength = series_price.data.length - 1; 
                                    	if(_theLength>1){
                                    		series_ohlc.removePoint(_theLength, true, true); 
                                    	}else{
                                    		return true;
                                    	}
                                    	series_ohlc.addPoint(ohlc_AddPoint, true, false); 
                                         
                                        Max_XValue = _Time;
                                        ChartObj.xAxis[0].setExtremes(MinDate, Max_XValue, true, false);

                                    }else if(TimeStr>_MaxTimes){
                                    	//新增
                                    	series_ohlc.addPoint(ohlc_AddPoint, true, false); 
                                    	Max_XValue = _Time;
                                    	ChartObj.xAxis[0].setExtremes(MinDate, Max_XValue, true, false);
                                    }  
        						}else{
        							
        							var series_svgPrice = ChartObj.series[0]; //蜡烛图
        							var _Price = Number(data[i].price); //最新价 
                                    var _Time = Number(data[i].time) * 1000; //时间   
                                    
                                    var _MaxTimes = ChartObj.xAxis[0].max; //x轴的最大值
                                    _MaxTimes = _MaxTimes/60000;
                                    _MaxTimes = Math.floor(_MaxTimes);
                                    
                                    var TimeStr = _Time / 60000; 
                                    TimeStr = Math.floor(TimeStr);
                                    
                                    var MinDate = ChartObj.xAxis[0].min;
                                    var price_AddPoint = new Array();
                                    
                                    price_AddPoint.push(_Time);
                                    price_AddPoint.push(_Price); 
                                    
                                    if(_MaxTimes==TimeStr){
                                    	//修改
                                    	var _theLength = series_svgPrice.data.length - 1; 
                                    	if(_theLength>1){ 
                                    		series_svgPrice.removePoint(_theLength, true, true); 
                                    	}else{
                                    		return false;
                                    	}
                                    	series_svgPrice.addPoint(price_AddPoint, true, false); 
                                         
                                        Max_XValue = _Time;
                                        ChartObj.xAxis[0].setExtremes(MinDate, Max_XValue, true, false);

                                    }else if(TimeStr>_MaxTimes){
                                    	//新增
                                    	series_svgPrice.addPoint(price_AddPoint, true, false); 
                                    	Max_XValue = _Time;
                                    	ChartObj.xAxis[0].setExtremes(MinDate, Max_XValue, true, false);
                                    }  
        							
        						}
        					}
        					
        				}
        				 
        			}
        		}
        	}
        }
	}
	
	$.extend({RChart:RChart})
})(jQuery,mui,Highcharts);
 

//DOM Ready Attach Action
mui.ready(function(){
	
	//获取产品的详细信息
	function GetProItemDetail(ProCode,Obj){
		ProCode = ProCode || "";
		if(ProCode!=""){
			$.ajax({
				type:"POST",
				url:"/productItem.shtml",
				data:{
					code:ProCode
				},
				dataType:"html",
				success:function(data){
					Obj.parent().find(".slideContainers").html(data).show(); 
					Obj.hide();
					Obj.parent().find(".slideContainers").find("input[name='pro_code']").val(ProCode);
				}
			})
		}
	} 
	
	//点击发送请求事件
	mui(".Ruiec_Prolist").on("tap",".product_obj a.cont",function(){ 
		var _ProCode = $(this).parent().attr("pro_code");
		if($(this).hasClass("curr")){
			$(this).removeClass("curr");
			$(this).parent().find(".slideContainers").empty();
			$(this).show();   
		}else{
			$(".product_obj a.cont").removeClass("curr");
			$(this).addClass("curr");
			$(this).parent().find(".slideContainers").empty(); 
			var _this = $(this);
			$(this).parent().siblings().find(".slideContainers").slideUp().empty();   
			$(".product_obj a.cont").show();
			GetProItemDetail(_ProCode,_this);
		} 
	}); 
	
	
	//点击发送请求事件
	mui("#list").on("tap",".slideContainers .top",function(){ 
		var product_obj = $(this).parents(".product_obj");
		product_obj.find(".slideContainers").hide(); 
		product_obj.find(".cont").show().removeClass("curr"); 
		$(this).parents(".product_obj").find(".chart_box").removeClass("curr");
	}); 
	
	//绑定图表切换的事件
	function BindChangeChartType(ProCode,ChartType){
		ProCode = ProCode == undefined ? "" : ProCode ;
		ChartType = ChartType == undefined ? 1 : ChartType ; 
		if(ProCode!=""){
			var _RChart = new $.RChart();
			var _Options = {
					ProId:ProCode,
					ChartType:ChartType,
					GetChartApi:"/getLine.shtml",
				}; 
			_RChart.Init(_Options); 
		} 
	}
	
	//切换折线图
	mui("#list").on("tap",".slideContainers .chart_ico",function(event){
		event = event || window.event;
		event.stopPropagation();
		if($(this).hasClass("curr")){
			$(this).removeClass("curr");
			$(this).parents(".product_obj").find(".chart_box").eq(0).hide();
		}else{  
			$(this).siblings().removeClass("curr");
			$(this).addClass("curr"); 
			var _theCode = $(this).parents(".product_obj").attr("pro_code"); //产品的ID
			var _ChartType = $(this).attr("value"); 
			
			BindChangeChartType(_theCode,_ChartType);
		}
	});
	
	//结算类型设定
	mui("#list").on("tap",".submit_form .liquidation_type>span",function(){
		$(this).siblings().removeClass("curr");
		$(this).addClass("curr");
		//结算类型切换
		if ($(this).attr("value") == "stopProfitLoss") {
			$("#liquidationType_stopProfitLoss").show();
			$("#liquidationType_time").hide();
		} else {
			$("#liquidationType_stopProfitLoss").hide();
			$("#liquidationType_time").show();
		}
	});
	
	//对每一个止盈止损点/时间进行设定
	mui("#list").on("tap",".submit_form .zs_level>span",function(){
		$(this).siblings().removeClass("curr");
		$(this).addClass("curr");
		var _theValue = $(this).attr("value");
		var _theValue_id = $(this).attr("id");
		var _theValue_rate = $(this).attr("rate");
		var liquidationType = $(".submit_form .liquidation_type .curr").attr("value");
		if (liquidationType == "stopProfitLoss") {
			$(this).parents(".submit_form").eq(0).find("input[name='rise_level']").eq(0).val(_theValue);
			$(this).parents(".submit_form").eq(0).find("input[name='rise_level']").eq(0).attr("rise_id",_theValue_id);
			$(this).parents(".submit_form").eq(0).find("input[name='rise_level']").eq(0).attr("rate",_theValue_rate);
		} else {
			$(this).parents(".submit_form").eq(0).find("input[name='time_level']").eq(0).val(_theValue);
			$(this).parents(".submit_form").eq(0).find("input[name='time_level']").eq(0).attr("time_id",_theValue_id);
			$(this).parents(".submit_form").eq(0).find("input[name='time_level']").eq(0).attr("rate",_theValue_rate);
		}
	});
	
	//绑定弹窗
	mui("#list").on("tap",".submit_form .sub_btn",function(event){
		event = event || window.event;
		event.stopPropagation();
		//$("#project_review").addClass("mui-active").show();
		//mui('#project_review').popover('show'); 
		
		var _theObj = $(this).parents(".submit_form").eq(0);
		var _trade_type = $(this).attr("value"); //看涨看跌方向
		var _pro_code = _theObj.find("input[name='pro_code']").val(); //产品的Code
		var _pro_name = _theObj.find("input[name='pro_name']").val(); //产品的Name
		var _trade_price = _theObj.find("input[name='trade_price']").val(); //合约订金
		var _rise_level = _theObj.find("input[name='rise_level']").val(); //止盈/止损
		var _rise_level_id = _theObj.find("input[name='rise_level']").attr("rise_id"); //止盈止损 id
		var _rise_level_rate = _theObj.find("input[name='rise_level']").attr("rate"); //收益率(不同止盈止损收益率不同)
		var _time_level = _theObj.find("input[name='time_level']").val(); //到期时间
		var _time_level_id = _theObj.find("input[name='time_level']").attr("time_id"); //到期时间 id
		var _time_level_rate = _theObj.find("input[name='time_level']").attr("rate"); //收益率(不同时间收益率不同)
		
		$('#project_review_stopProfitLoss').find("input[name='pro_code']").val(_pro_code);  //同步产品
		$('#project_review_stopProfitLoss').find(".pro_code").eq(0).empty().text(_pro_code);
		$('#project_review_stopProfitLoss').find(".pro_name").eq(0).empty().text(_pro_name);
		$('#project_review_time').find("input[name='pro_code']").val(_pro_code);  //同步产品
		$('#project_review_time').find(".pro_code").eq(0).empty().text(_pro_code);
		$('#project_review_time').find(".pro_name").eq(0).empty().text(_pro_name);
		
		$('#project_review_stopProfitLoss').find("input[name='trade_price']").val(_trade_price); //合约订金
		$('#project_review_stopProfitLoss').find(".trade_price").eq(0).empty().text(_trade_price); 
		$('#project_review_time').find("input[name='trade_price']").val(_trade_price); //合约订金
		$('#project_review_time').find(".trade_price").eq(0).empty().text(_trade_price); 
		
		$('#project_review_stopProfitLoss').find("input[name='rise_level']").attr("rise_id",_rise_level_id); //同步止盈止损 id 
		$('#project_review_stopProfitLoss').find("input[name='rise_level']").val(_rise_level); //同步止盈止损
		$('#project_review_stopProfitLoss').find(".rise_level").eq(0).empty().text(_rise_level);
		$('#project_review_time').find("input[name='time_level']").attr("time_id",_time_level_id); //同步到期时间 id 
		$('#project_review_time').find("input[name='time_level']").val(_time_level); //同步到期时间
		$('#project_review_time').find(".time_level").eq(0).empty().text(_time_level);
		
		$('#project_review_stopProfitLoss').find("input[name='trade_type']").val(_trade_type);  //同步看涨看跌方向
		$('#project_review_time').find("input[name='trade_type']").val(_trade_type);  //同步看涨看跌方向
		if(_trade_type == "1"){
			//看涨
			$('#project_review_stopProfitLoss').find(".trade_type").eq(0).empty().text("看涨");
			$('#project_review_time').find(".trade_type").eq(0).empty().text("看涨");
		}else{
			//看跌
			$('#project_review_stopProfitLoss').find(".trade_type").eq(0).empty().text("看跌");
			$('#project_review_time').find(".trade_type").eq(0).empty().text("看跌");
		}
		
		var liquidationType = $(".submit_form .liquidation_type .curr").attr("value");
		if (liquidationType == "stopProfitLoss") { // 展示弹窗(按照止盈止损结算)
			$('#project_review_stopProfitLoss').find(".rate").eq(0).empty().text(parseFloat(_rise_level_rate) * 100 + "%");
			$('#project_review_stopProfitLoss').modal('show');
		} else { // // 展示弹窗(按照时间结算)
			$('#project_review_time').find(".rate").eq(0).empty().text(parseFloat(_time_level_rate) * 100 + "%");
			$('#project_review_time').modal('show');
		}
	});
	
	//绑定弹窗的提交内容(按照止盈止损结算)
	mui("#project_review_stopProfitLoss").on("tap","#reSubmit",function(){
		//提交表单
		var _theObj = $(this).parents("#project_review_stopProfitLoss").eq(0);
		var _trade_type = _theObj.find("input[name='trade_type']").val(); //方向
		var _pro_code = _theObj.find("input[name='pro_code']").val(); //产品的Code
		var _trade_price = _theObj.find("input[name='trade_price']").val(); //合约订金
		// var _rise_level = _theObj.find("input[name='rise_level']").val(); //止盈/止损
		var _rise_level_id = _theObj.find("input[name='rise_level']").attr("rise_id"); //止盈/止损 id
		// var _stopProfitPrice = _theObj.find(".stopProfitPrice").text(); //止盈价
		// var _stopLossPrice = _theObj.find(".stopLossPrice").text(); //止损价
		var _latestPrice = _theObj.find(".latestPrice").text(); //最新价
		
		$.ajax({
			type:"POST",
			url: siteUrl + "/trade/commitUPIByStopProfitLoss.shtml", //后台接收订单的接口
			data:{
				isBuyUp:_trade_type,
				code:_pro_code,
				payAmount:_trade_price,
				goodsStopProfitLossId:_rise_level_id,
				latestPrice:_latestPrice
			},
			dataType:"JSON",
			error:function(){
				$('#project_review_stopProfitLoss').modal('hide'); //关闭弹窗
			},
			success:function(data){
				if (data.type == "info") {
                    layer.msg(data.message);
                    getUserPositionInfo();
                } else {
                    layer.msg(data.message);
                }
				$('#project_review_stopProfitLoss').modal('hide'); //关闭弹窗
			}
		}); 
	});
	
	//绑定弹窗的提交内容(按照时间结算)
	mui("#project_review_time").on("tap","#reSubmit",function(){
		//提交表单
		var _theObj = $(this).parents("#project_review_time").eq(0);
		var _trade_type = _theObj.find("input[name='trade_type']").val(); //方向
		var _pro_code = _theObj.find("input[name='pro_code']").val(); //产品的Code
		var _trade_price = _theObj.find("input[name='trade_price']").val(); //合约订金
		// var _time_level = _theObj.find("input[name='time_level']").val(); //到期时间
		var _time_level_id = _theObj.find("input[name='time_level']").attr("time_id"); //到期时间 id
		// var _stopProfitPrice = _theObj.find(".stopProfitPrice").text(); //止盈价
		// var _stopLossPrice = _theObj.find(".stopLossPrice").text(); //止损价
		var _latestPrice = _theObj.find(".latestPrice").text(); //最新价
		
		$.ajax({
			type:"POST",
			url: siteUrl + "/trade/commitUPIByTime.shtml", //后台接收订单的接口
			data:{
				isBuyUp:_trade_type,
				code:_pro_code,
				payAmount:_trade_price,
				goodsTimeId:_time_level_id,
				latestPrice:_latestPrice
			},
			dataType:"JSON",
			error:function(){
				$('#project_review_time').modal('hide'); //关闭弹窗
			},
			success:function(data){
				if (data.type == "info") {
                    layer.msg(data.message);
                    getUserPositionInfo();
                } else {
                    layer.msg(data.message);
                }
				$('#project_review_time').modal('hide'); //关闭弹窗
			}
		}); 
	});
	
	// 订阅行情数据
    try{
    	for(var i = 0; i < goodsArray.length; i++){
     		subscribeMarket(goodsArray[i]);
    	} 
    }catch(e){
    	console.log(e);
    }
	
}); 


