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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.xiaoyi.ssm.util.HTTPSTrustManager;
import com.xiaoyi.ssm.util.HttpUtils;
import com.xiaoyi.ssm.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description: 安居客接口功能代码
 * @author 宋高俊
 * @date 2018年7月16日 上午9:24:15
 */
public class AnJuKeUtil {

	
	/**  
	 * @Description: 根据名称查询小区
	 * @author 宋高俊  
	 * @date 2018年7月24日 下午3:32:59 
	 */ 
	public static JSONArray getSelectByName(String name) {
		Map<String, String> map = HttpUtils.sendGetHtml( "https://shenzhen.anjuke.com/ajax/autocomplete/?callback=jQuery111307143346449175805_1532402529727&kw="+name+"&city_id=11&app_id=1&from=2&num=10&_=1532402529729", "utf-8");
		String jsonString = StringUtil.unicodeToString(map.get("html").substring(map.get("html").indexOf("(") + 1, map.get("html").lastIndexOf("") - 1));
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		return jsonArray;
	}
	
	/**  
	 * @Description: 根据名称获取小区均价
	 * @author 宋高俊  
	 * @date 2018年7月24日 下午3:32:59 
	 */ 
	public static String getEstateAvgPrice(String name) {
		if (!StringUtils.isBlank(name)) {
			Map<String, String> map = new HashMap<>();
			map = HttpUtils.sendGetHtml( "https://shenzhen.anjuke.com/community/?kw="+name+"&from=sugg_hot", "UTF-8");
			String html = map.get("html");
			if (html.indexOf("元/平米") != -1) {
				String money = html.substring(html.indexOf("元/平米") - 23);
				money = money.substring(money.indexOf(">") + 1);
				money = money.substring(0,money.indexOf("<"));
				return money;
			}
		}
		return "暂无报价";
	}
	public static void main(String[] args) {
		HTTPSTrustManager.allowAllSSL();// 信任所有证书
		getEstateAvgPrice("美丽365");
		// // 安居客测试接口
		// Map<String, String> parameters = new HashMap<String, String>();
		// parameters.put("signExpiresTime", "1800000");
		// parameters.put("sid", "broker");
		// parameters.put("url", "aHR0cDovL3d3dy5hbmp1a2UuY29t");
		// parameters.put("history", "");
		// parameters.put("password", Utils.BaseEncode("000000"));
		// parameters.put("telphone", "13923734655");
		// parameters.put("seccodetxt", "");
		// parameters.put("telcode", "");
		// parameters.put("verifyType", "sms");
		// parameters.put("bcb41ccdc4363c6848a1d760f26c28a0",
		// "7e9f3b4647316b0463a7eb72273e2422");
		// parameters.put("se45088a1",
		// "e84badbe93e08f70b11ff251b9bff2ef%7Cze1NdrvoKiRgdUQCyaND4A%3D%3Dae%3D%3D");
		// Map<String, String> map = login("http://vip.anjuke.com/login",
		// parameters);
		// System.out.println(map.get("html"));
		// final Map<String, String> returnMap = sendGet2(
		// "http://vip.anjuke.com/house/publish/ershou/?chooseWeb%5B%5D=2",
		// cookie);
		//
		// List<Map<String, String>> listMaps = new
		// ArrayList<Map<String,String>>();
		// listMaps.add(new HashMap<String, String>(){{
		// for (String key : returnMap.keySet()) {
		// put(key, returnMap.get(key));
		// System.out.println(key + ":" + returnMap.get(key));
		// }
		//
		// put("action", "publish");
		// put("fixPlanId", "");
		// put("params_41", "sadfsdggh3");
		// put("bianHao", "sfsgh");
		// put("community_unite", "深圳湾花园");
		// put("xiaoquId", "100477279");
		// put("shi", "3");
		// put("ting", "2");
		// put("wei", "4");
		// put("params_16", "1");
		// put("zhuangXiuQingKuang", "2");
		// put("chaoXiang", "1");
		// put("params_17", "1");
		// put("suoZaiLouCeng", "23");
		// put("zongLouCeng", "56");
		// put("ps_params_19", "鹏鸣阁");
		// put("ps_params_82", "595482");
		// put("ps_params_20", "");
		// put("ps_params_83", "");
		// put("ps_params_21", "102");
		// put("params_94", "40814930");
		// put("mianJi", "81");
		// put("params_12", "66");
		// put("params_24", "1");
		// put("params_81", "1");
		// put("params_14", "2004");
		// put("params_22", "1");
		// put("params_23", "");
		// put("params_26", "1");
		// put("params_25", "2");
		// put("params_18", "1");
		// put("params_29", "0.5");
		// put("jiaGe", "1000");
		// put("params_28", "");
		// put("title", "深圳龙华两房一厅深圳龙华两房一厅3333");
		// put("content_fangyuanxiangqing",
		// "上的广告低功耗上的广告低功耗上的广告低功耗上的广告低功耗上的广告低功耗上的广告低功耗");
		// put("content_yezhuxintai", "上的广告低功耗上的广告低功耗上的广告低功耗上的广告低功耗");
		// put("content_xiaoqupeitao", "上的广告低功耗上的广告低功耗上的广告低功耗上的广告低功耗");
		// put("content_fuwujieshao", "上的广告低功耗上的广告低功耗上的广告低功耗上的广告低功耗");
		// put("defaultImgID", "c0c8a88fb891499aa1ad01547e6e5a75");
		// put("room_upload_validate", "0.43512353698686157");
		// put("model_upload_validate", "0.5428656992606884");
		// put("outside_upload_validate", "1");
		// }});
		// listMaps.add(new HashMap<String, String>(){{
		// put("picture_shineitu[]","{\"host\":\"1\",\"id\":\"2835ff0d30299282bd8db1a578404181\",\"width\":900,\"height\":1600,\"size\":125982,\"hash\":\"2835ff0d30299282bd8db1a578404181\",\"format\":\"JPEG\",\"exists\":1,\"exif\":\"a:8:{s:12:\\\"FileDateTime\\\";s:10:\\\"1531407216\\\";s:8:\\\"FileSize\\\";s:6:\\\"125982\\\";s:8:\\\"MimeType\\\";s:0:\\\"\\\";s:4:\\\"Make\\\";s:0:\\\"\\\";s:5:\\\"Model\\\";s:0:\\\"\\\";s:8:\\\"Software\\\";s:0:\\\"\\\";s:8:\\\"DateTime\\\";s:10:\\\"1531407216\\\";s:9:\\\"copyright\\\";i:0;}\",\"proc_time\":0.019056081771851,\"copyright\":0}");
		// put("[]","{\"host\":\"1\",\"id\":\"2835ff0d30299282bd8db1a578404181\",\"width\":900,\"height\":1600,\"size\":125982,\"hash\":\"2835ff0d30299282bd8db1a578404181\",\"format\":\"JPEG\",\"exists\":1,\"exif\":\"a:8:{s:12:\\\"FileDateTime\\\";s:10:\\\"1531407216\\\";s:8:\\\"FileSize\\\";s:6:\\\"125982\\\";s:8:\\\"MimeType\\\";s:0:\\\"\\\";s:4:\\\"Make\\\";s:0:\\\"\\\";s:5:\\\"Model\\\";s:0:\\\"\\\";s:8:\\\"Software\\\";s:0:\\\"\\\";s:8:\\\"DateTime\\\";s:10:\\\"1531407216\\\";s:9:\\\"copyright\\\";i:0;}\",\"proc_time\":0.019056081771851,\"copyright\":0}");
		// put("roomorder[]", "2835ff0d30299282bd8db1a578404181");
		// }});
		// listMaps.add(new HashMap<String, String>(){{
		// put("picture_shineitu[]","{\"host\":\"1\",\"id\":\"c0c8a88fb891499aa1ad01547e6e5a75\",\"width\":1600,\"height\":1131,\"size\":331058,\"hash\":\"c0c8a88fb891499aa1ad01547e6e5a75\",\"format\":\"JPEG\",\"exists\":1,\"exif\":\"a:8:{s:12:\\\"FileDateTime\\\";s:10:\\\"1531407220\\\";s:8:\\\"FileSize\\\";s:6:\\\"331058\\\";s:8:\\\"MimeType\\\";s:0:\\\"\\\";s:4:\\\"Make\\\";s:0:\\\"\\\";s:5:\\\"Model\\\";s:0:\\\"\\\";s:8:\\\"Software\\\";s:0:\\\"\\\";s:8:\\\"DateTime\\\";s:10:\\\"1531407220\\\";s:9:\\\"copyright\\\";i:0;}\",\"proc_time\":0.027429819107056,\"copyright\":0}");
		// put("[]","{\"host\":\"1\",\"id\":\"c0c8a88fb891499aa1ad01547e6e5a75\",\"width\":1600,\"height\":1131,\"size\":331058,\"hash\":\"c0c8a88fb891499aa1ad01547e6e5a75\",\"format\":\"JPEG\",\"exists\":1,\"exif\":\"a:8:{s:12:\\\"FileDateTime\\\";s:10:\\\"1531407220\\\";s:8:\\\"FileSize\\\";s:6:\\\"331058\\\";s:8:\\\"MimeType\\\";s:0:\\\"\\\";s:4:\\\"Make\\\";s:0:\\\"\\\";s:5:\\\"Model\\\";s:0:\\\"\\\";s:8:\\\"Software\\\";s:0:\\\"\\\";s:8:\\\"DateTime\\\";s:10:\\\"1531407220\\\";s:9:\\\"copyright\\\";i:0;}\",\"proc_time\":0.027429819107056,\"copyright\":0}");
		// put("roomorder[]", "c0c8a88fb891499aa1ad01547e6e5a75");
		//
		// }});
		// listMaps.add(new HashMap<String, String>(){{
		// put("picture_shineitu[]","{\"host\":\"1\",\"id\":\"a89ae585461211800afd238bb8397fd1\",\"width\":900,\"height\":1600,\"size\":289439,\"hash\":\"a89ae585461211800afd238bb8397fd1\",\"format\":\"JPEG\",\"exists\":1,\"exif\":\"a:8:{s:12:\\\"FileDateTime\\\";s:10:\"1531407251\\\";s:8:\\\\\\\\\"FileSize\\\";s:6:\\\"289439\\\";s:8:\\\"MimeType\\\";s:0:\\\"\\\";s:4:\\\"Make\\\";s:0:\\\"\\\";s:5:\\\"Model\\\";s:0:\\\"\\\";s:8:\\\"Software\\\";s:0:\\\"\\\";s:8:\\\"DateTime\\\";s:10:\\\"1531407251\\\";s:9:\\\"copyright\\\";i:0;}\",\"proc_time\":0.022395849227905,\"copyright\":0}");
		// put("[]","{{\"host\":\"1\",\"id\":\"a89ae585461211800afd238bb8397fd1\",\"width\":900,\"height\":1600,\"size\":289439,\"hash\":\"a89ae585461211800afd238bb8397fd1\",\"format\":\"JPEG\",\"exists\":1,\"exif\":\"a:8:{s:12:\\\"FileDateTime\\\";s:10:\"1531407251\\\";s:8:\\\\\\\\\"FileSize\\\";s:6:\\\"289439\\\";s:8:\\\"MimeType\\\";s:0:\\\"\\\";s:4:\\\"Make\\\";s:0:\\\"\\\";s:5:\\\"Model\\\";s:0:\\\"\\\";s:8:\\\"Software\\\";s:0:\\\"\\\";s:8:\\\"DateTime\\\";s:10:\\\"1531407251\\\";s:9:\\\"copyright\\\";i:0;}\",\"proc_time\":0.022395849227905,\"copyright\":0}");
		// put("roomorder[]", "a89ae585461211800afd238bb8397fd1");
		//
		// }});
		// listMaps.add(new HashMap<String, String>(){{
		// put("picture_huxingtu[]","{\"host\":\"1\",\"id\":\"626bb67d613c37fc92316f67198e60ee\",\"width\":340,\"height\":340,\"size\":13479,\"hash\":\"626bb67d613c37fc92316f67198e60ee\",\"format\":\"JPEG\",\"exists\":1,\"exif\":\"a:8:{s:12:\\\"FileDateTime\\\";s:10:\\\"1531407386\\\";s:8:\\\"FileSize\\\";s:5:\\\"13479\\\";s:8:\\\"MimeType\\\";s:0:\\\"\\\";s:4:\\\"Make\\\";s:0:\\\"\\\";s:5:\\\"Model\\\";s:0:\\\"\\\";s:8:\\\"Software\\\";s:0:\\\"\\\";s:8:\\\"DateTime\\\";s:10:\\\"1531407386\\\";s:9:\\\"copyright\\\";i:0;}\",\"proc_time\":0.0070168972015381,\"copyright\":0}");
		// put("[]","{\"host\":\"1\",\"id\":\"626bb67d613c37fc92316f67198e60ee\",\"width\":340,\"height\":340,\"size\":13479,\"hash\":\"626bb67d613c37fc92316f67198e60ee\",\"format\":\"JPEG\",\"exists\":1,\"exif\":\"a:8:{s:12:\\\"FileDateTime\\\";s:10:\\\"1531407386\\\";s:8:\\\"FileSize\\\";s:5:\\\"13479\\\";s:8:\\\"MimeType\\\";s:0:\\\"\\\";s:4:\\\"Make\\\";s:0:\\\"\\\";s:5:\\\"Model\\\";s:0:\\\"\\\";s:8:\\\"Software\\\";s:0:\\\"\\\";s:8:\\\"DateTime\\\";s:10:\\\"1531407386\\\";s:9:\\\"copyright\\\";i:0;}\",\"proc_time\":0.0070168972015381,\"copyright\":0}");
		// put("modelorder[]", "626bb67d613c37fc92316f67198e60ee");
		//
		// }});
		// String result = sendPost(
		// "http://vip.anjuke.com/house/publish/ershou/?chooseWeb%5B%5D=2",
		// listMaps, cookie);
		// System.out.println(result);
	}

