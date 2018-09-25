<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!--左侧导航-->
<div class="mainLeft" id="sidebarNav">
    <!--预警一级-->
    <div class="TabContentItem">
        <!--重点人预警二级 -->
        <div class="CateItem curr">
            <dl>
                <dt>
                    <a href="javascript:void(0)">
                        <i class="iconfont">&#xe65b;</i>
                        <span class="text-name">重点人预警</span>
                        <em class="arrow_down"></em>
                    </a>
                </dt>

                <!-- 三级 -->
                <dd class="curr">
		            <i class="icon"></i>
		            <a href="/admin/controlPersonAlarm/list.shtml" class="text-name">预警列表</a>
		        </dd>
		        <!-- 三级 -->
			   <%--  <c:if test=" ${sessionScope.user.unit.id ==1 or sessionScope.loginUserUnit.unitRank.name()=='city' or sessionScope.loginUserUnit.unitRank=='null'}">
			    	<dd>
						<i class="icon"></i> <a href="/admin/controlPersonAlarm/stableControlList.shtml" class="text-name">稳控列表</a>
					</dd>
				</c:if> --%>
				 <c:if test="${sessionScope.user.id ==1 or sessionScope.loginUserUnit.unitRank=='city'}">
			    	<dd>
						<i class="icon"></i> <a href="/admin/controlPersonAlarm/stableControlList.shtml" class="text-name">稳控列表</a>
					</dd>
				</c:if>
				<!-- 三级 -->
		        <dd>
		            <i class="icon"></i>
		            <a href="/admin/controlPersonInstructi/issuedInstruction.shtml" class="text-name">已下发预警</a>
		        </dd>
                <!-- 三级 -->
                <c:if test="${sessionScope.user.id!=1}">
		        <dd>
		            <i class="icon"></i>
		            <a href="/admin/controlPersonInstructi/receivedInstruction.shtml" class="text-name">待签收预警</a>
		        </dd>
		        </c:if>
                <!-- 三级 -->
            </dl>
        </div><!-- 重点人员 -->
        <!--重点人预警二级 -->
    </div>
    <!--预警一级-->
    <!--关注人员一级-->
    <div class="TabContentItem">
        <!--关注人员二级 -->
        <div class="CateItem">
            <dl>
                <dt>
                    <a href="javascript:void(0)">
                        <i class="iconfont">&#xe65b;</i>
                        <span class="text-name">关注人员筛查</span>
                        <em class="arrow_right"></em>
                    </a>
                </dt>
		        <dd>
		            <i class="icon"></i>
		            <a href="/admin/leavePerson/list.shtml" class="text-name">离市人员</a>
		        </dd>
                <!-- 三级 -->
            </dl>
        </div>
        <!--关注人员二级 -->
    </div>
    <!--关注人员一级-->
</div>
