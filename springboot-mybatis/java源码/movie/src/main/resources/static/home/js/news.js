$(function () {
//	点击显示更多，收起
	$(".add").click(function(){
		$(".tele_dl dl dd p").addClass("active");
	})
	$(".shou").click(function(){
		$(".tele_dl dl dd p").removeClass("active");
	})
	
})

