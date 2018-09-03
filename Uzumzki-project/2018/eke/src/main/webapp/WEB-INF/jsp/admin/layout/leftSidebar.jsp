<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<a href="http://www.ekeae.com" class="brand-link">
    <img src="/WebRelease/admin/static/image/logo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
    <span class="brand-text font-weight-light">管理系统</span>
</a>

<div class="sidebar">
    <!-- Sidebar user panel (optional) -->
    <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
            <img src="/WebRelease/admin/static/plugins/img/user2-160x160.jpg" class="img-circle elevation-2 myinfo" alt="User Image" style="cursor: pointer;">
        </div>
        <div class="info">
            <a href="javascript:;" class="d-block myinfo">${sessionScope.adminloginuser.rname}（${sessionScope.adminloginuser.position}）</a>
        </div>
    </div>
    <!-- Sidebar Menu -->
    <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
            <!-- Add icons to the links using the .nav-icon class
   with font-awesome or any other icon font library -->
            <li class="nav-item">
                <a href="/WebRelease/admin/common/index" class="nav-link" id="selectindex_0">
                    <i class="nav-icon fa fa-dashboard"></i>
                    <p>控制台</p>
                </a>
            </li>

            <li class="nav-item has-treeview">
                <a href="#" class="nav-link">
                    <i class="nav-icon iconfont icon-xinpin"></i>
                    <p>新盘 <i class="fa fa-angle-left right"></i>
                    </p>
                </a>
                <ul class="nav nav-treeview">
                    <li class="nav-item">
                        <a href="/WebRelease/admin/town/listview" class="nav-link" id="selectindex_4">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>新盘</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebRelease/admin//listview" class="nav-link" id="selectindex_5">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>报备</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebRelease/admin//listview" class="nav-link" id="selectindex_6">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>驻场</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebRelease/admin//listview" class="nav-link" id="selectindex_7">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>储备</p>
                        </a>
                    </li>
                </ul>
            </li>

            <li class="nav-item has-treeview">
                <a href="#" class="nav-link">
                    <i class="nav-icon iconfont icon-jiaoyichenggong"></i>
                    <p>租售<i class="fa fa-angle-left right"></i>
                    </p>
                </a>
                <ul class="nav nav-treeview">
                    <li class="nav-item">
                        <a href="/WebRelease/admin/estate/listview" class="nav-link" id="selectindex_8">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>小区</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebRelease/admin/property/listview" class="nav-link" id="selectindex_9">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>房源</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebRelease/admin/propertyPub/listview" class="nav-link" id="selectindex_10">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>公盘</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebRelease/admin/custAppointResp/listview" class="nav-link" id="selectindex_12">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>约看</p>
                        </a>
                    </li>
                    <!-- <li class="nav-item">
                        <a href="/WebRelease/admin//listview" class="nav-link" id="selectindex_11">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>托管交易</p>
                        </a>
                    </li> -->
                </ul>
            </li>

            <li class="nav-item has-treeview">
                <a href="#" class="nav-link">
                    <i class="nav-icon iconfont icon-merchant"></i>
                    <p>商户<i class="fa fa-angle-left right"></i>
                    </p>
                </a>
                <ul class="nav nav-treeview">
                    <li class="nav-item">
                        <a href="/WebRelease/admin/store/listview" class="nav-link" id="selectindex_2">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>商户</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebRelease/admin/employee/listview" class="nav-link" id="selectindex_1">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>经纪人</p>
                        </a>
                    </li>
                    <!-- <li class="nav-item">
                        <a href="/WebRelease/admin//listview" class="nav-link" id="selectindex_3">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>储备经纪人</p>
                        </a>
                    </li> -->
                    <!--<li class="nav-item">
                        <a href="Lookat.html" class="nav-link" id="selectindex_30">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>约看</p>
                        </a>
                    </li>-->
                </ul>
            </li>

            <li class="nav-item has-treeview">
                <a href="#" class="nav-link">
                    <i class="nav-icon iconfont icon-webicon306"></i>
                    <p>伙伴<i class="fa fa-angle-left right"></i>
                    </p>
                </a>
                <ul class="nav nav-treeview">
                    <li class="nav-item">
                        <a href="/WebRelease/admin/staff/listview" class="nav-link" id="selectindex_13">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>伙伴</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebRelease/admin//listview" class="nav-link" id="selectindex_14">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>ACL</p>
                        </a>
                    </li>
                </ul>
            </li>

            <li class="nav-item has-treeview">
                <a href="#" class="nav-link">
                    <i class="nav-icon iconfont icon-yunyingzhongxinxianxing"></i>
                    <p>运营<i class="fa fa-angle-left right"></i>
                    </p>
                </a>
                <ul class="nav nav-treeview">
                    <li class="nav-item">
                        <a href="/WebRelease/admin/city/listview" class="nav-link" id="selectindex_15">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>城市</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebRelease/admin//listview" class="nav-link" id="selectindex_16">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>管理</p>
                        </a>
                    </li>
                    <!--<li class="nav-item">
                        <a href="FakeListing.html" class="nav-link" id="selectindex_17">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>假房源</p>
                        </a>
                    </li>-->
                    <li class="nav-item">
                        <a href="/WebRelease/admin/idea/listview" class="nav-link" id="selectindex_17">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>声音</p>
                        </a>
                    </li>
                </ul>
            </li>
			<li class="nav-item has-treeview">
                <a href="#" class="nav-link">
                    <i class="nav-icon iconfont icon-xinwenchuban"></i>
                    <p> 新闻
                        <i class="fa fa-angle-left right"></i>
                    </p>
                </a>
                <ul class="nav nav-treeview">
                    <li class="nav-item">
                        <a href="/WebRelease/admin/newsCust/listview" class="nav-link" id="selectindex_28">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>新闻</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebRelease/admin/newsHead/listview" class="nav-link" id="selectindex_18">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>楼讯</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebRelease/admin/newsBanner/listview" class="nav-link" id="selectindex_26">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>横幅</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebRelease/admin//listview" class="nav-link" id="selectindex_27">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>移动弹屏</p>
                        </a>
                    </li>
                </ul>
            </li>


            <li class="nav-item has-treeview">
                <a href="#" class="nav-link">
                    <i class="nav-icon iconfont icon-tuiguang"></i>
                    <p>推房<i class="fa fa-angle-left right"></i>
                    </p>
                </a>
                <ul class="nav nav-treeview">
                    <li class="nav-item">
                        <a href="/WebRelease/admin/order/listview" class="nav-link" id="selectindex_19">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>订单</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebRelease/admin//listview" class="nav-link" id="selectindex_20">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>个人采集</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebRelease/admin/massPropertyPublish/listview" class="nav-link" id="selectindex_21">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>群发任务</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebRelease/admin//listview" class="nav-link" id="selectindex_22">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>刷新任务</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/WebRelease/admin//listview" class="nav-link" id="selectindex_23">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>小区透视</p>
                        </a>
                    </li>
                    <!--<li class="nav-item">
                        <a href="CommonlyUsedCellName.html" class="nav-link" id="selectindex_24">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>常用小区名</p>
                        </a>
                    </li>-->
                    <li class="nav-item">
                        <a href="/WebRelease/admin/notice/listview" class="nav-link" id="selectindex_25">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>公告</p>
                        </a>
                    </li>
                </ul>
            </li>
            <li class="nav-item has-treeview">
                <a href="#" class="nav-link">
                    <i class="nav-icon iconfont icon-tuiguang"></i>
                    <p>订场<i class="fa fa-angle-left right"></i>
                    </p>
                </a>
                <ul class="nav nav-treeview">
                    <li class="nav-item">
                        <a href="/WebRelease/admin/reserve/listview" class="nav-link" id="selectindex_29">
                            <i class="fa fa-circle-o nav-icon"></i>
                            <p>场馆</p>
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
        </div>
    </div>
</div>
