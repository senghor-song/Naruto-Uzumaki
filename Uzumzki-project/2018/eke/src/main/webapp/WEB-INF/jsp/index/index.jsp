<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<head>
<title>房产易推房登陆页-房产中介公司营销管理的软件</title>
<link href="<%=path%>/Static/Css/common.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/Static/Css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/Static/Css/common.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/Static/Css/index_content.css" rel="stylesheet" type="text/css">
<script src="<%=path%>/Static/Js/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/Static/Js/respond.js"></script>
<script src="<%=path%>/Static/Js/bootstrap.js"></script>
<link href="<%=path%>/Static/Css/index.css" rel="stylesheet" type="text/css"></head>
</head>

<body>
	<%@include file="indexTop.jsp"%>
	<style>
#hdp .carousel-inner>.item {
	height: 424px;
	width: 100%;
	background-position: 50% 0%;
	background-repeat: no-repeat;
}

#hdp .carousel-inner>.item.hdp1 {
	height: 424px;
	width: 100%;
	background: url(<%=path%>/Static/Image/hdp1.jpg) no-repeat 50% 0%;
	background-position: 50% 0%;
	background-repeat: no-repeat;
}

#hdp .carousel-inner>.item.hdp2 {
	height: 424px;
	width: 100%;
	background: url(<%=path%>/Static/Image/hdp2.jpg) no-repeat 50% 0%;
}

#hdp .carousel-inner>.item.hdp3 {
	height: 424px;
	width: 100%;
	background: url(<%=path%>/Static/Image/hdp3.jpg) no-repeat 50% 0%;
}

