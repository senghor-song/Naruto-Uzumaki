<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/ruiec-functions" prefix="ruiec"%>

<!--top-->
<div class="Top">
	<div class="w1180">
    	<div class="c_6 fl">
        	欢迎您来到<a href="javascript:void(0);">二元期权</a>，投资有风险，选择需谨慎！
        </div>
        <div class="y-last fr">
        	<c:choose>
        		<c:when test="${empty session_user }">
        			<a href="${base }/login/view.shtml" class="cOrange">登录</a>
		            <em>|</em>
		        	<a href="${base }/register/view.shtml" class="cBlue">注册</a>
        		</c:when>
        		<c:otherwise>
        			<a href="${base }/user/userInfo/view.shtml" class="cOrange">${session_user.username }</a>
		            <em>|</em>
		        	<a href="${base }/login/out.shtml" class="cBlue">退出</a>
        		</c:otherwise>
        	</c:choose>
            <em>|</em>
        	<a href="javascript:void(0);">旗下APP</a>
            <em>|</em>
        	<a href="http://wpa.qq.com/msgrd?v=3&uin=${global.setting.qq }&site=qq&menu=yes">在线客服</a>
            <em>|</em>
        	<a href="javascript:void(0);">关于我们</a>
        	<a href="javascript:void(0);"><i class="icon iconfont c_9">&#xe610;</i></a>
        	<a href="javascript:void(0);"><i class="icon iconfont c_9">&#xe60f;</i></a>
            <em>|</em>
        	<span class="c_6"><i class="icon iconfont cBlue">&#xe611;</i> 4000-000-000</span>
        </div>
    </div>
</div>	
<!--top-->

<!--header-->
<div class="Head">
	<div class="w1180">
    	<div class="logo fl"></div>
        <div class="fr HNav">
        	<ul>
            	<li class="curr" id="1000"><a href="${base }/">二元期权</a></li>
            	<li id="2000"><a href="${base }/trade/view.shtml" target="_blank">交易中心</a></li>
            	<li id="3000"><a href="${base }/information/marketNews.shtml">行情资讯</a></li>
            	<li id="4000"><a href="${base }/tradeGuide/view.shtml">交易指南</a></li>
            	<li id="5000"><a href="${base }/joinUs/view.shtml">招商加盟</a></li>
            	<%-- <li id="6000"><a href="${base }">最新优惠</a></li> --%>
            	<li id="7000"><a href="${base }/aboutUs/view.shtml">关于我们</a></li>
            </ul>
        </div>
    </div>
</div>	
<!--header-->
<script type ="text/javascript" src="${base }/resources/home/js/artDialog4.1.7/artDialog.js?skin=blue"></script>
<script type ="text/javascript" src="${base }/resources/home/js/artDialog4.1.7/plugins/iframeTools.js"></script>
	
<script type="text/javascript">
$(function() {
	
	/* 顶部菜单回显 */
	var optionId = $("body").attr("headOptionId");
	if (optionId) {
		var $headOption = $("#" + optionId);
		$headOption.parent().find("li").removeClass();
		$headOption.addClass("curr");
	}
	
	var redirect = '${global.authorityCenterUrl }' + '/login?from=${global.setting.siteUrl }/user/memberCenter/view.shtml';
	$(document).ajaxError(function( event, jqxhr, settings, errorThrown ) {
		var errorMsg = jqxhr.getResponseHeader("loginStatus");
		if(errorMsg){
			if(errorMsg == "accessDenied"){
				layer.confirm("请先登录？", {icon: 3}, function(){
					window.location.href = redirect;
	            });
				/* artDialog({
					id: 'Prompt',
					icon: 'question',
					fixed: true,
					lock: true,
					opacity: .1,
					content: '请先登录',
					init: function () {},
					ok: function () {
						//var redirect = '${global.authorityCenterUrl }' + '/login?from=' + window.location.href + '&hello=good';
						var redirect = '${global.authorityCenterUrl }' + '/login?from=${global.setting.siteUrl }/user/memberCenter/view.shtml';
						window.location.href = redirect;
					},
					cancel: true
				}); */

				return;
			}
		}
	});
});
</script>
