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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * @Description: 新浪二手房接口功能代码
 * @author 宋高俊
 * @date 2018年7月16日 上午9:26:17
 */
public class LeJuUtil {
	public static void main(String[] args) {
//		HTTPSTrustManager.allowAllSSL();// 信任所有证书
		
		//乐居测试接口
		List<String> keys = sendGet("http://j.esf.leju.com/ucenter/login");
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", "13360063236");
		parameters.put("password", "8b4a85958ed1c427e35cce6f1d6cf49cf46d45acf6bce921179265b49dc3e0ef8880dd2be1c391fa1be16bb8ba171757fd0592b4192e1790e3ed6ad5c14d8458bcb8e7455e87aa4c847914957c12a6a077850e867acfee8533bb166f4b0800d285c90555c870ca4c62250bc7617999d7b20ae0d39e53f449293b118920fe4cb8");
		parameters.put("ckey", keys.get(0));
		parameters.put("imgcode", "");
		Map<String, String> map = sendPost("http://j.esf.leju.com/ucenter/login?curcity=sz", parameters);
		JSONObject jsonObject = JSONObject.fromObject(map.get("html"));
		System.out.println(jsonObject.get("status"));
		
//		Map<String, String> parameters2 = new HashMap<String, String>();
//		parameters2.put("pub", "0");
//		parameters2.put("edithouse", "0");
//		parameters2.put("district", "龙华");
//		parameters2.put("block", "民治");
//		parameters2.put("communityname", "京基御景华城");
//		parameters2.put("housesite", "福田区福田华强南路与滨河路交汇处");
//		parameters2.put("sina_id", "384");
//		parameters2.put("communityPrice", "");
//		parameters2.put("avgprice", "57064");
//		parameters2.put("upPriceRange", "4.56512|100000");
//		parameters2.put("downPriceRange", "4.56512|100000");
//		parameters2.put("PriceLimit", "1|9999999.99");
//		parameters2.put("tradetype", "1");
//		parameters2.put("propername", "住宅");
//		parameters2.put("deleteData", "");
//		parameters2.put("tkdeleteData", "");
//		parameters2.put("check_code", "");
//		parameters2.put("housecode", "");
//		parameters2.put("price", "800");
//		parameters2.put("down", "1");
//		parameters2.put("downtype", "1");
//		parameters2.put("downrate", "30");
//		parameters2.put("downtax", "1");
//		parameters2.put("downremark", "");
//		parameters2.put("atype", "1");
//		parameters2.put("arate", "2");
//		parameters2.put("model_room", "2");
//		parameters2.put("model_hall", "2");
//		parameters2.put("model_toilet", "1");
//		parameters2.put("buildingarea", "80");
//		parameters2.put("propertype", "3");
//		parameters2.put("fitment", "1");
//		parameters2.put("direction", "2");
//		parameters2.put("floor", "-102");
//		parameters2.put("total_floor", "80");
//		parameters2.put("complete", "2005");
//		parameters2.put("property_rights", "0");
//		parameters2.put("house_fee", "");
//		parameters2.put("newtag", "");
//		parameters2.put("house_state", "0");
//		parameters2.put("look_time", "1");
//		parameters2.put("date[]", "6");
//		parameters2.put("date[]", "6");
//		parameters2.put("housetitle", "龙华民治365花园龙华民治365花园778899");
//		parameters2.put("house_desc", "龙华民治365花园两房一厅龙华民治365花园两房一厅龙华民治365花园两房一厅龙华民治365花园两房一厅龙华民治365花园两房一厅龙华民治365花园两房一厅龙华民治365花园两房一厅龙华民治365花园两房一厅");
//		parameters2.put("pixcover", "");
//		parameters2.put("pkey", "c203fc3508d8b8e9cec564188f1de659");
//		parameters2.put("sign", "77c15a34347030c43a81af8fbe2913f7");
//		parameters2.put("u", "3478307");
//		parameters2.put("citycode", "sz");
//		String result = HttpUtils.sendPost1("http://j.esf.leju.com/house/publishsubmit/?ajax=y", parameters2, cookie);
//		
//		
//		System.out.println(result);
	}
	
