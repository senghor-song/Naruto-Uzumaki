<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../../common/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>出售房源管理</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/saleManager_1b2829a78572fecaf2ba3f19b64abfa1.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/lightbox.min.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/xmsSelect_0687a4d524bc0ea4a5a2ff201f642e0a.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/tip-yellowsimple_d6a155759956acdb4b49b381d5bf5fbf.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/introjs_03b43e32a5901af129087d7ae282fea4.css' rel='stylesheet' type='text/css'></link>
	<style type="text/css">
	.task-title li{width:290px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;}
	
	/*分页*/
	#releaseLogTaskPageBox li{float:left;padding:3px 7px;margin-right:5px;font-size:14px;cursor:pointer;
		border:1px solid #6BB05F;
	}
	#releaseLogTaskPageBox li.on{background: #6BB05F;color:#fff;}
	#webAccountTable td{padding: 2px;}
	.select2-container{vertical-align:inherit}
	</style>
  </head>
<body>
<!--  隐藏参数   -->
<div>
	<input type="hidden" id="postType" value="${houseEnterDto.postType }" />
	<input type="hidden" id="buildingType" value="1" />
	<input type="hidden" id="buildingStatus" value="0" />
	<input type="hidden" id="page" value="${page.pageNum }" />
	<input type="hidden" id="basePath" value="" />
	<input type="hidden" id="staticPath" value="//demo.com" />
	<input type="hidden" id="rlsRem" value="50" />
	<input type="hidden" id="kcRem" value="199" />
	<input type="hidden" id="rlsUsed" value="0" />
	<input type="hidden" id="rlsUsing" value="0" />
	<input type="hidden" id="rlsTotal" value="50" />
	<input type="hidden" id="isAppointment" value="0" />
	<input type="hidden" id="status" value="1" />
	<input type="hidden" id="isDraft" value="0" />
	<input type="hidden" id="updateTags" value="" />
	<input type="hidden" id="nowDate" value="2018-5-15" />
	<input type="hidden" id="buildingId" value="0"></input>
	<input type="hidden" id="guideIndex" value="0"></input>
	<input type="hidden" id="houseNum" value="0"></input>
	<input type="hidden" id="deleteType" value="1"></input>
	<input type="hidden" id="isType" value="${houseEnterDto.isType }"></input>
</div>

