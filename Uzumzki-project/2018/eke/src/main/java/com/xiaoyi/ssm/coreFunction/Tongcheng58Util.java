package com.xiaoyi.ssm.coreFunction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.xiaoyi.ssm.util.HttpUtils;
import com.xiaoyi.ssm.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description: 58同城接口功能代码
 * @author 宋高俊
 * @date 2018年7月16日 上午9:31:49
 */
public class Tongcheng58Util {

	/**
	 * @Description: 根据名称查询小区
	 * @author 宋高俊
	 * @date 2018年7月24日 下午3:32:59
	 */
	public static JSONArray getSelectByName(String name) {
		// 登录前先访问页面获取Cookie，建立连接
		Map<String, String> htmlmap = HttpUtils.sendGetHtml("http://suggest.58.com/searchsuggest_6.do?inputbox=" + name + "&cityid=4&catid=8&type=1&callback=jQuery18103246753391368342_1532428658130&cityid=4&catid=12&_=1532428666434", "", "utf-8");
		String jsonString = StringUtil.unicodeToString(htmlmap.get("html").substring(htmlmap.get("html").indexOf("(") + 1, htmlmap.get("html").lastIndexOf("") - 1));
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray jsonArray = jsonObject.getJSONArray("w");
		return jsonArray;
	}
	
	/**  
	 * @Description: 根据名称获取小区均价
	 * @author 宋高俊  
	 * @date 2018年7月24日 下午3:32:59 
	 */ 
	public static String getEstateAvgPrice(String name) {
		if (!StringUtils.isBlank(name)) {
			Map<String, String> map = HttpUtils.sendGetHtml( "http://sz.58.com/xiaoqu/?key="+name, "utf-8");
			String html = map.get("html");
			if (html.indexOf("money") != -1) {
				String money = html.substring(html.indexOf("money") + 7);
				money = money.substring(0,money.indexOf("<"));
				return money;
			}
		}
		return "暂无报价";
	}
	
	public static void main(String[] args) {
		System.out.println(getSelectByName("美丽365").toString());
//		getEstateAvgPrice("365");
//		 HTTPSTrustManager.allowAllSSL();//信任所有证书
//		 //登录前先访问页面获取Cookie，建立连接
//		 // Map<String, String> map =
//		 HttpUtils.sendGetHtml("https://passport.58.com/login/pc/dologin", "utf-8");
//		 //建立连接后去登录
//		 Map<String, String> parameters = new HashMap<String, String>();
//		 parameters.put("source", "passport");
//		 //eke123456
//		 parameters.put("password", "637330d6553b3ec7444fbf93389fa982564f2b84d3a1d0c4ad5891d4259da92b1e2882ed8930ccbdb13fe3b9c24d07f8441c069bb9991a39cbf9e8d46c85cb5f26ae1966473c51a35e4c62cfafb5496514ca51f98ca170b75bcf037b96e129271302b3fa7066e6967c487bd6016615997619b74b748ed99ee881ccb28a177913");
//		 parameters.put("timesign", "");
//		 parameters.put("isremember", "false");
//		 parameters.put("callback", "successFun");
//		 parameters.put("yzmstate", "");
//		 parameters.put("fingerprint", "9920902C1EE5A45E043CD65182158DCFE1EC322D805B645D_111");
//		 parameters.put("path", "http://post.58.com/4?PGTID=0d50000c-0000-4c0d-8f90-766cf7b14aaf&ClickID=5&pts=1531805867322");
//		 parameters.put("finger2",  "zh-CN|24|1|4|1920_1080|1920_1080|-480|1|1|1|undefined|1|unknown|Win32|unknown|3|false|false|false|false|false|0_false_false|d41d8cd98f00b204e9800998ecf8427e|d9148e3b61841e2dc63cad49b22af8b4");
//		 parameters.put("username", "13360063236");
//		 parameters.put("validcode", "");
//		 parameters.put("vcodekey", "");
//		 parameters.put("btnSubmit", "登录中...");
//		 String cookie = login("http://passport.58.com/login/pc/dologin", parameters, "");
//		
//		 String result = login("http://my.58.com/persondata?r=0.750686020899637 ", parameters, cookie);
//		
//		 System.out.println(cookie);
	}

	/**
	 * @Description: 58同城登录接口免费版
	 * @author 宋高俊
	 * @date 2018年7月17日 上午11:50:56
	 */
	public static String login(String url, Map<String, String> parameters, String cookie) {
		CookieManager manager = new CookieManager();
		// 设置cookie策略，只接受与你对话服务器的cookie，而不接收Internet上其它服务器发送的cookie
		// manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
		manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(manager);
		String result = "";// 返回的结果
		BufferedReader in = null;// 读取响应输入流
		PrintWriter out = null;
		StringBuffer sb = new StringBuffer();// 处理请求参数
		String params = "";// 编码之后的参数
		try {
			// 编码请求参数
			if (parameters.size() > 0) {
				if (parameters.size() == 1) {
					for (String name : parameters.keySet()) {
						sb.append(name).append("=").append(URLEncoder.encode(parameters.get(name), "UTF-8"));
					}
					params = sb.toString();
				} else {
					for (String name : parameters.keySet()) {
						sb.append(name).append("=").append(URLEncoder.encode(parameters.get(name), "UTF-8")).append("&");
					}
					String temp_params = sb.toString();
					params = temp_params.substring(0, temp_params.length() - 1);
				}
			}
			String full_url = url + "?" + params;
			// 创建URL对象
			URL connURL = new URL(url);
			// 打开URL连接
			HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
			// 设置通用属性
			// httpConn.setRequestProperty("Connection", "keep-alive");
			// httpConn.setRequestProperty("Content-Length", "144");
			// httpConn.setRequestProperty("Cache-Control", "max-age=0");
			// httpConn.setRequestProperty("Origin", "http://www.baixing.com");
			// httpConn.setRequestProperty("Upgrade-Insecure-Requests", "1");
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpConn.setRequestProperty("Cookie", cookie);

			// 设置POST方式
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			// 获取HttpURLConnection对象对应的输出流
			out = new PrintWriter(httpConn.getOutputStream());
			// 发送请求参数
			out.write(params);
			// flush输出流的缓冲
			out.flush();

			// 定义BufferedReader输入流来读取URL的响应，设置编码方式
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
			CookieStore store = manager.getCookieStore();
			// 得到所有的 URI
			List<URI> uris = store.getURIs();
			for (URI ur : uris) {
				// 筛选需要的 URI
				// 得到属于这个 URI 的所有 Cookie
				List<HttpCookie> cookies = store.get(ur);
				for (HttpCookie coo : cookies) {
					cookie += coo.toString() + ";";
					// System.out.println(cookie);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return cookie;
	}
}
