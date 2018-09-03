package com.xiaoyi.ssm.coreFunction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

import org.apache.commons.lang3.StringUtils;

import com.xiaoyi.ssm.util.HttpUtils;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 房天下接口功能代码
 * @author 宋高俊
 * @date 2018年7月16日 上午9:27:22
 */
public class FangTianXiaUtil {
	
	/**  
	 * @Description: 根据名称查询小区
	 * @author 宋高俊  
	 * @date 2018年7月24日 下午3:32:59 
	 */ 
	public static List<Map> getSelectByName(String name) {
		Map<String, String> map = new HashMap<>();
		try {
			map = HttpUtils.sendGetHtml( "http://esf.sz.fang.com/usercenter/HousePublishAjax/GetLouDongList?type=5&t=&key="+URLEncoder.encode(name,"GBK"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String html = map.get("html");
		System.out.println(html);
		List<Map> xmlmap = Utils.readStringXmlOut(html);
		return xmlmap;
	}
	
	/**  
	 * @Description: 根据名称获取小区均价
	 * @author 宋高俊  
	 * @date 2018年7月24日 下午3:32:59 
	 */ 
	public static String getEstateAvgPrice(String name) {
		if (!StringUtils.isBlank(name)) {
			Map<String, String> map = new HashMap<>();
			try {
				map = HttpUtils.sendGetHtml( "http://esf.sz.fang.com/integrate/c61-kw"+URLEncoder.encode(name,"GBK")+"/", "GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String html = map.get("html");
//			System.out.println(html);
			if (html.indexOf("小区均价") != -1) {
				String money = html.substring(html.indexOf("小区均价") + 51);
				money = money.substring(0,money.indexOf("元/平米"));
				return money;
			}
		}
		return "暂无报价";
	}
	public static void main(String[] args) {
		System.out.println(getEstateAvgPrice("美丽365"));
//		HTTPSTrustManager.allowAllSSL();//信任所有证书
//
//		Map<String, String> parameters = new HashMap<String, String>();
//		parameters.put("Uid", "soufun-s59284995");
//		parameters.put("Pwd", "858ef64b2812b9a716b50b30011cd662c5920a96b7243201efbae7f54164270a683cf63524c63efaa26ed4c0dad90bb9a05445727114abb521ba77c596405c16259ed2b2fcf0f9a309d1dda605bba04e9789ea3154ce95d036e6f89daf2d0c301b9da74715c2ea2908b00e918344fbbd1e6b43dd54cb4f1ce24b21e1c3dcb2c7");
//		parameters.put("Service", "esf-agent-web");
//		
//		Map<String, String> map3 = login("https://passport.fang.com/login.api", parameters, "");
////		System.out.println(cookie);
//		Map<String, String> map = HttpUtils.sendGetHtml("http://n.agent.fang.com/magent/house/sale/saleinput.aspx", map3.get("cookie"), "GBK");
////		Map<String, String> map2 = HttpUtils.sendGetHtml("http://n.agent.fang.com/magent/house/sale/saleinput.aspx", map.get("cookie"), "GBK");
//		System.out.println(map.get("html"));
		

//		Map<String, String> parameters2 = new HashMap<String, String>();
//
//		parameters2.put("input_y_str_PROJNAME", "美丽365");
//		parameters2.put("input_y_str_ADDRESS", "龙华东环二路与三联路交汇处");
//		parameters2.put("input_y_str_DISTRICT0", "请选择区县");
//		parameters2.put("input_y_str_DISTRICT", "龙华区");
//		parameters2.put("input_y_str_COMAREA0", "请选择商圈");
//		parameters2.put("input_y_str_COMAREA", "龙华");
//		parameters2.put("str_infocode", "");
//		parameters2.put("str_innerid", "");
//		parameters2.put("input_y_str_PAYINFO0", "商品房");
//		parameters2.put("input_y_str_PAYINFO", "商品房");
//		parameters2.put("input_PropertySubType", "普通住宅");
//		parameters2.put("input_str_PropertySubType", "普通住宅");
//		parameters2.put("input_y_num_PRICE", "500");
//		parameters2.put("input_ROOM", "2");
//		parameters2.put("input_HALL", "1");
//		parameters2.put("input_TOILET", "1");
//		parameters2.put("input_KITCHEN", "1");
//		parameters2.put("input_BALCONY", "1");
//		parameters2.put("input_HouseStructure", "选择结构");
//		parameters2.put("input_str_HouseStructure", "选择结构");
//		parameters2.put("input_BuildingType", "选择类别");
//		parameters2.put("input_str_BuildingType", "选择类别");
//		parameters2.put("6b5e2e9binput9d54", "100");
//		parameters2.put("input_y_num_LIVEAREA", "98");
//		parameters2.put("input_n_str_CREATETIME", "2002");
//		parameters2.put("input_FLOOR", "2");
//		parameters2.put("input_ALLFLOOR", "5");
//		parameters2.put("input_n_str_FORWARD", "西北");
//		parameters2.put("input_n_str_FITMENT", "简装修");
//		parameters2.put("input_n_str_LOOKHOUSE", "随时可看");
//		parameters2.put("5fed3002input40d5", "美丽365美丽365美丽365美丽365美丽365");
//		parameters2.put("input_n_str_CONTENT", "美丽365花园两房一厅500万美丽365花园两房一厅500万美丽365花园两房一厅500万");
//		parameters2.put("input_str_OwnerMentality", "美丽365美丽365美丽365美丽365美丽365");
//		parameters2.put("input_str_TaxAnalysis", "美丽365花园两房一厅500万美丽365花园两房一厅500万美丽365花园两房一厅500万");
//		parameters2.put("input_str_CommunityMatching", "美丽365花园两房一厅500万美丽365花园两房一厅500万美丽365花园两房一厅500万");
//		parameters2.put("input_str_ServiceIntroduction", "美丽365花园两房一厅500万美丽365花园两房一厅500万美丽365花园两房一厅500万");
//		parameters2.put("input_n_str_TRAFFICINFO", "公交： 沙井新和（M291路空调），同富裕工业区（756路），喜客隆超市（B867路,656路,B774路空调,B867路空调），后亭村（756路），沙松路囗（656路），沙松路口（B774路空调）地铁：4号线清湖站C出口，距离小区约300米");
//		parameters2.put("input_n_str_SUBWAYINFO", "大学：宝安广播电视大学、西北工业大学深圳教学点、湖南工业大学深圳教学点中小学：三联小学、中心小学、新华中学、龙华中学、松和小学，龙华二小:幼儿园：美丽365幼儿园、小精灵幼儿园:商场：百佳超市、景乐市场、龙华市场:邮局：中国邮政:银行：农业银行、工商银行、建设银行、平安银行:医院：龙华人民医院:其他：金鹏酒店、龙泉酒店:");
//		parameters2.put("agentmainput_Hfile", "");
//		parameters2.put("agentmainput_Sfile", "");
//		parameters2.put("agentmainput_Xfile", "");
//		parameters2.put("txtImageDes_2_1", "外景图");
//		parameters2.put("txtImage_2_1", "http://imgs.soufun.com/house/2012_03/20/sz/1332228059867_000.jpg");
//		parameters2.put("inpUrlsExtend_2_1", "");
//		parameters2.put("inputIsProj_2_1", "2");
//		parameters2.put("inputOrderIndex_2_1", "1");
//		parameters2.put("photoRefId_2_1", "undefined");
//		parameters2.put("source_2_1", "undefined");
//		parameters2.put("txtImageDes_2_2", "外景图");
//		parameters2.put("txtImage_2_2", "http://img3.soufunimg.com/agents/2013_07/23/M07/00/D8/wKgFqFHuW1KIVsISAALq02PexVwAABUmQFmR-sAAurr746.jpg");
//		parameters2.put("inpUrlsExtend_2_2", "");
//		parameters2.put("inputIsProj_2_2", "2");
//		parameters2.put("inputOrderIndex_2_2", "2");
//		parameters2.put("photoRefId_2_2", "undefined");
//		parameters2.put("source_2_2", "undefined");
//		parameters2.put("txtImageDes_2_3", "外景图");
//		parameters2.put("txtImage_2_3", "http://img1.soufunimg.com/agents2/2011_11/27/21/77/82/sz/houseinfo/400054678700.jpg");
//		parameters2.put("inpUrlsExtend_2_3", "");
//		parameters2.put("inputIsProj_2_3", "2");
//		parameters2.put("inputOrderIndex_2_3", "3");
//		parameters2.put("photoRefId_2_3", "undefined");
//		parameters2.put("source_2_3", "undefined");
//		parameters2.put("DelegateImg_Qrfile", "");
//		parameters2.put("additionphotoadd", "");
//		parameters2.put("additionphotodel", "");
//		parameters2.put("additionphoto", "");
//		parameters2.put("input_y_str_HOUSENUMBER", "");
//		parameters2.put("input_y_num_ISVIDEO", "0");
//		parameters2.put("input_y_num_ISBaiDu_Vod", "0");
//		parameters2.put("input_y_num_vtitle", "");
//		parameters2.put("input_houseType", "");
//		parameters2.put("input_y_str_soufunvideo", "");
//		parameters2.put("chk_promisetruth", "");
//		parameters2.put("hTagSchool", "0");
//		parameters2.put("chksfb", "true");
//		parameters2.put("inputdrawtext", "");
//		parameters2.put("hdHouseDicCity", "0");
//		parameters2.put("inputT", "2018-07-19 09:46:39");
//		parameters2.put("input_y_str_PURPOSE", "住宅");
//		parameters2.put("input_y_str_ISPAY", "0");
//		parameters2.put("intput_y_str_HTYPE", "Sale");
//		parameters2.put("imageCount", "3");
//		parameters2.put("coverPhoto", "");
//		parameters2.put("newcode", "2810024375");
//		parameters2.put("tempprojimgs", "");
//		parameters2.put("newprojimgs", "");
//		parameters2.put("UseProjImage", "");
//		parameters2.put("input_y_str_MANAGERNAME", "soufun-s59284995");
//		parameters2.put("input_y_str_BUSINESSTYPE", "CS");
//		parameters2.put("input_y_str_COMPANYNAME", "家家顺");
//		parameters2.put("input_y_str_PRICETYPE", "万元/套");
//		parameters2.put("hiddenProjname", "美丽365");
//		parameters2.put("hdUseMode", "");
//		parameters2.put("input_DelegateIDAndAgentID", "0");
//		parameters2.put("input_draftsID", "0");
//		parameters2.put("changbr", "true");
//		String result =
//		enter("http://n.agent.fang.com/MAgent/house/InputSave.aspx?flag=2&haveMultipleCity=False&city=%E6%B7%B1%E5%9C%B3&isWireless=0",
//		parameters2, map2.get("cookie"));
//		System.out.println(result);
	}

	/**
	 * @Description: 登录
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
			String full_url = url + "?" + params;
			// 创建URL对象
			URL connURL = new URL(full_url);
			// 打开URL连接
			HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
			httpConn.setRequestProperty("Connection", "keep-alive");
			httpConn.setRequestProperty("Cache-Control", "max-age=0");
			httpConn.setRequestProperty("Upgrade-Insecure-Requests", "1");
			httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
			httpConn.setRequestProperty("Accept", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
			httpConn.setRequestProperty("Referer", "http://agent.fang.com/login.aspx");
			httpConn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
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
			// System.out.println(result);
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
			httpConn.setRequestProperty("Host", "n.agent.fang.com");
			httpConn.setRequestProperty("Connection", "keep-alive");
			httpConn.setRequestProperty("Content-Length", "5477");
			httpConn.setRequestProperty("Accept", "text/plain, */*; q=0.01");
			httpConn.setRequestProperty("Origin", "http://n.agent.fang.com");
			httpConn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.26 Safari/537.36 Core/1.63.5702.400 QQBrowser/10.2.1893.400");
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			httpConn.setRequestProperty("Referer", "http://n.agent.fang.com/magent/house/sale/saleinput.aspx");
			httpConn.setRequestProperty("Accept-Encoding", "gzip, deflate");
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
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "GBK"));
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
