<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/WebRelease/test/anjukeLogin" method="post">
		安居客账号：<input type="text" name="username"><br>
		安居客密码：<input type="password" name="password">
		<input type="submit">成功
	</form><br>
	<form action="/WebRelease/test/anjukeEnter" method="post">
		安居客房源标题：<input type="text" name="housetitle"><br>
		安居客房源描述：<input type="text" name="house_desc"><br>
		安居客房源价格：<input type="text" name="price"><br>
		安居客房源城市：<input type="text" name="district">
		<input type="submit">未完成
	</form><br>
	<form action="/WebRelease/test/anjukeEnter" method="post" onsubmit="return enpassword2()">
		加密模数：<input type="hidden" name="modulus" value="#{pubkey}"><br>
		乐居账号：<input type="text" name="username" value="13923734655"><br>
		乐居密码：<input type="password" name="password" id="lejupassword2" value="123456"><br>
		乐居房源标题：<input type="text" name="housetitle" value="两房一厅"><br>
		乐居房源描述：<input type="text" name="house_desc" value="两房一厅"><br>
		乐居房源价格：<input type="text" name="price" value="800"><br>
		乐居房源面积：<input type="text" name="buildingarea" value="200">
		<input type="submit">成功
	</form><br>
	<form action="/WebRelease/test/anjukeEnter" method="post" onsubmit="return enpassword3()">
		<input type="text" id="modulus2" value="978C0A92D2173439707498F0944AA476B1B62595877DD6FA87F6E2AC6DCB3D0BF0B82857439C99B5091192BC134889DFF60C562EC54EFBA4FF2F9D55ADBCCEA4A2FBA80CB398ED501280A007C83AF30C3D1A142D6133C63012B90AB26AC60C898FB66EDC3192C3EC4FF66925A64003B72496099F4F09A9FB72A2CF9E4D770C41">
		房天下账号：<input type="text" name="username" value="soufun-s59284995"><br>
		房天下密码：<input type="text" name="password" id="fangpassword" value="13923734655"><br>
		房天下房源标题：<input type="text" name="housetitle" value="美丽365美丽365两房一厅"><br>
		房天下房源描述：<input type="text" name="house_desc" value="美丽365美丽365两房一厅"><br>
		房天下房源价格：<input type="text" name="price" value="800"><br>
		房天下房源面积：<input type="text" name="buildingarea" value="200">
		<input type="submit">成功
	</form><br>
</body>
<script src="<%=path%>/Static/Js/enter/jquery-1.11.3.min.js"></script><%-- 
<script src="<%=path%>/Static/Js/enter/BigInt.js"></script>
<script src="<%=path%>/Static/Js/enter/Barrett.js"></script>
<script src="<%=path%>/Static/Js/enter/RSA.js"></script> --%>
<script src="<%=path%>/Static/Js/rsa.js"></script>
<script type="text/javascript">
	function enpassword(){
		setMaxDigits(129);
		var key_to_encode = new RSAKeyPair("10001", "", $("#modulus").val());
		var pwdRtn = encryptedString(key_to_encode, $("#lejupassword").val());
		console.log(pwdRtn);
		$("#lejupassword").val(pwdRtn);
		return true;
	}
	function enpassword2(){
		setMaxDigits(129);
		var key_to_encode = new RSAKeyPair("10001", "", $("#modulus").val());
		var pwdRtn = encryptedString(key_to_encode, $("#lejupassword2").val());
		console.log(pwdRtn);
		$("#lejupassword2").val(pwdRtn);
		return true;
	}
	function enpassword3(){
		setMaxDigits(129);
		var key_to_encode = new FangRSAKeyPair("10001", "", $("#modulus2").val());
		var pwdRtn = FangEncryptedString(key_to_encode, $("#fangpassword").val());
		console.log(pwdRtn);
		$("#fangpassword").val(pwdRtn);
		return true;
	}
</script>
</html>
