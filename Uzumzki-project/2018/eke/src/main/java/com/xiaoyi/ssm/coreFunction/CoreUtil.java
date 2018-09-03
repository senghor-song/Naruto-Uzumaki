package com.xiaoyi.ssm.coreFunction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.xiaoyi.ssm.util.HttpUtils;
import com.xiaoyi.ssm.util.Utils;

import net.sf.json.JSONObject;

/**  
 * @Description: 执行调用接口的代码
 * @author 宋高俊  
 * @date 2018年7月21日 上午10:15:41 
 */ 
public class CoreUtil {
	
	/**  
	 * @Description: 判断账号密码登录是否成功
	 * @author 宋高俊  
	 * @param account 账户
	 * @param encryptedPassword 加密密码
	 * @param webid 网站ID
	 * @date 2018年7月21日 上午10:17:43 
	 */ 
	public static boolean loginFlag(String account, String encryptedPassword, String webid) {
		if (StringUtils.isBlank(account) || StringUtils.isBlank(encryptedPassword) || StringUtils.isBlank(webid)) {
			return false;
		}

		Map<String, String> loginParameters = new HashMap<String, String>();
		if ("101".equals(webid)) {
			//房天下
			loginParameters.put("Uid", account);
			loginParameters.put("Pwd", encryptedPassword);
			loginParameters.put("Service", "esf-agent-web");
			Map<String, String> map = FangTianXiaUtil.login("https://passport.fang.com/login.api", loginParameters, "");
			String login = map.get("html");
			//判断返回的json是否包含成功标识
			if (login.indexOf("Success") != -1) {
				return true;
			}
		}else if ("102".equals(webid)) {
			//安居客
			loginParameters.put("signExpiresTime", "1800000");
			loginParameters.put("sid", "broker");
			loginParameters.put("url", "aHR0cDovL3d3dy5hbmp1a2UuY29t");
			loginParameters.put("history", "");
			loginParameters.put("password", Utils.BaseEncode(encryptedPassword));
			loginParameters.put("telphone", account);
			loginParameters.put("seccodetxt", "");
			loginParameters.put("telcode", "");
			loginParameters.put("verifyType", "sms");
			loginParameters.put("bcb41ccdc4363c6848a1d760f26c28a0", "7e9f3b4647316b0463a7eb72273e2422");
			loginParameters.put("se45088a1", "e84badbe93e08f70b11ff251b9bff2ef%7Cze1NdrvoKiRgdUQCyaND4A%3D%3Dae%3D%3D");
			Map<String, String> map = AnJuKeUtil.login("http://vip.anjuke.com/login", loginParameters);
			String login = map.get("html");
			//判断返回的json是否包含成功标识
			if (login.indexOf("登录成功") != -1) {
				return true;
			}
		}else if ("103".equals(webid)) {
			//58同城(三网合一)
			
		}else if ("104".equals(webid)) {
			//赶集网(三网合一)
			
		}else if ("105".equals(webid)) {
			/**
			 * 备注：由于新浪二手房服务器可能记录了我们的session,导致后续测试账户登录时,会提示已登录,则需要先访问退出登录接口,后继续测试登录账户
			 */
			//新浪二手房
			HttpUtils.sendGetHtml("http://j.esf.leju.com/ucenter/logout/", "utf-8"); 
			List<String> keys = LeJuUtil.sendGet("http://j.esf.leju.com/ucenter/login");
			loginParameters.put("username", account);
			loginParameters.put("password", encryptedPassword);
			loginParameters.put("ckey", keys.get(0));
			loginParameters.put("imgcode", "");
			Map<String, String> map = LeJuUtil.sendPost("http://j.esf.leju.com/ucenter/login?curcity=sz", loginParameters);
			String login = map.get("html");
			JSONObject jsonObject = JSONObject.fromObject(login);
			//判断返回的json是否包含成功标识
			if ("1".equals(jsonObject.get("status").toString())) {
				return true;
			}
		}else if ("106".equals(webid)) {
			//百度糯米
			
		}else if ("107".equals(webid)) {
			//百姓网
			
		}else if ("108".equals(webid)) {
			//深圳房信网
			
		}else if ("109".equals(webid)) {
			//U房网
			//登录前先访问页面获取Cookie，建立连接
			Map<String, String> map = HttpUtils.sendGetHtml("http://esf.ufang.com/login?refer=%2F", "utf-8");
			//建立连接后去登录
			loginParameters.put("LoginForm[username]", account);
			loginParameters.put("LoginForm[password]", encryptedPassword);
			loginParameters.put("LoginForm[rememberMe]", "0");
			loginParameters.put("yt0", "");
			Map<String, String> loginmap = UfangUtil.login("http://esf.ufang.com/login?refer=%2F", loginParameters, map.get("cookie"));
			//判断返回的json是否包含成功标识
			if (loginmap.get("html").indexOf(account) != -1) {
				return true;
			}
		}else if ("110".equals(webid)) {
			//联盟找房网
			
		}else if ("111".equals(webid)) {
			//赶集网(免费端口)
			
		}else if ("112".equals(webid)) {
			//58同城(免费端口)
			
		}else if ("113".equals(webid)) {
			//搜狐焦点二手房
			loginParameters.put("passport", account);
			loginParameters.put("password", Utils.md5Password(encryptedPassword));
			loginParameters.put("channel", "5");
			loginParameters.put("auto", "1");
			Map<String, String> map = SouHuJiaoDianUtil.login("http://login.focus.cn/login/loginByPassport", loginParameters, "");
			//判断返回的json是否包含成功标识
			if (map.get("html").indexOf("成功") != -1) {
				return true;
			}
		}else if ("114".equals(webid)) {
			//城市房产
			
		}else if ("115".equals(webid)) {
			//快点8
			
		}else if ("116".equals(webid)) {
			//去114网
			
		}else if ("117".equals(webid)) {
			//今题网
			
		}else if ("118".equals(webid)) {
			//搜房网
			
		}else if ("119".equals(webid)) {
			//楼盘网
			
		}else if ("120".equals(webid)) {
			//易登网
			
		}else if ("121".equals(webid)) {
			//列表网
			
		}else if ("122".equals(webid)) {
			//第一时间房源网
			
		}else if ("123".equals(webid)) {
			//个人房源网
			
		}else if ("124".equals(webid)) {
			//赶场网
			
		}else if ("125".equals(webid)) {
			//城际分类网
			
		}
		return false;
	}
}
