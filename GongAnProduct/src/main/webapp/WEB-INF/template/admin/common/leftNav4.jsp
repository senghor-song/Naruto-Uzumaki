<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--左侧导航-->
<div class="mainLeft" id="sidebarNav">
<!--情报分析一级-->
    <div class="TabContentItem">
        <!-- 二级 -->
        <div class="CateItem curr">
            <dl>
                <dt>
                    <a href="javascript:void(0)">
                        <i class="iconfont">&#xe71c;</i>
                        <span class="text-name">情报分析</span>
                        <em class="arrow_down"></em>
                    </a>
                </dt>
                
                <!-- 三级 -->
                <dd class="curr">
                    <i class="icon"></i>
                    <a href="/admin/informationAnalysis/dateCollect.shtml" class="text-name curr" target="main-iframe2">数据资源汇聚情况</a>
                </dd>
                <!-- 三级 -->
                <dd>
                    <i class="icon"></i>
                    <a href="/admin/informationAnalysis/leaveTendency.shtml" class="text-name" target="main-iframe2">重点人员预警分析</a>
                </dd>
                <!-- 三级 -->
                <dd>
                    <i class="icon"></i>
                    <a href="/admin/informationAnalysis/personAlarm.shtml" class="text-name" target="main-iframe2">离堰人员综合分析</a>
                </dd>
                <!-- 三级 -->
                <dd>
                    <i class="icon"></i>
                    <a href="/admin/informationAnalysis/personRelevance.shtml" class="text-name" target="main-iframe2">重点人员关联分析</a>
                </dd>
            </dl>
        </div>
        <!-- 二级 -->
    </div>
    <!--情报分析一级-->
</div>
