<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!--左侧导航-->
<div class="mainLeft" id="sidebarNav">
    <!--合成研判一级-->
    <div class="TabContentItem">
        <!--合成研判二级 -->
        <div class="CateItem curr">
            <dl>
                <dt>
                    <a href="javascript:void(0)">
                        <i class="iconfont">&#xe65b;</i>
                        <span class="text-name">合成研判</span>
                        <em class="arrow_down"></em>
                    </a>
                </dt>
                <!-- 三级 -->
                <dd class="curr">
		            <i class="icon"></i>
		            <a href="/admin/syntheticAnalysis/taskList.shtml" class="text-name">任务列表</a>
		        </dd>
                <!-- 三级 -->
                <dd>
		            <i class="icon"></i>
		            <a href="/admin/syntheticAnalysis/unitTaskList.shtml" class="text-name">单位任务列表</a>
		        </dd>
				<!-- 三级 -->
		        <dd>
		            <i class="icon"></i>
		            <a href="/admin/syntheticAnalysis/add.shtml?urgency=2" class="text-name">创建任务</a>
		        </dd>
                <!-- 三级 -->
                <c:if test="${loginUserUnit.urgency or user.id==1}">
			        <dd>
			            <i class="icon"></i>
			            <a href="/admin/syntheticAnalysis/add.shtml?urgency=1" class="text-name">紧急研判</a>
			        </dd>
                </c:if>
                <!-- 三级 -->
            </dl>
        </div>
        <!--合成研判二级 -->
    </div>
    <!--合成研判一级-->
</div>
