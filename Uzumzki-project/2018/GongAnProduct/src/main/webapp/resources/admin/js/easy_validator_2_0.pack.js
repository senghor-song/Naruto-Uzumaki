/*
	Copyright (C) 2009 - 2012
	WebSite:	Http://wangking717.javaeye.com/
	Author:		wangking
	
	添加原因： 每次验证都要输入正则表达式 1不好记忆 2对正则表达式不熟悉的人用不了 
	添加常用数据类型这样就不用每次都输入正则表达式了
        更改样式出错的样式，模仿Eclipse红色波浪线
	更改提示框的显示方式         
        为Form添加属性：success 和faild属性 参考下句
        为每一个验证元素都增加了一个success 和faild属性 用来验证失败执行的代码 
        添加自定义验证 check属性 在这个属性里可以写自己的代码来验证复杂的验证
*/

//第七页添加开始20120714
	var vtip_arrow_path = "images/vtip_arrow.png";
	var diqye_invalid_line = "images/invalid_line.gif";	//background: url("../images/default/grid/invalid_line.gif") repeat-x scroll center bottom #FFFFFF;

	//提示框显示时间
	var SHOW_TIME = 0;
	//隐藏所用的时间
	var HIDE_TIME = 0;
	//常用form验证正则
    var email = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
    var int = "^-?\\d+$";
    var float = "^(-?\\d+)(\\.\\d+)?$";
    var string = "^[a-zA-Z0-9_]+$";
    var url = "^(http|https|ftp)\\://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(:[a-zA-Z0-9]*)?/?([a-zA-Z0-9\\-\\._\\?\\,\\'/\\\\+&%\$#\\=~])*$";
    var not_null = "\.+";
    var _null = "\\n[\\s| ]*\\r";
    
    var letter = "^\\w+$";
    var letter_or_int = "^[A-Za-z0-9]+$";
    var china = "[\\u4e00-\\u9fa5]+";
    var html = "<(.*)>.*<\/.+>|<(.*) \/>";
    var trim = "(^\\s*)|(\\s*$)";
    var phone11 = "\\d{11,11}"//十一位手机号码
//结束
$(function(){
	var xOffset = -20; // x distance from mouse
    var yOffset = 20; // y distance from mouse  
	//第七页 添加开始 20120713
    $('body').append( '<p id="vtip" style="display:none;"><img id="vtipArrow" src="'+vtip_arrow_path+'"/><a></a></p>');
    //第七页添加结束
	
	//input action
    //第七页修改开始
	//$("[reg],[url]:not([reg]),[tip]").hover(
      $("[obj],[reg],[url]:not([reg],[obj]),[tip],[check]").hover(
	//第七页修改结束
		function(e) {
			if($(this).attr('tip') != undefined){
				var top = (e.pageY + yOffset);
				var left = (e.pageX + xOffset);
				//第七页修改开始
				//$('body').append( '<p id="vtip"><img id="vtipArrow" src="'+vtip_arrow_path+'"/>' + $(this).attr('tip') + '</p>' );
				$('p#vtip').css("top", top+"px").css("left", left+"px");
				$('p#vtip a').html($(this).attr('tip'));
				//$('p#vtip').stop();
				$('p#vtip').show(SHOW_TIME);
				$('p#vtip').bgiframe();
				//结束
			}
		},
		function() {
			if($(this).attr('tip') != undefined){
				//第七页修改开始
				//$("p#vtip").remove();
				$("p#vtip").hide(HIDE_TIME);
			//	$('p#vtip').dequeue();
				//结束
			}
		}
	).mousemove(
		function(e) {
			if($(this).attr('tip') != undefined){
				var top = (e.pageY + yOffset);
				var left = (e.pageX + xOffset);
				$("p#vtip").css("top", top+"px").css("left", left+"px");
			}
		}
	).blur(function(){
		if($(this).attr("url") != undefined){
			//第七页修改开始20120716
			//ajax_validate($(this));
			if(ajax_validate($(this))) {
				//验证成功增加事件
				if(true == $(this).attr("success")) {
					eval($(this).attr("success"));
				}
			}else {
				//验证失败增加事件
				if($(this).attr("faild")) {
					eval($(this).attr("faild"));
				}
			}
			//结束
		}else if($(this).attr("reg") != undefined ){
			//第七页修改开始20120716
			//validate($(this));
			if(true == validate($(this))){
				//验证成功增加事件
				if($(this).attr("success")) {
					eval($(this).attr("success"));
				}
			}else {
				//验证失败增加事件
				if($(this).attr("faild")) {
					eval($(this).attr("faild"));
				}
			}
			//结束
		}
		//第七页修改开始20120716
		else if($(this).attr("obj") != undefined) {
			//第七页修改开始20120716
			//validate($(this));
			if(true == validate($(this))){
				//验证成功增加事件
				if($(this).attr("success")) {
					eval($(this).attr("success"));
				}
			}else {
				//验证失败增加事件
				if($(this).attr("faild")) {
					eval($(this).attr("faild"));
				}
			}
			//结束
		}
		//结束
		//第七页添加开始20120717
		else if($(this).attr("check") != undefined) {
			var checkState = eval($(this).attr("check"));
			//alert(checkState);
			if(checkState == null) {
				
			}else if(checkState == "success"){
				change_error_style($(this),"remove");
				if($(this).attr("success")) {
					eval($(this).attr("success"));
				}
				return ;
			}
			
			change_error_style($(this),"add");
			//验证失败增加事件
			if($(this).attr("faild")) {
				eval($(this).attr("faild"));
			}
		}
		//结束
	});
	
	$("form").submit(function(){
		var isSubmit = true;
	//第七页修改开始20120716	
	//	$(this).find("[reg],[url]:not([reg])").each(function(){
		$(this).find("[reg],[obj],[url]:not([reg],[obj]),[check]").each(function(){
//			if($(this).attr("reg") == undefined){
			//第七页修改开始20120717
			//if($(this).attr("reg") == undefined && $(this).attr("obj") == undefined){
			if($(this).attr("url")){
			//结束	
	//结束	
				if(!ajax_validate($(this))){
					if(isSubmit)isSubmit = false;
				}
			//第七页修改开始20120717	
			//}else{
			}else if($(this).attr("obj") || $(this).attr("reg")){
			//结束	
				if(!validate($(this))){
					if(isSubmit)isSubmit = false;
				}
			}
			//第七页添加20120717
			else if($(this).attr("check") != undefined){
				var checkState = eval($(this).attr("check"));
				//console.log(checkState);
				if(checkState != "success") {
					change_error_style($(this),"add");
					if(isSubmit)isSubmit = false;
				}else {
					change_error_style($(this),"remove");
				}
			} 
			//结束
		});
		if(typeof(isExtendsValidate) != "undefined"){
   			if(isSubmit && isExtendsValidate){
				return extendsValidate();
			}
   		}
		//第七页添加
		if(isSubmit == false) {  
			if($(this).attr("faild") != null) { //验证失败
				eval($(this).attr("faild"));
			}
		//	console.log("false");
				
		}else {
			if($(this).attr("success") != null) {//验证成功
				eval($(this).attr("success"));
			}
			
		}
		//结束
		return isSubmit;
	});
	
});

