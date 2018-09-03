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
                    <div class="col-lg-4 listrow">
                        <span class="nti">编号：</span>
                        <span class="ncon">${employee.empno }</span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">手机绑定：</span>
                        <span class="ncon">${employee.tel}</span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">状态：</span>
                        <span class="ncon">
                            <select class="form-control" name="status" id="status">
                                <option value="正常" <c:if test="${employee.status eq '正常'}">selected="selected"</c:if>>正常</option>
                                <option value="申请合作" <c:if test="${employee.status eq '申请合作'}">selected="selected"</c:if>>申请合作</option>
                                <option value="加入等待" <c:if test="${employee.status eq '加入等待'}">selected="selected"</c:if>>加入等待</option>
                                <option value="异常" <c:if test="${employee.status eq '异常'}">selected="selected"</c:if>>异常</option>
                            </select>
                        </span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">姓名：</span>
                        <span class="ncon">${employee.emp}</span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">微信绑定：</span>
                        <span class="ncon">${employee.wxno}</span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">是否管理：</span>
                        <span class="ncon">
                            <select class="form-control" name="storemanage" id="storemanage">
                                <option value="1" <c:if test="${employee.storemanage == 1}">selected="selected"</c:if>>是</option>
                                <option value="0" <c:if test="${employee.storemanage == 0}">selected="selected"</c:if>>否</option>
                            </select>
                        </span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">商户：</span>
                        <span class="ncon">${employee.empStore.empstore}</span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">实名验证：</span>
                        <span class="ncon">${employee.authstate}</span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">备注：</span>
                        <span class="ncon">
                            <input class="form-control" type="text" value="${employee.remark}" name="remark" id="remark">
                        </span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">性别：</span>
                        <span class="ncon">${employee.sex}</span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">身份证号：</span>
                        <span class="ncon">${employee.idcard}</span>
                    </div>
                    <div class="col-lg-4 listrow">
                        <span class="nti">&nbsp;</span>
                        <button class="form-control btn btn-primary w-25" id="saveEmployee">保存</button>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 text-center">
                <img src="${employee.icon}"
                     style="width: 180px;height: 180px; border-radius: 180px; border:1px solid #eee;"
                      onerror="javascript:this.src='/WebRelease/admin/static/image/error.png'">
            </div>
        </div>
    </div>
</div>
<script>
	$(function(){
		$("#saveEmployee").click(function () {
			var status = $('#status').val();
			var remark = $('#remark').val();
			var storemanage = $('#storemanage').val();
			var id = '${employee.id}';
           	$.ajax({  
                type : "POST",  //提交方式  
                url : "/WebRelease/admin/employee/saveEmployee",//路径  
                data : {id : id, status : status, remark : remark, storemanage : storemanage},//数据，这里使用的是Json格式进行传输  
                dataType:"json",
                success : function(result) {//返回数据根据结果进行相应的处理  
                    if ( result.code == 200 ) {  
                    	parent.window.location.href="/WebRelease/admin/employee/listview";
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