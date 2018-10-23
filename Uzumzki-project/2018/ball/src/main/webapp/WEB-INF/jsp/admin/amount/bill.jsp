<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>对帐</title>
    <style>
        @page{
            size: auto;
            margin:0mm;   
        }
        .bill-col{
            width: 24%;
            display: inline-block;
            vertical-align: text-top;
            box-sizing: border-box;
            padding-left: 40px;
        }
        html body{
            min-height: 100%;
            line-height: 1.8;
            padding: 0;
            margin: 0;
            font-size: 14px;
        }
        .bill-fd{
            position: absolute;
            bottom: 0px;
            width: 100%;
        }
        li{
            list-style: none;
        }
        ul{
            padding: 0;
        }
        hr{
            margin: 0;
        }
        th{
            font-weight: normal;
        }
        th,td{
            width: 25%;
        }
    </style>
</head>
<body>

    <div class="bill-hd" >
        <div class="bill-col" >交易日期：xxx</div>
        <div class="bill-col" ></div>
        <div class="bill-col" ></div>
        <div class="bill-col" >对账时间：xxx</div>
    </div>
    <hr/>
    <div class="bill-row" >
        <div class="bill-col" >系统对账风评：正常/异常</div>
        <div class="bill-col" >
            <ul>
                <li>提现笔数：xx</li>
                <li>提现总额：xxxx</li>
                <li>基本户全天入账：xxxx</li>
            </ul>
        </div>
        <div class="bill-col" >
            <ul>
                <li>新人额度提现：xxxx</li>
                <li>新人额度补贴：xxx</li>
                <li>新机构额度提现：123.45</li>
                <li>新机构额度补贴：123.45</li>
            </ul>
        </div>
        <div class="bill-col" >
            <ul>
                <li>基本户余额(对账时)：123.45</li>
                <li>基本户当前余额：123.45</li>
                <li>运营户余额(对账时)：123.45</li>
                <li>运营户当前余额：123.45</li>
            </ul>
        </div>
    </div>
    <div class="bill-row" >
        <div class="bill-col" >当日盈亏：123.45</div>
    </div>
    <div class="bill-row" >
        <div class="bill-col" >转入对公账户：123.45</div>
        <div class="bill-col" >平台费：20.00</div>
    </div>
    
    <div class="bill-fd" >
        <hr/>
        <div class="bill-col" >会计：</div>
        <div class="bill-col" >财务：</div>
        <div class="bill-col" >审批：</div>
        <div class="bill-col" ></div>
    </div>

</body>
</html>