#hdp .carousel-inner>.item.hdp4 {
	height: 424px;
	width: 100%;
	background: url(<%=path%>/Static/Image/hdp4.jpg) no-repeat 50% 0%;
}
</style>
	<div class="hdp">
		<div class=" body_1" style="width: 100%;">
			<div id="hdp" class="carousel slide" data-ride="carousel">
				<div style="position: relative;" class="body_1">
					<ol class="carousel-indicators ">
						<a data-target="#hdp" data-slide-to="0" class="active">最新会员活动</a>
						<a data-target="#hdp" data-slide-to="1">企业版介绍</a>
						<a data-target="#hdp" data-slide-to="2">个人版介绍</a>
						<a data-target="#hdp" data-slide-to="3">代理加盟政策</a>
					</ol>
				</div>
				<div class="carousel-inner">
					<div class="item active hdp1 ">
						<a href="registerPC.html"></a>
					</div>
					<div class="item  hdp2 ">
						<a href="qy.html#ipr_b"></a>
					</div>
					<div class="item hdp3 ">
						<a href="gr.html#ipr_b"></a>
					</div>
					<div class="item hdp4 ">
						<a href="Agent.html#a1"></a>
					</div>
				</div>
			</div>
			<div class="hdpfoot"></div>
		</div>
	</div>
	<script>
		// if($(window).width()<=1024){  //图片不一样
		// 	$("#hdp").addClass("jrx");	
		// }
	</script>
	<div class="ib_1">
		<div class="body_1">
			<div class="ib_1b oi1">
				<h4>经纪人应用</h4>
				<a href="gr.html#ipr_b" class="on4"></a>
				<div>
					<a href="gr.html#ipr_b">提高效率、提升效果</a>
				</div>
				<a href="gr.html#ipr_b">了解更多</a>
			</div>
			<div class="ib_1b oi2">
				<h4>网络运营管理</h4>
				<a class="on4" href="qy.html#ipr_b"></a>
				<div>
					<a href="qy.html#ipr_b">复制优秀、提升不足</a>
				</div>
				<a href="qy.html#ipr_b">了解更多</a>
			</div>
			<div class="ib_1b oi3">
				<h4>大数据应用</h4>
				<a class="on4" href="jg.html#ipr_b"></a>
				<div>
					<a href="jg.html#ipr_b">掌握数据、把握机会</a>
				</div>
				<a href="jg.html#ipr_b">了解更多</a>
			</div>
			<div class="ib_1b oi4">
				<h4>互联网平台定制</h4>
				<a class="on4" href="pingtaidingzhi.html#ipr_b"></a>
				<div>
					<a href="pingtaidingzhi.html#ipr_b">打造真实、提升品牌</a>
				</div>
				<a href="pingtaidingzhi.html#ipr_b">了解更多</a>
			</div>
		</div>
	</div>
	<div class="ib_2">
		<div class="body_1">
			<h3>他们是这样评价易推房的！</h3>
			<div id="hdp2" class="carousel slide" data-ride="carousel">
				<div class="carousel-inner" role="listbox" style="height: 336px;">
					<div class="item active">
						<div class="case1  ">
							<div>
								<a> <img src="<%=path%>/Static/Picture/552cd67a3656f.jpg"></a> <a>
									<h4 style="text-align: left;">
										阙雄旺 <span>福州/ 134*****502</span>
									</h4>
								</a>
								<div class="b">
									我们公司使用易推房的几年了，见证了易推房客户端到云端的成长，也见证了易推房企业版的诞生。易推房软件能帮我管理很多事情，查看店员每天发布量，刷新量......
									<!-- <a>[了解详情]</a>-->
								</div>
							</div>
						</div>
						<div class="case1 oo ">
							<div>
								<a> <img src="<%=path%>/Static/Picture/552dfcb1a8bb6.jpg"></a> <a>
									<h4 style="text-align: left;">
										辛波 <span>南京/ 182*****362</span>
									</h4>
								</a>
								<div class="b">
									小秘书很实用的，对于端口多有没有时间打理的经纪人来说非常好，可以定时刷新，定时发布，包括房源搬家这些功能，都为我们省了不少力气，希望小秘书越做越......
									<!-- <a>[了解详情]</a>-->
								</div>
							</div>
						</div>
						<div class="case1  ">
							<div>
								<a> <img src="<%=path%>/Static/Picture/552ce7fea25c3.jpg"></a> <a>
									<h4 style="text-align: left;">
										冯振兵 <span>福州/ 139*****684</span>
									</h4>
								</a>
								<div class="b">
									现在二手房行业竞争相当的激烈，从以前的简单的门店接单，到报纸上打广告，到后期网络端口的开通，网络端口开通一个不够要多个。网络端口开通越来越情况，......
									<!-- <a>[了解详情]</a>-->
								</div>
							</div>
						</div>
						<div class="case1 oo ">
							<div>
								<a> <img src="<%=path%>/Static/Picture/552ce233f17ab.jpg"></a> <a>
									<h4 style="text-align: left;">
										林陈聪 <span>厦门/ 135*****452</span>
									</h4>
								</a>
								<div class="b">
									使用易推房已经好多年，从最初的个人购买，到后期公司统一购买，见证了易推房的一次又一次的成长，现在的易推房云端无需要下载，不用担心公司网络慢、也不......
									<!-- <a>[了解详情]</a>-->
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="case1  ">
							<div>
								<a> <img src="<%=path%>/Static/Picture/552e16ed41ddc.jpg"></a> <a>
									<h4 style="text-align: left;">
										亢永乐 <span>杭州/ 180*****035</span>
									</h4>
								</a>
								<div class="b">
									小秘书模板精致，刷新时间可以按照自己想要的时间，还有就是方便房源管理，很棒！
									<!-- <a>[了解详情]</a>-->
								</div>
							</div>
						</div>
						<div class="case1 oo ">
							<div>
								<a> <img src="<%=path%>/Static/Picture/552dfed17b99e.jpg"></a> <a>
									<h4 style="text-align: left;">
										徐日升 <span>杭州/ 189*****548</span>
									</h4>
								</a>
								<div class="b">
									做经纪人也有2年时间了，接触过各类外网工具，最喜欢的还是小秘书，功能多，性价比高，而且我们提要求和问题也会帮我们解决，上个月我业绩突破新高，成为......
									<!-- <a>[了解详情]</a>-->
								</div>
							</div>
						</div>
						<div class="case1  ">
							<div>
								<a> <img src="<%=path%>/Static/Picture/552ce4d2c07fd.jpg"></a> <a>
									<h4 style="text-align: left;">
										李贵宝 <span>南京/ 152*****597</span>
									</h4>
								</a>
								<div class="b">
									我是通过同事介绍才使用的易推房，第一感觉是这款软件非常实用、简约、快捷！不用再一个个的去发不同账号的房源，还可以检测所发房子的网站排名，可以根据......
									<!-- <a>[了解详情]</a>-->
								</div>
							</div>
						</div>
						<div class="case1 oo ">
							<div>
								<a> <img src="<%=path%>/Static/Picture/552e175113d7e.jpg"></a> <a>
									<h4 style="text-align: left;">
										张韬 <span>杭州/ 158*****850</span>
									</h4>
								</a>
								<div class="b">
									我是小秘书的老客户了，见证了小秘书的每一次更新、成长，小秘书功能强大，而且服务周到，售后人员非常负责，有因必答，顶小秘书，哈哈
									<!-- <a>[了解详情]</a>-->
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="case1  ">
							<div>
								<a> <img src="<%=path%>/Static/Picture/552ceb3dc49a1.jpg"></a> <a>
									<h4 style="text-align: left;">
										刘忠英 <span>厦门/ 139*****097</span>
									</h4>
								</a>
								<div class="b">
									使用易推房软件已经挺长时间了，效果确实挺明显的，刚开始接触易推房软件到现在使用习惯离不开易推房软件，提高了我自己的工作效率，而且节约出来的时间足......
									<!-- <a>[了解详情]</a>-->
								</div>
							</div>
						</div>
						<div class="case1 oo ">
							<div>
								<a> <img src="<%=path%>/Static/Picture/552ce2f59e150.jpg"></a> <a>
									<h4 style="text-align: left;">
										黄玉节 <span>南京/ 159*****527</span>
									</h4>
								</a>
								<div class="b">
									公司统一购买易推房软件，试用了一下，感觉方便，省时间。真心蛮好的，房源发布便捷，提高工作效率。......
									<!-- <a>[了解详情]</a>-->
								</div>
							</div>
						</div>
						<div class="case1  ">
							<div>
								<a> <img src="<%=path%>/Static/Picture/554d7e2747adc.jpg"></a> <a>
									<h4 style="text-align: left;">
										王力 <span>南京/ 182*****133</span>
									</h4>
								</a>
								<div class="b">
									小秘书帮助我提升了工作效率，让我有更多的时间让我打电话、带客户看房，使我的业绩有显著的提升，是我们的好助手。
									<!-- <a>[了解详情]</a>-->
								</div>
							</div>
						</div>
						<div class="case1 oo ">
							<div>
								<a> <img src="<%=path%>/Static/Picture/55306be327f48.jpg"></a> <a>
									<h4 style="text-align: left;">
										王帅 <span>苏州/ 188*****026</span>
									</h4>
								</a>
								<div class="b">
									用了房产小秘书这么久，感觉非常好，录入房源、预约刷新、和采集房源都非常方便。省去了很大一部分时间，现在工作效率更高了，客户经理服务的也是非常好，......
									<!-- <a>[了解详情]</a>-->
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="case1  ">
							<div>
								<a> <img src="<%=path%>/Static/Picture/552e1ee45dc99.jpg"></a> <a>
									<h4 style="text-align: left;">
										刁婷 <span>杭州/ 158*****798</span>
									</h4>
								</a>
								<div class="b">
									使用小秘书虽然才短短一个月，感觉到了它的各种方便，各种好，以前没用的时候房源要一套一套录，一套一套刷，自从有了小秘书，工作量提高了，还省下了不少......
									<!-- <a>[了解详情]</a>-->
								</div>
							</div>
						</div>
						<div class="case1 oo ">
							<div>
								<a> <img src="<%=path%>/Static/Picture/552dcfc22f6c1.jpg"></a> <a>
									<h4 style="text-align: left;">
										曹久贵 <span>南京/ 187*****815</span>
									</h4>
								</a>
								<div class="b">
									自从用了易推房再也不要烦神了
									<!-- <a>[了解详情]</a>-->
								</div>
							</div>
						</div>
						<div class="case1  ">
							<div>
								<a> <img src="<%=path%>/Static/Picture/552e21369cfd7.jpg"></a> <a>
									<h4 style="text-align: left;">
										刘佳森 <span>苏州/ 153*****237</span>
									</h4>
								</a>
								<div class="b">
									小秘书确实挺好用的，继续使用。
									<!-- <a>[了解详情]</a>-->
								</div>
							</div>
						</div>
						<div class="case1 oo ">
							<div>
								<a> <img src="<%=path%>/Static/Picture/552dfc51d27cb.jpg"></a> <a>
									<h4 style="text-align: left;">
										江伟立 <span>福州/ 150*****479</span>
									</h4>
								</a>
								<div class="b">
									小秘书确实不错，用这个签了很多单子，希望小秘书越办越好，当然价格优惠点就更好了。
									<!-- <a>[了解详情]</a>-->
								</div>
							</div>
						</div>
					</div>
				</div>
				<a class="left  c1 " href="#hdp2" role="button" data-slide="prev"> <span style=""></span>
				</a> <a class="right c1" href="#hdp2" role="button" data-slide="next"> <span style=""></span>
				</a>
			</div>
			<a class="button1" href="Message.html">了解更多>></a>
		</div>
	</div>
	<div class="ib_3" style="height: 600px;">
		<div class="body_1">
			<ul class="nav navbar-nav" id="myTab">
				<li class="active"><a href="#messages">成交客户</a></li>
				<li><a href="#home">新闻中心</a></li>
				<li><a href="#profile">经验心得</a></li>
			</ul>
			<div style="clear: both;"></div>
			<div class="tab-content">
				<div class="tab-pane " id="home">
					<div style="height: 345px;" class="b">
						<div class="case2">
							<div>
								<a href="News-7251.html"> <img src="<%=path%>/Static/Picture/5ad69e0e64f42.png"></a> <a href="News-7251.html">
									<h4>关于2018年五一劳动节放假安......</h4>
								</a>
								<div class="b">
									<a href="News-7251.html">2018年“五一”劳动节从4月29日至5月1日放假调休共三天，4月28日正常上班...... <br> <span>来源：房产易推房软件官网 &nbsp; 2018-04-18</span></a>
								</div>
							</div>
						</div>
						<div class="case2">
							<div>
								<a href="News-7182.html"> <img src="<%=path%>/Static/Picture/5abb30924150c.jpg"></a> <a href="News-7182.html">
									<h4>关于2018年清明节放假安排通......</h4>
								</a>
								<div class="b">
									<a href="News-7182.html">2018年清明节从4月5日至4月7日放假调休共三天，4月8日正常上班；...... <br> <span>来源：房产易推房软件官网 &nbsp; 2018-03-28</span></a>
								</div>
							</div>
						</div>
						<div class="case2">
							<div>
								<a href="News-7051.html"> <img src="<%=path%>/Static/Picture/5a814b163c508.png"></a> <a href="News-7051.html">
									<h4>致用户：2018年春节期间房产......</h4>
								</a>
								<div class="b">
									<a href="News-7051.html">我们全体人员年后上班时间是2月22日，农历大年初七。届时我们将一如既往的为您提供...... <br> <span>来源：房产易推房软件官网 &nbsp; 2018-02-12</span></a>
								</div>
							</div>
						</div>
						<div class="case2">
							<div>
								<a href="News-6965.html"> <img src="<%=path%>/Static/Picture/5a5d5cc339161.png"></a> <a href="News-6965.html">
									<h4>关于2018年春节放假安排通知</h4>
								</a>
								<div class="b">
									<a href="News-6965.html">公司定于2月14日至2月21日放假调休共计8天，2月22日(初七）正常上班...... <br> <span>来源：房产易推房软件官网 &nbsp; 2018-01-16</span></a>
								</div>
							</div>
						</div>
					</div>
					<div style="clear: both;"></div>
					<a class="button1" href="Newslist-3.html">了解更多>></a>
				</div>
				<div class="tab-pane" id="profile">
					<div style="height: 345px;" class="b">
						<div class="case2">
							<div>
								<a href="News-7332.html"> <img src="<%=path%>/Static/Picture/5afba462352f2.jpg"></a> <a href="News-7332.html">
									<h4>楼市调控多达120次，向三......</h4>
								</a>
								<div class="b">
									<a href="News-7332.html">楼市调控的鼓点又密了起来，年内各地调控政策已多达120次 调控重点逐渐向三四...... <br> <span>来源：房产易推房软件官网 &nbsp; 2018-05-16</span></a>
								</div>
							</div>
						</div>
						<div class="case2">
							<div>
								<a href="News-7331.html"> <img src="<%=path%>/Static/Picture/5afba35234b15.jpg"></a> <a href="News-7331.html">
									<h4>统计局前四月商品房销售额增......</h4>
								</a>
								<div class="b">
									<a href="News-7331.html">房地产开发投资同比增10.3%，比1-3月份回落0.1个百分点；商品房销售额...... <br> <span>来源：房产易推房软件官网 &nbsp; 2018-05-16</span></a>
								</div>
							</div>
						</div>
						<div class="case2">
							<div>
								<a href="News-7328.html"> <img src="<%=path%>/Static/Picture/5afa3a9326a85.jpg"></a> <a href="News-7328.html">
									<h4>社科院个人房贷利率仍有上升......</h4>
								</a>
								<div class="b">
									<a href="News-7328.html">个人住房贷款在市场化资金成本上升的推动下，贷款利率将继续上行。...... <br> <span>来源：房产易推房软件官网 &nbsp; 2018-05-15</span></a>
								</div>
							</div>
						</div>
						<div class="case2">
							<div>
								<a href="News-7327.html"> <img src="<%=path%>/Static/Picture/5afa39e589d16.jpg"></a> <a href="News-7327.html">
									<h4>67.4%受访者将公积金用......</h4>
								</a>
								<div class="b">
									<a href="News-7327.html">将公积金用于贷款买房（67.4%）的情况最为普遍，接下来是用于购房首付（56...... <br> <span>来源：房产易推房软件官网 &nbsp; 2018-05-15</span></a>
								</div>
							</div>
						</div>
					</div>
					<div style="clear: both;"></div>
					<a class="button1" href="Member-8.html">了解更多>></a>
				</div>
				<div class="tab-pane active" id="messages">
					<div class="b">
						<div class="b_l">
							<div style="padding: 10px;">
								<img class='js-xms-lazy' data-original="<%=path%>/Static/Picture/l1.jpg?1"> 
								<img class='js-xms-lazy' data-original="<%=path%>/Static/Picture/l2.jpg?1">
								<img class='js-xms-lazy' data-original="<%=path%>/Static/Picture/l3.jpg?1"> 
								<img class='js-xms-lazy' data-original="<%=path%>/Static/Picture/l4.jpg?1">
								<img class='js-xms-lazy' data-original="<%=path%>/Static/Picture/l5.jpg?1"> 
								<img class='js-xms-lazy' data-original="<%=path%>/Static/Picture/l6.jpg?1">
								<img class='js-xms-lazy' data-original="<%=path%>/Static/Picture/l7.jpg?1"> 
								<img class='js-xms-lazy' data-original="<%=path%>/Static/Picture/l8.jpg?1">
								<img class='js-xms-lazy' data-original="<%=path%>/Static/Picture/l9.jpg?1"> 
								<img class='js-xms-lazy' data-original="<%=path%>/Static/Picture/l10.jpg?1">
								<img class='js-xms-lazy' data-original="<%=path%>/Static/Picture/l11.jpg?1"> 
								<img class='js-xms-lazy' data-original="<%=path%>/Static/Picture/l12.jpg?1">
							</div>
						</div>
						<div class="b_r">
							<div id=demo style="overflow: hidden; background-color: #ffffff; height: 300px; width: 354px; margin-top: 20px; color: #ffffff">
								<div id=demo1>
									<ul>
										<li><a>厦门高鹏房地产营销策划有限公司</a></li>
										<li><a>北京爱屋吉屋房地产经纪有限公司</a></li>
										<li><a>厦门市置强房产营销策划代理有限公司</a></li>
										<li><a>泛城置地(北京)房地产经纪有限公司</a></li>
										<li><a>北京链家房地产经纪有限公司</a></li>
										<li><a>湖南中环地产顾问有限公司</a></li>
										<li><a>常州上城房地产服务有限公司</a></li>
										<li><a>江苏鑫洋置业顾问有限公司</a></li>
										<li><a>成都佳泰房产有限公司</a></li>
										<li><a>北京邦家房地产经纪有限公司</a></li>
										<li><a>天津金麦田房地产经纪有限公司</a></li>
										<li><a>Q房网</a></li>
										<li><a>深圳市世华房地产投资顾问有限公司</a></li>
										<li><a>厦门骊特房地产代理有限公司</a></li>
										<li><a>珠海市正顺房地产代理有限公司</a></li>
										<li><a>福州麦田房产经纪有限公司</a></li>
										<li><a>北京恒远嘉业房地产开发有限公司</a></li>
										<li><a>福州朝阳房产代理有限公司</a></li>
										<li><a>厦门麦田房产代理有限公司</a></li>
										<li><a>成都满堂红置业有限公司</a></li>
										<li><a>福州丹厦房产营销策划有限公司</a></li>
										<li><a>北京我爱我家房地产经纪有限公司</a></li>
										<li><a>福州骊特房产代理有限公司</a></li>
										<li><a>杭州21世纪不动产有限公司</a></li>
										<li><a>北京中联置家房地产经纪有限公司</a></li>
										<li><a>杭州盛世管家房地产经纪有限公司</a></li>
										<li><a>北京兴达恒远房地产经纪有限公司</a></li>
										<li><a>上海我爱我家</a></li>
										<li><a>杭州链家房地产经纪有限公司</a></li>
										<li><a>豪世华邦房地产代理有限公司</a></li>
										<li><a>合肥辉达房产公司</a></li>
										<li><a>合肥市蓝海不动产营销有限公司</a></li>
										<li><a>南昌中环地产顾问有限公司</a></li>
										<li><a>成都伊诚地产有限公司</a></li>
										<li><a>南昌市江投置业顾问有限公司</a></li>
										<li><a>苏州鼎丰房地产经纪有限公司</a></li>
										<li><a>北京优巢房地产经纪有限公司</a></li>
										<li><a>南昌满堂红房产</a></li>
										<li><a>厦门金海岸房产服务有限公司</a></li>
										<li><a>济南21世纪不动产有限公司</a></li>
										<li><a>厦门海希房地产营销策划有限公司</a></li>
										<li><a>济南海拓房产经纪有限公司</a></li>
										<li><a>济南万兴房地产有限公司</a></li>
										<li><a>九江家天下房产有限公司</a></li>
										<li><a>柳州市成强房地产中介有限公司</a></li>
										<li><a>南京满堂红置业有限公司</a></li>
										<li><a>福州21世纪不动产有限公司</a></li>
										<li><a>南京中广置业集团公司</a></li>
										<li><a>泉州南新房地产公司</a></li>
										<li><a>苏州我爱我家</a></li>
										<li><a>上海21世纪不动产有限公司</a></li>
										<li><a>北京麦田房产经纪有限公司</a></li>
										<li><a>上海爱屋吉屋房地产经纪有限公司</a></li>
										<li><a>深圳家家顺地产</a></li>
										<li><a>深圳满堂红置业有限公司</a></li>
										<li><a>中原地产代理（深圳）有限公司</a></li>
										<li><a>北京住总新远房地产经纪有限责任公司</a></li>
										<li><a>石家庄国大房产中介</a></li>
										<li><a>福州家天下房产代理有限公司</a></li>
										<li><a>石家庄21世纪不动产</a></li>
										<li><a>沈阳瑞家房产中介</a></li>
										<li><a>厦门万喜来置业代理有限公司</a></li>
										<li><a>南京我爱我家</a></li>
										<li><a>沈阳芒果不动产</a></li>
										<li><a>天津链家地产</a></li>
										<li><a>北京恒昌联行房地产</a></li>
										<li><a>天津我爱我家</a></li>
										<li><a>天津顺驰置业有限公司</a></li>
										<li><a>江苏壹家房地产经纪有限公司</a></li>
										<li><a>温州巨信实业有限公司</a></li>
										<li><a>山东鲁房投资控股有限公司</a></li>
										<li><a>威海宜居房产经纪有限公司</a></li>
										<li><a>苏州华美世居不动产经纪有限公司</a></li>
										<li><a>杭州我爱我家</a></li>
										<li><a>厦门21世纪不动产</a></li>
										<li><a>厦门浦厦投资集团</a></li>
										<li><a>福州双安房产代理有限公司</a></li>
										<li><a>美华房地产营销策划有限公司</a></li>
										<li><a>双赢集团房地产开发有限公司</a></li>
										<li><a>百分百房地产经纪有限公司</a></li>
										<li><a>重庆钢运置业代理有限公司</a></li>
										<li><a>泉州易嘉房地产经纪有限公司</a></li>
									</ul>
								</div>
								<div id=demo2></div>
							</div>
						</div>
					</div>
					<a class="button1" href="Case.html">了解更多>></a>
				</div>
			</div>
			<script>
				$('#myTab a').mousemove(function(e) {
					e.preventDefault();
					$(this).tab('show');
				})
			</script>
		</div>
	</div>
	<style type="text/css">
.index-link-box {
	background: #fff;
	width: 100%;
	height: 150px;
	text-align: center;
}

.index-link-box p {
	padding: 15px;
	color: #000;
	font-size: 30px
}

#ilink {
	overflow: hidden;
	height: 55px;
	width: 1000px;
	margin: 0 auto;
}

