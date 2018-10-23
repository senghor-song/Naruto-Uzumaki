<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
	.contextMenuDialog .listrow{border: none}
</style>
<div class="contextMenuDialog" id="item1">
    <div class="card-body">
        <div class="row">
            <div class="input-group col-lg-9">
                <div class="row">
                    <div class="col-lg-6 listrow">
                        <span class="nti">场馆编号：</span>
                        <span class="ncon" style="width: 60%;">
                            <input class="form-control" type="text" value="${venueno}" name="venueno" id="venueno" readonly="readonly">
                        </span>
                    </div>
                    <div class="col-lg-6 listrow">
                        <span class="nti">状态：</span>
                        <span class="ncon">
                            <select class="form-control" name="showflag" id="showflag">
                                <option value="2" <c:if test="${showflag eq '2'}">selected="selected"</c:if>>禁用</option>
                                <option value="1" <c:if test="${showflag eq '1'}">selected="selected"</c:if>>正常</option>
                            </select>
                        </span>
                    </div>
                    <div class="col-lg-6 listrow">
                        <span class="nti">原始ID：</span>
                        <span class="ncon" style="width: 60%;">
                            <input class="form-control" type="text" value="${venueid}" name="id" id="id" readonly="readonly">
                        </span>
                    </div>
                    <div class="col-lg-6 listrow">
                        <span class="nti">场馆：</span>
                        <span class="ncon" style="width: 60%;">
                            <input class="form-control" type="text" value="${name}" name="venueName" id="venueName">
                        </span>
                    </div>
                    <div class="col-lg-6 listrow">
                        <span class="nti">城市：</span>
                        <span class="ncon">
                            <select class="form-control" name="cityid" id="cityid">
                            	<c:forEach items="${citys}" var="city">
                                	<option value="${city.id }" <c:if test="${city.id eq cityid}">selected="selected"</c:if>>${city.city }</option>
                              	</c:forEach>
                            </select>
                        </span>
                    </div>
                    <div class="col-lg-6 listrow">
                        <span class="nti">电话：</span>
                        <span class="ncon" style="width: 60%;">
                            <input class="form-control" type="text" value="${tel}" name="tel" id="tel">
                        </span>
                    </div>
                    <div class="col-lg-6 listrow">
                        <span class="nti">区县：</span>
                        <span class="ncon">
                            <select class="form-control" name="districtid" id="districtid">
                            </select>
                        </span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">&nbsp;</span>
                        <button class="form-control btn btn-primary w-25" id="saveVenue">保存</button>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 text-center">
                <div class="col-lg-12">
                	<img src="${image}" id="headImage"
                     style="width: 180px;height: 180px; border:1px solid #eee;"
                      onerror="javascript:this.src='/WebBackAPI/admin/static/image/error.png'">
                </div>
                <div class="col-lg-12">
                    <input type="hidden" value="${image}" name="image" id="image">
		            <div class="publicbg-title">
						<button class="layui-btn test" lay-data="{url: '/WebBackAPI/admin/common/uploadImage'}" name="file">上传图片</button>
					</div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
	$(function(){
		layui.use('upload', function() {
			var upload = layui.upload;
			upload.render({
				elem: '.test',
				before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
					
				},
				done: function(res, index, upload) {
					layer.close(index);
					layer.msg("上传成功");
					$("#image").val(res.data);
					$("#headImage").attr("src", res.data);
				},
				error: function(index, upload){
				    layer.closeAll('loading'); //关闭loading
				}
			})
		});
		
		$("#saveVenue").click(function () {
			var id = $('#id').val();
			var cityid = $('#cityid').val();
			var districtid = $('#districtid').val() || "";
			var showflag = $('#showflag').val();
			var venueName = $('#venueName').val();
			var tel = $('#tel').val();
			var image = $('#image').val();
           	$.ajax({  
                type : "POST",  //提交方式  
                url : "/WebBackAPI/admin/venue/update/venue",//路径  
                data : {id : id, cityid : cityid, districtid : districtid, showflag : showflag, venueName : venueName, tel : tel, image : image},//数据，这里使用的是Json格式进行传输 
                dataType:"json",
                success : function(result) {//返回数据根据结果进行相应的处理  
                    if ( result.code == 200 ) {  
    					layer.msg("修改成功");
                    } else {
                		layer.confirm(result.msg, {
                			btn: ['确定'] //按钮
                		});
                    }
                }
            }); 
		})
		getDistrict();
		$("#cityid").change(function () {
			getDistrict();
		})
		
		function getDistrict(){
			var cityid = $('#cityid').val();
           	$.ajax({  
                type : "POST",
                url : "/WebBackAPI/admin/city/district/list", 
                data : {id : cityid},
                dataType:"json",
                success : function(result) {
                	$("#districtid").empty();
                	var list = result.data; 
                	for (var i = 0; i < list.length; i++) {
                        //先创建好select里面的option元素
                        var option = document.createElement("option");
                        //转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
                        $(option).val(list[i].id);
                        //给option的text赋值,这就是你点开下拉框能够看到的东西
                        $(option).text(list[i].district);
                        //获取select 下拉框对象,并将option添加进select
                        $('#districtid').append(option);
                	}
                }  
            }); 
		}
	})
</script>