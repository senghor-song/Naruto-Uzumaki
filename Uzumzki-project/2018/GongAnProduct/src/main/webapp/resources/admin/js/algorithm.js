(function($){
	var RFn = function(){
		this.param = {};
	}
	
	RFn.prototype = {
	    Init: function () {
	        this.InitConsole();
	        this.InitJSON(); 
	    },
	    /**
        * @argument 处理console的兼容问题  
        */
	    InitConsole:function(){
	        var method;
	        var noop = function () { };
	        var methods = [
                'assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error',
                'exception', 'group', 'groupCollapsed', 'groupEnd', 'info', 'log',
                'markTimeline', 'profile', 'profileEnd', 'table', 'time', 'timeEnd',
                'timeStamp', 'trace', 'warn'
	        ];
	        var length = methods.length;
	        var console = (window.console = window.console || {});
	        while (length--) {
	            method = methods[length]; 
	            if (!console[method]) {
	                console[method] = noop;
	            }
	        }
	    },
        /**
        * @argument 处理JSON对象兼容问题
        */
	    InitJSON: function () {
	        //console.log("浏览器是否支持JSON对象"); 
	        /**
            * @content 判断浏览器的版本
            * @author  梁汝翔<liangruxiang >  
            * @time 2015年7月28日 10:07:09
            */
	        var _Agent = navigator.userAgent.toLowerCase();
	        //IE浏览器
	        if (_Agent.indexOf("msie") > 0) {
	            var _regStr_ie = /msie [\d.]+;/gi;
	            var _theBrowser = _Agent.match(_regStr_ie);
	            var _theBanben = (_theBrowser + "").replace(/[^0-9.]/ig, "");
	            _theBanben = isNaN(parseInt(_theBanben)) ? 9 : parseInt(_theBanben);
	            if (_theBanben < 8) {
	                if ($("body #json_jr").size() < 1) {
	                    var _JS_JSON2 = "<script id='json_jr'  type='text/javascript' src='/templates/SSC/js/JSON2.js?" + parseInt(Math.random() * 100000) + "'><\/script>";
	                    $("body").append($(_JS_JSON2));
	                }
	            }
	        }
	        return true;
	    },
		/**
		* @argument 公用AJAX方法，便于后期优化   
		*/
		RAjax:function(type,url,data,dataType,success_call,error_call){
			if(type == undefined)  type = 'POST';
			if(url == undefined)  url = '';
			if(data == undefined)  data = {};
			if(dataType == undefined)  dataType = 'JSON';
			if(success_call == undefined){
				success_call = function(data){
				    //console.log(data); 
					return true;
				} 
			}
			if(error_call == undefined){
				error_call = function(error){
					//console.log(error);
					return true;
				} 
			} 
			$.ajax({
				type:type,
				url:url,
				data:data,
				dataType:dataType,
				error:function(error){
					error_call(error);
				},
				success:function(data){
					success_call(data)
				}
			})
		}, 
		/**
		* @argument 获取JSON数据   
		*/
		RGetJSON:function(url,data,success_call){
			$.getJSON(url,data,function(results){
				success_call(results);
			});
		},
		/**
		* @argument  概率组合计算，相当于数学C(n,m)
		*/
		MathCombin: function (n, m) {
            if (n == "" || m == "" || n == "-" || m == "-" || n == 0 || m == 0 || isNaN(n) || isNaN(m)) {
                oResult = 0;  //返回的结果
            }
            else {
                //m必须大于n,如果小于则返回0;
                oResult = parseInt(this.ruiec_mathfactorial(n) / (this.ruiec_mathfactorial(n - m) * this.ruiec_mathfactorial(m)));
            }
            return oResult;
        },
        /**
		* @argument  数学阶乘运算,相当于数学公式(num!)
		*/
		Mathfactorial: function (num) {
            if (num <= 1) {
                return 1;
            } else {
                return num * arguments.callee(num - 1);   //用arguments对象的callee属性来指向外层函数调用自己 
            }
        },
        /**
		* @argument 把数组转换成String字符串    
		*/
        GetArray_ToString: function (arr, str) {
        	return arr.join(str);
        	/*
            if (arr == undefined) { arr = []; }
            if (str == undefined) { arr = ''; }
            var _resultString = "";
            for (var i = 0 ; i < arr.length ; i++) {
                _resultString += arr[i];
                if (i + 1 < arr.length) {  //除了最后一个 都要一字符串链接
                    _resultString += str;
                }
            }
            return _resultString;
            */
        },
		/**
		 * @argument 去除数组中重复的值 
		 * @return  有重复返回true；否则返回false 
		 */
        Del_Arrat_RepeatInfos: function (arr) {
            var theReturn = false; //默认是没有的
            if (arr == undefined) { arr = []; }
            //arr.sort();  //数组排序
            var New_Array = new Array();
            for (var i = 0 ; i < arr.length ; i++) {

                var theReturn = false; //默认是没有的
                var theValue = arr[i];
                if (i == 0) { New_Array.push(theValue); } //第一个不是重复的 
                for (var j = 0 ; j < arr.length ; j++) {  //当前元素与后面的所有元素都不相等；迭代  
                    if (i != j && theValue == arr[j]) {
                        theReturn = true;
                        j = arr.length;
                    }
                }
                if (!theReturn && i != 0) {
                    New_Array.push(theValue);
                }
            }
            return New_Array;
        },
		/**
		 * @argument 二维数组的排列组合 
		 * @return  组合后的一维数组
		 */
        TwoD_ArrayCombination: function (arr2) {
            if (arr2.length < 1) {
                return [];
            }
            var w = arr2[0].length,
				h = arr2.length,
				i, j,
				m = [],
				n,
				result = [],
				_row = [];

            m[i = h] = 1;

            while (i--) {
                m[i] = m[i + 1] * arr2[i].length;
            }
            n = m[0];
            for (i = 0; i < n; i++) {
                _row = [];
                for (j = 0; j < h; j++) {
                    _row[j] = arr2[j][~~(i % m[j] / m[j + 1])];
                }
                result[i] = _row;
            }
            return result;
        },
		/**
		 * @argument 把字符串转成JSON对象  
		 */
        String_To_Json:function(StrData) {
           if (StrData != undefined && StrData != "") {
                    var Jsdata;
                    if (JSON != undefined) {
                        Jsdata = JSON.parse(StrData);
                    }
                    else {
                        Jsdata = jQuery.parseJSON(StrData);
                    }

                    return Jsdata;
                }
            else {
               return {};
            }
        }, 
		/**
		 * @argument JSON对象转换成String类型对象 
		 * @param JSONObj 
		 */
        Json_To_String: function (JSONObj) {
            var Result = "";
            if (JSONObj != undefined) {
                if (JSON != undefined) {
                    Result = JSON.stringify(JSONObj);
                }
                else {
                    Result = JSONObj.toString();
                }
            }
            return Result;
        },
		/**
		 * @argument 验证字符串是否存在与数组中 
		 * @param str 字符串，arry 匹配数组 
		 */
        CheckStrInArray: function (str, arry) {
            var theState = false ;
            for (var i = 0 ; i < arry.length; i++) {
                if (arry[i] == str) {
                    i = arry.length;
                    theState = true;
                }
            }
            return theState;
        },
		/**
		 * @argument 设定单选按钮的UI 
		 * @param parentObj 直属父级，value 匹配数组 eg:[是,否]
		 */
        RsetForm_UI_Checkbox:function(parentObj,value){
        	//查找复选框
	        var checkObj = parentObj.children('input:checkbox').eq(0);
	        parentObj.children().hide();
	        //添加元素及样式
	        var newObj = $('<a href="javascript:;">'
			+ '<i class="off">'+value[1]+'</i>'
			+ '<i class="on">'+value[0]+'</i>'
			+ '</a>').prependTo(parentObj);
	        parentObj.addClass("RFUI-checkbox");
	        //判断是否选中
	        if (checkObj.prop("checked") == true) {
	            newObj.addClass("selected");
	        }
	        //检查控件是否启用
	        if (checkObj.prop("disabled") == true) {
	            newObj.css("cursor", "default");
	            return;
	        }
	        //绑定事件
	        newObj.click(function () {
	            if ($(this).hasClass("selected")) {
	                $(this).removeClass("selected");
	            } else {
	                $(this).addClass("selected");
	            }
	            checkObj.trigger("click"); //触发对应的checkbox的click事件
	        });
	        //绑定反监听事件
	        checkObj.on('click', function () {
	            if ($(this).prop("checked") == true && !newObj.hasClass("selected")) {
	                alert();
	                newObj.addClass("selected");
	            } else if ($(this).prop("checked") == false && newObj.hasClass("selected")) {
	                newObj.removeClass("selected");
	            }
	        });
        },
		/**
		 * @argument 将HTML转换成string类型 
		 * @param str  为要转换的html结构
		 */
        Html_To_String:function(str) {
		    var RexStr = /\<|\>|\"|\'|\&|　| /g
		    str = str.replace(RexStr,function (MatchStr) {
			        switch (MatchStr) {
			            case "<":
			                return "&lt;";
			                break;
			            case ">":
			                return "&gt;";
			                break;
			            case "\"":
			                return "&quot;";
			                break;
			            case "'":
			                return "&#39;";
			                break;
			            case "&":
			                return "&amp;";
			                break;
			            case " ":
			                return "&ensp;";
			                break;
			            case "　":
			                return "&emsp;";
			                break;
			            default:
			                break;
			        }
			    }
		    )
		    return str;
        },
	    /**
		 * @argument 获取url指定的值
		 * @param str  为要转换的html结构
		 */
	    getQueryString:function(name) {
	        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	        var r = window.location.search.substr(1).match(reg);
	        if (r != null) return unescape(r[2]); return null;
	    },
	    /**
		 * @argument 过滤输入的字符串，转换为整数
		 * @param str  为要转换的字符串
		 */
	    FilteringValue_ToInt: function (str) {
	        str = str || 0;
	        str = str.replace(/[^\d]/g, ""); 
	        str = isNaN(Number(str)) ? 0 : Number(str);
	        return str;
	    },
	    /**
		 * @argument 过滤输入的字符串，转换为整数
		 * @param str  为要转换的字符串
		 */
	    FilteringValue_ToFloat: function (str) {
	        str = str || 0;
	        if (str.indexOf(".") < 0) {
	            str = str.replace(/[^\d]/g, "");
	        } else {
	            str = str.replace(/[^\d|.]/g, "");
	            if (str.indexOf(".") != str.length - 1) {
	                var _theValueString = str.split(".");
	                str = _theValueString[0] + "." + _theValueString[1];
	            } else {
	                str = _theValueString[0] + ".0";
	            }
	        } 
	        return str;
	    }
	}
	 
	$.extend({ RFn: RFn });
	var $RFn = new $.RFn();
	$RFn.Init();
})(jQuery);