#ilink img {
	height: 50px;
	margin-right: 10px;
}

#ilink li {
	float: left;
}
</style>
	<div class='index-link-box'>
		<p>友情链接</p>
		<div id='ilink' style='overflow: hidden; height: 60px; width: 1000px;'>
			<div id='linkbox'>
				<div id='ilink1'>
					<a href="http://ty.5i5j.com/" target="_blank"> <img src="<%=path%>/Static/Picture/58648765cbd59.jpg"></a>
				</div>
				<div id='ilink2'></div>
			</div>
		</div>
	</div>
	<div class="footw">
		<div class="body_1 foot1">
			<ul class="navr">
				<li><a href="">首页</a></li>
				<li><a href="page-1.html">关于我们</a>
					<ul class="">
						<li><a href="page-1.html">公司介绍</a></li>
						<li><a href="page-2.html">团队介绍</a></li>
						<li><a href="page-3.html">成长经历</a></li>
						<li><a href="page-4.html">人才招聘</a></li>
						<li><a href="Newslist-3.html">新闻中心</a></li>
					</ul></li>
				<li><a href="gr.html">产品介绍</a>
					<ul class="">
						<li><a href="gr.html">个人版</a></li>
						<li><a href="qy.html">企业版</a></li>
						<li><a href="jg.html">产品价格</a></li>
					</ul></li>
				<li><a href="Case.html">客户见证</a>
					<ul class="">
						<li><a href="Case.html">客户案例</a></li>
						<li><a href="Message.html">用户感言</a></li>
					</ul></li>
				<li><a href="Agent.html">代理加盟</a>
					<ul class="">
						<li><a href="Agent.html">合作政策</a></li>
						<li><a href="Cooperation.html">市场合作</a></li>
						<li><a href="apply.html">加盟申请</a></li>
					</ul></li>
				<li><a href="Customer.html">客服中心</a>
					<ul class="">
						<li><a href="Customer.html">客户服务</a></li>
						<li><a href="feedback.html">客户反馈</a></li>
						<li><a href="Contact.html">联系方式</a></li>
					</ul></li>
				<li><a href="Help-4.html">帮助中心</a>
					<ul class="">
						<li><a href="Help-4.html">视频教程</a></li>
						<li><a href="Help-5.html">常见问题</a></li>
						<li><a href="Help-6.html">支付问题</a></li>
						<li><a href="Help-7.html">会员问题</a></li>
					</ul></li>
				<li><a href="#">会员中心</a>
					<ul class="">
						<li><a href="registerPC.html">会员注册</a></li>
						<li><a href="loginPage.html">会员登录</a></li>
						<li><a href="#">推广中心</a></li>
						<li><a href="Member-8.html">经验心得</a></li>
					</ul></li>
			</ul>
		</div>
		<div class="body_1 foot2">
			<img src="<%=path%>/Static/Picture/b9.jpg" style="width: 1000px;">
			<table align="center" style="margin: 0 auto">
				<tr>
					<td><img src="<%=path%>/Static/Picture/b10.jpg"></td>
					<td>&nbsp;&nbsp;&nbsp; <span style="position: relative; top: 5px">版权所有 © 厦门市好景科技有限公司 闽ICP备09006319号 <script type="text/javascript">
						var cnzz_protocol = (("https:" == document.location.protocol) ? " https://"
								: " http://");
						document
								.write(unescape("%3Cspan id='cnzz_stat_icon_1259598464'%3E%3C/span%3E%3Cscript src='"
										+ cnzz_protocol
										+ "s95.cnzz.com/z_stat.php%3Fid%3D1259598464%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));
					</script>
					</span>
					</td>
				</tr>
			</table>
			<br>
		</div>
		<br> <br>
	</div>
	<script src="<%=path%>/Static/Js/jquery.lazyload.min.js"></script>
	<script type="text/javascript">
		if ($('.js-xms-lazy').length > 0) {
			$('.js-xms-lazy').lazyload();
		}
	</script>
	<script type="text/javascript">
		var objNode = function(o) {
			return document.getElementById(o);
		}

		totop('demo', 'demo1', 'demo2', 50, 'itop');

		if ($('#ilink1 img').length > 10) {
			$('#linkbox').css('width', '300%');
			var html = $('#ilink1').html()
			$('#ilink1').html(html + html)
			toleft('ilink', 'ilink1', 'ilink2', 50, 'indexLink');
		}

		function toleft(demo, demo1, demo2, speed, flag) {

			demo = objNode(demo);
			demo1 = objNode(demo1);
			demo2 = objNode(demo2);

			demo2.innerHTML = demo1.innerHTML;

			function Marquee() {

				if (demo2.offsetWidth - demo.scrollLeft <= 0) {
					demo.scrollLeft -= demo1.offsetWidth;
				} else {
					demo.scrollLeft++;
					console.log(demo.scrollLeft)
				}
			}

			flag = setInterval(Marquee, speed)
			demo.onmouseover = function() {
				clearInterval(flag);
			}
			demo.onmouseout = function() {
				flag = setInterval(Marquee, speed);
			}
		}

		function totop(demo, demo1, demo2, speed, flag) {

			demo = objNode(demo);
			demo1 = objNode(demo1);
			demo2 = objNode(demo2)

			demo2.innerHTML = demo1.innerHTML

			function Marquee() {
				if (demo2.offsetTop - demo.scrollTop <= 0) {
					demo.scrollTop -= demo1.offsetHeight
				} else {
					demo.scrollTop++
				}
			}

			flag = setInterval(Marquee, speed)
			demo.onmouseover = function() {
				clearInterval(flag);
			}
			demo.onmouseout = function() {
				flag = setInterval(Marquee, speed);
			}
		}
	</script>
</body>

</html>