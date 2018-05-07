<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--左侧导航-->
<div class="mainLeft" id="sidebarNav">
    <!--系统查询-->
    <div class="TabContentItem">
        <!-- 二级 -->
        <div class="CateItem curr">
            <dl>
                <dt>
                    <a href="javascript:void(0)">
                        <i class="iconfont">&#xe694;</i>
                        <span class="text-name">系统查询</span>
                        <em class="arrow_down"></em>
                    </a>
                </dt>

                <!-- 三级 -->
                <dd class="curr">
                    <i class="icon"></i>
                    <a href="/admin/systemSelect/controlPersonList.shtml" class="text-name">人员查询</a>
                </dd>
                <!-- 三级 -->
                <dd>
                    <i class="icon"></i>
                    <a href="/admin/systemSelect/controlPersonAlarmList.shtml" class="text-name">预警信息查询</a>
                </dd>
                 <dd>
                    <i class="icon"></i>
                    <a href="/admin/systemSelect/leavePersonList.shtml" class="text-name">离市查询</a>
                </dd>
                <!-- 三级 -->
            </dl>
        </div><!-- 重点人员 -->
        <!-- 二级 -->
    </div>
    <!--系统操作日志-->
</div>
