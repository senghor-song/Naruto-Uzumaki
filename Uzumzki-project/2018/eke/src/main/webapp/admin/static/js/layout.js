$(".content-wrapper").css({height: $(window).height()-57});
$("#topSidebar").load("/WebRelease/admin/common/top");
//$("#arealist").load("layout/arealist.html");
$("#leftSidebar").load("/WebRelease/admin/common/left", function (a) {
    var index = $("#leftSidebar").data("selectindex");
    var obj = $('#selectindex_' + index);
    obj.addClass("active");
    obj.parent().parent().parent().addClass("menu-open");

    $("body").append('<script src="/WebRelease/admin/static/plugins/js/adminlte.js">\<\/script>');
});