package com.xiaoyi.ssm.wxPay;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.jdom.JDOMException;

import com.xiaoyi.ssm.util.DateUtil;
import com.xiaoyi.ssm.util.Utils;

/**  
 * @Description: 微信小程序支付工具类
 * @author 宋高俊  
 * @date 2018年10月11日 上午10:29:04 
 */ 
public class WXPayWxappUtil extends WXPayUtil{

	private static Logger logger = Logger.getLogger(WXPayWxappUtil.class.getName());
	
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
	public static Map getPayParams(String body, String out_trade_no, String openid, double price, String ip, String notify_url){
		// 最终返回Map
		SortedMap<Object, Object> finalPackage = new TreeMap<Object, Object>();

		// 预下单参数
		int total_fee = Utils.doubleToInt(price);
		
		// 封装参数
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", WXConfig.wxAppAppid);// 小程序ID
		packageParams.put("mch_id", WXConfig.mch_id);// 商户号
		packageParams.put("nonce_str", WXPayUtil.getNonceStr());// 随机字符串
		packageParams.put("body", body);// 商品描述
		packageParams.put("out_trade_no", out_trade_no);// 商户订单号
		packageParams.put("total_fee", String.valueOf(total_fee));// 标价金额
		packageParams.put("spbill_create_ip", ip);// 终端IP
		packageParams.put("time_start", DateUtil.getFormat(new Date(), "yyyyMMddHHmmss"));// 交易起始时间
		packageParams.put("time_expire", DateUtil.getFormat(DateUtil.getPreTime(new Date(), 1, 6), "yyyyMMddHHmmss"));// 交易结束时间
		packageParams.put("notify_url", notify_url);// 通知地址
		packageParams.put("trade_type", "JSAPI");// 交易类型
		packageParams.put("openid", openid);// 用户标识
		

		// 获取支付签名,统一使用微信商户号秘钥
		String sign = WXPayUtil.createSign(packageParams, WXConfig.paternerKey);
		logger.info("签名 = " + sign);
		packageParams.put("sign", sign);// 签名
		
		// 将map参数转化为xml字符串
		String requestXML = XMLUtil.mapToXml(packageParams);
		logger.info("参数xml = " + requestXML);

		// 发送预下单请求获取响应的xml字符串
		String resXml = HttpUtil.postData(WXConfig.url, requestXML);
		logger.info("响应xml = " + resXml);
		Map xmlMap = new HashMap();
		try {
			xmlMap = XMLUtil.doXMLParse(resXml);
		} catch (JDOMException e) {
			return finalPackage;
		} catch (IOException e) {
			return finalPackage;
		}

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
		if (WXPayUtil.isTenpaySign("UTF-8", xmlMap, WXConfig.paternerKey)) {

			// 用于生成支付签名参数的数据
			SortedMap<Object, Object> paymap = new TreeMap<Object, Object>();
			long time = new Date().getTime() / 1000;
			String nonceStr = WXPayUtil.getNonceStr();

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
			finalPackage.put("paySign", WXPayUtil.createSign(paymap, WXConfig.paternerKey));
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
