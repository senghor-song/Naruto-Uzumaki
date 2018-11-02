<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>审核</title>
	<link rel="icon" href="/WebBackAPI/admin/static/image/logo.png" type="image/x-icon"/>
	<link rel="stylesheet" href="abc.css">
</head>
<style>
	@media print {
	    button {
	        display: none !important;
	    }
	    @page {
	        size: 210mm 290mm​;
	        /* auto is the initial value */
	        /* margin: 0mm auto; */
	        /* this affects the margin in the printer settings */
	    }
	}


    .withdraw{
        width: 1015px;
        font-size: 13px;
        padding: 10px;
        line-height: 19px;
        color: rgb(50, 50, 50);
        /* color: red; */
    }
    .withdraw li{
        list-style: none;
    }
    .withdraw ul{
        margin: 0px;
        padding: 0px;
    }
    .withdraw-tit{
        font-size: 17px;
        font-weight: normal;
        text-decoration-line: underline;
        /* line-height: 20px; */
        margin:10px 0;
    }
    .withdraw-header{
        width: 100%;
        border-bottom: 1px solid #797979;
    }
    .withdraw-tabel1{
        width: 100%;
        margin-bottom: 30px;
        
    }
    .withdraw-tabel1 td{
        width: 33%;
        vertical-align: top;
    }
    .withdraw-tabel1 td:last-child{
        font-size: 20px;
        width: 370px!important;
        line-height: 30px;
    }
    .withdraw-tabel2{
        width: 1015px;
        border-collapse: collapse;
        text-align: center;
        font-size: 12px;
    }
    .withdraw-tabel2 th{
        font-weight: normal;
        border: 1px solid #797979;
    }
    .withdraw-tabel2 td{
        border: 1px solid #797979;
        width: 100px;
        padding: 0px;
    }
    .withdraw-tabel2 tr>td:first-child{
        width: 150px;
    } 
    .withdraw-tabel3{
        padding-top: 7px;
        width: 100%;
        margin-bottom: 20px;
        font-size: 12px;
        /* border: 1px solid; */
    }
    .withdraw-tabel3 h5{
        font-size: 14px;
        font-weight: normal;
        margin: 0px;
    }
    .withdraw-tabel3 td{
        vertical-align: text-top;
        width: 50%;
    }
    .withdraw-print{
        border-top: 1px dashed #797979;
        padding: 15px 0;
    }
</style>
<body>
	<button onclick="iframePrint()">打印</button>
    <div class="withdraw" >
        <table class="withdraw-header">
            <tr>
                <td>会计:</td>
                <td>财务:</td>
                <td>审核:</td>
                <td style="width: 230px;font-size: 20px;">转账金额:${amount.amount }</td>
            </tr>
        </table>
        <table class="withdraw-tabel1">
            <tr>
                <td>
	                <div>申请时间：${amount.createTime }</div>
	                <div>城　　市：${amount.city }</div>
	                <div>场　　馆：${amount.venue }</div>
	                <div>申请提现：${amount.managerType }</div>
	                <div>实名认证：${amount.realname }</div>
                </td>
                <td>
                     <div>提现金额：${amount.amountSum }</div>
                     <div>手续费率：${amount.amountRate }</div>
                     <div>手 续 费：${amount.amountFee }</div>
                </td>
                <td style="width:370px">
                    <div>
	                    <div>回款银行：${amount.amountbank }</div>
	                    <div>回款账户：${amount.amountaccount }</div>
	                    <div>回款账号：${amount.amountaccountnumber }</div>
                    </div>
                </td>
            </tr>
        </table>
        <table class="withdraw-tabel2">
			<tr>
				<th width="15%">订单编号</th>
				<th width="18%">支付时间</th>
				<th width="14%">会员</th>
				<th width="10%">订场日期</th>
				<th width="14%">场地</th>
				<th width="11%">时间</th>
				<th width="8%">支付方式</th>
				<th width="10%">金额</th>
			</tr>
			<c:forEach var="list" items="${amount.orderList}">
				<tr>
					<td>${list.orderno}</td>
					<td>${list.modifytime}</td>
					<td>${list.member}</td>
					<td>${list.orderdate}</td>
					<td>${list.field}</td>
					<td>${list.times}</td>
					<td>${list.paytype}</td>
					<td>${list.price}</td>
				</tr>
			</c:forEach>
		</table>
        <h4 class="withdraw-tit">订场：${amount.orderAmount}</h4>
        <table class="withdraw-tabel2">
			<tr>
				<th width="15%">散拼编号</th>
				<th width="18%">支付时间</th>
				<th width="14%">会员</th>
				<th width="10%">订场日期</th>
				<th width="14%">场地</th>
				<th width="11%">时间</th>
				<th width="8%">支付方式</th>
				<th width="10%">金额</th>
			</tr>
			<c:forEach var="list" items="${amount.combineList}">
				<tr>
					<td>${list.combineno}</td>
					<td>${list.createtimetime}</td>
					<td>${list.member}</td>
					<td>${list.combinedate}</td>
					<td>${list.fieldName}</td>
					<td>${list.times}</td>
					<td>${list.paytype}</td>
					<td>${list.price}</td>
				</tr>
			</c:forEach>
		</table>
        <h4 class="withdraw-tit">拼场：${amount.combineAmount}</h4>
        <table class="withdraw-tabel2">
			<tr>
				<th width="15%">培训编号</th>
				<th width="20%">支付时间</th>
				<th width="20%">会员</th>
				<th width="27%">课程信息</th>
				<th width="8%">支付方式</th>
				<th width="10%">金额</th>
			</tr>
			<c:forEach var="list" items="${amount.trainList}">
				<tr>
					<td>${list.trainno}</td>
					<td>${list.createtime}</td>
					<td>${list.member}</td>
					<td>${list.content}</td>
					<td>${list.paytype}</td>
					<td>${list.price}</td>
				</tr>
			</c:forEach>
		</table>
        <h4 class="withdraw-tit">培训：${amount.trainAmount}</h4>
        <table class="withdraw-tabel3">
            <tr>
                <td >
                    <h5>提现日志</h5>
                    <div>
						<c:forEach var="list" items="${amount.amountLogList}">
                       		<div>${list.createtime } ${list.content }</div>
                        </c:forEach>
                    </div>
                </td>
            </tr>
            <tr>
                <td >
                    <h5>历史提现</h5>
                    <div>
						<c:forEach var="list" items="${amount.historyLogList}">
                       		<div>${list.createtime } ${list.name } ${list.type } ${list.amount }</div>
                        </c:forEach>
                    </div>
                </td>
            </tr>
        </table>
        <div class="withdraw-print">
            打印：${amount.printDate}
        </div>
    </div>
</body>

<script src="/WebBackAPI/admin/static/plugins/layui/layui.all.js"></script>
<script>
    function iframePrint(){    //添加打印事件
	    window.print();
    }
</script>
</html>