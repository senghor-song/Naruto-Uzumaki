<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
	.contextMenuDialog .listrow{border: none}
</style>
<div class="contextMenuDialog" id="item1">
    <div class="card-body">
        <div class="row">
            <div class="input-group col-lg-6">
                <div class="row">
                    <div class="col-lg-6 listrow">
                        <span class="nti">机构编号：</span>
                        <span class="ncon" style="width: 60%;">
                            <input class="form-control" type="text" value="${trainTeamNo}" name="trainTeamNo" id="trainTeamNo" readonly="readonly">
                        </span>
                    </div>
                    <div class="col-lg-6 listrow">
                        <span class="nti">评级：</span>
                        <span class="ncon">
                            <select class="form-control" name="level" id="level">
                                <option value="1" <c:if test="${level eq '1'}">selected="selected"</c:if>>1</option>
                                <option value="2" <c:if test="${level eq '2'}">selected="selected"</c:if>>2</option>
                                <option value="3" <c:if test="${level eq '3'}">selected="selected"</c:if>>3</option>
                                <option value="4" <c:if test="${level eq '4'}">selected="selected"</c:if>>4</option>
                                <option value="5" <c:if test="${level eq '5'}">selected="selected"</c:if>>5</option>
                                <option value="6" <c:if test="${level eq '6'}">selected="selected"</c:if>>6</option>
                                <option value="7" <c:if test="${level eq '7'}">selected="selected"</c:if>>7</option>
                                <option value="8" <c:if test="${level eq '8'}">selected="selected"</c:if>>8</option>
                                <option value="9" <c:if test="${level eq '9'}">selected="selected"</c:if>>9</option>
                                <option value="10" <c:if test="${level eq '10'}">selected="selected"</c:if>>10</option>
                                <option value="11" <c:if test="${level eq '11'}">selected="selected"</c:if>>11</option>
                                <option value="12" <c:if test="${level eq '12'}">selected="selected"</c:if>>12</option>
                            </select>
                        </span>
                    </div>
                    <div class="col-lg-6 listrow">
                        <span class="nti">原始ID：</span>
                        <span class="ncon" style="width: 60%;">
                            <input class="form-control" type="text" value="${id}" name="id" id="id" readonly="readonly">
                        </span>
                    </div>
                    <div class="col-lg-6 listrow">
                        <span class="nti">机构：</span>
                        <span class="ncon" style="width: 60%;">
                            <input class="form-control" type="text" value="${title}" name="title" id="title">
                        </span>
                    </div>
                    <div class="col-lg-6 listrow">
                        <span class="nti">城市：</span>
                        <span class="ncon">
                            <select class="form-control" name="cityId" id="cityId">
                            	<c:forEach items="${citys}" var="city">
                                	<option value="${city.id }" <c:if test="${city.id eq cityid}">selected="selected"</c:if>>${city.city }</option>
                              	</c:forEach>
                            </select>
                        </span>
                    </div>
                    <div class="col-lg-6 listrow">
                        <span class="nti">电话：</span>
                        <span class="ncon" style="width: 60%;">
                            <input class="form-control" type="text" value="${phone}" name="phone" id="phone">
                        </span>
                    </div>
                    <div class="col-lg-6 listrow">
                        <span class="nti">状态：</span>
                        <span class="ncon">
                            <select class="form-control" name="typeFlag" id="typeFlag">
                                <option value="0" <c:if test="${typeFlag eq '0'}">selected="selected"</c:if>>禁用</option>
                                <option value="1" <c:if test="${typeFlag eq '1'}">selected="selected"</c:if>>正常</option>
                            </select>
                        </span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">&nbsp;</span>
                        <button class="form-control btn btn-primary w-25" id="saveTrainTeam">保存</button>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 text-center">
                <div class="col-lg-12 listrow">
                    <span class="nti">简介：</span>
                        <textarea class="form-control" name="brandContent" id="brandContent"  style="width: 80%;height: 200px;" >
                        ${brandContent}
                        </textarea>
                </div>
            </div>
            <div class="col-lg-3 text-center">
                <div class="col-lg-12">
                	<img src="${headImage}" id="image"
                     style="width: 180px;height: 180px; border:1px solid #eee;"
                      onerror="javascript:this.src='/admin/static/image/error.png'">
                </div>
                <div class="col-lg-12">
                    <input type="hidden" value="${headImage}" name="headImage" id="headImage">
		            <div class="publicbg-title">
						<button class="layui-btn test" lay-data="{url: '/admin/common/uploadImage'}" name="file">上传图片</button>
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
					//加载层-风格4
					layer.msg('上传中', {
					  icon: 16,shade: 0.01
					});
				},
				done: function(res, index, upload) {
				    layer.closeAll('loading'); //关闭loading
					layer.msg("上传成功");
					$("#headImage").val(res.data);
					$("#image").attr("src", res.data);
				},
				error: function(index, upload){
				    layer.closeAll('loading'); //关闭loading
				}
			})
		});
		
		$("#saveTrainTeam").click(function () {
			var id = $('#id').val();
			var cityId = $('#cityId').val();
			var level = $('#level').val();
			var title = $('#title').val();
			var phone = $('#phone').val();
			var headImage = $('#headImage').val();
			var brandContent = $('#brandContent').val();
			var typeFlag = $('#typeFlag').val();
           	$.ajax({  
                type : "POST",  //提交方式  
                url : "/admin/trainTeam/update",//路径  
                data : {id : id, cityId : cityId, level : level, title : title, phone : phone, headImage : headImage, brandContent : brandContent, typeFlag : typeFlag},//数据，这里使用的是Json格式进行传输 
                dataType:"json",
                success : function(result) {//返回数据根据结果进行相应的处理  
                    if ( result.code == 200 ) {  
    					layer.msg("修改成功");
                        layer.closeAll(); 
                        $(".contextMenuDialog").addClass("hide");
    					$.reload(tableObj.obj);
                    } else {
                		layer.confirm(result.msg, {
                			btn: ['确定'] //按钮
                		});
                    }
                }
            }); 
		})
	})
</script>