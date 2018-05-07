<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页统计</title>
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
                        <p class="label-title">重点人数量</p>
                        <p class="label-value">5,494</p>
                    </div>
                </div>
            </li>
            <li data-row="1" data-col="3" data-sizex="4" data-sizey="6">
                <div class="Chart_items" type="column-bar1" axis_x='特警(反恐)支队,张湾区公安分局,丹江口市公安局,郧阳区公安分局,郧阳区公安分局,茅箭区公安分局,郧西县公安局,房县公安局,竹山县公安局,东岳公安分局,竹溪县公安局,武当山特区公安局,经济开发区分局' axis_y='[{"name":"", "data":[{"color":"#7cb5ec","y":654},{"color":"#434348","y":628},{"color":"#90ed7d","y":608},{"color":"#f7a35c","y":580},{"color":"#8085e9","y":520},{"color":"#f15c80","y":414},{"color":"#e4d354","y":401},{"color":"#2b908f","y":380},{"color":"#f45b5b","y":350},{"color":"#91e8e1","y":261},{"color":"#136cc3","y":195},{"color":"#96c3c0","y":168}]}]' title="重点人管控单位"></div>
            </li>
            <li data-row="1" data-col="7" data-sizex="4" data-sizey="6">
                <div class="Chart_items" type="pie-circle" data="[['重点上访人员',3410],['精神病人', 581], ['扬言个人极端',29],['其他关注人员',308],['涉稳人员', 453],['涉毒人员',546]]"  title="重点人类别"></div>
            </li>
            <li data-row="4" data-col="1" data-sizex="2" data-sizey="3">
                <div class="table-items">
                    <div class="account_zdr">
                        <p class="label-title">关注人数量</p>
                        <p class="label-value">11,745</p>
                    </div>
                </div>
            </li>
            <!-- 第一行 -->

            <!-- 第二行 -->
            <li data-row="5" data-col="1" data-sizex="2" data-sizey="3">
                <div class="table-items">
                    <div class="account_zdr">
                        <p class="label-title">今日预警次数</p>
                        <p class="label-value">328</p>
                    </div>
                </div>
            </li>
            <li data-row="5" data-col="3" data-sizex="4" data-sizey="6">
                <div class="Chart_items" type="pie" data="[['重点上访人员',110],['其他关注人员',58],['犯事前科人员',58],['涉稳人员', 43],['涉毒人员',76]]"  title="预警人员类别"></div>
            </li>
            <li data-row="5" data-col="7" data-sizex="4" data-sizey="6">
                <div class="Chart_items" type="column-bar" axis_x='18-27,28-37,38-47,48-57,58-67,68+' axis_y='[{"name":"男", "data":[20,50,100,58,50,30]},{"name":"女","data":[15,45,80,128,54,40]}]' title="预警人员性别年龄"></div>
            </li>
            <li data-row="9" data-col="1" data-sizex="2" data-sizey="3">
                <div class="table-items">
                    <div class="account_zdr">
                        <p class="label-title">近七天预警次数</p>
                        <p class="label-value">2,518</p>
                    </div>
                </div>
            </li>
            <!-- 第二行 -->

            <!--
            <li data-row="7" data-col="1" data-sizex="10" data-sizey="3">
                <div id="containers" style="width:500px; height:400px"></div>
            </li>
            <li data-row="10" data-col="1" data-sizex="5" data-sizey="4">

            </li>
            <li data-row="10" data-col="1" data-sizex="5" data-sizey="4">

            </li>
            -->
        </ul>
    </div>
    <script src="${base }/resources/admin/show/js/charts.js"></script>
</body>
</html>