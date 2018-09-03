<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="" id="item8">
        <div class="item8-wrap">
            <span class="item8-close"><a href="javascript:;">关闭</a></span>
            <div class="publicbg-title">1.封面图</div>
            <div class="publicbg-row image1">
            	<c:if test="${!empty headimg}">
	                <div class="publicbg-box"><img class="publicbg-img" src="${headimg}"></div>
            	</c:if>
            </div>
            <div class="publicbg-title">2.小区图</div>
            <div class="publicbg-row image2">
            	<c:forEach items="${estateUrls}" var="image">
                	<div class="publicbg-box"><img class="publicbg-img" src="${image.path}"></div>
            	</c:forEach>
            </div>
            <div class="publicbg-title">3.房型图</div>
            <div class="publicbg-row image3">
            	<c:forEach items="${houseTypeUrls}" var="image">
                	<div class="publicbg-box"><img class="publicbg-img" src="${image.path}"></div>
            	</c:forEach>
            </div>
            <div class="publicbg-title">4.室内图</div>
            <div class="publicbg-row image3">
            	<c:forEach items="${indoorUrls}" var="image">
                	<div class="publicbg-box"><img class="publicbg-img" src="${image.path}"></div>
            	</c:forEach>
            </div>
        </div>
    </div>
    <style>
        #item8 .item8-wrap{ padding-left: 50px;}
        #item8 .item8-close{ color: #ffffff; position: absolute; top:35px; right:35px;}
        #item8 .item8-close a{ color: #ffffff;}
        #item8 .item8-close a:after{ color: #ffffff;}
        .publicbg-title{ color:#000000; font-size: 20px; line-height: 24px; margin: 10px 0; }
        .publicbg-title input[type=text]{ padding: 0 10px; margin-left: 10px; width: 200px;}
        .publicbg-row:after{ content: " "; height: 0; overflow: hidden; clear: both; float: none; display: block;}
       .publicbg-box{ width:150px; height: 150px; position: relative; float: left; margin: 0 20px 20px 0;}
       .publicbg-img{ background-size: cover; background-repeat: no-repeat; background-position: center; position: absolute; width: 100%; height: 100%; top:0; left:0;}
    </style>
