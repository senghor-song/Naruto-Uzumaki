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
        <li data-row="1" data-col="1" data-sizex="2" data-sizey="2">
            <div class="table-items">
                <div class="account_zdr">
                    <p class="label-title">今日离市人员数量</p>
                    <p class="label-value">325</p>
                </div>
            </div>
        </li>
        <li data-row="1" data-col="3" data-sizex="8" data-sizey="4">
            <div class="Chart_items" type="spline" title="近20天离市人员趋势" data="[{name: '当日预警数量',data: [ 174,269,171,379,199,175, 167, 177, 243, 151, 243, 152, 154,147, 135, 141, 156, 185, 194,215] }]" ></div>
        </li>
        <li data-row="3" data-col="1" data-sizex="2" data-sizey="2">
            <div class="table-items">
                <div class="account_zdr">
                    <p class="label-title">近7天离市人员数量</p>
                    <p class="label-value">58,352</p>
                </div>
            </div>
        </li>
        <!-- 第一行 -->

        <li data-row="5" data-col="1" data-sizex="10" data-sizey="4">
            <h2 class="block-title">重点人同行分析（N）</h2>
            <div class="txfx_table">
                <table cellpadding="0" cellspacing="0" >
                    <colgroup>
                        <col width="50%" />
                        <col width="25%" />
                        <col width="25%" />
                    </colgroup>
                    <thead>
                        <tr>
                            <th>同行标识</th>
                            <th>出行日期时间</th>
                            <th>出行人数</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><span class="underline">请注意：2017年10月11日有两名以上，XX市籍关注人员从秦皇岛市到天津市</span></td>
                            <td>2017-10-11</td>
                            <td>3</td>
                        </tr>
                        <tr>
                            <td><span class="underline">请注意：2017年10月11日有两名以上，XX市籍重点上访人员从武汉市到北京市</span></td>
                            <td>2017-10-11</td>
                            <td>5</td>
                        </tr>
                        <tr>
                            <td><span class="underline">请注意：2017年10月11日有两名以上，XX市籍涉毒人员从西安市到天津市</span></td>
                            <td>2017-10-11</td>
                            <td>4</td>
                        </tr>
                        <tr>
                            <td><span class="underline">请注意：2017年10月11日有两名以上，XX市籍精神病人员从襄阳市到上海市</span></td>
                            <td>2017-10-11</td>
                            <td>3</td>
                        </tr>
                        <tr>
                            <td><span class="underline">请注意：2017年10月10日有两名以上，XX市籍刑事前科人员从孝感市到武汉市</span></td>
                            <td>2017-10-10</td>
                            <td>4</td>
                        </tr>
                        <tr>
                            <td><span class="underline">请注意：2017年10月10日有两名以上，XX市籍扬言个人极端人员从西安市到天津市</span></td>
                            <td>2017-10-10</td>
                            <td>5</td>
                        </tr>
                        <tr>
                            <td><span class="underline">请注意：2017年10月10日有两名以上，XX市籍关注人员从西安市到南京市</span></td>
                            <td>2017-10-10</td>
                            <td>4</td>
                        </tr>
                        <tr>
                            <td><span class="underline">请注意：2017年10月8日有两名以上，XX市籍关注人员从武汉市到深圳市</span></td>
                            <td>2017-10-8</td>
                            <td>3</td>
                        </tr>
                        <tr>
                            <td><span class="underline">请注意：2017年10月8日有两名，XX市籍关注人员从襄阳市到安徽市</span></td>
                            <td>2017-10-8</td>
                            <td>2</td>
                        </tr>
                        <tr>
                            <td><span class="underline">请注意：2017年10月7日有两名以上，XX市籍关注人员从仙桃市到广州市</span></td>
                            <td>2017-10-7</td>
                            <td>3</td>
                        </tr>
                        <tr>
                            <td><span class="underline">请注意：2017年10月7日有两名以上，XX市籍关注人员从天门市到南阳市</span></td>
                            <td>2017-10-7</td>
                            <td>3</td>
                        </tr>
                        <tr>
                            <td><span class="underline">请注意：2017年10月7日有两名以上，XX市籍关注人员从合肥市到保定市</span></td>
                            <td>2017-10-7</td>
                            <td>3</td>
                        </tr>
                        <tr>
                            <td><span class="underline">请注意：2017年10月7日有两名以上，XX市籍关注人员从天门市到石家庄市</span></td>
                            <td>2017-10-7</td>
                            <td>3</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </li>

        <!-- 第一行 -->
        <li data-row="9" data-col="1" data-sizex="5" data-sizey="4">
            <div class="Chart_items" type="column-bar1" axis_x="XX市-北京市,XX2市-南京市,XX3市-西安市,XX5市-广州市,XX6市-上海市,XX7市-深圳市,XX8市-武浩市,XX9市-重庆市,XX9市-新疆市,XX10市-昆明市" axis_y='[{"name":"", "data":[258,180,230,251,261,215,312,188,354,254]}]'  title="重点人到的重点市分布"></div>
        </li>
        <li data-row="9" data-col="6" data-sizex="5" data-sizey="4">
            <div class="Chart_items" type="column-bar1" axis_x="XX市-北京市,XX2市-石家庄,XX3市-天津市,XX5市-张家口,XX6市-承德,XX7市-唐山,XX8市-保定,XX9市-廊坊" axis_y='[{"name":"", "data":[{"color":"#f7a35c","y":1180},{"color":"#8085e9","y":850},{"color":"#f15c80","y":545},{"color":"#e4d354","y":456},{"color":"#136cc3","y":343},{"color":"#f45b5b","y":354},{"color":"#91e8e1","y":120},{"color":"#2b908f","y":56}]}]'  title="普口到达重点市分布"></div>
        </li>

    </ul>
</div>
<script src="${base }/resources/admin/show/js/charts.js"></script>
</body>
</html>