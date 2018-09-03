<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common/tag.jsp"%>
	            
	            <table class="hovertable">
	                <thead>
		                <tr>
			                <th width="5%">序号</th>
			                <th width="12%">网站</th>
			                <th width="12%">账号</th>
			                <th width="8%">房源类型</th>
			                <th width="8%">刷新条数</th>
			                <th width="8%">剩余条数</th>
			                <th width="10%">刷新结果</th>
			                <th>刷新房源</th>
		                    <th>计划号</th>
			                <th width="15%">刷新时间</th>
		                </tr>
	                </thead>
	                <tbody id="tb_tasks">
	                	<c:forEach var="list" items="${page.list}" varStatus="index">
							<tr>
								<td>${(10 * (page.pageNum == 1 ? 0 : page.pageNum - 1))+index.index+1}</td>
								<td>${list.webname }</td>
								<td>${list.account }</td>
								<td>${list.housetype }</td>
								<td>${list.refreshsum }</td>
								<td>${list.refreshsum }/${list.refreshremain }</td>
								<td><span title="${list.refreshresult}">${list.refreshresult}</span></td>
								<td></td>
								<td>${list.projectname}</td>
								<td><fmt:formatDate value="${list.refreshtime}" pattern="yy-MM-dd HH:mm"/></td>
							</tr>
	                	</c:forEach>
					</tbody>
	            </table><br>
				<div id="paging" align="center">
						<c:if test="${1 != page.pageNum }">
		                	<span id="lastSpan"><a class="page" href="javascript:" onclick="getRefreshLog(${page.pageNum - 1})">上一页</a></span>
						</c:if>
		                <span id="link"> 
	                		<!-- <a class="page" href="javascript:" onclick="getRefreshLog(1)" style="color:red;border-color:  red;">1</a> -->
							<c:if test="${page.pageNum > 6}">
								<c:forEach var="i" begin="1" end="${page.pages}" varStatus="index">
									<c:if test="${index.index > page.pageNum-6 and index.index < page.pageNum+6}">
										<a id="page_${index.index}" class="page" href="javascript:" onclick="getRefreshLog(${index.index})" 
										<c:if test="${index.index == page.pageNum}"> style="color:red;border-color:  red;"</c:if>>${index.index}</a>
									</c:if>
			                	</c:forEach>
							</c:if>
							<c:if test="${page.pageNum <= 6}">
								<c:forEach var="i" begin="1" end="${page.pages > 10 ? 10 : page.pages}" varStatus="index">
										<a id="page_${index.index}" class="page" href="javascript:" onclick="getRefreshLog(${index.index})" 
										<c:if test="${index.index == page.pageNum}"> style="color:red;border-color:  red;"</c:if>>${index.index}</a>
			                	</c:forEach>
							</c:if>
						</span> 
						<c:if test="${page.pages != page.pageNum }">
							<span id="nextSpan"><a class="page" href="javascript:" onclick="getRefreshLog(${page.pageNum + 1})">下一页</a></span> 
						</c:if>
	                	<span id="nowSpan">
		                	<select name="pageNow" id="pageNow" onchange="getRefreshLog(this.value)" style="height: 22px;margin-top: -5px;">
		                	<c:forEach var="i" begin="1" end="${page.pages}" varStatus="index">
								<option value="${index.index}">第${index.index}页</option>
		                	</c:forEach>
						</select>
						</span> 共<span id="rowCountSpan" style="color: red">${page.total }</span>条记录
				 		 刷新<span id="houseCountSpan" style="color: red">${page.total }</span>条房源
            	</div>
            	