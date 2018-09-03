package com.xiaoyi.ssm.wxPay;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.alibaba.fastjson.JSONObject;
import com.xiaoyi.ssm.model.Venue;
import com.xiaoyi.ssm.util.Global;
import com.xiaoyi.ssm.util.RedisUtil;
import com.xiaoyi.ssm.util.StringUtil;

/**
 * @Description: 微信工具类
 * @author 宋高俊
 * @date 2018年8月30日 下午7:14:37
 */
@SuppressWarnings("deprecation")
public class WXUtil {

	/**
	 * @Description: 将请求参数转换为xml格式的string
	 * @author 宋高俊
	 * @param parameters 排序的map
	 * @date 2018年8月30日 下午7:06:57
	 */
	@SuppressWarnings("rawtypes")
	public static String mapToXml(SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

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
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + AppKey);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		return sign;
	}

	/**
	 * 获取随机字符串 Nonce Str
	 *
	 * @return String 随机字符串
	 */
	public static String generateNonceStr() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
	}

	/**
	 * 生成 MD5
	 *
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
	 * 
	 * @param data 待处理数据
	 * @param key  密钥
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
	 * 生成32位随机数字
	 */
	public static String genNonceStr() {
		Random random = new Random();
		return MD5Util.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
	}

	/**
	 * 获取当前时间戳，单位秒
	 * 
	 * @return
	 */
	public static long getCurrentTimestamp() {
		return System.currentTimeMillis() / 1000;
	}

	/**
	 * 是否签名正确,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 *
	 */
	// 方法一 map的键值对是object类型
	@SuppressWarnings({ "rawtypes" })
	public static boolean isTenpaySign(String characterEncoding, SortedMap<Object, Object> packageParams,
			String API_KEY) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"sign".equals(k) && null != v && !"".equals(v)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + API_KEY);
		// 算出摘要
		String mysign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toLowerCase();
		String tenpaySign = ((String) packageParams.get("sign")).toLowerCase();
		return tenpaySign.equals(mysign);
	}

	// 方法一 map的键值对是object类型
	@SuppressWarnings({ "rawtypes" })
	public static boolean isTenpaySignB(String characterEncoding, Map<String, String> packageParams, String API_KEY) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"sign".equals(k) && null != v && !"".equals(v)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + API_KEY);
		// 算出摘要
		String mysign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toLowerCase();
		String tenpaySign = ((String) packageParams.get("sign")).toLowerCase();
		return tenpaySign.equals(mysign);
	}

	/**  
	 * @Description: 发送模板消息微信用户
	 * @author 宋高俊  
	 * @param openid 用户的openid
	 * @param templateId 模板ID
	 * @param url 要跳转的url
	 * @param venue 球场
	 * @param date 时间
	 * @param price 费用
	 * @param type 状态
	 * @param message 提示
	 * @return json字符串
	 * @date 2018年9月1日 下午1:58:40 
	 */ 
	public static String sendTemplate(String openid, String templateId, String url, String venue, String date,
			String price, String type, String message) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("touser", openid);
		jsonObject.put("template_id", templateId);
		if (!StringUtil.isBank(url)) {
			jsonObject.put("url", url);
		}
		jsonObject.put("topcolor", "#FF0000");
		JSONObject datajson = new JSONObject();
		datajson.put("venue", JSONObject.parseObject("{\"value\":\"" + venue + "\",\"color\":\"#173177\"}"));
		datajson.put("date", JSONObject.parseObject("{\"value\":\"" + date + "\",\"color\":\"#173177\"}"));
		datajson.put("price", JSONObject.parseObject("{\"value\":\"" + price + "\",\"color\":\"#173177\"}"));
		datajson.put("type", JSONObject.parseObject("{\"value\":\"" + type + "\",\"color\":\"#173177\"}"));
		datajson.put("message", JSONObject.parseObject("{\"value\":\"" + message + "\",\"color\":\"#173177\"}"));
		jsonObject.put("data", datajson);
//		String access_token = (String) RedisUtil.getRedisOne(Global.REDIS_ACCESS_TOKEN, WXConfig.appid);
		return HttpUtil.sendPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=13_E_Q7-CbhzsgWvnzydpypKn-ivj7PyOl2b_S-5kjXFZKwoBJgGOMdpXDfxlMfYujtDml05tWTXOpXqqfBExBNiVG8SpPdD22yxA5leWN3Jw9An935Cfnjk5kTKAE_9vdoDjQPIdCScrfHwPX_EFIhADATUX" ,
				jsonObject.toString());
	}
	public static void main(String[] args) {
		System.out.println(WXUtil.sendTemplate("oGuNW6JKRIG6khdb9DZmO06G1nIE", "Xr4Lm7zxLGOvwrbUE2tLWnF5_OHyJRFy4bEuzdx8qb0", "", "1", "1", "1", "1", "1"));
	}

}