function validate(obj){
	var validata = obj.attr("obj");
	if(validata == null ||validata == "undefined") {
		validata = obj.attr("reg");
	}
	switch (validata) {
	case 'email':
		validata = email;
		break;
	case 'string':
		validata = string;
		break;
	case 'int':
		validata = int;
		break;
	case 'float': //汉字
		validata = float;
		break;
	case 'url': //汉字
		validata = url;
		break;
	case 'not-null': //汉字
		validata = not_null;
		break;
		break;
	case 'null': //汉字
		validata = _null;
		break;
	case 'letter': //汉字
		validata = letter;
		break;
	case 'letter+int': //汉字
		validata = letter_or_int;
		break;
	case 'china': //汉字
		validata = china;
		break;
	case 'html': //汉字
		validata = html;
		break;
	case 'phone11': //汉字
		validata = phone11;
		break;
	default:
		break;
	}
	//第七页添加结束
	var reg = new RegExp(validata);
	
	var objValue = obj.attr("value");
	
	if(!reg.test(objValue)){
		change_error_style(obj,"add");
		change_tip(obj,null,"remove");
		return false;
	}else{
		if(obj.attr("url") == undefined){
			change_error_style(obj,"remove");
			change_tip(obj,null,"remove");
			return true;
		}else{
			return ajax_validate(obj);
		}
		
	}
}

function ajax_validate(obj){
	
	var url_str = obj.attr("url");
	//第七页添加20120716
	if(!url_str) return false;
	//结束
	if(url_str.indexOf("?") != -1){
		url_str = url_str+"&"+obj.attr("name")+"="+obj.attr("value");
	}else{
		url_str = url_str+"?"+obj.attr("name")+"="+obj.attr("value");
	}
	var feed_back = $.ajax({url: url_str,cache: false,async: false}).responseText;
	feed_back = feed_back.replace(/(^\s*)|(\s*$)/g, "");
	if(feed_back == 'success'){
		change_error_style(obj,"remove");
		change_tip(obj,null,"remove");
		return true;
	}else{
		change_error_style(obj,"add");
		change_tip(obj,feed_back,"add");
		return false;
	}
}

function change_tip(obj,msg,action_type){
	
	if(obj.attr("tip") == undefined){//初始化判断TIP是否为空
		obj.attr("is_tip_null","yes");
	}
	if(action_type == "add"){
		if(obj.attr("is_tip_null") == "yes"){
			obj.attr("tip",msg);
		}else{
			if(msg != null){
				if(obj.attr("tip_bak") == undefined){
					obj.attr("tip_bak",obj.attr("tip"));
				}
				obj.attr("tip",msg);
			}
		}
	}else{
		if(obj.attr("is_tip_null") == "yes"){
			obj.removeAttr("tip");
			obj.removeAttr("tip_bak");
		}else{
			obj.attr("tip",obj.attr("tip_bak"));
			obj.removeAttr("tip_bak");
		}
	}
}

function change_error_style(obj,action_type){
	if(action_type == "add"){
		obj.addClass("input_validation-failed");
	}else{
		obj.removeClass("input_validation-failed");
	}
}

$.fn.validate_callback = function(msg,action_type,options){
	this.each(function(){
		if(action_type == "failed"){
			change_error_style($(this),"add");
			change_tip($(this),msg,"add");
		}else{
			change_error_style($(this),"remove");
			change_tip($(this),null,"remove");
		}
	});
};
