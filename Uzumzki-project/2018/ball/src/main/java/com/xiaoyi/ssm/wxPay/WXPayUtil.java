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
import com.xiaoyi.ssm.model.WXCompanyPayment;
import com.xiaoyi.ssm.service.AmountRefundService;
import com.xiaoyi.ssm.service.InviteBallService;
import com.xiaoyi.ssm.service.WXCompanyPaymentService;
import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.SpringUtils;
import com.xiaoyi.ssm.util.StringUtil;
import com.xiaoyi.ssm.util.Utils;

/**
 * @Description: 微信支付公用工具类
 * @author 宋高俊
 * @date 2018年8月30日 下午7:14:37
 */
@SuppressWarnings("deprecation")
public class WXPayUtil {

	private static Logger logger = Logger.getLogger(WXPayUtil.class.getName());

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
		parameters.put("sign", WXPayUtil.createSign(parameters, WXConfig.paternerKey));

		String xml = XMLUtil.mapToXml(parameters);

		logger.info(xml);

		String result = "";
		String url = "https://api.mch.weixin.qq.com/secapi/pay/refund";
		try {
			File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "/cert/apiclient_cert.p12");
			// 获取微信退款的接口回调
			result = WXPayUtil.payHttps(url, xml, file);
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

		// 退款业务数据处理
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
			// 退款成功
			amountRefund.setRefundStatus(1);
		} else {
			// 退款失败
			// 新增退款记录数据
			amountRefund.setRefundStatus(2);
		}
		amountRefundService.insert(amountRefund);

		return map;
	}

	/**
	 * @Description: 企业付款接口
	 * @author 宋高俊  
	 * @param partner_trade_no 商户订单号
	 * @param openid 微信公众号用户的openid
	 * @param amount 金额分为单位
	 * @param desc 描述
	 * @return 
	 * @date 2018年9月27日 下午8:56:15 
	 */ 
	@SuppressWarnings({ "rawtypes" })
	public static String wxCompanyPayment(String partner_trade_no,String openid, double amount, String desc) {

		// String openid = "oozuywt6GdCgM-Z4Kk9CrvvTAkJo";
		// String re_user_name = "宋高俊";
		// double amount = 1;
		// String desc = "测试支付";

		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		String nonceStr = Utils.getUUID();// 随机字符串
		parameters.put("mch_appid", WXConfig.appid);// 商户账号ID
		parameters.put("mchid", WXConfig.mch_id);// 商户号
		parameters.put("nonce_str", nonceStr);// 随机字符串
		parameters.put("partner_trade_no", partner_trade_no);// 商户订单号
		parameters.put("openid", openid);// 用户openid
		parameters.put("check_name", "NO_CHECK");// 校验用户姓名选项NO_CHECK：不校验真实姓名  FORCE_CHECK：强校验真实姓名
//		parameters.put("re_user_name", name);// 校验用户姓名选项
		parameters.put("amount", String.valueOf(Utils.doubleToInt(amount)));// 订单金额
		parameters.put("desc", desc);// 企业付款描述信息
		parameters.put("spbill_create_ip", "120.25.157.66");// IP地址,该IP可传用户端或者服务端的IP。目前使用服务器ip
		parameters.put("sign", WXPayUtil.createSign(parameters, WXConfig.paternerKey));// 将上面的参数生成签名

		String xml = XMLUtil.mapToXml(parameters);

		logger.info(xml);

		String result = "";
		try {
			File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "cert/apiclient_cert.p12");
			// 获取微信接口回调
			result = WXPayUtil.payHttps(WXConfig.payment, xml, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		WXCompanyPaymentService wxCompanyPaymentService = SpringUtils.getBean("wxCompanyPaymentServiceImpl", WXCompanyPaymentService.class);
		WXCompanyPayment wxCompanyPayment = new WXCompanyPayment();
		wxCompanyPayment.setId(Utils.getUUID());
		wxCompanyPayment.setCreateTime(new Date());
		wxCompanyPayment.setAmount(amount);
		wxCompanyPayment.setDesc(desc);
		wxCompanyPayment.setOpenid(openid);
		
		Map map = new HashMap<>();
		try {
			logger.info("返回参数：" + result);
			map = XMLUtil.doXMLParse(result);
			wxCompanyPayment.setReturnXml(result);
			
			if ("FAIL".equals(map.get("return_code"))) {
				wxCompanyPayment.setPayType(2);
				wxCompanyPayment.setPayMsg(map.get("return_msg") != null ? map.get("return_msg").toString() : "");
				logger.info(map.get("return_msg"));
			}else {
				if ("FAIL".equals(map.get("result_code"))) {
					wxCompanyPayment.setPayType(2);
					wxCompanyPayment.setPayMsg(map.get("err_code_des") != null ? map.get("err_code_des").toString() : "");
					logger.info(map.get("err_code_des"));
				}
			}
			wxCompanyPaymentService.insertSelective(wxCompanyPayment);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wxCompanyPayment.getId();
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

		parameters.put("sign", WXPayUtil.createSign(parameters, WXConfig.paternerKey));

		String xml = XMLUtil.mapToXml(parameters);

		logger.info(xml);

		String result = "";
		try {
			File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "cert/apiclient_cert.p12");
			result = WXPayUtil.payHttps("https://api.mch.weixin.qq.com/pay/downloadbill", xml, file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("返回参数：" + result);

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

		String sign = WXPayUtil.createSign2(parameters, WXConfig.paternerKey);
		logger.info("sign:" + sign);
		parameters.put("sign", sign);

		String xml = XMLUtil.mapToXml(parameters);

		logger.info(xml);

		String result = "";
		try {
			File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "cert/apiclient_cert.p12");
			result = WXPayUtil.payHttps("https://api.mch.weixin.qq.com/pay/downloadfundflow", xml, file);
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
	 * @Description: 微信订单查询方法
	 * @author 宋高俊  
	 * @param out_trade_no
	 * @return 
	 * @date 2018年10月11日 上午9:48:10 
	 */ 
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> wxOrderQuery(String partner_trade_no) {

		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		String nonceStr = Utils.getUUID();// 随机字符串
		parameters.put("appid", WXConfig.appid);// 商户账号ID
		parameters.put("mch_id", WXConfig.mch_id);// 商户号

		parameters.put("partner_trade_no", partner_trade_no);// 微信订单号优先级比商户订单号高
		parameters.put("nonce_str", nonceStr);// 随机字符串
		
		parameters.put("sign", WXPayUtil.createSign(parameters, WXConfig.paternerKey));
		String xml = XMLUtil.mapToXml(parameters);

		logger.info(xml);

		String result = "";
		try {
			File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "cert/apiclient_cert.p12");
			// 获取微信接口回调
			result = WXPayUtil.payHttps("https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo", xml, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map map = new HashMap<>();
		try {
			logger.info("返回参数：" + result);
			map = XMLUtil.doXMLParse(result);
			

			Map<String, Object> returnMap = new HashMap<String, Object>();
			if ("FAIL".equals(map.get("return_code"))) {
				logger.info(map.get("return_msg"));
			}else {
				if ("FAIL".equals(map.get("result_code"))) {
					logger.info(map.get("err_code_des"));
				}else {
					
				}
			}
			return map;
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	public static void main(String[] args) {
//		System.out.println(WXPayUtil.wxOrderQuery("76e09798d5ad457da9c6e4b173787b23"));
		String uuidString = Utils.getUUID();
		System.out.println(uuidString);
		System.out.println(WXPayUtil.wxCompanyPayment(uuidString, "oozuywt6GdCgM-Z4Kk9CrvvTAkJo", 0.4, "测试"));
	}

}