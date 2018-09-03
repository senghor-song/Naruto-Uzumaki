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
import com.xiaoyi.ssm.util.Utils;

/**  
 * @Description: 搜房网接口功能代码(发布需修改标题)
 * @author 宋高俊  
 * @date 2018年7月18日 上午11:00:17 
 */ 
public class SouFangUtil {
	public static void main(String[] args) {
		Map<String, String> map = HttpUtils.sendGetHtml("http://agent.sofang.com/majorLogin?type=2", "utf-8");
		String html = map.get("html");

		String token = "";
		token = html.substring(html.indexOf("name=\"crtoken\"") + 22);
		token = token.substring(0, token.indexOf("\""));

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("_token", token);
		parameters.put("userName", "13360063236");
		parameters.put("password", Utils.md5Password("123456"));
		String cookie = login("http://agent.sofang.com/auth/login", parameters, map.get("cookie"));
		
		
		Map<String, String> parameters2 = new HashMap<String, String>();
		parameters2.put("_token", token);
		parameters2.put("communityId", "252165");
		parameters2.put("houseType1", "3");
		parameters2.put("longitude", "114.043899");
		parameters2.put("latitude", "22.627422");
		parameters2.put("provinceId", "13");
		parameters2.put("cityId", "118");
		parameters2.put("cityareaId", "1163");
		parameters2.put("businessAreaId", "2515");
		parameters2.put("housingInspectionNum", "");
		parameters2.put("name", "吉祥楼（民治）");
		parameters2.put("address", "龙华新区深圳龙华民治万众城家居旁1234");
		parameters2.put("area", "100");
		parameters2.put("price2", "300");
		parameters2.put("priceUnit", "2");
		parameters2.put("roomId", "");
		parameters2.put("roomStr", "2_2_2_2");
		parameters2.put("houseRoom", "2");
		parameters2.put("houseType2", "301");
		parameters2.put("fitment", "3");
		parameters2.put("faceTo", "1");
		parameters2.put("currentFloor", "1");
		parameters2.put("totalFloor", "10");
		parameters2.put("firstPay", "20");
		parameters2.put("ownership", "3");
		parameters2.put("buildYear", "2006");
		parameters2.put("equipment[]", "1");
		parameters2.put("tagId", "13|14|15|16|124|123");
		parameters2.put("diyTag", "");
		parameters2.put("title", "龙华新区深圳龙华民治万众城家居旁789");
		parameters2.put("describe", "<p>龙华新区深圳龙华民治万众城家居旁（即沙吓村）龙华新区深圳龙华民治万众城家居旁（即沙吓村）龙华新区深圳龙华民治万众城家居旁（即沙吓村）</p><p><br></p>");
		parameters2.put("leyout", "[]");
		parameters2.put("indoor", "[{\"img\":\"/house/sale/2018_07/18/111718_702_F55L.jpg\",\"note\":\"\",\"type\":\"10\"}]");
		parameters2.put("trafficimg", "[]");
		parameters2.put("peripheral", "[]");
		parameters2.put("exterior", "[]");
		parameters2.put("titleimg", "[{\"img\":\"/house/sale/2018_07/18/111725_239_49TM.jpg\",\"type\":\"9\",\"note\":\"\",\"tag\":\"2\"}]");
		parameters2.put("deleteImgId", "");
		parameters2.put("linkman", "");
		parameters2.put("linkmobile", "");
		String result = enter("http://agent.sofang.com/submitsale", parameters2, cookie);
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
