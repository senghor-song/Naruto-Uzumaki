<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>云刷新管理</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/basic.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/refresh_73486e9c079c120215cbb874064cc87d.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/loginSet.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/tip-yellowsimple_d6a155759956acdb4b49b381d5bf5fbf.css' rel='stylesheet' type='text/css'></link>
  </head>
<body>
<div class="" style="display: none; position: absolute;"><div class="aui_outer"><table class="aui_border"><tbody><tr><td class="aui_nw"></td><td class="aui_n"></td><td class="aui_ne"></td></tr><tr><td class="aui_w"></td><td class="aui_c"><div class="aui_inner"><table class="aui_dialog"><tbody><tr><td colspan="2" class="aui_header"><div class="aui_titleBar"><div class="aui_title" style="cursor: move;"></div><a class="aui_close" href="javascript:/*artDialog*/;">×</a></div></td></tr><tr><td class="aui_icon" style="display: none;"><div class="aui_iconBg" style="background: none;"></div></td><td class="aui_main" style="width: auto; height: auto;"><div class="aui_content" style="padding: 20px 25px;"></div></td></tr><tr><td colspan="2" class="aui_footer"><div class="aui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="aui_e"></td></tr><tr><td class="aui_sw"></td><td class="aui_s"></td><td class="aui_se" style="cursor: se-resize;"></td></tr></tbody></table></div></div>
	<div class="saleHouse" style="min-height: 871px;">
	    <input type="hidden" id="tabIndex" value="${tabIndex}">
	    <input type="hidden" id="staticPath" value="//s.img.xms.4846.com">
	    <input type="hidden" id="basePath" value="/xms-refresh">
	    <div class="col_main" id="col_main_function">
	        <div class="main_hd">
	            <div id="topTab" class="title_tab">
	                <ul class="title_tab_navs" id="refresh_title_tab_navs_function">
	                    <li class="title_tab_nav js_top <c:if test="${tabIndex == 0}">selected</c:if>">
	                        <a>刷新设置</a>
	                    </li>
	                    <li id="refresh_1" class="title_tab_nav js_top <c:if test="${tabIndex == 1}">selected</c:if>">
	                        <a>刷新统计</a>
	                    </li>
	                    <li id="refresh_2" class="title_tab_nav js_top <c:if test="${tabIndex == 2}">selected</c:if>">
	                        <a>刷新日志</a>
	                    </li>
	                    <li id="refresh_3" class="title_tab_nav js_top <c:if test="${tabIndex == 3}">selected</c:if>">
	                        <a>操作日志</a>
	                    </li>
	                </ul>
	            </div>
	        </div>
	        <div class="main_bd main_bd_function" <c:if test="${tabIndex != 0}"> style="display: none;"</c:if>>
	            <ul class="sale-content-tip" style="margin:8px 8px 0 8px">
					<li>
						<span class="sale-tip-img"></span>
						<span>功能说明:</span>
						<span class="sale-tip-colse"></span>
					</li>
					<li>1.本功能针对搜房帮等支持刷新的付费端口，帮您根据用户访问时间，手动刷新房源，提高端口展现效果。</li>
					<li>2.您可以按房源类型（售/租）、房型分布、价格分布、小区分布或选择重点房源灵活设置新条件。</li>
					<li>3.您可以在每个网站上同时开启多个刷新计划，每个刷新计划独立进行。所以请尽量不要为同一时间设置多个刷新计划。</li>
					<li>4.针对安居客进行活跃度优化，每天自动为您登录安居客，使您的房源排名更靠前。</li>
					<li>5."定时刷光"指系统会在指定的某个时间点自动检测您端口里的刷新量，如果还有剩余刷新量，会在这个时间点一次性帮您刷光。</li>
					<li>6."智能补推"指系统会定期检测您端口里的在线房源数量，如果不满，会将下架的房源进行上架，帮您补足在线房源库存。</li>
				</ul>
	            <div class="lay_card_panel" id="lay_card_panel_function"><div class="lay_card"><div class="lay_card_top"><img class="logo" src="http://xms-dimg.oss-cn-hangzhou.aliyuncs.com/pic/logo/soufun.png"><div class="content blue_bg"><ul class="left_part" id="plan_select_function"><li class="account" title="soufun-s59284995" usable="1">soufun-s5928...</li><li class="plan_select_panel"><div class="plan_select_content"><span class="float-l" style="color:#f00;border:none">尚未启用刷新计划...</span><img class="select_btn" src="//s.img.xms.4846.com/refresh/images/select_btn.png"></div><div class="plan-select clear"><dl class="plan_select_list" style="display:none"><dd style="color:#827F7F"><p id="planName_55_16199725_0" class="tableOverflow tbable80 float-l">刷新计划一</p>(未设置)<span class="set_plan_function" pid="55" tid="0" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16199725_1" class="tableOverflow tbable80 float-l">刷新计划二</p>(未设置)<span class="set_plan_function" pid="55" tid="1" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16199725_2" class="tableOverflow tbable80 float-l">刷新计划三</p>(未设置)<span class="set_plan_function" pid="55" tid="2" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16199725_3" class="tableOverflow tbable80 float-l">刷新计划四</p>(未设置)<span class="set_plan_function" pid="55" tid="3" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16199725_4" class="tableOverflow tbable80 float-l">刷新计划五</p>(未设置)<span class="set_plan_function" pid="55" tid="4" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16199725_5" class="tableOverflow tbable80 float-l">刷新计划六</p>(未设置)<span class="set_plan_function" pid="55" tid="5" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16199725_6" class="tableOverflow tbable80 float-l">刷新计划七</p>(未设置)<span class="set_plan_function" pid="55" tid="6" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16199725_7" class="tableOverflow tbable80 float-l">刷新计划八</p>(未设置)<span class="set_plan_function" pid="55" tid="7" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16199725_8" class="tableOverflow tbable80 float-l">刷新计划九</p>(未设置)<span class="set_plan_function" pid="55" tid="8" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16199725_9" class="tableOverflow tbable80 float-l">刷新计划十</p>(未设置)<span class="set_plan_function" pid="55" tid="9" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16199725_10" class="tableOverflow tbable80 float-l">刷新计划十一</p>(未设置)<span class="set_plan_function" pid="55" tid="10" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16199725_11" class="tableOverflow tbable80 float-l">刷新计划十二</p>(未设置)<span class="set_plan_function" pid="55" tid="11" refreshrefcurv="1">设置</span></dd></dl></div></li><li><span class="set_plan_function" pid="55" tid="0" refreshrefcurv="1">设置</span></li><li><span class="refresh_stat_function">刷新报告</span></li></ul><ul class="right_part"><li class="time" style="padding:0 0 0 4px;line-height:29px"><div>定时刷光:<span class="set_time_function mr8">23:00</span><span class="close_time_function" configid="2502831">停用</span></div><div>智能补推:<span class="set_push_function" pushtime="" title="设置在选择时间段内，当后台推送的房源量低于总推送量的60%会自动推满房源">设置</span></div></li></ul><ul><li style="border-left:1px solid #cacaca;"><input type="checkbox" id="defaultMode_soufun-s59284995_55" class="defaultMode" checked=""><label for="defaultMode_soufun-s59284995_55" class="defaultModeFont">智能补刷</label></li></ul></div></div><div class="block"></div><div class="lay_card_bottom"><div class="plan_detail_panel blue_bg" style="display:none"><div class="detail_panel_bar"><i class="hidde_panel_function"></i><font>【计划<font class="ft_planNo"></font>】</font><span class="dateTypeName"></span><font class="ft_times"></font>次刷新,合计刷新<font class="ft_count"></font>条房源</div><div id="charts_container_55_16199725" style="width: 620px; height: 100px; margin: 0 auto; margin-top: 10px;"></div><div class="panel_tab"></div><div class="panel_tab_bar clearfix"><div class="tab_bar_buttons"><span class="add_condition_function">添加条件</span><span class="edit_batch_function">批量修改</span><span class="del_batch_function">批量删除</span></div></div></div></div></div><div class="lay_card"><div class="lay_card_top"><img class="logo" src="http://xms-dimg.oss-cn-hangzhou.aliyuncs.com/pic/logo/soufun.png"><div class="content blue_bg"><ul class="left_part" id="plan_select_function"><li class="account" title="evergreensakura7" usable="1">evergreensak...</li><li class="plan_select_panel"><div class="plan_select_content"><span class="float-l" style="color:#f00;border:none">尚未启用刷新计划...</span><img class="select_btn" src="//s.img.xms.4846.com/refresh/images/select_btn.png"></div><div class="plan-select clear"><dl class="plan_select_list" style="display:none"><dd style="color:#827F7F"><p id="planName_55_16994034_0" class="tableOverflow tbable80 float-l">刷新计划一</p>(未设置)<span class="set_plan_function" pid="55" tid="0" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16994034_1" class="tableOverflow tbable80 float-l">刷新计划二</p>(未设置)<span class="set_plan_function" pid="55" tid="1" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16994034_2" class="tableOverflow tbable80 float-l">刷新计划三</p>(未设置)<span class="set_plan_function" pid="55" tid="2" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16994034_3" class="tableOverflow tbable80 float-l">刷新计划四</p>(未设置)<span class="set_plan_function" pid="55" tid="3" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16994034_4" class="tableOverflow tbable80 float-l">刷新计划五</p>(未设置)<span class="set_plan_function" pid="55" tid="4" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16994034_5" class="tableOverflow tbable80 float-l">刷新计划六</p>(未设置)<span class="set_plan_function" pid="55" tid="5" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16994034_6" class="tableOverflow tbable80 float-l">刷新计划七</p>(未设置)<span class="set_plan_function" pid="55" tid="6" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16994034_7" class="tableOverflow tbable80 float-l">刷新计划八</p>(未设置)<span class="set_plan_function" pid="55" tid="7" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16994034_8" class="tableOverflow tbable80 float-l">刷新计划九</p>(未设置)<span class="set_plan_function" pid="55" tid="8" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16994034_9" class="tableOverflow tbable80 float-l">刷新计划十</p>(未设置)<span class="set_plan_function" pid="55" tid="9" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16994034_10" class="tableOverflow tbable80 float-l">刷新计划十一</p>(未设置)<span class="set_plan_function" pid="55" tid="10" refreshrefcurv="1">设置</span></dd><dd style="color:#827F7F"><p id="planName_55_16994034_11" class="tableOverflow tbable80 float-l">刷新计划十二</p>(未设置)<span class="set_plan_function" pid="55" tid="11" refreshrefcurv="1">设置</span></dd></dl></div></li><li><span class="set_plan_function" pid="55" tid="0" refreshrefcurv="1">设置</span></li><li><span class="refresh_stat_function">刷新报告</span></li></ul><ul class="right_part"><li class="time" style="padding:0 0 0 4px;line-height:29px"><div>定时刷光:<span class="set_time_function mr8">23:00</span><span class="close_time_function" configid="2542601">停用</span></div><div>智能补推:<span class="set_push_function" pushtime="" title="设置在选择时间段内，当后台推送的房源量低于总推送量的60%会自动推满房源">设置</span></div></li></ul><ul><li style="border-left:1px solid #cacaca;"><input type="checkbox" id="defaultMode_evergreensakura7_55" class="defaultMode" checked=""><label for="defaultMode_evergreensakura7_55" class="defaultModeFont">智能补刷</label></li></ul></div></div><div class="block"></div><div class="lay_card_bottom"><div class="plan_detail_panel blue_bg" style="display:none"><div class="detail_panel_bar"><i class="hidde_panel_function"></i><font>【计划<font class="ft_planNo"></font>】</font><span class="dateTypeName"></span><font class="ft_times"></font>次刷新,合计刷新<font class="ft_count"></font>条房源</div><div id="charts_container_55_16994034" style="width: 620px; height: 100px; margin: 0 auto; margin-top: 10px;"></div><div class="panel_tab"></div><div class="panel_tab_bar clearfix"><div class="tab_bar_buttons"><span class="add_condition_function">添加条件</span><span class="edit_batch_function">批量修改</span><span class="del_batch_function">批量删除</span></div></div></div></div></div><div class="lay_card"><div class="lay_card_top"><img class="logo" src="http://xms-dimg.oss-cn-hangzhou.aliyuncs.com/pic/logo/anjukeNew.png"><div class="content blue_bg"><ul class="left_part" id="plan_select_function"><li class="account" title="13923734655" usable="1">13923734655</li><li class="plan_select_panel"><div class="plan_select_content"><span class="tableOverflow tbable180 float-l" style="border:none">正在运行登陆计划一  <a class="show_panel_functionheader" pid="103" loginname="13923734655" userwebid="16202565" tid="0" refreshrefcurv="0">查看</a></span><img class="select_btn" src="//s.img.xms.4846.com/refresh/images/select_btn.png"></div><div class="plan-select clear"><dl class="plan_select_list" style="display:none"><dd><p id="planName_103_16202565_0" class="tableOverflow tbable80 float-l" title="登陆计划一">登陆计划一</p>(已启用)<span class="show_panel_function" pid="103" loginname="13923734655" userwebid="16202565" modify="0" tid="0" refreshrefcurv="0">查看</span><span style="color:#f00;border:1px solid #f00" name="span_plan_state" modify="0" pid="2019005">停止</span></dd></dl></div></li><li><span class="set_plan_function" pid="103" tid="0" refreshrefcurv="0">设置</span></li><li><span class="refresh_stat_function">登陆报告</span></li></ul><ul class="right_part"><li class="time" style="padding:0 0 0 4px;">智能补推:<span class="set_push_function mr8" pushtime="09-11">09-11</span><span class="close_push_function">停用</span></li></ul></div></div><div class="block"></div><div class="lay_card_bottom"><div class="plan_detail_panel blue_bg" style="display:none"><div class="detail_panel_bar"><i class="hidde_panel_function"></i><font>【计划<font class="ft_planNo"></font>】</font><span class="dateTypeName"></span><font class="ft_times"></font>次登陆</div><div id="charts_container_103_16202565" style="width: 620px; height: 100px; margin: 0 auto; margin-top: 10px;"></div><div class="panel_tab"></div><div class="panel_tab_bar clearfix"><div class="tab_bar_buttons"><span class="add_loginSet_function">添加条件</span></div></div></div></div></div><div class="lay_card"><div class="lay_card_top"><img class="logo" src="http://xms-dimg.oss-cn-hangzhou.aliyuncs.com/pic/logo/new_wubavip.png"><div class="content blue_bg"><ul class="left_part" id="plan_select_function"><li class="account col-f60 font-bold" title="13923734655" usable="0">13923734655</li><li class="plan_select_panel"><div class="plan_select_content"><span class="float-l" style="color:#f00;border:none">尚未启用推送计划...</span><img class="select_btn" src="//s.img.xms.4846.com/refresh/images/select_btn.png"></div><div class="plan-select clear"><dl class="plan_select_list" style="display:none"><dd style="color:#827F7F"><p id="planName_104_16251986_0" class="tableOverflow tbable80 float-l">推送计划一</p>(未设置)<span class="set_plan_function" pid="104" tid="0" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_104_16251986_1" class="tableOverflow tbable80 float-l">推送计划二</p>(未设置)<span class="set_plan_function" pid="104" tid="1" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_104_16251986_2" class="tableOverflow tbable80 float-l">推送计划三</p>(未设置)<span class="set_plan_function" pid="104" tid="2" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_104_16251986_3" class="tableOverflow tbable80 float-l">推送计划四</p>(未设置)<span class="set_plan_function" pid="104" tid="3" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_104_16251986_4" class="tableOverflow tbable80 float-l">推送计划五</p>(未设置)<span class="set_plan_function" pid="104" tid="4" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_104_16251986_5" class="tableOverflow tbable80 float-l">推送计划六</p>(未设置)<span class="set_plan_function" pid="104" tid="5" refreshrefcurv="0">设置</span></dd></dl></div></li><li><span class="set_plan_function" pid="104" tid="0" refreshrefcurv="0">设置</span></li><li><span class="refresh_stat_function">推送报告</span></li></ul><ul class="right_part"><li class="time" style="padding:0 0 0 4px;line-height:29px"><div>定时推送:<span class="close_time_function" configid="0">停用</span></div><div><span class="set_pushTime_function mr8">00:01</span><span class="set_pushTime_function mr8">20:00</span><span class="set_pushTime_function mr8">1天</span></div></li></ul><ul><li style="border-left:1px solid #cacaca;"><input type="checkbox" id="defaultMode_13923734655_104" class="defaultMode"><label for="defaultMode_13923734655_104">智能补刷</label></li></ul></div></div><div class="block"></div><div class="lay_card_bottom"><div class="plan_detail_panel blue_bg" style="display:none"><div class="detail_panel_bar"><i class="hidde_panel_function"></i><font>【计划<font class="ft_planNo"></font>】</font><span class="dateTypeName"></span><font class="ft_times"></font>次推送,合计推送<font class="ft_count"></font>条房源</div><div id="charts_container_104_16251986" style="width: 620px; height: 100px; margin: 0 auto; margin-top: 10px;"></div><div class="panel_tab"></div><div class="panel_tab_bar clearfix"><div class="tab_bar_buttons"><span class="add_condition_function">添加条件</span><span class="edit_batch_function">批量修改</span><span class="del_batch_function">批量删除</span></div></div></div></div></div><div class="lay_card"><div class="lay_card_top"><img class="logo" src="http://xms-dimg.oss-cn-hangzhou.aliyuncs.com/pic/logo/sina.png"><div class="content blue_bg"><ul class="left_part" id="plan_select_function"><li class="account" title="13923734655" usable="1">13923734655</li><li class="plan_select_panel"><div class="plan_select_content"><span class="float-l" style="color:#f00;border:none">尚未启用刷新计划...</span><img class="select_btn" src="//s.img.xms.4846.com/refresh/images/select_btn.png"></div><div class="plan-select clear"><dl class="plan_select_list" style="display:none"><dd style="color:#827F7F"><p id="planName_24_16201359_0" class="tableOverflow tbable80 float-l">刷新计划一</p>(未设置)<span class="set_plan_function" pid="24" tid="0" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16201359_1" class="tableOverflow tbable80 float-l">刷新计划二</p>(未设置)<span class="set_plan_function" pid="24" tid="1" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16201359_2" class="tableOverflow tbable80 float-l">刷新计划三</p>(未设置)<span class="set_plan_function" pid="24" tid="2" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16201359_3" class="tableOverflow tbable80 float-l">刷新计划四</p>(未设置)<span class="set_plan_function" pid="24" tid="3" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16201359_4" class="tableOverflow tbable80 float-l">刷新计划五</p>(未设置)<span class="set_plan_function" pid="24" tid="4" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16201359_5" class="tableOverflow tbable80 float-l">刷新计划六</p>(未设置)<span class="set_plan_function" pid="24" tid="5" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16201359_6" class="tableOverflow tbable80 float-l">刷新计划七</p>(未设置)<span class="set_plan_function" pid="24" tid="6" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16201359_7" class="tableOverflow tbable80 float-l">刷新计划八</p>(未设置)<span class="set_plan_function" pid="24" tid="7" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16201359_8" class="tableOverflow tbable80 float-l">刷新计划九</p>(未设置)<span class="set_plan_function" pid="24" tid="8" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16201359_9" class="tableOverflow tbable80 float-l">刷新计划十</p>(未设置)<span class="set_plan_function" pid="24" tid="9" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16201359_10" class="tableOverflow tbable80 float-l">刷新计划十一</p>(未设置)<span class="set_plan_function" pid="24" tid="10" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16201359_11" class="tableOverflow tbable80 float-l">刷新计划十二</p>(未设置)<span class="set_plan_function" pid="24" tid="11" refreshrefcurv="0">设置</span></dd></dl></div></li><li><span class="set_plan_function" pid="24" tid="0" refreshrefcurv="0">设置</span></li><li><span class="refresh_stat_function">刷新报告</span></li></ul><ul class="right_part"><li class="time" style="padding:0 0 0 4px;line-height:29px"><div>定时刷光:<span class="set_time_function mr8">23:00</span><span class="close_time_function" configid="2502891">停用</span></div><div>智能补推:<span class="set_push_function" pushtime="" title="设置在选择时间段内，当后台推送的房源量低于总推送量的60%会自动推满房源">设置</span></div></li></ul><ul><li style="border-left:1px solid #cacaca;"><input type="checkbox" id="defaultMode_13923734655_24" class="defaultMode" checked=""><label for="defaultMode_13923734655_24" class="defaultModeFont">智能补刷</label></li></ul></div></div><div class="block"></div><div class="lay_card_bottom"><div class="plan_detail_panel blue_bg" style="display:none"><div class="detail_panel_bar"><i class="hidde_panel_function"></i><font>【计划<font class="ft_planNo"></font>】</font><span class="dateTypeName"></span><font class="ft_times"></font>次刷新,合计刷新<font class="ft_count"></font>条房源</div><div id="charts_container_24_16201359" style="width: 620px; height: 100px; margin: 0 auto; margin-top: 10px;"></div><div class="panel_tab"></div><div class="panel_tab_bar clearfix"><div class="tab_bar_buttons"><span class="add_condition_function">添加条件</span><span class="edit_batch_function">批量修改</span><span class="del_batch_function">批量删除</span></div></div></div></div></div><div class="lay_card"><div class="lay_card_top"><img class="logo" src="http://xms-dimg.oss-cn-hangzhou.aliyuncs.com/pic/logo/sina.png"><div class="content blue_bg"><ul class="left_part" id="plan_select_function"><li class="account col-f60 font-bold" title="13600164055" usable="0">13600164055</li><li class="plan_select_panel"><div class="plan_select_content"><span class="float-l" style="color:#f00;border:none">尚未启用刷新计划...</span><img class="select_btn" src="//s.img.xms.4846.com/refresh/images/select_btn.png"></div><div class="plan-select clear"><dl class="plan_select_list" style="display:none"><dd style="color:#827F7F"><p id="planName_24_16994084_0" class="tableOverflow tbable80 float-l">刷新计划一</p>(未设置)<span class="set_plan_function" pid="24" tid="0" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16994084_1" class="tableOverflow tbable80 float-l">刷新计划二</p>(未设置)<span class="set_plan_function" pid="24" tid="1" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16994084_2" class="tableOverflow tbable80 float-l">刷新计划三</p>(未设置)<span class="set_plan_function" pid="24" tid="2" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16994084_3" class="tableOverflow tbable80 float-l">刷新计划四</p>(未设置)<span class="set_plan_function" pid="24" tid="3" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16994084_4" class="tableOverflow tbable80 float-l">刷新计划五</p>(未设置)<span class="set_plan_function" pid="24" tid="4" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16994084_5" class="tableOverflow tbable80 float-l">刷新计划六</p>(未设置)<span class="set_plan_function" pid="24" tid="5" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16994084_6" class="tableOverflow tbable80 float-l">刷新计划七</p>(未设置)<span class="set_plan_function" pid="24" tid="6" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16994084_7" class="tableOverflow tbable80 float-l">刷新计划八</p>(未设置)<span class="set_plan_function" pid="24" tid="7" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16994084_8" class="tableOverflow tbable80 float-l">刷新计划九</p>(未设置)<span class="set_plan_function" pid="24" tid="8" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16994084_9" class="tableOverflow tbable80 float-l">刷新计划十</p>(未设置)<span class="set_plan_function" pid="24" tid="9" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16994084_10" class="tableOverflow tbable80 float-l">刷新计划十一</p>(未设置)<span class="set_plan_function" pid="24" tid="10" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_24_16994084_11" class="tableOverflow tbable80 float-l">刷新计划十二</p>(未设置)<span class="set_plan_function" pid="24" tid="11" refreshrefcurv="0">设置</span></dd></dl></div></li><li><span class="set_plan_function" pid="24" tid="0" refreshrefcurv="0">设置</span></li><li><span class="refresh_stat_function">刷新报告</span></li></ul><ul class="right_part"><li class="time" style="padding:0 0 0 4px;line-height:29px"><div>定时刷光:<span class="set_time_function">设置</span></div><div>智能补推:<span class="set_push_function" pushtime="" title="设置在选择时间段内，当后台推送的房源量低于总推送量的60%会自动推满房源">设置</span></div></li></ul><ul><li style="border-left:1px solid #cacaca;"><input type="checkbox" id="defaultMode_13600164055_24" class="defaultMode"><label for="defaultMode_13600164055_24" class="defaultModeFont">智能补刷</label></li></ul></div></div><div class="block"></div><div class="lay_card_bottom"><div class="plan_detail_panel blue_bg" style="display:none"><div class="detail_panel_bar"><i class="hidde_panel_function"></i><font>【计划<font class="ft_planNo"></font>】</font><span class="dateTypeName"></span><font class="ft_times"></font>次刷新,合计刷新<font class="ft_count"></font>条房源</div><div id="charts_container_24_16994084" style="width: 620px; height: 100px; margin: 0 auto; margin-top: 10px;"></div><div class="panel_tab"></div><div class="panel_tab_bar clearfix"><div class="tab_bar_buttons"><span class="add_condition_function">添加条件</span><span class="edit_batch_function">批量修改</span><span class="del_batch_function">批量删除</span></div></div></div></div></div><div class="lay_card"><div class="lay_card_top"><img class="logo" src="http://xms-dimg.oss-cn-hangzhou.aliyuncs.com/pic/logo/baixing1.jpg"><div class="content blue_bg"><ul class="left_part" id="plan_select_function"><li class="account" title="小百姓05091807297" usable="1">小百姓050918072...</li><li class="plan_select_panel"><div class="plan_select_content"><span class="float-l" style="color:#f00;border:none">尚未启用刷新计划...</span><img class="select_btn" src="//s.img.xms.4846.com/refresh/images/select_btn.png"></div><div class="plan-select clear"><dl class="plan_select_list" style="display:none"><dd style="color:#827F7F"><p id="planName_13_16058689_0" class="tableOverflow tbable80 float-l">刷新计划一</p>(未设置)<span class="set_plan_function" pid="13" tid="0" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_13_16058689_1" class="tableOverflow tbable80 float-l">刷新计划二</p>(未设置)<span class="set_plan_function" pid="13" tid="1" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_13_16058689_2" class="tableOverflow tbable80 float-l">刷新计划三</p>(未设置)<span class="set_plan_function" pid="13" tid="2" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_13_16058689_3" class="tableOverflow tbable80 float-l">刷新计划四</p>(未设置)<span class="set_plan_function" pid="13" tid="3" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_13_16058689_4" class="tableOverflow tbable80 float-l">刷新计划五</p>(未设置)<span class="set_plan_function" pid="13" tid="4" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_13_16058689_5" class="tableOverflow tbable80 float-l">刷新计划六</p>(未设置)<span class="set_plan_function" pid="13" tid="5" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_13_16058689_6" class="tableOverflow tbable80 float-l">刷新计划七</p>(未设置)<span class="set_plan_function" pid="13" tid="6" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_13_16058689_7" class="tableOverflow tbable80 float-l">刷新计划八</p>(未设置)<span class="set_plan_function" pid="13" tid="7" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_13_16058689_8" class="tableOverflow tbable80 float-l">刷新计划九</p>(未设置)<span class="set_plan_function" pid="13" tid="8" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_13_16058689_9" class="tableOverflow tbable80 float-l">刷新计划十</p>(未设置)<span class="set_plan_function" pid="13" tid="9" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_13_16058689_10" class="tableOverflow tbable80 float-l">刷新计划十一</p>(未设置)<span class="set_plan_function" pid="13" tid="10" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_13_16058689_11" class="tableOverflow tbable80 float-l">刷新计划十二</p>(未设置)<span class="set_plan_function" pid="13" tid="11" refreshrefcurv="0">设置</span></dd></dl></div></li><li><span class="set_plan_function" pid="13" tid="0" refreshrefcurv="0">设置</span></li><li><span class="refresh_stat_function">刷新报告</span></li></ul><ul class="right_part"><li class="time" style="padding:0 0 0 4px;"><div>定时刷光:<span class="set_time_function mr8">23:00</span><span class="close_time_function" configid="2495515">停用</span></div></li></ul><ul><li style="border-left:1px solid #cacaca;"><input type="checkbox" id="defaultMode_小百姓05091807297_13" class="defaultMode" checked=""><label for="defaultMode_小百姓05091807297_13" class="defaultModeFont">智能补刷</label></li></ul></div></div><div class="block"></div><div class="lay_card_bottom"><div class="plan_detail_panel blue_bg" style="display:none"><div class="detail_panel_bar"><i class="hidde_panel_function"></i><font>【计划<font class="ft_planNo"></font>】</font><span class="dateTypeName"></span><font class="ft_times"></font>次刷新,合计刷新<font class="ft_count"></font>条房源</div><div id="charts_container_13_16058689" style="width: 620px; height: 100px; margin: 0 auto; margin-top: 10px;"></div><div class="panel_tab"></div><div class="panel_tab_bar clearfix"><div class="tab_bar_buttons"><span class="add_condition_function">添加条件</span><span class="edit_batch_function">批量修改</span><span class="del_batch_function">批量删除</span></div></div></div></div></div><div class="lay_card" style="padding-bottom: 300px;"><div class="lay_card_top"><img class="logo" src="http://xms-dimg.oss-cn-hangzhou.aliyuncs.com/pic/logo/szhome2.jpg"><div class="content blue_bg"><ul class="left_part" id="plan_select_function"><li class="account col-f60 font-bold" title="13923734655" usable="0">13923734655</li><li class="plan_select_panel"><div class="plan_select_content"><span class="float-l" style="color:#f00;border:none">尚未启用刷新计划...</span><img class="select_btn" src="//s.img.xms.4846.com/refresh/images/select_btn.png"></div><div class="plan-select clear"><dl class="plan_select_list" style="display:none"><dd style="color:#827F7F"><p id="planName_91_16203191_0" class="tableOverflow tbable80 float-l">刷新计划一</p>(未设置)<span class="set_plan_function" pid="91" tid="0" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_91_16203191_1" class="tableOverflow tbable80 float-l">刷新计划二</p>(未设置)<span class="set_plan_function" pid="91" tid="1" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_91_16203191_2" class="tableOverflow tbable80 float-l">刷新计划三</p>(未设置)<span class="set_plan_function" pid="91" tid="2" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_91_16203191_3" class="tableOverflow tbable80 float-l">刷新计划四</p>(未设置)<span class="set_plan_function" pid="91" tid="3" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_91_16203191_4" class="tableOverflow tbable80 float-l">刷新计划五</p>(未设置)<span class="set_plan_function" pid="91" tid="4" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_91_16203191_5" class="tableOverflow tbable80 float-l">刷新计划六</p>(未设置)<span class="set_plan_function" pid="91" tid="5" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_91_16203191_6" class="tableOverflow tbable80 float-l">刷新计划七</p>(未设置)<span class="set_plan_function" pid="91" tid="6" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_91_16203191_7" class="tableOverflow tbable80 float-l">刷新计划八</p>(未设置)<span class="set_plan_function" pid="91" tid="7" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_91_16203191_8" class="tableOverflow tbable80 float-l">刷新计划九</p>(未设置)<span class="set_plan_function" pid="91" tid="8" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_91_16203191_9" class="tableOverflow tbable80 float-l">刷新计划十</p>(未设置)<span class="set_plan_function" pid="91" tid="9" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_91_16203191_10" class="tableOverflow tbable80 float-l">刷新计划十一</p>(未设置)<span class="set_plan_function" pid="91" tid="10" refreshrefcurv="0">设置</span></dd><dd style="color:#827F7F"><p id="planName_91_16203191_11" class="tableOverflow tbable80 float-l">刷新计划十二</p>(未设置)<span class="set_plan_function" pid="91" tid="11" refreshrefcurv="0">设置</span></dd></dl></div></li><li><span class="set_plan_function" pid="91" tid="0" refreshrefcurv="0">设置</span></li><li><span class="refresh_stat_function">刷新报告</span></li></ul><ul class="right_part"><li class="time" style="padding:0 0 0 4px;"><div>定时刷光:<span class="set_time_function">设置</span></div></li></ul><ul><li style="border-left:1px solid #cacaca;"><input type="checkbox" id="defaultMode_13923734655_91" class="defaultMode"><label for="defaultMode_13923734655_91" class="defaultModeFont">智能补刷</label></li></ul></div></div><div class="block"></div><div class="lay_card_bottom"><div class="plan_detail_panel blue_bg" style="display:none"><div class="detail_panel_bar"><i class="hidde_panel_function"></i><font>【计划<font class="ft_planNo"></font>】</font><span class="dateTypeName"></span><font class="ft_times"></font>次刷新,合计刷新<font class="ft_count"></font>条房源</div><div id="charts_container_91_16203191" style="width: 620px; height: 100px; margin: 0 auto; margin-top: 10px;"></div><div class="panel_tab"></div><div class="panel_tab_bar clearfix"><div class="tab_bar_buttons"><span class="add_condition_function">添加条件</span><span class="edit_batch_function">批量修改</span><span class="del_batch_function">批量删除</span></div></div></div></div></div></div>
	        </div>
	        <div class="main_bd main_bd_function" <c:if test="${tabIndex != 1}"> style="display: none;"</c:if>>
	            <table width="808">
	                <tbody><tr>
	                    <td valign="top">
	                        <ul class="stat_left"><li class="stat_select"><img width="82px" height="30px" src="http://xms-dimg.oss-cn-hangzhou.aliyuncs.com/pic/logo/soufun.png"><a class="moreselAge" href="javascript:" wid="55" loginname="soufun-s5928..." userwebid="16199725">soufun-s59284995</a></li><li class="stat_select"><img width="82px" height="30px" src="http://xms-dimg.oss-cn-hangzhou.aliyuncs.com/pic/logo/soufun.png"><a class="moreselAge" href="javascript:" wid="55" loginname="evergreensak..." userwebid="16994034">evergreensakura7</a></li><li class="stat_select"><img width="82px" height="30px" src="http://xms-dimg.oss-cn-hangzhou.aliyuncs.com/pic/logo/anjukeNew.png"><a class="moreselAge" href="javascript:" wid="103" loginname="13923734655" userwebid="16202565">13923734655</a></li><li class="stat_select"><img width="82px" height="30px" src="http://xms-dimg.oss-cn-hangzhou.aliyuncs.com/pic/logo/sina.png"><a class="moreselAge" href="javascript:" wid="24" loginname="13923734655" userwebid="16201359">13923734655</a></li><li class="stat_select"><img width="82px" height="30px" src="http://xms-dimg.oss-cn-hangzhou.aliyuncs.com/pic/logo/baixing1.jpg"><a class="moreselAge" href="javascript:" wid="13" loginname="小百姓050918072..." userwebid="16058689">小百姓05091807297</a></li></ul>
	                    </td>
	                    <td valign="top">
	                        <div class="refresh_statistics">
	                            <div id="container" style="width: 600px; height: 600px;"></div>
	                            <div id="chart_plugin1"></div>
	                        </div>
	                    </td>
	                    <td width="80" valign="top">
	                        <div class="date_item"><a class="selected" date="2018-07-04">07月04号</a><a date="2018-07-03">07月03号</a><a date="2018-07-02">07月02号</a><a date="2018-07-01">07月01号</a><a date="2018-06-30">06月30号</a><a date="2018-06-29">06月29号</a><a date="2018-06-28">06月28号</a></div>
	                    </td>
	                </tr>
	            </tbody></table>
	        </div>
        <div class="main_bd main_bd_function" <c:if test="${tabIndex != 2}"> style="display: none;"</c:if>>
	        <div class="lay_card_panel">
	        	<div class="reflesh-log-select">
					<span>网站: 
						<select class="reflesh_web web_select" id="query_web">
							<option value="">不限</option>
							<option value="搜房帮">搜房帮</option>
							<option value="安居客新版">安居客新版</option>
							<option value="58新版(安居客)">58新版(安居客)</option>
							<option value="新浪网">新浪网</option>
							<option value="百姓网">百姓网</option>
							<option value="深圳房信网">深圳房信网</option>
						</select>
					</span> 
					<span>账号: <input id="query_loginName" class="reflesh_input"></span> 
		            <span>结果:
			            <select id="query_failReason" class="reflesh_select">
							<option value="">不限</option>
							<option value="刷新成功">刷新成功</option>
							<option value="刷新失败">刷新失败</option>
						</select>
					</span> 
					<span>模式:
						<select id="query_type" class="reflesh_select">
							<option value="">不限</option>
							<option value="0">普通刷新</option>
							<option value="1">一键刷新</option>
							<option value="2">预约刷新</option>
							<option value="3">真实刷新</option>
							<option value="4">修改刷新</option>
						</select>
					</span> 
					<span>计划号:
						<select id="query_planNo" class="reflesh_select">
							<option value="">不限</option>
							<option value="计划一">计划一</option>
							<option value="计划二">计划二</option>
							<option value="计划三">计划三</option>
							<option value="计划四">计划四</option>
							<option value="计划五">计划五</option>
							<option value="计划六">计划六</option>
							<option value="计划七">计划七</option>
							<option value="计划八">计划八</option>
							<option value="计划九">计划九</option>
							<option value="计划十">计划十</option>
							<option value="计划十一">计划十一</option>
							<option value="计划十二">计划十二</option>
						</select>
					</span> 
					<span>日期:<select id="query_date" class="reflesh_select">
						<option value="7">近一周</option>
						<c:forEach items="${dateList}" var="date" varStatus="index" >
							<option value="${index.index}">${date}</option>
						</c:forEach>
						</select>
					</span>
					<button class="search-button" onclick="getRefreshLog(1);">查询</button>
	            </div>
	            <div id="refrechForm">
	            
	            </div>
        	</div>
	    </div>
	    <div id="refreshOperlog" class="main_bd main_bd_function" <c:if test="${tabIndex != 3}"> style="display: none;"</c:if>>
	    	<div class="reflesh-log-select">
	    		 <span>网站: 
	    		 	<select class="refleshlog_select web_select" id="web_operlog"><option value="-1">不限</option><option value="55">搜房帮</option><option value="103">安居客新版</option><option value="104">58新版(安居客)</option><option value="24">新浪网</option><option value="13">百姓网</option><option value="91">深圳房信网</option></select>
	    		 </span>
	    		 <span>操作类型: 
	    		 	<select class="reflesh_web" id="type_operlog">
	    		 		<option value="">不限</option>
	    		 		<option value="PLAN_STATE_ON">开启刷新计划</option>
	    		 		<option value="PLAN_STATE_OFF">停止刷新计划</option>
	    		 		<option value="PLAN_NEW">新增刷新计划</option>
	    		 		<option value="PLAN_MODIFY">修改刷新计划</option>
	    		 		<option value="REFRESHGG_STATE_OFF">停止刷光光</option>
	    		 		<option value="REFRESHGG_NEW">刷光光时间设置</option>
	    		 		<option value="REFRESHGG_MODIFY">刷光光时间修改</option>
	    		 		<option value="AUTOREFRESH_NEW">智能补推设置</option>
	    		 		<option value="AUTOREFRESH_MODIFY">智能补推修改</option>
	    		 		<option value="AUTOREFRESH_STATE_ON">启动智能补刷</option>
	    		 		<option value="AUTOREFRESH_STATE_OFF">停止智能补刷</option>
	    		 		<option value="SCHEDULE_PLAN">排班计划设置</option>
	    		 		<option value="SCHEDULE_PLAN_ON">开启排班计划</option>
	    		 		<option value="SCHEDULE_PLAN_OFF">停止排班计划</option>
	    		 		<option value="SCHEDULE_PLAN_DEL">删除排班计划</option>
	    		 		<option value="SCHEDULE_PLAN_STOP">停止子账号</option>
	    		 		<option value="AUTO_PLAN_NEW">托管刷新计划</option>
	    		 		<option value="AUTO_REFRESHGG_NEW">托管刷光光设置</option>
	    		 		<option value="SYNC_HOUSELIST">立即同步</option>
	    		 	</select>
	    		 </span>
	    		 <span>计划号:
	    		  	<select class="refleshlog_select" id="planNo_operlog">
                        <option value="-1">不限</option>
                        <option value="0">计划一</option>
                        <option value="1">计划二</option>
                        <option value="2">计划三</option>
                        <option value="3">计划四</option>
                        <option value="4">计划五</option>
                        <option value="5">计划六</option>
                        <option value="6">计划七</option>
                        <option value="7">计划八</option>
                        <option value="8">计划九</option>
                        <option value="9">计划十</option>
                        <option value="10">计划十一</option>
                        <option value="11">计划十二</option>
                    </select>
                 </span>
                 <span>操作日期:<select class="refleshlog_select" id="date_operlog"><option value="">近一周</option><option value="2018-07-04">07月04号</option><option value="2018-07-03">07月03号</option><option value="2018-07-02">07月02号</option><option value="2018-07-01">07月01号</option><option value="2018-06-30">06月30号</option><option value="2018-06-29">06月29号</option><option value="2018-06-28">06月28号</option></select></span>
                 <span>关键词:<input style="width:120px" class="reflesh_input" type="text" id="key_operlog" placeholder="操作员的名称或号码"></span>
                 <button class="search-button" onclick="getRefreshOperLog();">查询</button>
	    	</div>
	   	    <table class="hovertable">
			<thead>
				<tr>
					<th width="5%">序号</th>
					<th width="12%">网站</th>
					<th width="12%">账号</th>
					<th>计划号</th>
					<th width="8%">操作员</th>
					<th width="8%">操作员手机号</th>
					<th width="8%">操作类型</th>
					<th>旧值</th>
					<th>新值</th>
					<th width="15%">操作时间</th>
				</tr>
			</thead>
			<tbody id="tb_refreshOperLog">
	        </tbody>
	        </table>
			<div id="paging" align="center">
				<span id="lastSpan_log"></span> <span id="link_log"></span> <span id="nextSpan_log"></span> <span id="nowSpan_log"></span> 共<span
					id="rowCountSpan_log" style="color: red"></span>条记录
			</div>
		</div>
	</div>
</div>
<script src='<%=path%>/Static/Js/checkInput.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/map.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.timers-1.2.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/selectList.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/placeholder.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.artDialog-chrome.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/highcharts.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/exporting.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/constants_4c41e15c7e59a6ad5c7dfb8a93e373be.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/objects.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/refresh_812e620bb607c5dd364570f88074e356.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/loginSet_fd998b2de12256971bcdb859a1d5f8c0.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/pushSet_54f8b75714b3543a44c19fc44756037d.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.poshytip.min.js' type='text/javascript'></script>
<script src="<%=path%>/Static/Js/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		getRefreshLog(1);
	})
</script>
</body>
</html>