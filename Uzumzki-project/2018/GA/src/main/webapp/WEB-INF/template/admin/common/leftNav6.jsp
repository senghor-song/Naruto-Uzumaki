<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!--左侧导航-->
<div class="mainLeft" id="sidebarNav">
    <!--布控一级-->
    <div class="TabContentItem">
        <!--布控二级 -->
        <div class="CateItem curr">
            <dl>
                <dt>
                    <a href="javascript:void(0)">
                        <i class="iconfont">&#xe65b;</i>
                        <span class="text-name">布控人员管理</span>
                        <em class="arrow_down"></em>
                    </a>
                </dt>
                <!-- 三级 -->
		    	<dd class="curr">
					<i class="icon"></i> 
					<a href="/admin/monitor/list.shtml" class="text-name">布控列表</a>
				</dd>
				<!-- 三级 -->
		        <dd>
		            <i class="icon"></i>
		            <a href="/admin/monitor/add.shtml" class="text-name">申请布控</a>
		        </dd>
                <!-- 三级 -->
            </dl>
        </div>
        <!--布控二级 -->
    </div>
    <!--预警一级-->
    <!--布控预警一级-->
    <div class="TabContentItem">
        <!--布控预警二级 -->
        <div class="CateItem">
            <dl>
                <dt>
                    <a href="javascript:void(0)">
                        <i class="iconfont">&#xe65b;</i>
                        <span class="text-name">布控人员预警</span>
                        <em class="arrow_right"></em>
                    </a>
                </dt>
		        <dd>
		            <i class="icon"></i>
		            <a href="/admin/monitorAlarm/list.shtml" class="text-name">布控预警列表</a>
		        </dd>
                <!-- 三级 -->
            </dl>
        </div>
        <!--布控预警二级 -->
    </div>
    <!--布控预警一级-->
</div>
