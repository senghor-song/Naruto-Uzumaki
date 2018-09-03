<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>房产易推房登陆页-房产中介公司营销管理的软件</title>
<link href="<%=path%>/Static/Css/common.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/Static/Css/Testimonials.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/Static/Css/common.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/Static/Css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/Static/Css/leaveWord.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/Static/Css/index.css" rel="stylesheet" type="text/css">
<script src="<%=path%>/Static/Js/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/Static/Js/respond.js"></script>
<script src="<%=path%>/Static/ Js/bootstrap.js"></script>
</head>
<body>
	<div class="bh1 ">
		<div class="bh2 body_1 " style="width: 960px;">
			<a href="../Index.html" style="float: left;" class="logo1"><img alt="房产易推房官网" src="<%=path%>/Static/Picture/logo.jpg"></a>
			<div class="br">
				<img style="float: right;" src="<%=path%>/Static/Picture/mt2.jpg">
			</div>
		</div>
	</div>
	<div class="m_b">
		<div class="body_1">
			<div style="margin-left: 100px;" class="mb1">
				<div class="b">
					<form name="xgy" id="xgy" action="/WebRelease/index/saveLeaveWord" method="post" enctype="multipart/form-data">
						<!-- <div class="ttp">
							<span>图片:</span><input name="img1" id="img1" size="30" type="file">
						</div> -->
						<input type="hidden" value="${sessionScope.loginuser.id}" name="userid" id="userid" disabled>
						<div class="m_input">
							<input type="text" value="${sessionScope.loginuser.emp}" name="emp" id="emp" disabled>
						</div>
						<div class="m_input">
							<input type="text" value="${sessionScope.loginuser.empStore.empstore}" name="empstore" id="empstore" disabled>
						</div>
						<div class="m_input">
							<input type="text" value="${sessionScope.loginuser.tel}" name="tel" id="tel" disabled>
						</div>
						<div class="m_input">
							<input type="text" value="${sessionScope.loginuser.empStore.cityT.city}" name="city" id="city" disabled>
						</div>
						<textarea onblur="if (this.value=='')this.value='您的留言';" onclick="if (this.value=='您的留言')this.value=''"
							style="font-size: 12px; font-weight: normal;" id="detail" name="detail" class="textarea">您的留言</textarea>
						<div class="mbu">
							<a onclick='mtj()' style="cursor: pointer">
							<img src="<%=path%>/Static/Picture/bu.jpg"></a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
	
		function mtj() {
			var text = $.trim($("#detail").val());

			if (text == "" || text == "您的留言") {
				alert("留言要填写");
				return;
			}

			$.ajax({
				type : "POST",
				url : '/WebRelease/index/saveLeaveWord',
				data : {userid:$("#userid").val(),detail:$("#detail").val()},
				dataType : "json",
				success : function(rs) {
					if(rs.code == 200){
						alert("留言成功");
					}else{
						alert("留言失败");
					}
				},
				error : function(rs) {
				}
			})
		}
	</script>
	<div class="footw">
		<div class="body_1 foot1">
			<ul class="navr">
				<li><a href="">首页</a></li>
				<li><a href="../page-1.html">关于我们</a>
					<ul class="">
						<li><a href="../page-1.html">公司介绍</a></li>
						<li><a href="../page-2.html">团队介绍</a></li>
						<li><a href="../page-3.html">成长经历</a></li>
						<li><a href="../page-4.html">人才招聘</a></li>
						<li><a href="../Newslist-3.html">新闻中心</a></li>
					</ul>
				</li>
				<li><a href="../gr.html">产品介绍</a>
					<ul class="">
						<li><a href="../gr.html">个人版</a></li>
						<li><a href="../qy.html">企业版</a></li>
						<li><a href="../jg.html">产品价格</a></li>
					</ul>
				</li>
				<li><a href="../Case.html">客户见证</a>
					<ul class="">
						<li><a href="../Case.html">客户案例</a></li>
						<li><a href="../Message.html">用户感言</a></li>
					</ul>
				</li>
				<li><a href="../Agent.html"> 代理加盟 </a>
					<ul class="">
						<li><a href="../Agent.html">合作政策</a></li>
						<li><a href="../Cooperation.html">市场合作</a></li>
						<li><a href="../apply.html">加盟申请</a></li>
					</ul>
				</li>
				<li><a href="../Customer.html">客服中心</a>
					<ul class="">
						<li><a href="../Customer.html">客户服务</a></li>
						<li><a href="../feedback.html">客户反馈</a></li>
						<li><a href="../Contact.html">联系方式</a></li>
					</ul>
				</li>
				<li><a href="../Help-4.html">帮助中心</a>
					<ul class="">
						<li><a href="../Help-4.html">视频教程</a></li>
						<li><a href="../Help-5.html">常见问题</a></li>
						<li><a href="../Help-6.html">支付问题</a></li>
						<li><a href="../Help-7.html">会员问题</a></li>
					</ul>
				</li>
				<li><a href="#">会员中心</a>
					<ul class="">
						<li><a href="../registerPC.html">会员注册</a></li>
						<li><a href="../loginPage.html">会员登录</a></li>
						<li><a href="#">推广中心</a></li>
						<li><a href="../Member-8.html">经验心得</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<div class="body_1 foot2">
			<img src="<%=path%>/Static/Picture/b9.jpg" style="width: 1000px;">
			<table align="center" style="margin: 0 auto">
				<tr>
					<td><img src="<%=path%>/Static/Picture/b10.jpg"></td>
					<td>&nbsp;&nbsp;&nbsp; <span style="position: relative; top: 5px">版权所有 © 厦门市好景科技有限公司 闽ICP备09006319号
						 <script type="text/javascript">
						var cnzz_protocol = (("https:" == document.location.protocol) ? " https://"
								: " http://");
						document
								.write(unescape("%3Cspan id='cnzz_stat_icon_1259598464'%3E%3C/span%3E%3Cscript src='"
										+ cnzz_protocol
										+ "s95.cnzz.com/z_stat.php%3Fid%3D1259598464%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));
						</script></span>
					</td>
				</tr>
			</table>
			<br>
		</div>
		<br>
		<br>
	</div>
	<script src="<%=path%>/Static/Js/jquery.lazyload.min.js"></script>
	<script type="text/javascript">
		if ($('.js-xms-lazy').length > 0) {
			$('.js-xms-lazy').lazyload();
		}
	</script>
</body>
</html>