<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style type="text/css">
	.contextMenuDialog .listrow{border: none}
</style>
<div class="contextMenuDialog" id="item2">
        <div class="card-body">
            <div class="row">
                <div class="col-lg-9">
                    <div class="row">
                        <div class="col-lg-4 listrow">
                            <span class="nti">来源：</span>
                            <span class="ncon">${newsCust.source }</span>
                        </div>
                        <div class="col-lg-4 listrow">
                            <span class="nti">标题：</span>
                            <span class="ncon">${newsCust.title }</span>
                        </div>
                        <div class="col-lg-4 listrow">
                            <span class="nti">展示时间：</span>
                            <span class="ncon"><fmt:formatDate value="${newsCust.showdate }" pattern="yyyy-MM-dd"/></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4 listrow">
                            <span class="nti">内容：</span>
                            <span class="ncon"><a href="/WebRelease/admin/newsCust/detailsNewsHead?id=${newsCust.id}" target="_blank">点击查看详情</a></span>
                        </div>
                        <div class="col-lg-4 listrow">
                            <span class="nti">平台：</span>
                            <span class="ncon">客户</span>
                        </div>
                        <div class="col-lg-4 listrow">
                            <span class="nti">关注：</span>
                            <span class="ncon">
                                120
                            </span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4 listrow">
                            <span class="nti">状态：</span>
                            <span class="ncon">${newsCust.status }</span>
                        </div>
                        <div class="col-lg-4 listrow">
                            <span class="nti">是否顶置：</span>
                            <span class="ncon">是</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 text-center">
                    <img src="${newsCust.coverpath}" style="height: 124px; border:1px solid #eee;" onerror="javascript:this.src='/WebRelease/admin/static/image/error.png'">
                </div>
            </div>
        </div>
    </div>