<div id="saleHouse" class="saleHouse">
	<div class="saleHouse-main release-main">
	
	<!-- 选择房源 -->
	<div class="selectHouse">
		<div class="notice">
			尊敬的用户，您今日还可推送 <span id="rlsSpan" class="col-f60 font-bold">50</span> 套房源 ,
			剩余可录入房源 <span id="kcSpan" class="col-f60 font-bold">50</span> 套
		</div>
		<ul class="saleprocess">
			<li id="selectHouse" class="active defluat"><span class="first">第一步：选择房源</span></li>
			<li id="selectWebsite" class="defluat"><span>第二步：选择网站</span></li>
			<li id="selectFinish" class="defluat last"><span class="last">第三步：操作完成</span></li>
		</ul>
		
		<!-- TAB切换部分 -->
		<div class="clear mt8">
			<ul class="sale-tab-sub manager-top">
				<li id="tab1" class="sale-sub-menu selected">
					<a id="table_1" href="javascript:" title="" onclick="switchTab(1)">住宅(<span id="typeSpan"></span>)</a>
				</li>
				<!-- <li id="tab2" class="sale-sub-menu">
					<a id="table_2" href="javascript:" title="" onclick="switchTab(2)">别墅(<span id="typeSpan2"></span>)</a>
				</li>
				<li id="tab3" class="sale-sub-menu">
					<a id="table_3" href="javascript:" title="" onclick="switchTab(3)">商铺(<span id="typeSpan3"></span>)</a>
				</li>
				<li id="tab4" class="sale-sub-menu">
					<a id="table_4" href="javascript:" title="" onclick="switchTab(4)">写字楼(<span id="typeSpan4"></span>)</a>
				</li>
				<li id="tab5" class="sale-sub-menu">
					<a id="table_5" href="javascript:" title="" onclick="switchTab(5)">厂房(<span id="typeSpan5"></span>)</a>
				</li> -->
			</ul>
		</div>
			
		<!-- 查询区 -->
		<ul class="saleManager-select">
			<li>
				<ul class="saleManager-state float-l">
					<li id="statusLi0" value="0" class="active">
						<a href="javascript:" onclick="switchStatusTab('statusLi0')">发布中(<span id="statusSpan"></span>)</a>
					</li>
					<li id="statusLi2" value="2">
						<a href="javascript:" onclick="switchStatusTab('statusLi2')">草稿箱(<span id="statusSpan1"></span>)</a>
					</li>
					<li id="statusLi1" value="1">
						<a href="javascript:" onclick="switchStatusTab('statusLi1')">回收站(<span id="statusSpan2"></span>)</a>
					</li>
				</ul>
				
				<div style="float: right;">
					<div style="font-size: 15px; color: #EC4300; font-weight: bold; float: left;">
						<span>切换城市：</span>
					</div>
					<div style="float: left;">
						<select id="city" name="city" class="iw92 xmsSelect" style="font-size: 15px; border: 1px solid #64ac58; border-radius: 3px; height: 26px;" onchange="getHousebyCondition()">
							<option value="">全部</option>
							<c:forEach var="city" items="${citys }">
								<option value="${city.id }">${city.city }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
			</li>
			<li>
				<ul class="saleManager-key float-l">
					<li>
						<select class="xmsSelect tbable100" id="cell" onchange="getHousebyCondition()">
							<option value="">全部小区</option>
						</select>
					</li>
					<li>
						<select class="xmsSelect tbable120" id="sort" onchange="getHousebyCondition()">
							<option value="0">更新时间排序</option>
							<option value="1">录入时间排序</option>
							<option value="2">推送时间排序</option>
							<option value="3">价格从大到小</option>
							<option value="4">价格从小到大</option>
							<option value="5">面积从大到小</option>
							<option value="6">面积从小到大</option>
						</select>
					</li>					
					<li>
						<input class="saleManager-price" id="price1" value="" placeholder="价格(${houseEnterDto.postType eq '0' ? '万':''}元)"/>
						<span style="margin:0 1px">-</span>
						<input class="saleManager-price" type="text" id="price2" value="" placeholder="价格(${houseEnterDto.postType eq '0' ? '万':''}元)"/>
					</li>
					<li><input class="saleManager-search-key" type="text" id="title" value="" onkeypress="javascript:enterSumbit()" placeholder="请输入房源编号、标题或内部编码" style="margin-right:0px;width:200px"></input></li>
					<li><input class="btn-search" type="button" onclick="getHousebyCondition()"></input></li>
					<li><input class="btn-more" type="button"></input></li>
				</ul>
			</li>
			<li class="js-more-list" style="display:none">
				<ul class="saleManager-key float-l">
				<li>
					<select class="xmsSelect" id="tagsTip" onchange="getHousebyCondition()" style="width:100px">
						<option value="">房源标签</option>
						<option value="notTags">无标签</option>
						<option value="yrz">已认证</option>
						<option value="new">新房</option>
						<option value="focus">集攻</option>
						<option value="recommend">推荐</option>
						<option value="secure">放心</option>
						<option value="hot">急推</option>
						<option value="notTags">无标签</option>							
					</select>
				</li>
				<li>
					<select class="xmsSelect" id="sourceType" onchange="getHousebyCondition()" style="width:120px">
						<option value="">房源来源</option>
						<option value="1">录入</option>
						<option value="7">秒录</option>
						<option value="2">克隆</option>
						<option value="3">另存</option>
						<option value="4">复制</option>
						<option value="9">导入</option>
						<option value="12">ERP</option>
					</select>
				</li>
				<li>
					<select class="xmsSelect tbable80" name="mutilFlag" onchange="getHousebyCondition()">
						<option value="">是否多图</option>					
						<option value="1">多图</option>
						<option value="0">非多图</option>			
					</select>
				</li>
					<li>
						<select class="xmsSelect tbable80" name="videoFlag" onchange="getHousebyCondition()">
							<option value="">是否视频</option>						
							<option value="1">视频</option>
							<option value="0">非视频</option>
						</select>
					</li>
				<li>
					<select class="xmsSelect tbable80" name="room" onchange="getHousebyCondition()">
						<option value="">居室</option>							
						<option value="1">一居</option>
						<option value="2">二居</option>
						<option value="3">三居</option>
						<option value="4">四居</option>
						<option value="5">五居</option>
						<option value="6">六居</option>
						<option value="7">七居</option>
						<option value="8">八居</option>
						<option value="9">九居</option>		
					</select>
				</li>
			</ul>
			</li>
		</ul>
		
		<!-- 数据内容区 -->
		<div class="saleManager-state-content con1">
			<!-- 表格区 -->
			<table class="h_list" width="100%" cellspacing="0" cellpadding="0s">
				<thead>
					<tr>
						<td>房源编号</td>
						<td>基本信息</td>
						<td id="webNumTD">网站数</td>
						<td>录入时间</td>
						<td id="updateTD">更新时间</td>
						<td>推送时间</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody id="houseTable">
					<c:if test="${empty page.list}">
						<tr>
							<td colspan="7">
								<div class="null-tip saleManager-state-content con2">
									<img width="70" height="70" src="<%=path%>/Static/Picture/icon_tip.png"></img>
									<p class="">没有满足条件的房源</p>
									<p>
										<a id="noHouse_guide" href="/WebRelease/massProperty/houseEnter?postType=${houseEnterDto.postType }" class="ui-btn">
										<span class="ui-btn-blue"><em>立即录入</em></span></a>
									</p>
								</div>
							</td>
						</tr>
					</c:if>
					<c:forEach var="list" items="${page.list}">
						<tr id="htr${list.propertyno }">
							<td width="45" class="firstBuilding">
								<ul style="text-align: center">
									<li><input class="saleManager-state-checkbox" type="checkbox" name="buildCheck" value="${list.id }" cellname="${list.estate }" bldcity="${list.estateid }"
										id="bid${list.propertyno }" videostatus="0" style="margin: 0px"></li>
									<li><label for="bid${list.propertyno }">${list.propertyno }</label></li>
								</ul>
							</td>
							<td>
								<dl class="h_info">
									<dt class="h_pic">
										<a href="javascript:modifyHouse(${houseEnterDto.postType },1,'${list.id }','U')">
										<img src="${list.headimgpath }" width="80" height="60"
											original="${list.headimgpath }"></a>
									</dt>
									<dd class="h_title">
										<a id="title_${list.propertyno }" title="${list.title }" href="javascript:modifyHouse(${houseEnterDto.postType },1,'${list.id }','U')">
										${list.title } </a>
									</dd>
									<dd class="eare">
										<b style="color: #5b8b00; font-weight: normal"> <span class="tableOverflow float-l max_width_240" id="span_title_${list.propertyno }" 
											cell="${list.estate }" title="【${!empty list.districtT ? list.districtT.district : '暂无'}】${list.estate }"> 
											【${!empty list.districtT ? list.districtT.district : '暂无'}】${list.estate } </span>
											<c:if test="${list.flagmoreimage == '1'}">
												<div class="float-r ml4">
													<span class="houseTagStyle mutilFlag_color">多图</span>
												</div>
											</c:if>
											<c:if test="${list.regtype == '1' }">
												<div class="float-r ml4">
													<span class="houseTagStyle source_color">录入</span>
												</div>
											</c:if>
											<div class="float-r" id="tags_pic_${list.propertyno}"></div>
										</b>
									</dd>
									<dd class="h_property">
										<span class="float-l h-property-left"> <span>${list.floor}/${list.floorall}</span> / 
										<span>${list.countf}室${list.countt }厅</span> / ${list.squarej }㎡ / 
											<c:if test="${houseEnterDto.postType eq '0'}">
												<b class="F_red">${list.price }</b> 万元
											</c:if>
											<c:if test="${houseEnterDto.postType eq '1'}">
												<b class="F_red">${list.price }</b> 元/月
											</c:if>
										</span>
									</dd>
								</dl>
							</td>
							<td width="30">${fn:length(list.massPropertyPublishs)}</td>
							<td width="50"><fmt:formatDate value="${list.regdate }" pattern="yy-MM-dd HH:mm"/></td>
							<td width="50"><fmt:formatDate value="${list.modifytime }" pattern="yy-MM-dd HH:mm"/></td>
							<td width="50">
								<c:if test="${empty list.sendtime}">未推送</c:if>
								<c:if test="${!empty list.sendtime}"><fmt:formatDate value="${list.sendtime }" pattern="yy-MM-dd HH:mm"/></c:if>
							</td>
							<td width="50"><input type="hidden" id="ct_${list.propertyno }" value="2018-05-09 14:31:56.51">
								<ul>
									<li><a href="javascript:modifyHouse(${houseEnterDto.postType },1,'${list.id }','U')">修改</a></li>
									<%-- <li>
										<div class="more-btn">
											<div>
												<a>更多操作</a>
											</div>
											<div class="opermore-relative">
												<ul class="opermore">
													<li><a name="link_share_type" buildingid="${list.propertyno }" clonefrom="" shareorgid="0">未共享</a></li>
													<li><a name="link_tag" buildingid="${list.propertyno }" tags="">设置标签</a></li>
													<li><a data-id="${list.propertyno }" class="js-clone-house">转换类型</a></li>
												</ul>
											</div>
										</div>
									</li> --%>
								</ul>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- 底部 -->
			<div class="saleManager-bottom">
				<div class="qx"><input type="checkbox" id="saleManager-all"></input><label for="saleManager-all">全选</label></div>
				<div>
					<div id="buttonDiv0" class="btns float-l">
						<input id="saleManager_push" onclick="releaseClick(0)" type="button" class="btn_o float-l ml8" value="发布"/>
						<!-- 
							<a id="batchRelease-video" onclick="javascript:releaseClick(2)" class="release-video float-l ml8">发视频</a> -->
						
						<input type="button" class="btn_o float-l ml8" value="删除" id="del_button" onclick="javascript:deleteHouse(1, 1, 1, ${houseEnterDto.isType})"></input>
						
						<%-- <input type="button" class="btn_o float-l ml8" value="设置共享" id="btn_share"></input>
						
						<div class="batch-opermore ml8">更多操作
							<img src="<%=path%>/Static/Picture/toTop.png" width="5" height="3" style="margin-bottom:3px"></img>
							<ul class="batch-opermore-content">
								<li onclick="releaseClick(1)">预约发布</li>
								<li onclick="batchUpdateTags()">设置标签</li>
								<li onclick="delNetworkBuildings()">全网下架</li>
								<li onclick="batchRestoreImgs()">恢复原图</li>
							</ul>
						</div> --%>
					</div>
					<div id="buttonDiv1" class="btns float-l" style="display: none">
						<input onclick="javascript:deleteHouse(0, 1, 3, ${houseEnterDto.isType})" type="button" class="btn_o" value="还原"></input>
						<input onclick="javascript:deleteHouse(2, 1, 2, ${houseEnterDto.isType})" type="button" class="btn_o" value="彻底删除"></input>
						<input onclick="javascript:deleteHouse(2, 0, 4, ${houseEnterDto.isType})" type="button" class="btn_o" value="清空回收站"></input>
						<input onclick="delNetworkBuildings()" type="button" class="btn_o" value="全网下架"></input>
					</div>
					<div id="buttonDiv2" class="btns float-l" style="display: none">
						<input onclick="javascript:deleteDraft()" type="button" class="btn_o" value="删除"></input>
					</div>
				</div>
				<div class="float-r">
					<div id="saleManager-fanye" class="commom-fanye">
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 选择网站 -->
	<div class="selectWebsite" style="display:none">
		<ul class="saleprocess">
			<li id="selectHouse" class="defluat selected"><span class="first">第一步：选择房源</span></li>
			<li id="selectWebsite" class="active defluat"><span>第二步：选择网站</span></li>
			<li id="selectFinish" class="defluat last"><span class="last">第三步：操作完成</span></li>
		</ul>
		<!-- 提示信息 -->
		<ul class="sale-content-tip">
			<li><span class="sale-tip-img"></span><span>贴心提示:</span></li>
			<li>1.如果您希望使用的账号不在其中， 您可以马上<a href="javascript:getWebManager()" id="addAccount-guide"> 添加账号  </a>。</li>
			<li>2.如果您希望修改"推送满时"与"房源重复"的发送处理方式， 请进入 <a href="javascript:getPersonSet()">个人设置 </a> 进行修改。</li>
		</ul>
		<!-- 查询区 -->
		<ul class="selectWebsite-select" style="display: none">
			<li class="float-l mr8" id="manage-date-guide">
				<ul class="manage-list manage-date1">
					<li class="float-l manage-font" style="height:20px;line-height:20px">指定日期：</li>
					<li class="manage-tip manage-date1-content float-l" onclick="selectOption('manage-date1-content')" style="height:20px;line-height:20px;padding-left:3px"><span id="dateSpan_1" style="width:60px"></span><img width="17" height="20" src="<%=path%>/Static/Picture/add_plus.jpg"></img></li>
					<li>
						<ul id="dateUl_1" class="manage-list-content manage-date1-content" style="display:none;width:81px;top:21px">
						</ul>
					</li>
				</ul>
				<ul class="manage-list manage-date2">
					<li class="float-l manage-font" style="height:20px;line-height:20px">至</li>
					<li class="manage-tip manage-date2-content float-l" onclick="selectOption('manage-date2-content')" style="height:20px;line-height:20px;padding-left:3px"><span id="dateSpan_2" style="width:60px"></span><img width="17" height="20" src="<%=path%>/Static/Picture/add_plus.jpg"></img></li>
					<li>
						<ul id="dateUl_2" class="manage-list-content manage-date2-content" style="display:none;width:81px;top:21px">
						</ul>
					</li>
				</ul>
			</li>
			<li class="float-l mr8" id="manage-time-guide">
				<ul class="manage-list manage-time1">
					<li class="float-l manage-font" style="height:20px;line-height:20px">起始时间：</li>
					<li class="manage-tip manage-time1-content float-l" onclick="selectOption('manage-time1-content')" style="height:20px;line-height:20px;padding-left:3px"><span id="hourSpan" style="width:24px">10</span><img width="17" height="20" src="<%=path%>/Static/Picture/add_plus.jpg"></img></li>
					<li class="float-l manage-font" style="height:20px;line-height:20px">点</li>
					<li>
						<ul id="hourUl" class="manage-list-content manage-time1-content" style="display:none;width:44px;right:12px;top:21px">
						</ul>
					</li>
				</ul>
				<ul class="manage-list manage-time2">
					<li class="manage-tip manage-time2-content float-l" onclick="selectOption('manage-time2-content')" style="height:20px;line-height:20px;padding-left:3px"><span id="minnuteSpan" style="width:24px">0</span><img width="17" height="20" src="<%=path%>/Static/Picture/add_plus.jpg"></img></li>
					<li class="float-l manage-font" style="height:20px;line-height:20px">分</li>
					<li>
						<ul id="minnuteUl" class="manage-list-content manage-time2-content" style="display:none;width:44px;right:14px;top:21px">
						</ul>
					</li>
				</ul>
			</li>
			<li class="float-l">
				<ul class="manage-list minnuteUl-time" id="manage-appNum-guide" style="width:118px">
					<li class="float-l manage-font" style="height:20px;line-height:20px">上架次数：</li>
					<li class="manage-tip manage-appNum-content float-l" onclick="selectOption('manage-appNum-content')" style="height:20px;line-height:20px;padding-left:3px"><span id="appNumSpan" style="width:24px">1</span><img width="17" height="20" src="<%=path%>/Static/Picture/add_plus.jpg"></img></li>
					<li>
						<ul id="appNumUl" class="manage-list-content manage-appNum-content" style="display:none;width:44px;right:12px;top:21px">
						</ul>
					</li>
				</ul>
			</li>
			<li class="float-l">
				<ul class="manage-list minnuteUl-time" id="manage-appMin-guide" style="width:148px">
					<li class="float-l manage-font" style="height:20px;line-height:20px">上架间隔时间：</li>
					<li class="manage-tip manage-appMin-content float-l" onclick="selectOption('manage-appMin-content')" style="height:20px;line-height:20px;padding-left:3px"><span id="appMinSpan" style="width:24px">240</span><img width="17" height="20" src="<%=path%>/Static/Picture/add_plus.jpg"></img></li>
					<li class="float-l manage-font" style="height:20px;line-height:20px">分</li>
					<li>
						<ul id="appMinUl" class="manage-list-content manage-appMin-content" style="display:none;width:44px;right:18px;top:21px">
						</ul>
					</li>
				</ul>
			</li>
			<li id="appDetail" class="float-r" style="font-size: 12px;">
				<a onclick="appDetail()">查看详情</a>
			</li>
		</ul>
		<!-- 网站信息 -->
		<div id="selectAccount-guide">
			<table class="h_list clear" width="100%" cellspacing="0" cellpadding="0s">
				<thead><tr><td>网站</td><td>帐号</td><td>推送满时发送处理</td><td>房源重复发送处理</td><td>推送设置</td></tr></thead>
				<tbody id="webAccountTable">
					<!-- ajax 请求内容    -->
				</tbody>
			</table>
		</div>
		<div class="saleManager-bottom clear">
			<div class="qx"><input type="checkbox" id="website-all"></input><label for="website-all">全选</label></div>
			<div class="float-r">
			    <span id="videoRls-tip" class="col-f60 font-16"></span>
				<input id="selectWebsite_on" type="button" class="btn_g" value="上一步"></input>
				<a id="selectWebsite_next" href="javascript:nextClick()" type="button" class="btn_g manager-rel">发布</a>
				<a style="display:none" id="selectWebsite_next" href="javascript:nextClick()" type="button" class="btn_g manager-per">预约</a>
			</div>
		</div>
	</div>
	
	<!-- 选择完成 -->
	<div class="selectFinish mt8" style="display:none">
			<ul class="saleprocess">
				<li id="selectHouse" class="defluat"><span class="first">第一步：选择房源</span></li>
				<li id="selectWebsite" class="selected defluat"><span>第二步：选择网站</span></li>
				<li id="selectFinish" class="active defluat last"><span class="last">第三步：操作完成</span></li>
			</ul>
		<!--发布提示信息 -->
			<div class="sale-push">
			
