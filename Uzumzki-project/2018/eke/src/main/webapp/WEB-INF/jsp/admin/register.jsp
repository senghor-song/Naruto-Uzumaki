<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>注册</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/WebRelease/admin/static/css/site.css" >
	<link rel="stylesheet" href="/WebRelease/admin/static/plugins/bootstrap-validator/vendor/bootstrap/css/bootstrap.css" />
	<link rel="stylesheet" href="/WebRelease/admin/static/plugins/bootstrap-validator/dist/css/bootstrapValidator.css" />
    <style>
        html,
        body {
            height: 100%;
        }

        .has-feedback {
            clear: both;
        }

        .has-feedback .titlen {
            float: left;
            position: relative;
            top: 5px;
            margin-right: 12px;
            color: #333;
        }

        body .reg0 {
            background: #e9ecef;
        }

        body .reg1 {
            margin-top: 200px;
            width: 431px;
            background: #fff;
            border-radius: 5px;
        }

        body .reg2 {
            width: 431px;
            height: 80px;
            border-bottom: 1px solid #ececec
        }

        body .reg3 {
            font-size: 24px;
            top: 28px;
            position: relative;
        }

        body .reg4 {
            width: 431px;
            padding: 34px 32px;
        }

        body .reg5 {
            width: 60%;
            float: left;
        }

        body .reg6 {
            margin-top: 8px;
        }

        body .reg10 {
            width: 90px !important;
            left: 12px;
            position: relative;
            top: 4px;
        }

        body .reg17 {
            position: relative;
            top: 10px;
        }

        body .reg18 {
            /* width: 60%; */
            margin-top: 10px;
        }

        body .reg19 {
            margin-top: 20px
        }

        body .reg20 {
            color: #222;
        }
        #form .control-label{ font-weight: 400;}
    </style>
</head>

