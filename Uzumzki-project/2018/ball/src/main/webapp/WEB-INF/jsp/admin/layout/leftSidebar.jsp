<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<a href="http://www.ekeae.com" class="brand-link">
    <img src="/WebBackAPI/admin/static/image/logo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
    <span class="brand-text font-weight-light">订场管理</span>
</a>

<div class="sidebar">
    <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
            <img src="/WebBackAPI/admin/static/plugins/img/user2-160x160.jpg" class="img-circle elevation-2 myinfo" alt="User Image" style="cursor: pointer;">
        </div>
        <div class="info">
            <a href="javascript:;" class="d-block myinfo">${sessionScope.adminloginuser.rname}（${sessionScope.adminloginuser.position}）</a>
        </div>
    </div>
    <!-- Sidebar Menu -->
    <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
            <li class="nav-item">
                <a href="/WebBackAPI/admin/common/index" class="nav-link" id="selectindex_0">
                    <i class="nav-icon fa fa-area-chart"></i>
                    <p>控制台</p>
                </a>
            </li>
            
            <li class="nav-item has-treeview">
                <a href="#" class="nav-link">
                    <i class="fa fa-thumbs-up fa-fw"></i>
                    <p>产品<i class="fa fa-angle-left right"></i></p>
                </a>
                <ul class="nav nav-treeview">
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/inviteBall/listview" class="nav-link" id="selectindex_1">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>约球</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/order/listview" class="nav-link" id="selectindex_2">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>订场</p>
                        </a>
                    </li>
                </ul>
            </li>
            
            <li class="nav-item has-treeview">
                <a href="#" class="nav-link">
                    <i class="fa fa-paper-plane fa-fw"></i>
                    <p>运营<i class="fa fa-angle-left right"></i></p>
                </a>
                <ul class="nav nav-treeview">
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/member/listview" class="nav-link" id="selectindex_3">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>用户</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/venue/listview" class="nav-link" id="selectindex_4">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>场馆</p>
                        </a>
                    </li>
                </ul>
            </li>
            
            <li class="nav-item has-treeview">
                <a href="#" class="nav-link">
                    <i class="fa fa-users fa-fw"></i>
                    <p>管理<i class="fa fa-angle-left right"></i></p>
                </a>
                <ul class="nav nav-treeview">
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/venue/listview" class="nav-link" id="selectindex_5">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>管理</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/newsBanner/listview" class="nav-link" id="selectindex_6">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>横幅</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/city/listview" class="nav-link" id="selectindex_7">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>城市</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/venue/listview" class="nav-link" id="selectindex_8">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>合作申请</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/venue/listview" class="nav-link" id="selectindex_9">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>场馆信息</p>
                        </a>
                    </li>
                </ul>
            </li>
            <li class="nav-item has-treeview">
                <a href="#" class="nav-link">
                    <i class="fa fa-handshake-o fa-fw"></i>
                    <p>伙伴<i class="fa fa-angle-left right"></i></p>
                </a>
                <ul class="nav nav-treeview">
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/staff/listview" class="nav-link" id="selectindex_10">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>伙伴</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/venue/listview" class="nav-link" id="selectindex_11">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>ACL</p>
                        </a>
                    </li>
                </ul>
            </li>
            <li class="nav-item has-treeview">
                <a href="#" class="nav-link">
                	<i class="fa fa-tasks fa-fw"></i>
                    <p>财务<i class="fa fa-angle-left right"></i></p>
                </a>
                <ul class="nav nav-treeview">
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/venue/listview" class="nav-link" id="selectindex_12">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>对账</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/venue/listview" class="nav-link" id="selectindex_13">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>提现明细</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/venue/listview" class="nav-link" id="selectindex_14">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>基本户流水</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/venue/listview" class="nav-link" id="selectindex_15">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>运营户流水</p>
                        </a>
                    </li>
                </ul>
            </li>

            <li class="nav-item has-treeview">
                <a href="#" class="nav-link">
                    <i class="fa fa-calendar-check-o fa-fw" aria-hidden="true"></i>
                    <p>订场<i class="fa fa-angle-left right"></i></p>
                </a>
                <ul class="nav nav-treeview">
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/amount/listview" class="nav-link" id="selectindex_17">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>提现</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/combine/listview" class="nav-link" id="selectindex_19">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>散拼</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebBackAPI/admin/train/listview" class="nav-link" id="selectindex_20">
                            <i class="fa fa-list-alt nav-icon"></i>
                            <p>培训</p>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </nav>
</div>
<div id="myinfoDiv" class="otherDialog hide">
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
                    <span class="float-left" style="margin-left: 5px; position: relative; margin-top: -3px;">修改密码</span>
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
				                url : "/WebBackAPI/admin/staff/updatePassword",//路径  
				                data : $("#infoForm").serialize(),//数据，这里使用的是Json格式进行传输  
				                dataType:"json",
				                success : function(result) {//返回数据根据结果进行相应的处理  
				                    if ( result.code == 200 ) {  
		                        		window.location.href="/WebBackAPI/admin/common/login";
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
        </div>
    </div>
</div>
