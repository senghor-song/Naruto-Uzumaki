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

import com.xiaoyi.ssm.util.HttpUtils;

/**  
 * @Description: 去114网接口功能代码
 * @author 宋高俊  
 * @date 2018年7月18日 下午1:01:38 
 */ 
public class Qu114Util {
	public static void main(String[] args) {

		Map<String, String> map = HttpUtils.sendGetHtml("http://www.liebiao.com/login/", "utf-8");
		String html = map.get("html");
		
		String csrf = "";
		csrf = html.substring(html.indexOf("name=\"_csrf\"")+20);
		csrf = csrf.substring(0, csrf.indexOf(">")-1);
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("login_username", "13923734655");
		parameters.put("login_password", "eg123456");
		parameters.put("login_type", "normal");
		parameters.put("_csrf", csrf);
		parameters.put("auto_login", "false");
		String cookie = login("http://www.liebiao.com/login/checkncw", parameters, map.get("cookie"));
		
		System.out.println(cookie);
		
		Map<String, String> parameters2 = new HashMap<String, String>();
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		parameters2.put("", "");
		String result = enter("http://housing.jinti.com/aspx/aspx/Company_post_sell.aspx?areaid=444", parameters2, cookie);
		System.out.println(result);
	}
	
	/**
	 * @Description: 登录
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
//			httpConn.setRequestProperty("Connection", "keep-alive");
//			httpConn.setRequestProperty("Content-Length", "144");
//			httpConn.setRequestProperty("Cache-Control", "max-age=0");
//			httpConn.setRequestProperty("Origin", "http://www.baixing.com");
//			httpConn.setRequestProperty("Upgrade-Insecure-Requests", "1");
//			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//			httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.26 Safari/537.36 Core/1.63.5680.400 QQBrowser/10.2.1852.400");
//			httpConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//			httpConn.setRequestProperty("Referer", "http://www.baixing.com/oz/login?redirect=http%3A%2F%2Fwww.baixing.com%2Fw%2F");
//			httpConn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
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
//					System.out.println(cookie);
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
	
	/**  
	 * @Description: 发布房源
	 * @author 宋高俊  
	 * @date 2018年7月16日 下午5:18:20 
	 */ 
	public static String enter(String url, Map<String, String> parameters, String cookie) {
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
//			String full_url = url + "?" + params;
			// 创建URL对象
			URL connURL = new URL(url);
			// 打开URL连接
			HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
			HttpURLConnection.setFollowRedirects(true);
			// 设置通用属性
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
		return result;
	}
}
