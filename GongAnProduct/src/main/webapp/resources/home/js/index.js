$(function(){
	//首页列表下拉
//	mui(".index_cont").on('tap','.cont ',function(){
//		$(this).hide();
//		$(this).parents("li").find(".two_cont").show();
//	})
//	
//	mui(".index_cont").on('tap','.top ',function(){
//		$(this).parents("li").find(".cont").show();
//		$(this).parents(".two_cont").hide();
//	})
	
	//连接href
	mui('body').on('tap', 'a', function (e) { 
        var href = this.getAttribute('href'); 
        if (typeof href == "undefined" || href == "" || href == false || href == null) return false; 
        mui.openWindow({ 
            url: href 
        }); 
    });
	
	mui.init({
		gestureConfig:{
		   tap: true, //默认为true
		   doubletap: true, //默认为false
		   longtap: true, //默认为false
		   swipe: true, //默认为true
		   drag: true, //默认为true
		   hold:false,//默认为false，不监听
		   release:false//默认为false，不监听
		}
	}); 
	
})

function userCenterTab(obj1,obj2){
	$("#"+obj1).attr("class","userCenter_tab_td_span1");
	$("#"+obj2).attr("class","userCenter_tab_td_span2");
	window.location="view.shtml?type="+obj1;
}