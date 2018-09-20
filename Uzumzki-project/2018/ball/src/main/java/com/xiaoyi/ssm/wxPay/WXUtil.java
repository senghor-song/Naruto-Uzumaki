package com.xiaoyi.ssm.wxPay;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jdom.JDOMException;

import com.alibaba.fastjson.JSONObject;
import com.xiaoyi.ssm.model.AmountRefund;
import com.xiaoyi.ssm.service.AmountRefundService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.SpringUtils;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 微信工具类
 * @author 宋高俊
 * @date 2018年8月30日 下午7:14:37
 */
@SuppressWarnings("deprecation")
public class WXUtil {

	private static Logger logger = Logger.getLogger(WXUtil.class.getName());

	/**
	 * @Description: https双向签名认证，用于支付申请退款
	 * @author 宋高俊
	 * @date 2018年8月30日 下午7:15:58
	 */
	public static String payHttps(String url, String xml, File file) throws Exception {

		// 商户id
		String MCH_ID = WXConfig.mch_id;
		// 指定读取证书格式为PKCS12
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		// 读取本机存放的PKCS12证书文件
		FileInputStream instream = new FileInputStream(file);

		try {
			// 指定PKCS12的密码(商户ID)
			keyStore.load(instream, MCH_ID.toCharArray());
		} finally {
			instream.close();
		}
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, MCH_ID.toCharArray()).build();
		// 指定TLS版本
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		// 设置httpclient的SSLSocketFactory
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		try {
			HttpPost httpost = new HttpPost(url); // 设置响应头信息
			httpost.addHeader("Connection", "keep-alive");
			httpost.addHeader("Accept", "*/*");
			httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			httpost.addHeader("Host", "api.mch.weixin.qq.com");
			httpost.addHeader("X-Requested-With", "XMLHttpRequest");
			httpost.addHeader("Cache-Control", "max-age=0");
			httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
			httpost.setEntity(new StringEntity(xml, "UTF-8"));
			CloseableHttpResponse response = httpclient.execute(httpost);
			try {
				HttpEntity entity = response.getEntity();
				String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
				EntityUtils.consume(entity);
				return jsonStr;
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}

	/**
	 * @Description: 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 * @author 宋高俊
	 * @date 2018年8月30日 下午7:07:49
	 */
	@SuppressWarnings("rawtypes")
	public static String createSign(SortedMap<Object, Object> packageParams, String AppKey) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = String.valueOf(entry.getValue());
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + AppKey);
		logger.info(sb.toString());
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		return sign;
	}

	/**
	 * @Description: 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 * @author 宋高俊
	 * @date 2018年8月30日 下午7:07:49
	 */
	@SuppressWarnings("rawtypes")
	public static String createSign2(SortedMap<Object, Object> packageParams, String AppKey) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = String.valueOf(entry.getValue());
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + AppKey);
		logger.info(sb.toString());
		String sign = "";
		try {
			sign = HMACSHA256(sb.toString(), AppKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sign;
	}

	/**
	 * 生成 MD5
	 * @param data 待处理数据
	 * @return MD5结果
	 */
	public static String MD5(String data) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] array = md.digest(data.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 生成 HMACSHA256 
	 * @param data 待处理数据
	 * @param key 密钥
	 * @return 加密结果
	 * @throws Exception
	 */
	public static String HMACSHA256(String data, String key) throws Exception {
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
		sha256_HMAC.init(secret_key);
		byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 获取当前时间戳，单位秒
	 * @return 例如 long 1537349355
	 */
	public static long getCurrentTimestamp() {
		return System.currentTimeMillis() / 1000;
	}

	/**
	 * 获取随机字符串 Nonce Str
	 * @return String 随机字符串
	 */
	public static String getNonceStr() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
	}

	/**
	 * 
	 * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串(已携带key)<br>
	 * @param paraMap 要排序的Map对象
	 * @return
	 */
	public static String formatUrlMap(Map<String, String> paraMap) {
		String buff = "";
		try {
			List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(paraMap.entrySet());
			// 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
			Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
				@Override
				public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
					return (o1.getKey()).toString().compareTo(o2.getKey());
				}
			});
			// 构造URL 键值对的格式
			StringBuilder buf = new StringBuilder();
			for (Map.Entry<String, String> item : infoIds) {
				if (StringUtils.isNotBlank(item.getKey())) {
					String key = item.getKey();
					String val = item.getValue();
					val = URLEncoder.encode(val, "utf-8");
					buf.append(key + "=" + val);
					buf.append("&");
				}
			}
			buf.append("key=" + WXConfig.paternerKey);
			buff = buf.toString();
		} catch (Exception e) {
			return null;
		}
		return buff;
	}

	/**
	 * @Description: 是否签名正确,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。(键是Object值是Object)
	 * @author 宋高俊
	 * @param characterEncoding 编码格式
	 * @param map 需要验证签名的Map,会自动按照ASCII码从小到大排序,Map中必须携带签名
	 * @param API_KEY 商户秘钥
	 * @return 签名正确则返回true反之false
	 * @date 2018年9月18日 上午11:20:16
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean isTenpaySign(String characterEncoding, Map map, String API_KEY) {
		String flagSign = ((String) map.get("sign")).toLowerCase();
		// 未携带签名直接返回不正确
		if (StringUtil.isBank(flagSign)) {
			return false;
		}
		// 因计算签名不能携带签名故删除
		map.remove("sign");
		// 将Map按照ASCII码从小到大排序,拼接成字符串
		String mapString = formatUrlMap(map);
		if (StringUtil.isBank(mapString)) {
			return false;
		}
		// 计算签名
		String mySign = MD5Util.MD5Encode(mapString, characterEncoding).toLowerCase();
		// 匹配结果
		return flagSign.equals(mySign);
	}

	/**
	 * @Description: 发送可跳转URL的模板消息
	 * @author 宋高俊
	 * @param openid 用户的openid
	 * @param templateId 模板ID
	 * @param url 要跳转的url
	 * @param datajson datajson.put("venue", JSONObject.parseObject("{\"value\":\"" + venue + "\",\"color\":\"#173177\"}"));
	 * @return json字符串
	 * @date 2018年9月1日 下午1:58:40
	 */
	@SuppressWarnings("unchecked")
	public static String sendWXTemplate(String openid, String templateId, String url, JSONObject datajson) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("touser", openid);
		jsonObject.put("template_id", templateId);
		if (!StringUtil.isBank(url)) {
			jsonObject.put("url", url);
		}
		// jsonObject.put("topcolor", "#FF0000");
		jsonObject.put("data", datajson);
		Map<String, Object> map = (Map<String, Object>) RedisUtil.getRedisOne(Global.REDIS_ACCESS_TOKEN, WXConfig.appid);
		String access_token = (String) map.get("access_token");
		return HttpUtil.sendPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token, jsonObject.toString());
	}

	/**
	 * @Description: 发送可跳转微信小程序的模板消息
	 * @author 宋高俊
	 * @param openid 用户的openid
	 * @param templateId  模板ID
	 * @param url 要跳转的url
	 * @param datajson datajson.put("venue", JSONObject.parseObject("{\"value\":\"" + venue + "\",\"color\":\"#173177\"}"));
	 * @return json字符串
	 * @date 2018年9月1日 下午1:58:40
	 */
	@SuppressWarnings("unchecked")
	public static String sendWXappTemplate(String openid, String templateId, String pagepath, JSONObject datajson) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("touser", openid);
		jsonObject.put("template_id", templateId);
		jsonObject.put("miniprogram", JSONObject.parseObject("{\"appid\":\"" + WXConfig.wxAppAppid + "\",\"pagepath\":\"" + pagepath + "\"}"));
		// jsonObject.put("topcolor", "#FF0000");
		jsonObject.put("data", datajson);
		Map<String, Object> map = (Map<String, Object>) RedisUtil.getRedisOne(Global.REDIS_ACCESS_TOKEN, WXConfig.appid);
		String access_token = (String) map.get("access_token");
		return HttpUtil.sendPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token, jsonObject.toString());
	}

	/**
	 * @Description: 微信退款接口
	 * @author 宋高俊
	 * @param id 订单id
	 * @param total_fee 退款总金额(分)
	 * @param refund_fee 退款金额(分)
	 * @param content 退款原因
	 * @param refundSource 退款来源(0=订场1=约球2=培训)
	 * @return
	 * @date 2018年9月7日 下午2:43:59
	 */
	@SuppressWarnings("rawtypes")
	public static Map weiXinRefund(String id, Double total_fee, Double refund_fee, String content, Integer refundSource) {

		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		String nonceStr = Utils.getUUID();// 随机字符串
		String out_refund_no = Utils.getUUID();
		parameters.put("appid", WXConfig.appid);// 公众账号ID
		parameters.put("mch_id", WXConfig.mch_id);// 商户号
		parameters.put("nonce_str", nonceStr);// 随机字符串
		parameters.put("out_trade_no", id);// 商户订单号
		parameters.put("transaction_id", "");//
		parameters.put("out_refund_no", out_refund_no);// 商户退款单号
		parameters.put("fee_type", "CNY");// 退款货币种类
		parameters.put("total_fee", String.valueOf(Utils.doubleToInt(total_fee)));// 订单金额
		parameters.put("refund_fee", String.valueOf(Utils.doubleToInt(refund_fee)));// 退款金额
		parameters.put("refund_desc", content);
		parameters.put("sign", WXUtil.createSign(parameters, WXConfig.paternerKey));

		String xml = XMLUtil.mapToXml(parameters);

		logger.info(xml);

		String result = "";
		String url = "https://api.mch.weixin.qq.com/secapi/pay/refund";
		try {
			File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "/cert/apiclient_cert.p12");
			// 获取微信退款的接口回调
			result = WXUtil.payHttps(url, xml, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		@SuppressWarnings("rawtypes")
		Map map = new HashMap<>();
		try {
			logger.info("返回参数：" + result);
			map = XMLUtil.doXMLParse(result);
			return map;
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		AmountRefund amountRefund = new AmountRefund();
		amountRefund.setId(Utils.getUUID());
		amountRefund.setContent(content);
		amountRefund.setSourceId(id);
		amountRefund.setRefundSource(refundSource);
		amountRefund.setRefundRemark(result);
		amountRefund.setRefundPayType(1);
		amountRefund.setAmountSum(total_fee);
		amountRefund.setAmount(refund_fee);

		AmountRefundService amountRefundService = SpringUtils.getBean("amountRefundServiceImpl", AmountRefundService.class);
		// result_code 返回SUCCESS/FAIL, SUCCESS
		// 新增退款记录数据
		if ("SUCCESS".equals(map.get("result_code"))) {
			amountRefund.setRefundStatus(1);
		} else {
			// 新增退款记录数据
			amountRefund.setRefundStatus(2);
		}
		amountRefundService.insert(amountRefund);

		return map;
	}

	/**
	 * @Description: 企业付款接口
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月11日 下午5:13:34
	 */
	@SuppressWarnings("rawtypes")
	public static Map wxPayment(String openid, String name, double amount, String desc) {

		// String openid = "oozuywt6GdCgM-Z4Kk9CrvvTAkJo";
		// String re_user_name = "宋高俊";
		// double amount = 1;
		// String desc = "测试支付";

		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		String nonceStr = Utils.getUUID();// 随机字符串
		String partner_trade_no = Utils.getUUID();
		parameters.put("mch_appid", WXConfig.appid);// 商户账号ID
		parameters.put("mchid", WXConfig.mch_id);// 商户号
		parameters.put("nonce_str", nonceStr);// 随机字符串
		parameters.put("partner_trade_no", partner_trade_no);// 商户订单号
		parameters.put("openid", openid);// 用户openid
		parameters.put("check_name", "FORCE_CHECK");// 校验用户姓名选项
		parameters.put("re_user_name", name);// 校验用户姓名选项
		parameters.put("amount", String.valueOf(Utils.doubleToInt(amount)));// 订单金额
		parameters.put("desc", desc);// 企业付款描述信息
		parameters.put("spbill_create_ip", "120.25.157.66");// Ip地址,该IP可传用户端或者服务端的IP。
		parameters.put("sign", WXUtil.createSign(parameters, WXConfig.paternerKey));

		String xml = XMLUtil.mapToXml(parameters);

		logger.info(xml);

		String result = "";
		try {
			File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "cert/apiclient_cert.p12");
			// 获取微信退款的接口回调
			result = WXUtil.payHttps(WXConfig.payment, xml, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map map = new HashMap<>();
		try {
			logger.info("返回参数：" + result);
			map = XMLUtil.doXMLParse(result);
			return map;
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * @Description: 下载对账单
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月12日 下午3:02:14
	 */
	public static String downloadbill() {

		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		String nonceStr = Utils.getUUID();// 随机字符串
		parameters.put("appid", WXConfig.appid);// 公众账号ID
		parameters.put("mch_id", WXConfig.mch_id);// 商户号
		parameters.put("nonce_str", nonceStr);// 随机字符串
		parameters.put("bill_date", "20180911");// 对账单日期
		parameters.put("bill_type", "ALL");// 账单类型
		// parameters.put("tar_type", "GZIP");// 压缩账单

		parameters.put("sign", WXUtil.createSign(parameters, WXConfig.paternerKey));

		String xml = XMLUtil.mapToXml(parameters);

		logger.info(xml);

		String result = "";
		try {
			File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "cert/apiclient_cert.p12");
			result = WXUtil.payHttps("https://api.mch.weixin.qq.com/pay/downloadbill", xml, file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("返回参数：" + result);
		// Map map = new HashMap<>();
		// try {
		// map = XMLUtil.doXMLParse(result);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		return result;
	}

	/**
	 * @Description: 下载资金对账账单
	 * @author 宋高俊
	 * @return
	 * @date 2018年9月12日 下午3:02:14
	 */
	@SuppressWarnings("rawtypes")
	public static String downloadfundflow() {

		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		String nonceStr = Utils.getUUID();// 随机字符串
		parameters.put("appid", WXConfig.appid);// 公众账号ID
		parameters.put("mch_id", WXConfig.mch_id);// 商户号
		parameters.put("nonce_str", nonceStr);// 随机字符串
		parameters.put("bill_date", "20180907");// 对账单日期
		parameters.put("account_type", "Basic");// 账单类型
		parameters.put("sign_type", "HMAC-SHA256");// 签名类型

		String sign = WXUtil.createSign2(parameters, WXConfig.paternerKey);
		logger.info("sign:" + sign);
		parameters.put("sign", sign);

		String xml = XMLUtil.mapToXml(parameters);

		logger.info(xml);

		String result = "";
		try {
			File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "cert/apiclient_cert.p12");
			result = WXUtil.payHttps("https://api.mch.weixin.qq.com/pay/downloadfundflow", xml, file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("返回参数：" + result);
		if (result.indexOf(">") > 0) {
			Map map = new HashMap<>();
			try {
				map = XMLUtil.doXMLParse(result);
				// 系统超时,再次尝试
				if (map.get("err_code").equals("SYSTEMERROR")) {
					downloadfundflow();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

		}

		return result;
	}

	/**
	 * @Description: 用于获取微信公众号的支付参数
	 * @author 宋高俊  
	 * @param body 商品详情
	 * @param out_trade_no 订单号
	 * @param openid openid
	 * @param price 金额
	 * @param ip 终端地址
	 * @param notify_url 回调地址
	 * @return 返回Map,参数errCode=200则预下单成功,errCode=400则预下单失败,errMag错误提示消息
	 * @throws JDOMException
	 * @throws IOException 
	 * @date 2018年9月19日 下午5:20:38 
	 */ 
	@SuppressWarnings("rawtypes")
	public static Map wxToPay(String body, String out_trade_no, String openid, double price, String ip, String notify_url) throws JDOMException, IOException {
		// 最终返回Map
		SortedMap<Object, Object> finalPackage = new TreeMap<Object, Object>();
		
		// 预下单参数
		int total_fee = Utils.doubleToInt(price);

		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", WXConfig.appid);// 微信公众号id
		packageParams.put("mch_id", WXConfig.mch_id);// 商户号
		packageParams.put("nonce_str", getNonceStr());// 随机字符串
		packageParams.put("body", body);// 商品描述
		packageParams.put("out_trade_no", out_trade_no);// 商户订单号
		packageParams.put("total_fee", String.valueOf(total_fee));// 订单金额
		packageParams.put("spbill_create_ip", ip);// 用户的终端IP
		packageParams.put("notify_url", notify_url);// 通知地址
		packageParams.put("openid", openid);// 用户的openid
		packageParams.put("trade_type", "JSAPI");

		// 获取支付签名,统一使用微信商户号秘钥
		String sign = WXUtil.createSign(packageParams, WXConfig.paternerKey);
		logger.info("签名 = " + sign);
		packageParams.put("sign", sign);// 签名

		// 将map参数转化为xml字符串
		String requestXML = XMLUtil.mapToXml(packageParams);
		logger.info("参数xml = " + requestXML);

		// 发送预下单请求获取响应的xml字符串
		String resXml = HttpUtil.postData(WXConfig.url, requestXML);
		logger.info("响应xml = " + resXml);
		Map xmlMap = XMLUtil.doXMLParse(resXml);

		// 订单支付异常
		if (!xmlMap.get("return_code").toString().equals("SUCCESS")) {
			logger.info("订单交易错误" + xmlMap.get("return_msg").toString());
			finalPackage.put("errCode", "400");
			finalPackage.put("errMsg", xmlMap.get("return_msg").toString());
			return finalPackage;
		}
		if (!xmlMap.get("result_code").toString().equals("SUCCESS")) {
			logger.info("订单业务结果错误" + xmlMap.get("err_code_des").toString());
			finalPackage.put("errCode", "400");
			finalPackage.put("errMsg", xmlMap.get("err_code_des").toString());
			return finalPackage;
		}

		// 判断签名是否正确
		if (WXUtil.isTenpaySign("UTF-8", xmlMap, WXConfig.paternerKey)) {

			// 获取预支付交易会话标识
			String prepay_id = (String) xmlMap.get("prepay_id");
			logger.info("prepay_id = " + prepay_id);

			// 把prepay_id保存到订单中,这一步 根据的自己的业务需求，是否需要保存到数据库中

			// 下面是公众号内调起的h5支付，看清楚和上边获取prepay_id的时候，注意单词的大小写
			// 字符串
			String nonceStr = packageParams.get("nonce_str").toString();
			// 订单详情扩展字符串
			String packages = "prepay_id=" + prepay_id;

			finalPackage.put("appId", WXConfig.appid);// 公众号id
			finalPackage.put("timeStamp", Long.toString(getCurrentTimestamp()));// 时间戳
			finalPackage.put("nonceStr", nonceStr);// 随机字符串
			finalPackage.put("package", packages);// 订单详情扩展字符串
			finalPackage.put("signType", "MD5");// 签名方式
			// 计算签名
			sign = WXUtil.createSign(finalPackage, WXConfig.paternerKey);
			finalPackage.put("paySign", sign);// 签名
			
			// 自定义参数不计入签名
			finalPackage.put("errCode", "200");
			finalPackage.put("errMsg","预下单成功");

			logger.info("发送map = " + finalPackage);
		} else {
			logger.info("微信响应签名不正确");
			finalPackage.put("errCode", "400");
			finalPackage.put("errMsg", "非正确请求");
		}

		return finalPackage;
	}
	
	/**
	 * @Description: 用于获取微信小程序的支付参数
	 * @author 宋高俊  
	 * @param body 商品详情
	 * @param out_trade_no 订单号
	 * @param openid openid
	 * @param price 金额
	 * @param ip 终端地址
	 * @param notify_url 回调地址
	 * @return 返回Map,参数errCode=200则预下单成功,errCode=400则预下单失败,errMag错误提示消息
	 * @throws JDOMException
	 * @throws IOException 
	 * @date 2018年9月19日 下午5:20:38 
	 */ 
	@SuppressWarnings("rawtypes")
	public static Map wxappToPay(String body, String out_trade_no, String openid, double price, String ip, String notify_url) throws JDOMException, IOException {
		// 最终返回Map
		SortedMap<Object, Object> finalPackage = new TreeMap<Object, Object>();

		// 预下单参数
		int total_fee = Utils.doubleToInt(price);
		
		// 封装参数
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", WXConfig.wxAppAppid);// 小程序ID
		packageParams.put("mch_id", WXConfig.mch_id);// 商户号
		packageParams.put("nonce_str", WXUtil.getNonceStr());// 随机字符串
		packageParams.put("body", body);// 商品描述
		packageParams.put("out_trade_no", out_trade_no);// 商户订单号
		packageParams.put("total_fee", String.valueOf(total_fee));// 标价金额
		packageParams.put("spbill_create_ip", ip);// 终端IP
		packageParams.put("time_start", DateUtil.getFormat(new Date(), "yyyyMMddHHmmss"));// 交易起始时间
		packageParams.put("time_expire", DateUtil.getFormat(DateUtil.getPreTime(new Date(), 1, 5), "yyyyMMddHHmmss"));// 交易结束时间
		packageParams.put("notify_url", notify_url);// 通知地址
		packageParams.put("trade_type", "JSAPI");// 交易类型
		packageParams.put("openid", openid);// 用户标识
		

		// 获取支付签名,统一使用微信商户号秘钥
		String sign = WXUtil.createSign(packageParams, WXConfig.paternerKey);
		logger.info("签名 = " + sign);
		packageParams.put("sign", sign);// 签名
		
		// 将map参数转化为xml字符串
		String requestXML = XMLUtil.mapToXml(packageParams);
		logger.info("参数xml = " + requestXML);

		// 发送预下单请求获取响应的xml字符串
		String resXml = HttpUtil.postData(WXConfig.url, requestXML);
		logger.info("响应xml = " + resXml);
		Map xmlMap = XMLUtil.doXMLParse(resXml);


		// 订单支付异常
		if (!xmlMap.get("return_code").toString().equals("SUCCESS")) {
			logger.info("订单交易错误" + xmlMap.get("return_msg").toString());
			finalPackage.put("errCode", "400");
			finalPackage.put("errMsg", xmlMap.get("return_msg").toString());
			return finalPackage;
		}
		if (!xmlMap.get("result_code").toString().equals("SUCCESS")) {
			logger.info("订单业务结果错误" + xmlMap.get("err_code_des").toString());
			finalPackage.put("errCode", "400");
			finalPackage.put("errMsg", xmlMap.get("err_code_des").toString());
			return finalPackage;
		}

		// 判断签名是否正确
		if (WXUtil.isTenpaySign("UTF-8", xmlMap, WXConfig.paternerKey)) {

			// 用于生成支付签名参数的数据
			SortedMap<Object, Object> paymap = new TreeMap<Object, Object>();
			long time = new Date().getTime() / 1000;
			String nonceStr = WXUtil.getNonceStr();

			// 获取预支付交易会话标识
			String prepay_id = (String) xmlMap.get("prepay_id");
			logger.info("prepay_id = " + prepay_id);
			
			//封装签名需要的参数
			paymap.put("appId", WXConfig.wxAppAppid);
			paymap.put("nonceStr", nonceStr);
			paymap.put("package", "prepay_id=" + prepay_id);
			paymap.put("signType", "MD5");
			paymap.put("timeStamp", time);

			//封装小程序唤起微信支付的参数
			finalPackage.put("timeStamp", time);
			finalPackage.put("nonceStr", nonceStr);
			finalPackage.put("package", "prepay_id=" + prepay_id);
			finalPackage.put("signType", "MD5");
			finalPackage.put("paySign", WXUtil.createSign(paymap, WXConfig.paternerKey));
			finalPackage.put("errCode", "200");
			finalPackage.put("errMsg","预下单成功");

			logger.info("发送map = " + finalPackage);
		} else {
			logger.info("微信响应签名不正确");
			finalPackage.put("errCode", "400");
			finalPackage.put("errMsg", "非正确请求");
		}
		return finalPackage;
	}
}