	/**  
	 * @Description: 乐居获取登录所需ckey
	 * @author 宋高俊  
	 * @date 2018年7月12日 下午2:25:58 
	 */ 
	public static List<String> sendGet(String url) {
		List<String> results = new ArrayList<String>();
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("curcity", "sz");
		String result = "";
		BufferedReader in = null;// 读取响应输入流
		StringBuffer sb = new StringBuffer();// 存储参数
		String params = "";// 编码之后的参数
		try {
			// 编码请求参数
			if (parameters.size() > 0) {
				if (parameters.size() == 1) {
					for (String name : parameters.keySet()) {
						sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8"));
					}
					params = sb.toString();
				} else {
					for (String name : parameters.keySet()) {
						sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8")).append("&");
					}
					String temp_params = sb.toString();
					params = temp_params.substring(0, temp_params.length() - 1);
				}
			}
			String full_url = url + "?" + params;
			// 创建URL对象
			java.net.URL connURL = new java.net.URL(full_url);
			// 打开URL连接
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
			// 设置通用属性
			httpConn.setRequestProperty("Accept", "*/*");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			// 建立实际的连接
			httpConn.connect();
			// 定义BufferedReader输入流来读取URL的响应,并设置编码方式
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}
			results.add(result.substring(result.indexOf("ckey")+13,result.indexOf("ckey")+53));
			results.add(result.substring(result.indexOf("pubkey")+15,result.indexOf("pubkey")+271));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return results;
	}
	
	/**  
	 * @Description: 安居客获取登录所需cookie
	 * @author 宋高俊  
	 * @date 2018年7月12日 下午2:25:58 
	 */ 
	public static String sendGet1(String url) {
		String cookieString = "";
		CookieManager manager = new CookieManager();
        //设置cookie策略，只接受与你对话服务器的cookie，而不接收Internet上其它服务器发送的cookie
//        manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
        manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
		CookieHandler.setDefault(manager);
		String result = "";
		BufferedReader in = null;// 读取响应输入流
		StringBuffer sb = new StringBuffer();// 存储参数
		String params = "";// 编码之后的参数
		try {
			// 创建URL对象
			URL connURL = new URL(url);
			// 打开URL连接
//			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
			// 设置通用属性
			httpConn.setRequestProperty("Accept", "*/*");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			// 建立实际的连接
			httpConn.connect();
			// 响应头部获取
//			Map<String, List<String>> headers = httpConn.getHeaderFields();
//			// 遍历所有的响应头字段
//			for (String key : headers.keySet()) {
//				System.out.println(key + "\t：\t" + headers.get(key));
//			}
			// 定义BufferedReader输入流来读取URL的响应,并设置编码方式
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}
			//请求头设置的cookie
			String cookieskey = "Set-Cookie";
			Map<String, List<String>> maps = httpConn.getHeaderFields();
			List<String> coolist = maps.get(cookieskey);
			Iterator<String> it = coolist.iterator();
			StringBuffer sbu = new StringBuffer();
			while(it.hasNext()){
				String next = it.next();
//				System.out.println(next);
				sbu.append(next + ";");
			}
			
//			System.out.println(sbu.toString());

			//请求相应的cookie
			CookieStore store = manager.getCookieStore();
			// 得到所有的 URI
			List<URI> uris = store.getURIs();
			for (URI ur : uris) {
				// 筛选需要的 URI
				// 得到属于这个 URI 的所有 Cookie
				List<HttpCookie> cookies = store.get(ur);
				for (HttpCookie coo : cookies) {
					cookieString += coo.toString() + ";";
				}
			}
//			System.out.println(cookieString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return cookieString;
	}
	
	/**
	 * 乐居网房源登录
	 * @param url
	 * @param parameters
	 * @return
	 */
	public static Map<String, String> sendPost(String url, Map<String, String> parameters) {
		Map<String, String> map = new HashMap<String, String>();
		String cookieString = "";
		CookieManager manager = new CookieManager();
        //设置cookie策略，只接受与你对话服务器的cookie，而不接收Internet上其它服务器发送的cookie
//        manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
        manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(manager);
		String result = "";// 返回的结果
		BufferedReader in = null;// 读取响应输入流
		PrintWriter out = null;
		StringBuffer sb = new StringBuffer();// 处理请求参数
		String params = "";// 编码之后的参数
		try {
			// 编码请求参数
			if (parameters.size() == 1) {
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8"));
				}
				params = sb.toString();
			} else {
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8")).append("&");
				}
				String temp_params = sb.toString();
				params = temp_params.substring(0, temp_params.length() - 1);
			}
			// 创建URL对象
			java.net.URL connURL = new java.net.URL(url);
			// 打开URL连接
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
			// 设置通用属性
			 httpConn.setRequestProperty("Accept", "*/*");
			 httpConn.setRequestProperty("Connection", "Keep-Alive");
			 httpConn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");

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
			map.put("html", result);
			HttpCookie res = null;
			CookieStore store = manager.getCookieStore();
			// 得到所有的 URI
			List<URI> uris = store.getURIs();
			for (URI ur : uris) {
				// 筛选需要的 URI
				// 得到属于这个 URI 的所有 Cookie
				List<HttpCookie> cookies = store.get(ur);
				for (HttpCookie coo : cookies) {
					res = coo;
					cookieString += coo.toString() + ";";
				}
			}
			map.put("cookie", cookieString);
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

}
