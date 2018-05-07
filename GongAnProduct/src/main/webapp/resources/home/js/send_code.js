/**
* fn 用于全站的 获取验证码
* @time 2015年12月29日 09:08:32
*/
theTime = 60 ;
function Reg_getPhoneYZM(){
	var reg =  /^(13[0-9]|15[0-9]|18[0-9]|14[0-9]|17[0-9])\d{8}$/;
	//点击获取手机验证码
	$("#getCode,#getYzm").click(function(){
		var _this = $(this);
        var _thecodeId= _this.attr("id");
        var _theareaCode = _this.attr('area_code');
        var theStatue = _this.attr("statue");
        var theRelationInput = _this.attr("relation") == undefined ? "" : _this.attr("relation");
		if(theStatue=="true"){
			var theType  = "Mobile";	//获取type
			//reg =  /^(13[0-9]|15[0-9]|18[0-9]|14[0-9]|17[0-9])\d{8}$/;
            //reg = /^[+]\d{1,4}\d{7,11}$/; // 加区号形式 如：+8613533947186
            //reg = /^\d{7,11}$/;
            //reg = /^\d+?$/;
            reg = /^\+?\d+(\d+)?$/;
			var theForm = _this.parents("form").eq(0);
			var theAccount = theForm.find("input[verify='account']").eq(0).val();
			if (theRelationInput == "") {
			    var thePhone = theForm.find("input[verify='isPhone']").eq(0).val();
			} else {
			    var thePhone = theForm.find("input[name='" + theRelationInput + "']").eq(0).val();
			}

			var theEmail = theForm.find("input[verify='isEmail']").eq(0).val();
			if(thePhone==undefined)
			{
				reg  =   /^(\w)+(\.\w+)*@(\w|[-])+((\.\w+)+)$/;
				theType = "Email";	//获取type
				thePhone = theEmail;
			}
			
			if(thePhone==""){ 
				var _theMsg = theForm.find("input[verify='isPhone']").eq(0).attr("msg") == undefined ? "" : theForm.find("input[verify='isPhone']").eq(0).attr("msg"); 
				if(_theMsg!=""&&layer!=undefined){ 
					layer.msg(_theMsg,{icon:2}); 
				}
			}
			
			if(theAccount==undefined)
			{
				theAccount = "" ;
			}
			else
			{
				if(theAccount=="")
				{
					layer.msg("请填写用户名",{icon:2});
					theForm.find("input[name='account']").eq(0).focus();
					return false;
				}
			}
           /* if(_theareaCode) // 支持发送国内外手机
            {
                var  thePhone = _theareaCode+thePhone;
            }*/
			//var the_corrent = reg.test(thePhone); //验证手机号码的格式
            var the_corrent = true;
			var snsType = $(this).attr("sns_type");// 获取类型，1：为绑定现在账号，2：为注册新账号并绑定
            /*if(!the_corrent){
				layer.msg("手机号码有误",{icon:2});
			}*/

			var theApi 	 = $(this).attr("url");  //获取接口
			if(theApi!=undefined&&theApi!=""&&the_corrent){

                var SendCode = "";
                if($("input[name='vcode']").size()>0){
                    SendCode = $("input[name='vcode']").val();
                }

				//发送请求
				$.ajax({
					type:"POST",
					url:theApi,
					data:{
						Type:theType,
						"Mobile":thePhone,
                        'area_code':_theareaCode,
						'sns_type':snsType,
                        vcode:SendCode,
						Account:theAccount
					},
					dataType:"json",
					success: function(data){
						_this.attr("disabled","disabled");
						if(data!=""){
							if(data.status=="1"){
                                var _theRandom = "SetDaojishiObj"+parseInt(Math.random()*10);
                                var theObj = {
                                    time:60,
                                    CodeId:_thecodeId
                                }
                                _theRandom = new $.CountDown();
                                _theRandom.init(theObj);
								$("#"+_thecodeId).attr("statue","false");
                                layer.msg(data.msg,{icon: 1});
							}
							else
							{
								$("#"+_thecodeId).attr("statue","true");
								layer.msg(data.msg,{icon: 2});
							}
						}
					}
				});
			}
		}else{
			if(theStatue==undefined)
			{
				$("input[verify='isPhone']").focus();
			}
			return false;
		}
	});
}

(function($){
    var _theCountDown = function(){
        this.param = "";
    }

    _theCountDown.prototype = {
        init:function(option){
            this.param = option.time;
            this.setDaoJishi(option.CodeId);
        },
        setDaoJishi:function (CodeId){
            var _this = this;
            this.param = this.param - 1 ;
            var theTime = this.param;
            if(theTime!=undefined&&theTime>0){
                var theNewString  = sending+"("+theTime+")" ;
                $("#"+CodeId).text(theNewString);
                $("#"+CodeId).removeAttr("style");
                $("#"+CodeId).attr("statue","false");
                setTimeout(function(){
                    _this.setDaoJishi(CodeId);
                },1000);
                var _gl_input = $("#" + CodeId).attr("relation");
                if(_gl_input!=undefined)
                {
                    $("#"+_gl_input).attr("readonly","true");
                }
                else
                {
                    $("input[verify='isPhone']").attr("readonly","true");
                }
                $("#"+CodeId).parent().parent().find(".ts_msg").hide();
            }else{
                var theNewString  = "获取验证码" ;
                $("#"+CodeId).text(theNewString);
                $("#"+CodeId).css({
                    //"background":"#5c5dcd",
                    //"color":"#fff"
                });
                $("#"+CodeId).attr("statue","true");
                var _gl_input = $("#" + CodeId).attr("relation");
                if(_gl_input!=undefined)
                {
                    $("#"+_gl_input).removeAttr("readonly");
                }
                else
                {
                    $("input[verify='isPhone']").removeAttr("readonly");
                }

            }
        }
    }

    $.extend({CountDown:_theCountDown});

})(jQuery);

$(function(){
	/**
	* fn 获取验证码
	* @time 2015年12月29日 09:08:32
	*/
	Reg_getPhoneYZM();
	
})
