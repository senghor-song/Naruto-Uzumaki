$(function() {
	//	$("#quote_time").datetimepicker({
	//        //minView: "month", 
	//		step: 2, //设置时间间隔  
	//        format: 'Y-m-d H:i',
	//        todayBtn:false,
	//        onChangeDateTime: function(dp, $input) { 
	//           var startDate = $("#quote_time").val();
	//       },
	//    }); 
	//	 $("#quote_time1").datetimepicker({
	//		 step: 2, //设置时间间隔  
	//         format: 'Y-m-d H:i', 
	//         todayBtn:false,
	//         onClose:function(current_time, $input) {
	//        	 console.log(current_time, $input);
	//        	 endDate = $("#quote_time1").val();
	//        	 var startDate = $("#quote_time").val();
	//        	 if (startDate > endDate) {
	//        		 layer.msg("结束时间不能小于开始时间");
	//        		 $("#quote_time1").val("");
	//        	 }
	//         },
	//     }); 
	var startTimeInit = $("#quote_time").val();
	var endTimeInit = $("#quote_time1").val();
	if(startTimeInit != null && startTimeInit != ""){
		var startDate = $("#quote_time").val();
		var endDate = $("#quote_time1").val();
		var startReplace = startDate.split(" ")[0].replace(/-/g, "/");
		var starTime = startDate.split(" ")[1];
		if (endDate != "" && endDate != null && endDate < startDate) {
			layer.msg("开始时间不能大于结束时间");
			$("#quote_time").val("");
		} else {
			$("#quote_time1").datetimepicker({
				minDate : startReplace,
				minTime : starTime,
			});
		}
	}else if(endTimeInit != null && endTimeInit != ""){
		var endDate = $("#quote_time1").val();
		var endReplace = endDate.split(" ")[0].replace(/-/g, "/");
		var endTime = endDate.split(" ")[1];
		var startDate = $("#quote_time").val();
		if (endDate != "" && endDate != null) {
			if (startDate > endDate) {
				layer.msg("结束时间不能小于开始时间");
				$("#quote_time1").val("");
			} else {
				$("#quote_time").datetimepicker({
					maxDate : endReplace,
					maxTime : endTime,
				});
			}
		}
	}
	
	var startDateInit = $("#quote_date").val();
	var endDateInit = $("#quote_date1").val();
	if(startDateInit != null && startDateInit != ""){
		var startDate = $("#quote_date").val();
		var endDate = $("#quote_date1").val();
		var startReplace = startDate.split(" ")[0].replace(/-/g, "/");
		var starTime = startDate.split(" ")[1];
		if (endDate != "" && endDate != null && endDate < startDate) {
			layer.msg("开始时间不能大于结束时间");
			$("#quote_date").val("");
		} else {
			$("#quote_date1").datetimepicker({
				minDate : startReplace,
				minTime : starTime,
			});
		}
	}else if(endDateInit != null && endDateInit != ""){
		var endDate = $("#quote_date1").val();
		var endReplace = endDate.split(" ")[0].replace(/-/g, "/");
		var endTime = endDate.split(" ")[1];
		var startDate = $("#quote_date").val();
		if (endDate != "" && endDate != null) {
			if (startDate > endDate) {
				layer.msg("结束时间不能小于开始时间");
				$("#quote_date1").val("");
			} else {
				$("#quote_date").datetimepicker({
					maxDate : endReplace,
					maxTime : endTime,
				});
			}
		}
	}
	
	var startTimeInit = $("#quote_datetime").val();
	var endTimeInit = $("#quote_datetime1").val();
	if(startTimeInit != null && startTimeInit != ""){
		var startDate = $("#quote_datetime").val();
		var endDate = $("#quote_datetime1").val();
		var startReplace = startDate.split(" ")[0].replace(/-/g, "/");
		var starTime = startDate.split(" ")[1];
		if (endDate != "" && endDate != null && endDate < startDate) {
			layer.msg("开始时间不能大于结束时间");
			$("#quote_datetime").val("");
		} else {
			$("#quote_datetime1").datetimepicker({
				minDate : startReplace,
				minTime : starTime,
			});
		}
	}else if(endTimeInit != null && endTimeInit != ""){
		var endDate = $("#quote_datetime1").val();
		var endReplace = endDate.split(" ")[0].replace(/-/g, "/");
		var endTime = endDate.split(" ")[1];
		var startDate = $("#quote_datetime").val();
		if (endDate != "" && endDate != null) {
			if (startDate > endDate) {
				layer.msg("结束时间不能小于开始时间");
				$("#quote_datetime1").val("");
			} else {
				$("#quote_datetime").datetimepicker({
					maxDate : endReplace,
					maxTime : endTime,
				});
			}
		}
	}
	
	$("#quote_time").datetimepicker({
		//minView: "month", 
		step : 1, //设置时间间隔  
		format : 'Y-m-d H:i',
		todayButton : false,
		validateOnBlur : false,
		onChangeDateTime : function(dp, $input) {
			var startDate = $("#quote_time").val();
			var endDate = $("#quote_time1").val();
			var startReplace = startDate.split(" ")[0].replace(/-/g, "/");
			var starTime = startDate.split(" ")[1];
			if (endDate != "" && endDate != null && endDate < startDate) {
				layer.msg("开始时间不能大于结束时间");
				$("#quote_time").val("");
			} else {
				$("#quote_time1").datetimepicker({
					minDate : startReplace,
					minTime : starTime,
				});
			}
		},
	});
	$("#quote_time1").datetimepicker({
		step : 1, //设置时间间隔  
		format : 'Y-m-d H:i',
		//defaultSelect:false, 
		validateOnBlur : false,
		onSelectDate : function(ct, $i) {
//			console.log(ct.dateFormat('d/m/Y'))
		},
		onClose : function(current_time, $input) {
			var endDate = $("#quote_time1").val();
			var endReplace = endDate.split(" ")[0].replace(/-/g, "/");
			var endTime = endDate.split(" ")[1];
			var startDate = $("#quote_time").val();
			if (endDate != "" && endDate != null) {
				if (startDate > endDate) {
					layer.msg("结束时间不能小于开始时间");
					$("#quote_time1").val("");
				} else {
					$("#quote_time").datetimepicker({
						maxDate : endReplace,
						maxTime : endTime,
					});
				}
			}
		},
	});
	
	$("#quote_date").datetimepicker({
		minView: "month", 
		format : 'Y-m-d',
		todayButton : false,
		validateOnBlur : false,
		onChangeDateTime : function(dp, $input) {
			var startDate = $("#quote_date").val()+" 00:00:00";
			var endDate = $("#quote_date1").val();
			var startReplace = startDate.split(" ")[0].replace(/-/g, "/");
			var starTime = startDate.split(" ")[1];
			if (endDate != "" && endDate != null && endDate < startDate) {
				layer.msg("不能大于当前时间");
				$("#quote_date").val("");
			} else {
				$("#quote_date1").datetimepicker({
					minDate : startReplace,
					minTime : starTime,
				});
			}
		},
	});
	$("#quote_date1").datetimepicker({
		minView: "month", 
		format : 'Y-m-d',
		onClose : function(current_time, $input) {
			var endDate = $("#quote_date1").val()+" 23:59:59";
			var endReplace = endDate.split(" ")[0].replace(/-/g, "/");
			var endTime = endDate.split(" ")[1];
			var startDate = $("#quote_date").val();
			if (endDate != "" && endDate != null) {
				if (startDate > endDate) {
					layer.msg("不能小于当前时间");
					$("#quote_date1").val("");
				} else {
					$("#quote_date").datetimepicker({
						maxDate : endReplace,
						maxTime : endTime,
					});
				}
			}
		},
	});
	
	$("#quote_datetime").datetimepicker({
		//minView: "month", 
		step : 1, //设置时间间隔  
		format : 'Y-m-d H:i:s',
		todayButton : false,
		validateOnBlur : false,
		onChangeDateTime : function(dp, $input) {
			var startDate = $("#quote_datetime").val();
			var endDate = $("#quote_datetime1").val();
			var startReplace = startDate.split(" ")[0].replace(/-/g, "/");
			var starTime = startDate.split(" ")[1];
			if (endDate != "" && endDate != null && endDate < startDate) {
				layer.msg("开始时间不能大于结束时间");
				$("#quote_datetime").val("");
			} else {
				$("#quote_datetime1").datetimepicker({
					minDate : startReplace,
					minTime : starTime,
				});
			}
		},
	});
	$("#quote_datetime1").datetimepicker({
		step : 1, //设置时间间隔  
		format : 'Y-m-d H:i:s',
		//defaultSelect:false, 
		validateOnBlur : false,
		onSelectDate : function(ct, $i) {
//			console.log(ct.dateFormat('d/m/Y'))
		},
		onClose : function(current_time, $input) {
			var endDate = $("#quote_datetime1").val();
			var endReplace = endDate.split(" ")[0].replace(/-/g, "/");
			var endTime = endDate.split(" ")[1];
			var startDate = $("#quote_datetime").val();
			if (endDate != "" && endDate != null) {
				if (startDate > endDate) {
					layer.msg("结束时间不能小于开始时间");
					$("#quote_datetime1").val("");
				} else {
					$("#quote_datetime").datetimepicker({
						maxDate : endReplace,
						maxTime : endTime,
					});
				}
			}
		},
	});
})