<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="/WebRelease/admin/static/css/site.css" rel="stylesheet">
<link rel="stylesheet" href="/WebRelease/admin/static/plugins/bootstrap-validator/dist/css/bootstrapValidator.css" />
<link rel="stylesheet" href="/WebRelease/admin/static/plugins/bootstrap-validator/vendor/bootstrap/css/dataValidator.css" />
<div id="myinfoDiv" class="otherDialog">
    <div style="padding: 18px;">
        <div class="row m-0 p-0">
            <div class="col-lg-7">
                <div class="row lh-24">
                    <div class="col-lg-4">英文名：</div>
                    <div class="col-lg-8">${sessionScope.adminloginuser.ename}</div>
                </div>
                <div class="row lh-24">
                    <div class="col-lg-4">中文名：</div>
                    <div class="col-lg-8">${sessionScope.adminloginuser.rname}</div>
                </div>
                <div class="row lh-24">
                    <div class="col-lg-4">职级：</div>
                    <div class="col-lg-8">${sessionScope.adminloginuser.position}</div>
                </div>
                <div class="row lh-24">
                    <div class="col-lg-4">状态：</div>
                    <div class="col-lg-8">${sessionScope.adminloginuser.status}</div>
                </div>
                <div class="row lh-24">
                    <div class="col-lg-4">最近登录：</div>
                    <div class="col-lg-8"><fmt:formatDate value="${sessionScope.adminloginuser.loginchange}" pattern="yyyy-MM-dd HH:mm:ss" /> </div>
                </div>
            </div>
            <div class="col-lg-5">
                <div class="row lh-24">
                    <div class="col-lg-4">权限：</div>
                    <div class="col-lg-8">${sessionScope.adminloginuser.power == 1 ? "一级" : sessionScope.adminloginuser.power == 2 ? "二级" : sessionScope.adminloginuser.power == 3 ? "三级" : sessionScope.adminloginuser.power == 4 ? "四级" : sessionScope.adminloginuser.power == 5 ? "五级" : sessionScope.adminloginuser.power == 6 ? "六级" : "七级"}</div>
                </div>
            </div>
        </div>
        <hr>
        <div class="row m-0 p-0">
            <div class="row p-0 m-0">
                <div class="w-100 clearfix">
                    <input type="checkbox" id="changePwd" class="float-left">
                    <span class="float-left" style="margin-left: 5px; position: relative; margin-top: -3px;">改密码</span>
                </div>
            </div>
            <form action="#" id="infoForm" class="w-100 hide form-horizontal">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="row form-group has-feedback w-100">
                            <div class="col-lg-4 lh-38">旧密码：</div>
                            <div class="col-lg-8">
                                <input type="password" class="form-control input-sm" name="oldPassword">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="row form-group has-feedback w-100">
                            <div class="col-lg-4 lh-38">新密码：</div>
                            <div class="col-lg-8">
                                <input type="password" class="form-control input-sm" name="newPassword">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="row form-group has-feedback w-100">
                            <div class="col-lg-5 lh-38">确认密码：</div>
                            <div class="col-lg-7">
                                <input type="password" class="form-control input-sm" name="confirmPassword">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="row w-100">
                            <div class="col-lg-4 lh-38"></div>
                            <div class="col-lg-8">
                                <input class="form-control btn btn-primary w-100" value="确认" id="updatePassword">
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

		<script src="/WebRelease/admin/static/plugins/jquery/jquery.min.js"></script>
		<script src="/WebRelease/admin/static/plugins/layer/layer.js"></script>
		<script type="text/javascript" src="/WebRelease/admin/static/plugins/bootstrap-validator/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/WebRelease/admin/static/plugins/bootstrap-validator/dist/js/bootstrapValidator.js"></script>
		<script type="text/javascript">
            $(function(){
            	$('#infoForm').bootstrapValidator({
                      message: '请输入正确的内容',
                      feedbackIcons: {
                          valid: 'glyphicon glyphicon-ok',
                          invalid: 'glyphicon glyphicon-remove',
                          validating: 'glyphicon glyphicon-refresh'
                      },
                      fields: {
                    	  oldPassword: {
                              validators: {
                                  notEmpty: {
                                      message: '旧密码不能为空'
                                  },
                                  different: {
                                      field: 'newPassword',
                                      message: '新密码不能和旧密码相同'
                                  }
                              }
                          },
                          newPassword: {
                              validators: {
                                  notEmpty: {
                                      message: '新密码不能为空'
                                  },
                                  identical: {
                                      field: 'confirmPassword',
                                      message: '两次输入密码不一致'
                                  },
                                  different: {
                                      field: 'oldPassword',
                                      message: '新密码不能和旧密码相同'
                                  }
                              }
                          },
                          confirmPassword: {
                              validators: {
                                  notEmpty: {
                                      message: '确认密码不能为空'
                                  },
                                  identical: {
                                      field: 'newPassword',
                                      message: '两次输入密码不一致'
                                  },
                                  different: {
                                      field: 'oldPassword',
                                      message: '新密码不能和旧密码相同'
                                  }
                              }
                          },
                      }
                  });
            	$("#updatePassword").click(function () {
                    $('#infoForm').bootstrapValidator('validate');
                    //是否通过校验
                    if($('#infoForm').data('bootstrapValidator').isValid()){  
			            $.ajax({  
			                type : "POST",  //提交方式  
			                url : "/WebRelease/admin/staff/updatePassword",//路径  
			                data : $("#infoForm").serialize(),//数据，这里使用的是Json格式进行传输  
			                dataType:"json",
			                success : function(result) {//返回数据根据结果进行相应的处理  
			                    if ( result.code == 200 ) {  
	                        		window.location.href="/WebRelease/admin/common/login";
			                    	layer.closeAll()
			                    } else {  
			                		layer.confirm(result.msg, {
			                            zIndex: 19891017,
			                			btn: ['确定'] //按钮
			                		});
			                    }  
			                }  
			            });
                    }
                });
            })
           </script>