/**
 * Created by Administrator on 2017-01-13.
 */
$(function(){
    /**
     * @content 处理主页面的导航展开收缩的js效果
     * 列表项点击js效果
     */
    if($(".mainLeft").size()>0){
        $(".mainLeft dt a").click(function(){
            if($(this).parents(".CateItem").eq(0).hasClass("curr")){
                $(this).parent().siblings("dd").stop(true,true).slideUp();
                $(this).parents(".CateItem").eq(0).removeClass("curr");
                $(this).find("em").removeClass("arrow_down").addClass("arrow_right");
            }else{
                $(this).parent().siblings("dd").stop(true,true).slideDown();
                $(this).parents(".CateItem").eq(0).addClass("curr");
                $(this).find("em").removeClass("arrow_right").addClass("arrow_down");
            }
        });
        $(".mainLeft dd a").click(function () {
            $(".mainLeft dd a").removeClass("curr");
            $(this).addClass("curr");
        })
    }

    SetMainPageLayout();

    /**
     * @content 切换顶部导航时候，切换左侧导航
     */
    $(".head ul li a").click(function(){
        var _theIndex = $(this).parent().index();
        _theIndex = _theIndex - 1;
        if(_theIndex>=0){
            $(".mainLeft .TabContentItem:eq("+_theIndex+")").siblings().hide().addClass("curr");
            $(".mainLeft .TabContentItem:eq("+_theIndex+")").show();

            $(".mainLeft .TabContentItem:eq("+_theIndex+")").find(".CateItem").each(function(){
                if($(this).find("dd").length){
                    $(this).removeClass("curr");
                }
            });
            $(".mainLeft .TabContentItem:eq("+_theIndex+")").find("dd").stop(true,true).slideUp();
        }
    })


});


/**
 * @description 取得cookie  中指定的值
 * @param {String} name 获取cookie里的执行的键值
 */
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';'); //把cookie分割成组
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i]; //取得字符串
        while (c.charAt(0) == ' ') { //判断一下字符串有没有前导空格
            c = c.substring(1, c.length); //有的话，从第二位开始取
        }
        if (c.indexOf(nameEQ) == 0) { //如果含有我们要的name
            return unescape(c.substring(nameEQ.length, c.length)); //解码并截取我们要值
        }
    }
    return false;
}

/**
 * @description 清除cookie  中指定的值
 * @param {String} name 获取cookie里的执行的键值
 */
function clearCookie(name) {
    this.setCookie(name, "", -1);
}
/**
 * @description 设定cookie的值
 * @param {String} name 设定cookie里的键值
 * @param {String} value 设定cookie里指定键的值
 * @param {String} seconds 设定cookie的存活时间秒为单位
 */
function setCookie(name, value, seconds) {
    seconds = seconds || 0; //seconds有值就直接赋值，没有为0，这个根php不一样。
    var expires = "";
    if (seconds != 0) { //设置cookie生存时间
        var date = new Date();
        date.setTime(date.getTime() + (seconds * 1000));
        expires = "; expires=" + date.toGMTString();
    }
    document.cookie = name + "=" + escape(value) + expires + "; path=/"; //转码并赋值
}

$(window).resize(function(){
    SetMainPageLayout();
});
/**
 * @content 通过JS设定页面布局框架的细节样式
 * @constructor
 */
function SetMainPageLayout(){
    var _WindowHeight = $(window).height();
    var _WindowWidth = $(window).width();

    var _LeftCateLength = $(".mainLeft dl").length || 0;
    if(_LeftCateLength < 1) return false;

    var _EachDDLength = $(".mainLeft dl:eq(0) dt:eq(0)").height();
    var _CateContainerHeight = _LeftCateLength * _EachDDLength;

    if(_CateContainerHeight < _WindowHeight){
        //容器的高度  小于容器的高度
        var _LogoHeight = $(".mainLeft .logo-cont").height() || 50;
        var _CateListHeight = _WindowHeight - _LogoHeight;
        $(".mainLeft").css({
            "height":_CateListHeight+"px"
        });
        $(".mainRight").css({
            "height":_CateListHeight+"px"
        });
    }else{
        var _LogoHeight = $(".mainLeft .logo-cont").height() || 50;
        var _CateListHeight = _WindowHeight - _LogoHeight;
        $(".mainLeft").css({
            "height":_CateListHeight+"px",
            "overflow-x":"hidden",
            "overflow-y":"auto"
        });
        $(".mainRight").css({
            "height":_CateListHeight+"px",
            "overflow-x":"hidden",
            "overflow-y":"auto"
        });
    }

    if($(".mainLeft").size()>0){
        $(".mainLeft").css("height",_CateListHeight+"px");
        $(".mainRight").css("height",_CateListHeight+"px");
    }
}

