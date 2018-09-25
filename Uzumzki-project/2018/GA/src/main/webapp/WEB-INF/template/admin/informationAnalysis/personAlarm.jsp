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
        <li data-row="1" data-col="3" data-sizex="8" data-sizey="5">
            <div class="Chart_items" type="spline" data="[{name: '当日预警数量',data: [143, 151, 243, 152, 154,147, 135, 141, 156, 174, 169,171,179,199,175, 167, 177, 198, 174, 170, 211, 158, 209, 214, 182, 185, 194, 181, 209, 174, 193, 224, 221, 195, 175, 171,215,241, 268, 301, 210, 189, 178, 189, 183, 174, 142, 230, 130] }]" ></div>
        </li>
        <li data-row="4" data-col="1" data-sizex="2" data-sizey="2">
            <div class="table-items">
                <div class="account_zdr">
                    <p class="label-title">系统预警总数</p>
                    <p class="label-value">58,325</p>
                </div>
            </div>
        </li>
        <!-- 第一行 -->

        <!-- 第一行 -->
        <li data-row="6" data-col="1" data-sizex="5" data-sizey="5">
            <div class="Chart_items" type="pie" data="[['XX市进站口信息',3410],['进站信息', 581],['人脸识别', 1181],['火车购票信息', 181], ['航班出行信息',29],['客运站进站信息',308]]"  title="轨迹信息趋势占比"></div>
        </li>
        <li data-row="6" data-col="6" data-sizex="5" data-sizey="5">
            <div class="Chart_items" type="column-bar1" axis_x="国成市公安局,国成市公安局骏骏区分局,国成市公安局云云区分局,国成市公安局志勇阳区分局,志勇西县公安局,华山县公安局,竹园县公安局,国成市公安局国县公安局,魏兵市公安局" axis_y='[{"name":"", "data":[{"color":"#90ed7d","y":258},{"color":"#f7a35c","y":180},{"color":"#8085e9","y":150},{"color":"#f15c80","y":245},{"color":"#e4d354","y":156},{"color":"#2b908f","y":243},{"color":"#f45b5b","y":154},{"color":"#91e8e1","y":120},{"color":"#136cc3","y":56}]}]'  title="各分县局预警数量"></div>
        </li>
        <!-- 第一行 -->

    </ul>
</div>
<script src="${base }/resources/admin/show/js/charts.js"></script>
</body>
</html>