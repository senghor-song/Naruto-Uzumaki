<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../../common/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>站点管理</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/siteManage_2864980e527d234c6433a0ad34fd0ccf.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/main_a541ef7359aad950c46de2d36c405a9d.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/introjs_03b43e32a5901af129087d7ae282fea4.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/tip-yellowsimple_d6a155759956acdb4b49b381d5bf5fbf.css' rel='stylesheet' type='text/css'></link>
  </head>
<body >
<input id="peakValue" type="hidden" value="3">
<input id="trueName" type="hidden" value="李先生">
<input id="city" type="hidden" value="755">
<input id="isCheckAccount" type="hidden" value="true"></input>
<input id="multiCity" type="hidden" value="true"></input>
<div class="saleHouse" id="saleHouse">
<div class="saleHouse-main" style="margin:0 8px">
	<input id="contextPath" value="" type="hidden"/>
	<input id="staticPath" value="//s.img.xms.demo.com" type="hidden"/>
	<input id="refreshUrl" value="//s.refresh.xms.demo.com/xms-refresh" type="hidden"></input>
	<input id="guideIndex" value="0" type="hidden"></input>
       	<ul class="sale-content-tip">
			<li>
				<span class="sale-tip-img"></span>
				<span>贴心提示:</span>
				<span class="sale-tip-colse"></span>
			</li>
			 <li>1、删除账号后刷新计划要重新设置。</li>
             <li>2、如果要修改账号名，请先删除旧账号再添加新账号。</li>
             <li>3、如果您添加的帐号在对应网站修改了密码，请及时同步更新在本站对应帐号的密码，如果没及时同步更新，将会造成发布或者刷新失败。</li>
			 <li><span class="siteManage-font">4、一个网站可添加3个账号,猛击"添账号"按钮!!!
				 (绑定账号时必须保证网站上的姓名或电话与易推房一致)
			 </span></li>
			 <li><span class="siteManage-font">5、鉴于新三网对账号登录限制严格，我们增加了绑定成功的账号在24小时内不能删除的限制</span></li>
			 <li><span class="siteManage-font">6、鉴于新三网账号登录严格，需发安居客"手机标签"房源的需要,请点击"短信验证"或"语音验证"验证</span></li>
			</ul>
           <div class="lay_card_panel">
           	<div class="main_hd">
                <div class="clear">
					<ul class="sale-tab-sub manager-top">
						<li class="sale-sub-menu selected">
							<a href="javascript:">全部网站</a>
						</li>
						<li class="sale-sub-menu">
							<a href="javascript:">已开通网站</a>
						</li>
						<li class="sale-sub-menu">
							<a href="javascript:">未开通网站</a>
						</li>
						<li class="sale-sub-menu">
							<a href="javascript:">操作日志</a>
						</li><!-- 
						<li class="sale-sub-menu">
							<a href="javascript:">免费注册</a>
						</li> -->
						<li class="sale-sub-menu">
							<a href="javascript:">向易推房推荐网站</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="sites_list_panel" >
				<c:forEach var="web" items="${list }">
					<div class="sites_list clear" userWebId="">
						<dl>
							<dd class="sites_pay_type" style="display: table">
								<ul class="account_ul">
									<c:if test="${web.refresh == 1}">
										<li><span>可以刷新</span></li>
									</c:if>
									<c:if test="${web.release == 1}">
										<li><span>可以发布</span></li>
									</c:if>
								</ul>
							</dd>
							<dd class="sites_logo">
								<a href="http://agent.fang.com/" target="_blank" rel="noreferrer"> <img width="100" height="40" src="${web.logourl }"
									title="${web.webname }" class="webBasic-img">
								</a>
								
								<c:if test="${web.charge == 1}">
									<span class="pay ispay"></span>
								</c:if>
								<c:if test="${web.charge == 0}">
									<span class="pay nopay"></span>
								</c:if>
							</dd>
							<dd class="sites_add" style="line-height: 0px">
								<ul class="manage_ul">
									<li><a class="ui-css ui-css-green add_account_function addAccount-guide" href="javascript:void(0)" userWebId="" webBasicId="55"
										delegable="1" webName="${web.id }" userWebCount="" style="z-index: 0 !important">添账号</a></li>
								</ul>
								<ul class="manage_ul">
									<li><a class="ui-css ui-css-green" href="${web.registerurl }" target="_blank" rel="noreferrer">去注册</a></li>
								</ul>
							</dd>
							<dd class="sites_account" style="display: table">
								<ul class="account_ul">
									<c:forEach var="massBind" items="${web.massBinds }">
										<li>
											<span class="siteManage-account-error" style="width: 120px"> 
												<input type="radio" name="${web.webname }" id="${massBind.id }" dis="1" isdef="false" liindex="1" userwebid="16199725"
											 	webbasicid="55" loginname="${massBind.account }" city="755" userwebcount="2" class="account-input" checked="checked"> 
												<label title="${massBind.account }" for="${massBind.id }"
												<c:if test="${massBind.checkavailable ==  0}">
													class="col-f60 font-bold"
												</c:if>
												>${massBind.account }</label>
											</span>
											<c:if test="${massBind.checkavailable ==  0}">
												<span class="siteManage-account-disable float-r">
													<span class="queMark-common float-r" title=""></span>
													<span class="tableOverflow max_width_130 float-r" title="${massBind.checkmes }">${massBind.checkmes }</span>
												</span>
											</c:if>
										</li>
									</c:forEach>
								</ul>
							</dd>
							<dd class="sites_manage" style="line-height: 0px">
								<c:if test="${!empty web.massBinds}">
									<ul class="manage_ul">
										<li><a href="javascript:void(0)" class="site_del_function ui-css ui-css-green">删除</a></li>
										<li><a href="javascript:void(0)" class="site_view_function ui-css ui-css-green">查看密码</a></li>
										<li><a class="ui-css ui-css-green" onclick="loginWeb(this)">登录后台</a></li>
									</ul>
								</c:if>
							</dd>
						</dl>
					</div>
				</c:forEach>
			</div>
			<div class="siteManagerLog" style="display:none"></div>
			<div class="reweb" style="display:none"></div>
		</div>
   </div>
</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.timers-1.2.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/map.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/checkInput.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js?skin=green' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/siteManage_024c6c6fdfac819716b412f8ff7ecf02.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/bindAccount_0bbed06e40fb8553a2b3f6d55ad93160.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_b016234725ebd1612a8f62bf4261c244.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.paginate.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.poshytip.min.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/intro_e523b61a7b0f0aa4d1bcc380363a6bba.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/introConfig_is_35d387b7f8f167a80d570c2f8d476f89.js' type='text/javascript'></script>
<script src="<%=path%>/Static/Js/rsa.js"></script>
<script src="<%=path%>/Static/Js/enter/BigInt.js"></script>
<script src="<%=path%>/Static/Js/enter/Barrett.js"></script>
<script src="<%=path%>/Static/Js/enter/RSA.js"></script> 
<script> 
var cityMap = new Map();
		cityMap.put("755", '深圳');
		cityMap.put("769", '东莞');
		cityMap.put("760", '中山');
		cityMap.put("752", '惠州');
		cityMap.put("20", '广州');
		cityMap.put("114", '惠东');
		cityMap.put("762", '河源');
		cityMap.put("115", '博罗');
</script>
</body>
</html>