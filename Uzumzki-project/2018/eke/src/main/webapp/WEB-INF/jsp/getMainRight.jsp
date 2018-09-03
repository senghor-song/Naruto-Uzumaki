<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>房产易推房登陆页-房产中介公司营销管理的软件</title>
	<%@include file="common/head.jsp"%>
  </head>
<body >
<input type="hidden" id="accountStatUrl" value="//s.release.xms.demo.com/release-web/ajax/infoServer/accountStat.do"></input>
    <input type="hidden" id="siteManagerUrl" value="//xms.demo.com/site/siteManage.do"></input>
    <input type="hidden" id="erpUrl" value="//erp.demo.com"></input>
    <input id="codeUrl" value="//weixin.demo.com/mweb/ajax/wxweb/getQrcode.do" type="hidden"></input>
    <input id="userId" value="2073512" type="hidden"></input>
    <div class="saleHouse" id="saleHouse">
        <div class="saleHouse-main clear">
            <div class="indexRight-main float-l">
                <div id="slide-top" style="padding-bottom: 8px;"></div>
                <ul class="indexRight-common">
                    <li>
                        <a href="javascript:redirect('importSell')" class=""><img width="70" height="60" src="<%=path%>/Static/Picture/saleImport.png"></img>
                        </a>
                        <a href="javascript:redirect('importSell')" class="mainRight-font">出售录入</a>
                    </li>
                    <li>
                        <a href="javascript:redirect('importRent')" class=""><img width="70" height="60" src="<%=path%>/Static/Picture/rentImport.png"></img>
                        </a>
                        <a href="javascript:redirect('importRent')" class="mainRight-font">出租录入</a>
                    </li>
                    <li>
                        <a href="javascript:redirect('houseManagerSell')" class=""><img width="70" height="60" src="<%=path%>/Static/Picture/houseSale.png"></img>
                        </a>
                        <a href="javascript:redirect('houseManagerSell')" class="mainRight-font">出售群发</a>
                    </li>
                    <li>
                        <a href="javascript:redirect('houseManagerRent')" class=""><img width="70" height="60" src="<%=path%>/Static/Picture/houseRent.png"></img>
                        </a>
                        <a href="javascript:redirect('houseManagerRent')" class="mainRight-font">出租群发</a>
                    </li>
                    <li>
                        <a href="javascript:redirect('refresh')" class=""><img width="70" height="60" src="<%=path%>/Static/Picture/houseRefresh.png"></img>
                        </a>
                        <a href="javascript:redirect('refresh')" class="mainRight-font">云刷新</a>
                    </li>
                    <li>
                        <a href="javascript:redirect('houseCollect0')" class=""><img width="70" height="60" src="<%=path%>/Static/Picture/housePerson.png"></img>
                        </a>
                        <a href="javascript:redirect('houseCollect0')" class="mainRight-font">云采集</a>
                    </li>
                    <li>
                        <a href="javascript:redirect('webManager')" class=""><img width="70" height="60" src="<%=path%>/Static/Picture/houseManager.png"></img>
                        </a>
                        <a href="javascript:redirect('webManager')" class="mainRight-font">站点管理</a>
                    </li>
                </ul>

                <div class="indexRight-list clear">
                    <div class="indexRight-list-header clear">
                        <div class="list-header-left"><em class="indexRight-notice-img"></em>
                        <span class="float-l">最新公告</span><span class="mainRight-noticeTime"></span></div>
                    </div>
                    <div class="indexRight-list-content clear" style="padding-bottom:8px">
                        <div class="indexRight-noticeTitle">
                            <a href="/WebRelease/massNotice/details?noticeId=${noticeList[0].id }">${noticeList[0].title }</a>
                        </div>
                        <div class="indexRight-noticeContent">${noticeList[0].content }</div>
                    </div>
                </div>

                <!-- 站内公告 -->
                <div class="indexRight-list clear">
                    <div class="indexRight-list-header clear">
                        <div class="list-header-left float-l"><em class="indexRight-notice-img">
                        </em><span class="float-l">站内公告</span></div>
                        <div class="list-header-right float-r"><a href="/WebRelease/massNotice/noticeList">查看更多</a></div>
                    </div>
                    <ul class="indexRight-list-content clear">
						<c:forEach var="list" items="${noticeList}">
							<li>
								<span class="float-l">【${list.type == 0 ? '系统公告' : list.type == 1 ? '网站公告' : list.type == 2 ? '客户公告' : list.type == 3 ? '公司公告' : '站内信息'} 】
								<fmt:formatDate value="${list.modifytime }" pattern="yy-MM-dd HH:mm"/></span>
								<a href="/WebRelease/massNotice/details?noticeId=${list.id }" class="ml8 float-l tableOverflow tbable350" id="${list.id }">
								${list.title }
								</a>
							</li>	
						</c:forEach>						
					</ul>
                </div>

                <!-- 使用简报 -->
                <div class="indexRight-list clear">
                    <div class="indexRight-list-header clear">
                        <div class="list-header-left"><em class="indexRight-report-img"></em><span class="float-l">使用简报</span></div>
                    </div>
                    <table class="indexRight-list-content clear" id="accountStatTable">
                        <tr>
                            <td>录入房源</td>
                            <td>今日手动录入</td>
                            <td>今日非手动录入</td>
                            <td>今日发布</td>
                            <td>绑定网站个数</td>
                            <td>异常账号个数</td>
                        </tr>
                        <tr>
                            <td>${massPropertyDto.propertySum1 + massPropertyDto.propertySum2}</td>
                            <td>${massPropertyDto.propertyDaySumManual1 + massPropertyDto.propertyDaySumManual2}</td>
                            <td>${massPropertyDto.propertyDaySumNoManual1 + massPropertyDto.propertyDaySumNoManual2}</td>
                            <td>${massPropertyDto.propertyDaySum }</td>
                            <td>${massPropertyDto.bindWebSum }</td>
                            <td>${massPropertyDto.exceptionAccount }</td>
                        </tr>
                    </table>
                </div>


                <div id="slide-bottom" style="padding-bottom: 8px;"></div>
            </div>
            <div class="indexTaotan-list float-r">
                <div class="indexRight-list-header clear">
                    <div class="list-header-left"><em class="indexRight-taocan-img"></em><span class="float-l">套餐情况</span></div>
                </div>
                <ul class="indexRight-taocan-content">
                    <li class="taocan"><span>会员级别:</span>&nbsp; ${sessionScope.loginuser.masstype}
                    </li>
                    <li class="taocan"><span>发布配额:</span>&nbsp; ${sessionScope.loginuser.massquota}
                    </li>
                    <li class="taocan"><span>最大库存:</span>&nbsp; ${sessionScope.loginuser.massstock}
                    </li>
                    <li class="taocan"><span>剩余天数:</span>&nbsp;
                    <b class="massvalidity">${sessionScope.loginuser.remainingDays}</b>
                    </li>
                    <li class="taocan" style="margin:2px 7px 7px 7px;">
                        <a class="payment_now" href="/WebRelease/pay/payRenew" target="main-iframe" >我要续费</a>
                    </li>
                    <li class="taocan_1">
                        <a class="font-right" href="/WebRelease/pay/payOrder">流水查询</a>
                        <a class="font-right" onclick="window.open('/WebRelease/index/leaveWord')">产品评价</a>
                    </li>
                    <li style="height:8px;"></li>
                </ul>
            </div>
            <div class="indexTaotan-kefu float-r">
                <div class="indexRight-list-header clear">
                    <div class="list-header-left"><em class="indexRight-taocan-contact"></em><span class="float-l">联系微信客服</span></div>
                </div>
                <ul class="indexRight-taocan-content" id="taocan-ul">
                    <li>
                        <div class="tantao-img">
                            <div class="xms-relative">
                                <div class="tantao-code"><img src="<%=path%>/Static/Picture/xms_code.png" width="160" height="160"></img>
                                </div>
                                <img src="<%=path%>/Static/Picture/wx-logo.png" width="40" height="40" class="wx-logo"></img>
                            </div>
                        </div>
                    </li>
                    <li class="taocan center" style="font-size:16px">扫码反馈问题</li>
                    <li class="taocan">
                        <span>客服热线:</span> 400-999-demo
                    </li>
                    <li class="taocan">
                        <span>工作时间:</span> 08:30-12:00/14:00-18:00
                    </li>
                    <li class="taocan-qqContent float-l mb4">
                        <ul class="tancan-qq-img">
                            <li><a target="_blank" href="http://wpa.qq.com/msgrd?V=3&uin=2880586952&Site=QQ客服&Menu=yes" class="tancan-qq-log"><span class="tancan-qq-font" title="点击咨询">客服苏苏</span></a></li>
                            <li><a target="_blank" href="http://wpa.qq.com/msgrd?V=3&uin=2880586950&Site=QQ客服&Menu=yes" class="tancan-qq-log"><span class="tancan-qq-font" title="点击咨询">客服妮妮</span></a></li>
                        </ul>
                    </li>
                    <li class="clear"></li>
                </ul>
            </div>
        </div>
    </div>
    <script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/jquery.timers-1.2.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/jquery.slides.min.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/jquery.qrcode.min.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/common_b016234725ebd1612a8f62bf4261c244.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/mainRight_7340a3c2d59baa9f4f47f47033668e27.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js?skin=green' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/jquery.mCustomScrollbar.concat.min.js' type='text/javascript'></script>
    <script>
         var orgId = '755012005';
         //将时间发布时长中的时间取出计算天数后重新赋值
         /* $(function(){
             //格式化创建时间
             var cellValue =$(".massvalidity").html();
             if(cellValue == ""){
            	 $(".massvalidity").html("0天");
             }else{
            	 var date1=new Date(cellValue); //开始时间 
            	 var date2=new Date(); //结束时间 
            	 var date3=date1.getTime()-date2.getTime() //时间差的毫秒数 

            	 //计算出相差天数 
            	 var days=Math.floor(date3/(24*3600*1000)) 
            	 $(".massvalidity").html(days+"天");
             }
         }); */
    </script>
</body>
</html>