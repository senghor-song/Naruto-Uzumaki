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
 * @Description: 城市房产(发布价格不能过于接近，否则会被认为是同一个房源)
 * @author 宋高俊  
 * @date 2018年7月18日 下午7:04:21 
 */ 
public class ChengShiFangChanUtil {
	public static void main(String[] args) {
		HTTPSTrustManager.allowAllSSL();//信任所有证书

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("login[uid]", "13923734655");
		parameters.put("login[pwd]", "eg123456");
		parameters.put("login[phone]", "");
		parameters.put("imgcode", "");
		parameters.put("login[pinCode]", "");
		parameters.put("login_type", "password");
		String cookie = login("http://sz.upload.cityhouse.cn/user/ajaxLogin.html?fromurl=", parameters, "");

		Map<String, String> parameters2 = new HashMap<String, String>();

		parameters2.put("booking[dealcode]", "");
		parameters2.put("booking[id]", "");
		parameters2.put("booking[housenewid]", "");
		parameters2.put("validatorType", "houseTrade");
		parameters2.put("booking[originid]", "");
		parameters2.put("booking[proptype]", "11");
		parameters2.put("booking[dist_code]", "FT");
		parameters2.put("booking[ha_name]", "深圳中心·天元");
		parameters2.put("booking[street_name]", "彩田路");
		parameters2.put("booking[stno]", "102");
		parameters2.put("booking[br]", "2");
		parameters2.put("booking[lr]", "2");
		parameters2.put("booking[ba]", "1");
		parameters2.put("booking[cr]", "1");
		parameters2.put("booking[bldgarea]", "120");
		parameters2.put("booking[price]", "400");
		parameters2.put("booking[u]", "万元");
		parameters2.put("booking[floor]", "2");
		parameters2.put("booking[bheight]", "10");
		parameters2.put("booking[buildyear]", "2008");
		parameters2.put("booking[face]", "1");
		parameters2.put("booking[intdeco]", "0");
		parameters2.put("booking[propRight]", "11");
		parameters2.put("booking[goods][]", "煤气/天然气");
		parameters2.put("booking[headline]", "深圳1000万200123");
		parameters2.put("booking[note]", "<span style=\"white-space:nowrap;\"><span style=\"white-space:nowrap;\">深圳中心·天元2室500万超值精品业主抛售</span><span style=\"white-space:nowrap;\">深圳中心·天元2室500万超值精品业主抛售</span><span style=\"white-space:nowrap;\">深圳中心·天元2室500万超值精品业主抛售</span></span>");
		parameters2.put("booking[contact]", "ucvjjhbyusxgvzkb");
		parameters2.put("booking[tel]", "13923734655");
		parameters2.put("booking[email]", "");
		String result = enter("http://sz.upload.cityhouse.cn/backend.php/booking/tradeSave", parameters2, cookie);
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