	/**
	 * 安居客登录接口
	 * 
	 * @param url
	 * @param parameters
	 * @return
	 */
	public static Map<String, String> login(String url, Map<String, String> parameters) {
		Map<String, String> map = new HashMap<String, String>();
		String cookieString = "";
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
			// httpConn.setRequestProperty("Accept", "*/*");
			// httpConn.setRequestProperty("Connection", "Keep-Alive");
			// httpConn.setRequestProperty("User-Agent",
			// "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			httpConn.setRequestProperty("Proxy-Connection", "keep-alive");
			httpConn.setRequestProperty("Cache-Control", "max-age=0");
			httpConn.setRequestProperty("Origin", "http://vip.anjuke.com");
			httpConn.setRequestProperty("Upgrade-Insecure-Requests", "1");
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
			httpConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			httpConn.setRequestProperty("Referer", "http://vip.anjuke.com/login");
			httpConn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
			httpConn.setRequestProperty("Cookie", "sessid=DBF61576-2F80-61A0-4A54-97ED1C77128D; aQQ_brokerguid=1590DCA6-9C53-9FC3-C4B7-B3345AF9D6A3; anjukereg=AqbcgjhUgGUA6JE4");

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

			if (result.indexOf("操作失败") == -1) {
				String s1 = result.substring(result.indexOf("https://passport.58.com"));
				String tongcheng58 = s1.substring(0, s1.indexOf("\'></script>"));

				String s2 = result.substring(result.lastIndexOf("http://www.ganji.com"));
				String ganji = s2.substring(0, s2.indexOf("\'></script>"));

				String s3 = result.substring(result.lastIndexOf("http://login.anjuke.com/login/verify"));
				String anjuke = s3.substring(0, s3.indexOf("\'></script>"));

				String s4 = result.substring(result.indexOf("http://vip.anjuke.com/account/createguid"));
				String token = s4.substring(0, s4.indexOf("\'></script>"));

				String s5 = result.substring(result.indexOf("content=\"0;URL=http://vip.anjuke.com"));
				String indexurl = s5.substring(15, s5.indexOf("\" />"));

				HttpUtils.sendGetRequest(tongcheng58);
				HttpUtils.sendGetRequest(ganji);
				cookieString = sendGet1(indexurl);
				// cookieString = sendGet1(token);
				// cookieString = cookieString + sendGet1(anjuke.replace("http",
				// "https"));
				sendGet1(token);
				sendGet1(anjuke.replace("http", "https"));
			} else {
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
			}

			map.put("html", result);
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

	/**
	 * 安居客发布接口
	 * 
	 * @param url
	 * @param parameters
	 * @return
	 */
	public static String sendPost(String url, List<Map<String, String>> listMaps, String cookie) {
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
			for (int i = 0; i < listMaps.size(); i++) {
				for (String name : listMaps.get(i).keySet()) {
					sb.append(name).append("=").append(java.net.URLEncoder.encode(listMaps.get(i).get(name), "UTF-8")).append("&");
				}
			}
			params = sb.toString();
			params = params.substring(0, params.length() - 1);
			// 创建URL对象
			URL connURL = new URL(url);
			// 打开URL连接
			HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
			httpConn.setRequestMethod("POST");
			// 设置通用属性
			// httpConn.setRequestProperty("Accept", "*/*");
			// httpConn.setRequestProperty("Connection", "Keep-Alive");
			// httpConn.setRequestProperty("User-Agent",
			// "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			httpConn.setRequestProperty("Ajk", "m=tjtx136-220-143.58os.org, pv=2018_28_14, jv=ga,app_name=broker-v5,jkjv=ga");
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			httpConn.setRequestProperty("Date", "Thu, 12 Jul 2018 14:56:31 GMT");
			httpConn.setRequestProperty("Keep-Alive", "timeout=38");
			httpConn.setRequestProperty(
					"Location",
					"/house/result?united=200&ajk=200&title=%E5%8F%91%E5%B8%83%E6%88%90%E5%8A%9F&type=1&subMsg=%E5%AE%89%E5%B1%85%E5%AE%A2%EF%BC%9A%E6%93%8D%E4%BD%9C%E6%88%90%E5%8A%9F%3B++&link=&linkName=&linkPage=&linkAuto=0");
			httpConn.setRequestProperty("P3p", "CP=CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR");
			httpConn.setRequestProperty("Server", "nginx");
			httpConn.setRequestProperty("Transfer-Encoding", "chunked");

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

	/**
	 * @Description: 安居客获取登录所需cookie
	 * @author 宋高俊
	 * @date 2018年7月12日 下午2:25:58
	 */
	public static String sendGet1(String url) {
		String cookieString = "";
		CookieManager manager = new CookieManager();
		// 设置cookie策略，只接受与你对话服务器的cookie，而不接收Internet上其它服务器发送的cookie
		// manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
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
			// java.net.HttpURLConnection httpConn =
			// (java.net.HttpURLConnection) connURL.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
			// 设置通用属性
			httpConn.setRequestProperty("Accept", "*/*");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			// 建立实际的连接
			httpConn.connect();
			// 响应头部获取
			// Map<String, List<String>> headers = httpConn.getHeaderFields();
			// // 遍历所有的响应头字段
			// for (String key : headers.keySet()) {
			// System.out.println(key + "\t：\t" + headers.get(key));
			// }
			// 定义BufferedReader输入流来读取URL的响应,并设置编码方式
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}
			// 请求头设置的cookie
			String cookieskey = "Set-Cookie";
			Map<String, List<String>> maps = httpConn.getHeaderFields();
			List<String> coolist = maps.get(cookieskey);
			Iterator<String> it = coolist.iterator();
			StringBuffer sbu = new StringBuffer();
			while (it.hasNext()) {
				String next = it.next();
				// System.out.println(next);
				sbu.append(next + ";");
			}

			// System.out.println(sbu.toString());

			// 请求相应的cookie
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
			// System.out.println(cookieString);
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
	 * @Description: 安居客获取验证所需参数
	 * @author 宋高俊
	 * @date 2018年7月12日 下午2:25:58
	 */
	public static Map<String, String> sendGet2(String url, String cookie) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String result = "";
		BufferedReader in = null;// 读取响应输入流
		StringBuffer sb = new StringBuffer();// 存储参数
		try {
			// 创建URL对象
			java.net.URL connURL = new java.net.URL(url);
			// 打开URL连接
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
			// 设置通用属性
			httpConn.setRequestProperty("Proxy-Connection", "keep-alive");
			httpConn.setRequestProperty("Cache-Control", "max-age=0");
			httpConn.setRequestProperty("Origin", "http://vip.anjuke.com");
			httpConn.setRequestProperty("Upgrade-Insecure-Requests", "1");
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
			httpConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			httpConn.setRequestProperty("Referer", "http://j.esf.leju.com/house/publish/?tradetype=1");
			httpConn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
			httpConn.setRequestProperty("Cookie", cookie);
			// 建立实际的连接
			httpConn.connect();
			// 定义BufferedReader输入流来读取URL的响应,并设置编码方式
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			String string2 = "";
			while ((line = in.readLine()) != null) {
				result += line;
				if (result.indexOf("input.name = ") != -1 && result.indexOf("$(input).appendTo") == -1) {
					String thisline = line;

					if (thisline.indexOf("/*") != -1) {
						String start = thisline.substring(0, thisline.indexOf("/*"));
						start += thisline.substring(thisline.lastIndexOf("*/") + 2, thisline.length());
						thisline = start;
					}
					if (thisline.indexOf("//") != -1) {
						thisline = thisline.substring(0, thisline.indexOf("//"));
					}
					string2 += thisline;
				}
			}
			// 保存最后一个参数验证的值
			String privatekey = "";
			privatekey = string2.substring(string2.indexOf("var"), string2.indexOf("',"));
			privatekey = privatekey.substring(privatekey.indexOf("=") + 1, privatekey.length());
			privatekey = privatekey.replace("'", "");
			privatekey = privatekey.replace("+", "");
			privatekey = privatekey.replace(" ", "");
			String privatekey2 = string2.substring(string2.indexOf("[["), string2.indexOf("]]") + 2);
			privatekey2 = privatekey2.replace("[", "");
			privatekey2 = privatekey2.replace("]", "");
			String[] p = privatekey2.split(",");
			int a = p.length / 2;
			for (int i = 0; i < a; i++) {
				privatekey = privatekey.substring(0, Integer.valueOf(p[i * 2])) + privatekey.substring(Integer.valueOf(p[i * 2 + 1]));
			}
			string2 = string2.substring(string2.indexOf("return"));
			privatekey += string2.substring(string2.indexOf("'") + 1, string2.lastIndexOf("'")) + "ae==";

			// 获取生成的验证一对参数
			String sdString = result.substring(result.indexOf("input.name = "));

			String sfcd = sdString.substring(14, sdString.indexOf("';"));
			// 截取\"name\":\"name\",后面的字符
			String sdString1 = result.substring(result.indexOf("\"name\":\"name\","));
			// 截取\"name\":\"value\",后面的字符
			String sdString2 = sdString1.substring(sdString1.indexOf("\"name\":\"value\","));
			// 获取生成的验证键值参数
			String name = sdString1.substring(35, sdString1.indexOf("\"        }"));
			// 获取生成的验证值参数
			String value = sdString2.substring(36, sdString2.indexOf("\"        }"));
			resultMap.put(name, value);
			resultMap.put(sfcd, privatekey);

			// String broker_id =
			// result.substring(result.indexOf("name=\"broker_id\""),result.indexOf("name=\"broker_id\"")+100);
			// broker_id = broker_id.substring(broker_id.indexOf("value=")+7);
			// resultMap.put("broker_id",
			// broker_id.substring(0,broker_id.indexOf("\"")));
			String broker_id = cookie.substring(cookie.indexOf("ajk_broker_id="), cookie.indexOf("ajk_broker_id=") + 30);
			broker_id = broker_id.substring(broker_id.indexOf("ajk_broker_id=") + 14);
			resultMap.put("broker_id", broker_id.substring(0, broker_id.indexOf(";")));
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
		return resultMap;
	}

}
