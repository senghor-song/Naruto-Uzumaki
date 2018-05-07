<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<title><ruiec:title/></title>
    <link rel="stylesheet" href="${base }/resources/admin/show/css/common.css">
    <link rel="stylesheet" href="${base }/resources/admin/show/css/jquery.gridster.min.css">
    <link rel="stylesheet" href="${base }/resources/admin/show/css/style.css">
    <script src="${base }/resources/admin/show/js/jquery-1.9.1.min.js"></script>
    <script src="${base }/resources/admin/show/js/jquery.gridster.min.js"></script>
    <script src="${base }/resources/admin/show/js/highcharts.js"></script>
</head>
<body class="page_body">
<div class="gridster">
    <ul>
        <!-- 第一行 -->
        <li data-row="1" data-col="1" data-sizex="2" data-sizey="3">
            <div class="table-items">
                <div class="account_zdr">
                    <p class="label-title">今日预警数量</p>
                    <p class="label-value">325</p>
                </div>
            </div>
        </li>
        <li data-row="1" data-col="3" data-sizex="8" data-sizey="6">
            <div class="Chart_items" type="spline-column" axis_x="2017-10-01,2017-10-02,2017-10-03,2017-10-04,2017-10-05,2017-10-06,2017-10-07,2017-10-08,2017-10-9,2017-10-10,2017-10-11,2017-10-12,2017-10-13,2017-10-14,2017-10-15,2017-10-16,2017-10-17,2017-10-18,2017-10-19,2017-10-20,2017-10-21,2017-10-22,2017-10-23,2017-10-24,2017-10-25,2017-10-26,2017-10-27,2017-10-28,2017-10-29,2017-10-30" data="[{name: '当日预警数量',type:'spline',color:'#f7a35c',zIndex:2,marker:{lineWidth: 2,color:'#f7a35c'},data: [243, 251, 243, 152, 154,147, 235, 141, 156,174, 169,171,279,199,175, 267, 177, 198, 274, 170, 268, 201, 210, 189, 178, 189, 183, 174, 142, 230]},{name: '当日离市人员数量',zIndex:1,'color':'#7cb5ec',type:'column',data: [343, 351, 403, 252, 314,447, 315, 401, 316, 301, 458,409, 814,582, 685, 494,381, 409, 474, 393, 424, 321, 295, 375, 471,315,341,442,330,330] }]" ></div>
        </li>
        <li data-row="4" data-col="1" data-sizex="2" data-sizey="3">
            <div class="table-items">
                <div class="account_zdr">
                    <p class="label-title">今日离市人员数量</p>
                    <p class="label-value">58,325</p>
                </div>
            </div>
        </li>
        <!-- 第一行 -->

        <!-- 第一行 -->
        <li data-row="7" data-col="1" data-sizex="5" data-sizey="5">
            <div class="Chart_items" type="pie" data="[['火车票出行信息',2410],['长途汽车客运站出行',1081],['自驾车出行信息',981],['航班出行信息',1029]]"  title="出行方式占比"></div>
        </li>
        <li data-row="7" data-col="6" data-sizex="5" data-sizey="5">
            <div class="Chart_items" type="column-bar1" axis_x="国成市公安局,国成市公安局骏骏区分局,国成市公安局云云区分局,国成市公安局志勇阳区分局,志勇西县公安局,华山县公安局,竹园县公安局,国成市公安局国县公安局,魏兵市公安局" axis_y='[{"name":"预警", "data":[{"color":"#f7a35c","y":258},{"color":"#f7a35c","y":180},{"color":"#f7a35c","y":150},{"color":"#f7a35c","y":245},{"color":"#f7a35c","y":156},{"color":"#f7a35c","y":243},{"color":"#f7a35c","y":154},{"color":"#f7a35c","y":120},{"color":"#f7a35c","y":56}]},{"name":"离市人员", "data":[{"color":"#434348","y":1258},{"color":"#434348","y":1180},{"color":"#434348","y":1150},{"color":"#434348","y":845},{"color":"#434348","y":756},{"color":"#434348","y":843},{"color":"#434348","y":954},{"color":"#434348","y":820},{"color":"#434348","y":756}]}]'  title="各分县局预警/离市人员数量对比"></div>
        </li>
        <!-- 第一行 -->

    </ul>
</div>
<script src="${base }/resources/admin/show/js/charts.js"></script>
</body>
</html>