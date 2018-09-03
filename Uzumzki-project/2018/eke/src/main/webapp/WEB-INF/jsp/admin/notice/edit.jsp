<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div class="contextMenuDialog" id="item2">
        <div class="card-body">
            <div class="row lh-38 mb-2">
                <div class="col-lg-1">公告标题</div>
                <div class="col-lg-11">
                    <div class="row">
                        <div class="col-lg-9">
                            <input type="hidden" name="id" id="id" value="${massNotice.id}">
                            <input type="text" class="form-control" id="title" name="title" value="${massNotice.title}">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row lh-38 mb-2">
                <div class="col-lg-1">内容</div>
                <div class="col-lg-11">
                    <textarea name="content" class="form-control" id="content" cols="30" style="height: 280px;">${massNotice.content}</textarea>
                </div>
            </div>
            <div class="row lh-38">
                <div class="col-lg-1">&nbsp;</div>
                <div class="col-lg-11">
                    <button class="form-control btn btn-primary w-25" id="update">保存</button>
                </div>
            </div>
        </div>
    </div>
    <script>
    	$(function(){
    		$("#update").click(function () {
    			var id = $('#id').val();
    			var title = $('#title').val();
    			var content = $('#content').val();
               	$.ajax({  
                    type : "POST",  //提交方式  
                    url : "/WebRelease/admin/notice/update",//路径  
                    data : {id:id,title:title,content:content},//数据，这里使用的是Json格式进行传输  
                    dataType:"json",
                    success : function(result) {//返回数据根据结果进行相应的处理  
                        if ( result.code == 200 ) {  
                        	parent.window.location.href="/WebRelease/admin/notice/listview";
                        } else {  
                    		layer.confirm(result.msg, {
                    			btn: ['确定'] //按钮
                    		});
                        }  
                    }  
                }); 
            });
    	})
    </script>