<!-- 				<ul class="sale-content-tip"> -->
<!-- 					<li><span class="sale-tip-img"></span><span>贴心提示:</span></li> -->
<!-- 					<li>1.您的房源发布任务已经开始。</li> -->
<!-- 					<li>2.发布过程根据网络情况需要几分钟之内完成。</li> -->
<!-- 					<li>3.成功发布的信息将会即时出现在发布成功记录中,您可以随时查看。</li> -->
<!-- 				</ul> -->
				
				
				<div class="sale-push-content">
					您选中了<span class="house-select select-font">0</span>条房源发布到<span class="website-select select-font">0</span>个网站，易推房为您创建了<span class="select-total select-font">0</span>个发布任务  &nbsp;
					进行中:<span class="select-font js-ing">0</span>
					成功:<span class="select-font js-success">0</span>
					失败:<span class="select-font js-fail">0</span>
					
					
				</div>
				
				<div id="repeatTip" style="margin-left: 50px;font-size:14px;"></div>
				<div id='currentReleaseTaskBox'></div>
				<div id='releaseLogTaskPageBox'></div>
				
				<div class="saleManager-bottom">
					<div class="float-r">
						<a id="releaseLog-guide" class="btn_g" href="javascript:redirectLog('/rellog/getlogview.do', '?index=1&guideRelease=1')">发布日志</a>
						<a class="btn_g" href="javascript:redirectLog('/rellog/getlogview.do', '?index=4')">发布统计</a>
						<a id="continue-sf" class="btn_g">继续发布</a>
					</div>
				</div>
			</div>
			
			<!--预约发布提示信息 -->
			<div class="sale-realsepush" style="display:none">
				<ul class="sale-content-tip">
					<li><span class="sale-tip-img"></span><span>贴心提示:</span></li>
					<li>祝贺您！您的预约任务已设定成功！</li>
					<li>任务将在指定的时间段内随机执行。</li>
				</ul>
				<div class="saleManager-bottom">
					<div class="float-r">
						<a id="see-task" class="btn_g" href="javascript:redirectAppointment('/appointment/getAppointmentView.do','?postType=0')">查看任务</a>
						<a id="continue-set" class="btn_g" >继续预约</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/json2.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/placeholder.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/map.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.lazyload.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/lazyload_b48db04a5a2ab61ce6f6ec45530c3bfb.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.paginate.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/jquery.poshytip.min.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js?skin=green' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/select2.full.min.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/lightbox.min_7036adb83f9be3e168896fb077b8e4f0.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/template.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/manager_978446ea1b000160e94d3d1bdfda3f41.js' type='text/javascript'></script>
