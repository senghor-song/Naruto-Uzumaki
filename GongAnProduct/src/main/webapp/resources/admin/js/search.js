/**
 * 后台列表页的多条件搜索.
 * @since 2016-12-05
 */


/**
 * 通过默认的URL,打开搜索弹框
 * @param width 弹出框的宽度
 * @param height 弹出框的高度
 * @return
 * Date: 2016-12-05 
 */
function openSearchPopupByDefaultURL(width, height){
	openSearchPopup(width, height, "search.shtml");
}

/**
 * 通过指定的URL,打开搜索弹框
 * @param width 弹出框的宽度
 * @param height 弹出框的高度
 * @param url 指定的URL
 * @return
 * Date: 2016-12-05 
 */
function openSearchPopupByAssignedURL(width, height, url){
	openSearchPopup(width, height, url);
}

/**
 * 打开搜索弹框
 * @param width 弹出框的宽度
 * @param height 弹出框的高度
 * @param url 访问的URL
 * @return
 * Date: 2016-12-07 
 */
function openSearchPopup(width, height, url){
	url = url + "?";
	var paramName = "";
	var paramValue = "";
	$(".searchCriteria").each(function(index,item) { // 传递给搜索弹框的参数值
		paramName = $(this).attr("name");
		paramValue = $(this).val();
		url += paramName + "=" + paramValue + "&";
	});
	if (url.lastIndexOf("&") == url.length - 1) { // 去除最尾部的字符&
		url = url.substring(0, url.length - 1);
	}
	if (url.lastIndexOf("?") == url.length - 1) { // 去除最尾部的字符?
		url = url.substring(0, url.length - 1);
	}
	layer.open({
		type: 2,
		title:'条件查询',
		skin: 'layui-layer-rim', //加上边框
		area: [width, height], //宽高
		closeBtn: 1,
		content: url
	})
}

/**
 * 搜索条件提交
 * @return
 * Date: 2016-12-05 
 */
function searchOnSubmit(){
	$(".searchCriteria").each(function(index,item) {
		$("#" + $(this).attr("id"), window.parent.document).val($(this).val()); // 弹框中搜索条件的值赋给父页面的表单元素
	});
	$("#list_form", window.parent.document).submit(); // 提交父页面的表单
}

/**
 * 搜索条件重置为空
 * @return
 * Date: 2016-12-05 
 */
function searchOnReset(){
	$(".searchCriteria").each(function(index,item) {
		if($(this).attr("notClear")!="true"){
			$(this).val("");
		}
	});
}


