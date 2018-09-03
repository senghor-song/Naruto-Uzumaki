<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../../common/tag.jsp"%>
    
<input type="hidden" id="status" value="0"></input>
<div id="add_account_win">
    <ul class="form_body add_account_input">
<!--    		<li id="cityli" style="display:none">
        	<span class="addLeft">城市：</span>
        	<select id="cityType" class="xms-common-text iw160"></select>
        </li>
        <li id="webli" style="display:none">
        	<span class="addLeft">类型：</span>
        	<select id="webType" class="xms-common-text iw160"></select>
        </li>
 -->        <li>
        	<span class="addLeft">账号：</span>
        	<input id="loginName" type="text" class="xms-common-text iw160" value="${sessionScope.loginuser.tel}"/>
        </li>
        <li>
        	<span class="addLeft">密码：</span>
        	<input id="loginPwd" type="password" class="xms-common-text iw160" value="${sessionScope.loginuser.password}"/>
        </li>
        <!-- <li id="codeli" style="display:none">
	        <span class="addLeft">图片验证码：</span>
	        <input id="loginCode" type="text" class="xms-common-text iw60"/>
	        <img id="codeUrl" src="" width="90" height="28" class="pointer float-r"></img>
        </li>
        <li id="phoneli" style="display:none">
        	<div>
        		<span class="addLeft">手机验证码：</span>
		        <input id="loginPhoneCode" type="text" class="xms-common-text iw60"/>
	            <input id="getPhoneToken" class="btnClass btnDisabled" type="button" value="获取手机验证码" disabled="disabled" from="0" onclick="getPhoneCode()"></input>
        	</div>
            <div id="phoneCodeTip" class="col-red" style="line-height:20px"></div>
        </li> -->
    </ul>
    <ul class="account-tip">
    	<li id="swhyTip" class="col-419d31"></li>
   		<li id="anjukeAdd" class="col-f60"></li>
    	<li id="setAccount" class="col-f60">*:一个网站最多可以添加<span id="accountSum">3</span>个账号</li>
    	<li id="delTip" style="display:none" class="col-f60">*:绑定成功24小时之内不能删除</li>
    	<li id="setTip" style="display:none;" class="col-72c962"><span class="col-f60" style="display:inline-block;width:218px;line-height:18px">*:必须保证网站上的姓名或电话与易推房一致</span></li>
    </ul>
</div>
