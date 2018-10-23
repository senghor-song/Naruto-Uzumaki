<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<link href="/WebBackAPI/admin/static/css/site.css" rel="stylesheet">
<link rel="stylesheet" href="/WebBackAPI/admin/static/plugins/bootstrap-validator/dist/css/bootstrapValidator.css" />
<link rel="stylesheet" href="/WebBackAPI/admin/static/plugins/bootstrap-validator/vendor/bootstrap/css/dataValidator.css" />
<div class="contextMenuDialog" id="createcontent">
        <div class="card-body form-control" style="border:none;">
            <div class="row">
                <div class="col-lg-12">
                	<form action="#" id="form" class="form-horizontal">
                    	<div class="row mt-2">
	                        <div class="col-lg-3">
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-4 nametl2">中文名</div>
	                                <div class="col-lg-8">
	                                	<input name="name" id="name" class="form-control" type="text" class="w-100" placeholder="真实姓名">
	                                </div>
	                            </div>
                            </div>
	                        <div class="col-lg-3">
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-4 nametl2">电话</div>
	                                <div class="col-lg-8">
	                                    <input name="tel" id="tel" class="form-control" type="text" class="w-100" placeholder="手机号码">
	                                </div>
	                            </div>
                            </div>
	                        <div class="col-lg-3">
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-4 nametl2">密码</div>
	                                <div class="col-lg-8">
	                                    <input name="password" id="password" class="form-control" type="text" class="w-100" placeholder="不填写则初始密码为123456">
	                                </div>
	                            </div>
                            </div>
                            
	                        <div class="col-lg-3">
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-4 nametl2">状态</div>
	                                <div class="col-lg-8">
	                                    <select name="statusFlag" class="form-control" id="status">
	                                        <option value="正常">正常</option>
			                                <option value="冻结">冻结</option>
			                                <option value="异常">异常</option>
	                                    </select>
	                                </div>
	                            </div>
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-4 nametl2">岗位</div>
	                                <div class="col-lg-8">
	                                    <select name="position" class="form-control" id="position">
				                            <option value="经理">经理</option>
				                            <option value="职员">职员</option>
				                            <option value="临时">临时</option>
	                                    </select>
	                                </div>
	                            </div>
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-4 nametl2">权限</div>
	                                <div class="col-lg-8">
	                                    <select name="power" class="form-control" id="power">
				                            <option value="7">七级</option>
				                            <option value="6">六级</option>
				                            <option value="5">五级</option>
				                            <option value="4">四级</option>
				                            <option value="3">三级</option>
				                            <option value="2">二级</option>
				                            <option value="1">一级</option>
	                                    </select>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="col-lg-3 mt-2">
	                            <div class="row form-group has-feedback">
	                                <div class="col-lg-2 nametl2"></div>
	                                <div class="col-lg-10">
	                                    <button class="form-control btn btn-primary w-25" id="addStaff">创建</button>
	                                </div>
	                            </div>
	                        </div>
                    	</div>
	            	</form>
                </div>
            </div>
        </div>
    </div>
    
    <script src="/WebBackAPI/admin/static/plugins/jquery/jquery.min.js"></script>
    <script src="/WebBackAPI/admin/static/plugins/layer/layer.js"></script>
	<script type="text/javascript" src="/WebBackAPI/admin/static/plugins/bootstrap-validator/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/WebBackAPI/admin/static/plugins/bootstrap-validator/dist/js/bootstrapValidator.js"></script>
<script>
$(function () {
	$('#form').bootstrapValidator({
//      live: 'disabled',
      message: '请输入正确的内容',
      feedbackIcons: {
          valid: 'glyphicon glyphicon-ok',
          invalid: 'glyphicon glyphicon-remove',
          validating: 'glyphicon glyphicon-refresh'
      },
      fields: {
    	  ename: {
              validators: {
                  notEmpty: {
                      message: '英文名不能为空'
                  }
              }
          },
          rname: {
              validators: {
                  notEmpty: {
                      message: '中文名不能为空'
                  }
              }
          },
          tel: {
              validators: {
                  notEmpty: {
                      message: '电话不能为空'
                  }
              }
          }
    	}
	});
	$("#addStaff").click(function () {
        $('#form').bootstrapValidator('validate');
        //是否通过校验
        if($('#form').data('bootstrapValidator').isValid()){  
        	$.ajax({  
                type : "POST",  //提交方式  
                url : "/WebBackAPI/admin/staff/saveStaff",//路径  
                data : $("form").serialize(),//数据，这里使用的是Json格式进行传输  
                dataType:"json",
                success : function(result) {//返回数据根据结果进行相应的处理  
                    if ( result.code == 200 ) {  
                    	parent.window.location.href="/WebBackAPI/admin/staff/listview";
                    } else {  
                		layer.confirm(result.msg, {
                			btn: ['确定'] //按钮
                		});
                    }  
                }  
            }); 
        }
    });
});
</script>
