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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoyi.ssm.util.HTTPSTrustManager;
import com.xiaoyi.ssm.util.HttpUtils;

/**
 * @Description: 百姓网接口功能代码
 * @author 宋高俊
 * @date 2018年7月16日 上午9:34:00
 */
public class BaixingUtil {
	public static void main(String[] args) {
		HTTPSTrustManager.allowAllSSL();//信任所有证书
		Map<String, String> map = HttpUtils.sendGetHtml("http://www.baixing.com/oz/login", "utf-8");
		String html = map.get("html");
		String token = "";// 验证参数
		String flag = "";
		String flagName = "";// 验证标识name
		String flagValue = "";// 验证标识value
		token = html.substring(html.indexOf("name=\"token\"") + 20);
		flag = token.substring(token.indexOf("name=\"") + 6);
		token = token.substring(0, token.indexOf("title") - 2);
		System.out.println(token);
		flagName = flag.substring(0, flag.indexOf("value") - 2);
		System.out.println(flagName);
		flagValue = flag.substring(flag.indexOf("value") + 7);
		flagValue = flagValue.substring(0, flagValue.indexOf("title") - 2);
		System.out.println(flagValue);

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("identity", "小百姓05091807297");
		parameters.put("password", "13923734655");
		parameters.put("token", token);
		parameters.put(flagName, flagValue);
		String cookie = login("http://www.baixing.com/oz/login?redirect=http%3A%2F%2Fwww.baixing.com%2Fw", parameters, map.get("cookie"));
		cookie = login("http://www.baixing.com/w", parameters, cookie);
		cookie = login("http://www.baixing.com/w/posts", parameters, cookie);
		Map<String, String> map2 = HttpUtils.sendGetHtml("http://www.baixing.com/w/posts?src=topbar_user", cookie);
		System.out.println(map2.get("html"));
	}

	/**
	 * @Description: 百姓网登录登录（验证参数已获取，Cookie获取缺失登录失败）
	 * @author 宋高俊
	 * @date 2018年7月16日 上午11:16:26
	 */
	public static String login(String url, Map<String, String> parameters, String cookie) {
		CookieManager manager = new CookieManager();
		// 设置cookie策略，只接受与你对话服务器的cookie，而不接收Internet上其它服务器发送的cookie
//		manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
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
			String full_url = url + "&" + params;
			// 创建URL对象
			URL connURL = new URL(url);
			// 打开URL连接
			HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
			HttpURLConnection.setFollowRedirects(true);
			// 设置通用属性
			httpConn.setRequestProperty("Connection", "keep-alive");
			httpConn.setRequestProperty("Content-Length", "144");
			httpConn.setRequestProperty("Cache-Control", "max-age=0");
			httpConn.setRequestProperty("Origin", "http://www.baixing.com");
			httpConn.setRequestProperty("Upgrade-Insecure-Requests", "1");
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.26 Safari/537.36 Core/1.63.5680.400 QQBrowser/10.2.1852.400");
			httpConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			httpConn.setRequestProperty("Referer", "http://www.baixing.com/oz/login");
			httpConn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
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
//			System.out.println(result);
			HttpCookie res = null;
			CookieStore store = manager.getCookieStore();
			// 得到所有的 URI
			List<URI> uris = store.getURIs();
			cookie = "";
			for (URI ur : uris) {
				// 筛选需要的 URI
				// 得到属于这个 URI 的所有 Cookie
				List<HttpCookie> cookies = store.get(ur);
				for (HttpCookie coo : cookies) {
					res = coo;
					cookie += coo.toString() + ";";
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
