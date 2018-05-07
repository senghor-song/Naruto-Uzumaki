/**
 * Created by Administrator on 2015/5/15.
 */
var pjw=function(){

};
var option = {};
pjw={
    sub: function (opt) { 
        option = {
            success: function (data) { 
                if (data.status == 0) {
                    layer.msg(data.info,{icon:2});
                }else{
                    if (data.url != '' || data.url != undefined || data.url != null) {
                        if(data.info==null||data.info==''){
                            location.href = data.url
                        }else{
                            if(data.url==2){
                                layer.msg(data.info,{icon: 1});
                            }else if(data.url=='1'){
                                layer.msg(data.info,{icon: 1});
                                setTimeout(function(){
                                    location.reload();
                                },500)
                            }else{
                                layer.msg(data.info,{icon: 1});
                                setTimeout(function(){
                                    location.href = data.url
                                },500)
                            }

                        }
                    } else {
                        layer.msg(data.info,{icon: 1});
                    }
                }
            },
            url:''
        }
        var _form;
        if (opt) {
           
            if (opt.success) {
                option.success = opt.success;
            }
            if (opt.id!=undefined&&opt.id) {
                _form=$(opt.id);
            }
            if (opt.form != undefined && opt.form) {
                _form = $(opt.form).parents('form').eq(0); 
            }
            if (opt.url != undefined && opt.url) {
                _form.attr('action',opt.url);
                //option.url = opt.url;
            }
        }
        
        
        
        if(opt.alert){
            layer.confirm(layer_ok_operating, {icon: 3}, function(){
                _form.ajaxSubmit({
                    success: option.success
                });
            });
        } else {

            
            if(_form.data('amui.validator')!=undefined){
                alert("11");
                if(_form.data('amui.validator').isFormValid()){
                    _form.ajaxSubmit({
                        success: option.success
                    });
                }
            } else {
                if (_form) { 
                    _form.ajaxSubmit({
                        success: option.success
                    });
                }
            }
        }

    },
    confirm:function(opt){
        var theObj = opt.obj;
        var thetext = opt.text == undefined ? layer_ok_operating : opt.text;
        var theicon = opt.icon == undefined ? 3 : opt.icon;
        var option = {
            obj: theObj,
            text: thetext,
            icon: theicon,
            close: layer.close()
        }
        option.ok = function () {
            console.log(layer_method_no);
        }
        if($(option.obj).attr('url')!=undefined){
            option.ok=function(){
                $.ajax({
                    "url":$(option.obj).attr('url'),
                    "type":"post",
                    "dataType":"json",
                    success:function (data, textStatus) {
                        if (data.status == 0) {
                            layer.msg(data.info,{icon:2});
                        }else{
                            if (data.url != '') {
                                if(data.info==''){
                                    location.href = data.url
                                }else{
                                    if(data.url=='1'){
                                        layer.msg(data.info,{icon: 1});
                                        setTimeout(function(){
                                            location.reload();
                                        },500)
                                    }else{
                                        layer.msg(data.info,{icon: 1});
                                        setTimeout(function(){
                                            location.href = data.url
                                        },500)
                                    }

                                }
                            } else {
                                layer.msg(data.info,{icon: 1});
                            }
                        }
                    }
                });
            }
        }
        layer.confirm(option.text, {icon: option.icon}, option.ok,option.close);
    },
    checkAll:function(){
        $("input[type='checkbox']").click();
    },
    delCheckAll:function(obj){
        option={
            id  :obj.id,
            text:       obj.text?opt.text:layer_ok_operating,
            icon:       obj.icon?opt.icon:3,
            close:      function(){
                $(obj.id).attr('action','');
                layer.close()
            },
            obj:obj.obj
            //ok:
        }
        var url=$(option.obj).attr('url');
        $(option.id).attr('action',url);
        layer.confirm(option.text, {icon: option.icon}, function(){
            pjw.sub({
                id:option.id
            })
        },option.close);
    }
}

var xb=function(){

};
xb={
    checkFansStatus:function(obj){
        $.ajax({
            "url":$(obj).attr('url'),
            "type":"get",
            "dataType":"json",
            "success":function(data){
                $(obj).attr('status',data.status);
                $(obj).attr('url',data.url);
                $(obj).text(data.info);
                if(data.status=="0"){
                    $(obj).attr('onclick','xb.addFans(this)');
                }else{
                    $(obj).attr('onclick','xb.delFans(this)');
                }
            }
        });
    },
    addFans:function(obj){
        $.ajax({
            "url":$(obj).attr('url'),
            "type":"get",
            "dataType":"json",
            "success":function(data){
                $(obj).attr('status',data.status);
                $(obj).attr('url',data.url);
                $(obj).text(data.info);
                if(data.status=="0"){
                    $(obj).attr('onclick','xb.addFans(this)');
                }else{
                    $(obj).attr('onclick','xb.delFans(this)');
                }
            }
        });
    },
    delFans:function(obj){
        $.ajax({
            "url":$(obj).attr('url'),
            "type":"get",
            "dataType":"json",
            "success":function(data){
                $(obj).attr('status',data.status);
                $(obj).attr('url',data.url);
                $(obj).text(data.info);
                if(data.status=="0"){
                    $(obj).attr('onclick','xb.addFans(this)');
                }else{
                    $(obj).attr('onclick','xb.delFans(this)');
                }
            }
        });
    }
}