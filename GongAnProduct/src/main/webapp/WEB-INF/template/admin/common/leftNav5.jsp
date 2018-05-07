<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--左侧导航-->
	<div class="mainLeft" id="sidebarNav">
	<!--用户管理-->
    <div class="TabContentItem">
        <!-- 二级 -->
        <div class="CateItem curr">
            <dl>
                <dt>
                    <a href="javascript:void(0)">
                        <i class="iconfont">&#xe744;</i>
                        <span class="text-name">权限管理</span>
                        <em class="arrow_down"></em>
                    </a>
                </dt>
                <!-- 三级 -->
                <dd	class="curr">
                    <i class="icon"></i>
                    <a href="/admin/user/list.shtml" class="text-name">用户管理</a>
                </dd>
                <!-- 三级 -->
                <dd>
                    <i class="icon"></i>
                    <a href="/admin/manager/list.shtml" class="text-name">管理员管理</a>
                </dd>
                <!-- 三级 -->
                <dd>
                    <i class="icon"></i>
                    <a href="/admin/unit/list.shtml" class="text-name">单位管理</a>
                </dd>
                <!-- 三级 -->
            </dl>
        </div>
        <!-- 二级 -->
        <!-- 二级 -->
        <div class="CateItem">
            <dl>
                <dt>
                    <a href="javascript:void(0)">
                        <i class="iconfont">&#xe744;</i>
                        <span class="text-name">字典库管理</span>
                        <em class="arrow_right"></em>
                    </a>
                </dt>
                <!-- 三级 -->
                <dd	>
                    <i class="icon"></i>
                    <a href="/admin/dictionaryType/list.shtml" class="text-name">字典类型</a>
                </dd>
                <!-- 三级 -->
                <dd>
                    <i class="icon"></i>
                    <a href="/admin/dictionary/list.shtml" class="text-name">字典数据列表</a>
                </dd>

            </dl>
        </div><!-- 重点人员 -->
        <!-- 二级 -->
        <!-- 二级 -->
        <div class="CateItem">
            <dl>
                <dt>
                    <a href="javascript:void(0)">
                        <i class="iconfont">&#xe61c;</i>
                        <span class="text-name">系统操作日志</span>
                        <em class="arrow_right"></em>
                    </a>
                </dt>

                <!-- 三级 -->
                <dd>
                    <i class="icon"></i>
                    <a href="/admin/loginLog/list.shtml" class="text-name">登录日志</a>
                </dd>
                <!-- 三级 -->
                <dd>
                    <i class="icon"></i>
                    <a href="/admin/operationLog/list.shtml" class="text-name">操作日志</a>
                </dd>
                <!-- 三级 -->
            </dl>
        </div><!-- 重点人员 -->
        <!-- 二级 -->
        <!-- 二级 -->
        <div class="CateItem">
            <dl>
                <dt>
                    <a href="javascript:void(0)">
                        <i class="iconfont">&#xe692;</i>
                        <span class="text-name">宣传管理</span>
                        <em class="arrow_right"></em>
                    </a>
                </dt>

                <!-- 三级 -->
                <dd>
                    <i class="icon"></i>
                    <a href="/admin/notice/list.shtml" class="text-name">公告列表</a>
                </dd>
                <!-- 三级 -->
                 <dd>
                    <i class="icon"></i>
                    <a href="/admin/banner/list.shtml" class="text-name">图片列表</a>
                </dd> 
                <!-- 三级 -->
            </dl>
        </div>
        <!-- 二级 -->
        <!-- 二级 -->
        <div class="CateItem">
            <dl>
                <dt>
                    <a href="javascript:void(0)">
                        <i class="iconfont">&#xe692;</i>
                        <span class="text-name">数据导入</span>
                        <em class="arrow_right"></em>
                    </a>
                </dt>
                <dd>
                    <i class="icon"></i>
                    <a href="/admin/db/list.shtml" class="text-name">数据源列表</a>
                </dd>
                <dd>
                    <i class="icon"></i>
                    <a href="/admin/dbShow/list.shtml" class="text-name">可导入表展示列表</a>
                </dd>
            </dl>
        </div>
        <!-- 二级 -->
    </div>
    <!--宣传管理-->
</div>
