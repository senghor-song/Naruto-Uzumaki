<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>我的易推房--房产易推房</title>
	<%@include file="common/head.jsp"%>
  </head>
	<style type="text/css">
		.index_a{cursor:pointer }
	</style> 
<body>

<input type="hidden" id="contextPath" value="" />
    <input type="hidden" id="staticPath" value="//demo.com" />
    <input type="hidden" id="postTypeIndex" value="0" />
    <input type="hidden" id="xmsNotice" value="true" />
    <input type="hidden" id="wxQcode" value="false" />
    <input type="hidden" id="wxQcodeUrl" value="" />
    <input type="hidden" id="closeNewIndex" value="0"></input>
    <input type="hidden" id="intro_guide" value="false"></input>
    <input type="hidden" id="intro_index" value="0"></input>
    <input type="hidden" id="second" value="10"></input>
    <div class="main">
        <div id="header_background">
            <div id="header_center">
                <div id="header_logo">
                <img class="index_a" src="<%=path%>/Static/Picture/xms_logo.png" height="46" onclick="javascript:window.location.href='/WebRelease/index/index'" ></img>
                </div>
                <div id="header_note" class="header-note">
                    <span class="col-000">${sessionScope.loginuser.emp}
				    (${sessionScope.loginuser.tel}, ${sessionScope.loginuser.empStore.cityT.city}  ${sessionScope.loginuser.empStore.empstore}  ${sessionScope.loginuser.empStore.districtT.district})
				</span>
                    <input type="hidden" id="city" value="755" />
                    <span class="col-000">欢迎您！</span>
                </div>
                <div class="help-index">
                    <a href="/WebRelease/employee/loginout">安全退出</a>
                    <a href="/WebRelease/idnex/videoHelp" target="_blank" class="mr8">视频教程</a>
                    <a href="/WebRelease/index/document" target="_blank" class="mr8">快速入门</a><!-- 
                    <a href="/WebRelease/pay/payRenew" target="main-iframe" style="color:#f00;" class="mr8">我要续费</a> -->
                    <a href="/WebRelease/test/" target="main-iframe" style="color:#f00;" class="mr8">测试页面</a>
                </div>
            </div>
        </div>
        <div class="container_box">
            <div class="col_side">
                <div id="main_left">
                    <div id="menu_function" class="menu_box">
                        <dl class="menu">
                            <dt class="menu_title border-index" onclick="hrefLink('getMainRight')">
							<i class="icon_menu index_logo"></i>
							<span class="main-font">管理主页</span>
						</dt>
                        </dl>
                        <dl class="menu menu_import">
                            <dt class="menu_title" id="import_guide">
							<i class="icon_menu copy_logo"></i>
							<span class="main-font xms-relative">房源录入
							</span>
						</dt>
                            <dd class="menu_item">
                                <div id="import_item_guide">
                                    <a id="importSell" class="import_link_guide" href="/WebRelease/massProperty/houseEnter?postType=0"  target="main-iframe">出售录入</a>
                                    <a id="importRent" class="import_link_guide" href="/WebRelease/massProperty/houseEnter?postType=1"  target="main-iframe">出租录入</a>
                                </div>
                                <!-- <a id="publicbuilding" href="/WebRelease/massProperty/publicbuilding" class="xms-relative" target="main-iframe">
									网络房源<span class="menunew-bubble menunew-bubble-left">新</span>
								</a>
                                <a id="houseShare" href="/WebRelease/massProperty/sharebuilding"  target="main-iframe">房源共享</a>
                                <a id="houseImport" href="/WebRelease/massProperty/houseImport" target="main-iframe">房源导入</a> -->
                            </dd>
                        </dl>
                        <dl class="menu menu_release">
                            <dt class="menu_title" id="release-guide">
							<i class="icon_menu release_logo"></i>
							<span class="main-font">云发布</span>
						</dt>
                            <dd class="menu_item">
                                <div id="release-item-guide">
                                    <a id="houseManagerSell" class="release-item-guide" href="/WebRelease/massProperty/saleMassList?postType=0&isType=0" target="main-iframe">出售群发</a>
                                    <a id="houseManagerRent" class="release-item-guide" href="/WebRelease/massProperty/saleMassList?postType=1&isType=0" target="main-iframe">出租群发</a>
                                </div>
                                <!-- <a id="appointment" href="/WebRelease/cloudRelease/appointmentView" target="main-iframe">预约管理</a>
                                <a id="releaseLog" href="/WebRelease/cloudRelease/releaseLog" target="main-iframe">发布日志</a> -->
                            </dd>
                        </dl>
                        <!-- <dl class="menu">
                            <dt class="menu_title">
								<i class="icon_menu refresh_logo"></i>
								<span class="main-font">云刷新</span>
							</dt>
                            <dd class="menu_item">
                                <a id="refresh" href="/WebRelease/massRefresh/refreshLogList?tabIndex=0" target="main-iframe">刷新设置</a>
                                <a id="refreshStat" href="/WebRelease/massRefresh/refreshLogList?tabIndex=1" target="main-iframe">刷新统计</a>
                                <a id="refreshLog" href="/WebRelease/massRefresh/refreshLogList?tabIndex=2" target="main-iframe">刷新日志</a>
                                <a id="refreshLog" href="/WebRelease/massRefresh/refreshLogList?tabIndex=3" target="main-iframe">操作日志</a>
                            </dd>
                        </dl> -->
                        <dl class="menu">
                            <dt class="menu_title" id="snatch-guide">
								<i class="icon_menu cell_logo"></i>
								<span class="main-font xms-relative">网络精耕</span>
							</dt>
                            <dd class="menu_item"><!-- 
                                <a id="snatch" href="/WebRelease/snatch/houseSecretary" target="main-iframe">房源秘书</a>
                                <a id="reReleasePlan" href="/WebRelease/snatch/reReleasePlan" class="xms-relative" target="main-iframe">预约重发
									<span class="menunew-bubble menunew-bubble-left">新</span>
								</a> -->
                                <a id="houseCollect4" href="/WebRelease/snatch/cellExact" target="main-iframe">小区透视</a>
                               <!--  <a id="cellSign" href="/WebRelease/snatch/cellSign" class="xms-relative" target="main-iframe">小区签到
								<span class="menunew-bubble menunew-bubble-left">新</span>
								</a>
                                <a id="houseMove" href="/WebRelease/snatch/houseMove" class="xms-relative"  target="main-iframe">房源搬家
									<span class="menunew-bubble menunew-bubble-left">新</span>
								</a> -->
                            </dd>
                        </dl>
                        <dl class="menu">
                            <dt class="menu_title" id="houseCollect_guide">
							<i class="icon_menu houseCollect_logo"></i>
							<span class="main-font">云采集</span>
						</dt>
                            <dd class="menu_item">
                                <div id="houseCollect_item_guide">
                                    <a id="houseCollect0" href="/WebRelease/massPerson/list?postType=0&cust=0" target="main-iframe">个人出售</a>
                                    <a id="houseCollect1" href="/WebRelease/massPerson/list?postType=1&cust=0" target="main-iframe">个人出租</a>
                                    <a id="houseCollect2" href="/WebRelease/massPerson/list?postType=2&cust=0" target="main-iframe">个人求购</a>
                                    <a id="houseCollect3" href="/WebRelease/massPerson/list?postType=3&cust=0" target="main-iframe">个人求租</a>
                                </div><!-- 
                                <a id="subscribe" href="houseCollect/755/subScribe/subscribeView.html" target="main-iframe">微信提醒</a> -->
                                <a id="storelistView" href="/WebRelease/massPerson/list?postType=0&cust=1" target="main-iframe">房源收藏</a>
                                <a id="forbidphoneView" href="/WebRelease/blacklist/blacklist" target="main-iframe">黑名单设置</a>
                            </dd>
                        </dl>
                        <dl class="menu">
                            <dt id="personManagerDT" class="menu_title">
							<i class="icon_menu personConfig_logo"></i>
							<span class="main-font">个人管理</span>
						</dt>
                            <dd class="menu_item">
                                <a id="webManager" href="/WebRelease/siteManage/siteManage" target="main-iframe">站点管理</a><!-- 
                                <a id="freeRegweb" href="snatch-web/freeReg/freeRegweb.html" target="main-iframe">免费注册</a> -->
                                <a id="houseDescManager" href="/WebRelease/massEmpSaleTemplate/describe" target="main-iframe">描述管理</a>
                                <a id="myImgManager" href="/WebRelease/image/imageManage?type=2" target="main-iframe">图库管理</a><!-- 
                                <a id="videoLib" href="release-web/videoLib/videoLib.html" target="main-iframe">视频管理</a> -->
                                <a id="watermarkConfig" href="/WebRelease/watermark/watermark" target="main-iframe">水印管理</a><!-- 
                                <a id="personSet" href="release-web/personConfig/getPersonConfig.html" target="main-iframe">个性设置</a> -->
                                <a id="updPasswd" href="/WebRelease/employee/personInfo" target="main-iframe">个人资料</a>
                                <a href="/WebRelease/employee/loginout" >安全退出</a>
                            </dd>
                        </dl>
                    </div>
                </div>
            </div>
            <div class="col_main" id="col_main_function">
                <iframe id="main-iframe" name="main-iframe" width="100%" height="100%" scrolling="auto" border="0" frameborder="no" src="getMainRight.html" onload="loadingHide()"></iframe>
                <!-- 正在加载中 -->
                <div class="loading-bg"></div>
                <div class="loading" style="display:none">
                    <img width="100" height="100" src="<%=path%>/Static/Picture/loading.gif"></img>
                </div>
            </div>
            <!-- QQ联系 -->
            <div id="lr-qqcode" class="xmsRight-pop" style="display:none">
                <div class="qqcode">
                    <div class="qqcode-header">售后服务</div>
                    <ul class="qqcode-content">
                        <li><a target="_blank" href="http://wpa.qq.com/msgrd?V=3&uin=2880586952&Site=QQ客服&Menu=yes"><span class="qqcode-content-img" title="点击咨询"></span><span class="qqcode-content-font">客服苏苏</span></a></li>
                        <li><a target="_blank" href="http://wpa.qq.com/msgrd?V=3&uin=2880586950&Site=QQ客服&Menu=yes"><span class="qqcode-content-img" title="点击咨询"></span><span class="qqcode-content-font">客服妮妮</span></a></li>
                    </ul>
                </div>
                <div class="qqcode-hide">关闭</div>
            </div>
            <!-- QQ联系 -->
            <div id="lr-qqcode2" class="xmsRight-pop" style="display:none">
                <div class="qqcode">
                    <div class="qqcode-header">售前咨询</div>
                    <ul class="qqcode-content">
                        <li><a target="_blank" href="http://wpa.qq.com/msgrd?V=3&uin=2881380918&Site=QQ客服&Menu=yes"><span class="qqcode-content-img" title="点击咨询"></span><span class="qqcode-content-font">售前敏敏</span></a></li>
                        <li><a target="_blank" href="http://wpa.qq.com/msgrd?V=3&uin=2880586987&Site=QQ客服&Menu=yes"><span class="qqcode-content-img" title="点击咨询"></span><span class="qqcode-content-font">售前雪娇</span></a></li>
                        <li><a target="_blank" href="http://wpa.qq.com/msgrd?V=3&uin=2881380919&Site=QQ客服&Menu=yes"><span class="qqcode-content-img" title="点击咨询"></span><span class="qqcode-content-font">售前丽媛</span></a></li>
                        <li><a target="_blank" href="http://wpa.qq.com/msgrd?V=3&uin=2880586970&Site=QQ客服&Menu=yes"><span class="qqcode-content-img" title="点击咨询"></span><span class="qqcode-content-font">售前琳琳</span></a></li>
                        <li><a target="_blank" href="http://wpa.qq.com/msgrd?V=3&uin=2881380925&Site=QQ客服&Menu=yes"><span class="qqcode-content-img" title="点击咨询"></span><span class="qqcode-content-font">售前婕婷</span></a></li>
                        <li><a target="_blank" href="http://wpa.qq.com/msgrd?V=3&uin=2880586969&Site=QQ客服&Menu=yes"><span class="qqcode-content-img" title="点击咨询"></span><span class="qqcode-content-font">售前菜菜</span></a></li>
                        <li><a target="_blank" href="http://wpa.qq.com/msgrd?V=3&uin=2880586986&Site=QQ客服&Menu=yes"><span class="qqcode-content-img" title="点击咨询"></span><span class="qqcode-content-font">售前婷婷</span></a></li>
                        <li><a target="_blank" href="http://wpa.qq.com/msgrd?V=3&uin=2881380924&Site=QQ客服&Menu=yes"><span class="qqcode-content-img" title="点击咨询"></span><span class="qqcode-content-font">售前莉莉</span></a></li>
                    </ul>
                </div>
                <div class="qqcode-hide">关闭</div>
            </div>

            <!-- 二微码 -->
            <div id="lr-qrcode" class="lr-pop xmsRight-pop">
                <div class="lr-close">关闭</div>
                <div class="lr-qcimg"></div>
            </div>
            <!--快捷工具-->
            <div id="lr-minTools" class="xmsRight-pop" style="display:none">
                <div class="tell-pop">
                    <div class="qqcode-header">快捷工具</div>
                    <ul>
                        <li><a href="http://m.db.house.qq.com/index.php?mod=calculator" target="_blank">房贷计算器</a></li>
                        <li><a href="http://static.fcxms.com/plugins/flash/drawtool.swf" target="_blank">在线绘图</a></li>
                    </ul>
                </div>
                <div class="minTools-close">关闭</div>
            </div>
            <!--APP下载-->
            <div id="lr-app" class="xmsRight-pop" style="display:none">
                <div class="tell-pop">
                    <div class="qqcode-header">APP下载</div>
                    <ul>
                        <li><img src='<%=path%>/Static/Picture/qr-android.jpg' width='140' /></li>
                        <li><img src='<%=path%>/Static/Picture/qr-ios.jpg' width='140' /></li>
                    </ul>
                </div>
                <div class="minTools-close app-close">关闭</div>
            </div>
            <!-- 返回顶部 -->
            <div id="lrToolRb" class="lr-tool-rb">
                <a class="lr-gotop" title="返回顶部"><span class="lricon"></span></a>

                <a class="lr-tccode" title="功能指引" href="javascript:getNewTip('/infoServer/guideIndexEnter.do')"><span class="tcicon"></span><br/>新手指引</a>

                <a class="lr-app" title="APP下载" href="javascript:toggleDiv('lr-app')"><img class="qqicon" width="30" height="30" src="<%=path%>/Static/Picture/App.png" /><br/>APP下载</a>
                <a class="lr-qrcode" title="微信客服" href="javascript:toggleDiv('lr-qrcode')"><img class="qqicon" width="24" height="24" src="<%=path%>/Static/Picture/wxkf.png"></img><br/>微信客服</a>

                <a class="lr-minTools" title="快捷工具" href="javascript:toggleDiv('lr-minTools')"><img class="qqicon" width="24" height="26" src="<%=path%>/Static/Picture/minTools.png"></img><br/>快捷工具</a>
                <a class="lr-qqcode" title="售后服务" href="javascript:toggleDiv('lr-qqcode')"><img class="qqicon" width="24" height="26" src="<%=path%>/Static/Picture/qq.png"></img><br/>售后服务</a>
                <a class="lr-qqcode" title="售前咨询" href="javascript:toggleDiv('lr-qqcode2')"><img class="qqicon" width="24" height="26" src="<%=path%>/Static/Picture/qq.png"></img><br/>售前咨询</a>
                <div class="del-tip">
                    <span class="lr-close">关闭</span>
                </div>
            </div>
            <div id="guide_msg" class="guide_msg">向导完成,点我再次查看</div>

            <!-- 红包提示 -->
            <div>
                <div id="couponTip_bg" class="newIndex-bg"></div>
                <div id="couponTip" style="display: none; position: absolute;z-index: 999999909; top: 140px; border-radius: 10px;">
                    <div style="height:334px; width: 266px;position:relative;background: url(<%=path%>/Static/Image/coupon-tip.png) no-repeat center;">
                        <a id="couponClose" style="position: absolute; cursor: pointer;width: 26px; height: 26px; left: 240px; top: 0px;"></a>
                        <div id="couponImg" style="position: absolute; cursor: pointer;width: 240px; height: 315px; left: 0px; top: 20px;">
                            <div id="couponText" style="text-align: center; margin-top: 160px; color: yellow; font-size: 16px; font-family: Microsoft YaHei;"></div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 公告通知 -->
            <div class="xms_tip_bg"></div>
            <div id="noticeTip" class="xms_tip" style="display:none"></div>

            <!-- 登录提示 -->
            <div id="guideIndex_bg">
                <div class="newIndex-bg"></div>
                <div id="newIndex" style="display:none"></div>
            </div>

            <!-- 免费会员续费提醒 -->

            <!-- 房源提醒 -->
            <div class="newHouse">
                <a href="javascript:colseModifyTip()">
                    <img width="20" height="20" src="<%=path%>/Static/Picture/houseTip.gif"></img>
                    <span id="newHouse-content">有新房源,请关注</span>
                </a>
            </div>

            <!-- begin: 公众号关注提醒 -->
            <div id="wxQrcodeBindLayer" style="display:none">
                <div class="xms_tip_bg" style="display:block"></div>
                <div class="main-box">
                    <div class="wx_qrcode-content">
                        <div class="wx_qrcode"></div>
                        <img src="<%=path%>/Static/Picture/wx-logo.png" width="48" height="48"></img>
                    </div>
                    <div class="text">
                        <div class="p1">扫一扫有惊喜!</div>
                        <div class="p2">第一时间获取最新个人房源信息，<br/>最新优惠活动通知!</div>
                    </div>
                    <div class="close"></div>
                </div>
            </div>
            <!-- end: 公众号关注提醒 -->


        </div>
        <div style="display:none">
            <script type="text/javascript">
                var isLogin = '0';
                // 			var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254600221'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/stat.php%3Fid%3D1254600221%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));
            </script>
        </div>
    </div>
	<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/constants_3318a849899f02bb3b56fc5c5f5b34eb.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/common_b016234725ebd1612a8f62bf4261c244.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/checkInput.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/intro_e523b61a7b0f0aa4d1bcc380363a6bba.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/introConfig_common_9d629b8dbe66c1053e0daeaaf81db2f4.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/jquery.qrcode.min.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/jquery.timers-1.2.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/jquery.slides.min.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/jquery.fly.min.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/jquery.poshytip.min.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/jquery.mCustomScrollbar.concat.min.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/login_c11c19adcd1218023d8d057a326aa49b.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/layer/layer.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/placeholder.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/main_f5b23574dec3a78c8469652f9f2e9dca.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/network.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/newIndex_0360365e521961c84864ee83de7fc7d8.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/requestAnimationFrame.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/selectCity_c951504f69be1b29311e9426b63db5bf.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/totop_395a537872883e734de4a75fb7fcae6d.js' type='text/javascript'></script>
	<script src='<%=path%>/Static/Js/xmsnotice_92e679a9fdab15a67decb41efc752c48.js' type='text/javascript'></script>
    <script>
        if ('' != null && '' != '') {
            $('.menu_open').removeClass('menu_open');
            $('#').parents('dl').addClass('menu_open');
            $('#').addClass('selected');
        }
    </script>
</body>
</html>
