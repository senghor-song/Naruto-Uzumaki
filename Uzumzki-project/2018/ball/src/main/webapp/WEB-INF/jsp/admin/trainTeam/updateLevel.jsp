<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
	.contextMenuDialog .listrow{border: none}
</style>
<div class="contextMenuDialog" id="item1">
    <div class="card-body">
        <div class="row">
            <div class="input-group col-lg-12">
                <div class="row col-lg-8">
                    <div class="col-lg-8 listrow">
                        <input type="hidden" value="${id}" name="id" id="id">
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
                    <div class="col-lg-4 listrow">
                        <button class="form-control btn btn-primary" id="saveTrainTeam">保存</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
	$(function(){
		
		$("#saveTrainTeam").click(function () {
			var id = $('#id').val();
			var level = $('#level').val();
           	$.ajax({  
                type : "POST",  //提交方式  
                url : "/WebBackAPI/admin/trainTeam/saveLevel",//路径  
                data : {id : id, level : level},//数据，这里使用的是Json格式进行传输 
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