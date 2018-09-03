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

/**  
 * @Description: 快点8接口功能代码
 * @author 宋高俊  
 * @date 2018年7月18日 下午6:28:29 
 */ 
public class KuaiDian8Util {
	public static void main(String[] args) {
		HTTPSTrustManager.allowAllSSL();//信任所有证书

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("__EVENTTARGET", "");
		parameters.put("__EVENTARGUMENT", "");
		parameters.put("__VIEWSTATE", "/wEPDwUKMTUyMTY4OTA3NGRkmKNPva7+ifmLZ8vi3ggpFNTkA70=");
		parameters.put("__VIEWSTATEGENERATOR", "EF8DE4A4");
		parameters.put("ctl00$viewtop1$keywords", "请输入搜索关键词");
		parameters.put("ctl00$ContentPlaceHolder1$TextBox1", "http://shenzhen.qd8.com.cn/");
		parameters.put("ctl00$ContentPlaceHolder1$Textbox3", "evergreensakura7");
		parameters.put("ctl00$ContentPlaceHolder1$Textbox4", "eg123456");
		parameters.put("ctl00$ContentPlaceHolder1$Button1", "登 录");
		String cookie = login("http://shenzhen.qd8.com.cn/users_login.aspx", parameters, "");

		Map<String, String> parameters2 = new HashMap<String, String>();

		parameters2.put("sel_city", "30");
		parameters2.put("sel_district", "246");
		parameters2.put("sel_road", "8558");
		parameters2.put("sfenlei", "42");
		parameters2.put("xxtitle", "民治龙华优质房源23456");
		parameters2.put("xiaoqu", "优质房");
		parameters2.put("shi", "1");
		parameters2.put("ting", "1");
		parameters2.put("wei", "1");
		parameters2.put("niandai", "2004");
		parameters2.put("louceng", "南");
		parameters2.put("mianji", "100");
		parameters2.put("zhuangxiu", "简单装");
		parameters2.put("jiawei", "100");
		parameters2.put("smcont", "优质房");
		parameters2.put("tese", "优质房");
		parameters2.put("peitao", "优质房");
		parameters2.put("miaoshu", "优质房源优质");
		parameters2.put("username", "");
		parameters2.put("telphone", "13923734655");
		parameters2.put("mobilephone", "13923734655");
		parameters2.put("qq", "");
		parameters2.put("email", "");
		parameters2.put("address", "深圳市南山区白石洲中信红");
		parameters2.put("Hidden2", "");
		parameters2.put("Text3", "114.024896, 22.546456");
		parameters2.put("xxpwd", "30");
		parameters2.put("Hidden1", "_fangwucs");
		parameters2.put("TextBox1", "");
		parameters2.put("TextBox2", "");
		String result = enter("http://shenzhen.qd8.com.cn/users/submit/Default.aspx?pid=13&id=1670&sid=0", parameters2, cookie);
		System.out.println(result);
	}
	
	/**
	 * @Description: 赶场网登录
	 * @author 宋高俊
	 * @date 2018年7月16日 上午11:16:26
	 */
	public static String login(String url, Map<String, String> parameters,
			String cookie) {
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
						sb.append(name).append("=").append(URLEncoder.encode(parameters.get(name),"UTF-8"));
					}
					params = sb.toString();
				} else {
					for (String name : parameters.keySet()) {
						sb.append(name).append("=").append(URLEncoder.encode(parameters.get(name),"UTF-8")).append("&");
					}
					String temp_params = sb.toString();
					params = temp_params.substring(0, temp_params.length() - 1);
				}
			}
			String full_url = url + "&" + params;
			// 创建URL对象
			URL connURL = new URL(url);
			// 打开URL连接
			HttpURLConnection httpConn = (HttpURLConnection) connURL .openConnection();
			HttpURLConnection.setFollowRedirects(true);
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
			in = new BufferedReader(new InputStreamReader( httpConn.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}
//			System.out.println(result);
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

	/**
	 * @Description: 发布房源
	 * @author 宋高俊
	 * @date 2018年7月16日 下午5:18:20
	 */
	public static String enter(String url, Map<String, String> parameters,
			String cookie) {
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
						sb.append(name)
								.append("=")
								.append(URLEncoder.encode(parameters.get(name),
										"UTF-8"));
					}
					params = sb.toString();
				} else {
					for (String name : parameters.keySet()) {
						sb.append(name)
								.append("=")
								.append(URLEncoder.encode(parameters.get(name),
										"UTF-8")).append("&");
					}
					String temp_params = sb.toString();
					params = temp_params.substring(0, temp_params.length() - 1);
				}
			}
			// String full_url = url + "?" + params;
			// 创建URL对象
			URL connURL = new URL(url);
			// 打开URL连接
			HttpURLConnection httpConn = (HttpURLConnection) connURL
					.openConnection();
			HttpURLConnection.setFollowRedirects(true);
			// 设置通用属性
			httpConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
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
			in = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream(), "UTF-8"));
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
