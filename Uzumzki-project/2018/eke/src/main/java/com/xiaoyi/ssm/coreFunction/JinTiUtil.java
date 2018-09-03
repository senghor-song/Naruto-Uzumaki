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
 * @Description: 今题网接口功能代码
 * @author 宋高俊
 * @date 2018年7月17日 下午6:51:11
 */
public class JinTiUtil {
	public static void main(String[] args) {
		HTTPSTrustManager.allowAllSSL();//信任所有证书

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("__EVENTTARGET", "");
		parameters.put("__EVENTARGUMENT", "");
		parameters.put("__VIEWSTATE", "5eA3rSrKEONv9RvBxuvIpeEvvBgT7VNWVPg7e7xKngPFbxzWMwhzAjU+03sxcf9ns1ddQUt6hVqTxO8GkK2C4pM47mvhtIkEC6BkBcQA5SxJrt1bwJDduUQ4zYAfNwdecy6m1viycok5tVaQZBh5lnEMo6U=");
		parameters.put("__VIEWSTATEGENERATOR", "C2EE9ABB");
		parameters.put("__EVENTVALIDATION", "RhAA9ErJENj6xAhdhlVj4smm/EfDw6u2PS357QrJA0mM7SpwPr9O0pSYda5nhNNjhnPI9GqmtcOy5iDVHFtW5iu50aSp1EfiaotDeNKnwUcww3V2/3kGPr0ZMWMbXPgtfuKsQ+MXdYbnzW0Ay9t8F6r0kjXrUwr2DAbgxy1hENuYJBiqicoAR41SiII=");
		parameters.put("action", "login");
		parameters.put("ReURL", "http%3a%2f%2fwww.jinti.com%2fmembers%2fmanage.aspx");
		parameters.put("account", "7158369@qq.com");
		parameters.put("password", "eg123456");
		parameters.put("btnLogin", "登录");
		String cookie = login("http://passport.jinti.com/SSO/Login.aspx", parameters, "");
//		List<Data> list = new ArrayList<Data>();
//		Data data = new Data();
//		data.put("", "");
//		list.add(data);

		Map<String, String> parameters2 = new HashMap<String, String>();

		parameters2.put("__EVENTTARGET", "btnSave");
		parameters2.put("__EVENTARGUMENT", "");
		parameters2.put("__VIEWSTATE", "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ODYDMzUzAzQ0MAMyNjUDMjI1AzM5MQQ3MzE0AzQ5OQQ0NDcxAzI2MQMxMTgDMjQzAzQ0NgMzMzEDNDExBDkwMjYDNDk0BTE0OTg3AzQ1NgQ1NDQwAzIwMgQ0NDIzAzI2OQMyMzIDMzQ3AzExNgM0NTgGNjEwMTA3AzMzNAMyMTIDMjcyAzQ3MAMzNjEDMzk5AzI2NwQyMDc3BDQ4MzUDNDA1BDMxMTQDMjI2BjYxMDEwNAMzNjUDMjUwAzIwMAMyNDkDMzg3AzQwNwQ1NDM5AzM3NQUxMzc0NwY2MTAxMDgDNDM2BDUzOTQFMTM3NDgDMzY3BDE3ODkFMTM0MjEENDgzNAMyOTQFMTQyNDgDMjE5AzMyNQMzMjEDMzYyBDM4MjcDMjI0AzIyOQMyNjQDMjkwAzQ1MQM0NzEDMjg5BTEzNzQ5BjYxMDQ3MwMzNzEDMzc3AjI1BDY2MDcDMzU1AzM1MQM0NTkDNDc3AzQ4MQQ0MzI4AzQ3NQM0NzIEMjYzMAUxMzkyNgQ0MzEwAzQ3MwQ0MTIyAzQ3NAM0OTUDMTE0AzY4NwM0MjUDNDQ3BTEzNzUwAzM1OQM0NTADMjUzAzI1NAMxMTkEMzc4NAM0OTYFMTQ2MDkDMzQxBTEzMzA4AzM1OAM0MDADNDY3AzEwOQMyNjIDMzM3AzI2NgMzMTUDMjc2AzMxMQQyNjM1BDQ0NzADMTAwAzIzNAMyMTcFMTQ2NDkDMTEwAzI0NQMyODgENDMzMAQ0ODM3AzMwMgQ0MTQyBDQxMDMDNDI4AzM0OAMyMzUDNDUzAzQzMAMyMDMDMjgzBDQ4NTEDMzE4AzMxMwQ0MjAzAzI5NQQ0MDE5AzIwNQMyMjcDMjgyAzQzMQMyNTEDMzA3BDQ0NTUDNDg2BDUzNTkDNDk3BTI0NjcyBTI0NjI2AzQ2MAMzNjAFMTM3NTIDMTE1BDI1OTgEMjY4NAY2MTAxMDkDMzg1BTEzNzUzAzI1NgMyOTgDMjMwAzIzNwM0MDEDNDA5AzIwNwY2MTAxMTAGNjEwMTExAzQzOAMxMDIFMTM4NDUDNDEyAzI2MAMyNjgDMzkwAzM1MAQ0NDMwAzQzNwMyODADMzM1BTEzNzU0BDQxNTADMzc0AzIwOQMyNzQDNDY5AzEwMQMzODIDMzQyAzM3NgUyNDkzNwMyNDQENDAzMAM0NTIDMzgzAzQ0OAM0NTcDMjc5BDQyOTMDMzAzAzQxNAMzNzkDMjgxBDIwNTEFMTQ2NjcDNDA4AzIzMwMzNzMEMzkzNAQzOTM3AzMxMAM0MzQDMjg0BTI0NzQ5AzI3NwMzMDADMzkzAzMyNAMzOTQDMzk1BDQzMDUEMjA1MAM5MTgDNDMzAzM0NgMxMTIDMzU0AzM2NAY2MTAxMTIDMjQ4AzI0NwQzOTk0AzM5NgMyNTkDNDE2BTEzMzA5BDQzMjUENDMxOQQyMDU4AzMwOQMyNzgDMzYzAzQzOQM0MjQDMzA0AzI4NgM0MTMEMzM0MwMzNDMDMzQwBDM4OTYDMzMzAzMyNgM0NDUDNDQ0AzQ5MAM0NDIENDAyNAMzMTYDNDc5BDQ0MDEDMjQ2BDM4MzgDMjA0BTE0Njc2AzQwNAMyMzYDMjQwAzI1NQMzNzgDMzIwAzI1NwMyNjMEMjA5NQM0OTMDMjk2BDQzNDcDNDY4BDQ4NTIDNDQzAzEwOAQ0MjUyBDQwNTYFMTQ2MTkDNDQxAzMyMwM0MjcDMjMxBTEzNDM4AzQxOQMyMzgDMjExAzI3MwMzOTIENDEzMQM0ODgDNDg1BjYxMDExMwQzMTI4AzM2OAQ0Mzk2AzI5NwM0MjADMzY2BDQyNjgDNDAzBDIwNTIDMjEzAzQ1NQMyNzADNDkxAzIyMAM0NjYDNDI5BDM4NTAFMTQxNzIDMzY5AzQ4MAMzNTIEMjU4OAM0NjIDMzEyAzQ2NwQyMDc1AzMyOQMzMzkDMzE5AzMyMgM0MTgDMzE3AzIxOAMyMTQENDg1NAMxMTcEMjYxMAMyOTkDMzA1BDM5MzgDMjg1AzIwNgM0MTADMjc1AzMwMQQzOTI3AzM4MAUxNDc4MwM0MjEDMjQyAzEwNgMyMDEENDg0NQQ0Mzc0AzM0NAQ5MDI4BDI5NTIDMzg0AzMxNAMyNTIDMjkxBDkyNTMDNDk4BDI2MjEDMjI4AzI4NwUxMzc1NQMzMzAFMTQzNTkDMzM2BDIwNTcDMzMyAzM1NgM0MjIDMjA4AzM0OQM0NzgDMzk3BDIwNzYEOTAyNQMyOTMENjUxMgMzMzgDMTExBDEwOTUDNDMyAzEwNQMxMDQDMzQ1AzM5OAQxMjQ3BDEyNTUEMjIxOQMxMDcEMzY0OQM0ODIDMzA2AzI1OAQzMTI1BDQ0MjAEMzQzMQQzODk5AzMwOAMzMjgDMjkyAzM3MgMzODEDMzg4BDE5ODgUKwO1A2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnFgECnAJkAg0PDxYCHwAFCFMg5rex5ZyzZGQCEA8QFgYfAgUKY2xhc3NfbmFtZR8DBQhjbGFzc19pZB8EZxAVCAzpgInmi6nljLrln58JQiAg5a6d5a6JCUYgIOemj+eUsAlMICDpvpnlspcJTCAg572X5rmWCU4gIOWNl+WxsQ9PICDmt7HlnLPlkajovrkJWSAg55uQ55SwFQgBMAM4MTcDODAwAzgxOAM4MTkDODIwBDIwMjUDODE2FCsDCGdnZ2dnZ2dnZGQCEg8QFggeBXN0eWxlBQ1kaXNwbGF5Om5vbmU7HwIFCWJsb2NrbmFtZR8DBQJpZB8EZxAVABUAFCsDAGRkAhMPEBYCHwUFCWRpc3BsYXk6OxAVAQzpgInmi6nlnLDmrrUVAQEwFCsDAWdkZAIUDw8WAh8ABSHpgInmi6nlnLDmrrXorqnmiL/mupDmm7TliqDnsr7lh4ZkZAIYDw9kFgIfBQUJZGlzcGxheTo7ZAIZDw9kFgIeB29ua2V5dXAFI3Jlc2lTZWFyY2goZXZlbnQsJ2xibFJlc2lOYW1lTXNnJyk7ZAIeDw9kFgIfBQUJZGlzcGxheTo7ZAIgDw9kFgIfBQUNZGlzcGxheTpub25lO2QCIQ8WAh8FBQlkaXNwbGF5OjtkAiIPD2QWAh8FBQlkaXNwbGF5OjtkAkIPDxYCHwAFCeeOi+Wwj+WnkGRkAkQPDxYCHwAFCzEzMzYwMDYzMjM2ZGQCUA9kFgICAQ88KwAJAGQCUw9kFgICAQ88KwAJAGRk/0nU7n9OoekXGhzfYqwEqBb5mcM=");
		parameters2.put("__VIEWSTATEGENERATOR", "267C8AF6");
		parameters2.put("hid_arrs", "444^817^B 宝安^1756^龙华^0^^(宝安区宝安龙华东环二路与三联路交汇处) ^^美丽365^^^100^2^1^1^500^^^2005^2^3^4^电视机,冰箱,空调,洗衣机,热水器,微波炉,电话,水,电,煤气,暖气,有线电视,宽带,车库,储藏室/地下室^1^美丽365美丽365美丽365美丽365^美丽365美丽365美丽365美丽365美丽365美丽365^^^^^^^^1^7^-1^^^0^0^1^70");
		parameters2.put("position", "0");
		parameters2.put("hidFxUrl", "0");
		parameters2.put("hidCommUrl", "0");
		parameters2.put("hidUserId", "12421233");
		parameters2.put("hidEditId", "0");
		parameters2.put("hidStatus", "N");
		parameters2.put("txtADAgencyID", "");
		parameters2.put("hidAreaId", "444");
		parameters2.put("ddlDeparea", "817");
		parameters2.put("ddlSpotArea", "1756");
		parameters2.put("txtResidential", "美丽365");
		parameters2.put("hidDep", "");
		parameters2.put("hidSpot", "");
		parameters2.put("hidBlock", "");
		parameters2.put("hidRId", "");
		parameters2.put("hidIsFind", "");
		parameters2.put("txtDetailAddress", "(宝安区宝安龙华东环二路与三联路交汇处) ");
		parameters2.put("txtMianJi", "100");
		parameters2.put("txtShi", "2");
		parameters2.put("txtTing", "1");
		parameters2.put("txtWS", "1");
		parameters2.put("txtRent", "500");
		parameters2.put("txtRent2", "");
		parameters2.put("txtLouCeng", "2");
		parameters2.put("txtZongLouCeng", "3");
		parameters2.put("tdianti", "1");
		parameters2.put("dianti", "1");
		parameters2.put("txtHouseAge", "2005");
		parameters2.put("peizhi", "电视机");
		parameters2.put("peizhi", "冰箱");
		parameters2.put("peizhi", "空调");
		parameters2.put("peizhi", "洗衣机");
		parameters2.put("peizhi", "热水器");
		parameters2.put("peizhi", "微波炉");
		parameters2.put("peizhi", "电话");
		parameters2.put("sheshi", "水");
		parameters2.put("sheshi", "电");
		parameters2.put("sheshi", "煤气");
		parameters2.put("sheshi", "暖气");
		parameters2.put("sheshi", "有线电视");
		parameters2.put("sheshi", "宽带");
		parameters2.put("sheshi", "车库");
		parameters2.put("sheshi", "储藏室/地下室");
		parameters2.put("txtAddress", "美丽365美丽365美丽365美丽365");
		parameters2.put("FCKeditor1", "美丽365美丽365美丽365美丽365美丽365美丽365");
		parameters2.put("hmlemail", "7158369@qq.com");
		parameters2.put("weixin", "");
		parameters2.put("qq", "");
		parameters2.put("picnum", "0");
		parameters2.put("picstr", "");
		parameters2.put("pictitle", "");
		parameters2.put("hidShiNeiPic", "");
		parameters2.put("hidPicMd", "");
		parameters2.put("hidFeng", "");
		parameters2.put("hidAllName", "");
		parameters2.put("hidAddName", "1");
		parameters2.put("hidPreview", "");
		parameters2.put("file", "");
		parameters2.put("picnum", "0");
		parameters2.put("picstr", "");
		parameters2.put("pictitle", "");
		parameters2.put("hidFangXingPic", "");
		parameters2.put("file", "");
		parameters2.put("picnum", "0");
		parameters2.put("picstr", "");
		parameters2.put("pictitle", "");
		parameters2.put("hidXiaoQuPic", "");
		String result = enter("http://housing.jinti.com/aspx/aspx/Company_post_sell.aspx?areaid=444", parameters2, cookie);
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