<script>var initEvent = false;</script>
<script src='<%=path%>/Static/Js/erpImport_bb73d7abd7959df15ef97258eb4fab92.js' type='text/javascript'></script>
<script src='<%=path%>/Static/Js/intro_e523b61a7b0f0aa4d1bcc380363a6bba.js' type='text/javascript'></script> 
<script src='<%=path%>/Static/Js/introConfig_release_manage_cd052974e8a0467751c2a56a8e22a814.js' type='text/javascript'></script>
	
<script>
$(".xmsSelect").select2({minimumResultsForSearch: -1});
$("#cell").select2();

var notDelWebIds = '58,83,195,245,253,258,262,291';

var cityMap = new Map();

	
		cityMap.put("755", '深圳');
	
		cityMap.put("769", '东莞');
	
		cityMap.put("760", '中山');
	
		cityMap.put("752", '惠州');
	
		cityMap.put("20", '广州');
	
		cityMap.put("114", '惠东');
	
		cityMap.put("762", '河源');
	
		cityMap.put("115", '博罗');
	


if ('' != '') {
	$("#cell").val('');
}

var tagMap = new Map();
$(document).ready(function() {
	value0 = '${massPropertyDto.releaseSum}' == '' ? 0 : '${massPropertyDto.releaseSum}';
	value1 = '${massPropertyDto.draftSum}' == '' ? 0 : '${massPropertyDto.draftSum}';
	value2 = '' == '' ? 0 : '';
	value3 = '' == '' ? 0 : '';
	value4 = '' == '' ? 0 : '';
	value5 = '' == '' ? 0 : '';
	value6 = '' == '' ? 0 : '';
	value7 = '' == '' ? 0 : '';
	value8 = '' == '' ? 0 : '';
	value9 = '' == '' ? 0 : '';
	
	value10 = '${massPropertyDto.recycleSum}' == '' ? 0 : '${massPropertyDto.recycleSum}';
	value11 = '' == '' ? 0 : '';
	value12 = '' == '' ? 0 : '';
	value13 = '' == '' ? 0 : '';
	value14 = '' == '' ? 0 : '';
	
	$("#typeSpan").html(parseInt(value0) + parseInt(value1) + parseInt(value10));
	$("#typeSpan2").html(parseInt(value2) + parseInt(value3) + parseInt(value11));
	$("#typeSpan3").html(parseInt(value4) + parseInt(value5) + parseInt(value12));
	$("#typeSpan4").html(parseInt(value6) + parseInt(value7) + parseInt(value13));
	$("#typeSpan5").html(parseInt(value8) + parseInt(value9) + parseInt(value14));
	$("#statusSpan").html(value0);
	$("#statusSpan1").html(value1);
	$("#statusSpan2").html(value10);
	
	switchTab($("#buildingType").val(), 0);
	var proTag = ($("#isDraft").val() == 0) ? 0 : 2;
	if (proTag == 0) {
		proTag = ($("#buildingStatus").val() == 0) ? 0 : 1;
	}
	switchStatusTab("statusLi" + proTag, 0);
	$("#saleManager-fanye").paginate({
		count 		: '${page.pages == 0 ? 1 : page.pages}',
		start 		: 1,
		display     : 6,
		border					: false,
		text_color  			: '#50b63f',
		text_hover_color		: '#fff',
		background_color    	: '#fff',	
		background_hover_color	: '#50b63f', 
		images		: false,
		mouse		: 'click',
		onChange	: function(){
			getHousebyCondition($(".jPag-current").html());
		}
	});
	
	initWidthAsTag();
	tagMap = new Map();
	
});
</script>

<script type="text/template" id='releaseTaskTpl'>
<table class="h_list clear" width="100%" cellspacing="0" cellpadding="0s">
	<thead><tr><td>房源编号</td><td>基本信息</td><td>目标网站</td><td>操作类型</td><td>发布账号</td><td>状态</td><td>操作时间</td></tr></thead>
	<tbody>
			{{each list as value i}}
			<tr>
				<td width="60">
					<a class='js-redirect-import' data-bid='{{value.buildingId}}' data-time='{{value.releaseTime}}'>{{value.buildingId}}</a>
				</td>
				<td style="text-align:left;width:300px">
					<ul class="task-title">{{getHouseInfo(value.buildingId)}}</ul>
				</td>
				<td><span class="webSpan">{{getWebName(value.webId)}}</span></td>
				<td>{{getOperType(value.operType)}}</td>
				<td><span class="loginName" title="{{value.loginName}}">{{value.loginName}}</span></td>
				<td class="viewFailGuide">{{getFailReason(value.failReason ,i)}}</td>
				<td>{{dateFormat(value.releaseTime)}}</td>
			</tr>
			{{/each}}
	</tbody>
</table>
</script>
</body>
</html>