<body class="hold-transition reg0" style="background: url(/WebRelease/admin/static/image/bg1.jpg) no-repeat center center;">

    <div class="login-box shadow-lg reg1">
        <div class="clearfix reg2">
            <div class="text-center reg3">用户注册</div>
        </div>
        <section>
            <div class="col-lg-12 mt-3">
                <form id="form" method="post" class="form-horizontal" action="target.php">
                    <div class="form-group">
                        <span class="col-lg-3 control-label">英文名</span>
                        <div class="col-lg-9">
                            <input type="text" class="form-control col-lg-12" name="ename" id="ename" placeholder="将作为登录名"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">中文名</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control col-lg-12" name="rname" id="rname" placeholder="真实姓名"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">手机号</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control col-lg-8" name="tel" id="tel" placeholder="请输入11位手机号" />
                            <input class="btn btn-default btn-block btn-flat btn-sm reg10" type="button" value="获取" id="getSMSCode" onclick="sendemail()">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">验证码</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control col-lg-12" name="smscode" id="smscode" placeholder="4位验证码"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">密码</label>
                        <div class="col-lg-9">
                            <input type="password" class="form-control col-lg-12" name="password" id="password" placeholder="6-16位密码"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">推荐人</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control col-lg-12" name="referee" id="referee" placeholder="如没有推荐人,则跳过" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12">
                    		<button class="btn btn-primary btn-block btn-flat" id="postBtn" type="button">提交注册</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-9 col-lg-offset-3">
                			<a class="text-center reg20" href="/WebRelease/admin/common/login">返回登录</a>
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <form action="/WebRelease/admin/" method="post" class="reg4" id="form2">
         <div class="has-feedback clearfix">
            </div>
        </form>
    </div>

    <!-- jQuery --><!-- 
    <script src="/WebRelease/admin/static/plugins/jquery/jquery.min.js"></script>
    <script src="/WebRelease/admin/static/js/jqBootstrapValidation-1.3.7.min.js"></script>
    <script src="/WebRelease/admin/static/js/jq-ext.js"></script> -->
	<script type="text/javascript" src="/WebRelease/admin/static/plugins/bootstrap-validator/vendor/jquery/jquery-1.10.2.min.js"></script>
    <script src="/WebRelease/admin/static/plugins/layer/layer.js"></script>
	<script type="text/javascript" src="/WebRelease/admin/static/plugins/bootstrap-validator/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/WebRelease/admin/static/plugins/bootstrap-validator/dist/js/bootstrapValidator.js"></script>
    <script>
	 	// 验证手机号
	    function isPhoneNo(phone) {
	        var pattern = /^1[34578]\d{9}$/;
	        return pattern.test(phone);
	    }
    	var countdown=60; 
	    function sendemail(){
	        var obj = $("#getSMSCode");
	        if(!isPhoneNo($("#tel").val())){
          		layer.msg("请输入正确手机号");
	        }else{
		    	var phone = $("#tel").val();
	        	$.ajax({  
	                type : "POST",  //提交方式  
	                url : "/WebRelease/admin/common/getSMSCode",//路径  
	                data : {  
	                    "phone" : phone,
	                    "type" : "0"
	                },//数据这里使用的是Json格式进行传输  
	                dataType:"json",
	                success : function(result) {//返回数据根据结果进行相应的处理  
	                	layer.msg("发送成功,请注意查收");
	                }  
	            });
		        settime(obj);
	        }
	    }
	    function settime(obj) { //发送验证码倒计时
	        if (countdown == 0) { 
	            obj.attr('disabled',false); 
	            obj.val("获取");
	            countdown = 60; 
	            return;
	        } else { 
	            obj.attr('disabled',true);
	            obj.val("重新发送(" + countdown + ")");
	            countdown--; 
	        } 
	    	setTimeout(function() {settime(obj) },1000);
	    }
        $(function () {
        	$('#form').bootstrapValidator({
//              live: 'disabled',
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
                              message: '手机号不能为空'
                          },
                          stringLength: {
                              min: 11,
                              max: 11,
                              message: '手机号只能为11位数字'
                          },
                          regexp: {
                              regexp: /^1[34578]\d{9}$/,
                              message: '请输入正确的手机号'
                          },
                      }
                  },
                  smscode: {
                      validators: {
                          notEmpty: {
                              message: '验证码不能为空'
                          },
                          stringLength: {
                              min: 4,
                              max: 4,
                              message: '请输入4位验证码'
                          }
                      }
                  },
                  password: {
                      validators: {
                          notEmpty: {
                              message: '密码不能为空'
                          },
                          stringLength: {
                              min: 6,
                              max: 16,
                              message: '密码最低6位最多16位'
                          }
                      }
                  }
              }
          });

          // Validate the form manually
          $('#postBtn').click(function() {
              $('#form').bootstrapValidator('validate');
			var ename = $("#ename").val();
          	var rname = $("#rname").val();
          	var tel = $("#tel").val();
          	var smscode = $("#smscode").val();
          	var password = $("#password").val();
          	if(ename == ''){
          		layer.msg("英文名必须填写");
          		return;
          	}
      		if(rname == ''){
          		layer.msg("中文名必须填写");
          		return;
          	}
      		if(!isPhoneNo(tel)){
          		layer.msg("电话号码必须填写正确");
          		return;
          	}
      		if(smscode == ''){
          		layer.msg("短信验证码必须填写");
          		return;
          	}
      		if(password == ''){
          		layer.msg("密码必须填写");
          		return;
          	}
			$.ajax({  
				type : "POST",  //提交方式  
				url : "/WebRelease/admin/common/register",//路径  
				data : {  
					"ename" : ename,
					"password" : password,
					"rname" : rname,
					"tel" : tel,
					"smscode" : smscode
				},//数据，这里使用的是Json格式进行传输  
                dataType:"json",
                success : function(result) {//返回数据根据结果进行相应的处理  
	                if ( result.code == 200 ) {  
	            		layer.msg(result.msg);
	            		window.location.href="/WebRelease/admin/common/login";
	                } else {  
						layer.confirm(result.msg, {
							btn: ['确定'] //按钮
						});
					}  
				}  
			}); 
		});
});
</script>
</body>

</html>