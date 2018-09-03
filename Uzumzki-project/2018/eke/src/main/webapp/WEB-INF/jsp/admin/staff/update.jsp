<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/WebRelease/admin/static/css/site.css" rel="stylesheet">
<link rel="stylesheet" href="/WebRelease/admin/static/plugins/bootstrap-validator/dist/css/bootstrapValidator.css" />
<link rel="stylesheet" href="/WebRelease/admin/static/plugins/bootstrap-validator/vendor/bootstrap/css/dataValidator.css" />
<form id="form" action="#" method="post">
	<div class="contextMenuDialog" id="item2">
        <div class="card-body form-control border-0">
            <div class="row">
                <div class="col-lg-3">
                    <div class="row">
                    	<input type="hidden" value="${staff.staffid}" name="staffid">
                        <div class="col-lg-2 lh-38">姓名</div>
                        <div class="col-lg-10 lh-38">${staff.rname}</div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2 lh-38">手机</div>
                        <div class="col-lg-10 lh-38">${staff.tel}</div>
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="row">
                        <div class="col-lg-2 lh-38">状态</div>
                        <div class="col-lg-10 lh-38">
                            <select name="status" class="form-control w-50" id="">
                                <option value="正常" <c:if test="${staff.status eq '正常'}"> selected="selected" </c:if>>正常</option>
                                <option value="冻结" <c:if test="${staff.status eq '冻结'}"> selected="selected" </c:if>>冻结</option>
                                <option value="异常" <c:if test="${staff.status eq '异常'}"> selected="selected" </c:if>>异常</option>
                            </select>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-lg-2 lh-38">岗位</div>
                        <div class="col-lg-10 lh-38">
	                        <select name="position" class="form-control w-50" id="">
	                            <option value="经理" <c:if test="${staff.position eq '经理'}"> selected="selected" </c:if>>经理</option>
	                            <option value="职员" <c:if test="${staff.position eq '职员'}"> selected="selected" </c:if>>职员</option>
	                            <option value="临时" <c:if test="${staff.position eq '临时'}"> selected="selected" </c:if>>临时</option>
	                        </select>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-lg-2 lh-38">权限</div>
                        <div class="col-lg-10 lh-38">
	                        <select name="power" class="form-control w-50" id="">
	                            <option value="7" <c:if test="${staff.power == 7}"> selected="selected" </c:if>>七级</option>
	                            <option value="6" <c:if test="${staff.power == 6}"> selected="selected" </c:if>>六级</option>
	                            <option value="5" <c:if test="${staff.power == 5}"> selected="selected" </c:if>>五级</option>
	                            <option value="4" <c:if test="${staff.power == 4}"> selected="selected" </c:if>>四级</option>
	                            <option value="3" <c:if test="${staff.power == 3}"> selected="selected" </c:if>>三级</option>
	                            <option value="2" <c:if test="${staff.power == 2}"> selected="selected" </c:if>>二级</option>
	                            <option value="1" <c:if test="${staff.power == 1}"> selected="selected" </c:if>>一级</option>
	                        </select>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3">
                    <span class="form-control btn btn-primary w-25" id="updateStaff">保存</span>
                </div>
            </div>
        </div>
    </div>
</form>
    <script src="/WebRelease/admin/static/plugins/jquery/jquery.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/layer/layer.js"></script>
	<script type="text/javascript" src="/WebRelease/admin/static/plugins/bootstrap-validator/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/WebRelease/admin/static/plugins/bootstrap-validator/dist/js/bootstrapValidator.js"></script>
<script>
$(function () {
	$("#updateStaff").click(function () {
    	$.ajax({  
            type : "POST",  //提交方式  
            url : "/WebRelease/admin/staff/updateStaff",//路径  
            data : $("form").serialize(),//数据，这里使用的是Json格式进行传输  
            dataType:"json",
            success : function(result) {//返回数据根据结果进行相应的处理  
                if ( result.code == 200 ) {  
                	parent.window.location.href="/WebRelease/admin/staff/listview";
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