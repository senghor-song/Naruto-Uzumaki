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
 * @Description: 楼盘网接口功能代码
 * @author 宋高俊
 * @date 2018年7月17日 下午7:40:53
 */
public class LouPanUtil {
	public static void main(String[] args) {
		HTTPSTrustManager.allowAllSSL();// 信任所有证书

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("sign_in[logoType]", "0");
		parameters.put("sign_in[account]", "13923734655");
		parameters.put("sign_in[passwd]", "eg123456");
		parameters.put("sign_in[checkcode]", "");
		parameters.put("sign_in[smscode]", "");
		parameters.put("sign_in[remember]", "1");
		parameters.put("sign_in[token]", "c2a1d019ce86bbb0b3c3d4e32484f27d");
		String cookie = login("http://user2.loupan.com/session/ajax_sign_in/ ", parameters, "");
		// List<Data> list = new ArrayList<Data>();
		// Data data = new Data();
		// data.put("", "");
		// list.add(data);

		Map<String, String> parameters2 = new HashMap<String, String>();

		parameters2.put("md5", "a47253fee727e1d2a8975e967fbe3f7e");
		parameters2.put("user_id", "14149145");
		parameters2.put("community_id", "217444");
		parameters2.put("community", "美丽365花园");
		parameters2.put("bedroom", "2");
		parameters2.put("livingroom", "1");
		parameters2.put("bathroom", "1");
		parameters2.put("floor", "1");
		parameters2.put("floor_total", "2");
		parameters2.put("area", "100");
		parameters2.put("orientation", "东");
		parameters2.put("total_price", "500");
		parameters2.put("decoration", "毛坯");
		parameters2.put("property_type", "个人产权");
		parameters2.put("housing_type", "");
		parameters2.put("description", "<p></p><p><strong>房源优势：美丽365花园</strong><br/><br/><br/><strong>房屋介绍：</strong><br/><br/><br/><strong>社区配套：</strong><br/><br/><br/><strong>服务自荐：</strong><br/><br/><br/></p><p></p>");
		parameters2.put("city_id", "36");
		parameters2.put("name", "美丽365花123456789");
		parameters2.put("use_area", "98");
		parameters2.put("linkman", "王小姐");
		parameters2.put("phone", "13923734655");
		String result = enter("http://user2.loupan.com/home/ajax_input_esf_data/", parameters2, cookie);
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
			String full_url = url + "&" + params;
			// 创建URL对象
			URL connURL = new URL(url);
			// 打开URL连接
			HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
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
			// String full_url = url + "?" + params;
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
