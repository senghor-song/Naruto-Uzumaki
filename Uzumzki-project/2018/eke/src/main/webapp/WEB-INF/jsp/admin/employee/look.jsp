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
                        <span class="ncon">${employee.status}</span>
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
                        <span class="ncon">${employee.storemanage == 1 ? "是" : "否"}</span>
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
                        <span class="ncon">${employee.remark}</span>
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
			var id = '${employee.id}';
           	$.ajax({  
                type : "POST",  //提交方式  
                url : "/WebRelease/admin/employee/saveEmployee",//路径  
                data : {id : id,status:status,remark:remark},//数据，这里使用的是Json格式进行传输  
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