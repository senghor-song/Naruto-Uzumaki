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
 * @Description: U房网接口功能代码
 * @author 宋高俊  
 * @date 2018年7月16日 上午9:36:27 
 */ 
public class UfangUtil {
	public static void main(String[] args) {
		HTTPSTrustManager.allowAllSSL();//信任所有证书
		//登录前先访问页面获取Cookie，建立连接
		Map<String, String> map = HttpUtils.sendGetHtml("http://esf.ufang.com/login?refer=%2F", "utf-8");
		//建立连接后去登录
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("LoginForm[username]", "13360063236");
		parameters.put("LoginForm[password]", "123456");
		parameters.put("LoginForm[rememberMe]", "0");
		parameters.put("yt0", "");
		Map<String, String> loginmap = login("http://esf.ufang.com/login?refer=%2F", parameters, map.get("cookie"));
		System.out.println(loginmap.get("html"));
//		//获取登录成功后的cookie去发布房源
//		Map<String, String> parameters2 = new HashMap<String, String>();
//		parameters2.put("HouseForm[title]", "深圳龙华民治地铁口两房一厅2");
//		parameters2.put("HouseForm[name]", "新怡美丽家园");
//		parameters2.put("HouseForm[address]", "博罗县博罗工业大道与远望大道交汇处");
//		parameters2.put("HouseForm[price]", "500");
//		parameters2.put("HouseForm[mright]", "个人产权");
//		parameters2.put("HouseForm[room]", "2");
//		parameters2.put("HouseForm[hall]", "1");
//		parameters2.put("HouseForm[toilet]", "1");
//		parameters2.put("HouseForm[kitchen]", "1");
//		parameters2.put("HouseForm[balcony]", "1");
//		parameters2.put("HouseForm[building_area]", "100");
//		parameters2.put("HouseForm[living_area]", "");
//		parameters2.put("HouseForm[floor]", "10");
//		parameters2.put("HouseForm[total_floor]", "11");
//		parameters2.put("HouseForm[forward]", "南北");
//		parameters2.put("HouseForm[purpose]", "");
//		parameters2.put("HouseForm[purpose]", "住宅");
//		parameters2.put("HouseForm[create_time]", "");
//		parameters2.put("HouseForm[purpose_class]", "");
//		parameters2.put("HouseForm[purpose_class]", "普通住宅");
//		parameters2.put("HouseForm[build_class]", "");
//		parameters2.put("HouseForm[build_class]", "板楼");
//		parameters2.put("HouseForm[house_way]", "");
//		parameters2.put("HouseForm[house_way]", "平层");
//		parameters2.put("HouseForm[valid_date]", "60");
//		parameters2.put("HouseForm[fitment]", "");
//		parameters2.put("HouseForm[fitment]", "豪华装修");
//		parameters2.put("HouseForm[look_house_info]", "");
//		parameters2.put("HouseForm[look_house_info]", "随时看房");
//		parameters2.put("HouseForm[base_service]", "");
//		parameters2.put("HouseForm[traffic_info]", "");
//		parameters2.put("HouseForm[around_condition]", "");
//		parameters2.put("HouseForm[description]", "");
//		parameters2.put("HouseForm[phone]", "");
//		parameters2.put("HouseForm[paid]", "5");
//		parameters2.put("HouseForm[aid]", "48");
//		parameters2.put("HouseForm[pic]", "");
//		String result = enter("http://esf.ufang.com/ajax/houseAdd", parameters2, loginmap.get("cookie"));
//		System.out.println(result);
	}

	/**
	 * @Description:  U房网登录
	 * @author 宋高俊
	 * @date 2018年7月16日 上午11:16:26
	 */
	public static Map<String, String> login(String url, Map<String, String> parameters, String cookie) {
		Map<String, String> map = new HashMap<String, String>();
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
			in = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}
			map.put("html", result);
			CookieStore store = manager.getCookieStore();
			// 得到所有的 URI
			List<URI> uris = store.getURIs();
			for (URI ur : uris) {
				// 筛选需要的 URI
				// 得到属于这个 URI 的所有 Cookie
				List<HttpCookie> cookies = store.get(ur);
				for (HttpCookie coo : cookies) {
					cookie += coo.toString() + ";";
				}
			}
			map.put("cookie", cookie);
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
		return map;
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
