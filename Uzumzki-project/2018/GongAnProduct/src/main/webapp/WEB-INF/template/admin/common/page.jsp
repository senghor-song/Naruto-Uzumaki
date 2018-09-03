<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- ==================
     js函数在common.js中
     ================== -->
<input type="hidden" id="sortProperty" name="sortProperty" value="${page.sortProperty }">
<input type="hidden" id="sortType" name="sortType" value="${page.sortType }">
<input type="hidden" id="pageNumber" name="pageNumber" value="${page.pageNumber }">
<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
<style type="text/css">
    .pageBtn:hover{background-color:#0073d0;color:#fff;}
</style>

 <div class="fPage fr">
     <div class="allSelect">
        <p>共<i class="color_blue">${page.totalCount }</i>条记录，当前显示第<i class="color_blue">${page.pageNumber }</i>页</p>
    </div>
        <ul>
        <c:if test="${page.pageNumber ne 1 }">    
            <li><a class="btn pageBtn" href="javascript:$.pageTo(1);">首页</a></li>
        </c:if>
        <c:if test="${page.pageNumber gt 1}">
            <li><a class="btn pageBtn" href="javascript:$.pageTo(${page.pageNumber-1 });">上一页</a></li>
        </c:if>
        <c:if test="${page.pageNumber-1 > 2 }">
            <li><span>...</span></li>
        </c:if>
        <c:forEach var="num" begin="${page.pageNumber-2 < 1 ? 1 : page.pageNumber-2 }" step="1" end="${page.pageNumber+2 > page.pageCount ? page.pageCount : page.pageNumber+2 }">
            <c:if test="${num eq page.pageNumber }">
                <li><span style="color: #0073d0;">${num }</span></li>
            </c:if>
            <c:if test="${num ne page.pageNumber }">
                <li><a href="javascript:$.pageTo(${num })">${num }</a></li>
            </c:if>
        </c:forEach>
        <c:if test="${page.pageNumber+4 < page.pageCount }">
            <li><span>...</span></li>
        </c:if>
        <c:if test="${page.pageCount gt page.pageNumber }">
            <li><a class="btn pageBtn" href="javascript:$.pageTo(${page.pageNumber+1 });">下一页</a></li>
        </c:if>
    
        <c:if test="${page.pageNumber ne page.pageCount }">
            <li class="lh30"><a class="btn pageBtn" href="javascript:$.pageTo(${page.pageCount });">尾页</a></li>
        </c:if> 
        <li class="lh30"><span>共${page.pageCount }页</span></li>
        <!-- style="width:18px;height:18px;border:1px solid #666;margin:0 2px 0 2px;outline: none;"  -->
        <%-- <li><span style="font-weight: normal;">到第<input type="text"  id="goto" value="${page.pageNumber }" datatype="isNum" nullmsg="请输入整数" errormsg="请输入整数" onkeyup='this.value=this.value.replace(/\D/gi,"")' maxlength="10" style="margin:0 8px;" onkeypress="return $.isNum(event)">页</span></li> --%>
        <li><input type="text"  id="goto" value="${page.pageNumber }" datatype="isNum" nullmsg="请输入整数" errormsg="请输入整数" onkeyup='this.value=this.value.replace(/\D/gi,"")' onkeypress="return $.isNum(event)" maxlength="9"></li>
        <li><a href="javascript:$.go();" class="go">GO</a></li>
        <%-- <li>
			<select class="pageSelect" >
			    <option value="10" <c:if test="${page.pageSize eq 10}">selected="selected"</c:if>>10</ption>
			    <option value="20" <c:if test="${page.pageSize eq 20}">selected="selected"</c:if>>20</option>
			    <option value="50" <c:if test="${page.pageSize eq 50}">selected="selected"</c:if>>50</option>
			    <option value="100" <c:if test="${page.pageSize eq 100}">selected="selected"</c:if>>100</option>
			</select>
		</li> --%>
    </ul>
</div>
