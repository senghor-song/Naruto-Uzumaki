<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../../common/tag.jsp"%>
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
					<b style="color: #5b8b00; font-weight: normal"> 
					<span class="tableOverflow float-l max_width_240" id="span_title_${list.propertyno }" cell="${list.estate }" title="【<redis:estate var="estate" estateId="${list.estateid }">${estate.district}</redis:estate>】${list.estate }"> 【<redis:estate var="estate" estateId="${list.estateid }">${estate.district}</redis:estate>】${list.estate } </span>
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
					<span>${list.countf}室${list.countt }厅</span> / ${list.squarej }㎡ / <b class="F_red">
						${list.price } </b> 万
					</span>
				</dd>
			</dl>
		</td>
		<c:if test="${houseEnterDto.isType ne '2'}">
			<td width="30">${fn:length(list.massPropertyPublishs)}</td>
		</c:if>
		
		<td width="50"><fmt:formatDate value="${list.regdate }" pattern="yy-MM-dd HH:mm"/></td>
		
		<c:if test="${houseEnterDto.isType ne '1'}">
			<td width="50"><fmt:formatDate value="${list.modifytime }" pattern="yy-MM-dd HH:mm"/></td>
		</c:if>
		<c:if test="${houseEnterDto.isType eq '1'}">
			<td width="50"><fmt:formatDate value="${list.deletedtime }" pattern="yy-MM-dd HH:mm"/></td>
		</c:if>
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
<script>
	$("#saleManager-fanye").html("");
	if ('${page.pages == 0 ? 1 : page.pages}' != null && '${page.pages == 0 ? 1 : page.pages}' != '') {
		/*翻页控件*/
		$("#saleManager-fanye").paginate({
			count 		: '${page.pages == 0 ? 1 : page.pages}',
			start 		: '${page.pageNum == 0 ? 1 : page.pageNum}',
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
	}
</script>
<script>
var cell = $("#cell").val();
var cellHtml = '<option value="">全部小区</option>';
if ('[碧水龙庭, 金山碧海花园]'.length > 0) {
	cellHtml += '<option value="碧水龙庭">碧水龙庭</option>';
	cellHtml += '<option value="金山碧海花园">金山碧海花园</option>';
}
$("#cell").html(cellHtml);
$("#cell").val(cell);
$("#cell").select2();

$(document).ready(function() {
	initWidthAsTag();
});

</script>
