<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>

<div class="salehouse-content personHouseContent">
<!-- 表格区 -->
<table id="houseTable_guide" class="h_list" width="100%" cellspacing="0" cellpadding="0s">
	<thead>
		<tr>
			<td>类型</td>
			<td>房源基本信息</td>
			<td>业主信息</td>
			<td>来源</td>
			<td width=80>操作</td>
		</tr>
	</thead>
	<tbody id="showTd">
						
	<c:forEach var="list" items="${page.list}">
	<tr houseId="${list.id }" id="firstPersonHouse" class="tr_${list.id }">
		<td width="80">【${list.type }】</td>
		<td>
			<dl class="h_info">
				<dt style="text-align:left"  class="personHouse-title">
					<span class="personHouse-refresh float-l reading" style="display:none">已读</span>
				 			<span title="刷新房源" class="personHouse-refresh float-l">刷新</span>
	                              	<a target="_blank" rel="noreferrer" title="${list.title }"  id = "title_${list.id }" href="${list.detailpath }">${list.title }</a>
				</dt>
				<dd class="h_houseCollect clear" >
					<div class="float-l">
						<span class="personHouse-districtNa max_width_80 tableOverflow">${list.district }</span>
						<span class="personHouse-areaNa max_width_80 tableOverflow">${list.area }</span>
						<span class="personHouse-cell max_width_100 tableOverflow">${list.estate }</span>
						<span title="" class="personHouse-addr"></span>
					</div>
					<div class="float-r">
						<span class="font-bold col-f60">${list.price }万元/套</span>
					</div>
				</dd>
				<dd class="h_property clear">
					<p class="float-l">
						<span>${list.countf }房${list.countt }厅${list.countw }卫</span>
						<span>${list.square }㎡</span>
						<c:if test="${postType == 0 or postType == 1}">
							<span>${list.floor }/${list.floorall }层</span>
						</c:if>
					</p>
					<div class="float-r imgspan">
						<span class="recommend_pic recommending"></span><span class="more_pics">
						</span><span class="personHouse-pic">${list.img == 0 ? '无' : list.img}图</span>
					</div>
				</dd>
			</dl>
		</td>
		<td id = "contacter_td_${list.id }">
			<dl>
				<dd>
				  <span id ="contacter_${list.id }" class="tableOverflow tbable80" title="${list.regname }">${list.regname }</span>
				</dd>
				<dd>
					<ul class="font-bold col-f60 tableOverflow tbable80" title="显示号码" id="phone_755116089511889133">
	 				  	<li title="${list.regtel }">${list.regtel }</li>
						<li title="${list.regtel }" class="xms-relative">
						    <input type="text" value="${list.regtel }" id="data_${list.regtel }" class="hideNum">
							<a style="text-decoration:none" href="#none" class="copyClass" onclick="copyNum('${list.regtel }')">复制</a>  
							<a style="text-decoration:none" href="https://www.baidu.com/s?wd=${list.regtel }" target="_blank">识别</a>
						</li>
	                </ul>
			    </dd>
			</dl>
		</td>
		<td width="80">
			<dl>
				<dd>${list.site }</dd>
				<dd><span class="col-999"><fmt:formatDate value="${list.collectdate }" pattern=" MM-dd HH:mm"/></span></dd>
			</dl>
		</td>
		<td>
			<ul>
	            <li class="store_li" id = "store_li_${list.id }">
            	<c:if test="${empty list.empCustPerson}">
            		<a href="javascript:addStore('${list.id }',${postType })">添加收藏</a>
            	</c:if>	
            	<c:if test="${!empty list.empCustPerson}">
            		<a href="javascript:delStore('${list.id }',${postType })">取消收藏</a>
            	</c:if>	
				<li id = "import_${list.id }"><a href="javascript:getPayPage()">一键秒录</a></li>
	            <li id = "addforbidphone_${list.id }"><a href="javascript:addforbidphone('${list.regtel }','${list.title }')">加入黑名单</a></li>
			</ul>
		</td>
	</tr>
	</c:forEach>
		</tbody>
	</table>
</div>
	<!-- 底部 -->
<div class="personHouse-bottom">
	<div class="download float-l">
	    <span class="xms_download" onclick="javascript:exportTable()"><span class="xms_download_logo"></span><span class="float-l">下载当前报表</span></span>
	</div>
	<div class="float-r">
		<div id="saleManager-fanye" class="commom-fanye "></div>
	</div>
</div>

<script>
	if ('${page.pages}' != null && '${page.pages}' != 0) {
		/*翻页控件*/
		$("#saleManager-fanye").paginate({
			count 		: '${page.pages}',
			start 		: '${page.pageNum}',
			display     : 10,
			border					: false,
			text_color  			: '#50b63f',
			text_hover_color		: '#fff',
			background_color    	: '#fff',	
			background_hover_color	: '#50b63f',
			images		: false,
			mouse		: 'click',
			onChange	: function(){
				$("#pageNow").val($(".jPag-current").html());
				ajaxSub($(".jPag-current").html());
			}
		});
	}
	 
	$("#rTimer").val('undefined');
	$("#personHouse-order").val('1');
	if ('' != null && '' != '') {
		cs_search_ajax('', '', '', '',
				'', '','','',
				'', '','','');
	}
</script>