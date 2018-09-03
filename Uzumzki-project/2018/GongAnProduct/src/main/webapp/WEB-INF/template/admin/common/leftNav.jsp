<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--左侧导航-->
<div class="mainLeft" id="sidebarNav">
<!--人员一级-->
    <div class="TabContentItem">
        <!-- 二级 -->
        <div class="CateItem curr">
            <dl>
                <dt>
                    <a href="javascript:void(0)">
                        <i class="iconfont">&#xe71c;</i>
                        <span class="text-name">人员管理</span>
                        <em class="arrow_down"></em>
                    </a>
                </dt>
                
                <!-- 三级 -->
                <dd class="curr">
                    <i class="icon"></i>
                    <a href="/admin/controlPerson/list.shtml?personnelType=1" class="text-name">重点人员</a>
                </dd>
                <!-- 三级 -->
                <dd>
                    <i class="icon"></i>
                    <a href="/admin/controlPerson/list.shtml?personnelType=2" class="text-name">关注人员</a>
                </dd>
                <!-- 三级 -->
                <dd>
                    <i class="icon"></i>
                    <a href="/admin/controlPerson/list.shtml?personnelType=3" class="text-name">外地人员</a>
                </dd>
                <!-- 三级 -->
                <dd>
                    <i class="icon"></i>
                    <a href="/admin/controlPerson/add.shtml" class="text-name">新增人员</a>
                </dd>
            </dl>
        </div><!-- 重点人员 -->
        <!-- 二级 -->
    </div>
    <!--人员一级-->
</div>
